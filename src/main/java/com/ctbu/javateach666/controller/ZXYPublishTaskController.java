package com.ctbu.javateach666.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.pojo.bo.AccessaryBo_zxy;
import com.ctbu.javateach666.pojo.bo.MyTaskReqBo;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.TaskBo_zxy;
import com.ctbu.javateach666.pojo.bo.TaskUploadInfoBo_zxy;
import com.ctbu.javateach666.pojo.bo.TeataskBo_zxy;
import com.ctbu.javateach666.pojo.po.AccessaryPo_zxy;
import com.ctbu.javateach666.pojo.po.AdminInfoPO_zxy;
import com.ctbu.javateach666.pojo.po.Dictionaries_zxy;
import com.ctbu.javateach666.pojo.po.MyTeaTaskPo_zxy;
import com.ctbu.javateach666.pojo.po.TaskPo_zxy;
import com.ctbu.javateach666.pojo.po.TaskUploadInfoPo_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.TeataskPo_zxy;
import com.ctbu.javateach666.service.interfac.TaskService_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;

@Controller
public class ZXYPublishTaskController {
	@Autowired
	TaskService_zxy taskService;
	@Autowired
	TeacherInfoService_zxy teainfoservice;
	@RequestMapping("publishtask")
	public String gopublishtask(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		
		String dtype="课题";
		List<Dictionaries_zxy> tasktype=taskService.getTaskInfo(dtype);
		session.setAttribute("tasktype",tasktype);
		//查询数据字典的课题类型
		return "teacherzxy/taskzxy/publishtask";
	}
	@RequestMapping("publishtaskinfo")
	public void gopublishtask(String tasktype,String taskname,String taskcontent,HttpServletResponse response) throws IOException, ParseException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		//查询当前登录的管理员的身份信息
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AdminInfoPO_zxy admin=taskService.getAdminInfo(userDetails.getUsername());
		int taskpublishid=admin.getId();
		String taskpublisher=admin.getAdminname();
		TaskBo_zxy taskBo=new TaskBo_zxy();
		taskBo.setTaskcontent(taskcontent);
		taskBo.setTaskname(taskname);
		taskBo.setTasktype(tasktype);
		TaskPo_zxy taskPo2=taskService.getTaskByTaskInfo(taskBo);
		if(taskPo2!=null){
			response.getWriter().print("<script> alert('课题信息发布失败,该课题信息已存在') </script>");
		}else{
			taskBo.setTaskpublisher(taskpublisher);
			taskBo.setTaskpublishid(taskpublishid);
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String time=sdf.format(date);
			Date tasktime;
			tasktime = sdf.parse(time);
			taskBo.setTaskpubtime(tasktime);
			int tag=0;
			tag=taskService.insertTaskInfo(taskBo);
			if(tag>0){
				response.getWriter().print("<script> alert('课题信息发布成功!') </script>");
			}else{
				response.getWriter().print("<script> alert('课题信息发布失败!') </script>");
			}
		}
	}
	@ResponseBody
	@RequestMapping("getAllpublishTask")
	public PageInfoBo<TaskBo_zxy> getAllPublishTask(TaskBo_zxy taskBo){
		PageInfoBo<TaskBo_zxy> page=new PageInfoBo<TaskBo_zxy>();
		page=taskService.getAllPublishTasks(taskBo);
		return page;
	}
	
	@ResponseBody
	@RequestMapping("task_delete")
	public Map goDeleteTask(int id){
		Map map=new HashMap<>();
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AdminInfoPO_zxy admin=taskService.getAdminInfo(userDetails.getUsername());
		int publisherid=admin.getId();
		//根据id查询该记录
		TaskPo_zxy task=taskService.getTaskById(id);
		int pubid=task.getTaskpublishid();
		
		if(pubid==publisherid){
			//判断该课题是否被选择且审批通过了
			TeataskBo_zxy teataskbo=new TeataskBo_zxy();
			teataskbo.setIscheck(1);
			teataskbo.setCheckstatus(1);
			teataskbo.setTaskid(id);
			List<TeataskPo_zxy> teataskPo=new ArrayList<>();
			teataskPo=taskService.isChoicedAndAgree(teataskbo);
			if(teataskPo.size()>0){
				map.put("mess", "1111");
				//System.out.println("有审批通过的");
			}else{
				int tag=0;
				tag=taskService.deleteTaskById(id);//删除task
				//删除teatask记录
				int taskid=id;
				int tag2=taskService.deleteTeaTaskByTaskId(taskid);
				map.put("mess", "0000");
			}

		}else{
			map.put("mess", "5555");
		}
		return map;
	}
	
	@RequestMapping("searchtask")
	public String gosearchtask(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="课题";
		List<Dictionaries_zxy> tasktype=taskService.getTaskInfo(dtype);
		session.setAttribute("tasktype",tasktype);
		return "teacherzxy/taskzxy/chiocetask";
	}
	@ResponseBody
	@RequestMapping("getAllpublishTaskchoice")
	public PageInfoBo<TaskPo_zxy> getAllpublishTaskchoice(TaskBo_zxy task){
		PageInfoBo<TaskPo_zxy> page=new PageInfoBo<TaskPo_zxy>();
		page=taskService.getAllpublishTaskTochoice(task);
		return page;
	}
	
	@ResponseBody
	@RequestMapping("task_choice")
	public Map gotask_choice(int id){
		//System.out.println("选择程序的ID"+id);
		Map map=new HashMap();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		String teaname=tea.getTeaname();
		//先判断用户选择了课题且审批没有
		int ischeck=1;
		int ischeck1=0;
		int taskid=id;
		TeataskBo_zxy teataskBo=new TeataskBo_zxy();
		TeataskBo_zxy teataskBo1=new TeataskBo_zxy();
		TeataskBo_zxy teataskBo2=new TeataskBo_zxy();
		teataskBo.setTeaid(teaid);
		teataskBo.setIscheck(ischeck);
		teataskBo.setCheckstatus(1);
		
		teataskBo2.setTeaid(teaid);
		teataskBo2.setIscheck(ischeck);
		teataskBo2.setCheckstatus(2);
		
		teataskBo1.setTeaid(teaid);
		teataskBo1.setIscheck(ischeck1);
		
		TeataskPo_zxy teatask=taskService.ChoiceAndIsAgree(teataskBo);
		TeataskPo_zxy teatask4=taskService.ChoiceAndIsAgree(teataskBo2);//选择驳回
		TeataskPo_zxy teatask2=taskService.IsCanChoiceById(teaid);
		TeataskPo_zxy teatask3=taskService.IsCanChoice(teataskBo1);
		TeataskBo_zxy teataskReqBo=new TeataskBo_zxy();
		
		teataskReqBo.setTaskid(taskid);//课题的id
		teataskReqBo.setTeaid(teaid);
		teataskReqBo.setTeaname(teaname);
		teataskReqBo.setCheckstatus(0);
		teataskReqBo.setIscheck(0);
		
		
		
		if(teatask2==null){
			//判断该用户选择了没有，没有则直接插入数据，有则更新数据
			try {
				taskService.inserTeaTask(teataskReqBo);
				//System.out.println("未选课题直接插入：");
				map.put("mess", "0000");//选择成功
			} catch (Exception e) {
				// TODO: handle exception
				map.put("mess", "1111");//数据库插入错误
			}
			
		}else if(teatask!=null){
			//已经选择且审批通过
			map.put("mess", "8888");
			System.out.println("已经选择且审批通过不能在审批：");
		}else if(teatask3!=null){
			//
			//System.out.println("已选择未审批直接修改：");
			try {
				int teataskidd=teatask3.getId();
				teataskReqBo.setId(teataskidd);
				taskService.updateTeaTask(teataskReqBo);
				map.put("mess", "0000");//选择成功
			} catch (Exception e) {
				// TODO: handle exception
				map.put("mess", "1111");//数据库插入错误
			}
		}else if(teatask4!=null){
			int teataskidd=teatask4.getId();
			teataskReqBo.setId(teataskidd);
			System.out.println("已经选择且审批驳回直接修改该记录：");
			taskService.updateTeaTaskDis(teataskReqBo);
			map.put("mess", "0000");//选择成功
		}
		return map;
	}
	@RequestMapping("searchmytask")
	public String gosearchmytask(){
		return "teacherzxy/taskzxy/mytaskchoiced";
	}
	
	@ResponseBody
	@RequestMapping("getMyTask")
	public PageInfoBo<MyTeaTaskPo_zxy> goMyTask(MyTaskReqBo myTask){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		//System.out.println("我的任务的ID："+teaid);
		myTask.setTeaid(teaid);
		PageInfoBo<MyTeaTaskPo_zxy> page=new PageInfoBo<MyTeaTaskPo_zxy>();
		page=taskService.goMytaskBuPage(myTask);
		return page;
	}
	//管理员查看选择情况
	@RequestMapping("looktaskinfo")
	public String golooktaskinfo(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="课题";
		List<Dictionaries_zxy> tasktype=taskService.getTaskInfo(dtype);
		session.setAttribute("tasktype",tasktype);
		return "teacherzxy/taskzxy/looktaskchoiceinfo";
	}
	
	@ResponseBody
	@RequestMapping("getTaskChoiceinfo")
	public PageInfoBo<MyTeaTaskPo_zxy> getTaskChoiceinfo(MyTaskReqBo myTask){
		
		PageInfoBo<MyTeaTaskPo_zxy> page=new PageInfoBo<MyTeaTaskPo_zxy>();
		page=taskService.getTaskChoiceByPage(myTask);
		
		return page;
	}
	
	@ResponseBody
	@RequestMapping("task_disagree")
	public Map gotask_disagree(int id,int ischeck,String taskpublisher) throws ParseException{
		Map map=new HashMap();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AdminInfoPO_zxy admin=taskService.getAdminInfo(userDetails.getUsername());
		String pubname=admin.getAdminname();
		int checkid=admin.getId();
		if(ischeck==1){
			map.put("mess","8888");
			return map;
		}
		if(!pubname.equals(taskpublisher)){
			map.put("mess","1111");
			return map;
		}
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(date);
		Date checktime;
		checktime = sdf.parse(time);
		TeataskBo_zxy teataskBo=new TeataskBo_zxy();
		teataskBo.setId(id);
		teataskBo.setIscheck(1);
		teataskBo.setChecker(pubname);
		teataskBo.setCheckid(checkid);
		teataskBo.setChecktime(checktime);
		teataskBo.setCheckstatus(2);//驳回
		try {
			//更新记录
			taskService.updateTaskChoice(teataskBo);
			map.put("mess","0000");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("task_agree")
	public Map gotask_agree(int id,int ischeck,String taskpublisher) throws ParseException{
		Map map=new HashMap();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AdminInfoPO_zxy admin=taskService.getAdminInfo(userDetails.getUsername());
		String pubname=admin.getAdminname();
		int checkid=admin.getId();
		if(ischeck==1){
			map.put("mess","8888");
			return map;
		}
		//System.out.println("发布人："+taskpublisher);
		if(!pubname.equals(taskpublisher)){
			
			map.put("mess","1111");
			return map;
		}
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(date);
		Date checktime;
		checktime = sdf.parse(time);
		TeataskBo_zxy teataskBo=new TeataskBo_zxy();
		teataskBo.setId(id);
		teataskBo.setIscheck(1);
		teataskBo.setChecker(pubname);
		teataskBo.setCheckid(checkid);
		teataskBo.setChecktime(checktime);
		teataskBo.setCheckstatus(1);
		try {
			//更新记录
			taskService.updateTaskChoice(teataskBo);
			map.put("mess","0000");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("mess","9999");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("task_modify")
	public Map gotask_modify(int id,HttpServletRequest request){
		HttpSession session=request.getSession();
	    Map map=new HashMap();
		TaskPo_zxy task=taskService.getTaskById(id);
		int pubid=task.getTaskpublishid();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AdminInfoPO_zxy admin=taskService.getAdminInfo(userDetails.getUsername());
		int publisherid=admin.getId();
		if(pubid!=publisherid){
			map.put("mess", "5555");
			return map;
		}else{
			if(task!=null){
				 map.put("mess", "0000");	
				 session.setAttribute("task", task);
				 return map;
			}else{
					map.put("mess", "8888");
					return map;
				}
		}
		
	
	}
	
	@RequestMapping("modifytaskinfo")
	public String gomodifytaskinfo(){
		return "teacherzxy/taskzxy/modifytask";
	}
	
	@RequestMapping("modifytaskmessage")
	public void gomodifytaskmessage(HttpServletResponse response,int id,String taskname,String taskcontent,String tasktype){
		//System.out.println(tasktype+" ffff  "+taskname+"   fff "+taskcontent);
		TaskBo_zxy  task=new TaskBo_zxy();
		task.setTaskname(taskname);
		task.setTasktype(tasktype);
		task.setTaskcontent(taskcontent);
		task.setId(id);
		int tag=0;
		response.setCharacterEncoding("UTF-8");
		//更新课题
		try {
			tag=taskService.updateTask(task);
			
			if(tag>0){
				response.getWriter().print("<script> alert('课题修改成功！') </script>");
			}else{
				response.getWriter().print("<script> alert('课题修改失败！') </script>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			try {
				response.getWriter().print("<script> alert('课题修改失败！') </script>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping("uploadmytaskfile")
	public String gouploadmytaskfile(HttpSession session)
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		TeataskBo_zxy teataskBo=new TeataskBo_zxy();
		int ischeck=1;
		int checkstatus=1;
		teataskBo.setTeaid(teaid);
		teataskBo.setIscheck(ischeck);
		teataskBo.setCheckstatus(checkstatus);
		TeataskPo_zxy teatask=taskService.isAdmined(teataskBo);
		if(teatask==null){
			session.setAttribute("mess", "8888");
		}else{
			int taskid=teatask.getTaskid();
			TaskPo_zxy taskpo=taskService.getTaskById(taskid);
			session.setAttribute("task", taskpo);
		}
		return "teacherzxy/taskzxy/uploadmytask";
	}
	//文件名
	private String makeFileName(String filename){    
			  //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名  
			 return UUID.randomUUID().toString() + "_" + filename;  
	} 
	@RequestMapping("/uploadtask")
	public void updateStuInfoImg(@RequestParam("file") CommonsMultipartFile file, @RequestParam("id") String id,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		response.setCharacterEncoding("utf-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		int acctype2=100;
		
		String filename=file.getOriginalFilename();
		filename=makeFileName(filename);
		String savepath ="D:/planfile";
		File file1=new File(savepath,filename);
		
		AccessaryBo_zxy accBo=new AccessaryBo_zxy();
		accBo.setAcctype(100);
		accBo.setAccurl(savepath+"/"+filename);
		accBo.setAccname("我的课题文件");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(date);
		Date uploadtime;
		uploadtime = sdf.parse(time);
		accBo.setUploadtime(uploadtime);
		accBo.setOwnid(teaid);
		
		//先判断有没有附件信息
		AccessaryBo_zxy accBo2=new AccessaryBo_zxy();
		accBo2.setOwnid(teaid);
		accBo2.setAcctype(acctype2);
		AccessaryPo_zxy accPo=taskService.getMyTaskFile(accBo2);
		
		
		int tag=0;
		if(accPo==null){
			//说明没有直接插入信息
			file.transferTo(file1);
			tag=taskService.insertAcceaasryTask(accBo);
			if(tag==1){
				response.getWriter().print("<script> alert('文件上传成功') </script>");
			}else{
				response.getWriter().print("<script> alert('文件上传失败') </script>");
			}
		}else{
			//说明已经有了直接更新
			file.transferTo(file1);
			accBo.setId(accPo.getId());
			//更新
			tag=taskService.updateAccessaryTask(accBo);
			if(tag==1){
				response.getWriter().print("<script> alert('文件上传成功') </script>");
			}else{
				response.getWriter().print("<script> alert('文件上传失败') </script>");
			}
		}
	}
	@ResponseBody
	@RequestMapping("searchmytaskfile")
	public Map SearchMyTaskfile(HttpServletRequest request){
		Map map=new HashMap<>();
		HttpSession session=request.getSession();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		int acctype=100;
		//查询我是否有课题文件
		AccessaryBo_zxy accBo=new AccessaryBo_zxy();
		accBo.setOwnid(teaid);
		accBo.setAcctype(acctype);
		AccessaryPo_zxy accPo=taskService.getMyTaskFile(accBo);
		if(accPo==null){
			map.put("mess","0000");
		}else{
			//根据teaid获取它的课题信息
			TeataskPo_zxy teatask=taskService.IsCanChoiceById(teaid);
			int taskid=-1;
			if(teatask!=null){
				taskid=teatask.getTaskid();
				//根据taskid查询课题详细信息
				TaskPo_zxy taskpo=taskService.getTaskById(taskid);
				if(taskpo!=null){
					session.setAttribute("task", taskpo);
					session.setAttribute("acc", accPo);
				}
			}
		}
		return map;
	}
	@RequestMapping("myTaskFile")
	public String gomyTaskFile(HttpServletRequest request){
		HttpSession session=request.getSession();
		TaskPo_zxy task=(TaskPo_zxy) session.getAttribute("task");
		session.setAttribute("task", task);
		AccessaryPo_zxy accPo=(AccessaryPo_zxy) session.getAttribute("acc");
		String accurl=accPo.getAccurl();
		 File file = new File(accurl);  
		 BufferedReader rd=null;
		 String str = "";
		 try {
			rd = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
			String s = rd.readLine();
			 while (null != s) {  
	             str += s;  
	             s = rd.readLine();  
	         }
		 } catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		 String username=tea.getTeaname();
		 session.setAttribute("username",username);
		 session.setAttribute("detailtask",str);
		 //读取文件的内容显示在页面上
		return "teacherzxy/taskzxy/mytaskfileacc"; 
	}
	@RequestMapping("looktaskuploadinfo")
	public String Golooktaskuploadinfo(){
		return "teacherzxy/taskzxy/looktaskupload";
	}
	//查看教师提交文档的详情
	@ResponseBody
	@RequestMapping("getTaskuploadinfo")
	public PageInfoBo<TaskUploadInfoPo_zxy> getTaskuploadinfo(TaskUploadInfoBo_zxy taskReqBo){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AdminInfoPO_zxy admin=taskService.getAdminInfo(userDetails.getUsername());
		int taskpublishid=admin.getId();
		taskReqBo.setTaskpublishid(taskpublishid);
	
		PageInfoBo<TaskUploadInfoPo_zxy> page=new PageInfoBo<TaskUploadInfoPo_zxy>();
		page=taskService.getTaskUploadInfoByPages(taskReqBo);
		return page;
	}
	
	@ResponseBody
	@RequestMapping("searchtaskfilebyId")
	public Map searchtaskfilebyId(String teaname,int id,String taskname,HttpServletRequest request,HttpServletResponse response){
	
		HttpSession session=request.getSession();
		session.setAttribute("taskname", taskname);
		session.setAttribute("accid", id);
		//根据id获取地址
		//System.out.println("id=========="+id);
		AccessaryPo_zxy accPo=taskService.getAccessaryById(id);
		Map map=new HashMap<>();
		if(accPo!=null){
			map.put("mess", "0000");
			String accurl=accPo.getAccurl();
			File file = new File(accurl);  
			BufferedReader rd=null;
			String str = "";
			try {
				rd = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				String s = rd.readLine();
				 while (null != s) {  
		             str += s;  
		             s = rd.readLine();  
		         }
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 session.setAttribute("teaname",teaname);
			 session.setAttribute("detailtask",str);
			 //读取文件的内容显示在页面上
			 
		}else{
			map.put("mess", "8888");
		}
		return map;
	}
	
	@RequestMapping("searchtaskfileandJudge")
	public String gosearchtaskfileandJudge(HttpServletRequest request){
		return "teacherzxy/taskzxy/mytaskfileToJudge";
	}
	//我发布的课题
	@RequestMapping("Mypublishtask")
	public String goMypublishtask(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="课题";
		List<Dictionaries_zxy> tasktype=taskService.getTaskInfo(dtype);
		session.setAttribute("tasktype",tasktype);
		return "teacherzxy/taskzxy/mypublishtasklist";
	}
	@ResponseBody
	@RequestMapping("getMypublishTask")
	public PageInfoBo<TaskPo_zxy> getMypublishTask(TaskBo_zxy task){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AdminInfoPO_zxy admin=taskService.getAdminInfo(userDetails.getUsername());
		int taskpublishid=admin.getId();
		task.setTaskpublishid(taskpublishid);
		PageInfoBo<TaskPo_zxy> page=new PageInfoBo<TaskPo_zxy>();
		page=taskService.getMypublishTaskBuPage(task);
		return page;
	}
	
	
	//登录当前的管理员查看选择情况
	@RequestMapping("lookMyTeataskinfo")
	public String golookMyTeataskinfo(){
		return "teacherzxy/taskzxy/lookMyTeataskchoiceinfo";
	}
	
	@ResponseBody
	@RequestMapping("getMyTaskChoiceinfo")
	public PageInfoBo<MyTeaTaskPo_zxy> getMyTaskChoiceinfo(MyTaskReqBo myTask){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AdminInfoPO_zxy admin=taskService.getAdminInfo(userDetails.getUsername());
		int taskpublishid=admin.getId();
		myTask.setTaskpublishid(taskpublishid);
		PageInfoBo<MyTeaTaskPo_zxy> page=new PageInfoBo<MyTeaTaskPo_zxy>();
		page=taskService.getMyTaskChoiceinfoByPage(myTask);
		
		return page;
	}
	
	@ResponseBody
	@RequestMapping("judgerank")
	public void gojudgerank(HttpServletResponse response,int accid,String degree){
		response.setCharacterEncoding("utf-8");
		AccessaryPo_zxy acc=taskService.getAccessaryById(accid);
		int ownid=-1;
		if(acc!=null){
			ownid=acc.getOwnid();
		}
		//System.out.println("degree="+degree);
		String rank="";
		if(degree!=null&&!"".equals(degree)){
			if(degree.equals("1")){
				rank="优";
			}else if(degree.equals("2")){
				rank="良";
			}else if(degree.equals("3")){
				rank="差";
			}
		}
		
		//更新teatask的评价记录
		TeataskBo_zxy teatask=new TeataskBo_zxy();
		teatask.setIscheck(1);
		teatask.setCheckstatus(1);
		teatask.setTeaid(ownid);
		teatask.setRank(rank);
		int tag=-1;
		try {
			tag=taskService.updaterank(teatask);
			if(tag==1){
				response.getWriter().print("<script> alert('课题文件的等级评价成功！') </script>");
			}else{
				response.getWriter().print("<script> alert('课题文件的等级评价失败！') </script>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			try {
				response.getWriter().print("<script> alert('课题文件的等级评价失败！') </script>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="downtaskfile",method=RequestMethod.GET)
	public void  downFile(HttpServletRequest request,HttpServletResponse response,int id) throws IOException{
		AccessaryPo_zxy accPo=taskService.getAccessaryById(id);
		String filename="";
		
		
		if(accPo!=null){
			filename=accPo.getAccurl();
			 String filename2=filename;
	         String prefix=filename2.substring(filename2.lastIndexOf(".")+1);
	         String contentType = new MimetypesFileTypeMap().getContentType(filename);
	         String contentDisposition = "attachment;filename=taskfile"+"."+prefix;
	         FileInputStream input;
			try {
				input = new FileInputStream(filename);
				response.setHeader("Content-Type",contentType);
		        response.setHeader("Content-Disposition",contentDisposition);
		        ServletOutputStream output = response.getOutputStream();
		        // 把输入流中的数据写入到输出流中
		        IOUtils.copy(input,output);
		        input.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
		}else{
			try {
				response.getWriter().print("<script> alert('文件下载失败！') </script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
