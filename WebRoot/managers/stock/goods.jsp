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
	
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/commons.js"></script>

<script type="text/javascript">

$(function(){
	
	

	var editing1 ; //判断用户是否处于编辑状态 
	var flag1  ;	  //判断新增和修改方法
	$('#t_user1').datagrid({
				idField:'tmp_in_id',
				title:'详细信息' , 
				fitColumns: true  ,
				url:'good/queryTmpInStock.do' ,				//修改请求的url
				striped: true ,					
				loadMsg: '数据正在加载,请耐心的等待...' ,
				rownumbers:true ,
				frozenColumns:[[
						{field:'ck' , checkbox:true}									
				]],
				columns:[[
					/* {
						field:'in_id' ,
						title:'入库单号' ,
						width:150 ,
						align:'center' ,
						styler:function(value , record){
							 if(value == 'admin'){
								return 'background:blue;';
							} 
						}   ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'入库单号必填!'
							}
						}  
					}, */
					{
						field:'good_id' ,
						title:'商品id' ,
						width:250 ,
						align:'center' ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						}/*  ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品id必填!'
							}
						}  */
					},
					{
						field:'good_name' ,
						title:'商品名' ,
						width:150 ,
						align:'center' ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						} /*  ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品名必填!'
							}
						}  */
					},{
						field:'good_quantity' ,
						title:'商品数量' ,
						width:100  ,
						formatter:function(value , record , index){
							 
								return '<span style=color:red; >'+value+'</span>' ;
							 
						} ,						
						 editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'商品数量必填!' 
							}
						} 
					},{
						field:'good_price' ,
						title:'商品价格' ,
						width:100  ,
					/* 	editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'实际库存必填!' ,
								min:0 , 
								max:150 ,
								precision:0
							}
						} */
					},{
						field:'subtotal' ,
						title:'小计' ,
						width:100  ,
						/* editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'实际库存必填!' ,
								min:0 , 
								max:150 ,
								precision:0
							}
						} */
					},{
						field:'manager_name' ,
						title:'操作员' ,
						width:100 ,
						align:'center' ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						}/*  ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'操作员必填!'
							}
						}  */
					}
				]] ,
				pagination: true , 
				pageSize: 10 ,
				pageList:[5,10,15,20,50] ,
				toolbar:[
					/*  {
						text:'新增入库单',
						iconCls:'icon-add' , 
						handler:function(){
										if(editing1 == undefined){
											flag1 = 'add';
											//1 先取消所有的选中状态
											$('#t_user1').datagrid('unselectAll');
											//2追加一行
											$('#t_user1').datagrid('appendRow',{description:''});
											//3获取当前页的行号
											editing1 = $('#t_user1').datagrid('getRows').length -1;
											//4开启编辑状态
											$('#t_user1').datagrid('beginEdit', editing1);
										}
								}
					}, */ {
						text:'修改',
						iconCls:'icon-edit' , 
						handler:function(){
								var arr = $('#t_user1').datagrid('getSelections');
								if(arr.length != 1){
										$.messager.show({
											title:'提示信息',
											msg:'只能选择一条记录进行修改!' 
										});
								} else {
									if(editing1 == undefined){
										flag1 = 'edit';
										
										//根据行记录对象获取该行的索引位置
										editing1 = $('#t_user1').datagrid('getRowIndex' , arr[0]);
										//开启编辑状态
										$('#t_user1').datagrid('beginEdit',editing1);
									}
								}
								
						}										
					},{
						text:'保存',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								if($('#t_user1').datagrid('validateRow',editing1)){
										$('#t_user1').datagrid('endEdit', editing1);
										editing1 = undefined;
								}
						}
					},{
						text:'生成入库单',
						iconCls:'icon-remove' , 
						handler:function(){
							var arr = $('#t_user1').datagrid('getSelections');
							if(arr.length <= 0 ){
								$.messager.show({
									title:'提示信息',
									msg:'请选择进行入库操作!'
								});											
							} else {
								$.messager.confirm('提示信息' , '确认生成?' , function(r){
									if(r){
										var tmp_in_ids = '';
										for(var i = 0 ; i < arr.length ; i++){
											tmp_in_ids += arr[i].tmp_in_id + ',';
										}
										tmp_in_ids = tmp_in_ids.substring(0,tmp_in_ids.length-1);
										$.post('tmpInStock/insertInStock.do' , {tmp_in_ids:tmp_in_ids},function(result){
											    $('#t_user1').datagrid('reload');
												$.messager.show({
													title:'提示信息',
													msg:'操作成功!'
												});
										});
										
									} else {
										 return ;
									}
								});
							}
						}
					},{
						text:'取消操作',
						iconCls:'icon-cancel' , 
						handler:function(){
							//回滚数据 
							$('#t_user1').datagrid('rejectChanges');
							editing1 = undefined;
						}
					}	
				] ,
				onAfterEdit:function(index , record){
					$.post(flag1=='add'?'UserServlet?method=save':'tmpInStock/updateTmpInStock.do' , record , function(result){
					    $('#t_user1').datagrid('reload');
						$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
					});
				}
		});
	
	$('#searchbtn').click(function(){
		$('#t_user1').datagrid('load' ,serializeForm($('#form')));
	});
	
	
	$('#clearbtn').click(function(){
		$('#form').form('clear');
		$('#t_user1').datagrid('load' ,{});
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	var editing ; //判断用户是否处于编辑状态 
	var flag  ;	  //判断新增和修改方法
	$('#t_user').datagrid({
				idField:'good_id' ,
				title:'商品信息' , 
				fitColumns: true  ,
				url:'good/querySellGood.do' ,		//修改请求的url
				striped: true ,					
				loadMsg: '数据正在加载,请耐心的等待...' ,
				rownumbers:true ,
				frozenColumns:[[
						{field:'ck' , checkbox:true}									
				]],
				columns:[[
							{
								field:'good_id' ,
								title:'商品id' ,
								width:180 ,
								align:'center' ,
								styler:function(value , record){
									/* if(value == 'admin'){
										return 'background:blue;';
									} */
								}/*  ,
								editor:{
									type:'validatebox' ,
									options:{
										required:true , 
										missingMessage:'商品id必填!'
									}
								} */
							},{
						field:'good_name' ,
						title:'商品名' ,
						width:150 ,
						align:'center' ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						}/*  ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品名必填!'
							}
						} */
					},
					{
						field:'brand_name' ,
						title:'品牌名' ,
						width:100 ,
						align:'center' ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						}/*  ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'品牌名必填!'
							}
						} */
					},	{
						field:'good_price' ,
						title:'商品原价' ,
						width:100  ,
					/* 	editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'商品原价必填!' ,
								min:0 , 
								precision:2
							}
						} */
					},{
						field:'good_nowprice' ,
						title:'商品现价' ,
						width:100 ,
						sortable : true ,
						/* editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'商品现价必填!' ,
								min:0 , 
								precision:2
							}
						} */
					}
					,{
						field:'good_desc' , 
						title:'商品描述' ,
						width:200 ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						} /* , 
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品描述必填!'
							}
						} */
					}
					,{
						field:'purchase_num' ,
						title:'采购数量' ,
						width:100 ,
						sortable : true ,
						editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'商品数量必填!' ,
								min:0 
							}
						}
					}
				]] ,
				pagination: true , 
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
					}, */{
						text:'采购商品',
						iconCls:'icon-edit' , 
						handler:function(){
								var arr = $('#t_user').datagrid('getSelections');
						/* 		if(arr.length != 1)
								{
										$.messager.show({
											title:'提示信息',
											msg:'只能选择一条记录进行修改!' 
										});
								} else {
									if(editing == undefined)
									{
										flag = 'edit';
										
										//根据行记录对象获取该行的索引位置
										editing = $('#t_user').datagrid('getRowIndex' , arr[0]);
										//开启编辑状态
										$('#t_user').datagrid('beginEdit',editing);
									}
								} */
								for(var i = 0 ; i < arr.length ; i++)
								{
									flag = 'edit';
									//根据行记录对象获取该行的索引位置
									editing = $('#t_user').datagrid('getRowIndex' , arr[i]);
									//开启编辑状态
									$('#t_user').datagrid('beginEdit',editing);
								}
						}										
					},{
						text:'保存数量',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								var arr = $('#t_user').datagrid('getSelections');
								for(var i = 0 ; i < arr.length ; i++)
								{
									//根据行记录对象获取该行的索引位置
									editing = $('#t_user').datagrid('getRowIndex' , arr[i]);
									if($('#t_user').datagrid('validateRow',editing)){
										$('#t_user').datagrid('endEdit', editing);
										//editing = undefined;
								} 
								}
						}
					}, {
						text:'确定',
						iconCls:'icon-remove' , 
						handler:function(){
							var arr = $('#t_user').datagrid('getSelections');
							if(arr.length <= 0 ){
								$.messager.show({
									title:'提示信息',
									msg:'请选择进行生成操作!'
								});											
							} else {
								$.messager.confirm('提示信息' , '确定吗?' , function(r){
									if(r){
										var good_ids = '';
										var purchase_nums = '';
										for(var i = 0 ; i < arr.length ; i++){
											good_ids += arr[i].good_id + ',';
											purchase_nums += arr[i].purchase_num + ',';
										}
										good_ids = good_ids.substring(0,good_ids.length-1);
										purchase_nums = purchase_nums.substring(0,purchase_nums.length-1);
										$.post('good/insertTempInStock.do' , {good_ids:good_ids,purchase_nums:purchase_nums},function(result){
											    $('#t_user').datagrid('reload');
											    $('#t_user1').datagrid('reload');
												$.messager.show({
													title:'提示信息',
													msg:'操作成功!'
												});
										}); 
										
									} else {
										 return ;
									}
								});
							}
						}
					}, {
						text:'取消操作',
						iconCls:'icon-cancel' , 
						handler:function(){
							//回滚数据 
							$('#t_user').datagrid('rejectChanges');
							editing = undefined;
						}
					}	
				] ,
				onAfterEdit:function(index , record){
				/* 	$.post(flag=='add'?'stock/addStock.do':'stockk/updateStock.do' , record , function(result){
							$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
					}); */
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
		    <br> <br>
					 <!-- <div><h2>商品购买</h2></div>  -->
				     	 <div style="text-align: left: ;" >	
						    	 <form    id="form"  action="" method="post"> 
						    	　　 商品id:
				    					<input type="text" name="good_id"  id="good_id"  class="easyui-textbox"   data-options="prompt:'请输入商品id'"     value=""  style="margin-right: 10px;" />
						    	 商品名:
				    					<input type="text" name="good_name"   id="good_name"  class="easyui-textbox"   data-options="prompt:'请输入商品名'"     value=""  style="margin-right: 10px;"  />
						    	 品牌名:
				    					<input type="text" name="brand_name"   id="brand_name"  class="easyui-textbox"   data-options="prompt:'请输入品牌名'"     value=""  style="margin-right: 10px;"  />
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a>
						    	 </form>
				    	 </div>  
				    	
				    </div>    
		 
		 <div region="center"  style="width: 100%;height:70%;">
			  		<table id="t_user"   style="width: 100%;"></table> 
		    </div>  
		     <div region="south"   style="height:30%;width: 100%;">
			  			 <table id="t_user1"   style="width: 100%;"></table>
		    </div>  
		    
		    
		</div>  

  </body>
</html>
