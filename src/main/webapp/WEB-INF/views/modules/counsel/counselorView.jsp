<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>咨询师详细信息</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/counsel/counselor/form?id=${counselor.id}">咨询师设置</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
				<div class="ibox-content"> 
				<form id="counselorForm" class="form-horizontal m-t" action="${ctx}/counsel/counselor/save" method="post" >		
					<sys:message content="${message}"/>	
					<input type="hidden" name="id" value="${ counselor.id }" />
					<input type="hidden" name="applyStatus" value="${ counselor.applyStatus }" />
					<div class="form-group">
						<label class="col-sm-3 control-label">咨询师：</label>
						<div class="col-sm-8">
							${ counselor.user.name }
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">资格证书：</label>
						<div class="col-sm-8">
							<img alt="资格证书" id="imgCertificatePath" width="40px" height="60px" border="1px" src="${fileAbsolutePath }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">认证状态：</label>
						<div class="col-md-4">
						<c:choose>
						<c:when test="${ counselor.applyStatus == 2 }">认证</c:when>
						<c:otherwise>未认证</c:otherwise>
						</c:choose>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">是否推荐到首页：</label>
                        <div class="col-md-4">
                        <c:choose>
                        <c:when test="${empty counselor.indexFlag }">否</c:when>
                        <c:when test="${counselor.indexFlag == 1}">否</c:when>
                        <c:otherwise>是</c:otherwise>
                        </c:choose>
                        </div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">是否内部咨询师：</label>
                        <div class="col-md-4">
                        <c:choose>
                        <c:when test="${empty counselor.insideFlag }">否</c:when>
                        <c:when test="${counselor.insideFlag == 1}">否</c:when>
                        <c:otherwise>是</c:otherwise>
                        </c:choose>
						</div>
					</div>
					<div class="form-group">
			            <label class="col-sm-3 control-label">描述：</label>
			            <div class="col-sm-8">     
			            <textarea name="instro" readonly="readonly" rows="4" cols="10" maxlength="128" class="form-control">${ counselor.instro }</textarea>
			            </div>
			        </div>	
					<div class="form-group">
					 <div class="col-sm-8 col-sm-offset-3">
						<input id="btnBack" class="btn" type="button" value="返 回" />
					</div>
					</div>
				</form>
				 </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
<script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btnBack").on("click", function(){
      		location.href = "${ctx}/counsel/counselor/list";
      	});
	});
</script>
</html>