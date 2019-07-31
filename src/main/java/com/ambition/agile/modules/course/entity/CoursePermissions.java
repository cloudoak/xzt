/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.entity;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.Office;

/**
 * 课件Entity
 * @author harry
 * @version 2017-06-24
 */
public class CoursePermissions extends DataEntity<CoursePermissions> {
	
	private static final long serialVersionUID = 1L;
	private Integer courseId;
	private Integer orgId;
	private Course course;
	private Office org;
	
	public CoursePermissions() {
		super();
	}

	public CoursePermissions(Integer id){
		super(id);
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Office getOrg() {
		return org;
	}

	public void setOrg(Office org) {
		this.org = org;
	}

}