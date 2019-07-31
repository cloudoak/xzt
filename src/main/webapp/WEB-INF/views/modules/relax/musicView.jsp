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
	<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>
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
         <div class="form-horizontal m-t">
			    <input type="hidden" name="id" value="${music.id }">
				<div class="form-group">
			      <label class="col-sm-3 control-label">音乐名称：</label>
			      <div class="col-sm-8">								
						${music.name }
			      </div>
			    </div>
				<div class="form-group">
					<label class="col-sm-3 control-label">音乐分类:</label>
					<div class="col-sm-4">
						${music.musicCatalogName}
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label">音乐类型:</label>
					<div class="col-sm-4">
					audio/${music.audioType }
					</div>
				</div>		
				<div class="form-group">
                     <label class="col-sm-3 control-label">演唱者：</label>
                     <div class="col-sm-8">								
						${music.singer }
                     </div>
                </div>
				 <div class="form-group">
						<label class="col-sm-3 control-label">音乐文件：</label>
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
							${music.intro }
						</div>
					</div>               	
					<div class="form-group">
                         <div class="col-sm-8 col-sm-offset-3">
							<input id="btnCancel" class="btn" type="button" value="返 回" onclick="back();"/>
                         </div>
                     </div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	$(function() {
		$("#btnCancel").on("click", function(){
			location.href = "${ctx}/relax/music/list";
		});
	});
</script>
</html>