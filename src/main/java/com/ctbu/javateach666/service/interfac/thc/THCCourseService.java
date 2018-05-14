package com.ctbu.javateach666.service.interfac.thc;

import java.util.List;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.dao.THCCourseDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;

public interface THCCourseService extends BaseService<THCCourseDao, THCCoursePO>{
	//学科信息管理
	public PageInfoBo<THCCourseListRspBO> getCourseList(THCCourseListRepBO tHCCourseListRepBO);
	public List<THCDictionariesPO> getCtypeList();
	public THCCoursePO checkCoursename(String couname);
}
