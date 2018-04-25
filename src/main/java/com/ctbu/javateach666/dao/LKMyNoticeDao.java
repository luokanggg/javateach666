package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListReqDaoBO;
import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListRspBO;

public interface LKMyNoticeDao {
	public List<LKGetMyNoticeListRspBO> getMyNoticeList(LKGetMyNoticeListReqDaoBO lKGetMyNoticeListReqDaoBO);
	public int getTotalMyNoticeList(LKGetMyNoticeListReqDaoBO lKGetMyNoticeListReqDaoBO);
}
