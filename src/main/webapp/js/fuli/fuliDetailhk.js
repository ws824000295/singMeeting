var shareUserId, shareLink, time_now,time_distance_start, _FULItimer,  useAdminId;
		
	$(function() {
		sessionStorage.setItem("loginuser",JSON.stringify(user));
			
 		if(byShare != "false"){		//公众平台分享进来
	 		shareUserId = byShare;
	 	}else{								//平台点进来
	 		shareUserId = user.id;
	 	}
	 	
 		useAdminId = user.adminId;

 		shareLink =   manageServerName+"/view/welfare/byShareIn?wid="+ welfareId +"&shareUserId=" +user.id+ "&adminId=" +user.adminId ;
 		if(user.adminId==31 || sourceType == "hk"){
 			var sharelogo =  shareImg;
 		}else{
 			var sharelogo =  shareCtx + shareImg;
 		}
		initShare(shareTitle, sharelogo, shareLink, shareDes ,"1",welfareId,null);
		 
		var reg = new RegExp(",","g");//g,表示全部替换。
		start_time = start_time.replace(reg,"");
		time_end   = time_end.replace(reg,"");
		time_now = new Date().getTime();
		time_distance = time_end - time_now;
		time_distance_start = start_time - time_now;
		
		//福利上方图片位置倒计时
	 	countDownTitle();
		
		//加载福利图片	 	
	 	loadBanner();
			 
		addPointsRecord();// 访问人数纪录**/

	 	addPageViews();
	 
		scalePage2(); //**适配手机*/    
		
		if(user.userType == "2"){ //金牌福星
			$("#putongUser").hide();
		}else{
			$("#putongUser").show();
		}
	
	//点击福利进行奖币操作
	addConisByPhp();
	 
	//获得流水记录
	getShareUser();
	
	howFuBiMustGive();
	
	 //去掉alert头的域名
	var wAlert = window.alert;  
	window.alert = function (message) {  
    try {  
        var iframe = document.createElement("IFRAME");  
	    iframe.style.display = "none";  
	    iframe.setAttribute("src", 'data:text/plain,');  
        document.documentElement.appendChild(iframe);  
        var alertFrame = window.frames[0];  
        var iwindow = alertFrame.window;  
        if (iwindow == undefined) {  
            iwindow = alertFrame.contentWindow;  
        }  
        iwindow.alert(message);  
        iframe.parentNode.removeChild(iframe);  
    }catch (exc) {  
        return wAlert(message);  
    }  
  }
	
})


 
//特殊处理里福利企业首页跳转到自己的首页
function toshouye2(id){
	window.location.href=ctx+"/manage/web/tocompanyInfo?id="+id+"&adminId="+user.adminId;
}

//跳转我的订单页面
function tomyOrder(){
	window.location.href=ctx +"/manage/center/toOrderList?adminId="+user.adminId+"&userId="+user.id;
}
 
