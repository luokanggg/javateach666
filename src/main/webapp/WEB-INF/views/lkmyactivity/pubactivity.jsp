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
<style type="text/css">
	/* #classinfo-datagrid tr{height:40px;} */
	.filebox{float: left;margin-left: 10px;}
	.textbox-button{right:0;}
</style>
</head>
<body>
	<!--选项卡-->
	<div class="tab">
		<p class="location">
			<em>当前位置 --
				<span id="dqwz">发布活动</span>
			</em>
		</p>
	</div>
	<!--选项卡-->
	<div id="bod">
		<table width="100%" height="500px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
			<tr>
				<td>活动名称：</td>
				<td><input type="text" v-model="PubInfo.pubname" name="pubname" id="pubname" /></td>
			</tr>
			<tr>
				<td>活动内容：</td>
				<td><textarea rows="3"  cols="50" v-model="PubInfo.pubcontent" name="pubcontent" id="pubcontent"></textarea></td>
			</tr>
			<tr>
				<td>活动地点：</td>
				<td><input type="text" v-model="PubInfo.pubaddress" name="pubaddress" id="pubaddress" /></td>
			</tr>
			<tr>
				<td>活动人数：</td>
				<td><input type="text" v-model="PubInfo.pubnumber" name="pubnumber" id="pubnumber" ></td>
			</tr>
			<tr>
				<td>活动开始时间：</td>
				<td><input type="date" v-model="PubInfo.pubtimestart" id="start-time-value"/></td>
			</tr>
			<tr>
				<td>活动结束时间：</td>
				<td><input type="date" v-model="PubInfo.pubtimeend" id="end-time-value"/></td>
			</tr>
			<tr>
				<td colspan="2"><button class="easyui-linkbutton" id="submits" v-on:click="sibmits">提交</button></button></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script type="text/javascript">
		$(function(){			
			var man = new Vue({
				el:'#bod',
				data:{
					PubInfo:{
						pubname:"",
						pubcontent:"",
						pubaddress:"",
						pubnumber:"",
						pubtimestart:"",
						pubtimeend:"",
					}
					
				},
				methods:{
					sibmits:function(){
						//alert("PubInfo.pubname  " + man.PubInfo.pubname);
						//alert("PubInfo.pubcontent  " + man.PubInfo.pubcontent);
						//alert("PubInfo.pubaddress  " + man.PubInfo.pubaddress);
						//alert("PubInfo.pubnumber  " + man.PubInfo.pubnumber);
						//alert("PubInfo.pubtimestart  " + man.PubInfo.pubtimestart);
						//alert("PubInfo.pubtimeend  " + man.PubInfo.pubtimeend);
						
						if(man.PubInfo.pubname == null || man.PubInfo.pubname == ""){
							alert("请输入活动名称！");
							return false;
						}else if(man.PubInfo.pubcontent == null || man.PubInfo.pubcontent == ""){
							alert("请输入活动内容！");
							return false;
						}else if(man.PubInfo.pubaddress == null || man.PubInfo.pubaddress == ""){
							alert("请输入活动地点！");
							return false;
						}else if(man.PubInfo.pubnumber == null || man.PubInfo.pubnumber == ""){
							alert("请输入活动人数！");
							return false;
						}else if(man.PubInfo.pubtimestart == null || man.PubInfo.pubtimestart == ""){
							alert("请选择活动开始时间！");
							return false;
						}else if(man.PubInfo.pubtimeend == null || man.PubInfo.pubtimeend == ""){
							alert("请选择活动结束时间！");
							return false;
						}
						
						var reg=/^[1-9]\d*$|^0$/;   // 注意：故意限制了 0321 这种格式，如不需要，直接reg=/^\d+$/;
						if(reg.test(man.PubInfo.pubnumber)==false){
						    alert("活动人数栏请输出数字！");
						    return false;
						}
						
						
						var data = {
							"pubname":man.PubInfo.pubname,
							"pubcontent":man.PubInfo.pubcontent,
							"pubaddress":man.PubInfo.pubaddress,
							"pubnumber":man.PubInfo.pubnumber,
							"pubtimestart":man.PubInfo.pubtimestart,
							"pubtimeend":man.PubInfo.pubtimeend,
						};
						//alert(JSON.stringify(data));
						$.ajax({
							type:'post',
							url:'pubactivity',
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
					},
				}
			});
		})
	</script>
</body>
</html>