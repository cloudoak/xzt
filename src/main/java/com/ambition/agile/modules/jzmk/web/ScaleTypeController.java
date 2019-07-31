/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

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
import com.ambition.agile.modules.jzmk.entity.ScaleType;
import com.ambition.agile.modules.jzmk.service.ScaleTypeService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 量表类别Controller
 * @author OAK
 * @version 2017-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleType")
public class ScaleTypeController extends BaseController {

	@Autowired
	private ScaleTypeService scaleTypeService;
	
	@ModelAttribute
	public ScaleType get(@RequestParam(required=false) Integer id) {
		ScaleType entity = null;
		if (id!=null&&id>0){
			entity = scaleTypeService.get(id);
		}
		if (entity == null){
			entity = new ScaleType();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scaleType:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScaleType scaleType, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		ScaleType nscaleType = new ScaleType();
		nscaleType.setOrgId(user.getCompany().getId());
		Page<ScaleType> page = scaleTypeService.findPage(new Page<ScaleType>(request, response), nscaleType); 
		model.addAttribute("page", page);
		return "modules/jzmk/scaleTypeList";
	}

	@RequiresPermissions("jzmk:scaleType:view")
	@RequestMapping(value = "form")
	public String form(ScaleType scaleType, Model model) {
		model.addAttribute("scaleType", scaleType);
		return "modules/jzmk/scaleTypeForm";
	}

	@RequiresPermissions("jzmk:scaleType:edit")
	@RequestMapping(value = "save")
	public String save(ScaleType scaleType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleType)){
			return form(scaleType, model);
		}
		//设置类型所属机构
		User user = UserUtils.getUser();
		scaleType.setOrgId(user.getCompany().getId());
		scaleTypeService.save(scaleType);
		addMessage(redirectAttributes, "保存量表类型成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleType/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleType:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleType scaleType, RedirectAttributes redirectAttributes) {
		scaleTypeService.delete(scaleType);
		addMessage(redirectAttributes, "删除量表类型成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleType/?repage";
	}

}