package com.ctbu.javateach666.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.InformationDao_zxy;
import com.ctbu.javateach666.pojo.bo.InformationBo_zxy;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.service.interfac.InformationService_zxy;
@Service
public class InformationServiceImpl_zxy implements InformationService_zxy{
	@Autowired
	InformationDao_zxy informationdao;
	public int insertInformation(InformationBo_zxy information) {
		// TODO Auto-generated method stub
		return informationdao.insertInformation(information);
	}
	public int getInformationListBySeach(InformationBo_zxy reqBo) {
		// TODO Auto-generated method stub
		return informationdao.getInformationListBySeach(reqBo);
	}
	public List<InformationBo_zxy> getInformationListByPage(InformationBo_zxy reqBo) {
		// TODO Auto-generated method stub
		return informationdao.getInformationListByPage(reqBo);
	}
	public PageInfoBo<InformationBo_zxy> getInformationList(InformationBo_zxy reqBo) {
		// TODO Auto-generated method stub
//		System.out.println(reqBo.getInfotype()+"==========");
		//定义出参
		PageInfoBo<InformationBo_zxy> rsp=new PageInfoBo<InformationBo_zxy>();
		//设置page为下标
		int page = 0;
		page=(reqBo.getPage()-1)*reqBo.getRows();
		reqBo.setPage(page);
		if(reqBo.getInfotype()!=null&&!"".equals(reqBo.getInfotype())){
			String type="";
			type="%"+reqBo.getInfotype()+"%";
			reqBo.setInfotype(type);
		}
		if(reqBo.getPublish_person_name()!=null&&!"".equals(reqBo.getPublish_person_name())){
			String publish_person_name="";
			publish_person_name="%"+reqBo.getPublish_person_name()+"%";
			reqBo.setPublish_person_name(publish_person_name);
			//System.out.println(publish_person_name);
		}
		int total=informationdao.getInformationListBySeach(reqBo);
		System.out.println(total);
		if(total < 1){
			return rsp;
		}
		List<InformationBo_zxy> list=informationdao.getInformationListByPage(reqBo);
		rsp.setTotal(total);
		rsp.setRows(list);
		return rsp;
	}
	public int deleteInformationById(int id) {
		// TODO Auto-generated method stub
		return informationdao.deleteInformationById(id);
	}

}
