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
<title>师资力量管理</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="introduceinfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    教师姓名：<input class="easyui-textbox" id="teaname"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
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
		    <td>教师姓名：</td>
		    <td><input class="easyui-textbox" id="teaname" name="teaname" readonly/></td>
		  </tr>
  		  <tr>
		    <td>教师头像：</td>
		    <td><input type="file" id="file" name="file" /></td>
		  </tr>
		  <tr>
		    <td>教师职位：</td>
		    <td><input type="easyui-combobox" id="professional" name="professional"/></td>
		  </tr>
		  <tr>
		    <td>教师介绍：</td>
		    <td><textarea id="prosonal_statement" name="prosonal_statement" style="word-wrap: break-word" rows="5" cols="40"></textarea></td>
		  </tr>
		</table>
	</form>
</div>
	<script type="text/javascript">
		
			/**
		     * Name 载入数据
		     */
		    $('#introduceinfo-datagrid').datagrid({
		        url: 'gettealist',
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
		            {field: 'teaname', title: '教师姓名', width: 50, sortable: true},
		            {field: 'teaimage', title: '教师头像', width: 50, sortable: false,formatter: function(value,row,index){
		            	var myurl = "${pageContext.request.contextPath}/"+value;
		            	var str = '<img width="100px" height="60px" border="0" src="'+ myurl +'"/>';
	                    return str;    
	                }},
	                {field: 'professional', title: '教师职称', width: 50, sortable: true},
		            {field: 'prosonal_statement', title: '教师介绍', width: 200, sortable: false,formatter: function(value,row,index){
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
		        var teaname = $("#teaname").val();
		        return {"teaname": teaname};
		    }
		  	//编辑教师介绍
		  	function editIntroduce(){
		  		var rows = $('#introduceinfo-datagrid').datagrid('getSelections');
		  		alert(JSON.stringify(rows[0]));
		        $('#addintroduce-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "修改新闻",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addintroduce-form").form('submit', {
		                        url: 'updateteacherintroduce',
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