<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${sysConfig.schoolName }--老师管理</title>
<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
<meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
<%@ include file="/WEB-INF/views/include/commoncss.jsp"%>
<link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/dropdownTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="example-title">档案查询</h4>
						<div class="example">
							<form id="searchForm" action="${ctx}/archives/archive/generateList" method="post" class="form-horizontal">
								<div class="form-group">
		                       	<label class="col-sm-2 control-label">
								  <div class="i-checks">
		                              <label><input type="checkbox" name="setUserName" value="0"> <i></i> 用户名</label>
		                          </div>
								  </label>
		                          <div class="col-sm-4">
		                             <input name="userName" type="text" value="${ archives.userName }" class="form-control" />
		                          </div>
		                       </div>
		                       <div class="form-group">
		                          <label class="col-sm-2 control-label">
		                          <div class="i-checks">
		                              <label><input type="checkbox" name="setUserType" value="0"> <i></i> 用户类型</label>
		                          </div>
		                          </label>
		                          <div class="col-sm-4">
		                             <select id="authType" name="authType" class="form-control">
										<c:forEach items="${fns:getDictList('sys_user_type')}" var="item">
											<c:if test="${ item.value > 1}">
											<option value="${item.value }">${item.label }</option>
											</c:if>
										</c:forEach>
									</select>
		                          </div>
		                       </div>
		                       <div class="form-group">
		                       	<label class="col-sm-2 control-label">
								  <div class="i-checks">
		                              <label><input type="checkbox" name="setOrg" value="0"> <i></i> 组织机构</label>
		                          </div>
								  </label>
		                          <div class="col-sm-4">
		                          	  <input type="hidden" id="orgId" name="orgId" />
			                          <input id="orgInput" name="orgInput" type="text" class="form-control" readonly />
		                          </div>
		                       </div>
		                       <div class="form-group">
		                       	  <label class="col-sm-2 control-label">档案内容</label>
		                          <div class="col-sm-8">
		                          <div class="i-checks">
									 	<label> <i></i> 个人信息</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="1"> <i></i> 测评记录</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="2"> <i></i> 咨询记录</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="3"> <i></i> 学习记录</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="4"> <i></i> 留言记录</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="5"> <i></i> 收到的评语及自评</label>
                            		 	<label><input type="checkbox" name="visitorMustOption" value="6"> <i></i> 总结性评语</label>
	                               </div>
		                          </div>
		                       </div>
	                           <div class="form-group">
	                           		<div class="col-sm-8 col-sm-offset-3">
	                           		<button class="btn btn-primary" type="submit" id="btnSelect">生成</button>&nbsp;
	                           		<button class="btn" type="reset" id="btnReset">重置</button>
	                           		</div>
	                           </div>
	                       </form>
							<table id="exampleTableToolbar1" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								data-height="400" data-pagination="true" data-search="true">
								<thead>
									<tr>
										<th data-field="fileName">文件名</th>
										<th data-field="userId">下载</th>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="archives">
									<tr>
										<td>${archives.fileName }</td>
										<td>
											<a class="text-info" target="_bank" href="${ctx}/archives/archive/downloadArchives?userId=${archives.userId}">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
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
		<!-- End Panel Basic -->
	</div>
</body>
<%@ include file="/WEB-INF/views/include/commonlistjs.jsp"%>
<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript">
	$(function() {
		var authType = '${archives.authType}', orgId = '${archives.org.id}'
			,orgName = '${archives.org.name}';
		if(authType){
			$("#authType").val(authType);
		}
	    
	    var dropdownTree = $("#orgInput").dropdownTree({
	    	root: "${rootParentId}",
            async: {
            	url: "${ctx}/sys/org/asynTree",
            	type: "json",
            	params: ["id"]
            },
            selectValue: orgId
	    });
	    
	   $("#searchForm").validate({
			errorElement: 'div',
	   		errorClass: 'help-block',
	   		focusInvalid: false,
	   		rules: {
	   			/* setDays: {
		    		required: true
		    	},
		    	days: {
		    		required: true
		    	} */
	   		},
	   		messages: {
	   			/* setDays: {
	    			required:'<font style="color:red">必须勾选预约天数！</font>'
	    		},
	    		days: {
	    			required:'<font style="color:red">设置预约天数必填！</font>'
	    		} */
	   		},
			submitHandler: function(form){
				$("#orgId").val(dropdownTree.getValue());
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
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</html>