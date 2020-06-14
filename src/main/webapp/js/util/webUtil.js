$(function(){
	
    $.ajax({
        type: 'post',
        data:{"mode":"2", "userId":getUser().id},
        url: 'http://test1.kexinapp.com/PD_Data/terminal/data',
        dataType: "jsonp"
 	 });


})