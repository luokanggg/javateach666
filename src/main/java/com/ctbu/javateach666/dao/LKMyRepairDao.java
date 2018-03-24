package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.LKMyRepairListReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListRspBO;

public interface LKMyRepairDao {
	public List<LKMyRepairListRspBO>  getMyRepairList(LKMyRepairListReqBO lKMyRepairListReqBO);
	public int getTotalRepairList(LKMyRepairListReqBO lKMyRepairListReqBO);
}
