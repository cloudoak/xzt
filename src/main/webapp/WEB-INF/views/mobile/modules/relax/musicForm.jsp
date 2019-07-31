<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/Support.css" /> 
<link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">  
<link rel="stylesheet" href="${ctxStatic}/mobile-css/LCalendar.css">  
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
 <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/LCalendar.js"></script>
    <title>${fns:getConfig('productName')} 音乐播放</title>
   <style type="text/css">
		.Song .SongControl .stop {background: url(${ctxStatic}/mobile-images/stop.png);background-size: 1.75rem;}
		 .Song .Songpic{top: 45%;}
	</style>
</head>
<body>
	<div class="Song" style="width:100%; overflow:hidden;">
		<div class="Header Headerparent">
			<a href="" class="Return"><i></i></a>
			<h1>${music.name }</h1>
		</div>
		<p class="name">${music.singer }</p>
		<div class="Songpic">
		<img src="" >
			<img src="${ctxStatic }/mobile-images/song1.jpg" width="100%" height="100%">
		</div>
		<div class="SongControl">
			<audio src="${ctxStatic}/mobile-images/chengdu.mp3" autoplay id="music" type="audio/${music.audioType}"></audio>
			<%-- <audio id="music" controls="controls">
			  	<source src="${fileAbsolutePath }" type="audio/${music.audioType}">
			</audio> --%>
			<div class="Controls_top">
				<div class="left">00:00</div>
				<div class="jindu">
					<div class="jindutiao" style="width:0%;"></div>
					<div class="jindubiao" style="left: 0;margin-left: 0;"></div>
				</div>
				<div class="right">03:45</div>
			</div>
			<div class="clear"></div>
			<div class="Controls_bottom">
				<div class="before" id="before"></div> 
				<div class="play stop" id="play"></div>
				<div class="next" id="next"></div>
			</div>
		</div>
  	</div>
</body>

<script>
	$(function(){
		var oAudio = $("#music");   //音乐播放标签元素
		var oPlaybackTime = $(".left");   //已经播放音乐的时间
		var oTotalTime = $(".right");         //音乐的总时间
		var oJindu = $(".jindu");        	//进度标外层的元素
		var oJindubiao = $(".jindubiao");    //播放音乐的进度标
		var oJindutiao = $(".jindutiao");     //播放音乐的进度条
		var oLast = $(".before").get(0);   //点击播放上一首的按钮
		var oPlay = $(".play");    //点击播放或暂停按钮
		var oNext = $(".next").get(0);    //点击播放下一首按钮

		document.getElementById("music").currentTime = 5;
		oAudio.get(0).addEventListener("canplaythrough",function(){

		    oPlay.click(function(){
				if(oAudio.get(0).paused){          //停止点击时播放
					oAudio.get(0).play();
					oAudio.get(0).paused == false;
					oPlay.addClass("stop");
					
				}else{                 //播放时点击停止
					oAudio.get(0).pause();
					oAudio.get(0).paused == true;
					oPlay.removeClass("stop");
				}
			});
		
			var oDuration = oAudio.get(0).duration;   //当前音频的总长度（秒）		
			var oCurrentTime = oAudio.get(0).currentTime;   //当前音频当前播放的长度（秒）
			var disparity = oJindu.width() - oJindubiao.width();    //进度标移动的最大距离
			var scale;         //拖拽的比例
			
			oTotalTime.html(timetransformation(oDuration));     //把音频总时间渲染在页面里
			
			setInterval(function(){
				oCurrentTime = oAudio.get(0).currentTime;   //音频播放到的时间
			   //	音频播放的比例
				scale = oCurrentTime/oDuration;        
				oPlaybackTime.html(timetransformation(oCurrentTime))
				oJindubiao.css('left', disparity*scale +"px");    //进度标的位置
				oJindutiao.css("width",disparity*scale +"px");    //进度条的宽度
			},1000);
		
		
			oAudio.get(0).ended=function(){   //音频播放结束时
				oPlay.removeClass("stop");
			}
		
		
		
			/*进度标拖拽移动效果*/
			var disX=0;
			
			
			oJindubiao.on("touchstart",function(event){
				
				disX=event.originalEvent.targetTouches[0].pageX-oJindubiao.offset().left;
	
				$(document).on("touchmove",function(event){
					var disXNow=event.originalEvent.targetTouches[0].pageX - disX - oJindu.offset().left;
					
				    if(disXNow > 0){	
				    	oJindubiao.css('left', disXNow+"px");    //进度标距离左边距离	
				    	//	移动的比例
				    	scale = disXNow/disparity;   

				    	if(scale>1){
				    		scale = 1
				    	}
				    	oJindutiao.css("width",disparity*scale +"px");    //进度条的宽度
				    	oPlaybackTime.html(timetransformation(oDuration*scale));   //左边显示播放的时间
//				    	oAudio.get(0).currentTime = oDuration*scale;
				    }
			    
				    if (disXNow > disparity) {
				    	oJindubiao.css('left', disparity+"px");
				    }
				 
				});
							
				$(document).on("touchend",function(){
					$(document).off();
					
				})
				
				return false;			 
			});

		},false);
				

	})
	function toDouble(num){    //把一位数字前面加0
		if(num<10){
		    return "0"+num;
		}else if(num > 9){
		    return num;
		}
	};
	function timetransformation(num){   		  //把秒数转化为分钟和秒
		var iM = toDouble(Math.floor(num/60));    //音频长度的分钟数
		var iS = toDouble(Math.floor(num%60));     //获取音频总长秒的时间
		var nowTime = iM + ":" +iS;
		return nowTime
	}
		
</script>
</html>