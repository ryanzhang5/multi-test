<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"  href="./css/styles.css"  type="text/css"/>
<script src="js/jquery-1.5.1.min.js"></script>

</head>
<body>
<table>
  <tr>
    <td>ssssssssssssssssss</td>
  </tr>
</table>
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>品名规格</th>
      <th>单位</th>
      <th>数量</th>
      <th>单价</th>
      <th>金额</th>
      <th>备注</th>
      <th style="width:80px"><button class="add">添加</button></th>
    </tr>
  </thead>
  <tbody id="realBody">
    
 
    <s:iterator>
    <tr id="3">
    <td style="width:80px">3</td>
    <td><input type="text" name="name[]" value='<s:property value="clientName"/>' /></td>
    <td><input type="text" name="name[]" value='<s:property value="storeName"/>' /></td>
	<td><input type="text" name="name[]" value='<s:property value="address"/>' /></td>
    <td><input type="text" name="name[]" value='<s:property value="mobilePhone"/>' /></td>
    <td><input type="text" name="name[]" value='<s:property value="deskPhone"/>' /></td>
    <td><input type="text" name="name[]" value='<s:property value="comments"/>' /></td>
    <td style="width:80px"><button  class="remove">删除</button></td>
   </tr>
    
    </s:iterator>
    
   <tbody>
   <tr><td style="width:80px">合计</td><td colspan="2"></td><td><input type="text" name="total_num" id="total_num" value="0" /></td><td></td><td><input type="text" name="total_price" id="total_price" value="0.0" /></td><td></td><td></td></tr>
   <tr><td style="width:80px">说明</td><td colspan="7">ssssssssssss</td></tr>
</table>

</body>
</html>