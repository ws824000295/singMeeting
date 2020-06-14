// 高级设置js
var articles = document.getElementById("new_detail").getElementsByTagName("article");
var minPrice ;
$(function () {
	
  //顶部基础 高级 奖励 福利 分享 设置点击
   $("#new_title").children("span").unbind().bind('click',function(){
  	changeNewTitleClass(this);
  }) 
  
  //动态加载福利企业
  selectCompanyList();

  //动态加载福利分类
  selectWelfareClass();
  
  if($('#welfareVideoPath').val()){
	  $('#welfareVideo').show();
	  $('#welfareVideo').attr('src',imgctx+$('#welfareVideoPath').val());
  }
 
});

//是否限时抢购
function changeTimeType(obj){
	var type = $(obj).val();
	if(type == 1){
		$("#limitRobSection").hide();
		$("#limitRobSection").find("input").val(null);
		
	}else{
		$("#limitRobSection").show();
	}
}

// 默认样式  切换文本和图片
$("#default_title").children("button").unbind().bind('click',function(){
   $(this).addClass("current").siblings().removeClass("current");
   var ind = $(this).attr("realVal");
   if(ind == 0){
   		$("#default_content").children("section").eq(0).addClass("current").siblings().removeClass("current");
   	}else{
   		$("#default_content").children("section").eq(1).addClass("current").siblings().removeClass("current");	
   	}
}) 


//默认样式 添加图片
function addDefaultImg(){
	var id = "img_"+new Date().getTime();
	var defaultImg = '';
		defaultImg += '<div style="position: relative;" onclick="loadDefaultImg(this)" >'
			defaultImg += '<img src="'+ctx+'/images/fuli/c1.png" id="'+id+'" style="width: 320px;">'
			defaultImg += '<i style="background-color: rgb(204, 204, 204);  right: 0px;" onclick="removeDefaultImg(this);"> X </i>'
		defaultImg += '</div>'
	$("#phone_content_children").append(defaultImg); 
	$("#default_img").attr("src",ctx+"/images/fuli/c1.png"); 
	$("#default_img").attr("fromid",id); 
 	setTimeout(function() { $('#phone_content_children').sortable(); }, 1);
	$("#phone_content").show();
	$("#phone_content_xiumi").hide();
	$("#phone_content").show();
	$("#phone_content_xiumi").hide();
}

//左侧手机 点击默认样式的图片
function loadDefaultImg(obj){
	$("#default_content").children("section").eq(1).addClass("current").siblings().removeClass("current");
 	var defaultImgId = $(obj).children('img').attr('id');
    var defaultImgUrl = $(obj).children('img').attr('src');
	$("#default_img").attr("fromid",defaultImgId);
	$("#default_img").attr("src",defaultImgUrl);
	$('.phone_content_children.ui-sortable').find('div').css('border','none');
	$(obj).css('border','1px solid red');
}

//左侧手机 删除默认样式的图片
function removeDefaultImg(obj){
	$(obj).parent("div").remove();
	$("#default_img").attr("fromid",""); 
	$("#default_img").attr("src","");
}

//默认样式 添加文本
function addDefaultText(){
	var id = "phoneInput_"+new Date().getTime();
	var defaultHtml = '';
		defaultHtml += '<div   style="height: 30px; line-height: 24px; position: relative;" onclick="loadDefaultText(this)" >'
		defaultHtml 	+= '<input class="asd" tyle="text" onkeyup="changeDefaultText(value, id)" style="background: #eee;display: block;width: 320px;height: 25px;border: 0px;outline: none;" id="'+id+'">'
		defaultHtml 	+= '<i style="background-color: rgb(204, 204, 204); top: -5px; right: 0px;" onclick="removeDefaultText(this);"> X </i>'
		defaultHtml += '</div>'
	$("#phone_content_children").append(defaultHtml); 
	$('#default_text_input').attr("fromid",id);
    setTimeout(function(){ $('#phone_content_children').sortable(); }, 1);
	$("#phone_content").show();
	$("#phone_content_xiumi").hide();
}

//左侧手机 点击默认样式的文本
function loadDefaultText(obj){
	$("#default_content").children("section").eq(0).addClass("current").siblings().removeClass("current");
	$('#default_text_input').html('');
	var inputVal = $(obj).children('input').val();
  	$('#default_text_input').val(inputVal);
  	$(obj).children('input').attr("value",inputVal);
}

//左侧手机 删除默认样式的文本
function removeDefaultText(obj){
	$(obj).parent("div").remove();
	$('#default_text_input').attr("fromid","");
	$('#default_text_input').val("");
}

//左侧手机 默认样式中 文本失去焦点改变 右侧输入框的值
function changeDefaultText(value,id) {
  $('#default_text_input').val(value);
  $('#default_text_input').attr("fromid",id);
  $('#'+id+'').attr("value",value);
}

//改变左侧 默认样式中 文本的内容
function changePhonetext(obj){
	var id = $(obj).attr("fromid");
	var content = $(obj).val();
 	$('#'+id+'').val(content);
 	$('#'+id+'').attr("value",content);
}

//添加随机ID
function random_id() {
  var id = '';
  var str = '7418520963'
  for (var i = 0; i < 8; i++) { id += str[~~(Math.random() * str.length)] }
  return id;
}

$('#phone_content>div').on('focus', function () {
  $(this).css('border', '2px dotted #F86F4D')
})


//图片上传回调
function uploadView(target,data){ 
	if(target=="picimg"){				//图片组件内回显
		$("#"+target).attr("src", imgctx + data.path_750);
		var divID = $("#picimg").attr("divID");
		$("#"+divID+"").children("img").attr("src",imgctx+data.path_750);
	}else if(target == "back_img"){		//背景组件内
		$("#back_img").attr("src",imgctx+data.path_1000);
		$(".phone_content_children").css("background-image", "url("+imgctx+data.path_1000+")");
		$(".phone_content_children").css("background-repeat", "no-repeat");
		$(".phone_content_children").css("background-size", "100% 100%");
		$("#backGround").val(data.path_1000);
	}else if(target == "shareImg"){
		$("#shareImg").attr("src",imgctx+data.path_200);
		$("input[name='shareImg']").val(data.path_200);
		$("#phone_shareImg").attr("src",imgctx+data.path_200);
	}else if(target == "welfareImg"){
		var imgLen = $("#sortable").children().length;
		if(imgLen >= 6){ alertMessage("最多加6个"); return; }
		var imgpath = ctx + data.path_500;
		var html =  "<li realUrl='"+data.path_750+"' style='background: url("+(imgctx + data.path_500)+") 100% 100% / 100% 100% no-repeat'><i class='delareImg' onclick='deleteWelfareImg(this)'></i></li>"
		$("#sortable").append(html);
		setTimeout(function(){ $('#sortable').sortable({ containment: '#sortable', cancel: '#welfareImg_list' }); },1);
		var newImgName = "welfareImg_"+new Date().getTime();
		welfareImgList[newImgName] = imgpath ; 
	}else if(target == "default_img"){
		$("#default_img").attr("src",imgctx+data.path_750);
		var phoneImgId = $("#default_img").attr("fromid");
		$('#'+phoneImgId+'').attr("src",imgctx+data.path_750);
	}
}
function uploadVideoShow(target,path){
	$('#welfareVideo').show();
	$("#"+target).attr("src", imgctx + path.videoPath);
	$("#welfareVideoPath").val(path.videoPath);
}
//删除 福利图片
function deleteWelfareImg(obj){
	$(obj).parent().remove();
}


// 切换样式
$("#details_title button").bind('click',function(){
	var type = $(this).val();
	if(type == 2){//默认样式
		$(".phone_content_children").css("width","100%");
	}else if(type == 0){ //自定义
		$(".phone_content_children").css("width","95%");
	}
	var htmlLen = $(".phone_content_children").html();
	var htmlxiuMi = $(".phone_content_xiumi_children").html();
	$(".phone_content_children").css("background","");
	if(htmlLen.indexOf("div") != -1  || htmlxiuMi.indexOf("<p>") != -1 ){
		changeModelType(type); 
	}else{
		realToChange(type);
	}
}) 


