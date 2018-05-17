<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>发布课题</title>
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
        <div id="#taskpub-toolbar">
            <div class="wu-toolbar-button">
                
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                <form action="publishtaskinfo" style="display: inline-block" method="post" >
			     课题类型：<select id="tasktype" class="easyui-combobox" name="tasktype" style="width:120px;">   
    					<c:forEach items="${tasktype}" var="type">
    						<option  value="${type.dicname }" >${type.dicname}</option>
    					</c:forEach>   
    			   </select>&nbsp;&nbsp;&nbsp;&nbsp;
			 课题名称:<input class="easyui-textbox" id="taskname" name="taskname" data-options="multiline:true,prompt:'Enter a task name'"  style="width:180px;height:25px" />&nbsp;&nbsp;&nbsp;&nbsp;
			 课题内容:<input class="easyui-textbox" data-options="multiline:true,prompt:'Enter a task contentl'" id="taskcontent" name="taskcontent" style="width:260px;height:25px" />  &nbsp; &nbsp;&nbsp; &nbsp;          
                    <input id="submits" type="submit"  class="easyui-linkbutton" value=" Submits " style="height:24px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="reset" class="easyui-linkbutton" value=" Reset " style="height:24px">
                </form>
            </div>
		</div>
         <!-- End of toolbar -->
         <div style="width:1343px;height:420px;">
        <table  id="taskpub-datagrid" toolbar="#taskpub-toolbar"></table>  
        </div>
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#taskpub-datagrid').datagrid({
		        url: 'getAllpublishTask',
		        // rownumbers: true,
		        singleSelect: true,
		        pageSize: 10,
		        pagination: true,
		       // queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            {field: 'id', title: 'ID',align:"center", height:50,width: 40, sortable: true},
		            {field: 'taskname', align:"center",title: '课程名称', width: 70},
		            {field: 'tasktype', align:"center",title: '课程类型', width: 70},
		            {field: 'taskcontent', align:"center",title: '课程内容', width: 200,
		            	 formatter: function(value,row,index){
		                      return '<span  title='+value+'>'+value+'</span>'  
		            	 }	
		            },
		            {field: 'taskpubtime', align:"center",title: '发布时间', width: 70,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }		
		            },
		            {field: 'taskpublisher', align:"center",title: '发布人', width: 100}/* ,
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="deletetask()"></a>'; 
						var str1='    ';
						var str2='<a href="#" name="opera1" class="easyui-linkbutton" onclick="modifytask()"></a>';
						return str+str1+str2;  
					}} */
				]],
				onLoadSuccess:function(data){   
				//	alert("成功");
						//$("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'}); 
						//$("a[name='opera1']").linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'}); 
				}
		    });
		   
		    function deletetask(){
		  		
		  		var rows = $('#taskpub-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		$.messager.confirm('确认', '确定要删除吗？', function(r){
					if (r){
						//alert('确认删除');    
						var param = {
								"id":rows[0].id
								
						}
						//alert(param);
						$.ajax({
							type:'post',
							url:'task_delete',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								
								
								if(result.mess=='0000'){
									self.location.reload();
								}
								if(result.mess=='8888'){
									$.messager.alert("提示","     课题删除失败！","info");
								}
								if(result.mess=='5555'){
									$.messager.alert("提示","     该课题不是您发布的不可以删除！","info");
								}
							} 
						});
					}
		  		});
		  	}
		   
		    $("#submits").click(function(){
		    	var taskname=$("#taskname").val();
				var taskcontent=$("#taskcontent").val();
				var tasktype=$("#tasktype option:selected").val();
				if(taskname==""){
					//alert("King");
					$.message.alert("提示","课题题目不能为空","info");
					$("#taskname").focus();
					return false;
				}else{
					if(taskcontent==""){
						$.message.alert("提示","课题内容不能为空","info");
						$("#taskcontent").focus();
						return false;
					}
				}
				
		    }
		    );
		    
		function check(){
			var taskname=$("#taskname").val();
			var taskcontent=$("#taskcontent").val();
			var tasktype=$("#tasktype option:selected").val();
			if(taskname==""){
				//alert("King");
				$.message.alert("提示","课题题目不能为空","info");
				//$("#taskname").focus();
				return false;
			}else{
				if(taskcontent==""){
					$.message.alert("提示","课题内容不能为空","info");
					//$("#taskcontent").focus();
					return false;
				}else{
					return true;
				}
			}
			
			
			
			
		}
		function modifytask(){
			var rows = $('#taskpub-datagrid').datagrid('getSelections');
	  		if (rows.length >1||rows.length==0) {
				$.messager.alert('操作提示', "请选择一条记录！", 'warning');
				return;
			}
	  		$.messager.confirm('确认', '确定要修改吗？', function(r){
				if (r){
					//alert('确认删除');    
					var param = {
							"id":rows[0].id
							
					}
					//alert(param);
					$.ajax({
						type:'post',
						url:'task_modify',
						dataType:"json",    //必须配置
						data:param,//转换成字符串，客户端作为生产者
						success:function(result){
							
							
							if(result.mess=='0000'){
								window.location.href="modifytaskinfo";
							}
							if(result.mess=='8888'){
								$.messager.alert("提示","     课题修改失败！","info");
							}
							if(result.mess=='5555'){
								$.messager.alert("提示","该课题不是您发布的，不能修改！","info");
							}
						} 
					});
				}
	  		});
		}
	</script>
</body>
</html>