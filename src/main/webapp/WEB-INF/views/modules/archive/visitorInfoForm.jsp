<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>来访者档案管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
	</script>
	<style type="text/css">
    .style2
    {
        font-weight: bold;
        width: 84px;
        border: #000000 1px solid
    }
    .style3
    {
  	  border: #000000 1px solid
    }
    .style4
    {
        font-weight: bold;
        width: 122px;
        border: #000000 1px solid
    }
    .style5
    {
        width: 112px;
        border: #000000 1px solid
    }
    .style6
    {
        font-weight: bold;
        width: 112px;
        border: #000000 1px solid
    }
    .style7
    {
        width: 84px;
        border: #000000 1px solid
    }
    .style8
    {
        width: 129px;
        border: #000000 1px solid
    }
    .style9
    {
        text-align: center;
        border: #000000 1px solid;
    }
    .style10
    {
        height: 57px;
        border: #000000 1px solid
    }
</style>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/archive/visitorInfo/">来访者档案列表</a></li>
		<li class="active"><a href="${ctx}/archive/visitorInfo/form?id=${visitorInfo.id}">来访者档案<shiro:hasPermission name="archive:visitorInfo:edit">${not empty visitorInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="archive:visitorInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
<div style="width:1200px;margin-left:60px;">
<div id="test_result_head"><div id="test_result_title">来访者档案</div>
	<table style="border: #000000 1px solid" cellSpacing="1" width="100%" bgcolor="#000000" border="0">
		<tr bgcolor="#ffffff" >
			<td class="style9" colspan="6">基本信息</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3"><b>姓名</b></td>
			<td class="style3">${visitorInfo.realName}</td>
			<td class="style5"><b>性别</b></td>
			<td class="style8">${visitorInfo.sex}</td>
			<td class="style7"><b>年龄</b></td>
			<td class="style3">${visitorInfo.age}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3"><b>编号</b></td>
			<td class="style3">${visitorInfo.visitorNo}</td>
			<td class="style5"><b>院系</b></td>
			<td class="style8">A学校 > 222</td>
			<td class="style7"><b>民族</b></td>
			<td class="style3">${visitorInfo.nation}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3"><b>籍贯</b></td>
			<td class="style3">${visitorInfo.nativePlace}</td>
			<td class="style5"><b>电子邮箱</b></td>
			<td class="style8">${visitorInfo.email}</td>
			<td class="style7"><b>是否城镇</b></td>
			<td class="style3">${visitorInfo.isCity}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style4">家庭住址</td>
			<td class="style3" colspan="5">${visitorInfo.address}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3" colspan="6"><b>爱好</b><br/>${visitorInfo.hobby}<br/></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3" colspan="6" class="style10"><b>特长</b><br/>${visitorInfo.speciality}<br/></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3" colspan="6"><b>童年成长经历</b><br/>${visitorInfo.childhoodExperience}<br/></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3" colspan="6"><b>身体健康情况</b><br/>${visitorInfo.physicalConditions}<br/></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3" colspan="6"><b>自我评价<br/></b>${visitorInfo.selfAssessment}<br/></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style4">是否单亲</td>
			<td class="style3">${visitorInfo.isSingleParent}</td>
			<td class="style6">是否与父母同住</td>
			<td class="style8">${visitorInfo.isLwyp}</td>
			<td class="style2">家庭排行</td>
			<td class="style3">${visitorInfo.familyConstellation}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style4">父亲姓名</td>
			<td class="style3">${visitorInfo.fatherName}</td>
			<td class="style6">父亲年龄</td>
			<td class="style8">${visitorInfo.fatherAge}</td>
			<td class="style2">父亲电话</td>
			<td class="style3">${visitorInfo.fatherPhone}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style4">父亲受教育水平</td>
			<td class="style3">${visitorInfo.fatherEducation}</td>
			<td class="style6">父亲职业</td>
			<td class="style8">${visitorInfo.fatherDuty}</td>
			<td class="style2">父亲职务</td>
			<td class="style3">${visitorInfo.fatherJob}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style4">母亲姓名</td>
			<td class="style3">${visitorInfo.motherName}</td>
			<td class="style6">母亲年龄</td>
			<td class="style8">${visitorInfo.motherAge}</td>
			<td class="style2">母亲电话</td>
			<td class="style3">${visitorInfo.motherPhone}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style4">母亲受教育水平</td>
			<td class="style3">${visitorInfo.motherEducation}</td>
			<td class="style6">母亲职业</td>
			<td class="style8">${visitorInfo.motherDuty}</td>
			<td class="style2">母亲职务</td>
			<td class="style3">${visitorInfo.motherJob}</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3" colspan="6"><b>直系亲属病史</b><br/>${visitorInfo.familyHistory}<br/></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3" colspan="6"><b>家庭中人际关系气氛</b><br/>${visitorInfo.familyRelationship}<br/></td>
		</tr>
		<tr bgcolor="#ffffff">
			<td class="style3" colspan="6"><b>亲朋好友基本情况及联系方式<br/></b>${visitorInfo.familyInformation}<br/></td>
		</tr>
	</table>
	<br>
    <table style="border: #000000 1px solid" cellSpacing="1" width="100%" bgColor="#000000">
    	<tr bgColor="#ffffff">
			<td class="style3"><b>量表</b></td>
			<td class="style3" align="center" colSpan="3">思维能力测试</td>
		</tr>
		<tr bgColor="#ffffff">
			<td class="style3"><b>时间</b></td>
			<td class="style3">2017/9/5 17:20:13</td>
			<td class="style3">指导咨询师</td>
			<td class="style3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr bgColor="#ffffff">
			<td class="style3"><b>评测结果</b></td>
			<td class="style3" colSpan="3">【结果解释】得分：<span class=score>8</span><br/>从测量的结果来看，你具有较强的逻辑思维能力，在类比、发散和聚合思维方面比其他人更强。</td></tr><tr bgColor="#ffffff">
			<td class="style3"><b>咨询师意见</b></td>
			<td class="style3" colSpan="3">在知识和技能的学习，逻辑思维能力等方面具有优势。受兴趣影响很大，容易产生偏科或坚持性不强，你需要综合地发展自己的能力，希望你合理利用自己的优势，在学业和事业上取得更大成功。</td></tr></table><br><table style="border: #000000 1px solid" cellSpacing="1" width="100%" bgColor="#000000">
		<tr bgColor="#ffffff">
			<td class="style3" align="center">网上问题留言记录</td>
		</tr>
		<tr bgColor="#ffffff">
			<td class="style3"><b>问题:</b> 123211231232132131[110 2017/9/13 17:19:41]<br>
				12321312321321<br><b>解答:</b> 你没救了[测试咨询师 2017/9/13 17:20:41]<br>傻不傻<br></td>
		</tr>
	</table>
	<br>
	<c:forEach items="${page1.list}" var="visitorInfo">									
		<table style="border: #000000 1px solid" cellSpacing="1" width="100%" bgColor="#000000">
			<tr bgColor="#ffffff">
				<td class="style3" align="center" colSpan="4">来访者心理咨询谈话记录</td>
			</tr>
			<tr bgColor="#ffffff">
				<td class="style3" align="center">咨询师</td>
				<td class="style3" align="center">${visitorInfo.counselorName}</td>
				<td class="style3" align="center">咨询时间</td>
				<td class="style3" align="center">${visitorInfo.counselDate}</td>counselDate
			</tr>
			<tr bgColor="#ffffff">
				<td class="style3" colSpan="4">${visitorInfo.content}</td>
			<tr>
		</table>
	</c:forEach>
	<br>
	<table style="border: #000000 1px solid" cellSpacing="1" width="100%" bgColor="#000000">
		<tr bgColor="#ffffff">
			<td class="style3" align="center">综合性评价</td>
		</tr>
		<tr bgColor="#ffffff">
			<td class="style3"><b>咨询师的评价:</b> 21312321321[测试咨询师 2017/9/13 17:22:01]<br>123213213213213让我分申飞飞<br></td>
		</tr>
	</table>
	<br>        
	<br>
	<INPUT type="button" value="返回" onclick="history.go(-1)" class="button Noprint">
</div>
</div>	
</body>
</html>