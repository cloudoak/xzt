/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.entity.ScoringFormula;
import com.ambition.agile.modules.jzmk.dao.ScoringFormulaDao;

/**
 * 计分方式Service
 * @author dortan
 * @version 2017-08-18
 */
@Service
@Transactional(readOnly = true)
public class ScoringFormulaService extends CrudService<ScoringFormulaDao, ScoringFormula> {

	public ScoringFormula get(Integer id) {
		return super.get(id);
	}
	
	public List<ScoringFormula> findList(ScoringFormula scoringFormula) {
		return super.findList(scoringFormula);
	}
	
	public Page<ScoringFormula> findPage(Page<ScoringFormula> page, ScoringFormula scoringFormula) {
		return super.findPage(page, scoringFormula);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScoringFormula scoringFormula) {
		return super.save(scoringFormula);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScoringFormula scoringFormula) {
		return super.delete(scoringFormula);
	}
	
}