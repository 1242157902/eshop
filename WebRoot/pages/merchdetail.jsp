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
     -商品详情</title>
     <link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="css/common.css"/>
     <link rel="stylesheet" type="text/css" href="css/style.css"/>
     <link rel="stylesheet" type="text/css" href="css/index.css"/>
     <link rel="stylesheet" type="text/css" href="css/merchandise.css"/> 
     <link rel="stylesheet"  type="text/css"  href="css/jquery.autocomplete.css"/>
     <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">   
     <link rel="stylesheet" type="text/css" href="css/mask.css">
     <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
     <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>   
      <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script>
     <script type="text/javascript" src="js/common.js"></script>    
     <script type="text/javascript" src="js/magnify.js"></script>
    <script type="text/javascript">
    $(document).ready(function($){

    	$('.theme-login').click(function(){
    		var goodid = $("#goodid").val();
    		 $.ajax({
     			cache:false,
     			url:"${pageContext.request.contextPath }/good/favGood.do",
     			data:{good_id:goodid},
     			type:"POST",
     			dataType:"json",
     			success:function(result) {
     	    		$('.theme-popover-mask').show();
     	    		$('.theme-popover-mask').height($(document).height());
     	    		$('.theme-popover').slideDown(200);
     			}
     		}); 
    	});
    	$('.theme-poptit .close').click(function(){
    		$('.theme-popover-mask').hide();
    		$('.theme-popover').slideUp(200);
    	});
    });
    
    </script>
     
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
        
        <!--main-body start-->
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
               
               
               
               
               <!--main-brand start-->
               <div class="brand" style="margin-top:10px;">
                  <div class="title">品牌专区</div>
                  <ul class="brand-img">
                     <li>
                        <a target="_self" href="javascript:void(0);">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_self" href="javascript:void(0);">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_self" href="javascript:void(0);">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_self" href="javascript:void(0);">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_self" href="javascript:void(0);">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_self" href="javascript:void(0);">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_self" href="javascript:void(0);">
                           <img width="98" height="50" src="images/logo.png" />
                        </a>
                      </li>
                      <li>
                        <a target="_self" href="javascript:void(0);">
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
            
            <div class="theme-popover-mask"></div>

		      <div class="theme-popover">
			      <div class="theme-poptit">
				      <a href="${pageContext.request.contextPath }/good/lookGoodById.do?good_id=${good.good_id}" title="关闭" class="close">×</a>
				      <h3>提示</h3>
			      </div>
			      
			      <div class="theme-popbod dform">
			      
							<h3><strong>您已成功关注该商品！</strong></h3>
							<a href="good/myFavorite.do">查看我的关注>></a>
						
				  </div>  
		      </div>
            
            <!--main-center start-->
            <form action="cart/addtocart.do"  method="post">
            <div class="main-center">
            
               <div class="info_top">
                <span class="big">${category.c_name}</span>
                > ${good.good_name}
                 
               </div>
               
               <div class="proinfo">
                    <input id="isBirthDayP" type="hidden" value="true"/>
                    <input id="isLevelP" type="hidden" value="true"/>
                    <input id="isLogin" type="hidden" value="false"/>
                    <input id="isLimitCount" type="hidden" value="true"/>
                    
                   
                   <div class="preview">
                   
                        <div id="vertical" class="bigImg">
                            <img id="midimg" src="${pageContext.request.contextPath }${goodPic.pic_url}" width="350"
                                  height="350"  />
                            <!--<div style="display:none;" id="winSelector"></div>-->	
                        </div>
                        
                        <!--bigImg end-->	
                        
                        <div class="smallImg">
                        
                            <div class="scrollbutton smallImgUp disabled"></div>
                            
                            <div id="imageMenu">
                            
                                <ul>
                                   <c:forEach items="${goodPics}" var="goodPic" varStatus="id">
                                   
                                     <c:if  test="${id.index+1==1}"></c:if>
                                   <c:choose>
                                    <c:when test="${id.index+1==1}">
                                      <li id="onlickImg"><img  src="${pageContext.request.contextPath }${goodPic.pic_url}" width="50" height="50"/>
                                      </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><img  src="${pageContext.request.contextPath }${goodPic.pic_url}" width="50" height="50"/>
                                         </li>
                                    </c:otherwise>
                                   </c:choose>
                          
                                  </c:forEach> 
                                   
                                </ul>
                                
                            </div>
                            
                            <div class="scrollbutton smallImgDown"></div>
                        </div>
                        <!--smallImg end --> 
                      
                        <div class="favorite">
                        <a  class="theme-login" <%-- href="good/favGood.do?good_id=${good.good_id }" --%>></span>关注商品</a>
                        </div>
                          
                   </div>
                   
                   <div class="view_content">
                   
                        <ul class="c_title">
                            <li> ${good.good_name}</li>
                            <input name="goodid" id="goodid" type="hidden" value="${good.good_id }"/>
                        </ul> 
                        <input id="hidSelectType" type="hidden" level="0" value="0"/>
                        <div class="c_content">
                            <dl>
                                <dt>兑换价格：</dt>
                                <dd>
                                    <span class="jf">
                                    <strong>${good.good_nowprice}</strong>
                                    积分
                                    </span>
                                </dd>
                               
                                <dt>商品评价：</dt>
                                <dd>暂无
                                    <!-- <ul class="grade-star">
                                        <li>一星</li>
                                        <li>一星</li>
                                        <li>一星</li>
                                        <li>一星</li>
                                        <li class="gray">三星</li>
                                    </ul>
                                    <span class="tips">（已有4429人兑换）</span> -->
                                </dd>
                                <dt>库&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp存：</dt>
                                <dd id="proNum">${good.stockQuantity}(件)</dd>
                                <dt>服&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp务：</dt>
                                <dd>
                                    由
                                    <span class="fws">
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
                                     </span>
                                    发货并提供售后服务
                                </dd>
                            </dl>
                         </div>
                         <dl id="dlProPayType" class="choose j">
                            <dt>选择兑换方式：</dt>
                            <dd id="dd1">
                                <ul id="ulexchangeType" class="type">
                                   <li class="dhfs" paypoint="8670" paytype="0"> ${good.good_nowprice}积分</li>
                                </ul>
                            </dd>
                         </dl>
                         <dl id="dl1" class="choose">
                            <dt>购买数量：</dt>
                            <dd>
                            <input id="text_sub" class="next" type="button"  name="sub" value="">
                            <input id="proScount" type="text" onkeyup="chageProCount();" name="proScount" value="1">
                            <input id="text_add" class="prev" type="button" name="add" value="">
                            </dd>
                        </dl>
                        <div class="choose money">
                            <p class="info-warn">
                                <em></em>
                                购物车已满，请
                                <a href="javascript:GoCar();">提交订单</a>
                                后重新添加
                            </p>
                            <div class="clear"> </div>
                            <p>
                            <input class="btn-addcart" type="submit"  value="加入购物车">
                            </p>
                        </div>
                         
						
                   </div>
                   </form>
				   <div class="info-list">
                   
                        <div class="info-title">
                            <span class="info-nav">商品详情</span>
                        </div>
                        
                        <div class="info-content">
                            <img src="${pageContext.request.contextPath }${good.good_detail_img}">
                            <br>
                        </div>
                   </div>
                   
               </div>
              
               
              <jsp:include page="/pages/recomGoods.jsp"></jsp:include>
                                
            </div>
            <!--main-center end-->
            
        </div>
        <!--main-body end-->
        <div style="clear:both"></div>
        
        <!--footer start-->
         <jsp:include page="/pages/footer.jsp"/>
        <!--footer end-->
  </body>
</html>
