/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 角色权限Entity
 * @author dortan
 * @version 2017-08-28
 */
public class SysRoleGrant extends DataEntity<SysRoleGrant> {
	
	private static final long serialVersionUID = 1L;
	private Integer menuId;		// 菜单ID
	private SysRoleGrant parent;		// 父节点Id
	private Integer gread;		// 菜单级别
	private String menuName;		// 菜单名称
	private Integer orgId;		// 所属机构
	
	public SysRoleGrant() {
		super();
	}

	public SysRoleGrant(Integer id){
		super(id);
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	@JsonBackReference
	public SysRoleGrant getParent() {
		return parent;
	}

	public void setParent(SysRoleGrant parent) {
		this.parent = parent;
	}
	
	public Integer getGread() {
		return gread;
	}

	public void setGread(Integer gread) {
		this.gread = gread;
	}
	
	@Length(min=0, max=50, message="菜单名称长度必须介于 0 和 50 之间")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}