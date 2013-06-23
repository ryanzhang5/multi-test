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
	$( "#add_new_card_div" ).dialog({
		autoOpen: false,
		 height: 450,
	     width: 450,
	     title: "添加卡片类型",
		buttons: [
			{
				text: "Ok",
				click: function() {
					if(!validateForm()){
						return;
					}else{
						 $.ajax({
					          type: "post",
					          url: "saveCards.action",
					          data:$("#addCardForm").serialize(),
					          success: function (dt) {
					             if(dt==0) alert("操作成功");
					             if(dt==1) alert("操作失败");
					             $("#content").load('getAllCards.action');
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
	
	$( "#update_card_div" ).dialog({
		autoOpen: false,
		 height: 350,
	     width: 450,
	     title: "修改卡片类型",
		buttons: [
			{
				text: "Ok",
				click: function() {
					  $.ajax({
				          type: "post",
				          url: "updateCard.action",
				          data:$("#updateCardForm").serialize()+ "&cardId=" +current_id,
				          success: function (dt) {
				             if(dt==0) alert("操作成功");
				             if(dt==1) alert("操作失败");
				             $("#content").load('getAllCards.action');
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
	
	$( "#delete_card_div" ).dialog({
		autoOpen: false,
		 height: 200,
	     width: 300,
	     title: "删除卡片类型",
		buttons: [
			{
				text: "Ok",
				click: function() {
					  $.ajax({
				          type: "post",
				          url: "deleteCard.action",
				          data: "cardId=" + current_id,
				          success: function (dt) {
				             if(dt==0) alert("删除成功");
				             if(dt==1) alert("删除失败");
				             $("#content").load('getAllCards.action');
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

function addNewCard(){
	$("#add_new_card_div").load( "toAddCard.action" );  
	$("#add_new_card_div").dialog( "open" );
}
function updateCard(id){
	current_id=id;
	$("#update_card_div").load( "toUpdateCard.action?cardId="+id );
	$("#update_card_div").dialog( "open" );
}
function deleteCard(id){
	current_id=id;
	$("#delete_card_div").dialog( "open" );
}



function checkName(){
	var cardName=$("#cardName").val();
	if(cardName==''){alert("请输入卡片名称!!");$("#cardName").focus();}
	  $.ajax({
          type: "post",
          url: "checkCard.action",
          data: "cardName=" +cardName,
          success: function (dt) {
             if(dt==1) {alert("同名卡片已存在，请更改名称!!");$("#cardName").focus();};
          },
          }); 
}

function validateForm(){
	var cardFee=$("#cardFee").val();
	if(cardFee==""){
		  alert("请输入费用!!");
		  return false;
	  }
	 if(isNaN(cardFee)){
		  alert("费用必须为数字!!");
		  return false;
	  }
		var p1=$("#cardType").children('option:selected').val();
		if(p1==2){
			var cardTimes=$("#cardTimes").val();
			if(cardTimes==""){
				  alert("请输入次数!!");
				 return false;
			  }
			if(isNaN(cardTimes)){
				  alert("次数必须为数字");
				 return false;
			  }
		}
	return true;
	
}
</script>

</head>
<body>
<table style="width:100%">
  <tr align="right" >
  <td align="left">当前位置 &gt;&gt;&gt; 卡片管理</td>
  </tr>
</table>
<form name="cardForm" id="cardForm"  action="saveCards" method="post">
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>卡片类型</th>
      <th>卡片名称</th>
      <th>次数</th>
      <th>费用</th>
      <th>备注</th>
      <th><input onclick="addNewCard()" type="button" value="添加卡片类型"/></th>
    </tr>
  </thead>
  <tbody>
    <s:iterator value="cards" status="listStatus">
    <tr>
    <td style="width:80px">
            <s:property value="#listStatus.index+1"/>
    </td>
    <td>
    <s:if test="cardType==1">期限卡</s:if>
    <s:if test="cardType==2">次卡</s:if>
    <s:if test="cardType==3">私教卡</s:if>
    </td>
    <td><s:property value="cardName"/></td>
    <td>
     <s:if test="cardType==1">&nbsp;&nbsp;&nbsp;&nbsp;</s:if>
     <s:if test="cardType==2"><s:property value="cardTimes"/></s:if>
     <s:if test="cardType==3"><s:property value="cardTimes"/></s:if>
    </td>
    <td><s:property value="cardFee"/></td>
    <td><s:property value="comments"/></td>
    <td style="width:80px">
        <button type="button" onclick="deleteCard('<s:property value="id"/>')">删除</button>
    </td>
   </tr>
    
    </s:iterator>
    
   <tbody>
</table>
</form>
  <div id="add_new_card_div"></div>
  <div id="update_card_div"></div>
  <div id="delete_card_div">确认删除？</div>
</body>
</html>