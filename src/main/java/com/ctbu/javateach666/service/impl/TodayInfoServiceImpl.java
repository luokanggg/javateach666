package com.ctbu.javateach666.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.TodayIndoDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.TeacourseBo_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeBoRsp_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeReqBo_zxy;
import com.ctbu.javateach666.pojo.po.TeacoursePo_zxy;
import com.ctbu.javateach666.service.interfac.TodayInfoService;
@Service
public class TodayInfoServiceImpl implements TodayInfoService{
	@Autowired
	TodayIndoDao today;

	@Override
	public List<TeacoursePo_zxy> getTodayTeaCourse(TeacourseBo_zxy teacou) {
		// TODO Auto-generated method stub
		return today.getTodayTeaCourse(teacou);
	}

	@Override
	public List<ToNoticeBoRsp_zxy> getTodayNotice(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		return today.getTodayNotice(toReq);
	}
	
	@Override
	public PageInfoBo<ToNoticeBoRsp_zxy> getTodayNoticeByPage(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		PageInfoBo<ToNoticeBoRsp_zxy> rsp=new PageInfoBo<ToNoticeBoRsp_zxy>();
		int page=(toReq.getPage()-1)*toReq.getRows();
		toReq.setPage(page);
		//System.out.println(toReq);
		int total=today.todayTodayinfo(toReq);
		if(total<1){
			return rsp;
		}else{
			List<ToNoticeBoRsp_zxy> list=today.getTodayNotice(toReq);
			rsp.setRows(list);
			rsp.setTotal(total);
			return rsp;
		}
		
	}

	@Override
	public int todayTodayinfo(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		return today.todayTodayinfo(toReq);
	}


}