function getShareUser(){
	$.ajax({
		url:ctx + "/manage/coinsRecord/queryShareDetailsByWelfareId",
		data: {"welfareId":welfareId},
	 	async: false,
		success:function(data){ 		
			$(".newsUl").empty();
			for(var i= 0 ; i<data.length; i++){
				var html = "";
				var coinsNum=data[i].coinsNum;
				coinsNumber=coinsNum*2;
				var isDivision=data[i].sendType;
				if(i==0){
					if(isDivision=="0"){
						html +=  '<li class="on newsLi">';
						html +=	'<div style="width:16px;float:left;margin-right:3px";><img src="'+data[i].icon+'" class="focus-avatar" /></div>';
						html +=  '<p>'+data[i].nickName+'*获得'+coinsNum+'福钻'+'</p>';
						html +='</li>';	
					}else{
						html +=  '<li class="on newsLi">';
						html +=	'<div style="width:16px;float:left;margin-right:3px";><img src="'+data[i].icon+'" class="focus-avatar" /></div>';
						html +=  '<p>'+data[i].nickName+'*与'+'</p>';
						html +=	'<div style="width:16px;float:left;margin-right:3px";><img src="'+data[i].clickIcon+'" class="focus-avatar" /></div>';
						html +=  '<p>'+data[i].gname+'*共分'+coinsNumber+'福钻'+'</p>';
						html +='</li>';	
					}
				}else{
					if(isDivision=="0"){
						html +=  '<li class="newsLi">';
						html +=	'<div style="width:16px;float:left;margin-right:3px";><img src="'+data[i].icon+'" class="focus-avatar" /></div>';
						html +=  '<p>'+data[i].nickName+'*获得'+coinsNum+'福钻'+'</p>';
						html +='</li>';		
					} else {
						html +=  '<li class="newsLi">';
						html +=	'<div style="width:16px;float:left;margin-right:3px";><img src="'+data[i].icon+'" class="focus-avatar" /></div>';
						html +=  '<p>'+data[i].nickName+'*与'+'</p>';
						html +=	'<div style="width:16px;float:left;margin-right:3px";><img src="'+data[i].clickIcon+'" class="focus-avatar" /></div>';
						html +=  '<p>'+data[i].gname+'*共分'+coinsNumber+'福钻'+'</p>';
						html +='</li>';		
					}
				}
				$(".newsUl").append(html);
			}
		}	
	})	
	//新闻透明轮播
	var curIndex = 0;
	var autoChange = setInterval(function(){
	    if(curIndex < $(".newsLi").length-1){ 
	      curIndex ++; 
	    }else{ 
	      curIndex = 0;
	    }
	    //调用变换处理函数
	   $(".newsUl").find(".newsLi").removeClass("on").hide().delay(8000).eq(curIndex).fadeIn();
	},5000);
 }
 				
 
//邀请您与您一起关注点击弹处框
function showWxQrcode(){
	$.ajax({
		url:ctx + "/manage/welfareIndex/showWxQrcode",
		data: {"adminId":user.adminId},
	 	async: false,
		success:function(data){
			 if(data.result){
				 var background = ctx + "/images/view/QR_bg.png";
				 $(".qunfulli").css("background","url("+background+") no-repeat")
				 $(".qunfulli").css("background-size","100%")
				 $(".code_qrcode").attr("src",ctx + data.code);
				 $(".codeText").text(data.customerName);
				 $("#concern_us_index").show();
			 }
		}
	})
}

//商家详情
function toCompanyDetail(adminid,userid){ 
	//window.location.href = ctx + "/view/home/homeCompanyHk?adminId="+adminid+"&userId="+userid+"&fromHK=HK"; 
	window.location.href=ctx+"/view/main/mainEasy?adminId="+user.adminId+"&type=0&fromHK=HK";
}

//点击客服微信咨询弹出框
function getWelfareWeiChat(){
	$.ajax({
		url:ctx + "/manage/shareRecords/getAminUserWxKf",
		data: {"adminId":user.adminId},
	 	async: false,
		success:function(data){
			$("#weixin_boxImg").attr("src",ctx + data.customerQrcode)		
			$('.alert_appendwx').show();
		}
	})
}
function addConisByPhp(){
	if(time_distance < 0  ){ return; }
	if(time_distance_start > 0){ return; } 
	$.ajax({
		url:ctx + "/manage/shareRecords/addConisByPhp",
		data: {"welfareId":welfareId, "clickUserId":user.id,"shareUserId":shareUserId,"adminId":welfareAdminId},
	 	async: false,
		success:function(data){
		}
	})
}
 
//查询福利状态
function queryWelfareStatus(){
	$.ajax({
		url:ctx + "/manage/shareRecords/queryWelfareStatus",
		data: {"welfareId":welfareId,"welfareAdminId":welfareAdminId,"clickAdminId":user.adminId},
	 	async: false,
		success:function(data){
			welfareStatus = data.welfareStatus;
		}
	})
}
  
   
/**
 * 去我的卡券
 */
