<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>欢迎来到菩纳瑜伽</title>
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/common.jsp"%>
<link rel="shortcut icon" href="${ctx}/img/ico.ico" type="image/x-icon">
<link href="${ctx}/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/css/bootstrap-responsive.css" rel="stylesheet">
<style>
html,body {
	margin: 0px;
	height: 100%;
}

.imgdiv {
	width: 100%;
	height: 100%;
	margin: 0px auto;
}

img {
	width: 100%;
	height: 100%;
	display: block;
}

.navbar-wrapper {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	z-index: 10;
	margin-top: 20px;
	margin-bottom: -90px;
}

.container {
	width: 1000px;
}

.navbar .navbar-inner {
	border: 0;
	-webkit-box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
	-moz-box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
	box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
}

.navbar .brand {
	padding: 14px 20px 16px;
	font-size: 16px;
	font-weight: bold;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, .5);
}

.navbar .nav>li>a {
	padding: 9px 20px;
}

.navbar .btn-navbar {
	margin-top: 10px;
}

.carousel-control {
	height: 80px;
	margin-top: 0;
	font-size: 120px;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
	background-color: transparent;
	border: 0;
	z-index: 10;
}
</style>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="${ctx}/js/html5shiv.js"></script>
    <![endif]-->
</head>

<body>



	<div class="navbar-wrapper">
		<div class="container">

			<div class="navbar">
				<div class="navbar-inner">
					<ul class="nav" style="margin-left:40px">
					<li><a href="index.html"><strong>菩纳首页</strong></a></li>
					<li class="divider-vertical"></li>
					<li><a href="about_poona.html"><strong>关于菩纳</strong></a></li>
					<li class="divider-vertical"></li>
					<li><a href="teachers.html"><strong>导师团队</strong></a></li>
					<li class="divider-vertical"></li>
					<li><a href="join.html"><strong>参与方式</strong></a></li>
					<li class="divider-vertical"></li>
					<li><a href="class_schedule.html"><strong>课程表</strong></a></li>
					<li class="divider-vertical"></li>
					<li><a href="graceful_poona.html"><strong>菩纳风采</strong></a></li>
					<li class="divider-vertical"></li>
					<li><a href="learn_in_india.html"><strong>印度留学</strong></a></li>
					<li class="divider-vertical"></li>
					<li><a href="poona_forum.html"><strong>瑜伽论坛</strong></a></li>
					<li class="divider-vertical"></li>
					<li><a href="contact_us.html"><strong>联系我们</strong></a></li>
				    </ul>
				</div>
			</div>
		</div>
	</div>

	<div id="myCarousel" class="carousel slide imgdiv">
		<div class="carousel-inner imgdiv">
			<div class="item imgdiv">
				<img src="${ctx}/img/guruji.jpg" alt="">
			</div>
			<div class="item imgdiv">
				<img src="${ctx}/img/studio.jpg" alt="">
			</div>
			<div class="item imgdiv">
				<img src="${ctx}/img/ganga.jpg" alt="">
			</div>
			<div class="item active imgdiv">
				<img src="${ctx}/img/welcome.jpg" alt="">
			</div>
			<div class="item imgdiv">
				<img src="${ctx}/img/teacher.jpg" alt="">
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
	</div>
	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script>
		!function($) {
			$(function() {
				$('#myCarousel').carousel()
			});
		}(window.jQuery);
	</script>
</body>
</html>