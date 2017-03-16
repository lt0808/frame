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
function btn_edit(v,row){
	return "<a href='javascript:void(0);' onclick='infodeal(\""+row.id+"\");' class='icon-edit btn_cell' title='编辑'></a>";
}
function infodeal(id){
	if(!id){
		id="";
	}
	dl.openDeal("${ctx }/web/info/infodeal?id="+id,{
		top:0,
		left:0,
		fit:true,
		saveSuccessMethod:function(){
			grid.reload("tb");
		}
	    });
}

</script>
</head>
<body>

<table id="tool" width="100%" > 
<tr>
<td style=" padding-top: 5px">
类别:<input name="typeid" class="easyui-combotree" data-options="url:'${ctx }/web/title/list',method:'get'">
发布日期:<input class="easyui-datebox" style="width:100px" name="sdate"> 至 <input class="easyui-datebox" style="width:100px" name="edate">
标题:<input class="easyui-textbox" name="title"  style="width:150px;">
 <a href="javascript:void(0)" onclick="grid.reload('tb')" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
</td>
<td style="white-space:nowrap;" align="right">
<a href="javascript:void(0);" onclick="infodeal()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增文章</a>
<a href="javascript:void(0);" onclick="delRows()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</td>
</tr>
</table>

<table id="tb" class="easyui-datagrid" data-options="
               url:'${ctx}/web/info/list',
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
            <th data-options="field:'title',sortable:true">文章标题</th>
            <th data-options="field:'topstaus',formatter:function(v){return v==1?'是':'否'}" align="center">是否置顶</th>
            <th data-options="field:'isshow',formatter:function(v){return (v=='y'?'是':'否');}" align="center">是否发布</th>
            <th field="typename">所属栏目</th>
            <th data-options="field:'recdate',formatter:lt.formatdate">发布时间</th>
            <th field="createUserName">发布人</th>
        </tr>
    </thead>
</table>


</body>
</html>