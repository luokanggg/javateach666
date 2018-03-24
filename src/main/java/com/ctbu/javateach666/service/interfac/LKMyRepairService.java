package com.ctbu.javateach666.service.interfac;

import com.ctbu.javateach666.pojo.bo.LKMyRepairListReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListRspBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;

public interface LKMyRepairService {
	public PageInfoBo<LKMyRepairListRspBO>  getMyRepairList(LKMyRepairListReqBO lKMyRepairListReqBO);
}
