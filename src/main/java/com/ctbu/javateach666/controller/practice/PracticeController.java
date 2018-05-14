package com.ctbu.javateach666.controller.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ctbu.javateach666.pojo.bo.QuestionPaper;
import com.ctbu.javateach666.pojo.po.questions.Completion;
import com.ctbu.javateach666.pojo.po.questions.Judgment;
import com.ctbu.javateach666.pojo.po.questions.MultipleChoice;
import com.ctbu.javateach666.pojo.po.questions.SingleChoice;
import com.ctbu.javateach666.pojo.po.questions.Subjective;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.practice.PracticeService;
import com.ctbu.javateach666.service.interfac.questions.CompletionService;
import com.ctbu.javateach666.service.interfac.questions.JudgmentService;
import com.ctbu.javateach666.service.interfac.questions.MultipleChoiceService;
import com.ctbu.javateach666.service.interfac.questions.SingleChoiceService;
import com.ctbu.javateach666.service.interfac.questions.SubjectiveService;
import com.ctbu.javateach666.util.CollectionUtils;


/**
 * 自主练习control
 * @author king
 *
 */
@Controller
@RequestMapping("/practice")
public class PracticeController {
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
	private PracticeService PracticeService;
	
	/**
	 * 跳转到练习导航页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initPractice")
	public String initPractice() {
        return "practice/practice";
    }
	
	/**
	 * 验证 所选练习方式
	 * @param type
	 * @return
	 */
	@RequestMapping("/practice")
	public String practice(@RequestParam("type") String type) {
        String url = "paper/showPaper";
        if (type != null && !"".equals(type)) {
            switch (type) {
                case "0":
                    break;
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    url = "";
            }
        }
        return url;
    }
	
