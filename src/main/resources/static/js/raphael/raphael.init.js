$(function(){
	$(".viewtabs .tabNav li").click(function(){
		var index=$(this).index();
		$(this).addClass("cur").siblings().removeClass("cur");
		$(".viewTabsCtt .viewContent").eq(index).show().siblings().hide();
	})
})