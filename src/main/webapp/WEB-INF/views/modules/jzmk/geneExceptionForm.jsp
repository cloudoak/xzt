<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>异常条件规则添加</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
    
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/jzmk/geneException/form?tid=${tid}&gid=${gid}">异常条件规则添加</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                 <form class="form-horizontal m-t" id="geneException" action="${ctx}/jzmk/geneException/save?tid=${tid}&gid=${gid}" method="post">
                     <input type="hidden" name="id" value="${geneException.id }">
                     <input type="hidden" name="gid" value="${geneException.gid }">
			      <div class="form-group">
			          <label class="col-sm-3 control-label"></label>
			          <div class="col-sm-12">
			         <div class="liangbao">
			           <a href="#" class="hover"><em class="em1 hover"></em>01基本信息</a>
			           <a href="#" class="hover"><em class="em2 hover"></em>02条目设置</a>
			           <a href="#" class="hover"><em class="em3 hover"></em>03因子设置</a>
			           <a href="#"><em class="em4"></em>04因子解释</a>
			           <a href="#"><em class="em5"></em>05总解释</a>
			         </div>
			       </div>
			      </div>
					<div class="form-group">
			            <label class="col-sm-3 control-label"><input type="checkbox" id="chkExAge" name="chkExAge" value="0"> &nbsp;<i></i>年龄异常条件：</label>
			            <div class="col-sm-8">
			            <div class="col-sm-4">
			             <select id="ageCondition" name="ageCondition" class="form-control m-b" style="width:100px">
			                 <option value="0" hassubinfo="true">></option>
			                 <option value="1" hassubinfo="true">>=</option>
			                 <option value="2" hassubinfo="true"><</option>
			                 <option value="3" hassubinfo="true"><=</option>
			                 <option value="4" hassubinfo="true">=</option>
			             </select>
			             </div>
			             <div class="col-sm-4">
			                <input id="ageConditionValue" name="ageConditionValue" value="${geneException.ageConditionValue }" class="form-control" style="width:150px" type="number"> 
			             </div>
			            </div>
			        </div>
			        <div class="form-group">
			            <label class="col-sm-3 control-label"><input type="checkbox" id="chkExGender" name="chkExGender" value="1"> &nbsp;&nbsp;<i></i>性别异常值：</label>
			            <div class="col-sm-8">
			            <fieldset>
			             <div class="radio radio-inline">${geneException.sexConditionValue}
			             <input type="radio" value="1" name="sexConditionValue" ${geneException.sexConditionValue?"checked":"" } >
			             <label for="sexConditionValue">男</label>
			             </div>
			             <div class="radio radio-info radio-inline">
			             <input type="radio" value="0" name="sexConditionValue" ${!geneException.sexConditionValue?"checked":""} >
			             <label for="sexConditionValue">女</label>
			             </div>
			             </fieldset>            
			            </div>
			        </div>
			        <div class="form-group">
			            <label class="col-sm-3 control-label"><input type="checkbox" id="chkExValue" name="chkExValue" value="2"> &nbsp;&nbsp;<i></i>异常得分值：</label>
			            <div class="col-sm-8">
			            <div class="col-sm-4">
			            <select id="term" name="term" class="form-control m-b" style="width:100px">
			                 <option value="0" hassubinfo="true">></option>
			                 <option value="1" hassubinfo="true">>=</option>
			                 <option value="2" hassubinfo="true"><</option>
			                 <option value="3" hassubinfo="true"><=</option>
			                 <option value="4" hassubinfo="true">=</option>
			             </select>
			             </div>
			             <div class="col-sm-4">
			                <input id="conditionValue" name="conditionValue" value="${geneException.conditionValue}" class="form-control" style="width:150px" type="number"> 
			             </div>
			            </div>
			        </div>	
	   				<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">添加</button>&nbsp;
                                    <button class="btn btn-primary" type="button" onclick="gotoGeneExceptionPage('${tid}','${gid}')">返回</button>
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
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script>
    function windowOpen(url, name, width, height){
    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
    	window.open(url ,name , options);
    }
    function gotoGeneExceptionPage(tid, gid){
    	document.location.href = "${ctx}/jzmk/geneException/list?tid=" + tid + "&gid="+gid;
    }
    $(function(){
    	
    	var ageCondition = '${geneException.ageCondition}', 
    		term = '${geneException.term}';
    	
   		if(ageCondition != ''){
   			$("ageCondition").val(ageCondition);
   		}
   		
   		if(term != ''){
   			$("term").val(term);
   		}
    	
       	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
    });
</script>
</body>
</html>