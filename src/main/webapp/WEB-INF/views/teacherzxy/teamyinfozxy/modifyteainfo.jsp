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
<title>个人信息</title>
</head>
<style>
#teano{

border:none;
}
.tab{
	border:0px solid #99CCFF;
	border-radius:5px;
	height:458px;
	width:1320px;
}
.tab1{
	border:0px solid red;
	border-radius:5px;
	height:450px;
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
	border:1px solid #99CCFF;
	border-radius:5px;
	height:23px;
	width:960px;
	background: #99CCFF;
	line-height:22px;
	width:1280px;
	margin-left:-200px;
}

#wei{
	font-weight: bolder;
	font-size: 12px;
}

</style>
<body>

	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
			<div class="tab">
			<div class="tab1">

			</div>
			<div class="tab2">
				<div class="tab3">
					<span color="#CCC" id="wei">&nbsp;&nbsp;个人设置/编辑资料</span>
				</div>
		
		 <table width="90%" height="400px" border="1" style="margin-left:6px;margin-top:13px; border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		  	<td width="10%" bgcolor="#FFFFFF" align="center" valign="middle">用&nbsp;户&nbsp;名</td>
		    <td width="20%" bgcolor="#FFFFFF" align="center" valign="middle"><input readonly value="${TeaInfo.teano }"  id="teano"></td>
		    <td width="10%" bgcolor="#FFFFFF" rowspan="2" align="center" valign="middle" >编辑头像</td>
		    <td width="20%"  bgcolor="#FFFFFF" rowspan="2" align="center" valign="middle">
         <form id="fileform" action="updateteainfoimg" method="post" enctype="multipart/form-data">
        <input id="file" name="file" style="width:100px;height:30px;" class="easyui-filebox" data-options="buttonText:'选择文件'"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value="提交" class="easyui-linkbutton" style="width:50px;height:30px">
		</form>
          
          </td>
		  </tr>
		  <tr>
		    <td width="10%" bgcolor="#FFFFFF" align="center" valign="middle">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>
		    <td width="20%" bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.teaname}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >入职时间</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >
		  <%--  ${TeaInfo.joined_date} --%>
		    
		    ${Joined_date }
		    </td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >职&nbsp;&nbsp;&nbsp;&nbsp;称</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.professional}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >年&nbsp;&nbsp;&nbsp;&nbsp;龄<font style="color:red">*</font></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" ><input class="easyui-textbox"  type="text" value="${TeaInfo.teaage}" id="teaage" style="width:217px;height:28px"></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >性&nbsp;&nbsp;&nbsp;&nbsp;别<font style="color:red">*</font></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" ><input class="easyui-textbox"  type="text" value="${TeaInfo.teasex}" id="teasex" style="width:217px;height:28px"></td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >专&nbsp;&nbsp;&nbsp;&nbsp;业<font style="color:red">*</font></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" ><input class="easyui-textbox"  type="text" value="${TeaInfo.major}" id="teamajor" style="width:217px;height:28px"></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >学&nbsp;&nbsp;&nbsp;&nbsp;院<font style="color:red">*</font></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" ><input class="easyui-textbox"  type="text" value="${TeaInfo.teacollage}" id="teacollage" style="width:217px;height:28px"></td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >民&nbsp;&nbsp;&nbsp;&nbsp;族</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >${TeaInfo.teanation}</td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >政治面貌<font style="color:red">*</font></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" ><input class="easyui-textbox"  type="text" value="${TeaInfo.political}" id="teapolitical" style="width:75%;height:28px"></td>
		  </tr>
		  <tr>
		    <td style="heigth:10px"  bgcolor="#FFFFFF" align="center" valign="middle" >手&nbsp;机&nbsp;号<font style="color:red">*</font></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >
		    <input class="easyui-textbox"  type="text" value="${TeaInfo.teaphone}"  id="phone" style="width:75%;height:28px"></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >邮&nbsp;&nbsp;&nbsp;&nbsp;箱<font style="color:red">*</font></td>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >
		    <input class="easyui-textbox"  id="email" class="easyui-validatebox" style="width:75%;height:70%" data-options="required:true,validType:'email'" value="${TeaInfo.email}"/>
		   <%--  <input type="text" value="${TeaInfo.email}" id="email" style="width:75%;height:75%"></td> --%>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF" align="center" valign="middle" >个人说明<font style="color:red">*</font></td>
		    <td bgcolor="#FFFFFF" height="70px" colspan="3" align="center" valign="middle" ><input style="width:90%;height:70%" value="${ TeaInfo.prosonal_statement}" class="easyui-textbox" data-options="multiline:true"  id="teastatement" ></input></td>
		  </tr>
		  <tr>
		    <td colspan="4" bgcolor="#FFFFFF" align="center" valign="middle">
		    	<button id="submits" iconCls="icon-ok" class="easyui-linkbutton"><font style="font-weight: bolder;font-size: 15px">提&nbsp;交</font></button>
		    	&nbsp;&nbsp;&nbsp;&nbsp;
		    	<a href="teainfo"  iconCls="icon-back" class="easyui-linkbutton" ><font style="font-weight: bolder;font-size: 15px">返&nbsp;回</font></a>
		    </td>
		  </tr>
		</table> 
		</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script type="text/javascript">
	
		$(function(){

		
			
			$('#submits').click(function(){
				//var professional=$('#professional').val();
				var teaage=$('#teaage').val();
				var teasex=$('#teasex').val();
				var major=$('#major').val();
				var teacollage=$('#teacollage').val();
				var political=$('#teapolitical').val();
				var teaphone=$('#phone').val();
				var testphone = /^1[3|4|5|7|8][0-9]{9}$/ ;
				if(! testphone.test(teaphone)){
					$.messager.alert('警告','请输入正确的手机号！','warning');    
						
						$('#phone').val("");
						$("#phone").focus();
						return false;
				}
				var email=$('#email').val();
				var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); 
				if(! reg.test(email)){
					alert("请输入正确的邮箱！");
					$('#email').val("");
					$("#email").focus();
					return false;
			}
				var prosonal_statement=$('#teastatement').val();
				var major=$('#teamajor').val();
				var teano=$('#teano').val();
				var data={
						//"professional":professional,
						"teaage":teaage,
						"teasex":teasex,
						"major":major,
						"teacollage":teacollage,
						"political":political,
						"teaphone":teaphone,
						"email":email,
						"prosonal_statement":prosonal_statement,
						"teano":teano
					};
				$.ajax({
					url:'http://localhost:8089/javateach666/updateTeaInfo',
					type:'get',
					data:data,
					dataType:"json",
					success:function(result){
						if(result.info=="0000"){
							$.messager.alert('提示','更新成功！','info'); 
							//alert("更新成功");
						}else{
							$.messager.alert('提示','更新失败！','info');
						}
					}
				});
			});
			
		});
	</script>
</body>
</html>