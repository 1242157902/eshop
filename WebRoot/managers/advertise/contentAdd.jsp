<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>AddContent</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/demo/demo.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/icon.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/easyloader.js"></script> 

    <style type="text/css">
    
    #imgA {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}  
    </style>
    
    <script type="text/javascript">
    var maxsize=1024*1024;//100kb     
    var check="格式错误";
    var picarea;
    var picsize;
    var ratio;
    function previewImage(file)  
    {  
        picarea=0;
        picsize=0;
        ratio=1;
        var MAXWIDTH  = 600;  
        var MAXHEIGHT = 400;  
        check="格式错误";
        var div = document.getElementById('imgApane');  
        //var ppdiv= document.getElementById('imgBpane');  
        if (file.files && file.files[0])  
        {  
		   div.innerHTML = '<div id=imgAdiv ><img id=imgA></div>';  
		  // ppdiv.innerHTML = '<div id=imgBdiv ><img id=imgB></div>';  
		   var imga = document.getElementById('imgA'); 	
		  // var imgb = document.getElementById('imgB'); 	
		   var imgadiv =document.getElementById('imgAdiv'); 	
	       imga.onload = function() { 
		       check="格式正确";
		       var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, imga.offsetWidth, imga.offsetHeight);  
		       picarea=imga.offsetWidth*imga.offsetHeight;
		       ratio=imga.offsetWidth/rect.width;
		       imga.width = rect.width;  
		       imga.height = rect.height; 
			   imgadiv.width = rect.width;  
		       imgadiv.height = rect.height; 
			   //hehe();
			  // getfsize();
		   };
		   var reader = new FileReader();  
		   reader.onload = function(evt){
			 imga.src = evt.target.result;  
			 //imgb.src = evt.target.result;  
		   };
           reader.readAsDataURL(file.files[0]);   
	    } 
	   else  //ie
	   { 
	    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';  
	    file.select();  
	    var src = document.selection.createRange().text;  
	    div.innerHTML = '<img id="imgA" >';  
	    var imga = document.getElementById("imgA");
		try{ 
		     imga.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		     //imga.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").sizingMethod="image";
		     setTimeout( function(){
		     var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, imga.offsetWidth, imga.offsetHeight); 
             picarea=imga.offsetWidth*imga.offsetHeight;
             ratio=imga.offsetWidth/rect.width;
		     //status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);  
		     div.innerHTML = "<div id=imgAdiv style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";  
		    // ppdiv.innerHTML = "<div id=imgBdiv style='width:"+rect.width+"px;height:"+rect.height+"px;"+sFilter+src+"\"'></div>";  
		    // hehe();
		     check="格式正确";
		     //getfsize();
		     },100);
		 }
		 catch(err) {
			 check="格式错误";
			 //ppdiv.innerHTML = "<div id=imgBdiv style='width:0px;height:0px;'></div>";  	    
	     }
	   }  
	} 
    function clacImgZoomParam( maxWidth, maxHeight, width, height ){  
    var param = {top:0, left:0, width:width, height:height};  
    if( width>maxWidth || height>maxHeight )  
    {  
        rateWidth = width / maxWidth;  
        rateHeight = height / maxHeight;  
          
        if( rateWidth > rateHeight )  
        {  
            param.width =  maxWidth;  
            param.height = Math.round(height / rateWidth);  
            
        }else  
        {  
            param.width = Math.round(width / rateHeight);  
            param.height = maxHeight;  
        }
    }        
    param.left = Math.round((maxWidth - param.width) / 2);  
    param.top = Math.round((maxHeight - param.height) / 2);  
    return param;  
}  
</script>
  </head>
  
  <body> 
  <form id=contentform action="advertise/UploadImg.do" method="post" enctype="multipart/form-data">   
     <input type="hidden" name="ext" id="ext"/>
  <table id="addcttable"  border="1" cellpadding="1"  bordercolor="gray"> 
       <tr>                             
           <td colspan=3 style="text-align:center;" > 添加内容信息   </td>   
           <td rowspan=8 style="width:800px;height:360px;text-align:center;" >
             <div id="imgApane"> <div id=imgAdiv><img id="imgA"/> </div> </div>  
           </td>                
       </tr>      
         <tr>                             
           <td style="text-align:center;" ><span style="font-size:12px;">上传附件(800*352)</span></td>              
           <td style="text-align:center;" colspan=2><input id="namex" type="file" name="img" style="width:300px;"  onchange="previewImage(this);" /> </td>              
       </tr> 
       <tr>                             
           <td colspan=3 style="text-align:center;" >
           <input id=btn1 type="submit" value="保存"  onClick="return ctsave()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
           <input id=btn2 type="button" value="取消">
            
           </td>               
       </tr>     
                                     
</table>  
</form>
    <script type="text/javascript">
   
     
    function ctsave()
	{
	
	  if( $("#namex").val()=='')  {alert("您还没有上传图片");return false;}
	 // if(check!="格式错误")   checksize();
	  //if(check!="格式正确")   {alert(check);return false;}
	
      //var actualx=$('#imgx').val()*ratio; 
      //var actualy=$('#imgy').val()*ratio; 
	  //var actualw=$('#imgw').val()*ratio; 
	  //var actualh=$('#imgh').val()*ratio; 
      
      //$('#imgx').val(Math.round(actualx));   
      //$('#imgy').val(Math.round(actualy));   
      //$('#imgw').val(Math.round(actualw));   
      //$('#imgh').val(Math.round(actualh));   
    // alert("x="+$('#imgx').val()+",y="+$('#imgy').val()+",w="+$('#imgw').val()+",h="+$('#imgh').val());
	 
	  return true;
	}	
  

</script>
  </body>
</html>
