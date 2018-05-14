package com.ctbu.javateach666.controller.exam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ctbu.javateach666.pojo.bo.QuestionPaper;
import com.ctbu.javateach666.pojo.po.exam.Achievement;
import com.ctbu.javateach666.pojo.po.exam.AnswerSheet;
import com.ctbu.javateach666.pojo.po.exam.ExamPaper;
import com.ctbu.javateach666.pojo.po.exam.ExamRule;
import com.ctbu.javateach666.pojo.po.exam.ReleaseExam;
import com.ctbu.javateach666.pojo.po.kingother.Account;
import com.ctbu.javateach666.pojo.po.kingother.QuestionAnswer;
import com.ctbu.javateach666.pojo.po.kingother.StuCourse;
import com.ctbu.javateach666.pojo.po.kingother.TeaCourse;
import com.ctbu.javateach666.pojo.po.questions.Subjective;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.exam.AchievementService;
import com.ctbu.javateach666.service.interfac.exam.AnswerSheetService;
import com.ctbu.javateach666.service.interfac.exam.ExamService;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.kingother.StuCourseService;
import com.ctbu.javateach666.service.interfac.kingother.TeaCourseService;
import com.ctbu.javateach666.service.interfac.paper.ExamPaperService;
import com.ctbu.javateach666.service.interfac.paper.ExamRuleService;
import com.ctbu.javateach666.service.interfac.questions.SubjectiveService;
import com.ctbu.javateach666.util.CollectionUtils;
import com.ctbu.javateach666.util.PageUtil;
import com.ctbu.javateach666.util.UserMessageUtils;

/**
 * 考试control
 * @author king
 *
 */
@Controller
@RequestMapping("/exam")
public class ExamController {
	@Autowired
	private ExamService ExamService;
	
	@Autowired
	private TeaCourseService TeaCourseService;
	
	@Autowired
	private StuCourseService StuCourseService;
	
	@Autowired
	private AccountService AccountService;
	
	@Autowired
	private AnswerSheetService AnswerSheetService;
	
	@Autowired
	private AchievementService AchievementService;
	
	@Autowired
	private ExamPaperService ExamPaperService;
	
	@Autowired
	private ExamRuleService ExamRuleService;
	
	@Autowired
	private SubjectiveService SubjectiveService;
	
	/**
	 * 跳转到安排考试列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/exam")
	public String initExam() {
        return "exam/exam";
    }
	
	/**
	 * 加载安排考试数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getExams")
	@ResponseBody
	public String getExams(HttpServletRequest request, HttpServletResponse response) {
        // 获取	传入当前页和每页显示数
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String couName = request.getParameter("couName");
        String exaName = request.getParameter("exaName");
        
        List<ReleaseExam> exams = new ArrayList<ReleaseExam>();
        ReleaseExam releaseExam = new ReleaseExam();
        //判断姓名是否非空
        if (couName != null && !"".equals(couName)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(couName));
        	releaseExam.setCourse(course);
        }
        if (exaName != null && !"".equals(exaName)) {
        	releaseExam.setExamPaperName(exaName);
        }
        // 获取当前用户的id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        // 查询当前用户的课程信息
        TeaCourse teaCourse = new TeaCourse();
        teaCourse.setTeaid(account.getUserdetailid());
        List<TeaCourse> course = TeaCourseService.findList(teaCourse);
        if (CollectionUtils.isNotBlank(course)) {
        	for (TeaCourse cou : course) {
        		releaseExam.setCourse(cou.getCourse());
        		List<ReleaseExam> findList = ExamService.findList(releaseExam);
        		if (CollectionUtils.isNotBlank(findList)) {
        			exams.addAll(findList);
        		}
        	}
        } 
        //	获取信息总条数
        int size = exams.size();
        int begin = (Integer.parseInt(page) - 1) * Integer.parseInt(rows);
        int end = begin + Integer.parseInt(rows);
        List<ReleaseExam> subList = exams.subList(begin, end > size ? size : end);
        String json = PageUtil.creatDataGritJson(subList, size);
        return json;
    }
	
	/**
	 * 保存发布的考试
	 * @param releaseExam
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("/saveExam")
	public String saveExam(ReleaseExam releaseExam,HttpServletRequest request) {
		releaseExam.setReleaseUsername(UserMessageUtils.getNowUserName());
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		releaseExam.setCouyear(Integer.valueOf(format.format(date)));
		if(date.getMonth() <= 7) {
			releaseExam.setSemester(1);
		} else {
			releaseExam.setSemester(2);
		}
		int row = ExamService.insert(releaseExam);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	/**
	 * 修改发布考试
	 * @param releaseExam
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("/updateExam")
	public String updateExam(ReleaseExam releaseExam,HttpServletRequest request) {
		releaseExam.setReleaseUsername(UserMessageUtils.getNowUserName());
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		releaseExam.setCouyear(Integer.valueOf(format.format(date)));
		if(date.getMonth() <= 7) {
			releaseExam.setSemester(1);
		} else {
			releaseExam.setSemester(2);
		}
		int row = ExamService.update(releaseExam);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	/**
	 * 删除考试
	 * @param releaseExam
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteExam")
	public String deleteExam(HttpServletRequest request) {
		String ids = request.getParameter("examID");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			ReleaseExam releaseExam = new ReleaseExam();
			releaseExam.setId(Integer.valueOf(id));
			ExamService.deleteByLogic(releaseExam);
		}
		return "OK";
	}
	
	/**
	 * 根据id获取考试对象
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExamById")
	public ReleaseExam getExamById(@RequestParam("id")String id) {
		ReleaseExam releaseExam = ExamService.get(Integer.valueOf(id));
		return releaseExam;
	}
	
	/**
	 * 初始化修改试卷页面
	 * @return
	 */
	@RequestMapping("/initCorrecting")
	public String initCorrecting() {
		return "exam/correctexam";
	}

