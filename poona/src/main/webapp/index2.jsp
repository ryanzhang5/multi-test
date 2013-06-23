<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ include file="/WEB-INF/js_css.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test1</title>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="span1"></div>
			<div class="span10  navbar">
				<div class="navbar-inner" style="background:url(img/top-bg.jpg) repeat; padding:0px 0px 0px 0px;"></div>
			</div>
		</div>
	</div>
	<div class="container">

		<!-- Docs nav
    ================================================== -->
		<div class="row">
			<div class="span1"></div>
			<div class="span10">

				<!-- Navbar
    ================================================== -->
				<!--  <div class="navbar">
              <div class="navbar-inner">
                <div class="container" style="width: auto;">
                  <ul class="nav"  role="navigation">
                    <li class="dropdown">
                      <a id="drop1" href="http://www.bootcss.com/javascript.html#" role="button" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="http://google.com/">Action</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="http://www.bootcss.com/javascript.html#anotherAction">Another action</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="http://www.bootcss.com/javascript.html#">Separated link</a></li>
                      </ul>
                    </li>
                    <li class="dropdown">
                      <a href="http://www.bootcss.com/javascript.html#" id="drop2" role="button" class="dropdown-toggle" data-toggle="dropdown">Dropdown 2<b class="caret"></b></a>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="http://www.bootcss.com/javascript.html#">Action</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="http://www.bootcss.com/javascript.html#">Separated link</a></li>
                      </ul>
                    </li>
                      <li>
                      <a href="">Dropdown 4</a>
                    </li>
                      <li>
                      <a href="">Dropdown 5</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>  -->
				<!-- /navbar-example -->


				<%@ include file="/WEB-INF/header.jsp"%>



				<!-- Carousel
          ================================================== -->
				<section id="carousel">
					<div class="bs-docs-example">
						<div id="myCarousel" class="carousel slide">
							<ol class="carousel-indicators">
								<li data-target="#myCarousel" data-slide-to="0" class=""></li>
								<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
								<li data-target="#myCarousel" data-slide-to="2" class=""></li>
							</ol>
							<div class="carousel-inner">
								<div class="item">
									<img src="img/bootstrap-mdo-sfmoma-01.jpg"
										style="width: 100%; height: 100%" alt="">
									<div class="carousel-caption">
										<h4>First Thumbnail label</h4>
										<p>Cras justo odio, dapibus ac facilisis in, egestas eget
											quam. Donec id elit non mi porta gravida at eget metus.
											Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
									</div>
								</div>
								<div class="item active">
									<img src="img/bootstrap-mdo-sfmoma-02.jpg"
										style="width: 100%; height: 100%" alt="">
									<div class="carousel-caption">
										<h4>Second Thumbnail label</h4>
										<p>Cras justo odio, dapibus ac facilisis in, egestas eget
											quam. Donec id elit non mi porta gravida at eget metus.
											Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
									</div>
								</div>
								<div class="item">
									<img src="img/bootstrap-mdo-sfmoma-03.jpg"
										style="width: 100%; height: 100%" alt="">
									<div class="carousel-caption">
										<h4>Third Thumbnail label</h4>
										<p>Cras justo odio, dapibus ac facilisis in, egestas eget
											quam. Donec id elit non mi porta gravida at eget metus.
											Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
									</div>
								</div>
							</div>
							<a class="left carousel-control" href="#myCarousel"
								data-slide="prev">‹</a> <a class="right carousel-control"
								href="#myCarousel" data-slide="next">›</a>
						</div>
					</div>

				</section>

				<!-- Carousel
          ================================================== -->
				<section id="carousel">
					<div class="bs-docs-example">
						<div id="myCarousel" class="carousel slide">
							<ol class="carousel-indicators">
								<li data-target="#myCarousel" data-slide-to="0" class=""></li>
								<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
								<li data-target="#myCarousel" data-slide-to="2" class=""></li>
							</ol>
							<div class="carousel-inner">
								<div class="item">
									<img src="img/bootstrap-mdo-sfmoma-01.jpg"
										style="width: 100%; height: 100%" alt="">
									<div class="carousel-caption">
										<h4>First Thumbnail label</h4>
										<p>Cras justo odio, dapibus ac facilisis in, egestas eget
											quam. Donec id elit non mi porta gravida at eget metus.
											Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
									</div>
								</div>
								<div class="item active">
									<img src="img/bootstrap-mdo-sfmoma-02.jpg"
										style="width: 100%; height: 100%" alt="">
									<div class="carousel-caption">
										<h4>Second Thumbnail label</h4>
										<p>Cras justo odio, dapibus ac facilisis in, egestas eget
											quam. Donec id elit non mi porta gravida at eget metus.
											Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
									</div>
								</div>
								<div class="item">
									<img src="img/bootstrap-mdo-sfmoma-03.jpg"
										style="width: 100%; height: 100%" alt="">
									<div class="carousel-caption">
										<h4>Third Thumbnail label</h4>
										<p>Cras justo odio, dapibus ac facilisis in, egestas eget
											quam. Donec id elit non mi porta gravida at eget metus.
											Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
									</div>
								</div>
							</div>
							<a class="left carousel-control" href="#myCarousel"
								data-slide="prev">‹</a> <a class="right carousel-control"
								href="#myCarousel" data-slide="next">›</a>
						</div>
					</div>

				</section>



			</div>
		</div>

	</div>



	<!-- Footer
    ================================================== -->
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















</body>
</html>

