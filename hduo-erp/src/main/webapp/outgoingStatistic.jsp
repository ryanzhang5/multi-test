<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/ui-lightness/jquery-ui-1.9.0.custom.css" rel="stylesheet">
<script src="js/jquery-1.8.2.js"></script>
<script src="js/jquery-ui-1.9.0.custom.js"></script>
<script src="js/jquery.ui.datepicker-zh-CN.js"></script>

<script>
  $(document).ready(function() {
				  var clicked_row =null;
				  $("table.dynatable tr").click(function() {
					if(clicked_row != null)
						$(clicked_row).css("background","#ffffff");
					  $(this).css("background","#CCC");
					  clicked_row = this;
	              }); 
					 $("#outgoingItemStasticForm").submit(function() {
						  $.ajax({
							  data:$(this).serialize(),
							  type:$(this).attr('method'),
							  url:$(this).attr('action'),
							  success:function(response){
								  $('#content').html(response);
							  }
						  });
						  return false;
				    });
					 $('#ui-datepicker-div').remove();

  });
  
  $(function() {
		$( "#from" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 2,
			dateFormat:"yy-mm-dd",
			onSelect: function( selectedDate ) {
				$( "#to" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#to" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 2,
			dateFormat:"yy-mm-dd",
			onSelect: function( selectedDate ) {
				$( "#from" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
		$( "#from" ).datepicker('setDate','<s:property value="from"/>');
		$( "#to" ).datepicker('setDate','<s:property value="to"/>');
		calcula();
		
	});

  
  
  function calcula(){
		var total_num  =0;
		var total_price = 0;
		$.each($('input[name="sum"]'),function(index){
			var row_status = $($('input[name="status"]')[index]).attr("value"); 
			if(row_status != "new_deleted" && row_status != "deleted"){
				var mynum = $($('input[name="sum"]')[index]).attr("value");
				var myprice=$($('input[name="price"]')[index]).attr("value");
				total_num   = total_num + parseInt(mynum);
				var item_price = parseInt(mynum)*parseFloat(myprice);
				total_price = total_price +  item_price;
				$($('input[name="itemprice"]')[index]).attr("value",item_price);
			}
		}); 
		 $("#total_price").attr("value",total_price); 
		 $("#total_num").attr("value",total_num); 
  
  }
</script>

</head>
<body>
<form name="outgoingItemStasticForm" id="outgoingItemStasticForm"  action="outgoingItemsStatistic" method="post">
<table style="width:100%">
  <tr align="right" >
  <td align="left">当前位置 &gt;&gt;&gt; 出库统计</td>
  <td>客户名称：<s:select name="clientId"  id="myclients" list="clients"   listKey="id" listValue="clientName"  theme="simple" headerKey="-1" headerValue="全部"  value="%{clientId}"></s:select></td>
  <td>商品名称：<s:select name="productId" id="myProducts" list="products" listKey="id" listValue="productName" theme="simple" headerKey="-1" headerValue="全部"  value="%{productId}"></s:select></td>
  <td>出库日期--从:<input type="text" id="from" name="from"/>到:<input type="text" id="to" name="to"/></td>
    <td align="right">
    <input id="save_button" type="submit" value="查询"/></td>
  </tr>
</table>
</form>
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>客户名称</th>
      <th>商品名称</th>
	  <th>价格</th>
	  <th>数量</th>
	  <th>金额</th>
	  <th>出库日期</th>
    </tr>
  </thead>
  <tbody id="realBody">
    <s:iterator value="printItems" status="listStatus">
    <tr>   
    <td style="width:80px"><s:property value="#listStatus.index+1"/></td>
    <td><s:property value="client.clientName"/></td>
    <td><s:property value="product.productName"/></td>
	<td><input type="text" name="price"     readonly="readonly"    value='<s:property value="price"/>' /></td>
	<td><input type="text" name="sum"        readonly="readonly"    value='<s:property value="sum"/>' /></td>
	<td><input type="text" name="itemPrice"  readonly="readonly"    value='<s:property value="itemPrice"/>' /></td>
    <td><s:date name="date" format="yyyy-MM-dd"/></td>
        
   </tr>
    </s:iterator>
   <tbody>
   <tr>
   <td>合计</td>
   <td colspan="3"></td>
   <td>总数量:<input style="width:80px" type="text" name="" id="total_num"  value='0' /></td>
   <td>总金额:<input style="width:80px" type="text" name="" id="total_price"  value='0.0' /></td>
   <td></td>
   </tr>
   
</table>
</body>
</html>