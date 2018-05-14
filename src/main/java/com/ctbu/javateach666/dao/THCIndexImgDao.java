package com.ctbu.javateach666.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.pojo.bo.thcbo.THCIndexImgListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCIndexImgListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCIndexImgPO;

public interface THCIndexImgDao extends BaseDao<THCIndexImgPO>{
	//首页图片管理
	public List<THCIndexImgListRspBO> getIndexImgbyPage(THCIndexImgListRepBO tHCIndexImgListRepBO);
	public int getTotal(THCIndexImgListRepBO tHCIndexImgListRepBO);
	public int insertImg(CommonsMultipartFile file, HttpServletRequest request);
	public int hideimg(THCIndexImgPO tHCIndexImgPO);
	public List<THCIndexImgPO> getPicture();
	public THCIndexImgPO selectImgno(String imgno);
}
