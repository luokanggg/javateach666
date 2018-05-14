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

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of rtoolbar -->
        <div id="choice-toolbar">
            <div class="wu-toolbar-button">
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openAddChoice()"
                   plain="true">添加</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditChoice()"
                   plain="true">修改</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="openRemoveChoice()"
                   plain="true">删除</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" onclick="openImportChoice()"
                   plain="true">导入</a>
                   <br/>
                <form id="choice-search-form" style="display: inline-block">
			                    科目：<input id="choice-course-value"  editable="false" panelMaxHeight="100"/>
			                    题目：<input class="easyui-textbox" id="choice-name-value"/>
			                     难度等级：<select class="easyui-combobox" id="choice-degree-value" style="width:80px;" panelMaxHeight="100" editable="false">
			               	<option></option>
			               	<option value="1">1</option>
			               	<option value="2">2</option>
			               	<option value="3">3</option>
			               	<option value="4">4</option>
			              </select>
			                     时间：<input type="date" id="bdaytime-course-value"/>~<input type="date" id="edaytime-course-value"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="choice-datagrid" toolbar="#choice-toolbar"></table>
    </div>
</div>
<!-- Begin of easyui-dialog -->

<!-- 添加修改页面 -->
<div id="choice-dialog" style="width:400px; padding:10px;">
    <form id="choice-form" method="post">
        <table>
            <tr>

                <td><input type="hidden" name="id"/></td>
            </tr>
            <tr>
                <td width="60" align="right">课程</td>
                <td>
                    <input id="couseId" name="course.id" required="required" editable="false" panelMaxHeight="100"/>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">题目难度</td>
                <td>
                    <select class="easyui-combobox" id="degree" name="degree" style="width:174px;" panelMaxHeight="100" editable="false">
		               	<option></option>
		               	<option value="1">1</option>
		               	<option value="2">2</option>
		               	<option value="3">3</option>
		               	<option value="4">4</option>
	                </select>
                </td>
            </tr>
            <tr>
                <td align="right">题目</td>
                <td>
                	<textarea name="multipleTitle" rows="3" cols="28">

                	</textarea>
                </td>
            </tr>
            <tr>
                <td align="right">答案A</td>
                <td>
                	<textarea name="answera" rows="3" cols="28">

                	</textarea>
                </td>
            </tr>

            <tr>
                <td align="right">答案B</td>
                <td>
                	<textarea name="answerb" rows="3" cols="28">

                	</textarea>
                </td>
            </tr>
            <tr>
                <td align="right">答案C</td>
                <td>
                	<textarea name="answerc" rows="3" cols="28">

                	</textarea>
                </td>
            </tr>
            <tr>
                <td align="right">答案D</td>
                <td>
                	<textarea name="answerd" rows="3" cols="28">

                	</textarea>
                </td>
            </tr>
            <tr>
                <td align="right">正确答案</td>
                <td><input type="text" name="multipleAnswer" class="easyui-textbox"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="choice-import-dialog"class="easyui-dialog" data-options="closed:true" style="padding: 30px">
    <form style="text-align: center" id="choice-import-form" method="post" enctype="multipart/form-data">
        <input id="choice-import-input" name="excel" style="width:300px">
    </form>
