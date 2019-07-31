/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.course.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.course.service.CourseCatalogService;

/**
 * 课件 管理Controller
 * @author harry
 * @version 2013-4-21
 */
@Controller
@RequestMapping(value = "${adminPath}/course")
public class CourseIndexController extends BaseController {

	@Autowired
	private CourseCatalogService courseCatalogService;
	
	@RequiresPermissions("course:view")
	@RequestMapping(value = "")
	public String index() {
		return "modules/course/courseIndex";
	}
	
	@RequiresPermissions("course:view")
	@RequestMapping(value = "tree")
	public String tree(Model model) {
		model.addAttribute("courseCatelogList",courseCatalogService.findByUser(true, null));
		return "modules/course/courseTree";
	}
	
	@RequiresPermissions("course:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/course/courseNone";
	}

}
