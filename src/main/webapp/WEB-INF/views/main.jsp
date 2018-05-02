<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>教务管理系统</title>
		<%@include file="/common/easyui.jspf"%>
		<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
	</head>
	<body class="mainbody">
		<!--顶部-->
		<div class="header">
			<div class="logo_box">
				<div class="logo">
					<h2><img src="${basePath}/static/img/login/logo_school.png" /></h2>
				</div>
				<div class="info">
					<ul>
						<li>
							<span>欢迎您：</span>
							<span><security:authentication property="principal.username" />同学</span>
						</li>
						<li>
							<a href="logout">退出</a>
						</li>
					</ul>
				</div>
			</div>
			<!--菜单-->
			<ul class="nav">
				<security:authorize ifAnyGranted="STUDENT" >
					<li class="nav_top">
						<a class="nav_top_link" href="mynoticelist" target="_son">
							<span class="index">返回首页</span>
						</a>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">我的考试</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="#">考试考试111</a>
							</li>
							<li>
								<a href="#">考试考试222</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">教学质量评价</span>
						</a>
						<ul class="sub">
							<li>
								<a href="gomyevaluate" target="_son">教学评价</a>
							</li>
							<li>
								<a href="goevaluatelist" target="_son">教学评价管理</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">我的信息</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="stuowninfo" target="_son">个人信息修改</a>
							</li>
							<li>
								<a href="myclassinfo" target="_son">我的班级</a>
							</li>
							<li>
								<a href="myfile" target="_son">我的文档</a>
							</li>
							<li>
								<a href="gomyactivityupdate" target="_son">我的活动编辑</a>
							</li>
							<li>
								<a href="goupdatepass" target="_son">修改密码</a>
							</li>
							<li>
								<a href="javascript:;"  onclick="flushDb()">清除系统缓存</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">我的课程</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="myownclassinfo" target="_son">查看我的课表</a>
							</li>
							<li>
								<a href="chooseclassonline" target="_son">网上选课</a>
							</li>
							<li>
								<a href="gosemesterteacher" target="_son">学期任课老师</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">我的重修</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="myrepairlist" target="_son">挂科科目</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">我的活动</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="gopubactivity" target="_son">发布活动</a>
							</li>
							<li>
								<a href="gochooseactivity" target="_son">活动报名</a>
							</li>
							<li>
								<a href="gomanageactivity" target="_son">活动管理</a>
							</li>
						</ul>
					</li>
				</security:authorize>
				<security:authorize ifAnyGranted="ADMIN" >
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">网上选课管理员1</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="#">全校性选课</a>
							</li>
							<li>
								<a href="#">课程行课情况查询</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">网上选课管理员2</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="#">全校性选课</a>
							</li>
							<li>
								<a href="#">课程行课情况查询</a>
							</li>
						</ul>
					</li>
				</security:authorize>
				<security:authorize ifAnyGranted="TEACHER" >
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">网上选课教师</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="#">全校性选课</a>
							</li>
							<li>
								<a href="#">课程行课情况查询</a>
							</li>
						</ul>
					</li>	
				</security:authorize>
			</ul>
			<!--选项卡-->
			<!-- <div class="tab">
				<p class="location">
					<em>当前位置 --
						<span id="dqwz">通知公告</span>
					</em>
				</p>
			</div> -->
			<!--选项卡-->
		</div>
		
		<!--中间-->
		<div class="center">
			<div>
				<iframe name="_son" src="mynoticelist"  width="98%" height="543px" style="margin: 0px 12px;" scrolling="no" frameborder="0"></iframe>
			</div>
		</div>
		<!--底部-->
		<div class="footer">
			
		</div>
		<script type="text/javascript">
		
			function flushDb(){
				$.ajax({
					type:'post',
					url:'flushdb',
					contentType:"application/json",    //必须配置
					//data:JSON.stringify(param),//转换成字符串，客户端作为生产者
					success:function(result){
						//alert(result.stuimage);
						alert(result.responseDesc);
					} 
				});
			}	
		
		</script>
	</body>
</html>
