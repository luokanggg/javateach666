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
}
