/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 量表总解释Entity
 * @author dortan
 * @version 2017-07-03
 */
public class ScaleTotalExplain extends DataEntity<ScaleTotalExplain> {
	
	private static final long serialVersionUID = 1L;
	private Double minValue;		// 开始值
	private Double maxValue;		// 结束值
	private String explainContent;		// 解释
	private Integer tid;		// 量表ID
	private Integer scoreType;		// 计分方式
	private Integer orgId;		// 机构ID
	private String tName;	//量表名称
	
	public ScaleTotalExplain() {
		super();
	}

	public ScaleTotalExplain(Integer id){
		super(id);
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	
	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	public String getExplainContent() {
		return explainContent;
	}

	public void setExplainContent(String explainContent) {
		this.explainContent = explainContent;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	public Integer getScoreType() {
		return scoreType;
	}

	public void setScoreType(Integer scoreType) {
		this.scoreType = scoreType;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}
	
}