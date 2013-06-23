<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>

var current_id ;

$(document).ready(function () {
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
function updateItem(id){
	current_id=id;
	$("#update_div").load( "toUpdateNonPaidCustomer.action?customerId="+id );
	$("#update_div").dialog( "open" );
}
function checkTrack(id){
	$("#checkTrack_div").load( "getTrackItems.action?customerId="+id );
	$("#checkTrack_div").dialog( "open" );
}
function addTrack(id){
	current_id=id;
	$("#addTrack_div").load( "toAddTrack.action");
	$("#addTrack_div").dialog( "open" );
}
function buyCard(id){
	current_id=id;
	$("#buyCard_div").load( "toBuyCard.action?customerId="+id);
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



</script>

</head>
<body>
<table style="width:100%">
  <tr align="right" >
  <td align="left">当前位置 &gt;&gt;&gt; 意向用户管理</td>
  </tr>
</table>
<form name="cardForm" id="cardForm"  action="saveCards" method="post">
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
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
      <th colspan="4"><input onclick="addNew()" type="button" value="添加意向客户"/></th>
    </tr>
  </thead>
  <tbody>
    <s:iterator value="nonPaidCustomers" status="listStatus">
    <tr>
    <td style="width:80px"><s:property value="#listStatus.index+1"/></td>
    
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


    
    
    <td style="width:80px">
    	<button type="button" onclick="updateItem('<s:property value="id"/>')">修改信息</button>
    </td>
    <td style="width:80px">
        <button type="button" onclick="checkTrack('<s:property value="id"/>')">查看跟踪</button>
    </td>
     <td style="width:80px">
        <button type="button" onclick="addTrack('<s:property value="id"/>')">添加跟踪</button>
    </td>
     <td style="width:80px">
        <button type="button" onclick="buyCard('<s:property value="id"/>')">客户买卡</button>
    </td>
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