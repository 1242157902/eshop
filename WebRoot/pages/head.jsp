<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--logo start-->
<div class="logo">
 
	   <div class="logo-pic">
	      <a href="login/queryAllContent.do" target="_blank">
         <c:choose>
	     	<c:when test="${sessionScope.user.company=='100001'}">
	     		<img src="images/logo_ncut.jpg" />
	     	</c:when>
	     	<c:when test="${sessionScope.user.company=='100002'}">
	     		<img src="images/logo.png" />
	     	</c:when>
	     	<c:otherwise>
	     		默认礼品商城主页
	     	</c:otherwise>
    	 </c:choose>
	      
	      
	      
	      </a>
	   </div>
	   
	   <div class="logo-search">
	   
	      <ul class="search-menu">
	         <li id="ckw" class="current">
	            <a>关键词</a>
	         </li>
	         <!-- <li is="cjf" class="">
	        <a>选择积分</a>
	        </li> -->
	     </ul>
	     
	     <form id="search" action="good/queryGoodsByKeyword.do" method="post">
	      <div class="search-input">
	         <div class="iKey" >
	            <input class="easyui-validatebox" name="keyword" type="text" maxlength="20" placeholder="请输入关键字" id="keyword" data-options="required:true,missingMessage:'请输入关键词！'" value="${keyword}" />
	           <!--  <input name="nowprice" type="text" placeholder="请输入积分范围" id="nowprice" class="hide"/> -->
	         </div>
	     </div>
	     <div class="search-btn">
	        <a><input  class="btn"  type="submit" value="搜索" onclick="return submitForm()"/></a>
	     </div> 
	    </form>
	    
	  </div>
 
</div> 

