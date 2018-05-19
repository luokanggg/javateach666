package com.ctbu.javateach666.service.impl.thc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.THCStudentDao;
import com.ctbu.javateach666.dao.THCTeacherDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCProfessionalRanksListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCProfessionalRanksListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCTeaPostListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCTeaPostListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAuthoritiesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCProfessionalRanksPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCTeachersInfoPO;
import com.ctbu.javateach666.service.interfac.thc.THCTeacherService;

@Service
public class THCTeacherServiceImpl extends BaseServiceImpl<THCTeacherDao, THCAccountPO> implements THCTeacherService{

	@Autowired
	private THCTeacherDao tHCTeacherDao;
	
	//教师信息管理
	public PageInfoBo<THCAccountListRspBO> getTeaList(THCAccountListRepBO tHCAccountListRepBO) {
		//定义出参
		PageInfoBo<THCAccountListRspBO> rsp = new PageInfoBo<THCAccountListRspBO>();
		int page = 0;
		page = ((tHCAccountListRepBO).getPage() - 1) * tHCAccountListRepBO.getRows();
		tHCAccountListRepBO.setPage(page);
		int total = tHCTeacherDao.getTeaTotal(tHCAccountListRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCAccountListRspBO> list = tHCTeacherDao.getTeaListbyPage(tHCAccountListRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}
	
	

	public PageInfoBo<THCTeaPostListRspBO> getTeaPostList(THCTeaPostListRepBO tHCTeaPostListRepBO) {
		//定义出参
		PageInfoBo<THCTeaPostListRspBO> rsp = new PageInfoBo<THCTeaPostListRspBO>();
		int page = 0;
		page = ((tHCTeaPostListRepBO).getPage() - 1) * tHCTeaPostListRepBO.getRows();
		tHCTeaPostListRepBO.setPage(page);
		int total = tHCTeacherDao.getTeaPostListTotal(tHCTeaPostListRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCTeaPostListRspBO> list = tHCTeacherDao.getTeaPostListbyPage(tHCTeaPostListRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

	public PageInfoBo<THCProfessionalRanksListRspBO> getPostRecordList(
			THCProfessionalRanksListRepBO tHCProfessionalRanksListRepBO) {
		//定义出参
		PageInfoBo<THCProfessionalRanksListRspBO> rsp = new PageInfoBo<THCProfessionalRanksListRspBO>();
		int page = 0;
		page = ((tHCProfessionalRanksListRepBO).getPage() - 1) * tHCProfessionalRanksListRepBO.getRows();
		tHCProfessionalRanksListRepBO.setPage(page);
		int total = tHCTeacherDao.getPostRecordListTotal(tHCProfessionalRanksListRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCProfessionalRanksListRspBO> list = tHCTeacherDao.getPostRecordListbyPage(tHCProfessionalRanksListRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

	public int inserttea(THCTeachersInfoPO tHCTeachersInfoPO) {
		return tHCTeacherDao.inserttea(tHCTeachersInfoPO);
	}

	public int insertauth(THCAuthoritiesPO tHCAuthoritiesPO) {
		return tHCTeacherDao.insertauth(tHCAuthoritiesPO);
	}

	public THCTeachersInfoPO selectIdbyTeano(THCTeachersInfoPO tHCTeachersInfoPO) {
		return tHCTeacherDao.selectIdbyTeano(tHCTeachersInfoPO);
	}

	public THCAccountPO selectById(THCAccountPO tHCAccountPO) {
		return tHCTeacherDao.selectById(tHCAccountPO);
	}

	public int deleteByLogicTea(THCTeachersInfoPO tHCTeachersInfoPO) {
		return tHCTeacherDao.deleteByLogicTea(tHCTeachersInfoPO);
	}

	public int deleteByLogicAuth(THCAuthoritiesPO tHCAuthoritiesPO) {
		return tHCTeacherDao.deleteByLogicAuth(tHCAuthoritiesPO);
	}

	public int updateTea(THCTeachersInfoPO tHCTeachersInfoPO) {
		return tHCTeacherDao.updateTea(tHCTeachersInfoPO);
	}

	public int updateTeaPost(THCTeachersInfoPO tHCTeachersInfoPO) {
		return tHCTeacherDao.updateTeaPost(tHCTeachersInfoPO);
	}

	public int updatePostRecord(THCProfessionalRanksPO tHCProfessionalRanksPO) {
		return tHCTeacherDao.updatePostRecord(tHCProfessionalRanksPO);
	}

	//前台师资力量展示
	public List<THCTeachersInfoPO> getTeacherIntroduce() {
		return tHCTeacherDao.getTeacherIntroduce();
	}

	//后台师资力量管理
	public int updateTeacherIntroduce(CommonsMultipartFile file, HttpServletRequest request) {
		//获取文件名字
		String filename = file.getOriginalFilename();
		//获取文件后缀
		String suffix = filename.substring(filename.lastIndexOf("."));
		//自定义文件名
		String imgname = request.getParameter("teaname") + suffix;
		String professional = request.getParameter("professional");
		String teaname = request.getParameter("teaname");
		String prosonal_statement = request.getParameter("prosonal_statement");
		String id = request.getParameter("id");
		//定义附件表实体
		THCTeachersInfoPO tHCTeachersInfoPO = new THCTeachersInfoPO();
		//定义数据库图片名称
		String imgurl = "static\\img\\teacher\\" + imgname;
		//定义文件上传路径
		String savepath = request.getServletContext().getRealPath("/") + "static\\img\\teacher";
		//String savepath = "C:\\Users\\Administrator\\Desktop\\javateach6666\\src\\main\\webapp\\" + "static\\img\\teacher";
		
		// 判断上传目录是否存在
		File savePath = new File(savepath);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		System.out.println("哈啊啊啊啊啊"+savepath);
		try {
			File myimg = new File(savePath, imgname);
			file.transferTo(myimg);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tHCTeachersInfoPO.setTeaimage(imgurl);
		tHCTeachersInfoPO.setTeaname(teaname);
		tHCTeachersInfoPO.setProfessional(professional);
		tHCTeachersInfoPO.setProsonal_statement(prosonal_statement);
		tHCTeachersInfoPO.setId(Integer.parseInt(id));
		int count = tHCTeacherDao.updateTeaIntroduce(tHCTeachersInfoPO);
		return count;
	}

	@Override
	public THCAccountPO checkUsername(String username) {
		return tHCTeacherDao.checkUsername(username);
	}

	@Override
	public THCTeachersInfoPO checkTeano(String teano) {
		return tHCTeacherDao.checkTeano(teano);
	}



	@Override
	public PageInfoBo<THCAccountListRspBO> getTeaList1(THCAccountListRepBO tHCAccountListRepBO) {
		//定义出参
		PageInfoBo<THCAccountListRspBO> rsp = new PageInfoBo<THCAccountListRspBO>();
		int page = 0;
		page = ((tHCAccountListRepBO).getPage() - 1) * tHCAccountListRepBO.getRows();
		tHCAccountListRepBO.setPage(page);
		int total = tHCTeacherDao.getTeaTotal(tHCAccountListRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCAccountListRspBO> list = tHCTeacherDao.getTeaListbyPage1(tHCAccountListRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

}
