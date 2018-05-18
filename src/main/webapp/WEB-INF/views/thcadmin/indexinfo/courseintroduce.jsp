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
<title>课程介绍管理</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="introduceinfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    课程名称：<input class="easyui-textbox" id="cname"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-add" onclick="addCourseIntroduce()"
                   plain="true">添加课程介绍</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="delCourseIntroduces()"
                   plain="true">删除</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="introduceinfo-datagrid" toolbar="#introduceinfo-toolbar"></table>
    </div>
</div>
<div id="addintroduce-dialog" style="width:600px;height:500px; padding:10px;">
	<form id="addintroduce-form" enctype="multipart/form-data" method = "post">
		<input type="hidden" name="id" class="easyui-textbox"/>
		<table style="margin:0 auto; height:250px;">
		  <tr>
		    <td>课程名：</td>
		    <td><input class="easyui-textbox" id="cname1" name="cname"/></td>
		  </tr>
  		  <tr>
		    <td>课程图片：</td>
		    <td><input type="file" id="file" name="file" /></td>
		  </tr>
		  <tr>
		    <td>课程介绍：</td>
		    <td><textarea id="csign" name="csign" style="word-wrap: break-word" rows="5" cols="40"></textarea></td>
		  </tr>
		</table>
	</form>
</div>
	<script type="text/javascript">
	
	//添加时验证输入内容
	$(function(){
	    $("input",$("#cname1").next("span")).blur(function(){
	        //alert("ok");
	    	$("#cname1").textbox('setValue', $(this).val());     //给文本框赋值
	    	var cname = $("#cname1").textbox('getText');     //获取文本框的值
			$.ajax({
				type: 'get',
				url: 'checkCname?cname='+cname,
				contentType: 'application/json',
				success: function(result){
					if(result == "OK"){
						$.messager.alert('信息提示', '该课程名已存在！请重新输入');
					}else{
						
					}
				}
			});
	    });
	});
	
		
			/**
		     * Name 载入数据
		     */
		    $('#introduceinfo-datagrid').datagrid({
		        url: 'getcourseintroducelist',
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
		            {field: 'cname', title: '课程名称', width: 50, sortable: true},
		            {field: 'cimg', title: '图片', width: 50, sortable: false,formatter: function(value,row,index){
		            	var myurl = "${pageContext.request.contextPath}/"+value;
		            	var str = '<img width="100px" height="60px" border="0" src="'+ myurl +'"/>';
	                    return str;    
	                }},
		            {field: 'csign', title: '课程介绍', width: 200, sortable: false,formatter: function(value,row,index){
		            	var str = '<textarea style="word-wrap: break-word" rows="5" cols="80" readonly>' + value + '</textarea>';
	                    return str;    
	                }},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="edit" class="easyui-linkbutton" onclick="editIntroduce()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
					$("a[name='edit']").linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
				},
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#introduceinfo-datagrid').datagrid({
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
		        var cname = $("#cname").val();
		        return {"cname": cname};
		    }
		    /**
		     * Name 删除记录
		     */
		    function delCourseIntroduces() {
		        var items = $('#introduceinfo-datagrid').datagrid('getSelections');
		        if (items.length != 0) {
		            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
		                if (result) {
		                    var ids = [];
		                    $(items).each(function () {
		                        ids.push(this.id)
		                    });
		                    var url = 'delcourseintroduces';
		                    $.get(url, {courseintroducesids: ids.toString()}, function (data) {
		                        if (data == "OK") {
		                            $.messager.alert('信息提示', '删除成功！', 'info');
		                            $("#introduceinfo-datagrid").datagrid("reload");// 重新加载数据库
		                            $('#addintroduce-dialog').dialog('close');
		                        } else if (data == "NO") {
		                            $.messager.alert('信息提示', '删除部分！', 'info');
		                            $('#addintroduce-dialog').dialog('close');
		                        }
		                        else {
		                            $.messager.alert('信息提示', '删除失败！', 'info');
		                        }
		                    });
		                }
		            });
		        }
		    }
		    //添加课程简介
		  	function addCourseIntroduce() {
		  		$('#addintroduce-form').form('clear');
		        $('#addintroduce-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "添加图片",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addintroduce-form").form('submit', {
		                        url: 'addcourseintroduce',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '提交成功！');
		                                $("#introduceinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addintroduce-dialog').dialog('close');
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
		                    $('#addintroduce-dialog').dialog('close');
		                }
		            }]
		        });
		  	}
		  	//编辑课程简介
		  	function editIntroduce(){
		  		var rows = $('#introduceinfo-datagrid').datagrid('getSelections');
		  		alert(JSON.stringify(rows[0]));
		        $('#addintroduce-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "修改课程简介",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addintroduce-form").form('submit', {
		                        url: 'updatecourseintroduce',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '修改成功！');
		                                $("#introduceinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addintroduce-dialog').dialog('close');
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
		                    $('#addintroduce-dialog').dialog('close');
		                }
		            }]
		        });
		        $('#addintroduce-form').form('load', rows[0]);
		  	}
	</script>
</body>
</html>