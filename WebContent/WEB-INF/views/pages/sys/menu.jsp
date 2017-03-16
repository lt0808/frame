<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctxFront}/easyui/themes/${theme.theme}/easyui.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="${ctxFront }/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctxFront }/ztree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctxFront }/ztree/js/jquery.ztree.core.js"></script>
	<script>
	function expandall(flag){
		var treeObj = $.fn.zTree.getZTreeObj("moduleTree"); 
		if(flag==1)
		  treeObj.expandAll(true); 
		else
			treeObj.expandAll(false); 
	}
	
	</script>
</head>
<body style="margin:0px" class="panel-body panel-body-noborder">
	<SCRIPT type="text/javascript">
	var _parent=window.parent;
		var setting = {
			data : {
				simpleData: {
					enable: true
				},
				key : {
					name : "name"
					//url : "urllink"
				}
			},
			async : {
				enable : true,
				url : "${ctx}/sys/module/tree"
			},
			callback:{
			    onClick:function(e,d,n){
			       _parent.addPanel(n.name,n.id,n.urllink,n.opentab);
			    }
			}
		};
		
		$(document).ready(function() {
			$.fn.zTree.init($("#moduleTree"), setting);
		});

		
	</SCRIPT>
	<div>

		<div class="zTreeDemoBackground left">
			<ul id="moduleTree" class="ztree"></ul>
		</div>
	</div>
</body>
</html>