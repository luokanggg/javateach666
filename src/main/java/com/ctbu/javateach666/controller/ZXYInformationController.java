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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
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
		//定义文件的保存路径
		String savepath="D:/file/";
		//String savepath = request.getServletContext().getRealPath("/")+"static\\file\\";
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
		String strPath="D:/file/";
		File file1=new File(strPath);
		String filename=new String(content.getBytes("ISO-8859-1"),"UTF-8");
		String filename1=filename.substring(8);
		//System.out.println(filename1);
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
				// System.out.println("存在");
				 File fa[] = file1.listFiles();
				 //System.out.println(fa.length);
			        for (int i = 0; i < fa.length; i++) {
			            File fs = fa[i];
			            
			          //  System.out.println(fs.getName());
			            if (fs.isDirectory()) {
			            //	System.out.println("是目录");
			                continue;
			               
			            } else if(fs.isFile()&&fs.getName().equals(filename1)){
			               // System.out.println(fs.getName()+"要删除的文件名称");
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
		        	System.out.println("不存在");
		        	map.put("mess", "删除失败");
					return map;
		        }
		}	
	}

	@ResponseBody
	@RequestMapping(value="downfile",method=RequestMethod.GET)
	public Map  downFile(HttpServletRequest request,HttpServletResponse response,String id,String content) throws IOException{
		content=new String(content.getBytes("ISO-8859-1"),"UTF-8");
		String urlString =content;
		String downloadFilePath=this.getClass().getResource("").getPath();//从我们的上传文件夹中去取
		String fileName ="";
		fileName= urlString.substring(urlString.lastIndexOf('/') + 1);//文件名称
		File file = new File(downloadFilePath);
		Map map=new HashMap<String,String>();
		FileInputStream fis = new FileInputStream(file);
	    BufferedInputStream bis = null;  
	    BufferedOutputStream bos = null;
	    try {
        	//设置浏览器显示的内容类型为windows下载框
            response.setContentType("application/x-msdownload");  
            response.setCharacterEncoding("utf-8");  
            //设置内容作为附件下载，保存为filename  
            response.setHeader("Content-disposition", "attachment; filename="  
                    + fileName);  
            // 通知客户文件的MIME类型：  
            bis = new java.io.BufferedInputStream(fis);
            bos = new java.io.BufferedOutputStream(response.getOutputStream());  
            byte[] buff = new byte[2048];  
            int bytesRead;  
            int i = 0;  
  
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
                i++;  
            }  
            bos.flush(); 
            map.put("mess", "下载成功！");
            return map;
        } catch (Exception e) {  
            e.printStackTrace();  
            map.put("mess", "下载失败！");
			 return map;
        } finally {  
            if (bis != null) {  
                try {  
                    bis.close();  
                }catch(IOException e){
                // TODO Auto-generated catch block  
                 e.printStackTrace();  
                }
                bis = null;  
            }  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
                bos = null;  
            }  
        }
		
		/*	Map map=new HashMap<String,String>();
		OutputStream out=null;
		FileInputStream in=null;
		try {
			
			File file=new File(urlString);
			if(!file.exists()){
				map.put("mess", "该文件不存在，下载失败！");
				return map;
			}
			

		//	String suffix = urlString.substring(urlString.lastIndexOf(".") + 1);
		
			response.reset();
			 //第一步：设置响应类型
			//response.setContentType("application/force-download");//应用程序强制下载
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-disposition", "attachment; filename=myfile");
	        response.setHeader("Content-Length", String.valueOf(file.length()));

	        out= response.getOutputStream();  
	        in = new FileInputStream(file);  
	        byte[] buff = new byte[in.available()];
	        int len = 0;
	      
	        while((len = in.read(buff))!=-1){
	          out.write(buff, 0, len);
	        }
	       
	        out.flush();
		    map.put("mess", "下载成功！");
			return map;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map.put("mess", "下载失败！");
			 return map;
		} finally {
            if (in != null)
                try{
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            if (out != null)
                try{
                	out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

        }*/
	}
}
