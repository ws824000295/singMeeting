/*$(document).ajaxError(function(a,b,c){alert("系统异常");});*/
//地图返回信息自动调用mapBack函数
function comp_showMap(options){
	var defaults = {
	    "address":"北京",
	    "mapTitle":"地图",
	    "url":ctx+"/common/map/showMap",
	    "size":"300*180"
	};
	var opts = $.extend(defaults, options);
	$.wbox({
		title:opts.mapTitle,
		target:opts.url+"?address="+encodeURI(encodeURI(opts.address))+"&size="+opts.size,
		requestType:"iframe"
	});
}
function cutImg(tagNum, imgScale, smallScale, cutType, minWidth, minHeight, title,boxWiden,boxHeight) {
	$.box({
		   "height":boxHeight,
		   "width":boxWiden,
		   "title":title,
		   "url":"common/cutimg/cutImg!showCutimgPage.action?ImageCut.imgProposalScale="
				+ imgScale
				+ "&ImageCut.smallScale="
				+ smallScale
				+ "&ImageCut.tagNum="
				+ tagNum
				+ "&ImageCut.cutType="
				+ cutType
				+ "&ImageCut.minWidth="
				+ minWidth
				+ "&ImageCut.minHeight=" + minHeight,
		   "left":25,
		   "top":19,
		   "target":"open"
		});
}

//新截图截图成功后回调viewImg(target,src)函数，target是标示,src是图片完事路径
function cutImg2(target,title){
	$.wbox({
		title:title,
		target:"common/uploadify/uploadify!toCutImg?target="+target
	});
}


//json字符串转为json对象
function comp_toJson(str){
	return eval("("+str+")");
}
//批量上传
function comp_uploadify(obj,options){
	var defaults = {
			'swf':ctx+'/js/uploadify/uploadify.swf',
			'uploader':"",
			"auto":false,
			"fileTypeExts":"*.jpg;*.gif;*.png",
			"fileSizeLimit":1024,//上传文件大小
			"buttonText":"选择文件",
			"queueSizeLimit":15,
			"queueID":"queue",
			"fileObjName":"fileUpload",//上传文件标识
			"removeTimeout":1,
			"onQueueComplete":null,//上传完所有文件事件
			"onUploadSuccess":null//上传成功 一个文件事件
		};
	var opts = $.extend(defaults, options);
	obj.uploadify(opts);
	this.upload=function(){
		obj.uploadify('upload', '*');
	}
	this.cancel=function(){
		obj.uploadify('cancel', '*');
	}
}


function toPage(url,formId,divId,success){
	var divc = "indexShowDiv";
	var json = {};
	if(null!=divId&&""!=divId){
		divc = divId;
	}
	if(null!=formId&&""!=formId){
		json = formToJsonObject(formId);
	}
	json._csrf=$("[name='_csrf']").attr("content");
	json._csrf_header=$("[name='_csrf_header']").attr("content");
	$("#"+divc).load(ctx+"/"+url,json,success);
}

function toPage2(url,json,divId,success){
	var divc = "indexShowDiv";
	if(null != divId && "" != divId){
		divc = divId;
	}
	json._csrf=$("[name='_csrf']").attr("content");
	json._csrf_header=$("[name='_csrf_header']").attr("content");
	$("#"+divc).load(ctx+"/"+url,json,success);
}


//弹出层插件
jQuery.wbox = function(options) {
	if(options.requestType!="html"&&options.requestType!="iframe"){
		options.target=ctx+"/"+options.target;
	}
	var defaults = {
		requestType: "ajax",
		wBoxURL:ctx+"/js/wbox/wbox/",
		show:true
	};
	var opts = $.extend(defaults, options);
	$(document).wBox(opts);
};

function toAjaxForm(url,formId,success){
	var json = {};
	if(null!=formId&&""!=formId){
		json = formToJsonObject(formId);
	}
	json._csrf=$("[name='_csrf']").attr("content");
	json._csrf_header=$("[name='_csrf_header']").attr("content");
	$.post(ctx+"/"+url,json,success,"json");
}

function toAjaxJson(url,json,success){
	json._csrf=$("[name='_csrf']").attr("content");
	json._csrf_header=$("[name='_csrf_header']").attr("content");
	$.post(ctx+"/"+url,json,success,"json");
}

