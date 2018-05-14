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
<title>密码管理</title>
</head>
<body>
	<div id="bod">
		<table width="80%" height="100px" bgcolor="#f2f6fb" border="1" style="border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		    <td width="20%">账号：</td>
		    <td width="20%">{{ AdminPass.username }}</td>
		    <td width="20%">密码：</td>
		    <td width="20%"><input type="password" v-model="AdminPass.password" /></td>
		  </tr>
  		  <tr>
		    <td colspan="4">
		    	<button class="easyui-linkbutton" style="width:100px;height:30px;" id="submits" v-on:click="submits">提交</button>
		    </td>
		  </tr>
		</table>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script type="text/javascript">
		$(function(){
			var man = new Vue({
				el:'#bod',
				data:{
					AdminPass:''
					
				},
				methods:{
					getDate: function(){
						$.ajax({
							type: 'post',
							url: 'getadminpass',
							contentType: "application/json",    //必须配置
							success: function(result){
								man.AdminPass = result;
								//alert(man.AdminInfo);
							} 
						});
					},
					submits: function(){
						var data = {
								"username": man.AdminPass.username,
								"password": man.AdminPass.password,
								};
						$.ajax({
							type: 'post',
							url: 'updateadminpass',
							contentType: 'application/json',
							data: JSON.stringify(data),
							success: function(result){
								man.AdminPass = result;
								alert(man.AdminPass.responseDesc);
							}
						});
					},
				}
			});
			
			man.getDate();
		})
	</script>
</body>
</html>