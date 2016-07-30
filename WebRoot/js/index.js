/**
 * 首页图片轮播
 */
$(function(){
	$("#swiper-wrapper").find("div").remove();
	$.ajax({
    	url:"advertise/getAdvList.do",
    	type:"post",
    	dataType:"json",
 		success:function(obj){
 			 if(obj!=null){ 
 				 var queue=obj[0].que;
 				 var que=queue.split(",");
 				 var img="";
 				 for(var i=0;i<que.length;i++){
 					 img+="<div class='swiper-slide'><a><img src='images/advertise/"+que[i]+"'></a></div>";
 				 }
 				$("#swiper-wrapper").append(img);
 				 
 			 }
 			var newSlideSize = function slideSize(){
 		        var w = Math.ceil($(".swiper-container").width()/2.5);//--调整高度---
 		        $(".swiper-container,.swiper-wrapper,.swiper-slide").height(w);
 		    };
 			
 		    newSlideSize();
 		    $(window).resize(function(){
 		        newSlideSize();
 		    });

 		    var mySwiper = new Swiper('.swiper-container',{
 		        pagination: '.pagination',
 		        loop:true,
 		        autoplay:6000,
 		        paginationClickable: true,
 		        onSlideChangeStart: function(){
 		            //回调函数
 		        }
 		    });
 		 }
    });
 
});

