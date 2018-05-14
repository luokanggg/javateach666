<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>资料管理</title>
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
        <div id="informationinfo-toolbar">
            <div class="wu-toolbar-button">
                <form id="choice-search-form" style="display: inline-block">
			                    &nbsp;&nbsp;&nbsp;类型：<input class="easyui-textbox" id="infotype"/>
			                   &nbsp;&nbsp;&nbsp; 发布者：<input class="easyui-textbox" id="publish_person_name"/>
                   &nbsp;&nbsp;&nbsp; <a id="choice-search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
                    &nbsp;&nbsp;&nbsp;<a id="choice-search-reset" class="easyui-linkbutton" iconCls="icon-undo">重置</a>
                </form>
            </div>

        </div>
        <!-- End of toolbar -->
        <table id="informationinfo-datagrid" toolbar="#informationinfo-toolbar"></table>
    </div>
</div>
	<script type="text/javascript">
		
			/**
		     * Name 载入数据
		     */
		    $('#informationinfo-datagrid').datagrid({
		        url: 'getinformationinfolist',
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
		          
		            {field: 'id', title: 'ID', width: 50, sortable: true,align:'center'},
		            {field: 'title', title: '资料标题', width: 80,align:'center'},
		            {field: 'infotype', title: '资料类型', width: 80,align:'center'},
		            {field: 'publish_person', title:'发布者编号', width: 80,align:'center'},
		            {field: 'publish_person_name', title: '发布者', width: 80,align:'center'},
		            {field: 'publish_date', title: '上传时间', width: 80,align:'center'},
		            {field: 'statement', title: '介绍', width: 140,align:'center'},
		            {field: 'content', title: '地址', width: 160,align:'center',
		            	formatter:function rowformatter(value,row,index){
		            		return "<a href='#' onclick='downfile("+row.id+","+index+");'>"+row.content+"</a>";
		            	}
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="deleteinformation()" ></a>'+"   "+'<a href="#" name="down" class="easyui-linkbutton" onclick="downfile()"></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
						$("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-cancel'});    
						$("a[name='down']").linkbutton({text:'下载',plain:true,iconCls:'icon-save'});
				}
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#informationinfo-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $("#infotype").val('');
		        $("#publish_person_name").val('');
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		        var infotype = $("#infotype").val();
		        var publish_person_name = $("#publish_person_name").val();
		      //  alert(publish_person_name);
		       // alert(infotype);
		        return {"infotype": infotype,"publish_person_name": publish_person_name};
		    }
		    
		  	function deleteinformation(){
		  		//alert("发送消息");
		  		var rows = $('#informationinfo-datagrid').datagrid('getSelections');
		  		
		  		$.messager.confirm('警告', '确定要删除吗？', function(message){
					if (message){
						//alert('我说的话: '+message);
						$.ajax({
							type:'get',
							url:'deleteInformation?id='+rows[0].id+"&publish_person="+rows[0].publish_person+"&content="+rows[0].content,
							//dataType:'json',    //必须配置
							//data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								//alert(result.stuimage);
								
								if(result.mess=="删除成功！"){
									self.location.reload();
								}else{
									$.messager.alert("提示","       "+result.mess,"info");
								}
							} 
						});
					}
				});
		  	} 
		  	
		  	function downfile(row,index){
		  		//alert("发送消息");
		  		var row = $('#informationinfo-datagrid').datagrid('getData').rows[index];
				var id = row.id;
				var content=row.content;
				//alert(id+" "+content)
				$.ajax({
					type:'get',
					url:"downfile?id="+id+"&content="+content,
					success:function(result){
						
					} 
				});
			}
		  	
	</script>
</body>
</html>