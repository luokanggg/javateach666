<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>课题提交情况</title>
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
.datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber, .datagrid-cell-rownumber
   {
                text-overflow: ellipsis;
   }
</style>

<body>
	<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="#looktaskupload-toolbar">
		</div>
        <table  id="looktaskupload-datagrid" toolbar="#looktaskupload-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#looktaskupload-datagrid').datagrid({
		        url: 'getTaskuploadinfo',
		        rownumbers: true,
		        singleSelect: true,
		        pageSize: 10,
		        pagination: true,
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            {field: 'id', align:"center",title: 'ID', width: 20},
		            {field: 'taskname', align:"center",title: '课程名称', width: 120},
		            {field: 'accurl', align:"center",title: '文件地址', width: 130},
		            {field: 'uploadtime', align:"center",title: '上传时间', width: 80,
		            	 formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  } 
		            },
		            
		            {field: 'teaname', align:"center",title: '课题作者', width: 70},
					{field: 'operate', title: '操作', align:'center',width:$(this).width()*0.12,formatter:function(value, row, index){  
						
						var str1='<a href="#" name="opera1" class="easyui-linkbutton" onclick="lookfileAdmin()">'+"          "+'<a href="#" name="down" class="easyui-linkbutton" onclick="downtaskfile()"></a>';  
						return str1;  
					}}
				]],
				onLoadSuccess:function(data){   
					$("a[name='opera1']").linkbutton({text:'查看课题内容',plain:true,iconCls:'icon-search'}); 
					$("a[name='down']").linkbutton({text:'下载',plain:true,iconCls:'icon-save'});
				}
		    });
	
		  $("#looktaskupload-datagrid").datagrid("hideColumn", "accurl");
		  $("#looktaskupload-datagrid").datagrid("hideColumn", "id");
		  function lookfileAdmin(){
			  var rows = $('#looktaskupload-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		var param = {
						"id":rows[0].id,
						"taskname":rows[0].taskname,
						"teaname":rows[0].teaname
				}
			$.ajax({
					
					url:'searchtaskfilebyId',
					type:'post',
					dataType:"json",    //必须配置
					data:param,
					success:function(result){
						if(result.mess=="0000"){
							window.location.href="searchtaskfileandJudge";
						}else{
							$.messager.alert('提示', "查询失败！", 'warning');
						}
					}
				});
		  }
		 function downtaskfile(){
			 var rows = $('#looktaskupload-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		window.location.href="downtaskfile?id="+rows[0].id;
		 }
	</script>
</body>
</html>