/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.entity;

import java.io.Serializable;

/**
 * 机构Entity
 * @author harry
 * @version 2013-05-15
 */
public class OrgOffice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer parentId;	// 父级编号
	private String name; 	// 机构名称
	private String useable;//是否可用
	private Integer count;//统计子节点数量，判断是否还有子节点
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUseable() {
		return useable;
	}
	public void setUseable(String useable) {
		this.useable = useable;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}