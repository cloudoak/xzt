/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 咨询记录Entity
 * @author harry
 * @version 2017-08-15
 */
public class CounselRecord extends DataEntity<CounselRecord> {
	
	private static final long serialVersionUID = 1L;
	private Integer visitorId;		// 学员标识
	private String visitorName;		// 学员名称
	private Integer counselorId;		// 咨询师标识
	private String counselorName;		// 咨询师名称
	private Date counselDate;		// 咨询时间
	private String questionType;		// 问题类型
	private String description;		// 问题描述
	private String content;		// 咨询内容
	private String isCase;		// 是否重点个案
	private String isParentwatch;		// 是否家长查看
	private String isStudentwatch;		// 是否学生查看
	private Integer orgId;		// 分校id
	
	public CounselRecord() {
		super();
	}

	public CounselRecord(Integer id){
		super(id);
	}

	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}
	
	@Length(min=0, max=32, message="学员名称长度必须介于 0 和 32 之间")
	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
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
	public Date getCounselDate() {
		return counselDate;
	}

	public void setCounselDate(Date counselDate) {
		this.counselDate = counselDate;
	}
	
	@Length(min=0, max=2, message="问题类型长度必须介于 0 和 2 之间")
	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	@Length(min=0, max=512, message="问题描述长度必须介于 0 和 512 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=512, message="咨询内容长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="是否重点个案长度必须介于 0 和 1 之间")
	public String getIsCase() {
		return isCase;
	}

	public void setIsCase(String isCase) {
		this.isCase = isCase;
	}
	
	@Length(min=0, max=1, message="是否家长查看长度必须介于 0 和 1 之间")
	public String getIsParentwatch() {
		return isParentwatch;
	}

	public void setIsParentwatch(String isParentwatch) {
		this.isParentwatch = isParentwatch;
	}
	
	@Length(min=0, max=1, message="是否学生查看长度必须介于 0 和 1 之间")
	public String getIsStudentwatch() {
		return isStudentwatch;
	}

	public void setIsStudentwatch(String isStudentwatch) {
		this.isStudentwatch = isStudentwatch;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}