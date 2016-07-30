<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stock.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/demo/demo.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/easyloader.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>  


</head>
  
  <body>
     <!--   上传商品图片功能实现： -->
     <div style="width:100%;height:900px;overflow:scroll;overflow-y:hidden;">
     <form action="${pageContext.request.contextPath}/servlet/PicUploadServlet" method="post" enctype="multipart/form-data">
     	<div style="text-align:center;font-size:20px">上传商品详细页图片</div> <br/>
     	 <table  align="center" cellpadding=5% cellspacing=5%>
     	  <tr>
     		<td>商品编号：</td>
     		<td><input class="easyui-validatebox" type="text" value="<%=request.getParameter("good_id")%>" name="GoodID" data-options="required:true" /></td>
     	  </tr>
     	  <tr>
     		<td>商品图片名称：</td>
     		<td><input class="easyui-validatebox" type="text" name="Picname" data-options="required:true" /></td>
     	  </tr>	
     	  <tr>
     		<td>中图片1(350*350):</td>
     		<td><input type="file" name="file1"/></td>
     	  </tr>
     	  <tr>
     		<td>中图片2(350*350):</td>
     		<td><input type="file" name="file2"/></td>
     	  </tr>
     	  <tr>
     		<td>中图片3(350x350):</td>
     		<td><input type="file" name="file3"/></td>
     	  </tr>
     	  <tr>
     		<td>中图片4(350*350):</td>
     		<td><input type="file" name="file4"/></td>
     	  </tr>
     	  <tr>
     		<td>中图片5(350*350):</td>
     		<td><input type="file" name="file5"/></td>
     	  </tr>
     	  <tr>
     		<td>小图片1(50*50):</td>
     		<td><input type="file" name="file6"/></td>
     	  </tr>
     	   <tr>
     		<td>小图片2(50*50):</td>
     		<td><input type="file" name="file7"/></td>
     	  </tr>
     	  <tr>
     		<td>小图片3(50*50):</td>
     		<td><input type="file" name="file8"/></td>
     	  </tr>
     	  <tr>
     		<td>小图片4(50*50):</td>
     		<td><input type="file" name="file9"/></td>
     	  </tr>
     	  <tr>
     		<td>小图片5(50*50):</td>
     		<td><input type="file" name="file9"/></td>
     	  </tr>
     	  <!--  <tr>
     		<td>显示顺序：</td>
     		<td><input type="text" name="order"/></td>
     	  </tr> -->
     	  <tr>
     		<td colspan="2" style="text-align: center">
     		<input type="submit" value="添加" style="width:150px;height:35px;">
     		</td>
     	  </tr>
     	</table>
     </form>
     </div>
  </body>
</html>
