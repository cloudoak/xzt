<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评测结果管理</title>
<meta name="decorator" content="default" />
<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<link
	href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0"
	rel="stylesheet">
<link
	href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.ztree {
	overflow: auto;
	margin: 0;
	_margin-top: 10px;
	padding: 10px 0 0 10px;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="example-title">我的对比图</h4>
						<form class="form-horizontal m-t" id="graphForm" action="${ctx}/jzmk/circle/showGraph" method="post">
						<div class="form-group">
							<label class="col-sm-3 control-label">对比地区：</label>
							<div class="col-sm-8">
								<select id="province" class="form-control" name="province">
										<option value="">请选择你所在的城市/省</option>
										<c:forEach items="${provinces}" var="province">
											<option value="${province.code}">${province.name}</option>
										</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
		                     <label class="col-sm-3 control-label"><br/></label>
		                     <div class="col-sm-8">
		                     	 <br/>
			                     <select id="city" class="form-control" name="city">
								 </select>
							 </div>
		                </div>
		                <div class="form-group">
		                     <label class="col-sm-3 control-label"></label>
		                     <div class="col-sm-8">
		                     	 <br/>
			                     <select id="area" class="form-control" name="area">
								 </select>
							 </div>
		                </div>
						<div class="form-group">
							<label class="col-sm-3 control-label">测评时间：</label>
							<div class="col-sm-8">
								<fieldset>
								<input name="startTime" id="startTime" type="text" readonly="readonly"
										maxlength="20" class="input-medium Wdate col-sm-4"
										value="<fmt:formatDate value="<%=new java.util.Date(System.currentTimeMillis()-7*24*3600*1000)%>" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-{%d-1}'});"/>
								<input	name="endTime" id="endTime" type="text" readonly="readonly" maxlength="20"
										class="input-medium Wdate col-sm-4 col-sm-offset-1"
										value="<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'});"/>
								</fieldset>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-8 col-sm-offset-3">
								<button id="btnQuery" class="btn btn-primary"
									type="submit">查看</button>
								<button id="btnQuery" class="btn btn-primary"
									onclick="javascript:gotoPage();">返回</button>
							</div>
						</div>
						</form>
						<c:if test="${showGraph==1}">
						<div class="example">
                        	<h4 class="col-sm-12"></h4>
                            <div id="chart1" class="col-sm-12 col-sm-offset-2" style="width: 600px;height:400px;">
                            </div>
                        </div>
                        <div class="form-group">
                        	<div class="col-sm-8 col-sm-offset-3">
                       			 <!-- <div class="bdsharebuttonbox" data-tag="share_1">
									 <a class="bds_mshare" data-cmd="mshare"></a>
									 <a class="bds_qzone" data-cmd="qzone" href="#"></a>
									 <a class="bds_tsina" data-cmd="tsina"></a>
									 <a class="bds_baidu" data-cmd="baidu"></a>
									 <a class="bds_weixin" data-cmd="weixin"></a>
									 <a class="bds_tqq" data-cmd="tqq"></a>
									 <a class="bds_more" data-cmd="more">更多</a>
									 <a class="bds_count" data-cmd="count"></a>
								</div> -->
								<div class="bshare-custom">
								<a title="分享到QQ空间" class="bshare-qzone"></a>
								<a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
								<a title="分享到微信" class="bshare-weixin"></a>
								<a title="分享到腾讯微博" class="bshare-qqmb"></a>
								<a title="分享到网易微博" class="bshare-neteasemb"></a>
								<a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
								<span class="BSHARE_COUNT bshare-share-count">0</span>
								</div>
							</div>
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<!-- End Panel Basic -->
	</div>
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
	<script src="${hplusStatic }/js/plugins/layer/layer.min.js"
		type="text/javascript"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
	<!-- <script src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8" type="text/javascript"></script> -->
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script
		src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"
		type="text/javascript"></script>
	<script src="${ctxStatic}/echarts/echarts.js"></script>
	<script type="text/javascript">
		//${ctx}/sys/delete?id=${office.id}
		 $(document).ready(function(){
        	var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
        			callback:{onClick:function(event, treeId, treeNode){
        					var id = treeNode.id == '0' ? '' :treeNode.id;
        					/* $('#officeContent').attr("src","${ctx}/sys/user/list?office.id="+id+"&office.name="+treeNode.name); */
        					$('#officeIdInput').attr("value",id);
        				}
        			}
        		};
        	$.getJSON("${ctx}/sys/office/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(false);
			});
        	$(window).on("load", function(){
        	$("#province").on("change", function(){
				var provinceId = $(this).val();
				$.ajax({
					url: '${ctx}/jzmk/circle/findByProvinceId',
					type: 'get',
					dataType: 'json',
					data: {'provinceId': provinceId},
				})
				.done(function(data) {
					$("#city").html("");
					$("#city").append(new Option('请选择你所在的管辖区/街道', ''));
					$("#area").html("");
				$("#area").append(new Option('请选择你所在的镇/乡/村', ''));
					for(var i = 0 ; i < data.length ;i++){
						var option = new Option(data[i].name, data[i].code);
						$("#city").append(option);
					}
				})
				.fail(function() {
					console.log("error");
				})
				.always(function() {
					console.log("complete");
				});
			});
			
			$("#city").on("change", function(){
				var cityId = $(this).val();
				$.ajax({
					url: '${ctx}/jzmk/circle/findByCityId',
					type: 'get',
					dataType: 'json',
					data: {'cityId': cityId},
				})
				.done(function(data) {
					$("#area").html("");
				$("#area").append(new Option('请选择你所在的镇/乡/村', ''));
					for(var i = 0 ; i < data.length ;i++){
						var option = new Option(data[i].name, data[i].code);
						$("#area").append(option);
					}
				})
				.fail(function() {
					console.log("error");
				})
				.always(function() {
					console.log("complete");
				});
			});
        	});
        	
        	$("#graphForm").validate({
   	    		errorElement: 'div',
   	    		errorClass: 'help-block',
   	    		focusInvalid: false,
   	    		rules: {
   		    		area: {
   		    			required: true
   		    		}
   		    	},messages: {
   		    		
   		    		area: {
   		    			required: "所在区必填！"
   		    		}
   		    	},
   		    	invalidHandler: function (event, validator) { //display error alert on form submit   
   					$('.alert-danger', $('.login-form')).show();
   				},
   				highlight: function (e) {
   					$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
   				},
   				success: function (e) {
   					$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
   					$(e).remove();
   				},
   				errorPlacement: function (error, element) {
   					 error.appendTo(element.parent());  
   				},
   				submitHandler: function (form) {
   					form.submit();
   				}
   	    	});
        });
		function gotoPage()
		{
			location.href = "${ctx}/jzmk/circle/list";
		}
		function getGraph()
		{
			
		}
		function printContent(printpage)
		{
			var headstr = "<html><head><title></title></head><body>"; 
			var footstr = "</body>"; 
			var newstr = document.all.item(printpage).innerHTML; 
			var oldstr = document.body.innerHTML; 
			document.body.innerHTML = headstr+newstr+footstr; 
			window.print(); 
			document.body.innerHTML = oldstr; 
			return false; 
		}
		<c:if test="${showGraph==1}">
		var option1 = {
				title : {
					text : '对比图'
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '我的健康指数', '区域平均得分' ]
				},
				toolbox : {
					show : true,
					feature : {
						dataZoom : {
							yAxisIndex : 'none'
						},
						dataView : {
							readOnly : false
						},
						magicType : {
							type : [ 'line', 'bar' ]
						},
						restore : {},
						saveAsImage : {}
					}
				},
				xAxis : {
					type : 'category',
					boundaryGap : false,
					data : [${xAxisData}]
				},
				yAxis : {
					type : 'value',
					max : ${yMaxValue},
					min : ${yMinValue}
				},
				series : [ 
					{
					    name : '我的健康指数',
					    type : 'line',
					    data : [${userScoreData}]
					}, 
					{
						name : '区域平均得分',
						type : 'line',
						data : [${areaScoreData}]
					}
					]
			};
		var myChart1 = echarts.init(document.getElementById('chart1'));
		myChart1.setOption(option1);
		</c:if>
		window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"image":{"viewList":["qzone","tsina","tqq","weixin","renren"],"viewText":"分享到：","viewSize":"16"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
		
</script>
<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script>
<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
</body>
</html>
