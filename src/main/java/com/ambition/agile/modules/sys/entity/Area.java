/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.entity;

import com.ambition.agile.common.persistence.TreeEntity;
import com.google.gson.annotations.Expose;

/**
 * 区域Entity
 * @author harry
 * @version 2013-05-15
 */
public class Area extends TreeEntity<Area> {

	private static final long serialVersionUID = 1L;
	private String provice; 	// 省
	private String city; 	// 市
	private Integer county;		// 县
	@Expose
	private String name; //名称
	
	public Area(){
		super();
	}

	public Area(Integer id){
		super(id);
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Area getParent() {
		return null;
	}

	@Override
	public void setParent(Area parent) {
		
	}
	
}