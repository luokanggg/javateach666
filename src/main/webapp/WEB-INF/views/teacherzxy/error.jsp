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
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/easyui.css"/>
 
<title>404</title>
</head>
<style>

.tab{
	border:1px solid #99CCFF;
	border-radius:5px;
	height:460px;
	width:1200px;
	
	background-image: url(/javateach666/static/img/404.png) ;
	background-position:5px -87px;
}




.txt01{font:Verdana, Geneva, sans-serif,宋体;padding:3px 2px 2px 2px; border-width:1px; border-color:#ddd;  color:#000;}
</style>
<body class="easyui-layout" style="background-color: #F8F8F8">
<center>
	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
		<div class="tab1" >
			<!-- <map name="Map" id="Map">
				<area coords="0,0,110,260" shape="rect" href="fanhuimain" />
			</map> -->
			<br><br><br><br>
			<a href="fanhuimain" target="parent"><img src="/javateach666/static/img/error.jpg"></a>
        </div>
	</div>
	
	</center>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
<script>


</script>
</body>
</html>