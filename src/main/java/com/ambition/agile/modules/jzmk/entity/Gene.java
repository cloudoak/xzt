/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 因子设置Entity
 * @author dortan
 * @version 2017-07-01
 */
public class Gene extends DataEntity<Gene> {
	
	private static final long serialVersionUID = 1L;
	private Integer tid;		// 所属量表
	private Integer typeId;		// 因子类别
	private String name;		// 因子名称
	private String enname;		// 英文名称
	private String shortName;		// 因子简称
	private Integer number;		// 因子序号
	private String formula;		// 计分公式
	private String question;		// 包含条目
	private Integer scoringFormula;		// 计分方式
	private Double maxValue;		// 最大值
	private Double minValue;		// 最小值
	private Double standardValue;		// 标准分
	private Double avgValue;		// 平均分
	private Integer orgId;		// 机构ID
	
	public Gene() {
		super();
	}

	public Gene(Integer id){
		super(id);
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=0, max=50, message="因子名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="英文名称长度必须介于 0 和 50 之间")
	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}
	
	@Length(min=0, max=20, message="因子简称长度必须介于 0 和 20 之间")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	@Length(min=0, max=2000, message="包含条目长度必须介于 0 和 2000 之间")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Integer getScoringFormula() {
		return scoringFormula;
	}

	public void setScoringFormula(Integer scoringFormula) {
		this.scoringFormula = scoringFormula;
	}
	
	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	
	public Double getStandardValue() {
		return standardValue;
	}

	public void setStandardValue(Double standardValue) {
		this.standardValue = standardValue;
	}
	
	public Double getAvgValue() {
		return avgValue;
	}

	public void setAvgValue(Double avgValue) {
		this.avgValue = avgValue;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}