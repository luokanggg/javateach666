<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
sss
  <% 
  String filename = request.getAttribute("filename");
  response.setContentType("application/x-download");//设置为下载application/x-download 
  String filedownload = filename;//即将下载的文件的相对路径 
  response.addHeader("Content-Disposition","attachment;filename=" + filename); 
   try 
  { 
  RequestDispatcher dis = request.getRequestDispatcher(filename); 
  if(dis!= null) 
  { 
  dis.forward(request,response); 
  } 
  response.flushBuffer(); 
  out.clear();  
out = pageContext.pushBody(); 
  } 
  catch(Exception e)   {  e.printStackTrace();   } 

%> 
</body>
</html>