<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>success</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
  <h2 style="color: red;"> ${message}</h2>
  <c:if test="${flag==1 }">
  	<img alt="excel样式" src="${pageContext.request.contextPath }/images/excel.png"/>
  </c:if>
  <br><br>
  
 
 	<input type="button" value="返回" style="width:50px;" class="myBtn"
														onclick="location.href='javascript:window.history.back()'" />
  </body>
</html>
