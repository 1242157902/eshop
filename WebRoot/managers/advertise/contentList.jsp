<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">    
    <title>ContentList</title>
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
	
</head>
<body style="padding:0px;">
    
	<div id="contentlistlay" class="easyui-layout" style="width: 100%;height:100%" >
		<div region="center" >
			<table id="contentlist"></table>
		</div>
		<div region="south"   style="height:300px;width: 100%;">
			  			 <table id="t_user1"   style="width: 100%;"></table>
		    </div> 
	</div>
	
	<script type="text/javascript">	
	 $(function(){
		$('#contentlist').datagrid({
			idField:'id',	
			title:'内容信息列表',
			//width:2000,
			fit:true,
			height:450 ,
			url:'advertise/queryPic.do' ,
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
					field:'name' ,
					title:'图片' ,
					width:50 ,
					height:50
				},
				{
					field:'operdate' ,
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
               text: "添加",
               iconCls: "icon-add",
               handler: function () {
            	   window.location.href="managers/advertise/contentAdd.jsp";
               }
            },'-',      
             {
					text:'选择图片',
					iconCls:'icon-remove' , 
					handler:function(){
						var arr = $('#contentlist').datagrid('getSelections');
						if(arr.length <= 1 ){
							$.messager.show({
								title:'提示信息',
								msg:'至少选择两张图片!'
							});											
						} else {
							$.messager.confirm('提示信息' , '确定吗?' , function(r){
								if(r){
									var ids = '';
									
									for(var i = 0 ; i < arr.length ; i++){
										ids += arr[i].id + ',';
										
									}
									ids = ids.substring(0,ids.length-1);
									
									$.post('advertise/insertAdvList.do' , {ids:ids},function(result){
										    $('#contentlist').datagrid('reload');
										    $('#t_user1').datagrid('reload');
											$.messager.show({
												title:'提示信息',
												msg:'选择成功!'
											});
									}); 
									
								} else {
									 return ;
								}
							});
						}
					}
				}]
			});
	});
	
	
    function fctrow(){
        var row = $('#contentlist').datagrid('getSelected');
        return $('#contentlist').datagrid('getRowIndex',row);       
    }
	
    
    
    var editing;
$('#t_user1').datagrid({
	idField:'id',
	title:'图片列表' , 
	fitColumns: true  ,
	url:'advertise/queryAdvList.do' ,				
	striped: true ,					
	loadMsg: '数据正在加载,请耐心的等待...' ,
	rownumbers:true ,
	frozenColumns:[[
			{field:'id' , checkbox:true}									
	]],
	columns:[[
		
		{
			field:'que' ,
			title:'图片列表' ,
			width:250 ,
			height:50
			
		},{
			field:'opername' ,
			title:'操作员' ,
			width:100 ,
			align:'center' 
		},{
			field:'showtime' ,
			title:'展示时间' ,
			width:100 ,
			align:'center',
			formatter:function(value,row){
				return row.showtime;
			},
			editor:{
				type:'datebox',
				/* options:{
					valueField:'id',
					textField:'name',
					method:'get',
					url:'js/json/unit.json',
					required:false
				} */
			}
		}
	]] ,
	pagination: true , 
	pageSize: 10 ,
	pageList:[5,10,15,20,50] ,
	toolbar:[
		{
			text:'设置展示时间',
			iconCls:'icon-remove' , 
			handler:function(){
				var arr = $('#t_user1').datagrid(
				'getSelections');
				if (arr.length != 1) {
					$.messager.show({
						title : '提示信息',
						msg : '只能选择一条记录进行操作!'
					});
				} else {
		
					if (editing == undefined) {
						//flag = 'edit';
		
						//根据行记录对象获取该行的索引位置
		
						editing = $('#t_user1')
								.datagrid(
										'getRowIndex',
										arr[0]);
						//开启编辑状态
						$('#t_user1').datagrid(
								'beginEdit', editing);
		
					}
				}
		
			}
		},{
			text : '保存设置',
			iconCls : 'icon-save',
			handler : function() {
				//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
				if ($('#t_user1').datagrid('validateRow', editing)) {
					$('#t_user1').datagrid('endEdit', editing);
					editing = undefined;

				}
			}
		},{
			text:'生成图片列表',
			iconCls:'icon-remove' , 
			handler:function(){
				var select = $('#t_user1').datagrid('getSelected');
				if(select.length <= 0 ){
					$.messager.show({
						title:'提示信息',
						msg:'请选择预图片列表!'
					});											
				} else if(select.length >1){
					$.messager.show({
						title:'提示信息',
						msg:'只能选择一个预图片列表!'
					});	
				}else{
					$.messager.confirm('提示信息' , '确认生成?' , function(r){
						if(r){
							var id= select.id ;
							
							$.post('advertise/finishAdvList.do' , {id:id},function(result){
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
		}
		] ,
		onAfterEdit:function(index , record){
			$.post('advertise/updateTmpAdvList.do' , record , function(result){
			    $('#t_user1').datagrid('reload');
				$.messager.show({
						title:'提示信息',
						msg:'操作成功!'
					});
			});
		}
});
	</script>  

   
</body>
</html>
