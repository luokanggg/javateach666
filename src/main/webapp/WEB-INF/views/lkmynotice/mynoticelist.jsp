<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
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
				消息来源：<select id="dd" class="easyui-combobox" name="dept" style="width:200px;"> 
                	  <option selected="selected">--请选择--</option>
                	  <option value="1">老师消息</option>
                	  <option value="3">学生消息</option>
				</select>
				<input type="checkbox" id="history">:显示所有历史消息
				&nbsp;<a id="choice-search-btn" class="easyui-linkbutton">查看</a>
                <!-- <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="right" iconCls="icon-ok" onclick="goReapair()"
                   >重修选课</a> -->
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
		        url: 'getmynoticelist',
		        rownumbers: true,
		        singleSelect: true,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            //{field: '', checkbox: true},
		            {field: 'nottitle', title: '标题', width: 50, sortable: true},
		            {field: 'notname', title: '发送人', width: 50, sortable: true},
		            {field: 'notcontent', title: '内容', width: 100},
		            {field: 'starttime', title: '发送时间', width: 180, sortable: true,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '年' +m + '月' + d + '日';
	                    }
	                },
		            {field: 'endtime', title: '过期时间', width: 100,
	                	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '年' +m + '月' + d + '日';
	                    }
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="goTo()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
					$("a[name='opera']").linkbutton({text:'前往',plain:true,iconCls:'icon-edit'});    
				}
		    });
			


		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		    	/* var a = $("#_easyui_textbox_input1").val();
		    	alert(a); */
		        /* var noticetype = $("#dd").val();
		        if(noticetype == null || noticetype == "--请选择--"){
		        	window.location.reload(true);
		        } */
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
		        var nottype = $("#dd").val();
		        //alert(nottype);
		        var nowtime = null;
		        var history = $("#history");
		        if(history[0].checked == true){
		        	nowtime = "历史";
		        	//alert("选中");
		        }
		        if(nottype == null || nottype == "--请选择--"){
		        	nottype = 0;
		        }
		        //alert(nottype);
		        //alert(couyear);
		        //alert(semester);
		        return {"nottype": nottype,"nowtime": nowtime};
		    }
		  	//查看已选课程表
		    function goTo() {
		  		//alert("前往");
		  		var rows = $('#classinfo-datagrid').datagrid('getSelections');
		  		//alert(rows[0].noturl);
		  		if(rows[0].noturl == "#"){
		  			alert("没有可跳转的页面！");
		  			return false;
		  		}else{
		  			window.location.href = rows[0].noturl; 
		  		}
		    	//window.location.href = "chooseclassonline";
		  	}
	</script>
</body>
</html>