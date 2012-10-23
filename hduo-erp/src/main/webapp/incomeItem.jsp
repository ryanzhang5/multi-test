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
				  var id = 3;
				  $("table.dynatable button.add").click(function() {
					  alert("first here " + id);
								  id++;
									prot = '<tr>'
									    +'<td style="width:80px">'+id+'<input type="hidden" name="incomeItem_id" value="" /><input type="hidden" id="new_status_'+id+'" name="status" value="new" /> '
									    +'</td>'
									    +'<td>'+<s:select name="productName" list="products" listKey="id" listValue="productName" theme="simple"></s:select>+'</td>'
									    +'<td><input type="text" name="num"      onchange=updateItem("new_status_'+id+'","new_updated")  id="num_'+id+'" /></td>'
									    +'<td><input type="text" name="price"    onchange=updateItem("new_status_'+id+'","new_updated") id="price_'+id+'" /></td>'
									    +'<td><input type="text" name="comments" onchange=updateItem("new_status_'+id+'","new_updated")  id="comments_'+id+'" /></td>'
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
				  
				  $( "#datepicker" ).datepicker({  
					 showOn: "button",           
					 buttonImage: "images/calendar.gif",  
					 buttonImageOnly: true,
					 dateFormat:"yy-mm-dd"
					 
				});
				$( "#datepicker" ).datepicker('setDate',new Date());
				  
  });
  
  function toSave(){
	  $("#incomeItemForm").submit();
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
  <td>入库日期: <input type="text" id="datepicker" /></td>
    <td align="right">
    
    <input id="save_button" type="button" onclick="toSave()" value="保存"/></td>
  </tr>
</table>
<form name="incomeItemForm" id="incomeItemForm"  action="saveIncomeItems" method="post">
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>商品名称</th>
      <th>数量</th>
      <th>入库价格</th>
      <th>备注</th>
      <th style="width:80px"><button class="add" type="button">添加</button></th>
    </tr>
  </thead>
  <tbody id="realBody">
    
        <s:iterator value="{1,2,3}" status="listStatus">
    <tr>
    <td style="width:80px">
            <s:property value="#listStatus.index+1"/>
            <input type="hidden" name="incomeItem_id"  value='' />
            <input type="hidden" name="status" id="status_<s:property value="#listStatus.index"/>"  value='new' />
    </td>
    <td><s:select name="productName" list="products" listKey="id" listValue="productName" theme="simple"></s:select></td>
    <td><input type="text" name="num"           onchange="updateItem('status_<s:property value="#listStatus.index"/>','new_updated')"  value='' /></td>
    <td><input type="text" name="price"         onchange="updateItem('status_<s:property value="#listStatus.index"/>,'new_updated')"   value='' /></td>
    <td><input type="text" name="comments"      onchange="updateItem('status_<s:property value="#listStatus.index"/>','new_updated')"  value='' /></td>
    <td style="width:80px" ><button type="button" onclick="deleteItem('status_<s:property value="#listStatus.index"/>','new_deleted')">删除</button></td>
  </tr>
    
    </s:iterator>
    
    
  
    
    
   <tbody>
</table>
</form>
</body>
</html>