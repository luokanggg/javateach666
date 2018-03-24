package com.ctbu.javateach666.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ctbu.javateach666.constant.Constant;
import com.ctbu.javateach666.dao.LKMyClassDao;
import com.ctbu.javateach666.dao.LKMyInfoDao;
import com.ctbu.javateach666.pojo.bo.AlreadyChooseComboBoxBO;
import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseReqBO;
import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseRspBO;
import com.ctbu.javateach666.pojo.bo.LKCheckCouyearAndSemesterIsOKBO;
import com.ctbu.javateach666.pojo.bo.LKCheckIsTimeOKReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListRspBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoRspBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.po.LKStucoursePO;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.pojo.po.LKTeacoursePO;
import com.ctbu.javateach666.service.interfac.LKMyClassService;

/**
 * 我的课程服务实现类
 *
 * @author luokan
 */
@Service
public class LKMyClassServiceImpl implements LKMyClassService{
	
	@Autowired
	private LKMyInfoDao lKMyInfoDao;
	
	@Autowired
	private LKMyClassDao lKMyClassDao;

	public List<LKInitMyClassInfoRspBO> initMyClassInfo() {
		//定义Dao入参
		LKInitMyClassInfoReqBO req = new LKInitMyClassInfoReqBO();
		//取得当前登录学生id;
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LKStudentInfoPO lKStudentInfoPO = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		req.setStuid(lKStudentInfoPO.getId());
		//取得当前时间
		Calendar cal =Calendar.getInstance();
		int couyear = cal.get(Calendar.YEAR);
		//设置学年
		req.setCouyear(couyear);
		int semester = cal.get(Calendar.MONTH) + 1;
		//设置第几学期
		if(semester <= 6){
			req.setSemester(Constant.SEMESTER.LAST);
		}else{
			req.setSemester(Constant.SEMESTER.NEXT);
		}
		
		//定义出参 
		return lKMyClassDao.initMyClassInfo(req);
	}

