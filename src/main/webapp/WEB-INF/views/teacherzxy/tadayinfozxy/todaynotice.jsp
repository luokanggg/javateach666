<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>今日信件</title>
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
<body id="bods">
	<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
  
         <div style="width:1343px;height:420px;">
        <table id="todaynoticeinfo-datagrid" toolbar="#todaynoticeinfo-toolbar"></table>  
    </div>
    </div>
	</div>
	
	<script type="text/javascript">
		  $('#todaynoticeinfo-datagrid').datagrid({
		        url:'todaynoticeinfolists',
		        rownumbers: true,
		        checkbox:true,
		        singleSelect:false,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        multiSort: true,
		        fitColumns: true,
		        pageList:[10,15,20],
		        fit: true,
		       striped:true,
		        columns: [[
		            {field: 'id', title:'ID',align:"center", width: 40},
		            {field: 'notname', align:"center",title: '通知人姓名', width: 80},
		          
		            {field: 'nottitle', align:"center",title: '标题', width: 150},
		            {field: 'nottype', align:"center",title: '类型', width: 140, 
		            	formatter : function(value){
		            		if(value==1){
		            			return "老师通知学生";
		            		}else{
		            			return "老师通知老师";
		            		}
		            	}
		            },
		            {field: 'notcontent', align:"center",title: '内容', width: 240},
		           /*  {field: 'noturl', align:"center",title: 'url地址', width: 140,
		            	formatter : function(value){
		            		return "<a href='#'  onclick='judgeurl()'>"+value+"</a>";
		            	}
		            }, */
		            {field: 'starttime', align:"center",title: '开始时间', width: 115,
		            	  formatter : function(value){
		                        var date = new Date(value);
		                        var y = date.getFullYear();
		                        var m = date.getMonth() + 1;
		                        var d = date.getDate();
		                        return y + '-' +m + '-' + d;
		            	  }
		            },
		            {field: 'endtime', align:"center", title:'结束时间', width: 115,
		            	  formatter : function(value){
		                        var date = new Date(value);
		                        var y = date.getFullYear();
		                        var m = date.getMonth() + 1;
		                        var d = date.getDate();
		                        return y + '-' +m + '-' + d;
		            	  }

		            }
				]],
				onLoadSuccess:function(data){ 
					
				}
		    });
		  $("#todaynoticeinfo-datagrid").datagrid('hideColumn', "id");
		 function judgeurl(){
			 var rows = $('#todaynoticeinfo-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		var uri=rows[0].noturl;
		  		
			 var URLRexExp = new RegExp(/^[A-Za-z]+:\/\/[A-Za-z0-9-_]+.[A-Za-z0-9-_%&\?\/.:=]+$/); 
			 if(URLRexExp.test(uri)){
				 window.location.href=uri;
			 }else{
				 window.open("goerror","_parent");
			}
			 
		 }
			
		 </script>
</body>
</html>