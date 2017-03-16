
function addPanel(modulename, moduleid, url, opentab) {
	if (url != "") {
		var title = modulename;
		
		var isexist=false;
		var idx=-1;
		var tabs=$(".tabs").find("li");
		tabs.each(function(i,n){

			if($(n).attr("id")=="l_"+moduleid){
				isexist=true;
				idx=parseInt($(n).attr("index"));
			}
		});
		if(isexist){
			$('#tt').tabs('select', idx);
			if (opentab + "" == "refresh")
				refreshTab(idx, url);
		} else {
			$('#tt').tabs('add',
							{
								title : modulename,
								content : "<iframe style='height: 100%; width:100%'  frameborder='0' id='main' name='main' src='"+ url + "'></iframe>",
								closable : true
							});
			$(".tabs-last").find(".tabs-title").attr("id","s_"+moduleid);
			$(".tabs-last").find(".tabs-title").attr("mid",moduleid);
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$(".tabs-last").find(".tabs-title").attr("index",index);
			
			$(".tabs-last").attr("id","l_"+moduleid);
			$(".tabs-last").attr("index",index);
		}
	}
}
function closePanel(title,index){
	var currthis=$(".tabs").find("li[index='"+(index)+"']");
	var nextall = currthis.nextAll();
	if (nextall.length > 0) {
		nextall.each(function(i, n) {
			var idx =parseInt($(n).attr("index"))-1;
		    $(n).attr("index",idx+"");
		    $(n).find("span").attr("index",idx+"");
		});
	}
	return true;
}
function bindTabEvent() {
//	$(".tabs").on('dblclick',".tabs-inner", function() {
//		var subtitle = $(this).children("span").html();
//		if ($(this).next().is('.tabs-close')) {
//			$('#tt').tabs('close', subtitle);
//		}
//	});
	$(".tabs").on('contextmenu',".tabs-inner", function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		var subtitle = $(this).children("span").html();
		var submid = $(this).children("span").attr("mid");
		var subindex = $(this).children("span").attr("index");
		var nextisclose = $(this).next().is('.tabs-close');
		$('#mm').data("currtab", subtitle);
		$('#mm').data("currtabid", submid);
		$('#mm').data("indexid", subindex);
		$('#mm').data("nextisclose", nextisclose);
		$('#mm').data("currthis", $(this).parent());

		return false;
	});
}
// 绑定tab右键菜单事件
function bindTabMenuEvent() {
	// 关闭当前
	$('#mm-tabclose').click(
			
			
			function() {
				if ($('#mm').data("nextisclose")) {
					var nextall = $('#mm').data("currthis").nextAll();
					if (nextall.length > 0) {
						nextall.each(function(i, n) {
							var idx =parseInt($(n).attr("index"))-1;
						    $(n).attr("index",idx+"");
						    $(n).find("span").attr("index",idx+"");
						});
					}
					
					var currtab_title = $('#mm').data("currtab");
					var currtab_id = $('#mm').data("currtabid");
					var currtab_index=parseInt($('#mm').data("indexid"));
				
					$('#tt').tabs('close', currtab_index);
				}
			});
	// 全部关闭
	$('#mm-tabcloseall').click(
			function() {
				$('.tabs-inner span').each(
						function(i, n) {
							if ($(this).parent().next().is('.tabs-close')) {
								//var t = parseInt($(n).attr("index"));
								$('#tt').tabs('close',1);
							}
						});
				$('#mm').menu('hide');
			});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother')
			.click(
					function() {
						//关闭右边的tab
						var nextall = $('#mm').data("currthis").nextAll();
						if (nextall.length != 0) {
							var currtab_index = parseInt($('#mm').data("indexid")?$('#mm').data("indexid"):"0");
							nextall.each(function(i, n) {
								$('#tt').tabs('close',currtab_index+1);
							});
							$('#tt').tabs('select',currtab_index);
						}
						//关闭左边的tab
						var prevall = $('#mm').data("currthis").prevAll();
						if (prevall.length > 1) {
							prevall.each(function(i, n) {
								if ($('a.tabs-close', $(n)).length > 0) {
									$('#tt').tabs('close',1);
								}
							});
							var currtab = $('#mm').data("currthis");
							$(currtab).attr("index","1");
							$(currtab).find("span").attr("index","1");
							$('#tt').tabs('select',1);
						}
						
						$('#mm').menu('hide');
					});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(
			function() {
				//var nextall = $('.tabs-selected').nextAll();
				var nextall = $('#mm').data("currthis").nextAll();
				if (nextall.length == 0) {
					alert('已经是最后一个了');
					return false;
				}
				var currtab_index = parseInt($('#mm').data("indexid")?$('#mm').data("indexid"):"0");
				nextall.each(function(i, n) {
					$('#tt').tabs('close',currtab_index+1);
				});
				$('#tt').tabs('select',currtab_index);
				$('#mm').menu('hide');
				return false;
			});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(
			function() {
				// var prevall = $('.tabs-selected').prevAll();
				var prevall = $('#mm').data("currthis").prevAll();
				if (prevall.length == 1) {
					alert('已经是第一个了');
					return false;
				}
				prevall.each(function(i, n) {
					if ($('a.tabs-close', $(n)).length > 0) {
						$('#tt').tabs('close',1);
					}
				});
				var currtab = $('#mm').data("currthis");
				$(currtab).attr("index","1");
				$(currtab).find("span").attr("index","1");
				$('#tt').tabs('select',1);
				$('#mm').menu('hide');
				return false;
			});
}
function refreshTab(idx, url) {
	var refresh_tab = $('#tt').tabs('getTab', idx);
	if (refresh_tab && refresh_tab.find('iframe').length > 0) {
		var _refresh_ifram = refresh_tab.find('iframe')[0];
		var refresh_url = url;
		// _refresh_ifram.src = refresh_url;
		_refresh_ifram.contentWindow.location.href = refresh_url;
	}
}
function sysinit(){
	$('#theme').change(
			function() {
				var t=$(this).val().split("@");
				window.location="admin/theme?theme="+t[0]+"&backcolor="+t[1];
//				ajax("system/themeAction_settheme","theme="+$(this).attr("css"),function(result){
//					if(result=="success"){
//						window.location="main";
//					}
//				},null,false);
	});
	
	$("#layoutbtn").bind('click', function(e) {
		$('#layoutmm').menu('show', {
			left : e.pageX-30,
			top : e.pageY+10
		});
		return false;
	});
//	//修改密码
//	$('#mm-fixpassword').click(
//			function() {
//				addPanel("修改密码","xgmm","system/userAction_userpass.action");
//			});
//	//注销
//	$('#mm-zhuxiao').click(
//			function() {
//				window.location="loginAction_cancel.action";
//			});
//	//样式主题
//	$('.theme').click(
//			function() {
//				ajax("system/themeAction_settheme","theme="+$(this).attr("css"),function(result){
//					if(result=="success"){
//						window.location="main";
//					}
//				},null,false);
//			});
}

function menuExpand(flag){
   $("#menucontent")[0].contentWindow.expandall(flag);
}

$(function() {
	sysinit();
	bindTabEvent();
	bindTabMenuEvent();
	$('#tt').tabs({
		onBeforeClose:closePanel
		});
});