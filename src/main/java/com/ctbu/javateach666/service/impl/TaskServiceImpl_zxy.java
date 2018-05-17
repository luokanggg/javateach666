package com.ctbu.javateach666.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.TaskDao_zxy;
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
import com.ctbu.javateach666.pojo.po.TeataskPo_zxy;
import com.ctbu.javateach666.service.interfac.TaskService_zxy;

@Service
public class TaskServiceImpl_zxy implements TaskService_zxy{
	@Autowired
	TaskDao_zxy taskDao;

	@Override
	public List<Dictionaries_zxy> getTaskInfo(String dtype) {
		// TODO Auto-generated method stub
		return taskDao.getTaskInfo(dtype);
	}

	@Override
	public AdminInfoPO_zxy getAdminInfo(String username) {
		// TODO Auto-generated method stub
		return taskDao.getAdminInfo(username);
	}

	@Override
	public int insertTaskInfo(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		return taskDao.insertTaskInfo(taskBo);
	}

	@Override
	public TaskPo_zxy getTaskByTaskInfo(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		return taskDao.getTaskByTaskInfo(taskBo);
	}

	@Override
	public int totalTask(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		return taskDao.totalTask(taskBo);
	}

	@Override
	public List<TaskBo_zxy> getTaskByPage(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		return taskDao.getTaskByPage(taskBo);
	}

	@Override
	public PageInfoBo<TaskBo_zxy> getAllPublishTasks(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		PageInfoBo<TaskBo_zxy> rsp=new PageInfoBo<TaskBo_zxy>();
		int page=0;
		page=(taskBo.getPage()-1)*taskBo.getRows();
		taskBo.setPage(page);
		int total=0;
		total=taskDao.totalTask(taskBo);
		if(total<1){
			return rsp;
		}else{
			List<TaskBo_zxy> list=taskDao.getTaskByPage(taskBo);
			rsp.setRows(list);
			rsp.setTotal(total);
			return rsp;
		}
		
	}

	@Override
	public int deleteTaskById(int id) {
		// TODO Auto-generated method stub
		return taskDao.deleteTaskById(id);
	}

	@Override
	public TaskPo_zxy getTaskById(int id) {
		// TODO Auto-generated method stub
		return taskDao.getTaskById(id);
	}

	@Override
	public int totalTaskToChoice(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		return taskDao.totalTaskToChoice(taskBo);
	}

	@Override
	public List<TaskPo_zxy> getAllTask(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		return taskDao.getAllTask(taskBo);
	}

