<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>通知记录</title>
<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/demo/demo.css">
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
select,#starttime,#endtime{

font-size:13px;	
border-radius:3px;
border:1px solid #c9dffd;
}
</style>
<body id="bods">
	<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="#noticeinfo-toolbar">
            <div class="wu-toolbar-button">
              <!--  <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openAddChoice()"
                   plain="true">添加</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditChoice()"
                   plain="true">修改</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="openRemoveChoice()"
                   plain="true">删除</a> -->
                
                <form id="choice-search-form" style="display: inline-block">
			                   &nbsp;&nbsp;&nbsp;通知人姓名：<input class="easyui-textbox" style="height:25px;border-color: pink" data-options="prompt:'Enter a notice name...'" id="notname"/>
			                   &nbsp;&nbsp;&nbsp; 被通知人姓名：<input class="easyui-textbox" style="height:25px;border-color: pink" data-options="prompt:'Enter a tonotice name...'" id="tonotname"/>
			                 &nbsp;&nbsp;&nbsp;   
			                 通知类型：<select id="nottype" style="padding-left:18px;width:130px;height:25px;" >
						<option  value="1" >老师to学生</option>
						<option  value="4" >老师to老师</option>
		    			</select>
			                 &nbsp;&nbsp;&nbsp;   通知时间：<input type="date" id="starttime" style="height:23px"/>
			           —— <input type="date" id="endtime" style="height:23px"/> &nbsp;&nbsp;&nbsp;
                    <a id="choice-search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>&nbsp;&nbsp;&nbsp;
                    <a id="choice-search-reset" class="easyui-linkbutton" iconCls="icon-undo">重置</a>
                </form>
            </div>
		</div>
         <!-- End of toolbar -->
        <table id="noticeinfo-datagrid" toolbar="#noticeinfo-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
		  $('#noticeinfo-datagrid').datagrid({
		        url:'getAllnoticelist',
		        rownumbers: true,
		        singleSelect: true,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		        striped:true,
		       // fitColumns: true,
		        pageList:[10,15,20],
		       // fit: true,
		        columns: [[
		            {field: 'id', title:'ID',align:"center", width: 40, sortable: true},
		            {field: 'notname', align:"center",title: '通知人姓名', width: 80},
		            {field: 'tonotname', align:"center",title: '被通知人姓名', width: 80},
		            {field: 'nottitle', align:"center",title: '标题', width: 140, editor:'text'},
		            {field: 'nottype', align:"center",title: '类型', width: 140, editor:'text',
		            	formatter : function(value){
		            		if(value==1){
		            			return "老师通知学生";
		            		}else{
		            			return "老师通知老师";
		            		}
		            	}
		            },
		            {field: 'notcontent', align:"center",title: '内容', width: 250,editor:'text'},
		            {field: 'noturl', align:"center",title: 'url地址', width: 120,editor:'text'},
		            {field: 'starttime', align:"center",title: '开始时间', width: 90, 
		            	  formatter : function(value){
		                        var date = new Date(value);
		                        var y = date.getFullYear();
		                        var m = date.getMonth() + 1;
		                        var d = date.getDate();
		                        return y + '-' +m + '-' + d;
		            	  }
		            },
		            {field: 'endtime', align:"center", title:'结束时间', width: 90,editor:'text',
		            	  formatter : function(value){
		                        var date = new Date(value);
		                        var y = date.getFullYear();
		                        var m = date.getMonth() + 1;
		                        var d = date.getDate();
		                        return y + '-' +m + '-' + d;
		            	  }

		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.12,formatter:function(value, row, index){  
						var str = '<a href="#" name="modify" class="easyui-linkbutton" onclick="ModifyNotice()"></a>'+"  "+'<a href="#" name="delete" onclick="DeleteNotice()"  class="easyui-linkbutton"></a>';  
						return str;  
					}} 
		       
				]] ,
				onLoadSuccess:function(data){   
					$("a[name='modify']").linkbutton({text:'Edit',plain:true,iconCls:'icon-edit'});
					$("a[name='delete']").linkbutton({text:'Remove',plain:true,iconCls:'icon-remove'});	
				} 
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#noticeinfo-datagrid').datagrid({ 
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $('#notname').val("");
		        $('#tonotname').val("");
		        $('#starttime').val("");
		        $('#endtime').val("");
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		        var notname = "";
		        notname=$("#notname").val();
		        var tonotname = "";
		        tonotname =$("#tonotname").val();
		        var endtime="";
		        endtime=$("#endtime").val();
		        var starttime="";
		        starttime=$("#starttime").val();
		        var nottype="1";
		        nottype=$("#nottype option:selected").val();
			//alert(notname+"  "+notname+"  "+tonotname);
		        return {
		        	"notname": notname,
		        	"tonotname": tonotname,
		        	"starttime":starttime,
		        	"endtime":endtime,
		        	"nottype":nottype
		        };
		    }
	
		     
			
		    function ModifyNotice(){
		  		
		  		var rows = $('#noticeinfo-datagrid').datagrid('getSelections');  
				var param = {"id":rows[0].id};
				$.ajax({
					type:'post',
					url:'notice_modify',
					dataType:"json",    //必须配置
					data:param,//转换成字符串，客户端作为生产者
					success:function(result){
						if(result.mess=="该通知不是您发布，不可以编辑"){
							$.messager.alert("警告","       "+result.mess,"warning");
						}else{
							var href=result.mess;
							window.location.href="http://localhost:8089/javateach666/"+href;
						}
					
					} 
				});				
		  	}
		    
 				function DeleteNotice(){
		  		
		  		var rows = $('#noticeinfo-datagrid').datagrid('getSelections');
		  		//alert(rows[0].id+"dddd");
		  		$.messager.confirm('确认', '确定要删除吗？', function(r){
					if (r){
						//alert('确认删除');    
						var param = {
								"id":rows[0].id
						}
						//alert(param);
						$.ajax({
							type:'post',
							url:'notice_delete',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								if(result.mess=='该通知不是您发布，不可以删除'||result.mess=='该通知不可以执行逻辑删除！'){
									$.messager.alert("警告","       "+result.mess,"warning");
								}else if(result.mess=='删除失败'){
									$.messager.alert("警告","       "+result.mess,"error");
								}else{
									$.messager.alert("提示","       "+result.mess,"info");
									self.location.reload();
								}
								
							} 
						});
					}
		  		});
		  	}
	</script>
</body>
</html>