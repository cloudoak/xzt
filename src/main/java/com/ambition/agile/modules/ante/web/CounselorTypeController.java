/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
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
import com.ambition.agile.modules.ante.entity.CounselorType;
import com.ambition.agile.modules.ante.entity.Department;
import com.ambition.agile.modules.ante.service.CounselorTypeService;
import com.ambition.agile.modules.ante.service.DepartmentService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 教师类型Controller
 * @author wyz
 * @version 2017-09-07
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/counselorType")
public class CounselorTypeController extends BaseController {

	@Autowired
	private CounselorTypeService counselorTypeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	public OrganizationService organizationService;
	
	@ModelAttribute
	public CounselorType get(@RequestParam(required=false) Integer id) {
		CounselorType entity = null;
		if (id!=null&&id>0){
			entity = counselorTypeService.get(id);
		}
		if (entity == null){
			entity = new CounselorType();
		}
		return entity;
	}
	
	@RequiresPermissions("ante:counselorType:view")
	@RequestMapping(value = {"list", ""})
	public String list(CounselorType counselorType, HttpServletRequest request, HttpServletResponse response, Model model) {
		counselorType.setOrganization(organizationService.getOrganizationJoinString());
		Page<CounselorType> page = counselorTypeService.findPage(new Page<CounselorType>(request, response), counselorType); 
		model.addAttribute("page", page);
		Page<Department> departments = departmentService.findPage(new Page<Department>(request, response), new Department()); 
		model.addAttribute("departments", departments);
		return "modules/ante/counselorTypeList";
	}

	@RequiresPermissions(value={"ante:counselorType:view","ante:counselorType:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(CounselorType counselorType, Model model) {
		model.addAttribute("counselorType", counselorType);
		return "modules/ante/counselorTypeForm";
	}
	
	@RequiresPermissions("ante:counselorType:view")
	@RequestMapping(value = "view")
	public String view(CounselorType counselorType, Model model) {
		model.addAttribute("counselorType", counselorType);
		return "modules/ante/counselorTypeView";
	}

	@RequiresPermissions("ante:counselorType:edit")
	@RequestMapping(value = "save")
	public String save(CounselorType counselorType, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		Integer orgId = user.getOffice().getId();
		counselorType.setOrgId(orgId);
		if(counselorType.getId()!=null) {
			counselorType.setUpdateBy(user);
			counselorType.setUpdateDate(new Date());
			counselorType.setIsNewRecord(false);
		}else {
			counselorType.setCreateBy(user);
			counselorType.setCreateDate(new Date());
			counselorType.setDelFlag("0");
			counselorType.setIsNewRecord(true);
		}
		if (!beanValidator(model, counselorType)){
			return form(counselorType, model);
		}
		
		counselorTypeService.save(counselorType);
		addMessage(redirectAttributes, "保存教师类型成功");
		return "redirect:"+Global.getAdminPath()+"/ante/counselorType/?list";
	}
	
	@RequiresPermissions("ante:counselorType:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselorType counselorType, RedirectAttributes redirectAttributes) {
		counselorTypeService.delete(counselorType);
		addMessage(redirectAttributes, "删除教师类型成功");
		return "redirect:"+Global.getAdminPath()+"/ante/counselorType/?repage";
	}

}