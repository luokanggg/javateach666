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
import com.ctbu.javateach666.pojo.po.questions.Completion;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.questions.CompletionService;
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
@RequestMapping("/completion")
public class CompletionController {
	
	@Autowired
	private CompletionService CompletionService;
	
	@Autowired
	private AccountService AccountService;
	
	/**
	 * 转发到填空题页面
	 * @return
	 */
	@RequestMapping("/completion")
	public String completion() {
		return "questions/completion";
	}
	
	/**
	 * 加载填空题题目
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/completions")
	public String listCompletion(HttpServletRequest request,HttpServletResponse response) {
		// 获取传入当前页和每页显示数
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("rows"));
        
        Completion completion = new Completion();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        // 查询参数
        String couseId = request.getParameter("couseId");
        String bTitleName = request.getParameter("bTitleName");
        String degree = request.getParameter("degree");
        String bTime = request.getParameter("bTime");
        String eTime = request.getParameter("eTime");
        if(couseId != null && !"".equals(couseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(couseId));
        	completion.setCourse(course);
        }
        if(bTitleName != null && !"".equals(bTitleName)) {
        	completion.setCompletionTitle(bTitleName);
        }
        if(degree != null && !"".equals(degree)) {
        	completion.setDegree(degree);
        }
        if(bTime != null && !"".equals(bTime)) {
        	try {
				completion.setbTime(format.parse(bTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        if(eTime != null && !"".equals(eTime)) {
        	try {
        		completion.seteTime(format.parse(eTime));
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
        completion.setTeaId(account.getUserdetailid());
        
        
        List<Completion> list = CompletionService.findList(completion);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}
	
	
	/**
	 * 逻辑删除单选题
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deleteCompletion")
	public String deleteCompletion(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("completionids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			Completion completion = new Completion();
			completion.setId(Integer.valueOf(id));
			CompletionService.deleteByLogic(completion);
		}
		return "OK";
	}
	
	/**
	 * 添加信息
	 * @param completion
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addCompletion")
	public String addCompletion(Completion completion) {
		completion.setCreateTime(new Date());
		int row = CompletionService.insert(completion);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	/**
	 * 修改信息
	 * @param completion
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateCompletion")
	public String updateCompletion(Completion completion) {
		completion.setCreateTime(new Date());
		int row = CompletionService.update(completion);
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
		        List<Object> importFile = ExcelUtils.importFile(name, in, Completion.class);
		        for(Object object : importFile) {
		        	Completion completion = (Completion) object;
		        	THCCoursePO course = new THCCoursePO();
		        	course.setId(Integer.valueOf(completion.getCourseId()));
		        	completion.setCourse(course);
		        	completion.setCreateTime(new Date());
		        	CompletionService.insert(completion);
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
