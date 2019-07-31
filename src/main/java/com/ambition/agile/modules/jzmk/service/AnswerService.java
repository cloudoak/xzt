/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.entity.Answer;
import com.ambition.agile.modules.jzmk.dao.AnswerDao;

/**
 * 条目答案Service
 * @author dortan
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class AnswerService extends CrudService<AnswerDao, Answer> {
	
	@Autowired
	private AnswerDao answerDao;

	public Answer get(Integer id) {
		return super.get(id);
	}
	
	public List<Answer> findList(Answer answer) {
		return super.findList(answer);
	}
	
	public BigDecimal findTotalScores(String questionIds) {
		return answerDao.findTotalScores(questionIds);
	}
	
	public List<Answer> findLastAllAnswers(Answer answer){
		return answerDao.findLastAllAnswers(answer);
	}
	
	public List<Answer> findList(Integer questionId) {
		Answer answer = new Answer();
		answer.setQuestionId(questionId);
		return super.findList(answer);
	}
	
	public Page<Answer> findPage(Page<Answer> page, Answer answer) {
		return super.findPage(page, answer);
	}
	
	@Transactional(readOnly = false)
	public Integer save(Answer answer) {
		return super.save(answer);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Answer answer) {
		return super.delete(answer);
	}
	
	public List<Answer> findByQuestionIds(String [] questionIds){
		return answerDao.findByQuestionIds(questionIds);
	}
	
	public List<Integer> findByDistinctQuestionCount(String [] id) {
		return answerDao.findByDistinctQuestionCount(id);
	}

	public Integer findMaxAnswerNo(Integer questionId) {
		return answerDao.findMaxAnswerNo(questionId);
	}
	
	@Transactional(readOnly = false)
	public int deleteByQuestionIdsAndAnswerNo(Integer answerNo, String [] questionIds) {
		return answerDao.deleteByQuestionIdsAndAnswerNo(answerNo, questionIds);
	}
	
	@Transactional(readOnly = false)
	public int deleteAnswerByQuestionId(Integer questionId) {
		return answerDao.deleteAnswerByQuestionId(questionId);
	}
	
	@Transactional(readOnly = false)
	public int deleteByQuestionIds(String questionIds) {
		return answerDao.deleteByQuestionIds(questionIds);
	}
	
}