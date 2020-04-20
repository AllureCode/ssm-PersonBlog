<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
	import="java.io.File"
    pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");

	String rootPath = application.getRealPath( "/" );
	//String rootPath=request.getServletPath();

	out.write( new ActionEnter( request, rootPath ).exec() );
//	request.setCharacterEncoding( "utf-8" );
//	response.setHeader("Content-Type" , "text/html");
//	String rootPath = application.getRealPath( "/" );
//	String action = request.getParameter("action");
//	String result = new ActionEnter( request, rootPath ).exec();
//	//在下面判断如果是列出文件或图片的，替换物理路径的“/”
//	if( action!=null && (action.equals("listfile") || action.equals("listimage") ) ){
//		rootPath = rootPath.replace("\\", "/");
//		result = result.replaceAll(rootPath, "/");
//	}
//	out.write( result );
	
%>