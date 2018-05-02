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
			        &nbsp;&nbsp;学号：&nbsp;&nbsp;<input class="easyui-textbox" id="choice-course-value"/>
			        &nbsp;&nbsp;学生姓名：&nbsp;&nbsp;<input class="easyui-textbox" id="choice-course-value2"/>
                    &nbsp;&nbsp;<a id="choice-search-btn" iconCls="icon-search" class="easyui-linkbutton">搜索</a>
                    &nbsp;&nbsp;<a id="choice-search-reset" iconCls="icon-undo" class="easyui-linkbutton">重置</a>
                </form>
                &nbsp;&nbsp;<a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconCls="icon-back" onclick="back()"
                   >返回</a>
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
		        url: 'getclassstudentslist',
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
		            {field: 'stuno', title: '学号', width: 50, sortable: true},
		            {field: 'stuname', title: '学生姓名', width: 50, sortable: true},
		            {field: 'classname', title: '班级名', width: 100, sortable: true},
		            {field: 'college', title: '学院', width: 180, sortable: true},
		            {field: 'major', title: '专业', width: 100},
		            {field: 'classyear', title: '年级', width: 100},
				]],
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
		        var stuno = $("#choice-course-value").val();
		        var stuname = $("#choice-course-value2").val();
		        //alert(question1);
		        //alert(question2);
		        
		        var id = "${requestScope.id}";
		        //alert("id" + id);
		        
		        return {"id": id,"stuno": stuno,"stuname": stuname};
		    }
		    //返回
		    function back(){
		    	window.location.href = "gosemesterteacher";
		    }
	</script>
</body>
</html>