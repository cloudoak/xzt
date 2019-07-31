<!-- 

注册信息

@author OAK 
@since 2017/12/19

-->
<%@page import="com.ambition.agile.modules.sys.entity.ThirdParty"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@page import="com.ambition.agile.modules.sys.entity.Office" %>
<%@page import="com.ambition.agile.modules.sys.entity.Dict" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>注册页</title>
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/Land.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
<style>
		.liborder {
		    border-bottom: 0px;
		    color: #999;
		    font-size: 0.6rem;
		    height: 2.2rem;
		    display: flex;
		    align-items: center;
		}
		.checkbox {
		    width: 0.9rem;
		    height: 0.9rem;
		    -webkit-appearance: checkbox;
		   margin: 0 0.2rem 0 1rem;
		}
	</style>
	<script src="${ctxStatic }/mobile-js/mobileSelect.js" type="text/javascript"></script>
	<script src="${ctxStatic }/mobile-js/jquery-min.js" type="text/javascript"></script>
</head>
<body>
<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>注册</h1>
	</div>
<form id="registorForm" action="${ctx}/register/save"  method="post">
          <input type="hidden" id="datamodel" name="datamodel" value="consultant" />
          <input type="hidden" id="enableSMS" name="enableSMS" value="${enableSMS }" />
          <input type="hidden" id="address" name="address" value="" />
  	<div class="main">
		<div class="Headerbg"></div>
		<ul class="registerNav">
			<li><a href="javascript:" class="on" data-model="consultant" href="#tab-1" aria-expanded="true">咨询师</a></li>
			<li><a href="javascript:" data-model="visitor" href="#tab-1" aria-expanded="false">来访者</a></li>
			<li><a href="javascript:" data-model="familyMembers" href="#tab-1" aria-expanded="false">家属</a></li>
		</ul>
		<div class="conBox">
	  
			<!--咨询师-->
			<ul class="registerList">
				<li>
					<div class="left">用户名</div>
					<input type="text" maxlength="10"  name = "account" placeholder="请输入用户名"/>
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">姓名</div>
					<input maxlength="10" type="text"  name="name"  placeholder="请输入姓名">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">密码</div>
					<input maxlength="15" type="password" name="password"  placeholder="请输入密码">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">确认密码</div>
					<input maxlength="15" type="password"  name="confirmpassword"  placeholder="请再次确认密码">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">昵称</div>
					<input maxlength="10" type="text"  name="nickName"  placeholder="请输入昵称">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">性别</div>
					<div class="gender">
						<label class="man">
							<input type="radio" name="sex"  ch value="1"><i>男</i>
						</label>
						<label class="woman">
							<input type="radio" name="sex"  value="0"><i>女</i>
						</label>
					</div>
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">民族</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择民族" id="trigger1" name="nationShow">
					<input type="hidden" id="nation1" name="nation">
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">出生日期</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择出生年月" id="trigger2" name="birthdayStr" >
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">联系电话</div>
					<input pattern="[0-9]*" maxlength="11" type="text" name="phone"   placeholder="请输入联系电话">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">短信验证</div>
					<input type="text" placeholder="请输入验证码" class="text1" name="sMSVerificationCode" >
					<input type="button" value="获取验证码" class="button" onClick="sendCode(this)" name="btnGetVerificationCode"  />
					<div class="xian"></div>
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">选择地区</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择您所在省／市／区（县）"  id="trigger3" name="province">
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">选择机构</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择您所在的机构" id="trigger4" name="organizationShow">
					<input type="hidden" id="organization1" name="organization">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">邮箱</div>
					<input pattern="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+" type="text" placeholder="请输入邮箱" name="email" >
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">职务</div>
					<input type="text" maxlength="10" placeholder="请输入职务" name="position" >
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">简介</div>
					<textarea placeholder="请输入自我简介" name="introduction" > </textarea>
					<div class="clear"></div>
				</li>
				<!--<li class="liborder"><input type="checkbox" class="checkbox"> 已阅读，并同意<a href="#">《心知堂用户注册协议》</a></li>-->
			</ul>
			
			
			
			
			<!--来访者-->
			<ul class="registerList" style="display: none;">
				<li>
					<div class="left">用户名</div>
					<input type="text" maxlength="10" name="account"  placeholder="请输入用户名">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">姓名</div>
					<input type="text" maxlength="10" name="name"  placeholder="请输入姓名">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">密码</div>
					<input type="password" maxlength="15" name="password"  placeholder="请输入密码">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">确认密码</div>
					<input type="password" maxlength="15" name="confirmpassword"  placeholder="请再次确认密码">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">昵称</div>
					<input type="text" maxlength="10" name="nickName"  placeholder="请输入昵称">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">性别</div>
					<div class="gender">
						<label class="man">
							<input type="radio" value="1" checked="checked" name="sex" ><i>男</i>
						</label>
						<label class="woman">
							<input type="radio" value="0" name="sex" ><i>女</i>
						</label>
					</div>
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">出生日期</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择出生年月" id="trigger22" name="birthday" > 
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">联系电话</div>
					<input pattern="[0-9]*" maxlength="11" type="text"  name="phone"  placeholder="请输入联系电话">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">短信验证</div>
					<input type="text" placeholder="请输入验证码" class="text1" name="sMSVerificationCode" >
					<input type="button" value="获取验证码" class="button"  name="btnGetVerificationCode"  onClick="sendCode(this)" />
					<div class="xian"></div>
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">选择地区</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择您所在省／市／区（县）"  id="trigger33" name="province">
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">选择机构</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择您所在的机构" id="trigger44" name="organization">
					<div class="clear"></div>
				</li>
				<!--<li class="liborder"><input type="checkbox" class="checkbox"> 已阅读，并同意<a href="#">《心知堂用户注册协议》</a></li>-->
			</ul>
			
			
			
			
			<!--家属-->
			<ul class="registerList" style="display: none;">
				<li>
					<div class="left">用户名</div>
					<input type="text" maxlength="10" placeholder="请输入用户名" name="acount">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">家属姓名</div>
					<input type="text" maxlength="10" placeholder="请输入姓名" name="name">
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">密码</div>
					<input type="password" maxlength="15" placeholder="请输入密码" name="password"> 
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">确认密码</div>
					<input type="password" maxlength="15" placeholder="请再次确认密码" name="confirmpassword" >
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">来访者用户名</div>
					<input type="text" maxlength="10" placeholder="请输入来访者用户名" name="visitorAccount" >
					<div class="clear"></div>
				</li><li>
					<div class="left">来访者手机号</div>
					<input pattern="[0-9]*" maxlength="11" type="text" placeholder="请输入来访者手机号" name="visitorPhone" >
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">性别</div>
					<div class="gender">
						<label class="man">
							<input type="radio" value="1" checked="checked" name="sex" ><i>男</i>
						</label>
						<label class="woman">
							<input type="radio" value="0" name="sex" ><i>女</i>
						</label>
					</div>
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">民族</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择民族" id="trigger111"  name="nation">
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">出生日期</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择出生年月" id="trigger222"  name="birthday" >
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">联系电话</div>
					<input pattern="[0-9]*" maxlength="11" type="text" placeholder="请输入联系电话" name="phone" >
					<div class="clear"></div>
				</li>
				<li>
					<div class="left">短信验证</div>
					<input type="text" placeholder="请输入验证码" class="text1" name="sMSVerificationCode" >
					<input type="button" value="获取验证码" class="button"  name="btnGetVerificationCode"  onClick="sendCode(this)" />
					<div class="xian"></div>
					<div class="clear"></div>
				</li>
				<li class="jiantou">
					<div class="left">选择地区</div>
					<input onfocus="this.blur()" type="text" placeholder="请选择您所在省／市／区（县）"  id="trigger333" name="province">
					<div class="clear"></div>
				</li>
			</ul>
		</div>
		<div class="liborder"><input type="checkbox" class="checkbox"/> 已阅读，并同意<a href="#">《心知堂用户注册协议》</a></div>
		<div class="button_2">
			<input type="submit" value="注册" class="buttoninput"  />
		</div>
		<input type="submit" style="display: none;" id="submitBtn" />
	</div>
	</form>
