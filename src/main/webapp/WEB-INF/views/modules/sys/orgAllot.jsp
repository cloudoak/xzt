<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">

    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title text-navy">正在为(${office.name})分配数据</h4>
				        <div class="row m-b-lg">
			            <div class="col-sm-12">
			                <div class="tabs-container">
			                    <div class="tabs-up">
			                        <ul class="nav nav-tabs">
			                            <li class="active"><a data-toggle="tab" href="#tab-8"> 管理员账号管理</a></li>
			                            <li class=""><a data-toggle="tab" href="#tab-9"> 量表数据分配</a></li>
			                        </ul>
			                        <div class="tab-content ">
			                            <div id="tab-8" class="tab-pane active">
			                                <div class="panel-body">
			                                	<div class="btn-group " id="exampleTableEventsToolbar" role="group">
					                                <shiro:hasPermission name="sys:office:edit"><a class="J_menuItem" href="${ctx}/sys/org/OrgUserAcountForm?orgId=${office.id}">
						                            <button type="button" class="btn btn-outline btn-default">
						                                <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
						                            </button>
					                               	</a></shiro:hasPermission>
						                            <!-- <button type="button" class="btn btn-outline btn-default">
						                                <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
						                            </button>
						                            <button type="button" class="btn btn-outline btn-default">
						                                <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
						                            </button> -->
						                        </div>
				                                <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-width="1400" data-pagination="true" data-search="true">
					                                <thead>
					                                    <tr>
					                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
					                                        <th data-field="loginName">账号</th>
					                                        <th data-field="name">姓名</th>
					                                        <th data-field="phone">联系电话</th>
					                                        <th>操作</th>
					                                    </tr>
					                                </thead>
					                                <c:forEach items="${list }" var="user">
														<tr>
															<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
															<td><a class="text-navy" href="${ctx}/sys/org/OrgUserAcountForm?orgId=${office.id}&id=${user.id}">${user.loginName}</a></td>
															<td>${user.name}</td>
															<td>${user.phone}</td>
															<td>
																<a class="text-navy" href="${ctx}/sys/org/OrgUserAcountForm?orgId=${office.id}&id=${user.id}">
																	<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
																</a>&nbsp;
																<a href="#" class="text-danger" onclick="delOrgUser('${user.id}','${user.loginName}')">
																	<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
																</a>
															</td>
														</tr>
													</c:forEach>
					                            </table>
												<div class="col-sm-8 col-sm-offset-5">
				                                <button class="btn btn-primary col-sm-1" type="button" onclick="gotoOrgList(${office.id})">返回</button>
				                                </div>
			                                </div>
			                            </div>
			                            <div id="tab-9" class="tab-pane">
			                                <div class="panel-body">
			                                   
						                       	<div class="row">
						                            <div class="col-sm-6">
						                                <div class="panel panel-default">
						                                    <div class="panel-heading"> 可分配量表</div>
						                                    <div class="panel-body">
						                                        <select id="selectUse" multiple="multiple" class="form-control m-b">
						                                        	<c:forEach items="${useScaleList }" var="scale">
						                                        	<option value="${scale.id }">${scale.name }</option>
						                                        	</c:forEach>
						                                        </select>
						                                    </div>
						                                </div>
						                            </div>
						                            <div class="col-sm-6">
						                                <div class="panel panel-primary">
						                                    <div class="panel-heading"> 已分配量表</div>
						                                    <div class="panel-body">
						                                        <select id="selectOrgScle" multiple="multiple" class="form-control m-b">
						                                        	<c:forEach items="${scaleList }" var="orgScale">
						                                        	<option value="${orgScale.sid }">${orgScale.scale.name }</option>
						                                        	</c:forEach>
						                                        </select>
						                                    </div>
						                                </div>
						                            </div>
						                        </div>
						                       <div class="col-sm-8 col-sm-offset-5">
			                                   <button class="btn btn-primary" type="button" onclick="addOrgAllot()">确认分配</button>
			                                   <button class="btn btn-primary" type="button" onclick="gotoOrgList(${office.id})">返回</button>
			                                   </div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			            </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8 col-sm-offset-5">
                        	<%-- <a href="${ctx}/sys/org"> --%>
                           		
                          	<!-- </a> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
    <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
    <!-- <script src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8" type="text/javascript"></script> -->
	<script type="text/javascript">
	//${ctx}/sys/org/delete?id=${office.id}
		function delOrgUser(id,name){
			layer.confirm('是否删除 ('+name+') 账号？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/sys/org/delOrgUser?userId="+id+"&orgId=${office.id}";
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		//返回
		function gotoOrgList(id){
			window.location.href="${ctx}/sys/org";
		}
		$(function(){
			//可用
			$("#selectUse option").click(function(){
				 var sid=$(this).val();
				 var sname=$(this).html();
				 var isLeft=false;
				 $("#selectOrgScle option").each(function(index,option){
					 if(sid===$(option).val()){
						 layer.msg('该量表已分配！', {icon: 6});
						 isLeft=true;
					 }
				 });
				 if(isLeft)return false;
				 $("#selectOrgScle").append('<option onclick="chkMove()" value="'+sid+'">'+sname+'</option>');
				 //$("#selectUse option[value='"+sid+"']").remove();
				//已分配
				$("#selectOrgScle option").click(function(){
					var sid=$(this).val();
					var sname=$(this).html();
					$("#selectOrgScle option[value='"+sid+"']").remove();
				});
			});
			//已分配
			$("#selectOrgScle option").click(function(){
				var sid=$(this).val();
				var sname=$(this).html();
				$("#selectOrgScle option[value='"+sid+"']").remove();
			});
		})
		//为右边系添加的绑定单击事件
		/* function chkMove(){
			var sid=$(this).val();
			var sname=$(this).html();
			$("#selectOrgScle option[value='"+sid+"']").remove();
		} */
		//分配量表数据
		function addOrgAllot(){
			var sids="";
			$("#selectOrgScle option").each(function(index,option){
				sids+=$(this).val()+",";
			});
			window.location.href="${ctx}/sys/org/allotAll?sids="+sids+"&orgId=${office.id}";
			layer.msg('分配量表数据成功', {icon: 1});
			/* $.get("${ctx}/jzmk/orgScale/allotAll",{
				sids:sids,
				orgId:'${office.id}'
			},function(da){
				if(da==='true'){
					layer.msg('分配量表数据成功', {icon: 1});
				}
			}); */
		}
	</script>
</body>
</html>
