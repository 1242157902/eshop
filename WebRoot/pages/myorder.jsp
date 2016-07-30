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
    <title>我的订单</title>
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
										<a href="javascript:void(0);">
										用户信息
										</a>
									</li> -->
									<li class="m-li current">
										<a class="curs" href="order/myorder.do">
										我的订单
										</a>
									</li>
									<li class="m-li">
										<a href="javascript:void(0);">
										收货地址
										</a>
									</li>
									<li class="m-li">
										<a href="good/myFavorite.do">
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
                  <h3>尊敬的用户：<span>${sessionScope.user.telephone}</span>
                  您好，您的可用积分为：<label style="color:red;">${totalscore }</label>
                  </h3>
               </div>
               <ul  class="clearfix persViewTit">
                <li class="h-text h-goods">商品</li>
                <li class="h-text h-price">售价</li>
                <li class="h-text h-amount">数量</li>
                <li class="h-text h-total">总价</li>
                <li class="h-text h-status">订单状态</li>
                 <li class="h-text h-operate">操作</li>
               
               </ul>
               
               <div>
                
                 <table class="myOdr_tb1" width="100%" cellspacing="0" cellpadding="0" border="0">
                    <colgroup>
                        <col class="cgWidth">
                        <col class="cgPay">
                        <col class="cgStatus">
                        <col class="cgOper">
                        
                    </colgroup>
                <c:forEach items="${orders}" var="order">
                    <tbody>
                         <tr >
                            <td colspan="5" style="height:10px;"></td>
                         </tr>
                        <tr>
                        <th class="oInfo clearfix" bgcolor="#f7f7f7" align="left" colspan="4">
                        <span class="gorderId" >订单号:${order.order_id }</a></span>
                        <span>收货人：${order.consignee_name }</span>
                        <span>下单时间：${order.create_time }</span>
                        </tr>
                        <tr  class="aOrder">
                           <td  class="aOrder-items">
                            <c:forEach items="${order.orderDetails}" var="orderItem">
                             <table  class="myOdr_tb2 fr"  cellspacing="0" cellpadding="0" border="0">
                            
                               <tbody>
                                 <tr>
                                   <td  align="center"> 
                                         <a class="aImg" href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${orderItem.good.good_id}" target="_blank">
                                             <img border="0" src="${pageContext.request.contextPath }${orderItem.good.good_small_img }" >
                                         </a>
                                   </td>
                                    <td class="goodsTitle">
                                       <a class="aTit" href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${orderItem.good.good_id}" target="_blank"> ${orderItem.good.good_name}</a>
                                   </td>
                                   <td class="price pd0" align="center">
            
                                                              ${orderItem.currprice}
                                   </td>
                                   <td class="amount bdrGray" align="center">${orderItem.good_quantity }</td>
                                   
                                  
                                 </tr>
                                 
                               </tbody>
                             
                             </table>
                               </c:forEach>
                           </td>
                           
                           <td class="itemPayTd rbBorder" align="center">
                                <p class="fb">${order.total }</p>
                   
                           </td>
                          <td class="orderStatus rbBorder" align="center">
                              <a target="_blank" >
                                <c:choose>
	                              
	                               <c:when test="${order.status eq 2 }">等待发货</c:when>
	                               <c:when test="${order.status eq 3 }">等待发货</c:when>
	                               <c:when test="${order.status eq 4 }">交易成功</c:when>
	                               <%-- <c:when test="${order.status eq 5 }">已取消</c:when> --%>
                                </c:choose>
                               </a>
                            </td>
                            
                            <td class="operate rbBorder" align="center">
                              <a target="_blank" href="order/showComment.do?order_id=${order.order_id}">
                                <c:choose>
	                              
	                               <c:when test="${order.status eq 2 }"></c:when>
	                               <c:when test="${order.status eq 3 }"></c:when>
	                               <c:when test="${order.status eq 4 }">评价订单</c:when>
	                               <%-- <c:when test="${order.status eq 5 }">已取消</c:when> --%>
                                </c:choose>
                               </a>
                            </td>
                         </tr>
                         
                    </tbody>
                    </c:forEach>
              </table>
              
              <!--page start-->
              <div style="width:100%;">
                      <div id="pageNum" class="pages">
                        <ul>
                            <li>
                              <a href="order/myorder.do?currentPage=1">首页</a>
                            </li>
                            <c:if test="${currentPage>1}">
                            <li>
                              <a href="order/myorder.do?currentPage=${currentPage-1 }">上一页</a>
                            </li> 
                            <%-- <li>
                              <a href="order/myorder.do?currentPage=${currentPage-1 }">${currentPage-1 }</a>
                            </li> --%>
                            </c:if>
                            <c:forEach var="current" begin="1" end="${count }">
                            <c:choose >
                            <c:when test="${currentPage==current }">
                              <li>
                              <a class="current" href="order/myorder.do?currentPage=${current }" />${current }</a>
                               </li>
                            </c:when>
                            <c:otherwise>
                              <li>
                                <a  href="order/myorder.do?currentPage=${current }" />${current }</a>
                              </li>
                            </c:otherwise>
                            
                            </c:choose>
                            </c:forEach>
                             <c:if test="${currentPage<count }">
                            <%-- <li>
                              <a href="order/myorder.do?currentPage=${currentPage+1 }">${currentPage+1 }</a>
                            </li> --%>
                            
                            <li>
                              <a href="order/myorder.do?currentPage=${currentPage+1 }">下一页</a>
                            </li>
                            </c:if>
                            <li>
                              <a href="order/myorder.do?currentPage=${count }">末页</a>
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
