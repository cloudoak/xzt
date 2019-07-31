<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>${sysConfig.schoolName } 查看预约</title>
   <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
   <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
   <%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
   <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
   <style type="text/css">
   	input {
   		border: 0px;
   	}
   </style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="ibox-tools"></div>
                    </div>
					<div class="ibox-content">
					<form class="form-horizontal m-t" id="inputForm" action="${ctx}/counsel/counselorUserBook/dealWithAfterList" method="post">
						<input type="hidden" name="counselorUserBookId" value="${counselorUserBook.id }">
						<input type="hidden" id="reserveDate" name="reserveDate" value='<fmt:formatDate value="${counselorUserBook.reserveDate}" pattern="yyyy-MM-dd"/>'>
						<input type="hidden" id="beginTime" name="beginTime" value="<fmt:formatDate value="${counselorUserBook.beginTime}" pattern="HH:mm:ss"/>">
						<input type="hidden" id="endTime" name="endTime" value="<fmt:formatDate value="${counselorUserBook.endTime}" pattern="HH:mm:ss"/>">
						<input type="hidden" id="counselorId" name="counselorId" value="${counselorUserBook.counselorId }" />
                       <div class="form-group">
                          <label class="col-sm-2 control-label" for="visitorInfoName">姓名</label>
                          <div class="col-sm-4">
                          	<input id="visitorInfoName" type="text" readonly="readonly" value="${ counselorUserBook.visitorInfo.user.name}" />
                          </div>
                          <label class="col-sm-2 control-label" for="counselorName">咨询师</label>
                          <div class="col-sm-4">
                          	<input id="counselorName" type="text" readonly="readonly" value="${ counselorUserBook.counselor.user.name}" />
                          </div>
                       </div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label">咨询时间</label>
                          <div class="col-sm-4">
                             <fmt:formatDate value="${counselorUserBook.reserveDate}" pattern="yyyy-MM-dd"/> 
                             <fmt:formatDate value="${counselorUserBook.beginTime}" pattern="HH:mm"/> 至  
							 <fmt:formatDate value="${counselorUserBook.endTime}" pattern="HH:mm"/>
                          </div>
                          <label class="col-sm-2 control-label">咨询方式</label>
                          <div class="col-sm-4">
                          <c:choose>
                          <c:when test="${counselorUserBook.consultation==0}">面询</c:when>
                          <c:when test="${counselorUserBook.consultation==1}">网上咨询</c:when>
                          <c:otherwise>
                          	电话咨询
                          </c:otherwise>
                          </c:choose>
                          </div>
                       </div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label">预约方式</label>
                          <div class="col-sm-4">
                          <c:choose>
                          <c:when test="${counselorUserBook.reservation==0}">当面</c:when>
                          <c:when test="${counselorUserBook.reservation==1}">电话</c:when>
                          <c:otherwise>
                          	网上
                          </c:otherwise>
                          </c:choose>
                          </div>
                          <label class="col-sm-2 control-label">问题类型</label>
                          <div class="col-sm-4">
                             ${fns:getDictLabel(counselorUserBook.questionType,'question_type', '抑郁 ') }
                          </div>
                       </div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label">自我分析</label>
                          <div class="col-sm-4">
                         	${counselorUserBook.selfAnalysis}
                          </div>
                       </div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label">处理状态</label>
                          <div class="col-sm-4">
                          <select name="dealStatus" class="form-control">
	                          <option value="0">未处理</option>
	                          <option value="1">接受预约</option>
	                          <option value="2">取消预约</option>
	                          <option value="3">咨询已结束</option>
                          </select>
                          </div>
                       </div>
                       <div class="form-group">
							<label class="col-sm-3 control-label">是否重点个案：</label>
							<div class="col-sm-4">
								<div class="i-checks">
								 <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="isCase1" value="1" name="isCase" ${counselorUserBook.isCase==1?"checked":"" } >
                                        <label for="isCase1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="isCase2" value="0" name="isCase" ${counselorUserBook.isCase==0?"checked":"" } >
                                        <label for="isCase2">否</label>
                                    </div>
                                </fieldset>
                                </div>
							</div>
						</div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label">处理意见</label>
                          <div class="col-sm-4">
                          		<textarea name="opinion" rows="10" cols="128" class="form-control">${counselorUserBook.opinion}</textarea>
                          </div>
                       </div>
                       <div class="form-group">
                       		<div class="col-sm-8 col-sm-offset-3">
                       		<button class="btn btn-primary" type="submit" id="btnDealWith">处理</button>
                      		<button class="btn btn-primary" type="button" id="btnCancel">取消</button>
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
<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		var showTitle = '${showTitle}';
		
		$("#btnCancel").on("click", function(){
			if(showTitle === 'true'){
				location.href = "${ctx}/counsel/counselorSchedule/list";
			}else{
				location.href = "${ctx}/counsel/counselorUserBook/list";
			}
		});
		
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	})
</script>
</body>
</html>