var shareUserId, shareLink,
time_now,time_distance_start, _FULItimer,
singlePeopleRes = false ,singleDayRes = false,
useAdminId;
var ipCity,ipProvince;
	
		
	$(function() {
		
		getCityAndProvince();
		 
	 	sessionStorage.setItem("loginuser",JSON.stringify(user));
	 	 
	 	if(byShare == "xcx"){				//小程序点进来
 		 	$(".mb_return").hide();
 		 	$("#s").hide();
 		 	if(xcxshareUserId == "" || xcxshareUserId == null || xcxshareUserId == "undefined"){
	 		 	shareUserId = user.id;
 		 	}else{
	 		 	shareUserId = xcxshareUserId;
 		 	}
 		 	sessionStorage.setItem("xcxUserId",user.id);	 
 		 	if(xcxFXSL != "" &&  xcxFXSL != null &&  xcxFXSL == "true" ){
				sessionStorage.setItem("xcxFXSL",xcxFXSL);
 		 	}
	 	}else if(byShare != "false"){		//公众平台分享进来
	 		shareUserId = byShare;
	 	}else{								//平台点进来
	 		shareUserId = user.id;
	 	}

 		useAdminId = user.adminId;
 		shareLink =   manageServerName+"/view/welfare/byShareIn?wid="+ welfareId +"&shareUserId=" +user.id+ "&adminId=" +user.adminId ;
 		
 		if(getQueryStr("companyType")!="" && getQueryStr("companyType")=="1"){
			initShare(shareTitle, shareCtx + shareImg, shareLink+"&companyType=1&companyTypeId="+getQueryStr("companyTypeId"), shareDes ,"1",welfareId);
		}else{
			initShare(shareTitle, shareCtx + shareImg, shareLink, shareDes ,"1",welfareId);
		}
		

		//特殊企业隐藏福分功能及处理
		if(getQueryStr("companyType")!=""&&getQueryStr("companyType")=="1"){
			showOrHideCompany();
		}
		
		if(billingType == "8"){
			$(".become").hide();		
			$(".bubaoyou").hide();		
			$(".mb_shangjiamsgtop").hide();		
		}
		
		//福利上方图片位置倒计时
	 	countDownTitle();
 		
	
		//查询福利的状态
//	  	queryWelfareStatus();
	
	 	if(user.adminId == "17"){
	 		$(document).attr("title","课程详情");
	 		$("#mb_menu").children().eq(0).find("a").html("课程"); 
	 	}
		
		//加载福利图片
	 	loadBanner();
			 
		var reg = new RegExp(",","g");//g,表示全部替换。
		start_time = start_time.replace(reg,"");
		time_end   = time_end.replace(reg,"");
		time_now = new Date().getTime();
		time_distance = time_end - time_now;
		time_distance_start = start_time - time_now;

		if(byShare != "xcx"){
		    if(	localStorage.getItem("province") == null || localStorage.getItem("province") == ""){
		  	  	toGetStorage();
		    }else{
			    var localCity = localStorage.getItem("city");
		    	var localProvince =  localStorage.getItem("province"); 
		    	if(localProvince.indexOf(ipProvince) < 0 || localCity.indexOf(ipCity) < 0){
		    		toGetStorage();
		    	}else{
		    		city = localStorage.getItem("city"); 
		    		province = localStorage.getItem("province"); 
					addPointsRecord();//**增加一条有效浏览积分 和 访问人数纪录**/
		    	}
		    }
			if(backGround.indexOf("/") >= 0){ 
				$("#item2").css("background-image","url("+ctx + backGround+")");
				$("#item2").css("background-size","100% 100%");
			}else if(backGround!=''){
				$("#item2").css("background-color",backGround);
			}
		 }else{
				addPointsRecord(); 
		 }
		
	 	addPageViews();
	 
		scalePage2(); //*适配手机*/    
		
		if(user.userType == "2"){ //金牌福星
			$("#putongUser").hide();
		}else{
			$("#putongUser").show();
		}
		
		
		//微信卡券类型 弹出微信卡包 
		wx.ready(function(){
		   	if(sessionStorage.getItem("orderNumber") !=null && sessionStorage.getItem("orderNumber") !=""){
			$.ajax({
				url: ctx + "/manage/payment/queryOrderByNumber",
				data:{"orderNumber":sessionStorage.getItem("orderNumber")},
			  	async: true,
			  	success:function(data){
			  		if(data.status == 1){
			  			toWXcard();
			  		}
			  	}
			  });
			} 
		  });

	  //隐藏企业按钮
	  if(user.adminId != welfareAdminId){
	  	$(".flex-btn").children().eq(0).show();
	  	$(".flex-btn").children().eq(1).hide();
	  	$(".flex-btn").children().eq(2).hide();
  	  }
  
    //当前这个人的福钻数 
   if(exchangeCoins > 0){ getClikcUserCoins(); }else{ $(".fubi-tips").hide(); }
		
	//弹选择分享样式
	if(sessionStorage.getItem("showCoins") == "1" ){
		sessionStorage.removeItem("showCoins");
		sessionStorage.removeItem("fuliHtml");
		$("#selectStyle").show();
	}
	
	//一进来就去成为分享人下级，没有任何提示
 	toSharedWelfare(); 	
	
	//点击福利进行奖币操作
	addConisByWelfare();
	 
	//获得流水记录
	getShareUser();
	
	//获取分享人的头像 展示在右上角
	getShareInfo();
	
	if(useAdminId == '4'){	
	    $("#weixin_boxImg").attr("src",ctx+"/images/fuli/kfwxTP4.jpg");
	}else if(useAdminId == '3'){
		$("#adminName").text("谈资优选邀您关注");
	}else if(useAdminId == '17'){
		$("#adminName").text("儿童教育汇邀您关注");
	}else if(useAdminId == '19'){
		$("#adminName").text("川川地球邀您关注");
	}else{
		$("#weixin_boxImg").attr("src",ctx+"/images/fuli/kfwxTP.png");
	}
	
	
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


function toGetStorage(){
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			province = r.address.province;
		   	city = r.address.city;  
			localStorage.setItem("longitude",r.point.lng);	//经度
			localStorage.setItem("latitude",r.point.lat);		//纬度
	 	 	localStorage.setItem("province",r.address.province); 
         	localStorage.setItem("city",r.address.city);  
     //    	addCoinsRecords();
        	addPointsRecord();//**增加一条有效浏览积分 和 访问人数纪录**/
		}        
	},{enableHighAccuracy: true})

}
 
