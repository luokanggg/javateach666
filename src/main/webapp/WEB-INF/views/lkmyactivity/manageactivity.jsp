<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/lklist.css"/>
<title>我的班级</title>
<!-- <style type="text/css">
	#classinfo-datagrid tr{height:40px;}
</style> -->
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="classinfo-toolbar">
            <div class="wu-toolbar-button">
                <!-- <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openAddChoice()"
                   plain="true">添加</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditChoice()"
                   plain="true">修改</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="openRemoveChoice()"
                   plain="true">删除</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" onclick="openImportChoice()"
                   plain="true">导入</a> -->
                <form id="choice-search-form" style="display: inline-block">
                	&nbsp;&nbsp;活动名：&nbsp;&nbsp;<input id="cc" name="dept" value="--请选择--"> 
                    &nbsp;&nbsp;<a id="choice-search-btn" iconCls="icon-search" class="easyui-linkbutton">查看</a>
                </form>
                &nbsp;&nbsp;<a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="right" iconCls="icon-cancel" onclick="openExportSearch()"
                   >踢出</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="classinfo-datagrid" toolbar="#classinfo-toolbar"></table>
    </div>
</div>
	<script type="text/javascript">
		
			/**
		     * Name 载入数据
		     */
		    $('#classinfo-datagrid').datagrid({
		        url: 'getmanageactivitylist',
		        rownumbers: true,
		        singleSelect: true,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		        striped:true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            //{field: '', checkbox: true},
		            //{field: 'pubid', hidden :true},
		            {field: 'id', hidden :true},
		            {field: 'stuid', hidden :true},
		            {field: 'stuno', hidden :true},
		            {field: 'stuname', title: '报名人姓名', width: 50, sortable: true},
		            {field: 'pubcontent', title: '内容', width: 180, sortable: true},
		            {field: 'pubaddress', title: '地点', width: 100, sortable: true},
		            {field: 'classname', title: '班级', width: 100},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="sendMessage()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
						$("a[name='opera']").linkbutton({text:'联系他',plain:true,iconCls:'icon-tip'});    
				}
		    });
			
		    $('#cc').combobox({    
		        url:'mypubactivitynamelist',    
		        valueField:'id',    
		        textField:'pubname'   
		    }); 
			
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		    	/* alert("查看");
		    	var id = $("input[type='hidden'],input[name='dept']").val();
		    	alert("id" + id); */
		    	
		        //点击搜索
		        $('#classinfo-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $("#choice-course-value2").val('');
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		    	var id = $("input[type='hidden'],input[name='dept']").val();
		        return {"id": id};
		    }
		  	//导出
		    function openExportSearch() {
		    	var rows = $('#classinfo-datagrid').datagrid('getSelections');
		  		//alert(rows[0].stuno);
		  		if(null == rows[0].id){
		  			alert("请选择一个成员踢出！");
		  			return false;
		  		}
		  		$.messager.prompt('消息框', '踢出原因', function(message){
					if (message){
						//alert(message);
						//alert('我说的话: '+message);
						var param = {
								"id":rows[0].id,
								"stuid":rows[0].stuid,
								"message":message
						}
						$.ajax({
							type:'post',
							url:'moveactivitymember',
							contentType:"application/json",    //必须配置
							data:JSON.stringify(param),//转换成字符串，客户端作为生产者
							success:function(result){
								//alert(result.stuimage);
								alert(result.responseDesc);
								$("#classinfo-datagrid").datagrid("reload");
							} 
						});
					}
				});
		  	}
		  	//发送消息
		  	function sendMessage(){
		  		//alert("发送消息");
		  		var rows = $('#classinfo-datagrid').datagrid('getSelections');
		  		//alert(rows[0].stuno);
		  		$.messager.prompt('消息框', '说点什么', function(message){
					if (message){
						//alert(message);
						//alert('我说的话: '+message);
						var param = {
								"stuno":rows[0].stuno,
								"message":message
						}
						$.ajax({
							type:'post',
							url:'sendmessagetostu',
							contentType:"application/json",    //必须配置
							data:JSON.stringify(param),//转换成字符串，客户端作为生产者
							success:function(result){
								//alert(result.stuimage);
								alert(result.responseDesc);
							} 
						});
					}
				});
		  	}
	</script>
</body>
</html>