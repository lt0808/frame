<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<<%@page import="com.frame.web.entity.Exam"%>
<<%@page import="com.frame.common.page.Pagination"%>
<%

Pagination p = (Pagination)request.getAttribute("p");
List<Exam> exams=(List<Exam>)p.getList();
int colsize=3;
int z=exams.size()/colsize;
int y=exams.size()%colsize;
int rowsize=z+(y>0?1:0);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>考场打印</title>
<script type="text/javascript">
function printl(){
	window.print();
}
</script>
</head>
<body>
<!--startprint-->
     <table cellpadding="5" cellspacing="0" border="0" width="100%">
       <tr>
        <td></td>
        <td></td>
        <td></td>
        <td align="right">
        
        </td>
       </tr>
     </table>
     <table cellpadding="0" cellspacing="0" border="1" bordercolor="#000" width="100%">
     <%
     int index=0;
     for(int i=0;i<rowsize;i++){
     %>
        <tr>
        <%for(int j=0;j<3;j++){ %>
        <td width="25%" height="130">
        
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td style="width:70px">姓名:</td>
				    <td><%=exams.get(index).getUsername() %></td>
				    <td rowspan="5" style="width:75px"
				        <img width="64" height="78" src="22">
				    </td>
				  </tr>
				  <tr>
				    <td>性别:</td>
				    <td><%=exams.get(index).getSex() %></td>
				  </tr>
				  <tr>
				    <td>报考单位:</td>
				    <td><%=exams.get(index).getUnitname() %></td>
				  </tr>
				  <tr>
				    <td>报考岗位:</td>
				    <td><%=exams.get(index).getPostname() %></td>
				  </tr>
				  <tr>
				    <td>座号:</td>
				    <td><%=exams.get(index).getZh() %></td>
				  </tr>
				  <tr>
				    <td>准考证号:</td>
				    <td colspan="2"><%=exams.get(index).getZkzh()%></td>
				  </tr>
				  <tr>
				    <td>身份证证号:</td>
				    <td colspan="2"><%=exams.get(index).getSfz() %></td>
				  </tr>
				</table>
        
        </td>
        <%
        index+=1;
        if(index>=exams.size()){
        	break;
        }
        } %>
        </tr>
     <%} %>
     </table>
     <!--endprint-->
</body>
</html>