
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
				  
				  $( "#datepicker" ).datepicker({  
					 dateFormat:"yy-mm-dd"
				});
				$( "#datepicker" ).datepicker('setDate',new Date());
				
				
				
				
				 $("#outgoingItemForm").submit(function() {
					  $.ajax({
						  data:$(this).serialize()+"&outgoingDate="+$( "#datepicker" ).attr("value"),
						  type:$(this).attr('method'),
						  url:$(this).attr('action'),
						  success:function(response){
							  $('#content').html(response);
						  }
					  });
					  return false;
			    });
				$("#print_button").click(function() {
					$("#printUrl").attr('href','outgoingPrint.action?'+$("#outgoingItemForm").serialize()+"&outgoingDate="+$( "#datepicker" ).attr("value"));
			    });
				
				
				 $('input[name="sum"]').change(function() {
					 var num =$(this).attr("value");
					 if(isNaN(num) || parseInt(num) < 0){
						 alert("出库数量必须是非零整数");
						 $(this).attr("value",0);
						 calcula();
						 return;
					 }else{
						 var inv = parseInt($(this).parents("tr").find("input[name='inventoryNum']").attr("value"));
						 if(parseInt(num) > inv){
					    alert("出库数量必须不大于库存数");
					    $(this).attr("value",0);
						 calcula();
						 return;
						 }
					 }	 
				 });
				 
				 $('input[name="price"]').change(function() {
					 var price =$(this).attr("value");
					 if(isNaN(price) || parseFloat(price) < 0){
						 alert("出库数量必须是非零数");
						 $(this).attr("value",0);
						 calcula();
						 return;
					 }else{
						 var incomePrice = parseFloat($(this).parents("tr").find("input[name='latestIncomePrice']").attr("value"));
						 if(incomePrice > parseFloat(price)){
					    var r = confirm("出库价格不低于最新入库价格,继续出库吗?")
							if(r==false){
								 $(this).attr("value",incomePrice);
								 calcula();
							}
						 }
					 }
				 });
				
				
				
				 
				 $("#myclients").change(function() {
					  $.ajax({
						  data:"clientId="+$(this).attr("value"),
						  type:"post",
						  url:"outgoing.action",
						  success:function(response){
							  $('#content').html(response);
						  }
					  });
					  return false;
			    });
				 
				 calcula();
				  
  });
  
  
  
  function deleteItem(item_id,newStatus){
	  $("#"+item_id).parents("tr").css("display","none");		
	  $("#"+item_id).attr("value",newStatus);
	  $("#save_button").css("background-color","#ff0000");
	  calcula();
	  }  
  
  function updateItem(item_id,newStatus){
	  $("#"+item_id).attr("value",newStatus);
	  $("#save_button").css("background-color","#ff0000");
	  calcula();
  }
  
  
  
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
				$($('input[name="item_price"]')[index]).attr("value",item_price);
			}
		}); 
		 $("#total_price").attr("value",total_price); 
		 $("#total_num").attr("value",total_num); 
  
  }
  
  
  
  
</script>

</head>
<body>
<form name="outgoingItemForm" id="outgoingItemForm"  action="saveoutgoingItem" method="post">
<table style="width:100%">
  <tr align="right" >
  <td align="left">当前位置 &gt;&gt;&gt; 出库</td>
  <td>客户名称：<s:select name="clientName" id="myclients" list="clients" listKey="id" listValue="clientName" theme="simple" headerKey="-1" headerValue="------"  value="%{clientId}"></s:select></td>
  <td>出库日期: <input type="text" id="datepicker" /></td>
  <td align="right"><input id="save_button" type="submit" value="保存"/></td>
  <td align="right" ><a href="outgoingPrint.action" target="_blank" id="printUrl"><input id="print_button" type="button"  value="打印"/></a></td>
  </tr>
</table>
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>商品名称</th>
      <th>最新入库价格</th>
      <th>出库价格</th>
      <th>库存</th>
      <th>数量</th>
      <th>金额</th>
      <th>备注</th>
      <th style="width:80px"></th>
    </tr>
  </thead>
  <tbody id="realBody">
   <s:iterator  var="myproduct" value="items" status="listStatus">
    <tr>
    <td style="width:80px">
            <s:property value="#listStatus.index+1"/>
            <input type="hidden" name="product_id"  value='<s:property value="id"/>' />
            <input type="hidden" name="status" id="status_<s:property value="#listStatus.index"/>"  value='new' />
    </td>
    <td><input type="text" name="productName"           readonly="readonly"   value='<s:property value="productName"/>' /></td>
    <td><input type="text" name="latestIncomePrice"     readonly="readonly"   value='<s:property value="latestIncomePrice"/>' /></td>
    <td><input type="text" name="price"          class="editable"                      onchange="updateItem('status_<s:property value="#listStatus.index"/>','updated')"   value='<s:property value="price"/>' /></td>
    <td><input type="text" name="inventoryNum"    readonly="readonly"   value='<s:property value="inventoryNum"/>' /></td>
    <td><input type="text" name="sum"             class="editable"                      onchange="updateItem('status_<s:property value="#listStatus.index"/>','nupdated')"   value='<s:property value="sum"/>' /></td>
    <td><input type="text" name="item_price"      readonly="readonly"                                                                                           value='' /></td>
    <td><input type="text" name="comments"        class="editable"                      onchange="updateItem('status_<s:property value="#listStatus.index"/>','updated')"  value='' /></td>
    <td style="width:80px" ><button type="button"                       onclick="deleteItem('status_<s:property value="#listStatus.index"/>','deleted')">删除</button></td>
  </tr>
    
    </s:iterator>
    
     <tbody>
   <tr>
   <td>合计</td>
   <td colspan="4"></td>
   <td>总数量:<input style="width:80px" type="text" name="total_num" id="total_num"  value='0' /></td>
   <td>总金额:<input style="width:80px" type="text" name="total_price" id="total_price"  value='0.0' /></td>
   <td ></td>
   </tr>
   <tr>
   <td>打印说明:</td>
   <td colspan="8"><input style="width:100%" type="text" name="print_comment"value='' /></td>
   </tr>
</table>
</form>
<form action="outgoingPrint.action" target="_blank" id="printForm" ></form>
</body>
</html>