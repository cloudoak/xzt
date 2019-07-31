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
import com.ambition.agile.modules.jzmk.dao.ScaleDao;
import com.ambition.agile.modules.jzmk.entity.Scale;

/**
 * 量表信息Service
 * @author dortan
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class ScaleService extends CrudService<ScaleDao, Scale> {

	@Autowired
	private ScaleDao scaleDao;

	public Scale get(Integer id) {
		return super.get(id);
	}
	
	public List<Scale> findList(Scale scale) {
		return super.findList(scale);
	}
	
	public List<Scale> findAll(Scale scale){
		return scaleDao.findAll(scale);
	}
	
	public Page<Scale> findPage(Page<Scale> page, Scale scale) {
		return super.findPage(page, scale);
	}
	
	@Transactional(readOnly = false)
	public void updateLock(Integer id, Integer isLock) {
		scaleDao.updateLock(id, isLock);
	}
	
	@Transactional(readOnly = false)
	public Integer save(Scale scale) {
		return super.save(scale);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Scale scale) {
		return super.delete(scale);
	}

	public List<Scale> findUseScale(Scale scale) {
		return scaleDao.findUseScale(scale);
	}
	
}