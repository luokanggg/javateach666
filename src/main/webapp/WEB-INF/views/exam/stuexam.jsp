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
    <table id="exam-datagrid" class="easyui-datagrid" toolbar="#exam-toolbar"></table>
</div>
<script type="text/javascript">
    /**
     * Name 载入数据
     */
    $('#exam-datagrid').datagrid({
        url: 'stuexams',
        rownumbers: true,
        singleSelect: true,
        pageSize: 20,
        pagination: true,
        multiSort: true,
        fitColumns: true,
        fit: true,
        columns: [[
            {field: 'id', hidden:true},
            {field: 'couname', title: '考试科目', width: 180},
            {field: 'examPaperName', title: '考试试卷', width: 100},
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
            {field: 'semester', title: '学期', width: 100},
            {field: '_operate', title: '操作', width: 100,formatter:function(value,row,index){
            	$.ajaxSettings.async = false;
                var flag = 0;
                var examid = row.release.examPaper.id;
                var btime = row.release.beginTime;
                var nowtime = new Date().getTime();
                var releaseId = row.release.id;
                $.get('isAreadyExam', {'examid': examid}, function (data) {
                	flag = data;
                }); 
                $.ajaxSettings.async = true;
                if(flag == 0 && btime <= nowtime && nowtime <= (btime+1800000)) {
	            	return '<a href="${basePath}/stuexam/startExam?releaseId='+releaseId+'&examid='+examid+'" target="blank">参加考试</a>';
                } else {
	            	return '';
                }
            }}
        ]]
    });
</script>
</body>
</html>



