package com.ctbu.javateach666.service.impl.practice;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.CompletionDao;
import com.ctbu.javateach666.dao.JudgmentDao;
import com.ctbu.javateach666.dao.MultipleChoiceDao;
import com.ctbu.javateach666.dao.SingleChoiceDao;
import com.ctbu.javateach666.dao.SubjectiveDao;
import com.ctbu.javateach666.pojo.po.questions.Completion;
import com.ctbu.javateach666.pojo.po.questions.Judgment;
import com.ctbu.javateach666.pojo.po.questions.MultipleChoice;
import com.ctbu.javateach666.pojo.po.questions.SingleChoice;
import com.ctbu.javateach666.pojo.po.questions.Subjective;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.service.interfac.practice.PracticeService;

/**
 * 练习service实现
 * @author king
 *
 */
@Service
public class PracticeServiceImpl implements PracticeService{
	
	@Autowired
	private SingleChoiceDao SingleChoiceDao;
	@Autowired
	private MultipleChoiceDao MultipleChoiceDao;
	@Autowired
	private JudgmentDao JudgmentDao;
	@Autowired
	private CompletionDao CompletionDao;
	@Autowired
	private SubjectiveDao SubjectiveDao;
	
	
	public String getQuestionsToPaper(String nums, String scores,Integer courseId, String questionType) {
		Integer num = Integer.valueOf(nums);
		Integer score = Integer.valueOf(scores);
		if(num > 0) {
			String json ="";
			THCCoursePO course = new THCCoursePO();
			course.setId(courseId);
			if(questionType.equals("choice")) {
				SingleChoice singleChoice = new SingleChoice();
				singleChoice.setCourse(course);
				List<SingleChoice> allChoice = SingleChoiceDao.findList(singleChoice);
				json = "[";
				Collections.shuffle(allChoice);
				for(int i = 0; i < num; i++) {
					json += "{'id':"+allChoice.get(i).getId();
					json += ",'score':"+score+"},";
				}
				json = json.substring(0, json.length()-1);
				json += "]";
			} else if(questionType.equals("multiple")) {
				MultipleChoice multipleChoice = new MultipleChoice();
				multipleChoice.setCourse(course);
				List<MultipleChoice> allMultiple = MultipleChoiceDao.findList(multipleChoice);
				json = "[";
				Collections.shuffle(allMultiple);
				for(int i = 0; i < num; i++) {
					json += "{'id':"+allMultiple.get(i).getId();
					json += ",'score':"+score+"},";
				}
				json = json.substring(0, json.length()-1);
				json += "]";
			} else if(questionType.equals("blank")) {
				Completion completion = new Completion();
				completion.setCourse(course);
				List<Completion> allBlank = CompletionDao.findList(completion);
				json = "[";
				Collections.shuffle(allBlank);
				for(int i = 0; i < num; i++) {
					json += "{'id':"+allBlank.get(i).getId();
					json += ",'score':"+score+"},";
				}
				json = json.substring(0, json.length()-1);
				json += "]";
			} else if(questionType.equals("judge")) {
				Judgment judgment = new Judgment();
				judgment.setCourse(course);
				List<Judgment> allJudge = JudgmentDao.findList(judgment);
				json = "[";
				Collections.shuffle(allJudge);
				for(int i = 0; i < num; i++) {
					json += "{'id':"+allJudge.get(i).getId();
					json += ",'score':"+score+"},";
				}
				json = json.substring(0, json.length()-1);
				json += "]";
			} else if(questionType.equals("subjective")) {
				Subjective subjective = new Subjective();
				subjective.setCourse(course);
				List<Subjective> allSubjective = SubjectiveDao.findList(subjective);
				json = "[";
				Collections.shuffle(allSubjective);
				for(int i = 0; i < num; i++) {
					json += "{'id':"+allSubjective.get(i).getId();
					json += ",'score':"+score+"},";
				}
				json = json.substring(0, json.length()-1);
				json += "]";
			}
			
			return json;
		} else {
			return "";
		}
	}
}
