<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
body{
width:700px;
text-align: center;
margin: 0 auto;
}
.dynatable {
	border: solid 1px #000;
	border-collapse: collapse;
	width:100%;
}
.dynatable th, .dynatable td {
	border: solid 1px #000;
	padding: 0px 0px;
	text-align: center;
	height: 30px;
}
.dynatable td input {
	width:100%;
	height:100%;
	font-size:16px;
	border:#000;
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
	width:80%;
	height:100%;
	font-size:18px;
	border:#000;
}
</style>
</head>
<body >
	<table class="headertable">
		<tr align="center">
			<th colspan="6">出货单</th>
		</tr>
		<tr >
			<td style="width:35%" colspan="2" nowrap="nowrap">客户名称：<input type="text" value="沃尔玛" /></td>
			<td style="width:40%" colspan="2" nowrap="nowrap">地址：<input type="text" value="中山南路中山南路" /></td>
			<td style="width:25%" colspan="2" nowrap="nowrap">日期：<input type="text" value="2012-10-10" /></td>
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
				<th>备注</th>
			</tr>
		</thead>
		<tbody id="realBody">
			<tr id="1">
				<td style="width: 80px">1</td>
				<td>ssssssssss</td>
				<td><input type="text" name="name[]" value="" /></td>
				<td><input type="text" name="num" id="num_1" value="0" /></td>
				<td><input type="text" name="price" id="price_1" value="0.0" /></td>
				<td><input type="text" name="sum" id="sum_1" value="0" /></td>
				<td><input type="text" name="name[]" value="" /></td>
			</tr>
			<tr id="2">
				<td style="width: 80px">2</td>
				<td><input type="text" name="name[]" value="" /></td>
				<td><input type="text" name="name[]" value="" /></td>
				<td><input type="text" name="num" id="num_2" value="0" /></td>
				<td><input type="text" name="price" id="price_2" value="0.0" /></td>
				<td><input type="text" name="sum" id="sum_2" value="0" /></td>
				<td><input type="text" name="name[]" value="" /></td>
			</tr>
			<tr id="3">
				<td style="width: 80px">3</td>
				<td><input type="text" name="name[]" value="" /></td>
				<td><input type="text" name="name[]" value="" /></td>
				<td><input type="text" name="num" id="num_3" value="0" /></td>
				<td><input type="text" name="price" id="price_3" value="0.0" /></td>
				<td><input type="text" name="sum" id="sum_3" value="0" /></td>
				<td><input type="text" name="name[]" value="" /></td>
			</tr>
		<tbody>
			<tr>
				<td>合计</td>
				<td colspan="3"></td>
				<td>总数量:<input style="width: 80px" type="text" name=""
					id="total_num" value='0' /></td>
				<td>总金额:<input style="width: 80px" type="text" name=""
					id="total_price" value='0.0' /></td>
				<td colspan="1">sss</td>
			</tr>
			<tr>
				<td colspan="7">说明：</td>
			</tr>
	</table>
</body>
</html>