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

import com.ctbu.javateach666.pojo.po.exam.Achievement;
import com.ctbu.javateach666.service.interfac.exam.AchievementService;
import com.ctbu.javateach666.util.CollectionUtils;
import com.ctbu.javateach666.util.PageUtil;

/**
 * 考试成绩control
 * @author king
 *
 */
@Controller
@RequestMapping("/teascore")
public class TeaScoreController {
	
	@Autowired
	private AchievementService AchievementService;
	
	/**
	 * 初始化到教师成绩页面
	 * @return
	 */
	@RequestMapping("/initTeaScore")
	public String initTeaScore() {
		return "score/teascore";
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
		// 获取	传入当前页和每页显示数
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        Achievement achievement = new Achievement();
        
        String paperId = request.getParameter("paperId");
        String stuName = request.getParameter("stuName");
        String json = "{}";
        if(paperId != null && !"".equals(paperId)) {
        	achievement.setPaperId(Integer.valueOf(paperId));
        	if(stuName != null && !"".equals(stuName)) {
        		achievement.setStuName(stuName);
        	}
        	List<Achievement> findList = AchievementService.findList(achievement);
        	
        	int size = findList.size();
            int begin = (Integer.parseInt(page) - 1) * Integer.parseInt(rows);
            int end = begin + Integer.parseInt(rows);
            List<Achievement> subList = findList.subList(begin, end > size ? size : end);
        	json = PageUtil.creatDataGritJson(subList, size);
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
	public void getScoreExcel(@RequestParam("paperId") String paperId,@RequestParam("stuId") String stuId,HttpServletRequest request, HttpServletResponse response) {
		Achievement achievement = new Achievement();
		List<Achievement> findList = new ArrayList<>();
        if(paperId != null && !"".equals(paperId)) {
        	achievement.setPaperId(Integer.valueOf(paperId));
        	if(stuId != null && !"".equals(stuId)) {
        		achievement.setStuName(stuId);
        	}
        	findList = AchievementService.findList(achievement);
        }
        
        // Excel头部标题
        List<String> title = new ArrayList<>();
        title.add("排名");
        title.add("学生姓名");
		title.add("总成绩");
		title.add("单选题分数");
		title.add("多选题分数");
		title.add("判断题分数");
		title.add("填空题分数");
		title.add("主观题分数");
		// Excel文件名
		String fileName = paperId;
		if(CollectionUtils.isNotBlank(findList)) {
			fileName = findList.get(0).getCouyear().toString() +"0"+ findList.get(0).getSemester().toString();
		}
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
            Achievement ac = findList.get(i);
            int j =0;
            row.createCell(j++).setCellValue(i+1);
            row.createCell(j++).setCellValue(ac.getStuName() != null ? ac.getStuName() : "");
            row.createCell(j++).setCellValue(ac.getScore()  != null ? ac.getScore() : 0);
            row.createCell(j++).setCellValue(ac.getSingleScore() != null ? ac.getSingleScore() : 0);
            row.createCell(j++).setCellValue(ac.getMultipleScore() != null ? ac.getMultipleScore() : 0);
            row.createCell(j++).setCellValue(ac.getJudgmentScore() != null ? ac.getJudgmentScore() : 0);
            row.createCell(j++).setCellValue(ac.getCompletionScore() != null ? ac.getCompletionScore() : 0);
            row.createCell(j++).setCellValue(ac.getSubjectiveScore() != null ? ac.getSubjectiveScore() : 0);
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
