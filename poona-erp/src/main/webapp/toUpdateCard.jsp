<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<form name="updateCardForm" id="updateCardForm" method="post">
<table style="width:100%">
  <tr>
  <td>卡片名称</td> <td><input type="text" id="cardName" name="cardName" value='<s:property value="card.cardName"/>'  onchange="checkName()"/></td>
  </tr>
  <tr>
  <td>费用</td> <td><input type="text" id="cardFee" name="cardFee" value='<s:property value="card.cardFee"/>'  onchange="checkFee()"/></td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments"   cols="30" rows="5"><s:property value="card.comments"/></textarea></td>
  </tr>
</table>
</form>
</body>
</html>