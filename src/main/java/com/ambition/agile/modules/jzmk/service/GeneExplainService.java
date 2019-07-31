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
import com.ambition.agile.modules.jzmk.dao.GeneExplainDao;
import com.ambition.agile.modules.jzmk.entity.FactorValue;
import com.ambition.agile.modules.jzmk.entity.GeneExplain;
import com.ambition.agile.modules.jzmk.entity.Sections;

/**
 * 因子解释Service
 * @author dortan
 * @version 2017-07-01
 */
@Service
@Transactional(readOnly = true)
public class GeneExplainService extends CrudService<GeneExplainDao, GeneExplain> {
	
	@Autowired
	private GeneExplainDao geneExplainDao;

	public GeneExplain get(Integer id) {
		return super.get(id);
	}
	
	public List<GeneExplain> findList(GeneExplain geneExplain) {
		return super.findList(geneExplain);
	}
	
	public Page<GeneExplain> findPage(Page<GeneExplain> page, GeneExplain geneExplain) {
		return super.findPage(page, geneExplain);
	}
	
	public List<Sections> getSections(Integer tid, Integer gid) {
		return geneExplainDao.getSections(tid, gid, GeneExplain.DEL_FLAG_NORMAL);
	}
	
	public FactorValue getFactorValue(Integer id) {
		return geneExplainDao.getFactorValue(id);
	}
	
	@Transactional(readOnly = false)
	public Integer save(GeneExplain geneExplain) {
		return super.save(geneExplain);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(GeneExplain geneExplain) {
		return super.delete(geneExplain);
	}
	
}