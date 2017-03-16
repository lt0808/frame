if(window.top!=window.self){
	window.top.location=window.location.href;
}
function setCookie(name,value)
{
	var Days = 366;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
}
function delCookie(name)
{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null)
	document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
function vilidLogin(){
	if(document.getElementById("username").value==""){
		alert("请输入登录名！");
		return false;
	}
	if(document.getElementById("remusername").checked){
		setCookie("name_",document.getElementById("username").value);
		setCookie("ckeckbox_","checked");
	}else{
		delCookie("name_");
		delCookie("ckeckbox_");
	}
	if(document.getElementById("password").value==""){
		alert("请输入密码！");
		return false;
	}
	return true;
}
window.onload = function(){
	document.getElementById("username").value=getCookie("name_");
	if(getCookie("ckeckbox_")=="checked"){
		document.getElementById("remusername").checked=true;
	}
}

