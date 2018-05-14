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
        <div id="dicinfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    字典类型：<input class="easyui-combobox" value="--请选择--" id="ddtype"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-add" onclick="addDic()"
                   plain="true">添加字典</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="delDics()"
                   plain="true">删除</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="dicinfo-datagrid" toolbar="#dicinfo-toolbar"></table> 
    </div>
</div>
<!-- 添加修改页面 -->
<div id="adddic-dialog" style="width:600px;height:500px; padding:10px;">
    <form id="adddic-form" method="post" >
    	<input type="hidden" name="id" class="easyui-textbox"/>
        <table style="margin:0 auto; height:250px">
			<tr>
			  <td>字典类型：</td>
			  <td><input class="easyui-textbox" id="dtype" name="dtype"/></td>
			</tr>
			<tr>
			  <td>字典名称：</td>
			  <td><input class="easyui-textbox" id="dicname" name="dicname" /></td>
			</tr>
			<tr>
			  <td>字典值：</td>
			  <td><input class="easyui-textbox" id="dvalue" name="dvalue" /></td>
			</tr>
			<tr>
			  <td>备注：</td>
			  <td><input class="easyui-textbox" id="remark" name="remark" /></td>
			</tr>
        </table>
    </form>
</div>
	<script type="text/javascript">
	
    //添加时验证输入内容
	$(function(){
	    $("input",$("#dicname").next("span")).blur(function(){
	        //alert("ok");
	    	$("#dicname").textbox('setValue', $(this).val());     //给文本框赋值
	    	var dicname = $("#dicname").textbox('getText');     //获取文本框的值
			$.ajax({
				type: 'get',
				url: 'checkDicname?dicname='+dicname,
				contentType: 'application/json',
				success: function(result){
					if(result == "OK"){
						$.messager.alert('信息提示', '该字典名已存在！请重新输入');
					}else{
						
					}
				}
			});
	    });
	})
	
	    $('#ddtype').combobox({
	    	url: 'getDicNameList',
	    	editable: true,//不可编辑，只能选择
	    	valueField: 'dtype',
	        textField: 'dtype'}
	    );	
			/**
		     * Name 载入数据
		     */
		    $('#dicinfo-datagrid').datagrid({
		        url: 'getdiclist',
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
		            {field: 'dicname', title: '字典名称', width: 50, sortable: false},
		            {field: 'dtype', title: '字典类型', width: 50, sortable: false},
		            {field: 'dvalue', title: '字典值', width: 30, sortable: false},
		            {field: 'remark', title: '备注', width: 250, sortable: false},
		            {field: 'is_delete', title: '是否删除', width: 50, sortable: false,
					  	formatter : function(value,row,index){ 
			                 if(value=='0'){return '否'} 
			                 else {return '是'}
		                }	
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="edit" class="easyui-linkbutton" onclick="editDic()" ></a>';  
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
		        $('#dicinfo-datagrid').datagrid({
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
		        var dtype = $("#ddtype").val();
		        return {"dtype": dtype};
		    }
		  	/**
		     * Name 删除记录
		     */
		    function delDics() {
		        var items = $('#dicinfo-datagrid').datagrid('getSelections');
		        if (items.length != 0) {
		            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
		                if (result) {
		                    var ids = [];
		                    $(items).each(function () {
		                        ids.push(this.id)
		                    });
		                    var url = 'deldics';
		                    $.get(url, {dicsids: ids.toString()}, function (data) {
		                        if (data == "OK") {
		                            $.messager.alert('信息提示', '删除成功！', 'info');
		                            $("#dicinfo-datagrid").datagrid("reload");// 重新加载数据库
		                            $('#dicinfo-dialog').dialog('close');
		                        } else if (data == "NO") {
		                            $.messager.alert('信息提示', '删除部分！', 'info');
		                            $('#dicinfo-dialog').dialog('close');
		                        }
		                        else {
		                            $.messager.alert('信息提示', '删除失败！', 'info');
		                        }
		                    });
		                }
		            });
		        }
		    }
		  	function addDic(){
		  		//$('#addnews-form').form('clear');
		        $('#adddic-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "添加字典",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#adddic-form").form('submit', {
		                        url: 'adddic',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '提交成功！');
		                                $("#dicinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#adddic-dialog').dialog('close');
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
		                    $('#adddic-dialog').dialog('close');
		                }
		            }]
		        });
		  	}
		  	//编辑字典
		  	function editDic(){
		  		var rows = $('#dicinfo-datagrid').datagrid('getSelections');
		  		alert(JSON.stringify(rows[0]));
		  		//$('#addnews-form').form('clear');
		        $('#adddic-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "修改字典",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#adddic-form").form('submit', {
		                        url: 'updatedic',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '修改成功！');
		                                $("#dicinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#adddic-dialog').dialog('close');
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
		                    $('#adddic-dialog').dialog('close');
		                }
		            }]
		        });
		        $('#adddic-form').form('load', rows[0]);
		  	}
	</script>
</body>
</html>