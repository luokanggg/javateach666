package com.ctbu.javateach666.service.interfac.thc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.dao.THCCourseIntroduceDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseIntroduceRepBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;

public interface THCCourseIntroduceService extends BaseService<THCCourseIntroduceDao, THCCourseIntroducePO>{
	public List<THCCourseIntroducePO> getCourseIntroduce();
	
	public PageInfoBo<THCCourseIntroducePO> getCourseIntroduceList(THCCourseIntroduceRepBO tHCCourseIntroduceRepBO);

	public int insertCourseIntroduce(CommonsMultipartFile file, HttpServletRequest request);
	public int updateCourseIntroduce(CommonsMultipartFile file, HttpServletRequest request);

	public THCCourseIntroducePO checkCname(String cname);
}