function alertMessage(mes,flag){
	$(".alert").show().find("p").html(mes);
	var box_margin_top =  $(window).scrollTop()+30 + "px";  
	$(".alert").css("top",box_margin_top);
	setTimeout(function(){
		$(".alert").hide(1000);
	},3000);
	if(null!=flag&&flag){
		$(".wBox_close").click();
	}
}
function colseMessage(){
	$(".alert").hide();
}
function confirm(message,yes,no){
	$.confirm({
		'title'		: '提示',
		'message'	: message,
		'buttons'	: {
			'确定'	: {
				'class'	: 'blue',
				'action': yes
			},
			'取消'	: {
				'class'	: 'gray',
				'action': no
			}
		}
	});
}

//图片直接上传回调uploadView(target,data)
function uploadImage(_this) {
    var options = {  
       url : ctx+"/common/uploadify/upload.do",  
       type : "POST",  
       dataType : "json",  
       success : function(data) {  
    	   if(data.result=='yes'){
    		   uploadView($("#fileUploadForm [name='target']").val(),data);
    	   }else{
    		   alert(data.message);
    	   }
       },
       beforeSubmit: function(formData, jqForm, options){
    	   $(_this).after('<input type="text" name="_csrf" value="'+$("[name='_csrf']").attr("content")+'"/><input type="text" name="_csrf_header" value="'+$("[name='_csrf_header']").attr("content")+'"/>');
       }
   };  
   $(_this).parent().ajaxSubmit(options);  
} 
function changeImg(target,size){
	$("#fileUploadForm").remove();
	var html = '<form id="fileUploadForm" method="post" enctype="multipart/form-data" style="display: none;">'+
	                '<input type="file" name="fileUpload"  onchange="uploadImage(this)">'+
	                '<input type="text" name="target" />'+
	                '<input type="text" name="size" />'+
	           '</form>';
	$("body").append(html);
	$("#fileUploadForm [name='target']").val(target);
	if(null!=size&&""!=size){
		$("#fileUploadForm [name='size']").val(size);
	}else{
		$("#fileUploadForm [name='size']").val("1000");
	}
	$("#fileUploadForm [name=fileUpload]").click();
}

//图片直接上传回调uploadVideo(target,data)
function uploadVideo(_this) { 
    var options = {  
       url : ctx+"/common/uploadify/uploadVideo.do",  
       type : "POST",  
       dataType : "json",  
       success : function(data) {
    	   if(data.result=='yes'){
    		   uploadVideoShow($("#videoUploadForm [name='target']").val(),data);
    	   }else{
    		   alert(data.message);
    	   }
       },
       beforeSubmit: function(formData, jqForm, options){
    	   $(_this).after('<input type="text" name="_csrf" value="'+$("[name='_csrf']").attr("content")+'"/><input type="text" name="_csrf_header" value="'+$("[name='_csrf_header']").attr("content")+'"/>');
       }
   };  
   $(_this).parent().ajaxSubmit(options);  
} 
function changeVideo(target){
	$("#videoUploadForm").remove();
	var html = '<form id="videoUploadForm" method="post" enctype="multipart/form-data" style="display: none;">'+
	                '<input type="file" name="fileUpload"  onchange="uploadVideo(this)">'+
	                '<input type="text" name="target" />'+
	                
	           '</form>';
	$("body").append(html);
	$("#videoUploadForm [name='target']").val(target);

	$("#videoUploadForm [name=fileUpload]").click();
}
String.prototype.endsWith=function(endStr){
    var d=this.length-endStr.length;
    return (d>=0&&this.lastIndexOf(endStr)==d);
}
String.prototype.startsWith=function(startStr){
	var d=startStr.length;
	return (d>=0&&this.indexOf(startStr)==0);
}

//confirm插件
function comp_confirm(message,doMethod,options){
	var defaults = {
			icon: 0, 
			title:'提示'
		}
	var opts = $.extend(defaults, options);
	layer.confirm(message,opts, function(index){
	   doMethod();
       layer.close(index);
	});
}


