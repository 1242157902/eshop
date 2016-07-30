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
    <link type="text/css" rel="stylesheet" href="css/application.css"/>
  	 <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
  	<script type="text/javascript" src="js/jquery.raty.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
    <!-- <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> -->
    <script type="text/javascript" src="js/common.js"></script>   
<script type="text/javascript">
$(document).ready(function($){
      $.fn.raty.defaults.path = 'images/img';
      var grade = $("#grade").val();
      if(grade!=0)
	   	{
	   	    $('#targetKeep-demo').raty({
	   	    	score:grade,
	   	    	readOnly  : true,
	   	       /*  target    : '#targetKeep-hint',
	   	        targetKeep: true */
	   	      });
	   	}
      else
   	  {
   	    $('#targetKeep-demo').raty({
   	        /* target    : '#targetKeep-hint', */
   	        
   	      });
   	  }
      
    });


    function usr(ccurrent)
    {
    	
    	var current=document.getElementById(ccurrent);   
		  var index=ccurrent.substr(9,ccurrent.length);
	   if(current.style.display=="none"||current.style.display=="") 
	    {
		   show(index);
		   current.style.display='block';
		 }
	    else
	    {
	    	show(index);
	    	current.style.display='none';
	    }
    } 
    function show(i)
    {
    	   var grade = $("#grade_"+i).val();
   	      if(grade!=0)
   		   	{
   		   	    $('#targetKeep-demo_'+i).raty({
   		   	    	score:grade,
   		   	    	readOnly  : true,
   		   	        /* target    : '#targetKeep-hint_'+i, */
   		   	        
   		   	      });
   		   	}
   	      else
   	   	  {
   	   	    $('#targetKeep-demo_'+i).raty({
   	   	       /*  target    : '#targetKeep-hint_'+i, */
   	   	       
   	   	      });
   	   	  }
    	      
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
										<a  class="" href="user/userInfo.do">
										个人中心
										</a>
									</li>
									<li class="m-li">
										<a href="javascript:void(0);">
										用户信息
										</a>
									</li>
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
                  <h3>商品评价
                  </h3>
               </div>
               
              
			<div id="evalu01" class="comt-main">
			
				<div class="comt-plists">
					<div class="comt-head">
						<ul>
							<li class="col1">商品信息</li>
							<li class="col2">评价状态</li>
						</ul>
					</div>
					<c:forEach items="${orderDetails}" var="orderDetails"  varStatus="status">
					<div class="comt-plist">
						<div class="pro-info" >
							<ul>
								<li class="fore1">
									<div class="p-item">
										<div class="p-img">
											<img class="" width="50" height="50" title="${ orderDetails.good_name}" data-lazy-img="done" src="${pageContext.request.contextPath }${orderDetails.good.good_small_img }">
										</div>
										<div class="p-msg">
											<div class="p-name">
												<a href="" target="_blank">${orderDetails.good.good_name }</a>
											</div>
											<div class="p-tiem ftx-03"> 购买时间：${orderDetails.createdTime} </div>
										</div>
									</div>
								</li>
								<li class="fore2 forem">
									<div class="op-wrap">
										<div class="op-cont">
											<div class="op-btns">
											<c:choose>
											<c:when test="${orderDetails.good_comment==null }">
												<a class="btn-9"  onclick="usr('comt-box_${status.index }')"  title="添加评价" >添加评价</a>
											</c:when>
											<c:otherwise >
												<a class="btn-9"  onclick="usr('comt-box_${status.index }')"  title="查看评价" >查看评价</a>
											</c:otherwise>
											</c:choose>
												<div class="ftx-03 jdbeans-331089">
												</div>
											</div>
										</div>
									</div>
								</li>
							</ul>
			            </div>
			            <form action="order/orderComment.do">
		            <div class="comt-box" id="comt-box_${status.index }" >
						<div class="box-t"></div>
			            <div class="form">
			            <input type="hidden" name="orderDetail_id" value="${orderDetails.orderitemid }"/>   
							<div class="item scoreEl">
							
								<span class="label" >
								<em>*</em>
								评分：
								</span>
								<div class="demo fl">
	                        		<div id="targetKeep-demo_${status.index }" class="target-demo"></div>
	                        		<input type="hidden" id="grade_${status.index }" name="grade" value="${orderDetails.grade}"/> 
	            					<%-- <div id="targetKeep-hint_${status.index }" class="hint"></div> --%>
	          					</div>
							</div>
							<div class="item xindeEl">
								<span class="label">
								<em>*</em>
								心得：
								</span>
								<div class="fl">
									<div class="comment-cont-box">
										<div class="comt-tags"> </div>
										 <c:choose>
											<c:when test="${orderDetails.good_comment==null }">
												<div class="comt-area-txt"><input type="text" id="good_comment" name="good_comment" value="${orderDetails.good_comment }"/></div>
											</c:when>
											<c:otherwise >
												<div class="comt-area-txt">${orderDetails.good_comment } </div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
								
							 <c:choose>
								<c:when test="${orderDetails.good_comment==null }">
									<div class="item">
										<span class="label">   </span>
										<div class="fl">
											<input class="btn-5 mr10 setcomment" type="submit" value="发表评价"/>
											<!-- <input id="anonymousFlag" class="jdcheckbox" type="checkbox"> -->
											<!-- <label for="anonymousFlag">匿名评价</label> -->
										</div>
								</div>
								</c:when>
								<c:otherwise >
								</c:otherwise>
							</c:choose>
						
								
							</div>
						</div>
						
					</form>
					
					</div>
				</c:forEach>
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
 