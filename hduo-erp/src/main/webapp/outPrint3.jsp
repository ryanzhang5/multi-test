<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
body {
	width: 700px;
	text-align: center;
	margin: 0 auto;
}

.dynatable {
	border: solid 1px #000;
	border-collapse: collapse;
	width: 100%;
}

.dynatable th,.dynatable td {
	border: solid 1px #000;
	padding: 0px 0px;
	text-align: center;
	height: 30px;
}

.dynatable td input {
	width: 100%;
	height: 100%;
	font-size: 16px;
	border: #000;
}

.headertable {
	width: 100%;
}

.headertable th {
	width: 100%;
	text-align: center;
	font-size: 35px;
	font-weight: bold;
}

.headertable td {
	padding: 0px 0px;
	text-align: left;
	height: 30px;
}

.headertable td input {
	width: 80%;
	height: 100%;
	font-size: 18px;
	border: #000;
}
</style>
</head>
<body>
	<table class="headertable">
		<tr align="center">
			<th colspan="6">出货单</th>
		</tr>
		<tr>
			<td style="width: 35%" colspan="2" nowrap="nowrap">客户名称：<input
				type="text" value="<s:property value="client.clientName"/>" /></td>
			<td style="width: 40%" colspan="2" nowrap="nowrap">地址：<input
				type="text" value="<s:property value="client.address"/>" /></td>
			<td style="width: 25%" colspan="2" nowrap="nowrap">日期：<input
				type="text" value="<s:property value="outDate"/>" /></td>
		</tr>
	</table>
	<table class="dynatable">
		<thead>
			<tr>
				<th style="width: 80px">行号</th>
				<th>商品名称</th>
				<th>规格</th>
				<th>价格</th>
				<th>数量</th>
				<th>小计</th>
			</tr>
		</thead>
		<tbody id="realBody">
	<s:iterator  value="printItems" status="listStatus">
    <tr>
    <td style="width:80px"><s:property value="#listStatus.index+1"/></td>
    <td><s:property value="product.productName"/></td>
    <td><s:property value="product.unit"/></td>
    <td><s:property value="price"/></td>
    <td><s:property value="sum"/></td>
    <td><s:property value="itemPrice"/></td>
  </tr>
    
    </s:iterator>
		<tbody>
			<tr>
				<td>合计</td>
				<td colspan="2"></td>
				<td>总数量:<s:property value="totalNum"/></td>
				<td>总金额:<s:property value="totalPrice"/></td>
				<td></td>
			</tr>
			<tr>
				<td>说明:</td>
				<td colspan="5"><s:property value="printComment"/></td>
			</tr>
	</table>
</body>
</html>