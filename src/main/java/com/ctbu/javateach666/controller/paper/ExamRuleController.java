package com.ctbu.javateach666.controller.paper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ctbu.javateach666.pojo.po.exam.ExamRule;
import com.ctbu.javateach666.pojo.po.kingother.Account;
import com.ctbu.javateach666.pojo.po.questions.Completion;
import com.ctbu.javateach666.pojo.po.questions.Judgment;
import com.ctbu.javateach666.pojo.po.questions.MultipleChoice;
import com.ctbu.javateach666.pojo.po.questions.SingleChoice;
import com.ctbu.javateach666.pojo.po.questions.Subjective;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.paper.ExamRuleService;
import com.ctbu.javateach666.service.interfac.questions.CompletionService;
import com.ctbu.javateach666.service.interfac.questions.JudgmentService;
import com.ctbu.javateach666.service.interfac.questions.MultipleChoiceService;
import com.ctbu.javateach666.service.interfac.questions.SingleChoiceService;
import com.ctbu.javateach666.service.interfac.questions.SubjectiveService;
import com.ctbu.javateach666.util.CollectionUtils;
import com.ctbu.javateach666.util.PageUtil;
import com.ctbu.javateach666.util.UserMessageUtils;

/**
 * 试卷规则control
 * @author king
 *
 */
@Controller
@RequestMapping("/examrule")
public class ExamRuleController {
	
	@Autowired
	private ExamRuleService ExamRuleService;
	
	@Autowired
	private SingleChoiceService SingleChoiceService;
	
	@Autowired
	private MultipleChoiceService MultipleChoiceService;
	
	@Autowired
	private JudgmentService JudgmentService;
	
	@Autowired
	private CompletionService CompletionService;
	
	@Autowired
	private SubjectiveService subjectiveService;
	
	@Autowired
	private AccountService AccountService;
	
	/**
	 * 转发到试卷规则页面
	 * @return
	 */
	@RequestMapping("/examrule")
	public String examrule() {
		return "paper/listRules";
	}
	
	/**
	 * 加载试卷规则列表
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/examrules")
	public String listExamRule(HttpServletRequest request,HttpServletResponse response) {
		// 获取传入当前页和每页显示数
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("rows"));
        
        ExamRule examRule = new ExamRule();
        
        // 查询参数
        String couseId = request.getParameter("course");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        if(couseId != null && !"".equals(couseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(couseId));
        	examRule.setCourse(course);
        }
        if(name != null && !"".equals(name)) {
        	examRule.setRuleName(name);
        }
        if(type != null && !"".equals(type)) {
        	examRule.setRuleType(Integer.valueOf(type));
        }
        
        
        // 传入当前教师id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        examRule.setTeaId(account.getUserdetailid());
        
        List<ExamRule> list = ExamRuleService.findList(examRule);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}
	
	
	/**
	 * 逻辑删除规则
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deleteExamRule")
	public String deleteExamRule(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("examruleids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			ExamRule examRule = new ExamRule();
			examRule.setId(Integer.valueOf(id));
			ExamRuleService.deleteByLogic(examRule);
		}
		return "OK";
	}
	
	/**
	 * 根据规则id获取规则对象
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExamRuleById")
	public String getExamRuleById(HttpServletRequest request) {
		String id = request.getParameter("ruleId");
		ExamRule examRule = ExamRuleService.get(Integer.valueOf(id));
		String jsonString = "";
		if(examRule != null) {
			jsonString = JSON.toJSONString(examRule);
		} 
		return jsonString;
	}
	
	/**
     * 初始化手动组卷
     *
     */
	@RequestMapping("/initHandPaper")
    public String initHandPaper(Model model) {
//        List<Course> query = new CourseServiceImpl().courseQuery();
//        request.setAttribute("courses", query);
    	
        return "paper/handPaper";
    }
    


