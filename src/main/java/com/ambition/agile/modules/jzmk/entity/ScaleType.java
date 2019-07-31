/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 量表类别Entity
 * @author dortan
 * @version 2017-08-25
 */
public class ScaleType extends DataEntity<ScaleType> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 量表类别名称
	private String introduce;		// 类别介绍
	private Integer orgId;		// 机构ID
	
	public ScaleType() {
		super();
	}

	public ScaleType(Integer id){
		super(id);
	}

	@Length(min=0, max=50, message="量表类别名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}