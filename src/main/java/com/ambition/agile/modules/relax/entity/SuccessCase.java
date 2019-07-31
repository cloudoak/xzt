/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 成功案例Entity
 * @author harry
 * @version 2017-06-25
 */
public class SuccessCase extends DataEntity<SuccessCase> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 案例名称
	private String isPublic;		// 是否共享
	private String path;		// 地址
	private String intro;		// 描述
	private Integer creatorId;	//创建人 id
	private String creatorName;	//创建人姓名
	//private Integer updateBy;	//修改人
	private Integer orgId;		// 分校id
	private String orgName;		// 分校名称
	
	public SuccessCase() {
		super();
	}

	public SuccessCase(Integer id){
		super(id);
	}

	@Length(min=0, max=32, message="案例名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="是否公开长度必须介于 0 和 1 之间")
	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	
	@Length(min=0, max=128, message="课件地址长度必须介于 0 和 128 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=128, message="描述长度必须介于 0 和 128 之间")
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	//@Length(min=0, max=11, message="分校id长度必须介于 0 和 11 之间")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}