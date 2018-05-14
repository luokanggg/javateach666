package com.ctbu.javateach666.dao;

import java.util.List;
import java.util.Map;

import com.ctbu.javateach666.pojo.bo.ProfessionInfoListReqBo;
import com.ctbu.javateach666.pojo.bo.ProfessionalRanksBo_zxy;
import com.ctbu.javateach666.pojo.po.Dictionaries_zxy;
import com.ctbu.javateach666.pojo.po.ProfessionalRanks_zxy;

public interface TeacherProfessionDao_zxy {
	//根据字典的类型，获取字典的数据
	public List<Dictionaries_zxy> getDictionByDtype(String dtype);
	
	//根据申请人编号和is_prof=2判断是否有未审批的记录，有记录则不能申请
	public int getIsHasNoProfData(Map map);
	
	//插入申请职称记录
	public int insertProfData(ProfessionalRanksBo_zxy profBo);
	
	//获取所有的升职记录（自己）
	public List<ProfessionalRanks_zxy> getAllProfessionalRanks(String teano);
	
	//获取所有的升职记录（自己）
	public List<ProfessionalRanks_zxy> getAllProfessionalRanksByPage(ProfessionInfoListReqBo pro);
	//计算总数
	public int getAllProfessionByquestion(ProfessionInfoListReqBo pro);
	
	//获取所有的记录满足查询条件的
	public List<ProfessionalRanks_zxy> getAllProfessionByeach(ProfessionInfoListReqBo pro);
	//根据升职记录的id(is_approve=1已审核)的删除升职记录
	public int deleteProfByid(int id);
	
	//根据查询条件模糊查询升职请求记录
	public List<ProfessionalRanks_zxy> getAllLikeSelectProfList(ProfessionalRanksBo_zxy profBo);
	//根据id获取ProfessionalRanks_zxy记录
	public ProfessionalRanks_zxy getProfessionalRanks_zxyById(int id);
	//更新申请记录
	public int updateProfession(ProfessionalRanksBo_zxy profBo);
	
	
	//管理员审批
	//获取所有的升职记录未审批的
	public List<ProfessionalRanks_zxy> getAllProfessionalRanksNoAppro(ProfessionInfoListReqBo pro);
	//计算总数
	public int getAllProfessionNoAppro(ProfessionInfoListReqBo pro);
	//更新审批结果
	public int updateApproveNOAppr(ProfessionalRanks_zxy proBo);
	//获取所有的升职记录已经审批的
	public List<ProfessionalRanks_zxy> getAllProfessionalRanksApproEd(ProfessionInfoListReqBo pro);
	//计算总数
	public int getAllProfessionApproEd(ProfessionInfoListReqBo pro);
	
	//根据Id获取升职请求记录人的编号
	public String getProfessionById(int id);
	
	//申请职称后，审批通过，更新教师信息的详细 信息
	public int updateProfessionByTeano(Map map);
	
	public ProfessionalRanks_zxy getProfessionalById(int id);
}
