<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>Insert title here</title>
</head>
<body>
<form class="easyui-form"  action="${ctx }/sys/user/saveeditpass" method="post">
<input type="hidden" name="id" value="<shiro:principal property="id" />">
<input name="oldpass" label="原密码：" labelPosition="top" class="easyui-passwordbox" prompt="输入原密码" iconWidth="28" style="width:250px;height:55px;padding:10px" required><br>
     <input id="pass" name="pass" label="新密码：" labelPosition="top" class="easyui-passwordbox" prompt="输入密码" iconWidth="28" style="width:250px;height:55px;padding:10px" required><br>
     <input label="确认密码：" labelPosition="top" class="easyui-passwordbox" prompt="再次输入密码" iconWidth="28" validType="confirmPass['#pass']" style="width:250px;height:55px;padding:10px" required><br><br>
     <a href="javascript:void(0)" onclick="esayFormSubmit();" class="easyui-linkbutton" iconCls="icon-ok" style="width:250px;height:32px" >修改密码</a>
</form>
    <script type="text/javascript">
        $.extend($.fn.validatebox.defaults.rules, {
            confirmPass: {
                validator: function(value, param){
                    var pass = $(param[0]).passwordbox('getValue');
                    return value == pass;
                },
                message: '验证密码必须与密码相同'
            }
        })
    </script>
</body>
</html>