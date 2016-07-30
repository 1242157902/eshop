<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>北方工业大学积分——商城</title>
    

  </head>
  
  <body>
    <%-- <c:redirect url="main/queryAllContent.do"/> --%>
    <jsp:forward page="main/queryAllContent.do"/>
    
  </body>
</html>
