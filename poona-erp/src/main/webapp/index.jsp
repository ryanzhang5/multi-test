<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>poona-erp</title>
	<link href="css/styles.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/smoothness/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
    <link rel="stylesheet" href="js/uploadify/uploadify.css" type="text/css" media="screen"/>
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
   <script type="text/javascript" src="js/uploadify/jquery.uploadify.min.js"></script> 
	<link href="css/bootstrap.css" rel="stylesheet" />
	<script src="js/bootstrap.min.js"></script> 
	
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css" />
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.min.js"></script>
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
			<div class="navbar">
				<div class="navbar-inner" style="text-align: center;">
					<ul class="nav" style="display: inline-block;">
					<sec:authorize url="/getPaiedCustomers.action">
					<li><a href="#" onclick="loadPage('getPaiedCustomers.action')"><strong>已办卡客户管理</strong></a></li>
					<li class="divider-vertical"></li>
					 </sec:authorize>
					 <sec:authorize url="/getNonPaiedCustomers.action">
					<li><a href="#" onclick="loadPage('getNonPaiedCustomers.action')"><strong>意向客户管理</strong></a></li>
					<li class="divider-vertical"></li>
					</sec:authorize>
					<sec:authorize url="/getAllCards.action">
					<li><a href="#" onclick="loadPage('getAllCards.action')"><strong>卡片管理</strong></a></li>
					<li class="divider-vertical"></li>
					</sec:authorize>
					<sec:authorize url="/getAllDocs.action">
					<li><a href="#" onclick="loadPage('getAllDocs.action')"><strong>文档管理</strong></a></li>
					<li class="divider-vertical"></li>
					</sec:authorize>
					<sec:authorize url="/getAllCosts.action">
					<li><a href="#" onclick="loadPage('getAllCosts.action')"><strong>开支管理</strong></a></li>
					<li class="divider-vertical"></li>
					</sec:authorize>
					<sec:authorize url="/getAllUser.action">
					<li class="dropdown">
                      <a href="#" id="drop2" role="button" class="dropdown-toggle" data-toggle="dropdown"><strong>后台管理</strong><b class="caret"></b></a>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="loadPage('getAllUser.action')">用户管理</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="loadPage('getAllRole.action')">部门管理</a></li>
                      </ul>
                    </li>
                   </sec:authorize> 
				    </ul>
				    <ul class="nav pull-right" style="display: inline-block;">
                  <li ><a  href="#">用户 <sec:authentication property="principal.username"></sec:authentication> </a></li> 
                   <li ><a  href="j_spring_security_logout">退出</a></li> 
				    </ul>
				</div>
			</div>
	<div id="content"></div>
</div>



</body>
</html>