package com.ctbu.javateach666.service.interfac;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.InformationBo_zxy;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;

public interface InformationService_zxy {
	//保存文件的数据
	public int insertInformation(InformationBo_zxy information);
	
	//获取资料的数据总数
	public int getInformationListBySeach(InformationBo_zxy reqBo);
		
	//获取资料的数据分页
	public List<InformationBo_zxy> getInformationListByPage(InformationBo_zxy reqBo);
	
	public PageInfoBo<InformationBo_zxy> getInformationList(InformationBo_zxy reqBo);
	
	//根据Id删除资料信息
	public int deleteInformationById(int id);
}
