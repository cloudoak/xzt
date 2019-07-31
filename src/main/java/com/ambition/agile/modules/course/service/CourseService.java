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
import com.ambition.agile.modules.course.dao.CourseCatalogDao;
import com.ambition.agile.modules.course.dao.CourseDao;
import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.entity.CourseCatalog;

/**
 * 课件Service
 * @author harry
 * @version 2017-06-24
 */
@Service
@Transactional(readOnly = true)
public class CourseService extends FileService<CourseDao, Course> {

	@Autowired
	private CourseCatalogDao courseCatalogDao;
	
	public Course get(Integer id) {
		return super.get(id);
	}
	
	public List<Course> findList(Course course) {
		return super.findList(course);
	}
	
	public Page<Course> findPage(Page<Course> page, Course course) {
		return super.findPage(page, course);
	}
	
	@Transactional(readOnly = false)
	public Integer save(Course course) {
		
		if(null != course && course.getCourseCatalogId()!=null && course.getCourseCatalogId()>0){
			CourseCatalog  courseCatalog = courseCatalogDao.get(course.getCourseCatalogId());
			if(null != courseCatalog && courseCatalog.getId()!=null && courseCatalog.getId()>0){
				course.setCourseCatalogParentids(courseCatalog.getParentIds());
			}
		}
		return super.save(course);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Course course) {
		return super.delete(course);
	}
	
}