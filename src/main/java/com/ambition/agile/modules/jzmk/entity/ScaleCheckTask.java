/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 量表评测任务Entity
 * @author dortan
 * @version 2017-07-01
 */
public class ScaleCheckTask extends DataEntity<ScaleCheckTask> {
	
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String batchNumber;		// 测评批号
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Integer principalId;		// 负责人ID
	private String principalName;		// 负责人名称
	private Integer state;		// 测评状态0未开始，1开始，2已结束
	private Integer orgId;		// 机构ID
	private String taskGeneIds; //测评任务量表ID串
	private String taskUserIds; //测评任务量表ID串
	
	public ScaleCheckTask() {
		super();
	}

	public ScaleCheckTask(Integer id){
		super(id);
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}
	@Length(min=0, max=20, message="测评批号长度必须介于 0 和 20 之间")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
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
	
	public Integer getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Integer principalId) {
		this.principalId = principalId;
	}
	
	@Length(min=0, max=20, message="负责人名称长度必须介于 0 和 20 之间")
	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
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

	public String getTaskGeneIds() {
		return taskGeneIds;
	}

	public void setTaskGeneIds(String taskGeneIds) {
		this.taskGeneIds = taskGeneIds;
	}

	public String getTaskUserIds() {
		return taskUserIds;
	}

	public void setTaskUserIds(String taskUserIds) {
		this.taskUserIds = taskUserIds;
	}
	
}