	
//-----------------------------------------------------公共开始-------------------------------------------------------------------------
			
//绑定拖动
function bindDrag(obj,type){
	var leftNum = 0;
	var topNum = 0;
	var dragJson = {
			helper:"",
	        containment: null==obj.attr("ctype")?"parent":"document",
	        zIndex: 1049,
	        refreshPositions: true,
	        stop: function(a,ui) {
	        	if(ui.position.left<0){
		    		ui.position.left=0;
		    		$(a.target).css({"left":"0"});
		    	 }
	        	$(".xian").remove();/*结束删掉线*/
	        	$(a.toElement).one("click", function(a) {
	                a.stopImmediatePropagation()
	             });
	            if(type == "img"){			//图片拖动完 改变手机框高
		            var editHeigh =  $(".edit_wrap").css("height").split("px")[0];
		            var divTop =  Number(ui.position.top) + Number(180)
		            if(divTop > 486 && divTop > editHeigh){
			            $(".edit_wrap").css("height",divTop); 
		            }
	            }else if(type == "textarea"){ //文本域
		            var editHeigh =  $(".edit_wrap").css("height").split("px")[0];
		            var divTop =  Number(ui.position.top) + Number(200)
		            if(divTop > 486 && divTop > editHeigh){
			            $(".edit_wrap").css("height",divTop); 
		            }
	            }else if(type == "form"){//表单
		            var editHeigh =  $(".edit_wrap").css("height").split("px")[0];
		            var divTop =  Number(ui.position.top) + Number(340);   
		            if(divTop > 486 && divTop > editHeigh){
			            $(".edit_wrap").css("height",divTop); 
		            }
	            }else if(type =="button"){//按钮
            	 	var editHeigh =  $(".edit_wrap").css("height").split("px")[0];
		            var divTop =  Number(ui.position.top) + Number(40);   
		            if(divTop > 486 && divTop > editHeigh){
			            $(".edit_wrap").css("height",divTop); 
		            }
	            }
	            
	            
	        },
	        drag:function(e,ui){
	         if(ui.position.left<0){
	    		ui.position.left=0;
	    	 }
	    	 if(ui.position.top<0){
	    	    ui.position.top=0;
	    	 }
	    		 
	    	 var xarea=320-$(this).width();
	    	 if(ui.position.left>=xarea){
	    		 ui.position.left=xarea;
	    	  }
	    	 
			   if($(".edit_wrap .columnSelect").length>1){
				  var left = ui.position.left-leftNum;
				  var top = ui.position.top-topNum; 
				 $.each(columnArr,function(i,n){
					 $(".edit [cuuid='"+n.cuuid+"']").css({"left":n.left+left,"top":n.top+top});
				 });
			  } 
			  
				  var be=$(".edit_wrap").width()+"px",
				  ulleft=ui.position.top+"px",
				  ulright=ui.position.left+"px",
				  Aulright=ui.position.left,
				  Hulleft=ui.position.top+486+"px";
				  var xian1='<div class="xian" style="width:100%;height:1px;background:#00ABFF;position: absolute;left:0px;top:'+ulleft+';"></div>';
				  var xian2='<div class="xian" style="width:1px;height:'+Hulleft+';background:#00ABFF;position: absolute;top:0px;left:'+ulright+';"></div>';
				  if($(".xian").length>0){
					  $(".xian").remove();
				  }
				  /*留2线*/
				  $(".edit_wrap").append(xian2);
				  $(".edit_wrap").append(xian1);
	        },
	        start:function(e,ui){

	        	leftNum = ui.position.left;
	        	topNum = ui.position.top;
	        }
	}
	comp_drag(obj,dragJson);
}
//拖动插件obj绑定拖动事件的对象options参数
function comp_drag(obj,options){
	var defaults = {
		helper: "clone",//复制
		opacity: 0.7,//拖动时候的透明度
		stop:null,
		containment:"",
		refreshPositions:true //位置更新
	};
	var opts = $.extend(defaults, options);
	obj.draggable(opts);
}

