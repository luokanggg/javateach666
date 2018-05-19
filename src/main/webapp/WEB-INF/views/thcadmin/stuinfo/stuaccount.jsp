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
<title>学生账户管理</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="stuinfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    学生账号：<input class="easyui-textbox" id="username"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-add" onclick="addstu()"
                   plain="true">添加账户</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="delAccount()"
                   plain="true">禁用账户</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" onclick="openImportstuinfo()"
                   plain="true">导入</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="stuinfo-datagrid" toolbar="#stuinfo-toolbar"></table> 
    </div>
</div>
<!-- 添加修改页面 -->
<div id="addstu-dialog" style="width:600px;height:500px; padding:10px;">
    <form id="addstu-form" method="post" >
    	<input type="hidden" name="id" class="easyui-textbox"/>
        <table style="margin:0 auto; height:350px">
			<tr>
			  <td>用户名：</td>
			  <td><input class="easyui-textbox" id="username1" name="username"/></td>
			</tr>
			<tr>
			  <td>密码：</td>
			  <td><input class="easyui-textbox" id="password" name="password" /></td>
			</tr>
			<tr>
			  <td>是否启用：</td>
			  <td>
			  	<select class="easyui-combobox" style="width:173px;" id="enable" name="enable">
			  		<option value="1">是</option>
			  		<option value="0">否</option>
			  	</select>
			  </td>
			</tr>
			<tr><td colspan="2">--------------------------------------------</td></tr>
			<tr>
			  <td>学生学号：</td>
			  <td><input class="easyui-textbox" id="stuno" name="stuno"/></td>
			</tr>
			<tr>
			  <td>学生姓名：</td>
			  <td><input class="easyui-textbox" id="stuname" name="stuname"/></td>
			</tr>
			<tr>
			  <td>性别：</td>
			  <td><input class="easyui-combobox" id="stusex" name="stusex"/></td>
			</tr>
			<tr>
			  <td>政治面貌：</td>
			  <td><input class="easyui-combobox" id="political" name="political"/></td>
			</tr>
			<tr>
			  <td>民族：</td>
			  <td><input class="easyui-textbox" id="nation" name="nation"/></td>
			</tr>
			<tr>
			  <td>年龄：</td>
			  <td><input class="easyui-textbox" id="stuage" name="stuage"/></td>
			</tr>
			<tr>
			  <td>学院：</td>
			  <td><input class="easyui-combobox" id="college" name="college"/></td>
			</tr>
			<tr>
			  <td>年级：</td>
			  <td><input class="easyui-textbox" id="classyear" name="classyear"/></td>
			</tr>
			<tr>
			  <td>专业：</td>
			  <td><input class="easyui-combobox" id="major" name="major"/></td>
			</tr>
			<tr>
			  <td>班级：</td>
			  <td><input class="easyui-combobox" id="classname" name="classname"/></td>
			</tr>
			<tr>
			  <td>电话：</td>
			  <td><input class="easyui-textbox" id="stuphone" name="stuphone"/></td>
			</tr>
        </table>
    </form>
