/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.ante.dao.ParentsDao;
import com.ambition.agile.modules.ante.dao.VisitorInfoDao;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.vo.ParentsVo;

/**
 * 家长 基本功能Service
 * @author fengqq
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class ParentsService extends CrudService<ParentsDao, Parents> {

	@Autowired
	private ParentsDao parentsDao;
	@Autowired
	private VisitorInfoDao visitorInfoDao;
	
	public Parents get(Integer id) {
		return super.get(id);
	}
	
	public Parents getUserByUserId(Integer userId) {
		return parentsDao.getUserByUserId(userId);
	}
	
	public List<Parents> findList(Parents parents) {
		return super.findList(parents);
	}
	
	public Page<Parents> findPage(Page<Parents> page, Parents parents) {
		return super.findPage(page, parents);
	}
	
	@Transactional(readOnly = false)
	public Integer save(Parents parents) {
		return super.save(parents);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Parents parents) {
		return super.delete(parents);
	}
	
	public Page<ParentsVo> findNewList(Page<Parents> page, Parents parents) {
		
		parents.setPage(page);
		
		Page<ParentsVo> pageVo = new Page<ParentsVo>();
		
		pageVo.setList(parentsDao.findNewList(parents));
		
		return pageVo;
	}
	
	public ParentsVo getParent(Integer id){
		
		return parentsDao.getParent(id);
		
	}

	@Transactional(readOnly = false)
	public void updatePar(ParentsVo parentsVo) {
		
		Parents parents = parentsVo;
		
		parentsDao.updateIfNull(parents);
		
		visitorInfoDao.update(parentsVo.getVisitorInfo());
	}
}