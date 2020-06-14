	
	var time_now_server,time_now_client,time_end,time_server_client,start_time,time_distance_start;;
	
	var shareUserId;

	$(function() {
		
		//alert去掉title域名
		(function(){
			window.alert = function(name){
			var iframe = document.createElement("IFRAME");
			iframe.style.display="none";
			iframe.setAttribute("src", 'data:text/plain');
			document.documentElement.appendChild(iframe);
			window.frames[0].window.alert(name);
			iframe.parentNode.removeChild(iframe);
			}
		})(); 

	

		time_end=new Date(endTime).getTime();
		
		time_now_server=new Date(serverTime).getTime();
		
		time_now_client=new Date().getTime();

		time_server_client=time_now_server-time_now_client;
		
		show_time();
		document.getElementById("start").innerHTML = FormatDate(startT); 
		document.getElementById("end").innerHTML = FormatDate(endT); 
		
		$.ajax({
			url:ctx + "/manage/web/queryShengYu",
			data:{"welfareId":welfareId},
			async: false,
			success:function(data){
				$("#shengYu").text(data.num+"份")
				welfareStatus = data.welfareStatus;
			}
		})
		
		
		var shareLink = ctx + "/manage/web/reciblerUrl?redUrl=" + detailUrl;
		initShare(shareTitle, ctx + shareImg, shareLink +"&shareUserId="+getUser().id, shareDes,"1",welfareId);
		
	});


function tofuliDetail(){
	if(welfareStatus == "1"){
		window.location.href=detailUrl;
	}else if(welfareStatus =="3"){
		alert("活动已结束。")
	}else{
		alert("活动已下架。")
	}
}
	
/**显示分享样式 弹框*/
function selectStyle() {
	if(welfareStatus == "1"){
		$("#selectStyle").show();
	}else if(welfareStatus =="3"){
		alert("活动已结束。")
	}else{
		alert("活动已下架")
	}
}
/**隐藏分享样式 弹框*/
function closeSelectStyle() {
	$("#selectStyle").hide();
}

/**显示链接分享弹框*/
function ShowLinkStyleShare(){
	$("#selectStyle").hide();
	$("#nfl_xiangqing").show();
}
/**关闭链接分享弹框*/
function closeLinkStyleShare(){
	$("#nfl_xiangqing").hide();
}
/** * 显示卡片分享样式 */
function showCarStyleShare(){
	var userId= getUser().id;
	window.location.href= ctx + "/manage/shareRecords/selectShareCard?userId="+userId+"&welfareName="+welfareName+"&welfareId="+welfareId+"&shareTitle="+shareTitle;
}
/**手机适配*/
function scalePageB() {
	var a, b, c = 1, clientWidth = document.documentElement.clientWidth, d = document.body.scrollWidth, e = document.body.scrollHeight;
	if (d / e >= 320 / 486 ? (c = e / 486, a = (d / c - 320) / 2) : (c = d / 320, b = (e / c - 486) / 2)) {
		var f = 320 / d, g = 486 / e, h = Math.max(f, g);
		h = h > 1 ? h : 160 * h, h = parseInt(h), 
		$("#viewport2").attr( "content", "width=320, target-densitydpi=" + h + ", user-scalable=no");
	}
}


function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}
function show_time() {
	var days = document.getElementById("days");
	var hours = document.getElementById("hours");
	var minutes = document.getElementById("minutes");
	var seconds = document.getElementById("seconds");
	var time_now, time_distance, str_time;
	var int_day, int_hour, int_minute, int_second;
	var time_now = new Date();
	time_now = time_now.getTime() + time_server_client;
	time_distance = time_end - time_now;
	 
	start_time = new Date(startT).getTime();
	
	time_distance_start = start_time - time_now; 
	
 	if(time_distance_start > 0){
		$(".flq_time2").empty();
		$(".flq_time2").append("<span id=''>活动未开始</span>");
	 } else

if (time_distance > 0 && welfareStatus != "3") {
		int_day = Math.floor(time_distance / 86400000)
		time_distance -= int_day * 86400000;
		int_hour = Math.floor(time_distance / 3600000)
		time_distance -= int_hour * 3600000;
		int_minute = Math.floor(time_distance / 60000)
		time_distance -= int_minute * 60000;
		int_second = Math.floor(time_distance / 1000)
		if (int_hour < 10)
			int_hour = "0" + int_hour;
		if (int_minute < 10)
			int_minute = "0" + int_minute;
		if (int_second < 10)
			int_second = "0" + int_second;
		days.innerHTML = int_day;
		hours.innerHTML = int_hour;
		minutes.innerHTML = int_minute;
		seconds.innerHTML = int_second;
		setTimeout("show_time()", 1000);
	}else if(welfareStatus == "3"){
		$(".flq_time2").empty();
		$(".flq_time2").append("<span id=''>活动已结束</span>");
	}else{
		$(".flq_time2").empty();
		$(".flq_time2").append("<span id=''>活动已结束</span>");
	}
}
