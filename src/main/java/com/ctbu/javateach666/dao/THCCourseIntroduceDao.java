package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseIntroduceRepBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;

public interface THCCourseIntroduceDao extends BaseDao<THCCourseIntroducePO>{
	public List<THCCourseIntroducePO> getCourseIntroduce();
	
	public List<THCCourseIntroducePO> getCourseIntroduceByPage(THCCourseIntroduceRepBO tHCCourseIntroduceRepBO);
	public int getCourseIntroduceTotal(THCCourseIntroduceRepBO tHCCourseIntroduceRepBO);
	public THCCourseIntroducePO checkCname(String cname);
}
