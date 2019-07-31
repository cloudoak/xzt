/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.testh.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * cuidEntity
 * @author harry
 * @version 2017-06-14
 */
public class TestH extends DataEntity<TestH> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	
	public TestH() {
		super();
	}

	public TestH(Integer id){
		super(id);
	}

	@Length(min=0, max=12, message="name长度必须介于 0 和 12 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}