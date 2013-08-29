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
				$("#add_new_role_div").dialog({
						autoOpen : false,
						height : 450,
						width : 450,
						title : "添加部门",
						buttons : [{
							text : "Ok",
							click : function() {
								if (!validateForm()) {
										return;
								} else {
									$.ajax({
										type : "post",
										url : "saveRole.action",
										data : $("#addRoleForm").serialize(),
										success : function(dt) {
												if (dt == 0) alert("操作成功");
												if (dt == 1) alert("操作失败");
												$("#content").load('getAllRole.action');
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


			});

	function addNewRole() {
		$("#add_new_role_div").load("toAddRole.action");
		$("#add_new_role_div").dialog("open");
	}

	function deleteRole() {
      var count = $("input[name='roleId']:checked").size();
		 if(count > 1){
			 alert('只能选择一条记录进行修改!');
			 return ;
		 }else if(count<1){
			 alert('请选择要修改的记录!');
			 return;
		 }else{
 		 current_id = $($("input[name='roleId']:checked")[0]).val();
 		if(confirm("确定删除此记录?")){
 			$.ajax({
				type : "post",
				url : "deleteRole.action",
				data : "roleId="+ current_id,
				success : function(dt) {
					if (dt == 0)alert("删除成功");
					if (dt == 1)alert("删除失败");
					$("#content").load('getAllRole.action');
				},
			});
		 }
	}
		
	}
	
	function authorize(){
		 var count = $("input[name='roleId']:checked").size();
		 if(count > 1){
			 alert('只能选择一条记录进行权限分配!');
			 return ;
		 }else if(count<1){
			 alert('请选择要分配权限的部门!');
			 return;
		 }else{
 		 current_id = $($("input[name='roleId']:checked")[0]).val();
 		 $("#content").load('getAllResource.action?roleId='+current_id);
		}
		
	}



	function validateForm() {
		var roleName = $("#roleName").val();
		if (roleName == "") {
			alert("请输入部门名称!!");
			return false;
		}
		return true;

	}
</script>

</head>
<body>
<div style="background-color:#f5f5f5">
<table style="width:100%">
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
		<td>首页/部门管理</td>
		<td align="right">
		<sec:authorize url="/toAddRole.action">
		<a class="btn btn-primary" onclick="addNewRole()">添加</a>
		</sec:authorize>
		<sec:authorize url="/deleteRole.action">
		<a class="btn btn-primary" onclick="deleteRole()">删除</a>
		</sec:authorize>
		<sec:authorize url="/getAllResource.action">
		<a class="btn btn-primary" onclick="authorize()">分配权限</a>
		</sec:authorize>
		</td>
	</tr>
	
</table>

</div>				
	<form name="cardForm" id="cardForm" action="saveCards" method="post">
		<table class="table table-bordered  table-striped table-hover">
			<thead>
				<tr>
				    <th></th>
					<th class="nowraptd">行号</th>
					<th class="nowraptd">部门名称</th>
					<th class="wraptd">备注</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="roleList" status="listStatus">
					<tr >
						<td><input type="checkbox" name="roleId" value='<s:property value="id"/>'/></td>
						<td><s:property value="#listStatus.index+1" /></td>
						<td><s:property value="roleName" /></td>
						<td><s:property value="comment" /></td>
					</tr>

				</s:iterator>
			<tbody>
		</table>
	</form>
	<div id="add_new_role_div"></div>
	<div id="delete_role_div"></div>
</body>
</html>