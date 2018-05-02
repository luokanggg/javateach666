<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/lklist.css"/>
<title>我的已选课程</title>
<!-- <style type="text/css">
	#classinfo-datagrid tr{height:40px;}
</style> -->
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="classinfo-toolbar">
            <div class="wu-toolbar-button">
                <!-- <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openAddChoice()"
                   plain="true">添加</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditChoice()"
                   plain="true">修改</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="openRemoveChoice()"
                   plain="true">删除</a>
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-ok" onclick="openImportChoice()"
                   plain="true">导入</a> -->
                <!-- <form id="choice-search-form" style="display: inline-block">
			                    学号：<input class="easyui-textbox" id="choice-course-value"/>
			                    学生姓名：<input class="easyui-textbox" id="choice-course-value2"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form> -->
                <!-- <select id="cc" class="easyui-combobox" name="dept" style="width:200px;" onclick="LoadSuccess()" > 
                	  <option selected="selected">请选择</option>
				</select> -->
				&nbsp;&nbsp;学年：&nbsp;&nbsp;<input id="cc" name="dept" value="--请选择--">  
				&nbsp;&nbsp;学期：&nbsp;&nbsp;<select id="dd" class="easyui-combobox" name="dept" style="width:200px;"> 
                	  <option selected="selected">--请选择--</option>
                	  <option>1</option>
                	  <option>2</option>
				</select>
				&nbsp;&nbsp;<a id="choice-search-btn" iconCls="icon-search" class="easyui-linkbutton">查看</a>
                &nbsp;&nbsp;<a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconCls="icon-tip" onclick="sendTeaMessage()"
                   >联系老师</a>
                &nbsp;&nbsp;<a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="right" iconCls="icon-redo" onclick="submitWirk()"
                   >提交作业</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="classinfo-datagrid" toolbar="#classinfo-toolbar"></table>
    </div>
</div>
	<script type="text/javascript">
		
			/**
		     * Name 载入数据
		     */
		    $('#classinfo-datagrid').datagrid({
		        url: 'getsemesterteacherlist',
		        rownumbers: true,
		        singleSelect: true,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		        striped:true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            //{field: '', checkbox: true},
		            {field: 'id', hidden :true},
		            {field: 'teaid', hidden :true},
		            {field: 'stuid', hidden :true},
		            {field: 'couname', title: '课程名称', width: 50, sortable: true},
		            {field: 'teaname', title: '教师姓名', width: 50, sortable: true},
		            {field: 'couyear', title: '学年', width: 100},
		            {field: 'semester', title: '学期', width: 100, sortable: true},
		            {field: 'couaddress', title: '上课地点', width: 200},
		            {field: 'counumber', title: '课程人数', width: 100},
		            {field: 'alcounumber', title: '选课人数', width: 100},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="goTo()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
					$("a[name='opera']").linkbutton({text:'课程学生',plain:true,iconCls:'icon-back'});    
				}
		    });
			
		    $('#cc').combobox({    
		        url:'alreadychoosecombobox',    
		        valueField:'id',    
		        textField:'id'   
		    });  


		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		    	/* var a = $("#_easyui_textbox_input1").val();
		    	alert(a); */
		    	var couyear = $("#_easyui_textbox_input1").val();
		        var semester = $("#dd").val();
		        if((couyear == null || couyear == "--请选择--") || (semester == null || semester == "--请选择--")){
		        	window.location.reload(true);
		        }
		        //点击搜索
		        $('#classinfo-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    /* $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $("#choice-course-value2").val('');
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    }); */
		    //将表单数据转为json
		    function formChoiceJson() {
		        var couyear = $("#_easyui_textbox_input1").val();
		        var semester = $("#dd").val();
		        if((couyear == null || couyear == "--请选择--") || (semester == null || semester == "--请选择--")){
		        	couyear = 0;
		        	semester = 0;
		        }
		        //alert(couyear);
		        //alert(semester);
		        return {"couyear": couyear,"semester": semester};
		    }
		    
		    //查看课程学生
		   function goTo(){
		    	var rows = $('#classinfo-datagrid').datagrid('getSelections');
		    	if("" != rows){
		    		window.location.href = "goclassstudents?id=" + rows[0].id;
		    	}
		    }
		    
		  	//给老师发消息
		    function sendTeaMessage() {
		    	var rows = $('#classinfo-datagrid').datagrid('getSelections');
		    	//alert(rows);
		    	if("" != rows){
		    		//获取完整的日期  
		    		var date=new Date;  
		    		var year=date.getFullYear();   
		    		var month=date.getMonth()+1;
		    		var semester = 0;
		    		if(month >0 && month <= 6){
		    			semester = 1;
		    		}else{
		    			semester = 2;
		    		}
		    		if(rows[0].couyear == year && rows[0].semester == semester){
		    			$.messager.prompt('消息框', '说点什么', function(message){
							if (message){
								//alert('我说的话: '+message);
								var param = {
										"teaid":rows[0].teaid,
										"message":message
								}
								$.ajax({
									type:'post',
									url:'sendmessagetotea',
									contentType:"application/json",    //必须配置
									data:JSON.stringify(param),//转换成字符串，客户端作为生产者
									success:function(result){
										//alert(result.stuimage);
										alert(result.responseDesc);
									} 
								});
							}
						});
		    		}else{
		    			alert("只能给本学期的课程老师发消息！");
		    		}
		    	}
		  	}
		  	
		  	//提交作业
		    function submitWirk() {
		  		
		    	var rows = $('#classinfo-datagrid').datagrid('getSelections');
		    	//alert(rows);
		    	if("" != rows){
		    		//获取完整的日期  
		    		var date=new Date;  
		    		var year=date.getFullYear();   
		    		var month=date.getMonth()+1;
		    		var semester = 0;
		    		if(month >0 && month <= 6){
		    			semester = 1;
		    		}else{
		    			semester = 2;
		    		}
		    		if(rows[0].couyear == year && rows[0].semester == semester){
		    			window.location.href = "submitclasswork?id=" + rows[0].id;
		    		}else{
		    			alert("只能提交本学期的课程作业！");
		    		}
		    	}
		  		
		    	//alert("请选择一条数据");
		    	//window.location.href = "chooseclassonline";
		  	}
	</script>
</body>
</html>