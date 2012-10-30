<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>hduo-erp</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" media="screen" />
<script src="js/jquery-1.8.2.js"></script>
<script>

$(document).ready(function() {
	 $("#content").load('toAddIncomeItem.action');
});

  function loadPage(url){
	  $('#ui-datepicker-div').remove();
	  $("#content").load(url);
  }
  
</script>

</head>
<body>
<div id="wrapper" >
	
	<div id="menu">
		<ul>
			<li><a href="#" onclick="loadPage('toAddIncomeItem.action')">入库</a></li>
			<li><a href="#" onclick="loadPage('incomeItemsStatistic.action')">入库统计</a></li>
			<li><a href="#" onclick="loadPage('outgoing.action')">出库</a></li>
			<li><a href="#" onclick="loadPage('outgoingItemsStatistic.action')">出库统计</a></li>
			<li><a href="#" onclick="loadPage('getAllInventoryItems.action')">库存盘点</a></li>
			<li><a href="#" onclick="loadPage('getAllProducts.action')">商品管理</a></li>
			<li><a href="#" onclick="loadPage('getAllClients.action')">客户管理</a></li>
		</ul>
	</div>
	<div id="content"></div>
</div>

</body>
</html>