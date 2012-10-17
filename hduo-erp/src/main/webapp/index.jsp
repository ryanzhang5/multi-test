<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>hduo-erp</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script src="js/jquery-1.5.1.min.js"></script>
<script>
  function loadPage(url){
	  alert(url);
	  $("#content").load(url);
  }
  
  
</script>

</head>
<body>
<div id="wrapper">
	
	<div id="menu">
		<ul>
			<li><a onclick="loadPage()">入库</a></li>
			<li><a href="#">入库统计</a></li>
			<li><a href="#">出库</a></li>
			<li><a href="#">出库统计</a></li>
			<li><a href="#">库存盘点</a></li>
			<li><a href="#">商品管理</a></li>
			<li><a onclick="loadPage('getAllClients.action')">客户管理</a></li>
		</ul>
	</div>
	<div id="content">
	</div>
</div>

</body>
</html>