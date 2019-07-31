<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-gene=1.0">
    <title>教师档案</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<meta name="decorator" content="default"/>
	<style media="print">
         .Noprint { display: none }
         .PageNext{ page-break-after: always }
    </style>
	<style type="text/css">
	    .style1
	    {
	        text-align: center;
	    }
	</style>
</head>
<body>
<ul class="nav nav-tabs Noprint">
<li><a href="${ctx}/archives/archive/list">档案查询</a></li>
<li class="active"><a href="${ctx}/archives/archive/form?userId=${archives.userId}">教师档案</a></li>
</ul><br/>
<form name="aspnetForm" method="post" action="" id="aspnetForm" enctype="multipart/form-data">
<div>
<div id="test_result_head"><div id="test_result_title">教师档案</div>
<div id="div_print" class="Noprint">
<a href="#" onclick="javascript:window.print()"><img src="${ctxStatic}/images/print.png" onmouseover="this.src='${ctxStatic}/images/print_f2.png'" onmouseout="this.src='${ctxStatic}/images/print.png'" title="打印"></a></div>
</div>
</div>
<table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000" border="0">
<tbody><tr bgcolor="#ffffff">
<td class="style1" colspan="6">基本信息</td>
</tr>
<tr bgcolor="#ffffff">
<td><b>姓名</b></td>
<td>${archives.name }</td>
<td><b>性别</b></td>
<td>${fns:getDictLabel(archives.sex, 'sex', '')}</td>
<td><b>年龄</b></td>
<td>${archives.age}</td>
</tr>
<tr bgcolor="#ffffff">
<td><b>类型</b></td>
<td>
<c:forEach items="${counselorTypes}" var="counselorType">
${counselor.counselorType==counselorType.id?counselorType.typeName:''}
</c:forEach>
</td>
<td><b>部门</b></td>
<td>
${archives.org.name}
</td>
<td><b>职称</b></td>
<td>
${counselor.jobName}
</td>
</tr>
<tr bgcolor="#ffffff">
<td><b>职务</b></td>
<td>
${counselor.job}
</td>
<td><b>出生日期</b></td>
<td colspan="3">
<fmt:formatDate value="${counselor.birthday}" pattern="yyyy-MM-dd" />
</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6">
<b>备注</b><br><br>${counselor.instro }
</td>
</tr>
</tbody></table>
<br>

<c:forEach var="scaleCheckResult" items="${scaleCheckResultList }">
<table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody>
<tr bgcolor="#ffffff">
<td><b>量表</b></td>
<td align="center" colspan="3">${ scaleCheckResult.scaleName}</td>
</tr>
<tr bgcolor="#ffffff">
<td><b>时间</b></td>
<td><fmt:formatDate value="${scaleCheckResult.answerStartTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
<td>指导教师</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr bgcolor="#ffffff">
<td><b>评测结果</b></td>
<td colspan="3">【结果解释】${scaleCheckResult.resultExplain}</td></tr><tr bgcolor="#ffffff">
<td><b>教师意见</b></td>
<td colspan="3">${scaleCheckResult.guidance}</td></tr></tbody></table><br>
</c:forEach>
<br>
<br>
<input type="button" value="返回" id="btnBack" class="button Noprint">
</div>
<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript">
	$(function(){
		$("#btnBack").on("click", function(){
			location.href = "${ctx}/archives/archive/list";
		});
	})
</script>
</form>
</body>
</html>