<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>Insert title here</title>
<script type="text/javascript">
function delRows(){
	var rows = $('#tb').datagrid('getChecked');
	var ids="";
	if(rows.length<1){
		msg.show("警告","请选择要删除的记录","red");
	}else{
	    $.messager.confirm('删除记录', '确定要删除吗？', function(r){
	        if (r){
				for(var i=0;i<rows.length;i++){
					var row=rows[i];
					if(ids==""){
						ids=row.id;
					}else{
						ids+=","+row.id;
					}
				}
				ajax2("${ctx}/web/info/delete?ids="+ids,function(){grid.reload("tb");});
	        }
	    });
	}
}
function infodeal(v,row){
    return '<a href="javascript:void(0);" onclick="deal(\''+row.id+'\',\''+row.status+'\')">审核</a>';
	
}
function deal(id,status){
	if(status=="agree"||status=="no"||status=="disagree"){
		$("#btn_check_1").attr("style","display:none");
		$("#btn_check_2").attr("style","display:none");
		$("#btn_check_3").attr("style","display:none");
	}else{
		$("#btn_check_1").attr("style","display:");
		$("#btn_check_2").attr("style","display:");
		$("#btn_check_3").attr("style","display:");
	}
	$('#dialog_check').dialog('open');
	$('#dialogFrame_check').attr("src","${ctx }/web/exam/checkdeal?id="+id);
}
function getunit(rec){
    var url = '${ctx}/web/exam/examunitselectlist?examclassid='+rec.id;
    $('#examunitid').combobox('clear');
    $('#examunitid').combobox('reload', url);

}
function restatus(v){
	if(v=="no"){
		return "<font color=red>未提交</font>";
	}else if(v=="agree"){
		return "<font color=green>已通过</font>";
	}else if(v=="disagree"){
		return "<font color=red>未通过</font>";
	}else if(v=="yes"){
		return "<font color=red>未审批</font>";
	}else{
		return "";
	}
}
function check(status,report){
	$('#dialog_check').find("iframe")[0].contentWindow.dialogsubmit(status,report);
}
</script>
</head>
<body>

<table id="tool" width="100%" > 
<tr>
<td style=" padding-top: 5px">
考试:<input required value="${ep.examclass.id }" id="examclassid" tip class="easyui-combobox" name="examclassid" style="width:250px;" tipPosition="bottom" data-options="
	                     url:'${ctx }/web/exam/examclassselectlist',
	                     valueField: 'id',
	                     textField: 'text'
	 ">
姓名:<input class="easyui-textbox" name="username"  style="width:60px;">
身份证:<input class="easyui-textbox" name="sfz"  style="width:140px;">

状态:<select class="easyui-combobox" panelHeight="110px" name="status">
      <option value="">请选择</option>
      <option value="agree">已通过</option>
      <option value="disagree">未通过</option>
      <option value="no">未提交</option>
      <option value="yes">未审核</option>
    </select>
<a href="javascript:void(0)" onclick="grid.reload('tb')" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
</td>
</tr>
</table>

<table id="tb" class="easyui-datagrid" data-options="
               url:'${ctx}/web/exam/checklist',
               fit:true,
               border:false,
               rownumbers:true,
               pagination:true,
               toolbar:'#tool',
               checkOnSelect:false,
               pageSize:${pageSize}
               " >
    <thead data-options="frozen:true">
        <tr>
            <th data-options="field:'sh',formatter:infodeal" align="center" ></th>
            <th data-options="field:'username'" align="center" >姓名</th>
            <th data-options="field:'sfz'" align="center">身份证</th>
        </tr>
    </thead>
    <thead>
        <tr>

            <th data-options="field:'unitname'" align="center">报考单位</th>
            <th data-options="field:'postname'" align="center">报考岗位</th>
            <th data-options="field:'status',formatter:restatus">审核状态</th>
            <th field="shr" align="center">审核人</th>
            <th data-options="field:'createDate',formatter:lt.formatdatetime" align="center">报考时间</th>
            <th data-options="field:'isjf',formatter:function(v){return lt.yn(v,'已缴费','未缴费');}" align="center">缴费状态</th>
            <th data-options="field:'examname'" align="center">考试</th>


        </tr>
    </thead>
</table>
		<!-- 弹出dialog模块编辑 -->
	    <div id="dialog_check" class="easyui-dialog" title="审核" style="top:15px;width:700px;height:510px;padding:0px;overflow: hidden;"
	            data-options="
	                iconCls: 'icon-save',
	                buttons: '#dlg-buttons_check',
	                maximizable:true,
	                modal:true,
	                closed:true
	            ">
	        	<iframe id="dialogFrame_check" style="height: 100%; width:100%; " frameborder="0"
				></iframe>
	    </div>
	    <div id="dlg-buttons_check">
	        <a id="btn_check_1" onclick="check('agree','')" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">审核通过</a>
	        <a id="btn_check_2" onclick="check('disagree','yes')" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'">审核不通过(允许重报)</a>
	        <a id="btn_check_3" onclick="check('disagree','no')" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'">审核不通过(不允许重报)</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialog_check').dialog('close')">关闭</a>
	    </div>

</body>
</html>