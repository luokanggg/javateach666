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
	width:300px;
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
	margin-left:-280px;
}

#wei{
	font-weight: bolder;
	font-size: 12px;
}
#cc{

border-radius:5px;
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
#btn{
	border:1px solid;
	margin-top: -50px;
	margin-left:120px;
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
				
			</div>&nbsp;&nbsp; 
			<div class="tab2">
				<div class="tab3">
					<span color="#CCC" id="wei">&nbsp;&nbsp;通知管理/发布通知</span>
					
				
				</div>
				
		<table width="93%" height="400px" border="0" style="margin-left:6px;margin-top:13px; border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		  	<td width="100px" height="32px" style="border-right-style:none;" bgcolor="#FFFFFF" align="left" valign="middle"><font style="font-size:13px">通知标题</font><font style="color:red">&nbsp;*</font></td>
		    <td  bgcolor="#FFFFFF" align="left" style="border-left-style:none;" valign="middle">
		    <input style="width:420px;height:28px" type="text" id="nottitle" class="easyui-textbox"  data-options="prompt:'Enter a notice title...'"  >
		    <span class="right"><a href="lookNoReadNotice" style="height:23px" class="easyui-linkbutton" data-options="iconCls:'icon-help'"><font >您有${nolook }条通知，待查看</font></a></span>	</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" height="32px" style="border-right-style:none" align="left" valign="middle"><font style="font-size:13px">通知类型</font><font style="color:red">&nbsp;*</font></td>
		    <td  bgcolor="#FFFFFF" align="left" style="border-left-style:none" valign="middle">
		     <select id="nottypt" style="width:110px;height:27px;display: block" >
				<option  value="1" >&nbsp;&nbsp;老师to学生</option>
				<option  value="4" >&nbsp;&nbsp;老师to老师</option>
		    </select>
		    &nbsp;&nbsp;&nbsp;
		    <button id="btn" onclick="noticeClass();"  height="20px" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"><span style="font-size:14px">通知班级</span></button>
			</td>
		  </tr>
		  <tr>
		    <td  bgcolor="#FFFFFF" height="32px" style="border-right-style:none" align="left" valign="middle"><font style="font-size:13px">通知人ID</font><font style="color:red">&nbsp;*</font></td>
		    <td  bgcolor="#FFFFFF" style="border-left-style:none" align="left" valign="middle">
		    <input class="easyui-textbox" style="width:420px;height:28px" data-options="prompt:'Enter a notice ID...'"   type="text" id="tonotid"></td>
		  </tr>
		  <tr>
		    <td  height="32px" bgcolor="#FFFFFF"  style="border-right-style:none" align="left" valign="middle"><font style="font-size:13px">通知内容</font><font style="color:red">&nbsp;*</font></td>
		    <td   bgcolor="#FFFFFF" style="border-left-style:none" align="left" valign="middle">
		   <!--  <textarea id="notcontent" rows="3" cols="48" ></textarea> -->
		   <input style="width:420px;height:28px" class="easyui-textbox" id="notcontent" data-options="multiline:true,prompt:'Enter a notice content...'"/>
		   </td>
		  </tr>
		  <tr>
		    <td  bgcolor="#FFFFFF" style="border-right-style:none" align="left" height="32px" valign="middle"><font style="font-size:13px">过期时间</font><font style="color:red">&nbsp;*</font></td>
		    <td  bgcolor="#FFFFFF" style="border-left-style:none" align="left" valign="middle">
		    <input type="date" id="endtime" style="width:200px;height:25px;border-radius:3px;border:1px solid #c9dffd;" /></td>
		  </tr>
		  <tr>
		   <td  height="32px" bgcolor="#FFFFFF" style="border-right-style:none" align="left" valign="middle"><font style="font-size:13px">链接页面&nbsp;</font></td>
		    <td  bgcolor="#FFFFFF" style="border-left-style:none" align="left" valign="middle">
		   
		   <input data-options="prompt:'Enter a notice url...'" style="width:420px;height:28px" class="easyui-textbox" data-options="multiline:true" id="noturl"/>
		    </td>
		  </tr>
		   <tr>
		    <td  height="32px" bgcolor="#FFFFFF" style="border-right-style:none" align="left" valign="middle"></td>
		    <td  height="32px"  bgcolor="#FFFFFF" align="left" valign="middle">
		    	<button height="20px" width="90px" id="submits" v-on:click="sibmits" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"><span style="font-size:14px">提&nbsp;交</span></button>
		    </td>
		  </tr>
		</table>
		</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	
	<script type="text/javascript">
	var type="simple";
	var tonotid="";
	$(function(){
		
		$("#submits").click(function(){
			
			var nottypt="";
			nottypt=$("#nottypt option:selected").val();
			// alert(type);
			var nottitle="";
			nottitle=$("#nottitle").val();
			
			if(""==nottitle){
				$.messager.alert('警告','标题不能为空,请填写标题！','warning');
					
				$("#nottitle").focus();
				return;
			}
			
			
			tonotid=$("#tonotid").val();
			if(""==tonotid){
				$.messager.alert('警告','通知人ID不能为空，请填写通知人ID','warning');
				$("#tonotid").focus();
				return;
			}
			var endtime=$("#endtime").val();
			if(""==endtime){
				//alert("为空");
				endtime="9999-01-01";
			}else
				{
				//alert(endtime);
				}
			var notcontent="";
			notcontent=$("#notcontent").val();
			if(notcontent==""){
				$.messager.alert('警告','通知的内容不能为空，请填写通知内容','warning');
				$("#notcontent").focus();
				return;
			}
			var noturl="";
			noturl=$("#noturl").val();
			 $.extend($.fn.validatebox.defaults.rules, {    
			    minLength: {    
			        validator: function(value, param){    
			            return value.length >= param[0];    
			        },    
			        message: 'Please enter at least {0} characters.'   
			    }    
			});
			//alert(notcontent+"  "+endtime+"  "+tonotid+"  "+nottypt+"  "+nottitle);
		 	data={
					"notcontent":notcontent,
					"endtime":endtime,
					"tonotid":tonotid,
					"nottypt":nottypt,
					"nottitle":nottitle,
					"noturl":noturl,
					"type":type
					}; 
			//alert(notcontent+"  "+endtime+"  "+tonotid+"  "+nottypt+"  "+nottitle);
			$.ajax({
				
				url:'publishnotice',
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
	
	
	function noticeClass(){
		type="class";
		$("#nottypt").find("option").remove();
		$("#nottypt").html("<c:forEach items='${classlist}' var='classlist'><option  value='${classlist.classid }' >${classlist.classname}</option></c:forEach>");
		 tonotid="0";
	}
	</script>
</body>
</html>