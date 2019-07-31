/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * aslec量表解释Entity
 * @author wyz
 * @version 2017-11-26
 */
public class ScaleAslec extends DataEntity<ScaleAslec> {
	
	private static final long serialVersionUID = 1L;
	private String question;		// question
	private String explain;		// explain
	private String idList;      // idList
	public ScaleAslec() {
		super();
	}

	public ScaleAslec(Integer id){
		super(id);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}
	
}