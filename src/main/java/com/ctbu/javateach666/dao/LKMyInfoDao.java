package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRepBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRspBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListReqDaoBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListRspBO;
import com.ctbu.javateach666.pojo.po.LKAccessoryPO;
import com.ctbu.javateach666.pojo.po.LKNoticePO;
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
	public int createNoticeTypeThree(LKNoticePO lKNoticePO);
	public List<LKMyFileListRspBO> getMyFileByPage(LKMyFileListReqDaoBO lKMyFileListReqDaoBO);
	public int getTotalByQuestionFile(LKMyFileListReqDaoBO lKMyFileListReqDaoBO);
	public int deleteMyFile(int id);
	public int uploadMyFile(LKAccessoryPO lKAccessoryPO);
}
