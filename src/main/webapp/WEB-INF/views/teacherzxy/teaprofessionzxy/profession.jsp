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
<title>升职管理</title>
</head>
<style>
select {
width:180px;	
height:24px;
font-size:13px;	
border-radius:3px;
border:2px solid #c9dffd;
}
#proftime {
width:160px;	
height:27px;
font-size:13px;	
}
#submits{
	width:70px; height:30px;
	border-radius: 4px; 
	background:#c9dffd;
	
	 text-align:center; 
	 color:#0a0af5;
	 font-size:14px;
	 font-weight: bolder;
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
	width:290px;
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
	border:0px solid #99CCFF;
	border-radius:5px;
	height:26px;
	width:1200px;
	background: #99CCFF;
	line-height:22px;
	margin-left:-250px;
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
					<span color="#CCC" id="wei">&nbsp;&nbsp;升职管理/升职请求</span>
				</div>
		 <table  border="0"  width="55%"  style="margin-left:6px;margin-top:13px; border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		    <td width="90px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle"  style="border-right-style:none">申请人编号</td>
		     <td bgcolor="#FFFFFF" align="left" valign="middle" style="border-left-style:none" >
		     <input class="easyui-textbox" readonly="true" style="width:420px;height:30px;" type="text" value="${TeaInfo.teano }" id="prof_person_id"></td>
		  </tr>
		  <tr>
		  <td colspan="2" height="8px"></td>
		  </tr>
		  <tr>
		  	 <td width="90px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none">申请人姓名</td>
		     <td bgcolor="#FFFFFF" align="left" valign="middle" style="border-left-style:none">
		     <input class="easyui-textbox" readonly="true" style="width:420px;height:30px;" style="border:none" type="text" value="${TeaInfo.teaname }" id="prof_person_name"></td>
		  </tr>
		  <tr>
		  <td colspan="2" height="8px"></td>
		  </tr>
		   <tr>
		    <td width="90px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none" >现任的职称</td>
		    <td bgcolor="#FFFFFF" align="left" valign="middle" style="border-left-style:none">
		    <input class="easyui-textbox" readonly="true" style="width:420px;height:30px;" style="border:none" type="text" value="${TeaInfo.professional }" id="professional"></td>
		  </tr>
		  <tr>
		  <td colspan="2" height="8px"></td>
		  </tr>
		  <tr>
		    <td width="90px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none">申请的职称</td>
		    <td bgcolor="#FFFFFF" align="left" valign="middle" style="border-left-style:none" >
		    <select id="prof" style="padding-left:70px;width:200px;height:27px">
				<c:forEach items="${dic}" var="diclist">
					 <option  value="${diclist.value }" >${diclist.dicname}</option>
				</c:forEach>
		    </select>
            </td>
		  </tr>
		  <tr>
		  <td colspan="2" height="8px"></td>
		  </tr>
		  <tr>
		    <td  bgcolor="#FFFFFF" align="left" valign="middle" height="52px" style="border-right-style:none">申请的原因</td>
		    <td bgcolor="#FFFFFF"   align="left" valign="middle" style="border-left-style:none">
		   <!--  <textarea rows="3" cols="38" id="reason"></textarea> -->
		     <input style="width:420px;height:28px" class="easyui-textbox" id="reason" data-options="multiline:true,prompt:'Enter a professional reason...'"/>
		    </td>
		  </tr>
		  <tr>
		  <td colspan="2" height="8px"></td>
		  </tr>
		  <tr>
		    <td width="90px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none" >申请的时间</td>
		    <td bgcolor="#FFFFFF"   align="left" valign="middle" style="border-left-style:none;border-top-style:none">
		    <input style="width:200px" type="text" class="easyui-datebox" required="required" id="proftime"></td>
		  </tr>
		  <tr>
		  <td colspan="2" height="8px"></td>
		  </tr>
		  <tr>
		  <td></td>
		    <td width="90px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle">
		    	<button id="submits" v-on:click="sibmits" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"><span style="font-size:14px">提&nbsp;&nbsp;交</span></button> 
		    </td>
		  </tr>
		</table> 
		 </div>
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/static/js/vue.js"></script>
	<script type="text/javascript">
	
	$(function(){
		
	$("#submits").click(function(){
		var pro="${TeaInfo.professional }";
		//alert("职称为："+pro);
		var prof_saltv="";
		prof_saltv=$("#prof option:selected").text();
		if(pro==prof_saltv){
			$.messager.alert('提示','不能申请现任职称！','warning');
			return false;
		}
		var prof_reason="";
		prof_reason=$("#reason").val();
		if(""==prof_reason){
			prof_reason="无";
		}
		var prof_time="";
		prof_time=$("#proftime").val();
		
		
		if(""==prof_time){
			
			prof_time="9999-01-01";
		}else{
			dy=prof_time.substring(0,2);
			prof_time=$("#proftime").val();
			dr=prof_time.substring(3,5);
			prof_time=$("#proftime").val();
			dn=prof_time.substring(6,10);
			prof_time=dn+"-"+dy+"-"+dr;
			
		}
		
		var prof_person_id="";
		prof_person_id=$("#prof_person_id").val();
		var prof_person_name="";
		prof_person_name=$("#prof_person_name").val();
	
		data={
				"prof_person_id":prof_person_id,
				"prof_person_name":prof_person_name,
				"prof_saltv":prof_saltv,
				"prof_time":prof_time,
				"prof_reason":prof_reason
				};
		
		$.ajax({
			
			url:'http://localhost:8089/javateach666/approverank',
			type:'get',
			dataType:"json",
			data:data,
			success:function(result){
				if(result.mess=="申请成功"){
				
					$.messager.alert('申请信息',result.prodata.prof_person_name+" 于  "+result.prodata.time+"  申请 "+result.prodata.prof_saltv+" 职位成功");
				
				}else{
					$.messager.alert('提示',result.mess,'warning');
				}
			}
		});
	  });
	});
	</script>
</body>
</html>