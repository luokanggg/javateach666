<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>升职请求</title>
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
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" iconAlign="left" onclick="ApproveChoicePL()"
                   >批量通过</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                 <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" iconAlign="left" onclick="DisApprovePL()"
                   >批量驳回</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                  <a href="professionlistAppred" class="easyui-linkbutton" iconCls="icon-search">已审核记录</a>		
            </div> 
		</div>
	
        <table  id="profinfo-datagrid" toolbar="#profinfo-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#profinfo-datagrid').datagrid({
		        url: 'getAllprofessionlistfk',
		        rownumbers: true,
		       // singleSelect: true,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		       // queryParams: formChoiceJson(),
		        multiSort: true,
		      //  fitColumns: true,
		       striped:true,
		       pageList:[10,15,20],
		        //fit: true,
		       // pagePosition:bottom,
		        columns: [[
		            {field: 'ck', width:"40" ,align:"center",checkbox: true},
		            {field: 'id', title: 'ID',align:"center", height:50,width: 40, sortable: true},
		            {field: 'prof_person_id', align:"center",title: '申请人ID', width: 90},
		            {field: 'prof_person_name', align:"center",title: '申请人姓名', width: 100},
		            {field: 'now_prof_saltv', align:"center",title: '现任的职称', width: 110},
		            {field: 'prof_saltv', align:"center",title: '申请的职称', width: 110},
		            {field: 'prof_time', align:"center",title: '申请的时间', width: 150,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }	
		            },
		            {field: 'prof_reason', align:"center",title: '申请的原因', width: 200},
		            {field: 'is_approve', title: '是否审批', width: 120,align:"center",
		            	formatter : function(value,row,index){ 
		                     if(value=='0'){return '未审批'}  
		                     else if(value=='1'){return '审批'}   
		                   }
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.18,formatter:function(value, row, index){  
						
						var str1='<a href="#" name="opera1" class="easyui-linkbutton" onclick="agree()"></a>       <a href="#" name="opera" class="easyui-linkbutton" onclick="disagree()"></a>';  
						return str1;  
					}}
				]],
				onLoadSuccess:function(data){   
					$("a[name='opera1']").linkbutton({text:'通过',plain:true,iconCls:'icon-ok'}); 
					$("a[name='opera']").linkbutton({text:'驳回',plain:true,iconCls:'icon-no'}); 
						
				}
		    });
		  
		    function disagree(){
		  		var rows = $('#profinfo-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		 $.messager.prompt('确认', '请输入驳回原因:', function(r){
					if (r){
						   
						var param = {
								"id":rows[0].id,
								"prof_fk_reason":r
						}
						
						$.ajax({
							type:'post',
							url:'profession_disagree',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								
								
								if(result.mess=='审核成功'){
									self.location.reload();
								}
								
								$.messager.alert("提示","       "+result.mess,"info");
							} 
						});
					}
		  		});
		  	}
		    
		    
		    function agree(){
		  		var rows = $('#profinfo-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		 $.messager.prompt('确认', '请输入通过原因:', function(r){
					if (r){
						   
						var param = {
								"id":rows[0].id,
								"prof_fk_reason":r,
								"prof_saltv":rows[0].prof_saltv
						}
						
						$.ajax({
							type:'post',
							url:'profession_agree',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								
								
								if(result.mess=='审核成功'){
									self.location.reload();
								}
								
								$.messager.alert("提示","       "+result.mess,"info");
							} 
						});
					}
		  		});
		  	}
		    function ApproveChoicePL(){
		    	var selectedRow = $('#profinfo-datagrid').datagrid('getSelections');
				var _list="";
				if (selectedRow.length == 0) {
					$.messager.alert('操作提示', "请至少选择一条记录！", 'warning');
					return;
				}
				for ( var i = 0; i < selectedRow.length; i++) {
					_list+=selectedRow[i].id+",";
				}
				 $.messager.prompt('确认', '请输入通过原因:', function(r){
						if (r){
							
							$.ajax({
								type:'post',
								url:'profession_agree_PL?_list='+_list+"&prof_fk_reason="+r,
								//dataType:"json",    //必须配置
								//data:param,//转换成字符串，客户端作为生产者
								success:function(result){
									
									
									if(result.mess=='审核成功'){
										self.location.reload();
									}
									
									$.messager.alert("提示","       "+result.mess,"info");
								} 
							});
						}
			  		});
		    }
		    
		    function DisApprovePL(){
		    	var selectedRow = $('#profinfo-datagrid').datagrid('getSelections');
				var _list="";
				if (selectedRow.length == 0) {
					$.messager.alert('操作提示', "请至少选择一条记录！", 'warning');
					return;
				}
				for ( var i = 0; i < selectedRow.length; i++) {
					_list+=selectedRow[i].id+",";
				}
				 $.messager.prompt('确认', '请输入驳回原因:', function(r){
						if (r){
							
							$.ajax({
								type:'post',
								url:'profession_disagree_PL?_list='+_list+"&prof_fk_reason="+r,
								//dataType:"json",    //必须配置
								//data:param,//转换成字符串，客户端作为生产者
								success:function(result){
									
									
									if(result.mess=='审核成功'){
										self.location.reload();
									}
									
									$.messager.alert("提示","       "+result.mess,"info");
								} 
							});
						}
			  		});
		    }
	</script>
</body>
</html>