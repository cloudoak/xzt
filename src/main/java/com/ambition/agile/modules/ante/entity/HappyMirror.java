/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.User;

/**
 * 幸福镜子Entity
 * @author whq
 * @version 2017-09-16
 */
public class HappyMirror extends DataEntity<HappyMirror> {
	
	private static final long serialVersionUID = 1L;
	private String testName;		// 测试人
	private String testIdType;		// 身份类型
	private String testScore;		// 分数
	private String orgId;			// 机构
	protected User createBy;		//创建者
	protected Date createDate;		//创建日期
	protected User updateBy;		//更新者
	protected Date updateDate;		//更新日期
	private String delFlag;			//删除标志
	
	public HappyMirror() {
		super();
	}

	public HappyMirror(Integer id){
		super(id);
	}

	@Length(min=0, max=100, message="测试人长度必须介于 0 和 100 之间")
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	@Length(min=0, max=1, message="身份类型长度必须介于 0 和 1 之间")
	public String getTestIdType() {
		return testIdType;
	}

	public void setTestIdType(String testIdType) {
		this.testIdType = testIdType;
	}
	
	@Length(min=0, max=3, message="分数长度必须介于 0 和 3 之间")
	public String getTestScore() {
		return testScore;
	}

	public void setTestScore(String testScore) {
		this.testScore = testScore;
	}
	
	@Length(min=0, max=11, message="机构长度必须介于 0 和 11 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	
}