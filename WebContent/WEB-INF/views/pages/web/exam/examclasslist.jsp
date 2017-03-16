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
				ajax2("${ctx}/web/exam/deleteclass?ids="+ids,function(){grid.reload("tb");});
	        }
	    });
	}
}
function btn_edit(v,row){
	return "<a href='javascript:void(0);' onclick='infodeal(\""+row.id+"\");' class='icon-edit btn_cell' title='编辑'></a>";
}
function infodeal(id){
	if(!id){
		id="";
	}
	openDialog("${ctx }/web/exam/examclassdeal?id="+id,"tb");
}
</script>
</head>
<body>
<table id="tool" width="100%" > 
<tr>
<td style="white-space:nowrap;" align="right">
<a href="javascript:void(0);" onclick="infodeal()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
<a href="javascript:void(0);" onclick="delRows()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</td>
</tr>
</table>

<table id="tb" class="easyui-datagrid" data-options="
               url:'${ctx}/web/exam/examclasslist',
               fit:true,
               border:false,
               rownumbers:true,
               pagination:true,
               toolbar:'#tool',
               checkOnSelect:false,
               pageSize:${pageSize}
               " >
    <thead>
        <tr>
            <th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'edit',formatter:btn_edit"></th>
            <th data-options="field:'examname',sortable:true">考试名称</th>
            <th data-options="field:'status',formatter:function(v){return v==1?'启用':'不启用'}" align="center">考试状态</th>
            <th data-options="field:'ispay',formatter:function(v){return v==1?'是':'否'}" align="center">是否缴费</th>
            <th data-options="field:'amount'" align="center">费用</th>
            <th data-options="field:'paystatus',formatter:function(v){return (v==1?'开启':'关闭');}" align="center">缴费功能</th>
            <th data-options="field:'signstatus',formatter:function(v){return (v==0?'关闭':(v==1?'首次报名开启':'首次报名关闭'));}" align="center">报名功能</th>
            <th data-options="field:'printstatus',formatter:function(v){return (v==1?'开启':'关闭');}" align="center">打印功能</th>
        </tr>
    </thead>
</table>


</body>
</html>