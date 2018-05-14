package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;

public interface THCCourseDao extends BaseDao<THCCoursePO>{
	//学科信息管理
	public List<THCCourseListRspBO> getCoursebyPage(THCCourseListRepBO tHCCourseListRepBO);
	public int getCourseTotal(THCCourseListRepBO tHCCourseListRepBO);
	public List<THCDictionariesPO> getCtypeList();
	public THCCoursePO checkCoursename(String couname);
}
