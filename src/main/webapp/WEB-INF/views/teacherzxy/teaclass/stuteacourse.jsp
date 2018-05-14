<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<title>学生选课信息</title>
</head>
<style>
.datagrid-row {
  height: 36px;
}
</style>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="stuteacouinfo-toolbar">
            <div class="wu-toolbar-button">
 
                <form id="choice-search-form" style="display: inline-block">
			                        学年：<select id="couyear" class="easyui-combobox" name="couyear" style="width:70px;">   
    					<c:forEach items="${semester}" var="se">
    						<option  value="${se.value }" >${se.value }</option>
    					</c:forEach>     
    			   </select> &nbsp;&nbsp; &nbsp;  &nbsp;         
			   学期：<select id="semester" class="easyui-combobox" name="semester" style="width:70px;">   
    					<option value="1">1</option>   
   						<option value="2">2</option>     
    			  </select> &nbsp;&nbsp; 
                    <a id="choice-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                   &nbsp;&nbsp; <a id="choice-search-reset" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
                </form>
              &nbsp;&nbsp; <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-save" onclick="openExportSearch()"
                  >导出</a>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="stuteacouinfo-datagrid" toolbar="#stuteacouinfo-toolbar"></table>
    </div>
</div>
	<script type="text/javascript">
		
			/**
		     * Name 载入数据
		     */
		    $('#stuteacouinfo-datagrid').datagrid({
		        url: 'getstuteacouinfolist',
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
		        striped:true,
		        columns: [[
		            //{field: '', checkbox: true},
		            {field: 'stuno', title: '学号', width:60,align:'center'},
		            {field: 'stuname', title: '学生姓名', width: 70,align:'center'},
		            {field: 'claname', title: '所在班级', width: 100,align:'center'},
		            {field: 'classyear', title: '年级', width: 100,align:'center'},
		            {field: 'major', title: '专业', width: 100,align:'center'},
		            {field: 'collage', title: '学院', width: 120,align:'center'},
		            {field: 'teano', title: '教师编号', width: 60,align:'center'},
		            {field: 'teaname', title: '教师姓名', width: 80,align:'center'},
		            {field: 'couyear', title: '学年', width: 40,align:'center'},
		            {field: 'semester', title: '学期', width: 40,align:'center'},
		            {field: 'couname', title: '课程名', width: 100,align:'center'},
		            {field: 'couaddress', title: '教室', width: 100,align:'center'}
				]],
				onLoadSuccess:function(data){    
						//$("a[name='opera']").linkbutton({text:'发消息',plain:true,iconCls:'icon-edit'});    
				}
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#stuteacouinfo-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		        var couyear = $("#couyear option:selected").val();
		        var semester = $("#semester option:selected").val();
		        
		        return {"couyear": couyear,"semester": semester};
		    }
		  	//导出
		    function openExportSearch() {
		    	 var couyear = $("#couyear option:selected").val();
			     var semester = $("#semester option:selected").val();
		      $.messager.confirm('提示', '是否确认导出学生的选课信息 ', function(r){
				if (r){
					window.location.href = "exportstuteaclassinfo?couyear=" + couyear + "&semester=" + semester;
				}
			  });
			 
		  	}
		  
	</script>
</body>
</html>