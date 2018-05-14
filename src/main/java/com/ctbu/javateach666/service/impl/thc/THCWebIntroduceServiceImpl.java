package com.ctbu.javateach666.service.impl.thc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.THCWebIntroduceDao;
import com.ctbu.javateach666.pojo.po.thcpo.THCWebIntroducePO;
import com.ctbu.javateach666.service.interfac.thc.THCWebIntroduceService;

@Service
public class THCWebIntroduceServiceImpl extends BaseServiceImpl<THCWebIntroduceDao, THCWebIntroducePO> implements THCWebIntroduceService{

	@Autowired
	private THCWebIntroduceDao tHCWebIntroduceDao;
	
	public THCWebIntroducePO getWebIntroduce() {
		return tHCWebIntroduceDao.getWebIntroduce();
	}

}
