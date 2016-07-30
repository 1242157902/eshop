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
	
	
	var editing ; //判断用户是否处于编辑状态 
	var flag  ;	  //判断新增和修改方法
	$('#t_user').datagrid({
				idField:'purchase_id' ,
				title:'采购单信息' , 
				fitColumns: true  ,
				url:'purchase/findAllPurchase.do' ,		//修改请求的url
				striped: true ,					
				loadMsg: '数据正在加载,请耐心的等待...' ,
				rownumbers:true ,
				frozenColumns:[[
						{field:'ck' , checkbox:true}									
				]],
				columns:[[
					{
						field:'purchase_id' ,
						title:'采购单号' ,
						width:100 ,
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
								missingMessage:'采购单号必填!'
							}
						}  */
					},
					{
						field:'quantity' ,
						title:'采购总量' ,
						width:100  ,
						editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'采购总量必填!' ,
								min:0 , 
								precision:0
							}
						}
					},{
						field:'purchase_total' ,
						title:'采购总价' ,
						width:100 ,
						sortable : true ,
						editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'采购总价必填!' ,
								min:0 , 
								precision:2
							}
						}
					},{
						field:'manager_name' , 
						title:'采购员' ,
						width:50 ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						} , 
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'采购员必填!'
							}
						}
					},{
						field:'purchasetime' , 
						title:'采购时间' ,
						width:50 ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						} /* , 
						editor:{
							type:'datetimebox' , 
							option:{
									required:true , 
									missingMessage:'时间必填!' ,
									editable:false 
							}
						} */
					}
				]] ,
				pagination: true , 
				pageSize: 10 ,
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
						text:'修改采购单',
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
						text:'保存采购单',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								if($('#t_user').datagrid('validateRow',editing)){
										$('#t_user').datagrid('endEdit', editing);
										editing = undefined;
								}
						}
					},{
						text:'生成入库单',
						iconCls:'icon-remove' , 
						handler:function(){
							var arr = $('#t_user').datagrid('getSelections');
							if(arr.length <= 0 ){
								$.messager.show({
									title:'提示信息',
									msg:'请选择进行生成操作!'
								});											
							} else {
								$.messager.confirm('提示信息' , '确认生成入库单?' , function(r){
									if(r){
										var purchase_ids = '';
										for(var i = 0 ; i < arr.length ; i++){
											purchase_ids += arr[i].purchase_id + ',';
										}
										purchase_ids = purchase_ids.substring(0,purchase_ids.length-1);
										$.post('purchase/insertInStock.do' , {purchase_ids:purchase_ids},function(result){
											    $('#t_user').datagrid('reload');
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
							$('#t_user').datagrid('rejectChanges');
							editing = undefined;
						}
					}	
				] ,
				onAfterEdit:function(index , record){
					$.post(flag=='add'?'stock/addStock.do':'purchase/updatePurchase.do' , record , function(result){
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
		  <div region="north"   title="" split="false" style="height:100px;text-align: center;"  >
		    
					 <div><h2>采购单查询</h2></div>
				     	 <div style="text-align: center;" >	
						    	 <form    id="form"  action="" method="post"> 
						    		 采购单号:
				    					<input type="text" name="purchase_id"  id="purchase_id"  class="easyui-textbox"  data-options="prompt:'请输入采购单号'"     value=""  style="margin-right: 10px;" />
						    	 采购时间:
				    					<input  type="text" name="purchasetime"   id="purchasetime"  class="easyui-datetimebox"   data-options="prompt:'请输入采购时间'"     value=""  style="margin-right: 10px;"  />
						    	 采购员:
				    					<input type="text" name="manager_name"   id="manager_name"  class="easyui-textbox"   data-options="prompt:'请输入采购员'"     value=""  style="margin-right: 10px;"  />
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a>
						    	 </form>
				    	 </div>  
				    	
				    </div>    
		 
		 <div region="center"  style="width: 100%;">
			  			<table id="t_user"  style="width: 100%;"></table>
		    </div>  
		     <div region="south"   title="底部" style="padding:5px;height:100px;width: 100%;">
			  			 
		    </div>  
		    
		    
		</div>  

  </body>
</html>
