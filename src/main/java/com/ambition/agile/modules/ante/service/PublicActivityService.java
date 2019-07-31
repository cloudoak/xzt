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
import com.ambition.agile.modules.ante.dao.PublicActivityDao;
import com.ambition.agile.modules.ante.entity.PublicActivity;

/**
 * 公益活动Service
 * @author fengqq
 * @version 2017-07-12
 */
@Service
@Transactional(readOnly = true)
public class PublicActivityService extends CrudService<PublicActivityDao, PublicActivity> {

	@Autowired
	PublicActivityDao publicActivityDao;
	
	public PublicActivity get(Integer id) {
		return super.get(id);
	}
	
	public List<PublicActivity> findTop3List(PublicActivity publicActivity){
		return publicActivityDao.findTop3List(publicActivity);
	}
	
	public List<PublicActivity> findList(PublicActivity publicActivity) {
		return super.findList(publicActivity);
	}
	
	public Page<PublicActivity> findPage(Page<PublicActivity> page, PublicActivity publicActivity) {
		return super.findPage(page, publicActivity);
	}
	
	@Transactional(readOnly = false)
	public Integer save(PublicActivity publicActivity) {
		return super.save(publicActivity);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(PublicActivity publicActivity) {
		return super.delete(publicActivity);
	}
	
}