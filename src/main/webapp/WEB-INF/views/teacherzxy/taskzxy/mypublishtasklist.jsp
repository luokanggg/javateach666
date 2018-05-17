<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>我发布课题</title>
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
        <div id="#mypubtask-toolbar">
            <div class="wu-toolbar-button">
                
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			     课题类型：<select id="tasktype" class="easyui-combobox" name="tasktype" style="width:120px;">   
    					<c:forEach items="${tasktype}" var="type">
    						<option  value="${type.dicname }" >${type.dicname}</option>
    					</c:forEach>   
    			   </select>&nbsp;&nbsp;&nbsp;&nbsp;
                 <a id="choice-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                 &nbsp;&nbsp;&nbsp;&nbsp; <a id="choice-search-reset" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
            </div>
		</div>
         <!-- End of toolbar -->
         <div style="width:1343px;height:420px;">
        <table  id="mypubtask-datagrid" toolbar="#mypubtask-toolbar"></table> 
        </div> 
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#mypubtask-datagrid').datagrid({
		        url: 'getMypublishTask',
		        // rownumbers: true,
		        singleSelect: true,
		        pageSize: 10,
		        pagination: true,
		       queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            {field: 'id', title: '编号',align:"center", height:50,width: 30, sortable: true},
		            {field: 'taskname', align:"center",title: '课程名称', width: 70},
		            {field: 'tasktype', align:"center",title: '课程类型', width: 50},
		            {field: 'taskcontent', align:"center",title: '课程内容', width: 240},
		            {field: 'taskpubtime', align:"center",title: '发布时间', width: 60,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }		
		            },
		            {field: 'taskpublisher', align:"center",title: '发布人', width: 70},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="deletetask()"></a>'; 
						var str1='    ';
						var str2='<a href="#" name="opera1" class="easyui-linkbutton" onclick="modifytask()"></a>';
						return str+str1+str2;  
					}}
				]],
				onLoadSuccess:function(data){   
				//	alert("成功");
						$("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'}); 
						$("a[name='opera1']").linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'}); 
						
				}
		    });
		  /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#mypubtask-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        
		        $('#mypubtask-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		    	var tasktype=$("#tasktype option:selected").val();
		        return {"tasktype": tasktype};
		    }
			function deletetask(){
		  		
		  		var rows = $('#mypubtask-datagrid').datagrid('getSelections');
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
								if(result.mess=='1111'){
									$.messager.alert("提示","     该课题已经被选择且审批通过不可以删除！","info");
								}
							} 
						});
					}
		  		});
		  	}
		function modifytask(){
	var rows = $('#mypubtask-datagrid').datagrid('getSelections');
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