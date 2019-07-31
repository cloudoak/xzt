/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.QuestionDao;
import com.ambition.agile.modules.jzmk.entity.Question;

/**
 * 量表条目Service
 * @author dortan
 * @version 2017-07-01
 */
@Service
@Transactional(readOnly = true)
public class QuestionService extends CrudService<QuestionDao, Question> {
	
	@Autowired
	private QuestionDao questionDao;
	
	public Question get(Integer id) {
		return super.get(id);
	}
	
	public List<Question> findList(Question question) {
		return super.findList(question);
	}
	
	public BigDecimal findTotalScores(@Param("sid") Integer sid) {
		return questionDao.findTotalScores(sid);
	}
	
	public List<String> findBySidRoot(Integer sid) {
		return questionDao.findBySidRoot(sid, Question.DEL_FLAG_NORMAL);
	}
	
	public List<String> findBySidChilren(Integer sid) {
		return questionDao.findBySidChilren(sid, Question.DEL_FLAG_NORMAL);
	}
	
	public List<Question> findByParentIdRoot(Question question){
		return questionDao.findByParentIdRoot(question);
	}
	
	public Page<Question> findPage(Page<Question> page, Question question) {
		return super.findPage(page, question);
	}
	
	public List<Question> findChildrenList(Question question){
		return questionDao.findChildrenList(question);
	}
	
	public List<Question> findByParentidAndSid(Integer parentId, Integer sid){
		return questionDao.findByParentidAndSid(parentId, sid, Question.DEL_FLAG_NORMAL);
	}
	
	public Integer findMaxNumber(Integer sid) {
		return questionDao.findMaxNumber(sid, Question.DEL_FLAG_NORMAL);
	}
	
	public List<Question> findLastAllQuestions(Question question) {
		return questionDao.findLastAllQuestions(question);
	}
	
	public Question findMaxQuestion(Integer number, Integer sid) {
		return questionDao.findMaxQuestion(number, sid, Question.DEL_FLAG_NORMAL);
	}
	
	public Question findMinQuestion(Integer number, Integer sid) {
		return questionDao.findMinQuestion(number, sid, Question.DEL_FLAG_NORMAL);
	}
	
	@Transactional(readOnly = false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, rollbackFor=RuntimeException.class)
	public void updateQuestionBatchs(List<Question> questions) {
		for(Question question : questions) {
			question.preUpdate();
			questionDao.update(question);
		}
	}
	
	@Transactional(readOnly = false)
	public Integer save(Question question) {
		return super.save(question);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Question question) {
		return super.delete(question);
	}
	
	@Transactional(readOnly = false)
	public int deleteQuestion(Integer id) {
		return questionDao.deleteQuestion(id);
	}
	
}