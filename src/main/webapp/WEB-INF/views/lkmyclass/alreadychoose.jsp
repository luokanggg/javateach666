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
				学年：<input id="cc" name="dept" value="--请选择--">  
				学期：<select id="dd" class="easyui-combobox" name="dept" style="width:200px;"> 
                	  <option selected="selected">--请选择--</option>
                	  <option>1</option>
                	  <option>2</option>
				</select>
				<a id="choice-search-btn" class="easyui-linkbutton">查看</a>
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="right" iconCls="icon-ok" onclick="openExportSearch()"
                   >预览我的课表</a>
                <a class="easyui-linkbutton" style="float: right" onclick="backToChooseClassOnline()" >返回</a>
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
		        url: 'goalreadychoose',
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
		            {field: 'id', hidden: true},
		            {field: 'couname', title: '课程名称', width: 50, sortable: true},
		            {field: 'teaname', title: '教师姓名', width: 50, sortable: true},
		            {field: 'couaddress', title: '上课地点', width: 100},
		            {field: 'couyear', title: '学年', width: 100, sortable: true},
		            {field: 'semester', title: '学期', width: 180, sortable: true},
		            {field: 'counumber', title: '课程容量', width: 100},
		            {field: 'alcounumber', title: '已报名人数', width: 100},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="cancelClass()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
						$("a[name='opera']").linkbutton({text:'取消选该课程',plain:true,iconCls:'icon-edit'});    
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
		  	//查看已选课程表
		    function openExportSearch() {
		    	alert("查看已选课程表");
		    	var couyear = $("#_easyui_textbox_input1").val();
		        var semester = $("#dd").val();
		        if((couyear == null || couyear == "--请选择--") || (semester == null || semester == "--请选择--")){
		        	alert("没有已选课程信息！");
		        	return false;
		        }
		    	window.location.href = "myownchooseclassinfo?couyear=" + couyear + "&semester=" + semester;
		    	//var rows = $('#dg').datagrid('getData');
		    	//alert(rows[0].teaname + rows[0].classyear);
		  	}
		  	//取消选择课程
		  	function cancelClass(){
		  		alert("取消选择该课程！");
		  		var rows = $('#classinfo-datagrid').datagrid('getSelections');
		    	alert("id:" + rows[0].id + "teaname:" + rows[0].teaname);
		    	var param = {
		    			"id":rows[0].id,
		    	}
		    	$.ajax({
					type:'post',
					url:'cancelclass',
					contentType:"application/json",    //必须配置
					data:JSON.stringify(param),//转换成字符串，客户端作为生产者
					success:function(result){
						alert(result.responseDesc);
						window.location.reload(true);
					}
				});
		  	}
		  	//回到在线选课页面
		  	function backToChooseClassOnline() {
		  		window.location.href = "chooseclassonline";
		  	}
	</script>
</body>
</html>