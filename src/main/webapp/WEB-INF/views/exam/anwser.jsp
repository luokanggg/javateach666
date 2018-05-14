<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/common/easyui.jspf" %>
    <title>Title</title>
</head>
<body>
	<link href="${basePath}/static/css/test/main.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}/static/css/test/test.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
	    .hasBeenAnswer {
	        background: #5d9cec;
	        color: #fff;
	    }
		.messager-body :nth-child(2){
		    margin: 27px auto;
		    font-size: 16px;
		    font-family: "Microsoft Yahei";
		}
		.messager-icon{
		    margin-top: 10px;
		    margin-left: 20px;
		}
	</style>
<form id="answer-paper-form" method="post">
    <!-- 答案id -->
    <input type="hidden" readonly="readonly" name="answerId" value="${answerSheet.id}"/>
    <!-- 学生id -->
    <input  type="hidden" name="stuId" value="${answerSheet.stuId}">
    <!-- 试卷id -->
    <input type="hidden" readonly name="paperId" value="${answerSheet.examId}">
    <!-- 成绩id -->
    <input name="scoreId" type="hidden" value="${achievement.id}"/>
     <div class="test_content">
     	<div class="test_content_title">
         	<h2>学生：${answerSheet.stuName}</h2>&nbsp;&nbsp;
         	<h2>试卷：${answerSheet.examName}</h2>&nbsp;&nbsp;
         	<h2>客观题得分：${achievement.singleScore+achievement.multipleScore+achievement.judgmentScore+achievement.completionScore}</h2>
     	</div>
 	</div>
    <div class="test_content_nr">
    <ul>
        <c:set value="1" var="subjectiveNum"/>
        <c:forEach var="sutSub" items="${stuSubAnswer}">
            <li>
                <div class="test_content_nr_tt">
                    <i>${subjectiveNum }</i><span>(${sutSub.score}分)</span><font>${sutSub.title}</font>
                </div>
                <div class="test_content_nr_tt" style="border: none;">
                	<span>参考答案:</span><font>${sutSub.answer}</font>
                </div>
                <div class="test_content_nr_tt" style="border: none;">
                	<span>学生答案:</span><font>${sutSub.checked}</font>
                </div>
                <div class="test_content_nr_tt" style="border: none;">
                	<span>教师评分:</span><input class="easyui-numberbox" name="${sutSub.id}" data-options="required:true,min:0,max:${sutSub.score},precision:0">
                </div>
            </li>
            <c:set value="${subjectiveNum+1 }" var="subjectiveNum"/>
        </c:forEach>
    </ul>
    </div>
</form>
</body>
</html>
