<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>我的课程表</title>
</head>
<body>
	<table width="100%" height="400px" border="1" style="border-color: #99CCFF; border-collapse : collapse">
		<thead>
			<tr>
				<th colspan="8" >课程表</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th></th>
				<th>星期一</th>
				<th>星期二</th>
				<th>星期三</th>
				<th>星期四</th>
				<th>星期五</th>
				<th>星期六</th>
				<th>星期日</th>
			</tr>
			<tr id="1"></tr>
			<tr id="2"></tr>
			<tr id="3"></tr>
			<tr id="4"></tr>
			<tr id="5"></tr>
			<tr id="6"></tr>
			<tr id="7"></tr>
			<tr id="8"></tr>
			<tr id="9"></tr>
			<tr id="10"></tr>
			<tr id="11"></tr>
			<tr id="12"></tr>
		</tbody>
	</table>
	<script type="text/javascript">
	$(function(){	
		$.ajax({
			type:'post',
			url:'initmyclassinfo',
			contentType:"application/json",    //必须配置
			//data:JSON.stringify(param),//转换成字符串，客户端作为生产者
			success:function(result){
				//$("tbody").append("Some appended text.");
				//alert(result[0].teaname);
				for (var i = 1; i < 13; i++) {
					$("#" + i ).append("<td>" + i + "</td>");
					/* if(i == 6){
						$("#" + i).append("<tr><td colspan=8 >  </td></tr>");
					} */
					for (var j = 1; j < 8; j++) {
						//$("#" + i ).append("<td>" + i + "</td>");
						var tag = 0;
						for (var n = 0; n < result.length; n++) {
							if(result[n].coutime == j && result[n].coufhour == i){
								$("#" + i ).append("<td rowspan="+ result[n].couhour + ">" + result[n].teaname + "<br>(" + result[n].couname + ")</td>");
								tag ++;
							}
						}
						if(tag == 0){
							//$("#" + i ).append("<td>" + j + "</td>");
							var tag2 = 0;
							for (var n = 0; n < result.length; n++) {
								//var frist = result[n].coufhour;
								//var last = result[n].coufhour + result[n].couhour - 1;
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
	</script>
</body>
</html>