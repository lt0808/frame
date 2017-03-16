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
       <div data-options="region:'center'" >  
       <shiro:hasPermission name="index">
       </shiro:hasPermission>
		   <table id="tb" class="easyui-treegrid" border="false"
		           data-options="
		               url: '${ctx}/web/title/list',
		               method: 'get',
		               rownumbers: true,
		               fit:true,
		               idField: 'id',
		               treeField: 'titlename',
		               onContextMenu: onContextMenu,
		               onDblClickRow:function(row){
		                    deal('edit');
		               }
		           ">
		       <thead>
		           <tr>
		               <th data-options="field:'titlename'" >栏目名称</th>
		               <th data-options="field:'sortid'" >排序</th>
		               <th data-options="field:'id'" >栏目ID标识</th>
		           </tr>
		       </thead>
		   </table>

       </div>

   </div>
   <!-- 右键菜单 -->
    <div id="mm" class="easyui-menu" style="width:120px;">
        <div id="add" onclick="deal('add')" data-options="iconCls:'icon-add'">新增下级栏目</div>
        <div id="add2" onclick="deal2('add')" data-options="iconCls:'icon-add'">新增同级栏目</div>
        <div id="del" onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div id="edit" onclick="deal('edit')" data-options="iconCls:'icon-edit'">编辑</div>
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
        	$('#mm').menu('disableItem', $('#add2'));  
        	$('#mm').menu('disableItem', $('#edit'));  
        }else{
        	$('#mm').menu('enableItem', $('#del'));    
        	$('#mm').menu('enableItem', $('#add2'));  
        	$('#mm').menu('enableItem', $('#edit'));  
        }
    }
}
function deal(cmd){
	var node = $('#tb').treegrid('getSelected');
	openDialog("${ctx}/web/title/deal?cmd="+cmd+"&id="+node.id,"tb");
//	dl.openDeal("${ctx}/sys/depart/deal?cmd="+cmd+"&id="+node.id,{
//		saveSuccessMethod:saveresult
//	});
}
function deal2(cmd){
	var node = $('#tb').treegrid('getSelected');
	openDialog("${ctx}/web/title/deal?cmd="+cmd+"&id="+node.parentid_,"tb");
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
    $.messager.confirm('删除', '确定要删除此栏目吗？', function(r){
        if (r){
            ajax("${ctx}/web/title/delete?id="+node.id,null,deldepart);
        }
    });
}
function deldepart(){
	$('#tb').treegrid('reload');
}
</script>
</body>
</html>