<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>Insert title here</title>
<script>
$(function(){
	gotoframe();
});

function gotoframe(){
	$("#frame_1")[0].src=$("#url").val();
}
function search2(){
	var examid=$("input[name='examclassid']").val();
	var kc=$("input[name='kc']").val();
	var address=$("input[name='address']").val();
	ajax2("${ctx}/web/exam/searchprint?examid="+examid+"&kc="+kc+"&address="+address+"&rows=15&page=1",function(result){
		$("#page1").pagination({
			pageNumber:1,
			total:result.total
			});
		$("#url").val("${ctx}/web/exam/examroomprint?examid="+examid+"&kc="+kc+"&address="+address);
		$("#frame_1")[0].src=$("#url").val()+"&rows=15&page=1";
	});
}
function printkc(){
	$("#frame_1")[0].contentWindow.printl();
}
</script>
</head>
<body>
    <input id="url" type="hidden" value="${ctx}/web/exam/examroomprint?rows=15">
    <div class="easyui-layout" id="cc" fit="true" border="false">
        <div data-options="region:'north'" style="height:40px;" border="false">
			<table id="tool" width="100%" class="datagrid-toolbar" > 
			<tr>
			<td style=" padding-top: 5px">
			考试:<input id="examclassid" name="examclassid" style="width:300px;"  class="easyui-combobox" data-options="
			                            url:'${ctx }/web/exam/examclassselectlist',
			                            valueField: 'id',
				                        textField: 'text'
			                            ">
			考场:<select id="kc" name="kc" style="width:80px;"  class="easyui-combobox">
			          <option value="">-请选择-</option>
			          <c:forEach items="${kcs }" var="k">
			              <option value="${k }">${k }</option>
			          </c:forEach>
			    </select>
			考试地点:<select id="address" name="address" style="width:250px;"  class="easyui-combobox">
			          <option value="">-请选择-</option>
			          <c:forEach items="${address }" var="a">
			              <option value="${a }">${a }</option>
			          </c:forEach>
			    </select>
			 <a href="javascript:void(0)" onclick="search2();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</td>
			<td style="white-space:nowrap;" align="right">
			<a href="javascript:void(0);" onclick="printkc()" class="easyui-linkbutton" iconCls="icon-print" >打印</a>
			</td>
			</tr>
			</table>
        </div>

        <div data-options="region:'center'" style="overflow: hidden" border="false">
            <iframe id="frame_1" style="height: 100%; width:100%; " frameborder="0"
				></iframe>
        </div>
        <div data-options="region:'south'" style="height:32px; border-left:none; border-right:none; border-bottom:none;" >
        <div id="page1" class="easyui-pagination" data-options="
                                         fit:true,
                                         layout:['first','prev','links','next','last'],
                                         pageSize:15,
                                         total:${p.totalCount},
                                         onSelectPage: function(pageNumber, pageSize){
								                $('#frame_1')[0].src=$('#url').val()+'&page='+pageNumber+'&rows=15';
								            }
        "></div>
        </div>
    </div>
    
</body>
</html>