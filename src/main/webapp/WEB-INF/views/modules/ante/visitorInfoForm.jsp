<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>来访者管理</title>
    <meta charset="utf-8">
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
	    <div class="row">
	        <div class="col-sm-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>
	                    	<a href="${ctx}/ante/visitorInfo/form?id=${visitorInfo.id}">来访者<shiro:hasPermission name="ante:visitorInfo:edit">${not empty visitorInfo.id?'修改':'新增'}</shiro:hasPermission><shiro:lacksPermission name="ante:visitorInfo:view">查看</shiro:lacksPermission></a>
	                    </h5>
	                </div>
	                <div class="ibox-content">
	                    <form:form id="signupForm" modelAttribute="visitorInfo" action="${ctx}/ante/visitorInfo/save" method="post" class="form-horizontal m-t">
	                        <input type="hidden" name="id" value="${visitorInfo.id}" />
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">编号：<span style="color:red;font-size:18px">*</span></label>
	                            <div class="col-sm-8">
	                                <input id="visitorNo" name="visitorNo" value="${visitorInfo.visitorNo}" htmlEscape="false" class="form-control" maxlength="30" required="required"/>
	                            </div>
	                        </div>
                        	<div class="form-group">
								<label class="col-sm-3 control-label">密码:<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="password" name="password" type="password" value="${visitorInfo.password}" maxlength="50" class="form-control"  minlength="3" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">验证密码:<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="confirmNewPassword" name="confirmNewPassword" type="password" maxlength="50" minlength="3" class="form-control" equalTo="#password" required="required"/>
								</div>
							</div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">姓名：<span style="color:red;font-size:18px">*</span></label>
	                            <div class="col-sm-8">
	                                <input id="name" name="user.name" value="${visitorInfo.user.name}" htmlEscape="false" maxlength="30" class="form-control" required="required"/>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">性别：<span style="color:red;font-size:18px">*</span></label>
	                            <div class="col-md-4">
		                            <fieldset>
		                                <div class="radio radio-info radio-inline">
		                                    <input type="radio" id="gender1" value="1" name="sex"
		                                        ${visitorInfo.sex==1?"checked":"" } required="required">
		                                    <label for="gender1">男</label>
		                                </div>
		                                <div class="radio radio-inline">
		                                    <input type="radio" id="gender2" value="0" name="sex"
		                                        ${visitorInfo.sex==0?"checked":"" } required="required">
		                                    <label for="gender2">女</label>
		                                </div>
		                            </fieldset>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">民族：<span style="color:red;font-size:18px">*</span></label>
	                            <div class="col-sm-8">
	                                <form:select path="nation" id="nation" class="form-control">
	                                    <form:option value="" label=""/>
	                                    <form:options items="${fns:getDictList('nation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
	                                </form:select>
	                            </div>
	                        </div>
	                        <div class="form-group">
								<label class="col-sm-3 control-label">出生年月：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="birthday" name="birthday" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
										value="<fmt:formatDate value="${visitorInfo.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" onchange="getAge()" required="required"/>
								</div>
							</div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">年龄：</label>
	                            <div class="col-sm-4">
	                                <input id="age" name="age" value="${visitorInfo.age}" readonly="readonly" htmlEscape="false" maxlength="3" class="form-control"/>
	                            </div>
	                        </div>
	                        <input type="hidden" id="age" name="age" value="${visitorInfo.age}">
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">籍贯：<span style="color:red;font-size:18px">*</span></label>
	                            <div class="col-sm-8">
	                                <input id="nativePlace" name="nativePlace" value="${visitorInfo.nativePlace}" htmlEscape="false" maxlength="20" class="form-control "/>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">联系电话：<span style="color:red;font-size:18px">*</span></label>
	                            <div class="col-sm-8">
	                                <input id="phoneNum" name="phoneNum" value="${visitorInfo.phoneNum}" htmlEscape="false" maxlength="20" class="form-control" required="required"/>
	                            </div>
	                        </div>
	                        <!-- 非必输字段区域 -->
	                        <div class="form-group">
	                        	<div class="col-sm-8">
	                        		<div class="i-check">
	                        		<input name="advshow" type="checkbox" />
	                        		<label class="col-sm-3 control-label">显示其他选项</label>
	                        		</div>
	                        	</div>
	                        </div>
	                        <div id="showadv" hidden="true">
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">昵称：</label>
		                            <div class="col-sm-8">
		                                <input id="nickName" name="nickName" value="${visitorInfo.nickName}" htmlEscape="false" maxlength="64" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">电子邮件：</label>
		                            <div class="col-sm-8">
		                                <input id="email" name="email" value="${visitorInfo.email}" htmlEscape="false" maxlength="30" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭住址：</label>
		                            <div class="col-sm-8">
		                                <input id="address" name="address" value="${visitorInfo.address}" htmlEscape="false" maxlength="50" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否城镇：</label>
		                            <div class="col-md-4">
			                            <fieldset>
			                                <div class="radio radio-info radio-inline">
			                                    <input type="radio" id="isCity1" value="1" name="isCity"
			                                        ${visitorInfo.isCity==1?"checked":"" } required="required">
			                                    <label for="isCity1">是</label>
			                                </div>
			                                <div class="radio radio-inline">
			                                    <input type="radio" id="isCity2" value="0" name="isCity"
			                                        ${visitorInfo.isCity==0?"checked":"" } required="required">
			                                    <label for="isCity2">否</label>
			                                </div>
			                            </fieldset>
			                        </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否住宿生：</label>
		                            <div class="col-md-4">
			                            <fieldset>
			                                <div class="radio radio-info radio-inline">
			                                    <input type="radio" id="isBoarder1" value="1" name="isBoarder"
			                                        ${visitorInfo.isBoarder==1?"checked":"" } required="required">
			                                    <label for="isBoarder1">是</label>
			                                </div>
			                                <div class="radio radio-inline">
			                                    <input type="radio" id="isBoarder2" value="0" name="isBoarder"
			                                        ${visitorInfo.isBoarder==0?"checked":"" } required="required">
			                                    <label for="isBoarder2">否</label>
			                                </div>
			                            </fieldset>
			                         </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否学生干部：</label>
		                            <div class="col-md-4">
			                            <fieldset>
			                                <div class="radio radio-info radio-inline">
			                                    <input type="radio" id="isStudentCadre1" value="1" name="isStudentCadre"
			                                        ${visitorInfo.isStudentCadre==1?"checked":"" } required="required">
			                                    <label for="isStudentCadre1">是</label>
			                                </div>
			                                <div class="radio radio-inline">
			                                    <input type="radio" id="isStudentCadre2" value="0" name="isStudentCadre"
			                                        ${visitorInfo.isStudentCadre==0?"checked":"" } required="required">
			                                    <label for="isStudentCadre2">否</label>
			                                </div>
			                            </fieldset>
			                          </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">爱好：</label>
		                            <div class="col-sm-8">
		                                <input id="hobby" name="hobby" value="${visitorInfo.hobby}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">特长：</label>
		                            <div class="col-sm-8">
		                                <input id="speciality" name="speciality" value="${visitorInfo.speciality}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">童年成长经历：</label>
		                            <div class="col-sm-8">
		                                <input id="childhoodExperience" name="childhoodExperience" value="${visitorInfo.childhoodExperience}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">身体健康情况：</label>
		                            <div class="col-sm-8">
		                                <input id="physicalConditions" name="physicalConditions" value="${visitorInfo.physicalConditions}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">学业情况：</label>
		                            <div class="col-sm-8">
		                                <input id="academicConditions" name="academicConditions" value="${visitorInfo.academicConditions}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">奖惩情况：</label>
		                            <div class="col-sm-8">
		                                <input id="rewardsConditions" name="rewardsConditions" value="${visitorInfo.rewardsConditions}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">自我评价：</label>
		                            <div class="col-sm-8">
		                                <input id="selfAssessment" name="selfAssessment" value="${visitorInfo.selfAssessment}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否单亲：</label>
		                            <div class="col-md-4">
			                            <fieldset>
			                                <div class="radio radio-info radio-inline">
			                                    <input type="radio" id="isSingleParent1" value="1" name="isSingleParent"
			                                        ${visitorInfo.isSingleParent==1?"checked":"" } required="required">
			                                    <label for="isSingleParent1">是</label>
			                                </div>
			                                <div class="radio radio-inline">
			                                    <input type="radio" id="isSingleParent2" value="0" name="isSingleParent"
			                                        ${visitorInfo.isSingleParent==0?"checked":"" } required="required">
			                                    <label for="isSingleParent2">否</label>
			                                </div>
			                            </fieldset>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否与父母同住：</label>
		                            <div class="col-md-4">
			                            <fieldset>
			                                <div class="radio radio-info radio-inline">
			                                    <input type="radio" id="isLwyp1" value="1" name="isLwyp"
			                                        ${visitorInfo.isLwyp==1?"checked":"" } required="required">
			                                    <label for="isLwyp1">是</label>
			                                </div>
			                                <div class="radio radio-inline">
			                                    <input type="radio" id="isLwyp2" value="0" name="isLwyp"
			                                        ${visitorInfo.isLwyp==0?"checked":"" } required="required">
			                                    <label for="isLwyp2">否</label>
			                                </div>
			                            </fieldset>
			                         </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭排行：</label>
		                            <div class="col-sm-8">
		                                <input id="familyConstellation" name="familyConstellation" value="${visitorInfo.familyConstellation}" htmlEscape="false" type="digits" maxlength="1"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲姓名：</label>
		                            <div class="col-sm-8">
		                                <input id="fatherName" name="fatherName" htmlEscape="false" value="${visitorInfo.fatherName}" maxlength="30" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲年龄：</label>
		                            <div class="col-sm-8">
		                                <input id="fatherAge" name="fatherAge" value="${visitorInfo.fatherAge}" htmlEscape="false" maxlength="2" type="digits"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲电话：</label>
		                            <div class="col-sm-8">
		                                <input id="fatherPhone" name="fatherPhone" value="${visitorInfo.fatherPhone}" htmlEscape="false" maxlength="11" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲受教育水平：</label>
		                            <div class="col-sm-8">
		                                <input id="fatherEducation" name="fatherEducation" value="${visitorInfo.fatherEducation}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲职业：</label>
		                            <div class="col-sm-8">
		                                <input id="fatherDuty" name="fatherDuty" value="${visitorInfo.fatherDuty}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲职务：</label>
		                            <div class="col-sm-8">
		                                <input id="fatherJob" name="fatherJob" value="${visitorInfo.fatherJob}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲姓名：</label>
		                            <div class="col-sm-8">
		                                <input id="motherName" name="motherName" value="${visitorInfo.motherName}" htmlEscape="false" maxlength="30" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲年龄：</label>
		                            <div class="col-sm-8">
		                                <input id="motherAge" name="motherAge" value="${visitorInfo.motherAge}" htmlEscape="false" maxlength="2" type="digits"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲电话：</label>
		                            <div class="col-sm-8">
		                                <input id="motherPhone" name="motherPhone" value="${visitorInfo.motherPhone}" htmlEscape="false" maxlength="11" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲受教育水平：</label>
		                            <div class="col-sm-8">
		                                <input id="motherEducation" name="motherEducation" value="${visitorInfo.motherEducation}" htmlEscape="false" maxlength="255"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲职业：</label>
		                            <div class="col-sm-8">
		                                <input id="motherDuty" name="motherDuty" value="${visitorInfo.motherDuty}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲职务：</label>
		                            <div class="col-sm-8">
		                                <input id="motherJob" name="motherJob" value="${visitorInfo.motherJob}" htmlEscape="false" maxlength="255" class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">直系亲属是否有病史：</label>
		                            <div class="col-md-4">
		                            <fieldset>
		                                <div class="radio radio-info radio-inline">
		                                    <input type="radio" id="isFamilyHistory1" value="1" name="isFamilyHistory"
		                                        ${visitorInfo.isFamilyHistory==1?"checked":"" } required="required">
		                                    <label for="isFamilyHistory1">是</label>
		                                </div>
		                                <div class="radio radio-inline">
		                                    <input type="radio" id="isFamilyHistory2" value="0" name="isFamilyHistory"
		                                        ${visitorInfo.isFamilyHistory==0?"checked":"" } required="required">
		                                    <label for="isFamilyHistory2">否</label>
		                                </div>
		                            </fieldset>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">直系亲属病史：</label>
		                            <div class="col-sm-8">
		                                <input id="familyHistory" name="familyHistory" value="${visitorInfo.familyHistory}" htmlEscape="false" maxlength="255"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭中人际关系气氛：</label>
		                            <div class="col-sm-8">
		                                <input id="familyRelationship" name="familyRelationship" value="${visitorInfo.familyRelationship}" htmlEscape="false" maxlength="255"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">亲朋好友基本情况及联系方式：</label>
		                            <div class="col-sm-8">
		                                <input id="familyInformation" name="familyInformation" value="${visitorInfo.familyInformation}" htmlEscape="false" maxlength="255"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否贫困：</label>
		                            <div class="col-md-4">
		                            <fieldset>
		                                <div class="radio radio-info radio-inline">
		                                    <input type="radio" id="isPoverty1" value="1" name="isPoverty"
		                                        ${visitorInfo.isPoverty==1?"checked":"" } required="required">
		                                    <label for="isPoverty1">是</label>
		                                </div>
		                                <div class="radio radio-inline">
		                                    <input type="radio" id="isPoverty2" value="0" name="isPoverty"
		                                        ${visitorInfo.isPoverty==0?"checked":"" } required="required">
		                                    <label for="isPoverty2">否</label>
		                                </div>
		                            </fieldset>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭年收入：</label>
		                            <div class="col-sm-8">
		                                <input id="householdIncome" name="householdIncome" type="number" value="${visitorInfo.householdIncome}" htmlEscape="false" class="form-control" />
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">兄弟姐妹数：</label>
		                            <div class="col-sm-8">
		                                <input id="householdBsTotal" name="householdBsTotal" type="digits" value="${visitorInfo.householdBsTotal}" htmlEscape="false" maxlength="2"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭总人数：</label>
		                            <div class="col-sm-8">
		                                <input id="householdTotal" name="householdTotal" type="digits" value="${visitorInfo.householdTotal}" htmlEscape="false" maxlength="2"
		                                            class="form-control"/>
		                            </div>
		                        </div>
		                       <%--  <div class="form-group">
		                            <label class="col-sm-3 control-label">关联用户：</label>
		                            <div class="col-sm-8">
		                                <sys:treeselect id="user" name="user.id" value="${visitorInfo.user.id}"
		                                                labelName="user.name" labelValue="${visitorInfo.user.name}"
		                                                title="用户" url="/sys/office/treeData?type=3" cssClass="form-control"
		                                                allowClear="true" notAllowSelectParent="true"/>
		                            </div>
		                        </div> --%>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">备注：</label>
		                            <div class="col-sm-8">
		                                <form:textarea path="remarks" name="remarks" value="${visitorInfo.remarks}" htmlEscape="false" rows="4" maxlength="255"
		                                               class="form-control"/>
		                            </div>
		                        </div>
		                       <%--  <div class="form-group">
		                            <label class="col-sm-3 control-label">机构：</label>
		                            <div class="col-sm-8">
		                                <input id="orgId" name="orgId" value="${visitorInfo.orgId}" htmlEscape="false" maxlength="11" class="form-control  digits"/>
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">机构：</label>
		                            <div class="col-sm-8">
				                        <select id="orgId" name="orgId" class="input-medium">
											<option value="">--请选择--</option>
											<c:forEach items="${offices.list}" var="office">
												<option value="${office.id}">${office.name}</option>
											</c:forEach>
										</select>
									</div>
		                        </div> --%>
		                    </div>
	                        <div class="form-group">
	                            <div class="col-sm-8 col-sm-offset-3">
	                                <shiro:hasPermission name="ante:visitorInfo:edit">
	                                	<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
	                                </shiro:hasPermission>
	                                <input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回" />
	                            </div>
	                        </div>
	                        <%--</form>--%>
	                    </form:form>
	                    <sys:message content="${message}"/>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
     <script type="text/javascript">
        $(function () {
            $("#signupForm").validate({
            	rules:{
            		"visitorNo":{required: true},
            		"realName": {required: true}
            	},
            	messages:{
            		visitorNo: {required: "请输入学号"},
            		realName: {required: "请输入姓名"},
            		confirmNewPassword: {equalTo: "输入与上面相同的密码"}
            	},
                submitHandler: function (form) {
                    //loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            $("input[type=checkbox][name=advshow]").on('ifClicked', function(event){
		   		var checked = $(this).is(':checked');
		       	if(!checked){
		       		$("#showadv").show();
		       	}else{
		       		$("#showadv").hide();
		       	}
		   	});
        });
        
        $(".radio").iCheck({radioClass:"iradio_square-green"});
        $(".i-check").iCheck({checkboxClass:"icheckbox_square-green"})
	    
	    //根据出生日期算出年龄
	    function getAge(){         
	        var returnAge;
	        var strBirthday = document.getElementById("birthday").value;
	        var strBirthdayArr=strBirthday.split("-"); 
	        var birthYear = strBirthdayArr[0];  
	        var birthMonth = strBirthdayArr[1];  
	        var birthDay = strBirthdayArr[2];  
	          
	        d = new Date();  
	        var nowYear = d.getFullYear();  
	        var nowMonth = d.getMonth() + 1;  
	        var nowDay = d.getDate();  
	          
	        if(nowYear == birthYear){  
	            returnAge = 0;//同年 则为0岁  
	        } else{  
	            var ageDiff = nowYear - birthYear ; //年之差  
	            if(ageDiff > 0){  
	                if(nowMonth == birthMonth) {  
	                    var dayDiff = nowDay - birthDay;//日之差  
	                    if(dayDiff < 0)  
	                    {  
	                        returnAge = ageDiff - 1;  
	                    }  
	                    else  
	                    {  
	                        returnAge = ageDiff ;  
	                    }  
	                }  
	                else  
	                {  
	                    var monthDiff = nowMonth - birthMonth;//月之差  
	                    if(monthDiff < 0)  
	                    {  
	                        returnAge = ageDiff - 1;  
	                    }  
	                    else  
	                    {  
	                        returnAge = ageDiff ;  
	                    }  
	                }  
	            }  
	            else  
	            {  
	                returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天  
	            }  
	        }  
	        document.getElementById("age").value = returnAge;
	    };
	</script>
</body>
</html>