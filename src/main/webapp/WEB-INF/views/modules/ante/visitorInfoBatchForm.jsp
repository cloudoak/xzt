<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 来访者信息管理--来访者批量开通</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/dropdownTreeStyle.css" rel="stylesheet" type="text/css" />
	<link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
	 <div id="content" class="row-fluid">
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>来访者批量开通</h5>
							</div>
								<div class="ibox-content">
			<form:form id="inputForm" action="${ctx}/ante/visitorInfo/toBatchSave" method="post" class="form-horizontal">
				<div class="form-group">
                    <label class="col-sm-3 control-label"> 组织机构</label>
                    <div class="col-sm-8">
                   	 <input type="hidden" id="orgId" name="orgId" value="${visitorInfo.orgId}" />
                     <input id="orgInput" name="orgInput" type="text" class="form-control" readonly />
                    </div>
                </div>
				<div class="form-group">
					<label class="col-sm-3 control-label">编号：</label>
					<div class="col-sm-8">
						<input name="visitorNo" value="*" type="text" class="form-control" maxlength="30" /> 
						<font style="color:red;">编号里‘*’代表可替换的部分</font>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">范围：</label>
					<div class="col-sm-8">
					<div class="form-inline">
						<input id="startNo" name="startNo" type="number" value="${visitorInfo.startNo}" class="form-control" width="50" maxlength="3" /> 
						&nbsp;&nbsp;到  &nbsp;&nbsp;
						<input name="endNo" type="number" value="${visitorInfo.endNo}" width="50" class="form-control" maxlength="3" />
					</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">通配符长</label>
					<div class="col-sm-8">
						<input name="starLen" type="number" value="${visitorInfo.starLen}"  class="form-control" maxlength="1" />
						<font style="color:red;">1-5位&nbsp;&nbsp;比如生成从001到999,通配符长度为3</font>
					</div>
				</div> 
				<div class="form-group">
					<label class="col-sm-3 control-label">密码</label>
					<div class="col-sm-8">
						<fieldset>
                         <div class="i-checks">
                             <div class="radio radio-info">
                                 <input type="radio" id="pwdType1" value="0" name="passwordType" checked="checked">
                                 <label for="pwdType1">编号</label>
                             </div>
                             <div class="radio">
                             <div class="form-inline">
                             	<input type="radio" id="pwdType2" value="1" name="passwordType" >
                                 <label for="pwdType2">固定值</label>
                             	<input name="password" type="text" class="form-control" value="123456"/>
                             </div>
                             </div>
                         </div>
                         </fieldset>
					</div>
				</div>
				<div class="form-group">
				<div class="col-sm-8 col-sm-offset-3">
					<input id="btnPreview" class="btn" type="button" value="预览" />
				</div>
				</div>
				<div class="form-group">
				<div class="col-sm-8 col-sm-offset-3">
					<input id="btnConfirmOpened" class="btn btn-primary" type="submit" value="确定开通" />
					<input id="btnExport" class="btn btn-primary" type="button" value="导出" />
				</div>
				</div>
			</form:form>
			<div class="example">
			<!-- <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="false">
			</table> -->
			<table id="exampleTableToolbar"></table>
			<div class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
					<div class="col-sm-8">
						<h4>操作示例：</h4>
						<div style="color:red;text-align: left;">
						譬如生成6001,6002,6003, ... ,6010<br>
						编号填 60*<br>
						范围 1~10<br>
						通配符长度为2,因为是01至10<br>
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
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp"%>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
		var orgId = "${visitorInfo.orgId}", msg = "${message}", success = $("${success}");
		
		 $(function() {
			 
			 if(msg){
					if(success){
						top.$.jBox.closeTip();
						top.$.jBox.tip("${message}","success",{persistent:true,opacity:0});
						$("#messageBox").show();
					}else{
						top.$.jBox.closeTip();
						top.$.jBox.tip("${message}","error",{persistent:true,opacity:0});
						$("#messageBox").show();
					}
				}
			 
			 $("#exampleTableToolbar").hide();
			 
			 var dropdownTree = $("#orgInput").dropdownTree({
			    	root: "${rootParentId}",
		            async: {
		            	url: "${ctx}/sys/org/asynTree",
		            	type: "json",
		            	params: ["id"]
		            },
		            selectValue: orgId
			    });
			 
			 $("#btnExport").on("click", function(){
				location.href = "${ctx}/ante/visitorInfo/export?" + $("#inputForm").serialize(); 
			 });
			 
			 $("#btnPreview").on("click", function(){
				 $.post("${ctx}/ante/visitorInfo/toBatchSaveVisitor", $("#inputForm").serialize(), function(data, status){
					 /* var exampleTableToolbar = $("#exampleTableToolbar");
					 exampleTableToolbar.empty(); */
					 if(data.success){
						 $("#exampleTableToolbar").show();
						 $("#exampleTableToolbar").bootstrapTable("destroy").bootstrapTable({
							columns : [{
								field : "no",
								title : "编号"
							},{
								field : "password",
								title : "密码"
							},{
								field : "prompt",
								title : "提示"
							}],
							data : data.data,
							iconSize : "outline",
							icons : {
								columns : "glyphicon-list"
							},
							height:"250"
						 });
						/*  $("#exampleTableToolbar").bootstrapTable({data: data.data,height:"250"});
						 
						 var thead = $("<thead></thead>").appendTo(exampleTableToolbar);
						 var tr = $("<tr>").appendTo(thead);
						 var th1 = $("<th>").html("编号").appendTo(tr);
						 var th2 = $("<th>").html("密码").appendTo(tr);
						 var th3 = $("<th>").html("提示").appendTo(tr);
						 var row;
						 for(var i = 0; i < data.data.length; i++){
							 var visitor = data.data[i];
							 row = $("<tr>").appendTo(exampleTableToolbar);
							 $("<th>").html(visitor.no).appendTo(row);
							 $("<th>").html(visitor.password).appendTo(row);
							 $("<th>").html(visitor.prompt).appendTo(row);
						 } */
					 }
				 }, "json");
			 });
			 
			 jQuery.validator.addMethod("compareMaxValue", function(value, element, param) {
	           	  var minValue = $("#" + param).val();
	           	  return this.optional(element) || (parseInt(value) > parseInt(minValue) );   
	           	});
			 
			 jQuery.validator.addMethod("validNo", function(value, element) {   
			     var vNo = /^[0-9A-Za-z_]{0,16}\*$/;
			     return this.optional(element) || (vNo.test(value));
			 });
			 
			 $("#inputForm").validate({
					errorElement: 'div',
			   		errorClass: 'help-block',
			   		focusInvalid: false,
			   		rules: {
			   			orgInput: {
				    		required: true
				    	},
				    	visitorNo: {
				    		required: true,
				    		validNo: true
				    	},
				    	startNo: {
				    		required: true,
				    		maxlength: 6
				    	},
				    	endNo: {
				    		required: true,
				    		maxlength: 6,
		    				compareMaxValue: 'startNo'
				    	},
				    	starLen: {
				    		required: true,
				    		maxlength: 5
				    	}
			   		},
			   		messages: {
			   			orgInput: {
			    			required:'<font style="color:red">组织必须选择！</font>'
			    		},
			    		visitorNo: {
			    			required:'<font style="color:red">来访者编号必填！</font>',
			    			validNo: '<font style="color:red">编号必须为数字字母下划线的组合,*必须放入最后一位！</font>'
			    		},
			    		startNo: {
			    			required:'<font style="color:red">开始范围必填！</font>',
			    			maxlength: '<font style="color:red">开始范围最多6位！</font>'
			    		},
			    		endNo: {
			    			required:'<font style="color:red">结束范围必填！</font>',
			    			maxlength: '<font style="color:red">结束范围最多6位！</font>',
			    			compareMaxValue: '<font style="color:red">结束范围必须要大于开始范围！</font>'
			    		},
			    		starLen: {
			    			required:'<font style="color:red">通配符长必填！</font>',
			    			maxlength: '<font style="color:red">通配符长1-5位！</font>'
			    		}
			   		},
					submitHandler: function(form){
						$("#orgId").val(dropdownTree.getValue());
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
			 
			 $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
		});
	</script> 
</body>
</html>
