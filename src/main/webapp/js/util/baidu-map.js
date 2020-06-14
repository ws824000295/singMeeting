(function() {
	
	if(getQueryStr("xcxUserId") =="" || getQueryStr("xcxUserId") == null){
		window.HOST_TYPE = "2";
		window.BMap_loadScriptTime = (new Date).getTime();
		document.write('<script type="text/javascript" src="'+ctxBaiDu+'/js/util/baidu-map-api.js"></script>');
	}
	
})();