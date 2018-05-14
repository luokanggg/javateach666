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
<title>网站介绍管理</title>
</head>
<style>
</style>
<body>
<div>
    <div class="easyui-panel" title="网站介绍" style="width:400px;padding:30px 50px;">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="wtitle" label="网站标题:" labelPosition="top" value="${WebInfo.wtitle }" style="width:100%;height:52px">
        </div>
        <label>介绍内容</label>
        <div style="margin-bottom:20px">
            <textarea id="wcontent" rows="10" cols="40">${WebInfo.wcontent }</textarea>
        </div>
        
        <div>
            <a href="javascript:;" id="updateWebInfo" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">确认修改</a>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	
	$("#updateWebInfo").on("click", function(){
		var data = {
				"wtitle": $("#wtitle").val(),
				"wcontent": $("#wcontent").val()
				};
		$.ajax({
			url:'updatewebintroduce',
			type:'post',
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function(result){
				if(result == "OK"){
					$.messager.alert('提示','修改成功！','info'); 
				}else{
					$.messager.alert('提示','修改失败！','info'); 
				}
				
			}
		})
	})
	
})
</script>
</body>
</html>