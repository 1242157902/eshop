/**
 * 搜索选择
 */
function isEmpty(target) {
	if(target == null || target == undefined || target == "") {
		return true;
	} else {
		return false;
	}
}
$(function(){
	/*$(".logo-search .search-menu").find("li").click(function(){
		var index=$(".logo-search .search-menu").find("li").index(this);		
		$(".logo-search .search-menu").find("li").removeClass("current");
		$(this).toggleClass("current");
		$(".logo-search .search-input .iKey").find("input").toggleClass("hide");
		var input=$(".logo-search .search-input .iKey").find("input").eq(index);
		input.removeClass("hide");		
	});*/
	$.ajax({
     	type:"get",
         url: "good/getKeyWord.do",
         dataType: "json",			           			         
         success: function( obj ) {		          
            $("#keyword").focus().autocomplete(obj,{minLength:1});
         }
    });

});

function submitForm() {
	var keyword=$("#keyword").val();
	if(isEmpty(keyword)){	
		return false;
	}
    else{
    	return true;
    }

};