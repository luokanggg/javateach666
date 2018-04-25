package com.ctbu.javateach666.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.constant.Constant;
import com.ctbu.javateach666.dao.LKMyEvaluateDao;
import com.ctbu.javateach666.dao.LKMyInfoDao;
import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateRspBO;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListReqBO;
import com.ctbu.javateach666.pojo.bo.LKSubmitEvaluateBO;
import com.ctbu.javateach666.pojo.bo.LKSubmitEvaluateDaoBO;
import com.ctbu.javateach666.pojo.bo.LKUpdateEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.getEvaluateListRspBO;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.service.interfac.LKMyEvaluateService;

/**
 * 我的评价服务类
 *
 * @author luokan
 */
@Service
public class LKMyEvaluateServiceImpl implements LKMyEvaluateService{
	
	@Autowired
	private LKMyInfoDao lKMyInfoDao;
	
	@Autowired
	private LKMyEvaluateDao lKMyEvaluateDao;


	public List<LKInitEvaluateRspBO> initEvaluate(LKInitEvaluateReqBO lKInitEvaluateReqBO) {
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		lKInitEvaluateReqBO.setStuid(logstu.getId());
		//取得当前时间
		Calendar cal =Calendar.getInstance();
		int couyear = cal.get(Calendar.YEAR);
		//设置学年
		lKInitEvaluateReqBO.setCouyear(couyear);
		int semester = cal.get(Calendar.MONTH) + 1;
		if(semester <= 6){
			lKInitEvaluateReqBO.setSemester(Constant.SEMESTER.LAST);
		}else{
			lKInitEvaluateReqBO.setSemester(Constant.SEMESTER.NEXT);
		}
		return lKMyEvaluateDao.initEvaluate(lKInitEvaluateReqBO);
	}


	public BaseInfoBO submitEvaluate(LKSubmitEvaluateBO lKSubmitEvaluateBO) {
		//定义出参
		BaseInfoBO rsp = new BaseInfoBO();
		
		for (int i = 0; i < lKSubmitEvaluateBO.getId().size(); i++) {
			LKSubmitEvaluateDaoBO reqdao = new LKSubmitEvaluateDaoBO();
			reqdao.setId(lKSubmitEvaluateBO.getId().get(i));
			reqdao.setEvaluate(lKSubmitEvaluateBO.getScore().get(i));
			int isupdate = lKMyEvaluateDao.submitEvaluate(reqdao);
			if(isupdate < 1){
				rsp.setResponseCode(Constant.RSP_FALSE_CODE);
				rsp.setResponseDesc("评价失败！教学质量评价异常！");
				return rsp;
			}
		}
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("评价成功！");
		return rsp;
	}


	public PageInfoBo<getEvaluateListRspBO> getEvaluateList(LKMyRepairListReqBO lKMyRepairListReqBO) {
		//定义出参
		PageInfoBo<getEvaluateListRspBO> rsp = new PageInfoBo<getEvaluateListRspBO>();
		//设置page为下标
		int page = 0;
		page = (lKMyRepairListReqBO.getPage() - 1) * lKMyRepairListReqBO.getRows();
		lKMyRepairListReqBO.setPage(page);
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		lKMyRepairListReqBO.setStuid(logstu.getId());
		
		if(lKMyRepairListReqBO.getCouyear() == 0 && lKMyRepairListReqBO.getSemester() == 0){
			//取得当前时间
			Calendar cal =Calendar.getInstance();
			int couyear = cal.get(Calendar.YEAR);
			//设置学年
			lKMyRepairListReqBO.setCouyear(couyear);
			int semester = cal.get(Calendar.MONTH) + 1;
			if(semester <= 6){
				lKMyRepairListReqBO.setSemester(Constant.SEMESTER.LAST);
			}else{
				lKMyRepairListReqBO.setSemester(Constant.SEMESTER.NEXT);
			}
		}
		
		int total = lKMyEvaluateDao.getTotalEvaluateList(lKMyRepairListReqBO);
		if(total < 1){
			return rsp;
		}
		
		List<getEvaluateListRspBO> list = lKMyEvaluateDao.getEvaluateList(lKMyRepairListReqBO);
		
		
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}


	public LKInitEvaluateRspBO getEvaluateById(LKcancelClassReqBO lKcancelClassReqBO) {
		
		return lKMyEvaluateDao.getEvaluateById(lKcancelClassReqBO);
	}


	public BaseInfoBO updateEvaluate(LKUpdateEvaluateReqBO lKUpdateEvaluateReqBO) {
		
		BaseInfoBO rsp = new BaseInfoBO();
		
		int count = lKMyEvaluateDao.updateEvaluate(lKUpdateEvaluateReqBO);
		if(count < 1){
			rsp.setResponseCode("8888");
			rsp.setResponseDesc("未知原因，评价失败！");
			return rsp;
		}
		
		rsp.setResponseCode("0000");
		rsp.setResponseDesc("评价成功！");
		return rsp;
	}

}
