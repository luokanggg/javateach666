<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<title>我的课程表</title>
</head>
<style>
td {     text-align:center; }
.right,.right1{
	float: right;
	position:relative;
	
}
</style>
<body>
<div>
<span class="right">
<a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-print" onclick="openExportSearch()">
打&nbsp;&nbsp;印</a>
</span>
</div>

	
		  学年：<select id="couyear" class="easyui-combobox" name="couyear" style="width:60px;">   
    					<c:forEach items="${diccouyear}" var="se">
    						<option  value="${se.value }" >${se.value }</option>
    					</c:forEach>     
    			   </select> &nbsp;&nbsp; &nbsp;         
	           学期：<select id="semester" class="easyui-combobox" name="semester" style="width:60px;">   
    					<option value="1">1</option>   
   						<option value="2">2</option>     
    			  </select>&nbsp;&nbsp; &nbsp;
    	 <a  href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-ok" id="sub"><font style="font-weight: bolder;font-size: 15px">提&nbsp;交</font></a>
	
	<center>
	<table id="tab" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
	
			<tr>
				<th colspan="7" style="height:34px">
				<span style="font-size: 15px;font-family: '微软雅黑'">教&nbsp;&nbsp;&nbsp;师&nbsp;&nbsp;&nbsp;课&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;表</span>
				</th>
			</tr>

			<tr style="height:30px">
				<th style="width:135px;">星期一</th>
				<th style="width:135px">星期二</th>
				<th style="width:135px">星期三</th>
				<th style="width:135px">星期四</th>
				<th style="width:135px">星期五</th>
				<th style="width:135px">星期六</th>
				<th style="width:135px">星期日</th>
			</tr>
			<tr id="1" style="height:30px"></tr>
			<tr id="2" style="height:30px"></tr>
			<tr id="3" style="height:30px"></tr>
			<tr id="4" style="height:30px"></tr>
			<tr id="5" style="height:30px"></tr>
			<tr id="6" style="height:30px"></tr>
			<tr id="7" style="height:30px"></tr>
			<tr id="8" style="height:30px"></tr>
			<tr id="9" style="height:30px"></tr>
			<tr id="10" style="height:30px"></tr>
			<tr id="11" style="height:30px"></tr>
			<tr id="12" style="height:30px"></tr>
		
	</table>
	
	</center>
	<script type="text/javascript">
	 $(function(){	
		$.ajax({
			type:'post',
			url:'myteacourseKBinfo',
			contentType:"application/json",    //必须配置
			//data:JSON.stringify(param),//转换成字符串，客户端作为生产者
			success:function(result){
				for (var i = 1; i < 13; i++) {
					//$("#" + i ).append("<td>" + i + "</td>");
					for (var j = 1; j < 8; j++) {
						var tag = 0;
						for (var n = 0; n < result.length; n++) {
							if(result[n].coutime == j && result[n].coufhour == i){
								$("#" + i ).append("<td rowspan="+ result[n].couhour + ">" + result[n].teaname + "<br>" + result[n].couname + "<br>("+result[n].coufhour+"——"+(result[n].coufhour-1+result[n].couhour)+")"+"</td>");
								tag ++;
							}
						}
						if(tag == 0){
							var tag2 = 0;
							for (var n = 0; n < result.length; n++) {
								if(result[n].coutime == j){
									var frist = result[n].coufhour;
									var last = result[n].coufhour + result[n].couhour - 1;
									if(i >= frist && i<= last){
										tag2 ++;
									}
								}
							}
							if(tag2 == 0){
								$("#" + i ).append("<td>   </td>");
							}
						}
					}
				}
			} 
		});
	})

	

	$("#sub").click(function(){
		var couyear=$("#couyear option:selected").val();
		var semester=$("#semester option:selected").val();
		var tab=document.getElementById("tab");
		var total=$("table tr").length;
		
		for(var r=0;r<total;r++){
			tab.deleteRow(0);
		}
		
		$("#tab").append("<tr><td colspan='7' style='width:950px;height:34px;font-size:15px'>教           师            课            程           表</td></tr>"); 
		$("#tab").append("<tr style='height:30px'><th style='width:135px;'>星期一</th><th style='width:135px'>星期二</th><th style='width:135px'>星期三</th><th style='width:135px'>星期四</th><th style='width:135px'>星期五</th><th style='width:135px'>星期六</th><th style='width:135px'>星期日</th></tr>");
		
		for(var r1=1;r1<=12;r1++){
			$("#tab").append("<tr style='height:30px' id='"+r1+"'></tr>");
		}
		 
			$.ajax({
			type:'post',
			url:'myteacourseKBinfoseach?couyear='+couyear+'&semester='+semester,
		
			success:function(result){
				for (var i = 1; i < 13; i++) {
				
					for (var j = 1; j < 8; j++) {
						var tag = 0;
						for (var n = 0; n < result.length; n++) {
							if(result[n].coutime == j && result[n].coufhour == i){
								$("#" + i ).append("<td rowspan="+ result[n].couhour + ">" + result[n].teaname + "<br>" + result[n].couname + "<br>("+result[n].coufhour+"——"+(result[n].coufhour-1+result[n].couhour)+")"+"</td>");
								tag ++;
							}
						}
						if(tag == 0){
							var tag2 = 0;
							for (var n = 0; n < result.length; n++) {
								if(result[n].coutime == j){
									var frist = result[n].coufhour;
									var last = result[n].coufhour + result[n].couhour - 1;
									if(i >= frist && i<= last){
										tag2 ++;
									}
								}
							}
							if(tag2 == 0){
								$("#" + i ).append("<td>   </td>");
							}
						}
					}
				}
			} 
		}); 
	});
	 
	
	 function openExportSearch() {
	    	
		 var couyear=$("#couyear option:selected").val();
		 var semester=$("#semester option:selected").val();
	      $.messager.confirm('提示', '是否确认导出课程表', function(r){
				if (r){
					
		  window.location.href = "myteacourseKBExport?couyear=" + couyear + "&semester=" + semester;
				
				}
	      });
	} 
	</script>
</body>
</html>