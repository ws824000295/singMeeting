

$(document).ready(function() {
	//切换 web_menu.jsp导航的样式
	$(".footer").children().children("div").click(function(){
		var curl = $(this).attr("curl");
		if(model == 0){
			$(".footer").children().children("div").removeClass("sed");
			$(this).addClass("sed");
		}else{
			$(".footer").children().children("div").removeClass("newsed");
			$(this).addClass("newsed");
		}
		
		if(curl.indexOf("toIndex")>=0){
			var thisShareUserId = sessionStorage.getItem("thisShareUserId");
			if(thisShareUserId != "" && thisShareUserId!= null){
				curl =curl +"?thisShareUserId="+thisShareUserId+"&adminId="+getUser().adminId+"&userId="+getUser().id;
				window.location.href=curl;
			}else{
				window.location.href=curl+"?adminId="+getUser().adminId+"&userId="+getUser().id;
			}
		}else{
			window.location.href=curl+"?adminId="+getUser().adminId+"&userId="+getUser().id;
		}
	});
	
})
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//切换 web_menu.jsp导航的选中样式
function addClass(index){
	if(model == 0){
		$(".footer").children().children("div").removeClass("sed");
		$(".footer").children().eq(index).children("div").addClass("sed");
	}else{
		$(".footer").children().children("div").removeClass("newsed");
		$(".footer").children().eq(index).children("div").addClass("newsed");
	}
}
