<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>家长管理</title>
<meta name="keywords"
	content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
<meta name="description"
	content="${fns:getConfig('description')} ${sysConfig.schoolName }">

<link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0"
	rel="stylesheet">
<link href="${hplusStatic }/css/plugins/iCheck/custom.css"
	rel="stylesheet">
<link href="${hplusStatic }/css/plugins/treeview/bootstrap-treeview.css"
	rel="stylesheet">
<link
	href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<a class="text-navy" href="#">家属审核
								<shiro:lacksPermission name="ante:parents:edit">查看</shiro:lacksPermission></a>
						</h5>
						<div class="ibox-tools">
							<!-- <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a> -->
						</div>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="inputForm"
							action="${ctx}/ante/parents/modStatus" method="post">
							<input type="hidden" name="id" value="${parentsVo.id }">
							<input type="hidden" name="parentNo" value="${parentsVo.parentNo }">
							<input type="hidden" name="userId" value="${parentsVo.userId }">
							<div class="form-group">
								<label class="col-sm-3 control-label">来访者编号：</label>
								<div class="col-sm-8">
									${parentsVo.studentCode }
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">来访者姓名：</label>
								<div class="col-sm-8">
									${parentsVo.visitorInfo.user.name}
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">姓名：</label>
								<div class="col-sm-8">
									${parentsVo.name }
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：</label>
								
								<div class="col-sm-8">
									${parentsVo.gender==1?"男":"女"}
								</div>
									
							</div>
							<div class="form-group">
								<div id="stautsId"  class="col-sm-2">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">出生年月：</label>
								<div class="col-sm-2">
									<fmt:formatDate value="${parentsVo.birthday}" pattern="yyyy-MM-dd"/>
								
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">家属用户名：</label>
								<div class="col-sm-8">
									${parentsVo.account }
								</div>
							</div>
							<%-- <div class="form-group">
								<label class="col-sm-3 control-label">密码：</label>
								<div class="col-sm-8">
									${parentsVo.password }
								</div>
							</div> --%>
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									
									<shiro:hasPermission name="ante:parents:edit"><input id="audited"  class="btn btn-primary" type="button" value="审核通过"/>&nbsp;</shiro:hasPermission>
									<shiro:hasPermission name="ante:parents:edit"><input id="reject"   class="btn btn-primary" type="button" value="驳回"/>&nbsp;</shiro:hasPermission>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script type="text/javascript">
		function windowOpen(url, name, width, height) {
			var top = parseInt((window.screen.height - height) / 2, 10), left = parseInt(
					(window.screen.width - width) / 2, 10), options = "location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"
					+ "resizable=yes,scrollbars=yes,"
					+ "width="
					+ width
					+ ",height=" + height + ",top=" + top + ",left=" + left;
			window.open(url, name, options);
		}
	</script>
	<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script
		src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${hplusStatic }/js/plugins/treeview/bootstrap-treeview.js"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" ></script>
	<%-- <script src="${hplusStatic }/js/demo/treeview-demo.min.js"></script> --%>
	<script>
		$(document).ready(function() {
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green",
			})
	
			$("#audited").click(function(){
				
				 parent.layer.confirm('确定审核通过吗？',
						 {icon: 3, title:'提示',
					 		shade: false //不显示遮罩
					 	 },function(index){
							$("#stautsId").append("<input name='state' type='hidden' value='1'/>");
							 $("#inputForm").submit();
							 parent.layer.close(index);
					});
			});
			
			$("#reject").click(function(){
				
				 	parent.layer.confirm('确定驳回吗？', {
				 		icon: 3, title:'提示',
				 		shade: false //不显示遮罩
				 	 }, function(){
						 $("#stautsId").append("<input name='state' type='hidden' value='2'/>");
						 $("#inputForm").submit();
					});
			});
			
			
		});
	</script>
	<script type="text/javascript">
		$("#treeview6").treeview({
			color : "#428bca",
			expandIcon : "glyphicon glyphicon-stop",
			collapseIcon : "glyphicon glyphicon-unchecked",
			nodeIcon : "glyphicon glyphicon-user",
			showTags : !0,
			data : '${fns:getMenuList()}'
		})
	</script>
	
	
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:16 GMT -->
</html>