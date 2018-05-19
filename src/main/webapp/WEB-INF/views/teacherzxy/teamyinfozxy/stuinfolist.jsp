<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>学生信息</title>
</head>
<style>
.datagrid-row {
  height: 45px;
}
.datagrid-header-row td
{
  background-color:#9aCCFF;
  height:32px;
}
</style>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="stuinfolist-toolbar">
            <div class="wu-toolbar-button">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	  学年：<select id="classyear" class="easyui-combobox" name="couyear" style="width:60px;">   
    					<c:forEach items="${diccouyear}" var="se">
    						<option  value="${se.dvalue }" >${se.dvalue }</option>
    					</c:forEach>     
    			   </select> &nbsp;&nbsp; &nbsp;
    			班级：<select id="classid" class="easyui-combobox" name="classid" style="width:110px;">   
    					<c:forEach items="${classlist}" var="cs">
    						<option  value="${cs.id }" >${cs.claname }</option>
    					</c:forEach>     
    			   </select>&nbsp;&nbsp; &nbsp;
                    <a id="choice-search-btn" style="height:27px" class="easyui-linkbutton" iconCls="icon-search">搜索</a> &nbsp;&nbsp;&nbsp;&nbsp;
            </div>

        </div>
        
        <table id="stuinfolist-datagrid" toolbar="#stuinfolist-toolbar"></table>
    </div>
</div>
	<script type="text/javascript">

		   $('#stuinfolist-datagrid').datagrid({
		        url: 'getstuinfolist',
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

		            {field: 'stuno', title: '编号', width: 50,align:'center',sortable:true },
		            {field: 'stuname', title: '姓名', width: 50,align:'center'},

		            {field: 'classname', title: '班级名称', width: 120,align:'center'},
		            {field: 'stuphone', title: '手机号', width: 100,align:'center'},
		            {field: 'stuage', title: '年龄', width: 40, align:'center'},
		            {field: 'stusex', title: '性别', width: 50,align:'center'},
		            {field: 'college', title: '学院', width: 100,align:'center'},
		            {field: 'nation', title: '名族', width: 60,align:'center'},
		            {field: 'political', title: '政治面貌', width: 80,align:'center'},
		            {field: 'major', title: '专业', width: 100,align:'center'}
		           
				]],
				onLoadSuccess:function(data){    
					//	$("a[name='opera']").linkbutton({text:'发消息',plain:true,iconCls:'icon-edit'});    
				}
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#stuinfolist-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		  
		    //将表单数据转为json
		    function formChoiceJson() {
		        var classid = $("#classid option:selected").val();
		        var classyear = $("#classyear option:selected").val();
		        
		        return {"classyear": classyear,"classid": classid};
		    } 
	</script>
</body>
</html>