	/**
	 * 获取练习题目
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getSuject")
	@ResponseBody
	public String getSuject(HttpServletRequest request, HttpServletResponse response) {
    	String subjectType = request.getParameter("ctype");
    	String courseId = request.getParameter("courseId");
    	String subjectNum = request.getParameter("practiceNum");
    	String degree = request.getParameter("degree");
    	Integer number = Integer.valueOf(subjectNum);
    	List<?> subList = null;
    	THCCoursePO course = new THCCoursePO();
    	course.setId(Integer.valueOf(courseId));
    	switch (subjectType) {
		case "choice":
			SingleChoice singleChoice = new SingleChoice();
			singleChoice.setCourse(course);
			singleChoice.setDegree(degree);
			List<SingleChoice> allChoice = SingleChoiceService.findList(singleChoice);
	    	Collections.shuffle(allChoice);
	    	subList = allChoice.subList(0, number > allChoice.size() ? allChoice.size() : number);
			request.getSession().setAttribute("choices", subList);
			break;
		case "multiple":
			MultipleChoice multipleChoice = new MultipleChoice();
			multipleChoice.setCourse(course);
			multipleChoice.setDegree(degree);
			List<MultipleChoice> allMChoice = MultipleChoiceService.findList(multipleChoice);
	    	Collections.shuffle(allMChoice);
	    	subList = allMChoice.subList(0, number > allMChoice.size() ? allMChoice.size() : number);
	    	request.getSession().setAttribute("mulChoices", subList);
			break;
		case "blank":
			Completion completion = new Completion();
			completion.setCourse(course);
			completion.setDegree(degree);
			List<Completion> allBlank = CompletionService.findList(completion);
			Collections.shuffle(allBlank);
	    	subList = allBlank.subList(0, number > allBlank.size() ? allBlank.size() : number);
	    	request.getSession().setAttribute("blanks", subList);
			break;
		case "judge":
			Judgment judgment = new Judgment();
			judgment.setCourse(course);
			judgment.setDegree(degree);
			List<Judgment> allJudge = JudgmentService.findList(judgment);
			Collections.shuffle(allJudge);
	    	subList = allJudge.subList(0, number > allJudge.size() ? allJudge.size() : number);
	    	request.getSession().setAttribute("judges", subList);
			break;
		case "subjective":
			Subjective subjective = new Subjective();
			subjective.setCourse(course);
			subjective.setDegree(degree);
			List<Subjective> allSubjective = SubjectiveService.findList(subjective);
			Collections.shuffle(allSubjective);
	    	subList = allSubjective.subList(0, number > allSubjective.size() ? allSubjective.size() : number);
	    	request.getSession().setAttribute("subjectives", subList);
			break;
		default:
			break;
		}
    	String url = "initSuject";
    	if(subList != null && subList.size() > 0) {
    		return url;
    	} else {
    		return "NO";
    	}
    }
	
	/**
	 * 加载练习题
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/initSuject")
	public String initSuject(HttpServletRequest request, HttpServletResponse response) {
    	
		List<SingleChoice> choice = (List<SingleChoice>) request.getSession().getAttribute("choices");
    	List<MultipleChoice> mulChoice = (List<MultipleChoice>) request.getSession().getAttribute("mulChoices");
    	List<Completion> blank = (List<Completion>) request.getSession().getAttribute("blanks");
    	List<Judgment> judge = (List<Judgment>) request.getSession().getAttribute("judges");
    	List<Subjective> subjective = (List<Subjective>) request.getSession().getAttribute("subjectives");
    	request.getSession().removeAttribute("choices");
    	request.getSession().removeAttribute("mulChoices");
    	request.getSession().removeAttribute("blanks");
    	request.getSession().removeAttribute("judges");
    	request.getSession().removeAttribute("subjectives");
    	if(CollectionUtils.isNotBlank(choice)) {
    		request.setAttribute("choices", choice);
    		request.setAttribute("choicesNum", choice.size());
    		request.setAttribute("practiceTitle", "单选题练习");
    	}
    	if(CollectionUtils.isNotBlank(mulChoice)) {
    		request.setAttribute("mulChoices", mulChoice);
    		request.setAttribute("mulChoicesNum", mulChoice.size());
    		request.setAttribute("practiceTitle", "多选题练习");
    	}
    	if(CollectionUtils.isNotBlank(blank)) {
    		request.setAttribute("blanks", blank);
    		request.setAttribute("blanksNum", blank.size());
    		request.setAttribute("practiceTitle", "填空题练习");
    	}
    	if(CollectionUtils.isNotBlank(judge)) {
    		request.setAttribute("judges", judge);
    		request.setAttribute("judgesNum", judge.size());
    		request.setAttribute("practiceTitle", "判断题练习");
    	}
    	if(CollectionUtils.isNotBlank(subjective)) {
    		request.setAttribute("subjectives", subjective);
    		request.setAttribute("subjectivesNum", subjective.size());
    		request.setAttribute("practiceTitle", "主观题练习");
    	}
    	return "practice/practiceExam";
    }
	
	/**
	 * 加载模拟测试
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/initexamination")
	public String initexamination(@RequestParam("courseId")String cId,HttpServletRequest request, HttpServletResponse response) {
    	Integer courseId = Integer.valueOf(cId);
    	
    	String choiceCk = PracticeService.getQuestionsToPaper("15", "2",courseId, "choice");
        String multipleCk = PracticeService.getQuestionsToPaper("5", "4",courseId, "multiple");
        String blankCk = PracticeService.getQuestionsToPaper("5", "2",courseId, "blank");
        String judgeCk = PracticeService.getQuestionsToPaper("5", "5",courseId, "judge");
        String subjectiveCk = PracticeService.getQuestionsToPaper("3", "5",courseId, "subjective");
    	
        // 根据课程id获取课程名
//		Course course = new CourseServiceImpl().getCourseByID(courseId);
//		request.setAttribute("course", course);
        
        // 单选题
        List<SingleChoice> choices = new ArrayList<>();
        List<QuestionPaper> choiceQues = JSONArray.parseArray(choiceCk, QuestionPaper.class);
		for (QuestionPaper choiceQue : choiceQues) {
			SingleChoice singleChoice = SingleChoiceService.get(choiceQue.getId());
			choices.add(singleChoice);
		}
        
        request.setAttribute("choices", choices); // 单选择题内容
		request.setAttribute("singleChoiceNum", 15); // 单选题总个数
		int singS = 30;
		request.setAttribute("singleScore", singS); // 单选题总分数
		
		// 多选题
		List<MultipleChoice> mulChoices = new ArrayList<>();
		List<QuestionPaper> mulChoiceQues = JSONArray.parseArray(multipleCk, QuestionPaper.class);
		for (QuestionPaper questionPaper : mulChoiceQues) {
			MultipleChoice multipleChoice = MultipleChoiceService.get(questionPaper.getId());
			mulChoices.add(multipleChoice);
		}
		request.setAttribute("mulChoices", mulChoices);
		request.setAttribute("mulChoiceNum", 5);
		int mulS = 20;
		request.setAttribute("mulScore", mulS);
		
		// 判断题
		List<Judgment> judges = new ArrayList<>();
		List<QuestionPaper> judgeQues = JSONArray.parseArray(judgeCk, QuestionPaper.class);
		for (QuestionPaper judgeQue : judgeQues) {
			Judgment judgment = JudgmentService.get(judgeQue.getId());
			judges.add(judgment);
		}
		request.setAttribute("judges", judges);
		request.setAttribute("judgeChoiceNum", 5);
		int judgeS = 10;
		request.setAttribute("judgeScore", judgeS);
		
		// 填空题
		List<Completion> blanks = new ArrayList<>();
		List<QuestionPaper> blankQues = JSONArray.parseArray(blankCk, QuestionPaper.class);
		for (QuestionPaper blankQue : blankQues) {
			Completion completion = CompletionService.get(blankQue.getId());
			blanks.add(completion);
		}
		request.setAttribute("blanks", blanks);
		request.setAttribute("blankChoiceNum", 5);
		int blankS = 25;
		request.setAttribute("blankScore", blankS);

		// 主观题
		List<Subjective> subjectives = new ArrayList<>();
		List<QuestionPaper> subQues = JSONArray.parseArray(subjectiveCk, QuestionPaper.class);
		for (QuestionPaper subQue : subQues) {
			Subjective subjective = SubjectiveService.get(subQue.getId());
			subjectives.add(subjective);
		}
		request.setAttribute("subjectives", subjectives);
		request.setAttribute("subChoiceNum", 3);
		int subS = 15;
		request.setAttribute("subScore", subS);
		
		return "practice/praExam";
    }
}
