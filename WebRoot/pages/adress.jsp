<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adress.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="cart/addAdress.do"  method="post">
    <table cellpadding="5"  style="margin-left:75px;" >
	                 <tr>
   				       <td class="words">收货人姓名：</td> 
   				       <td><input data-options="required:true"  class="easyui-textbox"  style="width:400px;height:30px;"  name="name"  type="text"  id="name"  /></td>
	                 </tr>
	                  
	                 <tr>
   				       <td class="words">详细地址：</td> 
   				       <td><textarea  data-options="required:true" class="easyui-textbox"  style="width:400px;height:50px;"  name="address"  type="text"  id="address"  ></textarea></td>
	                 </tr>
	      </table>
	      <button type="sumbit" value="提交" />
	      
	      
	      </form>
  </body>
</html>
