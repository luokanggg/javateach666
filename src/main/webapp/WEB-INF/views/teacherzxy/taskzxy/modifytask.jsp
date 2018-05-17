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
<title>课题修改</title>
</head>
<style>

.tab{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:458px;
	width:1320px;
}
.tab1{
	border:0px solid #99CCFF;
	
	height:455px;
	width:290px;
	margin-top:2px;
	float:left;
}
.tab2{
	border:0px solid blue;
	border-radius:5px;
	height:450px;
	width:960px;
	margin-top:2px;
	margin-left:17px;
	float:left;
}
.tab3{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:23px;
	width:1180px;
	background: #99CCFF;
	line-height:22px;
	margin-left:-255px;	
}
.tab4{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:23px;
	width:650px;
	
	line-height:22px;
	margin-top: 10px;
}
#wei{
	font-weight: bolder;
	font-size: 12px;
}
#submits{
	width:70px; height:30px;
	border-radius: 4px; 
	background:#c9dffd;
	
	 text-align:center; 
	 color:#0a0af5;
	 font-size:14px;
	 font-weight: bolder;
}
#cc0{
	border:1px solid blue;
	margin-top:40px;
	height:100px;
	width:290px;

	margin-left: auto;
margin-right: auto;
}



</style>

<body>

	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
			<div class="tab">
			<div class="tab1">
				<!-- <div id="cc0"><marquee direction="right"><span style="color:red;font-weight:bolder;font-family: '微软雅黑'" >?</span>&nbsp;问号是开启任何一门科学的钥匙。——巴甫洛夫</marquee></div> -->
				 
			</div>
			<div class="tab2">
				<div class="tab3">
					<span color="#CCC" id="wei">&nbsp;&nbsp;课题管理/课题修改</span>
					
				</div>
	
		<form name="form1" action="modifytaskmessage" method="post" enctype="multipart/form-data" >
		<table  width="70%" border="0" style="margin-left:6px;margin-top:13px; border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		  	<td colspan="2" height="17px"> <input type="hidden" id="id" name="id"   value="${task.id}"></td></td>
		   
		   
		  </tr>
		  <tr><td colspan="2" height="17px"></td></tr>
		  <tr>
		  	<td width="100px" height="47px" style="border-right-style:none;" bgcolor="#FFFFFF" align="center" valign="middle"><font style="font-size:13px">课题名称</font></td>
		    <td  bgcolor="#FFFFFF" align="left" style="border-left-style:none;" valign="middle">
		    <input readonly style="width:450px;height:30px;" type="text" id="taskname" name="taskname" class="easyui-textbox"  value="${task.taskname}"></td>
		  </tr>
		  <tr><td colspan="2" height="17px"></td></tr>
		  <tr>
		  	<td width="100px" height="47px"style="border-right-style:none;" bgcolor="#FFFFFF" align="center" valign="middle"><font style="font-size:13px">课题类型</font></td>
		    <td  bgcolor="#FFFFFF" align="left" style="border-left-style:none;" valign="middle">
		    <input readonly style="width:450px;height:30px;" type="text" id="tasktype" name="tasktype" class="easyui-textbox"  value="${task.tasktype}"></td>
		  </tr>
		   <tr><td colspan="2" height="17px"></td></tr>
		  <tr>
		    <td  width="100px" height="47px" bgcolor="#FFFFFF"  style="border-right-style:none" align="center" valign="middle"><font style="font-size:13px">课题内容</font><font style="color:red">&nbsp;*</font></td>
		    <td   bgcolor="#FFFFFF" style="border-left-style:none" align="left" valign="middle">
		   <!--  <textarea id="statement" rows="3" cols="48" name="statement"></textarea> -->
		    <input id="taskcontent" style="width:450px;height:45px" class="easyui-textbox" name="taskcontent" data-options="multiline:true" value="${task.taskcontent }"/>
		    </td>
		  </tr>
		   <tr><td colspan="2" height="17px"></td></tr>
		  <tr>
		  <td></td>
		  <td  height="47px" bgcolor="#FFFFFF" align="left" valign="middle">
		    	<!-- <input style="width:90px;"  type="submit"  id="submits" value="提&nbsp;&nbsp;&nbsp;&nbsp;交" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"> -->
		    	<button id="submits" onclick="return check()" iconCls="icon-ok" class="easyui-linkbutton"><font style="font-weight: bolder;font-size: 15px">提&nbsp;交</font></button>
		    	&nbsp;&nbsp;&nbsp;&nbsp;
		    	<a href="publishtask"  iconCls="icon-back" class="easyui-linkbutton" ><font style="font-weight: bolder;font-size: 15px">返&nbsp;回</font></a>
		   </td>
		   </tr>
		 </table>
		 </form>
		 </div>
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script>
	
	$("#submits").click(function(){
		var taskname=$("#taskname").val();
		var tasktype=$("#tasktype").val();
		var taskcontent=$("#taskcontent").val();
		var id=$("#id").val();
		if(title==''||title==null){
			$.messager.alert("警告",'文件的标题不能为空',"warning");
        	return false;
		}
		if(tasktype==''||tasktype==null){
			$.messager.alert("警告",'课题的类型不能为空',"warning");
        	return false;
		}
		if(taskcontent==''||taskcontent==null){
			$.messager.alert("警告",'课题的内容不能为空',"warning");
        	return false;
		}
		
	});
		

	</script>
</body>
</html>