<script type="text/javascript">
	
	 //倒计时
	 var clock = '';
	 var nums = 60;
	 var btn;
	 function sendCode(thisBtn){ 
		 btn = thisBtn;
		 btn.disabled = true; 
		 btn.value = '重新获取'+nums+'s';
		 btn.style.color="#999";
		 clock = setInterval(doLoop, 1000); 
	}
	function doLoop(){
		 nums--;
		 if(nums > 0){
			  btn.value = '重新获取'+nums+'s';
			  btn.style.color="#999";
		 }else{
			  clearInterval(clock); 
			  btn.disabled = false;
			  btn.value = '获取验证码';
			  btn.style.color="#f3892d";
			  nums = 60; 
		 }
	 }
	 //弹出选择层
	 var nation1 = "${nationList}".replace('[','').replace(']','').split(','); 
	  var nation1Val = [<c:forEach items="${nationList}" var="item">${item.value},</c:forEach>];
	 
	/* var nation1=['汉族','壮族','满族','回族','苗族','维吾尔族','土家族']; */					
	/* var institution1 = ['中国秦山机构1','中国秦山机构2','中国秦山机构3','中国秦山机构4','中国秦山机构5','中国秦山机构6']; */
	var institution1 = "${offices}".replace('[','').replace(']','').split(',');
	var institution1Val = [<c:forEach items="${offices}" var="item">${item.id},</c:forEach>]; 
	var year1=['2017','2018','2019','2020','2021'];
	var month1=['12','11','10','9','8','7','6','5','4','3','2','1'];
	var day1=['31','30','29','28','27','26','25','24','23','22','21','20','19','18','17','16','15','14','13','12','11','10','9','8','7','6','5','4','3','2','1'];
	/* var province1 =['河北省','河北省','河北省','河北省','河北省']; */
	var province1= [];
	var privince1Val = new Array();
	<%
		List<ThirdParty> provinces =(List<ThirdParty>)request.getAttribute("provinces");
		for(ThirdParty t:provinces){
			%>
		province1.push('<%=t.getName()%>');
		privince1Val.push('<%=t.getId()%>');
			<%
		}
	%>
			/* province1 = "${provinces}".replace('[','').replace(']','').split(',');  */
   /*  var province1= [<c:forEach items="${provinces}" var="item">${item.name},</c:forEach>];
	alert(province1);
	 alert("222"); */
	/* var city1 =['石家庄','唐山','秦皇岛','邯郸','邢台','保定、张家口','承德','衡水','廊坊','沧州'];
	var county1 =['临漳县','成安县','大名县','涉 县','磁 县','肥乡县','永年县']; */
	var city1 =[""];
	var county1 =[""];
	
	var mobileSelect1 = new MobileSelect({
		trigger: '#trigger1', 
		title: '选择民族',  
		wheels: [
					{data: nation1}
				],
		position:[0], //初始化定位 打开时默认选中的哪个 如果不填默认为0
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			$("#nation1").val(nation1Val[indexArr]);
		}
	});
	
	 
	var mobileSelect2 = new MobileSelect({
		trigger: '#trigger2',
		title: '选择日期',
		wheels: [
					{data: year1},
					{data: month1},
					{data: day1}
				],
		position:[0, 4, 1], 
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			var dataVal = "";
			for(i =0;i<data.length;i++){
				if(i == 0){
					dataVal = data[i];
				}else{
					dataVal += "-"+data[i];
				}
			}
			$("#trigger2").val(dataVal);
			console.log(data);
		}
	});
	
	var mobileSelect3 = new MobileSelect({
		trigger: '#trigger3',
		title: '选择地区',
		wheels: [
					{data: province1},
					{data: city1},
					{data: county1}
				],
		position:[0, 0, 0], 
		transitionEnd:function(indexArr, data){
			alert("ttt");
			alert(indexArr);
			alert(data);
			console.log(data);
		},
		callback:function(indexArr, data){
			console.log(data);
		}
	});
	
	
	var mobileSelect4 = new MobileSelect({
		trigger: '#trigger4', 
		title: '选择机构',  
		wheels: [
					{data: institution1}
				],
		position:[0], //初始化定位 打开时默认选中的哪个 如果不填默认为0
		transitionEnd:function(indexArr, data){//下拉菜单停止滚动后执行
			
		},
		callback:function(indexArr, data){//点击确定后执行回调
			 $("#organization1").val(institution1Val[indexArr]); 
		}
	});
	$(function(){
		$(".registerNav li").click(function(){
			$(".registerNav li a").removeClass("on");
			$(this).children().addClass("on");
			var oIndex = $(this).index();
			$(".conBox ul").eq(oIndex).show().siblings().hide();
			
		})
	})
	
	
	
	
	
	
	
	
	
	
	
	
