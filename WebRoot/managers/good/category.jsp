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
	$('#t_category').datagrid({
				idField:'c_id' ,
				title:'分类信息' , 
				fitColumns: true  ,
				url:'category/queryCategory.do' ,		
				striped: true ,					
				loadMsg: '数据正在加载,请耐心的等待...' ,
				rownumbers:true ,
				frozenColumns:[[
						{field:'ck' , checkbox:true}									
				]],
				columns:[[
					{
						field:'c_id' ,
						title:'类别编号' ,
						width:100 ,
						align:'center' ,
						/* editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'类别编号必填!'
							}
						} */
					}, 
					{
						field:'c_name' ,
						title:'类别名' ,
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
								missingMessage:'类别名必填!'
							}
						}
					},
					{
						field:'c_parent' ,
						title:'所属类别' ,
						width:100  ,
						align:'center' ,
						 editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'所属类别必填!' ,
							}
						} 
					},{
						field:'c_desc' ,
						title:'分类描述' ,
						width:100 ,
						align:'center' ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'所属类别必填!' ,
							}
						}
					}
				]] ,
				pagination: true , 
				pageSize: 10 ,
				pageList:[5,10,15,20,50] ,
				toolbar:[
					{
						text:'新增',
						iconCls:'icon-add' , 
						handler:function(){
										if(editing == undefined){
											flag = 'add';
											//1 先取消所有的选中状态
											$('#t_category').datagrid('unselectAll');
											//2追加一行
											$('#t_category').datagrid('appendRow',{description:''});
											//3获取当前页的行号
											editing = $('#t_category').datagrid('getRows').length -1;
											//4开启编辑状态
											$('#t_category').datagrid('beginEdit', editing);
										}
								}
					}, {
						text:'修改',
						iconCls:'icon-edit' , 
						handler:function(){
								var arr = $('#t_category').datagrid('getSelections');
								if(arr.length != 1){
										$.messager.show({
											title:'提示信息',
											msg:'只能选择一条记录进行修改!' 
										});
								} else {
									if(editing == undefined){
										flag = 'edit';
										
										//根据行记录对象获取该行的索引位置
										editing = $('#t_category').datagrid('getRowIndex' , arr[0]);
										//开启编辑状态
										$('#t_category').datagrid('beginEdit',editing);
									}
								}
								
						}										
					},{
						text:'保存',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								if($('#t_category').datagrid('validateRow',editing)){
										$('#t_category').datagrid('endEdit', editing);
										editing = undefined;
								}
						}
					}, /*  {
						text:'删除',
						iconCls:'icon-remove' , 
						handler:function(){
							var arr = $('#t_category').datagrid('getSelections');
							if(arr.length <= 0 ){
								$.messager.show({
									title:'提示信息',
									msg:'请选择进行删除操作!'
								});											
							} else {
								$.messager.confirm('提示信息' , '确认删除?' , function(r){
									if(r){
										var c_ids = '';
										for(var i = 0 ; i < arr.length ; i++){
											c_ids += arr[i].c_id + ',';
										}
										c_ids = c_ids.substring(0,c_ids.length-1);
										$.post('category/deleteCategory.do' , {c_ids:c_ids},function(result){
											   $('#t_category').datagrid('reload');
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
					}, */  {
						text:'取消',
						iconCls:'icon-cancel' , 
						handler:function(){
							//回滚数据 
							$('#t_category').datagrid('rejectChanges');
							editing = undefined;
						}
					}	
				] ,
				onAfterEdit:function(index , record){
					$.post(flag=='add'?'category/addCategory.do':'category/updateCategory.do' , record , function(result){
							$('#t_category').datagrid('reload');
							$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
					});
				}
		});
	
	$('#searchbtn').click(function(){
		$('#t_category').datagrid('load' ,serializeForm($('#form')));
	});
	
	
	$('#clearbtn').click(function(){
		$('#form').form('clear');
		$('#t_category').datagrid('load' ,{});
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
		  <div region="north"   title="" split="false" style="height:100px;"  >
				     	 <div >	
						    	 <form    id="form"  action="" method="post"> 
						    	     <table>
						    	     	<tr>
						    	 			<td>类别编号:</td>
						    	 			<td><input type="text" name="c_id"  id="C_id"  class="easyui-textbox"   data-options="prompt:'请输入类别编号'"     value=""  style="margin-right: 10px;" /></td>
						    	 		</tr>
						    	 		<tr>
						    	 			<td>类别名称:</td>
						    	 			<td><input type="text" name="c_name"  id="C_name"  class="easyui-textbox"   data-options="prompt:'请输入类别名'"     value=""  style="margin-right: 10px;" /></td>
						    	 		</tr>
						    	 		<!-- <tr>
						    	 		    <td>所属类别:</td>
						    	 		    <td><input type="text" name="c_parent"  id="C_parent"  class="easyui-textbox"   data-options="prompt:'请输入所属类别'"     value=""  style="margin-right: 10px;" /></td>
						    	 		</tr> -->
						    	 		<tr>
						    	 			<td colspan="2" style="text-align: center;">
						    	 			<a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
				    						<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a>
						    	 			</td>
						    	 		</tr>
						    	 	</table>
						    	<!--  类别名称:
				    					<input type="text" name="c_name"  id="C_name"  class="easyui-textbox"   data-options="prompt:'请输入类别名'"     value=""  style="margin-right: 10px;" />
				    			 所属类别:
				    					<input type="text" name="c_parent"  id="C_parent"  class="easyui-textbox"   data-options="prompt:'请输入所属类别'"     value=""  style="margin-right: 10px;" />		
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a> -->
						    	 </form>
				    	 </div>  
				    	
				    </div>    
		 
		 <div region="center"  style="width: 100%;">
			  			<table id="t_category"></table>
		    </div>  
		    <!--  <div region="south"   title="底部" style="padding:5px;height:100px;width: 100%;">
			  			 
		    </div>  
		     -->
		    
		</div>  

  </body>
</html>