	public PageInfoBo<LKChooseClassOnlineListRspBO> getChooseClassOnlineList(
			LKChooseClassOnlineListReqBO lKChooseClassOnlineListReqBO) {
		//设置page为下标
		int page = 0;
		page = (lKChooseClassOnlineListReqBO.getPage() - 1) * lKChooseClassOnlineListReqBO.getRows();
		lKChooseClassOnlineListReqBO.setPage(page);
		
		if(lKChooseClassOnlineListReqBO.getTeaname() != null && lKChooseClassOnlineListReqBO.getTeaname() != ""){
			String teaname = "";
			teaname = "%" + lKChooseClassOnlineListReqBO.getTeaname() + "%";
			lKChooseClassOnlineListReqBO.setTeaname(teaname);
		}
		
		if(lKChooseClassOnlineListReqBO.getCouname() != null && lKChooseClassOnlineListReqBO.getCouname() != ""){
			String couname = "";
			couname = "%" + lKChooseClassOnlineListReqBO.getCouname() + "%";
			lKChooseClassOnlineListReqBO.setCouname(couname);
		}
		//取得当前时间
		Calendar cal =Calendar.getInstance();
		int semester = cal.get(Calendar.MONTH) + 1;
		if(semester <= 6){
			lKChooseClassOnlineListReqBO.setSemester(Constant.SEMESTER.NEXT);
			int couyear = cal.get(Calendar.YEAR);
			//设置学年
			lKChooseClassOnlineListReqBO.setCouyear(couyear);
		}else{
			lKChooseClassOnlineListReqBO.setSemester(Constant.SEMESTER.LAST);
			int couyear = cal.get(Calendar.YEAR);
			//设置学年
			lKChooseClassOnlineListReqBO.setCouyear(couyear + 1);
		}
		
		//定义出参 
		PageInfoBo<LKChooseClassOnlineListRspBO> rsp = new PageInfoBo<LKChooseClassOnlineListRspBO>();
		
		int total = lKMyClassDao.getTotalByQuestion(lKChooseClassOnlineListReqBO);
		if(total <  1){
			return rsp;
		}
		
		List<LKTeacoursePO> list = lKMyClassDao.getChooseClassOnlineList(lKChooseClassOnlineListReqBO);
		
		List<LKChooseClassOnlineListRspBO> listrsp = new ArrayList<LKChooseClassOnlineListRspBO>();
		for (LKTeacoursePO lk : list) {
			LKChooseClassOnlineListRspBO bo = new LKChooseClassOnlineListRspBO();
			BeanUtils.copyProperties(lk, bo);
			String coutime = "星期" + lk.getCoutime() + "第" + lk.getCoufhour() + "到" + (lk.getCoufhour() + lk.getCouhour() - 1) + "节";
			bo.setCoutime(coutime);
			listrsp.add(bo);
		}
		
		rsp.setRows(listrsp);
		rsp.setTotal(total);
		
		return rsp;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public BaseInfoBO ChooseClass(LKChooseClassReqBO lKChooseClassReqBO) {
		//定义出参
		BaseInfoBO rsp = new BaseInfoBO();
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		//设置id
		lKChooseClassReqBO.setStuid(logstu.getId());
		//判断该课程是否已选
		int ischoose = lKMyClassDao.checkIsChoose(lKChooseClassReqBO);
		if(ischoose >= 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("选课失败！该课程已选！");
			return rsp;
		}
		
		//取得教师课程表的信息
		LKTeacoursePO lKTeacoursePO = lKMyClassDao.getTeacourseById(lKChooseClassReqBO);
		
		if(lKTeacoursePO.getCounumber() <= lKTeacoursePO.getAlcounumber()){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("选课失败！该课程选课人数已满！");
			return rsp;
		}
		
		//检查当前选课是否与已选课程有冲突或者选课人数已满
		LKCheckIsTimeOKReqBO lKCheckIsTimeOKReqBO = new LKCheckIsTimeOKReqBO();
		lKCheckIsTimeOKReqBO.setCouyear(lKTeacoursePO.getCouyear());
		lKCheckIsTimeOKReqBO.setSemester(lKTeacoursePO.getSemester());
		lKCheckIsTimeOKReqBO.setStuid(lKChooseClassReqBO.getStuid());
		lKCheckIsTimeOKReqBO.setTeacourseid(lKChooseClassReqBO.getTeacourseid());
		
		List<LKStucoursePO> choosedlist = lKMyClassDao.checkIsTimeOK(lKCheckIsTimeOKReqBO);
		
		if(choosedlist != null){
			for (LKStucoursePO ls : choosedlist) {
				int coutime = ls.getCoutime();
				if(lKTeacoursePO.getCoutime() == coutime ){
					int lastcouhour = ls.getCouhour();
					int nextcouhour = ls.getCouhour() + ls.getCoufhour() - 1;
					if(!(lKTeacoursePO.getCouhour() > nextcouhour || (lKTeacoursePO.getCouhour() + lKTeacoursePO.getCoufhour() - 1) < lastcouhour)){
						rsp.setResponseCode(Constant.RSP_FALSE_CODE);
						rsp.setResponseDesc("选课失败！" + ls.getTeaname() + "老师的" + ls.getCouname() + "与该课程的上课时间冲突！");
						return rsp;
					}
				}
			}
		}
		
		//封装入参
		LKStucoursePO req = new LKStucoursePO();
		req.setStuid(logstu.getId());
		req.setTeacourseid(lKChooseClassReqBO.getTeacourseid());
		req.setCoutime(lKTeacoursePO.getCoutime());
		req.setCouhour(lKTeacoursePO.getCouhour());
		req.setCoufhour(lKTeacoursePO.getCoufhour());
		req.setTeaname(lKTeacoursePO.getTeaname());
		req.setCouname(lKTeacoursePO.getCouname());
		req.setCouid(lKTeacoursePO.getCouid());
		
		int isInsert = lKMyClassDao.createNewStucourser(req);
		if(isInsert < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("选课失败！生成选课信息异常！");
			return rsp;
		}
		
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("选课成功！");
		return rsp;
	}
	
	public PageInfoBo<LKAlreadyChooseRspBO> goAlreadyChoose(LKAlreadyChooseReqBO lKAlreadyChooseReqBO){
		//System.out.println("测试goAlreadyChoose");
		//设置page为下标
		int page = 0;
		page = (lKAlreadyChooseReqBO.getPage() - 1) * lKAlreadyChooseReqBO.getRows();
		lKAlreadyChooseReqBO.setPage(page);
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		
		lKAlreadyChooseReqBO.setStuid(logstu.getId());
		
		if(lKAlreadyChooseReqBO.getCouyear() == 0 && lKAlreadyChooseReqBO.getSemester() == 0){
			//取得当前时间
			Calendar cal =Calendar.getInstance();
			int semester = cal.get(Calendar.MONTH) + 1;
			if(semester <= 6){
				lKAlreadyChooseReqBO.setSemester(Constant.SEMESTER.NEXT);
				int couyear = cal.get(Calendar.YEAR);
				//设置学年
				lKAlreadyChooseReqBO.setCouyear(couyear);
			}else{
				lKAlreadyChooseReqBO.setSemester(Constant.SEMESTER.LAST);
				int couyear = cal.get(Calendar.YEAR);
				//设置学年
				lKAlreadyChooseReqBO.setCouyear(couyear + 1);
			}
		}
		
		//定义出参
		PageInfoBo<LKAlreadyChooseRspBO> rsp = new PageInfoBo<LKAlreadyChooseRspBO>();
		
		int total = lKMyClassDao.getTotalAlreadyChooseByQuestion(lKAlreadyChooseReqBO);
		if(total <  1){
			return rsp;
		}
		
		List<LKAlreadyChooseRspBO> list = lKMyClassDao.goAlreadyChoose(lKAlreadyChooseReqBO);
		
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}
	
	public List<AlreadyChooseComboBoxBO> getAlreadyChooseComboBox(){
		//取得当前用户信息；
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//取得登录学生的id和姓名
		LKStudentInfoPO logstu = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		//定义出参
		List<AlreadyChooseComboBoxBO> rsp = new ArrayList<AlreadyChooseComboBoxBO>();
		for (int i = logstu.getClassyear(); i <= logstu.getClassyear() + 4; i++) {
			AlreadyChooseComboBoxBO bo = new AlreadyChooseComboBoxBO();
			bo.setId(i);
			rsp.add(bo);
		}
		return rsp;
	}
	
	public List<LKInitMyClassInfoRspBO> getMyClassInfo(LKInitMyClassInfoReqBO lKInitMyClassInfoReqBO){
		//取得当前登录学生id;
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LKStudentInfoPO lKStudentInfoPO = lKMyInfoDao.initStuInfo(userDetails.getUsername());
		lKInitMyClassInfoReqBO.setStuid(lKStudentInfoPO.getId());
		
		//定义出参 
		return lKMyClassDao.initMyClassInfo(lKInitMyClassInfoReqBO);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public BaseInfoBO cancelClass(LKcancelClassReqBO lKcancelClassReqBO) {
		//定义出参
		BaseInfoBO rsp = new BaseInfoBO();
		
		LKCheckCouyearAndSemesterIsOKBO lKCheckCouyearAndSemesterIsOKBO = new LKCheckCouyearAndSemesterIsOKBO();
		lKCheckCouyearAndSemesterIsOKBO.setId(lKcancelClassReqBO.getId());
		//取得当前时间
		Calendar cal =Calendar.getInstance();
		int semester = cal.get(Calendar.MONTH) + 1;
		if(semester <= 6){
			lKCheckCouyearAndSemesterIsOKBO.setSemester(Constant.SEMESTER.NEXT);
			int couyear = cal.get(Calendar.YEAR);
			//设置学年
			lKCheckCouyearAndSemesterIsOKBO.setCouyear(couyear);
		}else{
			lKCheckCouyearAndSemesterIsOKBO.setSemester(Constant.SEMESTER.LAST);
			int couyear = cal.get(Calendar.YEAR);
			//设置学年
			lKCheckCouyearAndSemesterIsOKBO.setCouyear(couyear + 1);
		}
		
		int isok = lKMyClassDao.checkCouyearAndSemesterIsOK(lKCheckCouyearAndSemesterIsOKBO);
		if(isok < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("取消失败！只能取消下学期选择的课程！");
			return rsp;
		}
		
		int count = lKMyClassDao.cancelClass(lKcancelClassReqBO);
		if(count < 1){
			rsp.setResponseCode(Constant.RSP_FALSE_CODE);
			rsp.setResponseDesc("取消失败！取消课程异常！");
			return rsp;
		}
		rsp.setResponseCode(Constant.RSP_SUCCESS_CODE);
		rsp.setResponseDesc("取消成功！");
		return rsp;
	}

}
