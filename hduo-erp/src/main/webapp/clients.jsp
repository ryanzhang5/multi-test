<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"  href="./css/styles.css"  type="text/css"/>
<script src="js/jquery-1.5.1.min.js"></script>
<script>
  $(document).ready(function() {
				  var id = 3;
				  $("table.dynatable button.add").click(function() {
								  id++;
								  var master = $(this).parents("table.dynatable");
								  // Get a new row based on the prototype row
								 // var prot = master.find(".prototype").clone();
								 // prot.attr("class", "")
								 // prot.find(".id").attr("value", id);
								  prot = '<tr id="'+id+'"><input type="hidden" name="item_id" value="'+id+'" />'
								       +'<td style="width:80px">'+id+'</td>'
								       +'<td><input type="text" name="name[]" value="" /></td>'
									   +'<td><input type="text" name="name[]" value="" /></td>'
									   +'<td><input type="text" name="num"    value="0"   id="num_'   +id+'"/></td>'
									   +'<td><input type="text" name="price"  value="0.0" id="price_' +id+'"/></td>'
									   +'<td><input type="text" name="sum"    value="0"   id="sum_'   +id+'"/></td>'
									   +'<td><input type="text" name="name[]" value="" /></td>'
								       +'<td style="width:80px"><button  class="remove">删除</button></td>'
									   +'</tr>';
								  master.find("#realBody").append(prot);
				  });

				  $("table.dynatable button.remove").live("click", function() {
								  $(this).parents("tr").remove();
								  var item_id = $(this).parents("tr").attr("id");
								 calcula(item_id);
				  });
				  $('input[name="num"]').live("change", function() {								 
								 var item_id = $(this).parents("tr").attr("id");
								calcula(item_id);
								 
				  });
				  $('input[name="price"]').live("change", function() {	
								var item_id = $(this).parents("tr").attr("id");
								calcula(item_id);
								
				  });
  });
  
  
  function calcula(item_id){
	   var price = $("#price_"+item_id).attr("value");							
								 var num   = $("#num_"+item_id).attr("value");								
								 $("#sum_"+item_id).attr("value",price*num);
								 var num_array = $('input[name="num"]');
								 var total_num  =0;
								 $.each(num_array,function(){
									total_num =total_num + parseInt($(this).val());
								
								});
								 $("#total_num").attr("value",total_num);
								 
								 var sum_array = $('input[name="sum"]');
								 var total_price  =0;
								 $.each(sum_array,function(){
									total_price	=total_price + parseFloat($(this).val());
								
								});
								 $("#total_price").attr("value",total_price); 
	  
	  }
  
  
</script>

</head>
<body>
<table>
  <tr>
    <td>ssssssssssssssssss</td>
  </tr>
</table>
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>名称</th>
      <th>联系人</th>
      <th>地址</th>
      <th>手机</th>
      <th>固定电话</th>
      <th>备注</th>
      <th style="width:80px"><button class="add">添加</button></th>
    </tr>
  </thead>
  <tbody id="realBody">
    
   <tr id="1">
   	<input type="hidden" name="item_id" value="1" />
    <td style="width:80px">1</td>
    <td><input type="text" name="name[]" value="" /></td>
    <td><input type="text" name="name[]" value="" /></td>
    <td><input type="text" name="num"   id="num_1"   value="0" /></td>
    <td><input type="text" name="price" id="price_1" value="0.0" /></td>
    <td><input type="text" name="sum"   id="sum_1"   value="0" /></td>
    <td><input type="text" name="name[]" value="" /></td>
    <td style="width:80px"><button  class="remove">删除</button></td>
   </tr>
   <tr id="2">
   <input type="hidden" name="item_id" value="2" />
    <td style="width:80px">2</td>
    <td><input type="text" name="name[]" value="" /></td>
    <td><input type="text" name="name[]" value="" /></td>
	<td><input type="text" name="num"   id="num_2"   value="0" /></td>
    <td><input type="text" name="price" id="price_2" value="0.0" /></td>
    <td><input type="text" name="sum"   id="sum_2"   value="0" /></td>
    <td><input type="text" name="name[]" value="" /></td>
    <td style="width:80px"><button  class="remove">删除</button></td>
   </tr> 
    <tr id="3">
    <input type="hidden" name="item_id" value="3" />
    <td style="width:80px">3</td>
    <td><input type="text" name="name[]" value="" /></td>
    <td><input type="text" name="name[]" value="" /></td>
	<td><input type="text" name="num"   id="num_3"   value="0" /></td>
    <td><input type="text" name="price" id="price_3" value="0.0" /></td>
    <td><input type="text" name="sum"   id="sum_3"   value="0" /></td>
    <td><input type="text" name="name[]" value="" /></td>
    <td style="width:80px"><button  class="remove">删除</button></td>
   </tr>
    
    
    
   <tbody>
   <tr><td style="width:80px">合计</td><td colspan="2"></td><td><input type="text" name="total_num" id="total_num" value="0" /></td><td></td><td><input type="text" name="total_price" id="total_price" value="0.0" /></td><td></td><td></td></tr>
   <tr><td style="width:80px">说明</td><td colspan="7">ssssssssssss</td></tr>
</table>

</body>
</html>