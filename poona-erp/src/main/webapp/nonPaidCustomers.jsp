<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap.css" rel="stylesheet" />
<script src="js/bootstrap.min.js"></script>
<script>

var current_id ;

$(document).ready(function () {
	$( document ).tooltip();
	$( "#add_new_div" ).dialog({
		autoOpen: false,
		 height: 600,
	     width: 450,
	     title: "添加意向客户",
		buttons: [
			{
				text: "Ok",
				click: function() {
					if(!validateForm()){
						return;
					}else{
						  $.ajax({
					          type: "post",
					          url: "saveCustomer.action",
					          data:$("#addNonPaidForm").serialize(),
					          success: function (dt) {
					             if(dt==0) alert("操作成功");
					             if(dt==1) alert("操作失败");
					             $("#content").load('getNonPaiedCustomers.action');
					          },
					          }); 
						$(this).dialog( "close" );
					}
				
				}
			},
			{
				text: "Cancel",
				click: function() {
					$(this).dialog( "close" );
				}
			}
		]
	});
	
	$( "#update_div" ).dialog({
		autoOpen: false,
		 height: 600,
	     width: 450,
	     title: "修改意向客户",
		buttons: [
			{
				text: "Ok",
				click: function() {
					
					if(!validateForm()){
						return;
					}else{
						  $.ajax({
					          type: "post",
					          url: "updateCustomer.action",
					          data:$("#updateNonPaidForm").serialize()+ "&customerId=" +current_id,
					          success: function (dt) {
					             if(dt==0) alert("操作成功");
					             if(dt==1) alert("操作失败");
					             $("#content").load('getNonPaiedCustomers.action');
					          },
					          }); 
						$(this).dialog( "close" );
					}
					
				}
			},
			{
				text: "Cancel",
				click: function() {
					$(this).dialog( "close" );
				}
			}
		]
	});
	
	$( "#checkTrack_div" ).dialog({
		autoOpen: false,
		 height: 500,
	     width: 600,
	     title: "查看跟踪",
	});
	
	
	$( "#addTrack_div" ).dialog({
		autoOpen: false,
		 height: 400,
	     width: 450,
	     title: "添加跟踪",
		buttons: [
			{
				text: "Ok",
				click: function() {
					  $.ajax({
				          type: "post",
				          url: "addTrack.action",
				          data:$("#addTrackForm").serialize()+ "&customerId=" +current_id,
				          success: function (dt) {
				             if(dt==0) alert("操作成功");
				             if(dt==1) alert("操作失败");
				             $("#content").load('getNonPaiedCustomers.action');
				          },
				          }); 
					$(this).dialog( "close" );
				}
			},
			{
				text: "Cancel",
				click: function() {
					$(this).dialog( "close" );
				}
			}
		]
	});
	
	$( "#buyCard_div" ).dialog({
		autoOpen: false,
		 height: 550,
	     width: 450,
	     title: "客户买卡",
		buttons: [
			{
				text: "Ok",
				click: function() {
					
					if(!validateBuyCardForm()){
						return;
					}else{
						  $.ajax({
					          type: "post",
					          url: "buyCard.action",
					          data:$("#buyCardForm").serialize()+ "&customerId=" +current_id,
					          success: function (dt) {
					             if(dt==0) alert("操作成功");
					             if(dt==1) alert("操作失败");
					             $("#content").load('getPaiedCustomers.action');
					          },
					          }); 
						$(this).dialog( "close" );
					}
					
				}
			},
			{
				text: "Cancel",
				click: function() {
					$(this).dialog( "close" );
				}
			}
		]
	});
	
	
	
});

function addNew(){
	$("#add_new_div").load( "toAddCustomer.action" );  
	$("#add_new_div").dialog( "open" );
}
function updateItem(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录进行修改!'); return;
	 }else if(count<1){
		 alert('请选择要修改的记录!');			return;
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}	
	$("#update_div").load( "toUpdateNonPaidCustomer.action?customerId="+current_id );
	$("#update_div").dialog( "open" );
}
function checkTrack(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');	return;		
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	$("#checkTrack_div").load( "getTrackItems.action?customerId="+current_id );
	$("#checkTrack_div").dialog( "open" );
}
function addTrack(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');return;			
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	$("#addTrack_div").load( "toAddTrack.action");
	$("#addTrack_div").dialog( "open" );
}
function buyCard(id){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');return;			
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	$("#buyCard_div").load( "toBuyCard.action?customerId="+current_id);
	$("#buyCard_div").dialog( "open" );
}
function checkName(){
	var name=$("#name").val();
	if(name==''){alert("请输入卡片名称!!");$("#name").focus();}
	  $.ajax({
          type: "post",
          url: "checkCustomer.action",
          data: "name=" +name,
          success: function (dt) {
             if(dt==1) {alert("同名客户已存在，请更改名称!!");$("#name").focus();};
          },
          }); 
}
function validateForm(){
	var mobilePhone=$("#mobilePhone").val();
	if(mobilePhone==""){
		  alert("请输入电话!!");
		  return false;
	  }
	 if(isNaN(mobilePhone)){
		  alert("请输入格式正确的电话信息!!");
		  return false;
	  }
		var address=$("#address").val();
		if(address==""){
			  alert("请输入地址!!");
			  return false;
		  }
	return true;
	
}


