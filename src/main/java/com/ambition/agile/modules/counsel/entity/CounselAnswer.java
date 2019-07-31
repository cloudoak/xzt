/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 答疑室回复Entity
 * @author harry
 * @version 2017-06-24
 */
public class CounselAnswer extends DataEntity<CounselAnswer> {
	
	private static final long serialVersionUID = 1L;
	private Integer questionId;		// 问题标识
	private String title;		// 标题
	private String content;		// 回复内容
	private String isAnony;		// 是否匿名
	private Integer orgId;		// 分校id
	private String createName;	//创建人名称
	
	public CounselAnswer() {
		super();
	}

	public CounselAnswer(Integer id){
		super(id);
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	@Length(min=0, max=32, message="标题长度必须介于 0 和 32 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=128, message="回复内容长度必须介于 0 和 128 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=4, message="是否匿名长度必须介于 0 和 4 之间")
	public String getIsAnony() {
		return isAnony;
	}

	public void setIsAnony(String isAnony) {
		this.isAnony = isAnony;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
}