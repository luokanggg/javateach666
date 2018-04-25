package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.CheckChooseActivityReqBO;
import com.ctbu.javateach666.pojo.bo.ChooseActivityReqBO;
import com.ctbu.javateach666.pojo.bo.ChooseActivityRspBO;
import com.ctbu.javateach666.pojo.bo.LKGetChooseActivityListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetChooseActivityListRspBO;
import com.ctbu.javateach666.pojo.bo.LKPubActivityReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.UpdatePubNumberReqBO;

public interface LKMyActivityDao {
	int pubActivity(LKPubActivityReqBO lKPubActivityReqBO);
	List<LKGetChooseActivityListRspBO> getChooseActivityList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO);
	int getTotalChooseActivity(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO);
	ChooseActivityRspBO getChooseActivity(int id);
	int chooseActivity(ChooseActivityReqBO chooseActivityReqBO);
	int checkChooseActivity(CheckChooseActivityReqBO checkChooseActivityReqBO);
	int updateAlPubNumber(UpdatePubNumberReqBO updatePubNumberReqBO);
	int getMyTotalChooseActivity(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO);
	List<LKGetChooseActivityListRspBO> getMyChooseActivityList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO);
	int cancelChooseActivity(LKcancelClassReqBO lKcancelClassReqBO);
	int updatePubActivity(LKPubActivityReqBO lKPubActivityReqBO);
	LKPubActivityReqBO getUpdatePubActivity(LKPubActivityReqBO lKPubActivityReqBO);
	int getCancelPubId(LKcancelClassReqBO lKcancelClassReqBO);
	int updateCancelAlPubNumber(LKcancelClassReqBO lKcancelClassReqBO);
	int CheckAlPubNumber(LKcancelClassReqBO lKcancelClassReqBO);
}
