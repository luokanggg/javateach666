package com.ctbu.javateach666.service.impl.thc;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.common.service.BaseService;
import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.constant.Constant;
import com.ctbu.javateach666.dao.THCIndexImgDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCIndexImgListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCIndexImgListRspBO;
import com.ctbu.javateach666.pojo.po.LKAccessoryPO;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCIndexImgPO;
import com.ctbu.javateach666.service.interfac.thc.THCIndexImgService;

@Service
public class THCIndexImgServiceImpl extends BaseServiceImpl<THCIndexImgDao, THCIndexImgPO> implements THCIndexImgService{
	
	@Autowired
	private THCIndexImgDao tHCIndexImgDao;
	
	//首页图片管理
	public PageInfoBo<THCIndexImgListRspBO> getImgList(THCIndexImgListRepBO tHCIndexImgListRepBO) {
		//定义出参
		PageInfoBo<THCIndexImgListRspBO> rsp = new PageInfoBo<THCIndexImgListRspBO>();
		int page = 0;
		page = ((tHCIndexImgListRepBO).getPage() - 1) * tHCIndexImgListRepBO.getRows();
		tHCIndexImgListRepBO.setPage(page);
		System.out.println("123"+ tHCIndexImgListRepBO);
		int total = tHCIndexImgDao.getTotal(tHCIndexImgListRepBO);
		if(total < 1)
			return rsp;
		List<THCIndexImgListRspBO> list = tHCIndexImgDao.getIndexImgbyPage(tHCIndexImgListRepBO);
		rsp.setRows(list);
		rsp.setTotal(total);
		
		return rsp;
	}

	public int insertImg(CommonsMultipartFile file, HttpServletRequest request) {
		//获取文件名字
		String filename = file.getOriginalFilename();
		//获取文件后缀
		String suffix = filename.substring(filename.lastIndexOf("."));
		//自定义文件名
		String imgname = request.getParameter("imgname") + suffix;
		String imgno = request.getParameter("imgno");
		String is_pub = request.getParameter("is_pub");
		//定义附件表实体
		THCIndexImgPO tHCIndexImgPO = new THCIndexImgPO();
		//定义数据库图片名称
		String imgurl = "static\\img\\indeximg\\" + imgname;
		//定义文件上传路径
		//String saveserverpath = request.getServletContext().getRealPath("/") + "static\\img\\indeximg";
		String savepath = "C:\\Users\\Administrator\\Desktop\\javateach6666\\src\\main\\webapp\\" + "static\\img\\indeximg";
		
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
		tHCIndexImgPO.setImgno(imgno);
		tHCIndexImgPO.setImgname(imgname);
		tHCIndexImgPO.setImgurl(imgurl);
		tHCIndexImgPO.setIs_pub(Integer.valueOf(is_pub));
		int count = tHCIndexImgDao.insert(tHCIndexImgPO);
		return count;
	}

	public int showimg(THCIndexImgPO tHCIndexImgPO) {
		return tHCIndexImgDao.showimg(tHCIndexImgPO);
	}
	
	public int hideimg(THCIndexImgPO tHCIndexImgPO) {
		return tHCIndexImgDao.hideimg(tHCIndexImgPO);
	}

	public List<THCIndexImgPO> getPicture() {
		return tHCIndexImgDao.getPicture();
	}

	public THCIndexImgPO selectImgno(String imgno) {
		return tHCIndexImgDao.selectImgno(imgno);
	}
}
