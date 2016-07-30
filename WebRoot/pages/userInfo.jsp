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
    
    <title>个人信息</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	
	 <link rel="stylesheet" type="text/css" href="css/loginstyle.css">
<link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="css/list.css"/> 
	<link rel="stylesheet" type="text/css" href="css/common.css"/> 
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="css/merchandise.css"/> 	
   
    <link rel="stylesheet"  type="text/css"  href="css/jquery.autocomplete.css"/>
    
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css">
      <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
     <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script> 
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
    <script type="text/javascript" src="js/common.js"></script>   
    
	<script type="text/javascript">
	$(document).ready(function($){
        var male=$("#male").val();
        var female=$("#female").val();
        var sex=$("#sex").val();
        if(sex==male){
        	$("#male").attr("checked","checked");
        }
        else if(sex==female){
        	
        	$("#female").attr("checked","checked");
        	
        };
	});
	
	function submitForm() {
		
		
		var flag=$('#ff').form('enableValidation').form('validate');

			return flag;

		}
	</script>
	
  </head>
  
  <body>
            <!---top--->
        <jsp:include page="/pages/toper.jsp"/>
        <!--top end-->
        <!--head-->
        <div id="head">
        <jsp:include page="/pages/head.jsp"/>
        <!--head end-->
         
           
           
           <!--logo end-->
           <!--nav start-->
           <div class="nav-box">
              <div class="nav">
              <ul class="top-nav">
                <li class="item1">
                  <a href="good/lookAll.do">全部商品</a>
                </li>
                 <li class="item2">
                  <a href="login/queryAllContent.do">首页</a>
                </li>
                 <li class="item3">
                  <a href="login/queryAllContent.do">积分特惠</a>
                </li>
                 <li class="item4">
                  <a href="login/queryAllContent.do">品牌特惠</a>
                </li>
              </ul>
              </div>
        
           </div>
           <!--nav end-->
        </div>
        <!--head end-->
        <div class="main-body">
  			<div class="my-menu">
				<div class="m-box">
					<div class="m-box_1">
						<div class="userCenterBox">
							<div class="userMenu">
								<ul class="my-ul">
									<li class="m-li">
									<a  class="curs" href="user/userInfo.do">
									个人中心
									</a>
									</li>
									<!-- <li class="m-li">
									<a href="user/userInfo.do">
									用户信息
									</a>
									</li> -->
									<li class="m-li ">
									<a class="" href="order/myorder.do">
									我的订单
									</a>
									</li>
									<li class="m-li">
									<a href="javascript:void(0);">
									收货地址
									</a>
									</li>
									<li class="m-li">
									<a  href="good/myFavorite.do">
									我的收藏
									</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
   
			<div class="main-center">

            	<div id="divMain">
              
	               <div style="margin-bottom:10px;">
	                  <h3>基本信息 ：</h3>   
	               </div>
           
                	 <div class="login-form" style="width:550px;margin:0px auto;margin-top:50px;" >
   
  
   
   <form  id="ff" action="user/updateInfo.do" method="post">
   
      <input  type="hidden" value="${user.sex}" id="sex"></input>
      <table class="table">
		  
          <tr>
				<td class="td-lable v">昵称:</td>
				<td  class="td-input"align=left><input class="easyui-textbox" type="text" value="${user.nickname}"
					name="nickname" id="nickname"
					data-options="required:true" 
                   style="width:200px;height:30px" />
                   <span style="color:red;">*</span>
				</td>
			</tr>

			<tr>
				<td class="td-lable">手机号:</td>
				<td  class="td-input"align=left>${user.telephone}<%-- <input class="easyui-textbox"  type="" value="${user.telephone}"
					name="telephone" id="telephone"
					data-options="required:true" 
                   style="width:200px;height:30px" /> --%>
                   <span style="color:red;">已验证，如需修改请联系管理员</span>
				</td>
			</tr>

           <tr>
				<td class="td-lable">性别:</td>
				<td class="td-input" align=left>
				
				<label><input id="male" name="sex" type="radio" value="1" />男 </label> 
                <label style="margin-left:30px;"><input id="female" name="sex" type="radio" value="0" />女 </label> 
				
                   
				</td>
			</tr>
  
              <tr>
	    			<td class="td-lable" width=150px; align="right">电子邮件:</td>
	    			<td class="td-input"><input class="easyui-textbox" type="text" value="${user.email}" name="email" data-options="required:true,validType:'email',missingMessage:'请输入邮箱地址！'" style="width:220px;height:25px;"></input>
	    			<span style="color:red;">*</span>
	    			</td>
	    		</tr>
	    		 <tr>
	    			<td class="td-lable" width=150px; align="right">职业:</td>
	    			<td class="td-input"><input class="easyui-textbox" type="text" value="${user.career }" name="career" data-options="missingMessage:'请输入职业！'" style="width:220px;height:25px;"></input>
	    			<span style="color:red;"></span>
	    			</td>
	    		</tr>


               <tr>
	    			<td class="td-lable" width=150px; align="right">出生日期:</td>
	    			<td class="td-input"><input class="easyui-datebox" type="text" value="${user.birthday}" name="birthday" editable="false" data-options="required:true,missingMessage:'请输入日期！'" style="width:220px;height:25px;"></input>
	    			<span style="color:red;">*</span>
	    			</td>
	    		</tr>


			<tr>
				<!-- <td  style="border:none; "colspan="" align=center>
				    
				    	<input type="submit" class="btn btn-primary" value="修改"  />
				    
				    
				</td>
				
				<td  style="border:none;"colspan="" align=center>
				    
				    
						<input type="reset" class="btn btn-primary" value="取消" />
						
                   
				</td> -->
				<td  style="border:none; padding-top:30px;"colspan="2" align=center>
					<input type="submit" class="btn btn-primary" value="提交" style="margin-left:230px;" onclick="return submitForm()" />
					
				</td>
			</tr>

		</table>

   
   
   </form>
   
  
   
   </div>
               
            
               
               
            </div>
            <!--page start-->
           
           
           
           
           </div>
          
           
           
           
           
           
           
        </div>
         <!--main-body end-->
        <div style="clear:both"></div>
        <!--footer start-->
         <jsp:include page="/pages/footer.jsp"/>
        <!--footer end-->
  </body>
</html>
