package com.ctbu.javateach666.service.interfac;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.AlreadyChooseComboBoxBO;
import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseReqBO;
import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseRspBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListRspBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoRspBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;

/**
 * 我的课程服务类接口
 *
 * @author luokan
 */
public interface LKMyClassService {
	public List<LKInitMyClassInfoRspBO> initMyClassInfo();
	public PageInfoBo<LKChooseClassOnlineListRspBO> getChooseClassOnlineList(LKChooseClassOnlineListReqBO lKChooseClassOnlineListReqBO);
	public BaseInfoBO ChooseClass(LKChooseClassReqBO lKChooseClassReqBO);
	public PageInfoBo<LKAlreadyChooseRspBO> goAlreadyChoose(LKAlreadyChooseReqBO lKAlreadyChooseReqBO);
	public List<AlreadyChooseComboBoxBO> getAlreadyChooseComboBox();
	public List<LKInitMyClassInfoRspBO> getMyClassInfo(LKInitMyClassInfoReqBO lKInitMyClassInfoReqBO);
	public BaseInfoBO cancelClass(LKcancelClassReqBO lKcancelClassReqBO);
}
