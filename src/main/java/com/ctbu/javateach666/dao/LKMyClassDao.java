package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.LKCheckIsTimeOKReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListRspBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoRspBO;
import com.ctbu.javateach666.pojo.po.LKStucoursePO;
import com.ctbu.javateach666.pojo.po.LKTeacoursePO;

public interface LKMyClassDao {
	public List<LKInitMyClassInfoRspBO> initMyClassInfo(LKInitMyClassInfoReqBO lKInitMyClassInfoReqBO);
	public List<LKChooseClassOnlineListRspBO> getChooseClassOnlineList(LKChooseClassOnlineListReqBO lKChooseClassOnlineListReqBO);
	public int getTotalByQuestion(LKChooseClassOnlineListReqBO lKChooseClassOnlineListReqBO);
	public int checkIsChoose(LKChooseClassReqBO lKChooseClassReqBO);
	public LKTeacoursePO getTeacourseById(LKChooseClassReqBO lKChooseClassReqBO);
	public int createNewStucourser(LKStucoursePO lKStucoursePO);
	public List<LKStucoursePO> checkIsTimeOK(LKCheckIsTimeOKReqBO lKCheckIsTimeOKReqBO);
}
