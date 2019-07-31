/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 测评人员答题Entity
 * @author dortan
 * @version 2017-07-01
 */
public class ScaleTaskAnswer extends DataEntity<ScaleTaskAnswer> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer taskId;     //测评任务ID
	private Integer taskUserId;	//测评任务人员ID
	private Integer tid;		//条目ID
	private Integer sid;        //量表ID
	private String answer;		//回答答案
	private Double score;		//因子得分
	private Integer orgId;		//机构ID
	private String taskNumber;  //任务号
	private Integer parentTid; //父条目ID
	private String geneQuestion; //因子条目ID集合   
	
	public ScaleTaskAnswer() {
		super();
	}

	public ScaleTaskAnswer(Integer id){
		super(id);
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getTaskUserId() {
		return taskUserId;
	}

	public void setTaskUserId(Integer taskUserId) {
		this.taskUserId = taskUserId;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Integer getParentTid() {
		return parentTid;
	}

	public void setParentTid(Integer parentTid) {
		this.parentTid = parentTid;
	}

	public String getGeneQuestion() {
		return geneQuestion;
	}

	public void setGeneQuestion(String geneQuestion) {
		this.geneQuestion = geneQuestion;
	}
	
}