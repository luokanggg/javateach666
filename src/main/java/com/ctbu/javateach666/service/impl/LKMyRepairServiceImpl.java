package com.ctbu.javateach666.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.constant.Constant;
import com.ctbu.javateach666.dao.LKMyInfoDao;
import com.ctbu.javateach666.dao.LKMyRepairDao;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListRspBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.service.interfac.LKMyRepairService;

/**
 * 我的重修服务实现类
 *
 * @author luokan
 */
@Service
public class LKMyRepairServiceImpl implements LKMyRepairService{
	
	@Autowired
	private LKMyInfoDao lKMyInfoDao;
	
	@Autowired
	private LKMyRepairDao lKMyRepairDao;

	public PageInfoBo<LKMyRepairListRspBO> getMyRepairList(LKMyRepairListReqBO lKMyRepairListReqBO) {
		//定义出参
		PageInfoBo<LKMyRepairListRspBO> rsp = new PageInfoBo<LKMyRepairListRspBO>();
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
		
		int total = lKMyRepairDao.getTotalRepairList(lKMyRepairListReqBO);
		if(total < 1){
			return rsp;
		}
		
		List<LKMyRepairListRspBO> list = lKMyRepairDao.getMyRepairList(lKMyRepairListReqBO);
		
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

}
