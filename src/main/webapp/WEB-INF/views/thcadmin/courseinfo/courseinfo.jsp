<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
	response.setHeader("X-Frame-Options", "SAMEORIGIN");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header"  content="${_csrf.headerName}"/> --%>
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<title>首页图片管理</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="courseinfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    课程类型：<input class="easyui-combobox" value="--请选择--" id="cctype"/>
			                    课程名称：<input class="easyui-textbox" id="couname"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-add" onclick="addCourse()"
                   plain="true">添加学科</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="delCourses()"
                   plain="true">删除</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="courseinfo-datagrid" toolbar="#courseinfo-toolbar"></table>
    </div>
</div>
<!-- 添加修改页面 -->
<div id="addcourse-dialog" style="width:600px;height:500px; padding:10px;">
    <form id="addcourse-form" method="post" >
    	<input type="hidden" name="id" class="easyui-textbox"/>
        <table style="margin:0 auto; height:250px">
			<tr>
			  <td>课程名称：</td>
			  <td><input class="easyui-textbox" id="couname1" name="couname"/></td>
			</tr>
			<tr>
			  <td>学分：</td>
			  <td><input class="easyui-textbox" id="credit" name="credit" /></td>
			</tr>
			<tr>
			  <td>课程类型：</td>
			  <td><input class="easyui-combobox" value="--请选择--" id="ctype" name="ctype" /></td>
			</tr>
        </table>
    </form>
</div>
	<script type="text/javascript">
	
    //添加时验证输入内容
	$(function(){
	    $("input",$("#couname1").next("span")).blur(function(){
	        //alert("ok");
	    	$("#couname1").textbox('setValue', $(this).val());     //给文本框赋值
	    	var couname = $("#couname1").textbox('getText');     //获取文本框的值
			$.ajax({
				type: 'get',
				url: 'checkCoursename?couname='+couname,
				contentType: 'application/json',
				success: function(result){
					if(result == "OK"){
						$.messager.alert('信息提示', '该课程名已存在！请重新输入');
					}else{
						
					}
				}
			});
	    });
	})
	
		var dtype = '课程类型';
	    $('#ctype').combobox({
	    	url: 'getJoutypeList?dtype='+ dtype,
	    	editable: true,//不可编辑，只能选择
	    	valueField: 'dvalue',
	        textField: 'dicname'}
	    );	
	    $('#cctype').combobox({
	    	url: 'getJoutypeList?dtype='+ dtype,
	    	editable: true,//不可编辑，只能选择
	    	valueField: 'dvalue',
	        textField: 'dicname'}
	    );
			/**
		     * Name 载入数据
		     */
		    $('#courseinfo-datagrid').datagrid({
		        url: 'getcourselist',
		        rownumbers: true,
		        singleSelect: false,
		        checkOnSelect:false,
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            {field: '', checkbox: true, hidden: true},
		            {field: 'id', title: '编号', width: 50, sortable: true, hidden: true},
		            {field: 'couname', title: '课程名称', width: 50, sortable: false},
		            {field: 'dicname', title: '课程类型', width: 50, sortable: false},
		            {field: 'credit', title: '学分', width: 100, sortable: false},
/* 		            {field: 'is_delete', title: '是否已删除', width: 50, sortable: false,
					  	formatter : function(value,row,index){ 
			                 if(value=='0'){return '否'} 
			                 else {return '是'}
		                }	
		            }, */
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="opera" class="easyui-linkbutton" onclick="editCourse()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){  
						$("a[name='opera']").linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});    
				},
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#courseinfo-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		        var ctype = $("#cctype").val();
		        var couname = $("#couname").val();
		        return {"ctype": ctype, "couname": couname};
		    }
		  	/**
		     * Name 删除记录
		     */
		    function delCourses() {
		        var items = $('#courseinfo-datagrid').datagrid('getSelections');
		        if (items.length != 0) {
		            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
		                if (result) {
		                    var ids = [];
		                    $(items).each(function () {
		                        ids.push(this.id)
		                    });
		                    var url = 'delcourses';
		                    $.get(url, {courseids: ids.toString()}, function (data) {
		                        if (data == "OK") {
		                            $.messager.alert('信息提示', '删除成功！', 'info');
		                            $("#courseinfo-datagrid").datagrid("reload");// 重新加载数据库
		                            $('#courseinfo-dialog').dialog('close');
		                        } else if (data == "NO") {
		                            $.messager.alert('信息提示', '删除部分！', 'info');
		                            $('#courseinfo-dialog').dialog('close');
		                        }
		                        else {
		                            $.messager.alert('信息提示', '删除失败！', 'info');
		                        }
		                    });
		                }
		            });
		        }
		    }
		  	function addCourse(){
		  		$('#addcourse-form').form('clear');
		        $('#addcourse-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "添加课程",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addcourse-form").form('submit', {
		                        url: 'addcourse',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '提交成功！');
		                                $("#courseinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addcourse-dialog').dialog('close');
		                            }
		                            else {
		                                $.messager.alert('信息提示', '提交失败！');
		                            }
		                        }

		                    });
		                }
		            }, {
		                text: '取消',
		                iconCls: 'icon-cancel',
		                handler: function () {
		                    $('#addcourse-dialog').dialog('close');
		                }
		            }]
		        });
		  	}
		  	//编辑新闻
		  	function editCourse(){
		  		var rows = $('#courseinfo-datagrid').datagrid('getSelections');
		  		alert(JSON.stringify(rows[0]));
		  		//$('#addnews-form').form('clear');
		        $('#addcourse-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "修改课程信息",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addcourse-form").form('submit', {
		                        url: 'updatecourse',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '修改成功！');
		                                $("#courseinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addcourse-dialog').dialog('close');
		                            }
		                            else {
		                                $.messager.alert('信息提示', '修改失败！');
		                            }
		                        }

		                    });
		                }
		            }, {
		                text: '取消',
		                iconCls: 'icon-cancel',
		                handler: function () {
		                    $('#addcourse-dialog').dialog('close');
		                }
		            }]
		        });
		        $('#addcourse-form').form('load', rows[0]);
		  	}
	</script>
</body>
</html>