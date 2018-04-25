package com.ctbu.javateach666.service.interfac;

import java.util.List;


import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateRspBO;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListReqBO;
import com.ctbu.javateach666.pojo.bo.LKSubmitEvaluateBO;
import com.ctbu.javateach666.pojo.bo.LKUpdateEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.getEvaluateListRspBO;

/**
 * 我的评价服务类接口
 *
 * @author luokan
 */
public interface LKMyEvaluateService {
	public List<LKInitEvaluateRspBO> initEvaluate(LKInitEvaluateReqBO lKInitEvaluateReqBO);
	public BaseInfoBO submitEvaluate(LKSubmitEvaluateBO lKSubmitEvaluateBO);
	public PageInfoBo<getEvaluateListRspBO> getEvaluateList(LKMyRepairListReqBO lKMyRepairListReqBO);
	public LKInitEvaluateRspBO getEvaluateById(LKcancelClassReqBO lKcancelClassReqBO);
	public BaseInfoBO updateEvaluate(LKUpdateEvaluateReqBO lKUpdateEvaluateReqBO);
}
