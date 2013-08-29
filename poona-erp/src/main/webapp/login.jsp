<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>poona-erp</title>
<link href="css/styles.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/smoothness/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="js/uploadify/uploadify.css" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="js/uploadify/jquery.uploadify.min.js"></script>
<script src="js/jquery-1.9.1.js"></script>
<link href="css/bootstrap.css" rel="stylesheet" />
<style>
#login ul {
	margin: 0;
}

#login form {
	margin: 0;
}

.login-footer {
	text-align: center;
	background: #45b9d1;
	margin: 0;
	padding: 5px 0;
	color: #eee;
}

#login-wrapper {
	width: 400px;
	margin: 12% auto;
	overflow: hidden;
	position: relative;
	box-shadow: 0 0 10px #000;
	-moz-box-shadow: 0 0 10px #000;
	-webkit-box-shadow: 0 0 10px #000;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px
}

.login-box {
	background: #fff;
}

.login-box ul {
	padding: 10px 15px;
}

.login-box li {
	clear: both;
	list-style: none;
	margin-bottom: 7px;
	position: relative;
}

.login-box li label {
	display: block;
	width: 100px;
	float: left;
	padding-top: 4px;
}

.login-text-box {
	border: #ccc 1px solid !important;
	padding: 5px 3px;
	background: #fff url(../image/input-boxt.png) repeat-x top;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

#login-header {
	height: 58px;
	background: url(../image/bg-login-header.png) no-repeat;
	position: relative;
}

.login-left {
	position: absolute;
	left: 150px;
	top: 15px;
	width: 150px;
	font-size: 14px;
	line-height: 25px;
}

</style>
<script>
	function toCheck() {
		$.ajax({
			type : "post",
			url : "checkUser.action",
			data : $("#loginForm").serialize(),
			success : function(dt) {
				if (dt == 1) {
					alert("用户名/密码不正确!!");
					window.location.href = "login.jsp";
				} else if (dt == 0) {
					$("#loginForm").submit();
				}
			}
		});
	}
</script>
</head>
<body id="login">
	<div id="login-wrapper">
	<div id="login-header">
		<div class="login-left"><i class="icon-block-color user-business-boss-c"></i> 管理员登录</div>
    </div>
		<div class="login-box">
			<form id="loginForm" action="j_spring_security_check" method="post">
				<ul>
					<li><label>用户名：</label><input name="j_username"
						id="j_username" type="text" class="login-text-box"></li>
					<li><label>密码：</label><input name="j_password" id="j_password"
						type="password" class="login-text-box usr"></li>
					<li><label>&nbsp;</label><input value="登录" type="button"  onclick="toCheck()" /></li>
				</ul>
			</form>
		</div>
	</div>
</body>
</html>