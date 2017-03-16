<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>岗位管理</title>
<tags:listpage url="${ctx }/web/exam" dealMethod="dealexampost" deleteMethod="deleteexampost"></tags:listpage>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:true" style="width:300px;" >
	        <table id="tb" class="easyui-datagrid" border="false"
	               data-options="
	                   singleSelect:true,
	                   fit:true,
	                   url: '${ctx}/web/exam/unitlist',
		               method: 'get',
		               rownumbers: true,
		               toolbar:'#tool',
		               onClickRow:onClickunit,
		               pagination:true,
		               pageSize:${pageSize}
		               ">
			    <thead>
			        <tr>
			            <th data-options="field:'unitname'">单位名称</th>
		                <th data-options="field:'unitcode'">单位代码</th>
			        </tr>
			    </thead>
			</table>
			<input id="unitid" name="unitid" type="hidden">
			<div id="tool" style="padding:5px" >
			   考试:<input name="examclassid" style="width:190px;"  class="easyui-combobox" data-options="
                            url:'${ctx }/web/exam/examclassselectlist',
                            valueField: 'id',
	                        textField: 'text'
                            ">
              <a href="javascript:void(0)" onclick="grid.reload('tb')" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</div>
        </div>
        <div data-options="region:'center'" border="false">

	        <table id="grid" class="easyui-datagrid" 
		            data-options="
		                  url:'${ctx }/web/exam/postlist',
		                  fit:true,
		                  rownumbers:true,
		                  toolbar:'#tool2',
		                  pagination:true,
		                  pageSize:${pageSize}
		                  ">
		        <thead>
		            <tr>
		                <th data-options="field:'ck',checkbox:true"></th>
		                <th data-options="field:'postname'">岗位名称</th>
		                <th data-options="field:'postcode'">岗位代码</th>
		                <th data-options="field:'isshow',formatter:function(v){return (v==1?'显示':'不显示')}">是否显示</th>
		                <th data-options="field:'postlevel'">岗位等级</th>
		                <th data-options="field:'personnum'">招聘人数</th>
		                <th data-options="field:'writename'">笔试类别</th>
		                <th data-options="field:'writecode'">笔试代码</th>
		                <th data-options="field:'unitname'">单位</th>
		                <th data-options="field:'classname'">考试</th>
		            </tr>
		        </thead>
		    </table>
	        <div id="tool2" align="right" style="padding:5px;">
	          <tags:importExcel formAction="${ctx }/web/exam/importExcel" tmpFileName="报考岗位模板.xls"/>&nbsp;
              <a href="javascript:void(0);" onclick="deal('')" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
              <a href="javascript:void(0);" onclick="delRows()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			</div>
        
        </div>
    </div>
<script type="text/javascript">

function onClickunit(index,row){
	$("#unitid").val(row.id);
	getPost(row.id);
}
function getPost(id){
	$('#grid').datagrid({
	    url:'${ctx }/web/exam/postlist?unitid='+id
	});
}
</script>
</body>
</html>