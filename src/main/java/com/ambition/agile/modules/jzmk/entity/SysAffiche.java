/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 系统公告管理Entity
 * @author system_admin
 * @version 2017-06-26
 */
public class SysAffiche extends DataEntity<SysAffiche> {
	
	private static final long serialVersionUID = 1L;
	private String afficheTitle;		// 公告标题
	private String afficheContent;		// 内容
	private String headImage;		// 缩略图
	private String firstDisplay;		// 首页显示
	private String readCount;		// 点击量
	private Integer orgId;		// 机构ID
	
	public SysAffiche() {
		super();
	}

	public SysAffiche(Integer id){
		super(id);
	}

	@Length(min=0, max=100, message="公告标题长度必须介于 0 和 100 之间")
	public String getAfficheTitle() {
		return afficheTitle;
	}

	public void setAfficheTitle(String afficheTitle) {
		this.afficheTitle = afficheTitle;
	}
	
	public String getAfficheContent() {
		return afficheContent;
	}

	public void setAfficheContent(String afficheContent) {
		this.afficheContent = afficheContent;
	}
	
	@Length(min=0, max=300, message="缩略图长度必须介于 0 和 300 之间")
	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
	@Length(min=0, max=1, message="首页显示长度必须介于 0 和 1 之间")
	public String getFirstDisplay() {
		return firstDisplay;
	}

	public void setFirstDisplay(String firstDisplay) {
		this.firstDisplay = firstDisplay;
	}
	
	@Length(min=0, max=11, message="点击量长度必须介于 0 和 11 之间")
	public String getReadCount() {
		return readCount;
	}

	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}