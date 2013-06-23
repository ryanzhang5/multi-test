<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
  $(document).ready(function() {
	$( "#practiceDatepicker" ).datepicker({  
			 dateFormat:"yy-mm-dd"
		});
	$( "#practiceDatepicker" ).datepicker('setDate',new Date());
  });

</script>

</head>
<body>
<form id="addPracticeRecordForm" method="post"><input type="hidden" name="customerId" value='<s:property value="customerId"/>'/>
<table style="width:100%">
  <tr>
  <td>上课日期</td> <td><input type="text" id="practiceDatepicker"  name="practiceDatepicker"/>*</td>
  </tr>
  <tr>
  <td>课程名称</td> <td><input type="text" name="className"/></td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments" cols="30" rows="5"></textarea></td>
  </tr>
</table>
</form>
</body>
</html>