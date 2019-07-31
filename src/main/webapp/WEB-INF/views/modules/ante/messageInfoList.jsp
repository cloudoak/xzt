<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 即时信息管理--收件箱</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">收件箱</h4>
						<div class="example">
					<form id="searchForm" modelAttribute="messageInfo" action="${ctx}/ante/messageInfo/list" method="post" class="form-inline">
						<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
						<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
						<div class="form-group">
							<label>收件人：</label>
							<input id="toUser" name="toUser" value="${messageInfo.toUser}" htmlEscape="false" maxlength="30" class="form-control"/>
						</div>
						<div class="form-group">
							<label>发件人：</label>
							<input id="fromUser" name="fromUser" value="${messageInfo.fromUser}" htmlEscape="false" maxlength="30" class="form-control"/>
						</div>
						<div class="form-group">
							<label>主题：</label>
							<input id="title" name="title" value="${messageInfo.title}" htmlEscape="false" maxlength="50" class="form-control"/>
						</div>
						<div class="form-group">
							<label>状态：</label>
							<select id="status" name="status" class="form-control" >
								<option value="">--请选择--</option>
								<option value="READ">已读</option>
								<option value="UNREAD">未读</option>
							</select>
						</div>
						<div class="form-group">
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
						</div>
					</form>
					<sys:message content="${message}"/>
					<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="false">
						<thead>
							<tr>
								<th>收件人</th>
								<th>发件人</th>
								<th>主题</th>
								<th>状态</th>
								<th>创建时间</th>
								<shiro:hasPermission name="ante:messageInfo:edit"><th>操作</th></shiro:hasPermission>
							</tr>
						</thead>
						<c:forEach items="${page.list}" var="messageInfo">
							<tr>
								<td><a class="text-navy" href="${ctx}/ante/messageInfo/read?id=${messageInfo.id}">
									${messageInfo.toUser}
								</a></td>
								<td>${messageInfo.fromUser}</td>
								<td>${messageInfo.title}</td>
								<td>
									<c:if test="${messageInfo.status=='READ'}">已读</c:if>
									<c:if test="${messageInfo.status=='UNREAD'}">未读</c:if>
								</td>
								<td>
									<fmt:formatDate value="${messageInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<shiro:hasPermission name="ante:messageInfo:edit"><td>
									<a class="text-navy" href="${ctx}/ante/messageInfo/read?id=${messageInfo.id}" title="查看">
										<i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
									</a>&nbsp;&nbsp;
									<a class="text-danger" href="${ctx}/ante/messageInfo/delete?id=${messageInfo.id}" onclick="return confirmx('确认要删除该消息管理吗？', this.href)" title="删除">
										<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
									</a>
								</td></shiro:hasPermission>
							</tr>
						</c:forEach>
					</table>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
</body>
</html>