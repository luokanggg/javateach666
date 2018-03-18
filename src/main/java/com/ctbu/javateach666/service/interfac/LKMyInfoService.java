package com.ctbu.javateach666.service.interfac;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.DeleteMyFileReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRepBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRspBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListRspBO;
import com.ctbu.javateach666.pojo.bo.LKSendMessageToStuReqBO;
import com.ctbu.javateach666.pojo.bo.LKUpdateStuInfoBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;


/**
 * 我的信息服务类接口
 *
 * @author luokan
 */
public interface LKMyInfoService {
	public LKStudentInfoPO initStuInfo(String username);
	public LKUpdateStuInfoBO updateStuInfo(String stuno, String stuphone);
	public LKStudentInfoPO updateStuInfoImg(String username, @RequestParam("file") CommonsMultipartFile file, HttpServletRequest request);
	public PageInfoBo<LKMyClassInfoListRspBO> getMyClassInfoList(LKMyClassInfoListRepBO lKMyClassInfoListRepBO);
	public List<LKMyClassInfoListRspBO> getMyClassInfoBySearch(LKMyClassInfoListRepBO lKMyClassInfoListRepBO);
	public BaseInfoBO sendMessageToStu(LKSendMessageToStuReqBO lKSendMessageToStuReqBO);
	public PageInfoBo<LKMyFileListRspBO> getMyFileList(LKMyFileListReqBO lKMyFileListReqBO);
	public BaseInfoBO deleteMyFile(DeleteMyFileReqBO deleteMyFileReqBO, HttpServletRequest request);
	public boolean uploadMyFile(String username, @RequestParam("file") CommonsMultipartFile file, HttpServletRequest request);
}
