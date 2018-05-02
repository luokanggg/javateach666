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
	/* #classinfo-datagrid tr{height:40px;} */
	.filebox{float: left;margin-left: 10px;}
	.textbox-button{right:0;}
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
					<span color="#CCC" id="wei">&nbsp;&nbsp;我的信息/我的活动编辑详情</span>
				</div>
				<table width="90%" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
					<tr>
						<td align="center" valign="middle">活动名称：</td>
						<td><input type="text" v-model="PubInfo.pubname" name="pubname" id="pubname" /></td>
					</tr>
					<tr>
						<td align="center" valign="middle">活动内容：</td>
						<td><textarea rows="3"  cols="50" v-model="PubInfo.pubcontent" name="pubcontent" id="pubcontent"></textarea></td>
					</tr>
					<tr>
						<td align="center" valign="middle">活动地点：</td>
						<td><input type="text" v-model="PubInfo.pubaddress" name="pubaddress" id="pubaddress" /></td>
					</tr>
					<tr>
						<td align="center" valign="middle">活动人数：</td>
						<td><input type="text" v-model="PubInfo.pubnumber" name="pubnumber" id="pubnumber" ></td>
					</tr>
					<tr>
						<td align="center" valign="middle">已报名人数：</td>
						<td>{{ PubInfo.alpubnumber }}</td>
					</tr>
					<tr>
						<td align="center" valign="middle">活动开始时间：</td>
						<td><!-- <input type="date" v-model="PubInfo.pubtimestart" name="pubtimestart" id="pubtimestart"/> -->{{ PubInfo.pubtimestart | time }}</td>
					</tr>
					<tr>
						<td align="center" valign="middle">活动结束时间：</td>
						<td><!-- <input type="date" v-model="PubInfo.pubtimeend" name="pubtimeend" id="pubtimeend"/> -->{{ PubInfo.pubtimeend | time }}</td>
					</tr>
					<tr>
						<td colspan="2" align="center" valign="middle">
							<button class="easyui-linkbutton" iconCls="icon-ok" id="submits" v-on:click="sibmits">提交</button>&nbsp;&nbsp;&nbsp;
							<button class="easyui-linkbutton" iconCls="icon-back" id="back" v-on:click="back">返回</button>
						</td>
					</tr>
				</table>
			</div>	
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script type="text/javascript">
		var id = "${ requestScope.id }";
		//alert(id);
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
		$(function(){			
			var man = new Vue({
				el:'#bod',
				data:{
					PubInfo:""
					
				},
				methods:{
					getDate:function(){
						var data = { "id":id };
						$.ajax({
							type:'post',
							url:'getupdatepubactivity',
							contentType:"application/json",    //必须配置
							/* beforeSend : function(xhr) {
				                xhr.setRequestHeader(header, token);
				            }, */
							data:JSON.stringify(data),//转换成字符串，客户端作为生产者
							success:function(result){
								man.PubInfo = result;
								//alert("man.PubInfo.pubtimestart" + man.PubInfo.pubtimestart);
								//alert(man.PubInfo.responseDesc);
							} 
						});
					},
					back:function(){
						window.location.href = "gomyactivityupdate";
					},
					sibmits:function(){
						
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
						}
						
						var reg=/^[1-9]\d*$|^0$/;   // 注意：故意限制了 0321 这种格式，如不需要，直接reg=/^\d+$/;
						if(reg.test(man.PubInfo.pubnumber)==false){
						    alert("活动人数栏请输出数字！");
						    return false;
						}
						
						var data = {
								"id":id,
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
							url:'updatepubactivity',
							contentType:"application/json",    //必须配置
							/* beforeSend : function(xhr) {
				                xhr.setRequestHeader(header, token);
				            }, */
							data:JSON.stringify(data),//转换成字符串，客户端作为生产者
							success:function(result){
								man.PubInfo = result;
								alert(man.PubInfo.responseDesc);
							} 
						});
					},
				}
			});
			
			man.getDate();
		})
	</script>
</body>
</html>