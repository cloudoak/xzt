<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>角色管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="#">角色<shiro:hasPermission name="sys:role:edit">${role.id!=null?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:role:edit">查看</shiro:lacksPermission></a></h5>
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
                        <form class="form-horizontal m-t" id="roleForm" action="${ctx}/sys/role/save" method="post">
                            <input type="hidden" name="id" id="id" value="${role.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">角色名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" value="${role.name }" class="form-control" type="text" >
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>（必填）</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">英文名称：</label>
                                <div class="col-sm-8">
                                    <input id="enname" name="enname" value="${role.enname }" class="form-control" type="text">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>（必填）</span>
                                </div>
                            </div>
                           <%--  <div>
                            	<label class="col-sm-3 control-label">权限资源：</label>
                                <div class="col-sm-8">
                            	<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
			                    <c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
			                     <div class="ibox-content">
				                    <div id="treeview6" class="test" ></div>
				                </div> --%>
			                    <%-- <label class="checkbox-inline i-checks"><input type="checkbox" value="${menu.id}">${menu.name}</label>
			                    <div class="hr-line-dashed"></div>
	                            <div class="form-group">
	                                <div class="col-sm-10">
	                                	<c:forEach items="${fns:getMenuList()}" var="mc" varStatus="idxStatus">
			                            <c:if test="${mc.parent.id eq menu.id&&mc.isShow eq '1'}">
											<c:if test="${mc.href eq '' or mc.href eq null}">
												<label class="checkbox-inline i-checks"><input type="checkbox" value="${mc.id}">${mc.name}</label>
						                            <c:forEach items="${fns:getMenuList()}" var="mcc" varStatus="idxStatus">
						                            <c:if test="${mcc.parent.id eq mc.id&&mcc.isShow eq '1'}">
						                            <label class="checkbox-inline i-checks"><input type="checkbox" value="${mcc.id}">${mcc.name}</label>
													</c:if>
						                            </c:forEach>
											</c:if>
										</c:if>
			                            </c:forEach>
	                                </div>
	                            </div> --%>
			                    <%-- </c:if>
			                    </c:forEach>
			                	</div>
                            </div> --%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
                                	<textarea id="remarks" name="remarks" rows="" cols="" class="form-control">${role.remarks }</textarea>
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>（选填）</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">保存</button>&nbsp;
                                    <input class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回"/>
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
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${hplusStatic }/js/plugins/treeview/bootstrap-treeview.js"></script>
    <script>
        $(function(){
        	$("#roleForm").validate({
        		errorElement: 'div',
        		errorClass: 'help-block',
        		focusInvalid: false,
        				rules: {
        			    	name: {
        			    			required: true,
        			    			remote: {
        				    		    url: "${ctx}/sys/role/checkRole",     //后台处理程序
        				    		    type: "post",               //数据发送方式
        				    		    dataType: "json",           //接受数据格式   
        				    		    data: {                     //要传递的数据
       				    		            name: function() {
       				    		            	return $("#name").val();
       				    		        	},
       				    		        	id:function(){
       				    		        		return $("#id").val();
       				    		        	}
        				    		    },
        				    		    dataFilter: function (data) {
			                            	return (data == 1);
			                            }
        				    		}
        			    		},
        					  enname:{
        						  required:true
        					  }
        			    	},messages: {
        			    		name: {
        			    			required: "角色不能为空！",
        			    			remote: "角色已存在！"
        			    		},
        			    		enname:{
        			    			required: "英文名称不能为空！"
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
        						form.submit();
        					}
        			});
        			
        	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});
       });
    $("#treeview6").treeview({
    	color:"#428bca",
    	expandIcon:"glyphicon glyphicon-stop",
    	collapseIcon:"glyphicon glyphicon-unchecked",
    	nodeIcon:"glyphicon glyphicon-user",showTags:!0,data:'${fns:getMenuList()}'});
    </script>
</body>
</html>