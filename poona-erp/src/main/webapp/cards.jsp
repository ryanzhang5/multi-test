<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
	var current_id;

	$(document).ready(function() {
				$("#add_new_card_div").dialog({
						autoOpen : false,
						height : 450,
						width : 450,
						title : "添加卡片类型",
						buttons : [{
							text : "Ok",
							click : function() {
								if (!validateForm()) {
										return;
								} else {
									$.ajax({
										type : "post",
										url : "saveCards.action",
										data : $("#addCardForm").serialize(),
										success : function(dt) {
												if (dt == 0) alert("操作成功");
												if (dt == 1) alert("操作失败");
												$("#content").load('getAllCards.action');
											},
										});
										$(this).dialog("close");
											}
										}
									},
									{
										text : "Cancel",
										click : function() {
										$(this).dialog("close");
										}}]
										});

						$("#update_card_div").dialog(
										{
											autoOpen : false,
											height : 350,
											width : 450,
											title : "修改卡片类型",
											buttons : [{
														text : "Ok",
														click : function() {
															$.ajax({
																type : "post",
																		url : "updateCard.action",
																		data : $("#updateCardForm").serialize()+ "&cardId="+ current_id,
																		success : function(dt) {
																			if (dt == 0) alert("操作成功");
																			if (dt == 1) alert("操作失败");
																			$("#content").load('getAllCards.action');
																		},
																	});
															$(this).dialog("close");
														}
													},
													{
														text : "Cancel",
														click : function() {
															$(this).dialog("close");
														}} ]});
			});

	function addNewCard() {
		$("#add_new_card_div").load("toAddCard.action");
		$("#add_new_card_div").dialog("open");
	}
	function updateCard(id) {
		current_id = id;
		$("#update_card_div").load("toUpdateCard.action?cardId=" + id);
		$("#update_card_div").dialog("open");
	}
	function deleteCard() {
      var count = $("input[name='cardId']:checked").size();
		 if(count > 1){
			 alert('只能选择一条记录进行修改!');
			 return ;
		 }else if(count<1){
			 alert('请选择要修改的记录!');
			 return;
		 }else{
 		 current_id = $($("input[name='cardId']:checked")[0]).val();
 		if(confirm("确定删除此记录?")){
 			$.ajax({
				type : "post",
				url : "deleteCard.action",
				data : "cardId="+ current_id,
				success : function(dt) {
					if (dt == 0)alert("删除成功");
					if (dt == 1)alert("删除失败");
					$("#content").load('getAllCards.action');
				},
			});
		 }
	}
		
	}

	function checkName() {
		var cardName = $("#cardName").val();
		if (cardName == '') {
			alert("请输入卡片名称!!");
			$("#cardName").focus();
		}
		$.ajax({
			type : "post",
			url : "checkCard.action",
			data : "cardName=" + cardName,
			success : function(dt) {
				if (dt == 1) {
					alert("同名卡片已存在，请更改名称!!");
					$("#cardName").focus();
				};
			},
		});
	}

	function validateForm() {
		var cardFee = $("#cardFee").val();
		if (cardFee == "") {
			alert("请输入费用!!");
			return false;
		}
		if (isNaN(cardFee)) {
			alert("费用必须为数字!!");
			return false;
		}
		var p1 = $("#cardType").children('option:selected').val();
		if (p1 == 2) {
			var cardTimes = $("#cardTimes").val();
			if (cardTimes == "") {
				alert("请输入次数!!");
				return false;
			}
			if (isNaN(cardTimes)) {
				alert("次数必须为数字");
				return false;
			}
		}
		return true;

	}
</script>

</head>
<body>
<div>
	<ul class="breadcrumb">
		<li><a href="#">首页</a> <span class="divider">/</span></li>
		<li class="active">卡片管理</li>
	</ul>
	<div class="pull-right">
		<a class="btn btn-primary" onclick="addNewCard()">添加</a>
		<a class="btn btn-primary" onclick="deleteCard()">删除</a>
	</div>
</div>				
	<form name="cardForm" id="cardForm" action="saveCards" method="post">
		<table class="table table-bordered  table-striped table-hover">
			<thead>
				<tr>
				    <th></th>
					<th>行号</th>
					<th>卡片类型</th>
					<th>卡片名称</th>
					<th>次数</th>
					<th>费用</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="cards" status="listStatus">
					<tr >
						<td><input type="checkbox" name="cardId" value='<s:property value="id"/>'/></td>
						<td><s:property
								value="#listStatus.index+1" /></td>
						<td><s:if test="cardType==1">期限卡</s:if> <s:if
								test="cardType==2">次卡</s:if> <s:if test="cardType==3">私教卡</s:if>
						</td>
						<td><s:property value="cardName" /></td>
						<td><s:if test="cardType==1">&nbsp;&nbsp;&nbsp;&nbsp;</s:if>
							<s:if test="cardType==2">
								<s:property value="cardTimes" />
							</s:if> <s:if test="cardType==3">
								<s:property value="cardTimes" />
							</s:if></td>
						<td><s:property value="cardFee" /></td>
						<td><s:property value="comments" /></td>
					</tr>

				</s:iterator>
			<tbody>
		</table>
	</form>
	<div id="add_new_card_div"></div>
	<div id="update_card_div"></div>
</body>
</html>