<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>青岛市卫生和计划生育人才综合服务中心</title>
<script src="${ctxFront}/login.js" type="text/javascript"></script>
<style>
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,p,blockquote,th,td{margin:0;padding:0;border:none;}
body{font-size:12px; font-family:Cambria, "Hoefler Text", "Liberation Serif", Times, "Times New Roman", serifbackground:#fff;color:#2b2b2b;}
address,caption,cite,code,dfn,em,strong,th,var{font-style:normal;font-weight:normal;}
select,input,img{vertical-align:middle;}
table{border-collapse:collapse;border-spacing:0;}
table,td,tr,th{font-size:12px;}
a{text-decoration:none;cursor:pointer;}
fieldset,img{border:0;}

.main{ position:absolute;left:50%; top:50%; background:url(${ctxFront}/images/login.png) no-repeat; width:772px; height:468px; margin:-234px 0 0 -386px;}

.input-box{ position:absolute; top:110px; left:410px;color:#0952a1;}
.input{ border:1px solid #7491b5; width:154px; height:28px; background-color:#f5fafe; padding-left:4px; line-height:28px;}
.input-box p{ line-height:40px;}
.input-box .check{ width:14px; height:14px; margin-left:48px;}
.input-box .record{ margin-left:6px;}
.input-box .link{ margin-top:14px; margin-left:70px;}
a{ height:28px; width:72px; display:inline-block; color:#fff; line-height:28px; text-align:center; margin-top:10px;}
.log{ width:72px; height:28px; border:none;background:url(${ctxFront}/images/log.png) no-repeat; margin-right:16px; margin-left:48px}

.reset{width:72px; height:28px; border:none;background:url(${ctxFront}/images/reset.png) no-repeat; color:#6d6d6d;}
.main-box .copy{ text-align:center; margin-top:8px; color:#666666;}
.text{ text-align:center; color:#fff; margin-top:398px; line-height:24px;}
</style>

</head>
<body style="background-color:#3987cf;">

<form action="${ctx}/login" method="post">
<div class="main">
<div class="login-box">
	 <div class="input-box">
        	<p>用户名：<input type="text" name="username" id="username" value="" class="input"/></p>
           	<p><span style=" padding-right:12px;">密</span>码：<input id="password" name="password" type="password" class="input"/></p>
           	<p><input type="checkbox" name="remusername" id="remusername"  class="check"/><span class="record">记住登录名</span></p>
      
      <input type="submit" class="log" value="登 录" onclick="return vilidLogin();" /><input type="reset" class="reset" value="重 置" /></div>
      </div>
      <div class="text">
           请使用IE8及以上版本浏览器
      </div>
      </div>
</form>
</body>
</html>