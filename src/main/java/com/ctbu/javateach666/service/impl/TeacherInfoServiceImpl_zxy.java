package com.ctbu.javateach666.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.TeacherInfoDao_zxy;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.TeacherInfoBo_zxy;
import com.ctbu.javateach666.pojo.po.Account_zxy;
import com.ctbu.javateach666.pojo.po.Authorities_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;
@Service
public class TeacherInfoServiceImpl_zxy implements TeacherInfoService_zxy {
	
	@Autowired 
	private TeacherInfoDao_zxy teacherinfo;

	public TeacherInfo_zxy getTeacherInfo(String username) {
		// TODO Auto-generated method stub
		return teacherinfo.getTeacherInfo(username);
	}

	public int updateTeacherInfo(TeacherInfoBo_zxy tea) {
		// TODO Auto-generated method stub
		return teacherinfo.updateTeacherInfo(tea);
	}

	public TeacherInfo_zxy getTeacherInfoByTeano(String teano) {
		// TODO Auto-generated method stub
		return teacherinfo.getTeacherInfoByTeano(teano);
	}

	public Authorities_zxy getAuthoritiesByUsername(String username) {
		// TODO Auto-generated method stub
		return teacherinfo.getAuthoritiesByUsername(username);
	}

	public Account_zxy getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return teacherinfo.getAccountByUsername(username);
	}

	public int updateTeaPasswordById(Map map) {
		// TODO Auto-generated method stub
		return teacherinfo.updateTeaPasswordById(map);
	}

	public List<TeacherInfo_zxy> getAllTeainfo(Map map) {
		// TODO Auto-generated method stub
		return teacherinfo.getAllTeainfo(map);
	}

	public int totalTeainfoBySeach(TeacherInfoBo_zxy teaBo) {
		// TODO Auto-generated method stub
		return teacherinfo.totalTeainfoBySeach(teaBo);
	}

	public List<TeacherInfo_zxy> getAllTeainfoBypage(TeacherInfoBo_zxy teaBo) {
		// TODO Auto-generated method stub
		return teacherinfo.getAllTeainfoBypage(teaBo);
	}

	public PageInfoBo<TeacherInfo_zxy> getTeainfolist(TeacherInfoBo_zxy teaBo) {
		// TODO Auto-generated method stub
		PageInfoBo<TeacherInfo_zxy> rsp=new PageInfoBo<TeacherInfo_zxy>();
		//设置page为下标
		int page = 0;
		page =(teaBo.getPage()-1)*teaBo.getRows();
		teaBo.setPage(page);
		
		if(teaBo.getProfessional()!=null&&!"".equals(teaBo.getProfessional())){
			String professional="";
			professional="%"+teaBo.getProfessional()+"%";
			teaBo.setProfessional(professional);
		}
		if(teaBo.getTeaname()!=null&&!"".equals(teaBo.getTeaname())){
			String teaname="";
			teaname="%"+teaBo.getTeaname()+"%";
			teaBo.setTeaname(teaname);
		}
		System.out.println(teaBo.getTeaname()+"   "+teaBo.getProfessional());
		int total=teacherinfo.totalTeainfoBySeach(teaBo);
		System.out.println(total+"total");
		if(total <1){
			return rsp;
		}
		List<TeacherInfo_zxy> list=teacherinfo.getAllTeainfoBypage(teaBo);
		//System.out.println(list.size()+"list的size");
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	public int updateTeaImageById(Map map) {
		// TODO Auto-generated method stub
		return teacherinfo.updateTeaImageById(map);
	}

}
