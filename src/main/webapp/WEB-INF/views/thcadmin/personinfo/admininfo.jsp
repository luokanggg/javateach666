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
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/lktable.css"/>
<title>个人信息修改</title>
</head>
<body>
	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
		<table width="80%" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		    <td width="20%">头像：</td>
		    <td width="20%" colspan="3"><img width="100px" height="100px" src="\javateach666\static\file\public.jpg"></td>
		  </tr>
		  <tr>
		    <td width="20%">姓名：</td>
		    <td width="20%">{{ AdminInfo.adminname }}</td>
		    <td width="20%">管理员编号：</td>
		    <td width="20%">{{ AdminInfo.adminno }}</td>
		  </tr>
		  <tr>
		    <td>性别：</td>
		    <td>{{ AdminInfo.adminsex }}</td>
		    <td>学院：</td>
		    <td>{{ AdminInfo.admincollage }}</td>
		  </tr>
		  <tr>
		    <td>电话号码：</td>
		    <td><input type="text" v-model="AdminInfo.adminphone" /></td>
		    <td>邮箱：</td>
		    <td><input type="text" v-model="AdminInfo.adminemail" /></td>
		  </tr>
		  <tr>
		    <td colspan="4" align="center" valign="middle">
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
					AdminInfo:''
					
				},
				methods:{
					getDate: function(){
						$.ajax({
							type: 'post',
							url: 'initadmininfo',
							contentType: "application/json",    //必须配置
							success: function(result){
								man.AdminInfo = result;
								//alert(JSON.stringify(man.AdminInfo));
							} 
						});
					},
					submits: function(){
						var testphone = /^1[3|4|5|7|8][0-9]{9}$/ ;
						if(! testphone.test(man.AdminInfo.adminphone)){
							alert("请输入正确的手机号码！");
							return false;
						};
						var testemail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						if(! testemail.test(man.AdminInfo.adminemail)){
							alert("请输入正确的邮箱！");
							return false;
						}
						
						var data = {
								"adminno": man.AdminInfo.adminno,
								"adminphone": man.AdminInfo.adminphone,
								"adminemail": man.AdminInfo.adminemail
								};
						$.ajax({
							type: 'post',
							url: 'updateadmininfo',
							contentType: 'application/json',
							data: JSON.stringify(data),
							success: function(result){
								man.AdminInfo = result;
								alert(man.AdminInfo.responseDesc);
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