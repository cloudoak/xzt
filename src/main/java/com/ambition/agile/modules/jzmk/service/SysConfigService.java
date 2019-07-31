/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.FileService;
import com.ambition.agile.modules.jzmk.dao.SysConfigDao;
import com.ambition.agile.modules.jzmk.entity.SysConfig;

/**
 * 系统参数配置Service
 * @author system_admin
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class SysConfigService extends FileService<SysConfigDao, SysConfig> {
	
	@Autowired
	private SysConfigDao sysConfigDao;

	public SysConfig get(Integer id) {
		return super.get(id);
	}
	
	public SysConfig findOne() {
		return sysConfigDao.findOne(new SysConfig());
	}
	
	public List<SysConfig> findList(SysConfig sysConfig) {
		return super.findList(sysConfig);
	}
	
	public Page<SysConfig> findPage(Page<SysConfig> page, SysConfig sysConfig) {
		return super.findPage(page, sysConfig);
	}
	
	@Transactional(readOnly = false)
	public Integer save(SysConfig sysConfig) {
		return super.save(sysConfig);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(SysConfig sysConfig) {
		return super.delete(sysConfig);
	}
	
}