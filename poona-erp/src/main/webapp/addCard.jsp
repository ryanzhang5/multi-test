<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
$(document).ready(function(){ 
	$('#newCardType').change(function(){ 
	var p1=$(this).children('option:selected').val();
	if(p1==1){$("#cardTimesTr").css('display','none');}
	if(p1==2 || p1==3){$('#cardTimesTr').removeAttr("style");}
	}); 
	}) 
</script>
</head>
<body>
<form name="addCardForm" id="addCardForm" method="post">
<table style="width:100%">
  <tr>
  <td>卡片类型</td> 
  <td>
  	<select name="cardType" id="newCardType">
  		<option value="1" selected="selected">期限卡</option>
  		<option value="2">次卡</option>
  		<option value="3">私教卡</option>
  	</select>
  </td>
  </tr>
  <tr>
  <td>卡片名称</td> <td><input type="text" id="cardName" name="cardName" onchange="checkName()"/>*</td>
  </tr>
  <tr id="cardTimesTr"  style="display:none">
  <td>次数</td> <td><input type="text" id="cardTimes" name="cardTimes"/>*</td>
  </tr>
  <tr>
  <td>卡片金额</td> <td><input type="text" id="cardFee" name="cardFee"/>*</td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments" cols="30" rows="5"></textarea></td>
  </tr>
</table>
</form>
</body>
</html>