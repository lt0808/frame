<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>模块编辑</title>
</head>
<body>
    <form id="form1" class="easyui-form"  action="${ctx }/sys/module/save" method="post">
    <input type="hidden" name="id" value="${module.id}">
		<table class="datagrid-btable">
		   <tr>
		   <td>所属父模块：</td>
		   <td><input name="parent.id" class="easyui-combotree" value="${pid }" data-options="url:'${ctx }/sys/module/list',method:'get'" style="width:200px"></td>
		   </tr>
		   <tr>
		   <td>模块名称：</td>
		   <td> <input class="easyui-textbox" id="modulename" name="modulename" value="${module.modulename }" required style="width:100%;"></td>
		   </tr>
		   <tr>
		   <td>连接地址：</td>
		   <td> <input class="easyui-textbox" name="urllink" value="${module.urllink }" style="width:100%;"></td>
		   </tr>
		   <tr>
		   <td>连接目标：</td>
		   <td> <input class="easyui-textbox" name="urltarget" value="${cmd=='add'?'main':module.urltarget }" style="width:100px;"></td>
		   </tr>
		   <tr>
		   <td>tab刷新：</td>
		   <td>
		        <select class="easyui-combobox" name="opentab" panelHeight="50px" style="width:100px;">
	                <option value="noRefresh" ${module.opentab=='noRefresh'?'selected':'' }>不刷新</option>
	                <option value="refresh" ${module.opentab=='refresh'?'selected':'' }>刷新</option>
	            </select>
		   </td>
		   </tr>
		   <tr>
		   <td>是否有效：</td>
		   <td>
		        <select class="easyui-combobox" name="isvalid" panelHeight="50px" style="width:100px;">
	                <option value="1" ${module.isvalid==1?'selected':'' }>有效</option>
	                <option value="0" ${module.isvalid==0?'selected':'' }>无效</option>
	            </select>
		   </td>
		   </tr>
		   <tr>
		   <td>排序：</td>
		   <td> <input name="sortid" value="${cmd=='add'?'0':module.sortid }" required class="easyui-numberbox" style="width:100px;" ></td>
		   </tr>
		</table>

	</form>
</body>
</html>