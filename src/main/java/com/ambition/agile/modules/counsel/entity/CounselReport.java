/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 咨询报表Entity
 * @author harry
 * @version 2017-07-06
 */
public class CounselReport extends DataEntity<CounselReport> {
	
	private static final long serialVersionUID = 1L;
	private String sn;		// 编号
	private String title;		// 标题
	private String reportPath;		// 报告路径
	private Date beginTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String conclusion;		// 结论
	private String remark;		// 备注
	private String createName;	//	制作 人
	private Integer createId;
	private Integer updateId;
	private Integer orgId;
	
	public CounselReport() {
		super();
	}

	public CounselReport(Integer id){
		super(id);
	}

	@Length(min=0, max=16, message="编号长度必须介于 0 和 16 之间")
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
	@Length(min=0, max=32, message="标题长度必须介于 0 和 32 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=64, message="报告路径长度必须介于 0 和 64 之间")
	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
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
	
	@Length(min=0, max=1024, message="结论长度必须介于 0 和 1024 之间")
	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	@Length(min=0, max=1024, message="备注长度必须介于 0 和 1024 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}