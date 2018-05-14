<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>教师信息</title>
</head>
<style>
.datagrid-row {
  height: 45px;
}
.datagrid-header-row td
{
  background-color:#9aCCFF;
  height:32px;
}
</style>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="teainfolist-toolbar">
            <div class="wu-toolbar-button">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <form id="choice-search-form" style="display: inline-block">
			                    教师姓名：<input  class="easyui-textbox" style="height:27px" data-options="prompt:'Enter a name...'" id="teaname"/>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			                    教师职称：<input style="height:27px" data-options="prompt:'Enter a professional...'" class="easyui-textbox" id="professional"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a id="choice-search-btn" style="height:27px" class="easyui-linkbutton" iconCls="icon-search">搜索</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    <a id="choice-search-reset" style="height:27px" class="easyui-linkbutton" iconCls="icon-undo">重置</a>
                </form>
            </div>

        </div>
        
        <table id="teainfolist-datagrid" toolbar="#teainfolist-toolbar"></table>
    </div>
</div>
	<script type="text/javascript">
		
			/**
		     * Name 载入数据
		     */
		    $('#teainfolist-datagrid').datagrid({
		        url: 'getteainfolist',
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
		        striped:true,
		       
		        columns: [[
		            //{field: '', checkbox: true},
		            {field: 'teano', title: '编号', width: 50,align:'center',sortable:true,
	                 
		            },
		            {field: 'teaname', title: '姓名', width: 50,align:'center'},
		            {field: 'professional', title: '职称', width: 100,align:'center'},
		            {field: 'joined_date', title: '入职时间', width: 120, 
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }	,align:'center'
		            },
		            {field: 'email', title: '邮箱', width: 120,align:'center'},
		            {field: 'teaphone', title: '手机号', width: 100,align:'center'},
		            {field: 'teaage', title: '年龄', width: 40, align:'center'},
		            {field: 'teasex', title: '性别', width: 50,align:'center'},
		            {field: 'teacollage', title: '学院', width: 100,align:'center'},
		            {field: 'teanation', title: '名族', width: 60,align:'center'},
		            {field: 'political', title: '政治面貌', width: 80,align:'center'},
		            {field: 'major', title: '专业', width: 100,align:'center'},
		            {field: 'prosonal_statement', title: '个人说明', width: 160,align:'center'}
				]],
				onLoadSuccess:function(data){    
					//	$("a[name='opera']").linkbutton({text:'发消息',plain:true,iconCls:'icon-edit'});    
				}
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#teainfolist-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $("#professional").val('');
		        $("#teaname").val('');
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		        var professional = $("#professional").val();
		        var teaname = $("#teaname").val();
		        //alert(question1);
		        //alert(question2);
		        return {"professional": professional,"teaname": teaname};
		    }
	</script>
</body>
</html>