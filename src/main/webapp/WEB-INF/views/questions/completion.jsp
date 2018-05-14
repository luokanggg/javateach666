<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/easyui.jspf" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="blank-toolbar">
            <div class="wu-toolbar-button">
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openAddBlank()"
                   plain="true">添加</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditBlank()"
                   plain="true">修改</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="openRemoveBlank()"
                   plain="true">删除</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" onclick="openImportBlank()"
                   plain="true">导入</a>
                <br/>
                <form id="blank-search-form" style="display: inline-block">
			                     科目：<input  id="blank-course-value" editable="false" panelMaxHeight="100"/>
			                     题目：<input class="easyui-textbox" id="blank-name-value"/>
			                    难度等级：<select class="easyui-combobox" id="choice-degree-value" style="width:80px;" panelMaxHeight="100" editable="false">
			               	<option></option>
			               	<option value="1">1</option>
			               	<option value="2">2</option>
			               	<option value="3">3</option>
			               	<option value="4">4</option>
			              </select>
			                     时间：<input type="date" id="bdaytime-course-value"/>~<input type="date" id="edaytime-course-value"/>
                    <a id="blank-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="blank-search-reset" class="easyui-linkbutton">重置</a>
                </form>
          
            </div>
        </div>
        <!-- End of toolbar -->
        <table id="blank-datagrid" toolbar="#blank-toolbar"></table>
    </div>
</div>
<!-- Begin of easyui-dialog -->

<!-- 添加修改页面 -->
<div id="blank-dialog" style="width:400px; padding:10px;">
    <form id="blank-form" method="post">
        <table>
            <tr>
                <td><input type="hidden" name="id" id="id"/></td>
            </tr>
            <tr>
                <td width="60" align="right">课程</td>
                <td>
                	<!-- <input name="course.id"  panelMaxHeight="100" class="easyui-textbox"/> -->
                	<input type="text" name="course.id" id="courseId"required="required" editable="false" panelMaxHeight="100"/>
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
                    <textarea name="completionTitle" rows="3" cols="28"></textarea>
				</td>
            </tr>
            <tr>
                <td align="right">正确答案</td>
                <td>
                	<textarea name="completionAnswer" rows="3" cols="28"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="blank-import-dialog" class="easyui-dialog" data-options="closed:true" style="padding: 30px" >
    <form style="text-align: center;" id="blank-import-form" method="post" enctype="multipart/form-data">
        <input id="fb" name="excel" style="width:300px">
    </form>
</div>

