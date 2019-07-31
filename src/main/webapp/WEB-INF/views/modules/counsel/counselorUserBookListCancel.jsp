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
                        <h4 class="example-title">撤销预约咨询列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                                </button>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
                                </button>
                            </div>
		<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="visitorInfo.user.name">咨询者</th>
                                        <th data-field="counselor.user.name">咨询师</th>
                                        <th data-field="consultation">咨询方式</th>
                                        <th data-field="questionType">咨询类型</th>
										<th data-field="reserveDate">咨询时间</th>
                                        <th></th>
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
										<a class="text-navy" href="javascript:canDetails(${counselorUserBook.id}, '')">
										详情
										</a>
										</td>
										<td>
										<a class="text-navy" href="javascript:cancelHandler(${counselorUserBook.id})">
										撤消
										</a>
										</td>
									</tr>
								</c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
<script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var canDetails = function(id, showTitle){
		location.href = "${ctx}/counsel/counselorUserBook/detailView?counselorUserBookId=" + id + "&showTitle=" + showTitle;
	};
	var cancelHandler = function(id){
		var html='<div class="ibox-content">'+
					'<div class="form-group">'+
						'<label class="col-sm-3 control-label">撤销原因：</label>'+
		                '<div class="col-sm-8">'+
		                    '<textarea id="undoReason" name="undoReason" rows="10" cols="128" class="form-control"></textarea>'+
		                '</div>'+
		            '</div>'+
	            '</div>';
		layer.open({
		    type: 1,
		    title: '原因',
		    shadeClose:true,
		    shade: 0.3,
		    area: ['340px', '215px'],
		    offset: 'c',
		    shift: 2,
		    content: html,
		    btn:['撤销','取消'],
		    yes: function(index, layero){
		    	var undoReason = $(layero.context).find("#undoReason").val();
		    	var hrefLocation = "${ctx}/counsel/counselorUserBook/cancelView?undoReason="+undoReason;
		    	if(id != null & id != ''){
		    		hrefLocation += "&counselorUserBookId="+id;
		    	}
		    	window.location.href = hrefLocation;
		    },btn2: function(index, layero){
		       
		    }
		});
	};
	$(function() {
		
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
       	return false;
    }
</script>
</html>