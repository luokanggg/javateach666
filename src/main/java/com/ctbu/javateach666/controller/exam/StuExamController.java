package com.ctbu.javateach666.controller.exam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ctbu.javateach666.pojo.po.LKStucoursePO;
import com.ctbu.javateach666.pojo.po.exam.Achievement;
import com.ctbu.javateach666.pojo.po.exam.AnswerSheet;
import com.ctbu.javateach666.pojo.po.exam.ExamPaper;
import com.ctbu.javateach666.pojo.po.exam.ExamRule;
import com.ctbu.javateach666.pojo.po.exam.ReleaseExam;
import com.ctbu.javateach666.pojo.po.exam.StuExam;
import com.ctbu.javateach666.pojo.po.kingother.Account;
import com.ctbu.javateach666.pojo.po.kingother.QuestionAnswer;
import com.ctbu.javateach666.pojo.po.kingother.TeaCourse;
import com.ctbu.javateach666.pojo.po.questions.Completion;
import com.ctbu.javateach666.pojo.po.questions.Judgment;
import com.ctbu.javateach666.pojo.po.questions.MultipleChoice;
import com.ctbu.javateach666.pojo.po.questions.SingleChoice;
import com.ctbu.javateach666.pojo.po.questions.Subjective;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.exam.AchievementService;
import com.ctbu.javateach666.service.interfac.exam.AnswerSheetService;
import com.ctbu.javateach666.service.interfac.exam.ExamService;
import com.ctbu.javateach666.service.interfac.exam.StuExamService;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;
import com.ctbu.javateach666.service.interfac.kingother.TeaCourseService;
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
 * 学生考试control
 * @author king
 *
 */
@Controller
@RequestMapping("/stuexam")
public class StuExamController {

	@Autowired
	private StuExamService StuExamService;
	
	@Autowired
	private AccountService AccountService;
	
	@Autowired
	private AnswerSheetService AnswerSheetService;
	
	@Autowired
	private AchievementService AchievementService;
	
	@Autowired
	private ExamService ExamService;
	
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
	private TeaCourseService TeaCourseService;
	/**
	 * 跳转到学生考试页面
	 * @return
	 */
	@RequestMapping("/stuexam")
	public String initStuExam() {
		return "exam/stuexam";
	}
	
