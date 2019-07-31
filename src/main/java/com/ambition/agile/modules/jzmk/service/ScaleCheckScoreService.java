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
import com.ambition.agile.modules.jzmk.entity.ScaleCheckScore;
import com.ambition.agile.modules.jzmk.dao.ScaleCheckScoreDao;

/**
 * 测评得分Service
 * @author wyz
 * @version 2017-11-23
 */
@Service
@Transactional(readOnly = true)
public class ScaleCheckScoreService extends CrudService<ScaleCheckScoreDao, ScaleCheckScore> {
	
	@Autowired
	private ScaleCheckScoreDao scaleCheckScoreDao;
	
	public ScaleCheckScore get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleCheckScore> findList(ScaleCheckScore scaleCheckScore) {
		return super.findList(scaleCheckScore);
	}
	
	public Page<ScaleCheckScore> findPage(Page<ScaleCheckScore> page, ScaleCheckScore scaleCheckScore) {
		return super.findPage(page, scaleCheckScore);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleCheckScore scaleCheckScore) {
		return super.save(scaleCheckScore);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleCheckScore scaleCheckScore) {
		return super.delete(scaleCheckScore);
	}
	
	public Integer getTotalCheckCount(ScaleCheckScore scaleCheckScore)
	{
		return scaleCheckScoreDao.getTotalCheckCount(scaleCheckScore);
	}
	
	public Integer getAbnormalCount(ScaleCheckScore scaleCheckScore)
	{
		return scaleCheckScoreDao.getAbnormalCount(scaleCheckScore);
	}
	
}