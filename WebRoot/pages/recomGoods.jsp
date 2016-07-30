<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div class="recommend">
         <div class="r-title"> 相关推荐</div>
         
         <div class="newpro_next">
         
             <a class="newarrow back disabled"></a>
             <div id="cidRecommend" class="pic_scroll">
             
                 <ul class="box-b" style="margin-left: 0px;">
                    <c:forEach items="${recomGoods}" var="good">
                     <li>
                         <div class="img">
                             <a href="good/lookGoodById.do?good_id=${good.good_id}">
                             <img src="${pageContext.request.contextPath }${good.good_category_img}">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="good/lookGoodById.do?good_id=${good.good_id}">${good.good_name}</a>
                         </div>
                     </li>
                   </c:forEach> 
                     <!-- <li>
                         <div class="img">
                             <a href="#">
                             <img src="images/recommend/20141008045517357.jpg_160_160.jpg">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="#">美国Eagle Creek 逸客黑色旅行鞋袋ECD41234010</a>
                         </div>
                     </li>
                     <li>
                         <div class="img">
                             <a href="#">
                             <img src="images/recommend/20141226051808691.jpg_160_160.jpg">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="#">美国Eagle Creek 逸客黑色旅行鞋袋ECD41234010</a>
                         </div>
                     </li>
                     <li>
                         <div class="img">
                             <a href="#">
                             <img src="images/recommend/201407310957473382.jpg_160_160.jpg">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="#">美国Eagle Creek 逸客黑色旅行鞋袋ECD41234010</a>
                         </div>
                     </li>
                     <li>
                         <div class="img">
                             <a href="#">
                             <img src="images/recommend/201410080459333188.jpg_160_160.jpg">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="#">美国Eagle Creek 逸客黑色旅行鞋袋ECD41234010</a>
                         </div>
                     </li>
                     <li>
                         <div class="img">
                             <a href="#">
                             <img src="images/recommend/201501040354049903.jpg_160_160.jpg">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="#">美国Eagle Creek 逸客黑色旅行鞋袋ECD41234010</a>
                         </div>
                     </li>
                     <li>
                         <div class="img">
                             <a href="#">
                             <img src="images/recommend/201506080132584090.jpg_160_160.jpg">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="#">美国Eagle Creek 逸客黑色旅行鞋袋ECD41234010</a>
                         </div>
                     </li>
                     <li>
                         <div class="img">
                             <a href="#">
                             <img src="images/recommend/201506080154162861.jpg_160_160.jpg">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="#">美国Eagle Creek 逸客黑色旅行鞋袋ECD41234010</a>
                         </div>
                     </li>
                     <li>
                         <div class="img">
                             <a href="#">
                             <img src="images/recommend/201506080427453757.jpg_160_160.jpg">
                             </a>
                         </div>
                         
                         <div class="title">
                             <a href="#">美国Eagle Creek 逸客黑色旅行鞋袋ECD41234010</a>
                         </div>
                     </li> -->
                 </ul>
             </div>
             <a class="newarrow forward"></a>
         </div>
         
</div>