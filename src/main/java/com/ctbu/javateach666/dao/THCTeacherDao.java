package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCProfessionalRanksListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCProfessionalRanksListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCTeaPostListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCTeaPostListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAuthoritiesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCProfessionalRanksPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCStudentInfoPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCTeachersInfoPO;

public interface THCTeacherDao extends BaseDao<THCAccountPO>{
	//教师信息管理
	public List<THCAccountListRspBO> getTeaListbyPage(THCAccountListRepBO tHCAccountListRepBO);
	public int getTeaTotal(THCAccountListRepBO tHCAccountListRepBO);
	public int inserttea(THCTeachersInfoPO tHCTeachersInfoPO);
	public int insertauth(THCAuthoritiesPO tHCAuthoritiesPO);
	public THCTeachersInfoPO selectIdbyTeano(THCTeachersInfoPO tHCTeachersInfoPO);
	public THCAccountPO selectById(THCAccountPO tHCAccountPO);
	public int deleteByLogicTea(THCTeachersInfoPO tHCTeachersInfoPO);
	public int deleteByLogicAuth(THCAuthoritiesPO tHCAuthoritiesPO);
	public int updateTea(THCTeachersInfoPO tHCTeachersInfoPO);
	public THCAccountPO checkUsername(String username);
	public THCTeachersInfoPO checkTeano(String teano);
	
	public List<THCTeaPostListRspBO> getTeaPostListbyPage(THCTeaPostListRepBO tHCTeaPostListRepBO);
	public int getTeaPostListTotal(THCTeaPostListRepBO tHCTeaPostListRepBO);
	public int updateTeaPost(THCTeachersInfoPO tHCTeachersInfoPO);
	
	public List<THCProfessionalRanksListRspBO> getPostRecordListbyPage(THCProfessionalRanksListRepBO tHCProfessionalRanksListRepBO);
	public int getPostRecordListTotal(THCProfessionalRanksListRepBO tHCProfessionalRanksListRepBO);
	public int updatePostRecord(THCProfessionalRanksPO tHCProfessionalRanksPO);
	
	//前台师资力量展示
	public List<THCTeachersInfoPO> getTeacherIntroduce();
	//后台师资力量管理
	public int updateTeaIntroduce(THCTeachersInfoPO tHCTeachersInfoPO);
	public List<THCAccountListRspBO> getTeaListbyPage1(THCAccountListRepBO tHCAccountListRepBO);
}
