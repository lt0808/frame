<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>Insert title here</title>
<script type="text/javascript">
function delRows(){
	var rows = $('#tb').datagrid('getChecked');
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
				ajax2("${ctx}/web/exam/delete?ids="+ids,function(){grid.reload("tb");});
	        }
	    });
	}
}
function infodeal(v,row){
    return '<a href="javascript:void(0);" onclick="deal(\''+row.id+'\')">'+v+'</a>';
}
function deal(id){
	openDeal("${ctx }/web/exam/examdeal?id="+id,"tb",{width:700});
}
function getunit(rec){

    $('#examunitid').combobox('clear');
    $('#examunitid').combobox('reload', '${ctx}/web/exam/examunitselectlist?examclassid='+rec.id);
    
    $('#kc').combobox('clear');
    $('#kc').combobox('reload', '${ctx}/web/exam/examkcselectlist?examclassid='+rec.id);
    
    $('#examaddress').combobox('clear');
    $('#examaddress').combobox('reload', '${ctx}/web/exam/examaddressselectlist?examclassid='+rec.id);
}
function exportinfo(){
	lt.formPost("${ctx}/web/exam/exportExcelInfo");
}
function exporttj(){
	if($("input[name='examclassid']").val()==""){
		alert("请先选择考试");
	}else{
	    lt.formPost("${ctx}/web/exam/exportExcelTj");
	}
}
</script>
</head>
<body>

<table id="tool" width="100%" > 
<tr>
<td style=" padding-top: 5px">
考试:<input required value="${ep.examclass.id }" id="examclassid" tip class="easyui-combobox" name="examclassid" style="width:250px;" tipPosition="bottom" data-options="
	                     url:'${ctx }/web/exam/examclassselectlist',
	                     valueField: 'id',
	                     textField: 'text',
	                     onSelect: getunit
	 ">
单位:<input required tipPosition="bottom" value="${ep.examunit.id }" id="examunitid" class="easyui-combobox" name="examunitid" style="width:150px;" data-options="
	                     valueField: 'id',
	                     textField: 'text'
	                    ">
姓名:<input class="easyui-textbox" name="username"  style="width:60px;">
身份证:<input class="easyui-textbox" name="sfz"  style="width:140px;">
准考证:<input class="easyui-textbox" name="zkzh"  style="width:80px;">
缴费:<select class="easyui-combobox" panelHeight="70px" name="isjf">
      <option value="">请选择</option>
      <option value="1">已缴费</option>
      <option value="0">未缴费</option>
    </select>
状态:<select class="easyui-combobox" panelHeight="110px" name="status">
      <option value="">请选择</option>
      <option value="agree">已通过</option>
      <option value="disagree">未通过</option>
      <option value="no">未提交</option>
      <option value="yes">未审核</option>
    </select>
</td>
</tr>
<tr>
<td style=" padding-top: 5px">
信息表打印:<select class="easyui-combobox" name="infoprint" panelHeight="70px">
      <option value="">请选择</option>
      <option value="1">已打印</option>
      <option value="0">未打印</option>
    </select>
承诺书打印:<select class="easyui-combobox" name="promiseprint" panelHeight="70px">
      <option value="">请选择</option>
      <option value="1">已打印</option>
      <option value="0">未打印</option>
    </select>
准考证打印:<select class="easyui-combobox" name="zkzprint" panelHeight="70px">
      <option value="">请选择</option>
      <option value="1">已打印</option>
      <option value="0">未打印</option>
    </select>
考点:<select class="easyui-combobox" name="examaddress" id="examaddress" style="width:300px" data-options="
                         valueField: 'id',
	                     textField: 'text'
                         ">
      <option value="">请选择</option>
    </select>
考场:<select class="easyui-combobox" name="kc" id="kc" data-options="          
                         valueField: 'id',
	                     textField: 'text'
                         ">
      <option value="">--请选择--</option>
    </select>
</td>
</tr>
<tr>
<td style="white-space:nowrap;" align="right">
<a href="javascript:void(0)" onclick="grid.reload('tb')" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
<a href="javascript:void(0)" onclick="exporttj()" class="easyui-linkbutton" iconCls="icon-excel">导出统计到Excel</a>
<a href="javascript:void(0)" onclick="exportinfo()" class="easyui-linkbutton" iconCls="icon-excel">考生信息导出到Excel</a>
<tags:importExcel btnName="笔试安排Excel表导入"></tags:importExcel>
<a href="javascript:void(0);" onclick="delRows()" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
</td>
</tr>
</table>

<table id="tb" class="easyui-datagrid" data-options="
               url:'${ctx}/web/exam/examlist',
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
            <th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'username',formatter:infodeal" align="center" >姓名</th>
            <th data-options="field:'sfz'" align="center">身份证</th>
        </tr>
    </thead>
    <thead>
        <tr>

            <th data-options="field:'unitname'" align="center">报考单位</th>
            <th data-options="field:'postname'" align="center">报考岗位</th>
            <th data-options="field:'examname'" align="center">考试</th>
            <th data-options="field:'createDate',formatter:lt.formatdatetime" align="center">报考时间</th>
            <th data-options="field:'isjf',formatter:function(v){return lt.yn(v,'已缴费','未缴费');}" align="center">缴费状态</th>
            <th field="zkzh" align="center">准考证号</th>
            <th field="kc" align="center">考场</th>
            <th field="zh" align="center">座号</th>
            <th data-options="field:'status',formatter:restatus">报考状态</th>
            <th field="shr" align="center">审核人</th>
            <th data-options="field:'info_print',formatter:function(v){return lt.yn(v,'已打印','未打印');}" align="center">信息表</th>
            <th data-options="field:'promise_print',formatter:function(v){return lt.yn(v,'已打印','未打印');}" align="center">承诺书</th>
            <th data-options="field:'zkz_print',formatter:function(v){return lt.yn(v,'已打印','未打印');}" align="center">准考证</th>
        </tr>
    </thead>
</table>
<script type="text/javascript">
function re(v,y,n){
	return "1";
}
function restatus(v){
	if(v=="no"){
		return "<font color=red>未提交</font>";
	}else if(v=="agree"){
		return "<font color=green>已通过</font>";
	}else if(v=="disagree"){
		return "<font color=red>未通过</font>";
	}else if(v=="yes"){
		return "<font color=red>未审批</font>";
	}else{
		return "";
	}
}
</script>

</body>
</html>