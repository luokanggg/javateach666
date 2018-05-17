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
				<!-- 管理员菜单 -->
				<security:authorize ifAnyGranted="ADMIN" >
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">个人设置</span>
						</a>
						
						<ul class="sub">
							<li>
								<a onclick="subtab('个人资料')" href="admininfo" target="_son">个人资料</a>
							</li>
							<li>
								<a onclick="subtab('密码管理')" href="adminsetpass" target="_son">密码管理</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">首页管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a onclick="subtab('轮播图片信息')" href="indeximg" target="_son">轮播图片信息</a>
							</li>
							<li>
								<a onclick="subtab('新闻信息管理')" href="gonewsinfo" target="_son">新闻信息管理</a>
							</li>
							<li>
								<a onclick="subtab('公告信息管理')" href="gopubinfo" target="_son">公告信息管理</a>
							</li>
							<li>
								<a onclick="subtab('网站介绍管理')" href="gowebintroduce" target="_son">网站介绍管理</a>
							</li>
							<li>
								<a onclick="subtab('课程简介管理')" href="gocourseintroduce" target="_son">课程简介管理</a>
							</li>
							<li>
								<a onclick="subtab('师资力量管理')" href="goteacherintroduce" target="_son">师资力量管理</a>
							</li>
							<li>
								<a onclick="subtab('学习资源管理')" href="gomaterials" target="_son">学习资源管理</a>
							</li>
<!-- 							<li>
								<a onclick="subtab('关于我们管理')" href="goourinfo" target="_son">关于我们管理</a>
							</li> -->
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">教师信息管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a onclick="subtab('教师账户信息')" href="goteaaccount" target="_son">教师账户信息</a>
							</li>
<!-- 							<li>
								<a onclick="subtab('教师职位信息')" href="goteapost" target="_son">教师职位信息</a>
							</li>
							<li>
								<a onclick="subtab('职位申请记录')" href="gopostrecord" target="_son">职位申请记录</a>
							</li> -->
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">学生信息管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a onclick="subtab('学生账户信息')" href="gostuaccount" target="_son">学生账户信息</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">数据字典管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a onclick="subtab('数据字典信息')" href="godiclist" target="_son">数据字典信息</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">学科管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a onclick="subtab('学科信息')" href="gocourseinfo" target="_son">学科信息</a>
							</li>
						</ul>
					</li>
				</security:authorize>
								<security:authorize ifAnyGranted="TEACHER" >
				  <!-- <li class="nav_top">
						<a class="nav_top_link" href="fanhuimain">
							<span class="index">返回首页</span>
						</a>
					</li> -->
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">信息设置</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="teainfo" target="_son">个人资料</a>
							</li>
							<li>
								<a href="modifyteainfo" target="_son">修改资料</a>
							</li>
							<li>
								<a href="allteainfo" target="_son">教师信息</a>
							</li>
							<li>
								<a href="allstuinfo" target="_son">学生信息</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">信件管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="noticeinfo" target="_son">发布信件</a>
							</li>
							<li>
								<a href="getAllTonotice" target="_son">我接收的信件</a>
							</li>
							<li>
								<a href="getAllMeSendnotice" target="_son">我发送的信件</a>
							</li>
							<!-- <li>
								<a href="noticeinfolist" target="_son">信件管理</a>
							</li> -->
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">资料管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="uploadinformation" target="_son">资料上传</a>
							</li>
							<li>
								<a href="informationlist"  target="_son">资料管理</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">课程管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="publishcourse" target="_son">发布选课</a>
							</li>
							<li>
								<a href="mycourseinfos" target="_son">授课信息</a>
							</li>
							<li>
								<a href="mycourseplandetail" target="_son">教学计划</a>
							</li>
							<li>
								<a href="stucourseinfo" target="_son">学生选课信息</a>
							</li>
							<li>
								<a href="initmyteaclassinfo" target="_son">教师课表</a>
							</li>
							
						</ul>
					</li>
					
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">课题管理</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="searchtask" target="_son">课题选择</a>
							</li>
							<li>
								<a href="searchmytask" target="_son">查看我的课题</a>
							</li>
							<li>
								<a href="uploadmytaskfile" target="_son">提交课题文件</a>
							</li>
						</ul>
					</li>
					<li class="nav_top">
						<a class="nav_top_link">
							<span class="down">今日安排</span>
						</a>
						
						<ul class="sub">
							<li>
								<a href="lookmycouse" target="_son">今日课程</a>
							</li>
							<li>
								<a href="lookmynotice" target="_son">今日信件</a>
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
		<script type="text/javascript">
			function subtab(param) { 
				$("#subtab").text(param);
			}
		</script>
	</body>
</html>
