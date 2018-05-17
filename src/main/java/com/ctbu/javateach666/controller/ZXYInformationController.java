package com.ctbu.javateach666.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.ctbu.javateach666.pojo.bo.InformationBo_zxy;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.service.interfac.InformationService_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;

@Controller
public class ZXYInformationController {
	@Autowired
	private TeacherInfoService_zxy teainfoservice;
	
	@Autowired
	InformationService_zxy informationservice;
	//文件名
	private String makeFileName(String filename){    
		   //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名  
		  return UUID.randomUUID().toString() + "_" + filename;  
	 }  
	@RequestMapping("/uploadfile")
	public void UploadFile(@RequestParam(value="file",required =false) CommonsMultipartFile file,String title,String type,String statement,HttpServletRequest request, HttpServletResponse response){
		//System.out.println("zhixing"+title+"  "+type+"  "+statement+"   "+file);
		response.setCharacterEncoding("UTF-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		HttpSession session=request.getSession();
		session.setAttribute("TeaInfo", tea);
		
		String filename=file.getOriginalFilename();//文件名字
		filename=makeFileName(filename);
		//定义文件的保存路径
		String savepath="D:/file/";
		//String savepath = request.getServletContext().getRealPath("/")+"static\\file\\";
		//System.out.println("保存路径："+savepath);
		File file1=new File(savepath,filename); 
	
		try {

			file.transferTo(file1);
	        InformationBo_zxy information=new InformationBo_zxy();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
			String time=format.format(new Date());  
			String teano=tea.getTeano();
			String publish_person=teano;
			information.setTitle(title);
			information.setStatement(statement);
			information.setPublish_date(time);
			information.setPublish_person(teano);
			information.setContent(savepath+filename);
			information.setInfotype(type);
			informationservice.insertInformation(information);
			response.getWriter().print("<script> alert('文件上传成功！') </script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				response.getWriter().print("<script> alert('文件上传失败！') </script>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}
	
	@RequestMapping("uploadinformation")
	public String goUploadFile(){
		return "teacherzxy/teainformation/information";
	}
	
	@RequestMapping("informationlist")
	public String goInformationList(){
		return "teacherzxy/teainformation/informationlist";
	}
	@ResponseBody
	@RequestMapping("getinformationinfolist")
	public PageInfoBo<InformationBo_zxy> getInformation(InformationBo_zxy reqBo){
		//System.out.println(reqBo.getInfotype()+"=========="+reqBo.getPublish_person_name());
		PageInfoBo<InformationBo_zxy> page=new PageInfoBo<InformationBo_zxy>();
		page=informationservice.getInformationList(reqBo);
		return page;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteInformation",method=RequestMethod.GET)
	public Map deleteInformation(HttpServletRequest request,String content,String publish_person,String id) throws UnsupportedEncodingException{
		Map map=new HashMap<String,String>();
		//String strPath=request.getServletContext().getRealPath("/")+"static\\file\\";
		String strPath="D:/file/";
		//String strPath="D:\\tomcat7\\webapps\\javateach666\\";
		File file1=new File(strPath);
		String filename=new String(content.getBytes("ISO-8859-1"),"UTF-8");
		File tempFile =new File( filename.trim());     
		String filename1=tempFile.getName();
		
		boolean tag=false;
		int flag=0;
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		String teano1=tea.getTeano();
		if(!teano1.equals(publish_person)){
			map.put("mess", "此资料不是您上传，没有权限删除！");
			return map;
		}else{
			 if(file1.exists()){   
				 
				 File fa[] = file1.listFiles();
				
			        for (int i = 0; i < fa.length; i++) {
			            File fs = fa[i];
			            
			        
			            if (fs.isDirectory()) {
			          
			                continue;
			               
			            } else if(fs.isFile()&&fs.getName().equals(filename1)){
			             
			                tag=fs.delete();
			                int id1=Integer.parseInt(id);
							flag=informationservice.deleteInformationById(id1);
							break;
			            }
			        }
			        if(tag==true&&flag==1){
	                	map.put("mess", "删除成功！");
						return map;
	                }else{
	                	map.put("mess", "删除失败");
	    				return map;
	                } 	
		        }else{  
		        	
		        	map.put("mess", "删除失败");
					return map;
		        }
		}	
	}

	@ResponseBody
	@RequestMapping(value="downfile",method=RequestMethod.GET)
	public void  downFile(HttpServletRequest request,HttpServletResponse response,String id,String content) throws IOException, ServletException{
		
		content=new String(content.getBytes("ISO-8859-1"),"UTF-8");
		String filename =content;
		 String filename2=filename;
         String prefix=filename2.substring(filename2.lastIndexOf(".")+1);
        /*  filename2=filename;
         int len=filename2.indexOf("_");
         String savename=filename2.substring(len+1, filename2.indexOf("."));
         System.out.println("savename="+savename);*/
         //根据文件名获取 MIME 类型
         String contentType = new MimetypesFileTypeMap().getContentType(filename);
         String contentDisposition = "attachment;filename=simple"+"."+prefix;
         FileInputStream input = new FileInputStream(filename);
         response.setHeader("Content-Type",contentType);
         response.setHeader("Content-Disposition",contentDisposition);
         // 获取绑定了客户端的流
         ServletOutputStream output = response.getOutputStream();
         // 把输入流中的数据写入到输出流中
         IOUtils.copy(input,output);
         input.close();        
	}
}
