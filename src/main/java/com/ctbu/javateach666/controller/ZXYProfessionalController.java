package com.ctbu.javateach666.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.ProfessionInfoListReqBo;
import com.ctbu.javateach666.pojo.bo.ProfessionalRanksBo_zxy;
import com.ctbu.javateach666.pojo.po.Dictionaries_zxy;
import com.ctbu.javateach666.pojo.po.ProfessionalRanks_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;
import com.ctbu.javateach666.service.interfac.TeacherProfessionService_zxy;

@Controller
public class ZXYProfessionalController {
	@Autowired
	TeacherProfessionService_zxy teacherprofessionservice;
	@Autowired
	TeacherInfoService_zxy teainfoservice;
	
	@RequestMapping("/profession")
	public String goProfession(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		HttpSession session=request.getSession();
		session.setAttribute("TeaInfo", tea);
		List<Dictionaries_zxy> diction=new ArrayList<Dictionaries_zxy>();
		diction=teacherprofessionservice.getDictionByDtype("1");
		session.setAttribute("dic", diction);
		return "teacherzxy/teaprofessionzxy/profession";
	}
	
	@ResponseBody
	@RequestMapping(value ="/approverank",method=RequestMethod.GET)
	public Map goGapproverank(HttpServletRequest request,ProfessionalRanksBo_zxy proBo) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		HttpSession session=request.getSession();
		session.setAttribute("TeaInfo", tea);
		Map info=new HashMap<String, String>();
		String prof_saltv=new String(proBo.getProf_saltv().getBytes("ISO-8859-1"),"UTF-8");
		String prof_reason=new String(proBo.getProf_reason().getBytes("ISO-8859-1"),"UTF-8");
		String prof_person_id2=new String(proBo.getProf_person_id().getBytes("ISO-8859-1"),"UTF-8");
		String prof_person_name=new String(proBo.getProf_person_name().getBytes("ISO-8859-1"),"UTF-8");
		Map map=new HashMap<String,String>();
		Map data=new HashMap();
		data.put("prof_person_id", prof_person_id2);
		data.put("prof_person_name",prof_person_name);
		data.put("prof_saltv", prof_saltv);
		data.put("prof_reason",prof_reason );
		data.put("time",proBo.getProf_time() );
		String prof_person_id=proBo.getProf_person_id();
		map.put("prof_person_id", prof_person_id);
		map.put("is_approve", 0);
		//System.out.println("sssssssss");
		//System.out.println(proBo.getProf_time());
		int flag=-1;
		flag=teacherprofessionservice.getIsHasNoProfData(map);
		if(flag!=0){
		info.put("mess","有未审批记录，不能申请");
				return info;
		}else{
			try {
					
				proBo.setIs_prof(2);
				proBo.setIs_approve(0);
				proBo.setProf_saltv(prof_saltv);
				proBo.setProf_person_id(prof_person_id2);
				proBo.setProf_person_name(prof_person_name);
				proBo.setProf_reason(prof_reason);
				proBo.setNow_prof_saltv(tea.getProfessional());
				teacherprofessionservice.insertProfData(proBo);
				info.put("mess","申请成功");
				info.put("prodata", data);
				return info;
			} catch (Exception e) {
				// TODO: handle exception
				info.put("mess", "申请失败！");
				return info;
			}
				
		}
	}

	@RequestMapping("/professionlist")
	public String goProfessionlistPage(HttpServletRequest request){
		HttpSession session=request.getSession();
		List<Dictionaries_zxy> diction=new ArrayList<Dictionaries_zxy>();
		diction=teacherprofessionservice.getDictionByDtype("1");
		session.setAttribute("dic", diction);
		
		return "teacherzxy/teaprofessionzxy/professionlist";
	}
	@ResponseBody
	@RequestMapping("/getAllprofessionlist")
	public PageInfoBo<ProfessionalRanks_zxy> goProfessionlist(HttpServletRequest request,ProfessionInfoListReqBo pro) throws UnsupportedEncodingException{
		//System.out.println(pro.getProf_saltv()+" ========="+pro.getApprove_id());
		request.setCharacterEncoding("utf-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		String teaid=tea.getTeano();
		pro.setProf_person_id(teaid);
		PageInfoBo<ProfessionalRanks_zxy> page=new PageInfoBo<ProfessionalRanks_zxy>();
		page=teacherprofessionservice.goProfessionlist(pro);
		return page;
	}
	@ResponseBody
	@RequestMapping(value="/profession_delete")
	public Map goProfession_delete(HttpServletRequest request,int is_approve,int id){
		//System.out.println("id:  "+id+"   "+is_approve);
		Map map=new HashMap();
		if(is_approve!=1){
			map.put("mess", "不能删除未审核的记录");
			return map;
		}else{
			try {
				int flag=0;
				flag=teacherprofessionservice.deleteProfByid(id);
				if(flag==1){
					map.put("mess", "删除成功");
					System.out.println("删除成功");
				}else{
					System.out.println("fff删除失败");
				}
				return map;
			} catch (Exception e) {
				// TODO: handle exception
				map.put("mess", "删除失败");
				System.out.println("删除失败");
				return map;
			}
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/profession_delete_Pl")
	public Map goProfession_delete_PL(HttpServletRequest request, String  _list){
		Map map=new HashMap<String,String>();
		String list[]=_list.split(",");
		int id=0;
		int tag=0;
		for (int i = 0; i < list.length; i++) {
			//System.out.println(list[i]);
			id=Integer.parseInt(list[i]);
			ProfessionalRanks_zxy pro=teacherprofessionservice.getProfessionalRanks_zxyById(id);
			int is_approve=pro.getIs_approve();
			if(is_approve!=1){
				map.put("mess", "不能删除未审核的记录");
				return map;
			}
			tag+=1;
		}
		if(tag==list.length){
			for (int i = 0; i < list.length; i++) {
				id=Integer.parseInt(list[i]);
				teacherprofessionservice.deleteProfByid(id);
			}
			map.put("mess", "删除成功");
			return map;
		}else
		{
			map.put("mess", "删除失败");
			return map;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/exportprofinfo",method=RequestMethod.GET)
	public Map goLikeSelectProfList(HttpServletResponse response,HttpServletRequest request,String prof_saltv,String approve_id){
		Map map=new HashMap<String,String>();
		String prof_saltv1="";
		String approve_id1="";
		ProfessionInfoListReqBo pro=new ProfessionInfoListReqBo();
		try {
			prof_saltv1=new String(prof_saltv.getBytes("ISO-8859-1"),"UTF-8");
			approve_id1=new String(approve_id.getBytes("ISO-8859-1"),"UTF-8");
			pro.setProf_saltv(prof_saltv);
			pro.setApprove_id(approve_id1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		pro.setProf_person_id(tea.getTeano());
		List<ProfessionalRanks_zxy> list=teacherprofessionservice.getAllProfessionByeach(pro);
		//创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("申请记录表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("我的历史申请记录");
        HSSFCellStyle cellStyle = wb.createCellStyle(); 
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font.setFontName("微软雅黑");  
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,12));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(2);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("ID");
        row2.createCell(1).setCellValue("申请人ID");
        row2.createCell(2).setCellValue("申请人姓名");
        row2.createCell(3).setCellValue("现任职称");
        row2.createCell(4).setCellValue("申请的职称");
        row2.createCell(5).setCellValue("申请时间");
        row2.createCell(6).setCellValue("申请的原因");
        row2.createCell(7).setCellValue("审批人ID");
        row2.createCell(8).setCellValue("审批人姓名");
        row2.createCell(9).setCellValue("审批的时间");
        row2.createCell(10).setCellValue("是否成功");
        row2.createCell(11).setCellValue("是否审批");
        row2.createCell(12).setCellValue("反馈的原因");
        //循环输出申请记录信息
        for (int i = 0; i< list.size(); i++) {
        	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        	HSSFRow rows = sheet.createRow(i+3);
        	rows.createCell(0).setCellValue(list.get(i).getId());
        	rows.createCell(1).setCellValue(list.get(i).getProf_person_id());
        	rows.createCell(2).setCellValue(list.get(i).getProf_person_name());
        	rows.createCell(4).setCellValue(list.get(i).getProf_saltv());
        	rows.createCell(3).setCellValue(list.get(i).getNow_prof_saltv());
        	rows.createCell(5).setCellValue(format.format(list.get(i).getProf_time()));
        	//System.out.println("4444444444444444444444"+list.get(i).getProf_time());
        	rows.createCell(6).setCellValue(list.get(i).getProf_reason());
        	rows.createCell(7).setCellValue(list.get(i).getApprove_id());
        	rows.createCell(8).setCellValue(list.get(i).getApprove_name());
        	rows.createCell(9).setCellValue(format.format(list.get(i).getApprove_time()));
        	//System.out.println("hhhhhhhhhhhh"+list.get(i).getApprove_time());
        	rows.createCell(10).setCellValue(list.get(i).getIs_prof());
        	rows.createCell(11).setCellValue(list.get(i).getIs_approve());
        	rows.createCell(12).setCellValue(list.get(i).getProf_reason());
		}
      //输出Excel文件
        OutputStream output = null;
		try {
			output = response.getOutputStream();
			map.put("mess", "导出成功");
		} catch (IOException e) {
			//e.printStackTrace();
			map.put("mess", "导出失败");
		}
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=myapprofessinfo.xls");
        response.setContentType("application/msexcel");
        try {
			wb.write(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value ="/profession_modify")
	public Map goProfession_modify(HttpServletRequest request,ProfessionalRanksBo_zxy proBo){
		Map map=new HashMap<String,String>();
		
		
		//System.out.println(proBo.getId()+"  "+proBo.getProf_reason()+proBo.getProf_saltv());
		
		int id=proBo.getId();
		ProfessionalRanks_zxy pro=teacherprofessionservice.getProfessionalRanks_zxyById(id);
		int is_approve=pro.getIs_approve();
		if(is_approve==1){
			map.put("mess", "不能修改已审核的记录");
			return map;
		}else{
			try {
				teacherprofessionservice.updateProfession(proBo);
				map.put("mess", "修改成功");
			} catch (Exception e) {
				// TODO: handle exception
				map.put("mess", "修改失败");
			}
			
		}
		return map;
	}
	@RequestMapping("ProfessionFK")
	public String goProfessionFK(HttpServletRequest request){
		/*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		HttpSession session=request.getSession();
		session.setAttribute("TeaInfo", tea);*/
		//查找审批人信息
		return "teacherzxy/teaprofessionzxy/professionlistfk";
	}
	//管理员获取所有的申请记录
	@ResponseBody
	@RequestMapping("getAllprofessionlistfk")
	public PageInfoBo<ProfessionalRanks_zxy> gogetAllprofessionlistfk(HttpServletRequest request,ProfessionInfoListReqBo pro){
		//System.out.println(pro.getPage()+"  "+"ssssssssssssssss");
		PageInfoBo<ProfessionalRanks_zxy> page=new PageInfoBo<ProfessionalRanks_zxy>();
		page=teacherprofessionservice.goProfessionlistNoAppro(pro);
		return page;
	}
	@ResponseBody
	@RequestMapping("profession_disagree")
	public Map DisagreeAprove(HttpServletRequest request,ProfessionalRanks_zxy proBo) throws ParseException{
		Map map=new HashMap();
		String approve_id="thc_001";//审批人ID 
		String approve_name="thc";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date time=new Date();
		String time1=sdf.format(time);
		Date approve_time=sdf.parse(time1);
		int is_prof=0;
		String prof_fk_reason=proBo.getProf_fk_reason();
		int id=proBo.getId();
		//System.out.println(id+"==========="+prof_fk_reason);
		
		//更新记录
		proBo.setApprove_id(approve_id);
		proBo.setApprove_name(approve_name);
		proBo.setApprove_time(approve_time);
		proBo.setIs_approve(1);
		proBo.setIs_prof(is_prof);
		try {
			teacherprofessionservice.updateApproveNOAppr(proBo);
			map.put("mess", "审核成功");
		} catch (Exception e) {
			map.put("mess", "审核失败");
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("profession_agree")
	public Map AgreeAprove(HttpServletRequest request,ProfessionalRanks_zxy proBo) throws ParseException{
		Map map=new HashMap();
		String approve_id="thc_001";//审批人ID 
		String approve_name="thc";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date time=new Date();
		String time1=sdf.format(time);
		Date approve_time=sdf.parse(time1);
		int is_prof=1;
		String prof_fk_reason=proBo.getProf_fk_reason();
		int id=proBo.getId();
		//System.out.println(id+"==========="+prof_fk_reason);
		//根据id获取该条记录申请人编号
		String teano=teacherprofessionservice.getProfessionById(id);
		String prof_saltv=proBo.getProf_saltv();//申请的职称
		Map map1=new HashMap();
		map1.put("teano",teano);
		map1.put("prof_saltv",prof_saltv );
		//更新记录
		proBo.setApprove_id(approve_id);
		proBo.setApprove_name(approve_name);
		proBo.setApprove_time(approve_time);
		proBo.setIs_approve(1);
		proBo.setIs_prof(is_prof);
		try {
			teacherprofessionservice.updateApproveNOAppr(proBo);
			//应该修改teainfo表的职称
			teacherprofessionservice.updateProfessionByTeano(map1);
			
			map.put("mess", "审核成功");
		} catch (Exception e) {
			map.put("mess", "审核失败");
		}
		return map;
	}

	
	@ResponseBody
	@RequestMapping("profession_agree_PL")
	public Map AgreeAprovePL(HttpServletRequest request,String prof_fk_reason,String  _list) throws ParseException{
		Map map=new HashMap();
		ProfessionalRanks_zxy proBo=new ProfessionalRanks_zxy();
		String approve_id="thc_001";//审批人ID 
		String approve_name="thc";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date time=new Date();
		String time1=sdf.format(time);
		Date approve_time=sdf.parse(time1);
		int is_prof=1;//批量通过
		
		proBo.setApprove_id(approve_id);
		proBo.setApprove_name(approve_name);
		proBo.setApprove_time(approve_time);
		proBo.setIs_approve(1);
		proBo.setIs_prof(is_prof);
		proBo.setProf_fk_reason(prof_fk_reason);
		String list[]=_list.split(",");
		int id=0;
		int tag=0;
		for (int i = 0; i < list.length; i++) {
			id=Integer.parseInt(list[i]);
			//根据id获取申请的职称和申请人编号
			ProfessionalRanks_zxy pros=teacherprofessionservice.getProfessionalById(id);
			String teano=pros.getProf_person_id();
			String prof_saltv=pros.getProf_saltv();
			System.out.println(teano+"  "+prof_saltv);
			Map map1=new HashMap();
			map1.put("teano",teano);
			map1.put("prof_saltv",prof_saltv);
			proBo.setId(id);
			teacherprofessionservice.updateApproveNOAppr(proBo);
			teacherprofessionservice.updateProfessionByTeano(map1);
			tag++;
		}
		if(tag==list.length){
			map.put("mess", "审核成功");
		}else{
			map.put("mess", "审核失败");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("profession_disagree_PL")
	public Map DisagreeAprovePL(HttpServletRequest request,String prof_fk_reason,String  _list) throws ParseException{
		Map map=new HashMap();
		ProfessionalRanks_zxy proBo=new ProfessionalRanks_zxy();
		String approve_id="thc_001";//审批人ID 
		String approve_name="thc";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date time=new Date();
		String time1=sdf.format(time);
		Date approve_time=sdf.parse(time1);
		int is_prof=0;//批量驳回
		
		proBo.setApprove_id(approve_id);
		proBo.setApprove_name(approve_name);
		proBo.setApprove_time(approve_time);
		proBo.setIs_approve(1);
		proBo.setIs_prof(is_prof);
		proBo.setProf_fk_reason(prof_fk_reason);
		String list[]=_list.split(",");
		int id=0;
		int tag=0;
		for (int i = 0; i < list.length; i++) {
			id=Integer.parseInt(list[i]);
			proBo.setId(id);
			teacherprofessionservice.updateApproveNOAppr(proBo);
			tag++;
		}
		if(tag==list.length){
			map.put("mess", "审核成功");
		}else{
			map.put("mess", "审核失败");
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="NoApprprofession_delete_Pl")
	public Map goNOApproProfession_delete_PL(HttpServletRequest request, String  _list){
		Map map=new HashMap<String,String>();
		String list[]=_list.split(",");
		int id=0;
		for (int i = 0; i < list.length; i++) {
			id=Integer.parseInt(list[i]);
			try {
				teacherprofessionservice.deleteProfByid(id);
				map.put("mess", "删除成功");
			} catch (Exception e) {
				// TODO: handle exception
				map.put("mess", "删除失败");
			}
			
		}	
			return map;
	}
	
	@RequestMapping(value="professionlistAppred")
	public String goprofessionlistAppred(){
		return "teacherzxy/teaprofessionzxy/professionlistAppred";
	}
	
	@ResponseBody
	@RequestMapping("getAllprofessionlistAppred")
	public PageInfoBo<ProfessionalRanks_zxy> goAllprofessionlistAppred(HttpServletRequest request,ProfessionInfoListReqBo pro){
		PageInfoBo<ProfessionalRanks_zxy> page=new PageInfoBo<ProfessionalRanks_zxy>();
		page=teacherprofessionservice.goProfessionlistApproed(pro);
		return page;
	}
}