	 /**
     * 跳转单选择题页面
     */
	@RequestMapping("/getChoice")
    public String getChoice(Model model,HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		model.addAttribute("courseId", courseId);
        return "paper/hchoice";
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
        String choicename = request.getParameter("choicename");
        String courseId = request.getParameter("courseId");
        String degree = request.getParameter("degree");
        String bTime = request.getParameter("bTime");
        String eTime = request.getParameter("eTime");
        if(choicename != null && !"".equals(choicename)) {
        	singleChoice.setSingleTitle(choicename);
        }
        if(courseId != null && !"".equals(courseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(courseId));
        	singleChoice.setCourse(course);
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
        
        List<SingleChoice> list = SingleChoiceService.findList(singleChoice);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}
	
   /**
    * 根据id获取单选题题目
    */
	@RequestMapping(value="/getSingleChoiceById",produces = "text/plain;charset=utf-8")
	@ResponseBody
    public String getSingleChoiceById(String choiceId,Model model,HttpServletRequest request) {
		SingleChoice singleChoice = SingleChoiceService.get(Integer.valueOf(choiceId));
		return singleChoice.getSingleTitle();
	}

    /**
     * 跳转多选择题页面
     */
	@RequestMapping("/getMultiple")
    public String getMultiple(Model model,HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		model.addAttribute("courseId", courseId);
        return "paper/hmultiplechoice";

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
        String choicename = request.getParameter("choicename");
        String degree = request.getParameter("degree");
        String bTime = request.getParameter("bTime");
        String eTime = request.getParameter("eTime");
        String courseId = request.getParameter("courseId");
        if(courseId != null && !"".equals(courseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(courseId));
        	multipleChoice.setCourse(course);
        }
        if(choicename != null && !"".equals(choicename)) {
        	multipleChoice.setMultipleTitle(choicename);
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
        
        List<MultipleChoice> list = MultipleChoiceService.findList(multipleChoice);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}

	/**
    * 根据id获取多选题题目
    */
	@RequestMapping(value="/getMultipleChoiceById",produces = "text/plain;charset=utf-8")
	@ResponseBody
    public String getMultipleChoiceById(String choiceId,Model model,HttpServletRequest request) {
		MultipleChoice multipleChoice = MultipleChoiceService.get(Integer.valueOf(choiceId));
		return multipleChoice.getMultipleTitle();
	}
	
    /**
     * 跳转填空题页面
     */
	@RequestMapping("/getBlank")
    public String getBlank(Model model,HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		model.addAttribute("courseId", courseId);
        return "paper/hcompletion";
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
        String couseId = request.getParameter("courseId");
        String completionname = request.getParameter("completionname");
        String degree = request.getParameter("degree");
        String bTime = request.getParameter("bTime");
        String eTime = request.getParameter("eTime");
        if(couseId != null && !"".equals(couseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(couseId));
        	completion.setCourse(course);
        }
        if(completionname != null && !"".equals(completionname)) {
        	completion.setCompletionTitle(completionname);
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
        
        
        List<Completion> list = CompletionService.findList(completion);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}

	/**
    * 根据id获取填空题题目
    */
	@RequestMapping(value="/getCompletionById",produces = "text/plain;charset=utf-8")
	@ResponseBody
    public String getCompletionById(String choiceId,Model model,HttpServletRequest request) {
		Completion completion = CompletionService.get(Integer.valueOf(choiceId));
		return completion.getCompletionTitle();
	}

    /**
     * 跳转判断题页面
     */
	@RequestMapping("/getJudge")
    public String getJudge(Model model,HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		model.addAttribute("courseId", courseId);
        return "paper/hjudge";

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
        String couseId = request.getParameter("courseId");
        String judgename = request.getParameter("judgename");
        String degree = request.getParameter("degree");
        String bTime = request.getParameter("bTime");
        String eTime = request.getParameter("eTime");
        if(couseId != null && !"".equals(couseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(couseId));
        	judgment.setCourse(course);
        }
        if(judgename != null && !"".equals(judgename)) {
        	judgment.setJudgmentTitle(judgename);
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
        
        List<Judgment> list = JudgmentService.findList(judgment);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}

	/**
    * 根据id获取判断题题目
    */
	@RequestMapping(value="/getJudgeById",produces = "text/plain;charset=utf-8")
	@ResponseBody
    public String getJudgeById(String choiceId,Model model,HttpServletRequest request) {
		Judgment judgment = JudgmentService.get(Integer.valueOf(choiceId));
		return judgment.getJudgmentTitle();
	}

    /**
     * 跳转主观题页面
     */
	@RequestMapping("/getSubjective")
    public String getSubjective(Model model,HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		model.addAttribute("courseId", courseId);
        return "paper/hsubjective";
    }
	
	/**
	 * 加载选择题题目
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/subjectives")
	public String listSubjective(HttpServletRequest request,HttpServletResponse response) {
		// 获取传入当前页和每页显示数
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("rows"));
        
        Subjective subjective = new Subjective();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        // 查询参数
        String couseId = request.getParameter("courseId");
        String subjectivename = request.getParameter("subjectivename");
        String degree = request.getParameter("degree");
        String bTime = request.getParameter("bTime");
        String eTime = request.getParameter("eTime");
        if(couseId != null && !"".equals(couseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(couseId));
        	subjective.setCourse(course);
        }
        if(subjectivename != null && !"".equals(subjectivename)) {
        	subjective.setSubjectiveTitle(subjectivename);
        }
        if(degree != null && !"".equals(degree)) {
        	subjective.setDegree(degree);
        }
        if(bTime != null && !"".equals(bTime)) {
        	try {
				subjective.setbTime(format.parse(bTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        if(eTime != null && !"".equals(eTime)) {
        	try {
        		subjective.seteTime(format.parse(eTime));
        	} catch (ParseException e) {
        		e.printStackTrace();
        	}
        }
        
        
        List<Subjective> list = subjectiveService.findList(subjective);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}
	
	/**
     * 根据id获取判断题题目
     */
	 @RequestMapping(value="/getSubjectiveById",produces = "text/plain;charset=utf-8")
	 @ResponseBody
     public String getSubjectiveById(String choiceId,Model model,HttpServletRequest request) {
		 Subjective subjective = subjectiveService.get(Integer.valueOf(choiceId));
		 return subjective.getSubjectiveTitle();
	 }
	 
	 
	/**
     * 将手动组卷内容存储到数据库
     */
	@RequestMapping("/saveHandPaper")
	@ResponseBody
    public String saveHandPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取单选题数据
        String choiceIds = request.getParameter("choiceIds");
        // 获取单选题总分数
        String choiceIdsScore = request.getParameter("choiceIdsScore");
        // 获取单选题总数
        String choiceIdsNum = request.getParameter("choiceIdsNum");

        // 获取多选题数据
        String multipleId = request.getParameter("multipleId");
        // 获取多选题总分数
        String multipleIdsScore = request.getParameter("multipleIdsScore");
        // 获取多选题总数
        String multipleIdsNum = request.getParameter("multipleIdsNum");

        // 获取判断题数据
        String blankIds = request.getParameter("blankIds");
        // 获取判断题总分数
        String blankIdsScore = request.getParameter("blankIdsScore");
        // 获取判断题总数
        String blankIdsNum = request.getParameter("blankIdsNum");

        // 获取填空题数据
        String judgeIds = request.getParameter("judgeIds");
        // 获取填空题总分数
        String judgeIdsScore = request.getParameter("judgeIdsScore");
        // 获取填空题总数
        String judgeIdsNum = request.getParameter("judgeIdsNum");

        // 获取主观题数据
        String subjectiveIds = request.getParameter("subjectiveIds");
        // 获取主观题总分数
        String subjectiveIdsScore = request.getParameter("subjectiveIdsScore");
        // 获取主观题总数
        String subjectiveIdsNum = request.getParameter("subjectiveIdsNum");
        
        // 获取班级id
        String courseId = request.getParameter("coursID");
        // 规则名设置
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
        String ruleName = dateFormat.format(date);
        
        // 获取总分
        Integer allScore = Integer.valueOf(choiceIdsScore) + Integer.valueOf(multipleIdsScore) + Integer.valueOf(blankIdsScore)
                + Integer.valueOf(judgeIdsScore) + Integer.valueOf(subjectiveIdsScore);

        ExamRule paperRule = new ExamRule();
        paperRule.setSingleRules(choiceIds);
        paperRule.setSingleScore(Integer.valueOf(choiceIdsScore));
        paperRule.setSingleNum(Integer.valueOf(choiceIdsNum));

        paperRule.setMultipleRules(multipleId);
        paperRule.setMultipleScore(Integer.valueOf(multipleIdsScore));
        paperRule.setMultipleNum(Integer.valueOf(multipleIdsNum));

        paperRule.setCompletionRules(blankIds);
        paperRule.setCompletionScore(Integer.valueOf(blankIdsScore));
        paperRule.setCompletionNum(Integer.valueOf(blankIdsNum));

        paperRule.setJudgmentRules(judgeIds);
        paperRule.setJudgmentScore(Integer.valueOf(judgeIdsScore));
        paperRule.setJudgmentNum(Integer.valueOf(judgeIdsNum));

        paperRule.setSubjectiveRules(subjectiveIds);
        paperRule.setSubjectiveScore(Integer.valueOf(subjectiveIdsScore));
        paperRule.setSubjectiveNum(Integer.valueOf(subjectiveIdsNum));
        	
        // 设置课程id
        THCCoursePO course = new THCCoursePO();
        course.setId(Integer.valueOf(courseId));
        paperRule.setCourse(course);
        paperRule.setAllScore(allScore);
        paperRule.setRuleType(Integer.valueOf(1));
        paperRule.setRuleName(ruleName);

        int row = ExamRuleService.insert(paperRule);
        if(row == 1) {
        	return "OK";
        } else {
        	return "NO";
        }
    }
	
	
	 /**
     * 修改手动提交
     */
	@RequestMapping("/updateHandRule")
	@ResponseBody
    public String updateHandRule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// 获取规则id
    	Integer ruleId = Integer.valueOf(request.getParameter("id"));
    	// 获取单选题数据
        String choiceIds = request.getParameter("choiceIds");
        // 获取单选题总分数
        String choiceIdsScore = request.getParameter("choiceIdsScore");
        // 获取单选题总数
        String choiceIdsNum = request.getParameter("choiceIdsNum");

        // 获取多选题数据
        String multipleId = request.getParameter("multipleId");
        // 获取多选题总分数
        String multipleIdsScore = request.getParameter("multipleIdsScore");
        // 获取多选题总数
        String multipleIdsNum = request.getParameter("multipleIdsNum");

        // 获取判断题数据
        String blankIds = request.getParameter("blankIds");
        // 获取判断题总分数
        String blankIdsScore = request.getParameter("blankIdsScore");
        // 获取判断题总数
        String blankIdsNum = request.getParameter("blankIdsNum");

        // 获取填空题数据
        String judgeIds = request.getParameter("judgeIds");
        // 获取填空题总分数
        String judgeIdsScore = request.getParameter("judgeIdsScore");
        // 获取填空题总数
        String judgeIdsNum = request.getParameter("judgeIdsNum");

        // 获取主观题数据
        String subjectiveIds = request.getParameter("subjectiveIds");
        // 获取主观题总分数
        String subjectiveIdsScore = request.getParameter("subjectiveIdsScore");
        // 获取主观题总数
        String subjectiveIdsNum = request.getParameter("subjectiveIdsNum");
        
        // 获取班级id
        String courseId = request.getParameter("coursID");
        // 规则名设置
        String ruleName = request.getParameter("ruleName");
        
        // 获取总分
        Integer allScore = Integer.valueOf(choiceIdsScore) + Integer.valueOf(multipleIdsScore) + Integer.valueOf(blankIdsScore)
                + Integer.valueOf(judgeIdsScore) + Integer.valueOf(subjectiveIdsScore);

        ExamRule paperRule = new ExamRule();
        paperRule.setSingleRules(choiceIds);
        paperRule.setSingleScore(Integer.valueOf(choiceIdsScore));
        paperRule.setSingleNum(Integer.valueOf(choiceIdsNum));

        paperRule.setMultipleRules(multipleId);
        paperRule.setMultipleScore(Integer.valueOf(multipleIdsScore));
        paperRule.setMultipleNum(Integer.valueOf(multipleIdsNum));

        paperRule.setCompletionRules(blankIds);
        paperRule.setCompletionScore(Integer.valueOf(blankIdsScore));
        paperRule.setCompletionNum(Integer.valueOf(blankIdsNum));

        paperRule.setJudgmentRules(judgeIds);
        paperRule.setJudgmentScore(Integer.valueOf(judgeIdsScore));
        paperRule.setJudgmentNum(Integer.valueOf(judgeIdsNum));

        paperRule.setSubjectiveRules(subjectiveIds);
        paperRule.setSubjectiveScore(Integer.valueOf(subjectiveIdsScore));
        paperRule.setSubjectiveNum(Integer.valueOf(subjectiveIdsNum));
        	
        // 设置课程id
        THCCoursePO course = new THCCoursePO();
        course.setId(Integer.valueOf(courseId));
        paperRule.setCourse(course);
        paperRule.setAllScore(allScore);
        paperRule.setRuleType(Integer.valueOf(1));
        paperRule.setRuleName(ruleName);
        paperRule.setId(ruleId);
        
        int row = ExamRuleService.update(paperRule);
        if(row == 1) {
        	return "OK";
        } else {
        	return "NO";
        }
    }
	
	 /**
     * 初始化自动组卷
     */
	@RequestMapping("/initAutoPaper")
    public String initAutoPaper(HttpServletRequest request, HttpServletResponse response) {
    	
		// 查询所有单选题
    	SingleChoice singleChoice = new SingleChoice();
    	List<SingleChoice> singleChoiceList = SingleChoiceService.findList(singleChoice);
    	// 查询所有多选题
    	MultipleChoice multipleChoice = new MultipleChoice();
    	List<MultipleChoice> multipleChoiceList = MultipleChoiceService.findList(multipleChoice);
    	// 查询所有判断题
    	Judgment judgment = new Judgment();
    	List<Judgment> judgmentList = JudgmentService.findList(judgment);
    	// 查询所有填空题
    	Completion completion = new Completion();
    	List<Completion> completionList = CompletionService.findList(completion);
    	// 查询所有主观题
    	Subjective subjective = new Subjective();
    	List<Subjective> subjectiveList = subjectiveService.findList(subjective);
    	
        if(CollectionUtils.isNotBlank(singleChoiceList)){
            request.setAttribute("sChoice",singleChoiceList.size());
        }
        if(CollectionUtils.isNotBlank(multipleChoiceList)){
            request.setAttribute("mChoice",multipleChoiceList.size());
        }
        if(CollectionUtils.isNotBlank(completionList)){
            request.setAttribute("blanks",completionList.size());
        }
        if(CollectionUtils.isNotBlank(judgmentList)){
            request.setAttribute("judges",judgmentList.size());
        }
        if(CollectionUtils.isNotBlank(subjectiveList)){
            request.setAttribute("subjectives",subjectiveList.size());
        }
        return "paper/autoPaper";
    }
	
}
