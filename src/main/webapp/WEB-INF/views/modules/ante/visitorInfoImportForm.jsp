<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--来访者资料批量导入</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/dropdownTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
       <div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
            	<div class="col-sm-12">
            	<h4 class="example-title">来访者资料批量导入</h4>
            	<div class="example">
            	<form id="visitorForm" action="${ctx}/ante/visitorInfo/improt" method="post" enctype="multipart/form-data" class="form-horizontal">
                 <div class="form-group">
                 <label class="col-sm-4 control-label">来访者信息模板</label>
                 <div class="col-sm-6">
                 	<a class="btn btn-default" href="${ctx}/ante/visitorInfo/download?fileName=visitorInfoTemp.xls">下载模板</a>
                 </div>
                 </div>
                 <div class="form-group">
                 	 <label class="col-sm-4 control-label">组织机构</label>
                    <div class="col-sm-6">
                    	<input type="hidden" id="orgId" name="orgId" />
                     	<input id="orgInput" name="orgInput" type="text" class="form-control" readonly />
                    </div>
                 </div>
                  <div class="form-group">
                 	  <label class="col-sm-4 control-label">上传来访者信息文件</label>
                    <div class="col-sm-6">
                    <div class="input-append">
                    <input id="filePath" name="filePath" type="text" class="span2" style="border: 1px solid #ccc;padding: 6px 0px;" readonly="readonly" placeholder="请选择文件...">
                    <input id="wirteExcel" name="wirteExcel" type="file" style="display:none;"/>
                    <button name="btnFileDialog" class="btn btn-default" type="button">选择</button>
                    </div>
                    </div>
                 </div>
                    <div class="form-group">
                    		<div class="col-sm-8 col-sm-offset-3">
                    		<button class="btn btn-primary" type="submit" id="btnSubmit">提交</button>
                    		<button id="btnCancel" class="btn" type="button">返 回</button>
                    		</div>
                    </div>
                </form>
             </div>
           </div>
        </div>
        </div>
    </div>
</div>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
<script type="text/javascript">
		var orgId = '${orgId}', msg = "${message}", success = $("${success}");
		
		$(function(){
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
			 $("button[type=button][name=btnFileDialog]").on("click", function(){
		    		$("input[type=file][name=wirteExcel]").click();
		      });
			 $("input[type=file][name=wirteExcel]").on("change", function(){
				var f = $(this)[0].files[0];
				if(f.type !== 'application/vnd.ms-excel'){
					layer.alert('文件类型不匹配请上传application/vnd.ms-excel格式文件', {icon: 1});
					return;
				}else if(f.size > (50*1024*1024)){
					layer.alert('文件大小超出' + (50*1024*1024) + 'KB范围', {icon: 1});
					return;
				}
				$("input[type=text][name=filePath]").val($(this).val());
			});
			 
		 	var dropdownTree = $("#orgInput").dropdownTree({
		    	root: "${rootParentId}",
	            async: {
	            	url: "${ctx}/sys/org/asynTree",
	            	type: "json",
	            	params: ["id"]
	            },
	            selectValue: orgId
		    });
		 	
			$("#visitorForm").validate({
				focusInvalid: false,
				rules: {
					orgInput: {
	    				required: true
	    			},
	    			filePath: {
	    				required: true
	    			}
				},messages: {
					orgInput: {
						required: '机构必须选择其中一项！',
	    			},
	    			filePath: {
						required: '文件必须选择！',
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
		});
</script>
</body>
</html>
