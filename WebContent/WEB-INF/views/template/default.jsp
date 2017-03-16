<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
	<head>
		<title><sitemesh:title/></title>
		<%@include file="/WEB-INF/views/include/head.jsp" %>
		<sitemesh:head/>
	</head>
	<body>
		<sitemesh:body/>
		
		<!-- 弹出dialog模块编辑 -->
	    <div id="dialog_" class="easyui-dialog" title="编辑" style="top:15px;width:550px;height:425px;padding:0px;overflow: hidden;"
	            data-options="
	                iconCls: 'icon-save',
	                buttons: '#dlg-buttons_',
	                maximizable:true,
	                modal:true,
	                closed:true
	            ">
	        	<iframe id="dialogFrame_" style="height: 100%; width:100%; " frameborder="0"
				></iframe>
	    </div>
	    <div id="dlg-buttons_">
	        <a id="dlg-buttons_save_" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialog_').dialog('close')">关闭</a>
	    </div>
		
	</body>
	
</html>
