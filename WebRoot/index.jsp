<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
   <title>易划</title>
   
   <link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="js/bootstrap/dist/css/bootstrap-table.css">
	
	<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.4.1/themes/icon.css">
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>	
	<style type="text/css">
	

	
	
body{

	margin:0;
	padding:0;
	
	font-size:12px;
	font-family:"宋体","微软雅黑";
	color:#646464;
	
    }
    
    #mypage{
		 width:400px;
		 height:42px;
		 margin: 0px auto;
		
		 margin-top:300px;
		 }
    
	
	</style>
	
	<script type="text/javascript">
	
	
/* 	$(function(){
 

  $.extend($.fn.validatebox.defaults.rules,{  
               mobile:{  
                   validator:function(value,param){  
                       return /^1[3|5|7|8|][0-9]{9}$/.test(value);  
                   },  
                   message:'请输入正确的11位手机号码.'  
               }
           });  
 });
	
	
	function submitForm() {
		return $('#ff').form('enableValidation').form('validate');

	}; */
	
	</script>
	
	
  </head>
  
  <body>


<!-- <div id="mypage">

<form id="ff" action="login/login.do" method="post">
	<input class="easyui-numberbox" type="text" name="phonenum"  id="phonenum"  data-options="required:true,validType:'mobile',missingMessage:'请输入手机号！'" style="width:200px;height:35px;border:1px solid #999999;line-height:35px;"/>

<input type="submit" class="btn btn-primary" value="进入商城"  onclick="return submitForm()"/>
				
</form>
</div> -->

 <jsp:forward page="/pages/login.jsp" />


  </body>
</html>
