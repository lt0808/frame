<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>角色管理</title>
	<link rel="stylesheet" href="${ctxFront }/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctxFront }/ztree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctxFront }/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript">
	var setting = {
		async: {
			enable: true,
			url : getUrl
		},
		check: {
			enable: true,
			chkboxType: { "Y": "p", "N": "s" }
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onCheck: zTreeOnCheck
		}
	};
	function zTreeOnCheck(event, treeId, treeNode){
		var treeObj = $.fn.zTree.getZTreeObj("moduleTree");
		var nodes = treeObj.getCheckedNodes(true);
		var changeNodes="";
		var ptypes="";
		for(var i=0; i<nodes.length;i++){
			if(changeNodes==""){
				if(nodes[i].ptype=="moduleview")
				    changeNodes=nodes[i].id;
				else
					changeNodes=nodes[i].pId;
				ptypes=nodes[i].ptype;
			}else{
				if(nodes[i].ptype=="moduleview")
			        changeNodes=changeNodes+","+nodes[i].id;
				else
					changeNodes=changeNodes+","+nodes[i].pId;
			    ptypes=ptypes+","+nodes[i].ptype;
			}
		}
		ajax("${ctx}/sys/role/savepermiss","modules="+changeNodes+"&ptypes="+ptypes+"&roleid="+$("#roleid").val());
	}
	function getUrl(treeId, treeNode) {
		var param = "roleid="+$("#roleid").val();
		return "${ctx}/sys/role/moduletree?" + param;
	}
	$(document).ready(function(){
		$.fn.zTree.init($("#moduleTree"), setting);
	});
</script>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:true" style="width:300px;" >
	        <table id="grid" class="easyui-datagrid" border="false"
	               data-options="
	                   singleSelect:true,
	                   fit:true,
	                   url: '${ctx}/sys/role/list',
		               method: 'get',
		               rownumbers: true,
		               toolbar:'#tb',
		               onClickRow:onClickRole
		               ">
			    <thead>
			        <tr>
			            <th data-options="field:'rolename',width:'80%'">角色名称</th>
			            <th data-options="field:'edit',formatter:roleedit"></th>
		                <th data-options="field:'del',formatter:roledel"></th>
			        </tr>
			    </thead>
			</table>
			<div id="tb" align="right">
			   <a href="javascript:void();" onclick="deal('add')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a>
			</div>
        </div>
        <div data-options="region:'center'" border="false">
            <div class="easyui-layout" data-options="fit:true">
	        <div data-options="region:'west',split:true,title:'设置权限',collapsible:false" style="width:300px;" >
	        <input type="hidden" id="roleid">
			<ul id="moduleTree" class="ztree tree"></ul>
	        </div>
	        <div data-options="region:'center',title:'拥有此权限人员'" border="false">
	        
	        <table id="userlist" class="easyui-datagrid" 
		            data-options="singleSelect:true,fit:true,rownumbers:true">
		        <thead>
		            <tr>
		                <th data-options="field:'username'" width="60">姓名</th>
		                <th data-options="field:'loginname'" width="100">登录名</th>
		                <th data-options="field:'departname'" width="100">部门</th>
		            </tr>
		        </thead>
		    </table>
	        
	        
	        </div>
	        </div>
        
        </div>
    </div>
<script type="text/javascript">
function roleedit(value,row,index){
	var edit="<a href='javascript:void(0);' onclick='deal(\"edit\",\""+row.id+"\");' class='icon-edit btn_cell' title='编辑'></a>";
	return edit;
}
function roledel(value,row,index){
	var del="<a href='javascript:void(0);' onclick='delp(\""+row.id+"\");' class='icon-remove btn_cell' title='删除' ></a>";
	return del;
}
function delp(id){
    $.messager.confirm('删除角色', '确定要删除此角色吗？', function(r){
        if (r){
            ajax("${ctx}/sys/role/delrole?id="+id,null,saveresult);
        }
    });
}
function onClickRole(index,row){
	$("#roleid").val(row.id);
	$.fn.zTree.destroy("moduleTree");
	$.fn.zTree.init($("#moduleTree"), setting);
	getUsers(row.id);
}
function deal(cmd,id){
	//var node = $('#grid').datagrid('getSelected');
	dl.openDeal("${ctx}/sys/role/deal?cmd="+cmd+"&id="+id,{
		width:400,
		height:150,
		saveSuccessMethod:saveresult
	});
}
function saveresult(){
	$('#grid').datagrid('reload');
	$.fn.zTree.destroy("moduleTree");
	$('#userlist').datagrid('loadData', { total: 0, rows: [] });
}
function getUsers(id){
	$('#userlist').datagrid({
	    url:'${ctx }/sys/role/usertree?roleid='+id
	});
}
</script>
</body>
</html>