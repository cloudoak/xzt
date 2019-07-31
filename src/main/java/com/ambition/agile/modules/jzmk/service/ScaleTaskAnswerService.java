/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.ScaleTaskAnswerDao;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskAnswer;

/**
 * 测评人员答题Service
 * @author dortan
 * @version 2017-07-01
 */
@Service
@Transactional(readOnly = true)
public class ScaleTaskAnswerService extends CrudService<ScaleTaskAnswerDao, ScaleTaskAnswer> {
	
	@Autowired
	ScaleTaskAnswerDao scaleTaskAnswerDao;

	public ScaleTaskAnswer get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleTaskAnswer> findList(ScaleTaskAnswer scaleTaskAnswer) {
		return super.findList(scaleTaskAnswer);
	}
	
	public Page<ScaleTaskAnswer> findPage(Page<ScaleTaskAnswer> page, ScaleTaskAnswer scaleTaskAnswer) {
		return super.findPage(page, scaleTaskAnswer);
	}
	
	public List<Integer> findByTaskNumberAndTaskUserIdAndSid(ScaleTaskAnswer scaleTaskAnswer){
		return scaleTaskAnswerDao.findByTaskNumberAndTaskUserIdAndSid(scaleTaskAnswer);
	}
	
	public ScaleTaskAnswer getScaleTaskAnswer(ScaleTaskAnswer scaleTaskAnswer) {
		return scaleTaskAnswerDao.getScaleTaskAnswer(scaleTaskAnswer);
	}
	
	public Double getSummayScore(ScaleTaskAnswer scaleTaskAnswer) {
		return scaleTaskAnswerDao.getSummayScore(scaleTaskAnswer);
	}
	
	public Double getSummaryGeneScore(ScaleTaskAnswer scaleTaskAnswer) {
		return scaleTaskAnswerDao.getSummaryGeneScore(scaleTaskAnswer);
	}
	
	public Double getMaxGeneScore(ScaleTaskAnswer scaleTaskAnswer) {
		return scaleTaskAnswerDao.getMaxGeneScore(scaleTaskAnswer);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleTaskAnswer scaleTaskAnswer) {
		return super.save(scaleTaskAnswer);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleTaskAnswer scaleTaskAnswer) {
		return super.delete(scaleTaskAnswer);
	}
	
}