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
     		北方工大礼品商城
     	</c:when>
     	<c:when test="${sessionScope.user.company=='100002'}">
     		迪信通礼品商城
     	</c:when>
     	<c:otherwise>
     		默认礼品商城
     	</c:otherwise>
     </c:choose>
    
    -商品分类</title>
    <link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="css/merchandise.css"/>
    <link rel="stylesheet"  type="text/css"  href="css/jquery.autocomplete.css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">   
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>  
    <script type="text/javascript" src="js/swiper-2.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
    <script type="text/javascript" src="js/common.js"></script>   
    <script type="text/javascript" src="js/swiperSlide.js"></script>

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
        
        <!--main-body start-->
        <div class="main-body">
        
            <!--main-left start-->
            <div class="main-left">
            
               <div class="menuNav">
                  <ul class="left-nav">
                    <c:forEach items="${firstcategories}" var="category">
	                    <li class="menu1">
	                      <a href="${pageContext.request.contextPath }/category/queryCategoryById.do?c_parent=${category.c_id}&pagenum=1" target="_blank">${category.c_name}</a>
	                    </li>
	                </c:forEach>
                  </ul>
               </div>  
               
               <!--main-brand start-->
               <div class="brand" style="margin-top:10px;">
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
               <!--main-brand end-->
               
               <!--main-integral start-->
               <jsp:include page="/pages/RankGoods.jsp"></jsp:include>
               
               <!--main-integral end-->
            </div>
            <!--main-left end-->
            
            <!--main-center start-->
            <div class="main-center">
            
                <div id="ads" style="width:100%;padding-top:5px;padding-left:5px;padding-bottom:10px;">
                    <div class="swiper-container one">
                        <div class="swiper-wrapper two" id="swiper-wrapper">
                            <div class="swiper-slide three"><a href="#"><img src="images/151120102626142801.jpg" alt=""></a></div>
                            <div class="swiper-slide three"><a href="#"><img src="images/151208115520321660.jpg" alt=""></a></div>
                            
                        </div>
                        <div class="pagination catepagination"></div>
                    </div>
                </div>
                
                 <!--main-center-brand start-->
                <div class="brand-class">
                    <span class="class-name">品牌分类</span>
                    <c:choose>
                      <c:when test="${c_id==-1}">
                      <ul id="proBrandlist" class="class-type">
                    
                        <li>
                        <a id="bid-1" href="good/lookAll.do?c_id=-1&c_parent=0&pagenum=1" class="xz">全部</a>
                        </li>
                      <c:forEach items="${secondCategories}" var="category" varStatus="status">
                      
                        <li>
                        <a id="bid_${status.index+1}" href="good/lookAll.do?c_id=${category.c_id }&c_parent=${category.c_parent}&pagenum=1">${category.c_name}</a>
                        </li>
                      </c:forEach>   
                    </ul>
                    
                      </c:when>
                      <c:otherwise>
                      <ul id="proBrandlist" class="class-type">
                    
                        <li>
                        <a id="bid-1" href="good/lookAll.do?c_id=-1&c_parent=0&pagenum=1" >全部</a>
                        </li>
                      <c:forEach items="${secondCategories}" var="category" varStatus="status">
                      <c:choose>
                        <c:when test="${c_id==category.c_id }">
                        <li>
                        <a  class="xz"  id="bid_${status.index+1}" href="good/lookAll.do?c_id=${category.c_id }&c_parent=${category.c_parent}&pagenum=1">${category.c_name}</a>
                        </li>
                       </c:when>
                       <c:otherwise>
                       <li >
                        <a id="bid_${status.index+1}" href="good/lookAll.do?c_id=${category.c_id }&c_parent=${category.c_parent}&pagenum=1">${category.c_name}</a>
                        </li>
                         </c:otherwise>
                        </c:choose>
                      </c:forEach>   
                    </ul>
                      </c:otherwise>
                    </c:choose>
                    
               </div>
               <!--main-center-brand end-->
               <dl class="orderBy-tags">
                    <!-- <dt>排序：</dt>
                    <dd>
                    <a id="aPoint" class="orderBy-m">积分</a>
                    </dd>
                    
                    <dd>
                    <a id="aName" class="orderBy-m">销量</a>
                    </dd> -->
               </dl>
               
               <div class="prolist">
               
                   <ul id="PListShow" class="pro_list">
                     <c:forEach items="${goodList}" var="good">
                         <li>
                            <div class="img">
                                <a target="_blank" href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}">
                                <img title="${good.good_name}" src="${pageContext.request.contextPath }${good.good_category_img}">
                                </a>
                            </div>
                            
                            <div class="ptitle">
                                <a title="${good.good_name}" target="_blank" href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}">${good.good_name}</a>
                            </div>
                            
                            <div style="display: block">
                                <div class="air">${good.good_nowprice} 积分</div>
                            </div>
                            
                       </li>
                     </c:forEach>
                       
                   </ul>
                   <div class="merpage">
				         <jsp:include page="/pages/public/page.jsp"></jsp:include>
                  </div>  
               </div>
               
               
            </div>
            <!--main-center end-->
        </div>
        <!--main-body end-->
        <div style="clear:both"></div>
        <jsp:include page="/pages/footer.jsp"/>
  </body>
</html>
