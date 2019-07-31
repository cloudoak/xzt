/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.ScaleTotalExplainDao;
import com.ambition.agile.modules.jzmk.entity.ScaleTotalExplain;

/**
 * 量表总解释Service
 * @author dortan
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class ScaleTotalExplainService extends CrudService<ScaleTotalExplainDao, ScaleTotalExplain> {

	public ScaleTotalExplain get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleTotalExplain> findList(ScaleTotalExplain scaleTotalExplain) {
		return super.findList(scaleTotalExplain);
	}
	
	public Page<ScaleTotalExplain> findPage(Page<ScaleTotalExplain> page, ScaleTotalExplain scaleTotalExplain) {
		return super.findPage(page, scaleTotalExplain);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleTotalExplain scaleTotalExplain) {
		return super.save(scaleTotalExplain);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleTotalExplain scaleTotalExplain) {
		return super.delete(scaleTotalExplain);
	}
	
}