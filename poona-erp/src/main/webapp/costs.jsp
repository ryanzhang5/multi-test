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
		
		$( "#cost_from" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 1,
			 dateFormat:"yy-mm-dd",
			onSelect: function( selectedDate ) {
				$( "#cost_to" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#cost_to" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 1,
			dateFormat:"yy-mm-dd",
			onSelect: function( selectedDate ) {
				$( "#cost_from" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
		
		
				$("#add_new_cost_div").dialog({
						autoOpen : false,
						height : 450,
						width : 450,
						title : "添加开支项",
						buttons : [{
							text : "Ok",
							click : function() {
								if (!validateForm()) {
										return;
								} else {
									$.ajax({
										type : "post",
										url : "saveCost.action",
										data : $("#addCostForm").serialize(),
										success : function(dt) {
												if (dt == 0) alert("操作成功");
												if (dt == 1) alert("操作失败");
												$("#content").load('getAllCosts.action');
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

			});

	function addNewCost() {
		$("#add_new_cost_div").load("toAddCost.action");
		$("#add_new_cost_div").dialog("open");
	}
	function searchCost() {
		var costFrom = $("#cost_from").val();
		var costTo = $("#cost_to").val();
		if(costFrom == "" || costTo == ""){
			alert("请输入开始和结束日期");
			return;
		}
		$("#content").load('getAllCosts.action?costFrom='+costFrom+"&costTo="+costTo);
	}

	function deleteCost() {
	      var count = $("input[name='costId']:checked").size();
			 if(count > 1){
				 alert('只能选择一条记录进行删除!');
				 return ;
			 }else if(count<1){
				 alert('请选择要删除的记录!');
				 return;
			 }else{
	 		 current_id = $($("input[name='costId']:checked")[0]).val();
	 		if(confirm("确定删除此记录?")){
	 			$.ajax({
					type : "post",
					url : "deleteCost.action",
					data : "costId="+ current_id,
					success : function(dt) {
						if (dt == 0)alert("删除成功");
						if (dt == 1)alert("删除失败");
						$("#content").load('getAllCosts.action');
					},
				});
			 }
		}
			
		}
	function validateForm() {
		var cardFee = $("#costAmount").val();
		if (cardFee == "") {
			alert("请输入费用!!");
			return false;
		}
		if (isNaN(cardFee)) {
			alert("费用必须为数字!!");
			return false;
		}
		var costItem = $("#costItem").val();
		if (costItem == "") {
			alert("请输入开支条目!!");
			return false;
		}
		return true;

	}
</script>

</head>
<body>
<div style="background-color:#f5f5f5">
<table style="width:100%">
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
		<td>首页/开支管理</td>
		<td align="right">
		  开始日期<input type="text"  id="cost_from" value='<s:property value="costFrom" />' name="cost_from"/>-----结束日期<input  value='<s:property value="costTo" />' type="text" id="cost_to" name="cost_to"/>
	    <a class="btn btn-primary" onclick="searchCost()">查询</a>
		<a class="btn btn-primary" onclick="addNewCost()">添加</a>
		<a class="btn btn-primary" onclick="deleteCost()">删除</a>
		</td>
	</tr>
	
</table>

</div>				

	<form name="costForm" id="costForm" action="saveCosts" method="post">
		<table class="table table-bordered  table-striped table-hover">
			<thead>
				<tr>
				    <th></th>
				    <th>序号</th>
					<th>条目</th>
					<th>金额</th>
					<th>日期</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="costList" status="listStatus">
					<tr >
						<td><input type="checkbox" name="costId" value='<s:property value="id"/>'/></td>
						<td><s:property value="#listStatus.index+1" /></td>
						<td><s:property value="costItem" /></td>
						<td><s:property value="costAmount" /></td>
						<td><s:date name="costDate" format="yyyy-MM-dd" /></td>
						<td><s:property value="comment" /></td>
					</tr>
				</s:iterator>
				<tr >
						<td></td>
						<td></td>
						<td></td>
						<td>总计:<s:property value="totalCost" /></td>
						<td></td>
						<td></td>
					</tr>
			<tbody>
		</table>
	</form>
	<div id="add_new_cost_div"></div>
</body>
</html>