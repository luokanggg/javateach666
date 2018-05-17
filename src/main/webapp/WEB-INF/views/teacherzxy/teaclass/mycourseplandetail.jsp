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
<body >
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="mycouplan-toolbar">
            <div class="wu-toolbar-button">
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
                   &nbsp;&nbsp; <a id="choice-search-reset" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
                </form>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                <form  id="fileform" action="uploadmyplan" method="post" enctype="multipart/form-data" style="display: inline">
                	 <input id="idd" name="id" type="text" value="0" style="display: none"/>
                	 <input id="file" name="file" style="width:100px;height:30px;" class="easyui-filebox" data-options="buttonText:'选择附件'"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" onclick="return check()" value="提交" class="easyui-linkbutton" style="width:50px;height:30px">
                	<!-- <input type="text" id="planfile" name="planfile" class="easyui-filebox" />
                &nbsp;&nbsp;	<a  class="easyui-linkbutton" onclick="uploadFile()" >上传附件</a> -->
                </form>
            </div>
        </div>
        
        <!-- End of toolbar -->
        <table id="teacouinfo-datagrid" toolbar="#mycouplan-toolbar"></table>
    </div>
</div>

	<script type="text/javascript">
	
			/**
		     * Name 载入数据
		     */
		    $('#teacouinfo-datagrid').datagrid({
		        url: 'getMycoursePlan',
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
		            {field: 'id', title:'ID',width: 20,align:'center'},
		            {field: 'couname', title: '课程名', width: 40,align:'center'},
		             {field: 'planclass', title: '计划班级', width: 60,align:'center'}, 
		            {field: 'plantitle', title: '计划标题', width: 120,align:'center'},
		            {field: 'plangoal', title: '计划目标', width: 180,align:'center'},
		            {field: 'plantime1', title: '开始时间', width: 80,align:'center',
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }	
		            },
		            {field: 'plantime2', title: '结束时间', width: 80,align:'center',
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '-' +m + '-' + d;
	            	  }	
		            },
		            {field: 'plancontent', title: '计划内容', width: 200,align:'center'},
		            {field: 'operate2', title: '操作', align:'center',width:$(this).width()*0.13,formatter:function(value, row, index)
		            {	var str = '<a href="#" name="down" class="easyui-linkbutton" onclick="downplanfile()"></a>';  
						return str;
		            }}
		           
					]],
				onLoadSuccess:function(data){    
						//$("a[name='opera']").linkbutton({text:'在线预览',plain:true,iconCls:'icon-search'}); 
						$("a[name='down']").linkbutton({text:'查看文件',plain:true,iconCls:'icon-search'});  
				},
				
		    });
		    $('#teacouinfo-datagrid').datagrid({   rownumbers:false});
		    $("#teacouinfo-datagrid").datagrid("hideColumn", "id");
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#teacouinfo-datagrid').datagrid({
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
		    
		   
		     function check(){
				   var row = $('#teacouinfo-datagrid').datagrid('getSelections');
				   if (row.length == 0) {
						$.messager.alert('操作提示', "请选择一条记录！", 'warning');
						return false;
					}else{
						var id=row[0].id;
					
						$("#idd").val(id);
					}
			}
		     
		    /*  function detailfile(){
		    	 var row = $('#teacouinfo-datagrid').datagrid('getSelections');
				   if (row.length == 0) {
						$.messager.alert('操作提示', "请选择一条记录！", 'warning');
						return false;
					}
				   var id = row[0].id;
				   $.ajax({
						type:'get',
						url:'lookdetailfile?id='+id,
						success:function(result){
							var planfile=result.mess;
							if(result.mess=="8888"){
								$.messager.alert('提示', "没有教学方案附件信息！", 'warning');
							}else{
								window.location.href="gomyplanfile";
							}
							
						}
			    	});
		     }
		      */
		     function downplanfile(){
		    	 var row = $('#teacouinfo-datagrid').datagrid('getSelections');
				   if (row.length == 0) {
						$.messager.alert('操作提示', "请选择一条记录！", 'warning');
						return false;
					}
				   var id = row[0].id;
				   //alert(id+"id")
				   window.location.href="downplanfile?id="+id;
		     }
	</script>
</body>
</html>