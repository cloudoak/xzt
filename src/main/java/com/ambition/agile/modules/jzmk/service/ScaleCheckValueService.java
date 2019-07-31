/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckValue;
import com.ambition.agile.modules.jzmk.dao.ScaleCheckValueDao;

/**
 * 测评评分Service
 * @author wyz
 * @version 2017-12-03
 */
@Service
@Transactional(readOnly = true)
public class ScaleCheckValueService extends CrudService<ScaleCheckValueDao, ScaleCheckValue> {

	public ScaleCheckValue get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleCheckValue> findList(ScaleCheckValue scaleCheckValue) {
		return super.findList(scaleCheckValue);
	}
	
	public Page<ScaleCheckValue> findPage(Page<ScaleCheckValue> page, ScaleCheckValue scaleCheckValue) {
		return super.findPage(page, scaleCheckValue);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleCheckValue scaleCheckValue) {
		return super.save(scaleCheckValue);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleCheckValue scaleCheckValue) {
		return super.delete(scaleCheckValue);
	}
	
}