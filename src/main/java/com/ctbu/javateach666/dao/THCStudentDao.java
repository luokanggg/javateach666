package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAuthoritiesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCClassPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCStudentInfoPO;

public interface THCStudentDao extends BaseDao<THCAccountPO>{
	//学生信息管理
	public List<THCAccountListRspBO> getStuListbyPage(THCAccountListRepBO tHCAccountListRepBO);
	public int getStuTotal(THCAccountListRepBO tHCAccountListRepBO);
	public int insertstu(THCStudentInfoPO tHCStudentInfoPO);
	public int insertauth(THCAuthoritiesPO tHCAuthoritiesPO);
	public List<THCDictionariesListRspBO> getSelectList(String dtype);
	public List<THCClassPO> getCollegeList();
	public List<THCClassPO> getMajorList(String college);
	public List<THCClassPO> getClassnameList(String major);
	public int getClassid(String classname);
	public int deleteByLogicStu(THCStudentInfoPO tHCStudentInfoPO);
	public int deleteByLogicAuth(THCAuthoritiesPO tHCAuthoritiesPO);
	public int updateStu(THCStudentInfoPO tHCStudentInfoPO);
	public THCAccountPO selectById(THCAccountPO tHCAccountPO);
	public THCStudentInfoPO selectIdbyStuno(THCStudentInfoPO tHCStudentInfoPO);
	public THCStudentInfoPO checkStuno(String stuno);
}
