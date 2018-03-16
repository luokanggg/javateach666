package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRepBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRspBO;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
/**
 * 我的信息Dao类
 *
 * @author luokan
 */
public interface LKMyInfoDao {
	public LKStudentInfoPO initStuInfo(String username);
	public LKStudentInfoPO getStuInfoByStuno(String stuno);
	public int updateStuInfoStuPhone(LKStudentInfoPO newstu);
	public int updateStuInofStuImage(LKStudentInfoPO newstu);
	public List<LKMyClassInfoListRspBO> getMyClassInfoByPage(LKMyClassInfoListRepBO lKMyClassInfoListRepBO);
	public int getTotalByQuestion(LKMyClassInfoListRepBO lKMyClassInfoListRepBO);
	public List<LKMyClassInfoListRspBO> getMyClassInfoBySearch(LKMyClassInfoListRepBO lKMyClassInfoListRepBO);
}
