<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 咨询师--机构设置</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
	<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/dropdownTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>咨询师-机构设置</h5>
                        <div class="ibox-tools">
                        </div>
					</div>
					<div class="ibox-content">
                    <form class="form-horizontal m-t" id="inputForm" action="${ctx}/ante/teacherInfo/savePermissions" method="post">
                        <input type="hidden" name="counselorId" value="${counselor.id }">
						<div class="form-group">
                            <label class="col-sm-3 control-label">咨询师名称：</label>
                            <div class="col-sm-8">								
							<input name="name" maxlength="16" class="form-control" value="${counselor.user.name }" type="text" />
                            </div>
                        </div>			
						<div class="form-group">
							<label class="col-sm-3 control-label">可浏览的机构：</label>
							<div class="col-sm-8">
								<input type="hidden" id="officeId" name="officeId" />
			                    <input id="orgInput" name="orgInput" type="text" class="form-control" readonly />
							</div>
						</div>
					<div class="form-group">
                        <div class="col-sm-8 col-sm-offset-3">
						<shiro:hasPermission name="ante:teacherInfo:edit">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="添加"/>&nbsp;
						</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="取消" />
                        </div>
                    </div>
                    </form>
                    
                    <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true"  data-pagination="true">
                             <thead>
                                 <tr>
                                     <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                     <th data-field="counselor.user.name">课件名称</th>
                                     <th data-field="officeId">组织</th>										
                                     <th>操作</th>
                                 </tr>
                             </thead>
                         <c:forEach items="${courseOffices}" var="courseOffice">									
						<tr>
							<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
							<td>${courseOffice.counselor.user.name}</td>
							<td>${fns:getOfficeRefences(courseOffice.officeId)}</td>
							<td>
								<a class="text-danger" href="${ctx}/ante/teacherInfo/deletePermissions?counselorId=${courseOffice.counselorId}" onclick="return confirm('确认要删除该咨询师机构吗？', this.href)">
									<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
                         </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" ></script>
<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
<script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
<script type="text/javascript">
		var orgId = '${counselor.orgId}';
		$(function() {
			var dropdownTree = $("#orgInput").dropdownTree({
		    	root: "${rootParentId}",
	            async: {
	            	url: "${ctx}/sys/org/asynTree",
	            	type: "json",
	            	params: ["id"]
	            },
	            selectValue: orgId
		    });
			
			$("#btnCancel").on("click", function(){
				location.href = "${ctx}/ante/teacherInfo/list";
			});
			
			$("#inputForm").validate({
				errorElement: 'div',
	    		errorClass: 'help-block',
	    		focusInvalid: false,
	    		rules: {
		    		name: {
		    			required: true
		    		}
		    	},messages: {
		    		name: {
		    			required: '咨询师名称必填！'
		    		}
		    	},
		    	invalidHandler: function (event, validator) { //display error alert on form submit   
					$('.alert-danger', $('.login-form')).show();
				},
				highlight: function (e) {
					$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
				},
				success: function (e) {
					$("#officeId").val(dropdownTree.getValue());
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
	</script>
</html>