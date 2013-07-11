<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
var current_id;
$(document).ready(function() {
	$('#file_upload').uploadify({
		'swf'      : 'js/uploadify/uploadify.swf',
		'uploader' : 'addDoc.action',
		'buttonText' : '上传附件',
		'fileTypeExts':'*.jpg;*.docx;*.doc;*.pdf;*.xls;*.xlsx',
		'onUploadSuccess' : function(file, data, response) {
			$("#content").load('getAllDocs.action');
		}
	});
  
    
});



function deleteDoc(id) {
		 current_id =id;
		 if(confirm("确定删除此记录?")){
			 $.ajax({
					type : "post",
					url : "deleteDoc.action",
					data : "docId="+ current_id,
					success : function(dt) {
						if (dt == 0)alert("删除成功");
						if (dt == 1)alert("删除失败");
						$("#content").load('getAllDocs.action');
					},
				});
		 }
	}
</script>

</head>
<body>
<div>
	<ul class="breadcrumb">
		<li><a href="#">首页</a> <span class="divider">/</span></li>
		<li class="active">文档管理</li>
	</ul>
	
	<div class="pull-right">
		 <form>
		<div id="queue"></div>
		<input id="file_upload" name="file_upload" type="file" multiple="true">
	  </form>
	</div>
</div>				
	<form name="docForm" id="docForm" action="docs" method="post">
		<table class="table table-bordered  table-striped table-hover">
			<thead>
				<tr>
				    <th></th>
					<th>行号</th>
					<th>文件名</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="docList" status="listStatus">
					<tr>
						<td><input type="checkbox" name="docId" value='<s:property value="id"/>'/></td>
						<td><s:property
								value="#listStatus.index+1" /></td>
						<td><s:property value="fileName" /></td>
						<td><a href='download.action?fileName=<s:property value="fileName" />'>下载</a></td>
						<td><a class="btn btn-primary" onclick="deleteDoc(<s:property value="id"/>)">删除</a></td>
								
						
					</tr>
				</s:iterator>
			<tbody>
		</table>
	</form>
</body>
</html>