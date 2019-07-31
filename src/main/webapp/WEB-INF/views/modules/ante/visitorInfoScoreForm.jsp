<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>来访者积分管理</title>
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h5><a href="${ctx}/ante/visitorInfo/scoreForm?id=${visitorInfo.id}">来访者积分<shiro:hasPermission name="ante:visitorInfo:edit">${not empty visitorInfo.user.score?'修改':'新增'}</shiro:hasPermission><shiro:lacksPermission name="ante:visitorInfo:edit">查看</shiro:lacksPermission></a></h5>
					    <div class="ibox-content">
							<form:form id="inputForm" modelAttribute="visitorInfo" action="${ctx}/ante/visitorInfo/scoreSave" method="post" class="form-horizontal m-t">
								<form:hidden path="id"/>
								<sys:message content="${message}"/>		
								
								<div class="form-group">
									<label class="col-sm-3 control-label">来访者编号：</label>
									<div class="col-sm-8">
										<form:input path="visitorNo" htmlEscape="false" maxlength="30" class="input-xlarge" required="true"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">积分：</label>
									<div class="col-sm-8">
										<form:input path="user.score" type="number" htmlEscape="false" maxlength="3" class="input-xlarge" required="true"/>
									</div>
								</div>
								<div class="form-group">
		                            <label class="col-sm-3 control-label">机构：</label>
		                            <div class="col-sm-8">
				                        <select id="orgId" name="orgId" class="input-medium">
											<option value="">--请选择--</option>
											<c:forEach items="${offices.list}" var="office">
												<option value="${office.id}">${office.name}</option>
											</c:forEach>
										</select>
									</div>
	                        	</div>
	                           	<div class="form-group">
	                             	<div class="col-sm-8 col-sm-offset-3">
	                                 <shiro:hasPermission name="ante:visitorInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
	                                 <button class="btn btn-primary" onclick="history.go(-1)">返回</button>
	                             	</div>
	                            </div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End Panel Basic -->
	</div>

	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:06 GMT -->
</html>
