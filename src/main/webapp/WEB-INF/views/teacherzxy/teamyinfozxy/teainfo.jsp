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
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/easyui.css"/>
 
<title>个人信息</title>
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
	width:200px;
	margin-top:2px;
	float:left;
}
.tab2{
	border:0px solid blue;
	border-radius:5px;
	height:450px;
	width:960px;
	margin-top:2px;
	margin-left:10px;
	float:left;
}
.tab3{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:23px;
	width:1280px;
	background: #99CCFF;
	line-height:22px;
		margin-left:-200px;				
}

#wei{
	font-weight: bolder;
	font-size: 12px;
}
.right{
	float: right;
	position:relative;
	margin-top: -2px;
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
.txt01{font:Verdana, Geneva, sans-serif,宋体;padding:3px 2px 2px 2px; border-width:1px; border-color:#ddd;  color:#000;}
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
<body class="easyui-layout">
	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
		<div class="tab">
			<div class="tab1">
			<!--  <div id="cc" class="easyui-calendar" data-options="formatter:formatDay" style="width:290px;height:230px;"></div>  -->
			
			</div>
			<div class="tab2">
				<div class="tab3">
					<span color="#CCC" id="wei">&nbsp;&nbsp;个人设置/个人资料</span>
					<span class="right"><a id="editpass" href="javascript:;" style="height:27px" class="easyui-linkbutton" data-options="iconCls:'icon-edit'"><font >重置密码</font></a></span>
				</div>
	<table  width="90%" height="400px" border="1" style="z-index:100000;margin-left:6px;margin-top:13px; border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		  	<td width="10%" bgcolor="#FFFFFF" align="center" valign="middle">用&nbsp;户&nbsp;名</td>
		    <td width="20%" bgcolor="#FFFFFF" align="center" valign="middle">${TeaInfo.teano }</td>
		    <td width="10%" bgcolor="#FFFFFF" rowspan="2" align="center" valign="middle" >头&nbsp;&nbsp;&nbsp;&nbsp;像</td>
		    <td width="20%"  bgcolor="#FFFFFF" rowspan="2" align="center" valign="middle">
		    <img width="40px" height="40px" src="${basePath}/static/file/${TeaInfo.teaimage}"></td>
		  </tr>
		  <tr>
		    <td width="10%" bgcolor="#FFFFFF" align="center" valign="middle">姓&nbsp;&nbsp;&nbsp;&nbsp;名</td>
		    <td width="20%" bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.teaname}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >入职时间</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >
		    <%-- ${TeaInfo.joined_date} --%>${Joined_date }
		    </td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >职&nbsp;&nbsp;&nbsp;&nbsp;称</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.professional}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >年&nbsp;&nbsp;&nbsp;&nbsp;龄</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.teaage}</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.teasex}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >专&nbsp;&nbsp;&nbsp;&nbsp;业</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.major}</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >学&nbsp;&nbsp;&nbsp;&nbsp;院</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.teacollage}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >民&nbsp;&nbsp;&nbsp;&nbsp;族</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.teanation}</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >政治面貌</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.political}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >手&nbsp;机&nbsp;号</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.teaphone}</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >邮&nbsp;&nbsp;&nbsp;&nbsp;箱</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.email}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >个人说明</td>
		    <td bgcolor="#FFFFFF" height="70px" colspan="3" align="center" valign="middle" >${ TeaInfo.prosonal_statement}</td>
		  </tr>
		  <tr>
		    <td colspan="4" bgcolor="#FFFFFF" align="center" valign="middle">
		    	
		    	<a href="modifyteainfo" target="_son" iconCls="icon-edit" class="easyui-linkbutton" ><font style="font-weight: bolder;font-size: 15px">编&nbsp;辑</font></a>
		    	
		    </td>
		  </tr>
		</table>
		
		 <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
		
		
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
<script>

 
 
//设置登录窗口
function openPwd() {
    $('#w').window({
        title: '修改密码',
        width: 300,
        modal: true,
        shadow: true,
        closed: true,
        height: 160,
        resizable:false
    });
}
//关闭登录窗口
function closePwd() {
    $('#w').window('close');
}

//修改密码
function serverLogin() {
    var newpass = $('#txtNewPass').val();
    var rePass = $('#txtRePass').val();

    if (newpass == '') {
       // alert('请输入密码！');
        $.messager.alert('提示','请输入密码！','warning');
        return false;
    }
    if (rePass == '') {
      
        $.messager.alert('提示','请在一次输入密码！','warning');
        return false;
    }

    if (newpass!= rePass) {
       
        $.messager.alert('提示','两次密码不一至！请重新输入！','warning');
        $('#txtNewPass').val("");
        $('#txtRePass').val("");
        return false;
    }

   
    $.ajax({
		url:'updatePassword?pass='+newpass,
		type:'post',
		//data:pass,
		//dataType:"json",
		success:function(result){
			if(result.info=="0000"){
				$.messager.alert('提示','密码更新成功！','info'); 
				closePwd();
			}else{
				$.messager.alert('提示','密码更新失败！','info');
				$('#txtNewPass').val("");
		        $('#txtRePass').val("");
			}
		}
	});
}

$(function() {

    openPwd();

    $('#editpass').click(function() {
        $('#w').window('open');
    });

    $('#btnEp').click(function() {
        serverLogin();
    })

	$('#btnCancel').click(function(){closePwd();})

});



</script>
</body>
</html>