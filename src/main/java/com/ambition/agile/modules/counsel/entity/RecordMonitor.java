/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 咨询督导Entity
 * @author harry
 * @version 2017-08-16
 */
public class RecordMonitor extends DataEntity<RecordMonitor> {
	
	private static final long serialVersionUID = 1L;
	private Integer recordId;		// 咨询记录标识
	private Integer counselorId;		// 督导咨询师标识
	private String counselorName;		// 督导咨询师名称
	private String monitorStatus;		// 督导状态
	private String content;		// 督导内容
	private Integer orgId;		// 分校id
	private Integer creatorId;	//创建人id
	
	public RecordMonitor() {
		super();
	}

	public RecordMonitor(Integer id){
		super(id);
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	
	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}
	
	@Length(min=0, max=32, message="督导咨询师名称长度必须介于 0 和 32 之间")
	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
	
	@Length(min=0, max=2, message="督导状态长度必须介于 0 和 2 之间")
	public String getMonitorStatus() {
		return monitorStatus;
	}

	public void setMonitorStatus(String monitorStatus) {
		this.monitorStatus = monitorStatus;
	}
	
	@Length(min=0, max=512, message="督导内容长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	
}