<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>升职请求记录</title>
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
        <div id="#profinfo-toolbar">
            <div class="wu-toolbar-button">
                
                <form id="choice-search-form" style="display: inline-block">
			                    申请职位：<input  class="easyui-textbox" id="prof_saltv"/>
			                    审批人编号：<input class="easyui-textbox" id="approve_id"/>
			                 <!--    申请时间：<input type="date" id="start-time-value"/>
			           —— <input type="date" id="start-time-value2"/> -->
                    <a id="choice-search-btn" class="easyui-linkbutton">搜索</a>&nbsp;&nbsp;
                    <a id="choice-search-reset" class="easyui-linkbutton">重置</a>&nbsp;&nbsp;&nbsp;
                </form>&nbsp;&nbsp;&nbsp;
                  <a href="profession" class="easyui-linkbutton" iconCls="icon-add" 
                   plain="true">添加</a>&nbsp;&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" id="modify"
                   plain="true">修改</a>&nbsp;&nbsp;&nbsp;
                 <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" iconAlign="left" onclick="RemoveChoice()"
                   plain="true">批量删除</a>&nbsp;&nbsp;&nbsp;
                <a href="javascript:;" style="text-align: right;" class="easyui-linkbutton" iconAlign="left" iconCls="icon-print" onclick="openExportSearch()"
                   plain="true">导出</a>&nbsp;
        		
            </div>
		</div>
	<div id="w" class="easyui-window" title="修改申请" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 320px; height: 180px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>申请的职称：</td>
                        <td><select id="prof" style="width:110px;">
							<c:forEach items="${dic}" var="diclist">
					 		<option  value="${diclist.value }" >${diclist.dicname}</option>
						</c:forEach>
		   	 			</select>
		   				 </td>
                    </tr>
                    <tr>
                        <td>申请的原因：</td>
                        <td><textarea rows="3" cols="20" id="reason"></textarea></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
         <!-- End of toolbar -->
        <table  id="profinfo-datagrid" toolbar="#profinfo-toolbar"></table>  
    </div>
	</div>
	
	<script type="text/javascript">
	
		
		  $('#profinfo-datagrid').datagrid({
		        url: 'getAllprofessionlist',
		        rownumbers: true,
		       // singleSelect: true,
		        checkOnSelect:false,  
	            selectOncheck:false,
		        pageSize: 10,
		        pagination: true,
		        queryParams: formChoiceJson(),
		        multiSort: true,
		       // fitColumns: true,
		       striped:true,
		       pageList:[10,15,20],
		        //fit: true,
		       // pagePosition:bottom,
		        columns: [[
		            {field: 'ck', width:"40" ,align:"center",checkbox: true},
		            {field: 'id', title: 'ID',align:"center", height:50,width: 40, sortable: true},
		            {field: 'prof_person_id', align:"center",title: '申请人ID', width: 70},
		            {field: 'prof_person_name', align:"center",title: '申请人姓名', width: 60},
		            {field: 'now_prof_saltv', align:"center",title: '现任的职称', width: 80},
		            {field: 'prof_saltv', align:"center",title: '申请的职称', width: 100},
		            {field: 'prof_time', align:"center",title: '申请的时间', width: 100},
		            {field: 'prof_reason', align:"center",title: '申请的原因', width: 90},
		            {field: 'approve_id', align:"center",title: '审批人ID', width: 70},
		            {field: 'approve_name', align:"center", title:'审批人姓名', width: 60},
		            {field: 'approve_time', align:"center",title: '审批的时间', width: 100},
		            {field: 'is_prof', title: '是否成功', align:"center",width: 60,
		            	 formatter : function(value,row,index){ 
		                     if(value=='0'){return '驳回'}  
		                     else if(value=='2'){return '未知'}   
		                     else if(value=='1'){return '通过'}
		                   }
		            },
		            {field: 'is_approve', title: '是否审批', width: 60,
		            	formatter : function(value,row,index){ 
		                     if(value=='0'){return '未审批'}  
		                     else if(value=='1'){return '审批'}   
		                   }
		            },
		            {field: 'prof_fk_reason',title: '反馈原因', width: 138,align:'center'},
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" name="opera" class="easyui-linkbutton" onclick="deleteMessage()"></a>';  
						return str;  
					}}
				]],
				onLoadSuccess:function(data){   
				//	alert("成功");
						$("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'}); 
						
				}
		    });
		    /* 搜索方法*/
		    $("#choice-search-btn").click(function () {
		        //点击搜索
		        $('#profinfo-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    /*重置方法*/
		    $("#choice-search-reset").click(function () {
		        $("#choice-search-form").form('clear');
		        $("#prof_saltv").val('');
		        $("#approve_id").val('');
		        $('#choice-datagrid').datagrid({
		            queryParams: formChoiceJson()
		        });
		    });
		    //将表单数据转为json
		    function formChoiceJson() {
		        var prof_saltv = $("#prof_saltv").val();
		        var approve_id = $("#approve_id").val();
		       //alert(prof_saltv);
		     //  alert(approve_id);
		        return {"prof_saltv": prof_saltv,"approve_id": approve_id};
		    }
		  	//导出
		    function openExportSearch() {
		    	
		    	
			  var approve_id = $("#approve_id").val();
		      var prof_saltv = $("#prof_saltv").val();
		     // alert(approve_id+"   "+prof_saltv);
		      $.messager.confirm('提示', '是否确认导出申请记录 ', function(r){
					if (r){
						
			  window.location.href = "exportprofinfo?prof_saltv=" + prof_saltv + "&approve_id=" + approve_id;
					
					}
		      });
		} 
		   
		    function deleteMessage(){
		  		
		  		var rows = $('#profinfo-datagrid').datagrid('getSelections');
		  		if (rows.length >1||rows.length==0) {
					$.messager.alert('操作提示', "请选择一条记录！", 'warning');
					return;
				}
		  		$.messager.confirm('确认', '确定要删除吗？', function(r){
					if (r){
						//alert('确认删除');    
						var param = {
								"id":rows[0].id,
								"is_approve":rows[0].is_approve
						}
						//alert(param);
						$.ajax({
							type:'post',
							url:'profession_delete',
							dataType:"json",    //必须配置
							data:param,//转换成字符串，客户端作为生产者
							success:function(result){
								
								
								if(result.mess=='删除成功'){
									self.location.reload();
								}
								/* self.location.reload();  //刷新框架内页面 */
								$.messager.alert("提示","       "+result.mess,"info");
							} 
						});
					}
		  		});
		  	}
		    
		function RemoveChoice(){
			var selectedRow = $('#profinfo-datagrid').datagrid('getSelections');
			var _list="";
			if (selectedRow.length == 0) {
				$.messager.alert('操作提示', "请至少选择一条记录！", 'warning');
				return;
			}
			for ( var i = 0; i < selectedRow.length; i++) {
				_list+=selectedRow[i].id+",";
			}
			
			$.ajax({
				type:'post',
				url:'profession_delete_Pl',
				data :{'_list':_list},
				success:function(result){
					$.messager.alert("提示","       "+result.mess,"info");
					if(result.mess=='删除成功'){
						
						self.location.reload();
					}
					/* self.location.reload();  //刷新框架内页面 */
					
				} 
			});
		}

		 $(function() {
			
	            openPwd();

	            $('#modify').click(function() {
	                $('#w').window('open');
	            });

	            $('#btnEp').click(function() {
	            	var rows = $('#profinfo-datagrid').datagrid('getSelections');
	    	  		if (rows.length >1||rows.length==0) {
	    				$.messager.alert('操作提示', "请选择一条记录！", 'warning');
	    				return;
	    			}
	    	  		var id=rows[0].id;
	    	  		 $('#w').window('open');
	    	  		var prof_reason=$("#reason").val();
	    	  		var prof_saltv="";
	    			prof_saltv=$("#prof option:selected").text();
	    			data={
	    					"prof_saltv":prof_saltv,
	    					"id":id,
	    					"prof_reason":prof_reason
	    					};
	    			$.ajax({
	    				type:'post',
	    				url:'profession_modify',
	    				dataType:"json",
	    				data:data,
	    				success:function(result){
	    					$.messager.alert("提示","       "+result.mess,"info");
	    					closePwd();
	    					if(result.mess=='修改成功'){
	    						
	    						self.location.reload();
	    					}
	    					
	    				} 
	    			});
	    			
	    			
	            });

				$('#btnCancel').click(function(){closePwd();})

	        });
			
		
		
		
		//设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改申请记录',
                width: 320,
                modal: true,
                shadow: true,
                closed: true,
                height: 180,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
        	$("#reason").val("");
            $('#w').window('close');
        }

	</script>
</body>
</html>