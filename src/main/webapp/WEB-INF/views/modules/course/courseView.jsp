<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--课件管理--课件预览</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>课件查看</h5>
                        <div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
					<div class="form-horizontal m-t">
                        <div class="form-group">
                                <label class="col-sm-3 control-label">课件名称：</label>
                                <div class="col-sm-8">
                                ${course.name }								
								</div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">课件描述：</label>
                                <div class="col-sm-8">								
								${course.intro }
								</div>
                            </div>
							<div class="form-group">
								<label class="col-sm-3 control-label">课件附件：</label>
								<div class="col-smcol-sm-8">
									<a class="text-navy" title="${course.name }" target="_blank" href="${fileAbsolutePath }">${course.path}</a>
								</div>
							</div>
							<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<input id="btnCancel" class="btn" type="button" value="返 回" onclick="back();"/>
                                </div>
                            </div>
                       </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	function back(){
		location.href = "${ctx}/course/course/list";
	}
</script>
</html>