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
<title>教师职位信息</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="teapost-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
                	教师编号：<input class="easyui-textbox" id="teano"/>
			                    教师姓名：<input class="easyui-textbox" id="teaname"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" onclick="openImportteapost()"
                   plain="true">导出</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="teapost-datagrid" toolbar="#teapost-toolbar"></table> 
    </div>
</div>
<!-- 添加修改页面 -->
<div id="updatepost-dialog" style="width:600px;height:500px; padding:10px;">
    <form id="updatepost-form" method="post" >
        <table style="margin:0 auto; height:250px">
			<tr>
			  <td>教师编号：</td>
			  <td><input class="easyui-textbox" id="teano" name="teano" readonly/></td>
			</tr>
			<tr>
			  <td>教师姓名：</td>
			  <td><input class="easyui-textbox" id="teaname" name="teaname" readonly/></td>
			</tr>
			<tr>
			  <td>学院：</td>
			  <td><input class="easyui-combobox" id="teacollage" name="teacollage"/></td>
			</tr>
			<tr>
			  <td>职称：</td>
			  <td><input type="easyui-combobox" id="professional" name="professional"/></td>
			</tr>
        </table>
    </form>
</div>
	<script type="text/javascript">
		    $('#teacollage').combobox({
		    	url: 'getCollegeList',
		    	editable: true,//不可编辑，只能选择
		    	valueField: 'college',
		        textField: 'college'
		    });
		    var dtype2 = "职称";
		    $('#professional').combobox({
		    	url: 'getSexList?dtype='+dtype2,
		    	editable: true,//不可编辑，只能选择
		    	panelMaxHeight: '100',
		    	valueField: 'dicname',
		        textField: 'dicname'}
		    );
			/**
		     * Name 载入数据
		     */
		    $('#teapost-datagrid').datagrid({
		        url: 'getteapostlist',
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
		            {field: '', checkbox: true, hidden: true},
		            {field: 'id', title: '编号', width: 50, sortable: true, hidden: true},
		            {field: 'teano', title: '教师编号', width: 50, sortable: false},
		            {field: 'teaname', title: '教师姓名', width: 50, sortable: false},
		            {field: 'teacollage', title: '学院', width: 50, sortable: false},
		            {field: 'professional', title: '职称', width: 50, sortable: false},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="edit" class="easyui-linkbutton" onclick="editTeaPost()" ></a>';  
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
		        $('#teapost-datagrid').datagrid({
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
		    	var teano = $("#teano").val();
		        var teaname = $("#teaname").val();
		        return {"teano": teano, "teaname": teaname};
		    }
		  	//修改教师职位
		  	function editTeaPost(){
		  		var rows = $('#teapost-datagrid').datagrid('getSelections');
		  		alert(JSON.stringify(rows[0]));
		        $('#updatepost-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "修改信息",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#updatepost-form").form('submit', {
		                        url: 'updateteapost',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '修改成功！');
		                                $("#teapost-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#updatepost-dialog').dialog('close');
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
		                    $('#updatepost-dialog').dialog('close');
		                }
		            }]
		        });
		        $('#updatepost-form').form('load', rows[0]);
		  	}
	</script>
</body>
</html>