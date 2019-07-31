/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 条目答案Entity
 * @author dortan
 * @version 2017-08-15
 */
public class Answer extends DataEntity<Answer> {
	
	private static final long serialVersionUID = 1L;
	private int answerNo;		// 答案选项名
	private String answerContent;		// 答案内容
	private Double score;		// 答案分数
	private String parseContent;		// 答案说明
	private Integer questionId;		// 条目ID
	
	public Answer() {
		super();
	}

	public Answer(Integer id){
		super(id);
	}

	public int getAnswerNo() {
		return answerNo;
	}

	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}
	
	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	@Length(min=0, max=255, message="答案说明长度必须介于 0 和 255 之间")
	public String getParseContent() {
		return parseContent;
	}

	public void setParseContent(String parseContent) {
		this.parseContent = parseContent;
	}
	
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
}