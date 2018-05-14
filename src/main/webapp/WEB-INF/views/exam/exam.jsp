<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>考试安排</title>
    <%@include file="/common/easyui.jspf"%>
</head>
<body>

<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="exam-toolbar">
        <div class="exam-toolbar-button">
            <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openExamAdd()"
               plain="true">添加</a>
            <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="openExamRemove()"
               plain="true">删除</a>
            <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" onclick="openExamEdit()" plain="true">修改</a>
	  			<br/>	
 			<form style="display: inline-block;" name="searchstudentform" method="post" action="" id ="searchstudentform">
				科目:<input id="stuPaperName" editable="false" panelMaxHeight="100"  name="couName"/>
				题目:<input id="stuTitleName" class="easyui-textbox"  name="exaName"/>
				<a id="stusearchbtn" class="easyui-linkbutton">搜索</a>
				<a id="sturesetbtn" class="easyui-linkbutton">重置</a>
			</form>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="exam-datagrid" class="easyui-datagrid" toolbar="#exam-toolbar"></table>
</div>
<!-- 添加和修改页面 -->
<div id="exam-dialog" style="width:400px; padding:10px;">
    <form id="exam-form" method="post">
        <table>
            <tr>
                <td width="60" align="right"></td>
                <td><input type="hidden" name="id" id="id"/></td>
            </tr>
            <tr>
                <td width="60" align="right">考试科目:</td>
                <td>
                    <input id="courseID" name="course.id" required="required" editable="false">
                </td>
            </tr>
            <tr>
                <td align="right">考试试卷:</td>
                <td>
                    <input id="paperID" name="examPaper.id" required="required" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name'" editable="false" panelMaxHeight="100"/>
                </td>
            </tr>
            <tr>
                <td align="right">考试时长:</td>
                <td><input id="examTime" type="text" name="examTime" required="required" class="easyui-textbox" required="required"/></td>
            </tr>
            <tr>
                <td align="right">考试时间:</td>
                <td><input id="beginTime" type="text" name="beginTime" required="required" class="easyui-datetimebox" editable="false"
                           required="required"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- End of easyui-dialog -->
<script type="text/javascript">
    /**
     * Name 添加和修改记录
     */
    var updateExamFuntion = function (url) {
        $('#exam-form').form('submit', {
            url: url,
            onSubmit: function () {
                var isValid = $(this).form('validate');
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                if (data == "OK") {
                    $.messager.alert('信息提示', '提交成功！', 'info');
                    $("#exam-datagrid").datagrid("reload");// 重新加载数据库
                    $('#exam-dialog').dialog('close');
                }
                else {
                    $.messager.alert('信息提示', '提交失败！', 'info');
                }
            }
        });
    }

    /**
     * Name 删除记录
     */
    function openExamRemove() {
        var items = $('#exam-datagrid').datagrid('getSelections');
        if (items.length != 0) {
            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
                if (result) {
                    var ids = [];
                    $(items).each(function () {
                        ids.push(this.id);
                    });
                    var url = "deleteExam";
                    $.get(url, {examID: ids.toString()}, function (data) {
                        if (data == 'OK') {
                            $.messager.alert('信息提示', '删除成功！', 'info');
                            $("#exam-datagrid").datagrid("reload");// 重新加载数据库
                            $('#exam-dialog').dialog('close');
                        } else if (data == 'NK') {
                            $.messager.alert('信息提示', '删除部分！', 'info');
                            $("#exam-datagrid").datagrid("reload");// 重新加载数据库
                            $('#exam-dialog').dialog('close');
                        } else {
                            $.messager.alert('信息提示', '删除失败！', 'info');
                        }
                    });
                }
            });
        } else {
            $.messager.alert('信息提示', '请选择要的删除数据！', 'info');
        }
    }

    /**
     * Name 打开添加窗口
     */
    function openExamAdd() {
        $('#exam-form').form('clear');
        $('#courseID').combobox('reload');
        $('#exam-dialog').dialog({
            closed: false,
            modal: true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    updateExamFuntion('saveExam');
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#exam-dialog').dialog('close');
                }
            }]
        });
    }

    /**
     * Name 打开修改窗口
     */
    function openExamEdit() {
        $('#exam-form').form('clear');
        var item = $('#exam-datagrid').datagrid('getSelections');
        if (item.length > 1) {
            $.messager.alert("提示信息", "只能修改一行", 'info');
        } else {
            var row = $('#exam-datagrid').datagrid('getSelected');
            if (row != null) {
            	// 加载数据
            	initdata(row);
                $('#exam-dialog').dialog({
                    closed: false,
                    modal: true,
                    title: "修改信息",
                    buttons: [{
                        text: '确定',
                        iconCls: 'icon-ok',
                        handler: function () {
                            updateExamFuntion('updateExam');
                        }
                    }, {
                        text: '取消',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            $('#exam-dialog').dialog('close');
                        }
                    }]
                });
            } else {
                $.messager.alert("提示信息", "请选择修改数据", 'info');
            }
        }
    }
    
    // form表单加载数据
    function initdata(data) {
    	$("#id").attr("value",data.id);
    	$("#courseID").combobox('select', data.course.id);
    	$("#paperID").combobox('select', data.examPaper.id);
    	$("#beginTime").datetimebox('setValue', parsedate(data.beginTime));
    	$("#examTime").textbox("setValue",data.examTime);
    }
    
    // 格式化时间
    function parsedate(value){  
        var date = new Date(value);  
	    var year = date.getFullYear();  
	    var month = date.getMonth()+1; //月份+1     
	    var day = date.getDate();   
	    var hour = date.getHours();   
	    var minutes = date.getMinutes();   
	    var second = date.getSeconds();  
	    return  day+"/"+month+"/"+year+" "+hour+":"+minutes +":"+second;  
	}  

    /**
     * Name 载入数据
     */
    $('#exam-datagrid').datagrid({
        url: 'getExams',
        queryParams: formExamJson(),
        rownumbers: true,
        singleSelect: false,
        pageSize: 20,
        pagination: true,
        multiSort: true,
        fitColumns: true,
        fit: true,
        columns: [[
            {field: '', checkbox: true},
            {field: 'id', hidden:true},
            {field: 'courseName', title: '考试科目', width: 180, sortable: true},
            {field: 'examPaperName', title: '考试试卷', width: 100, sortable: true},
            {field: 'examTime', title: '考试时长', width: 100},
            {field: 'beginTime', title: '考试时间', width: 100, formatter: function (value, row, index) {
            	if(value != null) {
	            	var date = new Date(value);
	            	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() +' '+date.getHours() +':'+ date.getMinutes() +':'+ date.getSeconds();
            	} else {
            		return "";
            	}
            }},
            {field: 'couyear', title: '学年', width: 100},
            {field: 'semester', title: '学期', width: 100}
        ]]
    });

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
    	var couName = $("#stuPaperName").val();
    	var exaName = $("#stuTitleName").val();
    	// 返回json
        return {"couName":couName,"exaName":exaName};
    }


    /**
     * 创建课程的下拉框
     */
    $('#courseID').combobox({
        url: '${basePath}/kingother/getCourseJson',
        valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
        onSelect: function (rec) {
            var url1 = '${basePath}/exampaper/getExamPaperBycourse?coursId=' + rec.id;
            $('#paperID').combobox('clear');
            $('#paperID').combobox('reload', url1);
             
        }
    });
    $("#stuPaperName").combobox({
		url:'${basePath}/kingother/getCourseJson',
		valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    });
</script>
</body>
</html>



