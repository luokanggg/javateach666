package com.ctbu.javateach666.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.PublishCourseDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.StuTeaCourseBo_zxy;
import com.ctbu.javateach666.pojo.bo.TeaCourseReqBo;
import com.ctbu.javateach666.pojo.bo.TeacourseBo_zxy;
import com.ctbu.javateach666.pojo.po.Course_zxy;
import com.ctbu.javateach666.pojo.po.Dictionaries_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.TeacoursePo_zxy;
import com.ctbu.javateach666.service.interfac.PublishCourseServise_zxy;
@Service
public class PublishCourseServiseImpl_zxy implements PublishCourseServise_zxy{

	@Autowired
	PublishCourseDao publishcourse;
	public List<Course_zxy> getCourseList() {
		// TODO Auto-generated method stub
		return publishcourse.getCourseList();
	}
	public List<TeacherInfo_zxy> getAllTeacher() {
		// TODO Auto-generated method stub
		return publishcourse.getAllTeacher();
	}
	public List<Dictionaries_zxy> getDictionariesByType(String dtype) {
		// TODO Auto-generated method stub
		return publishcourse.getDictionariesByType(dtype);
	}
	public int TotalTeaCourse() {
		// TODO Auto-generated method stub
		return publishcourse.TotalTeaCourse();
	}
	public List<TeacoursePo_zxy> getAllTeaCourse(TeaCourseReqBo teacourse) {
		// TODO Auto-generated method stub
		return publishcourse.getAllTeaCourse(teacourse);
	}
	public PageInfoBo<TeacoursePo_zxy> getTeaCourseByPage(TeaCourseReqBo teacourse) {
		// TODO Auto-generated method stub
		PageInfoBo<TeacoursePo_zxy> rsp=new PageInfoBo<TeacoursePo_zxy>();
		//设置page为下标
		int page = 0;
		page =(teacourse.getPage()-1)*teacourse.getRows();
		teacourse.setPage(page);
		int total=publishcourse.TotalTeaCourse();
		//System.out.println("total-=="+total);
		if(total < 1){
			return rsp;
		}
		List<TeacoursePo_zxy> list=publishcourse.getAllTeaCourse(teacourse);
		
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}
	public Course_zxy getCourseById(int id) {
		// TODO Auto-generated method stub
		return publishcourse.getCourseById(id);
	}
	public int insertTeaCourse(TeacourseBo_zxy teacourse) {
		// TODO Auto-generated method stub
		return publishcourse.insertTeaCourse(teacourse);
	}

	public int getTeadeail_idByTeaname(String teaname) {
		// TODO Auto-generated method stub
		return publishcourse.getTeadeail_idByTeaname(teaname);
	}
	public int insertTeaCou(TeacourseBo_zxy teacou) {
		// TODO Auto-generated method stub
		return publishcourse.insertTeaCou(teacou);
	}

	public int Is_HaveTeaCou(TeacourseBo_zxy tc) {
		// TODO Auto-generated method stub
		return publishcourse.Is_HaveTeaCou(tc);
	}
	public List<TeacourseBo_zxy> getSameTimeTeaCou(TeacourseBo_zxy teacouBo) {
		// TODO Auto-generated method stub
		return publishcourse.getSameTimeTeaCou(teacouBo);
	}
	public int UpdateTeaCou(TeacourseBo_zxy teacouBo) {
		// TODO Auto-generated method stub
		return publishcourse.UpdateTeaCou(teacouBo);
	}
	public int totalStuTeaCourse(StuTeaCourseBo_zxy stcBo) {
		// TODO Auto-generated method stub
		return publishcourse.totalStuTeaCourse(stcBo);
	}
	public List<StuTeaCourseBo_zxy> getStuTeaCourseByPage(StuTeaCourseBo_zxy stcBo) {
		// TODO Auto-generated method stub
		return publishcourse.getStuTeaCourseByPage(stcBo);
	}
	public PageInfoBo<StuTeaCourseBo_zxy> getStuTeaCourseBySeach(StuTeaCourseBo_zxy stcBo) {
		// TODO Auto-generated method stub
		PageInfoBo<StuTeaCourseBo_zxy> rsp=new PageInfoBo<StuTeaCourseBo_zxy>();
		int page=0;
		page=(stcBo.getPage()-1)*stcBo.getRows();
		stcBo.setPage(page);
		int total=publishcourse.totalStuTeaCourse(stcBo);
		if(total < 1){
			return rsp;
		}
		List<StuTeaCourseBo_zxy> list=publishcourse.getStuTeaCourseByPage(stcBo);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}
	public List<StuTeaCourseBo_zxy> getAllTeaStuClassExport(StuTeaCourseBo_zxy teacouBo) {
		// TODO Auto-generated method stub
		return publishcourse.getAllTeaStuClassExport(teacouBo);
	}
	public List<TeacoursePo_zxy> getTeaCourseKB(Map map) {
		// TODO Auto-generated method stub
		return publishcourse.getTeaCourseKB(map);
	}
	
}
