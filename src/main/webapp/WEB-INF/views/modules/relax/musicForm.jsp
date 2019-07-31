<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--音乐管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
          <div class="ibox-title">
              <h5>音乐信息</h5>
              <div class="ibox-tools"></div>
          </div>
        </div>
    </div>
		<div class="ibox-content">
         <form class="form-horizontal m-t" id="inputForm" action="${ctx}/relax/music/save" method="post">
			    <input type="hidden" name="id" value="${music.id }">
				<div class="form-group">
			      <label class="col-sm-3 control-label">音乐名称：</label>
			      <div class="col-sm-8">								
						<input name="name" maxlength="50" value="${music.name }" class="form-control" type="text" />
			      </div>
			    </div>
				<div class="form-group">
					<label class="col-sm-3 control-label">音乐分类:</label>
					<div class="col-sm-4">
						<sys:treeselect id="musicCatalog" name="musicCatalogId" value="${music.musicCatalogId}" labelName="musicCatalogName" 
						labelValue="${music.musicCatalogName}" title="栏目" url="/relax/musicCatalog/treeData"  
						selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" cssClass="required" />
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label">音乐类型:</label>
					<div class="col-sm-4">
					<select id="audioType" name="audioType" class="form_control">
					<option value="mpeg">audio/mpeg</option>
					<option value="ogg">audio/ogg</option>
					<option value="wav">audio/wav</option>
					<option value="mp3">audio/mp3</option>
					</select>
					</div>
				</div>		
				<div class="form-group">
                     <label class="col-sm-3 control-label">演唱者：</label>
                     <div class="col-sm-8">								
						<input name="singer" maxlength="16" value="${music.singer }" class="form-control" type="text" />
                     </div>
                </div>			
				<div class="form-group">
					<label class="col-sm-3 control-label">是否公开：</label>
					<div class="col-sm-8">
						<div class="i-checks">
						<fieldset>
						<c:forEach items="${fns:getDictList('yes_no')}" var="item" varStatus="status">
							<div class="radio radio-info radio-inline">
                                    <input type="radio" id="isPublic${status.index}" value="${item.value }" name="isPublic" ${empty music.isPublic ? "" : (music.isPublic==item.value?"checked":"") } >
                                    <label for="isPublic${status.index}">${item.label }</label>
                                </div>
						</c:forEach>
						</fieldset>
                        </div>
					</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">音乐文件：</label>
						<div class="col-sm-8">
							<input type="text" name="path" id="path" value="${music.path}"  readonly="readonly" class="input-mini" />
							<input id="btnShowDialog" class="btn btn-primary" type="button" value="上传"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-8">
						<audio id="musicDetails" controls="controls">
						  	<source src="${fileAbsolutePath }" type="audio/${music.audioType}">
							Your browser does not support the audio tag.
						</audio>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">描述：</label>
						<div class="col-sm-8">
							<textarea name="intro" rows="4" cols="128" class="form-control">${music.intro }</textarea>
						</div>
					</div>               	
					<div class="form-group">
                       <div class="col-sm-8 col-sm-offset-3">
						<shiro:hasPermission name="course:course:edit">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="返 回" />
	                   </div>
	                </div>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript">
	var audioType = "${music.audioType}";
	$(function() {
		$("#btnCancel").on("click", function(){
			location.href = "${ctx}/relax/music/list";
		});
		if(audioType){
			$("#audioType").val(audioType);
		}
		var ajaxFileUpload = function(){
			layui.use(['layer', 'form'], function(){
				  var layer = layui.layer ,form = layui.form;
					var ctx = '${ctx}', 
						html = '<form  class="layui-form" action="'+ctx+'/relax/music/upload" method="post">' +
						'<div class="layui-form-item">'+
							'<label class="layui-form-label">音乐文件附件：</label>'+
				            '<div class="layui-input-block">'+
				            	'<input type="hidden" name="fileType" />' +
				            	'<input type="text" class="layui-input file" readonly="readonly" name="musicPath" value="" />'+
				                '<input type="file" name="file" style="display:none;" />'+
				                '<input name="btnImageDialog" class="btn btn-primary" type="button" value="选择音乐文件..."/>'+
				            '</div>'+
				        '</div>'+
				        '</form>';
				    layer.open({
					    type: 1,
					    title: "音乐文件附件上传",
					    shadeClose:true,
					    shade: 0.3,
					    area: ['540px', '175px'],
					    offset: 'c',
					    shift: 2,
					    scrollbar : false,
					    content: html,
					    btn:['保存','关闭'],
					    success: function(layero, index){
					    	layero.find("input[type=button][name=btnImageDialog]").on("click", function(){
					    		layero.find("input[type=file][name=file]").click();
					      	});
					    	layero.find("input[type=file][name=file]").change(function(){
								var f = $(this)[0].files[0];
								if(f.type !== 'audio/mpeg' && f.type !== 'audio/ogg' && f.type !== 'audio/wav' && f.type !== 'audio/mp3'){
									layer.alert('文件类型不匹配请上传audio/mpeg、audio/ogg、audio/wav、audio/mp3格式文件', {icon: 1});
									return;
								}else if(f.size > (50*1024*1024)){
									layer.alert('文件大小超出' + (50*1024*1024) + 'KB范围', {icon: 1});
									return;
								}
								layero.find("input[type=hidden][name=fileType]").val(f.type);
								layero.find("input[type=text][name=musicPath]").val($(this).val());
							});
					    },
					    yes: function(index, layero){
					    	var fileType = layero.find("input[type=hidden][name=fileType]").val();
					    	var formData = new FormData();
					    	formData.append('file', layero.find("input[type=file][name=file]")[0].files[0]);
					    	$.ajax({
								url : '${ctx}/relax/music/upload',
								type : 'POST',
								cache: false,
								data: formData,
								processData: false,
								contentType: false
							}).done(function(res) {
								//res = JSON.stringify(res);
								res = res.replace("\\", "\\\\");
								var data = JSON.parse(res); //$.parseJSON(res);
								if(data.success){
									$("#path").val(data.message);
									$("#musicDetails").empty();
									$("#musicDetails").append('<source src="'+'${fileAbsPath}' + data.message+'" type="'+fileType+'" />');
								}
								layer.close(index);
							}).fail(function(res) {
								
							});
					    }
				    });
			});
		};
		
		$("#btnShowDialog").on("click", ajaxFileUpload);
		
		$("#inputForm").validate({
			errorElement: 'div',
    		errorClass: 'help-block',
    		focusInvalid: false,
    		rules: {
	    		name: {
	    			required: true
	    		},
	    		path: {
	    			required: true
	    		}
	    	},messages: {
	    		name: {
	    			required: '音乐名称必填！'
	    		},
	    		path: {
	    			required: '音乐文件必填！'
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
		
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
	});
</script>
</html>