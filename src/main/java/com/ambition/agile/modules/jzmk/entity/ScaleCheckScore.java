/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 测评得分Entity
 * @author wyz
 * @version 2017-11-23
 */
public class ScaleCheckScore extends DataEntity<ScaleCheckScore> {
	
	private static final long serialVersionUID = 1L;
	private Integer rid;		// 量表ID
	private String taskNumber;  // 测评编号
	private Integer gid;		// gid
	private Double score;		// score
	private Double score2;		// score2
	private Integer abnormal;		// abnormal
	private Double conditionValue;		// condition_value
	private Integer orgId;		// org_id
	private String geneName;    // geneName
	private String geneEnName;    // geneEnName
	private String scaleName;    //测评量表名称
	private String userName;     //测评用户姓名
	private String loginName;    //测评用户登录名
	private Integer userType;    //测评用户角色
	private Integer abnormalCount;    //测评批次异常数量
	private Integer totalCheckCount;    //测评批次总数
	
	public ScaleCheckScore() {
		super();
	}

	public ScaleCheckScore(Integer id){
		super(id);
	}

	@Length(min=0, max=11, message="量表ID长度必须介于 0 和 11 之间")
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	@Length(min=0, max=11, message="gid长度必须介于 0 和 11 之间")
	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	public Double getScore2() {
		return score2;
	}

	public void setScore2(Double score2) {
		this.score2 = score2;
	}
	
	public Integer getAbnormal() {
		return abnormal;
	}

	public void setAbnormal(Integer abnormal) {
		this.abnormal = abnormal;
	}
	
	public Double getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(Double conditionValue) {
		this.conditionValue = conditionValue;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getGeneEnName() {
		return geneEnName;
	}

	public void setGeneEnName(String geneEnName) {
		this.geneEnName = geneEnName;
	}

	public String getScaleName() {
		return scaleName;
	}

	public String getUserName() {
		return userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setScaleName(String scaleName) {
		this.scaleName = scaleName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getAbnormalCount() {
		return abnormalCount;
	}

	public Integer getTotalCheckCount() {
		return totalCheckCount;
	}

	public void setAbnormalCount(Integer abnormalCount) {
		this.abnormalCount = abnormalCount;
	}

	public void setTotalCheckCount(Integer totalCheckCount) {
		this.totalCheckCount = totalCheckCount;
	}
	
}