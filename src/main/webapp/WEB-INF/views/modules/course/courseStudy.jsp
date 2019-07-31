<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 心理课堂--课堂学习</title>
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
                        <h4 class="example-title">课堂学习</h4>
                        <div class="example">
                        	<form id="searchForm" action="${ctx }/course/course/myCourse" class="form-inline" method="POST">
		                        <div class="form-group">
			                        <label class="control-label">分类</label>
			                        <select id="courseCatalogId" name="courseCatalogId" class="form-control">
									<c:forEach items="${courseCatelogList}" var="tpl">
										<option value="${tpl.id}">${tpl.name}</option>	
			        				</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<button id="btnOk" type="button" class="btn btn-outline btn-default">确定</button>
								</div>
							</form>
						<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true"  data-pagination="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="name">课件名称</th>
                                        <th data-field="catalog">课件分类</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="course">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/course/course/view?id=${course.id}">${course.name}</a></td>
										<td>${course.courseCatalogName}</td>
									</tr>
								</c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
</body>
<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script type="text/javascript">
		$(function() {
			var courseCatalogId = "${course.courseCatalogId}";
			$("#courseCatalogId").val(courseCatalogId);
			$("#btnOk").on("click", function(){
				$("#searchForm").submit();
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