package com.ambition.agile.modules.jzmk.entity;

import java.math.BigDecimal;

import com.ambition.agile.common.persistence.DataEntity;

public class FactorValue extends DataEntity<FactorValue> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8007911701935363470L;

	private BigDecimal minValue; 
	
	private BigDecimal maxValue;
	
	public FactorValue() {
		
	}

	public FactorValue(BigDecimal minValue, BigDecimal maxValue) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}
	
}
