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
	$('#dlg').dialog('close');
	
	var editing ; //判断用户是否处于编辑状态 
	var flag  ;	  //判断新增和修改方法
	$('#t_user').datagrid({
				idField:'id' ,
				title:'订单信息' , 
				fitColumns: true  ,
				url:'user/findAllAdminUser.do' ,		//修改请求的url
				striped: true ,					
				loadMsg: '数据正在加载,请耐心的等待...' ,
				rownumbers:true ,
				frozenColumns:[[
						{field:'ck' , checkbox:true}									
				]],
				columns:[[
				/* 	{
						field:'order_id' ,
						title:'订单号' ,
						width:100 ,
						align:'center' ,
						styler:function(value , record){
							 if(value == 'admin'){
								return 'background:blue;';
							} 
						}  ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'订单号必填!'
							}
						}  
					}, */
					{
						field:'name' ,
						title:'用户名' ,
						width:50  ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'用户名必填!' ,
							}
						}
					},
					{
						field:'phone' ,
						title:'手机号' ,
						width:50  ,
					/* 	editor:{
							type:'numberbox' ,
							options:{
								required:true , 
								missingMessage:'手机号必填!' ,
								min:0 , 
								max:5
							}
						} */
					},	{
						field:'com_no' ,
						title:'单位名' ,
						width:100 ,
						align:'center' ,
						formatter:function(value , record , index){
							if(value == '100001'){
								return '<span  >北方工大</span>' ;
							} else if( value == '100002'){
								return '<span >迪信通公司</span>' ; 
							}
						}  ,
						editor:{
							type:'combobox' ,
							options:{
								data:[{id:100001 , val:'北方工大'},{id:100002 , val:'迪信通公司'}] ,
								valueField:'id' , 
								textField:'val' ,
								required:true , 
								missingMessage:'单位名必填!'
							}
						}
					},{
						field:'pwd' , 
						title:'密码' ,
						width:50 ,
						styler:function(value , record){
							/* if(value == 'admin'){
								return 'background:blue;';
							} */
						}/*  , 
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'密码必填!'
							}
						} */
					},{
						field:'status' , 
						title:'用户状态' ,
						width:50 ,
						formatter:function(value , record , index){
							if(value == 1){
								return '<span style=color:red; >在职</span>' ;
							} else if( value == 0){
								return '<span style=color:green; >离职</span>' ; 
							}
						}  , 
						editor:{
							type:'combobox' ,
							options:{
								data:[{id:1 , val:'在职'},{id:0 , val:'离职'}] ,
								valueField:'id' , 
								textField:'val' ,
								required:true , 
								missingMessage:'状态必填!'
							}
						}
					}
				]] ,
				pagination: true , 
				pageSize: 10 ,
				pageList:[5,10,15,20,50] ,
				toolbar:[
				 	{
						text:'新增用户',
						iconCls:'icon-add' , 
						handler:function(){
							$('#dlg').dialog('open');
								/* 		if(editing == undefined){
											flag = 'add';
											//1 先取消所有的选中状态
											$('#t_user').datagrid('unselectAll');
											//2追加一行
											$('#t_user').datagrid('appendRow',{description:''});
											//3获取当前页的行号
											editing = $('#t_user').datagrid('getRows').length -1;
											//4开启编辑状态
											$('#t_user').datagrid('beginEdit', editing);
										} */
								}
					}, {
						text:'修改用户',
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
						text:'保存用户',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								if($('#t_user').datagrid('validateRow',editing)){
										$('#t_user').datagrid('endEdit', editing);
										editing = undefined;
								}
						}
					}/* ,{
						text:'用户离职',
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
										var order_ids = '';
										for(var i = 0 ; i < arr.length ; i++){
											order_ids += arr[i].order_id + ',';
										}
										order_ids = order_ids.substring(0,order_ids.length-1);
										$.post('order/insertOutStock.do' , {order_ids:order_ids},function(result){
											    $('#t_user').datagrid('reload');
											    $.messager.show({
													title:'提示信息',
													msg:result.message
												});
										},'json');
										
									} else {
										 return ;
									}
								});
							}
						}
					} */,{
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
					$.post(flag=='add'?'user/insertAdminUser.do':'user/updateAdminUser.do' , record , function(result){
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
	
	$('#savebtn').click(function(){
		//var name = ("#name").val();
		

		 $.ajax({
			   type: "POST",
			   url: "user/insertAdminUser.do",
			   data: serializeForm($('#userAddForm')),
			   success: function(msg){
			   }
			}); 
		
		
		
		$('#t_user').datagrid('reload');
		$('#dlg').dialog('close');
	});
	
	
	$('#clearbtn1').click(function(){
		$('#userAddForm').form('clear');
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
		    
					  <br><br>
				     	 <div style="text-align: left;" >	
						    	 <form    id="form"  action="" method="post"> 
						    	 用户名:
				    					<input type="text" name="name"   id="name"  class="easyui-textbox"   data-options="prompt:'请输入用户名'"     value=""  style="margin-right: 10px;"  />
						    	 电话:
				    					<input type="text" name="phone"   id="cphone"  class="easyui-textbox"   data-options="prompt:'请输入手机号'"     value=""  style="margin-right: 10px;"  />
						    	 单位名:
				    					<select class="easyui-combobox" name="com_no" style="width:200px;">
												<option value=" "> </option>
												<option value="100001">北方工大</option>
												<option value="100002">迪信通公司</option>
										</select>
						    	 状态:
				    					<select class="easyui-combobox" name="status" style="width:200px;">
												<option value=""> </option>
												<option value="0">离职</option>
												<option value="1">在职</option>
										</select>
						    	 
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
				    					<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a>
						    	 </form>
				    	 </div>  
				    	
				    </div>    
		 
		 <div region="center"  style="width: 100%;">
			  			<table id="t_user"  style="width: 100%;"></table>
		    </div>  
	<div id="dlg" class="easyui-dialog" title="新增用户" data-options="iconCls:'icon-add'" style="width:300px;height:250px;padding:10px"  >
			<form id="userAddForm" method="post">
			<table class="grid">
				<tr>
					 <td>姓名:</td>
					<td><input name="name"  id="name"   type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input name="pwd"  id="pwd"  type="pwd" placeholder="请输入密码" class="easyui-validatebox" data-options="required:true"></td>
				</tr>
				<tr>
					<td>手机号:</td>
					<td><input type="text"  name="phone"  id="phone"   class="easyui-numberbox"  placeholder="请输入手机号"   data-options="required:true"/></td>
				</tr>
				<tr>
					<td>单位名:</td>
					<td><select id="organizationId" name="com_no"   id="com_no"  style="width: 148px; height: 29px;" class="easyui-combobox" data-options="required:true">
												<option value=" " selected="selected"> 请选择单位名</option>
												<option value="100001">北方工大</option>
												<option value="100002">迪信通公司</option>
					</select></td>
				</tr>
				<tr >
						<td style="text-align: right; padding-top: 5px;"   >
						<a href="javascript:void(0)" class="easyui-linkbutton"   id="savebtn" " >确定</a></td>
						<td style="text-align: center; padding-top: 5px;">
						<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn1" "   >清除</a></td>
				</tr>
				
				
				
			</table>
		</form>
	</div>
		    
		    
		</div>  

  </body>
</html>
