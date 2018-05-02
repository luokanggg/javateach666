<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/lktable.css"/>
<title>上传课程作业</title>
<style type="text/css">
	/* #classinfo-datagrid tr{height:40px;} */
	.filebox{float: left;margin-left: 10px;}
	.textbox-button{right:0;}
</style>
</head>
<body>
	<%-- ${requestScope.id } 上传课程作业 --%>
	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
		<div class="tab">
			<div class="tab1">
			<!--  <div id="cc" class="easyui-calendar" data-options="formatter:formatDay" style="width:290px;height:230px;"></div>  -->
			
			</div>
			<div class="tab2">
				<div class="tab3">
					<span color="#CCC" id="wei">&nbsp;&nbsp;我的课程/课程作业提交</span>
				</div>
				<form id="fileform" action="updateclasswork" method="post" enctype="multipart/form-data">
					<table width="100%" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
					  <tr>
					    <td width="12%" align="center" valign="middle">课程名称：</td>
					    <td width="12%" align="center" valign="middle">{{ couname }}</td>
					    <td width="12%" align="center" valign="middle">教师名称：</td>
					    <td width="12%" align="center" valign="middle">{{ teaname }}</td>
					  </tr>
					  <tr>
					  	<td colspan="4">
					  		作业提交记录：
					  	</td>
					  </tr>
					  <tr>
					  	<th colspan="2">文件名称</th>
					  	<th colspan="2">上传时间</th>
					  </tr>
					  <tr v-if="submits == ''">
					  	<td colspan="4">没有作业上传记录！</td>
					  </tr>
					  <tr v-for="sub in submits">
					  	<td colspan="2" align="center" valign="middle">{{ sub.accname }}</td>
					  	<td colspan="2" align="center" valign="middle">{{ sub.uploadtime | time }}</td>
					  </tr>
					  <tr>
						<td colspan="2" align="center" valign="middle">
							<!-- <input type="file" id="file" name="file" /> -->
							<input type="text" id="file" name="file" class="easyui-filebox" />
						</td>
						<input type="hidden" name="id" value="${requestScope.id }" />
						<td colspan="2" align="center" valign="middle"><button id="submits" iconCls="icon-ok" class="easyui-linkbutton" v-on:click="sibmits">提交</button></td>
					  </tr>
					</table>
				</form>
				<table width="100%" height="20px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
					<tr>
						<td>
		    				<center><button id="back" class="easyui-linkbutton" iconCls="icon-back" v-on:click="back">返回</button></center>
		    			</td>
		    		</tr>
		    	</table>
		    </div>
	    </div>	
	</div>
<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
<script type="text/javascript">
	$(function(){	
		Vue.filter('time', function (value) {
			 //return new Date(parseInt(value)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
			var date = new Date(value);
			Y = date.getFullYear(),
			m = date.getMonth() + 1,
			d = date.getDate(),
			H = date.getHours(),
			i = date.getMinutes(),
			s = date.getSeconds();
			if (m < 10) {
			m = '0' + m;
			}
			if (d < 10) {
			d = '0' + d;
			}
			if (H < 10) {
			H = '0' + H;
			}
			if (i < 10) {
			i = '0' + i;
			}
			if (s < 10) {
			s = '0' + s;
			}
			//获取时间格式 2017-01-03
			var t = Y + '年' + m + '月' + d + '日';
			return t;
		});
		var man = new Vue({
			el:'#bod',
			data:{
				teaname:"",
				couname:"",
				submits:[],
			},
			methods:{
				getDate:function(){
					var param = {
							"id":"${requestScope.id }",
					}
					//alert(JSON.stringify(param));
					$.ajax({
						type:'post',
						url:'getsubmitclassworkdata',
						contentType:"application/json",    //必须配置
						data:JSON.stringify(param),//转换成字符串，客户端作为生产者
						success:function(result){
							man.teaname = result.teaname;
							man.couname = result.couname;
							man.submits = result.submits;
							//alert(result.responseDesc);
							//window.location.reload(true);
							//man.data = result;
						} 
					});
				},
			    back: function(){
			    	//alert("返回");
			    	window.location.href = "myownclassinfo" ;
			    	return false;
			    },	
				sibmits: function(){
					var file = $("#filebox_file_id_1").val();
					if(file != null && file != ""){
						//alert("上传图片");
						$('#fileform').submit();
					}else{
						alert("请选择文件！");
						return false;
					}
					//return false;
				}
			}
		});
		man.getDate();
	})
</script>
</body>
</html>