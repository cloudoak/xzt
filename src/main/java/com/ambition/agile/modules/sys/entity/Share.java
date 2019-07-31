/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.sys.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 共享积分Entity
 * @author shenclus
 * @version 2017-12-11
 */
public class Share extends DataEntity<Share> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 共享人ID
	private String dataType;		// 数据来源:0-系统;1-量表;2-课件;3-心灵咖啡屋;
	private String dataLink;		// 资料链接
	private String score;		// 奖励积分
	private String remark;		// 备注
	private String orgId;		// 机构号
	private String status;		// 审核状态：0-待审核；1-审核通过；2-审核未通过；
	
	public Share() {
		super();
	}

	public Share(Integer id){
		super(id);
	}

	@NotNull(message="共享人ID不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=11, message="数据来源:0-系统;1-量表;2-课件;3-心灵咖啡屋;长度必须介于 1 和 11 之间")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Length(min=1, max=250, message="资料链接长度必须介于 1 和 250 之间")
	public String getDataLink() {
		return dataLink;
	}

	public void setDataLink(String dataLink) {
		this.dataLink = dataLink;
	}
	
	@Length(min=0, max=11, message="奖励积分长度必须介于 0 和 11 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=500, message="备注长度必须介于 0 和 500 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=1, max=11, message="机构号长度必须介于 1 和 11 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=1, max=11, message="审核状态：0-待审核；1-审核通过；2-审核未通过；长度必须介于 1 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}