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
						学年:<input id="couyear"  class="easyui-textbox"/>
						学期:<select id="semester" class="easyui-combobox" style="width: 50px;">
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
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
    /**
     * Name 载入数据
     */
    $(function () {
        $('#sc-datagrit').datagrid({
            url: 'getScores',
            rownumbers: true,
            singleSelect: true,
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
                {field: 'couname', title: '课程名', width: 180},
                {field: 'score', title: '总分数', width: 100,sortable: true}
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
	
	/*导出方法*/
 	$("#reportbtn").click(function(){
 		var couyear = $("#couyear").val();
    	var semester = $("#semester").val();
    	if(couyear != null && couyear != "" && !semester != null && semester != "") {
    		window.location.href="${basePath}/stuscore/getScoreExcel?couyear="+couyear+"&semester="+semester;
    	}
 		
 	});

    //将表单数据转为json
    function formJson() {
    	var couyear = $("#couyear").val();
    	var semester = $("#semester").val();
    	// 返回json
        return {"couyear":couyear,"semester":semester};
    }
    
    /**
     * 创建学年的下拉框
     */
    $("#couyear").combobox({
		url:'${basePath}/kingother/getCouyearJson',
		valueField: 'id',
        textField: 'name',
        panelMaxHeight: '100',
    });

</script>
</body>
</html>