//	来访者，第二个
	 
	 //弹出选择层
	 
	var nation2=['汉族','壮族','满族','回族','苗族','维吾尔族','土家族'];					
	var institution2 = ['中国秦山机构1','中国秦山机构2','中国秦山机构3','中国秦山机构4','中国秦山机构5','中国秦山机构6'];
	var year2=['2017','2018','2019','2020','2021'];
	var month2=['12','11','10','9','8','7','6','5','4','3','2','1'];
	var day2=['31','30','29','28','27','26','25','24','23','22','21','20','19','18','17','16','15','14','13','12','11','10','9','8','7','6','5','4','3','2','1'];
	
	var province2 =['河北省','河北省','河北省','河北省','河北省'];
	var city2 =['石家庄','唐山','秦皇岛','邯郸','邢台','保定、张家口','承德','衡水','廊坊','沧州'];
	var county2 =['临漳县','成安县','大名县','涉 县','磁 县','肥乡县','永年县'];
	
	 
	var mobileSelect22 = new MobileSelect({
		trigger: '#trigger22',
		title: '选择日期',
		wheels: [
					{data: year2},
					{data: month2},
					{data: day2}
				],
		position:[0, 4, 1], 
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			console.log(data);
		}
	});
	
	var mobileSelect33 = new MobileSelect({
		trigger: '#trigger33',
		title: '选择地区',
		wheels: [
					{data: province2},
					{data: city2},
					{data: county2}
				],
		position:[0, 3, 1], 
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			console.log(data);
		}
	});
	
	
	var mobileSelect44 = new MobileSelect({
		trigger: '#trigger44', 
		title: '选择机构',  
		wheels: [
					{data: institution2}
				],
		position:[0], //初始化定位 打开时默认选中的哪个 如果不填默认为0
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			console.log(data);
		}
	});
	
		
		
		
		
		
		
		
		
		//	注册-家属，第三个
