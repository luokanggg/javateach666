package com.ctbu.javateach666.service.interfac.thc;

import java.util.List;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.dao.ExamDao;
import com.ctbu.javateach666.dao.THCNewsDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismRepBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;

public interface THCNewsService extends BaseService<THCNewsDao, THCJournalismPO>{
	//新闻公告管理
	public PageInfoBo<THCJournalismListRspBO> getJourList(THCJournalismListRepBO tHCJournalismListRepBO);
	public List<THCDictionariesListRspBO> getJoutypeList(String dtype);
	public int getAdminId(String username);
	public THCJournalismPO checkTitle(String title);
	public THCJournalismPO getJouDetail(int id);
	
	//首页展示新闻/公告列表
	public List<THCJournalismPO> getList(int j_type);
}
