<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/easyui.jspf"%>
<title>发布课程</title>
<style type="text/css">
	.txt01{font:Verdana, Geneva, sans-serif,宋体;padding:3px 2px 2px 2px; border-width:1px; border-color:#ddd;  color:#000;}
.datagrid-row {
  height: 45px;
}
.datagrid-header-row td
{
  background-color:#9aCCFF;
  height:32px;
}
</style>

</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <!-- Begin of toolbar -->
        <div id="publishclassinfo-toolbar">
            <div class="wu-toolbar-button">
              
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                <form action="maketeacourse" style="display: inline-block" method="post">
			     教师：<select id="teano" class="easyui-combobox" name="teano" style="width:90px;">   
    					<c:forEach items="${teas}" var="tea">
    						<option  value="${tea.teano }" >${tea.teaname}</option>
    					</c:forEach>   
    			   </select>&nbsp;&nbsp;&nbsp;&nbsp;
			     学年：<select id="couyear" class="easyui-combobox" name="couyear" style="width:90px;">   
    					<c:forEach items="${semester}" var="se">
    						<option  value="${se.value }" >${se.value }</option>
    					</c:forEach>     
    			   </select> &nbsp;&nbsp; &nbsp;  &nbsp;         
			   学期：<select id="semester" class="easyui-combobox" name="semester" style="width:90px;">   
    					<option value="1">1</option>   
   						<option value="2">2</option>     
    			  </select> &nbsp;&nbsp;  &nbsp; &nbsp;          
			    课程名：<select id="couseid" class="easyui-combobox" name="couseid" style="width:90px;">   
    					<c:forEach items="${course}" var="course">
    						<option  value="${course.id }" >${course.couname }</option>
    					</c:forEach>    
    			   </select> &nbsp;&nbsp;&nbsp;&nbsp;
			 上课地址:<input class="easyui-textbox" id="couaddress" name="couaddress" style="width:90px;" value="待定"/>&nbsp;&nbsp;&nbsp;&nbsp;
			  课程容量:<input class="easyui-textbox" id="counumber" name="counumber" style="width:90px;" value="100"/>  &nbsp;&nbsp; &nbsp;&nbsp;           
                    <input type="submit" class="easyui-linkbutton" value=" Submits " style="height:24px">&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="reset" class="easyui-linkbutton" value=" Reset " style="height:24px">
                </form>
            </div>

        </div>
            <!--修改课程信息-->
    <div id="w" class="easyui-window" title="课程信息设置" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 320px; height: 200px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true" id="did">
           <!-- <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                 <table cellpadding=3>
                    <tr>
                        <td>上课时间：</td>
                        <td>
                        	<select id="coutime" class="easyui-combobox" name="coutime" style="width:90px;">   
    							<option value="1">星期一</option>   
    							<option value="2">星期二</option>   
   								 <option value="3">星期三</option>   
    							<option value="4">星期四</option>   
    							<option value="5">星期五</option>
    							<option value="6">星期六</option>   
    							<option value="7">星期日</option>   
							</select> 
                        </td>
                    </tr>
                    <tr>
                        <td>课时：</td>
                        <td><input name="couhour" id="couhour" type="text" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>开始节数：</td>
                        <td><input name="coufhour" id="coufhour" type="text" class="txt01"/></td>
                    </tr> 
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div> -->
        </div>
    </div>
        <!-- End of toolbar -->
        <table id="publishclassinfo-datagrid" toolbar="#publishclassinfo-toolbar"></table>
    </div>
</div>
	<script type="text/javascript">
		
			/**
		     * Name 载入数据
		     */
		    $('#publishclassinfo-datagrid').datagrid({
		        url: 'publishcouselist',
		       // rownumbers: true,
		        singleSelect: true,
		        pageSize: 10,
		        pagination: true,
		       // queryParams: formChoiceJson(),
		        multiSort: true,
		        fitColumns: true,
		        fit: true,
		       // striped:true,
		        columns: [[
		            
		            {field: 'id', title: 'ID', width: 40, aligh:'center'},
		           	
		            {field: 'teaname', title: '教师姓名', width: 90, aligh:'center',editor:'text'},	
		            {field: 'couname', title: '课程名', width: 90, aligh:'center',editor:'text'},
		     
		            {field: 'couyear', title: '学年', width: 55, aligh:'center',editor:'text'},
		            {field: 'semester', title: '学期', width: 55, aligh:'center',editor:'text'},
		            {field: 'couaddress', title: '教室', width: 100, aligh:'center',editor:'text'},
		            {field: 'counumber', title: '课程容量', width:70, aligh:'center',editor:'text'},
		            {field: 'alcounumber', title: '已选人数', width:70, aligh:'center'},
		            {field: 'coutime', title: '是否编制课程信息', width:70, aligh:'center',
		            	  formatter : function(value,row,index){
		                     if(value==''){
		                     	return '否';
		                     }else {
		                    	 return '是';
		                    	 }                        
		                   } 
		            },
		            {field: 'operate', title: '操作', align:'center',width:$(this).width()*0.1,formatter:function(value, row, index){  
						var str = '<a href="#" id="opera1" name="opera1" class="easyui-linkbutton" onclick="makeTeaCourse()" ></a>';  
						return str;  
		            }
		            }
				]],
				onLoadSuccess:function(data){    
						//$("a[name='opera']").linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});   
						$("a[name='opera1']").linkbutton({text:'设置课程信息',plain:true,iconCls:'icon-ok'});   
						
				}
		    });
		   // $('#publishclassinfo-datagrid').datagrid('hideColumn', 'id');  
		  
		    //设置登录窗口
	        function openPwd() {
	            $('#w').window({
	                title: '设置课程信息',
	                width: 320,
	                modal: true,
	                shadow: true,
	                closed: true,
	                height: 200,
	                resizable:false
	            });
	        }
	        //关闭登录窗口
	        function closePwd() {
	            $('#w').window('close');
	        }
	        $(function() {

	            openPwd();
	            $('#did').html('');
	        }); 
	        function makeTeaCourse(){
	        	var rows = $('#publishclassinfo-datagrid').datagrid('getSelections');
	        	var id=rows[0].id;
	        	var teaname=rows[0].teaname;
	        	var couname=rows[0].couname;
	        	var couyear=rows[0].couyear;
	        	var semester=rows[0].semester;
	        	var couaddress=rows[0].couaddress;
	        	var counumber=rows[0].counumber;
	        	var alcounumber=rows[0].alcounumber;
	    		var coutime=rows[0].coutime;
	    		if(coutime!=''){
	    			$.messager.alert("提示","该条记录已分配课程信息","warning");
	    			return false;
	    		}
	        	$('#w').window('open');
	        	
	        	$('#did').html('<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">'+
	        	'<table cellpadding=3><tr><td>上课时间：</td><td><select id="coutime" class="easyui-combobox" name="coutime" style="width:90px;">'+
	        	'<option value="1">星期一</option><option value="2">星期二</option><option value="3">星期三</option><option value="4">星期四</option>'+
	        	'<option value="5">星期五</option><option value="6">星期六</option><option value="7">星期日</option></select> </td></tr>'+
	        	'<tr><td>课时：</td><td><input name="couhour" id="couhour" type="text" class="txt01" /></td></tr>'+
	        	'<tr><td>开始节数：</td><td><input name="coufhour"   id="coufhour" type="text" class="txt01"/></td></tr></table></div>'+
	        	'<div class="easyui-layout" region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">'+
	        	'<a id="btnEp"  style="text-decoration: none;" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" ><span style="font-size:13px;color:#678FC2;font-weight:bolder">确定</span></a>&nbsp;&nbsp;'+
	        	'<a id="btnCancel" style="text-decoration: none;" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)"><span style="font-size:12px;color:#678FC2;font-weight:bolder">取消</span></a></div>');
	        	
	        	
	        	
	        	
	        	$('#btnEp').click(function() {
	        		var couhour=$("#couhour").val();
		        	var coufhour=$("#coufhour").val();
		        	var coutime=$("#coutime option:selected").val();
	                //alert(coufhour+" "+couhour+" "+coutime);
	                if(couhour==''||couhour==null){
	                	$.messager.alert("警告",'上课的课时不能为空',"warning");
	                	return false;
	              
	                }else{
	                	var reg=/^[1-9]\d*$|^0$/;
	                	if(reg.test(couhour)==true){
	                		
		                	
	                	}else{
	                		$.messager.alert("警告",'请输入数字类型',"warning");
	                		$("#couhour").val("");
	                		$("#couhour").focus();
		                	return false;
	                	}
	                }
	                if(coufhour==''||coufhour==null){
	                	$.messager.alert("警告",'上课的开始节数不能为空',"warning");
	                	return false;
	                }else{
	                	var reg=/^[1-9]\d*$|^0$/;
	                	if(reg.test(coufhour)==true){
	                		
		                	
	                	}else{
	                		$.messager.alert("警告",'请输入数字类型',"warning");
	                		$("#coufhour").val("");
	                		$("#coufhour").focus();
		                	return false;
	                	}
	                }
	                data={
	                	"id":id,
	                	"teaname":teaname,
	                	"couname":couname,
	                	"couyear":couyear,
	                	"semester":semester,
	                	"couaddress":couaddress,
	                	"counumber":counumber,
	                	"alcounumber":alcounumber,
	                	"couhour":couhour,
	                	"coufhour":coufhour,
	                	"coutime":coutime
	                };
	                $.ajax({
	        			
	        			url:'makeclasstime',
	        			type:'post',
	        			dataType:"json",
	        			data:data,
	        			success:function(result){
	        				$.messager.alert("提示",result.mess,"info");
	        			}
	        		});
	            });
	        	$('#btnCancel').click(function(){closePwd();})
	        }
	        
	</script>
</body>
</html>