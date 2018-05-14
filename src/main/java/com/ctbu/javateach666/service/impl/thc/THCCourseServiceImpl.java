package com.ctbu.javateach666.service.impl.thc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.THCCourseDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;
import com.ctbu.javateach666.service.interfac.thc.THCCourseService;

@Service
public class THCCourseServiceImpl extends BaseServiceImpl<THCCourseDao, THCCoursePO> implements THCCourseService{
	
	@Autowired
	private THCCourseDao tHCCourseDao;
	
	//学科信息管理
	public PageInfoBo<THCCourseListRspBO> getCourseList(THCCourseListRepBO tHCCourseListRepBO) {
		//定义出参
		PageInfoBo<THCCourseListRspBO> rsp = new PageInfoBo<THCCourseListRspBO>();
		int page = 0;
		page = ((tHCCourseListRepBO).getPage() - 1) * tHCCourseListRepBO.getRows();
		tHCCourseListRepBO.setPage(page);
		int total = tHCCourseDao.getCourseTotal(tHCCourseListRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCCourseListRspBO> list = tHCCourseDao.getCoursebyPage(tHCCourseListRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

	public List<THCDictionariesPO> getCtypeList() {
		return tHCCourseDao.getCtypeList();
	}

	public THCCoursePO checkCoursename(String couname) {
		return tHCCourseDao.checkCoursename(couname);
	}
}
