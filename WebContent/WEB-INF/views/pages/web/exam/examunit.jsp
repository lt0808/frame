<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>招聘单位管理</title>
<tags:listpage url="${ctx }/web/exam" dealMethod="examunitdeal" gridName="unitlist" deleteMethod="deleteexamunit"></tags:listpage>


</head>
<body>
  <table id="unitlist" class="easyui-datagrid"  border="false"
       data-options="
            url:'${ctx }/web/exam/unitlist',
            fit:true,
            rownumbers:true,
            toolbar:'#tool',
            pagination:true,
            checkOnSelect:false,
            pageSize:${pageSize}
            ">
       <thead>
           <tr>
               <th data-options="field:'ck',checkbox:true"></th>
               <th data-options="field:'edit',formatter:celledit"></th>
               <th data-options="field:'unitname'">单位名称</th>
               <th data-options="field:'unitcode'">单位代码</th>
               <th data-options="field:'isshow',formatter:function(v){return (v==1?'显示':'不显示')}">显示状态</th>
               <th data-options="field:'users'">绑定用户</th>
               <th data-options="field:'classname'">考试类别</th>
           </tr>
       </thead>
   </table>

<table id="tool" width="100%" > 
<tr>
<td style=" padding-top: 5px">
考试:<input name="examclassid" style="width:400px;"  class="easyui-combobox" data-options="
                            url:'${ctx }/web/exam/examclassselectlist',
                            valueField: 'id',
	                        textField: 'text'
                            ">
 <a href="javascript:void(0)" onclick="grid.reload('unitlist')" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
</td>
<td style="white-space:nowrap;" align="right">
<a href="javascript:void(0);" onclick="deal('')" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
<a href="javascript:void(0);" onclick="delRows()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</td>
</tr>
</table>

</body>
</html>