function changeModelType(type){
	var html = '';
		html += '<div class="model-alert-content">';
		html += '	<div class="model-alert-title">';
		html += '		<span>提示</span><i onclick="quxiao()" class="icon-close"></i>';
		html += '	</div>';
		html += '	<div class="wenhao-green"></div>';
		html += '	<p class="succ-text">切换新样式将会清空当前内容，是否确定？</p>';
		html += '	<div class="model-alert-btnBox">';
		html += '		<input onclick="realToChange(\'' + type + '\')" class="model-alert-btn sure" type="button" name="" value="确定"/>';
		html += '		<input onclick="quxiao()" class="model-alert-btn cancel" type="button" name="" value="取消"/>';
		html += '	</div>';
		html += '</div>';
		$('.model-alert.delate-alert').empty();
		$('.model-alert.delate-alert').append(html);
		$('.model-alert.delate-alert').fadeIn(500);
}

function realToChange(type){
		$("[name='modelType']").val(type)
		$("#phone_content_children").empty();
		$(".phone_content_xiumi_children").empty();
		
		UE.getEditor('editor').setContent("");
		if(type==1){	//秀米
			$("#details_title button").eq(2).addClass('current').siblings().removeClass("current");	
			$("#details_content div").siblings().removeClass("current");
			$("#xiumi").show();
			$("#editor").show();
		}else if(type == 0){ //老版
			$("#details_title button").eq(1).addClass('current').siblings().removeClass("current");	
			$("#details_content div").eq(1).addClass('current').siblings().removeClass("current");	
			//$('#custom_text_div').attr('class', 'current');
			$("#custom_div").children().eq(0).addClass('current').siblings().removeClass("current");
			$('#phone_content').css('overflow-y','hidden');
			$("#xiumi").hide();
			$("#editor").hide();
		}else{	//默认
			$("#details_title button").eq(0).addClass('current').siblings().removeClass("current");	
			$("#details_content div").eq(0).addClass('current').siblings().removeClass("current");	
			$('#phone_content').css('overflow-y','scroll');
			$("#xiumi").hide();
			$("#editor").hide();
		}
	$('.model-alert').fadeOut(500);
	$('.model-alert-content').remove();
}

//取消删除
function quxiao() {
	$('.model-alert').fadeOut(500);
	$('.model-alert-content').remove();
}
	
	
// 自定义样式
$("#custom_button button").click(function() {
	$(this).addClass("current").siblings().removeClass("current");	
	var index = $(this).index() -1;
	$("#custom_div div").eq(index).addClass('current assembly_ws').siblings().removeClass('current assembly_ws');
})


//添加福利企业
function toCreateCompany(){
	var url = "manage/company/main";
	$(".fl_wrap li").removeClass("sed");
	$(".fl_wrap li").eq(1).addClass("sed");
	toPage(url,null,"indexShowDiv",function(){ $(".sp-container").remove();  });
}

//添加福利分类
function toFenLei(){
	var url = "manage/welfare/main";
	$(".fl_wrap li").removeClass("sed");
	$(".fl_wrap li").eq(2).addClass("sed");
 	toPage(url,null,"indexShowDiv",function(){ $(".sp-container").remove();  });
}

//动态拼接福利企业
function selectCompanyList(){
	toAjaxJson("manage/company/selectCompanyList",{},function(data){
		if(data.success){
			var companyId = '';
			   companyId = '<option value="" >请选择福利所属企业</option>';
	           $(data.data.companyInfos).each(function(i){
	        	   companyId += '<option value="'+this.id+'" >'+this.companyName+'</option>';
	           });
	         $("#companyId").html(companyId);
	      	if(typeof(welfareEntity)!="undefined"){
	         	$("#companyId").val(welfareEntity.companyId)
	         }
		 }else{
		 	alertMessage("修改状态失败，请稍后再试！",true);
		 }
	});
}

//查询福利分类
function selectWelfareClass(){
	toAjaxJson("manage/classification/selectWelfareClassification",{},function(data){
		if(data.success){
			var welfareType = '';
			welfareType = '<option value="" >请选择福利分类</option>';
               $(data.welfareClassifications).each(function(i){
            	   welfareType += '<option value="'+this.id+'" >'+this.name+'</option>';
               });
             $("#welfareType").html(welfareType);
             if(typeof(welfareEntity) != "undefined"){
             	$("#welfareType").val(welfareEntity.welfareType);
             }
		 }else{
		 	alertMessage("修改状态失败，请稍后再试！",true);
		 }
	});
}


//省份查询
function queryProvinces(){
	var json={};
	toAjaxJson("manage/dict/queryProvinces",json,function(data){
		if(data.success){
               var province = '';
               $(data.data.provinceList).each(function(i){
            	   province += '<option value="'+this.id+'" >'+this.provName+'</option>';
               });
             $("#provCode").append(province);
              if(typeof(welfareEntity)!="undefined"){
	             $("#provCode").val(welfareEntity.provCode);
	              $("#provCode").trigger("change");
             }
           }else{
        	  alertMessage("获取省份异常"); 
           }
	});
}
	
//根据选择的省编码查询市区信息
$('#provCode').change(function(){
   var sel_value = $(this).val(); //得到当前选中的值
   var provName = $(this).find("option:selected").text();
   if(sel_value==""){
	  $("#img_area").html("暂无");
   }else{
	  $("#img_area").html("此福利需为"+provName+"<span id='img_cityName'></span>地区网络IP地址");  
   }
   $("#provName").val(provName);
   var json={"provCode":sel_value};
   toAjaxJson("manage/dict/queryCitys",json,function(data){
		if(data.success){
               var city = '';
               city = '<option value="" >不限制</option>';
               $(data.data.cityList).each(function(i){
            	   city += '<option value="'+this.id+'" >'+this.cityName+'</option>';
               });
             $("#cityCode").html(city);
              if(typeof(welfareEntity)!="undefined"){
	             $("#cityCode").val(welfareEntity.cityCode);
	              $("#cityCode").trigger("change");
             }
           }else{
           	 alertMessage("获取城市异常"); 
       }
	});
});
	
//根据城市
$('#cityCode').change(function(){
   var cityName = $(this).find("option:selected").text();
   $("#cityName").val(cityName);
   if(cityName == "不限制"){
   		var provName=  $("#provName").val();
	    $("#img_area").html("此福利需为"+provName+"<span id='img_cityName'></span>地区网络IP地址"); 
   }else{
   	   $("#img_cityName").html(cityName); 
   }
});

//福币奖励
$("input[name='shareLevel']").bind("click",function(){
	var ind = $(this).val();
	if(ind == 0){
		$("#coinsLevel_one").show();
		$("#coinsLevel_two").hide();
	}else{
		$("#coinsLevel_one").show();
		$("#coinsLevel_two").show();		
	}
})

function changeNewTitleClass(obj){
	 var ind = $(obj).index();
	 var xiuMi =  $("#details_title button").eq(2).hasClass("current");
 	 $("#new_title span").eq(ind).addClass('current').siblings().removeClass('current');
     $("#new_detail article").eq(ind).addClass('current').siblings().removeClass('current');
     if(ind == 3){
     	if(xiuMi){
	     	$("#phone_content").hide();
	     	$("#phone_content_xiumi").show();
     	}else{
	     	$("#phone_content").show();
	     	$("#phone_content_xiumi").hide();
     	}
     	$("#share_set").hide();
     }else{
     	$("#share_set").show();
     	$("#phone_content").hide();
     	$("#phone_content_xiumi").hide();
     }

     showByBillType();
     
     $("#new_title span").eq(ind).css("backgroundImage","url('"+ctx+"/images/fuli/newbackstyle/mouse_click_" + ind + ".png')").siblings().css('backgroundImage',"");
     $("#new_title span").eq(ind).css("backgroundSize","100% 100%").siblings().css('backgroundSize');
     $("#new_title span").eq(ind).css("color","white").siblings().css('color','#BCBCBC');
     for(var i=0; i<5; i++){
     	$("#new_title span").eq(i).find("i").css("backgroundImage","url('"+ctx+"/images/fuli/newbackstyle/blank_gray_" + i + ".png')");
     }
  	 $("#new_title span").eq(ind).find("i").css("backgroundImage","url('"+ctx+"/images/fuli/newbackstyle/blank_" + ind + ".png')");
}

