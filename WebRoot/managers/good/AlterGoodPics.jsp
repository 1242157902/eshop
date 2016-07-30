<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
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
     <!-- 修改商品图片 -->
     <div style="width:100%;height:900px;overflow:scroll;overflow-y:hidden;">
     <form action="${pageContext.request.contextPath}/servlet/AlterGoodPicServlet" method="post" enctype="multipart/form-data">
     	<div style="text-align:center;font-size:20px">修改商品图片</div> <br/>
     	 <table  align="center" cellpadding=5% cellspacing=5%>
     	   <tr>
     		<td>商品编号：</td>
     		<td><input class="easyui-validatebox" type="text" value="<%=request.getParameter("good_id")%>" name="Good_id" data-options="required:true" /></td>
     	  </tr>	
     	  <%-- <tr>
     		<td>商品名：</td>
     		<td><input class="easyui-validatebox" type="text" value="<%=request.getParameter("good_name")%>" name="Goodname" data-options="required:true" /></td>
     	  </tr>	 --%>
     		<td>首页大图片：</td>
     		<td><input type="file" name="file1"/></td>
     	  </tr>
     	   <tr>
     		<td>首页小图片：</td>
     		<td><input type="file" name="file2"/></td>
     	  </tr>
     	   <tr>
     		<td>详细页大图片：</td>
     		<td><input type="file" name="file3"/></td>
     	  </tr>
     	   <tr>
     		<td>类别显示图片：</td>
     		<td><input type="file" name="file4"/></td>
     	  </tr>
     	  <tr>
     		<td colspan="2" style="text-align: center">
     		<input id="tianjia" type="submit" value="修改" style="width:150px;height:35px;">
     		</td>
     	  </tr>
     	</table>
     </form>
     </div>
  </body>
</html>
