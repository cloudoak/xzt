/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.CircleDao;
import com.ambition.agile.modules.jzmk.entity.Circle;

/**
 * 圈子Service
 * @author dortan
 * @version 2017-07-09
 */
@Service
@Transactional(readOnly = true)
public class CircleService extends CrudService<CircleDao, Circle> {

	public Circle get(Integer id) {
		return super.get(id);
	}
	
	public List<Circle> findList(Circle circle) {
		return super.findList(circle);
	}
	
	public Page<Circle> findPage(Page<Circle> page, Circle circle) {
		return super.findPage(page, circle);
	}
	
	@Transactional(readOnly = false)
	public Integer save(Circle circle) {
		return super.save(circle);
	}
	
	@Transactional(readOnly = false)
	public void update(Circle circle) {
		dao.update(circle);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Circle circle) {
		return super.delete(circle);
	}
	
}