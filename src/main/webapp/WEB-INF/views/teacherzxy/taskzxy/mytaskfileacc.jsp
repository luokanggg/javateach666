<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
	response.setHeader("X-Frame-Options", "SAMEORIGIN");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header"  content="${_csrf.headerName}"/> --%>
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<title>课题详情</title>
</head>
<style>

.tab{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:458px;
	width:1310px;
}

.tab2{
	border:0px solid red;
	border-radius:5px;
	height:450px;
	width:1250px;
	margin-top:2px;
	margin-left: 30px; 
	float:left;
}
.tab3{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:23px;
	width:1180px;
	background: #99CCFF;
	line-height:22px;
	margin-left:-255px;	
}
.tab4{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:23px;
	width:650px;
	
	line-height:22px;
	margin-top: 10px;
}
#wei{
	font-weight: bolder;
	font-size: 12px;
}
#submits{
	width:70px; height:30px;
	border-radius: 4px; 
	background:#c9dffd;
	
	 text-align:center; 
	 color:#0a0af5;
	 font-size:14px;
	 font-weight: bolder;
}
#cc0{
	border:0px solid blue;
	margin-top:40px;
	height:100px;
	width:290px;

	margin-left: auto;
margin-right: auto;
}



</style>

<body>

	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
			<div class="tab">
			<div class="tab2">
				
		<form name="form1" action="uploadtask" method="post" enctype="multipart/form-data" >
		<table  width="98%" border="0" style="margin-left:12px;margin-top:13px; border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		  	<td ><font style="font-size:13px">课题名称:&nbsp;&nbsp;</font>${task.taskname}
		  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size:13px">课题类型:&nbsp;&nbsp;</font>${task.tasktype}
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size:13px">课题作者:&nbsp;&nbsp;${username}</font>
		  </tr>
		  <tr><td  height="17px"></td></tr>
		  <tr><td >
		  <input readonly style="width:1230px;height:380px" class="easyui-textbox"  data-options="multiline:true" value="${detailtask }"/>
		  </td></tr>
		  
		 </table>
		 </form>
		 </div>
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
</body>
</html>