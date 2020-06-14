//处理左侧导航高度
$(window).on("ready", function() {
	var h = window.innerHeight || document.body.clientHeight || document.documentElement.clientHeight;
	$(".fl_left_nav").css("height", h); 
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

});