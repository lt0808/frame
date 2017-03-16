<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>编辑</title>
</head>
<body>
    <form id="form1" class="easyui-form"  action="${ctx }/sys/role/save" method="post">
    <input type="hidden" name="id" value="${role.id}">
		<table class="datagrid-btable">
		   <tr>
		   <td>模块名称：</td>
		   <td> <input class="easyui-textbox" id="rolename" name="rolename" value="${role.rolename }" required style="width:100%;"></td>
		   </tr>
		</table>

	</form>
</body>
</html>