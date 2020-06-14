function toAjaxJson(url,json,success){
	$.post(ctx+"/"+url,json,success,"json");
}

function toPage(url,json,divId,success){
	$("#"+divId).load(ctx+"/"+url,json,success);
}


function getUser(){
	var str = sessionStorage.getItem("loginuser");
	if(null==str||""==str){
		//alert("请重新登录")
	}else{
		return $.parseJSON(str);
	}

}

/***用户点击分享到微信圈后加载接口接口****** initType = 0 普通分享 initType= 1 福利详情分享 成功后+1 */
function initShare(shareTitle,shareImg,shareLink,des,adminId){
	$.post(ctx+"/manage/web/getShareInfo",{"url":window.location.href,"adminId":adminId},function(data,status){
		wx.config({
            debug: false,
            appId: data.appid,
            timestamp:data.timestamp,
            nonceStr:data.nonceStr,
            signature:data.signature,
            jsApiList: [
                'checkJsApi',
                'hideAllNonBaseMenuItem',
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo',
                'onMenuShareQZone',
                'checkJsApi',
                'hideOptionMenu',
                'showOptionMenu',
                'hideMenuItems',
                'showMenuItems',
                'showAllNonBaseMenuItem'
            ]
        });
        wx.ready(function(){
            wx.onMenuShareTimeline({//分享到朋友圈
                title : shareTitle, // 分享标题
                link : shareLink, // 分享链接
                desc: des, //分享描述
                imgUrl : shareImg, // 分享图标
                success : function() {
                	
                },
                cancel : function() {
                }
            });
            wx.onMenuShareAppMessage({//分享给朋友
                title : shareTitle,  
                link : shareLink, 
                desc: des, 
                imgUrl : shareImg, 
                success : function(data) {
                }, 
                cancel : function() {
                }
            });
            
            wx.onMenuShareQQ({//分享QQ
                title : shareTitle, 
                link : shareLink,  
                desc: des,  
                imgUrl : shareImg, 
                success : function() { },
                cancel : function() { }
            });
            
            wx.onMenuShareWeibo({//分享微博
                title : shareTitle, 
                link : shareLink,
                desc: des, 
                imgUrl : shareImg, 
                success : function() { },
                cancel : function() { }
            });
            
            wx.onMenuShareQZone({//分享到空间
                title : shareTitle, 
                link : shareLink, 
                desc: des, 
                imgUrl : shareImg, 
                success : function() { },
                cancel : function() { }
            });
        });
    });
}
/***用户分享赠送订单******  */
function initShareSucess(orderNumber,shareTitle,shareImg,shareLink,des,initType,url,adminId){
	$.post(ctx+"/manage/web/getShareInfo",{"url":window.location.href,"adminId":adminId },function(data,status){
		wx.config({
            debug: false,
            appId: data.appid,
            timestamp:data.timestamp,
            nonceStr:data.nonceStr,
            signature:data.signature,
            jsApiList: [
                'checkJsApi',
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo',
                'onMenuShareQZone'
            ]
        });
 
    });
    wx.ready(function(){
        wx.onMenuShareTimeline({//分享到朋友圈
            title : shareTitle, // 分享标题
            link : shareLink, // 分享链接
            desc: des, //分享描述
            imgUrl : shareImg, // 分享图标
            success : function() {
            	window.location.href=url;
            	$.ajax({
        			url : ctx+'/manage/shareRecords/addOrderShareRecord',
        			data : { "orderNumber" : orderNumber},
        			success : function(data) {
        			}
        		});
            }
        });
        wx.onMenuShareAppMessage({//分享给朋友
            title : shareTitle,  
            link : shareLink, 
            desc: des, 
            imgUrl : shareImg, 
            success : function(data) {
            	window.location.href=url;
            	$.ajax({
        			url : ctx+'/manage/shareRecords/addOrderShareRecord',
        			data : { "orderNumber" : orderNumber},
        			success : function(data) {
        			}
        		});
            }
        });

    });
}
//加载福利图片
function loadwelfareImg(welfareImg){
	if(welfareImg.indexOf("welfareImg_") < 0 ){
		return welfareImg;
	}else{
	 var welfareImgList = JSON.parse(welfareImg);
	 for(var k in welfareImgList){
		  if(k.indexOf("welfareImg_")>=0){
			  var img = ctx+ welfareImgList[k];
			  return img;
		  }
	}
	}
}
//向PD_DATA保存记录
function countData(from){
  //测试    url: 'http://www.fuxingfuli.com/PD_Data/terminal/data',		
	//正式url:'http://data.fuxingfuli.com/terminal/data'
 	$.ajax({
        type: 'post',
        data:{"mode":"2", "userId":getUser().id,"from":from},	
        url: 'https://data.fuxingfuli.com/terminal/data',
        dataType: "jsonp"
 	 });

}
//向PD_DATA保存记录
function countData(from,userId){
  //测试    url: 'http://www.fuxingfuli.com/PD_Data/terminal/data',		
	//正式url:'http://data.fuxingfuli.com/terminal/data'
 	$.ajax({
        type: 'post',
        data:{"mode":"2", "userId":userId,"from":from},	
        url: 'https://data.fuxingfuli.com/terminal/data',
        dataType: "jsonp"
 	 });

}
function getQueryStr(str) {
	 var LocString = String(window.document.location.href);
	 var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(LocString), tmp;
	 if (tmp = rs) {
	  return tmp[2];
	 }
	 return "";
}//改变福利名字（如果福利名字太长的话）
function changeWelfareName(name,length){
	 var tempName;
	 if(!name){
		 return "无福利名称";
	 }
	 var nameLength=name.length;
	 if(nameLength>length){
		  tempName=name.substring(0,length)+"**";
	 }
	 else{
		 tempName=name;
	 }
	return tempName;
}
