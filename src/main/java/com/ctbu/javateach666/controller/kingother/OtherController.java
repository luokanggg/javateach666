package com.ctbu.javateach666.controller.kingother;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ctbu.javateach666.pojo.po.kingother.Account;
import com.ctbu.javateach666.pojo.po.kingother.TeaCourse;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.kingother.TeaCourseService;
import com.ctbu.javateach666.util.CollectionUtils;
import com.ctbu.javateach666.util.UserMessageUtils;

/**
 * 其他方法control
 * @author king
 *
 */
@Controller
@RequestMapping("/kingother")
public class OtherController {
	@Autowired
	private TeaCourseService TeaCourseService;
	
	@Autowired
	private AccountService AccountService;
	
	/**
	 * 根据当前用户获取用户拥有的课程列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("getCourseJson")
	@ResponseBody
	public String getCourseJson(HttpServletRequest request, HttpServletResponse response) {
		String userName = UserMessageUtils.getNowUserName();
		Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
		TeaCourse teaCourse = new TeaCourse();
        teaCourse.setTeaid(account.getUserdetailid());
        List<TeaCourse> course = TeaCourseService.findList(teaCourse);
        if(CollectionUtils.isNotBlank(course)) {
	        ArrayList<HashMap<String,Object>> arrayList = new ArrayList<HashMap<String, Object>>();
			for(TeaCourse tc : course) {
		        HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("name", tc.getCourse().getCouname());
				map.put("id",tc.getCourse().getId());
				arrayList.add(map);
			}
			String json = JSON.toJSONString(arrayList);
			System.out.println(json);
			return json;
        } else {
        	return "";
        }
    }
	
	/**
	 * 获取学年json
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getCouyearJson")
	@ResponseBody
	public String getCouyearJson(HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Integer year = Integer.valueOf(format.format(date));
		ArrayList<HashMap<String,Object>> arrayList = new ArrayList<HashMap<String, Object>>();
		for(int i = 0; i < 4; i++) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("id", year-i);
			map.put("name",year-i);
			arrayList.add(map);
		}
		String json = JSON.toJSONString(arrayList);
		System.out.println(json);
		return json;
	}
}
