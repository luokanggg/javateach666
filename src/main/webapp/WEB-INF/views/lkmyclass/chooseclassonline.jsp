<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>网上选课</title>
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
			                    教师姓名：<input class="easyui-textbox" id="choice-course-value"/>
			                    课程名字：<input class="easyui-textbox" id="choice-course-value2"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a class="easyui-linkbutton" iconAlign="right" iconCls="icon-ok" onclick="goAlreadyChoose()">查看我的已选课程</a>
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
		        url: 'getchooseclassonlinelist',
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
		            {field: 'id', hidden :true},
		            {field: 'couname', title: '课程名称', width: 50, sortable: true},
		            {field: 'teaname', title: '教师姓名', width: 50, sortable: true},
		            {field: 'couaddress', title: '上课地点', width: 100, sortable: true},
		            {field: 'coutime', title: '上课时间', width: 180, sortable: true},
		            {field: 'counumber', title: '课程容量', width: 100},
		            {field: 'alcounumber', title: '已报名人数', width: 100},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="chooseClass()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
						$("a[name='opera']").linkbutton({text:'选课',plain:true,iconCls:'icon-edit'});    
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
		        var teaname = $("#choice-course-value").val();
		        var couname = $("#choice-course-value2").val();
		        //alert(question1);
		        //alert(question2);
		        return {"teaname": teaname,"couname": couname};
		    }
		    //选课
		    function chooseClass() {
		    	alert("选课");
		    	var rows = $('#classinfo-datagrid').datagrid('getSelections');
		    	alert("id:" + rows[0].id + "teaname:" + rows[0].teaname);
		    	var param = {
						"teacourseid":rows[0].id,
				}
				$.ajax({
					type:'post',
					url:'chooseclass',
					contentType:"application/json",    //必须配置
					data:JSON.stringify(param),//转换成字符串，客户端作为生产者
					success:function(result){
						//alert(result.stuimage);
						alert(result.responseDesc);
					} 
				});
		    }
		  	//查看我的已选课程
		    function goAlreadyChoose() {
		  		alert("查看已选课程");
				window.location.href = "goalreadychoosepage";
		  	}
	</script>
</body>
</html>