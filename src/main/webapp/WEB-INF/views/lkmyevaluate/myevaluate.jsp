<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<title>教学质量评价</title>
</head>
<body>
	<div id="bod">
		<table width="100%" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		    <td width="12%">课程名称：</td>
		    <td width="12%">{{ data[count].couname }}</td>
		    <td width="12%">教师名称：</td>
		    <td width="12%">{{ data[count].teaname }}</td>
		    <td width="12%">评分：</td>
		    <td width="12%">
		    		<span v-if="data[count].evaluate == 0">未评价</span>
		    		<span v-if="data[count].evaluate == 1">不满意</span>
		    		<span v-if="data[count].evaluate == 2">较满意</span>
		    		<span v-if="data[count].evaluate == 3">满意</span>
		    		<span v-if="data[count].evaluate == 4">很满意</span>
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
		    <td colspan="6">
		    	<button id="save" v-on:click="save">保存</button>
		    	<button id="submits" v-on:click="submits">提交</button>
		    </td>
		  </tr>
		</table>
	</div>
<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
<script type="text/javascript">
	$(function(){			
		
		var man = new Vue({
			el:'#bod',
			data:{
				score:[],
				id:[],
				data:[],
				count:0,
			},
			methods:{
				getDate:function(){
					//alert("getDate()");
					$.ajax({
						type:'post',
						url:'initevaluate',
						contentType:"application/json",    //必须配置
						//data:JSON.stringify(param),//转换成字符串，客户端作为生产者
						success:function(result){
							//alert(result[0].teaname);
							man.data = result;
						} 
					}); 
					//alert("getDate()2");
				},
				save:function(){
					//alert("保存");
					if(man.count >= man.data.length){
						alert("你已经完成所有评价！可以进行提交操作！");
						$("input[type='radio']").prop("checked",false);
						return false;
					}
					var e1 = $('input:radio[name="evaluate1"]:checked').val();
					var e2 = $('input:radio[name="evaluate2"]:checked').val();
					var e3 = $('input:radio[name="evaluate3"]:checked').val();
					var e4 = $('input:radio[name="evaluate4"]:checked').val();
					if(e1 == null || e2 == null || e3 == null || e4 == null){
						alert("有评价未选择！");
						return false;
					}
					//alert("e1:" + e1 + "  e2:" + e2 + "  e3:" + e3 + "  e4:" + e4);
					var total = Math.round((parseInt(e1) + parseInt(e2) + parseInt(e3) + parseInt(e4))/4);
					alert("total" + total);
					man.score[man.count] = total;
					man.id[man.count] = man.data[man.count].id;
					man.count++;
					if(man.count >= man.data.length){
						alert("你已经完成所有评价！可以进行提交操作！");
					}
					$("input[type='radio']").prop("checked",false); 
				},
				submits:function(){
					if(man.score.length < man.data.length){
						alert("请全部评选完毕再提交！");
						return false;
					}
					var param = {
							"score":man.score,
							"id":man.id
					};
					$.ajax({
						type:'post',
						url:'submitevaluate',
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