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
	

	
	
	
});


function updateCustomer(id){
	current_id=id;
	$("#update_customer_div").load( "toUpdatePaidCustomer.action?customerId="+id );
	$("#update_customer_div").dialog( "open" );
}
function checkPractice(id){
	$("#check_practice_div").load( "check_customer_practice.action?customerId="+id );
	$("#check_practice_div").dialog( "open" );
}
function addPractice(id){
	current_id=id;
	$("#add_practice_div").load( "toAddPracticeRecord.action?customerId="+id);
	$("#add_practice_div").dialog( "open" );
}


</script>

</head>
<body>
<table style="width:100%">
  <tr align="right" >
  <td align="left">当前位置 &gt;&gt;&gt; 已办卡用户管理</td>
  </tr>
</table>
<form name="cardForm" id="cardForm"  action="saveCards" method="post">
<table class="dynatable">
  <thead>
    <tr>
      <th>行号</th>
      <th>会员卡类型</th>
      <th>姓名</th>
      <th>性别</th>
      <th>购买价格</th>
      <th>剩余次数</th>
      <th>开始日期</th>
      <th>结束日期</th>
      <th>手机</th>
      <th>固定电话</th>
      <th>地址</th>
      <th>职务</th>
      <th>公司</th>
      <th>国籍</th>
      <th>备注</th>
      <th></th>
      <th>上课记录</th>
    </tr>
  </thead>
  <tbody>
  
    <s:iterator value="paidCustomers" status="listStatus">
    <tr>
    <td>
            <s:property value="#listStatus.index+1"/>
    </td>
    <td><s:property value="card.cardName"/></td>
    <td><s:property value="name"/></td>
     <td>
	  <s:if test="sex==0">女</s:if>
      <s:if test="sex==1">男</s:if>
     </td>
    <td><s:property value="realPay"/></td>
    <td>
    <s:if test="card.cardType==1">&nbsp;&nbsp;&nbsp;&nbsp;</s:if>
    <s:if test="card.cardType==2"><s:property value="leftTimes"/></s:if>
    <s:if test="card.cardType==3"><s:property value="leftTimes"/></s:if>
    </td>
    <td><s:date name="from" format="yyyy-MM-dd" /></td>
    <td><s:date name="to" format="yyyy-MM-dd" /></td>
    <td><s:property value="mobilePhone"/></td>
    
    <td><s:property value="deskPhone"/></td>
    <td><s:property value="address"/></td>
    <td><s:property value="career"/></td> 
    <td><s:property value="company"/></td> 
     <td><s:property value="nationality"/></td> 
    <td><s:property value="comments"/></td>
    <td>
    	<button type="button" onclick="updateCustomer('<s:property value="id"/>')">修改剩余次数</button>
    </td>
    <td>
    	<button type="button" onclick="checkPractice('<s:property value="id"/>')">查看</button>
    	<button type="button" onclick="addPractice('<s:property value="id"/>')">添加</button>
    </td>
   </tr>
    
    </s:iterator>
    
   <tbody>
</table>
</form>
  <div id="update_customer_div"></div>
  <div id="check_practice_div"></div>
  <div id="add_practice_div"></div>
</body>
</html>