$(function(){
   function caculateNum(){
	    var totalnum=0;
		var length=$(".tPro .psc-check02:checked").length;
		for(var i=0;i<length;i++){
			var num=parseInt($(".icount").eq(i).val());
			 totalnum+=num;		
		}
		return totalnum;
		
   }
	function caculateIntegral(){
		var totalIntegral=0;
		var length=$(".tPro .psc-check02:checked").length;
	    for(var i=0;i<length;i++){
			var num=parseInt($(".icount").eq(i).val());
			var integral=parseInt($(".integral").eq(i).text());
			
			 totalIntegral+=num*integral;		
		}
		return totalIntegral;
	}
	
	$(".psc-check,.psc-check03").bind("click", function () {
        if ($(this).is(":checked")) {
            $(".tPro .psc-check02").attr("checked", 'true');
            $(".psc-check").attr("checked", 'true');
            $(".psc-check03").attr("checked", 'true');
			$("#pidPointCount").text(caculateNum());
			$("#order-box-point").show();
        }
        else {
            $(".tPro .psc-check02").removeAttr("checked");
            $(".psc-check03").removeAttr("checked");
            $(".psc-check").removeAttr("checked");
			$("#order-box-point").hide();
        }  
		$("#pidPointCount").text(caculateNum());
		$("#pidTotalPoint").text(caculateIntegral());
		
    });
    //单选,单取消
    $(".tPro .psc-check02").bind("click", function () {
        if ($(this).is(":checked")) {
            $(this).attr("checked", 'true');
            var checkCount = 0;
            $(".tPro .psc-check02").each(function () {
                if ($(this).is(":checked")) {
                    checkCount++;
                }
            });
            if (checkCount == $(".tPro .psc-check02").length) {
                $(".psc-check").attr("checked", 'true');
                $(".psc-check03").attr("checked", 'true');
            }
        } else {
            //单选取消 
            $(this).removeAttr("checked");
            $(".psc-check").removeAttr("checked");
            $(".psc-check03").removeAttr("checked");
        }
		$("#pidPointCount").text(caculateNum());
		$("#pidTotalPoint").text(caculateIntegral());
        
    });
	
	$(".pro-warn .next").bind("click", function () {
        var index = $(".pro-warn .next").index(this);;
		console.info(index);
        var num = $(".icount").eq(index).val();
		console.info(num);
        num = parseInt(num) - 1;
        if (num > 0) {
            //var result = showUpdateData(index, -1); //更新数目减一
            //if (result) {
                $(".icount").eq(index).val(num);  //更新购物数
				$("#pidPointCount").text(caculateNum());
				$("#pidTotalPoint").text(caculateIntegral());
                //$(".icount").eq(index).attr("oldnum", num); //更新老的购物数
               // var ischeck = $(".psc-check02").eq(index).is(":checked"); //是否选择
               /* var myCartServer = new MyCartServer();
                myCartServer.LoadData();
                if (ischeck) { //如果更新的是选择项 重新计算数据
                    myCartServer.CalculateTotal();
                }
                myCartServer.UpdateCartNumShow(); //更新顶部小购物车数目*/
           // }
        } else {
            showErrorTip("购买数量最小是1", index);
        }
        //return false;

    });

    //增加
    $(".pro-warn .prev").bind("click", function () {
        var index = $(".pro-warn .prev").index(this);
        var num = $(".icount").eq(index).val();
        //var kc = $(".icount").eq(index).attr("prokc"); //库存
        //if (parseInt($("#carNum").html()) < 50) {
            num = parseInt(num) + 1;
            //if (num > kc) {
             //   num = kc;
            //}
            //var result = showUpdateData(index, 1);
            //if (result) {
                $(".icount").eq(index).val(num);
				$("#pidPointCount").text(caculateNum());
				$("#pidTotalPoint").text(caculateIntegral());
               // $(".icount").eq(index).attr("oldnum", num); //数据一致
                //var ischeck = $(".psc-check02").eq(index).is(":checked"); //是否选择
                /*var myCartServer = new MyCartServer();
                myCartServer.LoadData();
                if (ischeck) {
                    myCartServer.CalculateTotal();
                }
                myCartServer.UpdateCartNumShow();*/
            //}
        //} else {
          //  showErrorTip("购物车已满，请重新添加！", index);
        //}
        //return false;

    });
    //更改失去焦点
    $(".pro-warn .icount").bind("change", function () {
        var index = $(".pro-warn .icount").index(this);
        var oldnum = $(this).attr("oldnum"); //老的数字
        var kc = $(this).attr("prokc"); //库存
        var num = $(this).val(); //更改的产品数目 
        if (!isNaN(num)) { //如果是数字
            if (parseInt(num) > 0) {
                if (parseInt(num) <= parseInt(kc)) { //小于库存
                    var buynum = parseInt(num) - parseInt(oldnum);
                    if ((parseInt($("#carNum").html()) + buynum) <= 50) {
                        var result = showUpdateData(index, buynum);
                        if (result) {
                            $(".icount").eq(index).val(num);
                            $(".icount").eq(index).attr("oldnum", num); //数据一致
                            var ischeck = $(".psc-check02").eq(index).is(":checked"); //是否选择
                            var myCartServer = new MyCartServer();
                            myCartServer.LoadData();
                            if (ischeck) {
                                myCartServer.CalculateTotal();
                            }
                            myCartServer.UpdateCartNumShow();
                        }
                    } else {
                        $(this).val(oldnum);
                        $(this).attr("oldnum", oldnum); //数据一致
                        showErrorTip("购物车已满，请重新添加！", index);
                    }

                } else {
                    //提示存库
                    showErrorTip("该商品的库存为：" + kc, index);
                    $(this).val(oldnum);
                    $(this).attr("oldnum", oldnum); //数据一致
                }
            } else {
                //提示最小是1
                showErrorTip("购买数量最小是1", index);
                $(this).val(oldnum);
                $(this).attr("oldnum", oldnum); //数据一致
            }
        } else {
            showErrorTip("购买数量只能是整数", index);
            $(this).val(oldnum);
            $(this).eq(index).attr("oldnum", oldnum); //数据一致
        }
        return false;
    });

    //错误提示信息
    function showErrorTip(cont, index) {
        var error = $(".errortip").eq(index);
        error.html(cont);
        error.show();
        setTimeout(function () {
            $(".errortip").eq(index).hide();
        }, 2000);
    }

});