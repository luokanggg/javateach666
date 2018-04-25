<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
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
			                    活动名称：<input class="easyui-textbox" id="choice-course-value"/>
			                    发布人：<input class="easyui-textbox" id="choice-course-value2"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="right" iconCls="icon-ok" onclick="openExportSearch()"
                   plain="true">已选活动</a>
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
		        url: 'getchooseactivitylist',
		        rownumbers: true,
		        singleSelect: true,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            //{field: '', checkbox: true},
		            //{field: 'pubid', hidden :true},
		            {field: 'id', hidden :true},
		            {field: 'pubname', title: '活动名称', width: 50, sortable: true},
		            {field: 'pubownname', title: '发布人', width: 50, sortable: true},
		            {field: 'pubcontent', title: '内容', width: 100, sortable: true},
		            {field: 'pubaddress', title: '地点', width: 180, sortable: true},
		            {field: 'pubnumber', title: '人数', width: 100},
		            {field: 'alpubnumber', title: '已报名', width: 100},
		            {field: 'pubtimestart', title: '开始时间', width: 100,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '年' +m + '月' + d + '日';
	                    }
		            },
		            {field: 'pubtimeend', title: '结束时间', width: 100,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '年' +m + '月' + d + '日';
	                    }
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="sendMessage()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
						$("a[name='opera']").linkbutton({text:'报名',plain:true,iconCls:'icon-edit'});    
				}
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
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
		        var pubname = $("#choice-course-value").val();
		        var pubownname = $("#choice-course-value2").val();
		        //alert(question1);
		        //alert(question2);
		        return {"pubname": pubname,"pubownname": pubownname};
		    }
		  	//导出
		    function openExportSearch() {
			  //alert("导出方法");
			  //var pubname = $("#choice-course-value").val();
		      //var pubownname = $("#choice-course-value2").val();
		      //alert("学号：" + stuno + "学生姓名：" + stuname);
			  window.location.href = "gomychooseactivity";
			  //window.location.href = "exportclassinfo?stuno=" + stuno + "&stuname=" + stuname;
		  	}
		  	//发送消息
		  	function sendMessage(){
		  		//alert("发送消息");
		  		var rows = $('#classinfo-datagrid').datagrid('getSelections');
		  		//alert(rows[0].stuno);
		  		$.messager.confirm('提示', '确认报名？ ', function(r){
					if (r){
						//alert('我说的话: '+rows[0].id);
						var param = {
								"id":rows[0].id,
						}
						$.ajax({
							type:'post',
							url:'chooseactivity',
							contentType:"application/json",    //必须配置
							data:JSON.stringify(param),//转换成字符串，客户端作为生产者
							success:function(result){
								//alert(result.stuimage);
								alert(result.responseDesc);
								window.location.reload(true);
							} 
						}); 
					}
				});
		  	}
	</script>
</body>
</html>