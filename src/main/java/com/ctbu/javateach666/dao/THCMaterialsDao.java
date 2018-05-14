package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.pojo.bo.thcbo.THCMaterialsRepBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCMaterialsPO;

public interface THCMaterialsDao extends BaseDao<THCMaterialsPO>{
	public List<THCMaterialsPO> getMaterials();
	
	public List<THCMaterialsPO> getMaterialsByPage(THCMaterialsRepBO tHCMaterialsRepBO);
	public int getTotal(THCMaterialsRepBO tHCMaterialsRepBO);
	public THCMaterialsPO checkMtitle(String mtitle);
}
