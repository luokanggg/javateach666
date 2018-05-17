<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>我的课题选择情况</title>
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
        <div id="#lookmytask-toolbar">
		</div>
         <!-- End of toolbar -->
        <table  id="lookmytask-datagrid" toolbar="#lookmytask-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#lookmytask-datagrid').datagrid({
		        url: 'getMyTaskChoiceinfo',
		        rownumbers: true,
		        singleSelect: true,
		        pageSize: 10,
		        pagination: true,
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            {field: 'id', align:"center",title: '课程ID', width: 20},
		            {field: 'taskname', align:"center",title: '课程名称', width: 100},
		            {field: 'tasktype', align:"center",title: '课程类型', width: 80},
		            {field: 'taskcontent', align:"center",title: '课程内容', width: 200,
		            	 formatter: function(value,row,index){
		                      return '<span  title='+value+'>'+value+'</span>'  
		            	 } 	
		            },
		            {field: 'teaname', align:"center",title: '选题者', width: 80},
		            {field: 'rank', align:"center",title: '等级', width: 80},
		           
		            {field: 'taskpublisher', align:"center",title: '发布人', width: 70},
		            {field: 'taskpubtime', align:"center",title: '发布时间', width: 80,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }	
		            },
		            {field: 'checker', align:"center",title: '审批人', width: 80},
		            {field: 'checktime', align:"center",title: '审批时间', width: 80,
		            	 
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
		            
		            {field: 'checkstatus', align:"center",title: '审批状态', width: 70,
		            	formatter : function(value,row,index){ 
		                     if(value=='0'){return '未知'}  
		                     else if(value=='2'){return '驳回'}   
		                     else if(value=='1'){return '通过'}
		                   }	
		            },
		            {field: 'ischeck', align:"center",title: '是否审批', width: 70,
		            	formatter : function(value,row,index){ 
		                     if(value=='0'){return '未审批'}  
		                     else{return '审批'}   
		                   }	
		            },
		            
		           
		           
		          
		           
					{field: 'operate', title: '操作', align:'center',width:$(this).width()*0.12,formatter:function(value, row, index){  
						
						var str1='<a href="#" name="opera1" class="easyui-linkbutton" onclick="agree()"></a>       <a href="#" name="opera" class="easyui-linkbutton" onclick="disagree()"></a>';  
						return str1;  
					}}
				]],
				onLoadSuccess:function(data){   
					$("a[name='opera1']").linkbutton({text:'通过',plain:true,iconCls:'icon-ok'}); 
					$("a[name='opera']").linkbutton({text:'驳回',plain:true,iconCls:'icon-cancel'}); 
						
				}
		    });
		  $("#lookmytask-datagrid").datagrid("hideColumn", "id");
		  
		  function disagree(){
		  		var rows = $('#lookmytask-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		/* alert(rows[0].id+"  "+rows[0].ischeck+"  "+rows[0].taskpublisher); */
		  		 $.messager.confirm('确认', '确认要驳回吗？', function(r){
					if (r){
						   
						var param = {
								"id":rows[0].id,
								"ischeck":rows[0].ischeck,
								"taskpublisher":rows[0].taskpublisher
						}
						
						$.ajax({
							type:'post',
							url:'task_disagree',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								if(result.mess=='0000'){
									$.messager.alert("提示","审批操作成功","info");
									//self.location.reload();
								}else if(result.mess=='8888'){
									$.messager.alert("提示","该课题已经被审批，不能再执行审批操作","info");
								}else if(result.mess=='1111'){
									$.messager.alert("提示","该课题不是您发布的，不能执行审批操作","info");
								}else if(result.mess=='9999'){
									$.messager.alert("提示","审批操作失败","info");
								}
							
							} 
						});
					}
		  		});
		  	}
		    
		    
		    function agree(){
		  		var rows = $('#lookmytask-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		 $.messager.confirm('确认', '确定审批通过吗?', function(r){
					if (r){
						   
						var param = {
								"id":rows[0].id,
								"ischeck":rows[0].ischeck,
								"taskpublisher":rows[0].taskpublisher
						}
						
						$.ajax({
							type:'post',
							url:'task_agree',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								
								if(result.mess=='0000'){
									$.messager.alert("提示","审批操作成功","info");
									//self.location.reload();
								}else if(result.mess=='8888'){
									$.messager.alert("提示","该课题已经被审批，不能再执行审批操作","info");
								}else if(result.mess=='1111'){
									$.messager.alert("提示","该课题不是您发布的，不能执行审批操作","info");
								}else if(result.mess=='9999'){
									$.messager.alert("提示","审批操作失败","info");
								}
							} 
						});
					}
		  		});
		  	}
	</script>
</body>
</html>