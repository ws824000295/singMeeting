<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<title>创建会议</title>
	<meta content="yes" name="apple-mobile-web-app-capable" />
	<meta content="yes" name="apple-touch-fullscreen" />
	<meta content="telephone=no,email=no" name="format-detection" />
	<link rel="shortcut icon" href="${ctx}/images/glodon.jpg" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery.confirm/jquery.confirm.css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.confirm/jquery.confirm.js"></script>
	<script type="text/javascript" src="${ctx}/js/layer/layer.js" ></script>
	<script type="text/javascript" src="${ctx}/js/util/util.js"></script> 
	
<style type="text/css">
* {
	padding: 0;
	margin: 0;
	border: 0;
	box-sizing: border-box;
}
.head{
font-size:18px;
height:50px;
line-height:50px;
padding:0 20px;
background: #0747a6;
    color: #deebff;
}
.page-box {
	width: 100%;
	height: auto;
	overflow: hidden;
	margin: 0 auto;
}

.add-btn-box {
	width: 100%;
	padding :10px 20px;
}

button {
	cursor: pointer;
	-webkit-appearance: none;
	text-align: center;
	outline: 0;
	padding: 12px 20px;
	font-size: 14px;
	border-radius: 4px;
	color: #fff;
	background-color: #409eff;
}

.checkbox-box {
	width: 100%;
	height: auto;
	overflow: hidden;
	background: #f3f3f3;
	padding: 20px 25px;
	font-size: 14px;
	align-items: center;
}

.checkbox-box span {
	margin: 0 20px 0 6px;
	color: #222;
}

.table-box {
	width: 100%;
	height: auto;
	overflow: hidden;
	padding:0 20px;
}

.table-box table{
	width:100%;
	border:1px solid #ebeef5;
	 border-spacing: 0;
    border-collapse: collapse;
}



.table-box thead th{
	background: #f3f3f3;
	
}

.table-box table th,.table-box table td{
    padding: 10px 5px;
border:1px solid #ebeef5;
font-size:12px;
}

.table-box table tbody tr:hover td{
background: #f5f7fa;
}


.btn-sm {
	cursor: pointer;
	-webkit-appearance: none;
	text-align: center;
	outline: 0;
	font-size: 12px;
	border-radius: 4px;
	color: #409eff;
	background: #fff;
	text-decoration: underline;
	padding: 6px;
}

#loading {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.7);
	z-index: 15000;
}
 
#loading img {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 80px;
	height: 80px;
	margin-top: -15px;
	margin-left: -15px;
}
.option-left{
float:left
}
.option-right{
float:right
}
 .clearfix:after,.clearfix:before{
        content: "";
        display: table;
    }
    .clearfix:after{
        clear: both;
    }
    .clearfix{
        *zoom: 1;
    }

</style>
</head>
<script type="text/javascript">
		
			 
	//创建会议
	 function createMeeting(){
		 var ids = [];
		 $('input[name="checkbox"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
			 ids.push($(this).val());//将选中的值添加到数组chk_value中    
         });
		 if(ids.length < 2){
			 alert("至少2台终端进会!");
			 return;
		 }
		 var meetName = $("#meetName").val();
		 if(meetName == "" || meetName.trim() == 0){
			 alert("请填写会议名称!");
			 return;
		 }
		 $('#loading').show();
		 toAjaxJson("single/meeting/createMeeting",{"ids":ids.toString(),"meetName":meetName},function(data){
			 if(data.success){
				 $('#loading').hide();
				 window.location.reload();
			 }else{
				 $('#loading').hide();
				 alert("创建异常");
			 }
		}); 
	}
	
	//关闭会议
	function closeMeeting(id){
	 	$("#confirmOverlay").css("display","block");
		confirm("确定要结束会议吗？",function(){
			 $("#confirmOverlay").css("display","none");
			 $('#loading').show();
			  toAjaxJson("single/meeting/closeMeeting",{"id":id},function(data){
				 if(data.success){
					 window.location.reload();
				 }
			}); 
		});
	}
	
	//条件查询会议
	function queryMeetingByParam(){
		var param = $("#queryParam").val();
		$("#dataTable").empty();
		toAjaxJson("single/meeting/queryMeetingByParam",{"param":param},function(data){
			 if(data.success){
				 for (var i=0 ; i<data.listMeet.length ; i++){
					 var index = i + 1;
					 var meet = data.listMeet[i];
					 var  html = '';
						 html += '<tr>';
						 html += 	'<td width="100" align="center">'+index+'</td>';
						 html += 	'<td>'+meet.meetName+'</td>';
						 html += 	'<td width="220" align="center">'+meet.createTimeBak+'</td>';
						 html += 	'<td width="160" align="center">'+meet.liveTime+'</td>';
						 html += 	'<td width="100" align="center">';
						 if(meet.status == "1"){
							 html += " <button class='btn-sm' type='button' onclick='downLoad(\""+meet.id+"\")'>下载</button> "; 
						 }else{
							 html += " <button class='btn-sm' type='button' onclick='closeMeeting(\""+meet.id+"\")'>关闭会议</button> "; 
						 }
					 	 html += '</td>';
					 	 $("#dataTable").append(html);
				 }
			 }
		}); 
	}
	
	//下载
	function downLoad(id){
		window.location.href = ctx+ "/single/meeting/downLoadMeet?meetId="+id;
	}
	
	function quxiao() {
		$("#confirmOverlay").css("display","none");
	}
	
</script>
<body>
<div class="head">北京市虚拟评标会议系统</div>
		<div class="page-box">
			
			<div id="loading" style="display: none;">
			    <img src="${ctx}/images/loading.gif">
			</div>
			
			<div class="checkbox-box">
				<div>
				<label>选择参会方：</label>
					<c:forEach items="${list}" var="device" varStatus="">
						<label>
							<input class="checkbox-item" type="checkbox"   name="checkbox" value="${device.id}" <c:if test="${device.state == 1}">  disabled="true" </c:if>  />
							<span>${device.region_name}</span>
						</label>
					</c:forEach>
				</div>
				<div>
					<label>填写会议名称： <input type="text" id="meetName" style="width: 280px;height: 40px;"></label>
					<button type="button" onclick="createMeeting();">新建会议</button>
				</div>
			</div>
			
			<div class="add-btn-box clearfix">
				<div class="option-right">
					<label><input type="text" id="queryParam"  placeholder="请输入会议名称" style="width: 280px;height: 40px; background-color: #f3f3f3;"></label>
					<button type="button" onclick="queryMeetingByParam();">查询</button>
				</div>
			</div>
			
			
			<div class="table-box">
			<table>
				<thead>
					<tr>
						<th width="100" align="center">序号</th>
						<th align="left">会议名称</th>
						<th width="220" align="center">创建时间</th>
						<th width="160" align="center">持续时间</th>
						<th width="100" align="center">操作</th>
					</tr>
				</thead>
				<tbody id="dataTable">
				<c:forEach items="${listMeet}" var="meet" varStatus="m">
					<tr>
						<td width="100" align="center">${m.index+1}</td>
						<td>${meet.meetName}</td>
						<td width="220" align="center">${meet.createTime}</td>
						<td width="160" align="left">${meet.liveTime}</td>
						<td width="100" align="center">
							<c:if test="${meet.status eq '1'}">
								<button class="btn-sm" type="button" onclick="downLoad('${meet.id}')">下载</button>
						 	</c:if>
						 	<c:if test="${meet.status eq '0'}">
							 	<button class="btn-sm" type="button" onclick="closeMeeting('${meet.id}')">结束会议</button>
						 	</c:if>
						 </td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
	</body>
</html>