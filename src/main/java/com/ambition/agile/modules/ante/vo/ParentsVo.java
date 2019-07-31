package com.ambition.agile.modules.ante.vo;

import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.VisitorInfo;

/**
 * 家长 基本功能扩展类
 * 
 */
public class ParentsVo extends Parents{

	private static final long serialVersionUID = 1L;
	private VisitorInfo visitorInfo;
	private String orgName;

	public VisitorInfo getVisitorInfo() {
		return visitorInfo;
	}

	public void setVisitorInfo(VisitorInfo visitorInfo) {
		this.visitorInfo = visitorInfo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