function showByBillType(){
	var billingType = $("[name='billingType']").val();
	if(billingType == 1){
	 	$(".cardSection").hide();
	 	$(".payParam").hide();
		$("#linkUrl").hide();
		$("#exchangeCoins_p").hide();
		$("#xuniPQ").hide();
	 }else if(billingType == 4){
	 	$(".cardSection").show();
	 	$("#exchangeCoins_p").show();
	 	$(".payParam").show();
	 	$("#linkUrl").hide();
		$(".xianZhi").show();
		$("#xuniPQ").hide();
	 	var cardtype = 	$("input[name='cardType']:checked").val();
	 	if(cardtype == "0"){ $(".xianZhi").show(); }else{ $(".xianZhi").hide(); }
	}else if(billingType == 5){
	 	$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").hide();
	}else if(billingType == 6){
		$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").show();
	}else if(billingType == 7){
		$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").show();
	}
	
	var singleDay = 	$("input[name='singleDay']:checked").val();
	$("[name='singleDay'][value='"+singleDay+"']").click();
}


//点击奖励限制
$("input[name='clickAddCoinsType']").bind("click",function(){
	 var ind = $(this).val();
	 $("#clickAddCoinsTypeDec").show();
	if(ind == 0){
		  $("#clickAddCoinsTypeDec").hide();
	}else if(ind == 1){
		  $("#clickAddCoinsNumDes").html("每日限领:");	
		  $("#clickAddCoinsNum").val(1);
	}else if(ind == 2){
		  $("#clickAddCoinsNumDes").html("每人限领:");		
		  $("#clickAddCoinsNum").val(1);
	}else if(ind == 3){
		 $("#clickAddCoinsNumDes").html("每人每日限领:");		
		  $("#clickAddCoinsNum").val(1);
	} 
});


function checkCoins(obj){
	var coins = $(obj).val();
	if(coins <= 0){
		$(obj).val(1);
	}
}



//获取phone_content内最后的拼接元素的topzhi加5
function getTopVlue(){
	var len = $(".phone_content_children").children().length;
	var topVal = 0;
	var heightVal = 0;
	if(len == 0){
		topVal = 5;
		heightVal = 5;
	}else{
		topVal = $(".phone_content_children").children(":last-child").css("top");
		heightVal = $(".phone_content_children").children(":last-child").css("height");
		topVal = Number(topVal.split("p")[0]) + Number(5)+ Number(heightVal.split("p")[0])  
	}
	return topVal;
}

//显示删除图标
 function showTopdiv(obj){
	 $(obj).find(".topDiv").show();
}  
//隐藏删除图标
 function hideTopdiv(obj){
	 $(obj).find(".topDiv").hide();
} 


//图片添加到模块
function imgpic() {
	var topVal = getTopVlue();
    var editHeigh =  $(".phone_content_children").css("height").split("px")[0];
	var res = Number(topVal) + Number(180);
	/*if(res > 486  && res > editHeigh){
		  var warpHeight =  Number(topVal) + Number(180);
		  $(".phone_content_children").css("height",warpHeight) 
		}*/
	var timestamp =new Date().getTime();
	var id = "divImg-"+timestamp;
 	var imgpic = "<div style='width:180px;height:180px;top:"+topVal+"px;' id='"+id+"' onmouseover='showTopdiv(this);' onmouseleave='hideTopdiv(this);'  >"+
					 "<div class='topDiv' onclick='removeObj(this,\"img\");' style='background: url("+ctx+"/images/fuli/dele-2.png) 100% 100% / 100% 100% no-repeat; display:none;z-index:9;width: 20px; height: 20px; position: absolute; right: 0px; top: 0px; '></div>"+
					 "<img style='width:100%;height:100%'   onclick='loadImgFun(this,\"img\",\""+id+"\");' src='"+ctx+"/images/fuli/c1.png'/>"+
				 "</div>";
	$(".phone_content_children").append(imgpic);

	bindDrag($(".phone_content_children>div"),"img");//拖动
	bindHover($(".phone_content_children>div"));//滑过有框
 	bindResizable($(".phone_content_children>div"));//改变大小
 	
 	$("#custom_div div:eq(1)").attr("divID",id);
	$("#imgLink").val("");
	$("input[name='imgOnclickType'][value='0']").attr("checked",true);
	bigColumn[id+"_imgOnclickType"] = "0";
	bigColumn[id+"_imgLink"] = "";
	var obj = $("#"+id+"").find("img");
	loadImgFun(obj,"img",id);
	$("#phone_content").show();
	$("#phone_content_xiumi").hide();
}

function loadImgFun(obj,type,divID){
	//切换右侧内容区显示
	$("#custom_div div:eq(1)").attr("divID",divID);
	$("#custom_button button").eq(1).addClass("current").siblings().removeClass("current");
	$("#custom_div div:eq(1)").addClass("current assembly_ws").siblings().removeClass("current assembly_ws");

	//回显图片
	$("#picimg").attr("src",$(obj).attr("src"));
	$("#picimg").attr("divID",divID);
	
	//切换点击事件
	var onclikType= bigColumn[divID+"_imgOnclickType"];
	if(typeof(onclikType) != "undefined"){
		var link = bigColumn[divID+"_imgLink"];
		$("#imgLink").val(link);
		$("input[name='imgOnclickType'][value='"+onclikType+"']").attr("checked",true);
	}
	var posOneImg = document.getElementById("posOneImg");
	var bgOneImg = document.getElementById("bgOneImg");
	var bfbOneImg = document.getElementById("bfbOneImg");
	var opacityBoxOneImg = document.getElementById("opacityBoxOneImg");
	AlphaInitFunTouMingDu(posOneImg, bgOneImg, bfbOneImg, opacityBoxOneImg,obj); 					//当前图片对象的 透明度滑动事件
	commonLoadFun(posOneImg,bgOneImg,bfbOneImg,divID+"_opacity");									//回显当前对象的透明度
	
	var posTwoImg = document.getElementById("posTwoImg");
	var bgTwoImg = document.getElementById("bgTwoImg");
	var bfbTwoImg = document.getElementById("bfbTwoImg");
	var opacityBoxTwoImg = document.getElementById("opacityBoxTwoImg");
	AlphaInitFunYuanjiao(posTwoImg, bgTwoImg, bfbTwoImg, opacityBoxTwoImg,obj); 					//当前图片对象的 圆角滑动事件
	commonLoadFun(posTwoImg,bgTwoImg,bfbTwoImg,divID+"_border-radius");								//回显当前对象的圆角
		    
	var posThreeImg = document.getElementById("posThreeImg");
	var bgThreeImg = document.getElementById("bgThreeImg");
	var bfbThreeImg = document.getElementById("bfbThreeImg");
	var opacityBoxThreeImg = document.getElementById("opacityBoxThreeImg");
	AlphaInitFunRotate(posThreeImg, bgThreeImg, bfbThreeImg, opacityBoxThreeImg,obj); 				//当前图片对象的旋转滑动事件
	commonLoadFun(posThreeImg,bgThreeImg,bfbThreeImg,divID+"_transform");							//回显当前对象的旋转
}


