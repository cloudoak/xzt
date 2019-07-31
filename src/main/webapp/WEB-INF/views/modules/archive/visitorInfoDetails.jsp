<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-gene=1.0">
    <title>来访者档案</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<meta name="decorator" content="default"/>
	<style media="print">
         .Noprint { display: none }
         .PageNext{ page-break-after: always }
    </style>
	<style type="text/css">
	    .style2
	    {
	        font-weight: bold;
	        width: 84px;
	    }
	    .style3
	    {
	    }
	    .style4
	    {
	        font-weight: bold;
	        width: 122px;
	    }
	    .style5
	    {
	        width: 112px;
	    }
	    .style6
	    {
	        font-weight: bold;
	        width: 112px;
	    }
	    .style7
	    {
	        width: 84px;
	    }
	    .style8
	    {
	        width: 129px;
	    }
	    .style9
	    {
	        text-align: center;
	    }
	    .style10
	    {
	        height: 57px;
	    }
	</style>
</head>
<body>
<ul class="nav nav-tabs Noprint">
<li><a href="${ctx}/archives/archive/list">档案查询</a></li>
<li class="active"><a href="${ctx}/archives/archive/form?userId=${archives.userId}">来访者档案</a></li>
</ul><br/>
<form name="aspnetForm" method="post" action="" id="aspnetForm" enctype="multipart/form-data">
<div>
<div id="test_result_head"><div id="test_result_title">学生档案</div>
<div id="div_print" class="Noprint">
<a href="#" onclick="javascript:window.print()"><img src="${ctxStatic}/images/print.png" onmouseover="this.src='${ctxStatic}/images/print_f2.png'" onmouseout="this.src='${ctxStatic}/images/print.png'" title="打印"></a></div>
</div>
<table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000" border="0">
<tbody><tr bgcolor="#ffffff">
<td class="style9" colspan="6">基本信息</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style3"><b>姓名</b></td>
<td>
${archives.name }
</td>
<td class="style5"><b>性别</b></td>
<td class="style8">
${fns:getDictLabel(archives.sex, 'sex', '')}
</td>
<td class="style7"><b>年龄</b></td>
<td>
${archives.age}
</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style3"><b>学号</b></td>
<td>
${archives.userName}
</td>
<td class="style5"><b>院系</b></td>
<td class="style8">
A学校 &gt; 组织1
</td>
<td class="style7"><b>民族</b></td>
<td>
${visitorInfo.nation}
</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style3"><b>籍贯</b></td>
<td>

</td>
<td class="style5"><b>电子邮箱</b></td>
<td class="style8">
${visitorInfo.email}
</td>
<td class="style7"><b>是否城镇</b></td>
<td>
否
</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style4">家庭住址</td>
<td colspan="5">
${visitorInfo.address}
</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style4">是否住宿生</td>
<td>
否
</td>
<td class="style6">是否学生干部</td>
<td class="style8">
否
</td>
<td class="style2">是否贫困生</td>
<td>
否
</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>爱好</b><br>
<br>
</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6" class="style10"><b>特长</b><br>
<br>

</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>童年成长经历</b><br>
<br>

</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>身体健康情况</b><br>
<br>

</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>学业情况</b><br>
<br>

</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>奖惩情况</b><br>
<br>

</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>自我评价<br>
</b>
<br>

</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style4">是否单亲</td>
<td>
否
</td>
<td class="style6">是否与父母同住</td>
<td class="style8">
否
</td>
<td class="style2">家庭排行</td>
<td>
0
</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style4">父亲姓名</td>
<td>

</td>
<td class="style6">父亲年龄</td>
<td class="style8">

</td>
<td class="style2">父亲电话</td>
<td>

</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style4">父亲受教育水平</td>
<td>

</td>
<td class="style6">父亲职业</td>
<td class="style8">

</td>
<td class="style2">父亲职务</td>
<td>

</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style4">母亲姓名</td>
<td>

</td>
<td class="style6">母亲年龄</td>
<td class="style8">

</td>
<td class="style2">母亲电话</td>
<td>

</td>
</tr>
<tr bgcolor="#ffffff">
<td class="style4">母亲受教育水平</td>
<td>

</td>
<td class="style6">母亲职业</td>
<td class="style8">

</td>
<td class="style2">母亲职务</td>
<td>

</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>直系亲属病史</b><br>
<br>

</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>家庭中人际关系气氛</b><br>
<br>

</td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="6"><b>亲朋好友基本情况及联系方式<br>
</b>
<br>

</td>
</tr>
</tbody></table>
<br>
<table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000">
<tbody><tr bgcolor="#ffffff">
<td align="center">网上问题留言记录</td>
</tr>
<tr bgcolor="#ffffff">
<td><b>问题:</b> jjjjjjjjjjjjjjjjjjjjj[王克 2017/12/10 21:54:17]<br>
lllljjjjjjjjjjjjjjjjjjj<br></td>
</tr>
</tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br><table style="border: #000000 1px solid" cellspacing="1" width="100%" bgcolor="#000000"><tbody><tr bgcolor="#ffffff">
<td align="center" colspan="4">学生心理咨询谈话记录</td>
</tr><tr bgcolor="#ffffff">
<td align="center">咨询师</td>
<td align="center">测试咨询师</td>
<td align="center">咨询时间</td>
<td align="center">2017/12/10 22:00:00</td>
</tr>
<tr bgcolor="#ffffff"><td colspan="4">kkkklkkkkkkkkkkk</td></tr><tr></tr></tbody></table><br>
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