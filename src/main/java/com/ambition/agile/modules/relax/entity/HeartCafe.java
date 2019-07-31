/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 心灵咖啡屋Entity
 * @author harry
 * @version 2017-06-26
 */
public class HeartCafe extends DataEntity<HeartCafe> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String content;		// 内容
	private Integer hits;		// 点击数
	private String isPublic;		// 是否公开
	private Integer creatorId;	//创建人 id
	private String creatorName;	//创建人姓名
	private Integer orgId;		// 分校id
	private String orgName;		// 分校名称
	
	public HeartCafe() {
		super();
	}

	public HeartCafe(Integer id){
		super(id);
	}

	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}
	
	@Length(min=0, max=1, message="是否公开长度必须介于 0 和 1 之间")
	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}