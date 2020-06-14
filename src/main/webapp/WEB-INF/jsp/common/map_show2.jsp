<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"	src="<%=request.getContextPath() %>/js/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=LEhGKDNBygX6Uw0g0TcxBaZuihImvcZc"></script>
</head>
<body>
	<div>
		<table width="600px">
			<tr align="center">
				<td>
				         企业地址：&nbsp;&nbsp; 
				         <input id="address" value="${address}" type="text" >&nbsp;&nbsp;&nbsp;&nbsp; 
				         <input	class="button_green" type="button" value="查询"	onclick="searchByStationName();"> 
				         <input type="button" class="button_green" value="保存" onclick="save();"><br/>
				</td>
			</tr>
		</table>
	</div>
	<div id="map_canvas" style="width: 680px; height: 430px;"></div>

	<script type="text/javascript">
	    var longitude = null;
	    var latitude = null;
		var map = new BMap.Map("map_canvas");
	    map.centerAndZoom("北京", 12);
	    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

	    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	    map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
		
	  //单击获取点击的经纬度
		map.addEventListener("click",function(e){
			longitude = e.point.lng;
			latitude =  e.point.lat;
			map.clearOverlays();//清空原来的标注
		    var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));  // 创建标注，为要查询的地方对应的经纬度
		    map.addOverlay(marker);
		    // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			
		});
		
		var localSearch = new BMap.LocalSearch(map);
		localSearch.enableAutoViewport(); //允许自动调节窗体大小
		
		function searchByStationName() {
			map.clearOverlays();//清空原来的标注
			var keyword = document.getElementById("address").value;
			localSearch.setSearchCompleteCallback(function (searchResult) {
			    var poi = searchResult.getPoi(0);
			    if(null==poi){
			    	longitude = null;
			 	    latitude = null;
			    	alert("没有找到具体位置，请在地图上选择");
			    	return;
			    }
				longitude = poi.point.lng;
				latitude =  poi.point.lat;
			    map.centerAndZoom(poi.point, 13);
			    var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
			    map.addOverlay(marker);
			    // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			});
			localSearch.search(keyword);
		} 
	
		$(function(){
			searchByStationName();
		});
	
		function save(){
			if(longitude == null||latitude == null){
		    	alert("没有找到具体位置，请在地图上选择");
		    	return;
			}
			var url = "http://api.map.baidu.com/staticimage/v2?ak=LEhGKDNBygX6Uw0g0TcxBaZuihImvcZc&markers="+longitude+","+latitude+"&zoom=15&width=${width}&height=${height}";
			var json = {
					"address":$("#address").val(),
					"longitude":longitude,
					"latitude":latitude,
					"img":url
			};
			parent.mapBack(json);
			$('.wBox_close', parent.document).click();
		}
	</script>


</body>
</html>