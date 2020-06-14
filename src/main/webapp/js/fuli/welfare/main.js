//处理左侧导航高度
$(window).on("ready", function() {
	var h = window.innerHeight || document.body.clientHeight || document.documentElement.clientHeight;
	$(".fl_left_nav").css("height", h);
	$(".fl_rightcount").css("height", h - 90);
	$(".biaoge").css("height", h - 230);

	//福利券设置img边框
	$(".flq_stylec img").click(function() {
		flq_Stylesed($(this), "sed")
	})
	//切换样式
	function flq_Stylesed(obj, sed) {
		obj.addClass(sed).siblings().removeClass(sed)
	}
	//组件设置切换
	$(".assembly span").click(function() {
		flq_Stylesed($(this), "sed2");
		$(".assemblybox>.assembly_c:eq(" + $(this).index() + ")").css("display", "block").siblings(".assembly_c").css("display", "none");
	})

	//透明度
	function AlphaInitFun(pos, bg, bfb, box) {
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
				//console.log(opacity)

				//	$(".img_bg img").css("opacity", opacity); //透明度
				//$(".img_bg img").css("border-radius", opacity * 10); //圆角
				$(".img_bg img").css("transform", "rotate(" + opacity * 180 + "deg)"); //旋转
			};
			document.onmouseup = function() {
				document.onmousemove = null;
				document.onmouseup = null;
			};
		};
	}

	/* 透明度滑动条 */

	//透明度初始化
	var posThr = document.getElementById("posThree");
	var bgThr = document.getElementById("bgThree");
	var bfbThr = document.getElementById("bfbThree");
	var opacityBoxThree = document.getElementById("opacityBoxThree");
	AlphaInitFun(posThr, bgThr, bfbThr, opacityBoxThree);

	var posOne = document.getElementById("posOne");
	var bgOne = document.getElementById("bgOne");
	var bfOne = document.getElementById("bfbOne");
	var opacityBoxOne = document.getElementById("opacityBoxOne");
	AlphaInitFun(posOne, bgOne, bfOne, opacityBoxOne);

	var posTwo = document.getElementById("posTwo");
	var bgTwo = document.getElementById("bgTwo");
	var bfTwo = document.getElementById("bfbTwo");
	var opacityBoxTwo = document.getElementById("opacityBoxTwo");
	AlphaInitFun(posTwo, bgTwo, bfTwo, opacityBoxTwo);
	//背景透明度
	var posTwo = document.getElementById("posBg");
	var bgTwo = document.getElementById("bgBg");
	var bfTwo = document.getElementById("bfbBg");
	var opacityBoxTwo = document.getElementById("opacityBoxBg");
	AlphaInitFun(posTwo, bgTwo, bfTwo, opacityBoxTwo);
	
	//点击事件切换
	$(".dj_tab input").click(function() {
		$(this).attr("checked", true);

		$(".dj_tabbox>p:eq(" + $(this).index() + ")").css("display", "block").siblings("p").css("display", "none");
	})
	//添加组件
	function create(itemhtml) {
		append(itemhtml);
	}

	//文本添加
	//添加到模块
	function fonttet() {
		var textare = "<textarea style='position:absolute;width:200px;height:300px'></textarea>"
		$(".edit_wrap").append(textare);
	}
	$('#tet').click(function() {
		fonttet();
	})
	//图片添加
	//添加到模块
	function imgpic() {
		var imgpic = "<img src='images/c1.png'/>"
		$(".edit_wrap").append(imgpic);
	}
	$('#imgpic').click(function() {
		imgpic();
	})
	//表单添加
	//添加到模块
	function addform() {
		var addform = '<ul class="biaodan_form">';
		addform += '<li><span>文本</span><input type="text" placeholder="姓名"><span class="red">*</span></li>';
		addform += '<li><span>邮箱</span><input type="text" placeholder="邮箱"><span class="red">*</span></li>';
		addform += '<li class="sub_btn"><a>提交</a></li>';
		addform += '</ul>'
		$(".edit_wrap").append(addform);
	}
	$('#addform').click(function() {
		addform();
	})
	//背景添加
	//添加到模块
	function bjpic() {
		$(".edit_wrap").css("background-image", "url(images/timg.jpg)");
		$(".edit_wrap").css("background-repeat", "no-repeat");
		$(".edit_wrap").css("background-size", "100% 100%")

	}
	$('#bjpic').click(function() {
		bjpic();
	})
	//按钮添加
	//添加到模块
	function addbtn() {
		var addbtn = "<span style='padding:10px 12px;background:red;color:#fff;border-radius:10px'>我是按钮</span>";
		$(".edit_wrap").append(addbtn);

	}
	$('#addbtn').click(function() {
		addbtn();
	})

	//表单划过显示添加组件
	$(".add_formbox").hover(function() {
		$(".add_formli").show()
	}, function() {
		$(".add_formli").hide()
	})


});