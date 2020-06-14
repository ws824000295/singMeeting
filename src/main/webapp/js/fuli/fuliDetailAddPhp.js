 $(function() {
	var pageNum=0;
	$("#mb_menu li a").click(function() {
		var oA = $(this);
		var index = oA.parent().index();
		var h = $(".item").eq(index).offset().top + 'px';
		if(oA.attr("class") != "current") {
			$('html,body').animate({
				scrollTop: h
			}, 300);
		}
		if($(this).parent("li").index()==0){
			$("#mb_menu").css("display","none")
		}
	});
	
	 //到后台那数据，浏览量，领取量，领取人头像
	 getfuliMsg();
	 getShareInfo();
	 pinglunlist(pageNum);
 
	var mySwiper = new Swiper ('.mb2_peoples .swiper-container', {
	    slidesPerView : 7,
	    spaceBetween : 5,
	    loop:true,
	    observer:true,
		observeParents:false,
	    autoplay : 2000
  	});
  	
	//判断是否是限时抢购福利
	if(limitRob!=0){
		$('.time').hide();
	}
	
	 //福利详情添加喜欢
	findUserWelfareType();
	
	$("#lianjieyaoqing").unbind('click').bind('click',function(){ $("#fl_xiangqingbak").css("display","block"); });
	
	$("#zhidaole").unbind('click').bind('click',function(){ $("#fl_xiangqingbak").css("display","none"); });
	
	//禁止蒙层下面内容滚动
	var obj = document.getElementsByClassName('welfare_kuangBlack')[0];
	obj.addEventListener('touchmove', function(event) {    
		event.preventDefault(); // 阻止浏览器默认事件，重要 
	}, false); 
	//禁止蒙层下面内容滚动
	var obj = document.getElementsByClassName('welfare_kuang')[0];
	obj.addEventListener('touchmove', function(event) {    
		event.preventDefault(); // 阻止浏览器默认事件，重要 
	}, false); 
});

//福利详情添加喜欢
 function attentionWelfare(){
 	$.ajax({
 		url:ctx + "/manage/web/attentionWelfare",
 		async: false,
 		data:{"welfareId":welfareId,"userId":user.id,"adminId":user.adminId},
 		success:function(data){
 			if(data.satue=="have"){
 				$('.mb_heart').css('background-image', 'url('+ctx+'/images/fuli/heart_xuan.png)');
 			}else{
 				$('.mb_heart').css('background-image', 'url('+ctx+'/images/fuli/heart_wei.png)');
 			}
 		}
 	})
 }
 
 //福利详情添加喜欢
 function findUserWelfareType(){
 	$.ajax({
 		url:ctx + "/manage/web/findUserWelfareType",
 		async: true,
 		data:{"welfareId":welfareId,"userId":user.id,"adminId":user.adminId},
 		success:function(data){
 			if(data.satue=="have"){
 				$('.mb_heart').css('background-image', 'url('+ctx+'/images/fuli/heart_xuan.png)');
 			}else{
 				$('.mb_heart').css('background-image', 'url('+ctx+'/images/fuli/heart_wei.png)');
 			}
 		}
 	})
 }
 
 	

