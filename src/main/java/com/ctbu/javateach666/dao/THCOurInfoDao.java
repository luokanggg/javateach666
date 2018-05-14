package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.pojo.po.thcpo.THCOurInfoPO;

public interface THCOurInfoDao extends BaseDao<THCOurInfoPO>{
	public List<THCOurInfoPO> getOurInfoList();
}
