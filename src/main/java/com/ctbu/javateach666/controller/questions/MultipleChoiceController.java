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
import com.ctbu.javateach666.pojo.po.questions.MultipleChoice;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.questions.MultipleChoiceService;
import com.ctbu.javateach666.util.CollectionUtils;
import com.ctbu.javateach666.util.ExcelUtils;
import com.ctbu.javateach666.util.PageUtil;
import com.ctbu.javateach666.util.UserMessageUtils;

/**
 * 多选题control
 * @author king
 *
 */
@Controller
@RequestMapping("/multiple")
public class MultipleChoiceController {
	
	@Autowired
	private MultipleChoiceService MultipleChoiceService;
	
	@Autowired
	private AccountService AccountService;
	
	/**
	 * 转发到选择题页面
	 * @return
	 */
	@RequestMapping("/multiplechoice")
	public String multiplechoice() {
		return "questions/multiplechoice";
	}
	
	/**
	 * 加载选择题题目
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/multiple")
	public String listMultipleChoice(HttpServletRequest request,HttpServletResponse response) {
		// 获取传入当前页和每页显示数
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("rows"));

        MultipleChoice multipleChoice = new MultipleChoice();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        // 查询参数
        String couseId = request.getParameter("couseId");
        String title = request.getParameter("title");
        String degree = request.getParameter("degree");
        String bTime = request.getParameter("bTime");
        String eTime = request.getParameter("eTime");
        if(couseId != null && !"".equals(couseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(couseId));
        	multipleChoice.setCourse(course);
        }
        if(title != null && !"".equals(title)) {
        	multipleChoice.setMultipleTitle(title);
        }
        if(degree != null && !"".equals(degree)) {
        	multipleChoice.setDegree(degree);
        }
        if(bTime != null && !"".equals(bTime)) {
        	try {
        		multipleChoice.setbTime(format.parse(bTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        if(eTime != null && !"".equals(eTime)) {
        	try {
        		multipleChoice.seteTime(format.parse(eTime));
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
        multipleChoice.setTeaId(account.getUserdetailid());
        
        List<MultipleChoice> list = MultipleChoiceService.findList(multipleChoice);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}
	
	/**
	 * 逻辑删除多选题
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deleteMultipleChoice")
	public String deleteMultipleChoice(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("choiceids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			MultipleChoice multipleChoice = new MultipleChoice();
			multipleChoice.setId(Integer.valueOf(id));
			MultipleChoiceService.deleteByLogic(multipleChoice);
		}
		return "OK";
	}
	
	/**
	 * 添加信息
	 * @param multipleChoice
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addMultipleChoice")
	public String addMultipleChoice(MultipleChoice multipleChoice) {
		multipleChoice.setCreateTime(new Date());
		int row = MultipleChoiceService.insert(multipleChoice);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	/**
	 * 修改信息
	 * @param multipleChoice
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateMultipleChoice")
	public String updateMultipleChoice(MultipleChoice multipleChoice) {
		multipleChoice.setCreateTime(new Date());
		int row = MultipleChoiceService.update(multipleChoice);
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
		        List<Object> importFile = ExcelUtils.importFile(name, in, MultipleChoice.class);
		        for(Object object : importFile) {
		        	MultipleChoice multipleChoice = (MultipleChoice) object;
		        	THCCoursePO course = new THCCoursePO();
		        	course.setId(Integer.valueOf(multipleChoice.getCourseId()));
		        	multipleChoice.setCourse(course);
		        	multipleChoice.setCreateTime(new Date());
		        	MultipleChoiceService.insert(multipleChoice);
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
