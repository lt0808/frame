<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>模块编辑</title>
</head>
<body>
    <form id="form1" class="easyui-form"  action="${ctx }/sys/module/savepermiss" method="post">
    <input type="hidden" name="id" value="${modulepermissions.id}">
		<table class="datagrid-btable">
		   <tr>
		   <td>所属模块：</td>
		   <td><input name="module.id" class="easyui-combotree" value="${mid }" data-options="url:'${ctx }/sys/module/list',method:'get',readonly:true" style="width:200px"></td>
		   </tr>
		   <tr>
		   <td>权限描述：</td>
		   <td> <input class="easyui-textbox" name="permissionsdesc" value="${modulepermissions.permissionsdesc }" required style="width:170px;"> 例如:"编辑"</td>
		   </tr>
		   <tr>
		   <td>权限标识：</td>
		   <td> <input class="easyui-textbox" name="permissionskey" value="${modulepermissions.permissionskey }" required style="width:170px;"> 例如:"sys:module:edit"</td>
		   </tr>
		   <tr>
		</table>

	</form>
</body>
</html>