//图片组件内切换点击事件
$("input[name='imgOnclickType']").bind('click',function(){	 
	var index = $(this).index();
	var name = "";
	if(index =="0"){
		name = "链接地址：";
		$("#imgLink").attr("placeholder","http://");
	}else if(index =="2"){
		name = "拨打电话：";
		$("#imgLink").attr("placeholder","");
	}else if(index =="4"){
		name = "发送短信：";
		$("#imgLink").attr("placeholder","");
	}else if(index =="6"){
		name = "打开淘宝APP：";
		$("#imgLink").attr("placeholder","");
	}else if(index =="8"){
		name ="播放视频：";
		$("#imgLink").attr("placeholder","");
	}
	$("#dj_tabbox_img").children().eq(0).text(name);
	var divID = $(this).closest("div").attr("divID");
	if(divID == ""){
		alert("请先选择操作对象");
	}else{
		bigColumn[divID+"_imgOnclickType"]= $(this).val();
		bigColumn[divID+"_imgLink"] ="";
		$("#imgLink").val("");
	}
});	


//删除当前对象
function removeObj(obj,type){
	comp_confirm('您确定删除吗?',function(){
		event.stopPropagation();
		var removeDiv = $(obj).parent().attr("id");
		 for(var k in bigColumn){						
			  if( k.indexOf(removeDiv)>=0 ){
				  delete bigColumn[k];
			  }
		  }
	 	$(obj).parent().remove();
		var bigTop = 0;
		$(".phone_content_children").children().each(function(){
			var t =  $(this).css("top").split("px")[0];
			if( Number(t) >= Number(bigTop) ){
				bigTop = t;
			}							 
		})
		var len  = $(".phone_content_children").children().length;
		if(len > 0){
			var lastTop = bigTop;
			var edithight;
			if(type == "img"){
				edithight = Number(lastTop) + Number(180);
			}else if(type == "textarea"){
				edithight = Number(lastTop) + Number(200);
			}else if(type == "form"){
				edithight = Number(lastTop) + Number(203);
			}else if(type == "button"){
				edithight = Number(lastTop) + Number(40);
			}
			if(edithight > 486){
				$(".phone_content_children").css("height",edithight);
			}else{
				$(".phone_content_children").css("height",486);
			}
		}
	});
}

//删除图片 
function deleteImg(obj,picimg){
	if(picimg=="picimg"){
		$("#"+picimg+"").attr("src",'');
		var divID =$("#"+picimg+"").attr("divID");
		$("#"+divID+"").children("img").attr("src",'');
	}else if(picimg=="back_img"){
		$("#back_img").attr("src","");
		$(".phone_content_children").css("background-image","");
		var color = $("#backgroundColor").val();
		if(color == ""){
			$(".phone_content_children").css("background-color","#ffffff");
			$("#backGround").val("#ffffff");
		}else{
			$(".phone_content_children").css("background-color",color);
			$("#backGround").val(color);
		}
	}else if(picimg =="welfareImg"){
		$("#welfareImg").attr("src","");
		$("input[name='welfareImg']").val("");
	}
}


function changeLink(obj,type){
	var divID = $(obj).closest(".assembly_ws").attr("divID");
	if(typeof(divID)!="undefined"){
		var linkContent = $(obj).val();
		bigColumn[divID+"_"+type] = linkContent;
	}
} 

//自定义文本添加组件
function fonttet(){
	var topVal = getTopVlue();
	var res = Number(topVal) + Number(200);
    var editHeigh =  $(".phone_content_children").css("height").split("px")[0];
	/*if(res > 486 && res > editHeigh){
		  var warpHeight =  Number(topVal) + Number(200);
		  $(".phone_content_children").css("height",warpHeight) 
	}*/
	var timestamp =new Date().getTime();
	var id = "divText-"+timestamp;
	var textare = "<div style='position:absolute;width:190px;height:200px;top:"+topVal+"px;' id='"+id+"'  onmouseover='showTopdiv(this);' onmouseleave='hideTopdiv(this);' onclick='loadDataText(this,\""+id+"\")' >";
		textare+= "<textarea style='width:100%;height:95%;resize:none;' onblur='changeTextarea(this,\""+id+"\")'></textarea>";
		textare+= "<div class='topDiv' onclick='removeObj(this,\"textarea\");'  style='background: url("+ctx+"/images/fuli/dele-2.png) 100% 100% / 100% 100% no-repeat; width: 20px; height: 20px; position: absolute; right: 0px; top: 0px; display: none;'></div>";
		textare+= "<div>";
		
	$(".phone_content_children").append(textare);

	bindDrag($(".phone_content_children>div"),"textarea");//拖动
	bindHover($(".phone_content_children>div"));		//滑过有框
	bindResizable($(".phone_content_children>div"));	//改变大小
	
	$("#custom_div div:eq(0)").attr("divID",id);
	$("#textLink").val("");
	$("input[name='textOnclickType'][value='0']").attr("checked",true);
	bigColumn[id+"_textOnclickType"] = "0";
	bigColumn[id+"_textLink"] = "";
	
	var obj = $("#"+id+"");
	loadDataText(obj,id);
	$("#phone_content").show();
	$("#phone_content_xiumi").hide();
}

function changeTextarea(obj,divID){
	 bigColumn[divID+"_textarea"] = $(obj).val();	
	 $(obj).html($(obj).val())
}


function loadDataText(obj,divID){
	//切换左侧内容区显示
	$("#custom_div div:eq(0)").attr("divID",divID);
	$("#custom_button button").eq(0).addClass("current").siblings().removeClass("current");
	$("#custom_div div:eq(0)").addClass("current assembly_ws").siblings().removeClass("current assembly_ws");
	//切换字体样式
	var fontStyle = $(obj).find("textarea").css("font-family");
	$('#classFont li').removeClass("sed");
	$('#classFont').find('li[fontStyle="'+ fontStyle +'"]').addClass("sed");			
	
	//切换点击事件
	var onclikType= bigColumn[divID+"_textOnclickType"];
	if(typeof(onclikType) != "undefined"){
		var link = bigColumn[divID+"_textLink"];
		$("#textLink").val(link);
		$("input[name='textOnclickType'][value='"+onclikType+"']").attr("checked",true);
	}
	
	//文本  透明度初始化
	var posOneText = document.getElementById("posOneText");
	var bgOneText = document.getElementById("bgOneText");
	var bfbOneText = document.getElementById("bfbOneText");
	var opacityBoxOneText = document.getElementById("opacityBoxOneText");
	AlphaInitFunTouMingDuByText(posOneText, bgOneText, bfbOneText, opacityBoxOneText,obj);					//当前文本对象的 透明度滑动事件
 	commonLoadFun(posOneText,bgOneText,bfbOneText,divID+"_opacity");										//回显当前对象的透明度
	
	var posTwoText = document.getElementById("posTwoText");
	var bgTwoText = document.getElementById("bgTwoText");
	var bfbTwoText = document.getElementById("bfbTwoText");
	var opacityBoxTwoText = document.getElementById("opacityBoxTwoText");
	AlphaInitFunYuanjiaoByText(posTwoText, bgTwoText, bfbTwoText, opacityBoxTwoText,obj);						//当前文本对象的 圆角滑动事件
	commonLoadFun(posTwoText,bgTwoText,bfbTwoText,divID+"_border-radius");										//回显当前对象的圆角
	   
	var posThreeText = document.getElementById("posThreeText");
	var bgThreeText = document.getElementById("bgThreeText");
	var bfbThreeText = document.getElementById("bfbThreeText");
	var opacityBoxThreeText = document.getElementById("opacityBoxThreeText");
	AlphaInitFunRanteByText(posThreeText, bgThreeText, bfbThreeText, opacityBoxThreeText,obj);						//当前文本对象的 旋转滑动事件
	commonLoadFun(posThreeText,bgThreeText,bfbThreeText,divID+"_transform");										//回显当前对象的 旋转
}

