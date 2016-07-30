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
    <title><c:choose>
     	<c:when test="${sessionScope.user.company=='100001'}">
     		北方工大礼品商城
     	</c:when>
     	<c:when test="${sessionScope.user.company=='100002'}">
     		迪信通礼品商城
     	</c:when>
     	<c:otherwise>
     		默认礼品商城
     	</c:otherwise>
     </c:choose>-商品分类</title>
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
    <script type="text/javascript">
    $(function(){
    	$("#proBrandlist").find("li a").click(function(){
    	    $("#proBrandlist").find("li a").removeClass("xz");										
    		$(this).toggleClass("xz");
    	
    	});
    	/* $(".orderBy-tags").find("dd a").click(function(){
    				if($(this).hasClass("orderBy-u-n")){
    					$(".orderBy-tags").find("dd a").attr("class","orderBy-m");
    					$(this).attr("class","orderBy-d-n");
    					
    				}else if($(this).hasClass("orderBy-d-n")){
    					$(".orderBy-tags").find("dd a").attr("class","orderBy-m");
    					$(this).attr("class","orderBy-u-n");
    					
    				}else{
    					$(".orderBy-tags").find("dd a").attr("class","orderBy-m");
    				    $(this).attr("class","orderBy-u-n");
    				}
    				//$(this).toggleClass("orderBy-u-n");										
    	
    	}); */
    	
    	
    	//查看某个二级类别下的第一页商品
    	$("#proBrandlist a").click(function(){	
    		$(".pro_list").find("li").remove();
    		$(".merpage").empty();
    		var c_id=$(this).attr("c_id");
    		$("input[name=c_id]").val(c_id);
    		$.ajax({
    			type:"post",
    			url:"good/lookMerchByKeyword.do",
    			data:$("#proForm").serialize(),
    			dataType:"json",
    			success:function(obj){
    				 if(obj!=null){
    					 var list=obj[0].list;
    					 var pagenum=obj[0].pagenum;
    					 var startPage=obj[0].startPage;
    					 var endPage=obj[0].endPage;
    					 var totalpage=obj[0].totalpage;
    					 var totalrecord=obj[0].totalrecord;
    					
    					 $.each(list,function(i,item){
    						 var img="<div class='img'><a target='_blank' href='good/lookGoodById.do?good_id="+item.good_id+"'><img title='"+item.good_name+"' src='/eshop"+item.good_img+"'></a></div>";
    					     var title="<div class='ptitle'><a title='"+item.good_name+"' target='_blank' href='good/lookGoodById.do?good_id="+item.good_id+"'>"+item.good_name+"</a></div>";
    					     var jifen="<div style='display: block'><div class='air'>"+item.good_nowprice+" 积分</div></div>";
    					     var li="<li>"+img+title+jifen+"</li>";
    					     $(".pro_list").append(li);
    					 });
    					 var page="当前第["+pagenum+"]页&nbsp;&nbsp;";
    					 if(pagenum>1){
    						 var pre=pagenum-1;
    						 page+="<a id='pre' onclick='javascript:myclick("+pre+");' pagenum='"+pre+"'>上一页</a>&nbsp;&nbsp;";
    					 }
    					 for(var i=startPage;i<=endPage;i++){
    						 page+="[ <a class='cur' onclick='javascript:myclick("+i+");' pagenum='"+i+"'>"+i+"</a>]&nbsp;&nbsp;";
    					 }
    					 if(pagenum<totalpage){
    						 var next=pagenum+1;
    						 page+="&nbsp;&nbsp;<a id='next' onclick='javascript:myclick("+next+");' pagenum='"+next+"'>下一页</a>&nbsp;&nbsp;";
    					 }
    					 page+="共["+totalpage+"]页，共["+totalrecord+"]条记录";
    					$(".merpage").append(page);
    				 }else{
    					 var li="<li>对不起，没有相关数据！</li>";
    					 $(".pro_list").append(li);
    				 }
    				
    			 }
    	});
      });
    });
    
    function orderByJiFen(obj){
    	alert(obj.class);
    	/* if($(this).hasClass("orderBy-u-n")){
			$(".orderBy-tags").find("dd a").attr("class","orderBy-m");
			$(this).attr("class","orderBy-d-n");
			
		}else if($(this).hasClass("orderBy-d-n")){
			$(".orderBy-tags").find("dd a").attr("class","orderBy-m");
			$(this).attr("class","orderBy-u-n");
			
		}else{
			$(".orderBy-tags").find("dd a").attr("class","orderBy-m");
		    $(this).attr("class","orderBy-u-n");
		} */
    }
    
