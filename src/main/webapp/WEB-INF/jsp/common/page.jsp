<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
function jumpPage(page,_this){
	if(page<=0){
		page = 0;
	}else if(page>=${pages.totalPages-1}){
		page = ${pages.totalPages-1};
	}
	$(_this).parent().find("[name='page']").val(page);
	search();
}
function toPageValue(_this){
	var pageValueObj = $(_this).parent().find("[id=toPageValue]");
	var page = 0;
	if(pageValueObj.val()==""){
		alert("请填写页码");
		return;
	}
	page = parseInt(pageValueObj.val())-1;
	jumpPage(page,_this);
}
</script>
<div class="page_nav">
    <input type="hidden" name="page">
	<span class="page_info">第${pages.number+1}页，共${pages.totalPages}页，共${pages.totalElements}条</span>
	<a href="javascript:void(0);" onclick="jumpPage(0,this)" class="page_first">首页</a>
	<a href="javascript:void(0);" onclick="jumpPage(${pages.number-1},this)" class="page_null">上一页</a>
	<a href="javascript:void(0);" onclick="jumpPage(${pages.number+1},this)" class="page_txt">下一页</a>
	<a href="javascript:void(0);" onclick="jumpPage(${pages.totalPages-1},this)" class="page_first">尾页</a>
	<span>转到第<input id="toPageValue" onkeyup="this.value=this.value.replace(/\D/g,'')" onmouseout="this.value=this.value.replace(/\D/g,'')" type="text" value="${pages.number+1}">页</span>
	<a href="javascript:void(0);" onclick="toPageValue(this)">
		<span  class="blue_btn" style="padding: 4px 8px;background: #3986d8;color:#fff;border-radius: 4px;">确定</span>
	</a>
</div>
<style>

.page_nav{
    font-size: 14px;
    margin-top: 10px;
    width: 100%;
    text-align: right;
    margin-left: -13px;
}
.page_nav a{
	padding: 4px 8px;
   border-right:none;
}
.page_nav a:hover{
	background: #3986d8;
	color:#fff;
}

.countPage span{
border:none !important;
}
.blue_btn{
	background: rgb(87, 105, 168);
	color:#fff;
	border-radius: 2px;
}
#toPageValue{
	width:30px;
	height:25px;
	line-height:25px;
}

</style>