/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 圈子记录Entity
 * @author dortan
 * @version 2017-07-09
 */
public class CircleRecord extends DataEntity<CircleRecord> {
	
	private static final long serialVersionUID = 1L;
	private Integer cId;		// 圈子ID
	private Integer replier;		// 回复人
	private Integer replierRole;		// 回复人身份
	private Integer replied;		// 被回复人
	private Integer repliedRole;		// 被回复人身份
	private String content;		// 回复内容
	private Integer orgId;		// 机构ID
	
	public CircleRecord() {
		super();
	}

	public CircleRecord(Integer id){
		super(id);
	}

	public Integer getCId() {
		return cId;
	}

	public void setCId(Integer cId) {
		this.cId = cId;
	}
	
	public Integer getReplier() {
		return replier;
	}

	public void setReplier(Integer replier) {
		this.replier = replier;
	}
	
	public Integer getReplierRole() {
		return replierRole;
	}

	public void setReplierRole(Integer replierRole) {
		this.replierRole = replierRole;
	}
	
	public Integer getReplied() {
		return replied;
	}

	public void setReplied(Integer replied) {
		this.replied = replied;
	}
	
	public Integer getRepliedRole() {
		return repliedRole;
	}

	public void setRepliedRole(Integer repliedRole) {
		this.repliedRole = repliedRole;
	}
	
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
	
}