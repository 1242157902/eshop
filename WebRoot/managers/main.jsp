<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stock.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/demo/demo.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/easyloader.js"></script> 
	<script type="text/javascript">
	
	 	$(function(){
				
			$('a[title]').click(function(){
						$("#bg").hide();
						var src = $(this).attr('title');
						var title = $(this).html();
						if($('#tab').tabs('exists' ,title)){
							$('#tab').tabs('select',title);
						} else {
							$('#tab').tabs('add',{   
							    title:title,   
							    content:'<iframe frameborder=0 style=width:100%;height:100% src='+ src +' ></iframe>',   
							    closable:true  
							});  
						}
				});
			
			$('#tab').tabs({
				onClose:function(){
					
				}
			});
			
			}); 
			
 		
	
	</script>
  </head>
  
  <body>
 
 
	<div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
	
		    <div region="north" title="  "  split="false" style="height:100px;text-align: center;  ">
		    
		    	<h2 style="font-size: xx-large;text-align: center;color: blue;padding-top:15px ;">${sessionScope.adminuser.companyName}</h2>
				  <div style="float:right; margin-right: 20px;">
					<c:choose>
					<c:when test="${sessionScope.adminuser.name==null}">
			           	 	<a id="btn" href="${pageContext.request.contextPath }/managers/index.jsp" class="easyui-linkbutton">请登录</a>
			     	</c:when>
					<c:otherwise>
             			欢迎您：<span style="color:blue">${sessionScope.adminuser.name}</span> &nbsp<a id="btn" href="admin/logout.do" class="easyui-linkbutton">退出</a>
       				</c:otherwise>
					</c:choose>
				 </div>
		</div>  
		  
		    <div region="west"  iconCls="icon-ok" split="true" title="导航菜单" style="width:150px;">
				<div id="aa" class="easyui-accordion"   fit=true >  
				    <div title="库存管理"  style="overflow:auto;padding:20px;"  selected="true" > 
				    	<a title="managers/stock/goods.jsp" >商品购买管理</a> <br/><br/>
			    	<!-- <a title="managers/stock/purchaseGoods.jsp" >采购单管理</a> <br/><br/>  -->
				    	<a title="managers/stock/instock1.jsp" >未入库单管理</a> <br/><br/>
				    	<a title="managers/stock/instock.jsp" >已入库单管理</a> <br/><br/>
				    	<a title="managers/stock/orders.jsp" >订单列表管理</a> <br/><br/>
				    	<a title="managers/stock/ordersout.jsp" >导出订单列表</a> <br/><br/>
				    	<a title="managers/stock/outstock.jsp" >已出库单管理</a> <br/><br/>
				    	<a title="managers/stock/stock.jsp" >库存信息管理</a> <br/> <br/>
				    	<a title="managers/stock/stockrecord.jsp" >库存记录管理</a> <br/> 
				    </div>  
				   <div title="商品管理"  selected="true" style="padding:10px;"> 
				    	<a title="managers/good/category.jsp" >类别管理</a> <br/><br/>
				    	<a title="managers/good/good.jsp" >商品编辑</a> <br/><br/>
				    	<a title="managers/good/good_record.jsp" >价格积分管理</a> <br/><br/>
				    </div>
				    <div title="图片管理" style="padding:10px;"> 
				    	<a title="managers/advertise/contentList.jsp" >查询图片</a><br>
				    	<a title="managers/advertise/adverList.jsp" >图片列表</a><br>
				    </div>   
				    <div title="员工管理"  style="overflow:auto;padding:20px;">  
				    	<a title="managers/user/user.jsp" >后台用户管理</a><br><br>
				    </div>
				    <div title="绩效管理"  style="overflow:auto;padding:20px;">  
				    	<a title="managers/score/importrewardinternal.jsp" >导入积分管理</a><br><br>
				    	<a title="managers/score/perScore.jsp" >积分比例管理</a><br><br>
				    </div>
				    <!-- <div title="资源管理">  
				    </div>  -->				     
				</div>  
		    </div>  
		    <div region="center"  title="主界面"  >
		 	   <div id="bg" style="padding: 2px;"> <img alt="欢迎使用礼品商城后台管理系统" src="images/welcome1.png"  width="100%"> </div>
				<div id="tab" class="easyui-tabs" fit=true style="width:500px;height:250px;">  
						
				</div>  
		    </div>  
		</div>  






  </body>
</html>
