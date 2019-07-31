/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.ante.entity.Department;
import com.ambition.agile.modules.ante.dao.DepartmentDao;

/**
 * 部门管理Service
 * @author whq
 * @version 2017-09-14
 */
@Service
@Transactional(readOnly = true)
public class DepartmentService extends CrudService<DepartmentDao, Department> {

	public Department get(Integer id) {
		return super.get(id);
	}
	
	public List<Department> findList(Department department) {
		return super.findList(department);
	}
	
	public Page<Department> findPage(Page<Department> page, Department department) {
		return super.findPage(page, department);
	}
	
	@Transactional(readOnly = false)
	public Integer save(Department department) {
		return super.save(department);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Department department) {
		return super.delete(department);
	}
	
}