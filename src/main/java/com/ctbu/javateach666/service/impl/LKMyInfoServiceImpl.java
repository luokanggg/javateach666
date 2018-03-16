package com.ctbu.javateach666.service.impl;

import java.io.File;
import java.io.IOException;
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
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRepBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRspBO;
import com.ctbu.javateach666.pojo.bo.LKUpdateStuInfoBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
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
		if(total <= 1){
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

}