</div>
	<script type="text/javascript">
	
	//添加时验证输入内容
	$(function(){
	    $("input",$("#username1").next("span")).blur(function(){
	        //alert("ok");
	    	$("#username1").textbox('setValue', $(this).val());     //给文本框赋值
	    	var username = $("#username1").textbox('getText');     //获取文本框的值
			$.ajax({
				type: 'get',
				url: 'checkUsername?username='+username,
				contentType: 'application/json',
				success: function(result){
					if(result == "OK"){
						$.messager.alert('信息提示', '该用户名已存在！请重新输入');
					}else{
						
					}
				}
			});
	    });
	    $("input",$("#stuno").next("span")).blur(function(){
	        //alert("ok");
	    	$("#stuno").textbox('setValue', $(this).val());     //给文本框赋值
	    	var stuno = $("#stuno").textbox('getText');     //获取文本框的值
			$.ajax({
				type: 'get',
				url: 'checkStuno?stuno='+stuno,
				contentType: 'application/json',
				success: function(result){
					if(result == "OK"){
						$.messager.alert('信息提示', '该学号已存在！请重新输入');
					}else{
						
					}
				}
			});
	    });
	});
	
			var dtype = "性别";
			var dtype1 = "政治面貌";
		    $('#stusex').combobox({
		    	url: 'getSexList?dtype='+dtype,
		    	editable: true,//不可编辑，只能选择
		    	panelMaxHeight: '100',
		    	valueField: 'dicname',
		        textField: 'dicname'}
		    );	
		    $('#political').combobox({
		    	url: 'getPoliticalList?dtype='+dtype1,
		    	editable: true,//不可编辑，只能选择
		    	valueField: 'dicname',
		        textField: 'dicname'}
		    );	
		    $('#college').combobox({
		    	url: 'getCollegeList',
		    	editable: true,//不可编辑，只能选择
		    	valueField: 'college',
		        textField: 'college',
		    	onSelect: function(rec){
		    		$('#major').combobox({
				    	url: 'getMajorList?college='+rec.college,
				    	editable: true,//不可编辑，只能选择
				    	valueField: 'major',
				        textField: 'major',
				        onSelect: function(recc){
						    $('#classname').combobox({
						    	url: 'getClassnameList?major='+recc.major,
						    	editable: true,//不可编辑，只能选择
						    	valueField: 'claname',
						        textField: 'claname'}
						    );
				        }
				        }
				    )
		    	}    
		    
		    }
		    );	
			/**
		     * Name 载入数据
		     */
		    $('#stuinfo-datagrid').datagrid({
		        url: 'getstulist',
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
		            {field: 'username', title: '用户名', width: 50, sortable: false},
		            {field: 'password', title: '密码', width: 50, hidden: true, sortable: false},
		            {field: 'enable', title: '是否启用', width: 50, sortable: false,formatter: function (value, row, index) {
		            	if(value == 1){
		            		return "是";
		            	}else{
		            		return "否";
		            	}
		            }},
		            {field: 'stuno', title: '学生学号', width: 50, sortable: false},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="edit" class="easyui-linkbutton" onclick="editStu()" ></a>';  
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
		        $('#stuinfo-datagrid').datagrid({
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
		        var username = $("#username").val();
		        return {"username": username};
		    }
		  	//禁用学生账号
		  	function delAccount(){
		        var items = $('#stuinfo-datagrid').datagrid('getSelections');
		        if (items.length != 0) {
		            $.messager.confirm('信息提示', '确定要禁用该记录？', function (result) {
		                if (result) {
		                    var ids = [];
		                    $(items).each(function () {
		                        ids.push(this.id)
		                    });
		                    var url = 'delstuaccounts';
		                    $.get(url, {accountids: ids.toString()}, function (data) {
		                        if (data == "OK") {
		                            $.messager.alert('信息提示', '禁用成功！', 'info');
		                            $("#stuinfo-datagrid").datagrid("reload");// 重新加载数据库
		                            $('#addstu-dialog').dialog('close');
		                        } else if (data == "NO") {
		                            $.messager.alert('信息提示', '禁用部分！', 'info');
		                            $('#addstu-dialog').dialog('close');
		                        }
		                        else {
		                            $.messager.alert('信息提示', '禁用失败！', 'info');
		                        }
		                    });
		                }
		            });
		        }
		  		
		  	}
		  	function addstu(){
		  		$('#addstu-form').form('clear');
		        $('#addstu-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "添加账户",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addstu-form").form('submit', {
		                        url: 'addstu',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '提交成功！');
		                                $("#stuinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addstu-dialog').dialog('close');
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
		                    $('#addstu-dialog').dialog('close');
		                }
		            }]
		        });
		  	}
		  	//编辑教师账户
		  	function editStu(){
		  		var rows = $('#stuinfo-datagrid').datagrid('getSelections');
		  		//alert(JSON.stringify(rows[0]));
		  		//$('#addnews-form').form('clear');
		        $('#addstu-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "修改信息",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addstu-form").form('submit', {
		                        url: 'updatestu',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '修改成功！');
		                                $("#stuinfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addstu-dialog').dialog('close');
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
		                    $('#addstu-dialog').dialog('close');
		                }
		            }]
		        });
		        $('#addstu-form').form('load', rows[0]);
		  	}
	</script>
</body>
</html>