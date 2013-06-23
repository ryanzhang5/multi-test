<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
$(function() {
	$( "#buy_card_from" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		 dateFormat:"yy-mm-dd",
		onSelect: function( selectedDate ) {
			$( "#buy_card_to" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$( "#buy_card_to" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		dateFormat:"yy-mm-dd",
		onSelect: function( selectedDate ) {
			$( "#buy_card_from" ).datepicker( "option", "maxDate", selectedDate );
		}
	});
	
});

/* $(document).ready(function(){ 
	$('#allMyCards').change(function(){ 
	var cardId=$(this).children('option:selected').val();
	  $.ajax({
          type: "post",
          url: "checkCardType.action",
          data: "cardId=" + cardId,  
          success: function (dt) {
        	  $("#cardType").val(dt);
          },
          }); 
	}); 
	});  */
</script>

</head>
<body>
<form id="buyCardForm" method="post">
<table style="width:100%">
 <tr>
  <td>姓名</td> <td><s:property value="customer.name"/><input type="hidden" name="cardType" id="cardType" value="0"/></td>
  </tr>
  <tr>
  <td>卡片类型</td> <td><s:select name="myCardType" list="cards" listKey="id" listValue="cardName" theme="simple" id="allMyCards"></s:select></td>
  </tr>
   <tr>
  <td>卡号</td> <td><input type="text" id="cardNumber" name="cardNumber"/>*</td>
  </tr>
   <tr>
  <td>购买价格</td> <td><input type="text" id="realPay" name="realPay"/>*</td>
  </tr>
  <!--  <tr>
  <td>购买次数</td> <td><input type="text" name="buyTimes"/></td>
  </tr> -->
  <tr>
  <td>开始日期</td> <td><input type="text" id="buy_card_from" name="from"/>*</td>
  </tr>
   <tr>
  <td>结束日期</td> <td><input type="text" id="buy_card_to" name="to"/>*</td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments" cols="30" rows="5"></textarea></td>
  </tr>
</table>
</form>
</body>
</html>