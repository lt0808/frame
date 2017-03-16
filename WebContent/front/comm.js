//页面中提交form表单操作
function esayFormSubmit(successFunc){//successFunc提交完成后执行函数

	$("form").form('submit', {
		success : function(result) {
			var win;
			lt.topWin().lt.message(result);
			var obj = eval('(' + result + ')')[0];

			if(successFunc){
				try{
					lt.runfun(successFunc+"('"+obj.operator+"','"+obj.message+"')");
				}catch(e){}
			}
			try{
				if(obj.operator=="success"){
					window.parent.$('#dialog_').dialog('close');
					$('#dialog_').dialog('close');
				}
			}catch(e){}
			
		},
		onLoadError: function(){
			lt.topWin().lt.message("提交错误，请重提交");
		}
	});
	
	//$("form").submit();
}
function formSubmit(validFunc){//validFunc提交前验证函数
	if(validFunc){
		if(lt.runfun(validFunc)){
			$("form").submit();
		}else
			return;
	}
	$("form").submit();
}
var function_;
function dialogSave_(fun,target){
	function_=null;
	if(fun){
		function_=fun;
		if(target=="self"){
			esayFormSubmit("function_");
		}else{
			$('#dialog_').find("iframe")[0].contentWindow.esayFormSubmit("window.parent.function_");
		}
	}else{
		$('#dialog_').find("iframe")[0].contentWindow.esayFormSubmit();
	}
}
var myrefresh_;
function refreshComponent_(refreshComponent){
	
	if(refreshComponent&&refreshComponent.length>0){
		myrefresh_=new Array();
		var cp=refreshComponent.split(",");
		for(var i=0; i<cp.length;i++){
			if($("#"+cp[i]).hasClass("easyui-treegrid")){
				myrefresh_[i]="$('#"+cp[i]+"').treegrid('reload');";
			}else if($("#"+cp[i]).hasClass("easyui-datagrid")){
				myrefresh_[i]="$('#"+cp[i]+"').datagrid('reload');";
			}else{
			}
		}
	}
}
function openDialog(url,refreshComponent){
	refreshComponent_(refreshComponent);
	dl.openDeal(url,{
		saveSuccessMethod:result_1
	});
}
function openDeal(url,refreshComponent,config){
	refreshComponent_(refreshComponent);
	if(config){
		config['saveSuccessMethod']=result_1;
		dl.openDeal(url,config);
	}
	else{
		dl.openDeal(url,{
			saveSuccessMethod:result_1
		});
	}
}
function result_1(operator,message){
	if(operator=="success"){
		if(myrefresh_.length>0){
			for(var i=0;i<myrefresh_.length;i++){
				lt.runfun(myrefresh_[i]);
			}
		}
	}
}
var lt={
	// 提交form
	formsubmit:function(action, method,fm,onSuccess){
		$(fm).form('submit', {
			url : action,
			onSubmit : function() {
				return $(this).form('validate');
			},

			success : function(result) {
				try{
				if(onSuccess!=""&&onSuccess!=undefined){
					var b=lt.runfun(onSuccess);
				}
				}catch(e){}
				lt.topWin().lt.message(result);
			}
		});
	},
	runfun:function(funs){
		var b = true;
		if (funs) {
			var fun = funs.split(";");
			for ( var i = 0; i < fun.length; i++) {
				var f="";
				if(fun[i].indexOf("(")==-1)
				    f = eval(fun[i]+"()") + "";
				else
					f = eval(fun[i]) + "";
				if (f != "true") {
					b = false;
					break;
				}
			}
		}
		return b;
	},
	// 提交后提示信息
	message:function(result){
		var msg = result;
		try {
			var obj;
			if(typeof(result)=="string"){
			    obj = eval('(' + result + ')')[0];
			}
			if(typeof(result)=="object"){
				obj = result[0];
			}

			if (obj.operator == "success") {
				msg = "<div class='successmsg'>"
						+ obj.message + "</div>";
			} else if (obj.operator == "error") {
				msg = "<div class='errormsg'>"
						+ obj.message + "</div>";
			} else {
				msg = "<font >" + result + "</font>";
			}
			if (obj.redirect && obj.redirect != "") {
				window.location = obj.redirect;
				return;
			}
		} catch (e) {
		}
		if (msg.indexOf("loginpage") > 0) {
			alert("登录失效，请重新登录");
			window.location = document.location.toString();
			return;
		}
		$.messager.show({
			title : '操作信息',
			msg : msg
		});		
	},
	// 对象转字串
	obj2str:function(o) {
		var r = [];
		if (typeof o == "string" || o == null) {
			return "'" + o + "'";
		}
		if (typeof o == "object") {
			if (!o.sort) {
				r[0] = "{"
				for ( var i in o) {
					r[r.length] = i;
					r[r.length] = ":";
					r[r.length] = lt.obj2str(o[i]);
					r[r.length] = ",";
				}
				r[r.length - 1] = "}"
			} else {
				r[0] = "["
				for ( var i = 0; i < o.length; i++) {
					r[r.length] = lt.obj2str(o[i]);
					r[r.length] = ",";
				}
				r[r.length - 1] = "]"
			}
			return r.join("");
		}
		return o.toString();
	},
	// 格式化日期datetime
	formatdatetime:function(val){
		var d=new Date(Date.parse((val+"").replace(/-/g,   "/").replace(/\.[\x00-\xff]*/g,'')));
		return lt.date2str(d,"yyyy-MM-dd hh:mm:ss");
	},
	// 格式化日期date
	formatdate:function(val){
		var d=new Date(Date.parse((val+"").replace(/-/g,   "/").replace(/\.[\x00-\xff]*/g,'')));
		return lt.date2str(d,"yyyy-MM-dd");
	},
	// 格式化是非
	yn:function(val,yes,no){
		return val==1?"<font color=green>"+yes+"</font>":"<font color=red>"+no+"</font>";
	},
	date2str:function date2str(x, y) {
		   var z = {
		      y: x.getFullYear(),
		      M: x.getMonth() + 1,
		      d: x.getDate(),
		      h: x.getHours(),
		      m: x.getMinutes(),
		      s: x.getSeconds()
		   };
		   return y.replace(/(y+|M+|d+|h+|m+|s+)/g, function(v) {
		      return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1))).slice(-(v.length > 2 ? v.length : 2))
		   });
	},
	// ajax异步提交方法
	// url:异步地址,为null时，会自动找到form的action
	// data:发送的数据，可用字串或json数据，可以直接使用getData('form的id')自动提交表单里的所有有name的数据;
	// successMethod:异步返回成功后执行的方法
	// berforeMethod:异步之前执行的方法
	// isMessage:是否有弹出message
	// formname:form的id，默认为form[0]	
	ajax:function(url, data, successMethod, beforeMethod, isMessage, formname){
		// 异步地址为null时，则会取data中form的action地址，慎用null(确保使用null时有form和其action)
		var URL0 = (url == null ? data.action : url);
		var ajaxFunction = successMethod;
		var beforeFunction = beforeMethod;;
		$.ajax({
			url : URL0,
			cache : false,
			type : "post",
			data : data,
			timeout:5000,
			beforeSend : function(e, xhr, o) {
				var currentForm = lt.getCurrentFrom(formname);
				try {
					if (!$(currentForm).form('validate')) {
						return false;
					}
				} catch (e) {
				}
				if (beforeFunction != null)
					beforeFunction();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(errorThrown);
				$(".error").remove();
			},
			success : function(result) {
				if (ajaxFunction != null)
					ajaxFunction(result);

				if (isMessage != "no" && isMessage != "n" && isMessage!=false)
					lt.topWin().lt.message(result);
				else
					lt.LoginExpiredJudge(result);
			},
			complete : function() {
				$(".error").remove();
			}
		});
	},
	// 登录过期判断，用的ajax过多，过期后页面中ajax操作需要跳转到login页,比较麻烦，meassge函数可解决，原理跟这一样
	// 此函数只用在没有使用message操作时
	LoginExpiredJudge:function(result){
		if (result.indexOf("loginpage") > 0) {
			alert("登录失效，请重新登录");
			window.location = document.location.toString();
		}
	},
	//获取form
	getCurrentFrom:function(formname){
		var form;
		if (formname == undefined || formname == "" || formname == null) {
			form = document.forms[0];
		} else {
			form = document.getElementById(formname);
		}
		return form;
	},
	// 获取form中数据，生成json数据，formname为空时则用document中第一个form
	getData:function(formname){
		var form = lt.getCurrentFrom(formname);

		var str = "{'action':'" + form.action + "'";
		for ( var i = 0; i < form.childNodes.length; i++) {

			if (form.childNodes[i].name != ""
					&& form.childNodes[i].name != undefined)
				str = lt.AppendStr(str, lt.getFormHashMap(form.childNodes[i]));

			if (form.childNodes[i].hasChildNodes()) {
				str += lt.getEle(form.childNodes[i]);
			}
		}
		str += "}";
		// alert(str);
		var json = eval('(' + str + ')');
		// alert(json.action);
		return json;
	},
	// 获取页面中所有form中数据
	getAllData:function(){	
		var str = "{'action':'"+document.forms[0].action+"'";
		for(var i=0; i<document.forms.length;i++){
			var form=document.forms[i];
		  for ( var i = 0; i < form.childNodes.length; i++) {
			
			  if (form.childNodes[i].name != ""
				&& form.childNodes[i].name != undefined)
				str = lt.AppendStr(str, lt.getFormHashMap(form.childNodes[i]));
			
			  if (form.childNodes[i].hasChildNodes()) {
				str += lt.getEle(form.childNodes[i]);
			  }
		  }
		}
		
		str += "}";
		// alert(str);
		var json = eval('(' + str + ')');
		// alert(json.action);
		return json;
	},
	// 获取页面中body中数据
	getBodyData:function(){	
		
		var str = "{'data__':'0'";

		var form=document.body;
		for ( var i = 0; i < form.childNodes.length; i++) {
			
			if (form.childNodes[i].name != ""
				&& form.childNodes[i].name != undefined)
				str = lt.AppendStr(str, lt.getFormHashMap(form.childNodes[i]));
			
			if (form.childNodes[i].hasChildNodes()) {
				str += lt.getEle(form.childNodes[i]);
			}
		}
		
		str += "}";
		// alert(str);
		var json = eval('(' + str + ')');
		// alert(json.action);
		return json;
	},
	// 循环找出多层子元素
	getEle:function(node){
		var str = "";
		for ( var i = 0; i < node.childNodes.length; i++) {
			if (node.childNodes[i].name != ""
					&& node.childNodes[i].name != undefined)
				str = lt.AppendStr(str, lt.getFormHashMap(node.childNodes[i]));
			if (node.childNodes[i].hasChildNodes()) {
				str += lt.getEle(node.childNodes[i]);
			}
		}
		return str;
	},
	// 返回键值对字串 key:'value'
	getFormHashMap:function(node){
		var str = "'" + node.name + "'" + ":";
		var v = "";
		if (node.tagName == "INPUT") {
			v = node.value;
			if (node.type == "checkbox" || node.type == "radio") {
				if (node.checked)
					v = node.value;
				else
					v = "";
			}
		}
		if (node.tagName == "TEXTAREA") {
			v = node.value;
		}
		if (node.tagName == "SELECT") {
			for ( var i = 0; i < node.options.length; i++) {
				if (node.options[i].selected) {
					v = v == "" ? node.options[i].value : v + ","
							+ node.options[i].value;
				}
			}

		}
		str += "'" + lt.TransSQM(v) + "'";
		return str;
	},
	// 单引号转义，目前只发现录入单引号时有问题(还有回车换行)
	TransSQM:function(str){
		return str.replace(/\'/g, "\\\'").replace(/\n/g,"\\\n").replace(/\r/g,"\\\r");
	},
	// 将键值对拼接到str上，主要针对checkbox的多个值拼接操作
	AppendStr:function(str, v) {
		var f = true;
		var s = "";
		var e1 = str.split(",");
		var e2 = v.split(":");

		for ( var i = 0; i < e1.length; i++) {
			if (s == "")
				s = e1[i];
			else
				s += "," + e1[i];
			if (e1[i].split(":")[0] == e2[0]) {
				if (e2[1] != "''")
					s = s.substring(0, s.length - 1) + "," + e2[1].substring(1);
				f = false;
			}
		}
		if (f) {
			s = str + "," + v;
		}
		return s;
	},
	//liutong 页面中创建一个form并提交，取第一个form中的参数放到创建的form中一并提交
	//2014-7-21修改为获取全部form参数数据
    formPost:function(url){
		//var params=lt.getData();
    	var params=lt.getBodyData();
		var fm = document.createElement("form");
		document.body.appendChild(fm);
		fm.action = url;
		fm.method = "POST";
		for (var key in params){
			var it = document.createElement("input");
			it.type="hidden";
			it.name=key;
			it.value=params[key];
			fm.appendChild(it);
		}
		fm.submit();
		document.body.removeChild(fm);
	},
	//分页创建form提交，将页面中所有form内容提交
	pagePost:function(url,querystr){
		var params=lt.getAllData();
		var fm = document.createElement("form");
		document.body.appendChild(fm);
		fm.action = url;
		fm.method = "POST";
		
		for (var key in params){
			var it = document.createElement("input");
			it.type="hidden";
			it.name=key;
			it.value=params[key];
			fm.appendChild(it);
		}
		var query = querystr.split("&");
		for(var i=0;i<query.length;i++){
			var it = document.createElement("input");
			it.type="hidden";
			it.name=query[i].split("=")[0];
			it.value=query[i].split("=")[1];
			fm.appendChild(it);
		}
		
		
		fm.submit();
	},
	//异步搜索，搜索按钮直接onclick="lt.searchgrid('grid id')"
	searchgrid:function(gridname,formname){
		grid.reload(gridname,lt.getData(formname));
	},
	delitem:function(url,id,grid){
		$.messager.confirm('警告', '确定要删除吗?', function(r) {
			if (r) {
				ajax(url, "id=" + id, function() {
					//grid.reload(grid);
					$('#'+grid).datagrid('reload');
				});
			}
		});
	},
	delitems:function(url,grid){
		var ids = lt.getcheckedids(grid);
		
		if (ids == "") {
			$.messager.alert("提示信息", "请选择要删除的项");
		} else {
			lt.delitem(url,ids,grid);
		}
	},
	getcheckedids:function(domid){
		var items=$('#'+domid).datagrid('getChecked');
		if(items.length<=0){
			return "";
		}else{
		    var ids = [];
	        $.each(items, function(index, item){
	           ids.push(item.id);
	        });
	        return ids.join(",");
		}
	},
	alert:function(message){
		lt.topWin().$.messager.alert('提示',message,'warning');
	},
	topWin:function(){
		var topwin=window.self;
		while(topwin.top!=topwin.self){
				topwin=topwin.window.parent;
		}
		return topwin;
	}
}
/////////////////////////////////////////////////////////////////////
function ajax(url, data, successMethod, beforeMethod, isMessage,formname) {
	lt.ajax(url, data, successMethod, beforeMethod, isMessage,formname);
}
function ajax2(url,successMethod) {
	lt.ajax(url, null, successMethod, null, "y",null);
}
// 获取form中数据，生成json数据，formname为空时则用document中第一个form
function getdata(formname){return lt.getBodyData();}
function getData(formname) {return lt.getBodyData();}
function gourl(url){window.location=url}
//panel控制
var pl={
       plhref:function(domid,url){
	      $("#"+domid).panel({href:url}); 
       }
}
//消息控制
var msg={
		show:function(title,content,fontcolor){
			if(fontcolor){
				content="<font color='"+fontcolor+"'>"+content+"</font>";
			}
			$.messager.show({
				title : title,
				msg : content
			});
		}
}
//window控制
var win={
		open:function(domid,url){
			$('#'+domid).window('open');
			$('#'+domid).window('refresh', url);
		},
        close:function(domid){
	        $('#'+domid).window('close');
        }
}
//dialog控制
var dl={
		open:function(domid,url){
			$('#'+domid).dialog('open');
			$('#'+domid).dialog('refresh', url);
		},
        close:function(domid){
	        $('#'+domid).dialog('close');
        },
        openDeal:function(url,config,target){
        	$("#dlg-buttons_save_").unbind(); 
        	if(config){
        		if(config.width){
	        		var bwidth=document.body.clientWidth;
	        		var dwidth=config.width;
	        		config.left=(bwidth-dwidth)/2;
        		}
	        	if(config.saveSuccessMethod){
	        		$("#dlg-buttons_save_").click(function(){
	        			dialogSave_(config.saveSuccessMethod,target);
	        		});
	        	}else{
	        		$("#dlg-buttons_save_").click(function(){
	        			dialogSave_(null,target);
	        		});
	        	}
        	   $('#dialog_').dialog(config);
        	}else{
        		$("#dlg-buttons_save_").click(function(){
        			dialogSave_(null,target);
        		});
        	}
        	$('#dialog_').dialog('open');
        	$('#dialogFrame_').attr("src",url);
        }
}
var grid={
		load:function(domid,data){
			if(data==undefined || data=="")
				$('#'+domid).datagrid('load', getData());
			else
			$('#'+domid).datagrid('load', data);
		},
		reload:function(domid,data){
			if(data==undefined || data=="")
				$('#'+domid).datagrid('reload', getData());
			else
			$('#'+domid).datagrid('reload', data);
		},
		getchecked:function(domid){
			$('#'+domid).datagrid('getChecked');
		},
		getcheckedid:function(domid){
			var items=$('#'+domid).datagrid('getChecked');
		    var ids = [];
	        $.each(items, function(index, item){
	           ids.push(item.id);
	        });
	        return ids;
		},
		getcheckedids:function(domid){
			var items=$('#'+domid).datagrid('getChecked');
			if(items.length<=0){
				return "";
			}else{
			    var ids = [];
		        $.each(items, function(index, item){
		           ids.push(item.id);
		        });
		        return ids.join(",");
			}
		},
		pageOptions:function(domid){
			var gdname="grid";
			if(domid!=undefined && domid!=""){
				gdname=domid;
			}
			return $('#'+gdname).datagrid('getPager').data("pagination").options;
		}
}
//liutong
//页面加载前mask，防止看到easyui的css加载上之前的画面乱
document.write("<div id='flashmask' style='background:#eee; position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:998;text-align: center;'>");
document.write("<div style='background:#fff; padding:5px;width:120px;font-size:12px; margin:auto; margin-top:"+((document.documentElement.clientHeight/2)-15)+"px'><table align='center'><tr><td><div class='loading'></div></td><td>&nbsp;页面加载中...</td></tr></table></div>");
document.write("</div>");
window.onload=function(){
	setTimeout(function(){
		document.getElementById("flashmask").style.display="none";
	    }, 500);	
	}