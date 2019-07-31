/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import java.sql.Time;
import java.util.Date;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 咨询预约Entity
 * @author OAK
 * @since 2017/12/14
 * @version 1.0
 */
public class CounselorUserBook extends DataEntity<CounselorUserBook> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer counselorId;		// 咨询师ID
	private Integer visitorId;		// 来访者ID
	private Integer consultation;		// 咨询方式
	private Integer reservation;		// 预约方式
	private Integer questionType;		// 问题类型
	private String selfAnalysis;		// 自我分析
	private String opinion;		// 处理意见
	private Date reserveDate;		// 预约时间
	private Time beginTime;		// 开始时间
	private Time endTime;		// 结束时间
	private String description;		// 咨询内容描述
	private Integer isCase;		// 是否个案
	private Integer isOwner; //是否是来访者自己提交
	private Integer dealStatus;		// 处理状态
	private Integer allowUser; //是否允许用户查看
	private Integer allowParents; //是否允许家长查看
	private String undoReason;//撤销原因
	private VisitorInfo visitorInfo; // 来访者信息
	private Counselor counselor; //咨询师信息
	private Time currentTime;		// 开始 当前日期 用于前台查询 不入库
	
	public CounselorUserBook() {
		super();
	}

	public CounselorUserBook(Integer id){
		super(id);
	}

	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}

	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}

	public Integer getConsultation() {
		return consultation;
	}

	public void setConsultation(Integer consultation) {
		this.consultation = consultation;
	}

	public Integer getReservation() {
		return reservation;
	}

	public void setReservation(Integer reservation) {
		this.reservation = reservation;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getSelfAnalysis() {
		return selfAnalysis;
	}

	public void setSelfAnalysis(String selfAnalysis) {
		this.selfAnalysis = selfAnalysis;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsCase() {
		return isCase;
	}

	public void setIsCase(Integer isCase) {
		this.isCase = isCase;
	}

	public Integer getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Integer isOwner) {
		this.isOwner = isOwner;
	}

	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Integer getAllowUser() {
		return allowUser;
	}

	public void setAllowUser(Integer allowUser) {
		this.allowUser = allowUser;
	}

	public Integer getAllowParents() {
		return allowParents;
	}

	public void setAllowParents(Integer allowParents) {
		this.allowParents = allowParents;
	}

	public String getUndoReason() {
		return undoReason;
	}

	public void setUndoReason(String undoReason) {
		this.undoReason = undoReason;
	}

	public VisitorInfo getVisitorInfo() {
		return visitorInfo;
	}

	public void setVisitorInfo(VisitorInfo visitorInfo) {
		this.visitorInfo = visitorInfo;
	}

	public Counselor getCounselor() {
		return counselor;
	}

	public void setCounselor(Counselor counselor) {
		this.counselor = counselor;
	}

	public Time getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Time currentTime) {
		this.currentTime = currentTime;
	}

}