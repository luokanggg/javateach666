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
<title>个人信息修改</title>
</head>
<body>
	<!--选项卡-->
	<div class="tab">
		<p class="location">
			<em>当前位置 --
				<span id="dqwz">通知公告</span>
			</em>
		</p>
	</div>
	<!--选项卡-->
	<div id="bod">
		<table width="100%" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		    <td width="12%" bgcolor="#FFFFFF">学号：</td>
		    <td width="12%" bgcolor="#FFFFFF">{{ StuInfo.stuno }}</td>
		    <td width="12%" bgcolor="#FFFFFF">姓名：</td>
		    <td width="12%" bgcolor="#FFFFFF">{{ StuInfo.stuname }}</td>
		    <td width="12%" bgcolor="#FFFFFF">性别</td>
		    <td width="12%" bgcolor="#FFFFFF">{{ StuInfo.stusex }}</td>
		    <td width="28%" rowspan="3" bgcolor="#FFFFFF"><img width="150px" length="150px" v-bind:src="StuInfo.stuimage"></td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF">政治面貌：</td>
		    <td bgcolor="#FFFFFF">{{ StuInfo.political }}</td>
		    <td bgcolor="#FFFFFF">名族：</td>
		    <td bgcolor="#FFFFFF">{{ StuInfo.nation }}</td>
		    <td bgcolor="#FFFFFF">年龄：</td>
		    <td bgcolor="#FFFFFF">{{ StuInfo.stuage }}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF">学级：</td>
		    <td bgcolor="#FFFFFF">{{ StuInfo.classyear }}</td>
		    <td bgcolor="#FFFFFF">学院：</td>
		    <td bgcolor="#FFFFFF">{{ StuInfo.college }}</td>
		    <td bgcolor="#FFFFFF">班级：</td>
		    <td bgcolor="#FFFFFF">{{ StuInfo.classname }}</td>
		  </tr>
		  <tr>
		    <td bgcolor="#FFFFFF">专业：</td>
		    <td bgcolor="#FFFFFF">{{ StuInfo.major }}</td>
		    <td bgcolor="#FFFFFF">电话：</td>
		    <td bgcolor="#FFFFFF"><input type="text" v-model="StuInfo.stuphone" /></td>
		    <td colspan="2" bgcolor="#FFFFFF"></td>
		    <td bgcolor="#FFFFFF">
		    	<form id="fileform" action="updatestuinfoimg" method="post" enctype="multipart/form-data">
		    		<input type="file" id="file" name="file" />
		    		<!-- <input type="text" name="file" value="abc" /> -->
		    		<%-- <input type="hidden" name="${ _csrf.parameterName}" value="${ _csrf.token}" /> --%>
		    		<!-- <input type="submit" value="修改"> -->
		    	</form>
		    </td>
		  </tr>
		  <tr>
		    <td colspan="7" bgcolor="#FFFFFF">
		    	<button id="submits" v-on:click="sibmits">提交</button>
		    </td>
		  </tr>
		</table>
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
						var file = $("#file").val();
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