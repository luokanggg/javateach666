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
        <div id="imginfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    图片名称：<input class="easyui-textbox" id="imgname"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-add" onclick="addImg()"
                   plain="true">添加图片</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="delImgs()"
                   plain="true">删除</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="imginfo-datagrid" toolbar="#imginfo-toolbar"></table>
    </div>
</div>
<div id="addindex-dialog" style="width:600px;height:500px; padding:10px;">
	<form id="addindex-form" enctype="multipart/form-data" method = "post">
		<input type="hidden" name="id" class="easyui-textbox"/>
		<table style="margin:0 auto; height:250px;">
		  <tr>
		    <td>图片编号：</td>
		    <td><input class="easyui-textbox" id="imgno" name="imgno"/></td>
		  </tr>
		  <tr>
		    <td>图片名称：</td>
		    <td><input class="easyui-textbox" id="imgname" name="imgname" /></td>
		  </tr>
  		  <tr>
		    <td>选择图片：</td>
		    <td><input type="file" id="file" name="file" /></td>
		  </tr>
		  <tr>
		    <td>是否展示：</td>
		    <td><input name="is_pub" type="radio" size="30" value="0" checked>否 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input name="is_pub" type="radio" size="30" value="1">是</td>
		  </tr>
		</table>
	</form>
</div>
	<script type="text/javascript">
    //添加时验证输入内容
	$(function(){
	    $("input",$("#imgno").next("span")).blur(function(){
	        //alert("ok");
	    	$("#imgno").textbox('setValue', $(this).val());     //给文本框赋值
	    	var imgno = $("#imgno").textbox('getText');     //获取文本框的值
	    	//alert(imgno);
			$.ajax({
				type: 'get',
				url: 'checkimgno?imgno='+imgno,
				contentType: 'application/json',
				success: function(result){
					if(result == "OK"){
						$.messager.alert('信息提示', '该编号已存在！请重新输入编号');
						//$("input",$("#imgno").next("span")).focus();
					}else{
						
					}
				}
			});
	    });
	})
	
			/**
		     * Name 载入数据
		     */
		    $('#imginfo-datagrid').datagrid({
		        url: 'getindeximglist',
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
		            {field: 'imgno', title: '图片编号', width: 50, sortable: true},
		            {field: 'imgname', title: '图片名称', width: 50, sortable: false},
		            {field: 'imgurl', title: '图片', width: 100, sortable: false,formatter: function(value,row,index){
		            	var myurl = "${pageContext.request.contextPath}/"+value;
		            	var str = '<img width="100px" height="60px" border="0" src="'+ myurl +'"/>';
	                    return str;    
	                }},
		            {field: 'create_time', title: '上传时间', width: 180, sortable: false, formatter: function (value, row, index) {
		            	if(value != null) {
			            	var date = new Date(value);
			            	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		            	} else {
		            		return "";
		            	}
		            }},
		            {field: 'is_pub', title: '是否展示', width: 50, sortable: false,
					  	formatter : function(value,row,index){ 
			                 if(value=='0'){return '否'} 
			                 else {return '是'}
		                }	
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="javascript:;" name="show" class="easyui-linkbutton" onclick="showImg()" ></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" name="hide" class="easyui-linkbutton" onclick="hideImg()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
						$("a[name='show']").linkbutton({text:'展示',plain:true,iconCls:'icon-ok'}); 
						$("a[name='hide']").linkbutton({text:'隐藏',plain:true,iconCls:'icon-cancel'});
				},
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#imginfo-datagrid').datagrid({
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
		        var imgname = $("#imgname").val();
		        return {"imgname": imgname};
		    }
		    /**
		     * Name 删除记录
		     */
		    function delImgs() {
		        var items = $('#imginfo-datagrid').datagrid('getSelections');
		        if (items.length != 0) {
		            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
		                if (result) {
		                    var ids = [];
		                    $(items).each(function () {
		                        ids.push(this.id)
		                    });
		                    var url = 'delimgs';
		                    $.get(url, {imgsids: ids.toString()}, function (data) {
		                        if (data == "OK") {
		                            $.messager.alert('信息提示', '删除成功！', 'info');
		                            $("#imginfo-datagrid").datagrid("reload");// 重新加载数据库
		                            $('#addindex-dialog').dialog('close');
		                        } else if (data == "NO") {
		                            $.messager.alert('信息提示', '删除部分！', 'info');
		                            $('#addindex-dialog').dialog('close');
		                        }
		                        else {
		                            $.messager.alert('信息提示', '删除失败！', 'info');
		                        }
		                    });
		                }
		            });
		        }
		    }
		    //添加图片		    
		  	function addImg() {
		  		$('#addindex-form').form('clear');
		        $('#addindex-dialog').dialog({
		            closed: false,
		            modal: true,
		            width: 500,
		            height: 350,
		            title: "添加图片",
		            buttons: [{
		                text: '确定',
		                iconCls: 'icon-ok',
		                handler: function () {
		                    $("#addindex-form").form('submit', {
		                        url: 'addindeximg',
		                        onSubmit: function () {

		                        },
		                        success: function (data) {
		                            if (data == "OK") {
		                                $.messager.alert('信息提示', '提交成功！');
		                                $("#imginfo-datagrid").datagrid("reload");// 重新加载数据库
		                                $('#addindex-dialog').dialog('close');
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
		                    $('#addindex-dialog').dialog('close');
		                }
		            }]
		        });
		  	}
		    
		  	//展示图片
		  	function showImg(){
		  		var rows = $('#imginfo-datagrid').datagrid('getSelections');
		  		alert(JSON.stringify(rows[0]));
				$.ajax({
					type: 'post',
					url: 'showimg',
					contentType: 'application/json',
					data: rows[0],
					success: function(result){
						$("#imginfo-datagrid").datagrid("reload");
					}
				});
				 $("#imginfo-datagrid").datagrid("reload");
		  	}
		  	//隐藏图片
		  	function hideImg(){
		  		var rows = $('#imginfo-datagrid').datagrid('getSelections');
		  		alert(JSON.stringify(rows[0]));
				$.ajax({
					type: 'post',
					url: 'hideimg',
					contentType: 'application/json',
					data: JSON.stringify(rows[0]),
					success: function(result){
						$("#imginfo-datagrid").datagrid("reload");
					}
				});
				 $("#imginfo-datagrid").datagrid("reload");
		  	}
	</script>
</body>
</html>