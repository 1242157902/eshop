<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>新用户注册</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/loginstyle.css">	
	<link rel="stylesheet" href="js/bootstrap/dist/css/bootstrap.min.css">
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css">
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	
	
	<script type="text/javascript" >
	
	
	function submitForm() {
	  var pas1=$("#password").val();
	  var pas2=$("#password2").val();
	  
	  if(pas1!=pas2)
	  {
	  
	  $("#xxxxx").html();
	  
	  $("#xxxxx").html("两次密码不一致！");
	  
	       return  false;
	  }
	
	var flag=$('#ff').form('enableValidation').form('validate');
	
	  
	
		return flag;

	}
</script>

  </head>
  
  <body>
  
  <div class="w">
       <div id="logo">
          <a href="#">
             <img width="" height="" alt="迪信通礼品商城" src="images/logo.png" />
          </a>
          <b>迪信通礼品商城</b>
       </div>
    </div>
    
  <div style="height=475px; background: #f2f2f2 none repeat scroll 0 0;">
  
  <div class="login-form" style="width:550px;margin:0px auto;margin-top:100px;" >
   
   <h2>用户注册:</h2>
   
   <form  id="ff" action="login/register.do" method="post">
   <input  type="hidden" name="phonenum" value="${requestScope.phonenum}"/>
   <input  type="hidden" name="flag" value="${requestScope.flag}"/>
  
   <table class="table">

   

		
			<tr>
				<td class="td-lable">请输入密码:</td>
				<td class="td-input" align=left><input class="easyui-textbox" type="password"
					name="password" id="password" data-options="required:true"
					style="width:200px;height:30px" />
					<span style="color:red;">*</span>
				</td>
			</tr>
			
			<tr>
				<td class="td-lable">确认密码:</td>
				<td class="td-input" align=left><input class="easyui-textbox" type="password"
					name="password2" id="password2"
					data-options="required:true" 
                   style="width:200px;height:30px" />
                   <span style="color:red;" id="xxxxx">*</span>
				</td>
			</tr>



          <tr>
				<td class="td-lable">昵称:</td>
				<td  class="td-input"align=left><input class="easyui-textbox" type="text"
					name="nickname" id="nickname"
					data-options="required:true" 
                   style="width:200px;height:30px" />
                   <span style="color:red;">*</span>
				</td>
			</tr>



           <tr>
				<td class="td-lable">性别:</td>
				<td class="td-input" align=left>
				
				<label><input name="sex" type="radio" value="1" />男 </label> 
                <label style="margin-left:30px;"><input name="sex" type="radio" value="0" />女 </label> 
				
                   
				</td>
			</tr>
  
              <tr>
	    			<td class="td-lable" width=150px; align="right">电子邮件:</td>
	    			<td class="td-input"><input class="easyui-textbox" type="text" name="email" data-options="required:true,validType:'email',missingMessage:'请输入邮箱地址！'" style="width:220px;height:25px;"></input>
	    			<span style="color:red;">*</span>
	    			</td>
	    		</tr>


               <tr>
	    			<td class="td-lable" width=150px; align="right">出生日期:</td>
	    			<td class="td-input"><input class="easyui-datebox" type="text" name="birthday" editable="false" data-options="required:true,missingMessage:'请输入日期！'" style="width:220px;height:25px;"></input>
	    			<span style="color:red;">*</span>
	    			</td>
	    		</tr>


			<tr>
				<td  style="border:none;"colspan="2" align=center><input type="submit"
					class="btn btn-primary" value="确认" onclick="return submitForm()" />
					<input type="reset" class="btn btn-primary" value="取消" /></td>


			</tr>

		</table>

   
   
   </form>
   
  
   
   </div>
  </div>  
  
  
  <div class="w">
<div id="footer-2013">
<!-- <div class="links">
<a href="#" target="_blank" rel="nofollow"> 关于我们 </a>
|
<a href="#" target="_blank" rel="nofollow"> 联系我们 </a>
|
<a href="#" target="_blank" rel="nofollow"> 人才招聘 </a>
|
<a href="#" target="_blank" rel="nofollow"> 商家入驻 </a>
|
<a href="#" target="_blank" rel="nofollow"> 广告服务 </a>
|
<a href="#" target="_blank"> 友情链接 </a>


</div> -->
<div class="copyright"> Copyright©2015  北方工业大学数据库实验室版权所有 </div>
</div>
</div>  
  
  
 
  
  </body>
</html>
