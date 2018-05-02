<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%
	response.setHeader("X-Frame-Options", "SAMEORIGIN");
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header"  content="${_csrf.headerName}"/> --%>
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/lktable.css"/>
<title>个人信息修改</title>
<style type="text/css">
	/*#classinfo-datagrid tr{height:40px;}*/
	.filebox{float: left;margin-left: 10px;}
	.textbox-button{right:0;}
/* .td{
	text-align:center;
	vertical-align:middle
} */
.txt01{font:Verdana, Geneva, sans-serif,宋体;padding:3px 2px 2px 2px; border-width:1px; border-color:#ddd;  color:#000;}
</style>
</head>
<body>
	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
		<div class="tab">
			<div class="tab1">
			<!--  <div id="cc" class="easyui-calendar" data-options="formatter:formatDay" style="width:290px;height:230px;"></div>  -->
			
			</div>
			<div class="tab2">
				<div class="tab3">
					<span color="#CCC" id="wei">&nbsp;&nbsp;我的信息/个人信息修改</span>
				</div>
				<table width="90%" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
				  <tr>
				    <td width="12%" align="center" valign="middle" bgcolor="#FFFFFF">学号：</td>
				    <td width="12%" align="center" valign="middle" bgcolor="#FFFFFF">{{ StuInfo.stuno }}</td>
				    <td width="12%" align="center" valign="middle" bgcolor="#FFFFFF">姓名：</td>
				    <td width="12%" align="center" valign="middle" bgcolor="#FFFFFF">{{ StuInfo.stuname }}</td>
				    <td width="12%" align="center" valign="middle" bgcolor="#FFFFFF">性别</td>
				    <td width="12%" align="center" valign="middle" bgcolor="#FFFFFF">{{ StuInfo.stusex }}</td>
				    <td width="28%" align="center" valign="middle" rowspan="3" bgcolor="#FFFFFF"><img width="150px" length="150px" v-bind:src="StuInfo.stuimage"></td>
				  </tr>
				  <tr>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">政治面貌：</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">{{ StuInfo.political }}</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">名族：</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">{{ StuInfo.nation }}</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">年龄：</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">{{ StuInfo.stuage }}</td>
				  </tr>
				  <tr>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">学级：</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">{{ StuInfo.classyear }}</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">学院：</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">{{ StuInfo.college }}</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">班级：</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">{{ StuInfo.classname }}</td>
				  </tr>
				  <tr>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">专业：</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">{{ StuInfo.major }}</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">电话：</td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle"><input type="text" v-model="StuInfo.stuphone" /></td>
				    <td colspan="2" bgcolor="#FFFFFF" align="center" valign="middle"></td>
				    <td bgcolor="#FFFFFF" align="center" valign="middle">
				    	<form id="fileform" action="updatestuinfoimg" method="post" enctype="multipart/form-data" style="display: inline">
				    		<input type="text" id="file" name="file" class="easyui-filebox" />
				    		<!-- <input type="text" name="file" value="abc" /> -->
				    		<%-- <input type="hidden" name="${ _csrf.parameterName}" value="${ _csrf.token}" /> --%>
				    		<!-- <input type="submit" value="修改"> -->
				    	</form>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="7" bgcolor="#FFFFFF" align="center" valign="middle">
				    	<button class="easyui-linkbutton" iconCls="icon-ok" id="submits" v-on:click="sibmits">提交</button>
				    </td>
				  </tr>
				</table>
			</div>	
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script type="text/javascript">
		$(function(){			
			//alert("123456");
			
			/* var header = $("meta[name='_csrf_header']").attr("content");
			var token =$("meta[name='_csrf']").attr("content");  */
			
			var man = new Vue({
				el:'#bod',
				data:{
					StuInfo:''
					
				},
				methods:{
					getDate:function(){
						//alert("getDate()");
						$.ajax({
							type:'post',
							url:'initstuinfo',
							contentType:"application/json",    //必须配置
							/* beforeSend : function(xhr) {
				                xhr.setRequestHeader(header, token);
				            }, */
							//data:JSON.stringify(param),//转换成字符串，客户端作为生产者
							success:function(result){
								//alert(result.stuimage);
								man.StuInfo = result;
							} 
						});
						//alert("getDate()2");
					},
					sibmits:function(){
						var testphone = /^1[3|4|5|7|8][0-9]{9}$/ ;
						if(! testphone.test(man.StuInfo.stuphone)){
							alert("请输入正确的手机号码！");
							return false;
						}

						var data = {"stuphone":man.StuInfo.stuphone,"stuno":man.StuInfo.stuno};
						//alert(JSON.stringify(data));
						$.ajax({
							type:'post',
							url:'updatestuinfo',
							contentType:"application/json",    //必须配置
							/* beforeSend : function(xhr) {
				                xhr.setRequestHeader(header, token);
				            }, */
							data:JSON.stringify(data),//转换成字符串，客户端作为生产者
							success:function(result){
								man.StuInfo = result;
								alert(man.StuInfo.responseDesc);
							} 
						});
						var file = $("#filebox_file_id_1").val();
						if(file != null && file != ""){
							//alert("上传图片");
							$('form').submit();
						}
					},
				}
			});
			
			man.getDate();
		})
	</script>
</body>
</html>