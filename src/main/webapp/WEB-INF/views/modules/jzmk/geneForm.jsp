<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-gene=1.0">

    <title>因子管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
    
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/jzmk/gene/form?id=${gene.id}">因子添加</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="geneForm" action="${ctx}/jzmk/gene/save" method="post">
                        	<input type="hidden" name="tid" value="${tid }"> 
                            <input type="hidden" name="id" value="${gene.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-12">
			                    	<div class="liangbao">
							         	<a href="#" class="hover"><em class="em1 hover"></em>01基本信息</a>
							            <a href="#" class="hover"><em class="em2 hover"></em>02条目设置</a>
							            <a href="#" class="hover"><em class="em3 hover"></em>03因子设置</a>
							            <a href="#"><em class="em4"></em>04因子解释</a>
							            <a href="#"><em class="em5"></em>05总解释</a>
							         </div>
						        </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">因子名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" value="${gene.name }" class="form-control" type="text" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">英文名称：</label>
                                <div class="col-sm-8">
                                 	<input id="enname" name="enname" value="${gene.enname }" class="form-control" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">因子简称：</label>
                                <div class="col-sm-8">
                                 	<input id="shortName" name="shortName" value="${gene.shortName }" class="form-control" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">对应条目：</label>
                                <div class="col-sm-8">
                                	<input id="question" name="question" value="${gene.question }" type="hidden">
                                	
                                	<table data-toggle="table" data-query-params="queryParams" data-page-size="100" data-click-to-select="true" data-checkbox-header="true"
                                	data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true" >
		                                <thead>
		                                    <tr>
		                                        <th data-field="id"><div class="i-checks"><input type="checkbox" name="btSelectAll" value="0" /></div></th>
		                                        <th data-field="number">序号</th>
		                                        <th data-field="content">条目</th>
		                                    </tr>
		                                </thead>
		                                <c:forEach items="${questions }" var="question" varStatus="status" >
											<tr>
												<td data-field="id"><div class="i-checks"><input type="checkbox" name="btSelectItem" value="${question.id}" /></div></td>
												<td data-field="number">${question.number}</td>
												<td data-field="content">${question.content}</td>
											</tr>
										</c:forEach>
		                            </table>
		                            
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">计分方式：</label>
                                <div class="col-sm-8">
                                	<select class="form-control m-b" id="scoringFormula" name="scoringFormula">
	                                    <option value="1" hassubinfo="true" ${gene.scoringFormula==1?"selected":""}>原始分</option>
	                                    <option value="2" hassubinfo="true" ${gene.scoringFormula==2?"selected":""}>Z分</option>
	                                    <option value="3" hassubinfo="true" ${gene.scoringFormula==3?"selected":""}>T分</option>
	                                    <option value="4" hassubinfo="true" ${gene.scoringFormula==4?"selected":""}>平均分</option>
	                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">最小值：</label>
                                <div class="col-sm-8">
                                    <input id="minValue" name="minValue" value="${gene.minValue }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">最大值：</label>
                                <div class="col-sm-8">
                                    <input id="maxValue" name="maxValue" value="${gene.maxValue }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标准分：</label>
                                <div class="col-sm-8">
                                    <input id="standardValue" name="standardValue" value="${gene.standardValue }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">平均分：</label>
                                <div class="col-sm-8">
                                    <input id="avgValue" name="avgValue" value="${gene.avgValue }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">下一步</button>&nbsp;
                                    <button class="btn btn-primary" type="button" onclick="toBack(${tid});">返回</button>
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
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
    <script>
    	var questions = '${gene.question}', callback = function() {
    		$("input[type=checkbox][name=btSelectAll]").on('ifClicked', function(event){
	       		var checked = $(this).is(':checked');
	           	$("input[type=checkbox][name=btSelectItem]").each(function () {  
	           		if (!checked) {  
	                    $(this).iCheck('check');// .attr("checked", "checked");
                    }else{
                   	 	$(this).iCheck('uncheck');  // .removeAttr("checked");
                    }
	   			 });
	           	if(!checked){
	        		$("input[type=checkbox][name=btSelectAll]").iCheck('check'); 
		       	}else{
		       		$("input[type=checkbox][name=btSelectAll]").iCheck('uncheck');
		       	} 
	       	});
         	if(questions && questions.length > 0){
        		$("input[type=checkbox][name=btSelectAll]").iCheck('check');  // .attr("checked", "checked");
	       	}else{
	       		$("input[type=checkbox][name=btSelectAll]").iCheck('uncheck'); // .removeAttr("checked");
	       	} 
        	
        	  $("input[type=checkbox][name=btSelectItem]").each(function ( ) { 
        		var selectItem = $(this).val();
				 if (questions.toString().indexOf($(this).val()) != -1) {  
                     $(this).iCheck('check'); //.attr("checked", "checked");
                 }else{
                	 $(this).iCheck('uncheck'); // .removeAttr("checked");
                 }
			 });
    	};
    	
    	function toBack(id){
	    	document.location.href = "${ctx}/jzmk/gene/geneList?tid=" + id;
	    }
    	
	    function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url ,name , options);
	    }
		
        $(function(){
        	
        	callback();
        	
        	jQuery.validator.addMethod("compareMaxValue", function(value, element, param) {
          	  var minValue = $("#" + param).val();
          	  return this.optional(element) || (parseInt(value) > parseInt(minValue) );   
          	});
        	
        	$("#geneForm").validate({
        		errorElement: 'div',
        		errorClass: 'help-block',
        		focusInvalid: false,
        		rules: {
        			maxValue: {
        				compareMaxValue: 'minValue',
        				remote: {
    						url: "${ctx}/jzmk/gene/checkMaxValue",
                            type: "post",
                            dataType: "json",
                            data: {
                            	maxValue: function () {
                            		return $("#maxValue").val();
                            	},
                            	questionIds: function() {
                            		 var questionIds = [];
	               	   				 $("input[type=checkbox][name=btSelectItem]").each(function (i, e) {  
	               	   					 if ($(this).is(':checked')) {  
	               	   						 questionIds.push($(this).val());  
	               	   	                  }
	               	   				 });
                            		return questionIds.join(',');
                            	}
                            },
                            dataFilter: function (data) {
                            	return (data == 1);
                            }
        				}
    	    		}
    	    	},messages: {
    	    		maxValue: {
    	    			compareMaxValue: '最小值比最大值数值要大！',
    	    			remote: '最大值数值超出了选择的所有条目设置的答案总分数值！'
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
    				 var questionIds = [];
	   				 $("input[type=checkbox][name=btSelectItem]").each(function (i, e) {  
	   					 if ($(this).is(':checked')) {  
	   						 questionIds.push($(this).val());  
	   	                  }
	   				 });
	   				 if(questionIds.length > 0){
	   					$("#question").val(questionIds.join(','));
	   				 }
    				 form.submit();
    			}
        	});
        	
        	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green", radioClass:"iradio_square-green", increaseArea: '20%'});

        });
    </script>
</body>
</html>