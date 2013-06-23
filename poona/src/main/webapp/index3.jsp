<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ include file="/WEB-INF/js_css3.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>test1</title>
</head>

<body>
	<div class="container">
	<div class="row-fluid">
			<div class="span12  navbar">
				<div class="navbar-inner" style="background:url(img/head.jpg) repeat;height:130px;"></div>
			</div>
		</div>
	<div class="row-fluid">
			<div class="span12">
				<%@ include file="/WEB-INF/header.jsp"%>
		    </div>
	</div>
	<div class="row-fluid">
			<div class="span12">
						<div id="myCarousel" class="carousel slide" style="height: 450px">
							<ol class="carousel-indicators">
								<li data-target="#myCarousel" data-slide-to="0" class=""></li>
								<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
								<li data-target="#myCarousel" data-slide-to="2" class=""></li>
							</ol>
							<div class="carousel-inner">
								<div class="item">
									<img src="img/01.jpg"
										style="width: 100%; height: 450px" alt="">
								</div>
								<div class="item active">
									<img src="img/02.jpg"
										style="width: 100%; height: 450px" alt="">
								</div>
								<div class="item">
									<img src="img/03.jpg"
										style="width: 100%; height: 450px" alt="">
								</div>
							</div>
							<a class="left carousel-control" href="#myCarousel"
								data-slide="prev">‹</a> <a class="right carousel-control"
								href="#myCarousel" data-slide="next">›</a>
						</div>
          	</div>
          </div>				
		<div class="row-fluid">
		<div class="span3" style="background-color: #d4d4d4; height: 150px">
		<h5 class="text-center">公告</h5>
			<div>
				<table class="table">
					<tr><td>公告1</td></tr>
					<tr><td>公告2</td></tr>
					<tr><td>公告3</td></tr>
				</table>
			</div>
			</div>
			<div class="span6" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span3" style="background-color: #d4d4d4; height: 150px"></div>
		</div>
		<section>
		<div class="row-fluid">
			<div class="span2" style="background-color: red; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
		</div>
		</section>
		<section>
		<div class="row-fluid">
			<div class="span2" style="background-color: red; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
			<div class="span2" style="background-color: #d4d4d4; height: 150px"></div>
		</div>
		</section>
		<div class="row-fluid">
		<div class="span12" >
		<footer class="footer">
		<div class="container">
			<p>
				Designed and built with all the love in the world by <a
					href="http://twitter.com/mdo" target="_blank">@mdo</a> and <a
					href="http://twitter.com/fat" target="_blank">@fat</a>.
			</p>
			<p>
				Code licensed under <a
					href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache
					License v2.0</a>, documentation under <a
					href="http://creativecommons.org/licenses/by/3.0/" target="_blank">CC
					BY 3.0</a>.
			</p>
			<p>
				<a href="http://glyphicons.com/" target="_blank">Glyphicons Free</a>
				licensed under <a href="http://creativecommons.org/licenses/by/3.0/"
					target="_blank">CC BY 3.0</a>.
			</p>
			<ul class="footer-links">
				<li><a href="http://www.bootcss.com/about.html" target="_blank">关于</a></li>
				<li class="muted">·</li>
				<li><a
					href="https://github.com/twitter/bootstrap/issues?state=open"
					target="_blank">问题反馈</a></li>
				<li class="muted">·</li>
				<li><a
					href="https://github.com/twitter/bootstrap/blob/master/CHANGELOG.md">版本变更记录</a></li>
				<li><a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备11008151号</a></li>
			</ul>
		</div>
	</footer>
		</div>
		</div>
	</div>
</body>
</html>

