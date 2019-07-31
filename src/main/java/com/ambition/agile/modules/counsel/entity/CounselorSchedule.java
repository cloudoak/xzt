/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import java.sql.Time;
import java.util.Date;

import com.ambition.agile.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 咨询师排班Entity
 * @author OAK
 * @since 2017/12/12
 * @version 1.0
 */
public class CounselorSchedule extends DataEntity<CounselorSchedule> {
	
	private static final long serialVersionUID = 1L;
	private Integer counselorId;		// 咨询师id
	private Date scheduleDate;		// 预约日期
	private Time beginTime;		// 开始时间
	private Time endTime;		// 结束时间
	private Integer orgId;		// 分校id
	private Date beginScheduleDate;		// 开始 预约日期 用于前台查询 不入库
	private Date endScheduleDate;		// 结束 预约日期 用于查询 不入库
	private Counselor counselor;
	
	public CounselorSchedule() {
		super();
	}

	public CounselorSchedule(Integer id){
		super(id);
	}

	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	@JsonFormat(pattern = "HH:mm:ss")
	public Time getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Time beginTime) {
			this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "HH:mm:ss")
	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Date getBeginScheduleDate() {
		return beginScheduleDate;
	}

	public void setBeginScheduleDate(Date beginScheduleDate) {
		this.beginScheduleDate = beginScheduleDate;
	}
	
	public Date getEndScheduleDate() {
		return endScheduleDate;
	}

	public void setEndScheduleDate(Date endScheduleDate) {
		this.endScheduleDate = endScheduleDate;
	}

	public Counselor getCounselor() {
		return counselor;
	}

	public void setCounselor(Counselor counselor) {
		this.counselor = counselor;
	}
	
}