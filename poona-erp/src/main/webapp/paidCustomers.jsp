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
	
	$( "#paid_from" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		 dateFormat:"yy-mm-dd",
		onSelect: function( selectedDate ) {
			$( "#paid_to" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$( "#paid_to" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		dateFormat:"yy-mm-dd",
		onSelect: function( selectedDate ) {
			$( "#paid_from" ).datepicker( "option", "maxDate", selectedDate );
		}
	});
	
	$( "#update_customer_div" ).dialog({
		 autoOpen: false,
		 height: 400,
	     width: 450,
	     title: "修改已购卡客户信息",
		buttons: [
			{
				text: "Ok",
				click: function() {
					  $.ajax({
				          type: "post",
				          url: "updatePaidCustomer.action",
				          data:$("#updatePaidForm").serialize(),
				          success: function (dt) {
				             if(dt==0) alert("操作成功");
				             if(dt==1) alert("操作失败");
				             $("#content").load('getPaiedCustomers.action');
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
	
	
	$( "#update_customer_comment_div" ).dialog({
		 autoOpen: false,
		 height: 400,
	     width: 450,
	     title: "修改已购卡客户备注",
		buttons: [
			{
				text: "Ok",
				click: function() {
					  $.ajax({
				          type: "post",
				          url: "updatePaidCustomerComment.action",
				          data:$("#updatePaidCommentForm").serialize(),
				          success: function (dt) {
				             if(dt==0) alert("操作成功");
				             if(dt==1) alert("操作失败");
				             $("#content").load('getPaiedCustomers.action');
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
	
	
	
	
	
	
	
	
	$( "#check_practice_div" ).dialog({
		autoOpen: false,
		 height: 500,
	     width: 600,
	     title: "查看上课记录",
	});
	
	
	$( "#add_practice_div" ).dialog({
		autoOpen: false,
		 height: 400,
	     width: 450,
	     title: "添加上课记录",
		buttons: [
			{
				text: "Ok",
				click: function() {
					  $.ajax({
				          type: "post",
				          url: "addPracticeRecord.action",
				          data:$("#addPracticeRecordForm").serialize(),
				          success: function (dt) {
				             if(dt==0) alert("操作成功");
				             if(dt==1) alert("操作失败");
				             $("#content").load('getPaiedCustomers.action');
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
	

	
	$( "#customerDetail_div" ).dialog({
		autoOpen: false,
		 height: 600,
	     width: 500,
	     title: "客户详情",
		buttons: [
			{
				text: "ok",
				click: function() {
					$(this).dialog( "close" );
				}
			}
		]
	});
	
});


function updateCustomer(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');return;			
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	$("#update_customer_div").load( "toUpdatePaidCustomer.action?customerId="+current_id );
	$("#update_customer_div").dialog( "open" );
}


function updateCustomerComment(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');return;			
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	$("#update_customer_comment_div").load( "toUpdatePaidCustomerComment.action?customerId="+current_id );
	$("#update_customer_comment_div").dialog( "open" );
}








function checkPractice(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');return;			
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	$("#check_practice_div").load( "check_customer_practice.action?customerId="+current_id );
	$("#check_practice_div").dialog( "open" );
}
function addPractice(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');return;			
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	$("#add_practice_div").load( "toAddPracticeRecord.action?customerId="+current_id);
	$("#add_practice_div").dialog( "open" );
}

function customerDetail(){
	var count = $("input[name='customerId']:checked").size();
	 if(count > 1){
		 alert('只能选择一条记录!');return;
	 }else if(count<1){
		 alert('请选择一条记录!');return;			
	 }else{
	 current_id = $($("input[name='customerId']:checked")[0]).val();
}
	$("#customerDetail_div").load( "getPaiedCustomerDetail.action?customerId="+current_id);
	$("#customerDetail_div").dialog( "open" );
}

function deleteCustomer(){
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
	             $("#content").load('getPaiedCustomers.action');
	          },
	          }); 
	 }
}


function searchPaid() {
	var costFrom = $("#paid_from").val();
	var costTo = $("#paid_to").val();
	if(costFrom == "" || costTo == ""){
		alert("请输入开始和结束日期");
		return;
	}
	$("#content").load('getPaiedCustomers.action?paidFrom='+costFrom+"&paidTo="+costTo);
}
</script>

</head>
<body>
<div style="background-color:#f5f5f5">
<table style="width:100%">
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
		<td>首页/已办卡用户管理</td>
		<td align="right">
		 办卡日期从<input type="text"  id="paid_from" value='<s:property value="paidFrom" />' />到<input  value='<s:property value="paidTo" />' type="text" id="paid_to" />
	    <a class="btn btn-primary" onclick="searchPaid()">查询</a>
		<sec:authorize url="/toUpdatePaidCustomer.action">
		<a class="btn  btn-primary" onclick="updateCustomer()">修改剩余次数</a>
		</sec:authorize>
		<sec:authorize url="/toUpdatePaidCustomerComment.action">
		<a class="btn  btn-primary" onclick="updateCustomerComment()">修改用户备注</a>
		</sec:authorize>
		<sec:authorize url="/toAddPracticeRecord.action">
		<a class="btn  btn-primary" onclick="addPractice()">添加上课记录</a>
		</sec:authorize>
		<sec:authorize url="/check_customer_practice.action">
		<a class="btn  btn-primary" onclick="checkPractice()">查看上课记录</a>
		</sec:authorize>
		<sec:authorize url="/getPaiedCustomerDetail.action">
		<a class="btn btn-primary"  onclick="customerDetail()">客户详情</a>
		</sec:authorize>
		<sec:authorize url="/deleteCustomer.action">
		<a class="btn btn-primary"  onclick="deleteCustomer()">删除</a>
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
      <th class="nowraptd">会员卡类型</th>
      <th class="nowraptd">购买价格</th>
      <th class="nowraptd">剩余次数</th>
      <th class="nowraptd">开始日期</th>
      <th class="nowraptd">结束日期</th>
      <th class="nowraptd">手机</th>
      <th class="nowraptd">固定电话</th>
      <th class="wraptd">备注</th>
    </tr>
  </thead>
  <tbody>
  
    <s:iterator value="paidCustomers" status="listStatus">
    <tr >
    <td><input type="checkbox" name="customerId" value='<s:property value="id"/>'/></td>
    <td>
            <s:property value="#listStatus.index+1"/>
    </td>
    <td><s:property value="name"/></td>
    <td><s:property value="cardName"/></td>
    <td><s:property value="realPay"/></td>
    <td style="background-color:<s:property value='leftTimeColor'/> ">
    <s:if test="cardType==1">&nbsp;&nbsp;&nbsp;&nbsp;</s:if>
    <s:if test="cardType==2"><s:property value="leftTimes"/></s:if>
    <s:if test="cardType==3"><s:property value="leftTimes"/></s:if>
    </td>
    <td><s:date name="from" format="yyyy-MM-dd" /></td>
    <td style="background-color:<s:property value='endDateColor'/> "><s:date name="to" format="yyyy-MM-dd" /></td>
    <td><s:property value="mobilePhone"/></td>
    
    <td><s:property value="deskPhone"/></td>
    <td><s:property value="comments"/></td>
   </tr>
    
    </s:iterator>
    
   <tbody>
</table>
</form>
  <div id="update_customer_div"></div>
  <div id="update_customer_comment_div"></div>
  <div id="check_practice_div"></div>
  <div id="add_practice_div"></div>
  <div id="customerDetail_div"></div>
  
</body>
</html>