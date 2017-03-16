<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>Insert title here</title>
</head>
<body>

   <div class="easyui-layout" data-options="fit:true" >  
       <div data-options="region:'west',split:true" style="width:300px;" >
        <table id="tb" class="easyui-treegrid" border="false"
		           data-options="
		               url: '${ctx}/sys/depart/list',
		               method: 'get',
		               fit:true,
		               idField: 'id',
		               treeField: 'departname',
		               onContextMenu: onContextMenu,
		               onDblClickRow:function(row){
		                    deal('edit');
		               },
		               onSelect:function(row){
		                    getUsers(row.id);
		               }
		           ">
		       <thead>
		           <tr>
		               <th data-options="field:'departname'" >部门名称</th>
		           </tr>
		       </thead>
		   </table>
       </div>
       <div data-options="region:'center'" border="false">  
		    <table id="userlist" class="easyui-datagrid" title="用户管理" 
		            data-options="singleSelect:true,fit:true,rownumbers:true,toolbar:'#ptool'">
		        <thead>
		            <tr>
		                <th data-options="field:'username'" width="60">姓名</th>
		                <th data-options="field:'loginname'" width="100">登录名</th>
		                <th data-options="field:'departname'" width="100">所属部门</th>
		                <th data-options="field:'tel'" width="120">电话</th>
		                <th data-options="field:'islogin'" width="70">能否登录</th>
		                <th data-options="field:'edit',formatter:useredit" width="25"></th>
		                <th data-options="field:'del',formatter:userdel" width="25"></th>
		            </tr>
		        </thead>
		    </table>
		    <div id="ptool" align="right">
		    <a href="javascript:void();" onclick="userdeal('add')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a>
		    </div>
		  

       </div>

   </div>
   <!-- 右键菜单 -->
    <div id="mm" class="easyui-menu" style="width:120px;">
        <div id="add" onclick="deal('add')" data-options="iconCls:'icon-add'">新增下级部门</div>
        <div id="add2" onclick="deal2('add')" data-options="iconCls:'icon-add'">新增同级部门</div>
        <div id="del" onclick="remove()" data-options="iconCls:'icon-remove'">删除部门</div>
        <div class="menu-sep"></div>
        <div id="edit" onclick="deal('edit')" data-options="iconCls:'icon-edit'">编辑部门</div>
        <div id="key" onclick="userdeal('add')" data-options="iconCls:'icon-man'">新增人员</div>
    </div>
   
<script type="text/javascript">

function onContextMenu(e,row){
    if (row){
        e.preventDefault();
        $(this).treegrid('select', row.id);

        $('#mm').menu('show',{
            left: e.pageX,
            top: e.pageY
        });  
        if(row.id=='1'){  
        	$('#mm').menu('disableItem', $('#del'));  
        	$('#mm').menu('disableItem', $('#key'));  
        	$('#mm').menu('disableItem', $('#add2'));  
        }else{
        	$('#mm').menu('enableItem', $('#del'));  
        	$('#mm').menu('enableItem', $('#key'));  
        	$('#mm').menu('enableItem', $('#add2'));  
        }
    }
}
function deal(cmd){
	var node = $('#tb').treegrid('getSelected');
	openDialog("${ctx}/sys/depart/deal?cmd="+cmd+"&id="+node.id,"tb");
//	dl.openDeal("${ctx}/sys/depart/deal?cmd="+cmd+"&id="+node.id,{
//		saveSuccessMethod:saveresult
//	});
}
function deal2(cmd){
	var node = $('#tb').treegrid('getSelected');
	openDialog("${ctx}/sys/depart/deal?cmd="+cmd+"&id="+node.parentid_,"tb");
//	dl.openDeal("${ctx}/sys/depart/deal?cmd="+cmd+"&id="+node.parentid_,{
//		saveSuccessMethod:saveresult
//	});
}
function saveresult(operator,message){
	if(operator=="success"){
		$('#tb').treegrid('reload');
	}
}
function remove(){
	var node = $('#tb').treegrid('getSelected');
    $.messager.confirm('删除部门', '确定要删除此部门吗？', function(r){
        if (r){
            ajax("${ctx}/sys/depart/delete?id="+node.id,null,deldepart);
        }
    });
}
function deldepart(){
	$('#tb').treegrid('reload');
}
function getUsers(id){
	$('#userlist').datagrid({
	    url:'${ctx }/sys/user/userlistByDepart?departid='+id
	});
}
function userdeal(cmd,id){
	var node = $('#tb').treegrid('getSelected');
	if(node){
		openDialog("${ctx }/sys/user/deal?did="+node.id+"&cmd="+cmd+"&id="+id,"userlist");
	}else{
		openDialog("${ctx }/sys/user/deal?cmd="+cmd+"&id="+id,"userlist");
	}
}

function useredit(value,row,index){
	var edit="<a href='javascript:void(0);' onclick='userdeal(\"edit\",\""+row.id+"\");' class='icon-edit btn_cell' title='编辑'></a>";
	return edit;
}
function userdel(value,row,index){
	var del="<a href='javascript:void(0);' onclick='deluser(\""+row.id+"\");' class='icon-remove btn_cell' title='删除'></a>";
	return del;
}
function deluser(id){
    $.messager.confirm('删除人员', '确定要删除此人员吗？', function(r){
        if (r){
            ajax("${ctx}/sys/user/delete?id="+id,null,delusersuccess);
        }
    });
}
function delusersuccess(){
	$("#userlist").datagrid("reload");
}
</script>
</body>
</html>