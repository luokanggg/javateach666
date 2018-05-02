package com.ctbu.javateach666.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ctbu.javateach666.constant.Constant;
import com.ctbu.javateach666.dao.LKMyActivityDao;
import com.ctbu.javateach666.dao.LKMyInfoDao;
import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.CheckChooseActivityReqBO;
import com.ctbu.javateach666.pojo.bo.ChooseActivityReqBO;
import com.ctbu.javateach666.pojo.bo.ChooseActivityRspBO;
import com.ctbu.javateach666.pojo.bo.LKGetChooseActivityListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetChooseActivityListRspBO;
import com.ctbu.javateach666.pojo.bo.LKGetManageActivityReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetManageActivityRspBO;
import com.ctbu.javateach666.pojo.bo.LKMoveActivityMemberReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyPubNameCombBoxBO;
import com.ctbu.javateach666.pojo.bo.LKPubActivityReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.UpdatePubNumberReqBO;
import com.ctbu.javateach666.pojo.po.LKNoticePO;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.service.interfac.LKMyActivityService;

@Service
public class LKMyActivityServiceImpl implements LKMyActivityService{
	
	@Autowired
	private LKMyInfoDao lKMyInfoDao;
	
	@Autowired
	private LKMyActivityDao lKMyActivityDao;
	
	public BaseInfoBO pubActivity(LKPubActivityReqBO lKPubActivityReqBO) {

		//定义出参
		BaseInfoBO rsp = new BaseInfoBO();
		
		//取得当前登录学生id;
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LKStudentInfoPO lKStudentInfoPO = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		lKPubActivityReqBO.setPubownid(lKStudentInfoPO.getId());
		lKPubActivityReqBO.setPubownname(lKStudentInfoPO.getStuname());
		
		int count = lKMyActivityDao.pubActivity(lKPubActivityReqBO);
		if(count < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("发布活动失败！");
			return rsp;
		}
		
		
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("发布活动成功！");
		return rsp;
	}

