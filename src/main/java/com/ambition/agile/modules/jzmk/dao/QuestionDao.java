/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.Question;

/**
 * 量表条目DAO接口
 * @author dortan
 * @version 2017-07-01
 */
@MyBatisDao
public interface QuestionDao extends CrudDao<Question> {
	
	BigDecimal findTotalScores(@Param("sid") Integer sid);

	Integer deleteQuestion(@Param("id") Integer id);
	
	Integer findMaxNumber(@Param("sid") Integer sid, @Param("DEL_FLAG_NORMAL") String del_flag);
	
	List<Question> findByParentIdRoot(Question question);
	
	List<Question> findLastAllQuestions(Question question);
	
	List<Question> findChildrenList(Question question);
	
	List<String> findBySidRoot(@Param("sid") Integer sid, @Param("DEL_FLAG_NORMAL") String del_flag);
	
	List<String> findBySidChilren(@Param("sid") Integer sid, @Param("DEL_FLAG_NORMAL") String del_flag);
	
	List<Question> findByParentidAndSid(@Param("parentId") Integer parentId, @Param("sid") Integer sid, @Param("DEL_FLAG_NORMAL") String del_flag);
	
	Question findMaxQuestion(@Param("number") Integer number, @Param("sid") Integer sid, @Param("DEL_FLAG_NORMAL") String del_flag);
	
	Question findMinQuestion(@Param("number") Integer number, @Param("sid") Integer sid, @Param("DEL_FLAG_NORMAL") String del_flag);
	
}