function myclick(obj){
    	 
    		$(".pro_list").find("li").remove();
    		var pagenum=obj;   		
    		$("input[name=pagenum]").val(pagenum);
    		$.ajax({
    			type:"post",
    			url:"good/lookMerchByKeyword.do",
    			data:$("#proForm").serialize(),
    			dataType:"json",
    			success:function(obj){
    				 if(obj!=null){
    					 var list=obj[0].list;
    					 var pagenum=obj[0].pagenum;
    					 var startPage=obj[0].startPage;
    					 var endPage=obj[0].endPage;
    					 var totalpage=obj[0].totalpage;
    					 var totalrecord=obj[0].totalrecord;
    					
    					 $.each(list,function(i,item){
    						 var img="<div class='img'><a target='_blank' href='good/lookGoodById.do?good_id="+item.good_id+"'><img title='"+item.good_name+"' src='/eshop"+item.good_img+"'></a></div>";
    					     var title="<div class='ptitle'><a title='"+item.good_name+"' target='_blank' href='good/lookGoodById.do?good_id="+item.good_id+"'>"+item.good_name+"</a></div>";
    					     var jifen="<div style='display: block'><div class='air'>"+item.good_nowprice+" 积分</div></div>";
    					     var li="<li>"+img+title+jifen+"</li>";
    					     $(".pro_list").append(li);
    					 });
    					 var page="当前第["+pagenum+"]页&nbsp;&nbsp;";
    					 if(pagenum>1){
    						 var pre=pagenum-1;
    						 page+="<a id='pre' href='javascript:myclick("+pre+")' pagenum='"+pre+"'>上一页</a>&nbsp;&nbsp;";
    					 }
    					 for(var i=startPage;i<=endPage;i++){
    						 page+="[ <a class='cur' href='javascript:myclick("+i+")' pagenum='"+i+"'>"+i+"</a>]&nbsp;&nbsp;";
    					 }
    					 if(pagenum<totalpage){
    						 var next=pagenum+1;
    						 page+="&nbsp;&nbsp;<a id='next' href='javascript:myclick("+next+")' pagenum='"+next+"'>下一页</a>&nbsp;&nbsp;";
    					 }
    					 page+="共["+totalpage+"]页，共["+totalrecord+"]条记录";
    					$(".merpage").empty();
    					$(".merpage").append(page);
    				 }else{
    					 var li="<li>对不起，没有相关数据！</li>";
    					 $(".pro_list").append(li);
    				 }
    				
    			 }
    	});
    		
    		
     }
    
   
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
                  <a href="good/lookAll.do?pagenum=1"  target="_blank">全部商品</a>
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
                 <form id="proForm">
	                 <input type="hidden" name="c_id"/>
	                 <input type="hidden" name="nowprice"/>
	                 <input type="hidden" name="sale_num"/>
	                 <input type="hidden" name="keyword" value="${keyword}"/>
	                 <input type="hidden" name="pagenum" value="1"/>
                 </form>
                 <!--main-center-brand start-->
                <div class="brand-class">
                    <span class="class-name">品牌分类</span>
                    <ul id="proBrandlist" class="class-type">
                        <li>
                        <a id="bid-1" class="xz" c_id="-1">全部</a>
                        </li>
                      <c:forEach items="${secondCategories}" var="category" varStatus="status">
                        <li>
                        <a id="bid_${status.index+1}" c_id="${category.c_id }">${category.c_name}</a>
                        </li>
                      </c:forEach>   
                    </ul>
                    
               </div>
               <!--main-center-brand end-->
               <dl class="orderBy-tags">
                    <!-- <dt>排序：</dt>
                    <dd>
                    <a id="aPoint" class="orderBy-m" href="javascript:orderByJiFen('')">积分</a>
                    </dd>
                    
                    <dd>
                    <a id="aName" class="orderBy-m" href="javascript:orderByJiFen('')">销量</a>
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
                   
                   <div class="merpage" id="merpage">
                   
				                    当前第[${page.pagenum}]页&nbsp;&nbsp;
					    <c:if test="${page.pagenum>1}">
					    
					    <a pagenum="${page.pagenum-1}" href="javascript:myclick(${page.pagenum-1})" >上一页</a>
					    
					    
					    </c:if>
					 
					    <c:forEach var="pagenum" begin="${page.startPage}" end="${page.endPage}"> 
					    
					         [ <a pagenum="${pagenum}" href="javascript:myclick(${pagenum})">${pagenum}</a>]
					    
					    
					    </c:forEach>
					    &nbsp;
					    <c:if test="${page.pagenum<page.totalpage}">
					    
					         <a pagenum="${page.pagenum+1}" href="javascript:myclick(${page.pagenum+1})">下一页</a>
					         
					         
					    </c:if>    
					    共[${page.totalpage}]页，共[${page.totalrecord}]条记录
					    
					    
					   
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
