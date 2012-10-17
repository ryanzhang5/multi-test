<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"  href="./css/styles.css"  type="text/css"/>
<script src="js/jquery-1.5.1.min.js"></script>
<script>
  $(document).ready(function() {
				  var id = '<s:property value="clientNum"/>';
				  $("table.dynatable button.add").click(function() {
								  id++;
								  var master = $(this).parents("table.dynatable");
								  // Get a new row based on the prototype row
								 // var prot = master.find(".prototype").clone();
								 // prot.attr("class", "")
								 // prot.find(".id").attr("value", id);
									   
									prot = '<tr>'
									    +'<td style="width:80px">'+id+'<input type="hidden" name="client_id" value="" /><input type="hidden" id="new_status_'+id+'" name="status_'+id+'" value="new" /> '
									    +'</td>'
									    +'<td><input type="text" name="clientName"   id="clientName_'+id+'" /></td>'
									    +'<td><input type="text" name="storeName"   id="storeName_'+id+'" /></td>'
									    +'<td><input type="text" name="address"   id="address_'+id+'" /></td>'
									    +'<td><input type="text" name="mobilePhone"   id="mobilePhone_'+id+'" /></td>'
									    +'<td><input type="text" name="deskPhone"   id="deskPhone_'+id+'" /></td>'
									    +'<td><input type="text" name="comments"   id="comments_'+id+'" /></td>'
									    +'<td style="width:80px" >'
									    +'<button  onclick=deleteItem("new_status_'+id+'","new_deleted")>删除</button></td>'
									    +'</tr>';
								  master.find("#realBody").append(prot);
				  });
  });
  
  
  function deleteItem(item_id,newStatus){
	  
	  alert
	  $("#"+item_id).parents("tr").css("display","none");		 
	  $("#"+item_id).attr("value",newStatus);
	  }  
  
  function updateItem(item_id,newStatus){
	  $("#"+item_id).attr("value",newStatus);
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
      <th>客户名称</th>
      <th>店名</th>
      <th>地址</th>
      <th>手机</th>
      <th>固定电话</th>
      <th>备注</th>
      <th style="width:80px"><button class="add">添加</button></th>
    </tr>
  </thead>
  <tbody id="realBody">
    
 
    <s:iterator value="clients" status="listStatus">
    <tr>
    <td style="width:80px">
            <s:property value="#listStatus.index+1"/>
            <input type="hidden" name="client_id"  value='<s:property value="id"/>' />
            <input type="hidden" name="status_<s:property value="id"/>" id="status_<s:property value="id"/>"  value='normal' />
    </td>
    <td><input type="text" name="clientName"  onchange="updateItem('status_<s:property value="id"/>','updated')" id='clientName_<s:property value="id"/>' value='<s:property value="clientName"/>' /></td>
    <td><input type="text" name="storeName"   onchange="updateItem('status_<s:property value="id"/>','updated')" id='storeName_<s:property value="id"/>' value='<s:property value="storeName"/>' /></td>
	<td><input type="text" name="address"     onchange="updateItem('status_<s:property value="id"/>','updated')" id='address_<s:property value="id"/>' value='<s:property value="address"/>' /></td>
    <td><input type="text" name="mobilePhone" onchange="updateItem('status_<s:property value="id"/>','updated')" id='mobilePhone_<s:property value="id"/>'  value='<s:property value="mobilePhone"/>' /></td>
    <td><input type="text" name="deskPhone"   onchange="updateItem('status_<s:property value="id"/>','updated')" id='deskPhone_<s:property value="id"/>' value='<s:property value="deskPhone"/>' /></td>
    <td><input type="text" name="comments"    onchange="updateItem('status_<s:property value="id"/>','updated')" id='comments_<s:property value="id"/>'value='<s:property value="comments"/>' /></td>
    <td style="width:80px" ><button  onclick="deleteItem('status_<s:property value="id"/>','deleted')">删除</button></td>
   </tr>
    
    </s:iterator>
    
   <tbody>
</table>

</body>
</html>