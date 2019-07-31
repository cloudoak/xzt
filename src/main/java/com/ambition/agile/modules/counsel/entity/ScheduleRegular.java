/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 日程安排常规Entity
 * @author harry
 * @version 2017-08-22
 */
public class ScheduleRegular extends DataEntity<ScheduleRegular> {
	
	private static final long serialVersionUID = 1L;
	private String week;		// 一星期的星期几
	private Integer counselorId;		// 咨询师ID
	private String counselorName;		// 咨询师名称
	private Date beginTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Integer orgId;		// 分中心ID
	private Integer creatorId;		// 创建id
	private Date createTime;		// 创建日期
	
	public ScheduleRegular() {
		super();
	}

	public ScheduleRegular(Integer id){
		super(id);
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	
	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}
	
	@Length(min=0, max=32, message="咨询师名称长度必须介于 0 和 32 之间")
	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建日期不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}