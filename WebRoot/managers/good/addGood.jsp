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

<script type="text/javascript">  
	$(function(){
		//异步获取商品分类信息；
		$.post("category/findAllCategory.do",
				null,function(data,textStatus){
					//返回的数据是JSON字符串；以下是处理JSON的方法
					
					var categorys=eval("("+data+")");
					
					for(var i=0;i<categorys.length;i++){
						var name=categorys[i].c_name;
						
						var $optionElement=$("<option value='"+name+"'>"+name+"</option>");
						$("#category").append($optionElement);
					}
				});
	});
</script>
  </head>
  
  <body>
     <!--   上传文件功能实现： -->
     <div style="width:100%;height:900px;overflow:scroll;overflow-y:hidden;">
     <form action="${pageContext.request.contextPath}/servlet/UploadServlet3" method="post" enctype="multipart/form-data">
     	<div style="text-align:center;font-size:20px">添加商品</div> <br/>
     	 <table  align="center" cellpadding=5% cellspacing=5%>
     	  <tr>
     		<td>商品名：</td>
     		<td><input class="easyui-validatebox" type="text" name="Goodname" data-options="required:true" /></td>
     	  </tr>	
     	  <tr>
     		<td>品    牌：</td>
     		<td><input class="easyui-validatebox" type="text" name="Brandname" data-options="required:true" /></td>
     	  </tr>
     	  <tr>
     	 	<td>商品规格：</td>
     	 	<td><input class="easyui-validatebox" type="text" name="guige" data-options="required:true" /></td>
     	  </tr>
     	  <tr>
     		<td>商品分类：</td>
     		<td>
     		 <select id="category" name="category"  style="width:156px;border-color:blue">
    	 		<option value="">请选择</option>     
   			 </select>
   			</td> 
     	  </tr>
     	  <tr>
     		<td>描述：</td>
     		<td><input class="easyui-validatebox" type="text" name="description" data-options="required:true" /></td>
     	  </tr>
     	  <tr>
     		<td>是否为虚拟商品：</td>
     		<td>是<input type="radio" name="isvisual" value="true"/>
     		            否<input type="radio"  name="isvisual" value="false"/></td>
     	  </tr>
     	  <tr>
     		<td>商品原价：</td>
     		<td><input type="text" name="price" class="easyui-numberbox" data-options="min:0,precision:2"/></td>
     	  </tr>
     	  <tr>
     		<td>积分：</td>
     		<td><input type="text" name="Nowprice" class="easyui-numberbox" data-options="min:0,precision:2"/></td>
     	  </tr> 
     	  <tr>
     	    <td>是否上架：</td>
     	    <td>是<input type="radio" name="isSell" value="true"/>
     	                        否<input type="radio" name="isSell" value="false"/>
     	    </td>
     	  </tr>
     	  <tr>
     		<td>首页大图片(190*190)：</td>
     		<td><input type="file" name="file1"/></td>
     	  </tr>
     	   <tr>
     		<td>首页小图片(110*110)：</td>
     		<td><input type="file" name="file2"/></td>
     	  </tr>
     	   <tr>
     		<td>详细页大图片：</td>
     		<td><input type="file" name="file3"/></td>
     	  </tr>
     	   <tr>
     		<td>类别显示图片(180*180)：</td>
     		<td><input type="file" name="file4"/></td>
     	  </tr>
     	  <tr>
     		<td colspan="2" style="text-align: center">
     		<input id="tianjia" type="submit" value="添加" style="width:150px;height:35px;">
     		</td>
     	  </tr>
     	</table>
     </form>
     </div>
  </body>
</html>
