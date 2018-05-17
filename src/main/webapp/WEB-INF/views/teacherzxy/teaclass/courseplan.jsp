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
<title>课程计划管理</title>
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
					<span color="#CCC" id="wei" >&nbsp;&nbsp;教学计划方案</span>
				</div>
		 <table  border="0"  width="55%"  style="margin-left:12px;margin-top:13px; border-color: #99CCFF; border-collapse : collapse">
		  <tr>
		    <td style="border-right-style:none" width="70px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle"  >计划编制人</td>
		     <td style="border-left-style:none;border-right-style:none" bgcolor="#FFFFFF" align="left" valign="middle"  >
		     <input class="easyui-textbox"  style="width:180px;height:30px;" type="text"  id="teaid" value="${TeaInfo.teaname }"></td>
		     <td style="border-right-style:none;border-left-style:none" width="70px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle"  >课程名称</td>
		     <td style="border-left-style:none" bgcolor="#FFFFFF" align="left" valign="middle" >
		     <input class="easyui-textbox"  style="width:180px;height:30px;" type="text"  id="couname" value="${teacourse.couname }"></td>
		  </tr>
		  <tr>
		  <td colspan="4" height="8px"></td>
		  </tr>
		  <tr>
		  	 <td style="border-right-style:none" width="70px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none">计划标题&nbsp;&nbsp;</td>
		     <td style="border-left-style:none" colspan="3" bgcolor="#FFFFFF" align="left" valign="middle" style="border-left-style:none">
		     <input data-options="multiline:true,prompt:'Enter a plan title...'" class="easyui-textbox"  style="width:440px;height:30px;" style="border:none" type="text"  id="plantitle"></td>
		  </tr>
		  <tr>
		  <td colspan="4" height="8px" style="border-style:none;"></td>
		  </tr>
		  <tr>
		    <td style="border-right-style:none" width="70px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none" >计划目标</td>
		    <td style="border-left-style:none" colspan="3" bgcolor="#FFFFFF" align="left" valign="middle" style="border-left-style:none">
		    <input class="easyui-textbox" data-options="multiline:true,prompt:'Enter a plan goal...'" style="width:440px;height:30px;" style="border:none" type="text"  id="plangoal"></td>
		  </tr>
		  <tr>
		  <td colspan="4" height="8px"></td>
		  </tr>
		  <tr>
		    <td  style="border-right-style:none" bgcolor="#FFFFFF" align="left" valign="middle" height="52px" style="border-right-style:none">计划内容</td>
		    <td style="border-left-style:none" colspan="3" bgcolor="#FFFFFF"   align="left" valign="middle" style="border-left-style:none">
		     <input style="width:440px;height:48px" class="easyui-textbox" id="plancontent" data-options="multiline:true,prompt:'Enter a plan detail...'"/>
		    </td>
		  </tr>
		   <tr>
		  <td colspan="4" height="8px"></td>
		  </tr>
		  <tr>
		    <td style="border-right-style:none;border-left-style:none" width="70px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none" >开始时间
		   </td><td bgcolor="#FFFFFF" align="left" valign="middle"> <input style="width:150px" type="text" class="easyui-datebox" required="required" id="plantime1"></td>
		     <td width="70px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none" >结束时间</td>
		    <td style="border-right-style:none;border-left-style:none" style="border-right-style:none" bgcolor="#FFFFFF"   align="left" valign="middle" style="border-left-style:none;border-top-style:none">
		    <input style="width:150px" type="text" class="easyui-datebox" required="required" id="plantime2"></td>
		  </tr>
		  <tr>
		  <td colspan="4" height="8px"></td>
		  </tr>
		  
		  <tr>
		    <td width="70px" height="52px" bgcolor="#FFFFFF" align="left" valign="middle" style="border-right-style:none">计划班级</td>
		    <td bgcolor="#FFFFFF" align="left" valign="middle" style="border-left-style:none" >
		    <select id="planclass" style="padding-left:12px;width:150px;height:27px">
		    <c:forEach items='${classlist}' var='classlist'>
		    <option  value='${classlist.classid }' >${classlist.classname}</option>
		    </c:forEach>
		    </select>
            </td>
            <td style="border-left-style:none" colspan="2" height="52px" bgcolor="#FFFFFF" align="left" valign="middle">
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
		
		var couyear="${teacourse.couyear}";
		var semester="${teacourse.semester}";
		var couname=$("#couname").val();
		var planclass="";
		planclass=$("#planclass option:selected").text();
		//alert(planclass);
		var plantitle="";
		plantitle=$("#plantitle").val();
		if(""==plantitle){
			$.messager.alert("提示","请输入计划标题","warning");
			return false;
		}
		var plangoal="";
		plangoal=$("#plangoal").val();
		if(""==plangoal){
			$.messager.alert("提示","请输入计划目标","warning");
			return false;
		}
		var plancontent="";
		plancontent=$("#plancontent").val();
		
		if(""==plancontent){
			$.messager.alert("提示","请输入计划内容","warning");
			return false;
		}
		
		var plantime2="";
		plantime2=$("#plantime2").val();
		
		
		if(""==plantime2){
			$.messager.alert("提示","请输入计划时间","warning");
			return false;
		}else{
			dy=plantime2.substring(0,2);
			plantime2=$("#plantime2").val();
			dr=plantime2.substring(3,5);
			plantime2=$("#plantime2").val();
			dn=plantime2.substring(6,10);
			plantime2=dn+"-"+dy+"-"+dr;
			
		}
		
		var plantime1="";
		plantime1=$("#plantime1").val();
		
		
		if(""==plantime1){
			$.messager.alert("提示","请输入计划时间","warning");
			return false;
		}else{
			dy=plantime1.substring(0,2);
			plantime1=$("#plantime1").val();
			dr=plantime1.substring(3,5);
			plantime1=$("#plantime1").val();
			dn=plantime1.substring(6,10);
			plantime1=dn+"-"+dy+"-"+dr;
		}
		
	
		data={
				"plantime1":plantime1,
				"plantime2":plantime2,
				"plancontent":plancontent,
				"plangoal":plangoal,
				"plantitle":plantitle,
				"couyear":couyear,
				"semester":semester,
				"planclass":planclass,
				"couname":couname
				};
		//alert(couname+" "+planclass+" "+semester+" "+couyear+" "+plantitle+"  "+plangoal+" "+plantime2+" "+plantime1+" ");
		$.ajax({
			
			url:'http://localhost:8089/javateach666/MakePlanCourse',
			type:'get',
			dataType:"json",
			data:data,
			success:function(result){
				if(result.mess=='0000'){
					$.messager.alert("提示",'课程计划编制成功',"info");
				}else{
					$.messager.alert("提示",'课程计划编制失败',"info");
				}
			}
		});
	  });
	});
	</script>
</body>
</html>