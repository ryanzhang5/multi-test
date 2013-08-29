<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<form name="addUserForm" id="addUserForm" method="post">
<table style="width:100%">
  <tr>
  <td>用户名</td> <td><input id="backendUser" type="text" id="userName" name="userName"   onblur="checkUserName()"/>*</td>
  </tr>
   <tr>
  <td>真实姓名</td> <td><input type="text" id=realName" name="realName"/>*</td>
  </tr>
   <tr>
  <td>密码</td> <td><input type="password" id=password" name="password"/>*</td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments" cols="30" rows="5"></textarea></td>
  </tr>
</table>
</form>
</body>
</html>