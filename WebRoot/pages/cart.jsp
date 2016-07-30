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
    
    <title>购物车</title>
  <link rel="stylesheet" type="text/css"  href="js/bootstrap/dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <link rel="stylesheet" href="css/merchandise.css" type="text/css"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"/>
    <link rel="stylesheet"  type="text/css"  href="css/jquery.autocomplete.css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
    <script src="js/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
	<script src="js/round.js"></script>
    <script type="text/javascript">
    $(function() {
	   showTotal();//计算总计
	
	 /*
	    给全选添加click事件
	   */
	$("#selectAll").click(function() {
		/*
		1. 获取全选的状态
		*/
		var bool = $("#selectAll").attr("checked");
		/*
		2. 让所有条目的复选框与全选的状态同步
		*/
		setItemCheckBox(bool);
		/*
		3. 让结算按钮与全选同步
		*/
		setJieSuan(bool);
		/*
		4. 重新计算总计
		*/
		showTotal();
	});
	
	/*
	给所有条目的复选框添加click事件
	*/
	$(":checkbox[name=checkboxBtn]").click(function() {
		var all = $(":checkbox[name=checkboxBtn]").length;//所有条目的个数
		var select = $(":checkbox[name=checkboxBtn][checked=true]").length;//获取所有被选择条目的个数

		if(all == select) {//全都选中了
			$("#selectAll").attr("checked", true);//勾选全选复选框
			setJieSuan(true);//让结算按钮有效
		} else if(select == 0) {//谁都没有选中
			$("#selectAll").attr("checked", false);//取消全选
			setJieSuan(false);//让结算失效
		} else {
			$("#selectAll").attr("checked", false);//取消全选
			setJieSuan(true);//让结算有效				
		}
		showTotal();//重新计算总计
	});
	
	/*
	给减号添加click事件
	*/
	$(".jian").click(function() {
		// 获取cartItemId
		var id = $(this).attr("id").substring(0, 32);
		
		// 获取输入框中的数量
		var quantity = $("#" + id + "Quantity").val();
		
		// 判断当前数量是否为1，如果为1,那就不是修改数量了，而是要删除了。
		if(quantity == 1) {
			if(confirm("您是否真要删除该条目？")) {
				location = "${pageContext.request.contextPath }/cart/delect.do?cartItemIds=" + id;
			}
		} else {
			sendUpdateQuantity(id, quantity-1);
		}
	});
	
	// 给加号添加click事件
	$(".jia").click(function() {
		// 获取cartItemId
		var id = $(this).attr("id").substring(0, 32);
		// 获取输入框中的数量
		var quantity = $("#" + id + "Quantity").val();
		sendUpdateQuantity(id, Number(quantity)+1);
	});
});

// 请求服务器，修改数量。
function sendUpdateQuantity(id, quantity) {
	$.ajax({
		async:false,
		cache:false,
		url:"${pageContext.request.contextPath }/cart/update.do",
		data:{cartItemId:id,quantity:quantity},
		type:"POST",
		dataType:"json",
		success:function(result) {
			//1. 修改数量
			$("#" + id + "Quantity").val(result.quantity);
			//2. 修改小计
			$("#" + id + "Subtotal").text(result.subtotal);
			//3. 重新计算总计
			showTotal();
		}
	});
}

/*
 * 计算总计
 */
function showTotal() {
	var total = 0;
	/*
	1. 获取所有的被勾选的条目复选框！循环遍历之
	
	
	*/
	

	$(":checkbox[name=checkboxBtn][checked=true]").each(function() {

		//2. 获取复选框的值，即其他元素的前缀
		var id = $(this).val();
		
		//3. 再通过前缀找到小计元素，获取其文本
		var text = $("#" + id + "Subtotal").text();
		
		//4. 累加计算
		total += Number(text);
	});
	// 5. 把总计显示在总计元素上
	$("#total").text(round(total, 2));//round()函数的作用是把total保留2位
}

/*
 * 统一设置所有条目的复选按钮
 */
function setItemCheckBox(bool) {
	$(":checkbox[name=checkboxBtn]").attr("checked", bool);
}

/*
 * 设置结算按钮样式
 */
function setJieSuan(bool) {
	if(bool) {
		$("#jiesuan").removeClass("kill").addClass("jiesuan");
		$("#jiesuan").unbind("click");//撤消当前元素止所有click事件
	} else {
		$("#jiesuan").removeClass("jiesuan").addClass("kill");
		$("#jiesuan").click(function() {return false;});
	}
	
}

/*
 * 批量删除
 */
function batchDelete() {
	// 1. 获取所有被选中条目的复选框
	// 2. 创建一数组，把所有被选中的复选框的值添加到数组中
	// 3. 指定location为CartItemServlet，参数method=batchDelete，参数cartItemIds=数组的toString()
	
	var cartItemIdArray = new Array();
	$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
		cartItemIdArray.push($(this).val());//把复选框的值添加到数组中
	});
	if(confirm("您是否真要删除选中条目？")) {
		location = "${pageContext.request.contextPath }/cart/delect.do?cartItemIds=" + cartItemIdArray;
	}
	
}




