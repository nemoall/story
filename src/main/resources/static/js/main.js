var PATH =base;//域名或IP
var RESOURCEPATH = '';//资源库前缀
var OAPPID = '';
var ver="1.0";//版本号
var mode="developer";//online
var script={
	base:[
		PATH+"js/base/jquery.base.js",
		PATH+"js/base/jqueryui.js",
		PATH+"js/base/jqGrid.js",
		PATH+"js/base/validate.js"

	],
	widget:[
		PATH+"js/libs/dialog.js",
		PATH+"js/libs/gridUtil.js",
		PATH+"js/libs/formValidate.js",
		PATH+"js/libs/widget.js",
		PATH+"js/libs/base.js"

	]/*,
	mod:[
		PATH+"js/mod/propertyList.js"
	]*/
}
for(resource in script){
	for(var i=0,len=script[resource].length;i<len;i++){
		script[resource][i]=script[resource][i]+"?ver="+ver;
	}
}

//加载基础库
if(mode!="online"){
	head.load(script.base,function(){
			//组件相关
		head.load(script.widget,function(){
			//框架模块
			/*head.load(script.mod,function(){
				
			});*/
		})
	});
}else{
	head.load(script.base,function(){
		//组件相关
		head.load(["resource/libs/widget.min.js?ver="+ver,"resource/libs/mod.min.js?ver="+ver],function(){});
	});
}