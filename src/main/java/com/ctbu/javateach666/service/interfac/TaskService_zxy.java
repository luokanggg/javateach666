package com.ctbu.javateach666.service.interfac;

import java.util.List;

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

public interface TaskService_zxy {
	//查询数据字典获取课题的类型
	public List<Dictionaries_zxy> getTaskInfo(String dtype);
	//查询当前登录人管理员信息
	public AdminInfoPO_zxy getAdminInfo(String username);
	//插入发布的课题信息
	public int insertTaskInfo(TaskBo_zxy taskBo);
	//查询是否课题重复
	public TaskPo_zxy getTaskByTaskInfo(TaskBo_zxy taskBo);
	
	//获取课题的总数
	public int totalTask(TaskBo_zxy taskBo);
	public List<TaskBo_zxy> getTaskByPage(TaskBo_zxy taskBo);
	public PageInfoBo<TaskBo_zxy> getAllPublishTasks(TaskBo_zxy taskBo);
	//删除task
	public int deleteTaskById(int id);
	public TaskPo_zxy getTaskById(int id);
	//选课题时候查询
	public int totalTaskToChoice(TaskBo_zxy taskBo);
	public List<TaskPo_zxy> getAllTask(TaskBo_zxy taskBo);
	public PageInfoBo<TaskPo_zxy> getAllpublishTaskTochoice(TaskBo_zxy task);
	
	//判断该用户是否可以选择
	public TeataskPo_zxy IsCanChoice(TeataskBo_zxy teataskBo);
	//直接判断给用户id
	public TeataskPo_zxy IsCanChoiceById(int teaid);
	//判断该用户已选择是否审批通过
	public TeataskPo_zxy ChoiceAndIsAgree(TeataskBo_zxy teataskBo);
	
	
	//直接插入课题选择
	public int inserTeaTask(TeataskBo_zxy teataskBo);
	//修改数据
	public int updateTeaTask(TeataskBo_zxy teataskBo);
	
	//我的课题选择
	public int totalMyTask(MyTaskReqBo myTask);
	public List<MyTeaTaskPo_zxy> getMytask(MyTaskReqBo myTask);
	public PageInfoBo<MyTeaTaskPo_zxy> goMytaskBuPage(MyTaskReqBo myTask);
	
	//管理员查询课题选择情况
	public int totalTaskChoiceinfo(MyTaskReqBo myTask);
	public List<MyTeaTaskPo_zxy> getTaskChoiceinfo(MyTaskReqBo myTask);
	public PageInfoBo<MyTeaTaskPo_zxy>  getTaskChoiceByPage(MyTaskReqBo myTask);
	
	//审批课题选择
	public int updateTaskChoice(TeataskBo_zxy teataskBo);
	//课题内容的更新
	public int updateTask(TaskBo_zxy task);
	//修改数据已驳回
	public int updateTeaTaskDis(TeataskBo_zxy teataskBo);
	
	//判断用户课题被审批通过没有
	public TeataskPo_zxy isAdmined(TeataskBo_zxy teataskBo);
	
	//插入课题的文件附件信息
	public int insertAcceaasryTask(AccessaryBo_zxy accBo);
	//查询我的课题文件
	public AccessaryPo_zxy getMyTaskFile(AccessaryBo_zxy accBo);	
	
	public int updateAccessaryTask(AccessaryBo_zxy accBo);
	public AccessaryPo_zxy getAccessaryById(int id);
	
	//当前登录用户的管理员查看课题情况
	public int totalMyTaskinfo(TaskBo_zxy taskBo);
	public List<TaskPo_zxy> getMyTaskinfo(TaskBo_zxy taskBo);
	public  PageInfoBo<TaskPo_zxy> getMypublishTaskBuPage(TaskBo_zxy taskBo);
	
	//当前登录的管理员查询课题选择情况(审批)
	public int totalMyTaskChoiceinfo(MyTaskReqBo myTask);
	public List<MyTeaTaskPo_zxy> getMyTaskChoiceinfo(MyTaskReqBo myTask);
	public PageInfoBo<MyTeaTaskPo_zxy> getMyTaskChoiceinfoByPage(MyTaskReqBo myTask);
	
	public int TotalTaskuploadinfo(TaskUploadInfoBo_zxy taskReqBo);
	public List<TaskUploadInfoPo_zxy> getTaskUploadInfoByPage(TaskUploadInfoBo_zxy taskReqBo);
	public PageInfoBo<TaskUploadInfoPo_zxy>  getTaskUploadInfoByPages(TaskUploadInfoBo_zxy taskReqBo);
	
	
	//管理员删除课题操作
	//判断该课题是否被选择且通过
	public List<TeataskPo_zxy> isChoicedAndAgree(TeataskBo_zxy teataskBo);
	public int deleteTeaTaskByTaskId(int id);
	
	//更新评价等级
	public int updaterank(TeataskBo_zxy teataskBo);
}
