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
	
	
	var editing ; //判断是否处于编辑状态 
	var flag  ;	  //判断新增和修改方法
	$('#t_perscore').datagrid({
				idField:'id' ,
				title:'百分比记录信息' , 
				fitColumns: true  ,
				url:'perscore/queryPerscore.do' ,		
				striped: true ,					
				loadMsg: '数据正在加载,请耐心的等待...' ,
				rownumbers:true ,
				frozenColumns:[[
						{field:'ck' , checkbox:true}									
				]],
				columns:[[
					{
						field:'id' ,
						title:'编号' ,
						width:100 ,
						align:'center' ,
					}, 
					{
						field:'percentage' ,
						title:'百分比' ,
						width:100 ,
						align:'center' ,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'百分比必填!'
							}
						}
					},
					{
						field:'adminname' ,
						title:'操作员' ,
						width:100 ,
						align:'center' ,
					},
					{
						field:'status' ,
						title:'状态' ,
						width:100  ,
						align:'center' ,
						formatter: function(value,row,index){
							 if(value == 1){
								return '使用';
							} else{
								return '停用';
							}
						} 
						
					}/* ,{
						field:'createtime' ,
						title:'时间' ,
						width:100 ,
						align:'center' ,
						editor:{
							type:'datebox' ,
							options:{
								required:true , 
								missingMessage:'百分比必填!'
							}
						}
						
					}  */
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
											$('#t_perscore').datagrid('unselectAll');
											//2追加一行
											$('#t_perscore').datagrid('appendRow',{description:''});
											//3获取当前页的行号
											editing = $('#t_perscore').datagrid('getRows').length -1;
											//4开启编辑状态
											$('#t_perscore').datagrid('beginEdit', editing);
										}
								}
					}, {
						text:'保存',
						iconCls:'icon-save' , 
						handler:function(){
								//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
								if($('#t_perscore').datagrid('validateRow',editing)){
										$('#t_perscore').datagrid('endEdit', editing);
										editing = undefined;
								}
						}
					},  {
						text:'取消',
						iconCls:'icon-cancel' , 
						handler:function(){
							//回滚数据 
							$('#t_perscore').datagrid('rejectChanges');
							editing = undefined;
						}
					}	
				] ,
				onAfterEdit:function(index , record){
					$.post(flag=='add'?'perscore/addPerscore.do':'' , record , function(result){
						    $('#t_perscore').datagrid('reload');
							$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
					});
				}
		});
	
	$('#searchbtn').click(function(){
		$('#t_perscore').datagrid('load' ,serializeForm($('#form')));
	});
	
	
	$('#clearbtn').click(function(){
		$('#form').form('clear');
		$('#t_perscore').datagrid('load' ,{});
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
		    
				     	  <div style="margin-top:10px" >	
				     	  	<form    id="form"  action="" method="post"> 
						    	 <table>
						    	 		<!-- <tr>
						    	 			<td>商品名</td>
						    	 			<td><input type="text" name="goodname"  id="goodname"  class="easyui-textbox"   data-options="prompt:'请输入商品名'"     value=""  style="margin-right: 10px;" /></td>
						    	 		</tr>
						    	 		<tr>
						    	 			<td colspan="2" style="text-align: center;">
						    	 			<a href="javascript:void(0)" class="easyui-linkbutton"   id="searchbtn" ">搜索</a>
				    						<a href="javascript:void(0)" class="easyui-linkbutton"   id="clearbtn" ">清除</a>
						    	 			</td>
						    	 		</tr> -->
						    	 </table>
						   </form>
				    	 </div>   
				    </div>    
		  
		 <div region="center"  style="width: 100%;">
			  			<table id="t_perscore"></table>
		     </div>  
		     <!-- <div region="south"   title="底部" style="padding:5px;height:100px;width: 100%;">
			  			 
		    </div>    -->
		    
		    
		</div>  
  </body>
</html>
