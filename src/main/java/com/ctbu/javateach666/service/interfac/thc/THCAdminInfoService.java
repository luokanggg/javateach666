package com.ctbu.javateach666.service.interfac.thc;

import com.ctbu.javateach666.pojo.bo.thcbo.THCUpdateAdminInBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCUpdateAdminPassBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAdminInfoPO;

public interface THCAdminInfoService {
	//个人信息管理
	public THCAdminInfoPO initAdminInfo(String username);
	public THCUpdateAdminInBO updateAdminInfo(String adminno, String adminphone, String adminemail);
	public THCAccountPO getAdminPass(String username);
	public THCUpdateAdminPassBO updateAdminPass(String username, String password);
}

