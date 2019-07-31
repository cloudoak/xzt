/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import java.math.BigDecimal;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 异常条件规则Entity
 * @author OAK
 * @version 2017-09-08
 */
public class GeneException extends DataEntity<GeneException> {
	
	private static final long serialVersionUID = 1L;
	private Integer ageCondition; 	//年龄异常条件
	private Integer ageConditionValue;	// 年龄异常条件值
	private Boolean sexConditionValue;	// 性别异常值
	private Double conditionValue;		// 异常值
	private Integer term;	// 异常值符号
	private Integer gid;		// 所属因子
	private Integer tid;		// 所属量表
	
	public GeneException() {
		super();
	}

	public GeneException(Integer id){
		super(id);
	}
	
	public Integer getAgeCondition() {
		return ageCondition;
	}

	public void setAgeCondition(Integer ageCondition) {
		this.ageCondition = ageCondition;
	}

	public Integer getAgeConditionValue() {
		return ageConditionValue;
	}

	public void setAgeConditionValue(Integer ageConditionValue) {
		this.ageConditionValue = ageConditionValue;
	}
	
	public Boolean getSexConditionValue() {
		return sexConditionValue;
	}

	public void setSexConditionValue(Boolean sexConditionValue) {
		this.sexConditionValue = sexConditionValue;
	}
	
	public Double getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(Double conditionValue) {
		this.conditionValue = conditionValue;
	}
	
	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}
	
	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
}