//到后台那数据：浏览量，领取量，领取人头像
function getfuliMsg(){
	$.ajax({
		url: ctx +"/manage/web/getfuliMsg",
		data:{"welfareId":welfareId,"adminId":user.adminId},
		success:function(data){
			if(data!=null){
				$("#companyInfoLogo").attr("src", ctx +data.companyInfo.companyLogo);
				$("#companyInfoname").html(data.companyInfo.companyName);
				$("#zhuanfa").html(data.num);												
				$("#liulan").html(data.welfare.viewCount);						//浏览
				if(data.welfare.receiveNum + data.welfare.welfareVirtualNum != 0){
					$("#canjia").html("<span class='bubaoyou-title'>已售出" + (data.welfare.receiveNum + data.welfare.welfareVirtualNum) + "件</span><p style='color: #fe3657;font-size: 10px;margin-left: 10px;'>剩余" + (data.welfare.welfareNum-data.welfare.receiveNum) + "件</p>");					
				}else{
					$("#canjia").hide();
					$("#touxiang2").hide();
				}
				var htmll='';
				for(var i=0;i<data.imgList.length;i++){
					htmll+= '<li class="swiper-slide"><img src="'+data.imgList[i]+'" /></li>';
				} 
				if(parseInt((data.welfare.welfareVirtualNum+data.welfare.receiveNum)) >=7){
					$("#touxiang2").append('<img class="go" src="'+ctx+'/images/fuli/go.png" />');
				}
				$("#touxiang").append(htmll);
				
				
			}
		}
	}) 
}
function menu(wrap, menuList, menuItems) {
	var deviceWidth = $(window).width();
	var positionX = 0;
	var menuListPositionX1 = wrap.offset().left; 
	var menuListPositionX2 = menuListPositionX1 + wrap.width();      
	$(menuList).attr("style","transition-duration: 0ms;transform: translateX(0px);");
	menuList.addEventListener('touchstart',function(event){
		if(event.targetTouches.length == 1){
	        var touch = event.targetTouches[0];
	        positionX = touch.pageX;
	        //确定本次拖动transform的初始值
	        var transformStr = menuList.style.transform;
	        transformStr = transformStr.substring(11);
	        var index = transformStr.lastIndexOf("p");
	        transformStr = transformStr.substring(0, index);
	        transformX = parseInt(transformStr);
	        //确定本次拖动的div宽度值
	        var widthStr = menuList.style.width;
	        thisWidth = parseInt(widthStr.substring(0,widthStr.lastIndexOf("p")));
	    }
	}, false);
	menuList.addEventListener('touchmove', function(event) {
	    //阻止其他事件
	    event.preventDefault();
	    //获取当前坐标
	    if(event.targetTouches.length == 1){
	        var touch = event.targetTouches[0];
	        menuList.style.transform = 'translateX('+(transformX+touch.pageX-positionX)+'px)';
	        $(menuList).css("width",thisWidth+positionX-touch.pageX);
	    }
	}, false);
	menuList.addEventListener('touchend', function(event) {
	    var menuItem1 = menuItems[0];
	    var menuItem1Left = $(menuItem1).offset().left;
	    console.log(menuItem1Left);
	   	var menuItem2 = menuItems[menuItems.length-1];
	    var menuItemPositionX = $(menuItem2).offset().left+$(menuItem2).width();
	    var firstToLast = menuItemPositionX - menuItem1Left;
	    if (menuItem1Left > menuListPositionX1 || firstToLast < deviceWidth) {
	        menuList.style.transform = 'translateX('+(menuListPositionX1)+'px)';
	    }
	    if(menuItemPositionX < menuListPositionX2 && menuItem1Left < 0 && firstToLast > deviceWidth) {
	    	var myWidth = $(menuList).width() - deviceWidth;
	        menuList.style.transform = 'translateX('+(0-myWidth)+'px)';
	    }
	}, false);
}

