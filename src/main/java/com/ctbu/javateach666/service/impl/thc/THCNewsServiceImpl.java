package com.ctbu.javateach666.service.impl.thc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.ExamDao;
import com.ctbu.javateach666.dao.THCNewsDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismRepBO;
import com.ctbu.javateach666.pojo.po.exam.ReleaseExam;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;
import com.ctbu.javateach666.service.interfac.thc.THCNewsService;

@Service
public class THCNewsServiceImpl extends BaseServiceImpl<THCNewsDao, THCJournalismPO> implements THCNewsService{
	
	@Autowired
	private THCNewsDao tHCNewsDao;

	//新闻和公告管理
	public PageInfoBo<THCJournalismListRspBO> getJourList(THCJournalismListRepBO tHCJournalismListRepBO) {
		//定义出参
		PageInfoBo<THCJournalismListRspBO> rsp = new PageInfoBo<THCJournalismListRspBO>();
		int page = 0;
		page = ((tHCJournalismListRepBO).getPage() - 1) * tHCJournalismListRepBO.getRows();
		tHCJournalismListRepBO.setPage(page);
		int total = tHCNewsDao.getJourTotal(tHCJournalismListRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCJournalismListRspBO> list = tHCNewsDao.getJourbyPage(tHCJournalismListRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

	public List<THCDictionariesListRspBO> getJoutypeList(String dtype) {
		return tHCNewsDao.getJoutypeList(dtype);
	}

	public List<THCDictionariesListRspBO> getJoutypeList1(String dtype) {
		return tHCNewsDao.getJoutypeList(dtype);
	}
	
	public int getAdminId(String username) {
		return tHCNewsDao.getAdminId(username);
	}

	public List<THCJournalismPO> getList(int j_type) {
		return tHCNewsDao.getList(j_type);
	}

	public THCJournalismPO checkTitle(String title) {
		return tHCNewsDao.checkTitle(title);
	}

	public THCJournalismPO getJouDetail(int id) {
		return tHCNewsDao.getJouDetail(id);
	}
}