//文本组件内切换点击事件
$("input[name='textOnclickType']").bind('click',function(){	 
	var index = $(this).index();
	var name = "";
	if(index =="0"){
		name = "链接地址：";
		$("#textLink").attr("placeholder","http://");
	}else if(index =="2"){
		name = "拨打电话：";
		$("#textLink").attr("placeholder","");
	}else if(index =="4"){
		name = "发送短信：";
		$("#textLink").attr("placeholder","");
	}else if(index =="6"){
		name = "打开淘宝APP：";
		$("#textLink").attr("placeholder","");
	}else if(index =="8"){
		name ="播放视频：";
		$("#textLink").attr("placeholder","");
	}
	$("#dj_tabbox_text").children().eq(0).text(name);
	var divID = $(this).closest("div").attr("divID");
	if(divID == ""){
		alert("请先选择操作对象");
	}else{
		bigColumn[divID+"_textOnclickType"]= $(this).val();
		bigColumn[divID+"_textLink"] ="";
		$("#textLink").val("");
	}
});	


//切换字体样式
$("#classFont").find("li").bind("click",function(){
	var fontStyle = $(this).attr("fontStyle");
	$(this).addClass("sed").siblings().removeClass("sed");
	var id = $(".assembly_ws").attr("divID");
	$("#"+id+"").find("textarea").css("font-family",fontStyle)
});



//背景添加添加到模块
function bjpic() {
	var color = $("#backgroundColor").val();
	var img = $("#back_img").attr("src");
	if(color == "" && img ==""){
		$(".phone_content_children").css("background-image", "url("+ctx+"/images/fuli/timg.jpg)");
		$(".phone_content_children").css("background-repeat", "no-repeat");
		$(".phone_content_children").css("background-size", "100% 100%");
		$("#back_img").attr("src",ctx+"/images/fuli/timg.jpg");
		$("#backGround").val("/images/fuli/timg.jpg");
	}
	
	var posBg = document.getElementById("posBg"); 
	var bgBg = document.getElementById("bgBg");
	var bfbBg = document.getElementById("bfbBg");
	var opacityBoxBg = document.getElementById("opacityBoxBg");
	AlphaInitFunBeijing(posBg, bgBg, bfbBg, opacityBoxBg, $(".phone_content_children")); 					//当前图片对象的 透明度滑动事件
	commonLoadFun(posBg,bgBg,bfbBg,"background_opacity");						//回显当前对象的透明度
	$("#phone_content").show();
	$("#phone_content_xiumi").hide();
}

//更换背景色
function change_bg_color(e) {
  $('.phone_content_children').css('backgroundColor', e);
}



//自定义按钮 
function addbtn(){
	var topVal = getTopVlue();
	var res = Number(topVal) + Number(40);
 	var editHeigh =  $(".phone_content_children").css("height").split("px")[0];
	var timestamp = new Date().getTime();
	var id = "divButton-"+timestamp;
	var addbtn = "<div style='width:140px;height:40px ;top:"+topVal+"px;' onmouseover='showTopdiv(this);' onmouseleave='hideTopdiv(this);' id='"+id+"' onclick='loadButton(this,\""+id+"\")' >";
		addbtn +=  "<div class='topDiv' onclick='removeObj(this,\"button\");' style='background: url("+ctx+"/images/fuli/dele-2.png) 100% 100% / 100% 100% no-repeat; width: 20px; height: 20px; z-index:9;position: absolute; right: 0px; top: 0px; display: none;'></div>"
		addbtn += "<span style='display:block;background:red;border-radius:10px;width:100%;height:100%;text-align: center;display: flex;justify-content: center;flex-direction: column;'>我是按钮</span>";
	 	addbtn +="</div>";
	$(".phone_content_children").append(addbtn);
 
	bindDrag($(".phone_content_children>div"),"button");//拖动
 	bindHover($(".phone_content_children>div"));//滑过有框
 	bindResizable($(".phone_content_children>div"));//改变大小
 	
 	$("#custom_div div:eq(3)").attr("divID",id);
	$("#buttonLink").val("");
	$("input[name='buttonOnclickType'][value='0']").attr("checked",true);
	bigColumn[id+"_buttonOnclickType"] = "0";
	bigColumn[id+"_buttonLink"] = "";
	
	var obj = $("#"+id+"");
	loadButton(obj,id);
$("#phone_content").show();
	$("#phone_content_xiumi").hide();
}

function loadButton(obj,divID){
	var buttonName = $(obj).find("span").text();
	$("#buttonName").val(buttonName);
	
	 var color = $(obj).find("span").css("background");
	/*comp_spectrum($("#buttonColor"),{	
	change:function(color){
		$("#buttonColor").attr("value",color.toHexString());
		var buttonID = $("#buttonColor").closest(".assembly_c").attr("divID");//取对应左侧手机框内的buttonid
		$("#"+buttonID+"").find("span").css("background",color.toHexString());
    },
    color:color
	});	*/
	
	//切换点击事件
	var onclikType= bigColumn[divID+"_buttonOnclickType"];
	if(typeof(onclikType) != "undefined"){
		var link = bigColumn[divID+"_buttonLink"];
		$("#buttonLink").val(link);
		$("input[name='buttonOnclickType'][value='"+onclikType+"']").attr("checked",true);
	}
	
	//切换右侧内容区显示
	$("#custom_div div:eq(3)").attr("divID",divID);
	$("#custom_button button").eq(3).addClass("current").siblings().removeClass("current");
	$("#custom_div div:eq(3)").addClass("current assembly_ws").siblings().removeClass("current assembly_ws");
	
 
	//按钮组件透明度初始化
	var posOneButton = document.getElementById("posOneButton");
	var bgOneButton = document.getElementById("bgOneButton");
	var bfbOneButton = document.getElementById("bfbOneButton");
	var opacityBoxOneButton = document.getElementById("opacityBoxOneButton");
	AlphaInitFunTouMingDuByButton(posOneButton, bgOneButton, bfbOneButton, opacityBoxOneButton,obj);					//当前文本对象的 透明度滑动事件
 	commonLoadFun(posOneButton,bgOneButton,bfbOneButton,divID+"_opacity");										//回显当前对象的透明度
	
	var posTwoButton = document.getElementById("posTwoButton");
	var bgTwoButton = document.getElementById("bgTwoButton");
	var bfbTwoButton = document.getElementById("bfbTwoButton");
	var opacityBoxTwoButton = document.getElementById("opacityBoxTwoButton");
	AlphaInitFunYuanjiaoByButton(posTwoButton, bgTwoButton, bfbTwoButton, opacityBoxTwoButton,obj);						//当前文本对象的 圆角滑动事件
	commonLoadFun(posTwoButton,bgTwoButton,bfbTwoButton,divID+"_border-radius");										//回显当前对象的圆角
	
	var posThreeButton = document.getElementById("posThreeButton");
	var bgThreeButton = document.getElementById("bgThreeButton");
	var bfbThreeButton = document.getElementById("bfbThreeButton");
	var opacityBoxThreeButton = document.getElementById("opacityBoxThreeButton");
	AlphaInitFunRanteByButton(posThreeButton, bgThreeButton, bfbThreeButton, opacityBoxThreeButton,obj);						//当前文本对象的 旋转滑动事件
	commonLoadFun(posThreeButton,bgThreeButton,bfbThreeButton,divID+"_transform");										//回显当前对象的 旋转
}
	
	
	
//图片组件内切换点击事件
$("input[name='buttonOnclickType']").bind('click',function(){	 
	var index = $(this).index();
	var name = "";
	if(index =="0"){
		name = "链接地址：";
		$("#buttonLink").attr("placeholder","http://");
	}else if(index =="2"){
		name = "拨打电话：";
		$("#buttonLink").attr("placeholder","");
	}else if(index =="4"){
		name = "发送短信：";
		$("#buttonLink").attr("placeholder","");
	}else if(index =="6"){
		name = "打开淘宝APP：";
		$("#buttonLink").attr("placeholder","");
	}else if(index =="8"){
		name ="播放视频：";
		$("#buttonLink").attr("placeholder","");
	}
	$("#dj_tabbox_button").children().eq(0).text(name);
	var divID = $(this).closest("div").attr("divID");
	if(divID == ""){
		alert("请先选择操作对象");
	}else{
		bigColumn[divID+"_buttonOnclickType"]= $(this).val();
		bigColumn[divID+"_buttonLink"] = "";
		$("#buttonLink").val("");
	}
});	


