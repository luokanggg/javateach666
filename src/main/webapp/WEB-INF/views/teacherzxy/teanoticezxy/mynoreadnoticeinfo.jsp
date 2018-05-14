<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>未阅读通知</title>
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
        <div id="#noreadnoticeinfo-toolbar">
             <div class="wu-toolbar-button">
              <a href="noticeinfo" plain="true" iconCls="icon-back" class="easyui-linkbutton" >返&nbsp;回</a>
              &nbsp;&nbsp;&nbsp;&nbsp; <a href="getAllTonotice" plain="true"  class="easyui-linkbutton" iconCls="icon-search" onclick="LookALlToNotice()"
                   >查看所有通知</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:;" plain="true"  class="easyui-linkbutton" iconCls="icon-edit" onclick="ReadNoticePL()"
                   >批量标志</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:;" plain="true"  class="easyui-linkbutton" iconCls="icon-cancel" onclick="DeleteNOReadNoticePL()">批量删除</a> 
               
            </div> 
		</div>
         <!-- End of toolbar -->
        <table id="noreadnoticeinfo-datagrid" toolbar="#noreadnoticeinfo-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
		  $('#noreadnoticeinfo-datagrid').datagrid({
		        url:'getAllNoReadnoticelist',
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
		       //fit: true,
		       striped:true,
		        columns: [[
		            {field: 'ck', width:"40" ,align:"center",checkbox: true},
		            {field: 'id', title:'ID',align:"center", width: 40},
		            {field: 'notname', align:"center",title: '通知人姓名', width: 80},
		            {field: 'tonotname', align:"center",title: '被通知人姓名', width: 80},
		            {field: 'nottitle', align:"center",title: '标题', width: 135,editor:"text"},
		            {field: 'nottype', align:"center",title: '类型', width: 150, 
		            	formatter : function(value){
		            		if(value==1){
		            			return "老师通知学生";
		            		}else{
		            			return "老师通知老师";
		            		}
		            	}
		            },
		            {field: 'notcontent', align:"center",title: '内容', width: 190,editor:"text"},
		            {field: 'noturl', align:"center",title: 'url地址', width: 100,editor:"text"},
		            {field: 'starttime', align:"center",title: '开始时间', width: 90,
		            	  formatter : function(value){
		                        var date = new Date(value);
		                        var y = date.getFullYear();
		                        var m = date.getMonth() + 1;
		                        var d = date.getDate();
		                        return y + '-' +m + '-' + d;
		            	  }
		            },
		            {field: 'endtime', align:"center", title:'结束时间', width: 90,
		            	  formatter : function(value){
		                        var date = new Date(value);
		                        var y = date.getFullYear();
		                        var m = date.getMonth() + 1;
		                        var d = date.getDate();
		                        return y + '-' +m + '-' + d;
		            	  }

		            },
		            
		            {field: 'is_look', align:"center",title: '是否已读', width: 50,
		            	formatter : function(value){
		            		if(value==1){
		            			return "已读";
		            		}else{
		            			return "未读";
		            		}
		            	}	
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.12,formatter:function(value, row, index){  
						var str = '<a href="#" name="read" class="easyui-linkbutton" onclick="ReadNotice()"></a>'+"  "+'<a href="#" name="delete" onclick="DeleteNOReadNotice()"  class="easyui-linkbutton"></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){   

					$("a[name='read']").linkbutton({text:'标志',plain:true,iconCls:'icon-edit'});
					$("a[name='delete']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
					
				}
		    });
		    
		    function ReadNotice(){
		  		
		  		var rows = $('#noreadnoticeinfo-datagrid').datagrid('getSelections');  
				var param = {"id":rows[0].id};
				$.ajax({
					type:'post',
					url:'read_modify',
					dataType:"json",    //必须配置
					data:param,//转换成字符串，客户端作为生产者
					success:function(result){
						$.messager.alert("提示","     "+result.mess,"info");
					} 
				});				
		  	}
		    
		    
				 function ReadNoticePL(){
		  		
		  		var rows = $('#noreadnoticeinfo-datagrid').datagrid('getSelections');  
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
					url:'read_modify_pl',
					//dataType:"json",    //必须配置
					data :{'_list':_list},
					success:function(result){
						$.messager.alert("提示","     "+result.mess,"info");
						self.location.reload();
					} 
				});				
		  	}
		    
 				function DeleteNOReadNotice(){
		  		
		  		var rows = $('#noreadnoticeinfo-datagrid').datagrid('getSelections');
		  		//alert(rows[0].id+"dddd");
		  		$.messager.confirm('确认', '确定要删除吗？', function(r){
					if (r){   
						var param = {"id":rows[0].id};
						$.ajax({
							type:'post',
							url:'ToNoReadnotice_delete',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								$.messager.alert("提示","       "+result.mess,"info");
							} 
						});
					}
		  		});
		  	} 
 				
 		function DeleteNOReadNoticePL(){
 			var rows = $('#noreadnoticeinfo-datagrid').datagrid('getSelections');  
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