<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 公益活动--添加/修改</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a href="${ctx}/ante/publicActivity/form?id=${publicActivity.id}">
                        公益活动<shiro:hasPermission name="ante:publicActivity:edit">${not empty publicActivity.id?'修改':'新增'}
                        </shiro:hasPermission><shiro:lacksPermission name="ante:publicActivity:edit">查看</shiro:lacksPermission></a></h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="publicActivity" action="${ctx}/ante/publicActivity/save?myActivity=${myActivity}" method="post" class="form-horizontal m-t">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>		
							<form:hidden path="accessCount"/>
							<form:hidden path="parterNum"/>
							<div class="form-group">
								<label class="col-sm-3 control-label">标题：</label>
								<div class="col-sm-8">
									<form:input path="title" htmlEscape="false" maxlength="30" class="form-control" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">活动内容：</label>
								<div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="200" class="form-control" required="required"/>
									<sys:ckeditor replace="content" uploadPath="/ante/publicActivity" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">开始时间：</label>
								<div class="col-sm-8">
									<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
										style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143"
										value="<fmt:formatDate value="${publicActivity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">结束时间：</label>
								<div class="col-sm-8">
									<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
										style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143"
										value="<fmt:formatDate value="${publicActivity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" required="true"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">
									<div class="i-checks"><input id="chk" checked="checked" readonly="readonly" type="checkbox"></div>
								</label>
								<div class="col-sm-8">
									我阅读，并同意<a href="#">《XXXXXXX免责声明》</a>
								</div>
							</div>
							<form:hidden path="remarks"/>
                           	<div class="form-group">
                             	<div class="col-sm-8 col-sm-offset-3">
                                 <shiro:hasPermission name="ante:publicActivity:edit">
                                 <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
                                 </shiro:hasPermission>
                                 <input type="button" class="btn" onclick="history.go(-1)" value="返回" />
                             	</div>
                            </div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
		jQuery.validator.addMethod("compareDate", function(value, element, param) {
				var startDate = $(param).val();
				var date1 = new Date(Date.parse(startDate.substring(0,10).replace(/-/g, "/")));
		        var date2 = new Date(Date.parse(value.substring(0,10).replace(/-/g, "/")));
	        return this.optional(element) || (date1 < date2);
		}, "结束日期必须大于开始日期!");
		
		$(function() {
			$("#inputForm").validate({
				rules: {
					"content":{
	                    required: true
	                },
					"startTime":{
	                    required: true
	                },
	                "endTime": {
	                    required: true,
	                    compareDate: "#startTime"
	                },
	                "chk": {
	                    required: true
	                }
				},
				messages: {
					content: {required: "请输入活动内容！"},
					startTime: {required: "请输入开始时间！"},
					endTime: {required: "请输入结束时间！"},
					chk: {required: "请选择免责声明！"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
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
			
			$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
		});
	</script>
</body>
</html>