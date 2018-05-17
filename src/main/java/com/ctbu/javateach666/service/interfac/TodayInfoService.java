package com.ctbu.javateach666.service.interfac;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.TeacourseBo_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeBoRsp_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeReqBo_zxy;
import com.ctbu.javateach666.pojo.po.TeacoursePo_zxy;

public interface TodayInfoService {
	//查询今日课程
	public List<TeacoursePo_zxy> getTodayTeaCourse(TeacourseBo_zxy teacou);
	//查询今日信息
	public List<ToNoticeBoRsp_zxy> getTodayNotice(ToNoticeReqBo_zxy toReq);
	public int todayTodayinfo(ToNoticeReqBo_zxy toReq);
	public PageInfoBo<ToNoticeBoRsp_zxy> getTodayNoticeByPage(ToNoticeReqBo_zxy toReq);
}
