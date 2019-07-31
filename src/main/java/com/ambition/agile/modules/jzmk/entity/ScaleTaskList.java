/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 任务测试量表Entity
 * @author dortan
 * @version 2017-07-01
 */
public class ScaleTaskList extends DataEntity<ScaleTaskList> {
	
	private static final long serialVersionUID = 1L;
	private Integer geneId;		// 量表ID
	private Integer taskId;		// 任务ID
	private Integer orgId;		// 机构ID
	
	public ScaleTaskList() {
		super();
	}

	public ScaleTaskList(Integer id){
		super(id);
	}

	public Integer getGeneId() {
		return geneId;
	}

	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}
	
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}