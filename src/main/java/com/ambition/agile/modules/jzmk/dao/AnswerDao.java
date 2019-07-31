/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.Answer;

/**
 * 条目答案DAO接口
 * @author dortan
 * @version 2017-08-15
 */
@MyBatisDao
public interface AnswerDao extends CrudDao<Answer> {

	/**
	 * 查询条目答案标签
	 * @param questionId
	 * @return
	 */
	Integer findMaxAnswerNo(@Param("questionId") Integer questionId);
	
	BigDecimal findTotalScores(@Param("question_ids") String questionIds);
	
	List<Integer> findByDistinctQuestionCount(@Param("array") String [] ids);
	
	List<Answer> findLastAllAnswers(Answer answer);
	
	int deleteAnswerByQuestionId(@Param("questionId") Integer questionId);
	
	int deleteByQuestionIds(@Param("questionId") String questionIds);
	
	int deleteByQuestionIdsAndAnswerNo(@Param("answerNo") Integer answerNo, @Param("array") String [] questionIds);
	
	List<Answer> findByQuestionIds(@Param("array") String [] questionIds);
	
}