</div>
<!-- End of easyui-dialog -->
<script type="text/javascript">
    //倒入
    function openImportChoice() {
        $('#choice-import-form').form('clear');
        $('#choice-import-dialog').dialog({
            title: '多选题倒入',
            width: 400,
            height: 200,
            closed: false,
            modal: true,
            buttons: [{
                text: '保存',
                iconCls: 'icon-ok',
                handler: function () {
                    $("#choice-import-form").form('submit', {
                        url: 'importExcel',
                        onSubmit: function () {
                            var isValid = $(this).form('validate');
                            if (isValid) {
                                $.messager.progress({
                                    title: '提示',
                                    msg: '正在导入...',
                                    text: '导入中',
                                });
                            }
                        },
                        success: function (data) {
                            $.messager.progress('close');
                            if (data == "OK") {
                                $.messager.alert('信息提示', '提交成功！');

                            } else {
                                $.messager.alert('信息提示', '提交失败！');
                            }
                            $('#choice-import-dialog').dialog('close');
                            $("#choice-datagrid").datagrid("reload");
                        }
                    });
                }
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#choice-import-dialog').dialog('close');
                }
            }]
        });
    }

    $(function () {
        $('#choice-import-input').filebox({
            buttonText: '选择导入的excel文件',
            buttonAlign: 'right',
            height: 40
        });
        $("#choice-import-dialog").find("a").css("margin-left","176px");
    });


    /**
     * Name 删除记录
     */
    function openRemoveChoice() {
        var items = $('#choice-datagrid').datagrid('getSelections');
        if (items.length != 0) {
            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
                if (result) {
                    var ids = [];
                    $(items).each(function () {
                        ids.push(this.choiceId)
                    });
                    var url = 'deleteMultipleChoice';
                    $.get(url, {stuId: ids.toString()}, function (data) {
                        if (data == "OK") {
                            $.messager.alert('信息提示', '删除成功！', 'info');
                            $("#choice-datagrid").datagrid("reload");// 重新加载数据库
                            $('#choice-dialog').dialog('close');
                        } else if (data == "NO") {
                            $.messager.alert('信息提示', '删除部分！', 'info');
                            $('#choice-dialog').dialog('close');
                        }
                        else {
                            $.messager.alert('信息提示', '删除失败！', 'info');
                        }
                    });
                }
            });
        }
    }


    /**
     * Name 打开添加窗口
     */
    function openAddChoice() {
        $('#choice-form').form('clear');
        $('#couseId').combobox('reload');
        $('#choice-dialog').dialog({
            closed: false,
            modal: true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    $("#choice-form").form('submit', {
                        url: 'addMultipleChoice',
                        onSubmit: function () {

                        },
                        success: function (data) {
                            if (data == "OK") {
                                $.messager.alert('信息提示', '提交成功！');
                                $("#choice-datagrid").datagrid("reload");// 重新加载数据库
                                $('#choice-dialog').dialog('close');
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
                    $('#choice-dialog').dialog('close');
                }
            }]
        });
    }

    /**
     * Name 打开修改窗口
     */
    function openEditChoice() {
        var rows = $('#choice-datagrid').datagrid('getSelections');
        if (rows.length > 1) {
            $.messager.alert("提示信息", "只能选择一行");
        } else if (rows.length > 0) {
            $('#choice-dialog').dialog({
                closed: false,
                modal: true,
                title: "修改信息",
                buttons: [{
                    text: '确定',
                    iconCls: 'icon-ok',
                    handler: function () {
                        $('#choice-form').form('submit', {
                            url: 'updateMultipleChoice',
                            success: function (data) {
                                if (data == "OK") {
                                    $.messager.alert('信息提示', '修改成功！');
                                    $("#choice-datagrid").datagrid("reload");// 重新加载数据库
                                    $('#choice-dialog').dialog('close');
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
                        $('#choice-dialog').dialog('close');
                    }
                }]
            });
            $('#choice-form').form('load', rows[0]);
        } else {
            $.messager.alert('信息提示', '请选择修改对象！');
        }
    }


    /**
     * Name 载入数据
     */
    $('#choice-datagrid').datagrid({
        url: 'multiple',
        rownumbers: true,
        singleSelect: false,
        pageSize: 10,
        pagination: true,
        queryParams: formChoiceJson(),
        multiSort: true,
        fitColumns: true,
        fit: true,
        columns: [[
            {field: '', checkbox: true, hidden: true},
            {field: 'id', title: '编号', width: 50, hidden: true},
            {field: 'couname', title: '科目', width: 100 },
            {field: 'multipleTitle', title: '题目', width: 180},
            {field: 'answera', title: '答案A', width: 100},
            {field: 'answerb', title: '答案B', width: 100},	
            {field: 'answerc', title: '答案C', width: 100},
            {field: 'answerd', title: '答案D', width: 100},
            {field: 'multipleAnswer', title: '正确答案', width: 50},
            {field: 'degree', title: '难度等级', width: 50},
            {field: 'createTime', title: '时间(xx-年xx-月xx-日) ',sortable: true, width: 135, formatter: function (value, row, index) {
            	if(value != null) {
	            	var date = new Date(value);
	            	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
            	} else {
            		return "";
            	}
            }}
        ]]
    });
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
        var bChoiceName = $("#choice-course-value").val();
        var title = $("#choice-name-value").val();
        var degree = $("#choice-degree-value").val();
        var bTime = $("#bdaytime-course-value").val();
        var eTime = $("#edaytime-course-value").val();
        return {"couseId": bChoiceName,"title":title,"degree":degree, "bTime": bTime,"eTime":eTime};
    }
    /**
     * 创建课程的下拉框
     */
    $('#couseId').combobox({
        url: '${basePath}/kingother/getCourseJson',
        valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    }); 
    $('#choice-course-value').combobox({
        url: '${basePath}/kingother/getCourseJson',
        valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    }); 
</script>
</body>
</html>