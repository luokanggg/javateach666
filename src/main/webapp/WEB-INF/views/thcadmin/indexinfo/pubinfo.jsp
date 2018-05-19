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
        <div id="pubinfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    公告标题：<input class="easyui-textbox" id="pubtitle"/>
			                    公告内容：<input class="easyui-textbox" id="pubcontent"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-add" onclick="addPubs()"
                   plain="true">添加公告</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="delPubs()"
                   plain="true">删除</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="pubinfo-datagrid" toolbar="#pubinfo-toolbar"></table>
    </div>
</div>
<!-- 添加修改页面 -->
<div id="addpubs-dialog" style="width:600px;height:500px; padding:10px;">
    <form id="addpubs-form" method="post" >
    	<input type="hidden" name="id" class="easyui-textbox"/>
        <table style="margin:0 auto; height:250px">
        	<tr>
        		<td>标题</td>
        		<td><input class="easyui-textbox" id="joutitle" name="joutitle"/></td>
        	</tr>
    	    <tr>
    	    	<td>公告类型</td>
    	    	<td><input class="easyui-combobox" value="--请选择--" id="joutype" name="joutype"/></td>
    	    </tr>
        	<tr>
        		<td>公告内容</td>
        		<td><textarea id="joucontent" name="joucontent" rows="10" cols="40"></textarea></td>
        	</tr>
        </table>
    </form>
</div>
	<script type="text/javascript">
	
	//添加时验证输入内容
	$(function(){
	    $("input",$("#joutitle").next("span")).blur(function(){
	        //alert("ok");
	    	$("#joutitle").textbox('setValue', $(this).val());     //给文本框赋值
	    	var title = $("#joutitle").textbox('getText');     //获取文本框的值
			$.ajax({
				type: 'get',
				url: 'checkTitle?title='+title,
				contentType: 'application/json',
				success: function(result){
					if(result == "OK"){
						$.messager.alert('信息提示', '该公告已存在！请重新输入');
					}else{
						
					}
				}
			});
	    });
	});
	
			var dtype = '公告类型';
		    $('#joutype').combobox({
		    	url: 'getJoutypeList?dtype='+ dtype,
		    	editable: true,//不可编辑，只能选择
		    	valueField: 'dvalue',
		        textField: 'dicname'}
		    );	
		
			/**
		     * Name 载入数据
		     */
		    $('#pubinfo-datagrid').datagrid({
		        url: 'getpublist',
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
		            {field: 'joutitle', title: '标题', width: 300, sortable: false},
		            {field: 'joucontent', title: '内容', width: 500, sortable: false,formatter: function(value,row,index){
		            	var str = '<textarea style="word-wrap: break-word" rows="5" cols="80" readonly>' + value + '</textarea>';
	                    return str;    
	                }},
		            {field: 'dicname', title: '公告类型', width: 50, sortable: false},
		            {field: 'starttime', title: '创建时间', width: 50, sortable: false, formatter: function (value, row, index) {
		            	if(value != null) {
			            	var date = new Date(value);
			            	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		            	} else {
		            		return "";
		            	}
		            }},
		            {field: 'endtime', title: '过期时间', width: 50, sortable: false, formatter: function (value, row, index) {
		            	if(value != null) {
			            	var date = new Date(value);
			            	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		            	} else {
		            		return "";
		            	}
		            }},
		            {field: 'adminname', title: '发布人', width: 50, sortable: false},
		            {field: 'operate', title: '操作', align:'center',width:50,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="edit" class="easyui-linkbutton" onclick="editPubs()" ></a>';  
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
		        $('#pubinfo-datagrid').datagrid({
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
		        var pubtitle = $("#pubtitle").val();
		        var pubcontent = $("#pubcontent").val();
		        return {"joutitle": pubtitle, "joucontent": pubcontent};
		    }
		    /**
		     * Name 删除记录
		     */
		    function delPubs() {
		        var items = $('#pubinfo-datagrid').datagrid('getSelections');
		        if (items.length != 0) {
		            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
		                if (result) {
		                    var ids = [];
		                    $(items).each(function () {
		                        ids.push(this.id)
		                    });
		                    var url = 'delpubs';
		                    $.get(url, {pubsids: ids.toString()}, function (data) {
		                        if (data == "OK") {
		                            $.messager.alert('信息提示', '删除成功！', 'info');
		                            $("#pubinfo-datagrid").datagrid("reload");// 重新加载数据库
		                            $('#pubinfo-dialog').dialog('close');
		                        } else if (data == "NO") {
		                            $.messager.alert('信息提示', '删除部分！', 'info');
		                            $('#pubinfo-dialog').dialog('close');
		                        }
		                        else {
		                            $.messager.alert('信息提示', '删除失败！', 'info');
		                        }
		                    });
		                }
		            });
		        }
		    }
		  	function addPubs(){

		  		//$('#addnews-form').form('clear');
		        $('#addpubs-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "添加公告",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addpubs-form").form('submit', {
		                        url: 'addpubs',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '提交成功！');
		                                $("#pubinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addpubs-dialog').dialog('close');
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
		                    $('#addpubs-dialog').dialog('close');
		                }
		            }]
		        });
		  	}
		  //编辑公告
		  	function editPubs(){
		  		var rows = $('#pubinfo-datagrid').datagrid('getSelections');
		  		//alert(JSON.stringify(rows[0]));
		  		//$('#addnews-form').form('clear');
		        $('#addpubs-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "修改新闻",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addpubs-form").form('submit', {
		                        url: 'updatepubs',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '修改成功！');
		                                $("#pubinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addpubs-dialog').dialog('close');
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
		                    $('#addpubs-dialog').dialog('close');
		                }
		            }]
		        });
		        $('#addpubs-form').form('load', rows[0]);
		  	}
	</script>
</body>
</html>