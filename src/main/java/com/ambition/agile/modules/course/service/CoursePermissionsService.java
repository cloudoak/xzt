/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.FileService;
import com.ambition.agile.modules.course.dao.CoursePermissionsDao;
import com.ambition.agile.modules.course.entity.CoursePermissions;

/**
 * 课件Service
 * @author OAK
 * @version 2017-06-24
 */
@Service
@Transactional(readOnly = true)
public class CoursePermissionsService extends FileService<CoursePermissionsDao, CoursePermissions> {

	@Autowired
	private CoursePermissionsDao coursePermissionsDao;
	
	public CoursePermissions get(Integer id) {
		return super.get(id);
	}
	
	public List<CoursePermissions> findList(CoursePermissions coursePermissions) {
		return super.findList(coursePermissions);
	}
	
	public Page<CoursePermissions> findPage(Page<CoursePermissions> page, CoursePermissions coursePermissions) {
		return super.findPage(page, coursePermissions);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CoursePermissions coursePermissions) {
		return super.save(coursePermissions);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CoursePermissions coursePermissions) {
		return super.delete(coursePermissions);
	}
	
}