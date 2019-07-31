<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>量表管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/jzmk/scale/form?id=${scale.id}">量表添加</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="scaleForm" action="${ctx}/jzmk/scale/save" method="post" >
                            <input type="hidden" name="id" value="${scale.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-12">
			                    	<div class="liangbao">
							         	<a href="#" class="hover"><em class="em1 hover"></em>01基本信息</a>
							            <a href="#"><em class="em2"></em>02条目设置</a>
							            <a href="#"><em class="em3"></em>03因子设置</a>
							            <a href="#"><em class="em4"></em>04因子解释</a>
							            <a href="#"><em class="em5"></em>05总解释</a>
							         </div>
						        </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">量表类型：</label>
                                <div class="col-sm-8">
	                                <select class="form-control m-b" id="typeId" name="typeId" required="required">
	                                	<c:forEach items="${scaleTypeList }" var="scaleType">
	                                		<option value="${scaleType.id }" hassubinfo="true">${scaleType.name }</option>
	                                	</c:forEach>
	                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">量表名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" value="${scale.name }" class="form-control" type="text" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">量表介绍：</label>
                                <div class="col-sm-8">
                                	<textarea name="introduce" rows="3" cols="100" required="required">${scale.introduce }</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">量表指导语：</label>
                                <div class="col-sm-8">
                                	<textarea name="instruction" rows="3" cols="100">${scale.instruction }</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                            	<label class="col-sm-3 control-label">咨询师必选项</label>
                            	<div class="col-sm-8">
                            		 <div class="i-checks">
                                        <label><input type="checkbox" name="teacherMustOption" value="0"> <i></i> 性别</label>
                                        <label><input type="checkbox" name="teacherMustOption" value="1"> <i></i> 职务</label>
                                        <label><input type="checkbox" name="teacherMustOption" value="2"> <i></i> 职称</label>
                                        <label><input type="checkbox" name="teacherMustOption" value="3"> <i></i> 出生年月</label>
                                    </div>
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="col-sm-3 control-label">来访者必选项</label>
                            	<div class="col-sm-8">
                            		 <div class="i-checks">
                            		 	<label><input type="checkbox" name="visitorMustOption" value="0"> <i></i> 性别</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="1"> <i></i> 民族</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="2"> <i></i> 出生年月</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="3"> <i></i> 籍贯</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="4"> <i></i> 是否城镇</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="5"> <i></i> 是否住宿</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="6"> <i></i> 是否干部</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="7"> <i></i> 是否单亲</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="8"> <i></i> 是否与父母同住</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="9"> <i></i> 父亲受教育水平</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="10"> <i></i> 父亲职业</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="11"> <i></i> 父亲职务</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="12"> <i></i> 母亲受教育水平</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="13"> <i></i> 母亲职业</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="14"> <i></i> 母亲职务</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="15"> <i></i> 直系亲属是否有病史</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="16"> <i></i> 是否贫困</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="17"> <i></i> 家庭排行</label>
                                    </div>
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="col-sm-3 control-label">家长必选项</label>
                            	<div class="col-sm-8">
                            		 <div class="i-checks">
                                        <label><input type="checkbox" name="parentMustOption" value="0"> <i></i> 性别</label>
                                        <label><input type="checkbox" name="parentMustOption" value="1"> <i></i> 出生年月</label>
                                    </div>
                            	</div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">查看规则：</label>
                                <div class="col-sm-8">
	                                <select id="modeid" name="modeid" class="form-control m-b">
	                                    <option value="0" hassubinfo="true">允许用户查看</option>
	                                    <option value="1" hassubinfo="true">不允许用户查看</option>
	                                    <option value="2" hassubinfo="true">正常允许异常不允许用户查看</option>
	                                </select>
                                </div>
                            </div>
                            <div id="ruleDisplay" class="form-group">
                                <label class="col-sm-3 control-label">规则解释：</label>
                                <div class="col-sm-8">
                                	<textarea name="ruleInterpreter" rows="3" cols="100" required="required">${scale.ruleInterpreter }</textarea>
			                        <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 不允许用户查看时（必填）</span>
                                </div>
                            </div>
                           <div class="form-group">
                                <label class="col-sm-3 control-label">允许家属查看：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="parentLook1" value="1" name="parentLook" ${empty scale.parentLook ? "checked" : (scale.parentLook==1?"checked":"") } required="required">
                                        <label for="parentLook1">允许</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="parentLook2" value="0" name="parentLook" ${scale.parentLook==0?"checked":"" } required="required">
                                        <label for="parentLook2">不允许</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">所需积分：</label>
                                <div class="col-sm-8">
                                    <input id="integral" name="integral" value="${scale.integral }" class="form-control" type="number" onkeypress="return noNumbers(event)"  required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">最少显示时间(机构：秒)：</label>
                                <div class="col-sm-8">
                                    <input id="minShowTime" name="minShowTime" value="${scale.minShowTime }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">最大答题时间(机构：分)：</label>
                                <div class="col-sm-8">
                                    <input id="maxAnswerTime" name="maxAnswerTime" value="${scale.maxAnswerTime }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">最小年龄限制：</label>
                                <div class="col-sm-8">
                                    <input id="minAgeLimit" name="minAgeLimit" value="${empty scale.minAgeLimit ? 0 : scale.minAgeLimit }" class="form-control" type="number"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">最大年龄限制：</label>
                                <div class="col-sm-8">
                                    <input id="maxAgeLimit" name="maxAgeLimit" value="${empty scale.maxAgeLimit ? 0 : scale.maxAgeLimit }" oninvalid="validMinAgeLimit();" class="form-control" type="number"> 
                                </div>
                            </div>
                            <c:choose>
                            <c:when test="${ userType == 0 }">
                            <input type="hidden" value="0" name="isPublic" />
                            </c:when>
                            <c:otherwise>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">是否共享量表：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="isPublic1" value="0" name="isPublic" ${scale.isPublic==0?"checked":"" } required="required">
                                        <label for="isPublic1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="isPublic2" value="1" name="isPublic" ${scale.isPublic==1?"checked":"" } required="required">
                                        <label for="isPublic2">否</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            </c:otherwise>
                            </c:choose>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否是内置量表：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="inside1" value="1" name="inside" ${scale.inside==1?"checked":"" } required="required">
                                        <label for="inside1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="inside2" value="0" name="inside" ${scale.inside==0?"checked": (scale.inside==1 ?"":"checked") } required="required">
                                        <label for="inside2">否</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <c:if test="${isAdmin == 2 }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否是家族量表：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="family1" value="1" name="family" ${scale.family==1?"checked":"" } required="required">
                                        <label for="family1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="family2" value="0" name="family" ${scale.family==0?"checked": (scale.family==1 ?"":"checked") } required="required">
                                        <label for="family2">否</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            </c:if>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">下一步</button>&nbsp;
                                    <button class="btn btn-primary" type="button" onclick="gotoScalePage();">返回</button>
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
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script>
    function windowOpen(url, name, width, height){
    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
    	window.open(url ,name , options);
    }
    function gotoScalePage(){
    	document.location.href = "${ctx}/jzmk/scale/getScale";
    }
    
    $(function(){
    	
    	var typeId = '${scale.typeId}';
    	
    	if(typeId){
    		$("#typeId").val(typeId);
    	}
    	
    	jQuery.validator.addMethod("compareMaxAgeLimit", function(value, element, param) {
    	  var minAgeLimit = $("#" + param).val();
    	  return this.optional(element) || (parseInt(value) >= parseInt(minAgeLimit) );   
    	});
    	
    	$("#scaleForm").validate({
    		errorElement: 'div',
    		errorClass: 'help-block',
    		focusInvalid: false,
    		rules: {
	    		maxAgeLimit: {
	    			compareMaxAgeLimit: 'minAgeLimit'
	    		}
	    	},messages: {
	    		maxAgeLimit: '最小年龄限制要比最大年龄限制数值要大！'
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
    	var i = 0, j = 0, k= 0, 
   		modeid = "${scale.modeid}",	
   		teacherMustOption = '${scale.teacherMustOption}', 
   		visitorMustOption = '${scale.visitorMustOption}', 
   		parentMustOption = '${scale.parentMustOption}', 
   		teacherMustOptionEl = document.getElementsByName("visitorMustOption"),
   		visitorMustOptionEl = document.getElementsByName("teacherMustOption"), 
   		parentMustOptionEl = document.getElementsByName("parentMustOption");
   		if(modeid !== ''){
   			$("#modeid").val(modeid);
   		}
   		
    	if($("#modeid").val() == 0){
    		$('#ruleDisplay').css("display", "none");
    	}
       	 $("#modeid").on('change',function(){
           	var val = $(this).val();
           	if(val !== "0"){
           		$('#ruleDisplay').css("display", "block");
           	}else{
           		$('#ruleDisplay').css("display", "none");
           	}
       	});
       	
       	for(; i < teacherMustOptionEl.length; i++){
       		if(teacherMustOption.toString().indexOf(teacherMustOptionEl[i].value) != -1){
       			teacherMustOptionEl[i].checked = true;
       		}
       	}
       	for(; j < visitorMustOptionEl.length; j++){
       		if(visitorMustOption.toString().indexOf(visitorMustOptionEl[j].value) != -1){
       			visitorMustOptionEl[j].checked = true;
       		}
       	}
       	for(; k < parentMustOptionEl.length; k++){
       		if(parentMustOption.toString().indexOf(parentMustOptionEl[k].value) != -1){
       			parentMustOptionEl[k].checked = true;
       		}
       	}
       	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
    });
</script>
</body>
</html>