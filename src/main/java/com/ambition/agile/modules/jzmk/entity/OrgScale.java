/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 分配给机构的量表Entity
 * @author dorstan
 * @version 2017-09-08
 */
public class OrgScale extends DataEntity<OrgScale> {
	
	private static final long serialVersionUID = 1L;
	private Integer sid;		// 量表ID
	private Integer orgId;		// 机构ID
	private Scale scale;	//量表
	
	public OrgScale() {
		super();
	}

	public OrgScale(Integer id){
		super(id);
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}
	
}