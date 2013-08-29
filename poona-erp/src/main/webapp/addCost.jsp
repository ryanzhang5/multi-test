<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
$(function() {
	$( "#costDate" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		 dateFormat:"yy-mm-dd",
	});
});
</script>
</head>
<body>
<form name="addCostForm" id="addCostForm" method="post">
<table style="width:100%">
  <tr>
  <td>开支条目</td> 
  <td><input type="text" id="costItem" name="costItem"/></td>
  </tr>
  <tr>
  <td>日期</td> <td><input type="text" id="costDate" name="costDate"/></td>
  </tr>
  <tr>
  <td>金额</td> <td><input type="text" id="costAmount" name="costAmount"/>*</td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comment" name="comment" cols="30" rows="5"></textarea></td>
  </tr>
</table>
</form>
</body>
</html>