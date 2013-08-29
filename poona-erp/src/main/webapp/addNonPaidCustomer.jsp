<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<form id="addNonPaidForm" method="post">
<table style="width:100%">
  <tr>
  <td>姓名</td> <td><input type="text" id="name" name="name" onblur="checkName()"/>*</td>
  </tr>
   <tr>
  <td>性别</td> 
  
  <td>
   <select name="sex" id="sex">
  		<option value="0" selected="selected">女</option>
  		<option value="1">男</option>
  	</select>
  </td>
  </tr>
  <tr>
  <td>国籍</td> <td><input type="text" id="nationality"  name="nationality" value="中国"/></td>
  </tr>
  <tr>
  <td>手机</td> <td><input type="text" id="mobilePhone"  name="mobilePhone" />*</td>
  </tr>
  <tr>
  <td>固定电话</td> <td><input type="text" id="deskPhone"  name="deskPhone" /></td>
  </tr>
    <tr>
  <td>职业</td> <td><input type="text" id="career"  name="career" /></td>
  </tr>
    <tr>
  <td>公司</td> <td><input type="text" id="company"  name="company" /></td>
  </tr>
  <tr>
  <td>地址</td> <td><input type="text" id="address"  name="address"/>*</td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments"  cols="30" rows="5"></textarea></td>
  </tr>
</table>
</form>
</body>
</html>