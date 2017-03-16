<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default" />
<title>编辑</title>
<script>
function dialogsubmit(status,report){
	$("#status").val(status);
	$("#isreport").val(report);
	$("form").form('submit', {
		success : function(result) {
			var win;
			lt.topWin().lt.message(result);
			
			var obj = eval('(' + result + ')')[0];

			try{
				if(obj.operator=="success"){
					window.parent.grid.reload("tb");
					window.parent.$("#dialog_check").dialog("close");
				}
			}catch(e){}
			
		},
		onLoadError: function(){
			lt.topWin().lt.message("提交错误，请重提交");
		}
	});
}
</script>
</head>
<body>
	<form id="form1" class="easyui-form " action="${ctx }/web/exam/checksave"
		method="post">
		<input type="hidden" name="id" value="${exam.id}">
		<input type="hidden" id="status" name="status" value="${exam.id}">
		<input type="hidden" id="isreport" name="isreport" value="${exam.id}">

		<div >
		<table  cellspacing="0" cellpadding="0" class="datagrid-body panel-body panel-body-noheader"
			border="0" width="100%">
				<tr>
					<td style="padding:5px; width:80px">报考单位:</td>
					<td style="padding:5px">
					${exam.unitname }
					</td>
					<td style="padding:5px;width:80px">报考岗位:</td>
					<td style="padding:5px">${exam.postname }</td>
					<td rowspan="8" style="padding:5px;width: 130px">
					</td>
				</tr>
				<tr>
					<td style="padding:5px;">姓名:</td>
					<td style="padding:5px">
					${exam.username }
					</td>
					<td style="padding:5px">身份证号:</td>
					<td style="padding:5px">
					${exam.sfz }
					</td>
					
				</tr>
				<tr>
				  <td style="padding:5px">性别:</td>
				  <td style="padding:5px">${exam.sex }</td>
				  <td style="padding:5px">民族</td>
				  <td style="padding:5px">${exam.minzu }</td>
		  </tr>
		  <tr>
				  <td style="padding:5px">出生年月:</td>
				  <td style="padding:5px">${exam.birthdate }</td>
				  <td style="padding:5px">政治面貌:</td>
				  <td style="padding:5px">${exam.zhengzhi }</td>
		  </tr>

		  <tr>
				  <td style="padding:5px">户口所在地:</td>
				  <td style="padding:5px">${exam.address }</td>
				  <td style="padding:5px">生源地:</td>
				  <td style="padding:5px">${exam.syd }</td>
		  </tr>
		  <tr>
				  <td style="padding:5px">毕业院校:</td>
				  <td style="padding:5px">${exam.school }</td>
				  <td style="padding:5px">专业 :</td>
				  <td style="padding:5px">${exam.zy }</td>

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
				  <td style="padding:5px">具备资格证书:</td>
				  <td style="padding:5px" colspan="4">${exam.zgzs }</td>
			
		  </tr>
		</table>
		
		<table  cellspacing="0" cellpadding="0" class="datagrid-body panel-body panel-body-noheader"
			border="0" width="100%">

		  <tr>
				  <td style="padding:5px">外语:</td>
				  <td style="padding:5px">${exam.wy }</td>
				  <td style="padding:5px">计算机:</td>
				  <td style="padding:5px">${exam.computer }</td>
		  </tr>
		  <tr>
				  <td style="padding:5px">固定电话:</td>
				  <td style="padding:5px">${exam.tel }</td>
				  <td style="padding:5px">手机:</td>
				  <td style="padding:5px">${exam.phone }</td>
		  </tr>
		  <tr>
				  <td style="padding:5px">通讯地址:</td>
				  <td style="padding:5px">${exam.address2}</td>
				  <td style="padding:5px">E-mail:</td>
				  <td style="padding:5px">${exam.email }</td>
		  </tr>
		  <tr>
				  <td style="padding:5px">现工作单位:</td>
				  <td style="padding:5px" colspan="3">${exam.dw}</td>
		  </tr>
	
		</table>
		<table  cellspacing="0" cellpadding="0" class="datagrid-body panel-body panel-body-noheader"
			border="0" width="100%">

		  <tr>
				  <td style="padding:5px;width:80px">学习简历:</td>
				  <td style="padding:5px">${exam.jiaoyubeijing }</td>
		  </tr>
		  <tr>
				  <td style="padding:5px">工作经历:</td>
				  <td style="padding:5px">${exam.gongzuojingli }</td>
		  </tr>
		  
		</table>
		<table  cellspacing="0" cellpadding="0" class="datagrid-body panel-body panel-body-noheader"
			border="0" width="100%">

		  <tr>
				  <td style="padding:5px;width:80px"><strong>未通过说明:</strong></td>
				  <td style="padding:5px">
				  <input name="remarks" value="${exam.remarks }" type="text" class="easyui-textbox" style="width:500px">
				  </td>
		  </tr>
		  
		</table>
        </div>
	</form>
</body>
</html>