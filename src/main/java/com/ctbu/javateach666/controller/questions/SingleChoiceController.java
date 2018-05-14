package com.ctbu.javateach666.controller.questions;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ctbu.javateach666.pojo.po.kingother.Account;
import com.ctbu.javateach666.pojo.po.questions.SingleChoice;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.questions.SingleChoiceService;
import com.ctbu.javateach666.util.CollectionUtils;
import com.ctbu.javateach666.util.ExcelUtils;
import com.ctbu.javateach666.util.PageUtil;
import com.ctbu.javateach666.util.UserMessageUtils;

/**
 * 单选题control
 * @author king
 *
 */
@Controller
@RequestMapping("/single")
public class SingleChoiceController {
	
	@Autowired
	private SingleChoiceService SingleChoiceService;
	
	@Autowired
	private AccountService AccountService;
	
	/**
	 * 转发到选择题页面
	 * @return
	 */
	@RequestMapping("/singlechoice")
	public String Choice() {
		return "questions/choice";
	}
	
	/**
	 * 加载选择题题目
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/single")
	public String listSingleChoice(HttpServletRequest request,HttpServletResponse response) {
		// 获取传入当前页和每页显示数
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("rows"));
        
        SingleChoice singleChoice = new SingleChoice();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        // 查询参数
        String cousename = request.getParameter("couseId");
        String choiceTitle = request.getParameter("choiceTitle");
        String degree = request.getParameter("degree");
        String bTime = request.getParameter("bTime");
        String eTime = request.getParameter("eTime");
        if(cousename != null && !"".equals(cousename)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setCouname(cousename);
        	singleChoice.setCourse(course);
        }
        if(choiceTitle != null && !"".equals(choiceTitle)) {
        	singleChoice.setSingleTitle(choiceTitle);
        }
        if(degree != null && !"".equals(degree)) {
        	singleChoice.setDegree(degree);
        }
        if(bTime != null && !"".equals(bTime)) {
        	try {
				singleChoice.setbTime(format.parse(bTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        if(eTime != null && !"".equals(eTime)) {
        	try {
        		singleChoice.seteTime(format.parse(eTime));
        	} catch (ParseException e) {
        		e.printStackTrace();
        	}
        }
        
        // 传入当前教师id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        singleChoice.setTeaId(account.getUserdetailid());
        
        
        List<SingleChoice> list = SingleChoiceService.findList(singleChoice);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}
	
	
	/**
	 * 逻辑删除单选题
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deleteSingleChoice")
	public String deleteSingleChoice(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("choiceids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			SingleChoice singleChoice = new SingleChoice();
			singleChoice.setId(Integer.valueOf(id));
			SingleChoiceService.deleteByLogic(singleChoice);
		}
		return "OK";
	}
	
	/**
	 * 添加信息
	 * @param singleChoice
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addSingleChoice")
	public String addSingleChoice(SingleChoice singleChoice) {
		singleChoice.setCreateTime(new Date());
		int row = SingleChoiceService.insert(singleChoice);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	/**
	 * 修改信息
	 * @param singleChoice
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateSingleChoice")
	public String updateSingleChoice(SingleChoice singleChoice) {
		singleChoice.setCreateTime(new Date());
		int row = SingleChoiceService.update(singleChoice);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	/**
	 * 导入Excel文件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public String importExcel(@RequestParam("excel") MultipartFile  file,HttpServletRequest request, HttpServletResponse response) {
		try {
			if(!file.isEmpty()) {
		        // 获取文件项对象
		        String name = file.getOriginalFilename();
		        InputStream in;
					in = file.getInputStream();
		        // 调用导入的方法
		        List<Object> importFile = ExcelUtils.importFile(name, in, SingleChoice.class);
		        for(Object object : importFile) {
		        	SingleChoice singleChoice = (SingleChoice) object;
		        	THCCoursePO course = new THCCoursePO();
		        	course.setId(Integer.valueOf(singleChoice.getCourseId()));
		        	singleChoice.setCourse(course);
		        	singleChoice.setCreateTime(new Date());
		        	SingleChoiceService.insert(singleChoice);
		        }
		        return "OK";
			}
			return "NO";
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "NO";
    }
	
}
