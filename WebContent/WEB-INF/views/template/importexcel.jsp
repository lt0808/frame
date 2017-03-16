<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>信息编辑</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<body>
           <form id="form1" class="easyui-form" name="form1" method="post" enctype="multipart/form-data" action="${formAction }">
           <table width="100%" cellpadding="5" cellspacing="0">
              <tr>
              <td style="width:180px">Excel导入表模板：</td>
              <td>
              <a href="${ctxFront }/download/excel/template/${tmpFileName}" class="easyui-linkbutton" iconCls="icon-excel"> 下载模板 </a>
              </td>
              </tr>
              <tr>
              <td>请选择你要导入的excel路径：</td>
              <td>
               <input name="upfile" required tipPosition="bottom" class="easyui-filebox" data-options="prompt:'选择文件...',buttonText:'选择'" style="width:310px">
              </td>
              </tr>
           </table>
           </form>
</body>
</html>