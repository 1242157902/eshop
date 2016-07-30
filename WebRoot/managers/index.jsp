<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="js/bootstrap/dist/css/bootstrap.min.css">
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
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


<script type="text/javascript" >
	function submitForm() {
		return $('#ff').form('enableValidation').form('validate');

	}
</script>
  </head>
  
  <body>
   
   <div style="width:600px;height:300px;margin:0px auto;margin-top:200px;">
   
   <h2>管理员登录:</h2>
   
   <form  id="ff" action="admin/adminLogin.do" method="post">
   
   <table class="table" >
   			<tr>
				<td align="right">单位名:</td>
				<td align="center">
				<select id="company" class="easyui-combobox" name="company" style="width:200px;height:30px">
					<option value="">请选择</option>     
				    <option value="100002">迪信通公司</option>   
				    <option value="100001">北方工大</option>   
				    <option value="默认">其它</option>  
				</select> 
				</td>
			</tr>
			<tr>
				<td align="right">用户名:</td>
				<td align="center"><input class="easyui-textbox" type="text"
					name="telephone" id="telephone" data-options="required:true"
					style="width:200px;height:30px" />
				</td>
			</tr>
			<tr >
				<td  align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
				<td align="center">
				<input class="easyui-textbox" type="password"
					name="password" id="password"
					data-options="required:true" 
                    style="width:200px;height:30px" />
				</td>
			</tr>
		
			<tr>
				<td colspan="2" align=center>
				<input type="submit" class="btn btn-primary" value="确认" onclick="return submitForm()" />
				<input type="reset" class="btn btn-primary" value="取消" /></td>
			</tr>

		</table>

   
   
   </form>
   
   ${requestScope.errmsg}
   
   </div>
   
   
   
   
   
  </body>
</html>
