package com.ctbu.javateach666.service.impl.thc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.THCCourseIntroduceDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseIntroduceRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCIndexImgPO;
import com.ctbu.javateach666.service.interfac.thc.THCCourseIntroduceService;

@Service
public class THCCourseIntroduceServiceImpl extends BaseServiceImpl<THCCourseIntroduceDao, THCCourseIntroducePO> implements THCCourseIntroduceService{

	@Autowired
	private THCCourseIntroduceDao tHCCourseIntroduceDao;
	
	public List<THCCourseIntroducePO> getCourseIntroduce() {
		return tHCCourseIntroduceDao.getCourseIntroduce();
	}

	public PageInfoBo<THCCourseIntroducePO> getCourseIntroduceList(THCCourseIntroduceRepBO tHCCourseIntroduceRepBO) {
		//定义出参
		PageInfoBo<THCCourseIntroducePO> rsp = new PageInfoBo<THCCourseIntroducePO>();
		int page = 0;
		page = ((tHCCourseIntroduceRepBO).getPage() - 1) * tHCCourseIntroduceRepBO.getRows();
		tHCCourseIntroduceRepBO.setPage(page);
		int total = tHCCourseIntroduceDao.getCourseIntroduceTotal(tHCCourseIntroduceRepBO);
		System.out.println("total"+total);
		if(total < 1)
			return rsp;
		List<THCCourseIntroducePO> list = tHCCourseIntroduceDao.getCourseIntroduceByPage(tHCCourseIntroduceRepBO);
		System.out.println(list);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}

	@Override
	public int insertCourseIntroduce(CommonsMultipartFile file, HttpServletRequest request) {
		//获取文件名字
		String filename = file.getOriginalFilename();
		//获取文件后缀
		String suffix = filename.substring(filename.lastIndexOf("."));
		//自定义文件名
		String cname = request.getParameter("cname") + suffix;
		String csign = request.getParameter("csign");
		//定义附件表实体
		THCCourseIntroducePO tHCCourseIntroducePO = new THCCourseIntroducePO();
		//定义数据库图片名称
		String imgurl = "static\\img\\course\\" + cname;
		//定义文件上传路径
		String savepath = request.getServletContext().getRealPath("/") + "static\\img\\course";
		//String savepath = "C:\\Users\\Administrator\\Desktop\\javateach6666\\src\\main\\webapp\\" + "static\\img\\course";
		
		// 判断上传目录是否存在
		File savePath = new File(savepath);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		System.out.println("哈啊啊啊啊啊"+savepath);
		try {
			File myimg = new File(savePath, cname);
			file.transferTo(myimg);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tHCCourseIntroducePO.setCimg(imgurl);
		tHCCourseIntroducePO.setCname(cname);
		tHCCourseIntroducePO.setCsign(csign);
		int count = tHCCourseIntroduceDao.insert(tHCCourseIntroducePO);
		return count;
	}

	@Override
	public int updateCourseIntroduce(CommonsMultipartFile file, HttpServletRequest request) {
		//获取文件名字
		String filename = file.getOriginalFilename();
		//获取文件后缀
		String suffix = filename.substring(filename.lastIndexOf("."));
		//自定义文件名
		String imgname = request.getParameter("cname") + suffix;
		String csign = request.getParameter("csign");
		String cname = request.getParameter("cname");
		String id = request.getParameter("id");
		//定义附件表实体
		THCCourseIntroducePO tHCCourseIntroducePO = new THCCourseIntroducePO();
		//定义数据库图片名称
		String imgurl = "static\\img\\course\\" + imgname;
		//定义文件上传路径
		//String saveserverpath = request.getServletContext().getRealPath("/") + "static\\img\\course";
		String savepath = "C:\\Users\\Administrator\\Desktop\\javateach6666\\src\\main\\webapp\\" + "static\\img\\course";
		
		// 判断上传目录是否存在
		File savePath = new File(savepath);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		System.out.println("哈啊啊啊啊啊"+savepath);
		try {
			File myimg = new File(savePath, imgname);
			file.transferTo(myimg);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tHCCourseIntroducePO.setCimg(imgurl);
		tHCCourseIntroducePO.setCname(cname);
		tHCCourseIntroducePO.setCsign(csign);
		tHCCourseIntroducePO.setId(Integer.parseInt(id));
		int count = tHCCourseIntroduceDao.update(tHCCourseIntroducePO);
		return count;
	}

	@Override
	public THCCourseIntroducePO checkCname(String cname) {
		return tHCCourseIntroduceDao.checkCname(cname);
	}

}
