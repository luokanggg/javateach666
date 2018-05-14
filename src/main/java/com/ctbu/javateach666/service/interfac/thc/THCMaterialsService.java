package com.ctbu.javateach666.service.interfac.thc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.dao.THCMaterialsDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCMaterialsRepBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCMaterialsPO;

public interface THCMaterialsService extends BaseService<THCMaterialsDao, THCMaterialsPO>{
	public List<THCMaterialsPO> getMaterials();
	
	public PageInfoBo<THCMaterialsPO> getMaterialsList(THCMaterialsRepBO tHCMaterialsRepBO);

	public int updateMaterials(CommonsMultipartFile file, HttpServletRequest request);

	public int insertMaterials(CommonsMultipartFile file, HttpServletRequest request);

	public THCMaterialsPO checkMtitle(String mtitle);
	
	public String downloadMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
