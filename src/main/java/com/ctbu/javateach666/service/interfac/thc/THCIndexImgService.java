package com.ctbu.javateach666.service.interfac.thc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.dao.THCIndexImgDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCIndexImgListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCIndexImgListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCIndexImgPO;

public interface THCIndexImgService extends BaseService<THCIndexImgDao, THCIndexImgPO>{
	//首页图片管理
	public PageInfoBo<THCIndexImgListRspBO> getImgList(THCIndexImgListRepBO tHCIndexImgListRepBO);
	public int insertImg(CommonsMultipartFile file, HttpServletRequest request);
	public int hideimg(THCIndexImgPO tHCIndexImgPO);
	public List<THCIndexImgPO> getPicture();
	public THCIndexImgPO selectImgno(String imgno);

}
