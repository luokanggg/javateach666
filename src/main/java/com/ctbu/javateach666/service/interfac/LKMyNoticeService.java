package com.ctbu.javateach666.service.interfac;

import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListRspBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;

/**
 * 我的通知服务类接口
 *
 * @author luokan
 */
public interface LKMyNoticeService {
	public PageInfoBo<LKGetMyNoticeListRspBO> getMyNoticeList(LKGetMyNoticeListReqBO lKGetMyNoticeListReqBO);
}
