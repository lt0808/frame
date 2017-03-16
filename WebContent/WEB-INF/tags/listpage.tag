<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="gridName" type="java.lang.String"%>
<%@ attribute name="deleteMethod" type="java.lang.String"%>
<%@ attribute name="dealMethod" type="java.lang.String"%>


<script type="text/javascript">
var url_0="${url}/";
var deleteMethod="${deleteMethod==null||deleteMethod==''?'delete':deleteMethod}";
var dealMethod="${dealMethod==null||dealMethod==''?'deal':dealMethod}";
var gridName="${gridName==null||gridName==''?'grid':gridName}";

function delRows(){
	var rows = $('#'+gridName).datagrid('getChecked');
	var ids="";
	if(rows.length<1){
		msg.show("警告","请选择要删除的记录","red");
	}else{
	    $.messager.confirm('删除记录', '确定要删除吗？', function(r){
	        if (r){
				for(var i=0;i<rows.length;i++){
					var row=rows[i];
					if(ids==""){
						ids=row.id;
					}else{
						ids+=","+row.id;
					}
				}
				ajax2(url_0+deleteMethod +"?ids="+ids,function(){grid.reload(gridName);});
	        }
	    });
	}
}
function celledit(v,row){
	return "<a href='javascript:void(0);' onclick='deal(\""+row.id+"\");' class='icon-edit btn_cell' title='编辑'></a>";
}
function deal(id){
	openDeal(url_0+dealMethod+"?id="+id,gridName);
}
</script>