<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default" />
<title>编辑</title>
</head>
<body>
	<form id="form1" class="easyui-form " action="${ctx }/web/exam/examsave"
		method="post">
		<input type="hidden" name="id" value="${exam.id}">

		<div class="datagrid-body panel-body panel-body-noheader">
		
		<table  cellspacing="0" cellpadding="0"
			border="0" width="100%">
				<tr>
					<td style="padding:5px;">姓名:</td>
					<td style="padding:5px">
					<input type="text" name="username" value="${exam.username }" class="easyui-textbox" style="width:100px" required>
					</td>
					<td style="padding:5px">身份证号:</td>
					<td style="padding:5px"><input name="sfz" value="${exam.sfz }" required type="text" class="easyui-textbox" style="width:140px"></td>
					<td rowspan="7" style="padding:5px;width: 130px">
					</td>
				</tr>
				<tr>
				  <td style="padding:5px">性别:</td>
				  <td style="padding:5px">${exam.sex }</td>
				  <td style="padding:5px">民族</td>
				  <td style="padding:5px">${exam.minzu }</td>
		  </tr>
				<tr>
				  <td style="padding:5px">生源地:</td>
				  <td style="padding:5px">${exam.syd }</td>
				  <td style="padding:5px">手机:</td>
				  <td style="padding:5px">${exam.phone }</td>
		  </tr>
				<tr>
				  <td style="padding:5px">电话:</td>
				  <td style="padding:5px">${exam.tel }</td>
				  <td style="padding:5px">资格证书:</td>
				  <td style="padding:5px">${exam.zgzs }</td>
		  </tr>
				<tr>
				  <td style="padding:5px">学历:</td>
				  <td style="padding:5px">${exam.xl }</td>
				  <td style="padding:5px">学位:</td>
				  <td style="padding:5px">${exam.xw }</td>
		  </tr>
				<tr>
				  <td style="padding:5px">毕业时间:</td>
				  <td style="padding:5px">${exam.graddate }</td>
				  <td style="padding:5px">参加工作时间:</td>
				  <td style="padding:5px">${exam.jobdate }</td>
		  </tr>
				<tr>
				  <td style="padding:5px">报考单位:</td>
				  <td style="padding:5px">${exam.unitname }</td>
				  <td style="padding:5px">报考岗位:</td>
				  <td style="padding:5px">${exam.postname }</td>
		  </tr>
		  <tr>
				  <td style="padding:5px">准考证号:</td>
				  <td style="padding:5px"><input name="zkzh" value="${exam.zkzh }" type="text" class="easyui-textbox" style="width:120px"></td>
				  <td style="padding:5px">考场:</td>
				  <td colspan="2" style="padding:5px"><input name="kc" value="${exam.kc }" type="text" class="easyui-textbox" style="width:120px"></td>
		  </tr>
		  <tr>
				  <td style="padding:5px">座号:</td>
				  <td style="padding:5px"><input name="zh" value="${exam.zh }" type="text" class="easyui-textbox" style="width:120px"></td>
				  <td style="padding:5px">考试时间:</td>
				  <td colspan="2" style="padding:5px"><input name="examdate" value="${exam.examdate }" type="text" class="easyui-textbox" style="width:200px"></td>
		  </tr>
		  <tr>
				  <td style="padding:5px">考试地点:</td>
				  <td colspan="4" style="padding:5px"><input name="examaddress" value="${exam.examaddress }" type="text" class="easyui-textbox" style="width:480px"></td>
		  </tr>
		</table>
        </div>
	</form>
</body>
</html>