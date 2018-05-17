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
</style>

<body>
	<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="#taskchoice-toolbar">
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
        <table  id="taskchoice-datagrid" toolbar="#taskchoice-toolbar"></table> 
        </div> 
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#taskchoice-datagrid').datagrid({
		        url: 'getAllpublishTaskchoice',
		        // rownumbers: true,
		        singleSelect: true,
		        pageSize: 10,
		        pagination: true,
		       queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            {field: 'id', title: 'ID',align:"center", height:50,width: 30, sortable: true},
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
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.06,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="choicetask()"></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){   
				//	alert("成功");
						$("a[name='opera']").linkbutton({text:'选择',plain:true,iconCls:'icon-ok'}); 
						
				}
		    });
		  /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#taskchoice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        
		        $('#taskchoice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		    	var tasktype=$("#tasktype option:selected").val();
		        return {"tasktype": tasktype};
		    }
		    function choicetask(){
		  		
		  		var rows = $('#taskchoice-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		$.messager.confirm('确认', '确定要选择吗？', function(r){
					if (r){
						 
						var param = {
								"id":rows[0].id
								
						}
						//alert(param);
						$.ajax({
							type:'post',
							url:'task_choice',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								if(result.mess=='0000'){
									$.messager.alert("提示","     课题选择成功！","info");
								}
								if(result.mess=='1111'){
									$.messager.alert("提示","     课题选择失败！","info");
								}
								if(result.mess=='8888'){
									$.messager.alert("提示","您之前选择的课题已经被审批通过，不能在选择了！","info");
								}
							} 
						});
					}
		  		});
		  	}
	
	</script>
</body>
</html>