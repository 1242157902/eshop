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
    <title>订单成功</title>  
    <link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="css/common.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/merchandise.css"/>   
    <link rel="stylesheet"  type="text/css" href="css/orderSuccess.css"/>
    <link rel="stylesheet"  type="text/css"  href="css/jquery.autocomplete.css"/>  
    
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <!-- <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script> -->
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
   
   <!--  <script type="text/javascript" src="js/jquery.1.4.2.min.js"></script>    -->  
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
    <script type="text/javascript" src="js/common.js"></script>   
    
  </head>
  
  <body>
        <!---top--->
        <jsp:include page="/pages/toper.jsp"/>
        <!--top end-->
        <!--head-->
        <div id="head">
        
           <!--logo start-->
           <jsp:include page="/pages/head.jsp"></jsp:include>
           <!--logo end-->
           
        </div>
        <!--head end-->
        <div id="CarContent" class="pay-content">
        
            <!--carnav开始-->
            <div class="cartnav">
                <ul>
                <li ><a>我的购物车</a></li>
                <li ><a>核对订单信息</a></li>
                
                <li class="cartcurrent"><a>成功提交订单</a></li>
                </ul>
            </div>
            <!--carnav结束-->
            
            <div class="s-info">
               <div class="s-box">
                 <div class="s-t">
                   
                    <h3 class="gx">感谢您，订单提交成功！</h3>
                 </div>
                 <div class="s-detail">
                    <ul class="list-order">
                       <li class="li-st">
                          <div class="for1">
                             订单号：
                             <a href="javascript:void(0);">${order.order_id }</a>                          </div>
                          <div class="for2">
                             积分支付：
                             <strong class="zfjf" >${order.total }积分</strong>                          </div>
                             <div class="for2">
                             剩余积分：
                             <strong class="zfjf" >${totalscore }积分</strong>                          </div>
                          <div class="for3">
                          配送方式：<span>快递</span>                          </div>
                       </li>
                    </ul>
                 </div>
                 
                  <div class="s-more">
               完成支付后，您可以：
<!-- <a href="#">查看订单状态</a> -->
<a href="login/queryAllContent.do">继续购物</a>               </div>
            </div>
              
        </div>
            
       </div>
       <jsp:include page="/pages/footer.jsp"/>
  </body>
</html>
