package com.ctbu.javateach666.service.interfac.practice;

/**
 * 练习service接口
 * @author king
 *
 */
public interface PracticeService {
	/**
	 * 获取指定数量的题目
	 * @param nums
	 * @param scores
	 * @param courseId
	 * @param questionType
	 * @return
	 */
	public String getQuestionsToPaper(String nums, String scores,Integer courseId, String questionType);
}
