/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.ante.entity.HappyMirror;
import com.ambition.agile.modules.ante.dao.HappyMirrorDao;

/**
 * 幸福镜子Service
 * @author whq
 * @version 2017-09-16
 */
@Service
@Transactional(readOnly = true)
public class HappyMirrorService extends CrudService<HappyMirrorDao, HappyMirror> {

	public HappyMirror get(Integer id) {
		return super.get(id);
	}
	
	public List<HappyMirror> findList(HappyMirror happyMirror) {
		return super.findList(happyMirror);
	}
	
	public Page<HappyMirror> findPage(Page<HappyMirror> page, HappyMirror happyMirror) {
		return super.findPage(page, happyMirror);
	}
	
	@Transactional(readOnly = false)
	public Integer save(HappyMirror happyMirror) {
		return super.save(happyMirror);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(HappyMirror happyMirror) {
		return super.delete(happyMirror);
	}
	
}