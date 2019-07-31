/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.archive.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.archive.dao.ArchivesDao;
import com.ambition.agile.modules.archive.entity.Archives;

/**
 * 档案Service
 * @author OAK
 * @version 1.0
 * @since 2018/1/12
 */
@Service
@Transactional(readOnly = true)
public class ArchivesService extends CrudService<ArchivesDao, Archives> {


	public Archives get(Integer id) {
		return super.get(id);
	}
	
	public List<Archives> findList(Archives teacherInfo) {
		return super.findList(teacherInfo);
	}
	
	public Page<Archives> findPage(Page<Archives> page, Archives archives) {
		return super.findPage(page, archives);
	}
	
}