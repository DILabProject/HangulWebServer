<%@page import="di.cs.skuniv.model.HangulVO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Gson gson=new Gson();
	String str_HangulVO=(String)request.getAttribute("str_HangulVO");
	HangulVO hangulVO=gson.fromJson(str_HangulVO, HangulVO.class);
	
	System.out.println(hangulVO.getHangul_map_list().get(0).get("cho").toString());
%>
<h1>
완료 되었습니다.
</h1>
</body>
</html>