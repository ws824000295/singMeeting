var dateList,viewCountList,viewUserList,shareCountList,shareUserList,formLogList;


$(function(){
	$('#inTime').html(time);
	//$('#adminName').html(adminName);
	todayOverviewPage();
	todayOverviewPageCoins();
	todayOverview();
	panoramicData();//全景数据
	tradingFlow("day");//交易流水
	switchTab();//切换查询
	queryWelfareRankings();

	loadTime();//加载时间

	var sTime = $('#date_demo1').html().split(" 至 ");
	$('#startTime').val(sTime[0]);
	$('#endTime').val(sTime[1]);
	companyRanking(sTime[0],sTime[1],"tradeFlowing");//福利企业TOP10排名
	tab();
	//
	/*$(".design").click(function(){
		confirm("功能即将开通");			
	});
	$(".visit").click(function(){
		confirm("功能即将开通");			
	});*/
});
//充值页面
function toRecharge(){
	toPage("manage/money/toRecharge",null,"indexShowDiv",function(){});
}
//今日数据
function todayOverviewPage(){
	$.ajax({
		url:ctx+"/manage/dataOverview/todayOverviewPage",
		data:{'adminId':adminId},
		success:function(dat){
			if(dat.adminuserAccount=="区块链报错"){
				$('#useFubi').html(0);
			}else{
				$('#useFubi').html(dat.adminuserAccount);
			}
			if(Number(dat.adminuserAccount)>1000){
				$('.less-tips').hide();
			}
			var data = dat.data;
			if(data!=null&&""!=data){
				$('#addedCoins').html(data[0].todday.addedCoins);
				$('#payCoins').html(Number(Number(data[0].todday.payCoins).toFixed(2)+Number($('#payCoins').html())).toFixed(2));
				$('#ttodayTrade').html(data[0].todday.todayTrade);
				$('#ytodayTrade').html(data[1].yesterday.todayTrade);
				$('#todaySharenum').html(data[0].todday.todaySharenum);
				$('#ytodaySharenum').html(data[1].yesterday.todaySharenum);

				$('#todayOrders').html(data[0].todday.todayOrders);
				$('#ytodayOrders').html(data[1].yesterday.todayOrders);

				$('#newUsers').html(data[0].todday.newUsers);
				$('#ynewUser').html(data[1].yesterday.newUsers);

			}
		}
	});
}
//今日数据  本社福钻流水扣除
function todayOverviewPageCoins(){
	$.ajax({
		url:ctx+"/manage/dataOverview/todayOverviewPageCoins",
		data:{'adminId':adminId},
		success:function(data){
			if(data!=null&&""!=data){
				$('#payCoins').html(Number(Number(data.todayCoins)+Number($('#payCoins').html())).toFixed(2));
			}
		}
	});
}
function todayOverview(){
	$.ajax({
		url:ctx+"/manage/dataOverview/todayOverview",
		data:{'adminId':adminId},
		success:function(data){
			if(data!=null&&""!=data){
				//todayView
				$('#todayView').html(data.today);
				$('#yesview').html(data.yestoday);
			}
		}
	});
}
function switchTab(){
	//切换
	$('.top100-tab span').click(function(){

		$('.top100-tab span').removeClass('green');
		$('.top100-tab span').find("i").removeClass('green-icon');
		$(this).find("i").addClass('green-icon');
		$(this).addClass('green');
		if(0 == $(this).index()){//交易流水
			companyRanking($('#startTime').val(),$('#endTime').val(),"tradeFlowing");//福利企业TOP10排名
		}
		if(1 == $(this).index()){//交易笔数
			companyRanking($('#startTime').val(),$('#endTime').val(),"tradeNumber");//福利企业TOP10排名
		}
		/*if(2 == $(this).index()){//推广佣金
			companyRanking("promotionCommission");//福利企业TOP10排名
		}*/
	});
	//
	$('.stream-tab span').click(function(){
		$('.stream-tab span').removeClass('stream-green');
		$(this).addClass('stream-green');
		if(0 == $(this).index()){//ri
			tradingFlow("day");//交易流水
		}
		if(1 == $(this).index()){//日
			tradingFlow("month");//交易流水
		}
	});
}
//全景数据
function panoramicData(){
	$.ajax({
		url:ctx+"/manage/dataOverview/panoramicData",
		data:{'adminId':adminId},
		success:function(data){
			$('#companyNum').html(data.companyNum);
			$('#orderNum').html(data.orderNum);
			$('#commission').html(data.commission);
			$('#ordersPrice').html(data.ordersPrice);
			$('#userNum').html(data.userNum);
		}
	});
}
//福利企业TOP10排名
function companyRanking (startTime,endTime,queryType){
	$.ajax({
		url:ctx+"/manage/dataOverview/companyRanking",
		data:{'adminId':adminId,'queryType':queryType,
			"startTime":startTime,"endTime":endTime},
			success:function(data){
				/*if(data==null||data.companyName.length==0){
					$('.nodataPic').show();
					$('.top-box').hide();
					return;
				}else{
					$('.nodataPic').hide();
					$('.top-box').show();
				}*/
				if(data==null||data.companyName.length==0){
					data.companyName = [0];
					data.priceOrNum = [0];
				}
				var name = '';
				if("tradeFlowing"==queryType){
					name='元';
				}
				if("tradeNumber"==queryType){
					name='笔';
				}
				// 基于准备好的dom，初始化echarts图表
				var chart = echarts.init(document.getElementById('companyRanking'));
				var option = {
						// 图表标题
						title: {
							/*text:"福利企业TOP10排名",*/
							x: 'left', y: 'top',       
							backgroundColor: 'rgba(0,0,0,0)',
							borderColor: '#ccc',       // 标题边框颜色
							borderWidth: 0,            // 标题边框线宽，单位px，默认为0（无边框）
							padding: 2,                // 标题内边距，单位px，默认各方向内边距为5，// 接受数组分别设定上右下左边距，同css
							itemGap: 10,               // 主副标题纵向间隔，单位px，默认为10，
							textStyle: {
								fontSize: 18,fontWeight: 'bolder',color: '#333'}
						},
						tooltip: {show: true},
						color : [ '#56b5e7'],
						grid: {  
							top:'30%',left: '5%',bottom: '40%' ,
							x: 80,y: 60,x2: 80,y2: 60,
							backgroundColor: 'rgba(0,0,0,0)',
							borderWidth: 1,borderColor: '#ccc'},
							xAxis : [
								{	 type : 'category',
									data : data.companyName,
									axisLabel:{  interval: 0,rotate:40}
								}
								],
								yAxis : [
									{
										name : '单位：'+name,
										type : 'value'
											// max : 500
									},
									],
									series : [
										{
											"name":"",
											"type":"bar",
											"data":data.priceOrNum,
											barWidth : 30,//柱图宽度
											axisLabel:{  interval: 0},
											//顶部数字展示pzr
											itemStyle: {
												//柱形图圆角，鼠标移上去效果，如果只是一个数字则说明四个参数全部设置为那么多
												emphasis: {
													barBorderRadius: 30
												},
												normal: {
													//柱形图圆角，初始化效果
													barBorderRadius:[15,15, 0, 0],
													label: {
														show: true,//是否展示
														textStyle: {
															fontWeight:'bolder',
															fontSize : '12',
															fontFamily : '微软雅黑',
														}
													}
												}
											}
										}
										]
				};
				// 为echarts对象加载数据 
				chart.setOption(option);
			}
	});
}
//交易流水
function tradingFlow(queryType){
	$.ajax({
		url:ctx+"/manage/dataOverview/tradingFlow",
		data:{'adminId':adminId,'queryType':queryType},
		success:function(data){
			// 基于准备好的dom，初始化echarts图表
			//var chart = echarts.init(document.getElementById('tradingFlow'));
			var myChart1 = echarts.init(document.getElementById('tradingFlow'));
			var option1 = {
					// 图表标题

					legend : {},
					color : [ '#56b5e7'],
					grid: {
						top:'10%',
						left: '5%',  
						bottom: '30%'  
					},
					xAxis : { 
						type : 'category', boundaryGap : false,
						splitLine : { show : true, lineStyle : { color : '#ccc', width : 1 } },
						data : data.xtime,
						axisLabel:{  
							interval:0,//横轴信息全部显示  
							rotate:30,//-30度角倾斜显示  
						}  
					},
					yAxis : {
						type : 'value',
						axisLabel : { formatter : '{value} '}
					},
					series : [
						{
							name : '访问次数',
							type : 'line',
							smooth : true, //这句就是让曲线变平滑的 
							areaStyle : {normal : {} },
							data : data.ydata,
							markPoint : { ata : [ { type : 'max', name : '最大值' } ]}
						}
						]
			};
			myChart1.setOption(option1);
		}
	});
}
//7天 推广福利排行
function queryWelfareRankings(){
	$.ajax({
		url:ctx+"/manage/dataOverview/welfareRankings",
		data:{'adminId':adminId},
		success:function(data){
			if(data!=null){
				var html = '';
				for(var i=0;i<data.length;i++){
					var view_num = 0;
					var visitnum = 0;
					var orderNum = 0;
					var extension = 0;
					if(data[i].view_num){
						view_num = data[i].view_num;
					}
					if(data[i].visitnum){
						visitnum = data[i].visitnum;
					}
					if(data[i].orderNum){
						orderNum = data[i].orderNum;
					}
					if(data[i].extension){
						extension = data[i].extension;
					}
					html +='<div class="tui-item">';
					html +='	<span>'+data[i].welfare_name+'</span>';
					html +='	<span>'+view_num+'</span>';
					html +='	<span>'+visitnum+'</span>';
					html +='	<span>'+orderNum+'</span>';
					html +='	<span class="tui-money">'+extension+'</span>';
					html +='</div>';
				}
				$('#welfareList').append(html);
			}
		}
	});
}
function tab(){
	$('.fuli').click(function(){
		if(0 == $(this).index()){//月
			$('.list-group-item')[2].click();
		}
		if(1 == $(this).index()){//日
			$('.list-group-item')[3].click();
		}
		if(2 == $(this).index()){//日
			$('.list-group-item')[1].click();
		}
		if(3 == $(this).index()){//日
			$('.list-group-item')[5].click();
		}
	});
}
function loadTime(){
/*日期选择控件*/
/**
 * 获得当前时间的前N天是几号
 */
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

function getBeforeOtherDay(number){
	var now = new Date;
	now.setDate(now.getDate() - number);
	return now.format("yyyy-MM-dd");
}
/*日期选择控件*/
function datatime(main, time1, time2, time3, time4, time9, time5, timecount) {
	var start = getBeforeOtherDay(0);
	var end = getBeforeOtherDay(6);
	var dateRange = new pickerDateRange(main, {
		aRecent7Days: time1, //最近7天
		aToday: time2, //今天
		aRecent14Days: time3,
		aRecent30Days: time4, //最近30天
		aRecent90Days: time9, //最近30天
		isTodayValid: true,
		startDate: start,
		endDate: end,
		defaultText: ' 至 ',
		inputTrigger: time5,
		theme: 'ta',
		success: function(obj) {
			$(timecount).html('开始时间 : ' + obj.startDate + '<br/>结束时间 : ' + obj.endDate);
			//console.log(obj.startDate, obj.endDate) //获取当前选择时间
			$('#startTime').val(obj.startDate);
			$('#endTime').val(obj.endDate);
			var s = $(".top-type.green").index();
			if(s==0){
				companyRanking (obj.startDate, obj.endDate,"tradeFlowing")
			}
			if(s==1){
				companyRanking (obj.startDate, obj.endDate,"tradeNumber")
			}
		}
	});
}
datatime('date_demo1', 'aRecent7DaysDemo7_01', 'aRecent7DaysDemo1_01', 'aRecent7DaysDemo15_01', 'aRecent7DaysDemo30_01', 'aRecent90DaysDemo30_01', 'input_trigger_demo1', "#dCon_demo1");
}