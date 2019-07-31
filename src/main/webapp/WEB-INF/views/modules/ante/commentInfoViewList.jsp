<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 评价管理--机构设置</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">评语记录</h4>
						<div class="example">
							<table id="contentTable" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
								<thead>
									<tr>
										<th data-field="title">评论标题</th>
										<th data-field="userName1">评价人姓名</th>
										<th data-field="userName2">被评价人姓名</th>
										<th data-field="createDate">评价时间</th>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="commentInfo">
									<tr>
										<td><a class="text-navy" href="${ctx}/ante/commentInfo/view?id=${commentInfo.id}">
											${commentInfo.title}
										</a></td>
										<td>${commentInfo.userName1}</td>
										<td>${commentInfo.userName2}</td>
										<td>
											<fmt:formatDate value="${commentInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
									</tr>
								</c:forEach>
							</table>
                             	<div class="col-sm-8 col-sm-offset-3">
                                 	<input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回"/>
                             	</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>

</body>
</html>