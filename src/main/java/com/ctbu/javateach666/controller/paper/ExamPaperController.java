package com.ctbu.javateach666.controller.paper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.ctbu.javateach666.pojo.po.exam.ExamPaper;
import com.ctbu.javateach666.pojo.po.exam.ExamRule;
import com.ctbu.javateach666.pojo.po.kingother.Account;
import com.ctbu.javateach666.pojo.po.questions.Completion;
import com.ctbu.javateach666.pojo.po.questions.Judgment;
import com.ctbu.javateach666.pojo.po.questions.MultipleChoice;
import com.ctbu.javateach666.pojo.po.questions.SingleChoice;
import com.ctbu.javateach666.pojo.po.questions.Subjective;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.paper.ExamPaperService;
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
 * 试卷control
 * @author king
 *
 */
@Controller
@RequestMapping("/exampaper")
public class ExamPaperController {
	
	@Autowired
	private ExamPaperService ExamPaperService;
	
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
	private SubjectiveService SubjectiveService;
	
	@Autowired
	private AccountService AccountService;
	
	
	/**
	 * 转发到试卷页面
	 * @return
	 */
	@RequestMapping("/exampaper")
	public String exampaper(Model model) {
		return "paper/listPapers";
	}
	
	/**
	 * 加载试卷列表
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/exampapers")
	public String listExamPaper(HttpServletRequest request,HttpServletResponse response) {
		// 获取传入当前页和每页显示数
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("rows"));
        
        ExamPaper examPaper = new ExamPaper();
        
        // 查询参数
        String couseId = request.getParameter("course");
        String pname = request.getParameter("pname");
        String rname = request.getParameter("rname");
        if(couseId != null && !"".equals(couseId)) {
        	THCCoursePO course = new THCCoursePO();
        	course.setId(Integer.valueOf(couseId));
        	examPaper.setCourse(course);
        }
        if(pname != null && !"".equals(pname)) {
        	examPaper.setExamPaperName(pname);
        }
        if(rname != null && !"".equals(rname)) {
        	examPaper.setExamRuleName(rname);
        }
        
        // 传入当前教师id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        examPaper.setTeaId(account.getUserdetailid());
        
        List<ExamPaper> list = ExamPaperService.findList(examPaper);
		String json = PageUtil.findPage(page, rows, list);
		return json;
	}
	
	/**
	 * 获取考试规则列表
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listExamRule")
	public String listExamRule(HttpServletRequest request,HttpServletResponse response) {
		// 传入科目，查询规则
		String courseid = request.getParameter("coursId");
		THCCoursePO course = new THCCoursePO();
		course.setId(Integer.valueOf(courseid));
		ExamRule examRule = new ExamRule();
		examRule.setCourse(course);
		List<ExamRule> rulelist = ExamRuleService.findList(examRule);
		String json = JSON.toJSONString(rulelist);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 逻辑删除试卷
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deleteExamPaper")
	public String deleteExamPaper(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("exampaperids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			ExamPaper examPaper = new ExamPaper();
			examPaper.setId(Integer.valueOf(id));
			ExamPaperService.deleteByLogic(examPaper);
		}
		return "OK";
	}
	
	/**
	 * 根据规则id获取试卷对象
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExamPaperById")
	public String getExamPaperById(HttpServletRequest request) {
		String id = request.getParameter("paperId");
		ExamPaper examPaper = ExamPaperService.get(Integer.valueOf(id));
		String jsonString = "";
		if(examPaper != null) {
			jsonString = JSON.toJSONString(examPaper);
		} 
		return jsonString;
	}
	
	/**
	 * 保存试卷信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveExamPaper")
	public String saveExamPaper(ExamPaper exampaper) {
		int row = ExamPaperService.insert(exampaper);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	/**
	 * 修改试卷信息
	 * @param exampaper
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateExamPaper")
	public String updateExamPaper(ExamPaper exampaper) {
		int row = ExamPaperService.update(exampaper);
		if(row == 1) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	
	/**
     * 初始化查看试卷
     */
	@RequestMapping("/initPaper")
    public String initPaper(@RequestParam("paperId") String paperId,HttpServletRequest request, HttpServletResponse response,Model model) {
        ExamPaper examPaper = ExamPaperService.get(Integer.valueOf(paperId));
        ExamRule examRule = ExamRuleService.get(examPaper.getExamRule().getId());
        // 单选题
        List<SingleChoice> choices = new ArrayList<SingleChoice>();
        String singleRules = examRule.getSingleRules();
        Integer singleNum = examRule.getSingleNum();
        Integer singleScore = examRule.getSingleScore();
        List<QuestionPaper> choiceQues = JSONArray.parseArray(singleRules, QuestionPaper.class);
        
        if(!singleRules.equals("") && singleNum != 0 && singleScore != 0) {
	        for (QuestionPaper choiceQue : choiceQues) {
	        	SingleChoice singleChoice = SingleChoiceService.get(choiceQue.getId());
	            choices.add(singleChoice);
	        }
	        model.addAttribute("choices", choices);                  // 单选择题内容
	        model.addAttribute("choiceQues", choiceQues);            // 单选题单个分数
	        model.addAttribute("singleChoiceNum", singleNum);  		 // 单选题总个数
	        if(examRule.getRuleType() == 2) {
	        	int singS = (int) (singleNum * choiceQues.get(0).getScore());
	        	request.setAttribute("singleScore", singS);          // 单选题总分数
	        } else {
	        	request.setAttribute("singleScore", singleScore);
	        }
        }

        // 多选题
        List<MultipleChoice> mulChoices = new ArrayList<MultipleChoice>();
        String mulChoiceChecked = examRule.getMultipleRules();
        Integer mulChoiceNum = examRule.getMultipleNum();
        Integer mulScore = examRule.getMultipleScore();
        List<QuestionPaper> mulChoiceQues = JSONArray.parseArray(mulChoiceChecked, QuestionPaper.class);
        if(!mulChoiceChecked.equals("") && mulChoiceNum != 0 && mulScore.doubleValue() != 0) {
        for (QuestionPaper questionPaper : mulChoiceQues) {
        	MultipleChoice multipleChoice = MultipleChoiceService.get(questionPaper.getId());
        	mulChoices.add(multipleChoice);
        }
        request.setAttribute("mulChoices", mulChoices);
        request.setAttribute("mulChoiceQues", mulChoiceQues);
        request.setAttribute("mulChoiceNum", mulChoiceNum);
        
        if(examRule.getRuleType() == 0) {
        	int mulS = (int) (mulChoiceNum * mulChoiceQues.get(0).getScore());
        	request.setAttribute("mulScore", mulS);         
        } else {
        	request.setAttribute("mulScore", mulScore);
        }
        }

        // 判断题
        List<Judgment> judges = new ArrayList<Judgment>();
        String judgeChoiceChecked = examRule.getJudgmentRules();
        Integer judgeChoiceNum = examRule.getJudgmentNum();
        Integer judgeScore = examRule.getJudgmentScore();
        List<QuestionPaper> judgeQues = JSONArray.parseArray(judgeChoiceChecked, QuestionPaper.class);
        if(!judgeChoiceChecked.equals("") && judgeChoiceNum != 0 && judgeScore.doubleValue() != 0) {
        for (QuestionPaper judgeQue : judgeQues) {
        	Judgment judgment = JudgmentService.get(judgeQue.getId());
            judges.add(judgment);
        }
        request.setAttribute("judges", judges);
        request.setAttribute("judgeQues", judgeQues);
        request.setAttribute("judgeChoiceNum", judgeChoiceNum);
        
        if(examRule.getRuleType() == 0) {
        	int judgeS = (int) (judgeChoiceNum * judgeQues.get(0).getScore());
        	request.setAttribute("judgeScore", judgeS);      
        } else {
        	request.setAttribute("judgeScore", judgeScore);
        }
        }

        // 填空题
        List<Completion> blanks = new ArrayList<Completion>();
        String blankChoiceChecked = examRule.getCompletionRules();
        Integer blankChoiceNum = examRule.getCompletionNum();
        Integer blankScore = examRule.getCompletionScore();
        List<QuestionPaper> blankQues = JSONArray.parseArray(blankChoiceChecked, QuestionPaper.class);
        if(!blankChoiceChecked.equals("") && blankChoiceNum != 0 && blankScore.doubleValue() != 0) {
        for (QuestionPaper blankQue : blankQues) {
            Completion completion = CompletionService.get(blankQue.getId());
        	blanks.add(completion);
        }
        request.setAttribute("blanks", blanks);
        request.setAttribute("blankQues", blankQues);
        request.setAttribute("blankChoiceNum", blankChoiceNum);
        
        if(examRule.getRuleType() == 0) {
        	int blankS = (int) (blankChoiceNum * blankQues.get(0).getScore());
        	request.setAttribute("blankScore", blankS);    
        } else {
        	request.setAttribute("blankScore", blankScore);
        }
    	}

        // 主观题
        List<Subjective> subjectives = new ArrayList<Subjective>();
        String subChoiceChecked = examRule.getSubjectiveRules();
        Integer subChoiceNum = examRule.getSubjectiveNum();
        Integer subScore = examRule.getSingleScore();
        List<QuestionPaper> subQues = JSONArray.parseArray(subChoiceChecked, QuestionPaper.class);
        if(!subChoiceChecked.equals("") && subChoiceNum != 0 && subScore.doubleValue() != 0) {
        	for (QuestionPaper subQue : subQues) {
        		Subjective subjective = SubjectiveService.get(subQue.getId());
        		subjectives.add(subjective);
	        }
	        request.setAttribute("subjectives", subjectives);
	        request.setAttribute("subQues", subQues);
	        request.setAttribute("subChoiceNum", subChoiceNum);
	        
	        if(examRule.getRuleType() == 0) {
	        	int subS = (int) (subChoiceNum * subQues.get(0).getScore());
	        	request.setAttribute("subScore", subS);    
	        } else {
	        	request.setAttribute("subScore", subScore);
	        }
        }

        return "paper/showPaper";
    }
	
	/**
	 * 根据科目id获取试卷对象
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExamPaperBycourse")
	public String getExamPaperBycourse(@RequestParam("coursId") String coursId,HttpServletRequest request) {
		THCCoursePO course = new THCCoursePO();
		course.setId(Integer.valueOf(coursId));
		ExamPaper examPaper = new ExamPaper();
		examPaper.setCourse(course);
		List<ExamPaper> findList = ExamPaperService.findList(examPaper);
		String jsonString = "";
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		for(ExamPaper ep : findList) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", ep.getId());
			map.put("name", ep.getExamPaperName());
			list.add(map);
		}
		jsonString = JSON.toJSONString(list);
		return jsonString;
	}
}
