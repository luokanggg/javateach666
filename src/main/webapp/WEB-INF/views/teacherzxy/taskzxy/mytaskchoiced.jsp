<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>我的课题</title>
</head>
<style>
.datagrid-row {
  height: 45px;
}
.datagrid-header-row td
{
  background-color:#9aCCFF;
  height:32px;
}
</style>

<body>
	<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="#mytask-toolbar">
        <span style="float:right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<a id="btnn"  style="float:right" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"><span style="font-size:14px">预览课题文件</span></a> 
		
		</div>
         <!-- End of toolbar -->
         <div style="width:1338px;height:423px;">
        <table  id="mytask-datagrid" toolbar="#mytask-toolbar"></table>
        </div>  
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#mytask-datagrid').datagrid({
		        url: 'getMyTask',
		        // rownumbers: true,
		        singleSelect: true,
		        pageSize: 10,
		        pagination: true,
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            //{field: 'id', align:"center",title: '课程ID', width: 20},
		            {field: 'taskname', align:"center",title: '课程名称', width: 110},
		            {field: 'tasktype', align:"center",title: '课程类型', width: 90},
		            {field: 'taskcontent', align:"center",title: '课程内容', width: 200},
		            {field: 'taskpubtime', align:"center",title: '发布时间', width: 50,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }	
		            },
		            {field: 'taskpublisher', align:"center",title: '发布人', width: 60},
		            {field: 'checkstatus', align:"center",title: '审批状态', width: 60,
		            	formatter : function(value,row,index){ 
		                     if(value=='0'){return '未知'}  
		                     else if(value=='2'){return '驳回'}   
		                     else if(value=='1'){return '通过'}
		                   }	
		            },
		            {field: 'ischeck', align:"center",title: '是否审批', width: 40,
		            	formatter : function(value,row,index){ 
		                     if(value=='0'){return '未审批'}  
		                     else{return '审批'}   
		                   }	
		            },
		            {field: 'checktime', align:"center",title: '审批时间', width: 70,
		            	 
		            formatter : function(value){
	            			 var date = new Date(value);
		                     var y = date.getFullYear();
		                     var m = date.getMonth() + 1;
		                     var d = date.getDate();
		                     var str= y + '-' +m + '-' + d;
		                     if(str=='1970-1-1'){
		                    	 return "";
		                     }else{
		                    	 return str;
		                     }
	            	  }	
		            },
		            {field: 'checker', align:"center",title: '审批人', width: 60},
		            {field: 'rank', align:"center",title: '等级', width: 60}
				]]
		    });
		  $("#btnn").click(function(){
			  
				$.ajax({
					
					url:'searchmytaskfile',
					type:'post',
					
					success:function(result){
						if(result.mess=='0000'){
							$.messager.alert("提示",'您还没有提交课题文件！',"info");
						}else{
							window.location.href="myTaskFile";
						}
					}
				});
		  });
	</script>
</body>
</html>