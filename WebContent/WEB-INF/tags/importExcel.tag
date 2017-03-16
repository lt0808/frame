<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctxFront" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getAdminPath()}"/>
<c:set var="uuid" value="<%=java.util.UUID.randomUUID().toString().replace('-', '_').toString() %>" />

<%@ attribute name="btnName" type="java.lang.String"%>
<%@ attribute name="formAction" type="java.lang.String"%>
<%@ attribute name="refreshGridName" type="java.lang.String"%>
<%@ attribute name="tmpFileName" type="java.lang.String"%>

<a href="javascript:void(0)" onclick="openDialog_excel_${uuid}()" class="easyui-linkbutton" iconCls="icon-excel">${btnName==null||btnName==''?'从Excel导入':btnName }</a>

<!-- 弹出excel dialog模块编辑 -->
   <div id="dialog_excel_${uuid }" class="easyui-dialog" title="Excel操作" style="top:15px;width:550px;height:160px;padding:0px;overflow: hidden;"
           data-options="
               iconCls: 'icon-excel',
               buttons: '#dlg-buttons_excel_${uuid }',
               maximizable:true,
               modal:true,
               resizable:false,
               maximizable:false,
               closed:true
           ">
           <iframe  style="height: 100%; width:100%; " frameborder="0" src="${ctx }/importExcel?formAction=${formAction}&tmpFileName=${tmpFileName}">
           </iframe>
           
   </div>
   <div id="dlg-buttons_excel_${uuid }">
	    <a id="dlg-buttons_save_excel_${uuid }" onclick="uploadexcel_${uuid}()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-excel-import'">导入</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialog_excel_${uuid }').dialog('close')">关闭</a>
   </div>
<script type="text/javascript">
	function openDialog_excel_${uuid}(){
		$("#dialog_excel_${uuid}").dialog("open");
	}
	function uploadexcel_${uuid}(){
		$('#dialog_excel_${uuid }').find("iframe")[0].contentWindow.esayFormSubmit("window.parent.successMethod_${uuid}");
		
	}
	function successMethod_${uuid}(){
		grid.reload("${refreshGridName==null||refreshGridName==''?'grid':refreshGridName}");
		$("#dialog_excel_${uuid}").dialog("close");
	}
</script>