package com.ctbu.javateach666.dao;

import java.util.List;


import com.ctbu.javateach666.pojo.bo.LKInitEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateRspBO;
import com.ctbu.javateach666.pojo.bo.LKSubmitEvaluateDaoBO;

public interface LKMyEvaluateDao {
	public List<LKInitEvaluateRspBO> initEvaluate(LKInitEvaluateReqBO lKInitEvaluateReqBO);
	public int submitEvaluate(LKSubmitEvaluateDaoBO lKSubmitEvaluateDaoBO );
}
