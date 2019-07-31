<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>咨询记录管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>


<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>咨询记录</h5>
                        <div class="ibox-tools">
                            <!--
<a class="text-navy" href="${ctx}/sys/org/form?id=${office.id}&parent.id=${office.parent.id}">咨询室查看</a>
							<a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
							<shiro:hasPermission name="sys:office:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission>
							-->
                        </div>
                    </div>
<!--
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/counsel/counselQuestion/">答疑室问题列表</a></li>
		<li class="active"><a href="${ctx}/counsel/counselQuestion/form?id=${counselQuestion.id}">答疑室问题<shiro:hasPermission name="counsel:counselQuestion:edit">${not empty counselQuestion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="counsel:counselQuestion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	-->
	
	
	<div class="ibox-content">
                        <form:form class="form-horizontal m-t" id="inputForm" 
						modelAttribute="counselRecord"
						action="${ctx}/counsel/counselRecord/save" method="post">
                            <input type="hidden" name="id" value="${counselRecord.id }">
							
							<input type="hidden" id="visitorId" name="visitorId" value="${counselRecord.visitorId }">
							<input type="hidden" id="visitorName" name="visitorName" value="${counselRecord.visitorName }">
							
							<input type="hidden" id="counselorId" name="counselorId" value="${counselRecord.counselorId }">
							<input type="hidden" id="counselorName" name="counselorName" value="${counselRecord.counselorName }">
							
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学员：</label>
                                <div class="col-sm-8">
                                    <!--
									<input id="name" name="name" value="${office.name }" class="form-control" type="text" required="required">
									-->
									<select id="visitorInfoSelect"  class="required">
									</select>
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 学员名称（必填）</span>
                                </div>
                            </div>
							
							  <div class="form-group">
                                <label class="col-sm-3 control-label">咨询师：</label>
                                <div class="col-sm-8">
									<select id="counselorSelect"  class="required">
									</select>
                                </div>
							</div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">咨询时间：</label>
                                <div class="col-sm-8">
								<input name="counselDate" type="text" readonly="readonly" maxlength="20" 
								class="form-control"
						value="<fmt:formatDate value="${counselRecord.counselDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
                                 
                                </div>
                            </div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">咨询问题类型：</label>
								<div class="col-sm-4">
									<form:select path="questionType" class="input-xlarge ">
										<!-- <form:option value="" label=""/>  -->
										<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
									</form:select>
								</div>
							</div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">问题描述：</label>
                                <div class="col-sm-8">								
									<form:textarea path="description" htmlEscape="false" maxlength="32" class="form-control"   type="text"/>
                                </div>
                            </div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">咨询内容：</label>
                                <div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="128" class="form-control" type="text"/>
                                </div>
                            </div>
							
							<div class="form-group">
			<label class="col-sm-3 control-label">是否重点个案：</label>
			<div class="col-sm-8">
				<form:radiobuttons path="isCase" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">是否家长查看：</label>
			<div class="col-sm-8">
				<form:radiobuttons path="isParentwatch" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">是否学生查看：</label>
			<div class="col-sm-8">
				<form:radiobuttons path="isStudentwatch" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		
			<div class="form-group">
			<label class="col-sm-3 control-label">督导老师：</label>
			<div class="col-sm-8">
				<div class="zTreeDemoBackground left" style="height:200px">
					<ul id="tree" class="ztree" style="height:200px"></ul>
					<input type="hidden" id="counselors"
										name="counselors" value='<s:property value="counselors" />' />
				</div>
			</div>
			</div>
							
							<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<shiro:hasPermission name="counsel:counselRecord:edit">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;</shiro:hasPermission>
									<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	
</body>

<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" ></script>
<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
  
    </script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="http://tajs.qq.com/stats?sId=9051096" type="text/javascript" charset="UTF-8"></script>
	
	<!-- 机构选择的js/css 开始 -->
	<link rel="stylesheet"  type="text/css" href="${ctxStatic}/jquery-ztree/3.5.12/css/demo.css" />
	<link rel="stylesheet"  type="text/css" href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" />
	<!--<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-1.4.4.min.js"></script>-->
	<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js"></script>
	<!--<script type="text/javascript" src="${ctxStatic}/admin/user/js/orgtree.js"></script>-->
	<!-- 机构选择的js/css 结束 -->
		
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					//学员 选择
					var visitorId = $("#visitorInfoSelect").find("option:selected").attr("id");
					var visitorName = $("#visitorInfoSelect").find("option:selected").attr("title");
					if(null == visitorId || visitorId ==''){
						return ;
					}
					$("#visitorId").val(visitorId);
					$("#visitorName").val(visitorName);
					//咨询师 选择
					var counselorId = $("#counselorSelect").find("option:selected").attr("id");
					var counselorName = $("#counselorSelect").find("option:selected").attr("title"); 
					if(null == counselorId || counselorId ==''){
						return ;
					}
					$("#counselorId").val(counselorId);
					$("#counselorName").val(counselorName);
					
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
			//获取 咨询师 ajax 
			
			$.ajax({
				url:"${ctx}/counsel/counselor/getCounselorList",
				type:"POST",
				dataType:"json",
				data:{"parent":0},
				success:function(data){
					//alert(data);
					for (i = 0; i < data.length; i++){
						var counselor = data[i];
						var counselorId = $('#counselorId').val();
						var option = "";
						if(counselorId != null && counselorId==counselor["id"] ){
							//alert("");
							option =  
							"<option id='"+counselor["id"]+"' title='"+counselor["userName"]+"' value='"+counselor["id"]+"' selected='true' >"+counselor["userName"]+"</option>";
						}else{
							option =  
							"<option id='"+counselor["id"]+"' title='"+counselor["userName"]+"' value='"+counselor["id"]+"'>"+counselor["userName"]+"</option>";
						}
						//);
						$('#counselorSelect').append(option);
					}
				}
			});
			
			
			
			
		});
	</script>
	<script type="text/javascript">
  		var setting1 = {
  				check: {
					enable: true
				},
  				data: {
  					simpleData: {
  						enable: true,
  						idKey: "id",
  						pIdKey:"parent",
  						rootPId: -1
  					}
  				},
  				callback: {
  					onCheck:onCheck
  				}
  		};
  		function onClick(event, treeId, treeNode, clickFlag) {
  			//selectOrg(treeNode.id, '');
  		};
  		
  		function onCheck(e,treeId,treeNode){
  			
            var treeObj=$.fn.zTree.getZTreeObj("tree");
            var nodes1=treeObj.getCheckedNodes(true);
            var ids="";
            for(var i=0;i<nodes1.length;i++){
	            ids +=nodes1[i].id +",";
            	//v+=nodes[i].name + ",";
	            //alert(nodes[i].id); //获取选中节点的值
            }
           	$("#counselors").val(ids);
           	
           	alert($("#counselors").val());
         };
  		
  		var json = ${counselorStr};//<s:property value="counselorStr" escape="false"/>;
  		//var json = <%//=jn%>;
 
  			
  		var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:11, pId:0, name:"随意勾选 1-1"},
			{ id:111, pId:0, name:"随意勾选 1-1-1"},
			{ id:112, pId:0, name:"随意勾选 1-1-2"},
			{ id:12, pId:0, name:"随意勾选 1-2"}
		];
  		//加入根节点
  		/*
  		var root_node = new Object(); 
  		root_node.id = 0;
  		root_node.parent = -1;
  		root_node.name = "全部";
  		root_node.open = true;
  		root_node.icon = "<%=request.getContextPath()%>/js/dtree/img/base.gif";
  		json[json.length] = root_node;
  		*/
  		
  		//var zTree;
  		$(document).ready(function(){
  				$.fn.zTree.init($("#tree"), setting1, json);
  				//设置默认 选中&展开 节点
  				var zTree_Menu = $.fn.zTree.getZTreeObj("tree");
  				//var curMenu = zTree_Menu.getNodeByParam("id", );
  				//zTree_Menu.selectNode(curMenu);
  				//zTree_Menu.expandNode(curMenu);
  		});
</script>
	
</html>