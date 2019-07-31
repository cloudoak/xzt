/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.TreeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 音乐分类Entity
 * @author harry
 * @version 2017-06-25
 */
public class MusicCatalog extends TreeEntity<MusicCatalog> {
	
	private static final long serialVersionUID = 1L;
	private MusicCatalog parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private Integer sort;		// 排序
	
	public MusicCatalog() {
		super();
	}

	public MusicCatalog(Integer id){
		super(id);
	}

	@JsonBackReference
	public MusicCatalog getParent() {
		return parent;
	}

	public void setParent(MusicCatalog parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2048, message="所有父级编号长度必须介于 0 和 2048 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : 0;
	}
}