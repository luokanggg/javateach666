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
<title>通知管理</title>
</head>
<style>
select {
width:120px;	
height:24px;
font-size:13px;	
border-radius:3px;
border:1px solid #c9dffd;
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
.right{
	float: right;
	position:relative;
	
}
.tab{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:458px;
	width:1320px;
}

.tab1{
	border:0px solid #99CCFF;
	
	height:455px;
	width:180px;
	margin-top:2px;
	float:left;
}
.tab2{
	border:0px solid blue;
	border-radius:5px;
	height:450px;
	width:960px;
	margin-top:2px;
	margin-left:14px;
	float:left;
}
.tab3{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:23px;
	width:1200px;
	background: #99CCFF;
	line-height:22px;
	margin-left:-170px;
}

#wei{
	font-weight: bolder;
	font-size: 12px;
}
#cc{
margin: auto;
border-radius:7px;
}
.calendar-day {
 
    cursor: pointer;
    border: 1px solid #fff;
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}

.calendar-title {
    text-align: center;
    height: 30px;
}
.calendar-title span {
    position: relative;
    display: inline-block;
    top: 2px;
    padding: 2 3px;
    height: 18px;
    line-height: 18px;
    font-size: 14px;
    cursor: pointer;
    font-weight:bolder;
    -moz-border-radius: 5px 5px 5px 5px;
    -webkit-border-radius: 5px 5px 5px 5px;
    border-radius: 5px 5px 5px 5px;
}
.calendar-header {
    position: relative;
    background:#C8DEFC;
    font-size: 14px;
    height: 30px;
}
.calendar-saturday,.calendar-sunday {
    color: #3399cc;
    font-weight:bold;
    line-height:16px;
	background-position:2px center;
}
</style>
<style scoped="scoped">
		.md{
			height:16px;
			line-height:16px;
			background-position:2px center;
			text-align:right;
			font-weight:bold;
			padding:0 2px;
			color:red;
		}
	</style>
