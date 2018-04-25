package com.ctbu.javateach666.dao;

import java.util.List;


import com.ctbu.javateach666.pojo.bo.LKInitEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateRspBO;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListReqBO;
import com.ctbu.javateach666.pojo.bo.LKSubmitEvaluateDaoBO;
import com.ctbu.javateach666.pojo.bo.LKUpdateEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.getEvaluateListRspBO;

public interface LKMyEvaluateDao {
	public List<LKInitEvaluateRspBO> initEvaluate(LKInitEvaluateReqBO lKInitEvaluateReqBO);
	public int submitEvaluate(LKSubmitEvaluateDaoBO lKSubmitEvaluateDaoBO );
	public List<getEvaluateListRspBO> getEvaluateList(LKMyRepairListReqBO lKMyRepairListReqBO);
	public int getTotalEvaluateList(LKMyRepairListReqBO lKMyRepairListReqBO);
	public LKInitEvaluateRspBO getEvaluateById(LKcancelClassReqBO lKcancelClassReqBO);
	public int updateEvaluate(LKUpdateEvaluateReqBO lKUpdateEvaluateReqBO);
}
