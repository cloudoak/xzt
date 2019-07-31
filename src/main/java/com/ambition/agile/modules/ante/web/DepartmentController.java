/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.ante.entity.Department;
import com.ambition.agile.modules.ante.service.DepartmentService;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 部门管理Controller
 * @author whq
 * @version 2017-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/department")
public class DepartmentController extends BaseController {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	public OrganizationService organizationService;
	
	@ModelAttribute
	public Department get(@RequestParam(required=false) Integer id) {
		Department entity = null;
		if (id!=null&&id>0){
			entity = departmentService.get(id);
		}
		if (entity == null){
			entity = new Department();
		}
		return entity;
	}
	
//	@ModelAttribute
//	public Office get(@RequestParam(required=false) Integer id) {
//		Office entity = null;
//		if (id!=null&&id>0){
//			entity = officeService.get(id);
//		}
//		if (entity == null){
//			entity = new Office();
//		}
//		return entity;
//	}
	
	@RequiresPermissions("ante:department:view")
	@RequestMapping(value = {"list", ""})
	public String list(Department department, Office office, HttpServletRequest request, HttpServletResponse response, Model model) {
		department.setOrganization(organizationService.getOrganizationJoinString());
		Page<Department> page = departmentService.findPage(new Page<Department>(request, response), department); 
		model.addAttribute("page", page);
		return "modules/ante/departmentList";
	}

	@RequiresPermissions("ante:department:view")
	@RequestMapping(value = "form")
	public String form(Department department, Model model) {
		model.addAttribute("department", department);
		return "modules/ante/departmentForm";
	}

	@RequiresPermissions("ante:department:edit")
	@RequestMapping(value = "save")
	public String save(Department department, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		Integer orgId = user.getOffice().getId();//机构号
		department.setOrgId(orgId);
		if(department.getId()!=null) {
			department.setUpdateBy(user);
			department.setUpdateDate(new Date());
			department.setIsNewRecord(false);
		}else {
			department.setCreateBy(user);
			department.setCreateDate(new Date());
			department.setDelFlag("0");
			department.setIsNewRecord(true);
		}
		if (!beanValidator(model, department)){
			return form(department, model);
		}
		departmentService.save(department);
		addMessage(redirectAttributes, "保存部门管理成功");
		return "redirect:"+Global.getAdminPath()+"/ante/department/?repage";
	}
	
	@RequiresPermissions("ante:department:edit")
	@RequestMapping(value = "delete")
	public String delete(Department department, RedirectAttributes redirectAttributes) {
		departmentService.delete(department);
		addMessage(redirectAttributes, "删除部门管理成功");
		return "redirect:"+Global.getAdminPath()+"/ante/department/?repage";
	}

}