<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stock.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/commons.js"></script>

<script type="text/javascript">

$(function(){
	//导出excel表格
	$('#outbtn').click(function(){
		  var starttime = $("input[name='create_time']").val();
		  var endtime = $("input[name='endtime']").val();
		 	//location.href = "/eshop/order/showOrderListByXls.do?create_time="+starttime+"&endtime="+endtime;
		 	location.href = "order/showOrderListByXls.do?create_time="+starttime+"&endtime="+endtime;
	});
	
	var editing ; //判断用户是否处于编辑状态 
	var flag  ;	  //判断新增和修改方法
	$('#t_user').datagrid({
				idField:'nick_name' ,
				title:'订单信息' , 
				fitColumns: true  ,
				url:'order/showOrder.do' ,		//修改请求的url
				striped: true ,					
				loadMsg: '数据正在加载,请耐心的等待...' ,
				rownumbers:true ,
				frozenColumns:[[
						{field:'ck' , checkbox:true}									
				]],
				columns:[[
					{
						field:'nick_name' ,
						title:'工号' ,
						width:50 ,
						align:'center' ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						} /* ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'订单号必填!'
							}
						}  */
					},
					{
						field:'good_name' ,
						title:'商品名' ,
						width:100  ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品名必填!' ,
							}
						}
					},
					{
						field:'good_quantity' ,
						title:'商品数量' ,
						width:50  ,
					/* 	formatter:function(value , record , index){
							if(value == 2){
								return '<span  >待出库</span>' ;
							}  
						},	 */					
				/* 		editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'订单状态必填!' ,
								min:0 , 
								max:5
							}
						} */
					},	{
						field:'consignee_name' ,
						title:'姓名' ,
						width:100 ,
						align:'center' ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						} ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'姓名必填!'
							}
						} 
					}/* ,{
						field:'consignee_phone' , 
						title:'收货人手机号' ,
						width:50 ,
						styler:function(value , record){
							 if(value == 'admin'){
								return 'background:blue;';
							} 
						} , 
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'收货人手机号必填!'
							}
						}
					} */,{
						field:'consignee_address' , 
						title:'部门' ,
						width:150 ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						} , 
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'部门必填!'
							}
						}
					}
					/* ,{
						field:'consignee_zipcode' , 
						title:'收货人邮编' ,
						width:100 ,
						styler:function(value , record){
							 if(value == 'admin'){
								return 'background:blue;';
							} 
						} , 
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'收货人邮编必填!'
							}
						}
					} */
				]] ,
				pagination: false , 
				pageSize: 5 ,
				pageList:[5,10,15,20,50] ,
				toolbar:[
				/* 	{
						text:'新增库单',
						iconCls:'icon-add' , 
						handler:function(){
										if(editing == undefined){
											flag = 'add';
											//1 先取消所有的选中状态
											$('#t_user').datagrid('unselectAll');
											//2追加一行
											$('#t_user').datagrid('appendRow',{description:''});
											//3获取当前页的行号
											editing = $('#t_user').datagrid('getRows').length -1;
											//4开启编辑状态
											$('#t_user').datagrid('beginEdit', editing);
										}
								}
					}, {
						text:'修改订单',
						iconCls:'icon-edit' , 
						handler:function(){
								var arr = $('#t_user').datagrid('getSelections');
								if(arr.length != 1){
										$.messager.show({
											title:'提示信息',
											msg:'只能选择一条记录进行修改!' 
										});
								} else {
									if(editing == undefined){
										flag = 'edit';
										
										//根据行记录对象获取该行的索引位置
										editing = $('#t_user').datagrid('getRowIndex' , arr[0]);
										//开启编辑状态
										$('#t_user').datagrid('beginEdit',editing);
									}
								}
								
						}										
					},{
						text:'保存订单',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								if($('#t_user').datagrid('validateRow',editing)){
										$('#t_user').datagrid('endEdit', editing);
										editing = undefined;
								}
						}
					},{
						text:'订单导出',
						iconCls:'icon-edit' , 
						handler:function(){
								
								
								$.post('order/showOrderListByXls.do' , {create_time:$("#create_time").val(),endtime:$("#endtime").val()},function(result){
									    
									    $.messager.show({
											title:'提示信息',
											msg:result.message
										});
								},'html');
										
								
								
							}
					
					
					}*//* ,{
						text:'保存订单',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								if($('#t_user').datagrid('validateRow',editing)){
										$('#t_user').datagrid('endEdit', editing);
										editing = undefined;
								}
						}
					} ,{
						text:'库单明细',
						iconCls:'icon-edit' , 
						handler:function(){
							var arr = $('#t_user').datagrid('getSelections');
							if(arr.length <= 0 ){
								$.messager.show({
									title:'提示信息',
									msg:'请选择进行生成操作!'
								});											
							} else {
										var order_ids = '';
										for(var i = 0 ; i < arr.length ; i++){
											order_ids += arr[i].order_id + ',';
										}
										order_ids = order_ids.substring(0,order_ids.length-1);
										
							}
						}
					},{
						text:'取消操作',
						iconCls:'icon-cancel' , 
						handler:function(){
							//回滚数据 
							$('#t_user').datagrid('rejectChanges');
							editing = undefined;
						}
					}	*/
				] ,
				onAfterEdit:function(index , record){
					$.post(flag=='add'?'stock/addStock.do':'order/updateOrder.do' , record , function(result){
							$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
					});
				}
		});
	
	$('#searchbtn').click(function(){
		$('#t_user').datagrid('load' ,serializeForm($('#form')));
	});
	
	
	
	
	$('#clearbtn').click(function(){
		$('#form').form('clear');
		$('#t_user').datagrid('load' ,{});
	});
});


			
			
			
			
			
			
			//js方法：序列化表单 			
			function serializeForm(form){
				var obj = {};
				$.each(form.serializeArray(),function(index){
					if(obj[this['name']]){
						obj[this['name']] = obj[this['name']] + ','+this['value'];
					} else {
						obj[this['name']] =this['value'];
					}
				});
				return obj;
			}
			
			
			
			
</script>
		

  </head>
  
  <body>
 
 
	<div id="cc" class="easyui-layout"  fit=true  style="width:100%;height:100%;">  
		  <div region="north"   title="" split="false" style="height:100px;text-align: left;"  >
		    
					  <br>
				     	 <div style="text-align: left;" >	
						    	 <form    id="form"  action="" method="post"> 
						   　　 	 开始时间:
				    					<input  type="text" name="create_time"   id="create_time"  class="easyui-datetimebox"   data-options="prompt:'请输入订单时间'"     value=""  style="margin-right: 10px;"  />
				    			 结束时间:
				    					<input  type="text" name="endtime"   id="endtime"  class="easyui-datetimebox"   data-options="prompt:'请输入结束时间'"     value=""  style="margin-right: 10px;"  />
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="outbtn" ">导出</a>
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a>
						    	 </form>
				    	 </div>  
				    	
				    </div>    
		 
		 <div region="center"  style="width: 100%;height:50%;">
			  			<table id="t_user"  style="width: 100%;"></table>
		    </div>  
		    
		</div>  

  </body>
</html>
