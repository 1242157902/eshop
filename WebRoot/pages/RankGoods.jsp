<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

               <div class="brand">
                   <div class="title">兑换排行榜</div>
                   <ul id="box" class="dh">
                     <c:forEach items="${rankGoodList}" var="good"  varStatus="id">
	                    <li>
	                      <c:choose>
	                      <c:when test="${id.index+1==1}">
	                         <img class="img1" width="22" height="22" src="images/home/one.gif">
	                         <a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" target="_blank">
	                         <img class="img2" alt="${good.good_name}" src="${pageContext.request.contextPath}${good.good_small_img}">
	                         </a>
	                         <div class="dh-t">
	                      </c:when>
	                      <c:when test="${id.index+1==2}">
	                          <img class="img1" width="22" height="22" src="images/home/two.gif">
	                         <a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" target="_blank">
	                         <img class="img2" alt="${good.good_name}" src="${pageContext.request.contextPath}${good.good_small_img}">
	                         </a>
	                         <div class="dh-t">
	                      </c:when>
	                      <c:when test="${id.index+1==3}">
	                          <img class="img1" width="22" height="22" src="images/home/three.gif">
	                          <a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" target="_blank">
	                         <img class="img2" alt="${good.good_name}" src="${pageContext.request.contextPath}${good.good_small_img}">
	                         </a>
	                         <div class="dh-t">
	                      </c:when>
	                      <c:otherwise>
	                          <span class="num">${id.index+1}</span>
	                          <div class="dh-t nopro">
	                      </c:otherwise>
	                      </c:choose>	    
		                            <h1>
		                                <a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" target="_blank">${good.good_name}</a>
		                            </h1>
		                            <h2>
		                                <strong>${good.good_nowprice}</strong>
		                                积分
		                            </h2>
	                            </div>
	                        
	                      </li>
                      </c:forEach>      
                   </ul>
               </div>
               
     