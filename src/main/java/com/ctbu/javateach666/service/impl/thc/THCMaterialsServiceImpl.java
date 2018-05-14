package com.ctbu.javateach666.service.impl.thc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.THCMaterialsDao;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCIndexImgListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCMaterialsRepBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCMaterialsPO;
import com.ctbu.javateach666.service.interfac.thc.THCMaterialsService;

@Service
public class THCMaterialsServiceImpl extends BaseServiceImpl<THCMaterialsDao, THCMaterialsPO> implements THCMaterialsService{
	
	@Autowired
	private THCMaterialsDao tHCMaterialsDao;
	
	public List<THCMaterialsPO> getMaterials() {
		return tHCMaterialsDao.getMaterials();
	}

	public PageInfoBo<THCMaterialsPO> getMaterialsList(THCMaterialsRepBO tHCMaterialsRepBO) {
		//定义出参
		PageInfoBo<THCMaterialsPO> rsp = new PageInfoBo<THCMaterialsPO>();
		int page = 0;
		page = ((tHCMaterialsRepBO).getPage() - 1) * tHCMaterialsRepBO.getRows();
		tHCMaterialsRepBO.setPage(page);
		System.out.println("123"+ tHCMaterialsRepBO);
		int total = tHCMaterialsDao.getTotal(tHCMaterialsRepBO);
		if(total < 1)
			return rsp;
		List<THCMaterialsPO> list = tHCMaterialsDao.getMaterialsByPage(tHCMaterialsRepBO);
		rsp.setRows(list);
		rsp.setTotal(total);
		return rsp;
	}
	
	
	public int insertMaterials(CommonsMultipartFile file, HttpServletRequest request) {
		//获取文件名字
		String filename = file.getOriginalFilename();
		//获取文件后缀
		String suffix = filename.substring(filename.lastIndexOf("."));
		//自定义文件名
		String imgname = request.getParameter("mtitle") + suffix;
		String mtitle = request.getParameter("mtitle");
		Date mtime = new Date();
		//定义附件表实体
		THCMaterialsPO tHCMaterialsPO = new THCMaterialsPO();
		//定义数据库图片名称
		String imgurl = "static\\materials\\" + imgname;
		//定义文件上传路径
		//String saveserverpath = request.getServletContext().getRealPath("/") + "static\\img\\materials";
		String savepath = "C:\\Users\\Administrator\\Desktop\\javateach6666\\src\\main\\webapp\\" + "static\\materials";
		
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
		tHCMaterialsPO.setMurl(imgurl);
		tHCMaterialsPO.setMtitle(mtitle);
		tHCMaterialsPO.setMtime(mtime);
		int count = tHCMaterialsDao.insert(tHCMaterialsPO);
		return count;
	}

	public int updateMaterials(CommonsMultipartFile file, HttpServletRequest request) {
		//获取文件名字
		String filename = file.getOriginalFilename();
		//获取文件后缀
		String suffix = filename.substring(filename.lastIndexOf("."));
		//自定义文件名
		String imgname = request.getParameter("mtitle") + suffix;
		String mtitle = request.getParameter("mtitle");
		Date mtime = new Date();
		String id = request.getParameter("id");
		//定义附件表实体
		THCMaterialsPO tHCMaterialsPO = new THCMaterialsPO();
		//定义数据库图片名称
		String imgurl = "static\\materials\\" + imgname;
		//定义文件上传路径
		//String saveserverpath = request.getServletContext().getRealPath("/") + "static\\img\\course";
		String savepath = "C:\\Users\\Administrator\\Desktop\\javateach6666\\src\\main\\webapp\\" + "static\\materials";
		
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
		tHCMaterialsPO.setMurl(imgurl);
		tHCMaterialsPO.setMtitle(mtitle);
		tHCMaterialsPO.setMtime(mtime);
		tHCMaterialsPO.setId(Integer.parseInt(id));
		int count = tHCMaterialsDao.update(tHCMaterialsPO);
		return count;
	}

	public THCMaterialsPO checkMtitle(String mtitle) {
		return tHCMaterialsDao.checkMtitle(mtitle);
	}
	
	public String downloadMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream");
		//定义文件上传路径
		String savepath = request.getServletContext().getRealPath("/")+"static\\materials\\";
		//定义文件保存的名称
		String filename = request.getParameter("fileName").substring(request.getParameter("fileName").indexOf("materials")+10);
		System.out.println("aaaaaaaaaaaaaa"+filename);
		File downloadFile=new File(savepath,filename);
		if(!downloadFile.exists()){
			return "文件不存在！";
		}
		try {
			// 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(downloadFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
            response.addHeader("Content-Length", "" + downloadFile.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            //response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return "";
		} catch (Exception e) {
			return "文件下载失败！";
		}
	}

}
