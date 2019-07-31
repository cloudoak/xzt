/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 咨询问题类型Entity
 * @author OAK
 * @version 2017/12/02
 */
public class CounselQuestionType extends DataEntity<CounselQuestionType> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3296484607839661113L;
	
	private Integer id;		//问题ID
	private String name;	//问题名称
	private String remark;	//问题说明
	
	public CounselQuestionType() {
		super();
	}

	public CounselQuestionType(Integer id){
		super(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
		
}