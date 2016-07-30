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
    
    <title>My JSP 'myFavorite.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css">   
    <link rel="stylesheet" type="text/css" href="css/list.css"/> 
	<link rel="stylesheet" type="text/css" href="css/common.css"/> 
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
     <link rel="stylesheet" type="text/css" href="css/merchandise.css"/>
 
    <link rel="stylesheet"  type="text/css"  href="css/jquery.autocomplete.css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">
     <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
    <!-- <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> -->
    <script type="text/javascript" src="js/common.js"></script>   
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
									<a  href="user/userInfo.do">
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
									<a class="curs" href="good/myFavorite.do">
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
                  <h3>关注的商品 ：</h3>
                  
               </div>
              <div class="prolist">
               
                   <ul id="PListShow" class="pro_list">
                     <c:forEach items="${goodFavList}" var="good">
                     
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
                   <div style="width:100%;clear:both;">
                      <div id="pageNum" class="pages">
                        <ul>
                            <li>
                              <a href="good/myFavorite.do?currentPage=1">首页</a>
                            </li>
                            <c:if test="${currentPage>1}">
                            <li>
                              <a href="good/myFavorite.do?currentPage=${currentPage-1 }">上一页</a>
                            </li> 
                            <%-- <li>
                              <a href="order/myorder.do?currentPage=${currentPage-1 }">${currentPage-1 }</a>
                            </li> --%>
                            </c:if>
                            <c:forEach var="current" begin="1" end="${count }">
                            <c:choose >
                            <c:when test="${currentPage==current }">
                              <li>
                              <a class="current" href="good/myFavorite.do?currentPage=${current }" />${current }</a>
                               </li>
                            </c:when>
                            <c:otherwise>
                              <li>
                                <a  href="good/myFavorite.do?currentPage=${current }" />${current }</a>
                              </li>
                            </c:otherwise>
                            
                            </c:choose>
                            </c:forEach>
                             <c:if test="${currentPage<count }">
                            <%-- <li>
                              <a href="order/myorder.do?currentPage=${currentPage+1 }">${currentPage+1 }</a>
                            </li> --%>
                            
                            <li>
                              <a href="good/myFavorite.do?currentPage=${currentPage+1 }">下一页</a>
                            </li>
                            </c:if>
                            <li>
                              <a href="good/myFavorite.do?currentPage=${count }">末页</a>
                            </li>
                        </ul>
                    </div>
            </div>
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
