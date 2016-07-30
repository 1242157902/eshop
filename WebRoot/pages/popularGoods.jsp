<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="Tj" style="clear:both">
               <div class="Tj-t">最具人气
               <span class="more">
                  <a href="good/lookAll.do?pagenum=1" target="_blank">更多>></a>
               </span>
               </div>
                <ul id="Tj1" class="TjBox">
                 <c:forEach items="${rankGoodList}" var="good" varStatus="id">
                   
                    <c:choose>
                    <c:when test="${id.index+1<5}">
                    <li class="good1">
	                      <div class="good t1">
	                       <h1><a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" target="_blank">${good.good_name}</a></h1>
	                       <h2>${good.good_nowprice}</h2>
	                       <span class="danwei">积分</span>
	                      </div>
	                       
	                      <a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" target="_blank">
	                        <img class="img1" src="${pageContext.request.contextPath}${good.good_img}"/>
	                      </a>  
	                    </li>
                    </c:when>
                    <c:when test="${id.index+1<9}">
	                    <li class="good2">
		                      <div class="good t2">
		                       <h1><a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" target="_blank">${good.good_name}</a></h1>
		                       <h2>${good.good_nowprice}</h2>
		                       <span class="danwei">积分</span>
		                      </div>
		                       
		                      <a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" target="_blank">
		                        <img class="img2" src="${pageContext.request.contextPath}${good.good_small_img}"/>
		                      </a>  
		                  </li>
                    </c:when>
                    </c:choose>
                 </c:forEach>  
                    
               </ul> 
            </div>