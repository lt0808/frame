<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>模块编辑</title>

</head>
<body>
    <form id="form1" class="easyui-form" action="${ctx }/sys/user/save" method="post">
    
    <input type="hidden" name="id" value="${user.id}">
		<table class="datagrid-btable">
		   <tr>
		   <td>所属部门：</td>
		   <td><input name="depart.id" class="easyui-combotree" value="${departid}" data-options="url:'${ctx }/sys/depart/list',method:'get'" style="width:200px" required></td>
		   </tr>
		   <tr>
		   <tr>
		   <td>姓名：</td>
		   <td> <input class="easyui-textbox" id="username" name="username" value="${user.username }" required style="width:150px;"></td>
		   </tr>
		   <tr>
		   <td>登录名：</td>
		   <td> <input class="easyui-textbox" name="loginname" value="${user.loginname }" required style="width:150px;"></td>
		   </tr>
		   <tr>
		   <td>密码：</td>
		   <td> <input class="easyui-passwordbox" id="pass" name="pass" iconWidth="28" style="width:150px;"> ${cmd=='add'?'空为默认密码：123':'空为密码不变' }</td>
		   </tr>
		   <tr>
		   <td>验证密码：</td>
		   <td> <input class="easyui-passwordbox" name="password2" iconWidth="28" validType="confirmPass['#pass']" style="width:150px;">
		   </td>
		   </tr>
		   <tr>
		   <td>联系方式：</td>
		   <td> <input class="easyui-textbox" name="tel" value="${user.tel }" style="width:100%;"></td>
		   </tr>
		   <tr>
		   <td>能否登录：</td>
		   <td>
		        <select class="easyui-combobox" name="canlogin" panelHeight="50px" style="width:60px;">
	                <option value="1" ${depart.canlogin==1?'selected':'' }>是</option>
	                <option value="0" ${depart.canlogin==0?'selected':'' }>否</option>
	            </select>
		   </td>
		   </tr>
		   <tr>
		   <td>排序：</td>
		   <td> <input name="sortid" value="${cmd=='add'?'0':user.sortid }" required class="easyui-numberbox" style="width:100px;" ></td>
		   </tr>
		   
		   <tr>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   </tr>
		   <tr>
		   <td>角色：</td>
		   <td><input name="roleids" value="${roles }" class="easyui-combotree"  data-options="url:'${ctx }/sys/role/combotree',method:'get'" multiple="true" multiline="true"  style="width:100%"></td>
		   </tr>
		   
		</table>
	
		

	</form>
</body>
</html>