<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("X-Frame-Options", "SAMEORIGIN");
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>新闻主页</title>
		<%@include file="/common/easyui.jspf"%>
		<link rel="stylesheet" type="text/css" href="${basePath}/static/css/wel.css"/>
	</head>

	<body class="welbody">
		<div class="main_box">
			<div class="mid_box">
				<!-- From内容 -->
				<span class="formbox">
						<!-- 根据主体内容选择具体的样式默认为frombox,请参考样式说明Readme.txt -->
					<div class="portallet" style="WIDTH:98%">
						<table class="data" cellspacing="0" border="0" width="100%">
							<tr>
								<td>
									<div class="movebox">
										<h2>
											<span class='titilebox'>
												相关新闻
											</span>
										</h2>
										<div class="movebox_con">
											<table class="datelist" cellpadding="3" width="100%">
												<tr>
													<td>公告标题</td>
													<td>发布单位</td>
													<td>发布时间</td>
													<td>有效期限</td>
												</tr>
												<tr>
													<td>
														<a href='#' onclick="">关于学生登录现代教学管理信息系统初始密码更改的通知</a>
													</td>
													<td>教务处</td>
													<td>2007-09-17 04:43:31</td>
													<td>2020-03-01</td>
												</tr>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<input name="txtblqk" type="text" id="txtblqk" style="DISPLAY:none" />
					</div>

				</span>
			<div class="footbox">
				<em class="footbox_con">
					<span class="pagination">
						<p class="pageleft">
							<!-- 全选反选位置 -->
						</p>
						<p class="pageright">
							<!-- 分页位置 -->
						</p>
					</span>
					<span class="footbutton">
						<!-- 底部按钮位置 --></span>
				</em>
			</div>
		</div>
		</div>

	</body>

</html>