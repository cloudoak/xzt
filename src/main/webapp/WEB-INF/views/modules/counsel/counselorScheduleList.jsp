<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${sysConfig.schoolName } 咨询师排班管理</title>
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
						<div class="example">
						<div class="tabs-container">
	                    <ul class="nav nav-tabs">
	                        <li class="active"><a data-toggle="tab" data-model="counselorScheduleList" href="#tab-1" aria-expanded="true">咨询师排班</a>
	                        </li>
	                        <shiro:hasPermission name="counsel:counselorSchedule:add">
	                        <li class=""><a data-toggle="tab" data-model="counselorSetDays" href="#tab-2" aria-expanded="false">添加时间段</a>
	                        </li>
	                        </shiro:hasPermission>
	                    </ul>
						<div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                            	<shiro:hasPermission name="counsel:counselorSchedule:add">
                            	<form id="searchForm" action="${ctx}/counsel/counselorSchedule/list" method="post" class="form-inline" role="form">
		                           <div class="form-group">
		                            	<div class="i-checks">
		                                  <label><input type="checkbox" name="setDays" value="0"> <i></i> 设置预约天数</label>
		                              	</div>
		                           </div>
		                           <div class="form-group">
		                           		<input id="days" name="days" class="form-control" type="digits" /> 
		                           </div>
		                           <div class="form-group">
		                           		<button class="btn btn-primary" type="submit" id="btnRefresh">刷新</button>&nbsp;
		                               	<button class="btn btn-primary" type="button" id="btnAddTimePart">添加时间段</button>
		                           </div>
		                        </form>
                            	</shiro:hasPermission>
                            	<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" >
	  							<c:if test="${ not empty scheduleMap }">
	  							 	<thead>
                                    <tr>
                                    <th data-field="name">教师</th>
	  								<c:forEach items="${scheduleMap['headerList']}" var="hdr">
	  									<th data-field="${ hdr }">${ hdr }</th>
	  								</c:forEach>
	  								</tr>
                                	</thead>
                                	<c:forEach items="${scheduleMap['contentBodyList']}" var="userBookMap">
                                	<tr>
                                		<td>${ userBookMap['name'] }</td>
                                		<c:forEach items="${scheduleMap['headerList']}" var="hd">
	  										<td>
	                                			<c:forEach items="${userBookMap[hd]}" var="counselorUserBook">
	                                			<c:choose>
	                                			<c:when test="${ counselorUserBook.dealStatus == 4 }">
	                                				<a href="javascript:redirectScheduleForm(${counselorUserBook.dealStatus }, ${counselorUserBook.counselorId}, 
	                                				'<fmt:formatDate value="${counselorUserBook.reserveDate}" pattern="yyyy-MM-dd"/>', 
	                                				'<fmt:formatDate value="${counselorUserBook.beginTime}" pattern="HH:mm:ss"/>', 
	                                				'<fmt:formatDate value="${counselorUserBook.endTime}" pattern="HH:mm:ss"/>');">
	                                				<font color="green">■</font></a>
	                                			</c:when>
	                                			<c:when test="${ counselorUserBook.dealStatus == 2 }">
	                                				<a href="javascript:redirectScheduleForm(${counselorUserBook.dealStatus }, ${counselorUserBook.counselorId},
	                                				'<fmt:formatDate value="${counselorUserBook.reserveDate}" pattern="yyyy-MM-dd"/>', 
	                                				'<fmt:formatDate value="${counselorUserBook.beginTime}" pattern="HH:mm:ss"/>', 
	                                				'<fmt:formatDate value="${counselorUserBook.endTime}" pattern="HH:mm:ss"/>');">
	                                				<font color="#ff0000">■</font></a>
	                                			</c:when>
	                                			<c:when test="${ counselorUserBook.dealStatus == 1 }">
	                                				<a href="javascript:redirectScheduleForm(${counselorUserBook.dealStatus }, ${counselorUserBook.counselorId},
	                                				'<fmt:formatDate value="${counselorUserBook.reserveDate}" pattern="yyyy-MM-dd"/>', 
	                                				'<fmt:formatDate value="${counselorUserBook.beginTime}" pattern="HH:mm:ss"/>', 
	                                				'<fmt:formatDate value="${counselorUserBook.endTime}" pattern="HH:mm:ss"/>');">
	                                				<font color="#0000ff">■</font></a>
	                                			</c:when>
	                                			<c:otherwise>
	                                				<a href="javascript:redirectScheduleForm(${counselorUserBook.dealStatus }, ${counselorUserBook.counselorId},
	                                				'<fmt:formatDate value="${counselorUserBook.reserveDate}" pattern="yyyy-MM-dd"/>', 
	                                				'<fmt:formatDate value="${counselorUserBook.beginTime}" pattern="HH:mm:ss"/>', 
	                                				'<fmt:formatDate value="${counselorUserBook.endTime}" pattern="HH:mm:ss"/>');">
	                                				<font color="#ffff00">■</font></a>
	                                			</c:otherwise>
	                                			</c:choose>
	                                			</c:forEach>
	                                		</td>
	  									</c:forEach>
                                	</tr>
                                	</c:forEach>
	  							</c:if>	
			 					</table>
			 					<div>
			 					<font color="green">■</font>空闲
								<font color="#ff0000">■</font>预约取消
								<font color="#0000ff">■</font>有效预约
								<font color="#ffff00">■</font>未处理的预约
			 					</div>
                            </div>
                        </div>
                        <shiro:hasPermission name="counsel:counselorSchedule:add">
                         <div id="tab-2" class="tab-pane">
                            <div class="panel-body">
                            	<form id="inputForm" action="${ctx}/counsel/counselorSchedule/save" method="post" class="form-horizontal">	
                            		<input type="hidden" name="counselorName" />
									<div class="form-group">
										<label class="col-sm-3 control-label">咨询师：</label>
										<div class="col-sm-8">
										<select id="counselorId" name="counselorId" class="form-control">
										<c:forEach items="${counselorList }" var="counselor">
											<option value="${ counselor.id }">${counselor.user.name }</option>
										</c:forEach>
										</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">预约日期：</label>
										<div class="col-sm-8">
											<input id="scheduleDate" name="scheduleDate" readonly="readonly" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">开始时间：</label>
										<div class="col-sm-8">
											<input id="beginTime" name="beginTime" readonly="readonly" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">结束时间：</label>
										<div class="col-sm-8">
											<input id="endTime" name="endTime" readonly="readonly" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-8 col-sm-offset-3">
										<shiro:hasPermission name="counsel:counselorSchedule:edit">
										<input id="btnSubmit" class="btn btn-primary" type="submit" value="添加"/>&nbsp;
										</shiro:hasPermission>
										<input id="btnCancel" class="btn" type="button" value="取消" />
										</div>
									</div>
								</form>
                            </div>
                         </div>
                         </shiro:hasPermission>
                        </div>
					</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript">
	var redirectScheduleForm;
	$(function() {
		
		layui.use('laydate', function(){
			  var laydate = layui.laydate;
			  laydate.render({
				type: 'date',
				format: 'yyyy-MM-dd',
			    elem: '#scheduleDate'
			  });
			  laydate.render({
				type: 'time',
			    elem: '#beginTime',
			    format:"HH:mm:ss"
			  });
			  laydate.render({
				type: 'time',
			    elem: '#endTime',
			    format:"HH:mm:ss"
			  });
		});
	    
		jQuery.validator.addMethod("compareBeginTime", function(value, element, param) {
    	  	var beginTime = $("#" + param).val();
    	  	return this.optional(element) || (value > beginTime);   
    	});
		
		// 对Date的扩展，将 Date 转化为指定格式的String   
		// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
		// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
		// 例子：   
		// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
		// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
		Date.prototype.Format = function(fmt)   
		{ //author: meizz   
		  var o = {   
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "h+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    "S"  : this.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		}  
		
		jQuery.validator.addMethod("compareNowTime", function(value, element, param) {
    	  	var beginTime = new Date().format("HH:mm:ss");     
    	  	return this.optional(element) || (value > beginTime);   
    	});
		
		
		redirectScheduleForm = function(dealStatus, counselorId, reserveDate, beginTime, endTime){
			location.href = '${ctx}/counsel/counselorSchedule/scheduleform?dealStatus=' + dealStatus + "&counselorId=" + counselorId 
					+ "&reserveDate=" + reserveDate + "&beginTime=" + beginTime + "&endTime=" + endTime;
		};
		
		$("#searchForm").validate({
			errorElement: 'div',
    		errorClass: 'help-block',
    		focusInvalid: false,
    		rules: {
    			setDays: {
		    		required: true
		    	},
		    	days: {
		    		required: true
		    	}
    		},
    		messages: {
    			setDays: {
	    			required:'<font style="color:red">必须勾选预约天数！</font>'
	    		},
	    		days: {
	    			required:'<font style="color:red">设置预约天数必填！</font>'
	    		}
    		},
			submitHandler: function(form){
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
		
		$("#inputForm").validate({
			errorElement: 'div',
    		errorClass: 'help-block',
    		focusInvalid: false,
    		rules: {
    			counselorId: {
		    		required: true
		    	},
		    	scheduleDate: {
		    		required: true
		    	},
		    	beginTime: {
		    		required: true,
		    		compareNowTime: true
		    	},
		    	endTime: {
		    		required: true,
		    		compareBeginTime: "beginTime",
		    		remote: {
    					url: "${ctx}/counsel/counselorSchedule/checkExists",
                        type: "post",
                        dataType: "json",
                        data: {
                        	counselorId: function () {
                        		return $("#counselorId").val();
                        	},
                        	scheduleDate: function () {
                        		return $("#scheduleDate").val();
                        	},
                        	beginTime: function() {
                        		return $("#beginTime").val();
                        	},
							endTime: function() {
                        		return $("#endTime").val();
                        	}
                        },
                        dataFilter: function (data) {
                        	return (data == 1);
                        }
	    			}
	    		},
    		},
    		messages: {
    			counselorId: {
	    			required:'<font style="color:red">咨询师必选！</font>'
	    		},
	    		scheduleDate: {
	    			required:'<font style="color:red">预约日期必填！</font>'
	    		},
	    		beginTime: {
	    			required:'<font style="color:red">开始时间必填！</font>',
	    			compareNowTime: '<font style="color:red">开始时间必须要大于当前时间！</font>'
	    		},
	    		endTime: {
	    			required:'<font style="color:red">结束时间必填！</font>',
	    			compareBeginTime: '<font style="color:red">结束时间必须大于开始时间10分钟以上</font>',
	    			remote: '<font style="color:red">该时间段已经被其它人约过,请选择其它时间</font>'
	    		}
    		},
			submitHandler: function(form){
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
		
		$("#btnAddTimePart").on('click', function(){
			$(".tabs-container .nav-tabs").children("li").find("a[data-model='counselorSetDays']").click();
		});
		$("#btnCancel").on('click', function(){
			$(".tabs-container .nav-tabs").children("li").find("a[data-model='counselorScheduleList']").click();
		});
		
		$("#counselorId").on("change",function(){
			var selectText = $(this).find("option:selected").text();
			$("#counselorName").val(selectText);
		});

	});
</script>
</body>
</html>