	/**
	 * 加载考试信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/stuexams")
	public String listStuExam(HttpServletRequest request, HttpServletResponse response) {
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
        
        StuExam stuExam = new StuExam();
        LKStucoursePO stucourse = new LKStucoursePO();
        ReleaseExam releaseExam = new ReleaseExam();
        stucourse.setStuid(account.getUserdetailid());
        stuExam.setStucourse(stucourse);
        
        Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		releaseExam.setCouyear(Integer.valueOf(format.format(date)));
		if(date.getMonth() <= 7) {
			releaseExam.setSemester(1);
		} else {
			releaseExam.setSemester(2);
		}
		stuExam.setRelease(releaseExam);
		
        List<StuExam> findList = StuExamService.findList(stuExam);
        
        String json = PageUtil.findPage(page, rows, findList);
		return json;
	}
	
	/**
	 * 判断是否已经参加过该考试
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isAreadyExam")
	public Integer isAreadyExam(@RequestParam("examid")String examid) {
		// 获取当前用户的id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        
        AnswerSheet answerSheet = new AnswerSheet();
        answerSheet.setStuId(Integer.valueOf(account.getUserdetailid()));
        answerSheet.setExamId(Integer.valueOf(examid));
        
        List<AnswerSheet> findList = AnswerSheetService.findList(answerSheet);
        if(CollectionUtils.isNotBlank(findList)) {
        	return 1;
        } else {
        	return 0;
        }
	}
	
	/**
	 * 参加考试
	 * @param examid
	 * @return
	 */
	@RequestMapping("/startExam")
	public String startExam(@RequestParam("releaseId")String releaseId,@RequestParam("examid")String examid,Model model) {
		// 获取当前用户的id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
        AnswerSheet answerSheet = new AnswerSheet();
        answerSheet.setStuId(Integer.valueOf(account.getUserdetailid()));
        answerSheet.setExamId(Integer.valueOf(examid));
        
        List<AnswerSheet> findList = AnswerSheetService.findList(answerSheet);
        if(CollectionUtils.isNotBlank(findList)) {
        	model.addAttribute("paper", false);
        } else {
			ReleaseExam releaseExam = ExamService.get(Integer.valueOf(releaseId));
			
			model.addAttribute("paper", true);
			// 获取课程信息
			THCCoursePO course = releaseExam.getCourse();
			model.addAttribute("course", course);
			// 获取试卷信息
			ExamPaper examPaper = releaseExam.getExamPaper();
			examPaper = ExamPaperService.get(examPaper.getId());
			model.addAttribute("paperID", examPaper.getId());
			// 获取规则信息
			ExamRule examRule = ExamRuleService.get(examPaper.getExamRule().getId());
			
			// 考试时间
			Integer examTime = releaseExam.getExamTime();
			model.addAttribute("times", examTime);
			
			// 单选题
			List<SingleChoice> choices = new ArrayList<>();
			String singleChoiceChecked = examRule.getSingleRules();
			Integer singleChoiceNum = examRule.getSingleNum();
			Integer singleScore = examRule.getSingleScore();
			List<QuestionPaper> choiceQues = JSONArray.parseArray(singleChoiceChecked, QuestionPaper.class);
			if (!singleChoiceChecked.equals("") && singleChoiceNum != 0 && singleScore.doubleValue() != 0) {
				for (QuestionPaper choiceQue : choiceQues) {
					SingleChoice singleChoice = SingleChoiceService.get(choiceQue.getId());
					choices.add(singleChoice);
				}
				model.addAttribute("choices", choices); // 单选择题内容
				model.addAttribute("choiceQues", choiceQues); // 单选题单个分数
				model.addAttribute("singleChoiceNum", singleChoiceNum); // 单选题总个数
				if (examRule.getRuleType() == 0) {
					int singS = (int) (singleChoiceNum * choiceQues.get(0).getScore());
					model.addAttribute("singleScore", singS); // 单选题总分数
				} else {
					model.addAttribute("singleScore", singleScore);
				}
			}
	
			// 多选题
			List<MultipleChoice> mulChoices = new ArrayList<>();
			String mulChoiceChecked = examRule.getMultipleRules();
			Integer mulChoiceNum = examRule.getMultipleNum();
			Integer mulScore = examRule.getMultipleScore();
			List<QuestionPaper> mulChoiceQues = JSONArray.parseArray(mulChoiceChecked, QuestionPaper.class);
			if (!mulChoiceChecked.equals("") && mulChoiceNum != 0 && mulScore.doubleValue() != 0) {
				for (QuestionPaper questionPaper : mulChoiceQues) {
					MultipleChoice multipleChoice = MultipleChoiceService.get(questionPaper.getId());
					mulChoices.add(multipleChoice);
				}
				model.addAttribute("mulChoices", mulChoices);
				model.addAttribute("mulChoiceQues", mulChoiceQues);
				model.addAttribute("mulChoiceNum", mulChoiceNum);
	
				if (examRule.getRuleType() == 0) {
					int mulS = (int) (mulChoiceNum * mulChoiceQues.get(0).getScore());
					model.addAttribute("mulScore", mulS);
				} else {
					model.addAttribute("mulScore", mulScore);
				}
			}
	
			// 判断题
			List<Judgment> judges = new ArrayList<>();
			String judgeChoiceChecked = examRule.getJudgmentRules();
			Integer judgeChoiceNum = examRule.getJudgmentNum();
			Integer judgeScore = examRule.getJudgmentScore();
			List<QuestionPaper> judgeQues = JSONArray.parseArray(judgeChoiceChecked, QuestionPaper.class);
			if (!judgeChoiceChecked.equals("") && judgeChoiceNum != 0 && judgeScore.doubleValue() != 0) {
				for (QuestionPaper judgeQue : judgeQues) {
					Judgment judgment = JudgmentService.get(judgeQue.getId());
					judges.add(judgment);
				}
				model.addAttribute("judges", judges);
				model.addAttribute("judgeQues", judgeQues);
				model.addAttribute("judgeChoiceNum", judgeChoiceNum);
	
				if (examRule.getRuleType() == 0) {
					int judgeS = (int) (judgeChoiceNum * judgeQues.get(0).getScore());
					model.addAttribute("judgeScore", judgeS);
				} else {
					model.addAttribute("judgeScore", judgeScore);
				}
			}
	
			// 填空题
			List<Completion> blanks = new ArrayList<>();
			String blankChoiceChecked = examRule.getCompletionRules();
			Integer blankChoiceNum = examRule.getCompletionNum();
			Integer blankScore = examRule.getCompletionScore();
			List<QuestionPaper> blankQues = JSONArray.parseArray(blankChoiceChecked, QuestionPaper.class);
			if (!blankChoiceChecked.equals("") && blankChoiceNum != 0 && blankScore.doubleValue() != 0) {
				for (QuestionPaper blankQue : blankQues) {
					Completion completion = CompletionService.get(blankQue.getId());
					blanks.add(completion);
				}
				model.addAttribute("blanks", blanks);
				model.addAttribute("blankQues", blankQues);
				model.addAttribute("blankChoiceNum", blankChoiceNum);
	
				if (examRule.getRuleType() == 0) {
					int blankS = (int) (blankChoiceNum * blankQues.get(0).getScore());
					model.addAttribute("blankScore", blankS);
				} else {
					model.addAttribute("blankScore", blankScore);
				}
			}
	
			// 主观题
			List<Subjective> subjectives = new ArrayList<>();
			String subChoiceChecked = examRule.getSubjectiveRules();
			Integer subChoiceNum = examRule.getSubjectiveNum();
			Integer subScore = examRule.getSubjectiveScore();
			List<QuestionPaper> subQues = JSONArray.parseArray(subChoiceChecked, QuestionPaper.class);
			if (!subChoiceChecked.equals("") && subChoiceNum != 0 && subScore.doubleValue() != 0) {
				for (QuestionPaper subQue : subQues) {
					Subjective subjective = SubjectiveService.get(subQue.getId());
					subjectives.add(subjective);
				}
				model.addAttribute("subjectives", subjectives);
				model.addAttribute("subQues", subQues);
				model.addAttribute("subChoiceNum", subChoiceNum);
	
				if (examRule.getRuleType() == 0) {
					int subS = (int) (subChoiceNum * subQues.get(0).getScore());
					model.addAttribute("subScore", subS);
				} else {
					model.addAttribute("subScore", subScore);
				}
			}
        }
		return "exam/startExam";
	}
	
