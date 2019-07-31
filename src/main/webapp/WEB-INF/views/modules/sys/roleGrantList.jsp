<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } </title>
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
                        <h4 class="example-title">设置角色权限</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" sysRoleGrant="group">
                                <%-- <button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="sys:sysRoleGrant:edit">
                                    <a class="J_menuItem" href="${ctx}/sys/sysRoleGrant/form?parentId=${sysRoleGrant.id}">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                   	</a>
                                   	</shiro:hasPermission>
                                </button>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                                </button>--%>
                                <input id="chkAllGrant" type="checkbox" />全选
                            </div>
                            <ul>
                            <c:forEach items="${sourcelist }" var="sysRoleGrant">
                            <c:if test="${sysRoleGrant.parentId==1 }">
                            <li class="list-group-item" chk="chk">
                            	<input type="checkbox" id="menu${sysRoleGrant.id }" value="${sysRoleGrant.id }">
                            	<a class="text-navy" href="javascript:">${sysRoleGrant.name}</a>
                            </li>
							<li class="list-group-item">		
								<ul>
								<c:forEach items="${sourcelist }" var="tGrant">
                                <c:if test="${sysRoleGrant.id==tGrant.parentId }">
									<li class="list-group-item" chk="chkSec">
										<input type="checkbox" id="menu${tGrant.id }" value="${tGrant.id }">
										<a class="text-navy" href="javascript:">${tGrant.name}</a>&nbsp;
									</li>
									<li class="list-group-item" chk="chkSec">
										<ul>
											<c:forEach items="${sourcelist }" var="grant">
			                                <c:if test="${tGrant.id==grant.parentId }">
			                                <li style="list-style: none;" chk="chkLast">
			                                	<input type="checkbox" id="menu${grant.id }" value="${grant.id }" chk="chkMin">
												<a class="text-navy" href="javascript:">${grant.name}</a>&nbsp;
												<c:forEach items="${sourcelist }" var="g">
				                                	<c:if test="${grant.id==g.parentId }">
														&nbsp;<input type="checkbox" id="menu${g.id }" value="${g.id }">
														<a class="text-navy" href="javascript:">${g.name}</a>&nbsp;
													</c:if>
												</c:forEach>
											</li>
											</c:if>
											</c:forEach>
										</ul>
									</li>
								</c:if>
								</c:forEach>
								</ul>
							</li>
							</c:if>
							</c:forEach>
                            </ul>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" id="btnSetRoleGrant">保存</button>&nbsp;
                                    <button class="btn btn-primary" onclick="history.go(-1)">返回</button>
                                </div>
                            </div>
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
	//${ctx}/sys/org/delete?id=${sysRoleGrant.id}
		
		$(function(){
			
			//全选
			$("#chkAllGrant").click(function(){
				$(this).attr("checked",!$(this).attr("checked"));
				if($(this).attr("checked")){
					$("input[id^='menu']").prop("checked",true);
				}else{
					$("input[id^='menu']").prop("checked",false);
				} 
			});
			//单选-全选
			$("input[id^='menu']").click(function(){
				//判断是否单击顶级复选框
				if($(this).parent().attr("chk")=="chk"){
					//选中子集复选框
					$(this).parent().next().find("input").prop("checked",$(this).prop("checked"));
				}
				//反选
				if($(this).parent().attr("chk")!="chk"){
					//选中子集复选框
					//alert('not chk');
					if($(this).parent().attr("chk")=="chkSec"){
						//选中子集复选框
						$(this).parent().next().find("input").prop("checked",$(this).prop("checked"));
						//$(this).parent().parent().parent().parent().parent().css({"color":"red","border":"2px solid red"});
						$(this).parent().parent().parent().prev().find("input:eq(0)").prop("checked",$(this).prop("checked"));
					}else{
						debugger;
						//alert($(this).parent().siblings().find("input").prop("checked"));
						$(this).parent().parent().parent().prev().find("input:eq(0)").prop("checked",$(this).prop("checked"));
						$(this).parent().parent().parent().parent().parent().prev().find("input:eq(0)").prop("checked",$(this).prop("checked"));
					}
				}
				/* if($(this).parent().attr("chk")=="chkLast"){
					//所有
					var n1=$(this).parent().find("input:gt(0)").length;
					var n2=$(this).parent().find("input:gt(0):checked").length;
					if(n1==n2){
						$(this).parent().children().first().prop("checked",true);
					}else{
						$(this).parent().children().first().prop("checked",false);
					}
					//选中子集复选框
					//$(this).parent().find("input:gt(0)").prop("checked",$(this).prop("checked"));
				} */
				
				//获取所有数量
				var sumInput=$("input[id^='menu']").length;
				//获取当前选中的数量
				var sumChecked=$("input[id^='menu']:checked").length;
				if(sumInput==sumChecked){
					$("#chkAllGrant").prop("checked",true);
				}else{
					$("#chkAllGrant").prop("checked",false);
				}
				
			});
			
			//保存角色权限
			$("#btnSetRoleGrant").click(function(){
				var menuIds="";
				$("input[id^='menu']:checked").each(function(index,chk){
					menuIds+=$(chk).val()+",";
				});
				$.post("${ctx}/sys/role/saveMenus",{
					menuIds:menuIds,
					roleId:'${roleId}'
				},function(data){
					layer.msg('权限设置成功！', {icon: 1});
					window.location.href="${ctx}/sys/role/list";
				});
			});
			
			//单击第一个顶级CheckBox
			/* $("li[id^=firstLi] input").click(function(){
				$(this).attr("checked",!$(this).attr("checked"));
			}); */
			
			//遍历回选
			$.each($.parseJSON('${menuList}'),function(index,val){
				$("#menu"+val).attr("checked",true);
			});
			
		})
	</script>
</body>
</html>
