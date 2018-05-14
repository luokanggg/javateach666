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
import com.ctbu.javateach666.pojo.po.questions.Judgment;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.questions.JudgmentService;
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
@RequestMapping("/judgment")
public class JudgmentController {
	
	@Autowired
	private JudgmentService JudgmentService;
	
	@Autowired
	private AccountService AccountService;
	/**
	 * 转发到选择题页面
	 * @return
	 */
	@RequestMapping("/judge")
	public String judgment() {
		return "questions/judge";
	}
	
	/**
	 * 加载判断题题目
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/judgement")
	public String listJudgment(HttpServletRequest request,HttpServletResponse response) {
		// 获取传入当前页和每页显示数
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("rows"));

        Judgment judgment = new Judgment();
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
        	judgment.setCourse(course);
        }
        if(title != null && !"".equals(title)) {
        	judgment.setJudgmentTitle(title);
        }
        if(degree != null && !"".equals(degree)) {
        	judgment.setDegree(degree);
        }
        if(bTime != null && !"".equals(bTime)) {
        	try {
				judgment.setbTime(format.parse(bTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        if(eTime != null && !"".equals(eTime)) {
        	try {
        		judgment.seteTime(format.parse(eTime));
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
        judgment.setTeaId(account.getUserdetailid());
        
        List<Judgment> list = JudgmentService.findList(judgment);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}
	/**
	 * 逻辑删除判断题
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deleteJudgment")
	public String deleteJudgment(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("judgeids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			Judgment judgment = new Judgment();
			judgment.setId(Integer.valueOf(id));
			JudgmentService.deleteByLogic(judgment);
		}
		return "OK";
	}
	
	/**
	 * 添加信息
	 * @param Judgment
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addJudgment")
	public String addJudgment(Judgment judgment) {
		judgment.setCreateTime(new Date());
		int row = JudgmentService.insert(judgment);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	/**
	 * 修改信息
	 * @param Judgment
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateJudgment")
	public String updateJudgment(Judgment judgment) {
		judgment.setCreateTime(new Date());
		int row = JudgmentService.update(judgment);
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
		        List<Object> importFile = ExcelUtils.importFile(name, in, Judgment.class);
		        for(Object object : importFile) {
		        	Judgment judgment = (Judgment) object;
		        	THCCoursePO course = new THCCoursePO();
		        	course.setId(Integer.valueOf(judgment.getCourseId()));
		        	judgment.setCourse(course);
		        	judgment.setCreateTime(new Date());
		        	JudgmentService.insert(judgment);
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
