/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.ante.entity.PersonalRecord;
import com.ambition.agile.modules.ante.dao.PersonalRecordDao;

/**
 * 个人成长记录Service
 * @author whq
 * @version 2017-09-20
 */
@Service
@Transactional(readOnly = true)
public class PersonalRecordService extends CrudService<PersonalRecordDao, PersonalRecord> {

	public PersonalRecord get(Integer id) {
		return super.get(id);
	}
	
	public List<PersonalRecord> findList(PersonalRecord personalRecord) {
		return super.findList(personalRecord);
	}
	
	public Page<PersonalRecord> findPage(Page<PersonalRecord> page, PersonalRecord personalRecord) {
		return super.findPage(page, personalRecord);
	}
	
	@Transactional(readOnly = false)
	public Integer save(PersonalRecord personalRecord) {
		return super.save(personalRecord);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(PersonalRecord personalRecord) {
		return super.delete(personalRecord);
	}
	
}