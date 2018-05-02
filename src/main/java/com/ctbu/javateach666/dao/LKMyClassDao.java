package com.ctbu.javateach666.dao;

import java.util.List;
import java.util.Map;

import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseReqBO;
import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseRspBO;
import com.ctbu.javateach666.pojo.bo.LKCheckCouyearAndSemesterIsOKBO;
import com.ctbu.javateach666.pojo.bo.LKCheckIsTimeOKReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetClassStudentsListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetClassStudentsListRspBO;
import com.ctbu.javateach666.pojo.bo.LKGetSemesterTeacherListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetSemesterTeacherListRspBO;
import com.ctbu.javateach666.pojo.bo.LKGetSubmitClassWorkDataSBSDaoBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoRspBO;
import com.ctbu.javateach666.pojo.bo.LKSubmotsWorkBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.UpdateAlCouNumberReqBO;
import com.ctbu.javateach666.pojo.po.LKAccessoryPO;
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
	public Map<String,String> getSubmitClassWorkDataTAC(int id);
	public List<LKSubmotsWorkBO> getSubmitClassWorkDataSBS(LKGetSubmitClassWorkDataSBSDaoBO lKGetSubmitClassWorkDataSBSDaoBO);
	public int submitWork(LKAccessoryPO lKAccessoryPO);
	public int updateAlCouNumber(UpdateAlCouNumberReqBO updateAlCouNumberReqBO);
	public int getCancelClassId(LKcancelClassReqBO lKcancelClassReqBO);
	public int UpCancelAlCounumber(LKcancelClassReqBO lKcancelClassReqBO);
	public List<LKGetSemesterTeacherListRspBO> getSemesterTeacherList(LKGetSemesterTeacherListReqBO lKGetSemesterTeacherListReqBO);
	public int getTotalSemesterTeacher(LKGetSemesterTeacherListReqBO lKGetSemesterTeacherListReqBO);
	public List<LKGetClassStudentsListRspBO> getClassStudentsList(LKGetClassStudentsListReqBO lKGetClassStudentsListReqBO);
	public int getTotalClassStudents(LKGetClassStudentsListReqBO lKGetClassStudentsListReqBO);
}
