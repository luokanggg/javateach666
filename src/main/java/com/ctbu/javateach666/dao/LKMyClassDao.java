package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseReqBO;
import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseRspBO;
import com.ctbu.javateach666.pojo.bo.LKCheckCouyearAndSemesterIsOKBO;
import com.ctbu.javateach666.pojo.bo.LKCheckIsTimeOKReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoRspBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.po.LKStucoursePO;
import com.ctbu.javateach666.pojo.po.LKTeacoursePO;

public interface LKMyClassDao {
	public List<LKInitMyClassInfoRspBO> initMyClassInfo(LKInitMyClassInfoReqBO lKInitMyClassInfoReqBO);
	public List<LKTeacoursePO> getChooseClassOnlineList(LKChooseClassOnlineListReqBO lKChooseClassOnlineListReqBO);
	public int getTotalByQuestion(LKChooseClassOnlineListReqBO lKChooseClassOnlineListReqBO);
	public int checkIsChoose(LKChooseClassReqBO lKChooseClassReqBO);
	public LKTeacoursePO getTeacourseById(LKChooseClassReqBO lKChooseClassReqBO);
	public int createNewStucourser(LKStucoursePO lKStucoursePO);
	public List<LKStucoursePO> checkIsTimeOK(LKCheckIsTimeOKReqBO lKCheckIsTimeOKReqBO);
	public List<LKAlreadyChooseRspBO> goAlreadyChoose(LKAlreadyChooseReqBO lKAlreadyChooseReqBO);
	public int cancelClass(LKcancelClassReqBO lKcancelClassReqBO);
	public int checkCouyearAndSemesterIsOK(LKCheckCouyearAndSemesterIsOKBO lKCheckCouyearAndSemesterIsOKBO);
	public int getTotalAlreadyChooseByQuestion(LKAlreadyChooseReqBO lKAlreadyChooseReqBO);
}
