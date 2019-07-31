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
import com.ambition.agile.modules.jzmk.entity.OrgScale;
import com.ambition.agile.modules.jzmk.service.OrgScaleService;

/**
 * 分配给机构的量表Controller
 * @author dorstan
 * @version 2017-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/orgScale")
public class OrgScaleController extends BaseController {

	@Autowired
	private OrgScaleService orgScaleService;
	
	@ModelAttribute
	public OrgScale get(@RequestParam(required=false) Integer id) {
		OrgScale entity = null;
		if (id!=null&&id>0){
			entity = orgScaleService.get(id);
		}
		if (entity == null){
			entity = new OrgScale();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:orgScale:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrgScale orgScale, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgScale> page = orgScaleService.findPage(new Page<OrgScale>(request, response), orgScale); 
		model.addAttribute("page", page);
		return "modules/jzmk/orgScaleList";
	}

	@RequiresPermissions("jzmk:orgScale:view")
	@RequestMapping(value = "form")
	public String form(OrgScale orgScale, Model model) {
		model.addAttribute("orgScale", orgScale);
		return "modules/jzmk/orgScaleForm";
	}

	@RequiresPermissions("jzmk:orgScale:edit")
	@RequestMapping(value = "save")
	public String save(OrgScale orgScale, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orgScale)){
			return form(orgScale, model);
		}
		orgScaleService.save(orgScale);
		addMessage(redirectAttributes, "保存分配量表成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/orgScale/?repage";
	}
	
	@RequiresPermissions("jzmk:orgScale:edit")
	@RequestMapping(value = "delete")
	public String delete(OrgScale orgScale, RedirectAttributes redirectAttributes) {
		orgScaleService.delete(orgScale);
		addMessage(redirectAttributes, "删除分配量表成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/orgScale/?repage";
	}

}