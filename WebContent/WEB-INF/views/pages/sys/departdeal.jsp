<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>部门编辑</title>
</head>
<body>
    <form id="form1" class="easyui-form"  action="${ctx }/sys/depart/save" method="post">
    <input type="hidden" name="id" value="${depart.id}">
		<table class="datagrid-btable">
		   <tr>
		   <td>所属父模块：</td>
		   <td><input name="parent.id" class="easyui-combotree" value="${pid }" data-options="url:'${ctx }/sys/depart/list',method:'get'" ${depart.id=='1'?'disabled':'' }  style="width:200px"></td>
		   </tr>
		   <tr>
		   <td>部门名称：</td>
		   <td> <input class="easyui-textbox" id="departname" name="departname" value="${depart.departname }" required style="width:100%;"></td>
		   </tr>
		   <tr>
		   <td>地址：</td>
		   <td> <input class="easyui-textbox" name="address" value="${depart.address }" style="width:100%;"></td>
		   </tr>
		   <tr>
		   <td>负责人：</td>
		   <td> <input class="easyui-textbox" name="master" value="${depart.master }" style="width:100px;"></td>
		   </tr>
		   <tr>
		   <td>联系电话：</td>
		   <td> <input class="easyui-textbox" name="phone" value="${depart.phone }" style="width:200px;"></td>
		   </tr>
		   <tr>
		   <td>传真：</td>
		   <td> <input class="easyui-textbox" name="fax" value="${depart.fax }" style="width:200px;"></td>
		   </tr>

		   <tr>
		   <td>排序：</td>
		   <td> <input name="sortid" value="${cmd=='add'?'0':depart.sortid }" required class="easyui-numberbox" style="width:100px;" ></td>
		   </tr>
		</table>

	</form>
</body>
</html>