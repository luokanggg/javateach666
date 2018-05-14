package com.ctbu.javateach666.service.impl.thc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.THCOurInfoDao;
import com.ctbu.javateach666.pojo.po.thcpo.THCOurInfoPO;
import com.ctbu.javateach666.service.interfac.thc.THCOurInfoService;

@Service
public class THCOurInfoServiceImpl extends BaseServiceImpl<THCOurInfoDao, THCOurInfoPO> implements THCOurInfoService{

	@Autowired
	private THCOurInfoDao tHCOurInfoDao;
	
	public List<THCOurInfoPO> getOurInfoList() {
		return tHCOurInfoDao.getOurInfoList();
	}
	
}
