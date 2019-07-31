/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.User;

/**
 * 教师类型Entity
 * @author wyz
 * @version 2017-09-07
 */
public class CounselorType extends DataEntity<CounselorType> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;			//类型ID
	private String typeName;	//类型名称
	private Integer orgId;		//所属机构
	protected User createBy;	//创建者
	protected Date createDate;	//创建日期
	protected User updateBy;	//更新者
	protected Date updateDate;	//更新日期
	private String delFlag;		//删除标志
	private String comment;		//类型描述
	private String permission; 	//类型权限
	
	public CounselorType() {
		super();
	}

	public CounselorType(Integer id){
		super(id);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Length(min=0, max=20, message="类型名称长度必须介于 0 和 20 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public User getCreateBy() {
		return createBy;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}