/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公益活动Entity
 * @author fengqq
 * @version 2017-07-12
 */
public class PublicActivity extends DataEntity<PublicActivity> {
	
	private static final long serialVersionUID = 1L;
	private String title;			// 标题
	private String content;			// 内容
	private Integer checkStatus;	// 审核状态
	private Integer activityStatus;	// 活动状态
	private String createName;		// 发布人
	private String orgName;			// 所属机构
	private Integer accessCount;	// 访问数量
	private Integer parterNum;		// 访问人数
	private Date startTime;			// 开始时间
	private Date endTime;			// 结束时间
	private Integer orgId;			// 机构ID
	private Date beginUpdateDate;	// 开始 发布时间
	private Date endUpdateDate;		// 结束 发布时间
	private String remarks;			// 审核意见
	private Integer flag;			// 操作标志：区分平台与个人活动
	
	public PublicActivity() {
		super();
	}

	public PublicActivity(Integer id){
		super(id);
	}

	@Length(min=0, max=30, message="标题长度必须介于 0 和 30 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getCheckStatus() {
		return checkStatus;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	@Length(min=0, max=30, message="发布人长度必须介于 0 和 30 之间")
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	@Length(min=0, max=100, message="所属机构长度必须介于 0 和 100 之间")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public Integer getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}
	
	public Integer getParterNum() {
		return parterNum;
	}

	public void setParterNum(Integer parterNum) {
		this.parterNum = parterNum;
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
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
		
}