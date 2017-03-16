<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="p" type="com.frame.common.page.Pagination" required="true"%>
<%@ attribute name="tableid" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="false"%>

<input type="hidden" name="page" id="page" value="${page.getPageNo()}">
<input type="hidden" name="rows" id="rows" value="${page.getPageSize()}">
<input type="hidden" name="sort" id="sort" value="${sort }">
<input type="hidden" name="order" id="order" value="${order }">
<script type="text/javascript">
var url_1='${url}';
if(url_1==""){
	url_1='${currentPath}';
}
	$(function(){
		
	    $('#${tableid}').datagrid({
	    	sortName:'${sort}',
	    	sortOrder:'${order}',
	    	onBeforeSortColumn: function(sort,order){
	    		$("#sort").val(sort);
	    		$("#order").val(order);
	    		lt.formPost(url_1);
	    	}
	    });
		
	    var pager = $('#${tableid}').datagrid().datagrid('getPager');
	    pager.pagination({
	    	total:${page.getTotalCount()},
	    	pageNumber:${page.getPageNo()},
	    	onSelectPage: function(pageNumber, pageSize){
                $("#page").val(pageNumber+"");
                $("#rows").val(pageSize+"");
                lt.formPost(url_1);
            }
	    });
	    

	})
</script>