$(function(){
	//prop
	$(".top-menu li").hover(function(){
		$(this).addClass("cur").find('.drop-down-layer').show();
	},function(){
		$(this).removeClass("cur").find('.drop-down-layer').hide();
	});
	
	$(".topModule a").click(function(){
		var self = $(this);
        var id = self.attr("moduleId");
		asyncOpenMenu(id);
	});
	
	//左边菜单点击
	$("#leftMenu dd a").click(function(){
		var self = $(this);
        document.title = self.text();
       	$("#baseLine").nextAll(":not(.ui-autocomplete):not('.ui-datepicker')").remove();
       	$("#baseLine").nextAll(":not(.ui-autocomplete):not('.ui-datepicker'):hidden").remove();
        $(this).addClass("on").parents("dd").siblings().find("a").removeClass("on");
    });
	
	$("#operArea .tc-15-dropdown").toggle(function(){
		$(this).addClass('tc-15-menu-active');
	},function(){
		$(this).removeClass('tc-15-menu-active');
	});
	
	$(document).click(function(){
		if(!$(this.activeElement).hasClass("tc-15-dropdown-link")){
			$(".tc-15-dropdown").removeClass('tc-15-menu-active');
		}		
	});
	

	$.layout();

});

var loadMenu =function(id){
	 return $.ajax({
	        type: "get",
	        url: "/"+id+"/menu",
	        dataType: "json",
	        contentType: "application/json"
	})
}

function asyncOpenMenu(id) {
	if(id==undefined || id==''){
		$.messageBox({message:'系统地址出错，请联系管理员',level:'error'});
		return;
	}

    loadMenu(id).then(function (result) {
        console.log(result);
        if (result) {
            console.log(result);

            var menu = "<a href='javascript:;'>" + result.permissionName + "</a>";
            menu += '<dl class="layui-nav-child" >';

            $(result.menu).each(function (index, element) {
                menu += '<dd>';
                menu += '<a class="menu-lv2 " parent="' + element.parent_uid + '" data-event="nav"';
                menu += ' href="/' + element.view_url + '" ><span>' + element.permission_name + '</span></a>';
                menu += '</dd>';
            });
            menu += '</dl>';
            $("#leftMenu").empty().html(menu);
            window.location.href="/"+result.menu[0].viewUrl;
        }
    });

}

//$("body").on("click","#leftMenu a[data-event]",function(e){
//	location.href=$(this).attr("href");
//})
function validateSuccessData(data){
	var isSuccess = (data && data.code ==200);
	if(!isSuccess){
		$.messageBox({
             level : 'warn',
             message : data.errorMsg||"出错了~"
        });
		return ;
	}
	return isSuccess;
}


function stateFormatter(el,options,row){
	switch(row.state){
		case "TYP_DELETED":
			return "已删除";
		case "TYP_DISABLE":
			return"禁用";
		case "TYP_ENABLE":
			return "启用";
		default:
		  return "--";
	}
}
