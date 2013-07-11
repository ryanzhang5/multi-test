<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<form id="updatePaidCommentForm" method="post">
<table style="width:100%">
 <tr>
  <td>姓名</td> <td><s:property value="customer.name"/><input type="hidden" name="customerId" value='<s:property value="customer.id"/>'/></td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments" cols="30" rows="5"><s:property value="customer.comments"/></textarea></td>
  </tr>
</table>
</form>
</body>
</html>