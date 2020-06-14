<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery-1.8.3.js"></script>
<script language="javascript" src="http://webapi.amap.com/maps?v=1.3&key=3139558dd343298b82544ea9fddb0978"></script>
</head>
<body onLoad="mapInit()">
  	<div>
		<table width="600px">
			<tr align="center">
				<td>
				企业地址：&nbsp;&nbsp;
				  <input id="address" type="text" onkeydown="down(event);" >&nbsp;&nbsp;&nbsp;&nbsp;
		            <input class="button_green" type="button" value="查询" onclick="geocoder('');">
		            <input type="button" class="button_green" value="保存" onclick="save();">
		            <br/>
	        	</td>
	        </tr>
		</table>
	</div>
	<div id="map_canvas" style="width:680px;height:430px;"></div>
</body>
<script language="javascript">
var mapObj;
var result;
var marker = [];
var windowsArr = [];
function mapInit () {
	var address = "<%=request.getAttribute("address")%>";
	geocoder(address);
};
var MGeocoder;
var address;
var resultStr;
function geocoder(address) {  //地理编码返回结果展示
	mapObj = new AMap.Map(document.getElementById("map_canvas"));    //默认定位：初始化加载地图时，center及level属性缺省，地图默认显示用户所在城市范围
			if(address != ""){
				//首次打开地图查询
				document.getElementById("address").value=address;
			}else{
				//查询按钮或回车键查询
				address = document.getElementById("address").value;
			}
			
			if(address=="" || address==null || address==undefined){
				address = "北京";
				document.getElementById("address").value = "北京" ;
			}
    	mapObj.plugin(["AMap.Geocoder"], function() {     //加载地理编码插件
        MGeocoder = new AMap.Geocoder();
        //返回地理编码结果
        AMap.event.addListener(MGeocoder, "complete", geocoder_CallBack);  
        AMap.event.addListener(MGeocoder, "error", show_Error);        
        MGeocoder.getLocation(address);  //地理编码
    });
} 

function show_Error(data){
	alert(data.info);
}

//地理编码返回结果展示  
function geocoder_CallBack(data){ 
    //地理编码结果数组
    var geocode = new Array();
    geocode = data.geocodes; 
    resultStr = geocode[0].location.getLat() +","+geocode[0].location.getLng()+";"+geocode[0].formattedAddress;
    addmarker(0, geocode[0]);
    mapObj.setFitView();  
} 
function addmarker(i, d) {
    var lngX = d.location.getLng();
    var latY = d.location.getLat();
    var markerOption = {
        map:mapObj,                
        icon:"http://webapi.amap.com/images/"+(i+1)+".png", 
        position:new AMap.LngLat(lngX, latY),
        draggable:false
    };           
    var mar = new AMap.Marker(markerOption); 
    marker.push(new AMap.LngLat(lngX, latY));
 
    var infoWindow = new AMap.InfoWindow({ 
        content:d.formattedAddress,
        autoMove:true,
        size:new AMap.Size(150,0), 
        offset:{x:0,y:-30}
    }); 
    windowsArr.push(infoWindow); 
    var aa = function(e){infoWindow.open(mapObj,mar.getPosition());}; 
    AMap.event.addListener(mar,"click",aa); 
}


function save(){
	if(resultStr!='' && resultStr !='null' && resultStr !=undefined){
		var arr1 = resultStr.split(";");
		var arr = arr1[0].split(",");
		var url = "http://restapi.amap.com/v3/staticmap?scale=1&location="+arr[1]+","+arr[0]+"&zoom=14&size=<%=request.getParameter("size")%>&markers=mid,,A:"+arr[1]+","+arr[0]+"&key=caaa086bdf5666322fba3baf5a6a2c03";
		var json = {
				"address":arr1[1],
				"longitude":arr[1],
				"latitude":arr[0],
				"img":url
		};
		parent.mapBack(json);
		$('.wBox_close', parent.document).click();
	}else {
        alert("没有找到具体位置，请重新输入地址");
    }
}


</script>
</html>