<body>

	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
		<div class="tab">
			<div class="tab1">
				<!-- <div id="cc" class="easyui-calendar" data-options="formatter:formatDay" style="width:290px;height:230px;"></div>  -->
			</div>
			<div class="tab2">
				<div class="tab3">
					<span color="#CCC" id="wei">&nbsp;&nbsp;通知管理/编辑通知</span>
					
				
				</div>
		<table width="93%" height="300px" border="0" style="margin-left:6px;margin-top:13px; border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		    <td colspan="4"  height="10px"  bgcolor="#FFFFFF" style="border-left-style:none" align="left" valign="middle"><input type="hidden" id="id" value="${resnotice.id}"/></td>
		  </tr>
		  <tr>
		    <td  width="80px" height="35px"  style="border-right-style:none;" bgcolor="#FFFFFF" align="right" valign="middle"><font style="font-size:13px">通&nbsp;&nbsp;知&nbsp;&nbsp;人&nbsp;</font></td>
		    <td  bgcolor="#FFFFFF" width="170px" style="border-left-style:none" align="left" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${resnotice.notname }</td>
		    <td style="border-right-style:none;" width="70px" height="32px"  bgcolor="#FFFFFF" align="left" valign="middle"><font style="font-size:13px">被通知人&nbsp;&nbsp;</font></td>
		    <td  bgcolor="#FFFFFF" width="170px" style="border-left-style:none" align="left" valign="middle">${resnotice.tonotname }</td>
		  </tr>
		 
		  <tr>
		 	 <td width="80px" bgcolor="#FFFFFF" height="35px" style="border-right-style:none" align="right" valign="middle"><font style="font-size:13px">通知类型&nbsp;&nbsp;</font></td>
		    <td  width="170px" bgcolor="#FFFFFF" style="border-left-style:none" align="left"  valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${resnotice.nottype == 1}">
			老师通知学生
			</c:if>
			<c:if test="${resnotice.nottype == 4}">
			老师通知老师
			</c:if>
			</td>
		  <td  width="70px"; bgcolor="#FFFFFF" style="border-right-style:none" align="left" height="35px" valign="middle"><font style="font-size:13px">过期时间</font><font style="color:red">&nbsp;*</font></td>
		    <td width="170px" bgcolor="#FFFFFF" style="border-left-style:none" align="left" valign="middle">
		    <input type="date" id="endtime" style="width:220px;height:25px;border-radius:3px;border:1px solid #c9dffd;" />
		    </td>
		  </tr>
		 
		 
		  <tr>
		  <td width="80px" height="35px" style="border-right-style:none;" bgcolor="#FFFFFF" align="right" valign="middle"><font style="font-size:13px">通知标题</font><font style="color:red">&nbsp;*</font></td>
		    <td  width="170px" bgcolor="#FFFFFF" align="center" style="border-left-style:none;" valign="middle">
		     <input style="width:220px;height:28px" type="text" id="nottitle" class="easyui-textbox"  value="${resnotice.nottitle }"  >
		     </td>
		      <td width="70px" height="35px" bgcolor="#FFFFFF" style="border-right-style:none" align="left" valign="middle"><font style="font-size:13px">链接页面&nbsp;</font><font style="color:red">&nbsp;*</font></td>
		    <td  bgcolor="#FFFFFF" style="border-left-style:none" align="left" valign="middle">
		  
		   <input value="${ resnotice.noturl}" style="width:220px;height:28px" class="easyui-textbox" data-options="multiline:true" id="noturl"/>
		    
		   </td>
		    
		    
		  </tr>
		 
		  <tr>
		  <td  width="80px" height="38px" style="border-right-style:none;" bgcolor="#FFFFFF" align="right" valign="middle"><font style="font-size:13px">通知内容</font><font style="color:red">&nbsp;*</font></td>
		    <td  colspan="3" bgcolor="#FFFFFF"  style="border-left-style:none" align="left" valign="middle">
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   <input style="width:620px;height:38px" class="easyui-textbox" id="notcontent" value="${ resnotice.notcontent}"/>
		    </td>
		  </tr>
		  <tr><td colspan="4" height="3px"></td></tr>
		  <tr>
		    <td  align="center" colspan="4" height="35px" bgcolor="#FFFFFF"  valign="middle">
		    	
		    	<a id="submitbtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"><span style="font-size:14px">提&nbsp;交</span></a> 
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	<a id="fankuibtn" href="noticeinfolist" class="easyui-linkbutton" data-options="iconCls:'icon-back'"><span style="font-size:14px">返&nbsp;回</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		    </td>
		  </tr>
		</table>
		</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script type="text/javascript">
	
	 var d1 = Math.floor((Math.random()*30)+1);
	 var d2 = Math.floor((Math.random()*30)+1);

	 function formatDay(date){
	 	var m = date.getMonth()+1;
	 	var d = date.getDate();

	 	var opts = $(this).calendar('options');
	 	if (opts.month == m && d == 1){
	 		return '<div class="icon-ok md">' + d + '</div>';
	 	} else if (opts.month == m && d == 30){
	 		//return '<div class="icon-search md">' + d + '</div>';
	 	}else if (opts.month == m && d == 31){
	 		return '<div class="icon-search md">' + d + '</div>';
	 	}
	 	return d;
	 }
	
	$(function(){    
    	$('#submitbtn').bind('click', function(){   
    		var id=$("#id").val(); 
        	var nottitle="";
			nottitle=$("#nottitle").val();
			if(""==nottitle){
				$.messager.alert('警告','标题不能为空,请填写标题！','warning');
				$("#nottitle").focus();
				return;
			} 
			var endtime=$("#endtime").val();
			if(""==endtime){
				endtime="9999-01-01";
			}  
			var noturl="";
			noturl=$("#noturl").val(); 
			var notcontent="";
			notcontent=$("#notcontent").val();
			if(notcontent==""){
				$.messager.alert('警告','通知的内容不能为空，请填写通知内容','warning');
			}
			data={
					"notcontent":notcontent,
					"endtime":endtime,
					"id":id,
					"nottitle":nottitle,
					"noturl":noturl
				};
				//alert(id+noturl+notcontent+nottitle+endtime);
				$.ajax({
				
				url:'updatenotice',
				type:'post',
				dataType:"json",
				data:data,
				success:function(result){
					//alert(result.mess);
					$.messager.alert('提示',result.mess,'info');
				}
			});
    	});    
	}); 
	</script>
</body>
</html>