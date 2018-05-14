<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>批改试卷</title>
    <%@include file="/common/easyui.jspf"%>
</head>
<body>

<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="exam-toolbar">
        <div class="exam-toolbar-button">
 			<form style="display: inline-block;" name="searchstudentform" method="post" action="" id ="searchstudentform">
				试卷名:<input id="paperName" editable="false" panelMaxHeight="100"/>
				<a id="stusearchbtn" class="easyui-linkbutton">搜索</a>
				<a id="sturesetbtn" class="easyui-linkbutton">重置</a>
			</form>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="exam-datagrid" class="easyui-datagrid" toolbar="#exam-toolbar"></table>
    <div id="read-paper-do"></div>
</div>
<script type="text/javascript">
    /**
     * Name 载入数据
     */
    $('#exam-datagrid').datagrid({
        url: 'getCorrecting',
        queryParams: formExamJson(),
        rownumbers: true,
        singleSelect: true,
        pageSize: 20,
        pagination: true,
        multiSort: true,
        fitColumns: true,
        fit: true,
        columns: [[
            {field: 'id', hidden:true},
            {field: 'examName', title: '试卷名', width: 180, sortable: true},
            {field: 'stuName', title: '学生姓名', width: 100, sortable: true},
            {field: '_operate', title: '操作', width: 100,formatter:function(value,row,index){
            	if(row.state == 0) {
            		return '<a id="tocorrect" href="javascript:;" onclick="tocorrect('+row.id+')">批改试卷</a>';
            	} else {
            		return '';
            	}
            }}
        ]]
    });

    function tocorrect(id) {
        $('#read-paper-do').dialog({
            top: 100,
            width: 700,
            closed: false,
            modal: true,
            title: "批改试卷",
            href: 'getAnswerById?anwserID=' + id,
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    $('#answer-paper-form').form('submit', {
                        url: 'saveCorrecting',
                        onSubmit: function(){
                            var isValid = $(this).form('validate');
                            return isValid;	// 返回false终止表单提交
                        },
                        success: function (data) {
                            var msg = data == "OK" ? "批改成功" : "批改失败";
                            $.messager.alert('提示', msg, 'tipinfo');
                            $('#read-paper-do').dialog('close');
                            $('#exam-datagrid').datagrid('reload');
                        }
                    });
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#read-paper-do').dialog('close');
                }
            }]
        });
    }
    
	/* 搜索方法*/
 	$("#stusearchbtn").click(function(){
 		//点击搜索
 		$('#exam-datagrid').datagrid({ 
 			queryParams: formExamJson()
 		});   
 	}); 
	
	/*重置方法*/
 	$("#sturesetbtn").click(function(){
 		$("#searchstudentform").form('clear');
 		// 重新加载数据
 		$('#exam-datagrid').datagrid({ 
 				queryParams: formExamJson()
 			}); 
 	});

    //将表单数据转为json
    function formExamJson() {
    	var paperid = $("#paperName").val();
    	// 返回json
        return {"paperid":paperid};
    }


    /**
     * 创建试卷的下拉框
     */
    $("#paperName").combobox({
		url:'getExamPaperJson',
		valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    });
</script>
</body>
</html>



