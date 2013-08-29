<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>

var current_id ;

$(document).ready(function () {
	
	$( "#non_paid_from" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		 dateFormat:"yy-mm-dd",
		onSelect: function( selectedDate ) {
			$( "#non_paid_to" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$( "#non_paid_to" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		dateFormat:"yy-mm-dd",
		onSelect: function( selectedDate ) {
			$( "#non_paid_from" ).datepicker( "option", "maxDate", selectedDate );
		}
	});
	
	
	
	
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
	var name=$("#name").val();
	if(name==''){alert("请输入姓名!!"); return false;}
	var mobilePhone=$("#mobilePhone").val();
	if(mobilePhone==""){
		  alert("请输入手机号码!!");
		  return false;
	  }
	 if(isNaN(mobilePhone)){
		  alert("请输入格式正确的手机号码!!");
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

function searchNonPaid() {
	var costFrom = $("#non_paid_from").val();
	var costTo = $("#non_paid_to").val();
	if(costFrom == "" || costTo == ""){
		alert("请输入开始和结束日期");
		return;
	}
	$("#content").load('getNonPaiedCustomers.action?nonPaidFrom='+costFrom+"&nonPaidTo="+costTo);
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
<div style="background-color:#f5f5f5">
<table style="width:100%">
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
		<td>首页/意向用户管理</td>
		<td align="right">
		
		 创建日期从<input type="text"  id="non_paid_from" value='<s:property value="nonPaidFrom" />' name="non_paid_from"/>到<input  value='<s:property value="nonPaidTo" />' type="text" id="non_paid_to" name="non_paid_to"/>
	    <a class="btn btn-primary" onclick="searchNonPaid()">查询</a>
		
		<sec:authorize url="/toAddCustomer.action">
		<a class="btn btn-primary" onclick="addNew()">添加意向客户</a>
		</sec:authorize>
		<sec:authorize url="/deleteCustomer.action">
		<a class="btn btn-primary"  onclick="deleteNonPaidCustomer()">删除</a>
		</sec:authorize>
		<sec:authorize url="/toUpdateNonPaidCustomer.action">
		<a class="btn  btn-primary"onclick="updateItem()">修改信息</a>
		</sec:authorize>
		<sec:authorize url="/getTrackItems.action">
		<a class="btn  btn-primary" onclick="checkTrack()">查看跟踪</a>
		</sec:authorize>
		<sec:authorize url="/toAddTrack.action">
		<a class="btn  btn-primary" onclick="addTrack()">添加跟踪</a>
		</sec:authorize>
		<sec:authorize url="/toBuyCard.action">
		<a class="btn  btn-primary" onclick="buyCard()">客户买卡</a>
		</sec:authorize>
		</td>
	</tr>
	
</table>

</div>	
<form name="cardForm" id="cardForm"  action="saveCards" method="post">
<table class="table table-bordered  table-striped table-hover">
  <thead>
    <tr>
      <th></th>
      <th class="nowraptd">行号</th>
      <th class="nowraptd">姓名</th>
	  <th class="nowraptd">性别</th>
      <th class="nowraptd">手机</th>
      <th class="nowraptd">固定电话</th>
      <th class="nowraptd">地址</th>
       <th class="nowraptd">创建日期</th>
      <th class="nowraptd">跟踪次数</th>
      <th class="wraptd">备注</th>
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

     <td><s:property value="address"/></td>
     <td nowrap="nowrap"><s:property value="createDate"/></td>
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