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

<script>
  $(document).ready(function() {
				  var clicked_row =null;
				  $("table.dynatable tr").click(function() {
					if(clicked_row != null)
						$(clicked_row).css("background","#ffffff");
					  $(this).css("background","#CCC");
					  clicked_row = this;
	              }); 

					 $("#inventoryItemStasticForm").submit(function() {
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
					 
					 $('input[name="real_num"]').change(function() {
						 var num =$(this).attr("value");
						 if(isNaN(num) || parseInt(num) < 0){
							 alert("实际数量必须是非零整数");
							 return;
						 }
					 });
  });
  
  function updateItem(item_id,newStatus){
	  $("#"+item_id).attr("value",newStatus);
	  $("#save_button").css("background-color","#ff0000");
  }
</script>

</head>
<body>
<form name="inventoryItemStasticForm" id="inventoryItemStasticForm"  action="saveInventoryItemsStatistic" method="post">
<table style="width:100%">
  <tr align="right" >
  <td align="left">当前位置 &gt;&gt;&gt; 库存盘点</td>
    <td align="right"><input id="save_button" type="submit" value="保存"/></td>
  </tr>
</table>

<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>商品名称</th>
      <th>数量</th>
      <th>实际数量</th>
      <th>盘点日期</th>
      <th>备注</th>
    </tr>
  </thead>
  <tbody id="realBody">
    <s:iterator value="inventoryItems" status="listStatus">
    <tr>   
 	    <td style="width:80px"><s:property value="#listStatus.index+1"/>
 	        <input type="hidden" name="inventoryItem_id"  value='<s:property value="id"/>' />
            <input type="hidden" name="status" id="status_<s:property value="id"/>"  value='normal' />
 	    </td>
	    <td><s:property value="product.productName"/></td>
	    <td><s:property value="sum"/></td>
		<td><input type="text" name="real_num" class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" value='<s:property value="sum"/>' /></td>
	    <td><s:date name="date" format="yyyy-MM-dd"/></td>
	    <td><input type="text" name="comments" onchange="updateItem('status_<s:property value="id"/>','updated')"  value='<s:property value="comments"/>' /></td>
   </tr>
    </s:iterator>
   <tbody>
</table>
</form>
</body>
</html>