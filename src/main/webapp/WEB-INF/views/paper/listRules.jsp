<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/common/easyui.jspf"%>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="rules-list-toolbar">
            <div class="wu-toolbar-button">
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="addListRulesByHand()" plain="true">添加规则</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" onclick="updateListRules()" plain="true">修改</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="removeListRules()" plain="true">删除</a>
	            <br/>
	            <form id="Rule-search-form" style="display: inline-block">
			                     课程：<input id="rule-course-value"  editable="false" panelMaxHeight="100"/>
			                     规则名(yyyy/MM/dd-hh:mm)：<input class="easyui-textbox" id="rule-name-value"/>
	                <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
	                <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
	            </form>
	             <br/>
            </div>
        </div>
        <!-- End of toolbar -->
        <table id="rules-list-datagrid" toolbar="#rules-list-toolbar">
        	
        </table>
        
        <div id="handPaperDialog"></div>
        <div id="autoPaperDialog"></div>
    </div>
</div>
<script type="text/javascript">
 
    /**
     * Name 删除记录
     */
    function removeListRules() {
        var items = $('#rules-list-datagrid').datagrid('getSelections');
        if (items.length != 0) {
            $.messager.confirm('信息提示', '确定要删除该记录？', function (result) {
                if (result) {
                    var ids = [];
                    $(items).each(function () {
                        ids.push(this.id)
                    });
                    var url = 'deleteExamRule';
                    $.get(url, {"examruleids": ids.toString()}, function (data) {
                        if (data == "OK") {
                            $.messager.alert('信息提示', '删除成功！', 'info');
                            $('#listRules_add').dialog('close');
                        } else if (data == "NO") {
                            $.messager.alert('信息提示', '删除部分！', 'info');

                            $('#listRules_add').dialog('close');
                        }
                        $("#rules-list-datagrid").datagrid("reload");// 重新加载数据库
                    });
                }
            });
        }
    }


    /**
     * 手动添加试卷
     */
     function addListRulesByHand() {
    	$("#handPaperDialog").dialog({
    		width:800,
    		height:600,
    		top:20,
    		title:'创建规则',
    		href:"initHandPaper",
    		modal:true,
    		onLoad:function(){
    			clear();
    			$("#subbutton").attr("onclick","paperSubmit()");
    		},
    		onBeforeClose:function(){
    			$('#handBox').remove();
    		}
    	});
     }
    
    
    /**
     * 修改规则
     */
     function updateListRules(){
    	 var rows = $('#rules-list-datagrid').datagrid('getSelections');
         if (rows.length > 1) {
             $.messager.alert("提示信息", "只能选择一行!");
         } else if(rows.length > 0 ){
        	 if(rows[0].ruleType == 1){
        		 $("#handPaperDialog").dialog({
       	     		width:800,
       	     		height:600,
       	     		top:20,
       	     		title:'修改规则',
       	     		href:"initHandPaper",
       	     		modal:true,
       	     		onLoad:function(){
       	     			clear();
       	     			$("#subbutton").attr("onclick","paperSubmitUpdate()");
	       	     		$.ajaxSettings.async =false;
	                	var ruleJson ="";
	                	$.get('getExamRuleById',{'ruleId':rows[0].id},function(data){
	                		ruleJson = data;
	                	});
	                	$.ajaxSettings.async =true;
	                	if(ruleJson != "") {
	                		var rule = eval('('+ruleJson+')');
	                		/* $("#coursIDHand").combobox('select', rule.courseID);
	                		$("#coursIDHand").combobox('readonly', true); */
	                		$("#coursIDHand").attr('value', rule.course.id); 
	                		$("#choiceIds").attr("value",rule.singleRules);
	                		$("#choiceIdsScore").attr("value",rule.singleScore);
	                		$("#choiceIdsNum").attr("value",rule.singleNum);
	                		$("#multipleIds").attr("value",rule.multipleRules);
	                		$("#multipleIdsScore").attr("value",rule.multipleScore);
	                		$("#multipleIdsNum").attr("value",rule.multipleNum);
	                		$("#blankIds").attr("value",rule.completionRules);
	                		$("#blankIdsScore").attr("value",rule.completionScore);
	                		$("#blankIdsNum").attr("value",rule.completionNum);
	                		$("#judgeIds").attr("value",rule.judgmentRules);
	                		$("#judgeIdsScore").attr("value",rule.judgmentScore);
	                		$("#judgeIdsNum").attr("value",rule.judgmentNum);
	                		$("#subjectiveIds").attr("value",rule.subjectiveRules);
	                		$("#subjectiveIdsScore").attr("value",rule.subjectiveScore);
	                		$("#subjectiveIdsNum").attr("value",rule.subjectiveNum);
	                		$("#handAllNum").attr("value",rule.singleNum+rule.multipleNum+
	                				 rule.completionNum+rule.judgmentNum+rule.subjectiveNum);	
	                		$("#handAllScore").attr("value",rule.allScore);
	                		$("#ruleId").attr("value",rows[0].id);
	                		$("#ruleName").attr("value",rule.ruleName);
	                	}
	                	
       	     		},
       	     		onBeforeClose:function(){
       	     			$("#handBox").remove();
       	     		}
       	     	});
	            $('#paper-list-form').form('load',rows[0]);
        	 }
         } else {
         	$.messager.alert('信息提示', '请选择修改对象！');
         }
    }

    /**
     * Name 载入数据
     */
    $('#rules-list-datagrid').datagrid({
        url: 'examrules',
        rownumbers: true,
        singleSelect: false,
        pageSize: 20,
        pagination: true,
        queryParams: formChoiceJson(),
        multiSort: true,
        fitColumns: true,
        fit: true,
        columns: [[
            {field:'',checkbox: true},
            {field: 'id', title: '编号', width: 50, hidden: true},
            {field: 'couname', title: '课程', width: 50},
            {field: 'ruleName', title: '规则名', width: 110},
            {field: 'singleNum', title: '单选题数量', width: 100},
            {field: 'singleScore', title: '单选题分数', width: 100},
            {field: 'multipleNum', title: '多选题数量', width: 100},
            {field: 'multipleScore', title: '多选题分数', width: 100},
            {field: 'completionNum', title: '填空题数量', width: 100},
            {field: 'completionScore', title: '填空题分数', width: 100},
            {field: 'judgmentNum', title: '判断题数量', width: 100},
            {field: 'judgmentScore', title: '判断题分数', width: 100},
            {field: 'subjectiveNum', title: '主观题数量', width: 100},
            {field: 'subjectiveScore', title: '主观题分数', width: 100},
            {field: 'allScore', title: '总分数', width: 100},
        ]]
    });
    
    /* 搜索方法*/
    $("#choice-search-btn").click(function () {
        //点击搜索
        $('#rules-list-datagrid').datagrid({
            queryParams: formChoiceJson()
        });
    });
    /*重置方法*/
    $("#choice-search-reset").click(function () {
        $("#Rule-search-form").form('clear');
        $('#rules-list-datagrid').datagrid({
            queryParams: formChoiceJson()
        });
    });
    //将表单数据转为json
    function formChoiceJson() {
        var course = $("#rule-course-value").val();
        var name = $("#rule-name-value").val();
        var type = $("#rule-type-value").val();
        return {"course": course,"name":name, "type": type};
    }
    
    /**
     * 创建课程的下拉框
     */
    $('#rule-course-value').combobox({
        url: '${basePath}/kingother/getCourseJson',
        valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    });
</script>
</body>
</html>