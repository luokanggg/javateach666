<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>我的班级</title>
<style type="text/css">
	/* #classinfo-datagrid tr{height:40px;} */
	.filebox{float: right;margin-right: 10px;}
	.textbox-button{right:0;}
</style>
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
                <form id="choice-search-form" style="display: inline-block">
			                    文件名：<input class="easyui-textbox" id="choice-course-value"/>
			                    时间：<input type="date" id="start-time-value"/>
			           —— <input type="date" id="start-time-value2"/>
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>
                </form>
                <form id="fileform" action="uploadmyfile" method="post" enctype="multipart/form-data" style="display: inline">
                	<a style="float: right;" class="easyui-linkbutton" onclick="uploadFile()" >上传文件</a>
                	<input type="text" id="file" name="file" class="easyui-filebox" />
                </form>
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
		        url: 'getmyfilelist',
		        rownumbers: true,
		        singleSelect: false,
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		        columns: [[
		            //{field: '', checkbox: true },
		            {field: 'accname', title: '文件名', width: 100, sortable: true},
		            {field: 'uploadtime', title: '上传时间', width: 180, sortable: true,
		            	formatter : function(value){
	                        var date = new Date(value);
	                        var y = date.getFullYear();
	                        var m = date.getMonth() + 1;
	                        var d = date.getDate();
	                        return y + '年' +m + '月' + d + '日';
	                    }	
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="downloadFile()" ></a>';  
						return str;  
					}},
		            {field: 'operate2', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="delete" class="easyui-linkbutton" onclick="deleteFile()" ></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){    
						$("a[name='opera']").linkbutton({text:'下载',plain:true,iconCls:'icon-edit'});   
						$("a[name='delete']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
				}
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#classinfo-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $("#start-time-value").val('');
		        $("#start-time-value2").val('');
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		        var accname = $("#choice-course-value").val();
		        var beforeuploadtime = $("#start-time-value").val();
		        var afteruploadtime = $("#start-time-value2").val();
		        //alert(question1);
		        //alert(question2);
		        return {"accname": accname,"beforeuploadtime": beforeuploadtime,"afteruploadtime":afteruploadtime};
		    }
		    //下载文件
		  	function downloadFile(){
		    	//alert("下载文件");
		    	var rows = $('#classinfo-datagrid').datagrid('getSelections');
		    	//alert(rows[0].accname + rows[0].accurl);
		    	$.messager.confirm('提示', '你是否要下载该文件？ ', function(r){
					if (r){
						var ss = encodeURIComponent((rows[0].accurl));
						alert(ss);
						var ss2 = encodeURIComponent(ss);
						alert(ss2);
						var aa = decodeURIComponent(ss2);
						alert(aa);
		    			//window.location.href = rows[0].accurl + '';
		    			window.open((rows[0].accurl), "_blank");
					}
				});
		    }
		    //删除文件
		    function deleteFile(){
		    	//alert("删除文件");
		    	var rows = $('#classinfo-datagrid').datagrid('getSelections');
		    	alert(rows[0].accname + rows[0].id);
		    	$.messager.confirm('警告', '你确定要删除该文件吗！？ ', function(r){
					if (r){
				    	var param = {
				    			"id":rows[0].id,
				    			"filename":rows[0].accurl
				    	}
				    	$.ajax({
							type:'post',
							url:'deletemyfile',
							contentType:"application/json",    //必须配置
							data:JSON.stringify(param),//转换成字符串，客户端作为生产者
							success:function(result){
								//alert(result.stuimage);
								alert(result.responseDesc);
							} 
						});	
		    		}
				});
		    }
		    //上传文件
		    function uploadFile(){
		    	alert("上传文件");
		    	var file = $("#filebox_file_id_1").val();
		    	alert(file);
				if(file != null && file != ""){
					alert("上传文件");
					$("#fileform").submit();
				}
		    }
	</script>
</body>
</html>