<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>成绩管理</title>
    <%@include file="/common/easyui.jspf" %>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'center',border:false">
		    	<!-- Begin of toolbar -->
		        <div id="sc-toolbar" style="align:center;">
		            <div class="wu-toolbar-button">
		                <form style="width: 100%" name="searchscoreform" method="post" action="" id ="searchscoreform">
						试卷:<input id="paperId"  class="easyui-textbox"/>
						学生姓名:<input id="stuId"  class="easyui-textbox"/>
						<a id="scoresearchbtn" class="easyui-linkbutton">搜索</a>
						<a id="scoreresetbtn" class="easyui-linkbutton">重置</a>
						<a id="reportbtn" class="easyui-linkbutton">导出</a>
						</form>
		            </div>
		        </div>
	        	<!-- End of toolbar -->
	        <table id="sc-datagrit" toolbar="#sc-toolbar"></table>
	    </div>
	</div>
<script type="text/javascript">
	// 总成绩
	$("#totalScore").ready(function(){
		var auto = $("#autoScore").val();
		var sub = $("#subScore").val();
		$("#totalScore").val(auto+sub);
	});
	
</script>
<!-- End of easyui-dialog -->
<script type="text/javascript">
    /**
     * Name 载入数据
     */
    $(function () {
        $('#sc-datagrit').datagrid({
            url: 'getScores',
            rownumbers: true,
            singleSelect: false,
            pageSize: 20,
            pagination: true,
            multiSort: true,
            fitColumns: true,
            fit: true,
            queryParams: formJson(),// 在请求远程数据的时候发送额外的参数。
            rowStyler:function(index,row){    
    	        if (row.scoreId > 0){    
    	            return 'background-color:#fff;'; // 为成绩行设置颜色   
    	        }    
    	    }, 
            columns: [[
                {field: 'id', title: '成绩编号', width: 100,hidden : true},
                {field: 'stuName', title: '学生姓名', width: 180},
                {field: 'paperName', title: '试卷名', width: 100},
                {field: 'singleScore', title: '单选题分数', width: 100,sortable: true,formatter: function (value, row, index) {
                	if(value == null) {
    	            	return 0;
                	} else {
                		return value;
                	}
                }},
                {field: 'multipleScore', title: '多选题分数', width: 100,sortable: true,formatter: function (value, row, index) {
                	if(value == null) {
    	            	return 0;
                	} else {
                		return value;
                	}
                }},
                {field: 'judgmentScore', title: '判断题分数', width: 100,sortable: true,formatter: function (value, row, index) {
                	if(value == null) {
    	            	return 0;
                	} else {
                		return value;
                	}
                }},
                {field: 'completionScore', title: '填空题分数', width: 100,sortable: true,formatter: function (value, row, index) {
                	if(value == null) {
    	            	return 0;
                	} else {
                		return value;
                	}
                }},
                {field: 'subjectiveScore', title: '主观题分数', width: 100,sortable: true,formatter: function (value, row, index) {
                	if(value == null) {
    	            	return 0;
                	} else {
                		return value;
                	}
                }},
                {field: 'score', title: '总分数', width: 100,sortable: true,formatter: function (value, row, index) {
                	if(value == null) {
    	            	return 0;
                	} else {
                		return value;
                	}
                }}
            ]]
        });
    });
    
    
    

	/* 搜索方法*/
 	$("#scoresearchbtn").click(function(){
 		//点击搜索
 		$('#sc-datagrit').datagrid({ 
 			queryParams: formJson()
 		});   
 	}); 
	
	/*重置方法*/
 	$("#scoreresetbtn").click(function(){
 		$("#searchscoreform").form('clear');
 		// 重新加载数据
 		$('#sc-datagrit').datagrid({ 
 				queryParams: formJson()
 		}); 
 	});

    //将表单数据转为json
    function formJson() {
    	var paperId = $("#paperId").val();
    	var stuId = $("#stuId").val();
    	// 返回json
        return {"paperId":paperId,"stuName":stuId};
    }
    
    /*导出方法*/
 	$("#reportbtn").click(function(){
 		var paperId = $("#paperId").val();
    	var stuId = $("#stuId").val();
    	if(paperId != null && paperId != "") {
    		if(stuId != null && stuId != "") {
	    		window.location.href="${basePath}/teascore/getScoreExcel?paperId="+paperId+"&stuId="+stuId;
    		} else {
	    		window.location.href="${basePath}/teascore/getScoreExcel?paperId="+paperId+"&stuId=";
    		}
    	} else {
    		alert("请选择需要导出的试卷！");
    	}
 		
 	});
    
    /**
     * 创建试卷的下拉框
     */
    $("#paperId").combobox({
		url:'${basePath}/exam/getExamPaperJson',
		valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    });

</script>
</body>
</html>

