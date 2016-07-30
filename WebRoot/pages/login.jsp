<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" type="text/css" href="css/loginstyle.css">	
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	
	
	<script type="text/javascript" >
	
 	//失去焦点事件
	function myFunction()
	{
 		var telephone = $("#telephone").val();
 		if(telephone=="")
 		 {
 			return ;
 		 }else
 			 {
 			 	if(/^1[3|5|7|8|][0-9]{9}$/.test(telephone))
		 		{
			 	   location.href = "login/login.do?phonenum="+telephone;
			 	  document.getElementById("password").focus();
		 		}
 			 	else
 			 	{
 			 		$("#telephone").val("");
 			 		document.getElementById("telephone").focus();
 			 		alert("手机号格式不正确！");
					history.go(0);
 			 		//return ;
 			 	}
 			 }
	}
	
	
	
 	$(function(){
 		
 		
  		var telephone = $("#telephone").val();
	 		if(telephone!="")
			{
	 			document.getElementById("password").focus();
			}
	 		else
			{
	 			document.getElementById("telephone").focus();
			} 
 		
 		 
 	    $("input",$("#telephone").next("span")).blur(function(){  
	 	    	var telephone = $("#telephone").val();
	 		    location.href = "login/login.do?phonenum="+telephone;
 	    })  ;
 	    
 	    
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

	} 
</script>
  </head>
  
  <body>
    <div class="w">
       <div id="logo">
          <a href="#">
             <img width="" height="70px" alt="迪信通礼品商城" src="images/welcome-pic.jpg" />
          </a>
          
       </div>
    </div>
    
    
    <div id="content">
      
    <div class="login-wrap">
        <div class="w">
            <div class="login-form">
                <div class="login-box">
                    <div class="mt">
                        <h1>会员登录</h1>
                        <!-- <div class="extra-r">
                            <div class="regist-link">
                              <a  target="_blank" href="#"><b></b>立即注册</a></div>
                        </div> -->
                    </div>
                   
                    <div class="mc">
                        <div class="form">
                            <form id="ff" action="login/userlogin.do"  method="post" >
                               
                                 <div class="item item-fore1">
                                    <label class="login-label name-label" for="loginname"></label>
                                    <input type="text" value="${requestScope.phonenum}"
					name="telephone" id="telephone"  
					  onblur="myFunction()" required="required" placeholder="手机" value="" autocomplete="off" tabindex="1"  class="itxt" id="loginname">
                                    
                                </div>
                                <div class="item item-fore2" id="entry">
                                    <label for="nloginpwd" style="background-position: -48px 0" class="login-label pwd-label"></label>
                                    
                                    <input type="password"  name="password" id="password"
					required="required"   placeholder="密码" autocomplete="off"  class="itxt itxt-error"  >
                                    
                                   
                                </div>
                                <div class="item item-fore3">
                                    <div class="safe">
                                       
                                         <span class="forget-pw-safe">
                                            <a target="_blank" class="" href="#">忘记密码?</a>
                                        </span>
                                    </div>
                                    
                                </div> 
                                <div class="item item-fore5">
                                    <div class="login-btn">
                                        <input type="submit"
					 value="登录" onclick="return submitForm()"   class="btn-img btn-entry" />
                                    </div>
                                </div>
                            </form>
                            ${requestScope.errmsg}
                        </div> 
                   </div>
                
              </div>
           </div>
        </div>
        
        <div class="login-banner" style="background-color: #654d89">
            <div class="w">
              <div id="banner-bg" class="i-inner" style="background:url(images/login-pic.jpg) 0px 0px no-repeat;background-color: #654d89" ></div>
            </div>
         </div>
        
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
   
    <%-- <div style="width:600px;height:300px;margin:0px auto;margin-top:200px;">
   
   <h2>用户登录:</h2>
   
   <form  id="ff" action="login/userlogin.do" method="post">
   
   <table class="table">

			<tr>
				<td>请输入手机号:</td>
		<!-- 		<td align=left><input class="easyui-numberbox" type="text"
					name="telephone" id="telephone" data-options="required:true,validType:'mobile',missingMessage:'请输入手机号！'" 
					style="width:200px;height:30px"  onblur="myFunction()" />
				</td> -->
				<td align=left><input  type="text"      value="${requestScope.phonenum}"
					name="telephone" id="telephone"  
					style="width:200px;height:30px"  onblur="myFunction()" />
				</td>
			</tr>
			<tr>
				<td>密码:</td>
				<td align=left><input  type="password"  
					name="password" id="password"
					data-options="required:true" 
                   style="width:200px;height:30px" />
				</td>
<!-- 				<td align=left><input class="easyui-textbox" type="password"
					name="password" id="password"
					data-options="required:true" 
                   style="width:200px;height:30px" />
				</td> -->
			</tr>

			<tr>
				<td colspan="2" align=center><input type="submit"
					class="btn btn-primary" value="确认" onclick="return submitForm()" />
					<input type="reset" class="btn btn-primary" value="取消" /></td>


			</tr>

		</table> 

   
   
   </form>
   
   ${requestScope.errmsg}
   
   </div> --%>
   
   
   
   
   
  </body>
</html>
