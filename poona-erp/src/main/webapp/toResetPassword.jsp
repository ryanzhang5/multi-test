<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<form name="resetPasswordForm" id="resetPasswordForm" method="post">
<table style="width:100%">
   <tr>
  <td>新密码</td> <td><input type="text" id="userId" name="userId" value='<s:property value="userId" />'/><input type="password" id="password" name="password"/>*</td>
  </tr>
</table>
</form>
</body>
</html>