function init() {
	var menuWrap = $('#content');
	var menuList = $(".mb_commoditylist.item")[0];
	var menuListItems = $("#item3 li");
	menu(menuWrap, menuList, menuListItems);
} 

 
//评论框显示  回复福利
function comment(){
	$("#pinglunMsg").val("");
	$('#fuliComment').show();	
}
//发表
function Submitcomment(){
	var content = $("#pinglunMsg").val();
	if(content!=null && content.length !=0){
		$.ajax({
			url: ctx +"/manage/web/saveComment",
			data:{"welfareId":welfareId,"adminId":user.adminId,"userId":user.id,"content":content},
			success:function(data){
				if(data.result='yes'){
					showtips($('#pinglunsuccess'));
					$('#fuliComment').hide();
					pinglunlist(0);
				}
			}
		})
	}else{
		alert("请填写评论");
	}
}
//评论框显示 回复用户
function huifu(id){
	sessionStorage.setItem("parentId",id);
	$("#pinglunMsg2").val("");
	$('#userComment').show();
}
//发表
function SubmitcommentAndParent(){
	var content2 = $("#pinglunMsg2").val();
	var parentId = sessionStorage.getItem("parentId");
	if(content2!=null && content2.length !=0){
		$.ajax({
			url: ctx +"/manage/web/saveComment",
			data:{"welfareId":welfareId,"adminId":user.adminId,"userId":user.id,"content":content2,"parentId":parentId},
			success:function(data){
				if(data.result='yes'){
					showtips($('#pinglunsuccess'));
					$('#userComment').hide();
					pinglunlist(0);
				}
			}
		})
	}else{
		alert("请填写评论");
	}
}
//加载评论
function pinglunlist(obj){
	$.ajax({
		url: ctx +"/manage/web/pinglunlist",
		data:{"welfareId":welfareId,"adminId":user.adminId,"pageNum":obj},
		success:function(data){
			if(data != null && data != ""){
				if(data.num!=null && data.num!=''){
					$("#commentNum").html("("+data.num+")");
				}
				var html='';
				for(var i=0;i<data.commentRecordDtoList.length;i++){
					html+= '<div class="mb_pinglunmsg">';
					html+= '	<img class="img" src="'+data.commentRecordDtoList[i].userIconUrl+'" />';
					html+= '	<div class="msg">';
					html+= '		<p class="name">'+data.commentRecordDtoList[i].userName+'</p>';
					html+= '		<p class="time"><span>'+FormatDate(data.commentRecordDtoList[i].createTime)+'</span></p>';
					html+= '		<div class="msgall">'+data.commentRecordDtoList[i].content+'</div>';
					html+= '	</div>';
					html+= "	<p class='huifu' onclick='huifu(\""+data.commentRecordDtoList[i].id+"\")'>回复</p>";
					html+= '</div>';
					if(data.commentRecordDtoList[i].commentRecordList !=null){
						for(var j=0;j<data.commentRecordDtoList[i].commentRecordList.length;j++){
							html+= '<div class="mb_pinglunmsg mb_pinglun_two">';
							if(data.commentRecordDtoList[i].commentRecordList[j].userName !='系统回复'){
								html+= '	<img class="img" src="'+data.commentRecordDtoList[i].commentRecordList[j].userIconUrl+'" />';
							}else{
								html+= '	<img class="img" src="'+ctx+'/images/xuniicon.png" />';
							}
							html+= '	<div class="msg">';
							html+= '		<p class="name">'+data.commentRecordDtoList[i].commentRecordList[j].userName+'</p>';
							html+= '		<p class="time"><span>'+FormatDate(data.commentRecordDtoList[i].commentRecordList[j].createTime)+'</span></p>';
							html+= '		<div class="msgall">'+data.commentRecordDtoList[i].commentRecordList[j].content+'</div>';
							html+= '	</div>';
							if(data.commentRecordDtoList[i].commentRecordList[j].userName !='系统回复'){
								html+= "	<p class='huifu' onclick='huifu(\""+data.commentRecordDtoList[i].id+"\")'>回复</p>";
							}else{
								html+= '	<p class=""></p>';
							}
							html+= '</div>';
						}
					}
				}
				if(data.num2>3){
					$("#jiazai").remove();
					$(".mb_pinglunlist").after("<p id='jiazai' style='text-align: center;color:#ccc' onclick='pinglunlist(\""+data.pageNum+"\")'>更多评论...</p>");
				}
				if(data.pageNum=='1'){
					$(".mb_pinglunlist").empty();
				}
				$(".mb_pinglunlist").append(html);
			}
		}
	})

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
  
function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}
function toback(){
	window.history.go(-1);
}
function guanbi(){
	$("#selectStyle").hide();
}

function showtips(obj) {
	obj.show().delay(500).fadeOut();
}
function closekuangContent(){
	$('.welfare_kuang').css('display', 'none');
	$('.welfare_kuangBlack').css('display', 'none');
}

function showkuangContent(){
	$('.welfare_kuangBlack').show();
	$('.welfare_kuang').show();
}
   