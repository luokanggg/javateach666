package com.ctbu.javateach666.service.impl.thc;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ctbu.javateach666.constant.Constant;
import com.ctbu.javateach666.dao.THCAdminInfoDao;
import com.ctbu.javateach666.pojo.bo.thcbo.THCUpdateAdminInBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCUpdateAdminPassBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAdminInfoPO;
import com.ctbu.javateach666.service.interfac.thc.THCAdminInfoService;
import com.ctbu.javateach666.util.RedisUtil;

@Service
public class THCAdminInfoServiceImpl implements THCAdminInfoService{
	
	@Autowired
	private THCAdminInfoDao tHCAdminInfoDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	//个人信息管理
	public THCAdminInfoPO initAdminInfo(String username) {
		String key = "THCAdminInfoService" + "initAdminInfo" + username;
		//定义出参
		THCAdminInfoPO tp = new THCAdminInfoPO();
		if(redisUtil.exist(key)){
			tp = redisUtil.getData(key);
		}else{
			tp = tHCAdminInfoDao.initAdminInfo(username);
			redisUtil.saveData(key, tp);
		}
		return tp;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public THCUpdateAdminInBO updateAdminInfo(String adminno, String adminphone, String adminemail) {
		System.out.println("tttttt");
		THCAdminInfoPO oldadinfo = tHCAdminInfoDao.getAdminInfoByAdminno(adminno);
		System.out.println(oldadinfo);
		oldadinfo.setAdminphone(adminphone);
		oldadinfo.setAdminemail(adminemail);
		
		THCUpdateAdminInBO rsp = new THCUpdateAdminInBO();
		int count = tHCAdminInfoDao.updateAdminInfo(oldadinfo);
		if(count > 0){
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String key = "THCAdminInfoService" + "updateAdminInfo" + userDetails.getUsername();
			redisUtil.deleteData(key);
			BeanUtils.copyProperties(oldadinfo, rsp);
			rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
			rsp.setResponseDesc("更新成功！");
			return rsp;
		}else{
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("更新失败！");
			return rsp;
		}
	}

	public THCAccountPO getAdminPass(String username) {
		String key = "THCAdminInfoService" + "getAdminPass" + username;
		//定义出参
		THCAccountPO tp = new THCAccountPO();
		if(redisUtil.exist(key)){
			tp = redisUtil.getData(key);
		}else{
			tp = tHCAdminInfoDao.getAdminPass(username);
			redisUtil.saveData(key, tp);
		}
		return tp;
	}

	public THCUpdateAdminPassBO updateAdminPass(String username, String password) {
		System.out.println("tttttt");
		THCAccountPO oldadpass = tHCAdminInfoDao.getAdminPass(username);
		System.out.println(oldadpass);
		oldadpass.setPassword(password);
		
		THCUpdateAdminPassBO rsp = new THCUpdateAdminPassBO();
		int count = tHCAdminInfoDao.updateAdminPass(oldadpass);
		if(count > 0){
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String key = "THCAdminInfoService" + "updateAdminPass" + userDetails.getUsername();
			redisUtil.deleteData(key);
			BeanUtils.copyProperties(oldadpass, rsp);
			rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
			rsp.setResponseDesc("更新成功！");
			return rsp;
		}else{
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("更新失败！");
			return rsp;
		}
	}
}

