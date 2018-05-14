package com.ctbu.javateach666.service.interfac.thc;

import java.util.List;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.dao.THCDicDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCInsertDicBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;

public interface THCDicService extends BaseService<THCDicDao, THCDictionariesPO>{
	//数据字典管理
	public PageInfoBo<THCDictionariesListRspBO> getDicList(THCDictionariesListRepBO tHCDictionariesListRepBO);
	public int deleteDic(String dicname);
	public List<THCDictionariesListRspBO> getDicNameList();
	public THCInsertDicBO addDic(THCInsertDicBO tHCInsertDicBO);
	public THCDictionariesPO checkDicname(String dicname);
}
