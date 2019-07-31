/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import java.io.Serializable;
import java.util.List;

/**
 * 量表条目含有子条目Entity
 * @author OAK
 * @version 2017-07-01
 */
public class MultiQuestion implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -843153215595201646L;

	private Question question;
	
	private List<Answer> answers;
	
	private String check;
	
	public MultiQuestion() {
		super();
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
	
}