//倒计时
	 
	 //弹出选择层
	 
	var nation3=['汉族','壮族','满族','回族','苗族','维吾尔族','土家族'];					
	var institution3 = ['中国秦山机构1','中国秦山机构2','中国秦山机构3','中国秦山机构4','中国秦山机构5','中国秦山机构6'];
	var year3=['2017','2018','2019','2020','2021'];
	var month3=['12','11','10','9','8','7','6','5','4','3','2','1'];
	var day3=['31','30','29','28','27','26','25','24','23','22','21','20','19','18','17','16','15','14','13','12','11','10','9','8','7','6','5','4','3','2','1'];
	
	var province3 =['河北省','河北省','河北省','河北省','河北省'];
	var city3 =['石家庄','唐山','秦皇岛','邯郸','邢台','保定、张家口','承德','衡水','廊坊','沧州'];
	var county3 =['临漳县','成安县','大名县','涉 县','磁 县','肥乡县','永年县'];
	
	var mobileSelect1 = new MobileSelect({
		trigger: '#trigger111', 
		title: '选择民族',  
		wheels: [
					{data: nation3}
				],
		position:[0], //初始化定位 打开时默认选中的哪个 如果不填默认为0
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			console.log(data);
		}
	});
	 
	var mobileSelect222 = new MobileSelect({
		trigger: '#trigger222',
		title: '选择日期',
		wheels: [
					{data: year3},
					{data: month3},
					{data: day3}
				],
		position:[0, 4, 1], 
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			console.log(data);
		}
	});
	
	var mobileSelect333 = new MobileSelect({
		trigger: '#trigger333',
		title: '选择地区',
		wheels: [
					{data: province3},
					{data: city3},
					{data: county3}
				],
		position:[0, 3, 1], 
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			console.log(data);
		}
	});
	
	$(".buttoninput").click(function(){   //点击注册按钮提交
//		咨询师表单验证
		if($(".registerNav li").eq(0).children("a").hasClass("on")){
			
		};
//		来访者表单验证
		if($(".registerNav li").eq(1).children("a").hasClass("on")){
			
		};
//		家属表单验证
		if($(".registerNav li").eq(2).children("a").hasClass("on")){
			
		}
		$("#submitBtn").click();
	})
	 
	
</script>
</body>
</html>