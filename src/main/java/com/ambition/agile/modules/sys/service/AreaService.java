/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.TreeService;
import com.ambition.agile.modules.sys.dao.AreaDao;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author harry
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public Integer save(Area area) {
		Integer b = super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
		return b;
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Area area) {
		Integer b = super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
		return b;
	}
	
}
