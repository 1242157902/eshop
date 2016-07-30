// JavaScript Document
$(document).ready(function(){
	// 图片上下滚动
	var count = $("#imageMenu li").length -5; /* 显示 6 个 li标签内容 */
	var interval = $("#imageMenu li:first").width();
	
	var curIndex = 0;
	
	$('.scrollbutton').click(function(){
		if( $(this).hasClass('disabled') ) return false;
		
		if ($(this).hasClass('smallImgUp')){
			--curIndex;
		}
		else{
			++curIndex;
		}
		
		$('.scrollbutton').removeClass('disabled');
		if (curIndex == 0) $('.smallImgUp').addClass('disabled');
		if (curIndex == count-1) $('.smallImgDown').addClass('disabled');
		
		$("#imageMenu ul").stop(false, true).animate({"marginLeft" : -curIndex*interval + "px"}, 600);
	});	
	
	var midChangeHandler = null;
    $("#imageMenu li img").bind("click", function(){
		if ($(this).attr("id") != "onlickImg") {
			midChange($(this).attr("src").replace("upload/small", "upload/middle"));
			$("#imageMenu li").removeAttr("id");
			$(this).parent().attr("id", "onlickImg");
		}
	}).bind("mouseover", function(){
		if ($(this).attr("id") != "onlickImg") {
			window.clearTimeout(midChangeHandler);
			
			midChange($(this).attr("src").replace("upload/small", "upload/middle"));
			$(this).css({ "border": "1px solid red" });
		}
	}).bind("mouseout", function(){
		if($(this).attr("id") != "onlickImg"){
			$(this).removeAttr("style");
			midChangeHandler = window.setTimeout(function(){
				midChange($("#onlickImg img").attr("src").replace("upload/small", "upload/middle"));
			}, 1000);
		}
	});
    function midChange(src) {
        $("#midimg").attr("src", src).load(function() {
           
        });
    }
    
	var count = $("#cidRecommend li").length - 3; /* 显示 6 个 li标签内容 */
	var interval1 = $("#cidRecommend li:first").width();
	var curIndex = 0;
	
	$('.newarrow').click(function(){
		if( $(this).hasClass('disabled') ) return false;
		
		if ($(this).hasClass('back')){
		         --curIndex;
		}
		else{ 
		         ++curIndex;
	    }
		
		$('.newarrow').removeClass('disabled');
		if (curIndex == 0) $('.back').addClass('disabled');
		if (curIndex == count-1) $('.forward').addClass('disabled');
		
		$("#cidRecommend ul").stop(false, true).animate({"marginLeft" : -curIndex*interval1 + "px"}, 600);
	});	
	$("ul#ulexchangeType").find("li").on("click",function(){
		$(this).addClass("clk1");
	});
	$("dl#dl1").find("input").on("click",function(){
		var num=$("dl#dl1").find("input[name=proScount]").val();
		if($(this).attr("id")=="text_sub"){ 
		   if(num!=1){
			    num--;
		   }
		   $("dl#dl1").find("input[name=proScount]").val(num);
		}else if($(this).attr("id")=="text_add"){
		   num++;
		   $("dl#dl1").find("input[name=proScount]").val(num);
		}
	})
    
});