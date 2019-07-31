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
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.ztree {
			overflow: auto;
			margin: 0;
			_margin-top: 10px;
			padding: 10px 0 0 10px;
		}
	</style>
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#scoreForm").submit();
        	return false;
        }
		
		//获取 学员 ajax						
		
		$.ajax({
			url:"${ctx}/counsel/counselQuestion/getVisitorInfoList",
			type:"POST",
			dataType:"json",
			data:{"parent":0},
			success:function(data){
				//alert(data);
				for (i = 0; i < data.length; i++){
					var visitorInfo = data[i];
					var visitorId = $('#askId').val();
					var option = "";
					if(visitorId != null && visitorId==visitorInfo["id"] ){
						//alert("");
						option = 						
						"<option id='"+visitorInfo["id"]+"' title='"+visitorInfo["realName"]+"' value='"+visitorInfo["id"]+"'  selected='true' >"+visitorInfo["realName"]+"</option>";
					}else{
						option =  
						"<option id='"+visitorInfo["id"]+"' title='"+visitorInfo["realName"]+"' value='"+visitorInfo["id"]+"'>"+visitorInfo["realName"]+"</option>";
					}
					
					$('#visitorInfoSelect').append(option);
					
				}
			}
		});
	</script>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<!-- <h4 class="example-title">来访者积分列表</h4> -->
						<div class="example">
							<sys:message content="${message}"/>
							<form id="scoreForm" action="${ctx}/ante/visitorInfo/list" method="post" class="breadcrumb form-search">
									<input type="hidden" id="id" name="id" /> 
									<input type="hidden" id="parentId" name="parentId" />
									<input type="hidden" id="historyId" name="historyId" />
									<!-- <div>
										<input id="orgNameChecked" name="orgNameChecked" type="checkbox"/>
										<label for="orgNameChecked">所属机构：</label>&nbsp&nbsp&nbsp
										<input id="orgName" type="text" maxlength="11"/>
									</div> -->
									<div>
										<label>组织机构:</label>
										<div id="ztree" class="ztree"></div>
									</div> 
									<div>
										<input id="visitorNoChecked" name="visitorNoChecked" type="checkbox"/>
										<label for="visitorNoChecked">来访者编号：</label>
										<input id="visitorNo" name="visitorNo" value="${visitorInfo.visitorNo}" type="text" maxlength="30"/>
									</div>
									<div>
										<input id="scoreChecked" name="scoreChecked" type="checkbox"/>
										<label>积分：</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										<input id="score" name="score" value="${visitorInfo.user.score}" type="number" maxlength="3"/>
									</div>
									
									<div class="btn-group " id="exampleTableEventsToolbar" role="group">
										<a class="J_menuItem" href="#" onclick="scorePlus()">
											<button type="button" class="btn btn-outline btn-default">
												<shiro:hasPermission name="ante:visitorInfo:view">积分增加</shiro:hasPermission>
											</button>
										</a>
										<%-- <a class="J_menuItem" href="${ctx}/ante/visitorInfo/scoreForm">
											<button type="button" class="btn btn-outline btn-default">
												<shiro:hasPermission name="ante:visitorInfo:view">积分设置</shiro:hasPermission>
											</button>
										</a> --%>
										<a class="J_menuItem" href="${ctx}/ante/visitorInfo/scoreList?visitorNo=${visitorInfo.visitorNo}">
											<button type="button" class="btn btn-outline btn-default">
												<shiro:hasPermission name="ante:visitorInfo:view">积分查询</shiro:hasPermission>
											</button>
										</a>
									</div>
							</form>
							<table id="visitorInfoScoreTable" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="false">
								<thead>
									<tr>
										<th data-field="visitorNo">学号</th>
										<th data-field="user.name">姓名</th>
										<th data-field="orgId">机构</th>
										<th data-field="user.score">积分</th>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="visitorInfo">
										<tr>
											<td><a class="text-navy" href="${ctx}/ante/visitorInfo/scoreForm?id=${visitorInfo.id}">${visitorInfo.visitorNo}</a></td>
											<td>${visitorInfo.user.name}</td>
											<td>
												<c:forEach items="${offices.list}" var="office">
													<c:if test="${visitorInfo.orgId==office.id}">
														${office.name}
													</c:if>
												</c:forEach>
											</td>
											<td>${visitorInfo.user.score}</td>
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

	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			 var parentId = '${parentId}',
			 	setting = { data: { 
				 		simpleData:{ 
				 			enable: true, 
				 			idKey: "id", 
				 			pIdKey: "pId", 
				 			rootPId: parentId
				 		}
				 	},
	       			callback:{onClick:function(event, treeId, treeNode){
	       					var id = treeNode.id == '0' ? '' : treeNode.id;
	       					$("#historyId").val(id);
	       					$('#id').val(id);
	       					$("#parentId").val(treeNode.pId);
	       					$('#name').val(treeNode.name);
	       				}
	       			}
       			};
	        $.getJSON("${ctx}/sys/org/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(false);
			});
	/* 		$("#orgForm").validate({
				rules: {
					name: {
						required: true,
						remote: {
    						url: "${ctx}/sys/org/checkRepeatOrgName",
                            type: "post",
                            dataType: "json",
                            data: {
                            	name: function () {
                            		return $("#name").val();
                            	}
                            },
                            dataFilter: function (data) {
                            	return (data == 1);
                            }
        				}
					}
				},
				messages: {
					name: {
						required: '组织机构名称必填！',
						remote: '该选择的组织下机构名称重复,请重新填写!'
					}
				},
				submitHandler: function(form){
					var historyId = $("#historyId").val(), parentId = $("#parentId").val();
					if(parentId == null || parentId == ''){
						$("#parentId").val(historyId);
					}
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
			}); */
			
			$("#btnCancel").on("click", function(){
				 $("#id").val('');
				 $("#name").val('');
				 $("#parentId").val('');
			});
			
			/* $("#btnDelete").on("click", function(){
				var id = $("#id").val();
				layer.confirm('是否删除所选择的组织机构？', {
				    btn: ['确认','取消'], //按钮
				    title:'提示',
				    shade: false //不显示遮罩
				}, function(){
					document.location.href="${ctx}/sys/org/delete?id="+id;
				    layer.msg('删除成功！', {icon: 6});
				}, function(){
				    layer.msg('已取消', {icon: 1});
				});
			}); */
		});
		
		/**
		 * 新增积分
		 */
		function scorePlus(){
			var visitorNo = document.getElementById("visitorNo").value;
			var score = document.getElementById("score").value;
			//alert(visitorNo);
			if(score==null||score==''){
				layer.msg('积分不能为空！', {icon: 6});
				return false;
			}
			document.location.href="${ctx}/ante/visitorInfo/scorePlus?scorePlus=true"+"&visitorNo="+visitorNo+"&user.score="+score;
		    layer.msg('新增积分成功！', {icon: 6});
		}
	</script> 
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:06 GMT -->
</html>
