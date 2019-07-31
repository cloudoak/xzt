/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 计分方式Entity
 * @author dortan
 * @version 2017-08-18
 */
public class ScoringFormula extends DataEntity<ScoringFormula> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String formula;		// 公式
	private Integer orgId;		// 机构ID
	
	public ScoringFormula() {
		super();
	}

	public ScoringFormula(Integer id){
		super(id);
	}

	@Length(min=0, max=50, message="名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1000, message="公式长度必须介于 0 和 1000 之间")
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}