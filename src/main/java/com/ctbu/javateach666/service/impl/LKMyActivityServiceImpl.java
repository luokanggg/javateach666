package com.ctbu.javateach666.service.impl;

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
import com.ctbu.javateach666.pojo.bo.LKPubActivityReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.UpdatePubNumberReqBO;
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
	
	
	
}
