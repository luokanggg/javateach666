package com.ctbu.javateach666.service.interfac.thc;

import java.util.List;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.dao.THCOurInfoDao;
import com.ctbu.javateach666.pojo.po.thcpo.THCOurInfoPO;

public interface THCOurInfoService extends BaseService<THCOurInfoDao, THCOurInfoPO>{
	public List<THCOurInfoPO> getOurInfoList();
}
