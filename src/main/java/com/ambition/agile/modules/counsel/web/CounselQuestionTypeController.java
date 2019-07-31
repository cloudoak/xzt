/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.web;


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
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.counsel.entity.CounselQuestionType;
import com.ambition.agile.modules.counsel.service.CounselQuestionTypeService;

/**
 * 咨询问题类型Controller
 * @author OAK
 * @version 2017/12/02
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselQuestionType")
public class CounselQuestionTypeController extends BaseController {

	@Autowired
	private CounselQuestionTypeService counselQuestionTypeService;
	
	@ModelAttribute
	public CounselQuestionType get(@RequestParam(required=false) Integer id) {
		CounselQuestionType entity = null;
		if (id!=null&&id>0){
			entity = counselQuestionTypeService.get(id);
		}
		if (entity == null){
			entity = new CounselQuestionType();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselQuestionType:view")
	@RequestMapping(value = {"list", ""})
	public String list(CounselQuestionType counselQuestionType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CounselQuestionType> page = counselQuestionTypeService.findPage(new Page<CounselQuestionType>(request, response), counselQuestionType); 
		model.addAttribute("page", page);
		return "modules/counsel/counselQuestionTypeList";
	}

	@RequiresPermissions("counsel:counselQuestionType:view")
	@RequestMapping(value = "form")
	public String form(CounselQuestionType counselQuestionType, Model model) {
		model.addAttribute("counselQuestionType", counselQuestionType);
		return "modules/counsel/counselQuestionTypeForm";
	}

	@RequiresPermissions("counsel:counselQuestionType:edit")
	@RequestMapping(value = "save")
	public String save(CounselQuestionType counselQuestionType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselQuestionType)){
			return form(counselQuestionType, model);
		}
		counselQuestionTypeService.save(counselQuestionType);
		addMessage(redirectAttributes, "保存咨询问题类型成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselQuestionType/?repage";
	}
	
	@RequiresPermissions("counsel:counselQuestionType:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselQuestionType counselQuestionType, RedirectAttributes redirectAttributes) {
		counselQuestionTypeService.delete(counselQuestionType);
		addMessage(redirectAttributes, "删除咨询问题类型成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselQuestionType/?repage";
	}

}