package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCInsertDicBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;

public interface THCDicDao extends BaseDao<THCDictionariesPO>{
	//数据字典管理
	public List<THCDictionariesListRspBO> getDicListbyPage(THCDictionariesListRepBO tHCDictionariesListRepBO);
	public int getDicTotal(THCDictionariesListRepBO tHCDictionariesListRepBO);
	public int deleteDic(String dicname);
	public List<THCDictionariesListRspBO> getDicNameList();
	public int addDic(THCInsertDicBO tHCInsertDicBO);
	public THCDictionariesPO checkDicname(String dicname);
}
