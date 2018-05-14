package com.ctbu.javateach666.dao;

import java.util.List;
import java.util.Map;

import com.ctbu.javateach666.pojo.bo.StuTeaCourseBo_zxy;
import com.ctbu.javateach666.pojo.bo.TeaCourseReqBo;
import com.ctbu.javateach666.pojo.bo.TeacourseBo_zxy;
import com.ctbu.javateach666.pojo.po.Course_zxy;
import com.ctbu.javateach666.pojo.po.Dictionaries_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.TeacoursePo_zxy;

public interface PublishCourseDao {
	//获取所有的课程名称
	public List<Course_zxy> getCourseList();
	//获取所有的教师信息
	public List<TeacherInfo_zxy> getAllTeacher();
	//获取所有的学年
	public List<Dictionaries_zxy> getDictionariesByType(String dtype);
	
	//获取教师课程关系的总数
	public int TotalTeaCourse();
	//获取教师课程的关系的信息
	public List<TeacoursePo_zxy> getAllTeaCourse(TeaCourseReqBo teacourse);
	
	//根据课程id获取课程信息
	public Course_zxy getCourseById(int id);
	//插入分配的教师课程
	public int insertTeaCourse(TeacourseBo_zxy teacourse);
	//判断插入教师课程信息是否存在
	public int Is_HaveTeaCou(TeacourseBo_zxy tc);
	
	//获取学生选课信息
	public int totalStuTeaCourse(StuTeaCourseBo_zxy stcBo);
	public List<StuTeaCourseBo_zxy> getStuTeaCourseByPage(StuTeaCourseBo_zxy stcBo);
	
	//根据教师的姓名获取教师详情表的id
	public int getTeadeail_idByTeaname(String teaname);
	//插入课程上课时间信息
	public int insertTeaCou(TeacourseBo_zxy teacou);
	//判断同一天安排的教师课程信息
	public List<TeacourseBo_zxy> getSameTimeTeaCou(TeacourseBo_zxy teacouBo);
	//当同一天没有安排教师课程信息，直接更新上课时间
	public int UpdateTeaCou(TeacourseBo_zxy teacouBo);
	
	//导出教师选课信息
	public List<StuTeaCourseBo_zxy> getAllTeaStuClassExport(StuTeaCourseBo_zxy teacouBo);
	
	//查询课程表信息
	public List<TeacoursePo_zxy> getTeaCourseKB(Map map);
}
