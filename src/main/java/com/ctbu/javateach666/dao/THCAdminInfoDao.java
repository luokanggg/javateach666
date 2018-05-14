package com.ctbu.javateach666.dao;

import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAdminInfoPO;

public interface THCAdminInfoDao{
	//个人信息管理
	public THCAdminInfoPO initAdminInfo(String username);
	public THCAdminInfoPO getAdminInfoByAdminno(String adminno);
	public int updateAdminInfo(THCAdminInfoPO adinfo);
	public THCAccountPO getAdminPass(String username);
	public int updateAdminPass(THCAccountPO adaccount);
}
