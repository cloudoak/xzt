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
					<div class="form-horizontal">
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
                         	<c:choose>
                       			<c:when test="${ counselorUserBook.dealStatus == 4 }">
									<font color="green">■</font>空闲
                       			</c:when>
                       			<c:when test="${ counselorUserBook.dealStatus == 2 }">
                       				<font color="#ff0000">■</font>已取消
                       			</c:when>
                       			<c:when test="${ counselorUserBook.dealStatus == 1 }">
                       				<font color="#0000ff">■</font>有效
                       			</c:when>
                       			<c:otherwise>
                       				<font color="#ffff00">■</font>未处理
                       			</c:otherwise>
                         	</c:choose>
                          </div>
                       </div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label">处理意见</label>
                          <div class="col-sm-4">
                         	${counselorUserBook.opinion}
                          </div>
                       </div>
                       <div class="form-group">
                       		<div class="col-sm-8 col-sm-offset-3">
                      		<button class="btn btn-primary" type="button" id="btnBack">返回</button>
                      		</div>
                       </div>
					</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
<script type="text/javascript">
	$(function(){
		var showTitle = '${showTitle}';
		$("#btnBack").on("click", function(){
			if(showTitle === 'true'){
				location.href = "${ctx}/counsel/counselorSchedule/list";
			}else{
				location.href = "${ctx}/counsel/counselorUserBook/listCancel";
			}
		});
	})
</script>
</body>
</html>