function validateBuyCardForm(){
	var cardNumber=$("#cardNumber").val();
	var realPay=$("#realPay").val();
	var cardType=$("#cardType").val();
	if(cardNumber==""){
		  alert("请输入卡号!!");
		  return false;
	  }
	 if(isNaN(cardNumber)){
		  alert("请输入格式正确的卡号!!");
		  return false;
	  }
	 if(realPay==""){
		  alert("请输入购买价格!!");
		  return false;
	  }
	 if(isNaN(realPay)){
		  alert("请输入格式正确的购买价格!!");
		  return false;
	  }
		if($("#buy_card_from").val()==""){alert("请输入开始日期!!");return false;}
		if($("#buy_card_to").val()==""){alert("请输入结束日期!!");return false;}
	return true;
	
}

function deleteNonPaidCustomer(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');return;			
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	 if(confirm("确定删除此记录?")){
		  $.ajax({
	          type: "post",
	          url: "deleteCustomer.action",
	          data:"customerId="+current_id,
	          success: function (dt) {
	             if(dt==0) alert("操作成功");
	             if(dt==1) alert("操作失败");
	             $("#content").load('getNonPaiedCustomers.action');
	          },
	          }); 
	 }
}

</script>
 <style>
  label {
    display: inline-block;
    width: 5em;
  }
  </style>
</head>
<body>
<ul class="breadcrumb">
		<li><a href="#">首页</a> <span class="divider">/</span></li>
		<li class="active">意向用户管理</li>
</ul>
<div class="pull-right">
		<a class="btn btn-primary" onclick="addNew()">添加意向客户</a>
		<a class="btn btn-primary"  onclick="deleteNonPaidCustomer()">删除</a>
		<a class="btn  btn-primary"onclick="updateItem()">修改信息</a>
		<a class="btn  btn-primary" onclick="checkTrack()">查看跟踪</a>
		<a class="btn  btn-primary" onclick="addTrack()">添加跟踪</a>
		<a class="btn  btn-primary" onclick="buyCard()">客户买卡</a>
	</div>
<form name="cardForm" id="cardForm"  action="saveCards" method="post">
<table class="table table-bordered  table-striped table-hover">
  <thead>
    <tr>
      <th></th>
      <th>行号</th>
      <th>姓名</th>
	  <th>性别</th>
      <th>手机</th>
      <th>固定电话</th>
      <th>职务</th>
      <th>公司</th>
      <th>国籍</th>
      <th>地址</th>
      <th>跟踪次数</th>
      <th>备注</th>
    </tr>
  </thead>
  <tbody>
    <s:iterator value="nonPaidCustomers" status="listStatus">
    <tr title='最新跟踪     <s:property value="latestTrack"/>'>
    <td><input type="checkbox" name="customerId" value='<s:property value="id"/>'/></td>
    <td><s:property value="#listStatus.index+1"/></td>
    
    <td><s:property value="name"/></td>
     <td>
	  <s:if test="sex==0">女</s:if>
      <s:if test="sex==1">男</s:if>
     </td>
     <td><s:property value="mobilePhone"/></td>
     <td><s:property value="deskPhone"/></td>
     
     <td><s:property value="career"/></td>
     <td><s:property value="company"/></td>
     <td><s:property value="nationality"/></td>
     <td><s:property value="address"/></td>
     <td><s:property value="trackTimes"/></td>
     <td><s:property value="comments"/></td>
   </tr>
    
    </s:iterator>
    
   <tbody>
</table>
</form>
  <div id="add_new_div"></div>
  <div id="update_div"></div>
  <div id="checkTrack_div"></div>
  <div id="addTrack_div"></div>
  <div id="buyCard_div"></div>
</body>
</html>