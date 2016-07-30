<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adverList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/demo/demo.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script> 
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/easyloader.js"></script> 
		

  </head>
  
  <body style="padding:0px;">
	   <div id="contentlistlay" class="easyui-layout" style="width: 100%;height:100%" >
			<div region="center" >
				<table id="advlist"></table>
			</div>
		</div>
   <script type="text/javascript">	
	 $(function(){
		$('#advlist').datagrid({
			idField:'id',	
			title:'图片信息列表',
			//width:2000,
			fit:true,
			height:450 ,
			url:'advertise/queryQueue.do' ,
			fitColumns:true ,  
			striped: true ,					//隔行变色特性 
			//nowrap: false ,				//折行显示 为true 显示在一会 
			loadMsg: '数据正在加载,请耐心的等待...' ,
			rownumbers:true ,
			editable:true, 
			singleSelect:false,	
			frozenColumns:[[
							{field:'id' , checkbox:true}									
					]],
			columns:[[

				{
					field:'que' ,
					title:'图片详情' ,
					width:400 ,
					height:50
				},
				{
					field:'showtime' ,
					title:'展示时间' ,
					width:150 ,
					height:50
				},
				{
					field:'createtime' ,
					title:'更新时间' ,
					width:150 ,
					height:50
				},
				{
					field:'opername' ,
					title:'操作员' ,
					width:150, 
					height:50
				}
				]] ,
			pagination: true , 
			pageSize: 20,
			pageList:[5,10,15,20,50], 
			toolbar: [
            {
               text: "删除",
               iconCls: "icon-remove",
               handler:function (){
                        if(fctrow()==-1) alert("请选择一行!");
                        else { 
                         $.messager.confirm('确认','确认删除?',function(data){  
                           if(data){
                            var selectedRow = $('#advlist').datagrid('getSelected');
                            $.ajax({ 
	                            type: "post",  
	                            url: "advertise/deleteListById.do", 
	                            data: {id:selectedRow.id},  
	                            dataType: "json",  
	                            success: function(data) {
	                            	if(data.status=="true"){
	                            	    alert(data.msg); $('#advlist').datagrid('reload');
	                            	}else{
	                            		alert(data.msg);
	                            	}
	                            }
               
                            });   
                           $('#advlist').datagrid('reload');  }
                         });  
			            }}	 
             }]
			});
	});
	
	
    function fctrow(){
        var row = $('#advlist').datagrid('getSelected');
        return $('#advlist').datagrid('getRowIndex',row);       
    }
	</script>
  </body>
</html>
