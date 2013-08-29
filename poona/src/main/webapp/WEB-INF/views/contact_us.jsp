<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<%@ include file="/WEB-INF/common.jsp"%>
<link rel="shortcut icon" href="${ctx}/img/ico.ico" type="image/x-icon">
<link href="${ctx}/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/css/poona.css" rel="stylesheet">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=64955445ad88df4b41fede786b96b22e"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>联系我们</title>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="${ctx}/js/html5shiv.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container top-container">
		<%@ include file="/WEB-INF/header.jsp"%>
		<div style="margin-top: 70px">
			<div class="row span12" style="text-align:center">
			  <h3>联系我们</h3>
			</div>
			<div class="row span12">
			 <ul>
			 	<li>地址：上海市浦东新区丁香路1089弄(联洋花园)18号302(靠近大拇指广场)</li>
			 	<li>乘车路线
			 		<ul>
			 			<li>地铁9号线杨高中路站3号品出，5-10分钟即到</li>
			 			<li>乘815路,987路到丁香路金松路站步行3分钟</li>
			 		</ul>
			 	</li>
			 	<li>预约电话：021-50673981</li>
			 </ul>
			</div>
		</div>
		<%-- <div class="row span12" style="height:600px" 	style="background-image: url('${ctx}/img/bg2.jpg');height:600px">	</div> --%>
		<div class="row span12" style="height:500px" 	id="allMap">	</div>
	</div>
	<script type="text/javascript">

// 百度地图API功能
var map = new BMap.Map("allMap");            // 创建Map实例
var point = new BMap.Point(121.563046, 31.234676);    // 创建点坐标
map.centerAndZoom(point,18);                     // 初始化地图,设置中心点坐标和地图级别。
map.enableScrollWheelZoom();                            //启用滚轮放大缩小

var marker1 = new BMap.Marker(new BMap.Point(121.563046, 31.234676));  // 创建标注
map.addOverlay(marker1);              // 将标注添加到地图中
map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
//创建信息窗口
var infoWindow1 = new BMap.InfoWindow("欢迎来到菩纳瑜伽");
marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});

</script>
</body>
</html>

