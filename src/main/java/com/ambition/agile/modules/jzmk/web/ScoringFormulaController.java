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
import com.ambition.agile.modules.jzmk.entity.ScoringFormula;
import com.ambition.agile.modules.jzmk.service.ScoringFormulaService;

/**
 * 计分方式Controller
 * @author dortan
 * @version 2017-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scoringFormula")
public class ScoringFormulaController extends BaseController {

	@Autowired
	private ScoringFormulaService scoringFormulaService;
	
	@ModelAttribute
	public ScoringFormula get(@RequestParam(required=false) Integer id) {
		ScoringFormula entity = null;
		if (id!=null&&id>0){
			entity = scoringFormulaService.get(id);
		}
		if (entity == null){
			entity = new ScoringFormula();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scoringFormula:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScoringFormula scoringFormula, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScoringFormula> page = scoringFormulaService.findPage(new Page<ScoringFormula>(request, response), scoringFormula); 
		model.addAttribute("page", page);
		return "modules/jzmk/scoringFormulaList";
	}

	@RequiresPermissions("jzmk:scoringFormula:view")
	@RequestMapping(value = "form")
	public String form(ScoringFormula scoringFormula, Model model) {
		model.addAttribute("scoringFormula", scoringFormula);
		return "modules/jzmk/scoringFormulaForm";
	}

	@RequiresPermissions("jzmk:scoringFormula:edit")
	@RequestMapping(value = "save")
	public String save(ScoringFormula scoringFormula, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scoringFormula)){
			return form(scoringFormula, model);
		}
		scoringFormulaService.save(scoringFormula);
		addMessage(redirectAttributes, "保存计分方式成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scoringFormula/?repage";
	}
	
	@RequiresPermissions("jzmk:scoringFormula:edit")
	@RequestMapping(value = "delete")
	public String delete(ScoringFormula scoringFormula, RedirectAttributes redirectAttributes) {
		scoringFormulaService.delete(scoringFormula);
		addMessage(redirectAttributes, "删除计分方式成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scoringFormula/?repage";
	}

}