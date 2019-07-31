/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.entity.ScaleAslec;
import com.ambition.agile.modules.jzmk.dao.ScaleAslecDao;

/**
 * aslec量表解释Service
 * @author wyz
 * @version 2017-11-26
 */
@Service
@Transactional(readOnly = true)
public class ScaleAslecService extends CrudService<ScaleAslecDao, ScaleAslec> {

	public ScaleAslec get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleAslec> findList(ScaleAslec scaleAslec) {
		return super.findList(scaleAslec);
	}
	
	public Page<ScaleAslec> findPage(Page<ScaleAslec> page, ScaleAslec scaleAslec) {
		return super.findPage(page, scaleAslec);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleAslec scaleAslec) {
		return super.save(scaleAslec);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleAslec scaleAslec) {
		return super.delete(scaleAslec);
	}
	
}