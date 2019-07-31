<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评语记录管理-查看</title>
	<meta name="decorator" content="default"/>
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
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
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    	<h5>评语记录查看</h5>
                    </div>
                    <div class="ibox-content">
						<form id="inputForm" modelAttribute="commentInfo" method="post" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-3 control-label">编号：</label>
								<div class="col-sm-8">
									<input id="userId2" name="userId2" value="${commentInfo.userId2}" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">评语类型：</label>
								<div class="col-sm-8">
										<div class="radio radio-inline">
											<input type="radio" id="rangeType" value="0" name="rangeType" ${commentInfo.rangeType==0?"checked":"" } disabled="disabled">
											<label for="gender2">总评</label>
										</div>
										<div class="radio radio-info radio-inline">
											<input type="radio" id="rangeType" value="1" name="rangeType" ${commentInfo.rangeType==1?"checked":"" }  disabled="disabled">
											<label for="gender1">阶段性评语</label>
										</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">权限：</label>
								<div class="col-sm-8">
										<div class="radio radio-inline">
											<input type="checkbox" id="powerType1" value="P" name="powerType" ${commentInfo.powerType==P?"checked":"" } disabled="disabled">
											<label for="powerType1">家属</label>
										</div>
										<div class="radio radio-info radio-inline">
											<input type="checkbox" id="powerType2" value="V" name="powerType" ${commentInfo.powerType==V?"checked":"" } disabled="disabled">
											<label for="powerType2">来访者</label>
										</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">评论标题：</label>
								<div class="col-sm-8">
									<input id="title" name="title" value="${commentInfo.title}" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">评论内容：</label>
								<div class="col-sm-8">
									<input id="content"  name="content" value="${commentInfo.content}" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " readonly="readonly"/>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
</body>
</html>