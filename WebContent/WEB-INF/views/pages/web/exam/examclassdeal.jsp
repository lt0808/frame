<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>编辑</title>
</head>
<body>
    <form id="form1" class="easyui-form"  action="${ctx }/web/exam/examclasssave" method="post">
    <input type="hidden" name="id" value="${ec.id}">
		<table class="datagrid-btable" cellpadding="8" cellspacing="0" width="100%">
		   <tr>
		   <td style="width:60px">考试名称：</td>
		   <td colspan="3"> <input class="easyui-textbox" id="examname" name="examname" value="${ec.examname }" required style="width:100%;" tipPosition="bottom"></td>
		   </tr>
		   <tr>
			   <td>是否缴费：</td>
			   <td>
			   <label><input type="radio" name="ispay" value="1" ${ec.id==null||ec.ispay==1?'checked':''}>是</label>
			   <label><input type="radio" name="ispay" value="0" ${ec.ispay==0?'checked':''}>否</label>
			   </td>
			   <td style="width:60px">考试费用：</td>
			   <td><input name="amount" class="easyui-numberbox" precision="2" style="width:100px;" value="${ec.amount }"> 元</td>
		   </tr>
		   <tr>
			   <td>考试状态：</td>
			   <td > 
			   	   <label><input type="radio" name="status" value="1" ${ec.id==null||ec.status==1?'checked':''}>启用</label>
				   <label><input type="radio" name="status" value="0" ${ec.status==0?'checked':''}>不启用</label>
				   &nbsp;&nbsp;
			   </td>
			   <td>&nbsp;</td>
			   <td>&nbsp;</td>
		   </tr>
		   <tr>
			   <td>报名功能：</td>
			   <td colspan="3"> 
			   	 <label><input type="radio" name="signstatus" value="1" ${ec.id==null||ec.signstatus==1?'checked':''}>开启首次报名</label>
			     <label><input type="radio" name="signstatus" value="2" ${ec.signstatus==2?'checked':''}>关闭首次报名</label>
			     <label><input type="radio" name="signstatus" value="0" ${ec.signstatus==0?'checked':''}>关闭</label>
			   </td>
		   </tr>
		   <tr>
			   <td>缴费功能：</td>
			   <td colspan="3">
			      <label><input type="radio" name="paystatus" value="1" ${ec.id==null||ec.paystatus==1?'checked':''}>开启</label>
			      <label><input type="radio" name="paystatus" value="0" ${ec.paystatus==0?'checked':''}>关闭</label>
			   </td>
		   </tr>
		   <tr>
			   <td>打印功能：</td>
			   <td colspan="3">
			      <label><input type="radio" name="printstatus" value="1" ${ec.id==null||ec.printstatus==1?'checked':''}>开启</label>
			      <label><input type="radio" name="printstatus" value="0" ${ec.printstatus==0?'checked':''}>关闭</label>
			   </td>
		   </tr>
		</table>

	</form>
</body>
</html>