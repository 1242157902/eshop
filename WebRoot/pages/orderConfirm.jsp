<%@ page language="java" import="java.util.*"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单核对</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
    <link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">   
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/demo/demo.css"> 
    <link rel="stylesheet"  type="text/css"  href="css/jquery.autocomplete.css"/>  
    <!-- <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script> -->
    <link rel="stylesheet" type="text/css" href="css/common.css"/> 
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/tasp.css" />
    <link rel="stylesheet" type="text/css" href="css/orderconfirm.css"  />
    <link rel="stylesheet" type="text/css" href="css/merchandise.css"/> 
    <link rel="stylesheet" type="text/css" href="css/mask.css">
     <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
     <script type="text/javascript" src="js/common.js"></script>   
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
    <script type="text/javascript">
$(document).ready(function($){

	$('.theme-login').click(function(){
		$('.theme-popover-mask').show();
		$('.theme-popover-mask').height($(document).height());
		$('.theme-popover').slideDown(200);
	});
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').hide();
		$('.theme-popover').slideUp(200);
	});

	
	//用于表单提交
	$("#J_Go").click(function(){
		
		 $.each($("input[type='radio']"), function(i, item){
			 
			 //判断哪个readio被选中
			 if($("#addrId_"+i).attr("checked"))
			 {
				 var name=$("#sname_"+i).val();
				 var address=$("#saddress_"+i).val();
				 var phone=$("#sphone_"+i).val();
				 
				   $("#sname").val(name);  

				   $("#saddress").val(address);

				   $("#sphone").val(phone); 
			 }
		 
		}); 
		 
		 $("#myorderform").submit();
		 
	});
	
	
	
	
});


function phoneFunction()
{
		var phone = $("#phone").val();
		if(phone=="")
		 {
			return ;
		 }else
			 {
			 	if(/^1[3|5|7|8|][0-9]{9}$/.test(phone))
	 		{
		 	    return true;
	 		}
			 	else
			 	{
			 		$("#phone").val("");
			 		document.getElementById("phone").focus();
			 		alert("手机号格式不正确！");
				//history.go(0);
			 		return false;
			 	}
			 }
}


	 
   
  
function mysubmit(){
	

				var name=$("#name").val();
				var address=$("#address").val();
				var phone=$("#phone").val();
				var val=name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+address+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+phone; 
				    
				if(name==""){
				    alert("姓名不能为空");
				    return false;
				 }
				 if(address==""){
				    alert("地址不能为空");
				    return false;
				 }
				 if(phone==""){
					    alert("电话不能为空");
					    return false;
					 } 
				    
				    $("#mytest").html("");
				    
				    $("#mytest").html(val);
				    
				    
				
				    $("#sname").html(name);  
				
				  $("#saddress").html(address);
				
				  $("#sphone").html(phone); 
  


$.ajax({
		cache:false,
		url:"${pageContext.request.contextPath }/cart/addAdress.do",
		data:{name:name,address:address,phone:phone},
		type:"POST",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		dataType:"text",
		success:function(result) {
		
			$('.theme-popover-mask').hide();
			
		    $('.theme-popover').slideUp(200);
		    
		    
		window.location.reload(); 
				
			//history.go(0);
		}
	});
 

};

$.ajax({
	  url: "orderConfirm.jsp",
	  cache: false,
	  success: function(html){
	    $("#mytest").append(jsp);
	  }
	});