</div>
<!-- End of easyui-dialog -->
<script type="text/javascript">
    //导入
    function openImportBlank() {
        $('#blank-import-form').form('clear');
        $('#blank-import-dialog').dialog({
            title: '填空题导入',
            width: 400,
            height: 200,
            closed: false,
            modal: true,
            buttons: [{
                text: '保存',
                iconCls: 'icon-ok',
                handler: function () {
                    $("#blank-import-form").form('submit', {
                        url: 'importExcel',
                        onSubmit: function () {
                            var validate =  $(this).form('validate');
                            if (validate){
                                $.messager.progress({
                                    title: '提示',
                                    msg: '正在导入...',
                                    text: '导入中',
                                });
                            }

                            return validate;
                        },
                        success: function (data) {
                            $.messager.progress('close');
                            if (data == "OK") {
                                $.messager.alert('信息提示', '提交成功！');
                            } else {
                                $.messager.alert('信息提示', '提交失败！');
                            }
                            $('#blank-import-dialog').dialog('close');
                            $('#blank-datagrid').datagrid('reload');
                        }

                    });
                }
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#blank-import-dialog').dialog('close');
                }
            }]
        });
    }

    $(function () {
        $('#fb').filebox({
            buttonText: '选择导入的excel文件',
            buttonAlign: 'right',
            height: 40,
        });
        $("#blank-import-dialog").find("a").css("margin-left","176px");
    });

    /**
     * Name 删除记录
     */
    function openRemoveBlank() {
        var items = $('#blank-datagrid').datagrid('getSelections');
        if (items.length != 0) {
            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
                if (result) {
                    var ids = [];
                    $(items).each(function () {
                        ids.push(this.blankId);
                    });
                    var url = 'deleteCompletion';
                    $.get(url, {"completionids": ids.toString()}, function (data) {
                        if (data == "OK") {
                            $.messager.alert('信息提示', '删除成功！', 'info');
                            $("#blank-datagrid").datagrid("reload");// 重新加载数据库
                            $('#blank-dialog').dialog('close');
                        } else if (data == "NO") {
                            $.messager.alert('信息提示', '删除部分！', 'info');
                            $('#blank-dialog').dialog('close');
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
    function openAddBlank() {
        $('#blank-form').form('clear');
        $('#blank-dialog').dialog({
            closed: false,
            modal: true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    $("#blank-form").form('submit', {
                        url: 'addCompletion',
                        onSubmit: function () {
							
                        },
                        success: function (data) {
                            if (data == "OK") {
                                $.messager.alert('信息提示', '提交成功！');
                                $("#blank-datagrid").datagrid("reload");// 重新加载数据库
                                $('#blank-dialog').dialog('close');
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
                    $('#blank-dialog').dialog('close');
                }
            }]
        });
    }

    /**
     * Name 打开修改窗口
     */
    function openEditBlank() {
        var rows = $('#blank-datagrid').datagrid('getSelections');
        if (rows.length > 1) {
            $.messager.alert("提示信息", "只能选择一行");
        } else if (rows.length > 0) {
            $('#blank-dialog').dialog({
                closed: false,
                modal: true,
                title: "修改信息",
                buttons: [{
                    text: '确定',
                    iconCls: 'icon-ok',
                    handler: function () {
                        $('#blank-form').form('submit', {
                            url: 'updateCompletion',
                            success: function (data) {
                                if (data == "OK") {
                                    $.messager.alert('信息提示', '修改成功！');
                                    $("#blank-datagrid").datagrid("reload");// 重新加载数据库
                                    $('#blank-dialog').dialog('close');
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
                        $('#blank-dialog').dialog('close');
                    }
                }]
            });
            $('#blank-form').form('load', rows[0]);
        } else {
            $.messager.alert('信息提示', '请选择修改对象！');
        }
    }


    /**
     * Name 载入数据
     */
    $('#blank-datagrid').datagrid({
        url: 'completions',
        rownumbers: true,
        singleSelect: false,
        pageSize: 10,
        pagination: true,
        multiSort: true,
        fitColumns: true,
        queryParams: formBlankJson(),
        fit: true,
        columns: [[
            {field: '', checkbox: true},
            {field: 'id', title: '编号', width: 50, sortable: true, hidden: true},
            {field: 'couname', title: '科目', width: 50, sortable: true},
            {field: 'completionTitle', title: '题目', width: 180, sortable: true},
            {field: 'completionAnswer', title: '正确答案', width: 100},
            {field: 'degree', title: '难度等级', width: 50},
            {field: 'createTime', title: '时间(xx-年xx-月xx-日) ', width: 100, formatter: function (value, row, index) {
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
    $("#blank-search-btn").click(function(){
        //点击搜索
        $('#blank-datagrid').datagrid({
            queryParams: formBlankJson()
        });
    });

    /*重置方法*/
    $("#blank-search-reset").click(function(){
        $("#blank-search-form").form('clear');
        $("#bdaytime-course-value").val('');
        $("#edaytime-course-value").val('');
        // 重新加载数据
        $('#blank-datagrid').datagrid({
            queryParams: formBlankJson()
        });
    });

    //将表单数据转为json
    function formBlankJson() {
        var bCourseName = $("#blank-course-value").val();
        var bTitleName = $("#blank-name-value").val();
        var degree = $("#choice-degree-value").val();
        var bTime = $("#bdaytime-course-value").val();
        var eTime = $("#edaytime-course-value").val();
        return {"couseId": bCourseName,"bTitleName":bTitleName,"degree":degree, "bTime": bTime,"eTime":eTime};
    }
    /**
     * 创建课程的下拉框
     */
    $('#courseId').combobox({
        url: '${basePath}/kingother/getCourseJson',
        valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    });
    $('#blank-course-value').combobox({
        url: '${basePath}/kingother/getCourseJson',
        valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    });
</script>
</body>
</html>