//动态改变 自定义button内容	
function changeButtonName(obj){
	var buttonID = $(obj).closest(".assembly_ws").attr("divID");
	var name = $(obj).val();
	$("#"+buttonID+"").find("span").text(name);
}

// 更换按钮颜色
function change_btn_color(e) {
  $('#').css('backgroundColor', e);
}

//微信分享标题和微信分享描述
function changeShare(obj,idName){
	if(idName =='shareTitle'){
		$(".nameIdLinkage").text($(obj).val()); 
	}else{
		$(".share_content").children('p').text($(obj).val());
	}
}

//返回按钮
function toMain(){
	var url = "manage/welfare/main";
	$(".fl_wrap li").removeClass("sed");
	$(".fl_wrap li").eq(2).addClass("sed");
	toPage(url,null,"indexShowDiv",function(){ $(".sp-container").remove();  });
}

//选择卡券类型
$("input[name='cardType']").bind('click',function(){
	var cardType =  $(this).val();
	if(cardType == 0){	//微信
		$(".xianZhi").show();
		queryCardListByWeixin();
	}else{
	   $(".xianZhi").hide();
	   $("#singleDay_input").val(0);
	   $("#singlePeople_input").val(0);
	   queryCardListByEcard();
	}
});

//微信卡卷加载
function queryCardListByWeixin(){
	$("#cardList").empty();
	toAjaxJson("manage/coupon/queryListCard",{},function(data){
		if(data.list.length  > 0){
			var card = '<option value="" >不使用</option>';
               $(data.list).each(function(i){
            	   card += '<option value="'+this.cardId+'" >'+this.title+'</option>';
               });
		       $("#cardList").append(card);
		        if(typeof(welfareEntity)!="undefined"){
	             	$("#cardList").val(welfareEntity.cardId);
	             }
		 } 
	});
}

//异步加载 一卡易卡券
function queryCardListByEcard(){
	$("#cardList").empty();
	var companyId =$("#companyId option:checked").val();
	toAjaxJson("manage/welfare/queryCardListByEcard",{"companyId":companyId},function(data){
		if(data.cardList.length  > 0){
			var card = '<option value="" >不使用</option>';
               $(data.cardList).each(function(i){
            	   card += '<option value="'+this.Guid+'" >'+this.Title+'</option>';
               });
		       $("#cardList").append(card);
	        if(typeof(welfareEntity) != "undefined"){
             	$("#cardList").val(welfareEntity.cardId)
             }
		 } 
	});
}

//百度地图
function searchByStationName() {
	var address = $("#text_").val();
	if(address == ""){
		alertMessage("详细地址不能为空");
		return;
	}  
    map.clearOverlays();//清空原来的标注
    var keyword = document.getElementById("text_").value;
    localSearch.setSearchCompleteCallback(function (searchResult) {
        var poi = searchResult.getPoi(0);
        document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat;
        map.centerAndZoom(poi.point, 13);
        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
        map.addOverlay(marker);
        var content = document.getElementById("text_").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
        var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
        marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
    });
    localSearch.search(keyword);
} 


//发布=1和保存=0
function putAndsave(type){
	
	//经纬度校验(如果为空再次获取)
	if(document.getElementById("result_").value == ""){
		map.clearOverlays();//清空原来的标注
		var keyword = document.getElementById("text_").value;
		localSearch.setSearchCompleteCallback(function (searchResult) {
			var poi = searchResult.getPoi(0);
			document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat;
			map.centerAndZoom(poi.point, 13);
			var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
			map.addOverlay(marker);
			var content = document.getElementById("text_").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
			var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
			marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
		});
		localSearch.search(keyword);
	}

 	var modelType = $("input[name='modelType']").val();
	var fuliHeight = $("#phone_content_children").css("height");
     
	if(fuliHeight == "0px"  && modelType != 1){
		$("#share_set").hide();
		$("#phone_content").show();
		fuliHeight = $("#phone_content_children").css("height");
    	$("[name='fuliHeight']").val(fuliHeight.split("px")[0]);
		$("#share_set").show();
    	$("#phone_content").hide();
	}else if(fuliHeight != "0px"  && modelType != 1){
    	$("[name='fuliHeight']").val(fuliHeight.split("px")[0]);
	}
 
	var ifchoose=($(".select-city:checked").val());
	//四级级联地址
	if(ifchoose!="0"){
		 var address="";
		 address=address+$("#provice option:selected").val()+"-";
		 address=address+$("#city option:selected").val()+"-";
		 address=address+$("#area option:selected").val()+"-";
		 address=address+$("#town option:selected").val();
		$("#region").val(address);
	 }else{
		$("#region").val("全国");
	 }

	if(modelType == "0" || modelType == "2"){
		var detailHtml= $(".phone_content_children").html();
		$("input[name='fuliDetailHtml']").val(detailHtml);
	}else if(modelType == "1"){
		var detailHtml  = UE.getEditor('editor').getContent();
		if(detailHtml.indexOf("http://www.mpbox.cn/index.php/ArticleIndex/pic2") < 0){
			var reg = new RegExp("http://statics.xiumi.us","g");//g,表示全部替换。				
			detailHtml = detailHtml.replace(reg,"http://www.mpbox.cn/index.php/ArticleIndex/pic2?url=http://statics.xiumi.us");//http://www.mpbox.cn/index.php/ArticleIndex/pic2?url 调用的宋佳的一个方法
		}
		$("input[name='fuliDetailHtmlNew']").val(detailHtml);
	}

 	$("input[name='bigColumn']").val(JSON.stringify(bigColumn));

 	welfareImgList = {};
 	var len = $("#sortable").children().length;
 	for(var y=0; y <len ; y++){
 		var img = $("#sortable").children().eq(y).attr("realUrl");
 		welfareImgList["welfareImg_"+y] = img
 	}
 	var welfareImg = JSON.stringify(welfareImgList);
 	$("input[name='welfareImg']").val(welfareImg);

 	var welfareVirtualNum = $("#welfareVirtualNum").val();
 	if(welfareVirtualNum==""){ alertMessage("高级设置-虚拟数量不能为空");   return; }
 	
	if($("#companyId  option:selected").val()==""){alertMessage("请选择-基础设置-福利企业");return;}
	if($("#welfareType  option:selected").val()==""){alertMessage("请选择-基础设置-福利分类");return;}
	if($("#welfareName").val().trim()==""){alertMessage("高级设置-福利名称为必填项");return;}
	
	/*if($("#startTime").val() == ""){alertMessage("高级设置-福利开始时间为必填项");return;}
	if($("#endTime").val() == ""){alertMessage("高级设置-福利结束时间为必填项");return;}*/
	
	if($("#welfareNum").val()==""){alertMessage("高级设置-库存为必填项");return;}
	
	var limitRob =  $("[name='limitRob']").val();
	if(limitRob == 0){
		if($("#startTime").val()==""){alertMessage("高级设置-福利开始时间为必填项");return;}
		if($("#endTime").val()==""){alertMessage("高级设置-福利结束时间为必填项");return;}
	}
	
 	
 	var companyId = $("#companyId  option:selected").val();
	if(companyId==""){alertMessage("请选择-基础设置-福利企业");return;}
	if($("#text_").val()==""){alertMessage("基础设置-详细地址不能为空");return;}

	var bilitype =  $("input[name='billingType']").val();
	if(bilitype == 5 || bilitype == 6 || bilitype == 7 ){ 
		var paymoney = $("#paymoney").val()
		var billingName = $("#billingName").val();
		var price_input = $("#price_input").val();
		var price = $("#price_input").val();
		 
		if(price == "" ){ alertMessage("高级设置-划线价格不能为空");   return; }
		if(paymoney  <= 0 ){ alertMessage("高级设置-支付金额不能小于0");   return; }
		if(price_input  < 0 ){ alertMessage("高级设置-划线价不能为0");   return; }
		if(billingName  == "" ){ alertMessage("高级设置-商品名称不能为空"); return; }
	} else if(bilitype == 8 ){
		var paymoney = $("#paymoney").val();
		if(minPrice < paymoney){
			alertMessage("支付金额不能小于"+minPrice);   return; 
		}
		
		var billingName = $("#billingName").val();
		var price_input = $("#price_input").val();
		var price = $("#price_input").val();
		if(price == "" ){ alertMessage("高级设置-划线价格不能为空");   return; }
		if(paymoney <= 0 ){ alertMessage("高级设置-支付金额不能小于0");   return; }
		if(price_input  < 0 ){ alertMessage("高级设置-划线价不能为0");   return; }
		if(billingName  == "" ){ alertMessage("高级设置-商品名称不能为空"); return; }
		
		var scenicId = $("#yuDingSelect  option:selected").val()
		$("[name='scenicId']").val(scenicId);
	    
	    var ticketId = $("#ticketIdSelect  option:selected").val();
	    if(ticketId  == "" ){ alertMessage("请选择门票"); return; }
	   
	    var uupid = $("#ticketIdSelect  option:selected").attr("uupid");
		$("[name='uupid']").val(uupid);
		
	   	var uuaid = $("#ticketIdSelect  option:selected").attr("uuaid");
		$("[name='uuaid']").val(uuaid);
		
	}
	
	if($("#singleDay_input").val() == ""){ $("#singleDay_input").val(0); }
	if($("#singlePeople_input").val() == ""){ $("#singlePeople_input").val(0); }
	if($("#billingBasis").val() == ""){ alertMessage("高级设置-广告费用为必填项目"); return; }
    if($("#welfareRewardCoin").val()==""){ alertMessage("高级设置-福利奖励为必填项"); return; }	 	
    
	toAjaxForm("manage/welfare/createWelfare?type="+type,"welfare",function(data){
		if(data.result){
        		$("#welfareId").val(data.welfareId);
        		$("#welfareStatus").val(data.welfareStatus);
        		type == "0" ? alertMessage("保存成功") : alertMessage("发布成功");
        		
        		if(type =="1"){ toFenLei(); }
        		
        	}else{
        		type == "0" ? alertMessage("保存失败") : alertMessage("发布失败"); 
        	}
	})
}



