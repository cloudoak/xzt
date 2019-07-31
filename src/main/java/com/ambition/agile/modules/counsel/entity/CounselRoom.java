/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 咨询室Entity
 * @author harry
 * @version 2017-07-03
 */
public class CounselRoom extends DataEntity<CounselRoom> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 咨询室名称
	private Integer counselorId;		// 咨询师
	private String  counselorName;	//咨询师名称
	private Integer status;		// 咨询室状态
	private String photoPath;		// 图片地址
	private String intro;		// 说明
	private String counselCenterId;		// 咨询中心标识
	private Integer orgId;		// 分校id
	
	public CounselRoom() {
		super();
	}

	public CounselRoom(Integer id){
		super(id);
	}

	@Length(min=0, max=32, message="咨询室名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=32, message="图片地址长度必须介于 0 和 32 之间")
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	@Length(min=0, max=128, message="说明长度必须介于 0 和 128 之间")
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@Length(min=0, max=11, message="咨询中心标识长度必须介于 0 和 11 之间")
	public String getCounselCenterId() {
		return counselCenterId;
	}

	public void setCounselCenterId(String counselCenterId) {
		this.counselCenterId = counselCenterId;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}

	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
	
}