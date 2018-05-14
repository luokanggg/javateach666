package com.ctbu.javateach666.service.impl.thc;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.constant.Constant;
import com.ctbu.javateach666.dao.THCDicDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCInsertDicBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;
import com.ctbu.javateach666.service.interfac.thc.THCDicService;
import com.ctbu.javateach666.util.RedisUtil;

@Service
public class THCDicServiceImpl extends BaseServiceImpl<THCDicDao, THCDictionariesPO> implements THCDicService{
	
	@Autowired
	private THCDicDao tHCDicDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	//数据字典管理
	public PageInfoBo<THCDictionariesListRspBO> getDicList(THCDictionariesListRepBO tHCDictionariesListRepBO) {
		//定义出参
		PageInfoBo<THCDictionariesListRspBO> rsp = new PageInfoBo<THCDictionariesListRspBO>();
		int page = 0;
		page = ((tHCDictionariesListRepBO).getPage() - 1) * tHCDictionariesListRepBO.getRows();
		tHCDictionariesListRepBO.setPage(page);
		int total = tHCDicDao.getDicTotal(tHCDictionariesListRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCDictionariesListRspBO> list = tHCDicDao.getDicListbyPage(tHCDictionariesListRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

	public int deleteDic(String dicname) {
		return tHCDicDao.deleteDic(dicname);
	}

	public List<THCDictionariesListRspBO> getDicNameList() {
		return tHCDicDao.getDicNameList();
	}

	public THCInsertDicBO addDic(THCInsertDicBO tHCInsertDicBO) {
		System.out.println("tttttt");
		THCInsertDicBO rsp = new THCInsertDicBO();
		int count = tHCDicDao.addDic(tHCInsertDicBO);
		if(count > 0){
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String key = "THCAdminInfoService" + "addDic" + userDetails.getUsername();
			redisUtil.deleteData(key);
			BeanUtils.copyProperties(tHCInsertDicBO, rsp);
			rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
			rsp.setResponseDesc("添加成功！");
			return rsp;
		}else{
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("添加失败！");
			return rsp;
		}
	}

	public THCDictionariesPO checkDicname(String dicname) {
		return tHCDicDao.checkDicname(dicname);
	}
}
