<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<SCRIPT type="text/javascript">
var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, <s:property value="jsonString"/>);
});





function getCheckedNodes(){
	var resourceId = "";
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	for(var i=0;i<nodes.length;i++){
		resourceId+=nodes[i].id;
		if(nodes.length!=i+1){
			resourceId+=",";
		}
	}
	
	$.ajax({
		type : "post",
		url : "roleAuthorize.action",
		data : "roleId="+ $("#roleId").val()+"&resourceId="+resourceId,
		success : function(dt) {
			if (dt == 0)alert("操作成功");
			if (dt == 1)alert("操作失败");
			$("#content").load('getAllRole.action');
		},
	});
	
	
}




	</SCRIPT>

</head>
<body>
<div style="background-color:#f5f5f5">
<table style="width:100%">
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
		<td>后台管理/部门管理/分配权限</td>
	</tr>
	
</table>
当前部门:<font color="red"><s:property value="role.roleName"/><input type="hidden" name="roleId" id="roleId" value='<s:property value="role.id"/>' /></font>
</div>
<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
</div>
<a class="btn" data-dismiss="modal" onclick="getCheckedNodes();">完 成</a>
</body>
</html>