//预览按钮
function  showLayer(type){
	var modelType = $("[name='modelType']").val();
	var fuliHeight = $("#phone_content_children").css("height");
	if(fuliHeight == "0px" && modelType != 1){
		$("#share_set").hide();
		$("#phone_content").show();
		fuliHeight = $("#phone_content_children").css("height");
    	$("[name='fuliHeight']").val(fuliHeight.split("px")[0]);
		$("#share_set").show();
    	$("#phone_content").hide();
	}else if(fuliHeight != "0px" && modelType != 1){
    	$("[name='fuliHeight']").val(fuliHeight.split("px")[0]);
	}
 
	
	var ifchoose=($(".select-city:checked").val());
	//四级级联地址
	if(ifchoose!="0"){
		 var address="";
		  address += $("#provice option:selected").val()+"-";
		  address += $("#city option:selected").val()+"-";
		  address += $("#area option:selected").val()+"-";
		  address += $("#town option:selected").val();
		  $("#region").val(address);
	} else{
		$("#region").val("全国");
	}
	
	
	if(modelType == "0"||modelType == "2"){
		var detailHtml= $(".phone_content_children").html();
		$("input[name='fuliDetailHtml']").val(detailHtml);
	}else if(modelType == "1"){
		var detailHtml  = UE.getEditor('editor').getContent();
		if(detailHtml.indexOf("http://www.mpbox.cn/index.php/ArticleIndex/pic2") < 0){
			var reg = new RegExp("http://statics.xiumi.us","g");//g,表示全部替换。				
			detailHtml = detailHtml.replace(reg,"http://www.mpbox.cn/index.php/ArticleIndex/pic2?url=http://statics.xiumi.us");//http://www.mpbox.cn/index.php/ArticleIndex/pic2?url 调用的宋佳的一个方法
		}
		$("input[name='fuliDetailHtmlNew']").val(detailHtml);
	}

	var bigJson = JSON.stringify(bigColumn);
 	$("input[name='bigColumn']").val(bigJson);

	welfareImgList = {};
 	var len = $("#sortable").children().length;
 	for(var y=0; y <len ; y++){
 		var img = $("#sortable").children().eq(y).attr("realUrl");
 		welfareImgList["welfareImg_"+y] = img
 	}
 	
 	var welfareImg = JSON.stringify(welfareImgList);
 	$("input[name='welfareImg']").val(welfareImg);
 	
	if($("#welfareType  option:selected").val()==""){alertMessage("请选择福利分类");return;}
	if($("#companyId  option:selected").val()==""){alertMessage("请选择福利企业");return;}
	if($("#welfareName").val().trim()==""){alertMessage("福利名称为必填项");return;}
	if($("#startTime").val() == ""){alertMessage("福利开始时间为必填项");return;}
	if($("#endTime").val() == ""){alertMessage("福利结束时间为必填项");return;}
	if($("#welfareNum").val()==""){alertMessage("福利数量为必填项");return;}
	if($("#startTime").val()==""){alertMessage("福利开始时间为必填项");return;}
	if($("#endTime").val()==""){alertMessage("福利结束时间为必填项");return;}
 	
 	var companyId = $("#companyId  option:selected").val();
	if(companyId==""){alertMessage("请选择福利企业");return;}
	if($("#text_").val()==""){alertMessage("详细地址不能为空");return;}
	
	

	var bilitype =  $("input[name='billingType']").val();
	if(bilitype == 5 || bilitype == 6 || bilitype == 7  ){
		var paymoney = $("#paymoney").val()
		var billingName = $("#billingName").val();
		var price_input = $("#price_input").val();
		if(paymoney <= 0 ){ alertMessage("支付金额不能小于0");   return; }
		if(price_input  < 0 ){ alertMessage("划线价不能为0");   return; }
		if(billingName  == "" ){ alertMessage("商品名称不能为空"); return; }
	}else if(bilitype == 8 ){
		
		var paymoney = $("#paymoney").val()
		var billingName = $("#billingName").val();
		var price_input = $("#price_input").val();
		if(paymoney <= 0 ){ alertMessage("支付金额不能小于0");   return; }
		if(price_input  < 0 ){ alertMessage("划线价不能为0");   return; }
		if(billingName  == "" ){ alertMessage("商品名称不能为空"); return; }
		
		var scenicId = $("#yuDingSelect  option:selected").val()
		$("[name='scenicId']").val(scenicId);
		
		 var ticketId = $("#ticketIdSelect  option:selected").val();
    	 if(ticketId  == "" ){ alertMessage("请选择门票"); return; }
	   
    	 var uupid = $("#ticketIdSelect  option:selected").attr("uupid");
		 $("[name='uupid']").val(uupid);
		 
	 	 var uuaid = $("#ticketIdSelect  option:selected").attr("uuaid");
		 $("[name='uuaid']").val(uuaid);
	}
	
	if($("#singleDay_input").val() == ""){ $("#singleDay_input").val(0); }
	if($("#singlePeople_input").val() == ""){ $("#singlePeople_input").val(0); }
	
	var reg = /^[0-9]*$/; 
	if(!reg.test($("#welfareRewardCoin").val())){
		alertMessage("福利奖励必须是正整数");
		return;
	}else if($("#welfareRewardCoin").val()==""){
		alertMessage("福利奖励为必填项");
		return;
	}	 	
	
	toAjaxForm("manage/welfare/createWelfare?type="+type,"welfare",function(data){
		if(data.result){
    		$("#welfareId").val(data.welfareId);
    		$("#fuliQrcode").attr("src",ctx+data.qrcode)
    		$("#layer").show();
    	}
	})
}