//颜色插件
function comp_spectrum(obj,options){
	var defaults = {
	    color: "rgb(121, 196, 80)",
	    showPalette: true,
        showInput: true,
        preferredFormat: 'hex',
	    cancelText: '取消',
		chooseText: '选择',
		palette: [
	        ['#FFFFFF', '#FF8080', '#FFFF80', '#80FF80', '#400040'],
	        ['#80FFFF', '#0080FF', '#FF80C0', '#FF80FF', '#C0C0C0'],
	        ['#FF0000', '#FFFF00', '#BA55D3', '#FF4500', '#408080'],
	        ['#0080C0', '#8080C0', '#FF00FF', '#804040', '#808080'],
	        ['#FF8040', '#008080', '#004080', '#8080FF', '#808000'],
	        ['#800040', '#FF0080', '#800000', '#000000', '#cece10'],
	        ['#FF8000', '#0000FF', '#0000A0', '#800080', '#400080'],
	        ['#8000FF', '#400000', '#804000', '#2E8B57', '#6495ED']
	    ]
	}
	var opts = $.extend(defaults, options);
	obj.spectrum(opts);
}


function getQueryStr(str) {
	 var LocString = String(window.document.location.href);
	 var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(LocString), tmp;
	 if (tmp = rs) {
	  return tmp[2];
	 }
	 return "";
}

function substring(str, len, hasDot) {  
    var newLength = 0;  
    var newStr = "";  
    var chineseRegex = /[^\x00-\xff]/g;  
    var singleChar = "";  
    var strLength = str.replace(chineseRegex,"**").length;  
    for(var i = 0;i < strLength;i++) {  
        singleChar = str.charAt(i).toString();  
        if(singleChar.match(chineseRegex) != null) {  
            newLength += 2;  
        } else {  
            newLength++;  
        }  
        if(newLength > len) {  
            break;  
        }  
        newStr += singleChar;  
    }  
    if(hasDot && strLength > len) {  
        newStr += "...";  
    }  
    return newStr;  
}
function getMyDate(str){  
    var oDate = new Date(str),  
    oYear = oDate.getFullYear(),  
    oMonth = oDate.getMonth()+1,  
    oDay = oDate.getDate(),  
    oHour = oDate.getHours(),  
    oMin = oDate.getMinutes(),  
    oSen = oDate.getSeconds(),  
    oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
    return oTime;  
}; 
//补0操作
function getzf(num){  
  if(parseInt(num) < 10){  
      num = '0'+num;  
  }  
  return num;  
}

function getUser(backpage){
	var str = sessionStorage.getItem("loginuser");
	if(null==str||""==str){
		window.location.href=ctx+"/manage/web/getWxCode?backpage="+backpage+"&adminId="+adminId;
		return null;
	}else{
		return $.parseJSON(str);
	}
}

