<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%-- <%
	response.setHeader("X-Frame-Options", "SAMEORIGIN");
%> --%>
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
<style type="text/css">
	/* #classinfo-datagrid tr{height:40px;} */
	.filebox{float: left;margin-left: 10px;}
	.textbox-button{right:0;}
</style>
</head>
<body>
	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
		<div class="tab">
			<div class="tab1">
			<!--  <div id="cc" class="easyui-calendar" data-options="formatter:formatDay" style="width:290px;height:230px;"></div>  -->
			
			</div>
			<div class="tab2">
				<div class="tab3">
					<span color="#CCC" id="wei">&nbsp;&nbsp;我的信息/修改密码</span>
				</div>
				<table width="90%" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
					<tr>
						<td align="center" valign="middle">用户名：</td>
						<td><security:authentication property="principal.username" /></td>
					</tr>
					<tr>
						<td align="center" valign="middle">原密码：</td>
						<td>
							<input type="password" v-model="PassInfo.oldpass" v-on:blur="checkoldpass" name="oldpass" id="oldpass" />
							<span id="oldpassinfo" ></span>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle">新密码：</td>
						<td><input type="password" v-model="PassInfo.newpass1" name="newpass1" id="newpass1" /></td>
					</tr>
					<tr>
						<td align="center" valign="middle">新密码确认：</td>
						<td>
							<input type="password" v-model="PassInfo.newpass2" v-on:blur="checknewpass" name="newpass2" id="newpass2" />
							<span id="newpassinfo" ></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" valign="middle"><button class="easyui-linkbutton" id="submits" iconCls="icon-ok" v-on:click="sibmits">提交</button></button></td>
					</tr>
				</table>
			</div>	
		</div>	
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script type="text/javascript">
		$(function(){			
			var man = new Vue({
				el:'#bod',
				data:{
					PassInfo:{
						username:"",
						oldpass:"",
						newpass1:"",
						newpass2:"",
					},
					ResultInfo:"",
					
				},
				methods:{
					sibmits:function(){

						var oldpassinfo = $("#oldpassinfo").text();
						if(oldpassinfo != "正确！"){
							alert(oldpassinfo);
							return false;
						}
						
						var oldpass = man.PassInfo.oldpass;
						var newpass1 = man.PassInfo.newpass1;
						var newpass2 = man.PassInfo.newpass2;
						if(newpass1 != newpass2){
							alert("两次密码输入不一致！");
							return false;
						}else if(newpass1 == newpass2 && oldpass == newpass1){
							alert("新密码和原密码相同！");
							return false;
						}
						
						var data = {
							"oldpass":man.PassInfo.newpass2,
							"username":"<security:authentication property='principal.username' />",
						};
						$.ajax({
							type:'post',
							url:'updatepass',
							contentType:"application/json",    //必须配置
							data:JSON.stringify(data),//转换成字符串，客户端作为生产者
							success:function(result){
								man.ResultInfo = result;
								if(man.ResultInfo.responseCode == "0000"){
									alert(man.ResultInfo.responseDesc + "  请重新登录系统！");
									//window.location.href = "logout";
									window.open("logout","_parent");
								}else{
									alert(man.ResultInfo.responseDesc);
									window.location.reload(true);
								}
							} 
						});
					},
					checkoldpass:function(){
						//var oldpass = man.PassInfo.oldpass;
						//alert("oldpass" + oldpass);
						//man.PassInfo.oldpass = "";
						
						var data = {
								"oldpass":man.PassInfo.oldpass,
								"username":"<security:authentication property='principal.username' />",
							};
						
						$.ajax({
							type:'post',
							url:'checkoldpass',
							contentType:"application/json",    //必须配置
							data:JSON.stringify(data),//转换成字符串，客户端作为生产者
							success:function(result){
								man.ResultInfo = result;
								//alert(man.ResultInfo.responseDesc);
								if(man.ResultInfo.responseCode == "0000"){
									$("#oldpassinfo").text("正确！");
								}else{
									$("#oldpassinfo").text(man.ResultInfo.responseDesc);
								}
							} 
						});
					},
					checknewpass:function(){
						var oldpass = man.PassInfo.oldpass;
						var newpass1 = man.PassInfo.newpass1;
						var newpass2 = man.PassInfo.newpass2;
						if(newpass1 != newpass2){
							$("#newpassinfo").text("两次密码输入不一致！");
						}else if(newpass1 == newpass2 && oldpass == newpass1){
							$("#newpassinfo").text("新密码和原密码相同！");
						}else{
							$("#newpassinfo").text("正确！");
						}
					},
				}
			});
		})
	</script>
</body>
</html>