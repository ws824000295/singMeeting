 	$(function () {
		
	   $("input[name='cardPayStatus']").bind("click",function(){
	    	var val = $(this).val();
	    	if(val =="0"){
	    		$("#money").hide();
	    		$("#paymoney").val(0);
	    	}else{
	    		$("#money").show();
	    	}
	    })
	    
		 $("input[name='billingType']").bind("click",function(){
		 	
 		 if($(this).val() > 3){
 			$("#exchangeCoins_p").show();
 		 }else{
 			$("#exchangeCoins_p").hide();
 		 }
		  $("input[name='jiangli'][value="+$(this).val()+"]").attr("checked",true);
		  var desc , billingDesc, basis, rewardType;
		  var html = '';
		  var coins_des = "";
 	 	  var coinsNum = $("#welfareRewardCoin").val();
		  var billingBasis = $("#billingBasis").val();
		  $("#biaozhu").empty();
		  if($(this).val()==1){
		 	 	html += '<span class="t_name" >';					 
			 	html += 	'<span class="red">*</span>福利计费标注 :</span>';					 
			  	html += '<span class="t_kuang">';
			  	html += 	'<input type="text" onblur="changbilling(this);" id="billingBasis" name="billingBasis" style="width: 93px;border-radius:5px ;text-align: center;" class="tet" placeholder="10" value='+billingBasis+'>元/个';
			  	html += 	'<span id="billingDesc" style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">领取一个福利商家需付款';
			  	html += 		'<a id="basis1"  style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">'+billingBasis+'</a>元';
			  	html += 	'</span>';
			  	html += '</span>';
			  	$("#biaozhu").append(html);
				coins_des =coinsNum +"个福钻1个有效报名（注1个福钻=1分钱)";
				desc = "通过分享链接成功报名即可获得福钻奖励";  
				basis="个";
				$("#cardCoins_input").hide();
				$("#coins_name").html("福钻奖励：");
				$("#payParam").hide();
				$("#billingName").hide();
				$("#price").hide();
				$("#purchasenuNmber").hide();
				$("#linkUrl").hide();
		  }else if($(this).val()==2){
			  	html += '<span class="t_name" >'					 
			 	html += 	'<span class="red">*</span>福利计费标注 :</span>';	
			  	html += '<span class="t_kuang">';
			  	html += 	'<input type="text" onblur="changbilling(this);" id="billingBasis" name="billingBasis" style="width: 93px;border-radius:5px ;text-align: center;" class="tet" placeholder="10" value='+billingBasis+'>元/次';
			  	html += 	'<span id="billingDesc" style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">领取一个福利商家需付款';
			  	html += 		'<a id="basis1"  style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">'+billingBasis+'</a>元';
			  	html += 	'</span>';
			  	html += '</span>';
			  	$("#biaozhu").append(html);
			  	coins_des =coinsNum+"个福钻1个有效点击（注1个福钻=1分钱)";
				desc = "产生有效点击可获得福钻奖励"  
				basis="次"; 
				$("#coins_name").html("福钻奖励：");
				$("#cardCoins_input").hide();
				$("#payParam").hide();
				$("#billingName").hide();
				$("#price").hide();
				$("#purchasenuNmber").hide();
				$("#linkUrl").show();
		  }else if($(this).val()==3){
			  	html += '<span class="t_name" >'					 
			 	html += 	'<span class="red">*</span>福利计费标注 :</span>';	
			  	html += '<span class="t_kuang">';
			  	html += 	'<input type="text" onblur="changbilling(this);" id="billingBasis" name="billingBasis" style="width: 93px;border-radius:5px ;text-align: center;" class="tet" placeholder="10" value='+billingBasis+'>元/次';
			  	html += 	'<span id="billingDesc" style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">领取一个福利商家需付款';
			  	html += 		'<a id="basis1"  style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">'+billingBasis+'</a>元';
			  	html += 	'</span>';
			  	html += '</span>';
			  	$("#biaozhu").append(html);
			  	coins_des =coinsNum+"个福钻1个有效浏览（注1个福钻=1分钱)";
				desc = "产生有效的浏览展示可获得福钻奖励" 
				basis="次"; 
				$("#coins_name").html("福钻奖励：");
				$("#cardCoins_input").hide();
				$("#payParam").hide();
				$("#billingName").hide();
				$("#price").hide();
				$("#purchasenuNmber").hide();
				$("#linkUrl").hide();
		  }else if($(this).val()==4){
		  		html += '<span class="t_name" >'					 
			 	html += 	'<span class="red">*</span>福利计费标注 :</span>';	
			  	html += '<span class="t_kuang">';
			  	html += 	'<input type="text" onblur="changbilling(this);" id="billingBasis" name="billingBasis" style="width: 93px;border-radius:5px ;text-align: center;" class="tet" placeholder="10" value='+billingBasis+'>元/次';
			  	html += 	'<span id="billingDesc" style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">领取一个福利商家需付款';
			  	html += 		'<a id="basis1"  style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">'+billingBasis+'</a>元';
			  	html += 	'</span>';
			  	html += '</span>';
			  	$("#biaozhu").append(html);
			  	var num = $("#cardCoins").val();
			  	coins_des ="领取卡券奖励"+coinsNum+"个福钻,核销奖励"+num+"个福钻";
				desc = "领取和核销卡券领取奖励" 
				basis="次领取"; 
				$("#coins_name").html("领卡奖励：");
				$("#cardCoins_input").show();
				$("#payParam").show();
			 	$("#billingName").hide();
			 	$("#price").hide();
			 	$("#purchasenuNmber").hide();
			 	$("#linkUrl").hide();

			 	$("#card_type").show();
			 	$("#is_or_pay").show();
			 	$("#cardList").parent().show();
			 	var cardtype = 	$("input[name='cardType']:checked").val();
			 	if(cardtype == "0"){
			 		$("#singleDay").show();
				 	$("#singlePeople").show();
			 	}else{
			 		$("#singleDay").hide();
				 	$("#singlePeople").hide();
			 	}
			}else{
			  	html += '<span class="t_name" >'					 
			 	html += 	'<span class="red">*</span>福利计费标注 :</span>';	
			  	html += '<span class="t_kuang">';
			  	html += 	'<input type="text" onblur="changbilling(this);" id="billingBasis" name="billingBasis" style="width: 93px;border-radius:5px ;text-align: center;" class="tet" placeholder="10" value='+billingBasis+'>元/次';
			  	html += 	'<span id="billingDesc" style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">领取一个福利商家需付款';
			  	html += 		'<a id="basis1"  style=";display:inline-block;float: none;font-size: 12px;color: #A1A1A1;">'+billingBasis+'</a>元';
			  	html += 	'</span>';
			  	html += '</span>';
			  	$("#biaozhu").append(html);
			  	coins_des =coinsNum+"个福钻1个有效购买（注1个福钻=1分钱)";
				desc = "产生有效购买可获得福钻奖励" 
				basis="次"; 
				$("#coins_name").html("福钻奖励：");
				$("#cardCoins_input").hide();
				$("#payParam").show();
			 	$("#billingName").show();
			 	$("#price").show();
			 	$("#purchasenuNmber").show();
			 	$("#linkUrl").hide();
			 	
			 	$("#singleDay").show();
			 	$("#singlePeople").show();
			 	$("#card_type").hide();
			 	
			 	$("#card_type").hide();
			 	$("#is_or_pay").hide();
			 	$("#cardList").parent().hide();
			 	
			}
			  $("#desc").val(desc); 
			  $("#img_desc").html(desc); 
			  $("#img_basis").html(basis); 
			  $("#img_rewardType").html(rewardType);
			  $("#coins_des").html(coins_des);
		  });
		  
	     selectCompanyList();		//查询福利企业
		 selectWelfareClass();		//查询福利分类
		 queryProvinces();

		 $('#welfareName').bind('input propertychange', function() { $('#fuli_name').html($(this).val());});
		 
		 $('#welfareRewardCoin').bind('input propertychange', function() { 
		 	  var type =  $("input[name='billingType']:checked").val();
		 	  var num  =  $("#cardCoins").val();
		 	  if(type == "1"){
		 		  $("#coins_des").html($(this).val() + "个福钻1个有效报名（注1个福钻=1分钱)")
		 	  }else if(type == '2'){
		 	  	  $("#coins_des").html($(this).val() + "个福钻1个有效点击（注1个福钻=1分钱)")
		 	  }else if(type == '3'){
		 	  	  $("#coins_des").html($(this).val() + "个福钻1个有效浏览（注1个福钻=1分钱)")
		 	  }else if(type == '4'){
		 	  	  $("#coins_des").html("领取卡券奖励"+$(this).val() + "个福钻,核销奖励"+num+"个福钻")
		 	  }
		 });
		 
		  $('#cardCoins').bind('input propertychange', function() { 
		 	  var num  =  $("#welfareRewardCoin").val();
	 	  	  $("#coins_des").html("领取卡券奖励"+ num + "个福钻,核销奖励"+$(this).val()+"个福钻")
		 });
		 
		 $('#startTime').bind('input propertychange', function() {$('#img_startTime').html($(this).val());});
		 $('#endTime').bind('input propertychange', function() {$('#img_endTime').html($(this).val());});

		 
	});
	
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
		        if(typeof(welfareEntity)!="undefined"){
	             	$("#cardList").val(welfareEntity.cardId)
	             }
			 } 
		});
	}
	
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
		             	$("#cardList").val(welfareEntity.cardId)
		             }
			 } 
		});
	}
	
	//发布=1和保存=0
	function putAndsave(type){
		var ifchoose=($(".select-city:checked").val());
		//四级级联地址
		if(ifchoose!="buxuanze"){
			if($("#provice").val()==""){
				alertMessage("请选择区域分类");
				return;
			}
			if($("#city").val()==""){
				alertMessage("请选择区域分类");
				return;
			}
			if($("#area").val()==""){
				alertMessage("请选择区域分类");
				return;
			}
			if($("#town").val()==""){
				alertMessage("请选择区域分类第四级");
				return;
			}
			var address="";
			 address=address+$("#provice option:selected").val()+"-";
			 address=address+$("#city option:selected").val()+"-";
			 address=address+$("#area option:selected").val()+"-";
			 address=address+$("#town option:selected").val();
			
			$("#region").val(address);
		}//不选择地址默认全国
		else{
			$("#region").val("全国");
		}
		var modelType = $("input[name='modelType']:checked").val();
		
		if(modelType == "0"){
			var detailHtml= $(".edit_wrap").html();
			$("input[name='fuliDetailHtml']").val(detailHtml);
		}else{
			var detailHtml  = UE.getEditor('editor').getContent();
			if(detailHtml.indexOf("http://www.mpbox.cn/index.php/ArticleIndex/pic2") < 0){
				var reg = new RegExp("http://statics.xiumi.us","g");//g,表示全部替换。				
				detailHtml = detailHtml.replace(reg,"http://www.mpbox.cn/index.php/ArticleIndex/pic2?url=http://statics.xiumi.us");//http://www.mpbox.cn/index.php/ArticleIndex/pic2?url 调用的宋佳的一个方法
			}
			$("input[name='fuliDetailHtmlNew']").val(detailHtml);
		}

		var bigJson = JSON.stringify(bigColumn);
	 	$("input[name='bigColumn']").val(bigJson);

	 	var welfareVirtualNum = $("#welfareVirtualNum").val();
	 	if(welfareVirtualNum==""){
	 		 $("#welfareVirtualNum").val(0);
	 	}
	 	
		if($("#companyId  option:selected").val()==""){alertMessage("请选择福利企业");return;}
		if($("#welfareType  option:selected").val()==""){alertMessage("请选择福利分类");return;}
		if($("#welfareName").val().trim()==""){alertMessage("福利名称为必填项");return;}
		if($("#startTime").val() == ""){alertMessage("福利开始时间为必填项");return;}
		if($("#endTime").val() == ""){alertMessage("福利结束时间为必填项");return;}
		if($("#welfareNum").val()==""){alertMessage("福利数量为必填项");return;}
	 	var companyId = $("#companyId  option:selected").val();
		if(companyId==""){alertMessage("请选择福利企业");return;}
		if($("#text_").val()==""){alertMessage("详细地址不能为空");return;}
	
		var bilitype =  $("input[name='billingType']:checked").val();
		if(bilitype == 5 || bilitype == 6  ){
			var paymoney = $("#paymoney").val()
			if(paymoney  < 0 ){
				alertMessage("支付金额不能小于0");  
				return;
			}
			var billingName = $("#billingName_input").val();
			if(billingName  == "" ){
				alertMessage("商品名称不能为空");
				return;
			}
		}else if(bilitype == 2){
			var linkUrlInput =$("#linkUrlInput").val();
			if(linkUrlInput  == "" ){
					alertMessage("跳转地址不能为空");
					return;
				}
		}
		
		if($("#singleDay_input").val() == ""){
			$("#singleDay_input").val(0);
		}
		if($("#singlePeople_input").val() == ""){
			$("#singlePeople_input").val(0);
		}
		
		var reg = /^[0-9]*$/; 
		/*if(!reg.test($("#billingBasis").val())){alertMessage("福利标准标注必须是正整数");return;}else if(billingBasis==""){alertMessage("福利标准标注为必填项");return;}*/
		
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
            		$("#welfareStatus").val(data.welfareStatus);
            		type == "0" ? alertMessage("保存成功") : alertMessage("发布成功");
            	}else{
            		type == "0" ? alertMessage("保存失败") : alertMessage("发布失败"); 
            	}
		})
	}
	
	
	function checkBigFuli(obj){
		var rel = $(obj).val();
		var xuni = $("#welfareVirtualNum").val();
		$("#fuli_num").html(Number(rel) + Number(xuni))
		$("#fuli_remain").html(rel)
	  }
	  
	  function checkBigXuNi(obj){
	  		var rel = $("#welfareNum").val();
	  		var xuni = $(obj).val();
	  		$("#fuli_num").html(Number(rel) + Number(xuni))
	  }
	  
		  
	function imgStartTime(){
		$('#img_startTime').html($('#startTime').val());
		if($('#endTime').val()!=""){
			startclock($('#startTime').val(),$('#endTime').val());
		}
	}
	function imgEndTime(){
		if($('#startTime').val()!=""){
			startclock($('#startTime').val(),$('#endTime').val());
		}
		$('#img_endTime').html($('#endTime').val());
	}
	
	//等级分享
	$('#shareLevel').change(function(){
		$('#img_level').html($(this).find("option:selected").text());	
	});
	
	
	//查询福利企业
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
				welfareType = '<option value="" >请选择</option>';
	               $(data.welfareClassifications).each(function(i){
	            	   welfareType += '<option value="'+this.id+'" >'+this.name+'</option>';
	               });
	             $("#welfareType").html(welfareType);
	             if(typeof(welfareEntity)!="undefined"){
	             	$("#welfareType").val(welfareEntity.welfareType)
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
	
	
	//根据公司名称
	$('#companyId').change(function(){
	   var companyName = $(this).find("option:selected").text();
	   $("#companyName").val(companyName);
	});
	
	function changbilling(obj){
		var reg = /^[0-9]*$/;
		var nu = $(obj).val();
		if(!reg.test(nu)){
			alertMessage("请输入正整数");
			return;
		}
		$("#basis1").html($("#billingBasis").val());
	}
	
        var timerID = null;
        var timerRunning = false;  
        function show_time(startTime,endTime){
            var time_end= new Date(endTime)/1000;   
            //自定义结束时间  
            time_now = Math.round(new Date(startTime) / 1000)
            time_distance = time_end - time_now;
        if (time_distance > 0) {
            int_day = Math.floor(time_distance / (60 * 60 * 24));
            int_hour = Math.floor(time_distance / (60 * 60)) - (int_day * 24);
            int_minute = Math.floor(time_distance / 60) - (int_day * 24 * 60) - (int_hour * 60);
            int_second = Math.floor(time_distance) - (int_day * 24 * 60 * 60) - (int_hour * 60 * 60) - (int_minute * 60);
            if (int_hour < 10)
                int_hour = "0" + int_hour;
            if (int_minute < 10)
                int_minute = "0" + int_minute;
            if (int_second < 10)
                int_second = "0" + int_second;
            str_time = int_day + "天" + int_hour + "小时" + int_minute + "分钟" + int_second + "秒";
            if(int_day < 10){
	            $("#int_day").html("0"+int_day);
            }else{
        	    $("#int_day").html(int_day);
            }
            $("#int_hour").html(int_hour);
            $("#int_minute").html(int_minute);
            $("#int_second").html(int_second);
            timerID = setTimeout("show_time()", 1000);
            timerRunning = true;  

        }
        else {
            clearTimeout(timerID)
        }
    }
        
    var timerID = null;
    var timerRunning = false;
    function stopclock() {
        if (timerRunning)
        clearTimeout(timerID);
        timerRunning = false;
    }
    
    function startclock(startTime,endTime) {
        stopclock();
        show_time(startTime,endTime);
    }
   
   
    
    
  