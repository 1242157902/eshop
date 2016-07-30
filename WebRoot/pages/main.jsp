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
    
    <title>
     <c:choose>
     	<c:when test="${sessionScope.user.company=='100001'}">
     		北方工大礼品商城主页
     	</c:when>
     	<c:when test="${sessionScope.user.company=='100002'}">
     		迪信通礼品商城主页
     	</c:when>
     	<c:otherwise>
     		默认礼品商城主页
     	</c:otherwise>
     </c:choose>
    
    
    
    </title>
    <link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet"  type="text/css"  href="css/jquery.autocomplete.css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">   
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script>    
    <script type="text/javascript" src="js/common.js"></script> 
    <script type="text/javascript" src="js/swiper-2.1.min.js"></script> 
    <script type="text/javascript" src="js/index.js"></script>   

  </head>
  
  <body>
    
        <!---top start--->
        <jsp:include page="/pages/toper.jsp"/>
        <!---top end--->
        <!--head-->
        <div id="head">
        <jsp:include page="/pages/head.jsp"/>
           
           <!--logo end-->
           <!--nav start-->
           <div class="nav-box">
              <div class="nav">
              <ul class="top-nav">
                <li class="item1">
                  <a href="good/lookAll.do?pagenum=1" target="_blank">全部商品</a>
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
            <!--main-left start-->
            <div class="main-left">
            
               <div class="menuNav">
                  <ul class="left-nav">
	                  <c:forEach items="${categoryList}" var="category">
	                    <li class="menu1">
	                      <a href="${pageContext.request.contextPath }/category/queryCategoryById.do?c_parent=${category.c_id}&pagenum=1" target="_blank">${category.c_name}</a>
	                    </li>
	                  </c:forEach>
                  </ul>
               </div>
               
               <div class="brand" >
                  <div class="title">品牌专区</div>
                  <ul class="brand-img">
                     <li>
                        <a target="_blank" href="">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_blank" href="">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_blank" href="">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_blank" href="">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_blank" href="">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_blank" href="">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_blank" href="">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_blank" href="">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                   </ul>
                      
               </div>
               <!--main-integral start-->
               <jsp:include page="/pages/RankGoods.jsp"></jsp:include>
               
               <!--main-integral end-->
               
               <div class="best">
               
               </div>
               
            </div>
            
            <div class="main-center">
	            <!--main-ads start-->
	            <div id="ads" style="width:100%;">
	                <div class="swiper-container">
	                    <div class="swiper-wrapper" id="swiper-wrapper">
	                       
	                    </div>
	                   <div class="pagination"></div>
	               </div>
	           </div>
	           <!--main-ads end-->
	          
	          
	            <!--main-newTj start-->
	            <jsp:include page="/pages/newGoods.jsp"></jsp:include>
	            <!--main-newTj end-->
	            
	            <!--main-bestSellTj start-->
	            <jsp:include page="/pages/popularGoods.jsp"></jsp:include>
            
            </div>
        
        
        </div>
        <!--main-left end-->
        <div style="clear:both"></div>
        
        <jsp:include page="/pages/footer.jsp"/>
        
        
  </body>
</html>
