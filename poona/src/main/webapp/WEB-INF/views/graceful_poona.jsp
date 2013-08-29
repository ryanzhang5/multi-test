<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<%@ include file="/WEB-INF/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${ctx}/img/ico.ico" type="image/x-icon">
<link href="${ctx}/css/docs.css" rel="stylesheet">
<link href="${ctx}/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/css/bootstrap-responsive.css" rel="stylesheet">
<link href="${ctx}/css/poona.css" rel="stylesheet">
<link href="${ctx}/js/holder.js" rel="stylesheet">
<link href="${ctx}/magnific-popup/magnific-popup.css" rel="stylesheet">
<script src="${ctx}/js/jquery-1.9.1.js"></script>
<script src="${ctx}/magnific-popup/jquery.magnific-popup.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<title>菩纳风采</title>
<script type="text/javascript">
$(document).ready(function() {
	$('.zoom-gallery').magnificPopup({
		delegate: 'a',
		type: 'image',
		closeOnContentClick: false,
		closeBtnInside: false,
		mainClass: 'mfp-with-zoom mfp-img-mobile',
		image: {
			verticalFit: true,
			titleSrc: function(item) {
				return item.el.attr('title') + ' &middot; <a class="image-source-link" href="'+item.el.attr('data-source')+'" target="_blank">image source</a>';
			}
		},
		gallery: {
			enabled: true
		},
		zoom: {
			enabled: true,
			duration: 300, // don't foget to change the duration also in CSS
			opener: function(element) {
				return element.find('img');
			}
		}
		
	});
});
</script>
<style type="text/css">
.image-source-link {
	color: #98C3D1;
}

.mfp-with-zoom .mfp-container,
.mfp-with-zoom.mfp-bg {
	opacity: 0;
	-webkit-backface-visibility: hidden;
	/* ideally, transition speed should match zoom duration */
	-webkit-transition: all 0.3s ease-out; 
	-moz-transition: all 0.3s ease-out; 
	-o-transition: all 0.3s ease-out; 
	transition: all 0.3s ease-out;
}

.mfp-with-zoom.mfp-ready .mfp-container {
		opacity: 1;
}
.mfp-with-zoom.mfp-ready.mfp-bg {
		opacity: 0.8;
}

.mfp-with-zoom.mfp-removing .mfp-container, 
.mfp-with-zoom.mfp-removing.mfp-bg {
	opacity: 0;
}
</style>
</head>

<body>
	<div class="container top-container">
		<%@ include file="/WEB-INF/header.jsp"%>
		<div style="margin-top: 80px">
			<div class="zoom-gallery">
			
	<div style="float:left;width:300px; height: 200px;margin-left:20px;margin-top:20px; border: 5px solid #FAA523">
	<a href="${ctx}/img/graceful_poona/IMG_5532_big.jpg" data-source="www.poonayoga.com" title="点击查看大图">
		<img src="${ctx}/img/graceful_poona/IMG_5532_small.jpg" >
	</a>
	</div>
	<div style="float:left;width:300px; height: 200px;margin-left:20px;margin-top:20px; border: 5px solid #FAA523">
	<a href="${ctx}/img/graceful_poona/DSC_0086_big.jpg" data-source="www.poonayoga.com" title="点击查看大图">
		<img src="${ctx}/img/graceful_poona/DSC_0086_small.jpg" >
	</a>
	</div>
	<div style="float:left;width:300px; height: 200px;margin-left:20px;margin-top:20px; border: 5px solid #FAA523">
	<a href="${ctx}/img/graceful_poona/IMG_5800_big.jpg" data-source="www.poonayoga.com" title="点击查看大图">
		<img src="${ctx}/img/graceful_poona/IMG_5800_small.jpg" >
	</a>
	</div>
	

	<div style="float:left;width:300px; height: 200px;margin-left:20px;margin-top:20px; border: 5px solid #FAA523">
	<a href="${ctx}/img/graceful_poona/IMG_5532_big.jpg" data-source="www.poonayoga.com" title="点击查看大图">
		<img src="${ctx}/img/graceful_poona/IMG_5532_small.jpg" >
	</a>
	</div>
	<div style="float:left;width:300px; height: 200px;margin-left:20px;margin-top:20px; border: 5px solid #FAA523">
	<a href="${ctx}/img/graceful_poona/DSC_0086_big.jpg" data-source="www.poonayoga.com" title="点击查看大图">
		<img src="${ctx}/img/graceful_poona/DSC_0086_small.jpg" >
	</a>
	</div>
	<div style="float:left;width:300px; height: 200px;margin-left:20px;margin-top:20px; border: 5px solid #FAA523">
	<a href="${ctx}/img/graceful_poona/IMG_5800_big.jpg" data-source="www.poonayoga.com" title="点击查看大图">
		<img src="${ctx}/img/graceful_poona/IMG_5800_small.jpg" >
	</a>
	</div>
	
</div>
		</div>
	</div>
</body>
</html>

