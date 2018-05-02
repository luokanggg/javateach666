<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登录页面</title>
		<%@include file="/common/easyui.jspf"%>
		<link rel="stylesheet" type="text/css" href="${basePath}/static/css/login.css"/>
		<script type="text/javascript" src="${basePath}/static/js/gVerify.js" charset="utf-8"></script>	
	</head>
	<body>
		<form action="/javateach666/login" method="post">
			<div class="main">
				<!--顶部logo-->
				<div class="top_logo">
					<h2><a href="index"><img src="${basePath}/static/img/login/logo_school.png" alt="重庆工商大学" /></a></h2>
					<h3><img src="" alt="" /></h3>
				</div>				
				<!--左侧图片-->
				<div class="left_img">
					<img src="${basePath}/static/img/login/login_pic.png" class="login_pic" />
				</div>
				<!--右侧表单-->
				<div class="right_form">
					<dl style="margin-top: 92px;">
						<dt class="username">
							<lable>用户名：</lable>
						</dt>
						<dd>
							<input type="text" name="username" class="text_nor" autocomplete="off"/>
						</dd>
					</dl>
					<div style="clear: both;"></div>
					<dl>
						<dt class="password">
							<lable>密&nbsp;&nbsp;&nbsp; 码：</lable>
						</dt>
						<dd>
							<input type="password" name="password" class="text_nor" autocomplete="off"/>
						</dd>
					</dl>
					<div style="clear: both;"></div>
					<dl style="position: relative;">
						<dt class="verification">
							<lable>验证码：</lable>
						</dt>
						<dd>
							<input type="text"  id="input_vcode" name="verification" autocomplete="off" class="verification_text"/>
							<div id="vcode" class="verification_img"></div>
						</dd>
					</dl>
					<div style="clear: both;"></div>
					<dl style="position: relative;">
						<dt class="username">
							<lable>记住我：</lable>
						</dt>
						<dd>
							<input type="checkbox" id="remeber_me" name="remeber-me" /><br>
						</dd>
					</dl>
					<input type="hidden" name="${ _csrf.parameterName}" value="${ _csrf.token}" />
					<div style="clear: both;"></div>
					<dl style="margin-left: 35%;">
						<dd>
							<input type="submit" name="submit" class="btn_d1" value=""/>
							<input type="reset" name="reset" class="btn_d2" value=""/>
						</dd>
					</dl>
					<div style="clear: both;"></div>
				</div>
			</div>			
		</form>
		<script type="text/javascript" src="${basePath}/static/js/login.js" charset="utf-8"></script>
		<script type="text/javascript">
			var verifyCode = new GVerify("vcode");//图片div id
			var errmess = "${ SPRING_SECURITY_LAST_EXCEPTION.message }" ;
			if(errmess != null){
				if("Bad credentials" == errmess){
					alert("用户名或密码错误");
				}else if("User is disabled" == errmess){
					alert("该账户已被禁用");
				}
				//alert(errmess);
			}
			
			$('form').submit(function(){
				var res = verifyCode.validate(document.getElementById("input_vcode").value);//input_vcode  输入框id
				if(res){
					//alert("验证正确！");
				}else{
					alert("验证错误！");
					$("#input_vcode").val("");
					return false;
				}
			});
		</script>
	</body>
</html>