//根据ip获取省市
function getCityAndProvince(){
	$.ajax({
		url: ctx + "/manage/web/getCityAndProvince",
	    async: false,
		success:function(data){
			ipCity = data.city;
			ipProvince = data.province;
		}
	});
}


function sendToFriend(){ 
	if(provName!="不限制" ){
		if((province.indexOf(provName) < 0)){
			alert("您不在福利区域范围内!");
			return;
		}else if(city.indexOf(cityName) < 0 && cityName!="不限制"){
			alert("您不在福利区域范围内!");
			return;
		}
	}
	if((limitRob == 0 &&  time_distance < 0 )  || welfareStatus == "3" ){
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
	if(singleDay != 0){
		var count = queryBuyStatusNew();
		if(count >= singlePeople){
			alert("超过购买限制");
			return;
		}	
	}
	var res = queryNumber();//查询是否卖完
	if(res){
		alert("已售罄");
		return;
	}
	
	if(sessionStorage.getItem("xcxUserId") != null && sessionStorage.getItem("xcxUserId") !="") {
		if(sessionStorage.getItem("xcxFXSL")=="true"){	//表示是福星送礼小程序进入的，这里还是进入到公众号的页面     
			window.location.href = ctx + "/manage/shareRecords/toBuyPresents?welfareName="+encodeURI(welfareName)+"&welfareId="+welfareId+"&shareUserId="+shareUserId+"&singleDay="+singleDay+"&singlePeople="+singlePeople;
		}else{
			ShowLinkStyleShare();
		}
	}else{
		window.location.href = ctx + "/manage/shareRecords/toBuyPresents?welfareName="+encodeURI(welfareName)+"&welfareId="+welfareId+"&shareUserId="+shareUserId+"&singleDay="+singleDay+"&singlePeople="+singlePeople;
	} 
}
 
//特殊处理里福利企业首页跳转到自己的首页
function toshouye2(id){
	window.location.href=ctx+"/manage/web/tocompanyInfo?id="+id+"&adminId="+user.adminId;
}

//跳转我的订单页面
function tomyOrder(){
	window.location.href=ctx +"/manage/center/toOrderList?adminId="+user.adminId+"&userId="+user.id;
}
	

function toWXcard(){ 
      $.ajax({
		url: ctx+"/manage/shareRecords/cardSingature",
		data:{"url":window.location.href,"cardId":cardId,"openid":user.openID,"adminId":user.adminId},
		async: false,
		success:function(data){
			sessionStorage.removeItem("orderNumber");
			 wx.addCard({
	           cardList: [{
	               cardId: data.cardId,
	               cardExt: '{"code":"", "openid": "'+data.openid+'", "nonce_str":"'+data.nonceStr+'","timestamp": "'+data.timestamp+'", "signature":"'+data.signature+'"}'
	           }], // 需要添加的卡券列表
	           success: function (res) { 
	               var cardList = res.cardList; // 添加的卡券列表信息
	           }
	       });
		}
	})
}


function getClikcUserCoins(){
	$.ajax({
		url: ctx + "/manage/shareRecords/getClikcUserCoins",
		data:{"clickUserId":user.id},
		async: false,
		success:function(data){ 
			$("#myAllCoins").text(data.coinsRemain);
			var haiCha = Number(data.coinsRemain) - Number(exchangeCoins);
			
			if(parseFloat(haiCha)>0){
				$("#haiCha_coins").text("可用"+exchangeCoins+"福钻")
			}else{
				$("#haiCha_coins").text("还差"+Number(haiCha).toFixed(1).split("-")[1]+"福钻")
			}
		}
	})
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
					}
					else {
						
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
 				
 //**邀请您与您一起关注如果该用户是被分享的则显示分享人信息，没有走默认
function getShareInfo(){
	$.ajax({
		url:ctx + "/manage/shareRecords/getShareInfo",
		data: {"shareUserId":shareUserId},
	 	async: false,
		success:function(data){
			if(shareUserId != user.id){
				$("#shareIcon").attr("src",data.shareUser.userIconUrl);
				$(".focus").children("p").text((data.shareUser.nickName).substring(0,1)+"**邀您关注");
			}
		}
	})
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
function addConisByWelfare(){
	
	if(time_distance < 0 || welfareStatus =="3"){ return; }
	if(time_distance_start > 0){ return; }
	if(welfareStatus =="0"){ return; }
	if(user.id==shareUserId){return;}//不是分享进来的
	$.ajax({
		url:ctx + "/manage/shareRecords/addConisByWelfare",
		data: {"welfareId":welfareId, "clickUserId":user.id,"shareUserId":shareUserId,"adminId":welfareAdminId},
	 	async: false,
		success:function(data){
		}
	})
}
 
//返回 0 可以购买
function queryBuyStatusNew(){
	//debugger;
	var count = 0;
	$.ajax({
		url:ctx + "/manage/shareRecords/queryBuyStatusNew",
		data: {"singleDay":singleDay,"singlePeople":singlePeople,"clickUserId":user.id,"welfareId":welfareId},
	 	async: false,
		success:function(data){
			count = data.count;
		}
	})
	return count; 
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
 
/*** 领取卡券按钮 */
function receiveCard(){
	var u = navigator.userAgent, app = navigator.appVersion;
	
	var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);				 //ios终端
	 if (isIOS) {   $('.get_alert_content').css({'width':'300px','left':'50%','margin-left':'-150px','z-index':'999999999'}); } 
	
	$("#cardP").removeAttr("onclick");
	if(provName!="不限制" ){
		if((province.indexOf(provName) < 0)){
			alert("您不在福利区域范围内!")
			$("#cardP").attr("onclick","receiveCard()");
			return;
		}else if(city.indexOf(cityName) < 0 && cityName!="不限制"){
			alert("您不在福利区域范围内!")
			$("#cardP").attr("onclick","receiveCard()");
			return;
		}
	}
	if(limitRob == 0  && time_distance_start > 0){
		alert("活动未开始!");
		$("#cardP").attr("onclick","receiveCard()");
		return;
	}
	if( (limitRob == 0  && time_distance < 0 ) || welfareStatus =="3"){
		alert("活动已结束!");
		$("#cardP").attr("onclick","receiveCard()");
		return;
	}
	if( welfareStatus =="0" ){
		alert("活动已下架!");
		$("#cardP").attr("onclick","receiveCard()");
		return;
	}
	if(cardType == 0){
		getWxCard();//微信卡券
	}else{
		queryEcardCountAndRegister();//查询一卡易卡券剩余领取数 和 是否注册过
	}
}

/**
 * 查询一卡易卡券剩余领取数 和 是否注册过
 */
function queryEcardCountAndRegister(){
	var result  = queryEcardCount();	//false 一卡易领完了
	if(!result){
		alert("卡券领完啦");
		$("#cardP").attr("onclick","receiveCard()");
		return;
	}else{
		$("#cardP").attr("onclick","receiveCard()");
		sessionStorage.setItem("readUrl",window.location.href);
		window.location.href = ctx + "/manage/shareRecords/buyWelfare?welfareName="+encodeURI(welfareName)+"&welfareId="+welfareId+"&shareUserId="+shareUserId+"&singleDay="+singleDay+"&singlePeople="+singlePeople;
	}
 
}

/**
 * 查询卡券余量是否大于 0 true大于 false小于
 * @return {}
 */
function queryEcardCount(){
	var result ;
	$.ajax({
		url:ctx+"/manage/shareRecords/queryEcardCount",
		data:{"cardId":cardId,"companyId":companyId},
		async: false,
		success:function(data){
			if(data.count > 0){
				result = true;
			}else{
				result = false;
			}
		}
	})
	return result;
}
   
/**
 * 去我的卡券
 */
function toEcardDetatil(){
	$("#get_alert_mask").hide();
	window.location.href= ctx+"/external/ecard/ecardList?userId="+user.id;
}

//微信卡券
function getWxCard(){
    //查询这个人领的次数有没有超过 卡券的次数（微信卡券）
    var res = queryCount(); 	
	if(!res){
		alert("您已超过领取次数")
		$("#cardP").attr("onclick","receiveCard()");
		return;
	}   
	$.ajax({
		url: ctx + "/manage/shareRecords/addCardInfo",
		data:{"shareUserId":shareUserId,"clickUserId":user.id,"clickUserOpenid":user.openID,"welfareId":welfareId,"cardId":cardId,"adminId":user.adminId},
	  	async: false
	});
	
	//查询这个人是否超过领取限制
	querySingleDayWX();		
	
	//微信卡券免费的话直接弹二维码
	if(cardPayStatus == 0){	
		toWXcard();
		$("#cardP").attr("onclick","receiveCard()");
	}else{
	 	$.ajax({
			url:ctx + "/manage/payment/createOrder",
			data: {"openId":user.openID,"userId":user.id, "welfareId":welfareId,"adminId":user.adminId,"readUrl":window.location.href,"wxCenterId":user.wxCenterId},
		 	async: false,
			success:function(data){   
				sessionStorage.setItem("orderNumber",data.orderNumber);
				window.location.href= data.toPay;
			}
		})
	}
}

function querySingleDayWX(){
	$.ajax({
		url:ctx + "/manage/shareRecords/querySingleDayWX",
		async: false,
		data:{"cardId":cardId,"clickOpenid":user.openID,"singleDay":singleDay,"singlePeople":singlePeople},
		success:function(data){
			if(singleDay!=0){
				if(data.singleDayresult){
					alert("您已超过单日领取限量");
					return;
				}
			}
			if(singlePeople!=0){
				if(data.singlePeopleCountresult){
					alert("您已超过此卡券的领取限量");
					return;
				}
			}
		}
	})
}
		
/**
 * 查询此人领券的次数 有没有超过卡券的领取限制
 * true 可以  false超过次数
 */
function queryCount(){
	var result;
	$.ajax({
		url:ctx + "/manage/shareRecords/queryCardInfoCount",
		async: false,
		data:{"cardId":cardId,"openId":user.openID,"adminId":user.adminId},
		success:function(data){
			if(data.result){
				result =true;
			}else{
				result =false;
			}
		}
	})
	return result;
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
function welfare_phone(){
	if(welfarePhone != ""){
		$("#aEvent").attr("href","tel:"+welfarePhone);
		$("#aEvent")[0].click();
	}
}
		
/**
 * 图片的点击事件
 * @param  divID 
 * _imgLink  代表这个组件点击的 内容
 * _imgOnclickType 代表这个组件点击的类型  0跳转链接  1打电话  2发短信 3打开淘宝 4播放视频
 * billingType 1：有效线上报名；2有效点击计费；3：浏览展现计费
 */
function loadImgFun(obj,type,divID){
	if(limitRob == 0 && time_distance_start > 0){
		alert("活动未开始!")
		return;
	}
	if( welfareStatus =="0"){
		alert("活动已下架!")
		return;
	}
	if((limitRob == 0 && time_distance < 0) || welfareStatus=="3"){
		alert("活动已结束!")
		return;
	}
	var link = bigColumn[divID+"_imgLink"];
	var type = bigColumn[divID+"_imgOnclickType"];
	if(type=="0" || type=="3"){
		if(link!=''){
			 window.location.href=link;
		}
	}else if(type=="1"){
		 $("#aEvent").attr("href","tel:"+link);
		 $("#aEvent")[0].click();
	}else if(type =="2"){
		 $("#aEvent").attr("href","sms:"+link);
		 $("#aEvent")[0].click();
	}else if(type =="4"){
		$("#divEvent").empty().append(link);
	}
}	

function changeTextarea (obj,asd){
	return;
}



/**
 * 文本域的点击事件
 * @param _textOnclickType文本域的点击类型 0跳转链接  1打电话  2发短信 3打开淘宝 4播放视频
 * @param _textLink  代表这个组件点击的 内容
 */
function loadDataText(obj,divID){
	return;
	if(time_distance_start > 0){
		alert("活动未开始!")
		return;
	}
	if( welfareStatus =="0"){
		alert("活动已下架!")
		return;
	}
	if(time_distance < 0 || welfareStatus=="3"){
		alert("活动已结束!")
			return;
	}
	var type= bigColumn[divID+"_textOnclickType"];
	var link = bigColumn[divID+"_textLink"];
	var _textarea = bigColumn[divID+"_textarea"];
	/*if(billingType == "2"){ 
		addClickCoinsRecords(); 
	}*/
	if(type=="0" || type=="3"){
		 window.location.href=link;
	}else if(type=="1"){
		 $("#aEvent").attr("href","tel:"+link);
		 $("#aEvent")[0].click();
	}else if(type =="2"){
		 $("#aEvent").attr("href","sms:"+link);
		 $("#aEvent")[0].click();
	}else if(type =="4"){
		$("#divEvent").empty().append(link);
	}
}

/**
 * 按钮的点击事件
 * @param  _buttonOnclickType 按钮的点击事件类型v 0跳转链接  1打电话  2发短信 3打开淘宝 4播放视频
 * @param  _buttonLink  代表这个组件点击的 内容
 */
function loadButton(obj,divID){
	if(limitRob == 0 && time_distance_start > 0){
			alert("活动未开始!")
		return;
	}
	if( welfareStatus =="0"){
		alert("活动已下架!")
		return;
	}
	if((limitRob == 0 && time_distance < 0) || welfareStatus=="3"){
		alert("活动已结束!")
		return;
	}
	var type= bigColumn[divID+"_buttonOnclickType"];
	var link = bigColumn[divID+"_buttonLink"];
	/*if(billingType == "2"){ 
		addClickCoinsRecords(); 
	}*/
	if(type=="0" || type=="3"){
		 window.location.href=link;
	}else if(type=="1"){
		 $("#aEvent").attr("href","tel:"+link);
		 $("#aEvent")[0].click();
	}else if(type =="2"){
		 $("#aEvent").attr("href","sms:"+link);
		 $("#aEvent")[0].click();
	}else if(type =="4"){
		$("#divEvent").empty().append(link);
	}
}

	
/**  添加一条 福钻流水记录 ~~~ ~~~  (有效浏览)
 * billingType 1：有效线上报名；2有效点击计费；3：浏览展现计费
 */
function addCoinsRecords(){
	if(billingType == "3" ){
		if( limitRob == 0 && time_distance_start > 0){
			alert("活动未开始!")
			return;
		}
		if( welfareStatus =="0"){
			alert("活动已下架!")
			return;
		}
		if( (limitRob == 0 && time_distance  < 0) || welfareStatus=="3"){
			alert("活动已结束!")
			return;
		}
		if(provName!="不限制" ){
			if((province.indexOf(provName) < 0)){
				alert("您不在福利区域范围内!")
				return;
			}
		}
		$.ajax({
			url:ctx+"/manage/coinsRecord/addCoinsRecords",
			data:{"welfareId":welfareId,"clickUserid":user.id,"shareUserid":shareUserId,"city":city},
		    async: false
		});
	}
}

/**如果福利类型 billingType= 2 添加一条 福钻流水记录（有效点击）  */
function addClickCoinsRecords(){
	if(limitRob == 0 && time_distance_start > 0){
		alert("活动未开始!")
		return;
	}
	if( welfareStatus =="0"){
		alert("活动已下架!")
			return;
		}
	if((limitRob == 0 && time_distance < 0) || welfareStatus=="3"){
		alert("活动已结束!")
		return;
	}
	if(provName!="不限制" ){
	if((province.indexOf(provName) < 0)){
		alert("您不在福利区域范围内!")
			return;
		}
	}
	$.ajax({
		url:ctx+"/manage/coinsRecord/addClickCoinsRecords",
		data:{"welfareId":welfareId,"clickUserid":user.id,"shareUserid":shareUserId,"city":city},
		async: false,
		success:function(res){
			if(res.result!=""){
				window.location.href = linkUrl;
			}
		}
	});
}

/*** 增加一条积分流水表( 浏览) 积分 只要浏览就加积分,不区分福利类型   +  访问记录 */
function addPointsRecord(){
    $.ajax({
        type: 'get',
        data:{"mode":"1","welfareId":welfareId,"clickUserId":user.id,"shareUserId":shareUserId,"city":city,"from":""},
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
	if(getQueryStr("companyType")!=""&&getQueryStr("companyType")=="1"){
		return;
	}else if( getQueryStr("xcxUserId") != "" &&  getQueryStr("xcxUserId") != null  ){
		initMiniToIndex();
	}else{
		var url = ctx + "/view/main/main?adminId="+user.adminId+"&type=0";
		window.location.href = url;
	}
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
/**适配*/
/*window.onresize = setHtmlFontSize;
function setHtmlFontSize(){
	var htmlWidth = document.documentElement.clientWidth || document.body.clientWidth;
	var htmlDom = document.getElementsByTagName('html')[0];
    htmlDom.style.fontSize = htmlWidth / 10 + 'px';
};
setHtmlFontSize();*/
/**
 * 以下这段代码是用于根据移动端设备的屏幕分辨率计算出合适的根元素的大小
 * 当设备宽度为375(iPhone6)时，根元素font-size=16px; 依次增大；
 * 限制当为设备宽度大于768(iPad)之后，font-size不再继续增大
 * scale 为meta viewport中的缩放大小
 */
(function (doc, win) {
 var docEl = win.document.documentElement;
 var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
 /**
   * ================================================
   *   设置根元素font-size
   * 当设备宽度为375(iPhone6)时，根元素font-size=16px; 
   × ================================================
   */
 var refreshRem = function () {
   var clientWidth = win.innerWidth
                     || doc.documentElement.clientWidth
                     || doc.body.clientWidth;

   console.log(clientWidth)
   if (!clientWidth) return;
   var fz;
   var width = clientWidth;
   fz = 16 * width / 375;
   docEl.style.fontSize = fz + 'px';
 };

 if (!doc.addEventListener) return;
 win.addEventListener(resizeEvt, refreshRem, false);
 doc.addEventListener('DOMContentLoaded', refreshRem, false);
 refreshRem();

})(document, window);

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
	if(getQueryStr("xcxUserId") != null && getQueryStr("xcxUserId")!=""){ 
		$(".nfl_xiangqing .fl_xiangqingpic").css("background","url("+ctx+"/images/fuli/fl_xiangqing_xcx.png) no-repeat center");
		$(".nfl_xiangqing .fl_xiangqingpic").css("background-size","100% 100%");
	}
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
//		  $("#dayDown").html(days);
		 /* $("#hourDown").html(Number(days*24)+Number(hours));
		  $("#minutesDown").html(minutes);
		  $("#secondsDown").html(seconds);*/
		
		  /* var html='';
		  html+='<span class="time-item" id="hourDown">'+Number(Number(days*24)+Number(hours))+'</span>';
		  html+='<i class="icon-maohao"></i>';
		  html+='<span class="time-item" id="minutesDown">'+minutes+'</span>';
		  html+='<i class="icon-maohao"></i>';
		  html+='<span class="time-item" id="secondsDown">'+seconds+'</span>';
		  $('#countDowe').append(html);*/
	  
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
	if( getQueryStr("xcxUserId") != "" &&  getQueryStr("xcxUserId") != null  ){
		initMiniToGold();
	}else{
		var url = ctx + "/manage/center/tofuxingcenter?adminId="+user.adminId;
		window.location.href = url;
	}
}

//成为金牌福星
function toGold(){
	var url = ctx + "/manage/shareRecords/toGold?userId="+user.id;
	window.location.href = url;
}


function showOrHideCompany(){
	//显示 订单
	$("#myOrder").show();
	//隐藏弹屏
	$(".newsUl").hide();
	//隐藏邀请关注
	$(".focus").hide();
	//看其TA福利>
	$(".golook").hide();
	//我的钱包
	$("#wdqb").before("<p  id='myOrder' onclick='tomyOrder();'>订单</p>");
	$("#wdqb").hide();
	//头像上方<p>标签
	$("#mb_xian_hide").hide();
	
	//联系他   全部福利
	$(".flex-btn a").hide();
	//隐藏转发福钻按钮
	$("#zffb").hide();
	$("#zffb").parent().attr("style","background:url('"+ctx+"/images/fuli/btn-bg2.png') no-repeat center !important;background-size:100%;border-radius:25px");
	$("#buttonName").attr("style","margin-left: 62px");
	//改变首页按钮默认的跳转链接
	$("#toshouye").attr("onclick","toshouye2('"+getQueryStr("companyTypeId")+"')");
}

//小程序点击钱包按钮进入小程序的页面
function initMiniToGold(){
	$.post(ctx+"/manage/web/getShareInfo",{"url":window.location.href,"adminId":user.adminId},function(data,status){
		wx.config({
            debug: false,
            appId: data.appid,
            timestamp:data.timestamp,
            nonceStr:data.nonceStr,
            signature:data.signature,
            jsApiList: [
                'checkJsApi' 
            ]
        });
        wx.ready(function(){ 
        	if(sessionStorage.getItem("xcxFXSL")=="true"){	//表示是福星送礼小程序进入的，这里是进入
         		wx.miniProgram.switchTab({ url: '/pages/my/my'})
        	}else{       		
         		wx.miniProgram.switchTab({ url: '/pages/commission/commission'})
        	}
        });
    });
}


function toshouye(){
	if( sessionStorage.getItem("xcxUserId") != "" && sessionStorage.getItem("xcxUserId") != null  ){
		initMiniToIndex();
	}else{
		var url = ctx + "/manage/welfareIndex/toIndex?adminId="+user.adminId;
		window.location.href = url;
	}
}

//去商家详情
function tocompanyInfo(id){
	if(getQueryStr("companyType")!=""&&getQueryStr("companyType")=="1"){
		return;
	}else if( sessionStorage.getItem("xcxUserId") != "" &&  sessionStorage.getItem("xcxUserId") != null  ){
		intMiniToCompany(id);
	}else{
		window.location.href=ctx+"/manage/web/tocompanyInfo?companyId="+id+"&adminId="+user.adminId;
	}
}


//小程序点击跳转到小程序企业管理
function intMiniToCompany(id){   
	var adminId = user.adminId;
	var json = {"url":window.location.href,"adminId": adminId }; 
	$.post(ctx+"/manage/web/getShareInfo",{"url":window.location.href,"adminId":user.adminId},function(data,status){
		wx.config({
            debug: false,
            appId: data.appid,
            timestamp:data.timestamp,
            nonceStr:data.nonceStr,
            signature:data.signature,
            jsApiList: [   'checkJsApi'   ]
        });			
        wx.ready(function(){  
			wx.miniProgram.navigateTo({ url: '/pages/company-detail/company-detail?id=' + id }) 
    	});
    });
}

//小程序点击跳转到小程序首页 
function initMiniToIndex(){   
	var adminId = user.adminId;
	var json = {"url":window.location.href,"adminId": adminId }; 
	$.post(ctx+"/manage/web/getShareInfo",{"url":window.location.href,"adminId":user.adminId},function(data,status){
		wx.config({
            debug: false,
            appId: data.appid,
            timestamp:data.timestamp,
            nonceStr:data.nonceStr,
            signature:data.signature,
            jsApiList: [   'checkJsApi'   ]
        });			
        wx.ready(function(){  
			wx.miniProgram.switchTab({ url: '/pages/index/index'}) 
    	});
    });
}


//小程序进入
function byXcxJoin(){
	var longitude  = getQueryStr("longitude");
	var latitude  = getQueryStr("latitude");
	sessionStorage.setItem("xcxFulidetail",window.location.href);	//用于小程序支付完 去success_pay页面用
	sessionStorage.setItem("xcxUserId",getQueryStr("xcxUserId"));	//address.jsp支付时使用   xcxUserId等于clickUserId   shareUserId = getQueryStr("xcxshareUserId");
	if(getQueryStr("xcxFXSL") != "" ||  getQueryStr("xcxFXSL") != null ){
		sessionStorage.setItem("xcxFXSL",getQueryStr("xcxFXSL"));
		$.ajax({
			url:ctx+"/manage/shareRecords/getUserById",
			data:{"xcxUserId": getQueryStr("xcxUserId") , "adminId":getQueryStr("adminId") ,"longitude":longitude, "latitude":latitude},
		    async: false,
			success:function(data){
				useAdminId = getQueryStr("adminId"); 
				if( getQueryStr("xcxshareUserId") =="" ||  getQueryStr("xcxshareUserId") == null){
					shareUserId  = getQueryStr("xcxUserId");
				}else{
					shareUserId  = getQueryStr("xcxshareUserId");
				}
				province = data.province;
				city = data.city;
				sessionStorage.setItem("province",province);
				sessionStorage.setItem("city",city);
				sessionStorage.setItem("loginuser",JSON.stringify(data.user));
			}
		});
	}else{
		$.ajax({
			url:ctx+"/manage/shareRecords/getUserById",
			data:{"xcxUserId": getQueryStr("xcxUserId") , "adminId":getQueryStr("adminId") ,"longitude":longitude, "latitude":latitude},
			async: false,
			success:function(data){
				useAdminId = getQueryStr("adminId");
				if( getQueryStr("xcxshareUserId") =="" ||  getQueryStr("xcxshareUserId") == null){
					shareUserId  = getQueryStr("xcxUserId");
					$("#s").html("推广赢福钻");
				}else{
					$("#s").hide();
					shareUserId  = getQueryStr("xcxshareUserId");
				}
				province = data.province;
				city = data.city;
				sessionStorage.setItem("province",province);
				sessionStorage.setItem("city",city);
				sessionStorage.setItem("loginuser",JSON.stringify(data.user));
			}
		});
	}
}

//初始化购买类型的福利
function initByPayFuli(){
	if(provName!="不限制" ){
		if((province.indexOf(provName) < 0)){
			alert("您不在福利区域范围内!");
			return;
		}else if(city.indexOf(cityName) < 0 && cityName!="不限制" ){
			alert("您不在福利区域范围内!");
			return;
		}
	}
	if( (limitRob == 0 && time_distance < 0 ) || welfareStatus == "3"){
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
	if(singleDay != 0){
		var count = queryBuyStatusNew();
		if(count >= singlePeople){
			alert("超过购买限制");
			return;
		}	
	}
	var res = queryNumber();//查询是否卖完
	if(res){
		alert("已售罄");
		return;
	}else{
		sessionStorage.setItem("readUrl",window.location.href);
		var url = ctx + "/manage/shareRecords/buyWelfare?welfareName="+encodeURI(welfareName)+"&welfareId="+welfareId+"&shareUserId="+shareUserId+"&singleDay="+singleDay+"&singlePeople="+singlePeople;
		window.location.href = url;
    }
}


//初始化票券和自定义票券的福利
function initByTicketFuli(){
	if(provName!="不限制" ){
		if((province.indexOf(provName) < 0)){
			alert("您不在福利区域范围内!")
			return;
		}else if(city.indexOf(cityName) < 0 && cityName!="不限制"){
			alert("您不在福利区域范围内!")
			return;
		}
	}
	
	if( (limitRob == 0 && time_distance < 0 ) || welfareStatus =="3"){
		alert("活动已结束!");
		$("#buttonName").html("活动已结束"); 
		return;
	}
	if(time_distance_start > 0 && limitRob == 0 ){
		alert("活动未开始!")
		return;
	}
	
	if(welfareStatus =="0"){
		alert("活动已下架!")
		return;
	}

	if(singleDay != 0){
		var count = queryBuyStatusNew();
		if(count >= singlePeople){
			alert("超过购买限制");
			return;
		}	
	}
	
//	queryBuyStatus();//查询此人是否超过购买限制
//	if(singleDayRes){
//		alert("您已超过单日领取限量");
//		return;
//	}
//	if(singlePeopleRes){
//		alert("您已超过此福利领取限量");
//		return;
//	}
	
	var res = queryNumber();//查询是否卖完
	if(res){
		alert("已领完");
		return;
	}else{
		sessionStorage.setItem("readUrl",window.location.href);
		var url = ctx + "/manage/shareRecords/buyWelfare?welfareName="+encodeURI(welfareName)+"&welfareId="+welfareId+"&shareUserId="+shareUserId+"&singleDay="+singleDay+"&singlePeople="+singlePeople;
		window.location.href = url ;
	//	$("#abc").find("a").attr("href",url);
    }
}

//加载福利图片
 function loadBanner(){
	 var welfareImgList = JSON.parse(welfareImg);
	 var indexImg='';
	 var pageImg=0;
	 for(var k in welfareImgList){
		  if(k.indexOf("welfareImg_")>=0){
			  var img = ctx + welfareImgList[k];
			  if(pageImg==0){
				  indexImg=img;
				  pageImg++;
			  }
			  var imgHtml = '<div class="swiper-slide"><img src="'+img+'" style="height: 100%;width:100%"/></div>'
			  $("#indexBanner").append(imgHtml);
		  }
	  }
	 
	 if(welfareImgList.welfareImg_0 && videoPath){
		 var html='<div class="swiper-slide" z-index=1 style="position:relative;" onclick="videoPlay()">';
		 	 html+='	<video poster="'+indexImg+'" x5-playsinline="" playsinline="" webkit-playsinline="" id="video_welfare" src='+(ctx+videoPath)+' style="height: 100%;width:100%;display:block;" controls=false></video>';
		 	 html+='</div>';
		 $("#indexBanner").prepend(html);
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
function videoPlay(){
	$('#playIcon').remove();
	$('#videoImg').remove();
	$('#video_welfare').css('display','block');
} 
//判断该奖励多少的福利
function howFuBiMustGive(){
	if(sendType==1){//均分
		if(user.userType==2){//金牌
			var num = Number(goldCoins)/200;
			$('#myWelfareRewardCoin').html("¥"+num.toFixed(2));
			$('#sonWelfareRewardCoin').html("¥"+(Number(scale)*num).toFixed(3));
			$('#myWelfareRewardCoin2').html(num.toFixed(2));
			$('#sonWelfareRewardCoin2').html("¥"+(Number(scale)*num).toFixed(3));
		}else{
			var num = Number(welfareRewardCoin)/200;
			$('#myWelfareRewardCoin').html("¥"+num.toFixed(2));
			$('#sonWelfareRewardCoin').html("¥"+(Number(scale)*num).toFixed(3));
			
			$('#myWelfareRewardCoin2').html("¥"+num.toFixed(2));
			$('#sonWelfareRewardCoin2').html("¥"+(Number(scale)*num).toFixed(3));
		}
	}
	if(sendType==0){//独享
		if(user.userType==2){//金牌
			var num = (Number(goldCoins)/100).toFixed(2);
			$('#myWelfareRewardCoin').html("¥"+num);
			$('#sonWelfareRewardCoin').html("¥"+(Number(scale)*num).toFixed(3));
			
			$('#myWelfareRewardCoin2').html("¥"+num);
			$('#sonWelfareRewardCoin2').html("¥"+(Number(scale)*num).toFixed(3));
		}else{
			var num = (Number(welfareRewardCoin)/100).toFixed(2);
			$('#myWelfareRewardCoin').html("¥"+num);
			$('#sonWelfareRewardCoin').html("¥"+(Number(scale)*num).toFixed(3));
			
			$('#myWelfareRewardCoin2').html("¥"+num);
			$('#sonWelfareRewardCoin2').html("¥"+(Number(scale)*num).toFixed(3));
		}
	}
}
 
 
 
 