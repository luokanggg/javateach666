package com.ctbu.javateach666.controller.thc;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.thcbo.THCUpdateAdminInBO;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.thcpo.THCAdminInfoPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCWebIntroducePO;
import com.ctbu.javateach666.service.interfac.thc.THCWebIntroduceService;

@Controller
public class THCWebIntroduceController {
	
	@Autowired
	private THCWebIntroduceService tHCWebIntroduceService;
	
	/**
	 * 首页显示网站介绍
	 */
	@ResponseBody
	@RequestMapping("/getwebintroduce")
	public THCWebIntroducePO getWebIntroduce(){
		return tHCWebIntroduceService.getWebIntroduce();
	}
	
	
	//后台管理
	@RequestMapping("/gowebintroduce")
	public String goWebIntroduce(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		THCWebIntroducePO webintroduce = tHCWebIntroduceService.getWebIntroduce();
		HttpSession session=request.getSession();
		session.setAttribute("WebInfo", webintroduce);
		return "thcadmin/indexinfo/webintroduce";
	}
	
	@ResponseBody
	@RequestMapping("/updatewebintroduce")
	public String updateWebIntroduce(@RequestBody THCWebIntroducePO webinfo){
		int m =  tHCWebIntroduceService.update(webinfo);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
}
