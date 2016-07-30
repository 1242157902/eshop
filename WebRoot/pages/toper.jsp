<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="top">
           <div class="top-m">
              <ul class="top-left">
	              <li class="welcome">
	                <p>欢迎来到礼品商城!</p>
	              </li>
	              <li>
	                <a href="login/queryAllContent.do" class="main-link">首页</a>
	              </li>
              </ul> 
              <ul class="top-right">
	             <c:choose >
	              <c:when test="${empty sessionScope.user}">
                  <li>
                     <a class="login-link" href="pages/login.jsp" target="_self">请登录</a>
                     <span class="fenge">|</span>
                   <a class="login-link"href="pages/download.jsp" target="_self">免费注册 </a><span class="fenge">|</span>
                  </li>
                  </c:when>
                  <c:otherwise>
	                 <li>
	                     <a class="login-link">您好,${sessionScope.user.telephone}</a>
	                      &nbsp; &nbsp;
	                     <a class="login-link">当前积分为<span style="color:red;">${sessionScope.totalscore}</span></a>
	                     <span class="fenge">|</span>
	                   <a class="login-link"href="${pageContext.request.contextPath }/user/logout.do" target="_self">退出</a><span class="fenge">|</span>
	                 </li>
                  </c:otherwise>
                 </c:choose>
                 <li>
                 <a class="login-link"  target="_self" href="user/userInfo.do">个人中心</a><span class="fenge">|</span>
                 </li>
                 <li class="cart-pic">
                 </li>
                 <li>
                 <span class=""> </span><a class="login-link" href="cart/cartlist.do" target="_self">购物车</a><span class="fenge">|</span>
                 </li>
                 <li>
                 <a class="login-link" href="order/myorder.do" target="_blank">我的订单</a>
                 </li>
                 
              </ul>
           </div>
        </div>
    