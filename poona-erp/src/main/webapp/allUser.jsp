<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
	var current_id;

	$(document).ready(function() {
				$("#add_new_user_div").dialog({
						autoOpen : false,
						height : 450,
						width : 450,
						title : "添加用户",
						buttons : [{
							text : "Ok",
							click : function() {
								if (!validateForm()) {
										return;
								} else {
									$.ajax({
										type : "post",
										url : "saveUser.action",
										data : $("#addUserForm").serialize(),
										success : function(dt) {
												if (dt == 0) alert("操作成功");
												if (dt == 1) alert("操作失败");
												$("#content").load('getAllUser.action');
											},
										});
										$(this).dialog("close");
											}
										}
									},
									{
										text : "Cancel",
										click : function() {
										$(this).dialog("close");
										}}]
										});
				
				$( "#reset_div" ).dialog({
					autoOpen: false,
					 height: 600,
				     width: 450,
				     title: "密码重置",
					buttons: [
						{
							text: "Ok",
							click: function() {
									  $.ajax({
								          type: "post",
								          url: "resetPassword.action",
								          data:$("#resetPasswordForm").serialize(),
								          success: function (dt) {
								             if(dt==0) alert("操作成功");
								             if(dt==1) alert("操作失败");
								             $("#content").load('getAllUser.action');
								          },
								          }); 
									$(this).dialog( "close" );

							}
						},
						{
							text: "Cancel",
							click: function() {
								$(this).dialog( "close" );
							}
						}
					]
				});
				
				
				$("#assign_department_div").dialog({
					autoOpen : false,
					height : 450,
					width : 450,
					title : "分配部门",
					buttons : [{
						text : "Ok",
						click : function() {
							
							var departmentId = "";
							$("input[name='myRoleName']:checked").each(function(){
								 departmentId += $(this).attr("value")+",";
							 });
							
								$.ajax({
									type : "post",
									url : "assignDepartment.action",
									data : "departmentId="+departmentId+"&userId="+$("#userId").val(),
									success : function(dt) {
											if (dt == 0) alert("操作成功");
											if (dt == 1) alert("操作失败");
											$("#content").load('getAllUser.action');
										},
									});
									$(this).dialog("close");
									}
								},
								{
									text : "Cancel",
									click : function() {
									$(this).dialog("close");
									}}]
									});


			});

	function addNewUser() {
		$("#add_new_user_div").load("toAddUser.action");
		$("#add_new_user_div").dialog("open");
	}

	function deleteUser() {
      var count = $("input[name='userId']:checked").size();
		 if(count > 1){
			 alert('只能选择一条记录进行修改!');
			 return ;
		 }else if(count<1){
			 alert('请选择要修改的记录!');
			 return;
		 }else{
 		 current_id = $($("input[name='userId']:checked")[0]).val();
 		if(confirm("确定删除此记录?")){
 			$.ajax({
				type : "post",
				url : "deleteUser.action",
				data : "userId="+ current_id,
				success : function(dt) {
					if (dt == 0)alert("删除成功");
					if (dt == 1)alert("删除失败");
					$("#content").load('getAllUser.action');
				},
			});
		 }
	}
		
	}



	function validateForm() {
		var userName = $("#userName").val();
		if (userName == "") {
			alert("用户名!!");
			return false;
		}
		return true;

	}
	
	function assignDepartment(){
		var count = $("input[name='userId']:checked").size();
		 if(count > 1){
			 alert('只能选择一条记录进行修改!');
			 return ;
		 }else if(count<1){
			 alert('请选择要修改的记录!');
			 return;
		 }else{
		 current_id = $($("input[name='userId']:checked")[0]).val();
		 $("#assign_department_div").load("toAssignDepartment.action?userId="+current_id);
		 $("#assign_department_div").dialog("open");
	}
		
	}
	
	
	function resetPassword(){
		var count = $("input[name='userId']:checked").size();
		 if(count > 1){
			 alert('只能选择一条记录进行修改!');
			 return ;
		 }else if(count<1){
			 alert('请选择要修改的记录!');
			 return;
		 }else{
		 current_id = $($("input[name='userId']:checked")[0]).val();
		 $("#reset_div").load( "toResetPassword.action?userId="+current_id );
		 $("#reset_div").dialog( "open" );
	}
		
	}
	
	
	
	
	
	
	
	function checkUserName(){
	var name=$("#backendUser").val();
	  $.ajax({
        type: "post",
        url: "checkUserExist.action",
        data: "name=" +name,
        success: function (dt) {
           if(dt==1) {alert("同名用户已存在，请更改名称!!");$("#backendUser").focus();};
        },
        }); 
}
</script>

</head>
<body>
<div style="background-color:#f5f5f5">
<table style="width:100%">
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
		<td>首页/用户管理</td>
		<td align="right">
		<sec:authorize url="/toAddUser.action">
		<a class="btn btn-primary" onclick="addNewUser()">添加</a>
		</sec:authorize>
		<sec:authorize url="/deleteUser.action">
		<a class="btn btn-primary" onclick="deleteUser()">删除</a>
		</sec:authorize>
		<sec:authorize url="/toResetPassword.action">
		<a class="btn btn-primary" onclick="resetPassword()">重置密码</a>
		</sec:authorize>
		<sec:authorize url="/toAssignDepartment.action">
		<a class="btn btn-primary" onclick="assignDepartment()">分配部门</a>
		</sec:authorize>
		</td>
	</tr>
	
</table>

</div>				
		<table class="table table-bordered  table-striped table-hover">
			<thead>
				<tr>
				    <th></th>
					<th class="nowraptd">行号</th>
					<th class="nowraptd">用户名</th>
					<th class="nowraptd">真实姓名</th>
					<th class="wraptd">备注</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="userList" status="listStatus">
					<tr >
						<td><input type="checkbox" name="userId" value='<s:property value="id"/>'/></td>
						<td><s:property value="#listStatus.index+1" /></td>
						<td><s:property value="userName" /></td>
						<td><s:property value="realName" /></td>
						<td><s:property value="comment" /></td>
					</tr>

				</s:iterator>
			<tbody>
		</table>
	<div id="add_new_user_div"></div>
	<div id="delete_user_div"></div>
	<div id="assign_department_div"></div>
	<div id="reset_div"></div>
</body>
</html>