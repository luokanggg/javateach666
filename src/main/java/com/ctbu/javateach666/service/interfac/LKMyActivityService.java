package com.ctbu.javateach666.service.interfac;


import java.util.List;

import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKGetChooseActivityListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetChooseActivityListRspBO;
import com.ctbu.javateach666.pojo.bo.LKGetManageActivityReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetManageActivityRspBO;
import com.ctbu.javateach666.pojo.bo.LKMoveActivityMemberReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyPubNameCombBoxBO;
import com.ctbu.javateach666.pojo.bo.LKPubActivityReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;

public interface LKMyActivityService {
	BaseInfoBO pubActivity(LKPubActivityReqBO lKPubActivityReqBO);
	PageInfoBo<LKGetChooseActivityListRspBO> getChooseActivityList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO);
	BaseInfoBO chooseActivity(LKcancelClassReqBO lKcancelClassReqBO);
	PageInfoBo<LKGetChooseActivityListRspBO> getMyChooseActivityList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO);
	BaseInfoBO cancelChooseActivity(LKcancelClassReqBO lKcancelClassReqBO);
	LKPubActivityReqBO updatePubActivity(LKPubActivityReqBO lKPubActivityReqBO);
	LKPubActivityReqBO getUpdatePubActivity(LKPubActivityReqBO lKPubActivityReqBO);
	List<LKMyPubNameCombBoxBO> getMyPubNameCombList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO);
	PageInfoBo<LKGetManageActivityRspBO> getManageActivityList(LKGetManageActivityReqBO lKGetManageActivityReqBO);
	BaseInfoBO moveActivityMember(LKMoveActivityMemberReqBO lKMoveActivityMemberReqBO);
}
