<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>青岛市卫生和计划生育人才综合服务中心</title>
<script src="${ctxFront}/home.js" type="text/javascript"></script>
<style type="text/css">
.panel-body {
	overflow: hidden;
}
a{ color:#fff; text-decoration:none; }
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="cc">
		<div data-options="region:'north'" border="false" style="height:45px;background-color: ${theme.backcolor};border-bottom: #ffffff solid 1px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
			 <td style="width:500px">
			  <img src="${ctxFront }/images/logo.png" >
			 </td>
			 <td align="right">
			   <table border="0" cellspacing="0" cellpadding="0" >
			   <tr>
			     <td align="right" style="color:#fff"><shiro:principal property="name"/>/<shiro:principal property="loginName"/></td>
			     <td style="width:15px">&nbsp;</td>
			     <td>
			     <a href="javascript:void(0);" onclick="addPanel('修改密码','editpass','${ctx}/sys/user/editpass','refresh')" style="cursor: pointer;display:inline-block;margin-right:10px"><span style="display:block;background: url('${ctxFront }/images/key.png') no-repeat left; padding-left:18px;height:16px">修改密码</span></a>
			     <a href="${ctx }/logout" style="cursor: pointer;display:inline-block;margin-right:10px"><span style="display:block;background: url('${ctxFront }/images/door_out.png') no-repeat left; padding-left:18px;height:16px">退出</span></a>
			     <a href="javascirpt:void(0);" id="layoutbtn" style="cursor: pointer;display:inline-block;margin-right:10px"><span style="display:block;background: url('${ctxFront }/images/category.png') no-repeat left; padding-left:18px;height:16px">布局</span></a>
			     </td>
			     <td style="width:15px">&nbsp;</td>
			      <td style="color:#fff">
				  <select id="theme"  name="theme" style="width:80px;height:20px">
	                <option value="default@718aac" ${theme.theme=='default'?'selected':''}>默认主题</option>
	                <option value="bootstrap@a9a9a9" ${theme.theme=='bootstrap'?'selected':''}>bootstrap</option>
	                <option value="black@000000" ${theme.theme=='black'?'selected':''}>黑色</option>
	                <option value="gray@989898" ${theme.theme=='gray'?'selected':''}>灰色</option>
	                <option value="material@a9a9a9" ${theme.theme=='material'?'selected':''} >material</option>
	                <option value="metro@1A355C" ${theme.theme=='metro'?'selected':''}>metro</option>
	                <option value="metro-blue@558c9d" ${theme.theme=='metro-blue'?'selected':''}>metro蓝</option>
	                <option value="metro-gray@a3a7ab" ${theme.theme=='metro-gray'?'selected':''}>metro灰</option>
	                <option value="metro-green@8f967c" ${theme.theme=='metro-green'?'selected':''}>metro绿</option>
	                <option value="metro-orange@a1977d" ${theme.theme=='metro-orange'?'selected':''}>metro橙</option>
	                <option value="metro-red@b79a9d" ${theme.theme=='metro-red'?'selected':''}>metro粉红</option>
	                <option value="ui-cupertino@7f9fb5" ${theme.theme=='ui-cupertino'?'selected':''}>暗蓝色</option>
	                <option value="ui-dark-hive@000000" ${theme.theme=='ui-dark-hive'?'selected':''}>暗黑色</option>
	                <option value="ui-pepper-grinder@9d9981" ${theme.theme=='ui-pepper-grinder'?'selected':''}>胡椒色</option>
	                <option value="ui-sunny@817865" ${theme.theme=='ui-sunny'?'selected':''}>阳光色</option>
	              </select>
                 </td>
                 <td style="width:15px">&nbsp;</td>
			   </tr>
			   </table>
			   
			 </td>
			</tr>
		</table>
		
		</div>
		<div data-options="region:'west',split:true,tools:'#treetool'" title="系统导航" style="width:230px;overflow: hidden;">
		<iframe id="menucontent" style="height: 100%; width:100%; " frameborder="0"
			src="${ctx }/sys/module/menu"></iframe>
		</div>
		<div id="treetool">
        <a href="javascript:void(0)" title="全部展开" class="icon-expand" onclick="menuExpand(1);"></a>
        <a href="javascript:void(0)" title="全部收缩" class="icon-shrink" onclick="menuExpand(0);"></a>
        </div>
		<div data-options="region:'center'" >
	
		
           <div id="tt" class="easyui-tabs" data-options="fit:true" border="false">
             <div id="home" title="首页" >

                
             </div>
           </div>
           
			<div id="mm" class="easyui-menu" style="width:150px;">
				<div id="mm-tabclose">关闭</div>
				<div id="mm-tabcloseall">关闭全部</div>
				<div id="mm-tabcloseother">关闭其他</div>
				<div class="menu-sep"></div>
				<div id="mm-tabcloseright">关闭右侧标签</div>
				<div id="mm-tabcloseleft">关闭左侧标签</div>
			</div>
           	<div id="layoutmm" class="easyui-menu" style="width:150px;">
				<div id="mm-left" data-options="iconCls:'icon-menu-left'">
				<span>左侧菜单</span>
				   <div>
				    <div class="theme" css="default" bgcolor="#E6EEF8" forecolor="#0E2D5F" iconCls="${theme.theme=='default'?'icon-ok':''}">树形</div>
				    <div class="theme" css="black" bgcolor="#444" forecolor="#fff" iconCls="${theme.theme=='black'?'icon-ok':''}">手风琴</div>
				   </div>
				</div>
				<div id="mm-top" data-options="iconCls:'icon-menu-top'">
				   <span>顶部菜单</span>
				</div>
				<div id="mm-top_left" data-options="iconCls:'icon-menu-top-left'">
				组合菜单
				   <div>
				    <div class="theme" css="default" bgcolor="#E6EEF8" forecolor="#0E2D5F" iconCls="${theme.theme=='default'?'icon-ok':''}">树形</div>
				    <div class="theme" css="black" bgcolor="#444" forecolor="#fff" iconCls="${theme.theme=='black'?'icon-ok':''}">手风琴</div>
				   </div>
				</div>
		    </div>
		</div>
		<div data-options="region:'south'" border="false" style="height:30px; text-align: center;" class="tabs-header tabs-header-noborder">
	     <table cellpadding="0" cellspacing="0" border="0" width="100%" height="30px">
	     <tr>
	      <td align="center" valign="middle" class="panel-title" style="font-weight: normal;">
	      @版权所有
	      </td>
	     </tr>
	     </table>
		</div>
		
	</div>
</body>
</html>