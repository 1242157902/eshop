<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>广告上传</title>
   
	

  </head>
  
  <body style="text-align:center;background-color:#E0ECFF">
  <center>
    <h2 style="color:red;padding:20px;">您上传的图片名称必须为advertise-1.jpg,advertise-2.jpg,advertise-3.jpg,advertise-4.jpg,advertise-5.jpg,advertise-6.jpg图片大小最大为9M</h2>
    <div style="margin:0px auto;width:200px;">
       <form action="advertise/modifyImg.do"  method="post" enctype="multipart/form-data">
         <div><input type="file" name="img"/></div>
         <div style="margin-top:20px;float:left;"><input style="width:80px;height:30px;" type="submit" value="修改"/></div>
       </form>
    </div>
    </center>
  </body>
</html>
