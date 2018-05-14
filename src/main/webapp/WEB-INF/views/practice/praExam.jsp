<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>

<head>
    <%@include file="/common/easyui.jspf" %>
	<link href="${basePath}/static/css/test/main.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}/static/css/test/test.css" rel="stylesheet" type="text/css"/>
	<style>
			.box{
				display:none;
				width: 500px;
				height: 400px;
				position: fixed;
				top: 50px;
				left: 30%;
				border-radius: 20px;
				background-color: #eee;
			}
			.box_tb{
				position: absolute;
				padding-top: 20px;
				left:20%;
				width:55%;
			}
			.box_tb span{
				display: block;
				width: 61%;
				text-align: right;
				font-size: 18px;
				font-family: "微软雅黑";
			}
			.sure{
				position: absolute;
				display: block;
				background-color:#389fc3;
				text-decoration: none;
				width: 80px;
				height: 40px;
				font-size: 20px;
				color: #FFF;
				text-align: center;
				line-height: 40px;
				border-radius: 10px;
				margin: 325px 40%;
				cursor:pointer;
			}
		</style>
</head>
<body>
<div class="main">
    <!--nr start-->
    <div class="test_main">
        <div class="nr_left">
            <div class="test">
                <h1 style="text-align: center"><%-- ${course.courseName} --%>模拟考试（总分100）</h1>

                <form id="stu-exam-form" method="post">
                    <div class="test_title">
                        <p class="test_time">
                            <b class="timer"></b>
                        </p>
                        <font><input type="button" name="test_jiaojuan" value="交卷" onclick="check()"></font>
                    </div>
                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>单选题</h2>
                            <p>
                                <span>共</span><i
                                    class="content_lit">${singleChoiceNum }</i><span>题，</span><span>合计</span><i
                                    class="content_fs">${singleScore }</i><span>分</span>
                            </p>

                        </div>
                    </div>
                    <div class="test_content_nr">
                        <ul>
                            <c:set value="1" var="choiceNum"/>
                            <c:forEach items="${choices }" var="choices">
                                <li id="qu_0_${choiceNum }">
                                    <div class="test_content_nr_tt">
                                        <i>${choiceNum }</i><span>(2分)</span><font>${choices.singleTitle }</font>
                                        <input type="hidden" name="singleScore${choices.id}" value="2"/>
                                        <input type="hidden" class="single" value="${choices.id}" an="${choices.singleAnswer }"/><br/>
                                        <span style="display:none;" class="answer">参考答案：${choices.singleAnswer }</span>
                                    </div>
                                    <div class="test_content_nr_main">
                                        <ul>
                                            <li class="option">
                                                <input type="radio" class="radioOrCheck" value="A"
                                                       name="singleChoice${choices.id }"
                                                       id="0_answer_${choiceNum }_option_1"/>
                                                <label for="0_answer_${choiceNum }_option_1">
                                                    A.<p class="ue" style="display: inline;">${choices.answera }</p>
                                                </label>
                                            </li>

                                            <li class="option">
                                                <input type="radio" class="radioOrCheck" value="B"
                                                       name="singleChoice${choices.id }"
                                                       id="0_answer_${choiceNum }_option_2"/>
                                                <label for="0_answer_${choiceNum }_option_2">
                                                    B.<p class="ue" style="display: inline;">${choices.answerb }</p>
                                                </label>
                                            </li>

                                            <li class="option">
                                                <input type="radio" class="radioOrCheck" value="C"
                                                       name="singleChoice${choices.id }"
                                                       id="0_answer_${choiceNum }_option_3"/>
                                                <label for="0_answer_${choiceNum }_option_3">
                                                    C.<p class="ue" style="display: inline;">${choices.answerc }</p>
                                                </label>
                                            </li>

                                            <li class="option">
                                                <input type="radio" class="radioOrCheck" value="D"
                                                       name="singleChoice${choices.id }"
                                                       id="0_answer_${choiceNum }_option_4"/>
                                                <label for="0_answer_${choiceNum }_option_4">
                                                    D.<p class="ue" style="display: inline;">${choices.answerd }</p>
                                                </label>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <c:set value="${choiceNum+1 }" var="choiceNum"/>
                            </c:forEach>
                        </ul>
                    </div>
                    
                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>多选题</h2>
                            <p>
                                <span>共</span><i
                                    class="content_lit">${mulChoiceNum }</i><span>题，</span><span>合计</span><i
                                    class="content_fs">${mulScore }</i><span>分</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <ul>
                            <c:set value="1" var="mulNum"/>
                            <c:forEach items="${mulChoices }" var="mulChoices">
                                <li id="qu_1_${mulNum }">
                                    <input type="hidden" name="multi" value="${mulChoices.id}"/>
                                    <div class="test_content_nr_tt">
	                                    <i>${mulNum }</i><span>(4分)</span><font>${mulChoices.multipleTitle }</font>
	                                    <input type="hidden" name="multiScore${mulChoices.id}" value="4"/>
	                                    <input type="hidden" class="multi" value="${mulChoices.id}" an="${mulChoices.multipleAnswer }"/><br/>
	                                    <span style="display:none;" class="mulChoices_answer">参考答案：${mulChoices.multipleAnswer }</span>
                                    </div>

                                    <div class="test_content_nr_main">
                                        <ul>
                                            <li class="option">
                                                <input type="checkbox" class="radioOrCheck" value="A"
                                                       name="multChoice${mulChoices.id }"
                                                       id="1_answer_${mulNum }_option_1"/>
                                                <label for="1_answer_${mulNum }_option_1">
                                                    A.<p class="ue"
                                                         style="display: inline;">${mulChoices.answera }</p>
                                                </label>
                                            </li>
                                            <li class="option">
                                                <input type="checkbox" class="radioOrCheck" value="B"
                                                       name="multChoice${mulChoices.id }"
                                                       id="1_answer_${mulNum }_option_2"/>
                                                <label for="1_answer_${mulNum }_option_2">
                                                    B.<p class="ue"
                                                         style="display: inline;">${mulChoices.answerb }</p>
                                                </label>
                                            </li>
                                            <li class="option">
                                                <input type="checkbox" class="radioOrCheck" value="C"
                                                       name="multChoice${mulChoices.id }"
                                                       id="1_answer_${mulNum }_option_3"/>
                                                <label for="1_answer_${mulNum }_option_3">
                                                    C.<p class="ue"
                                                         style="display: inline;">${mulChoices.answerc }</p>
                                                </label>
                                            </li>
                                            <li class="option">
                                                <input type="checkbox" class="radioOrCheck" value="D"
                                                       name="multChoice${mulChoices.id }"
                                                       id="1_answer_${mulNum }_option_4"/>
                                                <label for="1_answer_${mulNum }_option_4">
                                                    D.<p class="ue"
                                                         style="display: inline;">${mulChoices.answerd }</p>
                                                </label>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <c:set value="${mulNum+1 }" var="mulNum"/>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>判断题</h2>
                            <p>
                                <span>共</span><i
                                    class="content_lit">${judgeChoiceNum }</i><span>题，</span><span>合计</span><i
                                    class="content_fs">${judgeScore }</i><span>分</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <ul>
                            <c:set value="1" var="judgeNum"/>
                            <c:forEach items="${judges }" var="judges">
                                <li id="qu_2_${judgeNum }">
                                    <input type="hidden" name="judge" value="${judges.id}"/>
                                    <div class="test_content_nr_tt">
                                        <i>${judgeNum }</i><span>(2分)</span><font>${judges.judgmentTitle }</font>
                                        <input type="hidden" name="judgeScore${judges.id}" value="2"/>
                                        <input type="hidden" class="judge" value="${judges.id}" an="${judges.judgmentAnswer }"/><br/>
	                                	<span style="display:none;" class="judges_answer">参考答案：${judges.judgmentAnswer == 1 ? "正确" : "错误" }</span>
                                    </div>
                                    <div class="test_content_nr_main">
                                        <ul>
                                            <li class="option">
                                                <input type="radio" class="radioOrCheck"
                                                       name="judgeNum${judges.id }"
                                                       id="2_answer_${judgeNum }_option_1" value="1"/>
                                                <label for="2_answer_${judgeNum }_option_1">
                                                    <p class="ue" style="display: inline;">正确</p>
                                                </label>
                                            </li>
                                            <li class="option">
                                                <input type="radio" class="radioOrCheck"
                                                       name="judgeNum${judges.id }"
                                                       id="2_answer_${judgeNum }_option_2" value="2"/>
                                                <label for="2_answer_${judgeNum }_option_2">
                                                    <p class="ue" style="display: inline;">错误</p>
                                                </label>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <c:set value="${judgeNum+1 }" var="judgeNum"/>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>填空题</h2>
                            <p>
                                <span>共</span><i
                                    class="content_lit">${blankChoiceNum }</i><span>题，</span><span>合计</span><i
                                    class="content_fs">${blankScore }</i><span>分</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <ul>
                            <c:set value="1" var="blankNum"/>
                            <c:forEach items="${blanks }" var="blanks">
                                <li id="qu_3_${blankNum }">
                                    <input type="hidden" name="blank" value="${blanks.id}"/>
                                    <div class="test_content_nr_tt">
	                                    <i>${blankNum }</i><span>(2分)</span><font>${blanks.completionTitle }</font>
	                                    <input type="hidden" name="blankScore${blanks.id}" value="2"/>
                                    	<input type="hidden" class="blank" value="${blanks.id}" an="${blanks.completionAnswer }"/><br/>
	                                	<span style="display:none;" class="blanks_answer">参考答案：${blanks.completionAnswer}</span>
                                    </div>
                                    <div class="test_content_nr_main">
                                        <ul>
                                            <li class="option" style="padding-bottom:10px">
                                                <label for="3_answer_${blankNum }_option_1">
                                                    <input type="text" name="blankNum${blanks.id }"
                                                           id="3_answer_${blankNum }_option_1"
                                                           style="width:100%;height:22px;float:none;"/>
                                                </label>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <c:set value="${blankNum+1 }" var="blankNum"/>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>主观题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${subChoiceNum }</i><span>题，</span><span>合计</span><i
                                    class="content_fs">${subScore }</i><span>分</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <ul>
                            <c:set value="1" var="subjectiveNum"/>
                            <c:forEach items="${subjectives }" var="subjectives">
                                <li id="qu_4_${subjectiveNum }">
                                    <input type="hidden" name="subjective" value="${subjectives.id}"/>
                                    <div class="test_content_nr_tt">
                                         <i>${subjectiveNum }</i><span>(8分)</span><font>${subjectives.subjectiveTitle }</font><br/>
                                         <input type="hidden" name="subjectiveScore${subjectives.id}" value="8"/>
                                         <span style="display:none;" class="subjectives_answer">参考答案：${subjectives.subjectiveAnswer}</span>
                                    </div>
                                    <div class="test_content_nr_main">
                                        <ul>
                                            <li class="option">
                                                <label for="4_answer_${subjectiveNum }_option_1">
                                                        <textarea name="subjectiveNum${subjectives.id}"
                                                                  style="width:100%;height:50px;"></textarea>
                                                </label>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <c:set value="${subjectiveNum+1 }" var="subjectiveNum"/>
                            </c:forEach>
                        </ul>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="nr_right">
        <div class="nr_rt_main">
            <div class="rt_nr1">
                <div class="rt_nr1_title">
                    <h1>
                        答题卡
                    </h1>
                    <p class="test_time">
                        <b class="timer"></b>
                    </p>
                </div>
                <div class="rt_content">
                    <div class="rt_content_tt">
                        <h2>单选题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${singleChoiceNum }</i><span>题</span>
                        </p>
                    </div>
                    <div class="rt_content_nr answerSheet">
                        <c:set value="1" var="chNum"/>
                        <ul>
                            <c:forEach items="${choices }" var="choices">
                                <li><a class="singleAn" href="#qu_0_${chNum }">${chNum }</a></li>
                                <c:set value="${chNum+1 }" var="chNum"/>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="rt_content">
                    <div class="rt_content_tt">
                        <h2>多选题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${mulChoiceNum }</i><span>题</span>
                        </p>
                    </div>
                    <div class="rt_content_nr answerSheet">
                        <ul>
                            <c:set value="1" var="mNum"/>
                            <c:forEach items="${mulChoices }" var="mulChoices">
                                <li><a class="mulAn" href="#qu_1_${mNum }">${mNum }</a></li>
                                <c:set value="${mNum+1 }" var="mNum"/>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="rt_content">
                    <div class="rt_content_tt">
                        <h2>判断题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${judgeChoiceNum }</i><span>题</span>
                        </p>
                    </div>
                    <div class="rt_content_nr answerSheet">
                        <ul>
                            <c:set value="1" var="juNum"/>
                            <c:forEach items="${judges }" var="judges">
                                <li><a class="judgeAn" href="#qu_2_${juNum }">${juNum }</a></li>
                                <c:set value="${juNum+1 }" var="juNum"/>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="rt_content">
                    <div class="rt_content_tt">
                        <h2>填空题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${blankChoiceNum }</i><span>题</span>
                        </p>
                    </div>
                    <div class="rt_content_nr answerSheet">
                        <ul>
                            <c:set value="1" var="blNum"/>
                            <c:forEach items="${blanks }" var="blanks">
                                <li><a class="blankAn" href="#qu_3_${blNum }">${blNum }</a></li>
                                <c:set value="${blNum+1 }" var="blNum"/>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="rt_content">
                    <div class="rt_content_tt">
                        <h2>主观题</h2>
                        <p>
                            <span>共</span><i class="content_lit">${subChoiceNum }</i><span>题</span>
                        </p>
                    </div>
                    <div class="rt_content_nr answerSheet">
                        <ul>
                            <c:set value="1" var="subNum"/>
                            <c:forEach items="${subjectives }" var="subjectives">
                                <li><a href="#qu_4_${subNum }">${subNum }</a></li>
                                <c:set value="${subNum+1 }" var="subNum"/>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--nr end-->
		<div class="box" id="box">
			<table class="box_tb">
				<tr><th><span>单选题正确个数：</span></th><td><p id="choiceNumbers"></p></td></tr>
				<tr><th><span>单选题得分：</span></th><td><p id="choiceScores"></p></td></tr>
				<tr><th><span>多选题正确个数：</span></th><td><p id="multiNumbers"></p></td></tr>
				<tr><th><span>多选题得分：</span></th><td><p id="multiScores"></p></td></tr>
				<tr><th><span>判断题正确个数：</span></th><td><p id="judgeNumbers"></p></td></tr>
				<tr><th><span>判断题得分：</span></th><td><p id="judgeScores"></p></td></tr>
				<tr><th><span>填空题正确个数：</span></th><td><p id="blankNumbers"></p></td></tr>
				<tr><th><span>填空题得分：</span></th><td><p id="blankScores"></p></td></tr>
				<tr><td colspan="2">----------------------------------------------------</td></tr>
				<tr><th><span>总分：</span></th><td><p id="allScores"></p></td></tr>
			</table>
			
			<a onclick="sure()" class="sure">确认</a>
			
		</div>

