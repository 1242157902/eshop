<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>所有商品</title>
    
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
	$('#t_good').datagrid({
				idField:'good_id' ,
				title:'分类信息' , 
				fitColumns: true  ,
				url:'good/queryGood.do' ,		
				striped: true ,					
				loadMsg: '数据正在加载,请耐心的等待...' ,
				rownumbers:true ,
				frozenColumns:[[
						{field:'ck' , checkbox:true}									
				]],
				columns:[[
					{
						field:'good_id' ,
						title:'商品编号' ,
						width:100 ,
						align:'center' ,
						/* editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品编号必填!'
							}
						} */
					},
					{
						field:'good_name' ,
						title:'商品名' ,
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
								missingMessage:'商品名必填!'
							}
						}
					},
					{
						field:'brand_name' ,
						title:'商品品牌' ,
						width:100  ,
						align:'center' ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品品牌必填!' ,
							}
						}
					},
						{
						field:'good_guige' ,
						title:'商品规格' ,
						width:100  ,
						align:'center' ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品规格必填!' ,
							}
						}
					},
					{
						field:'isvisual' ,
						title:'是否为虚拟商品' ,
						width:100 ,
						align:'center' ,
						sortable : true ,
						formatter: function(value,row,index){
							 if(value == true){
								return '是';
							} else{
								return '否';
							}
						} ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'是否为虚拟商品必填!' ,
							}
						}
						
					},
					{
						field:'c_id' ,
						title:'商品类别' ,
						width:100 ,
						align:'center' ,
						sortable : true ,
						/* editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品类别必填!' ,
							}
						} */
					},
					{
						field:'good_price' ,
						title:'商品原价' ,
						width:100 ,
						align:'center' ,
						sortable : true ,
						/* editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品原价必填!' ,
							}
						} */
					},
					{
						field:'good_nowprice' ,
						title:'积分' ,
						width:100 ,
						align:'center' ,
						sortable : true ,
						/* editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'积分必填!' ,
							}
						} */
					},
					{
						field:'good_desc' ,
						title:'商品描述' ,
						width:100 ,
						align:'center' ,
						sortable : true ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'商品描述必填!' ,
							}
						}
					},
					{
						field:'is_sell' ,
						title:'是否上架' ,
						width:100 ,
						align:'center' ,
						sortable : true ,
						formatter: function(value,row,index){
							 if(value==true){
								return '是';
							} else{
								return '否';
							}
						} ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'是否上架必填!' ,
							}
						}
					}
				]] ,
				pagination: true , 
				pageSize: 10 ,
				pageList:[5,10,15,20,50] ,
				toolbar:[
				 	 {
						text:'增加商品',
						iconCls:'icon-add' , 
						handler:function(){
						 window.location.href="managers/good/addGood.jsp";
							/* var src = 'managers/good/addGood.jsp';
							var title = $(this).html();
							if($('#tab').tabs('exists' ,title)){
								$('#tab').tabs('select',title);
							} else {
								$('#tab').tabs('add',{   
								    title:title,   
								    content:'<iframe frameborder=0 style=width:100%;height:100% src='+ src +' ></iframe>',   
								    closable:true  
								});  
							} */
					    }
					},{
						text:'修改主页图片',
						iconCls:'icon-edit' , 
						handler:function(){
								var arr = $('#t_good').datagrid('getSelected');
								if(arr){
									window.location.href="managers/good/AlterGoodPics.jsp?good_id="+arr.good_id+"&good_name="+arr.good_name;
								}else{
									$.messager.show({
										title:'提示信息',
										msg:'请选择一条记录!'
									});
								}
						}										
					},{
						text:'修改',
						iconCls:'icon-edit' , 
						handler:function(){
								var arr = $('#t_good').datagrid('getSelections');
								if(arr.length != 1){
										$.messager.show({
											title:'提示信息',
											msg:'只能选择一条记录进行修改!' 
										});
								} else {
									if(editing == undefined){
										flag = 'edit';
										
										//根据行记录对象获取该行的索引位置
										editing = $('#t_good').datagrid('getRowIndex' , arr[0]);
										//开启编辑状态
										$('#t_good').datagrid('beginEdit',editing);
									}
								}
								
						}										
					},{
						text:'保存',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								if($('#t_good').datagrid('validateRow',editing)){
										$('#t_good').datagrid('endEdit', editing);
										editing = undefined;
								}
						}
					},/*  {
						text:'删除',
						iconCls:'icon-remove' , 
						handler:function(){
							var arr = $('#t_good').datagrid('getSelections');
							if(arr.length <= 0 ){
								$.messager.show({
									title:'提示信息',
									msg:'请选择进行删除操作!'
								});											
							} else {
								$.messager.confirm('提示信息' , '确认删除?' , function(r){
									if(r){
										var good_ids = '';
										for(var i = 0 ; i < arr.length ; i++){
											good_ids += arr[i].good_id + ',';
										}
										good_ids = good_ids.substring(0,good_ids.length-1);
										$.post('good/deleteGood.do' , {good_ids:good_ids},function(result){
											    $('#t_good').datagrid('reload');
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
					}, */ {
						text:'取消',
						iconCls:'icon-cancel' , 
						handler:function(){
							//回滚数据 
							$('#t_good').datagrid('rejectChanges');
							editing = undefined;
						}
					},{
						text:'上传详细页图片',
						iconCls:'icon-add' , 
						handler:function(){
								var arr = $('#t_good').datagrid('getSelected');
								if(arr){
									window.location.href="managers/good/good_pics.jsp?good_id="+arr.good_id;
								}else{
									$.messager.show({
										title:'提示信息',
										msg:'请选择一条记录!'
									});
								}
						}										
					}	
				] ,
				onAfterEdit:function(index , record){
					$.post(flag=='add'?'good/addGood.do':'good/updateGood.do' , record , function(result){
							$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
					});
				}
		});
	
	$('#searchbtn').click(function(){
		$('#t_good').datagrid('load' ,serializeForm($('#form')));
	});
	
	
	$('#clearbtn').click(function(){
		$('#form').form('clear');
		$('#t_good').datagrid('load' ,{});
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
			
</script>
		

  </head>
  
  <body>
 
 
	<div id="cc" class="easyui-layout"  fit=true  style="width:100%;height:100%;">  
		  <div region="north"   title="" split="false" style="height:100px;"  >
				     	 <div>	
						    	 <form    id="form"  action="" method="post"> 
						    	 	<table>
						    	 		<tr>
						    	 			<td>商品编号:</td>
						    	 			<td><input type="text" name="good_id"  id="Good_id"  class="easyui-textbox"   data-options="prompt:'请输入商品id'"     value=""  style="margin-right: 10px;" /></td>
						    	 			<td>商品名称:</td>
						    	 			<td><input type="text" name="good_name"  id="Good_name"  class="easyui-textbox"   data-options="prompt:'请输入商品名'"     value=""  style="margin-right: 10px;" /></td>
						    	 		</tr>
						    	 		<br>
						    	 		<tr>
						    	 		    <td>商品品牌:</td>
						    	 		    <td> <input type="text" name="brand_name"  id="Brand_name"  class="easyui-textbox"   data-options="prompt:'请输入商品品牌'"     value=""  style="margin-right: 10px;" /></td>
						    	 		</tr>
						    	 		<tr>
						    	 		<td colspan="2" style="text-align: center;"><a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
						    	 			    <a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a>
						    	 			</td>
						    	 		</tr>
						    	 	</table>
						    	<!--   商品编号:
				    					<input type="text" name="good_id"  id="Good_id"  class="easyui-textbox"   data-options="prompt:'请输入商品id'"     value=""  style="margin-right: 10px;" />
						    	   商品名称:
				    					<input type="text" name="good_name"  id="Good_name"  class="easyui-textbox"   data-options="prompt:'请输入商品名'"     value=""  style="margin-right: 10px;" />
				    			   商品品牌：	
				    			        <input type="text" name="brand_name"  id="Brand_name"  class="easyui-textbox"   data-options="prompt:'请输入商品品牌'"     value=""  style="margin-right: 10px;" />
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a> -->
						    	 </form>
				    	 </div>  
				    	
				    </div>    
		 
		 <div region="center"  style="width: 100%;">
			  			<table id="t_good"></table>
			  		 	<div id="tab" class="easyui-tabs" fit=true style="width:500px;height:250px;">
		    </div>  
		   <!--   <div region="south"   title="底部" style="padding:5px;height:100px;width: 100%;">
			  			 
		    </div>  
		     -->
		    
		</div>  
 		${requestScope.message}
  </body>
</html>
