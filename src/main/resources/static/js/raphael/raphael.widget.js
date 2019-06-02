$.raphael=function(options){
		var self=this;
		var raphaelDfop={
			id:'',
			width:880,
			height:650,
			baseHeight:40,
			baseWidth:100,
			radius:250,
			color:'#3874C8',
			hoverColor:'#E86F00',
			url:'',
			nodeClick:function(thisDate){
				
			},
			moreClick:function(){
				//alert(1)
			}
		}
		$.extend(raphaelDfop,options);
//		alert(raphaelDfop.height);
		var that=new Raphael(raphaelDfop.id, raphaelDfop.width,raphaelDfop.height);
		
		that.connections=[];
		that.object=that.set();
		that.popup=function(options){
			var dfop={
				top:'50%',
				left:'50%',
				content:'',
				url:''
			};
			$.extend(dfop,options);
			if(dfop.thatDom){
				dfop.top=$(dfop.thatDom[0]).offset().top-$(that.getById(dfop.thatDom.nodeId)[0]).height()/2,
				dfop.left=$(dfop.thatDom[0]).offset().left+$(dfop.thatDom[0]).width()+23
			}
			var build=function(){
				var $dom;
				if($("#holderBoxPop")[0]){
					$dom=$("#holderBoxPop");
				}else{
					//alert(1);
					$dom=$('<div class="tips" id="holderBoxPop"><a href="javascript:;" class="tip-close">X</a><div class="tips-text"></div><div class="tips-angle diamond"></div></div>');
					$dom.find(".tip-close").click(function(){
						$("#holderBoxPop").fadeOut();
					})
					$dom.appendTo("body");
				}
				var popContent;
				if(dfop.url!=''){
					$.ajax({
						url:encodeURI(encodeURI(dfop.url)),
						type:'POST',
						dataType:'html',
						success:function(html){
							var zIndex;
							if($(".ui-dialog")[0]){
								zIndex=$(".ui-dialog").css("z-index")+1;
							}else{
								zIndex=9999;
							}
							$("#holderBoxPop").find(".tips-text").html(html).end().show().animate({
								top:dfop.top,
								left:dfop.left,
								'z-index':zIndex
							});
						}
					})
				}else{
					$("#holderBoxPop").find(".tips-text").html(dfop.content);
					$("#holderBoxPop").show().animate({
						top:dfop.top,
						left:dfop.left
					});
				}
			};
			build();
		}
		that.connection=function (obj1, obj2, line, bg,thisRelation,triangle) {
			if (obj1.line && obj1.from && obj1.to) {
				line = obj1;
				obj1 = line.from;
				obj2 = line.to;
			}
			var bb1 = obj1.getBBox(),
				bb2 = obj2.getBBox(),
				p = [{x: bb1.x + bb1.width / 2, y: bb1.y - 1},
				{x: bb1.x + bb1.width / 2, y: bb1.y + bb1.height + 1},
				{x: bb1.x - 1, y: bb1.y + bb1.height / 2},
				{x: bb1.x + bb1.width + 1, y: bb1.y + bb1.height / 2},
				{x: bb2.x + bb2.width / 2, y: bb2.y - 1},
				{x: bb2.x + bb2.width / 2, y: bb2.y + bb2.height + 1},
				{x: bb2.x - 1, y: bb2.y + bb2.height / 2},
				{x: bb2.x + bb2.width + 1, y: bb2.y + bb2.height / 2}],
				d = {}, dis = [];
			for (var i = 0; i < 4; i++) {
				for (var j = 4; j < 8; j++) {
					var dx = Math.abs(p[i].x - p[j].x),
						dy = Math.abs(p[i].y - p[j].y);
					if ((i == j - 4) || (((i != 3 && j != 6) || p[i].x < p[j].x) && ((i != 2 && j != 7) || p[i].x > p[j].x) && ((i != 0 && j != 5) || p[i].y > p[j].y) && ((i != 1 && j != 4) || p[i].y < p[j].y))) {
						dis.push(dx + dy);
						d[dis[dis.length - 1]] = [i, j];
					}
				}
			}
			if (dis.length == 0) {
				var res = [0, 4];
			} else {
				res = d[Math.min.apply(Math, dis)];
			}
			var x1 = p[res[0]].x,
				y1 = p[res[0]].y,
				x4 = p[res[1]].x,
				y4 = p[res[1]].y;
			dx = Math.max(Math.abs(x1 - x4) / 2, 10);
			dy = Math.max(Math.abs(y1 - y4) / 2, 10);
			var x2 = [x1, x1, x1 - dx, x1 + dx][res[0]].toFixed(3),
				y2 = [y1 - dy, y1 + dy, y1, y1][res[0]].toFixed(3),
				x3 = [0, 0, 0, 0, x4, x4, x4 - dx, x4 + dx][res[1]].toFixed(3),
				y3 = [0, 0, 0, 0, y1 + dy, y1 - dy, y4, y4][res[1]].toFixed(3);
			function getArr(x1, y1, x2, y2, size) {//设置三角
				var angle = Raphael.angle(x1, y1, x2, y2);//得到两点之间的角度
				var a45 = Raphael.rad(angle - 35);//角度转换成弧度
				var a45m = Raphael.rad(angle + 35);
				var x2a = x2 + Math.cos(a45) * size;
				var y2a = y2 + Math.sin(a45) * size;
				var x2b = x2 + Math.cos(a45m) * size;
				var y2b = y2 + Math.sin(a45m) * size;
				var result = ["M", x1, y1, "L", x2, y2, "L", x2a, y2a, "M", x2, y2, "L", x2b, y2b];
				return result;
			}
			var path;
			if(triangle==true){
				path = getArr(x1,y1,x4,y4,15);
			}else{
				path= ["M", x1.toFixed(3), y1.toFixed(3), "C", x2, y2, x3, y3, x4.toFixed(3), y4.toFixed(3)].join(",");
			}
			if (line && line.line) {
				line.bg && line.bg.attr({path: path});
				line.line.attr({path: path});
				if(line.relation){//add motion
					line.relation.attr({
						x:(x1+x4)/2,
						y:(y1+y4)/2
					})
				}
			} else {
				var color = typeof line == "string" ? line : "#000";
				var connection={
					//bg: bg && bg.split && this.path(path).attr({stroke: bg.split("|")[0], fill: "none", "stroke-width": bg.split("|")[1] || 3}),
					//line: this.path(path).attr({stroke: color, fill: "none"}),
					line:this.path(path).attr({'stroke-width':1,'stroke-opacity':0.5,fill: "none",stroke: color}),
					from: obj1,
					to: obj2
				}
				if(thisRelation){//init motion
					connection.relation=thisRelation.attr({
						x:(x1+x4)/2,
						y:(y1+y4)/2
					})
				}
				that.connections.push(connection);
				that.object.push(connection);
				return connection;
			}
		};//连线
		that.dragger = function (e) {
			this.ox = this.type == "rect" ? this.attr("x") : this.attr("cx");
			this.oy = this.type == "rect" ? this.attr("y") : this.attr("cy");
			this.animate({"fill-opacity": .7}, 500);
		};
		that.move = function (dx, dy) {
			var att = this.type == "rect" ? {x: this.ox + dx, y: this.oy + dy} : {cx: this.ox + dx, cy: this.oy + dy};
			var thatAttrs=this.attrs;
			var id=this.id;
			var linkText=that.getById("text"+id);
			var motionText=that.getById("motion"+this.id);
			this.attr(att);
			if(linkText){
				var thisAtt={
					x:att.x+thatAttrs.width/2,
					y:att.y+thatAttrs.height/2
				};
				$.extend(true,att,thisAtt);
				linkText.attr(att);
			}
			for (var i = that.connections.length; i--;) {
				that.connection(that.connections[i]);
			}
			that.safari();
		};
		that.up = function () {
			this.animate({"fill-opacity": 1}, 500);
		};
		that.createNode=function(id,x, y, width, height,r){
			var thatRect=that.rect(x, y, width, height,r).attr({fill: '#3874C8', "stroke-width":0,stroke:raphaelDfop.color, cursor: "hand"});
			thatRect.id=id;
			that.object.push(thatRect);
			return thatRect;
		};
		that.createFamilyText=function(id,x,y,name){
			var thisFamilyText=that.text(x, y, name).attr({font: 'normal 12px "microsoft yahei", Arial',fill: '#000',cursor:'hand'});
			thisFamilyText.id="text"+id;
			thisFamilyText.nodeId=id;
			return thisFamilyText;
		};
		that.createText=function(id,x,y,name,indexType){
			var thatText=that.text(x, y, name).attr({font: 'bold 12px "microsoft yahei", Arial',fill: '#000',cursor:'hand'}).mouseover(function(){//绑定指向事件
				var thisDate={
					id:this.id,
					type:this.nodeType
				}
				if(that.getById(this.nodeId).type=='rect'){
					if(this.glows!=undefined){
						this.glows.show();
					}else{
						this.glows=that.getById(this.nodeId).glow({color:'#3874C8',width:7});
					}
				}
				raphaelDfop.nodeTextMouseover(that,thisDate);
			}).mouseout(function(){//绑定鼠标移出事件
				var thisDate={
					id:this.id,
					type:this.nodeType
				}
				if(that.getById(this.nodeId).type=='rect'){
					this.glows.hide();
				}
				raphaelDfop.nodeTextMouseout(that,thisDate);
			});
			thatText.id="text"+id;
			thatText.nodeId=id;
			thatText.indexType=indexType;
			that.object.push(thatText);
			return thatText;
		};
		that.createMoreBtn=function(id,x,y,name){
			var thatMoreBtn=that.text(x, y, name).attr({font: 'bold 12px "microsoft yahei", Arial',fill: '#fff',cursor:'hand'}).mouseover(function(){//绑定指向事件
				var thisDate={
					id:this.id,
					type:this.nodeType
				}
				if(that.getById(this.nodeId).type=='rect'){
					if(this.glows!=undefined){
						this.glows.show();
					}else{
						this.glows=that.getById(this.nodeId).glow({color:'#3874C8',width:7});
					}
				}
			}).mouseout(function(){//绑定鼠标移出事件
				var thisDate={
					id:this.id,
					type:this.nodeType
				}
				if(that.getById(this.nodeId).type=='rect'){
					this.glows.hide();
				}
			}).click(function(){//创建文本并绑定click事件
				var thisDate={
					id:this.nodeId,
					type:this.nodeType,
					indexType:this.indexType,
					tableName:this.tableName
				}
				raphaelDfop.moreClick(that,thisDate,this);
			});
			thatMoreBtn.id="more"+id;
			thatMoreBtn.nodeId=id;
			that.object.push(thatMoreBtn);
			return thatMoreBtn;
		};
		that.createRelation=function(id,x,y,name){
			var thatRelation=that.text(x, y, name).attr({font:'bold 16px "microsoft yahei", Arial',cursor:'hand'});
			thatRelation.id="relation"+id;
			that.object.push(thatRelation);
			return thatRelation;
		};
		that.createImage=function(src,x,y,w,h){
			var thatImage=that.image(src,x, y,w,h);
			
			//thatMotion.id="motion"+id;
			return thatImage;
		};
		that.createEllipse = function(x, y, width, height){
			return that.ellipse(x, y, width, height);
		};
		
		that.buildNode=function(holder,thisData){
			var dfop={
				height:25,
				centerX:$("#holderBox").width()/2-50,
				centerY:$("#holderBox").height()/2
			}
			$.extend(dfop,thisData);
			function calculateSize(title,singleByteWidth){
				var singleWidth= singleByteWidth==undefined ? 10: singleByteWidth;
				var maxSize=0;
				var thisTitleArr=title.split("\n");
				for(var j=0;j<thisTitleArr.length;j++){//计算最大写符
					if(maxSize<thisTitleArr[j].length){
						maxSize=thisTitleArr[j].length;
					}
				}
				return {
					maxSize:maxSize,
					titleEnterLength:thisTitleArr.length,
					childBoxWidth:(maxSize*singleWidth+30),//最大字符数乘以单字节宽度+误差
					childBoxHeight:boxHeight*thisTitleArr.length
				}
			}
			var boxWidth=calculateSize(thisData.title).childBoxWidth;//根据字符长度计算宽度
			var boxHeight=dfop.height;
			var radius=raphaelDfop.radius;
			var childData=thisData.childNodes;
			var length=childData.length;
			var angle=360/length;
			var centerX=dfop.centerX;
			var centerY=dfop.centerY;
			
			var buildFamilyName=function(id,x,y,title){
				var thisFamilyText=holder.createFamilyText(id,x,y,title).click(function(){
					
				});
				return thisFamilyText;
			}
			
			var buildName=function(id,x,y,title,indexType){
				var thisText=holder.createText(id,x,y,title,indexType).click(function(){
					
				});
				return thisText;
			}
			var buildMoreBtn=function(id,x,y,title){
				var thisMoreBtn=holder.createMoreBtn(id,x,y,title).click(function(){
					
				});
				return thisMoreBtn;
			}
			var buildRelation=function(id,x,y,motion){
				var thisRelation=holder.createRelation(id,x,y,motion);
				return thisRelation;
			}
			var parentBox=holder.createNode(thisData.id,centerX,centerY, calculateSize(thisData.title,14).childBoxWidth, calculateSize(thisData.title,15).childBoxHeight+20,3).drag(holder.move, holder.dragger, holder.up).attr({cursor: "move",fill:'#E86F00'});
			var parentText=buildFamilyName(thisData.id,centerX+boxWidth/2+10,centerY+calculateSize(thisData.title).childBoxHeight/2+14,thisData.title).attr({font: 'bold 16px "microsoft yahei", Arial',fill: '#fff'});
			
			for(var i=0;i<length;i++){
				var childBoxWidth=calculateSize(childData[i].title).childBoxWidth;
				var childBoxHeight=calculateSize(childData[i].title).childBoxHeight;
				var x=centerX+radius*Math.cos(angle*Math.PI/180*(i+1))-(childBoxWidth/2-boxWidth/2);//x轴坐标
				var y=centerY+radius*Math.sin(angle*Math.PI/180*(i+1))-(childBoxHeight/2-boxHeight/2);//y轴坐标
				var thisBox=holder.createNode(childData[i].id,x, y, childBoxWidth, childBoxHeight+30,3).attr({title:childData[i].title}).click(function(e){//创建盒子
					var thisDate={
						id:this.id,
						type:this.nodeType
					}
					raphaelDfop.nodeClick(holder,thisDate,this);
				});
				that.rect(x+5,y+5,calculateSize(childData[i].title).childBoxWidth-10,calculateSize(childData[i].title).childBoxHeight,3).attr({fill: '#ffffff', "stroke-width":0});
				var thisText=buildName(childData[i].id,x+childBoxWidth/2,y+childBoxHeight/2+5,childData[i].title,childData[i].indexType).click(function(){//创建文本并绑定click事件
					var thisDate={
						id:this.nodeId,
						type:this.nodeType,
						indexType:this.indexType
					}
					raphaelDfop.nodeTextClick(holder,thisDate,this);
				})
				var thisMoreBtn=buildMoreBtn(childData[i].id,x+childBoxWidth/2,y+childBoxHeight+15,'查看详细');
				var thisRelation=buildRelation(childData[i].id,centerX,centerY,childData[i].type);//创建关系
				//初始化参数
				
				thisBox.nodeType=childData[i].type;
				thisText.nodeType=childData[i].type;
				thisMoreBtn.nodeType=childData[i].type;
				thisMoreBtn.indexType=childData[i].indexType;
				thisMoreBtn.tableName=childData[i].tableName;
				holder.connection(parentBox,thisBox,"#333",'',thisRelation);
			}
		}
		if(raphaelDfop.url!=''){
			$.ajax({
				url:raphaelDfop.url,
				success:function(data){
					that.buildNode(that,data);
				}
			})
		}else{
			that.buildNode(that,raphaelDfop.data);
		}
		return that;
	}