//关闭预览
function closeLayer(){ $("#layer").hide(); }

//编辑回显时候根据福利类型
function showFunctionByType(type,entity){
	$(".selectBilltype").children().eq(type).addClass("billtypeSelect").siblings().removeClass("billtypeSelect");	
	$(".yuDing").hide();
	$(".lakalaList").hide();
	var xuniType = $("input[name='xuniType']:checked").val();
	var billingType  = $("input[name='billingType']").val();
  	if(billingType == 1){
  		$(".cardSection").hide();
	 	$(".payParam").hide();
		$("#linkUrl").hide();
		$("#exchangeCoins_p").hide();
		$("#xuniPQ").hide();
		$("[name='billingType']").val(billingType);
	 }else if(billingType == 4){
	 	$(".cardSection").show();
	 	$("#exchangeCoins_p").show();
	 	$(".payParam").hide();
	 	$("#linkUrl").hide();
		$(".xianZhi").show();
		$("#xuniPQ").hide();
	 	var cardtype = 	$("input[name='cardType']:checked").val();
	 	if(cardtype == "0"){ $(".xianZhi").show(); }else{ $(".xianZhi").hide(); }
	 	$("[name='billingType']").val(billingType);
	}else if(billingType == 5 || billingType == 10){
	 	$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").hide();
		$("[name='billingType']").val(billingType);
		
		if(billingType == 10){
			$(".lakalaList").show();
			loadLakaLa();
		}
		
	}else if(billingType == 6){
		$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").show();
		if(xuniType == billingType){
			$("[name='billingType']").val(6);
		}else{
			$("[name='billingType']").val(7);
		}
	} else if(billingType == 8){
		$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").hide();
		$("[name='billingType']").val(billingType);
		$(".yuDing").show();
		getReserveCard();
		
	}
}

//选择福利类型
$(".selectBilltype").children().bind('click',function(){
	if(welfareEntity.welfareStatus == "1" && loadType == "1" ){   alertMessage("已发布的不能重新选择"); return; }
	$(this).addClass("billtypeSelect").siblings().removeClass("billtypeSelect");	
	var billingType = $(this).attr("val");
	$(".yuDing").hide();
	$(".lakalaList").hide();
	var xuniType = $("input[name='xuniType']:checked").val();
  	if(billingType == 1){
	 	$(".cardSection").hide();
	 	$(".payParam").hide();
		$("#linkUrl").hide();
		$("#exchangeCoins_p").hide();
		$("#xuniPQ").hide();
		$("[name='billingType']").val(billingType);
	 }else if(billingType == 4){
	 	$(".cardSection").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".xianZhi").show();
		$("#xuniPQ").hide();
	 	var cardtype = 	$("input[name='cardType']:checked").val();
	 	if(cardtype == "0"){ $(".xianZhi").show(); }else{ $(".xianZhi").hide(); }
	 	$("[name='billingType']").val(billingType);
	 	$(".payParam").show();
	}else if(billingType == 5  || billingType == 10){
	 	$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").hide();
		$("[name='billingType']").val(billingType);
		
		if(billingType == 10){
			$(".lakalaList").show();
			loadLakaLa();
		}
		
	}else if(billingType == 6){
		$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").show();
		if(xuniType == billingType){
			$("[name='billingType']").val(6);
		}else{
			$("[name='billingType']").val(7);
		}
	} else if(billingType == 8){
		$(".payParam").show();
	 	$(".xianZhi").show();
	 	$("#exchangeCoins_p").show();
	 	$("#linkUrl").hide();
		$(".cardSection").hide();
		$(".xianZhi").show();
		$("#xuniPQ").hide();
		$("[name='billingType']").val(billingType);
		
		$(".yuDing").show();
		getReserveCard();
		
	}
});

//异步加载拉卡拉福利
function loadLakaLa(){
	$("#lakalaSelect").empty();
	toAjaxJson("manage/welfare/queryLaKaLaList",{},function(data){
		data = data.list;
		if(data.length  > 0){
			var card = '<option value="" >--请选择--</option>';
               $(data).each(function(i){
            	   card += '<option value="'+this.code+'" >'+this.productName+'</option>';
               });
		       $("#lakalaSelect").append(card);
	        if(typeof(welfareEntity) != "undefined"  && welfareEntity != "" ){
             	$("#lakalaSelect").val(welfareEntity.itemId);
             }
		 } 
	});
}

//预约景点
function getReserveCard(){
	toAjaxJson("manage/welfare/getReserveCard",{},function(data){
		data = data.listCard;
		var carHtml = '<option value="" >请选择景点</option>';
           $(data).each(function(i){
        	   carHtml += '<option value="'+this.uuid+'" >'+this.uutitle+'</option>';
           });
         $("#yuDingSelect").html(carHtml);
         if(typeof(welfareEntity)!="undefined"){
         	$("#yuDingSelect").val(welfareEntity.scenicId);
         	if(welfareEntity.scenicId != "" && welfareEntity.scenicId != null){
         		getTicketId(welfareEntity.scenicId);
         	}
         }
	});
}

function reloadGet(obj){
  	var scenicId = $(obj).val();
	getTicketId(scenicId)
}

function getTicketId(scenicId){
	toAjaxJson("manage/welfare/getTicketId",{"scenicId":scenicId},function(data){
		data = data.listTicket;
		var carHtml = '<option value="" >请选择门票</option>';
           $(data).each(function(i){
        	   carHtml += '<option value="'+this.uuid+'" uupid="'+this.uupid+'" uuaid="'+this.uuaid+'" uuprice="'+this.uutprice+'" >'+this.uutitle+'</option>';
           });
         $("#ticketIdSelect").html(carHtml);
         if(typeof(welfareEntity)!="undefined"){
         	$("#ticketIdSelect").val(welfareEntity.ticketId);
         }
	});
}

function changeUUprice(obj){
	
	 var price =  $(obj).find("option:selected").attr("uuprice")
	 if(typeof(price)=="undefined"){
	 	$("#paymoney").val(0);
	 	minPrice = 0;
	 }else{
		$("#paymoney").val(price);
		minPrice = price;
	 }
}




$("[name='xuniType']").bind('click',function(){
	var ty = $(this).val();
	$("[name='billingType']").val(ty);
});


$("[name='singleDay']").bind('click',function(){
	var content = $(this).val();
	if(content == 0){
		$("#singlePeople_section").hide();
	}else if(content == 1){
		$("#singlePeople_section").show();
		$("#xianZhiSpan").text("每日限购");
	}else if(content == 2){
		$("#singlePeople_section").show();
		$("#xianZhiSpan").text("每人限购");
	}else if(content == 3){
		$("#singlePeople_section").show();
		$("#xianZhiSpan").text("每人每日限购");
	}
})


//校验 领取限制
function checkSinglePeople(obj){
	var num = $(obj).val();
	var res = /^[0-9]*$/;
	if (!res.test(num)) { $(obj).val(1); alertMessage('请输入大于正整数'); }
	if(num <= 0){
		$(obj).val(1);
		alertMessage('请输入大于0的正整数');
	}
}




function getBillingBasis(){
	var price = $("#paymoney").val();
	var settlementPrice = $("#settlementPrice").val();
	var res = price -  settlementPrice;
	$("#billingBasis").val(res)
	
}

function initValue(){
  var id=$("#companyId").val();
  if(id!=null && id!=''){
	  toAjaxJson("manage/company/selectCompanyById",{"id":id},function(data){
			if(data.success){
				$("#text_").val(data.address);//详细地址
				$("#welfarePhone").val(data.phone);				
			 }
		});
  }
	
}







 