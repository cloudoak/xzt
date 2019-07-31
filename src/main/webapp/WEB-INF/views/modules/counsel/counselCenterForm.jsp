<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>咨询中心管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <script src="${ctxStatic}/My97DatePicker/WdatePicker.js" ></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
			 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>咨询中心信息：</h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
		 <div class="ibox-content">
		 <form id="inputForm" action="${ctx}/counsel/counselCenter/save" method="post" class="form-horizontal">
		 <input type="hidden" name="id" value="${counselCenter.id }"/>	
		 <div class="form-group">
			<label class="col-sm-3  control-label">咨询中心介绍：</label>
			 <div class="col-sm-8">
			 	<textarea name="intro" rows="4" cols="10" maxlength="128" class="form-control">${counselCenter.intro}</textarea>
			</div>
         </div>
		 <div class="form-group">
			<label class="col-sm-3 control-label">咨询中心制度：</label>
			 <div class="col-sm-8">
			 	<textarea name="institution" rows="4" cols="10" maxlength="128" class="form-control">${counselCenter.institution}</textarea>
			</div>
		 </div>
		 <div class="form-group">
			<label class="col-sm-3 control-label">工作时间：</label>
			<div class="col-sm-8">
				<input type="text" name="workHour" value="${counselCenter.workHour}" maxlength="64" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		 </div>
		 <div class="form-group">
			<label class="col-sm-3 control-label">咨询中心地址：</label>
			<div class="col-sm-8">
				<input type="text" name="address" value="${counselCenter.address}" maxlength="64" class="form-control" />
			</div>
		 </div>
		 <div class="form-group">
			<label class="col-sm-3 control-label">联系方式：</label>
			<div class="col-sm-8">
				<input type="text" name="contactWay" value="${counselCenter.contactWay}" maxlength="64" class="form-control" />
			</div>
		 </div>
		 <div class="form-group">
			<div class="col-sm-8 col-sm-offset-3">
			<shiro:hasPermission name="counsel:counselCenter:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/></shiro:hasPermission>
			</div>
		 </div>
	     </form>
	     </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>