/***用户点击分享到微信圈后加载接口接口****** initType = 0 普通分享     initType= 1 福利详情分享 成功后+1 */
function initShare(shareTitle,shareImg,shareLink,des,initType,welfareId,from){
	var AdminId = getUser().adminId;
	$.post(ctx+"/manage/web/getShareInfo",{"url":window.location.href,"adminId":getUser().adminId},function(data,status){
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
                'onMenuShareQZone',
     	        'hideAllNonBaseMenuItem',
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
                	$.ajax({
	                		url:ctx+"/manage/shareRecords/addShareRecord",
	                		data:{"shareUserId":getUser().id,"welfareId":welfareId,"shareOpenid":getUser().openID,"initType":initType,"adminId":AdminId,"from":"timeline"},
	                		async: false
	                	})
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
	                	$.ajax({
	                		url:ctx+"/manage/shareRecords/addShareRecord",
	                		data:{"shareUserId":getUser().id,"welfareId":welfareId,"shareOpenid":getUser().openID,"initType":initType,"adminId":AdminId,"from":"singlemessage"},
	                		async: false
	                	})
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
function initShareHide(){
	$.post(ctx+"/manage/web/getShareInfo",{"url":window.location.href,"adminId":getUser().adminId},function(data,status){
		wx.config({
            debug: false,
            appId: data.appid,
            timestamp:data.timestamp,
            nonceStr:data.nonceStr,
            signature:data.signature,
            jsApiList: [
                'checkJsApi',
                'hideMenuItems'
            ]
        });
        wx.ready(function(){
            wx.hideMenuItems({
                menuList: ['menuItem:share:qq',
                           'menuItem:share:weiboApp',
                           'menuItem:favorite',
                           'menuItem:share:facebook',
                           'menuItem:share:QZone',
                           'menuItem:share:appMessage',
                           'menuItem:share:timeline',
                           'menuItem:share:email',
                           'menuItem:openWithQQBrowser'], 
                success:function(res){
                    alert("隐藏");
                }
            });
        });
    });
}

//模拟alert弹出框插件；
    $.extend({
        malert: function (option) {
            var _win_w = $(window).width();
            var _name = option.name || "_zb_alert_hs_";
            var _bg_opacity = option.bg_opacity || .5;
            var _radius = option.radius || 5;
            var _unit = option.unit || "rem"; //以移动端rem为单位
            var _w = option.width || "6";
            var _h = option.height || "4";
            var _text = option.text;
            var _type = option.type;
            //弹框类型
            if(_type=='tip'){
                var _ceng_bg = '<div style="width:100%;height:100%;position:fixed;top:0;left:0;background:#000;opacity:0;z-index:99999"></div>';
                var _ceng_con = '<div style="width:' + _w + _unit + ';height:' + _h + _unit + ';margin:' + (-_h / 2) + _unit + ' 0 0 ' + (-_w / 2) + _unit + ';border-radius:' + _radius + 'px;position:fixed;top:50%;left:50%;background:#000">';
                _ceng_con += '<p class="' + _name + '_p" style="color:#fff;font-size:.4rem;text-align:center;padding:0 .3rem"><span>' + _text + '</span></p>';
                _ceng_con += '</div>';
                var _res_html = _ceng_bg + _ceng_con;
                $('body').append(_res_html);
                var _ceng_con_p_h = $('.' + _name + '_p').height() / (_win_w / 10);
                var _ceng_con_p_span_w = $('.' + _name + '_p').children('span').width() / (_win_w / 10);
                if(_ceng_con_p_span_w + 0.6*2>=_w){
                    var _ceng_con_p_w=_w-0.6*2;
                }else{
                    var _ceng_con_p_w=_ceng_con_p_span_w;
                }
                $('.' + _name + '_p').css({"margin-top": 0.3 + "rem"});
                $('.' + _name + '_p').parent('div').css({"height": (_ceng_con_p_h + 0.6) + "rem","width": (_ceng_con_p_w + 0.6*2) + "rem","margin-left":-(_ceng_con_p_w + 0.6*2)/2+"rem","margin-top":-(_ceng_con_p_h + 0.6)/2+"rem"});
                setTimeout(function(){
                    $('.' + _name + '_p').parent('div').prev('div').remove();
                    $('.' + _name + '_p').parent('div').remove();
                },1200);
            }else{
                var _ceng_bg = '<div style="width:100%;height:100%;position:fixed;top:0;left:0;background:#000;opacity:' + _bg_opacity + '"></div>';
                var _ceng_con = '<div style="width:' + _w + _unit + ';height:' + _h + _unit + ';margin:' + (-_h / 2) + _unit + ' 0 0 ' + (-_w / 2) + _unit + ';border-radius:' + _radius + 'px;position:fixed;top:50%;left:50%;background:#fff">';
                _ceng_con += '<div style="width:100%;height:100%;position:relative">';
                _ceng_con += '<div style="width:100%;height:' + (_h - 1) + 'rem;padding-top:1px">';
                _ceng_con += '<p class="' + _name + '_p" style="color:#666;font-size:.4rem;text-align:center;padding:0 .5rem">' + _text + '</p>';
                _ceng_con += '</div>';
                _ceng_con += '<div style="width:100%;height:1rem;position:absolute;bottom:0;left:0;border-top:1px solid #e6e6e6">';
                _ceng_con += '<a href="javascript:void(0)" class="' + _name + '_ok_btn" style="display:block;height:1rem;float:left;width:100%;text-align:center;line-height:1rem;color:#222;font-size:.38rem;margin-left:-1px;border-left:1px solid #e6e6e6">关闭</a>';
                _ceng_con += '</div>';
                _ceng_con += '</div>';
                _ceng_con += '</div>';
                var _res_html = _ceng_bg + _ceng_con;
                $('body').append(_res_html);
                var _ceng_con_p_h = $('.' + _name + '_p').height() / (_win_w / 10);
                var pingfen_top = (_h - 1 - _ceng_con_p_h) / 2;
                $('.' + _name + '_p').css({"margin-top": pingfen_top + "rem"});
                $('.' + _name + '_cancle_btn').click(function () {
                    $(this).parent('div').parent('div').parent('div').prev('div').remove();
                    $(this).parent('div').parent('div').parent('div').remove();
                    if(option.cancle){
                        option.cancle();
                    }
                });
                $('.' + _name + '_ok_btn').click(function () {
                    $(this).parent('div').parent('div').parent('div').prev('div').remove();
                    $(this).parent('div').parent('div').parent('div').remove();
                    if(option.ok){
                        option.ok();
                    }
                });
            }
        }
    });
   //时间格式化
    Date.prototype.format = function(fmt) { 
	     var o = { 
	        "M+" : this.getMonth()+1,                 //月份 
	        "d+" : this.getDate(),                    //日 
	        "h+" : this.getHours(),                   //小时 
	        "m+" : this.getMinutes(),                 //分 
	        "s+" : this.getSeconds(),                 //秒 
	        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
	        "S"  : this.getMilliseconds()             //毫秒 
	    }; 
	    if(/(y+)/.test(fmt)) {
	            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	    }
	     for(var k in o) {
	        if(new RegExp("("+ k +")").test(fmt)){
	             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	         }
	     }
	    return fmt; 
	}
 
//向PD_DATA保存记录
function countData(from){
  //测试    url: 'http://www.fuxingfuli.com/PD_Data/terminal/data',		
	//正式url:'https://data.fuxingfuli.com/terminal/data'
 	$.ajax({
        type: 'post',
        data:{"mode":"2", "userId":getUser().id,"from":from},	
        url: 'http://test1.kexinapp.com/PD_Data/terminal/data',
        dataType: "jsonp"
 	 });
}
	
		// 百度地图API功能
/*	function getAddress(){
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				sessionStorage.setItem("longitude",r.point.lng);	//经度
				sessionStorage.setItem("latitude",r.point.lat);		//纬度
		 	 	sessionStorage.setItem("province",r.address.province); 
	         	sessionStorage.setItem("city",r.address.city); 
	         	sessionStorage.setItem("hasAddress",true); 
			} else {
				alert('failed'+this.getStatus());
			}        
		},{enableHighAccuracy: true})
	}*/

/**
 * 脱敏操作方法 字符串
 * @param str(字符串)
 * @returns
 * @author ssy
 * 字符长度大于3 活小于2
 */
function unsensitive(str){
	if(str.length<3){
		str = str.charAt(0)+"*";
	}
	if(str.length>=3){
		var ruten = str.substring(3,str.length); //提取字符串下标之间的字符。
		str= str.replace(ruten,'**'); //字符串中用字符替换另外字符，或替换一个与正则表达式匹配的子串。
	}
	return str;
}
//加载福利图片
function loadwelfareImg(welfareImg){
	if(welfareImg.indexOf("welfareImg_") < 0 ){
		if(welfareImg.indexOf("http") == 0){
			return welfareImg;
		}else{
			return ctx+ welfareImg;
		}
	}else{
		var welfareImgList = JSON.parse(welfareImg);
		for(var k in welfareImgList){
			if(k.indexOf("welfareImg_")>=0){
				var img = ctx+ welfareImgList[k];
				if(welfareImgList[k].indexOf("http") == 0){
					img = welfareImgList[k];
				}
				return img;
			}
		}
	}
}
//加载福利图片
function loadPhpwelfareImg(welfareImg){
	if(welfareImg.indexOf("welfareImg_") < 0 ){
		return welfareImg;
	}else{
	 var welfareImgList = JSON.parse(welfareImg);
	 for(var k in welfareImgList){
		  if(k.indexOf("welfareImg_")>=0){
			  var img =  welfareImgList[k];
			  return img;
		  }
	}
	}
}
//改变名字（如果福利名字太长的话）
function changeName(name,length){
	 var tempName;
	 var nameLength=0;
	 if(name != null){
		 nameLength=name.length;
	 }else{
		 name = "";
	 }
	 if(nameLength>length){
		  tempName=name.substring(0,length)+"**";
	 }
	 else{
		 tempName=name;
	 }
	return tempName;
}