package com.ctbu.javateach666.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.TeacherProfessionDao_zxy;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.ProfessionInfoListReqBo;
import com.ctbu.javateach666.pojo.bo.ProfessionalRanksBo_zxy;
import com.ctbu.javateach666.pojo.po.Dictionaries_zxy;
import com.ctbu.javateach666.pojo.po.ProfessionalRanks_zxy;
import com.ctbu.javateach666.service.interfac.TeacherProfessionService_zxy;
@Service
public class TeacherProfessionServiceImpl_zxy implements TeacherProfessionService_zxy{

	@Autowired
	TeacherProfessionDao_zxy teacherprofessiondao;
	public List<Dictionaries_zxy> getDictionByDtype(String dtype) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getDictionByDtype(dtype);
	}
	public int getIsHasNoProfData(Map map) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getIsHasNoProfData(map);
	}
	public int insertProfData(ProfessionalRanksBo_zxy profBo) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.insertProfData(profBo);
	}
	public List<ProfessionalRanks_zxy> getAllProfessionalRanks(String teano) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getAllProfessionalRanks(teano);
	}
	public int deleteProfByid(int id) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.deleteProfByid(id);
	}
	public List<ProfessionalRanks_zxy> getAllLikeSelectProfList(ProfessionalRanksBo_zxy profBo) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getAllLikeSelectProfList(profBo);
	}
	
	public List<ProfessionalRanks_zxy> getAllProfessionalRanksByPage(ProfessionInfoListReqBo pro) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getAllProfessionalRanksByPage(pro);
	}
	public PageInfoBo<ProfessionalRanks_zxy> goProfessionlist(ProfessionInfoListReqBo pro){
		PageInfoBo<ProfessionalRanks_zxy> rsp=new PageInfoBo<ProfessionalRanks_zxy>();
		//设置page为下标
		int page = 0;
		page =(pro.getPage()-1)*pro.getRows();
		pro.setPage(page);
		if(pro.getProf_saltv()!=null&&!"".equals(pro.getProf_saltv())){
			String prof_saltv="";
			prof_saltv="%"+pro.getProf_saltv()+"%";
			pro.setProf_saltv(prof_saltv);
		}
		if(pro.getApprove_id()!=null&&!"".equals(pro.getApprove_id()))
		{
			String approve_id="";
			approve_id="%"+pro.getApprove_id()+"%";
			pro.setApprove_id(approve_id);
		}
		//System.out.println(pro.getProf_saltv()+"dddddddddddddd"+pro.getApprove_id());
		int total=teacherprofessiondao.getAllProfessionByquestion(pro);
		//System.out.println(total);
		if(total < 1){
			return rsp;
		}
		List<ProfessionalRanks_zxy> list=teacherprofessiondao.getAllProfessionalRanksByPage(pro);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}
	
	public int getAllProfessionByquestion(ProfessionInfoListReqBo pro) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getAllProfessionByquestion(pro);
	}
	public List<ProfessionalRanks_zxy> getAllProfessionByeach(ProfessionInfoListReqBo pro) {
		// TODO Auto-generated method stub
		if(pro.getProf_saltv()!=null&&!"".equals(pro.getProf_saltv())){
			String prof_saltv="";
			prof_saltv="%"+pro.getProf_saltv()+"%";
			pro.setProf_saltv(prof_saltv);
		}
		if(pro.getApprove_id()!=null&&!"".equals(pro.getApprove_id()))
		{
			String approve_id="";
			approve_id="%"+pro.getApprove_id()+"%";
			pro.setApprove_id(approve_id);
		}
		
		return teacherprofessiondao.getAllProfessionByeach(pro);
	}
	public ProfessionalRanks_zxy getProfessionalRanks_zxyById(int id) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getProfessionalRanks_zxyById(id);
	}
	public int updateProfession(ProfessionalRanksBo_zxy profBo) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.updateProfession(profBo);
	}
	public List<ProfessionalRanks_zxy> getAllProfessionalRanksNoAppro(ProfessionInfoListReqBo pro) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getAllProfessionalRanksNoAppro(pro);
	}
	public int getAllProfessionNoAppro(ProfessionInfoListReqBo pro) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getAllProfessionNoAppro(pro);
	}
	public PageInfoBo<ProfessionalRanks_zxy> goProfessionlistNoAppro(ProfessionInfoListReqBo pro) {
		PageInfoBo<ProfessionalRanks_zxy> rsp=new PageInfoBo<ProfessionalRanks_zxy>();
		//设置page为下标
		int page = 0;
		page =(pro.getPage()-1)*pro.getRows();
		pro.setPage(page);
		
		int total=teacherprofessiondao.getAllProfessionNoAppro(pro);
		if(total < 1){
			return rsp;
		}
		List<ProfessionalRanks_zxy> list=teacherprofessiondao.getAllProfessionalRanksNoAppro(pro);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}
	public int updateApproveNOAppr(ProfessionalRanks_zxy proBo) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.updateApproveNOAppr(proBo);
	}
	public List<ProfessionalRanks_zxy> getAllProfessionalRanksApproEd(ProfessionInfoListReqBo pro) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getAllProfessionalRanksApproEd(pro);
	}
	public int getAllProfessionApproEd(ProfessionInfoListReqBo pro) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getAllProfessionApproEd(pro);
	}
	public PageInfoBo<ProfessionalRanks_zxy> goProfessionlistApproed(ProfessionInfoListReqBo pro) {
		PageInfoBo<ProfessionalRanks_zxy> rsp=new PageInfoBo<ProfessionalRanks_zxy>();
		//设置page为下标
		int page = 0;
		page =(pro.getPage()-1)*pro.getRows();
		pro.setPage(page);
		
		int total=teacherprofessiondao.getAllProfessionApproEd(pro);
		if(total < 1){
			return rsp;
		}
		List<ProfessionalRanks_zxy> list=teacherprofessiondao.getAllProfessionalRanksApproEd(pro);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}
	@Override
	public String getProfessionById(int id) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getProfessionById(id);
	}
	@Override
	public int updateProfessionByTeano(Map map) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.updateProfessionByTeano(map);
	}
	@Override
	public ProfessionalRanks_zxy getProfessionalById(int id) {
		// TODO Auto-generated method stub
		return teacherprofessiondao.getProfessionalById(id);
	}
}
