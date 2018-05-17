<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>课题选择情况</title>
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
        <div id="#looktask-toolbar">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            
           <form id="choice-search-form" style="display: inline-block" >
			     课题类型：<select id="tasktype" class="easyui-combobox" name="tasktype" style="width:120px;">   
    					<c:forEach items="${tasktype}" var="type">
    						<option  value="${type.dicname }" >${type.dicname}</option>
    					</c:forEach>   
    			   </select>&nbsp;&nbsp;&nbsp;&nbsp;
			 课题发布者:<input class="easyui-textbox" id="taskpublisher" name="taskpublisher" data-options="multiline:true,prompt:'Enter a task taskpublisher'"  style="width:180px;height:25px" />&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp; &nbsp;&nbsp;          
                    <a id="choice-search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>&nbsp;&nbsp;&nbsp;
                  &nbsp;   <a id="choice-search-reset" class="easyui-linkbutton" iconCls="icon-redo">重置</a>&nbsp;&nbsp;&nbsp;
                </form>
		</div>
         <!-- End of toolbar -->
        <table  id="looktask-datagrid" toolbar="#looktask-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#looktask-datagrid').datagrid({
		        url: 'getTaskChoiceinfo',
		        rownumbers: true,
		        singleSelect: true,
		        pageSize: 10,
		        pagination: true,
		        multiSort: true,
		        fitColumns: true,
		        queryParams: formChoiceJson(),
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
		            {field: 'taskpubtime', align:"center",title: '发布时间', width: 80,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }	
		            },
		            {field: 'taskpublisher', align:"center",title: '发布人', width: 70},
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
		            {field: 'checker', align:"center",title: '审批人', width: 80},
		            {field: 'teaname', align:"center",title: '选题者', width: 80},
		            {field: 'rank', align:"center",title: '等级', width: 80}
				]],
				onLoadSuccess:function(data){   
					
				}
		    });
		  $("#looktask-datagrid").datagrid("hideColumn", "id");
		  /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#looktask-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $("#taskpublisher").val('');
		        $('#looktask-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		        var taskpublisher = $("#taskpublisher").val();
		        var tasktype = $("#tasktype option:selected").val();
		        return {"taskpublisher": taskpublisher,"tasktype": tasktype};
		    }
	</script>
</body>
</html>