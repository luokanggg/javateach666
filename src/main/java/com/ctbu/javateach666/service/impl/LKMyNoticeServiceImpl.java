package com.ctbu.javateach666.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.LKMyInfoDao;
import com.ctbu.javateach666.dao.LKMyNoticeDao;
import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListReqDaoBO;
import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListRspBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.service.interfac.LKMyNoticeService;

/**
 * 我的通知服务实现类
 *
 * @author luokan
 */
@Service
public class LKMyNoticeServiceImpl implements LKMyNoticeService{
	
	@Autowired
	private LKMyInfoDao lKMyInfoDao;
	
	@Autowired
	private LKMyNoticeDao lKMyNoticeDao;

	public PageInfoBo<LKGetMyNoticeListRspBO> getMyNoticeList(LKGetMyNoticeListReqBO lKGetMyNoticeListReqBO) {
		//定义出参
		PageInfoBo<LKGetMyNoticeListRspBO> rsp = new PageInfoBo<LKGetMyNoticeListRspBO>();
		//设置page为下标
		int page = 0;
		page = (lKGetMyNoticeListReqBO.getPage() - 1) * lKGetMyNoticeListReqBO.getRows();
		lKGetMyNoticeListReqBO.setPage(page);
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		//设置id
		lKGetMyNoticeListReqBO.setId(logstu.getId());
		
		LKGetMyNoticeListReqDaoBO daoreq = new LKGetMyNoticeListReqDaoBO();
		BeanUtils.copyProperties(lKGetMyNoticeListReqBO, daoreq);
		if(lKGetMyNoticeListReqBO.getNowtime() != null && lKGetMyNoticeListReqBO.getNowtime() != ""){
			daoreq.setNowtime(null);
		}else{
			daoreq.setNowtime(new Date());
		}
		
		int total = lKMyNoticeDao.getTotalMyNoticeList(daoreq);
		if(total < 1){
			return rsp;
		}
		
		List<LKGetMyNoticeListRspBO> list = lKMyNoticeDao.getMyNoticeList(daoreq); 
		
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

}
