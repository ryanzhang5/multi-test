<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"  href="css/styles.css"  type="text/css"/>
<script src="js/jquery-1.8.2.js"></script>
<script>
  $(document).ready(function() {
				  var id = '<s:property value="clientNum"/>';
				  $("table.dynatable button.add").click(function() {
								  id++;
									prot = '<tr>'
									    +'<td style="width:80px">'+id+'<input type="hidden" name="client_id" value="" /><input type="hidden" id="new_status_'+id+'" name="status" value="new" /> '
									    +'</td>'
									    +'<td><input type="text" name="clientName" class="editable" onchange=updateItem("new_status_'+id+'","new_updated")  id="clientName_'+id+'" /></td>'
									    +'<td><input type="text" name="storeName"  class="editable" onchange=updateItem("new_status_'+id+'","new_updated")  id="storeName_'+id+'" /></td>'
									    +'<td><input type="text" name="address"    class="editable" onchange=updateItem("new_status_'+id+'","new_updated")  id="address_'+id+'" /></td>'
									    +'<td><input type="text" name="mobilePhone" class="editable" onchange=updateItem("new_status_'+id+'","new_updated")  id="mobilePhone_'+id+'" /></td>'
									    +'<td><input type="text" name="deskPhone"   class="editable" onchange=updateItem("new_status_'+id+'","new_updated")  id="deskPhone_'+id+'" /></td>'
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
				  
				  $("#clientsForm").submit(function() {
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
				  
				  $('input[name="clientName"]').live("change",function() {
					  $.ajax({
						  data:"clientName="+$(this).attr("value"),
						  type:"post",
						  url:"checkClient.action",
						  success:function(response){
							 if(response==1){
								 alert("客户已存在");
							 }
						  }
					  });
					  return false;
					 });
				  
  });
  
  function toSave(){
	  $("#clientsForm").submit();
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
  <td align="left">当前位置 &gt;&gt;&gt; 客户管理</td>
    <td align="right"><input id="save_button" type="button" onclick="toSave()" value="保存"/></td>
  </tr>
</table>
<form name="clientsForm" id="clientsForm"  action="saveClients" method="post">
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
      <th style="width:80px"><button class="add" type="button">添加</button></th>
    </tr>
  </thead>
  <tbody id="realBody">
    
 
    <s:iterator value="clients" status="listStatus">
    <tr>
    <td style="width:80px">
            <s:property value="#listStatus.index+1"/>
            <input type="hidden" name="client_id"  value='<s:property value="id"/>' />
            <input type="hidden" name="status" id="status_<s:property value="id"/>"  value='normal' />
    </td>
    <td><input type="text" name="clientName" class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='clientName_<s:property value="id"/>' value='<s:property value="clientName"/>' /></td>
    <td><input type="text" name="storeName"  class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='storeName_<s:property value="id"/>' value='<s:property value="storeName"/>' /></td>
	<td><input type="text" name="address"    class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='address_<s:property value="id"/>' value='<s:property value="address"/>' /></td>
    <td><input type="text" name="mobilePhone" class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='mobilePhone_<s:property value="id"/>'  value='<s:property value="mobilePhone"/>' /></td>
    <td><input type="text" name="deskPhone"   class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='deskPhone_<s:property value="id"/>' value='<s:property value="deskPhone"/>' /></td>
    <td><input type="text" name="comments"    class="editable" onchange="updateItem('status_<s:property value="id"/>','updated')" id='comments_<s:property value="id"/>'value='<s:property value="comments"/>' /></td>
    <td style="width:80px" ><button type="button" onclick="deleteItem('status_<s:property value="id"/>','deleted')">删除</button></td>
   </tr>
    
    </s:iterator>
    
   <tbody>
</table>
</form>
</body>
</html>