package com.ctbu.javateach666.service.interfac.thc;


import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.dao.THCWebIntroduceDao;
import com.ctbu.javateach666.pojo.po.thcpo.THCWebIntroducePO;

public interface THCWebIntroduceService extends BaseService<THCWebIntroduceDao, THCWebIntroducePO>{
	public THCWebIntroducePO getWebIntroduce();
}
