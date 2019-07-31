<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-gene=1.0">
    <title>家长档案</title>
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
<li class="active"><a href="${ctx}/archives/archive/form?userId=${archives.userId}">家长档案</a></li>
</ul><br/>
<form name="aspnetForm" method="post" action="" id="aspnetForm" enctype="multipart/form-data">
<div>
<div id="test_result_head"><div id="test_result_title">家长档案</div>
<div id="div_print" class="Noprint">
<a href="#" onclick="javascript:window.print()"><img src="${ctxStatic}/images/print.png" onmouseover="this.src='${ctxStatic}/images/print_f2.png'" onmouseout="this.src='${ctxStatic}/images/print.png'" title="打印"></a></div>
</div>
<table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000" border="0" cellpadding="1">
<tbody><tr bgcolor="#ffffff">
<td class="style1" colspan="6">基本信息</td>
</tr>
<tr bgcolor="#ffffff">
<td>姓名</td>
<td>${archives.name }</td>
<td>性别</td>
<td>${fns:getDictLabel(archives.sex, 'sex', '')}</td>
<td>年龄</td>
<td>${archives.age}</td>
</tr>
<tr bgcolor="#ffffff">
<td>出生日期</td>
<td colspan="5"><fmt:formatDate value="${parents.birthday}" pattern="yyyy-MM-dd" /></td>
</tr>
</tbody></table>
<br>
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