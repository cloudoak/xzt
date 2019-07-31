/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.common.persistence.Page;

/**
 * 量表类别Entity
 * @author dortan
 * @version 2017-08-25
 */
public class ScaleTypeCollection extends DataEntity<ScaleTypeCollection> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 量表类别名称
	private String introduce;		// 类别介绍
	private Integer orgId;		// 机构ID
	private Page<Scale> scales; //量表
	
	public ScaleTypeCollection() {
		super();
		scales = new Page<Scale>();
	}

	public ScaleTypeCollection(Integer id){
		super(id);
		scales = new Page<Scale>();
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

	public Page<Scale> getScales() {
		return scales;
	}

	public void setScales(Page<Scale> scales) {
		this.scales = scales;
	}
	
}