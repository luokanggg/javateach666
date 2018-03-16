<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<%@include file="/common/easyui.jspf"%>
</head>
<body>
	helloword LKKKKK222！！！
	
	<div id="cc" class="easyui-layout" data-options="fit:true" style="width:600px;height:400px;">
			<div data-options="region:'north'" style="height:100px;">
				头部

			</div>
			<div data-options="region:'south'" style="height:100px;">
				底部
			</div>
			<!--<div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>-->
			<div data-options="region:'west'" style="width:16%">
				<div id="aa" class="easyui-accordion" data-options="fit:true">
					<c:forEach items="${menus }" var="menu">
						<div title="${menu.menuName }" id="${menu.menuUrl }" style="overflow:auto;padding:10px;">
							${menu.menuName }
						</div>
					</c:forEach>
				</div>
			</div> 
			<div data-options="region:'center'" style="padding-left:1px;">
				<div id="tt" class="easyui-tabs" data-options="fit:true">
					<div title="Tab1" style="padding:20px;display:none;">
						tab1
					</div>
				</div>
			</div>
		</div>
</body>
</html>