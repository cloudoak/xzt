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
	                    <h5>来访者查看</h5>
	                </div>
	                <div class="ibox-content">
	                    <form:form id="signupForm" modelAttribute="visitorInfo" method="post" class="form-horizontal m-t">
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">编号：</label>
	                            <div class="col-sm-8">
	                                ${visitorInfo.visitorNo}
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">姓名：</label>
	                            <div class="col-sm-8">
	                                ${visitorInfo.user.name}
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">性别：</label>
	                            <div class="col-md-8">
	                            	${fns:getDictLabel(visitorInfo.sex, 'sex', '女')}
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">民族：</label>
	                            <div class="col-sm-8">
	                            	${fns:getDictLabel(visitorInfo.nation, 'nation', '未知')}
	                            </div>
	                        </div>
	                        <div class="form-group">
								<label class="col-sm-3 control-label">出生年月：</label>
								<div class="col-sm-8">
									<fmt:formatDate value="${visitorInfo.birthday}" pattern="yyyy-MM-dd"/>
								</div>
							</div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">籍贯：</label>
	                            <div class="col-sm-8">
	                                ${visitorInfo.nativePlace}
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">联系电话：</label>
	                            <div class="col-sm-8">
	                                ${visitorInfo.phoneNum}
	                            </div>
	                        </div>
	                        <!-- 非必输字段区域 -->
	                        <div class="form-group">
	                        	<div class="col-sm-8">
	                        		<div class="i-checks">
	                        			<input name="advshow" type="checkbox" />
	                        			<label class="col-sm-3 control-label">显示其他选项</label>
	                        		</div>
	                        	</div>
	                        </div>
	                        <div id="showadv" hidden="true">
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">昵称：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.nickName}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">电子邮件：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.email}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭住址：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.address}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否城镇：</label>
		                            <div class="col-sm-8">
		                                ${fns:getDictLabel(visitorInfo.isCity, 'is_city', '否')}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否住宿生：</label>
		                            <div class="col-sm-8">
		                                ${fns:getDictLabel(visitorInfo.isBoarder, 'is_boarder', '否')}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否学生干部：</label>
		                            <div class="col-sm-8">
		                                ${fns:getDictLabel(visitorInfo.isStudentCadre, 'is_student_cadre', '否')}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">爱好：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.hobby}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">特长：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.speciality}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">童年成长经历：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.childhoodExperience}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">身体健康情况：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.physicalConditions}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">学业情况：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.academicConditions}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">奖惩情况：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.rewardsConditions}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">自我评价：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.selfAssessment}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否单亲：</label>
		                            <div class="col-md-4">
		                            	${fns:getDictLabel(visitorInfo.isSingleParent, 'is_single_parent', '否')}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否与父母同住：</label>
		                            <div class="col-md-4">
		                            	${fns:getDictLabel(visitorInfo.isLwyp, 'is_lwyp', '否')}
			                        </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭排行：</label>
		                            <div class="col-sm-8">
		                               ${visitorInfo.familyConstellation}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲姓名：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.fatherName}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲年龄：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.fatherAge}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲电话：</label>
		                            <div class="col-sm-8">
		                               ${visitorInfo.fatherPhone}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲受教育水平：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.fatherEducation}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲职业：</label>
		                            <div class="col-sm-8">
		                               ${visitorInfo.fatherDuty}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">父亲职务：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.fatherJob}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲姓名：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.motherName}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲年龄：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.motherAge}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲电话：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.motherPhone}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲受教育水平：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.motherEducation}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲职业：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.motherDuty}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">母亲职务：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.motherJob}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">直系亲属是否有病史：</label>
		                            <div class="col-md-4">
		                            	${fns:getDictLabel(visitorInfo.isFamilyHistory, 'is_family_history', '否')}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">直系亲属病史：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.familyHistory}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭中人际关系气氛：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.familyRelationship}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">亲朋好友基本情况及联系方式：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.familyInformation}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">是否贫困：</label>
		                            <div class="col-md-4">
		                            ${fns:getDictLabel(visitorInfo.isPoverty, 'is_poverty', '否')}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭年收入：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.householdIncome}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">兄弟姐妹数：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.householdBsTotal}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">家庭总人数：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.householdTotal}
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">备注：</label>
		                            <div class="col-sm-8">
		                                ${visitorInfo.remarks}
		                            </div>
		                        </div>
		                    </div>
	                        <div class="form-group">
	                            <div class="col-sm-8 col-sm-offset-3">
	                                <input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回" />
	                            </div>
	                        </div>
	                    </form:form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("input[type=checkbox][name=advshow]").on('ifClicked', function(event){
		   		var checked = $(this).is(':checked');
		       	if(!checked){
		       		$("#showadv").show();
		       	}else{
		       		$("#showadv").hide();
		       	}
		   	});
		});
	    $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
	</script>
</body>
</html>