	public PageInfoBo<LKGetChooseActivityListRspBO> getChooseActivityList(
			LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO) {
		
		//出参定义

		
		PageInfoBo<LKGetChooseActivityListRspBO> rsp = new PageInfoBo<LKGetChooseActivityListRspBO>();
		
		//设置page为下标
		int page = 0;
		page = (lKGetChooseActivityListReqBO.getPage() - 1) * lKGetChooseActivityListReqBO.getRows();
		lKGetChooseActivityListReqBO.setPage(page);
		
		if(lKGetChooseActivityListReqBO.getPubname() != null && lKGetChooseActivityListReqBO.getPubname() != ""){
			String pubname = "";
			pubname = "%" + lKGetChooseActivityListReqBO.getPubname() + "%";
			lKGetChooseActivityListReqBO.setPubname(pubname);
		}
		
		if(lKGetChooseActivityListReqBO.getPubownname() != null && lKGetChooseActivityListReqBO.getPubownname() != ""){
			String pubownname = "";
			pubownname = "%" + lKGetChooseActivityListReqBO.getPubownname() + "%";
			lKGetChooseActivityListReqBO.setPubownname(pubownname);
		}
		lKGetChooseActivityListReqBO.setNowtime(new Date());
		
		int total = lKMyActivityDao.getTotalChooseActivity(lKGetChooseActivityListReqBO);
		if(total <  1){
			return rsp;
		}
		
		List<LKGetChooseActivityListRspBO> listrsp = lKMyActivityDao.getChooseActivityList(lKGetChooseActivityListReqBO);
		
		rsp.setRows(listrsp);
		rsp.setTotal(total);
		return rsp;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public BaseInfoBO chooseActivity(LKcancelClassReqBO lKcancelClassReqBO) {
		
		//出参定义
		BaseInfoBO rsp = new BaseInfoBO();
		
		//取得当前登录学生id;
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LKStudentInfoPO lKStudentInfoPO = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		
		ChooseActivityRspBO choact = lKMyActivityDao.getChooseActivity(lKcancelClassReqBO.getId());
		
		if(lKStudentInfoPO.getId() == choact.getPubownid()){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("不能选择自己发布的活动!");
			return rsp;
		}
		if(choact.getPubnumber() <= choact.getAlpubnumber()){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("报名人数已满!");
			return rsp;
		}
		CheckChooseActivityReqBO cheact = new CheckChooseActivityReqBO();
		cheact.setPubid(lKcancelClassReqBO.getId());
		cheact.setStuid(lKStudentInfoPO.getId());
		int ischo = lKMyActivityDao.checkChooseActivity(cheact);
		if(ischo > 0){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("已报名该活动!");
			return rsp;
		}
		
		ChooseActivityReqBO cho = new ChooseActivityReqBO();
		BeanUtils.copyProperties(choact, cho);
		cho.setAlpubnumber(choact.getAlpubnumber() + 1);
		cho.setPubid(lKcancelClassReqBO.getId());
		cho.setStuid(lKStudentInfoPO.getId());
		
		int count = lKMyActivityDao.chooseActivity(cho);
		if(count < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("未知原因，活动报名失败!");
			return rsp;
		}
		
		UpdatePubNumberReqBO up = new UpdatePubNumberReqBO();
		up.setAlpubnumber(choact.getAlpubnumber() + 1);
		up.setId(lKcancelClassReqBO.getId());
		int isup = lKMyActivityDao.updateAlPubNumber(up);
		if(isup < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("未知原因，报名人数增加失败!");
			return rsp;
		}
		
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("活动报名成功!");
		return rsp;
	}

	public PageInfoBo<LKGetChooseActivityListRspBO> getMyChooseActivityList(
			LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO) {
		
		//出参定义
		PageInfoBo<LKGetChooseActivityListRspBO> rsp = new PageInfoBo<LKGetChooseActivityListRspBO>();
		
		//设置page为下标
		int page = 0;
		page = (lKGetChooseActivityListReqBO.getPage() - 1) * lKGetChooseActivityListReqBO.getRows();
		lKGetChooseActivityListReqBO.setPage(page);
		
		if(lKGetChooseActivityListReqBO.getPubname() != null && lKGetChooseActivityListReqBO.getPubname() != ""){
			String pubname = "";
			pubname = "%" + lKGetChooseActivityListReqBO.getPubname() + "%";
			lKGetChooseActivityListReqBO.setPubname(pubname);
		}
		
		if(lKGetChooseActivityListReqBO.getPubownname() != null && lKGetChooseActivityListReqBO.getPubownname() != ""){
			String pubownname = "";
			pubownname = "%" + lKGetChooseActivityListReqBO.getPubownname() + "%";
			lKGetChooseActivityListReqBO.setPubownname(pubownname);
		}
		lKGetChooseActivityListReqBO.setNowtime(new Date());
		
		//取得当前登录学生id;
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LKStudentInfoPO lKStudentInfoPO = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		
		lKGetChooseActivityListReqBO.setStuid(lKStudentInfoPO.getId());
		
		int total = lKMyActivityDao.getMyTotalChooseActivity(lKGetChooseActivityListReqBO);
		if(total <  1){
			return rsp;
		}
		
		List<LKGetChooseActivityListRspBO> listrsp = lKMyActivityDao.getMyChooseActivityList(lKGetChooseActivityListReqBO);
		
		rsp.setRows(listrsp);
		rsp.setTotal(total);
		return rsp;
	}

	public BaseInfoBO cancelChooseActivity(LKcancelClassReqBO lKcancelClassReqBO) {
		
		//出参定义
		BaseInfoBO rsp = new BaseInfoBO();
		
		int pubid = lKMyActivityDao.getCancelPubId(lKcancelClassReqBO);
		LKcancelClassReqBO pubidbo = new LKcancelClassReqBO();
		pubidbo.setId(pubid);
		int isup = lKMyActivityDao.updateCancelAlPubNumber(pubidbo);
		if(isup < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("取消报名失败，已报名人数更新失败!");
			return rsp;
		}
		
		int count = lKMyActivityDao.cancelChooseActivity(lKcancelClassReqBO);
		if(count < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("未知原因，取消报名失败!");
			return rsp;
		}
		
		
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("取消报名成功!");
		return rsp;
	}

	public LKPubActivityReqBO updatePubActivity(LKPubActivityReqBO lKPubActivityReqBO) {
		//定义出参
		LKPubActivityReqBO rsp = new LKPubActivityReqBO();
		
		LKcancelClassReqBO checkbo = new LKcancelClassReqBO();
		checkbo.setId(lKPubActivityReqBO.getId());
		int alpubnumber = lKMyActivityDao.CheckAlPubNumber(checkbo);
		if(alpubnumber > lKPubActivityReqBO.getPubnumber()){
			LKPubActivityReqBO value = lKMyActivityDao.getUpdatePubActivity(lKPubActivityReqBO);
			BeanUtils.copyProperties(value, rsp);
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("活动人数不能小于已报名人数！");
			return rsp;
		}
		
		int count = lKMyActivityDao.updatePubActivity(lKPubActivityReqBO);
		if(count < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("活动更新失败！");
			return rsp;
		}
		
		int count2 = lKMyActivityDao.updatePerActivity(lKPubActivityReqBO);
		if(count2 < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("活动更新失败！");
			return rsp;
		}
		
		LKPubActivityReqBO value = lKMyActivityDao.getUpdatePubActivity(lKPubActivityReqBO);
		BeanUtils.copyProperties(value, rsp);
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("活动更新成功！");
		return rsp;
	}

	public LKPubActivityReqBO getUpdatePubActivity(LKPubActivityReqBO lKPubActivityReqBO) {
		
		LKPubActivityReqBO rsp = lKMyActivityDao.getUpdatePubActivity(lKPubActivityReqBO);
		
		return rsp;
	}

	public List<LKMyPubNameCombBoxBO> getMyPubNameCombList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO) {
		
		//取得当前登录学生id;
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LKStudentInfoPO lKStudentInfoPO = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		lKGetChooseActivityListReqBO.setStuid(lKStudentInfoPO.getId());
		lKGetChooseActivityListReqBO.setNowtime(new Date());
		
		return lKMyActivityDao.getMyPubNameCombList(lKGetChooseActivityListReqBO);
	}

