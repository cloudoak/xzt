/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 量表条目Entity
 * @author OAK
 * @version 2017-07-01
 */
public class Question extends DataEntity<Question> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 条目内容
	private Integer number;		// 序号
	private Integer sid;		// 量表ID
	private Integer orgId;		// 机构ID
	private String parentId; // 父条目ID
	private Integer isSetAnswer; //是否设置答案
	public Question() {
		super();
	}

	public Question(Integer id){
		super(id);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getIsSetAnswer() {
		return isSetAnswer;
	}

	public void setIsSetAnswer(Integer isSetAnswer) {
		this.isSetAnswer = isSetAnswer;
	}
	
}