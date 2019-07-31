<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>测评任务添加</title>
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
<link
	href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
<link href="${hplusStatic }/css/plugins/summernote/summernote.css"
	rel="stylesheet">
<link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css"
	rel="stylesheet">
<link
	href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link
	href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"
	rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/dropdownTreeStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.ztree {
	overflow: auto;
	margin: 0;
	_margin-top: 10px;
	padding: 10px 0 0 10px;
}
</style>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<a class="text-navy" href="javascript:">测评任务添加</a>
						</h5>
						<div class="ibox-tools"></div>
					</div>
					<sys:message content="${message}" />
					<div class="ibox-content">
						<div>
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#tab-1"
									aria-expanded="true">咨询师</a></li>
								<li class=""><a data-toggle="tab" href="#tab-2"
									aria-expanded="false">来访者</a></li>
								<li class=""><a data-toggle="tab" href="#tab-3"
									aria-expanded="false">家属</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active">
									<div class="panel-body">
										<div class="col-sm-12">
											<label class="col-sm-2">所在部门</label> <select
												name="departmentSel" id="departmentSel" class="col-sm-4">
												<option value="">--请选择--</option>
												<c:forEach items="${departmentList }" var="department">
													<option value="${department.id }">${department.deptName }</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-12">
											<label class="col-sm-2 control-label">咨询师类型</label> <select
												name="teacherType" id="teacherType" class="col-sm-4">
												<option value="">--请选择--</option>
												<c:forEach items="${counselorTypeList }" var="counselorType">
													<option value="${counselorType.id }">${counselorType.typeName }</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-12">
											<label class="col-sm-2 control-label">姓名</label> <input
												name="teacherName" id="teacherName" class="col-sm-4"
												type="text" />
										</div>
										<div class="col-sm-12">
											<label class="col-sm-2 control-label">性别</label> <input
												id="male" type="radio" name="sex" class="col-sm-1" /> <label
												class="col-sm-1 control-label">男</label> <input id="female"
												type="radio" name="sex" class="col-sm-1" /> <label
												class="col-sm-1 control-label">女</label>
										</div>
										<div class="col-sm-12">
											<label class="col-sm-2 control-label">年龄</label> <input
												name="beginAge" id="beginAge" class="col-sm-1" type="text" /><label
												class="col-sm-1 control-label">至</label><input name="endAge"
												id="endAge" class="col-sm-1" type="text" />
										</div>
										<div class="col-sm-12">
											<div class="col-sm-8 col-sm-offset-3">
												<button id="btnQuery1" class="btn btn-primary"
													onclick="javascript:ajaxQuery(1);">查询</button>
											</div>
										</div>
										<div id="queryResult1" class="example" style="display: none">
											<table id="dataTable1" data-toggle="table"
												data-query-params="queryParams"
												data-mobile-responsive="true" data-pagination="true">
												<thead>
													<tr>
														<th data-field="id">ID</th>
														<th data-field="loginName">ID</th>
														<th data-field="name">姓名</th>
														<th data-field="sex">性别</th>
														<th data-field="identity">身份</th>
														<shiro:hasPermission name="jzmk:scaleType:edit">
															<th data-field="operation">操作</th>
														</shiro:hasPermission>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
											<div class="form-group">
												<%-- <form class="form-horizontal m-t" id="signupForm"
													action="${ctx}/jzmk/scaleCheckTask/save?queryType=1"
													method="post"> --%>
												<input type="hidden" name="id" value="${scale.id }">
												<div class="col-sm-8 col-sm-offset-3">
													<button class="btn btn-primary" type="button"
														onclick="javascript:submitCheck(1);">下一步</button>
												</div>
												<!-- </form> -->
											</div>
										</div>
									</div>
								</div>
								<div id="tab-2" class="tab-pane">
									<div class="panel-body">
										<div>
											<div class="col-sm-12">
												<label class="col-sm-2">所属组织</label>
												<input type="hidden" id="orgId" name="orgId" />
			                        			<input id="orgInput" name="orgInput" type="text" readonly="readonly" class="col-sm-4"/>
											</div>
											<div class="col-sm-12">
												<label class="col-sm-2">来访者编号</label> <input
													name="studentNumber" id="studentNumber" class="col-sm-4"
													type="text" />
											</div>
											<div class="col-sm-12">
												<label class="col-sm-2">姓名</label> <input name="studentName"
													id="studentName" class="col-sm-4" type="text" />
											</div>
											<div class="col-sm-12">
												<label class="col-sm-2">性别</label> <input id="male"
													type="radio" name="sex" class="col-sm-1" /><label
													class="col-sm-1">男</label><input id="female" type="radio"
													name="sex" class="col-sm-1" /><label class="col-sm-1">女</label>
											</div>
											<div class="col-sm-12">
												<label class="col-sm-2">年龄</label> <input name="beginAge"
													id="beginAge" class="col-sm-1" type="text" /><label
													class="col-sm-1">至</label><input name="endAge" id="endAge"
													class="col-sm-1" type="text" />
											</div>
											<!-- <div class="col-sm-12">
												<label class="col-sm-2">入学年度</label> <input name="enrolYear"
													id="enrolYear" class="col-sm-4" type="text" />
											</div> -->
										</div>
										<div class="col-sm-8 col-sm-offset-3">
											<button id="btnQuery2" class="btn btn-primary"
												onclick="javascript:ajaxQuery(2);">查询</button>
										</div>
										<div id="queryResult2" class="example" style="display: none">
											<table id="dataTable2" data-toggle="table"
												data-query-params="queryParams"
												data-mobile-responsive="true" data-pagination="true">
												<thead>
													<tr>
														<th data-field="id">ID</th>
														<th data-field="loginName">ID</th>
														<th data-field="name">姓名</th>
														<th data-field="sex">性别</th>
														<th data-field="identity">身份</th>
														<shiro:hasPermission name="jzmk:scaleType:edit">
															<th data-field="operation">操作</th>
														</shiro:hasPermission>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
											<div class="form-group">
												<form class="form-horizontal m-t" id="signupForm"
													action="${ctx}/jzmk/scaleCheckTask/save?queryType=2"
													method="post">
													<input type="hidden" name="id" value="${scale.id }">
													<div class="col-sm-8 col-sm-offset-3">
														<button class="btn btn-primary" type="button"
															onclick="javascript:submitCheck(2);">下一步</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
								<div id="tab-3" class="tab-pane">
									<div class="panel-body">
										<div>
											<div class="col-sm-12">
												<label class="col-sm-2">来访者组织</label>
												<input type="hidden" id="parentOrgId" name="parentOrgId" />
			                        			<input id="parentOrgInput" name="parentOrgInput" type="text" readonly="readonly" class="col-sm-4"/>
											</div>
											<div class="col-sm-12">
												<label class="col-sm-2">来访者编号</label> <input
													name="studentNumber" id="studentNumber" class="col-sm-4"
													type="text" />
											</div>
											<div class="col-sm-12">
												<label class="col-sm-2">家属姓名</label> <input
													name="parentName" id="parentName" class="col-sm-4"
													type="text" />
											</div>
											<div class="col-sm-12">
												<label class="col-sm-2">家属性别</label> <input id="male"
													type="radio" name="sex" class="col-sm-1" /><label
													class="col-sm-1">男</label><input id="female" type="radio"
													name="sex" class="col-sm-1" /><label class="col-sm-1">女</label>
											</div>
											<div class="col-sm-12">
												<label class="col-sm-2">家属年龄</label> <input name="beginAge"
													id="beginAge" class="col-sm-1" type="text" /><label
													class="col-sm-1">至</label><input name="endAge" id="endAge"
													class="col-sm-1" type="text" />
											</div>
											<!-- <div class="col-sm-12">
												<label class="col-sm-2">入学年度</label> <input name="enrolYear"
													id="enrolYear" class="col-sm-4" type="text" />
											</div> -->
										</div>
										<div class="col-sm-8 col-sm-offset-3">
											<button id="btnQuery3" class="btn btn-primary"
												onclick="javascript:ajaxQuery(3);">查询</button>
										</div>
										<div id="queryResult3" class="example" style="display: none">
											<table id="dataTable3" data-toggle="table"
												data-query-params="queryParams"
												data-mobile-responsive="true" data-pagination="true">
												<thead>
													<tr>
														<th data-field="id">ID</th>
														<th data-field="loginName">ID</th>
														<th data-field="name">姓名</th>
														<th data-field="sex">性别</th>
														<th data-field="identity">身份</th>
														<shiro:hasPermission name="jzmk:scaleType:edit">
															<th data-field="operation">操作</th>
														</shiro:hasPermission>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
											<div class="form-group">
												<form class="form-horizontal m-t" id="signupForm"
													action="${ctx}/jzmk/scaleCheckTask/save?queryType=3"
													method="post">
													<input type="hidden" name="id" value="${scale.id }">
													<div class="col-sm-8 col-sm-offset-3">
														<button class="btn btn-primary" type="button"
															onclick="javascript:submitCheck(3);">下一步</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${hplusStatic }/js/plugins/layer/layer.min.js"
		type="text/javascript"></script>
	<script
		src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"
		type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
	<script type="text/javascript">
    function ajaxQuery(v){
    	debugger;
    	var parameters ="";
    	switch(v)
    	{
    	case 1:
    		parameters = "queryType="+v+"&userName="+$("#teacherName").val();
    		break;
    	case 2:
    		parameters = "queryType="+v+"&userName="+$("#studentName").val();
    		break;
    	case 3:
    		parameters = "queryType="+v+"&userName="+$("#parentName").val();
    		break;
    	}
        $.ajax({  
        //data:"queryType="+v,
        data:parameters,
        async:false,
        type:"POST",  
        dataType: 'json',  
        url:"${ctx}/jzmk/scaleCheckTask/queryCounselor",  
        error:function(data){  
            //alert("出错了！！:");
            layer.alert("没有数据");
        },  
        success:function(data){  
            //alert("success:");
            var json = eval(data); //数组
            var count = loadData(json,v);
            /*
            $.each(json, function (index, item) {  
                //循环获取数据    
                var name = json[index].name;  
                var id = json[index].id;  
                var sex = json[index].sex;
                var type = json[index].type;
                alert(name+" "+id+" "+sex+" "+type);
                //$("#list").html($("#list").html() + "<br>" + name + " - " + idnumber + " - " + sex + "<br/>");  
            });
           	*/
           	if(count==0)
           	{
           		layer.alert("没有数据");
           	}
           	if(count>0){
            	$('#'+'queryResult'+v).css('display','block');
            	$('tr').find('th:eq(0)').hide(); 
            	$('tr').find('td:eq(0)').hide();
            	//$('#dataTable'+v).bootstrapTable('hideColumn', 'id');
           	}
            
        }  
        });  
    	
    }  
    function loadData(res,t) {
    	var retCount = 0;
        var tBody = $("#"+"dataTable"+t).find("tbody");
        tBody.html('');
        for ( var index in res) {  
            //新建一行  
            var newTr = $("<tr></tr>");  
            //新建节点  
            var itemId = $("<td data-field='id' data-visible='false'></td>");
            var itemLoginName = $("<td data-field='loginName'></td>"); 
            var itemName = $("<td data-field='name'></td>"); 
            var itemSex = $("<td data-field='sex'></td>");
            var itemType = $("<td data-field='identity'></td>");
            var itemOperate = $("<td data-field='operation'></td>");
           /*  var itemId = $("<td data-field='id'></td>");
            var itemLoginName = $("<td></td>"); 
            var itemName = $("<td></td>"); 
            var itemSex = $("<td></td>");
            var itemType = $("<td></td>");
            var itemOperate = $("<td></td>"); */
            //新建超链接  
            var newsA = $("<a></a>");  
  
            //添加内容和时间  
            var id = res[index].id;
            var loginName = res[index].loginName; 
            var name = res[index].name; 
            var sex = res[index].sex==1?'男':'女';
            var type = res[index].type;
            /* alert(noticeTitle); 
            alert(noticeDate); */  
            //newsA.text(noticeTitle);  
            //dateTd.text(noticeDate);
            var deleteText ="<a href='#' class='text-danger' title='删除' onclick='delItem(this);'><i class='glyphicon glyphicon-trash' aria-hidden='true'></i></a>";
            //添加数据td-tr-tbody  
            newTr.append(itemId.text(id));
            newTr.append(itemLoginName.text(loginName));
            newTr.append(itemName.text(name));
            newTr.append(itemSex.text(sex));
            newTr.append(itemType.text(type));
            newTr.append(itemOperate.html(deleteText));
            //newTr.append(dateTd);  
            tBody.append(newTr);
            retCount++;
        }
        return retCount;
    }
    function delItem(tr){
    	//alert();
    	/*
    	if(confirm("确定删除吗？"))
    	{
    		$(tr).parents("tr").remove();
    	}
    	*/
		var prompt=layer.confirm('确定删除吗？', {
		    btn: ['确认','取消'], //按钮
		    title:'删除确认',
		    shade: false //不显示遮罩
		}, function(){
			$(tr).parents("tr").remove();
			layer.close(prompt);
		}, function(){
		});
	}
    
    function submitCheck(v)
    {
    	
    	if( $("#"+"dataTable"+v).find("tbody").children("tr").length > 0)
    	{
    		var a = $.map($("#"+"dataTable"+v).find("tbody").children("tr"), function (row) {
                return $(row).children().eq(0).text();
            });
    		var ids=JSON.stringify(a);
    		//alert("${ctx}/jzmk/scaleCheckTask/save?queryType="+v+"&ids="+ids);
    		document.location.href="${ctx}/jzmk/scaleCheckTask/save?queryType="+v+"&ids="+ids;
    		//$("#signupForm").submit();
    	}
    	else
    	{
    		layer.alert('请选择参加测评的用户');
    	}
    	
    }
    </script>
	<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script
		src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
	<script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
	<script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
	<script type="text/javascript"
		src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

	<script type="text/javascript">
	var dropdownTree;
	var parentDropdownTree;
	$(document).ready(function() {
		//var epResultStateStr = '${epResultState}';
		dropdownTree = $("#orgInput").dropdownTree({
		    	root: "${rootParentId}",
	            async: {
	            	url: "${ctx}/sys/org/asynTree",
	            	type: "json",
	            	params: ["id"]
	            },
	            selectValue: orgId
		});
		 parentDropdownTree = $("#parentOrgInput").dropdownTree({
	    	root: "${rootParentId}",
            async: {
            	url: "${ctx}/sys/org/asynTree",
            	type: "json",
            	params: ["id"]
            },
            selectValue: parentOrgId
		}); 
	});
    </script>
	<!-- <script src="http://tajs.qq.com/stats?sId=9051096" type="text/javascript" charset="UTF-8"></script> -->
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:16 GMT -->
</html>