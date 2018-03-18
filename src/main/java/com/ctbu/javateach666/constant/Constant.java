package com.ctbu.javateach666.constant;

/**
 * 常量配置
 *
 * @author luokan
 */
public class Constant {
	
	public static final String RSP_SUCCESS_CODE = "0000";
	public static final String RSP_SUCCESS_MESSAGE = "成功";
	
	public static final String RSP_FALSE_CODE = "8888";
	public static final String RSP_FALSE_MESSAGE = "失败";
	
	//通知表类型
	public static final class NOTICE_TYPE {
		//老师to学生
		public static final int TEA_TO_STU = 1;
		//学生to老师
		public static final int STU_TO_TEA = 2;
		//学生to学生
		public static final int STU_TO_STU = 3;
	}
	
	//附件表类型
	public static final class ACCESSORY_TYPE {
		//学生资料
		public static final int STU = 1;
		//老师资料
		public static final int TEA = 2;
		//课程作业
		public static final int CLASS = 3;
	}
	
	//学期
	public static final class SEMESTER {
		//上学期
		public static final int LAST = 1;
		//下学期
		public static final int NEXT = 2;
	}
}
