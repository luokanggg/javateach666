package com.ctbu.javateach666.service.impl.thc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.THCNewsDao;
import com.ctbu.javateach666.dao.THCStudentDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAuthoritiesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCClassPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCStudentInfoPO;
import com.ctbu.javateach666.service.interfac.thc.THCStudentService;

@Service
public class THCStudentServiceImpl extends BaseServiceImpl<THCStudentDao, THCAccountPO> implements THCStudentService{
	
	@Autowired
	private THCStudentDao tHCStudentDao;
	
	//学生信息管理
	public PageInfoBo<THCAccountListRspBO> getStuList(THCAccountListRepBO tHCAccountListRepBO) {
		//定义出参
		PageInfoBo<THCAccountListRspBO> rsp = new PageInfoBo<THCAccountListRspBO>();
		int page = 0;
		page = ((tHCAccountListRepBO).getPage() - 1) * tHCAccountListRepBO.getRows();
		tHCAccountListRepBO.setPage(page);
		int total = tHCStudentDao.getStuTotal(tHCAccountListRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCAccountListRspBO> list = tHCStudentDao.getStuListbyPage(tHCAccountListRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

	public int insertstu(THCStudentInfoPO tHCStudentInfoPO) {
		return tHCStudentDao.insertstu(tHCStudentInfoPO);
	}

	public int insertauth(THCAuthoritiesPO tHCAuthoritiesPO) {
		return tHCStudentDao.insertauth(tHCAuthoritiesPO);
	}

	public List<THCDictionariesListRspBO> getSelectList(String dtype) {
		return tHCStudentDao.getSelectList(dtype);
	}

	public List<THCClassPO> getCollegeList() {
		return tHCStudentDao.getCollegeList();
	}

	public List<THCClassPO> getMajorList(String college) {
		return tHCStudentDao.getMajorList(college);
	}

	public List<THCClassPO> getClassnameList(String major) {
		return tHCStudentDao.getClassnameList(major);
	}

	public int getClassid(String classname) {
		return tHCStudentDao.getClassid(classname);
	}

	public int deleteByLogicStu(THCStudentInfoPO tHCStudentInfoPO) {
		return tHCStudentDao.deleteByLogicStu(tHCStudentInfoPO);
	}

	public int updateStu(THCStudentInfoPO tHCStudentInfoPO) {
		return tHCStudentDao.updateStu(tHCStudentInfoPO);
	}

	public int deleteByLogicAuth(THCAuthoritiesPO tHCAuthoritiesPO) {
		return tHCStudentDao.deleteByLogicAuth(tHCAuthoritiesPO);
	}

	public THCAccountPO selectById(THCAccountPO HCAccountPO) {
		return tHCStudentDao.selectById(HCAccountPO);
	}

	public THCStudentInfoPO selectIdbyStuno(THCStudentInfoPO tHCStudentInfoPO) {
		return tHCStudentDao.selectIdbyStuno(tHCStudentInfoPO);
	}

	@Override
	public THCStudentInfoPO checkStuno(String stuno) {
		return tHCStudentDao.checkStuno(stuno);
	}

}
