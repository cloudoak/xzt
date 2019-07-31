<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scaleTotalExplain=1.0">

<title>总解释管理</title>
<meta name="keywords"
	content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
<meta name="description"
	content="${fns:getConfig('description')} ${sysConfig.schoolName }">

<link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0"
	rel="stylesheet">
<link
	href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link
	href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
<link href="${hplusStatic }/css/plugins/summernote/summernote.css"
	rel="stylesheet">
<link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css"
	rel="stylesheet">
<link href="${hplusStatic }/css/plugins/iCheck/custom.css"
	rel="stylesheet">

<link href="${serverUrl }css/measure.css" type="text/css"
	rel="stylesheet" />

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<a class="text-navy" href="javascript:">测评时间</a>
						</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="timeSetForm" action=""
							method="post">
							<input type="hidden" name="id" value=""> <input
								type="hidden" name="queryType" value="${queryType}">
							<div class="form-group">
								<label class="col-sm-3 control-label">开始时间:</label>
								<div class="col-sm-8">
									<input id="startTime" name="startTime" type="text"
										readonly="readonly" maxlength="20" class="input-medium Wdate "
										value="<fmt:formatDate value="${scaleTaskUser.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">结束时间:</label>
								<div class="col-sm-8">
									<input id="endTime" name="endTime" type="text"
										readonly="readonly" maxlength="20" class="input-medium Wdate "
										value="<fmt:formatDate value="${scaleTaskUser.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">测评负责人:</label>
								<div class="col-sm-8">
									<select name="teacherType" id="teacherType"
										style="width: 155px;">
										<c:forEach items="${userList}" var="counselorType">
											<option value="${counselorType.id}">${counselorType.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">测评批号:</label>
								<div class="col-sm-8">
									<input name="checkNumber" id="checkNumber"
										style="width: 155px;" type="text" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button class="btn btn-primary" type="button"
										onclick='gotoScaleChoosePage(${queryType},${ids})'>上一步</button>
									&nbsp;
									<button class="btn btn-primary" type="button" id="submitBtn">完成</button>
								</div>
							</div>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script
		src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
	<script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
	<script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
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
	<script>
    	function getNowBatchNumber() {
        	var date = new Date();
        	var seperator1 = "-";
        	var seperator2 = ":";
        	var month = date.getMonth() + 1;
        	var strDate = date.getDate();
        	var batchNumber = "${batchNumber}";
        	if (month >= 1 && month <= 9) {
            	month = "0" + month;
        	}
        	if (strDate >= 0 && strDate <= 9) {
            	strDate = "0" + strDate;
        	}
        	if (batchNumber >= 1 && batchNumber <= 9) {
            	batchNumber = "0" + batchNumber;
        	}
        	var currentBatchNumber = date.getFullYear() +""+ month +""+ strDate+""+batchNumber;
        	return currentBatchNumber;
    	} 
    	$("#checkNumber").val(getNowBatchNumber());
	    function gotoScaleChoosePage(id,ids){
	    	document.location.href = '${ctx}/jzmk/scaleChoose/form?queryType=' + id+'&ids='+ids;
	    }
	    function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url ,name , options);
	    }
    	//删除总解释
	    function delscaleTotalExplain(id,sid){
			layer.confirm('是否删除该总解释？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/scaleTotalExplain/delete?id="+id+"&sid="+sid;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
    	
    	
	    $("#submitBtn").click(function(){
	    	//validate the time set
	    	if($('#startTime').val().trim()=="")
	    	{
	    		layer.alert("必须设置开始时间");
	    		return false;
	    	}
	    	if(Date.parse(new Date($('#startTime').val()))<Date.parse(new Date())+60000)
	    	{
	    		layer.alert("必须设置开始时间大于当前时间1分钟");
	    		return false;
	    	}
	    	if($('#endTime').val().trim()=="")
	    	{
	    		layer.alert("必须设置结束时间");
	    		return false;
	    	}
	    	if($('#startTime').val()>=$('#endTime').val())
	    	{
	    		layer.alert("开始时间值必须小于结束时间值");
	    		return false;
	    	}
	    	if((Date.parse(new Date($('#startTime').val()))+60000)>Date.parse(new Date($('#endTime').val())))
	    	{
	    		layer.alert("开始时间必须与结束时间相差至少1分钟");
	    		return false;
	    	}
	    	if($('#checkNumber').val().trim()=="")
	    	{
	    		layer.alert("必须设置测评批号");
	    		return false;
	    	}
	    	//debugger;
	        $.ajax({  
	        data:{queryType:${queryType},
	        	  teacherType:$('#teacherType').val(),
	        	  teacherName:$('#teacherType').find("option:selected").text(),
	        	  startTime:$('#startTime').val(),
	        	  endTime:$('#endTime').val(),
	        	  checkNumber:$('#checkNumber').val(),
	        	  ids:JSON.stringify(${ids}),
	        	  tids:JSON.stringify(${tids})
	        	  },
	        async:false,
	        type:"POST",  
	        dataType: 'json',  
	        url:"${ctx}/jzmk/scaleChoose/saveTime",  
	        error:function(data){  
	            alert("出错了！！:");  
	        },  
	        success:function(data){  
	            //alert("success:");
	            var json = eval(data); //数组
	            //var count = loadData(json,v);
	            
	            $.each(json, function (index, item) {  
	                //循环获取数据    
	                var code = json[index].code; 
	                if(code==1)
	                successHint();
	                //$("#list").html($("#list").html() + "<br>" + name + " - " + idnumber + " - " + sex + "<br/>");  
	            });
	           	
	           	
	        }  
	        });  
	        });
	    	
        function successHint()
        {
        	layer.open({
			    type: 1,
			    title: "成功信息",
			    shadeClose:true,
			    shade: 0.3,
			    area: ['340px', '215px'],
			    offset: 'c',
			    shift: 2,
			    content: "<br/> 进行测评任务维护 / 继续添加测评任务？",
			    btn:['添加测评任务','测评任务维护'],
			    yes: function(index, layero){
			    	//继续添加量表
			    	window.location.href="${ctx}/jzmk/scaleCheckTask/form";
			    },btn2: function(index, layero){
			       //进入量表维护
			    	window.location.href="${ctx}/jzmk/scaleCheckTask/list";
			    }
			});
        }
        
    	$(function(){
    		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
    	});
    </script>
</body>
</html>