function toEcardDetatil(){
	$("#get_alert_mask").hide();
	window.location.href= ctx+"/external/ecard/ecardList?userId="+user.id;
}

/*** 报名按钮 */
function singUp(){
	if(limitRob == 0 && time_distance_start > 0){
		alert("活动未开始!")
		return;
	}
	if( welfareStatus =="0"){
		alert("活动已下架!")
		return;
	}
	if((limitRob == 0 && time_distance < 0) || welfareStatus =="3"){
		alert("活动已结束!")
		return;
	}
	if(provName!="不限制" ){
		if((province.indexOf(provName) < 0)){
			alert("您不在福利区域范围内!")
			return;
		}
	}
	var cardUrl= $("#card_url").attr("src");
	window.location.href = ctx + "/manage/formRecord/singUp?welfareId="+welfareId+"&shareUserId="+shareUserId+"&welfareName="+welfareName+"&cardUrl="+cardUrl+"&city="+city+"&userId="+user.id;
}
	
/**
 * 关闭 支付成功后的弹框
 */
function closePayLayer(){
	$("#pay_layer").hide();
	sessionStorage.removeItem("orderNumber");
}

/**
 * 吸底 拨打商家电话
 */
function welfare_phone(companyTel){
	if(companyTel != ""){
		$("#aEvent").attr("href","tel:"+companyTel);
		$("#aEvent")[0].click();
	}
}
 	
/*** 增加一条积分流水表( 浏览) 积分 只要浏览就加积分,不区分福利类型   +  访问记录 */
function addPointsRecord(){
    $.ajax({
        type: 'get',
        data:{"mode":"1","welfareId":welfareId,"clickUserId":user.id,"shareUserId":shareUserId,"city":"","from":""},
        url: dataUrl, 
        dataType: "jsonp"
  });
}


/*** 增加访问量 */
function addPageViews(){  
	$.ajax({
		url:ctx+"/manage/shareRecords/addPageViews",
		data:{"welfareId":welfareId,"shareUserId":shareUserId,"clickUserId":user.id,"adminId":user.adminId},
		async: false
	});
}
	
/**显示分享样式 弹框*/
function selectStyle() {
	$("#selectStyle").show();
}
/**隐藏分享样式 弹框*/
function closeSelectStyle() {
	$("#selectStyle").hide();
}

/**显示链接分享弹框*/
function ShowLinkStyleShare(){
	$("#selectStyle").hide();
	if(sessionStorage.getItem("xcxUserId") != null && sessionStorage.getItem("xcxUserId") !="") {
		$(".nfl_xiangqing .fl_xiangqingpic").css("background","url("+ctx+"/images/fuli/fl_xiangqing_xcx.png) no-repeat center");
		$(".nfl_xiangqing .fl_xiangqingpic").css("background-size","100% 100%");
	} 
	$(".nfl_xiangqing").show();
	
}
/**关闭链接分享弹框*/
function closeLinkStyleShare(){
	$(".nfl_xiangqing").hide();
}

/** * 显示卡片分享样式 */
function showCarStyleShare(){
	var userId= user.id;
	window.location.href= ctx + "/manage/shareRecords/selectShareCard?userId="+userId+"&welfareName="+welfareName+"&welfareId="+welfareId+"&shareTitle="+shareTitle+"&adminId="+user.adminId;
}

/**
 * 去首页
 */
function toIndex(){
	var url = ctx + "/view/main/main?adminId="+user.adminId+"&type=0";
	window.location.href = url;
}

/**手机适配*/
function scalePage2() {
	var a, b, c = 1, clientWidth = document.documentElement.clientWidth, d = document.body.scrollWidth, e = document.body.scrollHeight;
	if (d / e >= 320 / 486 ? (c = e / 486, a = (d / c - 320) / 2) : (c = d / 320, b = (e / c - 486) / 2)) {
		var f = 320 / d, g = 486 / e, h = Math.max(f, g);
		h = h > 1 ? h : 160 * h, h = parseInt(h), 
		$("#viewport").attr( "content", "width=320, target-densitydpi=" + h + ", user-scalable=no");
	}
}

