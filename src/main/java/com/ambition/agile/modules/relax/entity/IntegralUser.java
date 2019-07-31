/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 用户积分Entity
 * @author harry
 * @version 2017-09-04
 */
public class IntegralUser extends DataEntity<IntegralUser> {
	
	private static final long serialVersionUID = 1L;
	private String integralType;		// 类型
	private String content;		// 内容
	private String status;		// 审核状态
	private Integer score;		// 积分
	private Integer orgId;		// 机构
	private Integer userId;		// 用户标识
	private String userName;		// 用户名称
	private Integer sid; //量表ID
	
	public IntegralUser() {
		super();
	}

	public IntegralUser(Integer id){
		super(id);
	}

	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getIntegralType() {
		return integralType;
	}

	public void setIntegralType(String integralType) {
		this.integralType = integralType;
	}
	
	@Length(min=0, max=64, message="内容长度必须介于 0 和 64 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="审核状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=32, message="用户名称长度必须介于 0 和 32 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
}