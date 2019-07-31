/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.entity.CourseCatalog;
import com.ambition.agile.modules.course.entity.CoursePermissions;
import com.ambition.agile.modules.course.service.CourseCatalogService;
import com.ambition.agile.modules.course.service.CoursePermissionsService;
import com.ambition.agile.modules.course.service.CourseService;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.google.common.collect.Maps;

/**
 * 课件Controller
 * @author harry
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/course/course")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CoursePermissionsService coursePermissionsService;
	
	@Autowired
	private CourseCatalogService courseCatalogService;
	
	@Autowired 
	private OfficeService officeService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = {"myCourse"})
	public String myCourse(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("courseCatelogList",courseCatalogService.findByUser(true, null));
		String orgId = organizationService.getOrganizationJoinString();
		course.setOrgId(orgId);
		course.setOwner(1);
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("course", course);
		return "modules/course/courseStudy";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("courseCatelogList",courseCatalogService.findByUser(true, null));
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("course", course);
		return "modules/course/courseList";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "setBrowsePermissions")
	public String setBrowsePermissions(@RequestParam(required=false) Integer id,  Model model, HttpServletRequest request) {
		if (null != id  && id>0){
			Course course = courseService.get(id);
			model.addAttribute("course", course);
			CoursePermissions coursePermission = new CoursePermissions();
			coursePermission.setCourseId(id);
			List<CoursePermissions> coursePermissions = coursePermissionsService.findList(coursePermission);
			model.addAttribute("coursePermissions", coursePermissions);
		}
		Integer parentId = UserUtils.getOrg().getId();
		model.addAttribute("rootParentId", parentId);
		return "modules/course/courseBrowseForm";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "savePermissions")
	public String savePermissions(@RequestParam(required=false) Integer id, CoursePermissions coursePermissions, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		Integer orgId = coursePermissions.getOrgId();
		Integer courseId = coursePermissions.getCourseId();
		if(orgId != null) {
			next(new CoursePermissions(), courseId, orgId);
		}
		addMessage(redirectAttributes, "保存课件组织权限成功");
		return "redirect:" + Global.getAdminPath() + "/course/course/setBrowsePermissions?id=" + courseId;
	}
	
	public void next(CoursePermissions coursePermissions, Integer courseId, Integer previous) {
		Office off = new Office();
		off.setParentId(previous);
		List<Office> children = officeService.findOrgList(off);
		if(children != null && children.size() > 0) {
			for(Office of : children) {
				CoursePermissions cPermissions = new CoursePermissions();
				cPermissions.setCourseId(courseId);
				cPermissions.setOrgId(of.getId());
				next(cPermissions, courseId, of.getId());
			}
		}else {
			coursePermissionsService.save(coursePermissions);
		}
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "deletePermissions")
	public String deletePermissions(Integer id, Integer courseId, RedirectAttributes redirectAttributes) {
		CoursePermissions coursePermissions = new CoursePermissions();
		coursePermissions.setId(id);
		coursePermissionsService.delete(coursePermissions);
		addMessage(redirectAttributes, "删除课件组织权限成功");
		return "redirect:"+Global.getAdminPath()+"/course/course/setBrowsePermissions?id=" + courseId;
	}

	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "form")
	public String form(@RequestParam(required=false) Integer id, Course course, Model model,HttpServletRequest request) {
		
		if (null != id  && id>0){
			course = courseService.get(id);
		}
		if (course == null){
			course = new Course();
		}
		
		if(course != null) {
			model.addAttribute("fileAbsolutePath", courseService.getOriginalFilePath(course.getPath()));
		}
		model.addAttribute("fileAbsPath", courseService.getOriginalFilePath());
		
		String courseCatalogId = (String)request.getParameter("course.courseCatalogId");
		if( StringUtils.isNotEmpty(courseCatalogId) ){
			
			CourseCatalog courseCatalog = courseCatalogService.get(Integer.parseInt(courseCatalogId));
			if(null != courseCatalog ){
				if(null != courseCatalog.getId()){
					course.setCourseCatalogId(courseCatalog.getId());
				}
				if(StringUtils.isNotEmpty(courseCatalog.getParentIds())){
					course.setCourseCatalogParentids(courseCatalog.getParentIds());
				}
				if(StringUtils.isNotEmpty(courseCatalog.getName())){
					course.setCourseCatalogName(courseCatalog.getName());
				}
			}
		}
		model.addAttribute("courseCatelogList",courseCatalogService.findByUser(true, null));
		model.addAttribute("course", course);
		return "modules/course/courseForm";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file) {
		return courseService.transferTo(file);
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "view")
	public String view(@RequestParam(required=false) Integer id,Course course, Model model,HttpServletRequest request) {
		
		
		if (null != id  && id>0){
			course = courseService.get(id);
		}
		if (course == null){
			course = new Course();
		}
		
		String courseCatalogId = (String)request.getParameter("course.courseCatalogId");
		if( StringUtils.isNotEmpty(courseCatalogId) ){
			
			CourseCatalog courseCatalog = courseCatalogService.get(Integer.parseInt(courseCatalogId));
			if(null != courseCatalog ){
				if(null != courseCatalog.getId()){
					course.setCourseCatalogId(courseCatalog.getId());
				}
				if(StringUtils.isNotEmpty(courseCatalog.getParentIds())){
					course.setCourseCatalogParentids(courseCatalog.getParentIds());
				}
				if(StringUtils.isNotEmpty(courseCatalog.getName())){
					course.setCourseCatalogName(courseCatalog.getName());
				}
			}
		}
		if(course != null) {
			model.addAttribute("fileAbsolutePath", courseService.getOriginalFilePath(course.getPath()));
		}
		model.addAttribute("fileAbsPath", courseService.getOriginalFilePath());
		
		model.addAttribute("courseCatelogList",courseCatalogService.findByUser(true, null));
		model.addAttribute("course", course);
		return "modules/course/courseView";
	}

	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "save")
	public String save(@RequestParam(required=false) Integer id,Course course, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		
		if (!beanValidator(model, course)){
			return form(id,course, model,request);
		}
		CommonsMultipartFile mFile = course.getFile();
		if(course.getCourseCatalogId()!=null)
		{
			course.setCourseCatalogName(courseCatalogService.get(course.getCourseCatalogId()).getName());
		}
		//取得当前上传文件的文件名称
		if(mFile!=null){
			String myFileName = mFile.getOriginalFilename();
			String FILE_PATH = request.getSession().getServletContext().getRealPath("/")+"WEB-INF\\file\\"+myFileName;
			//存文件
			File newFile = new File(FILE_PATH);
			try {
				mFile.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			course.setPath(FILE_PATH);
		}
		courseService.save(course);
		addMessage(redirectAttributes, "保存课件成功");
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage&course.courseCatalogParentids="+course.getCourseCatalogParentids()+"&course.courseCatalogId="+course.getCourseCatalogId();
		//return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "delete")
	public String delete(Course course, RedirectAttributes redirectAttributes) {
		courseService.delete(course);
		addMessage(redirectAttributes, "删除课件成功");
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage&course.courseCatalogParentids="+course.getCourseCatalogParentids()+"&course.courseCatalogId="+course.getCourseCatalogId();
		//return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}

}