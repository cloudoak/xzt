/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.Office;

/**
 * 咨询师机构Entity
 * @author OAK
 * @version 1.0
 * @since 2018/02/21
 */
public class CounselorOffice extends DataEntity<CounselorOffice> {
	
	private static final long serialVersionUID = 1L;
	private Integer counselorId;
	private Integer officeId;
	private Counselor counselor;
	private Office office;
	
	public CounselorOffice() {
		super();
	}

	public CounselorOffice(Integer id){
		super(id);
	}

	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public Counselor getCounselor() {
		return counselor;
	}

	public void setCounselor(Counselor counselor) {
		this.counselor = counselor;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

}