<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"  href="css/styles.css"  type="text/css"/>
<script src="js/jquery-1.5.1.min.js"></script>
<script>

  $(document).ready(function() {
				  var id = '<s:property value="productNum"/>';
				  $("table.dynatable button.add").click(function() {
								  id++;
									prot = '<tr>'
									    +'<td style="width:80px">'+id+'<input type="hidden" name="product_id" value="" /><input type="hidden" id="new_status_'+id+'" name="status" value="new" /> '
									    +'</td>'
									    +'<td><input type="text" name="productName" class="editable" onchange=updateItem("new_status_'+id+'","new_updated")  id="productName_'+id+'" /></td>'
									    +'<td><input type="text" name="unit"        class="editable" onchange=updateItem("new_status_'+id+'","new_updated")  id="unit_'+id+'" /></td>'
									    +'<td><input type="text" name="comments"    class="editable" onchange=updateItem("new_status_'+id+'","new_updated")  id="comments_'+id+'" /></td>'
									    +'<td style="width:80px" >'
									    +'<button type="button" onclick=deleteItem("new_status_'+id+'","new_deleted")>删除</button></td>'
									    +'</tr>';
								  $("#save_button").css("background-color","#ff0000");	    
								  $("#realBody").append(prot);
				  });
				  
				  var clicked_row =null;
				  $("table.dynatable tr").click(function() {
					if(clicked_row != null)
						$(clicked_row).css("background","#ffffff");
					  $(this).css("background","#CCC");
					  clicked_row = this;
	              }); 
				  
				  $("#myform").submit(function() {
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
				  
				  
				  $('input[name="productName"]').live("change",function() {
					  $.ajax({
						  data:"productName="+$(this).attr("value"),
						  type:"post",
						  url:"checkProduct.action",
						  success:function(response){
							 if(response==1){
								 alert("商品已存在");
							 }
						  }
					  });
					  return false;
					 });
				  
				  
				  
  });
  
  function toSave(){
	 $("#myform").submit();
  }
  
  function deleteItem(item_id,newStatus){
	  $("#"+item_id).parents("tr").css("display","none");		
	  $("#"+item_id).attr("value",newStatus);
	  }  
  
  function updateItem(item_id,newStatus){
	  $("#"+item_id).attr("value",newStatus);
	  $("#save_button").css("background-color","#ff0000");
  }
  
  
</script>

</head>
<body>
<table style="width:100%">
  <tr align="right" >
  <td align="left">当前位置 &gt;&gt;&gt; 商品管理</td>
    <td align="right"><input id="save_button" type="button" onclick="toSave()" value="保存"/></td>
  </tr>
</table>
<form name="myform" id="myform"  action="saveProducts" method="post">
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>商品名称</th>
      <th>单位</th>
      <th>备注</th>
      <th style="width:80px"><button class="add" type="button">添加</button></th>
    </tr>
  </thead>
  <tbody id="realBody">
    
 
    <s:iterator value="products" status="listStatus">
    <tr>
    <td style="width:80px">
            <s:property value="#listStatus.index+1"/>
            <input type="hidden" name="product_id"  value='<s:property value="id"/>' />
            <input type="hidden" name="status" id="status_<s:property value="id"/>"  value='normal' />
    </td>
    <td><input type="text" name="productName"  class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='productName_<s:property value="id"/>' value='<s:property value="productName"/>' /></td>
    <td><input type="text" name="unit"         class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='unit_<s:property value="id"/>' value='<s:property value="unit"/>' /></td>
    <td><input type="text" name="comments"     class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='comments_<s:property value="id"/>'value='<s:property value="comments"/>' /></td>
    <td style="width:80px" ><button type="button" onclick="deleteItem('status_<s:property value="id"/>','deleted')">删除</button></td>
   </tr>
    
    </s:iterator>
    
   <tbody>
</table>
</form>
</body>
</html>