<style>
    .hasBeenAnswer {
        background: #5d9cec;
        color: #fff;
    }
</style>
<script src="${basePath}/static/js/jquery.easy-pie-chart.js"></script>
<!--时间js-->
<script src="${basePath}/static/js/jquery.countdown.js"></script>
<script>
	$(function() {
		$('li.option label').click(
		function() {
			var examId = $(this).closest('.test_content_nr_main')
					.closest('li').attr('id'); // 得到题目ID
			var cardLi = $('a[href="#' + examId + '"]'); // 根据题目ID找到对应答题卡
			// 设置已答题
			if (!cardLi.hasClass('hasBeenAnswer')) {
				cardLi.addClass('hasBeenAnswer');
			}
		});
		$('li.option input').click(
		function() {
			var examId = $(this).closest('.test_content_nr_main')
					.closest('li').attr('id'); // 得到题目ID
			var cardLi = $('a[href="#' + examId + '"]'); // 根据题目ID找到对应答题卡
			// 设置已答题
			if (!cardLi.hasClass('hasBeenAnswer')) {
				cardLi.addClass('hasBeenAnswer');
			}
		});
		
	});

    var examTime = 90;
    var time = examTime * 60;
    function timer() {
        // 计算小时
        var hour = handlerTime(Math.floor(time / 3600));
        // 计算分钟
        var minutes = handlerTime(Math.floor(time % 3600 / 60));
        // 计算秒
        var second = handlerTime(Math.floor(time % 3600 % 60));
        // 判断考试时间到自动提交试卷
        if (time == 0) {
            stu_submit_exam()
            clearInterval(timers);
        }
        $(".timer").html(hour + ":" + minutes + ":" + second);
        time--;
    }
    function handlerTime(t) {
        var t1 = t;
        if (t < 10) {
            t1 = "0" + t;
        }

        return t1;
    }

    var timers = setInterval("timer();", 1000);
	
    function check(){
    	var choiceNumber = 0;
    	var multiNumber = 0;
    	var judgeNumber = 0;
    	var blankNumber = 0;
	    var single = $(".single");
		if (single != null) {
			single.each(function(index, element) {
				var num = 'singleChoice' + $(this).val();
				var ch = $('input:radio[name=' + num + ']:checked').val();
				var an = $(this).attr("an");
				if (ch == an) {
					$(".answer").eq(index).css(
							"background-color", "green");
					$(".test_content_nr_tt").eq(index).css(
							"background-color", "#fff");
					$(".singleAn").eq(index).css(
							"background-color", "green");
					choiceNumber++;
				} else {
					$(".answer").eq(index).css(
							"background-color", "red");
					$(".test_content_nr_tt").eq(index).css(
							"background-color", "red");
					$(".singleAn").eq(index).css(
							"background-color", "red");
				}
			});
			$(".answer").css("display", "inline-block");
    	}
		
		var multi = $(".multi");
		if (multi != null) {
			multi.each(function(index, element) {
				var num = 'multChoice' + $(this).val();
				var ch = "";
				$('input:checkbox[name=' + num + ']:checked').each(
						function() {
							ch += $(this).val();
						});
				var an = $(this).attr("an");
				if (ch == an) {
					$(".mulChoices_answer").eq(index).css(
							"background-color", "green");
					$(".test_content_nr_tt").eq(index+15).css(
							"background-color", "#fff");
					$(".mulAn").eq(index).css("background-color", "green");
					multiNumber++;
				} else {
					$(".mulChoices_answer").eq(index).css(
							"background-color", "red");
					$(".test_content_nr_tt").eq(index+15).css(
							"background-color", "red");
					$(".mulAn").eq(index).css("background-color", "red");
				}
			});
			$(".mulChoices_answer").css("display", "inline-block");
    	}
		
		var judge = $(".judge");
		if (judge != null) {
			judge.each(function(index, element) {
				var num = 'judgeNum' + $(this).val();
				var ch = $('input:radio[name=' + num + ']:checked').val();
				var an = $(this).attr("an");
				if (ch == an) {
					$(".judges_answer").eq(index)
							.css("background-color", "green");
					$(".test_content_nr_tt").eq(index+20).css(
							"background-color", "#fff");
					$(".judgeAn").eq(index)
					.css("background-color", "green");
					judgeNumber++;
				} else {
					$(".judges_answer").eq(index).css("background-color", "red");
					$(".test_content_nr_tt").eq(index+20).css(
							"background-color", "red");
					$(".judgeAn").eq(index)
					.css("background-color", "red");
				}
			});
			$(".judges_answer").css("display", "inline-block");
		}
		
		
		var blank = $(".blank");
		if (blank != null) {
			blank.each(function(index, element) {
				var num = 'blankNum' + $(this).val();
				var ch = $("input[name='"+num+"']").val();
				var an = $(this).attr("an");
				if (ch == an) {
					$(".blanks_answer").eq(index).css(
							"background-color", "green");
					$(".test_content_nr_tt").eq(index+25).css(
							"background-color", "#fff");
					$(".blankAn").eq(index)
					.css("background-color", "green");
					blankNumber++;
				} else {
					$(".blanks_answer").eq(index).css(
							"background-color", "red");
					$(".test_content_nr_tt").eq(index+25).css(
							"background-color", "red");
					$(".blankAn").eq(index)
					.css("background-color", "red");
				}
			});
			$(".blanks_answer").css("display", "inline-block");
    	}
		
		var subjective = $(".subjective");
		if (subjective != null) {
			$(".subjectives_answer").css("display", "inline-block");
		}
		
		$("#box").css("display","block");
		$("#choiceNumbers").html(""+choiceNumber+"");
		$("#choiceScores").html(""+choiceNumber*2+"");
		$("#multiNumbers").html(""+multiNumber+"");
		$("#multiScores").html(""+multiNumber*4+"");
		$("#judgeNumbers").html(""+judgeNumber+"");
		$("#judgeScores").html(""+judgeNumber*2+"");
		$("#blankNumbers").html(""+blankNumber+"");
		$("#blankScores").html(""+blankNumber*2+"");
		var allScores = blankNumber*2+judgeNumber*2+multiNumber*4+choiceNumber*2;
		$("#allScores").html(""+allScores+"");
	}
    
    function sure(){
    	$("#box").css("display","none");
    }
    
</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>
</body>
</html>