	/**
	 * 加载答案数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCorrecting")
	public String getCorrecting(HttpServletRequest request) {
		// 获取	传入当前页和每页显示数
		Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("rows"));
		// 获取当前用户的id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        
        AnswerSheet answerSheet = new AnswerSheet();
		String paperid = request.getParameter("paperid");
		if(paperid != null && !"".equals(paperid)) {
			answerSheet.setExamId(Integer.valueOf(paperid));
		}
		answerSheet.setTeaId(account.getUserdetailid());
		
		List<AnswerSheet> findList = AnswerSheetService.findList(answerSheet);
		String json = PageUtil.findPage(page, rows, findList);
		return json;
	}
	
	/**
	 * 根据id获取学生答案
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAnswerById")
	public String getAnswerById(HttpServletRequest request,@RequestParam("anwserID")String id,Model model) {
		AnswerSheet answerSheet = AnswerSheetService.get(Integer.valueOf(id));
		model.addAttribute("answerSheet", answerSheet);
		
		// 获取试卷分数
		Achievement achievement = new Achievement();
		achievement.setPaperId(answerSheet.getExamId());
		achievement.setStuId(answerSheet.getStuId());
		List<Achievement> achievementList = AchievementService.findList(achievement);
		model.addAttribute("achievement", achievementList.get(0));
		
        //获取试卷规则
		ExamPaper examPaper = ExamPaperService.get(answerSheet.getExamId());
        ExamRule examRule = ExamRuleService.get(examPaper.getExamRule().getId());
        String subChoiceChecked = examRule.getSubjectiveRules();
        //将规则转为 id score 对象 list集合
        List<QuestionPaper> questionPapers = JSONArray.parseArray(subChoiceChecked, QuestionPaper.class);
        //转为 id score map集合
        Map<Integer, Integer> questionPaper = CollectionUtils.parseQuestionPaper(questionPapers);

        String subjectiveJson = answerSheet.getSubjectiveAnswer();
        List<QuestionAnswer> questionAnswers = JSONArray.parseArray(subjectiveJson, QuestionAnswer.class);
        for (QuestionAnswer stuAswer : questionAnswers){
            Subjective subjective = SubjectiveService.get(stuAswer.getId());
            stuAswer.setTitle(subjective.getSubjectiveTitle());
            stuAswer.setAnswer(subjective.getSubjectiveAnswer());
            stuAswer.setScore(questionPaper.get(subjective.getId()));
        }
        model.addAttribute("stuSubAnswer", questionAnswers);
        return "exam/anwser";
	}
	
	/**
	 * 保存主观题批改
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveCorrecting")
	public String saveCorrecting(HttpServletRequest request) {
		String scoreId = request.getParameter("scoreId");
        String answerId = request.getParameter("answerId");
        
        AnswerSheet answerSheet = AnswerSheetService.get(Integer.valueOf(answerId));
        answerSheet.setState("1");// 设置已经阅卷
        int row1 = AnswerSheetService.update(answerSheet);	// 保存答案信息
        List<QuestionAnswer> questionAnswers = JSONArray.parseArray(answerSheet.getSubjectiveAnswer(), QuestionAnswer.class);
        Integer score = 0;
        for (QuestionAnswer questionAnswer : questionAnswers){
            Integer id = questionAnswer.getId();
            String scores = request.getParameter(String.valueOf(id));
            score += Integer.valueOf(scores);
        }
        Achievement achievement = AchievementService.get(Integer.valueOf(scoreId));
        achievement.setSubjectiveScore(score);
        achievement.setScore(achievement.getScore()+score);
        int row2 = AchievementService.update(achievement); // 保存分数信息
        
        // 保存到学生课程表中
        StuCourse stuCourse = new StuCourse();
        stuCourse.setStuid(achievement.getStuId());
        stuCourse.setCourse(achievement.getCourse());
        List<StuCourse> findList = StuCourseService.findList(stuCourse);
        if(CollectionUtils.isNotBlank(findList)) {
        	stuCourse = findList.get(0);
        }
        stuCourse.setScore(achievement.getScore()+score);
        stuCourse.setRescore(achievement.getScore()+score);
        int row3 = StuCourseService.update(stuCourse);
        
		return row1 == 1 && row2 == 1 && row3 == 1 ? "OK" : "NO";
	}
	
	/**
	 * 获取当前教师的试卷json
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExamPaperJson")
	public String getExamPaperJson(HttpServletRequest request) {
		// 获取当前用户的id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        TeaCourse teaCourse = new TeaCourse();
        teaCourse.setTeaid(account.getUserdetailid());
        List<TeaCourse> teacourseList = TeaCourseService.findList(teaCourse);
        
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for(TeaCourse tc : teacourseList) {
        	THCCoursePO course = tc.getCourse();
        	ExamPaper examPaper = new ExamPaper();
        	examPaper.setCourse(course);
        	List<ExamPaper> exampaperList = ExamPaperService.findList(examPaper);
        	for(ExamPaper ep : exampaperList) {
        		HashMap<String, Object> map = new HashMap<>();
        		map.put("id", ep.getId());
        		map.put("name", ep.getExamPaperName());
        		arrayList.add(map);
        	}
        }
        String json = JSON.toJSONString(arrayList);
		return json;
	}
}
