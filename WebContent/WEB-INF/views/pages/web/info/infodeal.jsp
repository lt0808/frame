<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>信息编辑</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="${ctxFront}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctxFront}/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctxFront}/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
    <form id="form1" class="easyui-form" action="${ctx }/web/info/save" method="post">
    <input type="hidden" name="id" value="${info.id }">
	<table class="datagrid-btable" width="100%">
	<tr>
	<td style="width:70px">所属栏目：</td>
	<td>
		<table cellpadding="0" cellspacing="0">
		<tr>
		<td><input name="typeid" class="easyui-combotree" value="${info.typeid }" data-options="url:'${ctx }/web/title/list',method:'get'"></td>

		<td>
		&nbsp;文章发布日期：
		</td>
		<td>
		<input name="recdate" class="easyui-datebox" style="width:100px" value="<fmt:formatDate value="${info.recdate==null?now:info.recdate}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />" required>
		</td>
		<td>&nbsp;是否置顶：</td>
		<td>
		   <input type="radio" name="topstaus" value="1" ${info.topstaus==1?'checked':'' }>
		</td>
		<td>是</td>
		<td>
		   <input type="radio" name="topstaus" value="0" ${info.topstaus==0||info.topstaus==null?'checked':'' }>
		</td>
		<td>否</td>
		</tr>
		</table>
	
	</td>
	</tr>
	<tr>
	<td>文章标题：</td>
	<td>
	    <table  cellpadding="0" cellspacing="0">
		<tr>
		<td>
	    <input name="title" class="easyui-textbox" style="width:500px" value="${info.title }" required/>
	    </td>
	    <td>&nbsp;
	            <select class="easyui-combobox" name="isshow" panelHeight="50px" style="width:70px;">
	                <option value="y" ${info.isshow=='y'?'selected':'' }>发布</option>
	                <option value="n" ${info.isshow=='n'?'selected':'' }>不发布</option>
	            </select></td>
	    </tr>
	    </table>
	</td>
	</tr>
	<tr>
	<td>文章摘要：</td>
	<td>
	    <table cellpadding="0" cellspacing="0">
		<tr>
		<td>
	    <input name="title2" class="easyui-textbox" style="width:300px" value="${info.title2 }" />
	    </td>
	    <td>&nbsp;作者：</td>
	    <td><input name="auther" class="easyui-textbox" style="width:80px" value="${info.auther }"/></td>
	    <td>&nbsp;来源：</td>
	    <td><input name="source" class="easyui-textbox" style="width:120px" value="${info.id==null?'青岛卫生人才市场':info.source }"/></td>
	    </tr>
	    </table>
	</td>
	</tr>
	<tr>
	<td>新闻图片：</td>
	<td>
	    <table cellpadding="0" cellspacing="0">
		<tr>
		<td>
	    <input class="easyui-textbox" id="picture" name="picturepath" style="width:300px" value="${info.picturepath }" /><a href="javascript:void(0);" onclick="upImage();">
	    </td>
	    <td style="padding-left: 5px">
	    <a href="javascript:void(0);" onclick="upImage();" class="easyui-linkbutton" data-options="iconCls:'icon-picture'">图片上传</a>
	    </td>
	    </tr>
	    </table>
	</td>
	</tr>
	<tr>
	<td colspan="2">
	<textarea id="editor" name="content" type="text/plain" style="width:100%;height:350px;">${info.content }</textarea>
	<script type="text/plain" id="upload_ue"></script>
	</td>
	</tr>
	
	</table>
		

	</form>
<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');
		
	var _editor;
	$(function() {
		_editor = UE.getEditor('upload_ue');
		_editor.ready(function () {
	        //设置编辑器不可用
	        _editor.setDisabled();
	        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
	        _editor.hide();
	        //侦听图片上传
	        _editor.addListener('beforeInsertImage', function (t, arg) {
	            //将地址赋值给相应的input,只去第一张图片的路径
	            $('#picture').textbox({value:arg[0].src});
	            //$("#picture").val(arg[0].src);
	            //图片预览
	            //$("#preview").attr("src", arg[0].src);
	        });
	     });

	});

	//弹出图片上传的对话框
	function upImage() {
	    var myImage = _editor.getDialog("insertimage");
	    myImage.open();
	}

</script>
</body>
</html>