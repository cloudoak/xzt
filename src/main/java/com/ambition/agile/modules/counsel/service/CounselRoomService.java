/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.dao.CounselRoomDao;
import com.ambition.agile.modules.counsel.entity.CounselRoom;

/**
 * 咨询室Service
 * @author harry
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class CounselRoomService extends CrudService<CounselRoomDao, CounselRoom> {

	public CounselRoom get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselRoom> findList(CounselRoom counselRoom) {
		return super.findList(counselRoom);
	}
	
	public Page<CounselRoom> findPage(Page<CounselRoom> page, CounselRoom counselRoom) {
		return super.findPage(page, counselRoom);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselRoom counselRoom) {
		return super.save(counselRoom);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselRoom counselRoom) {
		return super.delete(counselRoom);
	}
	
}