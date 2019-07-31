/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.entity.ScaleType;
import com.ambition.agile.modules.jzmk.dao.ScaleTypeDao;

/**
 * 量表类别Service
 * @author dortan
 * @version 2017-08-25
 */
@Service
@Transactional(readOnly = true)
public class ScaleTypeService extends CrudService<ScaleTypeDao, ScaleType> {

	public ScaleType get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleType> findList(ScaleType scaleType) {
		return super.findList(scaleType);
	}
	
	public Page<ScaleType> findPage(Page<ScaleType> page, ScaleType scaleType) {
		return super.findPage(page, scaleType);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleType scaleType) {
		return super.save(scaleType);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleType scaleType) {
		return super.delete(scaleType);
	}
	
}