	@Override
	public PageInfoBo<TaskPo_zxy> getAllpublishTaskTochoice(TaskBo_zxy task) {
		// TODO Auto-generated method stub
		PageInfoBo<TaskPo_zxy> rsp=new PageInfoBo<TaskPo_zxy>();
		int page=0;
		page=(task.getPage()-1)*task.getRows();
		task.setPage(page);
		if(task.getTasktype()!=null&&!"".equals(task.getTasktype())){
			String tasktype="";
			tasktype="%"+task.getTasktype()+"%";
			task.setTasktype(tasktype);
		}
		int total=0;
		total=taskDao.totalTaskToChoice(task);
		if(total < 1){
			return rsp;
		}
		List<TaskPo_zxy> list=taskDao.getAllTask(task);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	@Override
	public TeataskPo_zxy IsCanChoice(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.IsCanChoice(teataskBo);
	}

	@Override
	public TeataskPo_zxy IsCanChoiceById(int teaid) {
		// TODO Auto-generated method stub
		return taskDao.IsCanChoiceById(teaid);
	}

	@Override
	public int inserTeaTask(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.inserTeaTask(teataskBo);
	}

	@Override
	public int updateTeaTask(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.updateTeaTask(teataskBo);
	}

	@Override
	public int totalMyTask(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		return taskDao.totalMyTask(myTask);
	}

	@Override
	public List<MyTeaTaskPo_zxy> getMytask(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		return taskDao.getMytask(myTask);
	}

	@Override
	public PageInfoBo<MyTeaTaskPo_zxy> goMytaskBuPage(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		PageInfoBo<MyTeaTaskPo_zxy> rsp=new PageInfoBo<MyTeaTaskPo_zxy>();
		int page=0;
		page=(myTask.getPage()-1)*myTask.getRows();
		myTask.setPage(page);
		int total=0;
		total=taskDao.totalMyTask(myTask);
		if(total < 1){
			return rsp;
		}
		List<MyTeaTaskPo_zxy> list=taskDao.getMytask(myTask);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	@Override
	public int totalTaskChoiceinfo(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		return taskDao.totalTaskChoiceinfo(myTask);
	}

	@Override
	public List<MyTeaTaskPo_zxy> getTaskChoiceinfo(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		return taskDao.getTaskChoiceinfo(myTask);
	}

	@Override
	public PageInfoBo<MyTeaTaskPo_zxy> getTaskChoiceByPage(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		PageInfoBo<MyTeaTaskPo_zxy> rsp=new PageInfoBo<MyTeaTaskPo_zxy>();
		int page=0;
		page=(myTask.getPage()-1)*myTask.getRows();
		myTask.setPage(page);
		if(myTask.getTaskpublisher()!=null||!"".equals(myTask.getTaskpublisher())){
			String publisher="";
			publisher="%"+myTask.getTaskpublisher()+"%";
			myTask.setTaskpublisher(publisher);
		}
		if(myTask.getTasktype()!=null||!"".equals(myTask.getTasktype())){
			String pubtype="";
			pubtype="%"+myTask.getTasktype()+"%";
			myTask.setTasktype(pubtype);
		}
		
		int total=0;
		total=taskDao.totalTaskChoiceinfo(myTask);
		if(total < 1){
			return rsp;
		}
		List<MyTeaTaskPo_zxy> list=taskDao.getTaskChoiceinfo(myTask);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	@Override
	public int updateTaskChoice(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.updateTaskChoice(teataskBo);
	}

	@Override
	public int updateTask(TaskBo_zxy task) {
		// TODO Auto-generated method stub
		return taskDao.updateTask(task);
	}

	@Override
	public TeataskPo_zxy isAdmined(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.isAdmined(teataskBo);
	}

	@Override
	public int insertAcceaasryTask(AccessaryBo_zxy accBo) {
		// TODO Auto-generated method stub
		return taskDao.insertAcceaasryTask(accBo);
	}

	@Override
	public AccessaryPo_zxy getMyTaskFile(AccessaryBo_zxy accBo) {
		// TODO Auto-generated method stub
		return taskDao.getMyTaskFile(accBo);
	}

	@Override
	public int updateAccessaryTask(AccessaryBo_zxy accBo) {
		// TODO Auto-generated method stub
		return taskDao.updateAccessaryTask(accBo);
	}

	@Override
	public int TotalTaskuploadinfo(TaskUploadInfoBo_zxy taskReqBo) {
		// TODO Auto-generated method stub
		return taskDao.TotalTaskuploadinfo(taskReqBo);
	}

	@Override
	public List<TaskUploadInfoPo_zxy> getTaskUploadInfoByPage(TaskUploadInfoBo_zxy taskReqBo) {
		// TODO Auto-generated method stub
		return taskDao.getTaskUploadInfoByPage(taskReqBo);
	}

	@Override
	public PageInfoBo<TaskUploadInfoPo_zxy> getTaskUploadInfoByPages(TaskUploadInfoBo_zxy taskReqBo) {
		// TODO Auto-generated method stub
		PageInfoBo<TaskUploadInfoPo_zxy> rsp=new PageInfoBo<TaskUploadInfoPo_zxy>();
		int page=0;
		page=(taskReqBo.getPage()-1)*taskReqBo.getRows();
		taskReqBo.setPage(page);
		int total=0;
		total=taskDao.TotalTaskuploadinfo(taskReqBo);
		if(total < 1){
			return rsp;
		}
		List<TaskUploadInfoPo_zxy> list=taskDao.getTaskUploadInfoByPage(taskReqBo);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	@Override
	public AccessaryPo_zxy getAccessaryById(int id) {
		// TODO Auto-generated method stub
		return taskDao.getAccessaryById(id);
	}

	@Override
	public int totalMyTaskinfo(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		return taskDao.totalMyTaskinfo(taskBo);
	}

	@Override
	public List<TaskPo_zxy> getMyTaskinfo(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		return taskDao.getMyTaskinfo(taskBo);
	}

	@Override
	public PageInfoBo<TaskPo_zxy> getMypublishTaskBuPage(TaskBo_zxy taskBo) {
		// TODO Auto-generated method stub
		PageInfoBo<TaskPo_zxy> rsp=new PageInfoBo<TaskPo_zxy>();
		int page=0;
		page=(taskBo.getPage()-1)*taskBo.getRows();
		taskBo.setPage(page);
		if(taskBo.getTasktype()!=null&&!"".equals(taskBo.getTasktype())){
			String tasktype="";
			tasktype="%"+taskBo.getTasktype()+"%";
			taskBo.setTasktype(tasktype);
		}
		int total=0;
		total=totalMyTaskinfo(taskBo);
		if(total < 1){
			return rsp;
		}
		List<TaskPo_zxy> list=taskDao.getMyTaskinfo(taskBo);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	@Override
	public int totalMyTaskChoiceinfo(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		return taskDao.totalMyTaskChoiceinfo(myTask);
	}

	@Override
	public List<MyTeaTaskPo_zxy> getMyTaskChoiceinfo(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		return taskDao.getMyTaskChoiceinfo(myTask);
	}

	@Override
	public PageInfoBo<MyTeaTaskPo_zxy> getMyTaskChoiceinfoByPage(MyTaskReqBo myTask) {
		// TODO Auto-generated method stub
		PageInfoBo<MyTeaTaskPo_zxy> rsp=new PageInfoBo<MyTeaTaskPo_zxy>();
		int page=0;
		page=(myTask.getPage()-1)*myTask.getRows();
		myTask.setPage(page);
		int total=0;
		total=taskDao.totalMyTaskChoiceinfo(myTask);
		if(total < 1){
			return rsp;
		}
		List<MyTeaTaskPo_zxy> list=taskDao.getMyTaskChoiceinfo(myTask);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	@Override
	public TeataskPo_zxy ChoiceAndIsAgree(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.ChoiceAndIsAgree(teataskBo);
	}

	@Override
	public int updateTeaTaskDis(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.updateTeaTaskDis(teataskBo);
	}

	@Override
	public List<TeataskPo_zxy> isChoicedAndAgree(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.isChoicedAndAgree(teataskBo);
	}

	@Override
	public int deleteTeaTaskByTaskId(int taskid) {
		// TODO Auto-generated method stub
		return taskDao.deleteTeaTaskByTaskId(taskid);
	}

	@Override
	public int updaterank(TeataskBo_zxy teataskBo) {
		// TODO Auto-generated method stub
		return taskDao.updaterank(teataskBo);
	}
	
}
