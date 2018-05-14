<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
	<%@include file="/common/easyui.jspf" %>
</head>
<body>

<div class="easyui-layout" data-options="fit:true" >
    <div data-options="region:'center',border:false">
        <!-- Begin of rtoolbar -->
        <div id="choice-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                     题目：<input class="easyui-textbox" id="choice-name-value"/>
			                     难度等级：<input class="easyui-textbox" id="choice-degree-value"/>
			                     时间：<input type="date" id="bdaytime-course-value"/>~<input type="date" id="edaytime-course-value"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
            </div>
        </div>
        <!-- End of toolbar -->
        <table id="choice-datagrid" toolbar="#choice-toolbar" style="width: 100%;"></table>
                      已选择内容：
        <div  style="width:99%;height: 150px;overflow:scroll;border: 1px solid;">
        	<table id="singles">
        		<tr><th>题目</th><th>分数</th><th>操作</th></tr>
        	</table>
		</div>
    </div>
</div>
<script type="text/javascript">
    /**
     * Name 载入数据
     */
    $('#choice-datagrid').datagrid({
        url: 'single?courseId='+${courseId},
        rownumbers: true,
        singleSelect: true,
        pageSize: 10,
        pagination: true,
        queryParams: formChoiceJson(),
        multiSort: true,
        fitColumns: true,
        fit: false,
        columns: [[
            {field: 'id', title: '编号', width: 10, hidden: true},
            {field: 'singleTitle', title: '题目', width: 60},
            {field: 'answera', title: '答案A', width: 30},
            {field: 'answerb', title: '答案B', width: 30},	
            {field: 'answerc', title: '答案C', width: 30},
            {field: 'answerd', title: '答案D', width: 30},
            {field: 'degree', title: '难度等级', width: 20},
            {field: '_operate', title: '操作', width: 30,formatter:function(value,row,index){
            	return '<a href="javascript:;" onclick="selectTitle('+index+')">选择</a>';
            }}
        ]]
    });
    
    // 选择题目
    function selectTitle(index) {
    	$('#choice-datagrid').datagrid('selectRow',index);// 关键在这里
        var row = $('#choice-datagrid').datagrid('getSelected');  
    	$("#singles").append("<tr><td><input type='checkbox' checked='checked' style='display:none;' id ='"+row.id+"' value='"+row.id+"'/>"+row.singleTitle+"</td><td><input type='text' id ='score"+row.id+"' placeholder='输入分数' name='score' size='4' /></td><td><a href='javascript:;' onclick='removeTitle(this);' >删除</a></td></tr>");
    }
    // 删除选择题目
    function removeTitle(nowtr){
        $(nowtr).parent().parent().remove();
    }
    
    /* 搜索方法*/
    $("#daytime-search-btn").click(function () {
        //点击搜索
        $('#choice-datagrid').datagrid({
            queryParams: formChoiceJson()
        });
    });
    /*重置方法*/
    $("#daytime-search-reset").click(function () {
        $("#daytime-search-form").form('clear');
        // 重新加载数据
        $('#choice-datagrid').datagrid({
            queryParams: formChoiceJson()
        });
    });
    /* 搜索方法*/
    $("#choice-search-btn").click(function () {
        //点击搜索
        $('#choice-datagrid').datagrid({
            queryParams: formChoiceJson()
        });
    });
    /*重置方法*/
    $("#choice-search-reset").click(function () {
        $("#choice-search-form").form('clear');
        $("#bdaytime-course-value").val('');
        $("#edaytime-course-value").val('');
        $('#choice-datagrid').datagrid({
            queryParams: formChoiceJson()
        });
    });
    //将表单数据转为json
    function formChoiceJson() {
        var bChoiceName = $("#choice-name-value").val();
        var degree = $("#choice-degree-value").val();
        var bTime = $("#bdaytime-course-value").val();
        var eTime = $("#edaytime-course-value").val();
        return {"choicename": bChoiceName,"degree":degree, "bTime": bTime,"eTime":eTime};
    }
    /**
     * 创建课程的下拉框
     */
    $('#courseId').combobox({
        url: 'courseServlet.do?method=getCourseByUserId',
        valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    });
</script>
</body>
</html>