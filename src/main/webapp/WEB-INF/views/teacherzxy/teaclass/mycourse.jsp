<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css"/>
<title>课程信息</title>
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
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			
 			
                <form id="choice-search-form" style="display: inline-block">
			                        学年：<select id="couyear" class="easyui-combobox" name="couyear" style="width:70px;">   
    					<c:forEach items="${diccouyear}" var="se">
    						<option  value="${se.value }" >${se.value }</option>
    					</c:forEach>     
    			   </select> &nbsp;&nbsp; &nbsp;  &nbsp;         
			   学期：<select id="semester" class="easyui-combobox" name="semester" style="width:70px;">   
    					<option value="1">1</option>   
   						<option value="2">2</option>     
    			  </select> &nbsp;&nbsp; &nbsp;  &nbsp;
                    <a id="choice-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                   &nbsp;&nbsp; 
                   
                   <a id="choice-search-reset" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
                </form>
		
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
		        url: 'getMycourselist',
		        rownumbers: true,
		        singleSelect: true,
		         
	            
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        striped:true,
		        columns: [[
		            //{field: '', checkbox: true},
		            {field: 'teaid', title: '教师ID', width: 30,align:'center'},
		            {field: 'couname', title: '课程名', width: 60,align:'center'},
		            /* {field: 'teaname', title: '教师姓名', width: 80,align:'center'}, */
		            {field: 'couyear', title: '学年', width: 40,align:'center'},
		            {field: 'semester', title: '学期', width: 40,align:'center'},
		            
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="plancourse()" ></a>';  
						
						return str;  
					}}
		           
				]],
				onLoadSuccess:function(data){    
						$("a[name='opera']").linkbutton({text:'编制教学计划',plain:true,iconCls:'icon-edit'}); 
						
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
		    
		    function plancourse()
		    {
		    	var row = $('#stuteacouinfo-datagrid').datagrid('getSelections');
		    	var teaid = row[0].teaid;
		    	var couname=row[0].couname;
		    	var couyear=row[0].couyear;
		    	var semester=row[0].semester;
		    
		    	$.ajax({
					type:'get',
					url:'plancorse?teaid1='+teaid+'&couname='+couname+'&couyear='+couyear+'&semester='+semester,
					
					success:function(result){
						window.location.href="mycourseplan";
						//alert("sdd");
					}
		    	});
		    }
		  
	</script>
</body>
</html>