function queryIsPay(){
	var end  ;
	$.ajax({
		url:ctx + "/manage/payment/queryIsPay",
		data: {"welfareId":welfareId,"userId":user.id},
	 	async: false,
		success:function(data){
			if(data.res == "true"){
				end = true;// 买过
			}else{
				end = false;
			}
		 }
	})
	return end;
}

//查询是否卖完
function queryNumber(){
	var result  ;
	$.ajax({
		url:ctx + "/manage/shareRecords/queryWelfareNumber",
		data: {"welfareId":welfareId},
	 	async: false,
		success:function(data){
			if(data.res == "true"){
				result = true;//卖完
				$("#buttonName").html("已售罄");
			}else{
				result = false;
			}
		}
	})
	return result;
}
 
//凑单样式
 function showCouDan(){
 	$(".gather-alert").show();
 }
 //凑单样式  
function closeCouDan(){ 
	$(".gather-alert").hide(); 
	$(".nfl_xiangqing").show();
}

//福利上方图片位置倒计时
function countDownTitle(){
	if(limitRob == 1){
		return;
	}
	var reg = new RegExp(",","g");//g,表示全部替换。
	start_time = start_time.replace(reg,"");
	time_end   =time_end.replace(reg,"");
	time_now = new Date().getTime();
	if(time_now<Number(start_time)){
	 $('.time-text').html("");
	 $('#countDowe').html("活动未开始");
	 $('#countDowe').css("text-align",'center');
	 return;
	}
	if(time_now>time_end){
		$('.time-text').html("");
		 $('#countDowe').html("活动已结束");
		 $('#countDowe').css("text-align",'center');
		 return;
	}
	 if(start_time<time_now<time_end){ 
		if(time_end<new Date().getTime())return;//设置的时间小于现在时间退出
		_ordertimer = setInterval(function(){leftTimer(time_end)}, 1000);
	} 
	 
}
function leftTimer(enddate){ 
	 var leftTime = Number(enddate) - new Date().getTime(); //计算剩余的毫秒数
	  var days = parseInt(leftTime / 1000 / 60 / 60 / 24, 10); //计算剩余的天数
	  var hours = parseInt(leftTime / 1000 / 60 / 60 % 24, 10); //计算剩余的小时
	  var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟
	  var seconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数
	  days = checkTime(days);
	  hours = checkTime(hours);
	  minutes = checkTime(minutes);
	  seconds = checkTime(seconds);
	  if (days >= 0 || hours >= 0 || minutes >= 0 || seconds >= 0) {
		  $('#countDowe').html('');
 	   	  var html='';  
		  html+='<span class="time-item" id="day">'+ days+ '</span>';
	 	  html+='<i  style="color:#fe3657">天</i>'; 
		  html+='<span class="time-item" id="hourDown">'+hours+'</span>';
	 	  html+='<i  style="color:#fe3657">时</i>';
		  html+='<span class="time-item" id="minutesDown">'+minutes+'</span>';
	 	  html+='<i  style="color:#fe3657">分</i>';
		  html+='<span class="time-item" id="secondsDown">'+seconds+'</span>';
		  $('#countDowe').append(html);
	  }
	  if (days <= 0 && hours <= 0 && minutes <= 0 && seconds <= 0) {
	  	window.clearInterval(_ordertimer);
	  	_FULItimer = null;
	  }
} 
function checkTime(i) { //将0-9的数字前面加上0，例1变为01
	  if (i < 10) {
	 	 i = "0" + i;
	  }
  return i;
}

//钱包按钮
function toFuxingCenter(){
	var url = ctx + "/manage/center/tofuxingcenter?adminId="+user.adminId;
	window.location.href = url;
}

//成为金牌福星
function toGold(){
	var url = ctx + "/manage/shareRecords/toGold?userId="+user.id;
	window.location.href = url;
}
 


