/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import java.util.Date;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 评测结果Entity
 * @author dortan
 * @version 2017-07-01
 */
public class ScaleCheckResult extends DataEntity<ScaleCheckResult> {
	
	private static final long serialVersionUID = 1L;
	private Integer Id;				// 表ID
	private Integer taskId;         // 测评任务ID
	private String taskNumber;		// 测评批号
	private Integer taskUserId;		// 测评任务人员ID
	private Integer taskScaleId;    // 测评任务量表ID
	private Integer geneId;    		// 因子ID
	private String guidance;		// 教师指导意见
	private String resultExplain;		// 结果解释
	private String scaleName;       //量表名称
	private Date answerStartTime;   // 答题开始时间
	private Date answerEndTime;		// 答题结束时间
	private Integer state;		// 答题结束时间
	private String batchNumber; //测评批次
	private Integer orgId;		// 机构ID
	private String userName;		// 测评用户姓名
	private String userLoginName;		// 测评用户登录名
	private String geneName;		// 测评因子名称
	private String standardValue;		// 测评因子标准分
	private String geneScore;		// 测评因子得分
	private String originalScore;		// 测评原始得分
	private String areaCode;        //用户地址码
	private Integer userSex;        //用户性别
	private Integer userAge;        //用户年龄
	private Integer userType;       //用户类型
	
	public ScaleCheckResult() {
		super();
	}

	public ScaleCheckResult(Integer id){
		super(id);
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Integer getTaskUserId() {
		return taskUserId;
	}

	public void setTaskUserId(Integer taskUserId) {
		this.taskUserId = taskUserId;
	}
	
	public Integer getTaskScaleId() {
		return taskScaleId;
	}

	public void setTaskScaleId(Integer taskScaleId) {
		this.taskScaleId = taskScaleId;
	}

	public Integer getGeneId() {
		return geneId;
	}

	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}

	public String getScaleName() {
		return scaleName;
	}

	public void setScaleName(String scaleName) {
		this.scaleName = scaleName;
	}

	public String getGuidance() {
		return guidance;
	}

	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}
	
	public String getResultExplain() {
		return resultExplain;
	}

	public void setResultExplain(String resultExplain) {
		this.resultExplain = resultExplain;
	}
	
	public Date getAnswerStartTime() {
		return answerStartTime;
	}

	public void setAnswerStartTime(Date answerStartTime) {
		this.answerStartTime = answerStartTime;
	}

	public Date getAnswerEndTime() {
		return answerEndTime;
	}

	public void setAnswerEndTime(Date answerEndTime) {
		this.answerEndTime = answerEndTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getStandardValue() {
		return standardValue;
	}

	public void setStandardValue(String standardValue) {
		this.standardValue = standardValue;
	}

	public String getGeneScore() {
		return geneScore;
	}

	public void setGeneScore(String geneScore) {
		this.geneScore = geneScore;
	}

	public String getOriginalScore() {
		return originalScore;
	}

	public void setOriginalScore(String originalScore) {
		this.originalScore = originalScore;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}