	public PageInfoBo<LKGetManageActivityRspBO> getManageActivityList(
			LKGetManageActivityReqBO lKGetManageActivityReqBO) {
		//出参定义
		PageInfoBo<LKGetManageActivityRspBO> rsp = new PageInfoBo<LKGetManageActivityRspBO>();
		
		//设置page为下标
		int page = 0;
		page = (lKGetManageActivityReqBO.getPage() - 1) * lKGetManageActivityReqBO.getRows();
		lKGetManageActivityReqBO.setPage(page);
		
		//取得当前登录学生id;
		/*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LKStudentInfoPO lKStudentInfoPO = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		lKGetManageActivityReqBO.setStuid(lKStudentInfoPO.getId());*/
		
		int total = lKMyActivityDao.getTotalManageActivityList(lKGetManageActivityReqBO);
		if(total <  1){
			return rsp;
		}
		
		List<LKGetManageActivityRspBO> listrsp = lKMyActivityDao.getManageActivityList(lKGetManageActivityReqBO);
		
		rsp.setRows(listrsp);
		rsp.setTotal(total);
		return rsp;
	}

	public BaseInfoBO moveActivityMember(LKMoveActivityMemberReqBO lKMoveActivityMemberReqBO) {
		//出参定义
		BaseInfoBO rsp = new BaseInfoBO();
		LKcancelClassReqBO peridbo = new LKcancelClassReqBO();
		peridbo.setId(lKMoveActivityMemberReqBO.getId());
		int pubid = lKMyActivityDao.getCancelPubId(peridbo);
		LKcancelClassReqBO pubidbo = new LKcancelClassReqBO();
		pubidbo.setId(pubid);
		int isup = lKMyActivityDao.updateCancelAlPubNumber(pubidbo);
		if(isup < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("取消报名失败，已报名人数更新失败!");
			return rsp;
		}
		
		int count = lKMyActivityDao.cancelChooseActivity(peridbo);
		if(count < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("未知原因，取消报名失败!");
			return rsp;
		}
		
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		//生成一个通知实体
		LKNoticePO lKNoticePO = new LKNoticePO();
		lKNoticePO.setNotid(logstu.getId());  //通知人id
		lKNoticePO.setTonotid(lKMoveActivityMemberReqBO.getStuid()); //被通知人id
		lKNoticePO.setNottype(Constant.NOTICE_TYPE.STU_TO_STU); //通知类型
		lKNoticePO.setNotname(logstu.getStuname()); //通知人姓名
		lKNoticePO.setNottitle("活动踢出"); //标题
		lKNoticePO.setNotcontent(lKMoveActivityMemberReqBO.getMessage()); //内容
		lKNoticePO.setNoturl("#"); //连接url
		lKNoticePO.setStarttime(new Date()); //通知创建时间
		Calendar cal = Calendar.getInstance();
		//下面的就是把当前日期加一个月
		cal.add(Calendar.MONTH, 1);
		lKNoticePO.setEndtime(cal.getTime()); //通知过期时间
		
		int count2 = lKMyInfoDao.createNoticeTypeThree(lKNoticePO);
		if(count2 < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("发送消息异常！");
			return rsp;
		}
		
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("成功踢出！");
		return rsp;
	}
	
}