	/**
	 * 保存答案和分数
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveStuExam",produces = "text/plain;charset=utf-8")
	public String saveStuExam(HttpServletRequest request, HttpServletResponse response,Model model) {

		String paperID = request.getParameter("paperID");
		Integer examId = Integer.valueOf(paperID);

		String courseID = request.getParameter("courseID");
		
		// 获取当前用户的id
        String userName = UserMessageUtils.getNowUserName();
        Account account = new Account();
        account.setUsername(userName);
        List<Account> accountlist = AccountService.findList(account);
        if(CollectionUtils.isNotBlank(accountlist)) {
        	account = accountlist.get(0);
        }
		Integer stuId = account.getUserdetailid(); // 学生id
		ExamPaper examPaper = ExamPaperService.get(examId); // 试卷信息
		
		List<QuestionAnswer> answers = new ArrayList<>();
		Integer score = 0;
		AnswerSheet answerSheet = new AnswerSheet(); // 答案对象
		Achievement achievement = new Achievement(); // 成绩对象
		if (paperID != null && !"".equals(paperID)) {
			Integer paperID1 = examPaper.getId();
			answerSheet.setExamId(paperID1);	// 将试卷id保存到答案表中
			answerSheet.setStuId(account.getUserdetailid());	// 将学生id保存到答案表中

			achievement.setPaperId(paperID1);	// 成绩保存试卷id
			achievement.setStuId(stuId);	// 成绩保存学生id
			
			TeaCourse teaCourse = new TeaCourse();
			THCCoursePO course = new THCCoursePO();
			course.setId(Integer.valueOf(courseID));
			teaCourse.setCourse(course);
			List<TeaCourse> findList = TeaCourseService.findList(teaCourse);
			if(CollectionUtils.isNotBlank(findList)) {
				teaCourse = findList.get(0);
			}
			achievement.setTeaId(teaCourse.getTeaid());	// 成绩保存教师id
			answerSheet.setTeaId(teaCourse.getTeaid());
			
			// 主观题
			String[] subjectives = request.getParameterValues("subjective");
			if (subjectives != null) {
				for (String subID : subjectives) {
					String subjectiveAnswer = request.getParameter("subjectiveNum" + subID);
					QuestionAnswer stuSubjective = new QuestionAnswer();
					stuSubjective.setId(Integer.valueOf(subID));
					stuSubjective.setChecked(subjectiveAnswer);
					answers.add(stuSubjective);
				}
				String subjectiveJson = JSON.toJSONString(answers);
				answers.clear();
				answerSheet.setSubjectiveAnswer(subjectiveJson);	// 保存主观题答案
			}
			// 填空题
			String[] blanks = request.getParameterValues("blank");
			if (blanks != null) {
				for (String blankID : blanks) {
					Integer id = Integer.valueOf(blankID);
					String blankScore = request.getParameter("blankScore" + blankID);
					String blankAnswer = request.getParameter("blankNum" + blankID);
					Completion completion = CompletionService.get(id);
					QuestionAnswer stuBlank = new QuestionAnswer();
					stuBlank.setId(id);
					stuBlank.setChecked(blankAnswer);
					if (completion.getCompletionAnswer().equals(blankAnswer)) {
						score += Integer.valueOf(blankScore);
						achievement.setCompletionScore(Integer.valueOf(blankScore));
					}
					answers.add(stuBlank);
				}
			}
			answerSheet.setCompletionAnswer(JSON.toJSONString(answers));
			// 判断题
			answers.clear();
			String[] judges = request.getParameterValues("judge");
			if (judges != null) {
				for (String judgeID : judges) {
					Integer id = Integer.valueOf(judgeID);
					String judgeScore = request.getParameter("judgeScore" + judgeID);
					String judgeAnswer = request.getParameter("judgeNum" + judgeID);
					Judgment judgment = JudgmentService.get(id);
					if (judgment.getJudgmentAnswer().equals(judgeAnswer)) {
						score += Integer.valueOf(judgeScore);
						achievement.setJudgmentScore(Integer.valueOf(judgeScore));
					}
					QuestionAnswer stuJudge = new QuestionAnswer();
					stuJudge.setChecked(judgeAnswer);
					stuJudge.setId(id);
					answers.add(stuJudge);
				}
			}
			answerSheet.setJudgmentAnswer(JSON.toJSONString(answers));
			// 多选题
			answers.clear();
			String[] multis = request.getParameterValues("multi");
			if (multis != null) {
				for (String multiID : multis) {
					String multiScore = request.getParameter("multiScore" + multiID);
					String[] multAnswers = request.getParameterValues("multChoice" + multiID);
					String multAnswer = "";
					if (multAnswers != null) {
						for (String ma : multAnswers) {
							multAnswer += ma;
						}
					}
					Integer id = Integer.valueOf(multiID);
					MultipleChoice multipleChoice = MultipleChoiceService.get(id);
					if (multipleChoice.getMultipleAnswer().equals(multAnswer)) {
						score += Integer.valueOf(multiScore);
						achievement.setMultipleScore(Integer.valueOf(multiScore));
					}
					QuestionAnswer stuMultChoice = new QuestionAnswer();
					stuMultChoice.setId(id);
					stuMultChoice.setChecked(multAnswer);
					answers.add(stuMultChoice);
				}
			}
			answerSheet.setMultipleAnswer(JSON.toJSONString(answers));
			// 单选题
			answers.clear();
			String[] singles = request.getParameterValues("single");
			if (singles != null) {
				for (String singleID : singles) {
					Integer id = Integer.valueOf(singleID);
					String singleAnswer = request.getParameter("singleChoice" + singleID);
					String singleScore = request.getParameter("singleScore" + singleID);
					SingleChoice singleChoice = SingleChoiceService.get(id);
					if (singleChoice.getSingleAnswer().equals(singleAnswer)) {
						score += Integer.valueOf(singleScore);
						achievement.setSingleScore(Integer.valueOf(singleScore));
					}
					QuestionAnswer stuSingle = new QuestionAnswer();
					stuSingle.setId(id);
					stuSingle.setChecked(singleAnswer);
					answers.add(stuSingle);
				}
			}
			answerSheet.setSingleAnswer(JSON.toJSONString(answers));
		}
		answerSheet.setState("0");	// 设置未阅卷
		int row1 = AnswerSheetService.insert(answerSheet);	// 保存答案
		achievement.setScore(score); // 设置当前总分数
		int row2 = AchievementService.insert(achievement);	// 保存分数
		
		return row1 == 1 && row2 == 1 ? "提交成功！" : "提交失败！";
		
	}
	
}
