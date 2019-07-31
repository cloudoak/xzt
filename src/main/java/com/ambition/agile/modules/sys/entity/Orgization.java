/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.entity;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 机构Entity
 * @author harry
 * @version 2013-05-15
 */
public class Orgization extends DataEntity<Orgization>  {

	private Integer id;
	private String text;
	private boolean children;
	
	public Orgization(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChildren() {
		return children;
	}

	public void setChildren(boolean children) {
		this.children = children;
	}

}