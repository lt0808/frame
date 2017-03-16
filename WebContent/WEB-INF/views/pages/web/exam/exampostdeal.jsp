<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>编辑</title>
</head>
<body>
    <form id="form1" class="easyui-form"  action="${ctx }/web/exam/exampostsave" method="post">
    <input type="hidden" name="id" value="${ep.id}">
		<table class="datagrid-btable" cellpadding="5" cellspacing="0" width="100%">
		   <tr>
			   <td style="width:60px">所属考试：</td>
			   <td>
			    <input required value="${ep.examclass.id }" id="examclass.id" tip class="easyui-combobox" name="examclass.id" style="width:420px;" tipPosition="bottom" data-options="
	                     url:'${ctx }/web/exam/examclassselectlist',
	                     valueField: 'id',
	                     textField: 'text',
	                     validType:'validEmpty',
	                     onSelect: getunit
	                    ">
			   </td>
		   </tr>
		   <tr>
			   <td style="width:60px">所属单位：</td>
			   <td>
			    <input required tipPosition="bottom" value="${ep.examunit.id }" id="examunitid" class="easyui-combobox" name="examunit.id" style="width:420px;" data-options="
	                     valueField: 'id',
	                     textField: 'text',
	                     validType:'validEmpty'
	                    ">
			   </td>
		   </tr>
		   <tr>
			   <td>岗位名称：</td>
			   <td>
			    <input class="easyui-textbox" id="postname" name="postname" value="${ep.postname }" required style="width:300px;" >
			   </td>
		   </tr>
		   <tr>
			   <td>岗位代码：</td>
			   <td>
			    <input class="easyui-textbox" id="postcode" name="postcode" value="${ep.postcode }" style="width:100px;" >
			   </td>
		   </tr>
		   <tr>
			   <td>显示状态：</td>
			   <td>
			   <label><input type="radio" name="isshow" value="1" ${ep.id==null||ep.isshow==1?'checked':''}>是</label>
			   <label><input type="radio" name="isshow" value="0" ${ep.isshow==0?'checked':''}>否</label>
			   </td>
		   </tr>
		   <tr>
			   <td>岗位等级：</td>
			   <td>
			    <input class="easyui-textbox" id="postlevel" name="postlevel" value="${ep.postlevel }" style="width:100px;" >
			   </td>
		   </tr>
		   <tr>
			   <td>招聘人数：</td>
			   <td>
			    <input class="easyui-numberbox" id="personnum" name="personnum" value="${ep.personnum }" style="width:100px;" >
			   </td>
		   </tr>
		   <tr>
			   <td>笔试类别：</td>
			   <td>
			    <input class="easyui-textbox" id="writename" name="writename" value="${ep.writename }" style="width:100px;" >
			   </td>
		   </tr>
		   <tr>
			   <td>笔试代码：</td>
			   <td>
			    <input class="easyui-textbox" id="writecode" name="writecode" value="${ep.writecode }" style="width:100px;" >
			   </td>
		   </tr>
		</table>

	</form>
	<script type="text/javascript">
	
	function getunit(rec){
        var url = '${ctx}/web/exam/examunitselectlist?examclassid='+rec.id;
        $('#examunitid').combobox('clear');
        $('#examunitid').combobox('reload', url);

	}
	
	$.extend($.fn.validatebox.defaults.rules, {
        validEmpty: {
           validator: function(value, param) {
               if(value=='--请选择--'){
            	   return false;
               }else{
            	   return true;
               }
           },
           message: "该输入项为必填项"
       }
    });
	</script>
</body>
</html>