package com.ctbu.javateach666.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.constant.Constant;
import com.ctbu.javateach666.dao.LKMyInfoDao;
import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.DeleteMyFileReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRepBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRspBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListReqDaoBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListRspBO;
import com.ctbu.javateach666.pojo.bo.LKSendMessageToStuReqBO;
import com.ctbu.javateach666.pojo.bo.LKUpdateStuInfoBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.po.LKAccessoryPO;
import com.ctbu.javateach666.pojo.po.LKNoticePO;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.service.interfac.LKMyInfoService;
import com.ctbu.javateach666.util.RedisUtil;

/**
 * 我的信息服务类
 *
 * @author luokan
 */
@Service
public class LKMyInfoServiceImpl implements LKMyInfoService{

	@Autowired
	private LKMyInfoDao lKMyInfoDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	public LKStudentInfoPO initStuInfo(String username) {
		String key = "LKMyInfoService" + "initStuInfo" + username;
		//定义出参
		LKStudentInfoPO rsp = new LKStudentInfoPO();
		if(redisUtil.exist(key)){
			rsp = redisUtil.getData(key);
		}else{
			rsp = lKMyInfoDao.initStuInfo(username);
			redisUtil.saveData(key, rsp);
		}
		return rsp;		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public LKUpdateStuInfoBO updateStuInfo(String stuno, String stuphone) {
		LKStudentInfoPO oldStuInfo = lKMyInfoDao.getStuInfoByStuno(stuno);
		oldStuInfo.setStuphone(stuphone);
		//定义出参
		LKUpdateStuInfoBO rsp = new LKUpdateStuInfoBO();
		int count = lKMyInfoDao.updateStuInfoStuPhone(oldStuInfo);
		if(count > 0){
			
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String key = "LKMyInfoService" + "initStuInfo" + userDetails.getUsername();
			redisUtil.deleteData(key);
			
			BeanUtils.copyProperties(oldStuInfo, rsp);
			rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
			rsp.setResponseDesc("更新成功！");
			return rsp;
		}else{
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("更新失败！");
			return rsp;
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public LKStudentInfoPO updateStuInfoImg(String username, @RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
		//定义文件上传路径
		String savepath = request.getServletContext().getRealPath("/")+"static\\file\\";
		LKStudentInfoPO oldstupo = lKMyInfoDao.initStuInfo(username);
		//定义文件保存的名称
		String filename = oldstupo.getStuno() + file.getOriginalFilename();
		//定义数据库图片名称
		String imagename = "\\javateach666\\static\\file\\" + filename;
		oldstupo.setStuimage(imagename);
		int count = lKMyInfoDao.updateStuInofStuImage(oldstupo);
		if(count > 0){
			File newFile=new File(savepath, filename);
			try {
				file.transferTo(newFile);
				
				String key = "LKMyInfoService" + "initStuInfo" + username;
				redisUtil.deleteData(key);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return oldstupo;
	}

	public PageInfoBo<LKMyClassInfoListRspBO> getMyClassInfoList(LKMyClassInfoListRepBO lKMyClassInfoListRepBO) {
		//定义出参
		PageInfoBo<LKMyClassInfoListRspBO> rsp = new PageInfoBo<LKMyClassInfoListRspBO>();
		//设置page为下标
		int page = 0;
		page = (lKMyClassInfoListRepBO.getPage() - 1) * lKMyClassInfoListRepBO.getRows();
		lKMyClassInfoListRepBO.setPage(page);
		
		if(lKMyClassInfoListRepBO.getStuname() != null && lKMyClassInfoListRepBO.getStuname() != ""){
			String stuname = "";
			stuname = "%" + lKMyClassInfoListRepBO.getStuname() + "%";
			lKMyClassInfoListRepBO.setStuname(stuname);
		}
		
		int total = lKMyInfoDao.getTotalByQuestion(lKMyClassInfoListRepBO);
		if(total < 1){
			return rsp;
		}
		
		List<LKMyClassInfoListRspBO> list = lKMyInfoDao.getMyClassInfoByPage(lKMyClassInfoListRepBO);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	public List<LKMyClassInfoListRspBO> getMyClassInfoBySearch(LKMyClassInfoListRepBO lKMyClassInfoListRepBO) {
		if(lKMyClassInfoListRepBO.getStuname() != null && lKMyClassInfoListRepBO.getStuname() != ""){
			String stuname = "";
			stuname = "%" + lKMyClassInfoListRepBO.getStuname() + "%";
			lKMyClassInfoListRepBO.setStuname(stuname);
		}
		return lKMyInfoDao.getMyClassInfoBySearch(lKMyClassInfoListRepBO);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public BaseInfoBO sendMessageToStu(LKSendMessageToStuReqBO lKSendMessageToStuReqBO) {
		//定义出参
		BaseInfoBO rsp = new BaseInfoBO();
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		//取得被通知学生的id
		LKStudentInfoPO noticestu = lKMyInfoDao.getStuInfoByStuno(lKSendMessageToStuReqBO.getStuno());
		//生成一个通知实体
		LKNoticePO lKNoticePO = new LKNoticePO();
		lKNoticePO.setNotid(logstu.getId());  //通知人id
		lKNoticePO.setTonotid(noticestu.getId()); //被通知人id
		lKNoticePO.setNottype(Constant.NOTICE_TYPE.STU_TO_STU); //通知类型
		lKNoticePO.setNotname(logstu.getStuname()); //通知人姓名
		lKNoticePO.setNottitle("同学消息"); //标题
		lKNoticePO.setNotcontent(lKSendMessageToStuReqBO.getMessage()); //内容
		lKNoticePO.setNoturl("#"); //连接url
		lKNoticePO.setStarttime(new Date()); //通知创建时间
		Calendar cal = Calendar.getInstance();
		//下面的就是把当前日期加一个月
		cal.add(Calendar.MONTH, 1);
		lKNoticePO.setEndtime(cal.getTime()); //通知过期时间
		
		int count = lKMyInfoDao.createNoticeTypeThree(lKNoticePO);
		if(count < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("发送消息异常！");
			return rsp;
		}
		
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("发送消息成功");	
		return rsp;
	}

	public PageInfoBo<LKMyFileListRspBO> getMyFileList(LKMyFileListReqBO lKMyFileListReqBO) {
		//定义出参
		PageInfoBo<LKMyFileListRspBO> rsp = new PageInfoBo<LKMyFileListRspBO>();
		//定义Dao层入参
		LKMyFileListReqDaoBO lKMyFileListReqDaoBO = new LKMyFileListReqDaoBO();
		//设置page为下标
		int page = 0;
		page = (lKMyFileListReqBO.getPage() - 1) * lKMyFileListReqBO.getRows();
		lKMyFileListReqBO.setPage(page);
		if(lKMyFileListReqBO.getAccname() != null && lKMyFileListReqBO.getAccname() != ""){
			String accname = "";
			accname = "%" + lKMyFileListReqBO.getAccname() + "%";
			lKMyFileListReqBO.setAccname(accname);
		}
		if(lKMyFileListReqBO.getBeforeuploadtime() != null && lKMyFileListReqBO.getBeforeuploadtime() != ""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date beforeuploadtime = sdf.parse(lKMyFileListReqBO.getBeforeuploadtime());
				lKMyFileListReqDaoBO.setBeforeuploadtime(beforeuploadtime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(lKMyFileListReqBO.getAfteruploadtime() != null && lKMyFileListReqBO.getAfteruploadtime() != ""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date afteruploadtime = sdf.parse(lKMyFileListReqBO.getAfteruploadtime());
				lKMyFileListReqDaoBO.setAfteruploadtime(afteruploadtime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		BeanUtils.copyProperties(lKMyFileListReqBO, lKMyFileListReqDaoBO);
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		//设置ownid
		lKMyFileListReqDaoBO.setOwnid(logstu.getId());
		
		int total = lKMyInfoDao.getTotalByQuestionFile(lKMyFileListReqDaoBO);
		if(total < 1){
			return rsp;
		}
		
		List<LKMyFileListRspBO> list = lKMyInfoDao.getMyFileByPage(lKMyFileListReqDaoBO);
		
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public BaseInfoBO deleteMyFile(DeleteMyFileReqBO deleteMyFileReqBO, HttpServletRequest request) {
		//定义出参
		BaseInfoBO rsp =new BaseInfoBO();
		int count = lKMyInfoDao.deleteMyFile(deleteMyFileReqBO.getId());
		if(count < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_MESSAGE);
			rsp.setResponseDesc("删除文件异常！");
			return rsp;
		}
		//定义文件上传路径
		String savepath = request.getServletContext().getRealPath("/")+"static\\file\\";
		//定义文件保存的名称
		String filename = deleteMyFileReqBO.getFilename().substring(deleteMyFileReqBO.getFilename().indexOf("file") + 5);
		//删除文件
		File deleteFile=new File(savepath, filename);
		deleteFile.delete();
		
		rsp.setResponseCode(Constant.RSP_SUCCESS_MESSAGE);
		rsp.setResponseDesc("删除文件成功！");
		return rsp;
	}

	public boolean uploadMyFile(String username, CommonsMultipartFile file, HttpServletRequest request) {
		//定义文件上传路径
		String savepath = request.getServletContext().getRealPath("/")+"static\\file\\";
		LKStudentInfoPO oldstupo = lKMyInfoDao.initStuInfo(username);
		//定义文件保存的名称
		String filename = oldstupo.getStuno() + file.getOriginalFilename();
		//定义附件表实体
		LKAccessoryPO lKAccessoryPO = new LKAccessoryPO();
		//定义数据库图片名称
		String accurl = "\\javateach666\\static\\file\\" + filename;
		lKAccessoryPO.setAccurl(accurl);
		lKAccessoryPO.setOwnid(oldstupo.getId());
		lKAccessoryPO.setAccname(filename);
		lKAccessoryPO.setAcctype(Constant.ACCESSORY_TYPE.STU);
		lKAccessoryPO.setUploadtime(new Date());
		int count = lKMyInfoDao.uploadMyFile(lKAccessoryPO);
		if(count > 0){
			File newFile=new File(savepath, filename);
			File oldFile=new File(savepath, filename);
			try {
				oldFile.delete();
				file.transferTo(newFile);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			return false;
		}
		return true;
	}

}
