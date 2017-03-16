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
       
		   <table id="tb" class="easyui-treegrid" border="false"
		           data-options="
		               url: '${ctx}/sys/module/list',
		               method: 'get',
		               rownumbers: true,
		               fit:true,
		               idField: 'id',
		               treeField: 'modulename',
		               onContextMenu: onContextMenu,
		               onDblClickRow:function(row){
		                    deal('edit');
		               },
		               onSelect:function(row){
		                    getModulePermiss(row.id);
		               }
		           ">
		       <thead>
		           <tr>
		               <th data-options="field:'modulename'" >模块名称</th>
		               <th data-options="field:'urltarget'" >目标</th>
		               <th data-options="field:'urllink'" >模块地址</th>
		               <th data-options="field:'opentab',formatter:formatRefresh" >tab刷新</th>
		               <th data-options="field:'isvalid',formatter:isvalid" >是否有效</th>
		               <th data-options="field:'sortid'" >排序</th>
		              
		           </tr>
		       </thead>
		   </table>

       </div>
       <div data-options="region:'east',split:true" style="width:280px;" border="false">  
		    <table id="permisslist" class="easyui-datagrid" title="模块所具有的权限" 
		            data-options="singleSelect:true,fit:true,rownumbers:true,toolbar:'#ptool'">
		        <thead>
		            <tr>
		                <th data-options="field:'permissionsdesc'">权限描述</th>
		                <th data-options="field:'permissionskey'">权限标识</th>
		                <th data-options="field:'edit',formatter:permissedit"></th>
		                <th data-options="field:'del',formatter:permissdel"></th>
		            </tr>
		        </thead>
		    </table>
		    <div id="ptool" align="right">
		    <a href="javascript:void();" onclick="permissdeal('add')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a>
		    </div>
       </div>

   </div>
   <!-- 右键菜单 -->
    <div id="mm" class="easyui-menu" style="width:120px;">
        <div id="add" onclick="deal('add')" data-options="iconCls:'icon-add'">新增下级模块</div>
        <div id="add2" onclick="deal2('add')" data-options="iconCls:'icon-add'">新增同级模块</div>
        <div id="del" onclick="remove()" data-options="iconCls:'icon-remove'">删除模块</div>
        <div class="menu-sep"></div>
        <div id="edit" onclick="deal('edit')" data-options="iconCls:'icon-edit'">编辑模块</div>
        <div id="key" onclick="permissdeal('add')">新增权限</div>
    </div>
    <!-- 弹出dialog模块编辑 -->
    <div id="dlg" class="easyui-dialog" title="模块编辑" style="top:15px;width:500px;height:400px;padding:0px;overflow: hidden;"
            data-options="
                iconCls: 'icon-save',
                buttons: '#dlg-buttons',
                maximizable:true,
                modal:true,
                closed:true
            ">
        	<iframe id="dealframe" style="height: 100%; width:100%; " frameborder="0"
			></iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="javascript:save()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
    </div>
    <!-- 弹出dialog权限编辑 -->
    <div id="dlg2" class="easyui-dialog" title="权限编辑" style="top:15px;width:450px;height:210px;padding:0px;overflow: hidden;"
            data-options="
                iconCls: 'icon-save',
                buttons: '#dlg2-buttons',
                maximizable:true,
                modal:true,
                closed:true
            ">
        	<iframe id="permissframe" style="height: 100%; width:100%; " frameborder="0"
			></iframe>
    </div>
    <div id="dlg2-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="javascript:savepermiss()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg2').dialog('close')">关闭</a>
    </div>
   
<script type="text/javascript">
function formatRefresh(v){
	if(v=="refresh"){
		return "刷新";
	}else{
		return "不刷新";
	}
}
function isvalid(v){
	if(v==1){
		return "有效";
	}else{
		return "无效";
	}
}
function onContextMenu(e,row){
    if (row){
        e.preventDefault();
        $(this).treegrid('select', row.id);

        $('#mm').menu('show',{
            left: e.pageX,
            top: e.pageY
        });  
        if(row.id=='1'){
        	$('#mm').menu('disableItem', $('#edit'));  
        	$('#mm').menu('disableItem', $('#del'));  
        	$('#mm').menu('disableItem', $('#key'));  
        	$('#mm').menu('disableItem', $('#add2'));  
        }else{
        	$('#mm').menu('enableItem', $('#edit'));  
        	$('#mm').menu('enableItem', $('#del'));  
        	$('#mm').menu('enableItem', $('#key'));  
        	$('#mm').menu('enableItem', $('#add2'));  
        }
    }
}

function deal(cmd){
	var node = $('#tb').treegrid('getSelected');
	$('#dlg').dialog('open');
	$('#dealframe').attr("src","${ctx }/sys/module/deal?id="+node.id+"&cmd="+cmd);
}
function deal2(cmd){
	var node = $('#tb').treegrid('getSelected');
	$('#dlg').dialog('open');
	$('#dealframe').attr("src","${ctx }/sys/module/deal?id="+node.parentid_+"&cmd="+cmd);
}
function remove(){
	var node = $('#tb').treegrid('getSelected');
    $.messager.confirm('删除模块', '确定要删除此模块吗？', function(r){
        if (r){
            ajax("${ctx}/sys/module/delete?id="+node.id,null,delmodule);
        }
    });
}
function delmodule(){
	$('#tb').treegrid('reload');
	$("#permisslist").datagrid("reload");
}
function save(){
	$('#dlg').find("iframe")[0].contentWindow.esayFormSubmit("window.parent.saveresult");
}
function saveresult(operator,message){
	if(operator=="success"){
		$('#dlg').dialog('close');
		$('#tb').treegrid('reload');
	}
}
function permissdeal(cmd,id){
	var node = $('#tb').treegrid('getSelected');
	if(node){
	$('#dlg2').dialog('open');
	$('#permissframe').attr("src","${ctx }/sys/module/permissdeal?mid="+node.id+"&cmd="+cmd+"&id="+id);
	}else{
		lt.alert("请先选择模块后增加");
	}
}
function getModulePermiss(id){
	$('#permisslist').datagrid({
	    url:'${ctx }/sys/module/permisslist?mid='+id
	});
}
function savepermiss(){
	$('#dlg2').find("iframe")[0].contentWindow.esayFormSubmit("window.parent.savepermissresult");
}
function savepermissresult(operator,message){
	if(operator=="success"){
		$('#dlg2').dialog('close');
		$("#permisslist").datagrid("reload");
	}
}
function permissedit(value,row,index){
	var edit="<a href='javascript:void(0);' onclick='permissdeal(\"edit\",\""+row.id+"\");' class='icon-edit btn_cell' title='编辑'></a>";
	return edit;
}
function permissdel(value,row,index){
	var del="<a href='javascript:void(0);' onclick='delp(\""+row.id+"\");' class='icon-remove btn_cell' title='删除'></a>";
	return del;
}
function delp(id){
    $.messager.confirm('删除权限', '确定要删除此权限吗？', function(r){
        if (r){
            ajax("${ctx}/sys/module/delpermiss?id="+id,null,delpermisssuccess);
        }
    });
}
function delpermisssuccess(){
	$("#permisslist").datagrid("reload");
}
</script>
</body>
</html>