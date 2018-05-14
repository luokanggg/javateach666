<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>我的通知</title>
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
        <!-- Begin of toolbar -->
        <div id="#alltonoticeinfo-toolbar">
             <div class="wu-toolbar-button">
              &nbsp;&nbsp;&nbsp;&nbsp; <a href="lookNoReadNotice" class="easyui-linkbutton" iconCls="icon-back" onclick="LookALlToNotice()">返回</a>
                   &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:;"  class="easyui-linkbutton" iconCls="icon-cancel" onclick="DeleteALLReadNoticePL()">批量删除</a> 
            </div> 
		</div>
         <!-- End of toolbar -->
        <table id="alltonoticeinfo-datagrid" toolbar="#alltonoticeinfo-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
		  $('#alltonoticeinfo-datagrid').datagrid({
		        url:'getAllTonoticelist',
		        rownumbers: true,
		        checkbox:true,
		        singleSelect:false,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        multiSort: true,
		        //fitColumns: true,
		        pageList:[10,15,20],
		       // fit: true,
		       striped:true,
		        columns: [[
					{field: 'ck', width:"40" ,align:"center",checkbox: true},
		            {field: 'id', title:'ID',align:"center", width: 40},
		            {field: 'notname', align:"center",title: '通知人姓名', width: 80},
		            {field: 'tonotname', align:"center",title: '被通知人姓名', width: 80},
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
		            {field: 'noturl', align:"center",title: 'url地址', width: 140},
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

		            },
		            
		            {field: 'is_look', align:"center",title: '是否已读', width: 80,
		            	formatter : function(value){
		            		if(value==1){
		            			return "已读";
		            		}else{
		            			return "未读";
		            		}
		            	}	
		            }
				]],
				onLoadSuccess:function(data){   
				}
		    });
		  
		  function DeleteALLReadNoticePL(){
			  var rows = $('#alltonoticeinfo-datagrid').datagrid('getSelections');  
		  		var _list="";
				if (rows.length == 0) {
					$.messager.alert('操作提示', "请至少选择一条记录！", 'warning');
					return;
				}
				for ( var i = 0; i < rows.length; i++) {
					_list+=rows[i].id+",";
				}
				
				$.ajax({
					type:'post',
					url:'deletetonoticepl',
					//dataType:"json",    //必须配置
					data :{'_list':_list},
					success:function(result){
						if(result.mess=='批量删除成功'){
							self.location.reload();
							$.messager.alert("提示","     "+result.mess,"info");
						}else{
							$.messager.alert("提示","     "+result.mess,"info");
						}	
					} 
				});		
		  }
	</script>
</body>
</html>