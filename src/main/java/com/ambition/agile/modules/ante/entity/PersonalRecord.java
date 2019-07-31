/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 个人成长记录Entity
 * @author whq
 * @version 2017-09-20
 */
public class PersonalRecord extends DataEntity<PersonalRecord> {
	
	private static final long serialVersionUID = 1L;
	private String subject;		// 主题
	private String content;		// 内容
	private Integer orgId;		// 机构
	
	public PersonalRecord() {
		super();
	}

	public PersonalRecord(Integer id){
		super(id);
	}

	@Length(min=0, max=255, message="主题长度必须介于 0 和 255 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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