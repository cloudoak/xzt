/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 咨询中心Entity
 * @author harry
 * @version 2017-06-22
 */
public class CounselCenter extends DataEntity<CounselCenter> {
	
	private static final long serialVersionUID = 1L;
	private String intro;		// 咨询中心介绍
	private String institution;		// 咨询中心制度
	private String workHour;		// 工作时间
	private String address;		// 咨询中心地址
	private String contactWay;		// 联系方式
	private Integer orgId;		// 分校id
	
	public CounselCenter() {
		super();
	}

	public CounselCenter(Integer id){
		super(id);
	}

	@Length(min=0, max=128, message="咨询中心介绍长度必须介于 0 和 128 之间")
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@Length(min=0, max=128, message="咨询中心制度长度必须介于 0 和 128 之间")
	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	@Length(min=0, max=64, message="工作时间长度必须介于 0 和 64 之间")
	public String getWorkHour() {
		return workHour;
	}

	public void setWorkHour(String workHour) {
		this.workHour = workHour;
	}
	
	@Length(min=0, max=64, message="咨询中心地址长度必须介于 0 和 64 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=64, message="联系方式长度必须介于 0 和 64 之间")
	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
	//@Length(min=0, max=11, message="分校id长度必须介于 0 和 11 之间")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}