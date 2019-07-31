/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.entity.GeneException;
import com.ambition.agile.modules.jzmk.dao.GeneExceptionDao;

/**
 * 异常条件规则Service
 * @author OAK
 * @version 2017-09-08
 */
@Service
@Transactional(readOnly = true)
public class GeneExceptionService extends CrudService<GeneExceptionDao, GeneException> {

	public GeneException get(Integer id) {
		return super.get(id);
	}
	
	public List<GeneException> findList(GeneException geneException) {
		return super.findList(geneException);
	}
	
	public Page<GeneException> findPage(Page<GeneException> page, GeneException geneException) {
		return super.findPage(page, geneException);
	}
	
	@Transactional(readOnly = false)
	public Integer save(GeneException geneException) {
		return super.save(geneException);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(GeneException geneException) {
		return super.delete(geneException);
	}
	
}