//处理鼠标点击可编辑的元素上，加hover样式
function bindHover(obj){
	obj.click(function(event) {
    	$(this).addClass('inside-hover');
    }).mouseleave(function(event) {
    	$(this).removeClass('inside-hover');
    });
}

//绑定处理大小改变
function bindResizable(obj){
	comp_resizable(obj,{'containment': '.edit_wrap'});
} 

//改变大小插件
function comp_resizable(obj,options){
	var defaults = {
		containment:"",			//区域
		start:function(e,a){
		//	addLog(e);
		},
		stop:function(a,ui){
			if((ui.position.left +$(this).width()) > 320){
				var width = 320 - ui.position.left;
				$(this).css({"width":width});
			}
			//加入到撤销
        //    addRevoke();
		}
	}
	var opts = $.extend(defaults, options);
	obj.resizable(opts);
}


//-----------------------------------------------------公共结束-------------------------------------------------------------------------
	
	

			//只针对图片组建 当前obj对象透明度滑动事件
			function AlphaInitFunTouMingDu(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100; //透明度
						$(obj).css("opacity", opacity);
						
						var divID = $(obj).parent().attr("id");
						divID = divID+"_opacity";
						bigColumn[divID] = opacity ;	
						
						//$("img").css("opacity", opacity); //透明度
						//$("img").css("border-radius", opacity * 10); //圆角
						//$(".img_bg img").css("transform", "rotate(" + opacity * 180 + "deg)"); //旋转
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
	/*		 只针对图片组件透明度滑动条 
			function opacityTouMingDu(divID){
				var opacity;
				var  type = bigColumn[divID];
				if(typeof(type) == "undefined" || type =="" ){
					opacity = 1;
				}else{
					opacity= bigColumn[divID];
				}
				var pos = document.getElementById("posOneImg");
				var bg = document.getElementById("bgOneImg");
				var bfb = document.getElementById("bfbOneImg");
				bfb.innerHTML =parseInt(100 - opacity*100)+"%";
				var xpos = parseInt((1 - opacity)*150);
				pos.style.left = xpos - pos.offsetWidth + 'px';
				parseInt(pos.style.left)<0?pos.style.left='0px':'';
				bg.style.width = xpos +'px';
				bg.style.background='#FEB10D';
			}
			*/
			
			
			//只针对图片组件obj对象圆角滑动事件
			function AlphaInitFunYuanjiao(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100; 
						
						$(obj).css("border-radius", parseInt(resultVal)*0.35); //圆角
					
						var divID = $(obj).parent().attr("id");
						divID = divID+"_border-radius";
						bigColumn[divID] = 1 - parseInt(resultVal)/100;		//将圆角的值放到json内
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			/*
			 只针对图片组件 圆角滑动条 
			function radiusCricle(divID){
				var borderCricle;
				var  type = bigColumn[divID];
				if(typeof(type) == "undefined" || type =="" ){
					borderCricle = 1;
				}else{
					borderCricle= bigColumn[divID];
				}
				var pos = document.getElementById("posTwoImg");				//圆点
				var bg = document.getElementById("bgTwoImg");				//背景加深选中条
				var bfb = document.getElementById("bfbTwoImg");			  //百分之的数值
			
				bfb.innerHTML =parseInt(100 - borderCricle*100)+"%";
				var xpos = parseInt((1 - borderCricle)*150);
				pos.style.left = xpos - pos.offsetWidth + 'px';
				parseInt(pos.style.left)<0?pos.style.left='0px':'';
				bg.style.width = xpos +'px';
				bg.style.background='#FEB10D';
			}*/
			
			
				//只针对图片组件 obj对象的旋转滑动事件
			function AlphaInitFunRotate(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100; 
						$(obj).css("transform", "rotate(" + opacity *360 + "deg)"); //旋转
						
						var divID = $(obj).parent().attr("id");
						divID = divID+"_transform";
						bigColumn[divID] = opacity;		//将旋转的值放到json内
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
			/* 只针对图片组件 回显旋转滑动条 
			function transformRotate(divID){
				var borderCricle;
				var  type = bigColumn[divID];
				if(typeof(type) == "undefined" || type =="" ){
					borderCricle = 1;
				}else{
					borderCricle= bigColumn[divID];
				}
				var pos = document.getElementById("posThreeImg");				//圆点
				var bg = document.getElementById("bgThreeImg");				//背景加深选中条
				var bfb = document.getElementById("bfbThreeImg");			//百分之的数值
				bfb.innerHTML =parseInt(100 - borderCricle*100)+"%";
				var xpos = parseInt((1 - borderCricle)*150);
				pos.style.left = xpos - pos.offsetWidth + 'px';
				parseInt(pos.style.left)<0?pos.style.left='0px':'';
				bg.style.width = xpos +'px';
				bg.style.background='#FEB10D';
			}
*/
			
			
	
			//只针对文本obj对象透明度滑动事件
			function AlphaInitFunTouMingDuByText(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100; //透明度
						$(obj).css("opacity", opacity);
						
						var divID = $(obj).attr("id");
						divID = divID+"_opacity";
						bigColumn[divID] = opacity ;	
						
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
	/*	 只针对文本组件 透明度滑动条 
			function opacityTouMingDuByText(divID){
				var opacity;
				var  type = bigColumn[divID];
				if(typeof(type) == "undefined" || type =="" ){
					opacity = 1;
				}else{
					opacity= bigColumn[divID];
				}
				var pos = document.getElementById("posOneText");
				var bg = document.getElementById("bgOneText");
				var bfb = document.getElementById("bfbOneText");
				bfb.innerHTML =parseInt(100 - opacity*100)+"%";
				var xpos = parseInt((1 - opacity)*150);
				pos.style.left = xpos - pos.offsetWidth + 'px';
				parseInt(pos.style.left)<0?pos.style.left='0px':'';
				bg.style.width = xpos +'px';
				bg.style.background='#FEB10D';
			}*/
			
			
			
			//只针对文本obj对象 圆角滑动事件
			function AlphaInitFunYuanjiaoByText(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100;  
						
						$(obj).children("textarea").css("border-radius", parseInt(resultVal)*0.35); //圆角
						var divID = $(obj).attr("id");
						divID = divID+"_border-radius";
						bigColumn[divID] = opacity;		//将圆角的值放到json内
						
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
	/*		 只针对文本组件 回显圆角滑动条 
			function opacityYuanjiaoByText(divID){
				var borderCricle;
				var  type = bigColumn[divID];
				if(typeof(type) == "undefined" || type =="" ){
					borderCricle = 1;
				}else{
					borderCricle= bigColumn[divID];
				}
				var pos = document.getElementById("posTwoText");				//圆点
				var bg = document.getElementById("bgTwoText");					//背景加深选中条
				var bfb = document.getElementById("bfbTwoText");				//百分之的数值
			
				bfb.innerHTML =parseInt(100 - borderCricle*100)+"%";
				var xpos = parseInt((1 - borderCricle)*150);
				pos.style.left = xpos - pos.offsetWidth + 'px';
				parseInt(pos.style.left)<0?pos.style.left='0px':'';
				bg.style.width = xpos +'px';
				bg.style.background='#FEB10D';
			}	*/
			
			
			
			//只针对文本组件 obj对象的旋转滑动事件
			function AlphaInitFunRanteByText(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100; 
						$(obj).css("transform", "rotate(" + opacity *360 + "deg)"); //旋转
						
						var divID = $(obj).attr("id");
						divID = divID+"_transform";
						bigColumn[divID] = opacity;		//将旋转的值放到json内
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
		/*	 只针对文本组件 回显旋转滑动条 
			function opacityRanteByText(divID){
				var borderCricle;
				var  type = bigColumn[divID];
				if(typeof(type) == "undefined" || type =="" ){
					borderCricle = 1;
				}else{
					borderCricle= bigColumn[divID];
				}
				var pos = document.getElementById("posThreeText");				//圆点
				var bg = document.getElementById("bgThreeText");				//背景加深选中条
				var bfb = document.getElementById("bfbThreeText");			//百分之的数值
				bfb.innerHTML =parseInt(100 - borderCricle*100)+"%";
				var xpos = parseInt((1 - borderCricle)*150);
				pos.style.left = xpos - pos.offsetWidth + 'px';
				parseInt(pos.style.left)<0?pos.style.left='0px':'';
				bg.style.width = xpos +'px';
				bg.style.background='#FEB10D';
			}*/

			
			
			
			
			//针对按钮组件透明度
			function AlphaInitFunTouMingDuByButton(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100; //透明度
						$(obj).css("opacity", opacity);
						var divID = $(obj).attr("id");
						divID += "_opacity";
						bigColumn[divID] = opacity ;	
						
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
			
			//针对按钮组件圆角滑动事件
			function AlphaInitFunYuanjiaoByButton(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100;  
						
						$(obj).children("span").css("border-radius", parseInt(resultVal)*0.35); //圆角
						var divID = $(obj).attr("id");
						divID = divID+"_border-radius";
						bigColumn[divID] = opacity;		//将圆角的值放到json内
						
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
			
			//只针对按钮组件对象的旋转滑动事件
			function AlphaInitFunRanteByButton(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100; 
						$(obj).css("transform", "rotate(" + opacity *360 + "deg)"); //旋转
						
						var divID = $(obj).attr("id");
						divID = divID+"_transform";
						bigColumn[divID] = opacity;		//将旋转的值放到json内
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
			
			//只针对图片组建 当前obj对象透明度滑动事件
			function AlphaInitFunBeijing(pos, bg, bfb, box,obj) {
				pos.onmousedown = function(event) {
					event.preventDefault();
					event = event || window.event;
					var disx = event.clientX - pos.offsetLeft;
					document.onmousemove = function(event) {
						var xpos = event.clientX - disx;
						var bfbNum = xpos / (box.offsetWidth) * 100;
						if(xpos < 0) {
							xpos = 0;
							bfbNum = 0;
							$(pos).addClass('zeroClass');
						}
						if(xpos > box.offsetWidth - pos.offsetWidth) {
							xpos = box.offsetWidth - pos.offsetWidth;
							bfbNum = 100;
						}
						event = event || window.event;
						pos.style.left = xpos + 'px';
						bg.style.width = xpos + pos.offsetWidth + 'px';
						bg.style.background = '#FEB10D';
						var resultVal;
						if(Math.round(bfbNum) > 0) {
							resultVal = Math.round(bfbNum) + "%";
							$(pos).removeClass('zeroClass');
						} else {
							resultVal = '0%';
						}
						bfb.innerHTML = resultVal;
						var opacity = 1 - parseInt(resultVal) / 100; //透明度
						$(obj).css("opacity", opacity);
						
						var divID = "background";
						divID = divID+"_opacity";
						bigColumn[divID] = opacity ;	
					};
					document.onmouseup = function() {
						document.onmousemove = null;
						document.onmouseup = null;
					};
				};
			}
			
		function commonLoadFun(pos,bg,bfb,divID){
				var borderCricle;
				var  asd = bigColumn[divID];
				if(typeof(asd) == "undefined" || bigColumn[divID] == null ){
					borderCricle = 1;
				}else{
					borderCricle= bigColumn[divID];
				}
				bfb.innerHTML =parseInt(100 - borderCricle*100)+"%";
				var xpos = parseInt((1 - borderCricle)*150);
				pos.style.left = xpos - pos.offsetWidth + 'px';
				parseInt(pos.style.left)<0?pos.style.left='0px':'';
				bg.style.width = xpos +'px';
				bg.style.background='#FEB10D';
			}
