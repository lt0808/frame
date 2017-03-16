<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>编辑</title>

</head>
<body>
    <form id="form1" class="easyui-form"  action="${ctx }/web/exam/examunitsave" method="post">
    <input type="hidden" name="id" value="${eu.id}">
		<table class="datagrid-btable" cellpadding="5" cellspacing="0" width="100%">
		   <tr>
			   <td style="width:90px">所属考试：</td>
			   <td>
			    <input value="${eu.examclass.id }" id="examclass.id" class="easyui-combobox" name="examclass.id" style="width:100%;" data-options="
	                     url:'${ctx }/web/exam/examclassselectlist',
	                     valueField: 'id',
	                     textField: 'text'
	                    ">
			   </td>
		   </tr>
		   <tr>
			   <td>单位名称：</td>
			   <td>
			    <input class="easyui-textbox" id="unitname" name="unitname" value="${eu.unitname }" required style="width:300px;" >
			   </td>
		   </tr>
		   <tr>
			   <td>单位代码：</td>
			   <td>
			    <input class="easyui-textbox" id="unitcode" name="unitcode" value="${eu.unitcode }" style="width:100px;" >
			   </td>
		   </tr>
		   <tr>
			   <td>显示状态：</td>
			   <td>
			   <label><input type="radio" name="isshow" value="1" ${eu.id==null||eu.isshow==1?'checked':''}>是</label>
			   <label><input type="radio" name="isshow" value="0" ${eu.isshow==0?'checked':''}>否</label>
			   </td>
		   </tr>
		   <tr>
			   <td>绑定审核账户：</td>
			   <td>
			   <input name="users" value="${users }" class="easyui-tagbox" style="width:300px" data-options="
		                url: '${ctx }/sys/user/allUserList',
		                method: 'get',
		                valueField: 'id',
		                textField: 'username',
		                limitToList: true,
		                hasDownArrow: true,
		                panelHeight:170,
		                prompt: '请选择'
                ">
			   </td>
		   </tr>
		   
		</table>

	</form>
</body>
</html>