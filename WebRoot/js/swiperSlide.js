// JavaScript Document
$(function(){
    var newSlideSize = function slideSize(){
        var w = Math.ceil($(".swiper-container").width()/8.5);/*--调整高度---*/
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
});

