<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>部门编辑</title>
</head>
<body>
    <form id="form1" class="easyui-form"  action="${ctx }/web/title/save" method="post">
    <input type="hidden" name="id" value="${title.id}">
		<table class="datagrid-btable">
		   <tr>
		   <td>所属父栏目：</td>
		   <td><input name="parent.id" class="easyui-combotree" value="${pid }" data-options="url:'${ctx }/web/title/list',method:'get'" ${title.id=='1'?'disabled':'' }  style="width:200px"></td>
		   </tr>
		   <tr>
		   <td>栏目名称：</td>
		   <td> <input class="easyui-textbox" id="titlename" name="titlename" value="${title.titlename }" required style="width:100%;"></td>
		   </tr>
		   <tr>
		   <td>备注：</td>
		   <td> <input class="easyui-textbox" name="titlememo" value="${title.titlememo }" multiline="true" style="width:100%;height:100px"></td>
		   </tr>
		   <tr>
		   <td>排序：</td>
		   <td> <input name="sortid" value="${cmd=='add'?'0':title.sortid }" required class="easyui-numberbox" style="width:100px;" ></td>
		   </tr>
		</table>

	</form>
</body>
</html>