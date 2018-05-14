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
        <div id="postrecord-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
               		处理结果：
				  	<select class="easyui-combobox" style="width:173px;" id="s_is_prof">
				  		<option value="">--请选择--</option>
				  		<option value="0">成功</option>
				  		<option value="1">失败</option>
				  		<option value="2">未处理</option>
				  	</select>
			                    求职者姓名：<input class="easyui-textbox" id="s_prof_person_name"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" onclick="openImportpostrecord()"
                   plain="true">导出</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="postrecord-datagrid" toolbar="#postrecord-toolbar"></table> 
    </div>
</div>
<!-- 添加修改页面 -->
<div id="updatepost-dialog" style="width:600px;height:500px; padding:10px;">
    <form id="updatepost-form" method="post" >
    	<input type="hidden" name="id" class="easyui-textbox"/>
        <table style="margin:0 auto; height:250px">
			<tr>
			  <td>求职人姓名：</td>
			  <td><input class="easyui-textbox" id="prof_person_name" name="prof_person_name" readonly/></td>
			</tr>
			<tr>
			  <td>现任职称：</td>
			  <td><input class="easyui-textbox" id="now_prof_saltv" name="now_prof_saltv" readonly/></td>
			</tr>
			<tr>
			  <td>求职职称：</td>
			  <td><input class="easyui-textbox" id="prof_saltv" name="prof_saltv" readonly/></td>
			</tr>
			<tr>
			  <td>求职原因：</td>
			  <td><input class="easyui-textbox" id="prof_reason" name="prof_reason" readonly/></td>
			</tr>
			<tr>
			  <td>求职时间：</td>
			  <td><input class="easyui-textbox" id="prof_time" name="prof_time" readonly/></td>
			</tr>
			<tr>
			  <td>处理结果：</td>
			  <td>
			  	<select class="easyui-combobox" style="width:173px;" id="is_prof" name="is_prof">
			  		<option value="0">成功</option>
			  		<option value="1">失败</option>
			  		<option value="2">未处理</option>
			  	</select>
			  </td>
			</tr>
        </table>
    </form>
</div>
	<script type="text/javascript">
			/**
		     * Name 载入数据
		     */
		    $('#postrecord-datagrid').datagrid({
		        url: 'getpostrecordlist',
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
		            {field: 'prof_person_name', title: '求职人姓名', width: 50, sortable: false},
		            {field: 'now_prof_saltv', title: '现任职称', width: 50, sortable: false},
		            {field: 'prof_saltv', title: '求职职称', width: 50, sortable: false},
		            {field: 'prof_reason', title: '求职原因', width: 50, sortable: false},
		            {field: 'prof_time', title: '求职时间', width: 50, sortable: false, formatter: function (value, row, index) {
		            	if(value != null) {
			            	var date = new Date(value);
			            	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		            	} else {
		            		return "";
		            	}
		            }},
		            {field: 'is_prof', title: '处理结果', width: 50, sortable: false,formatter: function (value, row, index) {
		            	if(value == 0){
		            		return "成功";
		            	}else if(value == 1){
		            		return "失败";
		            	}else{
		            		return "未处理";
		            	}
		            }},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="edit" class="easyui-linkbutton" onclick="detailpostrecord()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){  
					$("a[name='edit']").linkbutton({text:'审批',plain:true,iconCls:'icon-edit'}); 
				},
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#postrecord-datagrid').datagrid({
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
		        var s_is_prof = $("#s_is_prof").val();
		        var s_prof_person_name = $("#s_prof_person_name").val();
		        return {"s_is_prof": s_is_prof, "s_prof_person_name": s_prof_person_name};
		    }
		  	//修改教师职位
		  	function detailpostrecord(){
		  		var rows = $('#postrecord-datagrid').datagrid('getSelections');
		  		alert(JSON.stringify(rows[0]));
		  		var time = rows[0].prof_time;
		  		var date = new Date(time);
		  		time = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		        $('#updatepost-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "职位审批",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#updatepost-form").form('submit', {
		                        url: 'updatepostrecord',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '修改成功！');
		                                $("#postrecord-datagrid").datagrid("reload");// 重新加载数据库
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
		        $("#prof_time").textbox("setValue",time);
		  	}
	</script>
</body>
</html>