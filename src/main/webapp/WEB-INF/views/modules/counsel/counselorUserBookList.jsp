<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--咨询预约管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>	
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                    	<c:if test="${not empty showTitle }"><h4 class="example-title">有下列咨询者预约此时间段：</h4></c:if>
                        <c:if test="${empty showTitle }"><h4 class="example-title">预约咨询列表</h4></c:if>
                        <div class="example">
		                    <c:choose>
                            <c:when test="${not empty showTitle }">
                            	 <form id="searchForm" action="${ctx}/counsel/counselorUserBook/list" method="post" class="form-inline" role="form">
		                           <div class="form-group">
		                            	<div class="i-checks">
		                                  <label><input type="checkbox" name="setQuestionType" value="0"> <i></i> 问题类型</label>
		                              	</div>
		                           </div>
		                           <div class="form-group">
		                           		<select id="questionType" name="questionType" class="form-control">
											<c:forEach items="${fns:getDictList('question_type')}" var="item">
												<option value="${item.value }">${item.label }</option>
											</c:forEach>
										</select>
		                           </div>
		                           <div class="form-group">
		                           		<button class="btn btn-primary" type="submit" id="btnLookOver">查看</button>
		                           </div>
		                           </form>
                            </c:when>
                            <c:otherwise>
                            <form id="searchForm" action="${ctx}/counsel/counselorUserBook/list" method="post" class="form-horizontal">
		                       <div class="form-group">
		                          <label class="col-sm-2 control-label">
		                          <div class="i-checks">
		                              <label><input type="checkbox" name="setDealStatus" value="0"> <i></i> 状态</label>
		                          </div>
		                          </label>
		                          <div class="col-sm-4">
		                             <select id="dealStatus" name="dealStatus" class="form-control">
										<c:forEach items="${fns:getDictList('deal_status')}" var="item">
											<option value="${item.value }">${item.label }</option>
										</c:forEach>
									</select>
		                          </div>
		                       </div>
		                       <div class="form-group">
		                       	<label class="col-sm-2 control-label">
								  <div class="i-checks">
		                              <label><input type="checkbox" name="setQuestionType" value="0"> <i></i> 问题类型</label>
		                          </div>
								  </label>
		                          <div class="col-sm-4">
		                            <select id="questionType" name="questionType" class="form-control">
										<c:forEach items="${fns:getDictList('question_type')}" var="item">
											<option value="${item.value }">${item.label }</option>
										</c:forEach>
									</select>
		                          </div>
		                       </div>
	                           <div class="form-group">
	                           		<div class="col-sm-8 col-sm-offset-3">
	                           		<button class="btn btn-primary" type="submit" id="btnLookOver">查看</button>
	                           		</div>
	                           </div>
	                       </form>
                            </c:otherwise>
                            </c:choose>
		                        
					<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                               <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="visitorInfo.user.name">咨询者</th>
                                        <th data-field="counselor.user.name">咨询师</th>
                                        <th data-field="consultation">咨询方式</th>
                                        <th data-field="questionType">咨询类型</th>
										<th data-field="reserveDate">咨询时间</th>
										<th data-field="dealStatus">状态</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="counselorUserBook">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" >${counselorUserBook.visitorInfo.user.name}</a></td>
										<td>${counselorUserBook.counselor.user.name}</td>
										<td>${fns:getDictLabel(counselorUserBook.consultation, 'counsel_mode', '')}</td>
										<td>${fns:getDictLabel(counselorUserBook.questionType, 'question_type', '')}</td>
										<td>
										<fmt:formatDate value="${counselorUserBook.reserveDate}" pattern="yyyy-MM-dd"/>&nbsp;
										<fmt:formatDate value="${counselorUserBook.beginTime}" pattern="HH:mm"/>
										<fmt:formatDate value="${counselorUserBook.endTime}" pattern="HH:mm"/>
										</td>
										<td>
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
										</td>
										<td>
										<c:choose>
			                       			<c:when test="${ counselorUserBook.dealStatus == 0 }">
												<a class="text-navy" href="javascript:dealWith(${counselorUserBook.id}, '${not empty showTitle}');">
													查看及处理
												</a>
			                       			</c:when>
			                       			<c:otherwise>
			                       				<a class="text-navy" href="javascript:dealView(${counselorUserBook.id}, '${not empty showTitle}');">
													查看及处理
												</a>
			                       			</c:otherwise>
			                         	</c:choose>
										</td>
									</tr>
								</c:forEach>
                            </table>
                            <c:choose>
                            <c:when test="${not empty showTitle }">
                            	 <div class="form-group">
		                           		<button class="btn btn-primary" type="button" id="btnManual">手工添加 </button>
		                           		<button class="btn btn-primary" type="button" id="btnWorkArrangement">返回"工作安排"</button>
		                          </div>
                            </c:when>
                            <c:otherwise>
                            <div>
			 					<font color="green">■</font>空闲
								<font color="#ff0000">■</font>预约取消
								<font color="#0000ff">■</font>有效预约
								<font color="#ffff00">■</font>未处理的预约
			 				</div>
                            </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
<script type="text/javascript">
	var dealWith = function(counselorUserBookId, showTitle){
		location.href = "${ctx}/counsel/counselorUserBook/dealWith?counselorUserBookId=" + counselorUserBookId + "&showTitle=" + showTitle;
	};
	var dealView = function(counselorUserBookId, showTitle){
		location.href = "${ctx}/counsel/counselorUserBook/dealView?counselorUserBookId=" + counselorUserBookId + "&showTitle=" + showTitle;
	};
	var dealStatus = '${counselorUserBook.dealStatus }', 
		counselorId = '${counselorUserBook.counselorId}',
		reserveDate = '<fmt:formatDate value="${counselorUserBook.reserveDate}" pattern="yyyy-MM-dd"/>', 
		beginTime = '<fmt:formatDate value="${counselorUserBook.beginTime}" pattern="HH:mm:ss"/>', 
		endTime = '<fmt:formatDate value="${counselorUserBook.endTime}" pattern="HH:mm:ss"/>'
	$(function() {
		
		$("#btnManual").on("click", function(){
			location.href = '${ctx}/counsel/counselorSchedule/manual?dealStatus=' + dealStatus + "&counselorId=" + counselorId 
			+ "&reserveDate=" + reserveDate + "&beginTime=" + beginTime + "&endTime=" + endTime;
		});
		$("#btnWorkArrangement").on("click", function(){
			location.href = "${ctx}/counsel/counselorSchedule/list";
		});
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
       	return false;
    }
</script>
</html>