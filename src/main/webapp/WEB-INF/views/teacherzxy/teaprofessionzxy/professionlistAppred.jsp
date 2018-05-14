<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>升职请求记录</title>
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
        <div id="#profinfo-toolbar">
            
               <div class="wu-toolbar-button">
              &nbsp;&nbsp;&nbsp;&nbsp; 
              <a href="ProfessionFK" class="easyui-linkbutton" iconCls="icon-back" >返回</a>
                   &nbsp;&nbsp;&nbsp;&nbsp;
               <a href="javascript:;"  class="easyui-linkbutton" iconCls="icon-cancel" onclick="RemoveChoicePL()">批量删除</a> 
            </div> 
		</div>
	
        <table  id="profinfo-datagrid" toolbar="#profinfo-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#profinfo-datagrid').datagrid({
		        url: 'getAllprofessionlistAppred',
		        rownumbers: true,
		       // singleSelect: true,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		    
		        multiSort: true,
		       // fitColumns: true,
		       striped:true,
		       pageList:[10,15,20],
		        //fit: true,
		       // pagePosition:bottom,
		        columns: [[
		            {field: 'ck', width:"40" ,align:"center",checkbox: true},
		            {field: 'id', title: 'ID',align:"center", height:50,width: 40, sortable: true},
		            {field: 'prof_person_id', align:"center",title: '申请人ID', width: 90},
		            {field: 'prof_person_name', align:"center",title: '申请人姓名', width: 80},
		            {field: 'now_prof_saltv', align:"center",title: '现任的职称', width: 100},
		            {field: 'prof_saltv', align:"center",title: '申请的职称', width: 80},
		            {field: 'prof_time', align:"center",title: '申请的时间', width: 100,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }		
		            },
		            {field: 'prof_reason', align:"center",title: '申请的原因', width:130},
		            {field: 'approve_id', align:"center",title: '审批人ID', width: 70},
		            {field: 'approve_name', align:"center", title:'审批人姓名', width: 90},
		            {field: 'approve_time', align:"center",title: '审批的时间', width: 100,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }			
		            },
		            {field: 'is_prof', title: '是否成功', align:"center",width: 60,align:"center",
		            	 formatter : function(value,row,index){ 
		                     if(value=='0'){return '驳回'}  
		                     else if(value=='2'){return '未知'}   
		                     else if(value=='1'){return '通过'}
		                   }
		            },
		            {field: 'is_approve', title: '是否审批', width: 60,align:"center",
		            	formatter : function(value,row,index){ 
		                     if(value=='0'){return '未审批'}  
		                     else if(value=='1'){return '审批'}   
		                   }
		            },
		            {field: 'prof_fk_reason',title: '反馈原因', width: 138,align:'center'}
				]]
				
		    });
		  function RemoveChoicePL(){
				var selectedRow = $('#profinfo-datagrid').datagrid('getSelections');
				var _list="";
				if (selectedRow.length == 0) {
					$.messager.alert('操作提示', "请至少选择一条记录！", 'warning');
					return;
				}
				for ( var i = 0; i < selectedRow.length; i++) {
					_list+=selectedRow[i].id+",";
				}
				
				$.ajax({
					type:'post',
					url:'NoApprprofession_delete_Pl',
					data :{'_list':_list},
					success:function(result){
						$.messager.alert("提示","       "+result.mess,"info");
						if(result.mess=='删除成功'){
							
							self.location.reload();
						}
					
						
					} 
				});
			}
	</script>
</body>
</html>