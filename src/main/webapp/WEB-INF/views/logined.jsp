<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="static/js/nouser.js"></script>
<title>Insert title here</title>
</head>
<body>
	登录成功页面！！JSP<br>
	security:authentication:principal对象<security:authentication property="principal" /><br>
	security:authentication:details对象<security:authentication property="details" /><br>
	security:authentication:Credentials对象<security:authentication property="Credentials" /><br>
	security:authentication:authorities对象<security:authentication property="authorities" /><br>
	拥有相应权限才能显示：<security:authorize ifAnyGranted="USER1" >
		拥有USER权限
	</security:authorize><br>
	<a href="logout">退出登录</a>
</body>
</html>