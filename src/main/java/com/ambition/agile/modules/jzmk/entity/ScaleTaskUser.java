/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import java.util.Date;

import com.ambition.agile.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 任务测评人员Entity
 * @author dortan
 * @version 2017-07-01
 */
public class ScaleTaskUser extends DataEntity<ScaleTaskUser> {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;		// 用户ID
	private Integer role;		// 用户身份
	private Integer taskId;		// 测评任务ID
	private String taskNumber;  // 测评批号
	private Integer tid;		// 量表ID
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Integer state;		// 状态
	private Integer orgId;		// 机构ID
	private String scaleName; //量表名称
	private int integral; //任务所需积分
	private Date startDate;		// 开始时间
	private Date endDate;		// 结束时间
	private int isState; //是否漏测试
	private String userLoginName; //用户登录名
	private String userName; //用户姓名
	private Integer userSex; //用户性别
	private Integer userAge; //用户年龄
	private Integer userType; //用户类型
	private Integer beginAge; //用户开始年龄
	private Integer endAge; //用户截止年龄
	private Integer checkTotalCount; //统计测评人数
	private Integer checkFinishedCount; //统计已测人数
	private Integer checkUnfinishedCount; //统计未测人数
	private Integer checkAbnormalCount; //统计异常人数
	private Integer checkInvalidCount; //统计无效人数
	
	public ScaleTaskUser() {
		super();
	}

	public ScaleTaskUser(Integer id){
		super(id);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
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

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getScaleName() {
		return scaleName;
	}

	public void setScaleName(String scaleName) {
		this.scaleName = scaleName;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getIsState() {
		return isState;
	}

	public void setIsState(int isState) {
		this.isState = isState;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getBeginAge() {
		return beginAge;
	}

	public Integer getEndAge() {
		return endAge;
	}

	public void setBeginAge(Integer beginAge) {
		this.beginAge = beginAge;
	}

	public void setEndAge(Integer endAge) {
		this.endAge = endAge;
	}

	public Integer getCheckTotalCount() {
		return checkTotalCount;
	}

	public Integer getCheckFinishedCount() {
		return checkFinishedCount;
	}

	public Integer getCheckUnfinishedCount() {
		return checkUnfinishedCount;
	}

	public Integer getCheckAbnormalCount() {
		return checkAbnormalCount;
	}

	public Integer getCheckInvalidCount() {
		return checkInvalidCount;
	}

	public void setCheckTotalCount(Integer checkTotalCount) {
		this.checkTotalCount = checkTotalCount;
	}

	public void setCheckFinishedCount(Integer checkFinishedCount) {
		this.checkFinishedCount = checkFinishedCount;
	}

	public void setCheckUnfinishedCount(Integer checkUnfinishedCount) {
		this.checkUnfinishedCount = checkUnfinishedCount;
	}

	public void setCheckAbnormalCount(Integer checkAbnormalCount) {
		this.checkAbnormalCount = checkAbnormalCount;
	}

	public void setCheckInvalidCount(Integer checkInvalidCount) {
		this.checkInvalidCount = checkInvalidCount;
	}
	
}