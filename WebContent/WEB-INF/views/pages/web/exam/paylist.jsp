<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>Insert title here</title>
<script type="text/javascript">




</script>
</head>
<body>

<table id="tool" width="100%" > 
<tr>
<td style=" padding-top: 5px">
考试:<input required value="${ep.examclass.id }" id="examclassid" tip class="easyui-combobox" name="examclassid" style="width:250px;" tipPosition="bottom" data-options="
	                     url:'${ctx }/web/exam/examclassselectlist',
	                     valueField: 'id',
	                     textField: 'text'
	 ">
姓名:<input class="easyui-textbox" name="userName"  style="width:60px;">
身份证:<input class="easyui-textbox" name="userSfz"  style="width:140px;">
报名序号:<input class="easyui-textbox" name="userId"  style="width:60px;">
订单号:<input class="easyui-textbox" name="orderNo"  style="width:100px;">

<a href="javascript:void(0)" onclick="grid.reload('tb')" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
</td>
</tr>
</table>

<table id="tb" class="easyui-datagrid" data-options="
               url:'${ctx}/web/exam/paylist',
               fit:true,
               border:false,
               rownumbers:true,
               pagination:true,
               toolbar:'#tool',
               checkOnSelect:false,
               pageSize:${pageSize}
               " >
    <thead data-options="frozen:true">
        <tr>
            <th data-options="field:'orderNo'" align="center" >订单号</th>
            <th data-options="field:'userName'" align="center" >姓名</th>
            <th data-options="field:'userId'" align="center" >报名序号</th>
            <th data-options="field:'userSfz'" align="center">身份证</th>
        </tr>
    </thead>
    <thead>
        <tr>
            <th data-options="field:'userPaymentAmount'" align="center">交易金额</th>
            <th data-options="field:'userPaymentHostdate'" align="center">交易日期</th>
            <th data-options="field:'userPaymentHosttime'" align="center">交易时间</th>
            <th data-options="field:'userBatchno'" align="center">银行批次号</th>
            <th data-options="field:'userPaymentVouchno'" align="center">交易类型</th>
            <th data-options="field:'status',formatter:function(v){return lt.yn(v,'已支付','未支付');}" align="center">订单状态</th>
        </tr>
    </thead>
</table>

</body>
</html>