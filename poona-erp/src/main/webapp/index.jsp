<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>poona-erp</title>
	<link href="css/styles.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/smoothness/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
<script>

$(document).ready(function() {
	 $("#content").load('getPaiedCustomers.action');
});

  function loadPage(url){
	  $("#content").load(url);
  }
  
</script>

</head>
<body>
<div id="wrapper" >
	
	<div id="menu">
		<ul>
			<li><a href="#" onclick="loadPage('getPaiedCustomers.action')">已办卡用户管理</a></li>
			<li><a href="#" onclick="loadPage('getNonPaiedCustomers.action')">意向用户管理</a></li>
			<li><a href="#" onclick="loadPage('getAllCards.action')">卡片管理</a></li>
		</ul>
	</div>
	<div id="content"></div>
</div>

</body>
</html>