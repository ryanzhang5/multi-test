<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="js/jquery-1.5.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#clientList').change(function() {
			alert($(this).val());
		});
	});
</script>

</head>
<body style="height:95%">
	client:
	<select name="clientName" id="clientList">
		<option value="client1">client1</option>
		<option value="client2">client2</option>
	</select>
	<br />
	<iframe id="outFrame" style="width:100%;height:90%;border:0px" name="outFrame"
		src="out_item.jsp"></iframe>

</body>
</html>