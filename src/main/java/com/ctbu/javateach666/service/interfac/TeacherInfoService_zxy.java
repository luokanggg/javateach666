package com.ctbu.javateach666.service.interfac;

import java.util.List;
import java.util.Map;

import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.TeacherInfoBo_zxy;
import com.ctbu.javateach666.pojo.po.Account_zxy;
import com.ctbu.javateach666.pojo.po.Authorities_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;

public interface TeacherInfoService_zxy {
	//获取教师的详细信息
	public  TeacherInfo_zxy getTeacherInfo(String username);
	
	//获取所有教师的信息
	public List<TeacherInfo_zxy> getAllTeainfo(Map map);
	
	//修改教师信息
	public int updateTeacherInfo(TeacherInfoBo_zxy tea);
	
	//根据账号查密码Account
	public Account_zxy getAccountByUsername(String username);
	
	//更新账号的密码更新id
	public int updateTeaPasswordById(Map map);
	
	//获取教师的详细,根据用户的教师账号
	public TeacherInfo_zxy getTeacherInfoByTeano(String teano);
	
	//根据登录的账号，查角色信息
	public Authorities_zxy getAuthoritiesByUsername(String username);
	
	//根据查询条件获取教师的总数
	public int totalTeainfoBySeach(TeacherInfoBo_zxy teaBo);
	//根据查询条件分页获取教师信息
	public List<TeacherInfo_zxy> getAllTeainfoBypage(TeacherInfoBo_zxy teaBo);
	
	public PageInfoBo<TeacherInfo_zxy> getTeainfolist(TeacherInfoBo_zxy teaBo);
	
	//根据登录用户的id更新用户的头像
	public int updateTeaImageById(Map map);
}
