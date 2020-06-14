<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">

$(function(){
	    $(".showItems").prepend('<option value="'+${pages.size}+'" selected> '+${pages.size}+'</option>'); 
	  
	    $(".showItems option").each(function() {
          text = $(this).text();
          if($("select option:contains("+text+")").length > 1)
              $("select option:contains("+text+"):gt(0)").remove();
       });
	   
	    if(${pages.number+1}==1){
	    	
	    	$(".last-page").addClass("disabled");
	    }
	    if(${pages.number+1}==${pages.totalPages}){	  
	    	
	    	$(".next-page").addClass("disabled");
	    }
	   
})

function jumpPages(page,_this){
	if(page<=0){
		page = 0;
	}else if(page>=${pages.totalPages-1}){
		page = ${pages.totalPages-1};
	}
	
	$(_this).parent().find("[name='page']").val(page);
	search();
}
function toPageValues(_this){
	var pageValueObj = $(_this).parent().find("[id='toPageValue']");
	var page = 0;
	if(pageValueObj.val()==""){
		alert("请填写页码");
		return;
	}
	page = parseInt(pageValueObj.val())-1; 
	jumpPages(page,_this); 
}
</script>
<div class="">
	 <input type="hidden" id="comPage"  name="page" value="${pages.number}"/>
	 <span>共有 ${pages.totalElements}条 记录，每页显示</span>
     <select name="showPage" class="showItems" >
         <option value="5">5</option>
         <option value="10">10</option>
         <option value="15">15</option>
     </select>
     <i class="last-page changePage " onclick="jumpPages(${pages.number-1},this)" >&lt;</i>
     <span>${pages.number+1}/${pages.totalPages}</span>
     <i class="next-page changePage" onclick="jumpPages(${pages.number+1},this)">&gt;</i>
     <input type="text" class="turnPages" value="${pages.number+1}" id="toPageValue" onkeyup="this.value=this.value.replace(/\D/g,'')"  onmouseout="this.value=this.value.replace(/\D/g,'')" >
     <input type="button" name="" value="跳转" class="turnPages-btn" onclick="toPageValues(this)">
</div>
