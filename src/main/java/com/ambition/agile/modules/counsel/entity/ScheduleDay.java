/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 日程安排天Entity
 * @author harry
 * @version 2017-08-22
 */
public class ScheduleDay extends DataEntity<ScheduleDay> {
	
	private static final long serialVersionUID = 1L;
	private Date day;		// 某年某月某天
	private Integer orgId;		// 分中心ID
	private Integer creatorId;		// 创建id
	private Date createTime;		// 创建日期
	
	public ScheduleDay() {
		super();
	}

	public ScheduleDay(Integer id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="某年某月某天不能为空")
	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
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