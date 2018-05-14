package com.ctbu.javateach666.controller.score;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ctbu.javateach666.pojo.po.kingother.Account;
import com.ctbu.javateach666.pojo.po.kingother.StuCourse;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.kingother.StuCourseService;
import com.ctbu.javateach666.util.CollectionUtils;
import com.ctbu.javateach666.util.UserMessageUtils;

/**
 * 学生考试成绩control
 * @author king
 *
 */
@Controller
@RequestMapping("/stuscore")
public class StuScoreController {
	
	@Autowired
	private StuCourseService StuCourseService;
	
	@Autowired
	private AccountService AccountService;
	
	/**
	 * 初始化到学生成绩页面
	 * @return
	 */
	@RequestMapping("/initStuScore")
	public String initStuScore() {
		return "score/stuscore";
	}
	
	/**
	 * 加载成绩
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getScores")
	@ResponseBody
	public String getScores(HttpServletRequest request, HttpServletResponse response) {
		// 获取当前用户的id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        
        String couyear = request.getParameter("couyear");
        String semester = request.getParameter("semester");
        
        String json = "";
        
        StuCourse stuCourse = new StuCourse();
        stuCourse.setStuid(account.getUserdetailid());
        if(couyear != null && !"".equals(couyear) && semester != null && !"".equals(semester)) {
        	stuCourse.setCouyear(Integer.valueOf(couyear));
        	stuCourse.setSemester(Integer.valueOf(semester));
        	List<StuCourse> findList = StuCourseService.findList(stuCourse);
        	json = JSON.toJSONString(findList);
        }
		return json;
	}
	
	/**
	 * 导出成绩
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/getScoreExcel",produces = "text/plain;charset=utf-8")
	public void getScoreExcel(@RequestParam("couyear") String couyear,@RequestParam("semester") String semester,HttpServletRequest request, HttpServletResponse response) {
		// 获取当前用户的id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        
        StuCourse stuCourse = new StuCourse();
        stuCourse.setStuid(account.getUserdetailid());
        List<StuCourse> findList = new ArrayList<>();
        if(couyear != null && !"".equals(couyear) && semester != null && !"".equals(semester)) {
        	stuCourse.setCouyear(Integer.valueOf(couyear));
        	stuCourse.setSemester(Integer.valueOf(semester));
        	findList = StuCourseService.findList(stuCourse);
        }
        
        // Excel头部标题
        List<String> title = new ArrayList<>();
        title.add("课程名");
		title.add("教师");
		title.add("成绩");
		// Excel文件名
		String fileName = couyear+"0"+semester;
		
		// 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook  wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(fileName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCell cell = null;
        //创建标题
        for(int i=0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            cell.setCellStyle(style);
        }
        //创建内容
        for(int i=0; i < findList.size(); i++) {
            row = sheet.createRow(i + 1);
            StuCourse sc = findList.get(i);
            int j =0;
            row.createCell(j++).setCellValue(sc.getCouname().toString());
            row.createCell(j++).setCellValue(sc.getTeaname().toString());
            row.createCell(j++).setCellValue(sc.getScore());
        }
        response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
		response.addHeader("Pargam", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		OutputStream os;
		try {
			os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
