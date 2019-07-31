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
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.jzmk.entity.ScaleAslec;
import com.ambition.agile.modules.jzmk.service.ScaleAslecService;

/**
 * aslec量表解释Controller
 * @author wyz
 * @version 2017-11-26
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleAslec")
public class ScaleAslecController extends BaseController {

	@Autowired
	private ScaleAslecService scaleAslecService;
	
	@ModelAttribute
	public ScaleAslec get(@RequestParam(required=false) Integer id) {
		ScaleAslec entity = null;
		if (id!=null&&id>0){
			entity = scaleAslecService.get(id);
		}
		if (entity == null){
			entity = new ScaleAslec();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scaleAslec:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScaleAslec scaleAslec, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScaleAslec> page = scaleAslecService.findPage(new Page<ScaleAslec>(request, response), scaleAslec); 
		model.addAttribute("page", page);
		return "modules/jzmk/scaleAslecList";
	}

	@RequiresPermissions("jzmk:scaleAslec:view")
	@RequestMapping(value = "form")
	public String form(ScaleAslec scaleAslec, Model model) {
		model.addAttribute("scaleAslec", scaleAslec);
		return "modules/jzmk/scaleAslecForm";
	}

	@RequiresPermissions("jzmk:scaleAslec:edit")
	@RequestMapping(value = "save")
	public String save(ScaleAslec scaleAslec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleAslec)){
			return form(scaleAslec, model);
		}
		scaleAslecService.save(scaleAslec);
		addMessage(redirectAttributes, "保存aslec量表解释成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleAslec/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleAslec:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleAslec scaleAslec, RedirectAttributes redirectAttributes) {
		scaleAslecService.delete(scaleAslec);
		addMessage(redirectAttributes, "删除aslec量表解释成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleAslec/?repage";
	}

}