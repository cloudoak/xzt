/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 圈子Entity
 * @author dortan
 * @version 2017-07-09
 */
public class Circle extends DataEntity<Circle> {
	
	private static final long serialVersionUID = 1L;
	private Integer cOrgId;		// 选择机构
	private String content;		// 发布内容
	private String cUrl;		// 对比图
	private Double healthScore;		// 我的健康指数
	private Integer userId;		// 发布人ID
	private String userName;		// 发布人姓名
	private Integer role;		// 发布人身份
	private Integer orgId;		// 机构ID
	private String areaCode;    //用户注册地区码
	
	public Circle() {
		super();
	}

	public Circle(Integer id){
		super(id);
	}

	public Integer getCOrgId() {
		return cOrgId;
	}

	public void setCOrgId(Integer cOrgId) {
		this.cOrgId = cOrgId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=300, message="对比图长度必须介于 0 和 300 之间")
	public String getCUrl() {
		return cUrl;
	}

	public void setCUrl(String cUrl) {
		this.cUrl = cUrl;
	}
	
	public Double getHealthScore() {
		return healthScore;
	}

	public void setHealthScore(Double healthScore) {
		this.healthScore = healthScore;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
}