/*
 * 结算
 */
function jiesuan() {
	// 1. 获取所有被选择的条目的id，放到数组中
	var cartItemIdArray = new Array();
	
		
	if($(":checkbox[name=checkboxBtn][checked=true]").val()==null){
		alert("购物车为空！");
	}else{
	$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
		cartItemIdArray.push($(this).val());//把复选框的值添加到数组中
	});	
	// 2. 把数组的值toString()，然后赋给表单的cartItemIds这个hidden
	$("#cartItemIds").val(cartItemIdArray.toString());
	// 把总计的值，也保存到表单中
	$("#hiddenTotal").val($("#total").text());
	// 3. 提交这个表单
	$("#jieSuanForm").submit();}
}


</script>
  </head>
  
  <body>
        <!---top--->
        <jsp:include page="/pages/toper.jsp"/>
        <!--top end-->
        
        <!--head-->
        <div id="head">
        
           <jsp:include page="/pages/head.jsp"></jsp:include>
           
        </div>
        <!--head end-->
        <!--main-body start-->

        <div id="CarContent" class="pay-content">
            <div class="cartnav">
                <ul>
                <li class="cartcurrent"><a>我的购物车</a></li>
                <li><a>核对订单信息</a></li>
                
                <li><a>提交订单</a></li>
                </ul>
            </div>
            <div class="pay-state">
                <h1 class="fl"> 我的购物车</h1>
            </div>
            <table class="table-pro ct1" width="1100px;" cellspacing="0" cellpadding="0">
                <thead> 
                    <tr style="wdith:1100px;"  class="tPro fl">
                    <td  style="width:123px;">
                    <input class="psc-check" id="selectAll" checked="checked" style="margin-left:18px;"  type="checkbox" /><label for="selectAll">全选</label>
                    </td> 
                    <td   class="pname"    > 商品名称 </td>
                    <td> 支付方式 </td>
                    <td> 所需积分 </td>
                    <td> 所需现金 </td>
                    <td> 数量 </td>
                    <td> 总共 </td>
                    <td> 操作 </td>
                    </tr>
                </thead> 
           
           
           
           
           
           
           
                <tbody>
                  <c:forEach items="${cartItems }" var="cartItem">
	                 <tr align="center"    class="tPro fl">
		                <td >
			              <input value="${cartItem.cartitemid }" type="checkbox" name="checkboxBtn" checked="checked"/>
		               </td>
		               
		             <td class="pname" >
                            <img width="50" height="50" onclick="ProInfoByPid('1138');" alt="${cartItem.goods.good_name }" src="${pageContext.request.contextPath}${cartItem.goods.good_small_img }">
                            <div class="name">
                            <a class="fl" href="" title="${cartItem.goods.good_name }"> ${cartItem.goods.good_name }</a>
                           </div>
                    </td>
		              <td > 积分 </td>
		             <td >
		                <span class="currPrice">${cartItem.goods.good_nowprice}</span>
		             </td>
		             <td > 0.00 </td>
		             <td >
			           <a class="jian" id="${cartItem.cartitemid }Jian"></a><input   class="quantity q-height" readonly id="${cartItem.cartitemid }Quantity" type="text" value="${cartItem.good_quantity }"/>
			           <a class="jia" id="${cartItem.cartitemid }Jia"></a>
		            </td>
		            <td >
			         <span class="subTotal" id="${cartItem.cartitemid }Subtotal">${cartItem.subtotal }</span>
		          </td> 
		           <td >
			         <a href="<c:url value='cart/delect.do?cartItemIds=${cartItem.cartitemid }'/>">删除</a>
		          </td>
	            </tr>
             </c:forEach>
             </tbody>
             <tr ><td style="border-top:1px solid #d7d7d5;width:1100px;"></td></tr> 
             
            <tr  class="tPro fl">
		       <td   colspan="4"   class="cart-info">
			     <a href="javascript:batchDelete();">批量删除</a>
		       </td>
		       <td  colspan="3"  align="left" class="cart-info" style="width:300px;">
			      <span>需要积分：</span><span class="price_t"><span id="total"></span></span>
		        </td>
	      </tr>
	      <tr  class="tPr">
		      <td  align="right" class="cart-info" style="border-bottom:1px solid #d7d7d5;width:1100px;">
			     <a href="javascript:jiesuan();" id="jiesuan" class="jiesuan"  >积分兑换</a>
		  </td>
	    </tr>
	   
      </table>
	<form id="jieSuanForm" action="<c:url value='/cart/loadcartitems.do'/>" method="post">
		<input type="hidden" name="cartItemIds" id="cartItemIds"/>
		<input type="hidden" name="total" id="hiddenTotal"/>
	</form>
 </table>          
                    

          </div> 
          
        </div>
        <!--main-body end-->
        <div style="clear:both"></div>
        <jsp:include page="/pages/footer.jsp"/>
        
        
        ${msg};
  </body>
</html>
 