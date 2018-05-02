<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/lktable.css"/>
<title>教学质量评价</title>
</head>
<body>
	<div id="bod" class="easyui-layout" data-options="fit:true" data-options="region:'center',border:false">
		<div class="tab">
				<div class="tab1">
				<!--  <div id="cc" class="easyui-calendar" data-options="formatter:formatDay" style="width:290px;height:230px;"></div>  -->
				
				</div>
				<div class="tab2">
					<div class="tab3">
						<span color="#CCC" id="wei">&nbsp;&nbsp;教学质量评价/重新评价</span>
					</div>
					<table width="100%" height="500px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
					  <tr>
					    <td width="12%" align="center" valign="middle" >课程名称：</td>
					    <td width="12%" align="center" valign="middle" >{{ data.couname }}</td>
					    <td width="12%" align="center" valign="middle" >教师名称：</td>
					    <td width="12%" align="center" valign="middle" >{{ data.teaname }}</td>
					    <td width="12%" align="center" valign="middle" >评分：</td>
					    <td width="12%" align="center" valign="middle" >
					    		<span v-if="data.evaluate == 0">未评价</span>
					    		<span v-if="data.evaluate == 1">不满意</span>
					    		<span v-if="data.evaluate == 2">较满意</span>
					    		<span v-if="data.evaluate == 3">满意</span>
					    		<span v-if="data.evaluate == 4">很满意</span>
						</td>
					  </tr>
					  <tr>
					  	<td colspan="6">
					  		<h3>该教师的教学态度评分：</h3>
					  		<input type="radio" class="evaluate" name="evaluate1" value="4"/>很满意<br>
					  		<input type="radio" class="evaluate" name="evaluate1" value="3"/>满意<br>
					  		<input type="radio" class="evaluate" name="evaluate1" value="2"/>较满意<br>
					  		<input type="radio" class="evaluate" name="evaluate1" value="1"/>不满意
					  	</td>
					  </tr>
					  <tr>
					  	<td colspan="6">
					  		<h3>该教师的教学水平评分：</h3>
					  		<input type="radio" class="evaluate" name="evaluate2" value="4"/>很满意<br>
					  		<input type="radio" class="evaluate" name="evaluate2" value="3"/>满意<br>
					  		<input type="radio" class="evaluate" name="evaluate2" value="2"/>较满意<br>
					  		<input type="radio" class="evaluate" name="evaluate2" value="1"/>不满意
					  	</td>
					  </tr>
					  <tr>
					  	<td colspan="6">
					  		<h3>该教师的教学方法和手段评分：</h3>
					  		<input type="radio" class="evaluate" name="evaluate3" value="4"/>很满意<br>
					  		<input type="radio" class="evaluate" name="evaluate3" value="3"/>满意<br>
					  		<input type="radio" class="evaluate" name="evaluate3" value="2"/>较满意<br>
					  		<input type="radio" class="evaluate" name="evaluate3" value="1"/>不满意
					  	</td>
					  </tr>
					  <tr>
					  	<td colspan="6">
					  		<h3>该教师的教学能力评分：</h3>
					  		<input type="radio" class="evaluate" name="evaluate4" value="4"/>很满意<br>
					  		<input type="radio" class="evaluate" name="evaluate4" value="3"/>满意<br>
					  		<input type="radio" class="evaluate" name="evaluate4" value="2"/>较满意<br>
					  		<input type="radio" class="evaluate" name="evaluate4" value="1"/>不满意
					  	</td>
					  </tr>
					  <tr>
					    <td align="center" colspan="6">
					    	<button id="submits" iconCls="icon-ok" class="easyui-linkbutton" v-on:click="submits">提交</button>
					    	<button id="save" iconCls="icon-back" class="easyui-linkbutton" v-on:click="back">返回</button>
					    </td>
					  </tr>
					</table>
			</div>	
		</div>
	</div>
<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
<script type="text/javascript">
	$(function(){			
		var id = "${ requestScope.id }";
		//alert("id" + id);
		var man = new Vue({
			el:'#bod',
			data:{
				data:"",
			},
			methods:{
				getDate:function(){
					//alert("getDate()");
					param = {"id":id};
					//alert("id" + id);
					$.ajax({
						type:'post',
						url:'getevaluatebyid',
						contentType:"application/json",    //必须配置
						data:JSON.stringify(param),//转换成字符串，客户端作为生产者
						success:function(result){
							//alert(result[0].teaname);
							man.data = result;
						} 
					}); 
					//alert("getDate()2");
				},
				back:function(){
					window.location.href = "goevaluatelist";
				},
				submits:function(){
					var e1 = $('input:radio[name="evaluate1"]:checked').val();
					var e2 = $('input:radio[name="evaluate2"]:checked').val();
					var e3 = $('input:radio[name="evaluate3"]:checked').val();
					var e4 = $('input:radio[name="evaluate4"]:checked').val();
					if(e1 == null || e2 == null || e3 == null || e4 == null){
						alert("有评价未选择！");
						return false;
					}
					var total = Math.round((parseInt(e1) + parseInt(e2) + parseInt(e3) + parseInt(e4))/4);
					//man.evaluate = total;
					var param = {
							"evaluate":total,
							"id":id
					};
					$.ajax({
						type:'post',
						url:'updateevaluate',
						contentType:"application/json",    //必须配置
						data:JSON.stringify(param),//转换成字符串，客户端作为生产者
						success:function(result){
							alert(result.responseDesc);
							window.location.reload(true);
							//man.data = result;
						} 
					});
					/* for (var i = 0; i < man.score.length; i++) {
						alert("score:" + man.score[i]);
					}
					for (var i = 0; i < man.id.length; i++) {
						alert("id:" + man.id[i]);
					} */
				}
			}
		});
		
		man.getDate();
	})
</script>
</body>
</html>