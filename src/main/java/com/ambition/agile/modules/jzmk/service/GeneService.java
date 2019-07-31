/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.GeneDao;
import com.ambition.agile.modules.jzmk.entity.Gene;

/**
 * 因子设置Service
 * @author dortan
 * @version 2017-07-01
 */
@Service
@Transactional(readOnly = true)
public class GeneService extends CrudService<GeneDao, Gene> {
	
	@Autowired
	GeneDao geneDao;

	public Gene get(Integer id) {
		return super.get(id);
	}
	
	public Integer findMaxNumber(Integer tid) {
		return geneDao.findMaxNumber(tid, Gene.DEL_FLAG_NORMAL);
	}
	
	public Gene findMaxGene(Integer number, Integer tid) {
		return geneDao.findMaxGene(number, tid, Gene.DEL_FLAG_NORMAL);
	}
	
	public Gene findMinGene(Integer number, Integer tid) {
		return geneDao.findMinGene(number, tid, Gene.DEL_FLAG_NORMAL);
	}
	
	@Transactional(readOnly = false)
	public Gene insertGeneBatchs(List<Gene> genes) {
		return geneDao.insertGeneBatchs(genes);
	}
	
	@Transactional(readOnly = false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, rollbackFor=RuntimeException.class)
	public void updateGeneBatchs(List<Gene> genes) {
		for(Gene gene : genes) {
			gene.preUpdate();
			geneDao.update(gene);
		}
	}
	
	public List<Gene> findList(Gene gene) {
		return super.findList(gene);
	}
	
	public Page<Gene> findPage(Page<Gene> page, Gene gene) {
		return super.findPage(page, gene);
	}
	
	public Page<Gene> findPage(Page<Gene> page, Integer tid) {
		Gene gene = new Gene();
		gene.setTid(tid);
		return super.findPage(page, gene);
	}

	@Transactional(readOnly = false)
	public Integer save(Gene gene) {
		return super.save(gene);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Gene gene) {
		return super.delete(gene);
	}
	
}