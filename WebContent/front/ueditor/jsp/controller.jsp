<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
	String action = request.getParameter("action");  
	String result = new ActionEnter( request, rootPath ).exec();  

	if( action!=null &&   
	   (action.equals("listfile") || action.equals("listimage") ) ){  
	    rootPath = rootPath.replace("\\", "/");
	    String rootpath2="";
        for (int i = 0; i < rootPath.length(); i++){  
            int chr1 = (char) rootPath.charAt(i);  
            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)  
            	rootpath2+="\\u" + Integer.toHexString(chr1);  
            }else{  
            	rootpath2+=rootPath.charAt(i);  
            }  
        }  
       rootPath=rootpath2;
//	   System.out.println(rootPath);
//	   System.out.println(result);
	   result = result.replace(rootPath, "/");
	}  
//	System.out.println("result:"+result);
	out.write( result );  
	
%>