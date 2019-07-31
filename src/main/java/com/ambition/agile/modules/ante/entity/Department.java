/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.User;

/**
 * 部门管理Entity
 * @author whq
 * @version 2017-09-14
 */
public class Department extends DataEntity<Department> {
	
	private static final long serialVersionUID = 1L;
	private String deptName;		// 部门名称
	private String deptBrief;		// 部门简介
	protected User createBy;		//创建者
	protected Date createDate;		//创建日期
	protected User updateBy;		//更新者
	protected Date updateDate;		//更新日期
	private String delFlag;			//删除标志
	private Integer orgId;			//机构ID
	
	public Department() {
		super();
	}

	public Department(Integer id){
		super(id);
	}

	@Length(min=0, max=20, message="部门名称长度必须介于 0 和 20 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=0, max=250, message="部门简介长度必须介于 0 和 250 之间")
	public String getDeptBrief() {
		return deptBrief;
	}

	public void setDeptBrief(String deptBrief) {
		this.deptBrief = deptBrief;
	}

	public User getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}