<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
  $(document).ready(function() {
	$( "#datepicker" ).datepicker({  
			 dateFormat:"yy-mm-dd"
		});
	$( "#datepicker" ).datepicker('setDate',new Date());
		//  data:$(this).serialize()+"&incomeDate="+$( "#datepicker" ).attr("value"),
		
  });

</script>

</head>
<body>
<form id="addTrackForm" method="post">
<table style="width:100%">
  <tr>
  <td>跟踪日期</td> <td><input type="text" id="datepicker"  name="datepicker"/>*</td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments" cols="30" rows="5"></textarea></td>
  </tr>
</table>
</form>
</body>
</html>