</script>

	<style>
		#page{width:auto;}
		#comm-header-inner,#content{width:950px;margin:auto;}
		#logo{padding-top:26px;padding-bottom:12px;}
		#header .wrap-box{margin-top:-67px;}
		#logo .logo{position:relative;overflow:hidden;display:inline-block;width:140px;height:35px;font-size:35px;line-height:35px;color:#f40;}
		#logo .logo .i{position:absolute;width:140px;height:35px;top:0;left:0;background:url(http://a.tbcdn.cn/tbsp/img/header/logo.png);}
	</style>

  </head>
 
   
    
	<body>
     <!--添加新地址-->
      <div class="theme-popover-mask"></div>

      <div class="theme-popover">
	      <div class="theme-poptit">
		      <a href="javascript:;" title="关闭" class="close">×</a>
		      <h3>新增收货人信息</h3>
	      </div>
	      
	      <div class="theme-popbod dform">
	      
	      
		  <form class="theme-signin"  id="myform"   method="post">
			<ol>
				
				<li><strong>收货人：</strong><input required="required" class="ipt" type="text" id="name" name="name"  size="20" /></li>
				<li><strong>详细地址：</strong><textarea required="required" class="ipt"  name="address" id="address" style="width:400px;height:50px;"></textarea></li>
				<li><strong>手机号码：</strong><input class="ipt" type="text" required="required"  name="phone" id="phone"  size="11" onblur="phoneFunction()"/></li>
				<li><input   class="btn btn-primary" type="button"  value=" 添加 " onclick="mysubmit()" /></li>
			</ol>
		</form>
		
		
	    </div>
	    
	    
      </div>
      
        <jsp:include page="/pages/toper.jsp"/>
        <!--top end-->
        <!--head-->
        <div id="head">
        
            <!--logo start-->
           <jsp:include page="/pages/head.jsp"></jsp:include>     
           <!--logo end-->          
        </div>
        <!--head end-->
        
        <div style="margin:0 auto; clear:both;">
	        <div class="cartnav">
	                <ul>
	                <li ><a>我的购物车</a></li>
	                <li class="cartcurrent"><a>核对订单信息</a></li>
	                
	                <li><a>成功提交订单</a></li>
	                </ul>
	        </div>       
       </div> 
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        <form  id="myorderform"  method="post" action="order/createorder.do" >  
	         <input type="hidden"  id="cartItemIds"  name="cartItemIds"  value="${cartItemIds }"  /> 
	
	           <div id="content" class="grid-c">
	              <!--选择地址开始-->
	              <div id="address" class="address" style="margin-top: 20px;" >
	              
	            
	              
	              
	                <h3>收货人信息<!-- <span class="theme-buy">
	               
	                   <a class="btn btn-primary theme-login" style="color:#fff;float:right;margin-right:750px;" href="javascript:;">添加新地址</a>
                    </span> -->
	                  
	               </h3>
	               <ul id="address-list1" class="address-list">
	                   
	                 <!-- ysl -->  
	                   
	                   <input type="hidden" name="name"  id="sname" />
	                   
	                   <input type="hidden" name="address" id="saddress" />
	                   
	                   <input type="hidden" name="phone" id="sphone" />
	                   
	                 <!-- ysl -->    
	                   
	                <c:forEach  var="adress" items="${adressList }"   begin="0" end="3" varStatus="status">
	                <li class="     "  data-point-url="">
	                
	                   <input type="hidden" name="name_${status.index}"  id="sname_${status.index}" value="${adress.name }"/>
	                   
	                   <input type="hidden" name="address_${status.index}" id="saddress_${status.index}" value="${adress.adress }"/>
	                   
	                   <input type="hidden" name="phone_${status.index}" id="sphone_${status.index}" value="${adress.phone}"/>
	                   
	                   <input name="address" class="" type="radio" value="0" id="addrId_${status.index}" required="required" 
	                   
	                   <c:if test="${status.index==0 }">
	                   		checked="checked"
	                   </c:if>
	                    > 
	                    <label for="addrId" class="user-address">
	               
	                  <div  id="mytest" >
	                  
	                  ${adress.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                  
	                  ${adress.adress }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                  
	                  
	                   ${adress.phone }
	                   
	                   </div> 
	                  </label>
	                  </li>
	                </c:forEach>
	               
	               </ul>
	               <br>
  <a class="btn btn-primary theme-login" style="color:#fff; href="javascript:;">添加新地址</a>
	             </div>
	            <!--选择地址结束-->
	            <!--付款方式开始-->     
	           <div id="payment" class="address" style="margin-top: 20px;" >
	              <h3>付款方式</h3>
	              <ul id="address-list2" class="address-list">
	                  <li class="     "  data-point-url="http://log.mmstat.com/buy.1.20"> 
	                   <div  class="address-info">           
	                    <input name="payment" class="" type="radio" value="1" id="payId_1" data-point-url="#" checked="checked">
	                   <label for="payId_1" class="user-address">积分支付 </label>
	                   </div>
	                 </li>
	            
	                 
	             </ul>
	        
	           </div>
	           <!--付款方式结束--> 
	           <!--配送方式开始-->
	           <div id="delivery" class="address" style="margin-top: 20px;">
	              <h3>配送方式</h3>
	              <ul id="address-list3" class="address-list">
	                  <li class="     "  > 
	                   <div  class="address-info">           
	                    <input name="delivery" class="" type="radio" value="1" id="deliveryId_1" data-point-url="#" checked="checked">
	                   <label for="deliveryId_1" class="user-address">快递配送 </label>
	                   </div>
	                 </li>
	            
	                 <li class="  "
	         data-point-url="http://log.mmstat.com/buy.1.20" >
	                   <div  class="address-info"> 
	                     <input name="delivery" class="" type="radio" value="0" id="deliveryId_2" data-point-url="#">
	                     <label for="deliveryId_2" class="user-address">上门自提</label>
	                   </div>
	                </li>
	             </ul>
	        
	           </div>
	           <!--配送方式结束-->
	        
	        
	        
	        
	        
	          <!--订单信息开始-->
	       
	          <div>
	            <h3 class="dib">确认订单信息</h3>
	              <table cellspacing="0" cellpadding="0" class="order-table" id="J_OrderTable" summary="统一下单订单信息区域">
	              <caption style="display: none">统一下单订单信息区域</caption>
	                <thead>
	                  <tr>
	                    <th class="s-title">商品名称<hr/></th>
	                    <th class="s-price">单价(元)<hr/></th>
	                    <th class="s-amount">数量<hr/></th>
	                    <th class="s-total">小计(元)<hr/></th>
	                 </tr>
	               </thead>
	             
	        
	        
	               <tbody data-spm="3" class="J_Shop" data-tbcbid="0" data-outorderid="47285539868"  data-isb2c="false" data-postMode="2" data-sellerid="1704508670">
	              
	                  <tr class="first">
	                     <td colspan="5"></td>
	                  </tr>
	                  <tr class="shop blue-line">
	                     <td colspan="3">&nbsp;</td>
	                     <td colspan="2" class="promo"></td>
	                  </tr>
	                  <c:forEach items="${cartItems }" var="cartItem"> 
	                    
	                  <tr class="item" data-lineid="19614514619:31175333266:35612993875" data-pointRate="0">
	                     <td class="s-title">
	                        <a href="javascript:void(0);" target="_blank" title="${cartItem.goods.good_name }" class="" data-point-url="http://log.mmstat.com/buy.1.5">
	                          <img width="50" height="50"src="${pageContext.request.contextPath}${cartItem.goods.good_small_img}" class="itempic">
	                          <span class="title-link" data-point-url="">${cartItem.goods.good_name }</span>
	                        </a>
	                     </td>
	                     <td class="s-price">
	                        <span class='price '>
	                        <em class="style-normal-small-black J_ItemPrice"  >${cartItem.goods.good_nowprice }</em>
	                        </span>
	        
	                     </td>
	                     <td class="s-amount" data-point-url="">
	                        <input type="hidden" class="J_Quantity" value="1" name="19614514619_31175333266_35612993875_quantity"/>${cartItem.good_quantity }</td>
	                     
	                     <td class="s-total">
	                        <span class='price '>
	                        <em class="style-normal-bold-red J_ItemTotal "  >${cartItem.subtotal }</em>
	                        </span>
	                        <input id="furniture_service_list_b_47285539868" type="hidden" name="furniture_service_list_b_47285539868"/>
	                     </td>
	                  </tr>
	                  </c:forEach> 
	        
	        
	                 <tr class="item-service">
	                     <td colspan="5" class="servicearea" style="display: none"></td>
	                 </tr>
	        
	                 <tr class="blue-line" style="height: 2px;"><td colspan="5"></td></tr>
	                <!--  <tr class="other other-line">
	                     <td colspan="5">
	                       <ul class="dib-wrap">
	                           <li class="dib user-info">
	                             
	                                 <div class="field gbook">
	                                   <label class="label">添加备注：</label>
	                                     <textarea style="width:350px;height:40px;" title="选填：对本次交易的补充" name=""></textarea>
	                                 </div>
	                               
	                           </li>
	                           
	                      </ul>
	                   </td>
	                 </tr> -->
	                 <tr class="shop-total blue-line">
	                     <td colspan="5">
	                     <div class="statistic" style="float:right;">
	                     <div class="list">
	                      <span>
	                        <em class="ftx-01">${gquantity }</em>件商品，总积分：
	                      </span>
	                      <em id="warePriceId" class="price">${total}</em>
	                     </div>
	                     <div class="list">
	                      <span>
	                        运费：
	                      </span>
	                      <em id="warePriceId" class="price">￥0.00</em>
	                     </div>
	                     <div class="list">
	                      <span>
	                        应付总额：
	                      </span>
	                      <em id="warePriceId" class="price">￥0.00</em>
	                     </div>
	                     </div>
	                     </td>
	                 </tr>
	                 
	            </tbody>
	            
	            <tfoot>
	               <tr>
	                 <td colspan="5">
	                   <div class="order-go" data-spm="4">
	                   <div class="essConfirm address-confirm">
	                   <div class="kd-popup pop-back" style="margin-bottom: 40px;">
	                   <div class="box">
	                   <div class="bd">
	                   <div class="point-in">
	           
	                    <em class="t">应付积分：</em>  <span class='price g_price '>
	                    <input name="total" value="${total}" type="hidden"/>
	                    <em class="style-large-bold-red"  id="J_ActualFee"  >${total}</em>
	                    </span>
	                   </div>
	        
	                   <ul >
	                     <li><span id="Confirm" style="word-break: break-all;"></span></li>
	                     <li></li>
	                   </ul>
	                  </div>
	                  </div>
	                  <a href="cart/addtocart.do" class="back " target="_top" data-point-url="">返回购物车</a>
	                  <button id="J_Go" class=" btn-go"  data-point-url=""  type="button"  >提交订单<b class="dpl-button"></b></button>
	                  </div>
	                  </div>
	                  </div>
	                </td>
	               </tr>
	            </tfoot>
	         </table>
	        </div>
	       
	        </form>
	        <!--订单信息结束-->  
	        
	   </div>
	<c:if test="${empty adressList}">
	  ${msg};
	</c:if>
	 
	</body>
</html>