function toshouye(){
	var url = ctx + "/manage/welfareIndex/toIndex?adminId="+user.adminId;
	window.location.href = url;
}

//去商家详情
function tocompanyInfo(id){
	window.location.href=ctx+"/manage/web/tocompanyInfo?companyId="+id+"&adminId="+user.adminId;
}

//去我的订单
function toMyOrder(){
	window.location.href=ctx+"/view/main/mainEasy?adminId="+user.adminId+"&type=1&fromHK=HK";
}

function sendToFriend(){  
	if((limitRob == 0 &&  time_distance < 0 )   ){
		alert("活动已结束!");
		$("#buttonName").html("活动已结束"); 
		return;
	}
	if(limitRob == 0  && time_distance_start > 0){
		alert("活动未开始!");
		return;
	}
	if(welfareStatus =="0"){
		alert("活动已下架!");
		return;
	} 
	var res = queryNumber();//查询是否卖完
	if(res){
		alert("已售罄");
		return;
	}
	//window.location.href = ctx + "/manage/shareRecords/toBuyPresents?welfareName="+encodeURI(welfareName)+"&welfareId="+welfareId+"&shareUserId="+shareUserId+"&singleDay=0&singlePeople=0&yky=yky";
	sessionStorage.setItem("readUrl",window.location.href);
	window.location.href = ctx + "/manage/shareRecords/toBuyPresents?welfareName="+encodeURI(welfareName)+"&welfareId="+welfareId+"&shareUserId="+shareUserId+"&singleDay=0"+"&singlePeople=0";

}
  
//初始化购买类型的福利
function initByPayFuli(){ 
	if( (limitRob == 0 && time_distance < 0 ) ){
		alert("活动已结束!");
		$("#buttonName").html("活动已结束"); 
		return;
	}
	if(limitRob == 0 && time_distance_start > 0){
		alert("活动未开始!");
		return;
	}
	if(welfareStatus =="0"){
		alert("活动已下架!");
		return;
	} 
	var res = queryNumber();//查询是否卖完
	if(res){
		alert("已售罄");
		return;
	}else{ 
		/*var url = ctx + "/manage/shareRecords/buyPhp?welfareId="+welfareId+"&shareUserId="+shareUserId+"&byShare="+byShare;
		window.location.href = url;*/
		sessionStorage.setItem("readUrl",window.location.href);
		var url = ctx + "/manage/shareRecords/buyWelfare?welfareName="+encodeURI(welfareName)+"&welfareId="+welfareId+"&shareUserId="+shareUserId+"&singleDay=0"+"&singlePeople=0";
		window.location.href = url;
    }
}

 
//加载福利图片
 function loadBanner(){	  
	 var welfareImgList = JSON.parse(welfareImg);
	 var indexImg='';
	 var pageImg=0;
	 for(var k in welfareImgList){
		  if(k.indexOf("welfareImg_")>=0){
			  var img = welfareImgList[k];			  
			  if(pageImg==0){
				  indexImg=img;
				  pageImg++;
			  }
			  var imgHtml = '<div class="swiper-slide"><img src="'+img+'" style="height: 100%;width:100%"/></div>'
			  $("#indexBanner").append(imgHtml);
		  }
	  }
	 var swiper3 = new Swiper('.s_banner', {
		  autoplay:1000,
	      loop: true,
	      observer: true,
	      observeParents: true,
	      speed:500,
	      pagination: {
	        el: '.swiper-pagination',
	        type: 'fraction'
	      }
	    });
	 
}

//判断该奖励多少的福利
function howFuBiMustGive(){
	var num = (Number(welfareRewardCoin)/100).toFixed(2);
	$('#myWelfareRewardCoin').html("¥"+num);
	$('#sonWelfareRewardCoin').html("¥"+num);
	$('#myWelfareRewardCoin2').html("¥"+num);
	$('#sonWelfareRewardCoin2').html("¥"+num);
} 