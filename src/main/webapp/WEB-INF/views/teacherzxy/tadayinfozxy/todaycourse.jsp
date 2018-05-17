<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<title>今日课程</title>
</head>
<style>
td {     text-align:center; }
.right,.right1{
	float: right;
	position:relative;
	
}
</style>
<body>
<div>

	<center>
	<br>
	<table id="tab"  width="600px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
	
			<tr>
				<th colspan="4" style="height:34px">
				<span style="float:center;font-size: 15px;font-family: '微软雅黑'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;今&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;</span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="float:right;">${date }&nbsp;星期
				<c:if test="${weekday == '0'}">
					日
				</c:if>
				<c:if test="${weekday != '0'}">
					${weekday}
				</c:if>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</th>
			</tr>

			<tr style="height:32px">
				<th style="width:100px;" align="center">节数</th>
				<th style="width:100px;" align="center">课时</th>
				<th style="width:100px;" align="center">课程名</th>
				<th style="width:100px;" align="center">教室</th>
			</tr>
			<c:if test="${not empty todaycourse }">
			   <c:forEach items="${todaycourse }" var="tc">
				   <tr  style="height:32px">
				       <td>第${tc.coufhour }节</td><td>${tc.couhour }课时</td><td>${tc.couname }</td><td>${tc.couaddress }</td>
				   </tr>
			   </c:forEach>	
			 
			</c:if>
			<c:if test="${empty todaycourse }">
			<tr><td colspan="4" style="width:100px;height:35px" align="center"><span style="color:red;font-size:14px">今日没有课程</span></td></tr>
			</c:if>
			
	</table>
	
	</center>
	<script type="text/javascript">
	</script>
</body>
</html>