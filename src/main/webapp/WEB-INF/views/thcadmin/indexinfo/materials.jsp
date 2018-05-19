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
<title>学习资源管理</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="materialsinfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    资源名称：<input class="easyui-textbox" id="mtitle"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-add" onclick="addMaterials()"
                   plain="true">添加学习资源</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="delMaterialss()"
                   plain="true">删除</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="materialsinfo-datagrid" toolbar="#materialsinfo-toolbar"></table>
    </div>
</div>
<div id="addmaterials-dialog" style="width:600px;height:500px; padding:10px;">
	<form id="addmaterials-form" enctype="multipart/form-data" method = "post">
		<input type="hidden" name="id" class="easyui-textbox"/>
		<table style="margin:0 auto; height:250px;">
		  <tr>
		    <td>资源名称：</td>
		    <td><input class="easyui-textbox" id="mtitle1" name="mtitle"/></td>
		  </tr>
  		  <tr>
		    <td>上传资源：</td>
		    <td><input type="file" id="file" name="file" /></td>
		  </tr>
		</table>
	</form>
</div>
	<script type="text/javascript">
	
	//添加时验证输入内容
	$(function(){
	    $("input",$("#mtitle1").next("span")).blur(function(){
	        //alert("ok");
	    	$("#mtitle1").textbox('setValue', $(this).val());     //给文本框赋值
	    	var mtitle = $("#mtitle1").textbox('getText');     //获取文本框的值
			$.ajax({
				type: 'get',
				url: 'checkMtitle?mtitle='+mtitle,
				contentType: 'application/json',
				success: function(result){
					if(result == "OK"){
						$.messager.alert('信息提示', '该学习资源已存在！请重新输入');
					}else{
						
					}
				}
			});
	    });
	});
		
			/**
		     * Name 载入数据
		     */
		    $('#materialsinfo-datagrid').datagrid({
		        url: 'getmaterialslist',
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
		            {field: 'mtitle', title: '资源名称', width: 50, sortable: true},
		            {field: 'murl', title: '资源地址', width: 200, sortable: false},
		            {field: 'mtime', title: '上传时间', width: 100, sortable: false, formatter: function (value, row, index) {
		            	if(value != null) {
			            	var date = new Date(value);
			            	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		            	} else {
		            		return "";
		            	}
		            }},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="edit" class="easyui-linkbutton" onclick="editMaterials()" ></a>';  
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
		        $('#materialsinfo-datagrid').datagrid({
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
		        var mtitle = $("#mtitle").val();
		        return {"mtitle": mtitle};
		    }
		    /**
		     * Name 删除记录
		     */
		    function delMaterialss() {
		        var items = $('#materialsinfo-datagrid').datagrid('getSelections');
		        if (items.length != 0) {
		            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
		                if (result) {
		                    var ids = [];
		                    $(items).each(function () {
		                        ids.push(this.id)
		                    });
		                    var url = 'delmaterials';
		                    $.get(url, {materialsids: ids.toString()}, function (data) {
		                        if (data == "OK") {
		                            $.messager.alert('信息提示', '删除成功！', 'info');
		                            $("#materialsinfo-datagrid").datagrid("reload");// 重新加载数据库
		                            $('#addmaterials-dialog').dialog('close');
		                        } else if (data == "NO") {
		                            $.messager.alert('信息提示', '删除部分！', 'info');
		                            $('#addmaterials-dialog').dialog('close');
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
		  	function addMaterials() {
		  		$('#addmaterials-form').form('clear');
		        $('#addmaterials-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "添加图片",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addmaterials-form").form('submit', {
		                        url: 'addmaterials',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '提交成功！');
		                                $("#materialsinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addmaterials-dialog').dialog('close');
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
		                    $('#addmaterials-dialog').dialog('close');
		                }
		            }]
		        });
		  	}
		  	//编辑课程简介
		  	function editMaterials(){
		  		var rows = $('#materialsinfo-datagrid').datagrid('getSelections');
		  		//alert(JSON.stringify(rows[0]));
		        $('#addmaterials-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "修改新闻",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addmaterials-form").form('submit', {
		                        url: 'updatematerials',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '修改成功！');
		                                $("#materialsinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addmaterials-dialog').dialog('close');
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
		                    $('#addmaterials-dialog').dialog('close');
		                }
		            }]
		        });
		        $('#addmaterials-form').form('load', rows[0]);
		  	}
	</script>
</body>
</html>