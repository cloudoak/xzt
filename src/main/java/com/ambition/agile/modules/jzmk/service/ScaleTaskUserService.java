/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.ScaleTaskUserDao;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskUser;

/**
 * 任务测评人员Service
 * @author dortan
 * @version 2017-07-01
 */
@Service
@Transactional(readOnly = true)
public class ScaleTaskUserService extends CrudService<ScaleTaskUserDao, ScaleTaskUser> {
	
	public ScaleTaskUser get(Integer id) {
		return super.get(id);
	}
	
	public ScaleTaskUser getForTask(ScaleTaskUser scaleTaskUser) {
		return dao.getForTask(scaleTaskUser);
	}
	
	public List<ScaleTaskUser> findList(ScaleTaskUser scaleTaskUser) {
		return super.findList(scaleTaskUser);
	}
	
	public Page<ScaleTaskUser> findPage(Page<ScaleTaskUser> page, ScaleTaskUser scaleTaskUser) {
		return super.findPage(page, scaleTaskUser);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleTaskUser scaleTaskUser) {
		return super.save(scaleTaskUser);
	}
	
	@Transactional(readOnly = false)
	public void update(ScaleTaskUser scaleTaskUser) {
		dao.update(scaleTaskUser);
	}
	
	@Transactional(readOnly = false)
	public void updateForTask(ScaleTaskUser scaleTaskUser) {
		dao.updateForTask(scaleTaskUser);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleTaskUser scaleTaskUser) {
		return super.delete(scaleTaskUser);
	}
	
	public Page<ScaleTaskUser> selectDcScalesByUser(Page<ScaleTaskUser> page, ScaleTaskUser scaleTaskUser) {
		scaleTaskUser.setPage(page);
		page.setList(dao.selectDcScalesByUser(scaleTaskUser));
		return page;
	}
	
	public Page<ScaleTaskUser> selectDcScalesCheckingByUser(Page<ScaleTaskUser> page, ScaleTaskUser scaleTaskUser) {
		scaleTaskUser.setPage(page);
		page.setList(dao.selectDcScalesCheckingByUser(scaleTaskUser));
		return page;
	}
	
	public Page<ScaleTaskUser> selectCcScalesCheckingByUser(Page<ScaleTaskUser> page, ScaleTaskUser scaleTaskUser) {
		scaleTaskUser.setPage(page);
		page.setList(dao.selectCcScalesCheckingByUser(scaleTaskUser));
		return page;
	}

	public Page<ScaleTaskUser> selectLcScalesByUser(Page<ScaleTaskUser> page, ScaleTaskUser scaleTaskUser) {
		scaleTaskUser.setPage(page);
		page.setList(dao.selectLcScalesByUser(scaleTaskUser));
		return page;
	}
	
	public ScaleTaskUser getStatisticResuslt(ScaleTaskUser scaleTaskUser){
		return dao.getStatisticResult(scaleTaskUser);
	}
	
}