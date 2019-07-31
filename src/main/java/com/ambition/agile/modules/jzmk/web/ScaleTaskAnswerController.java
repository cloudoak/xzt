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
import com.ambition.agile.modules.jzmk.entity.ScaleTaskAnswer;
import com.ambition.agile.modules.jzmk.service.ScaleTaskAnswerService;

/**
 * 测评人员答题Controller
 * @author dortan
 * @version 2017-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleTaskAnswer")
public class ScaleTaskAnswerController extends BaseController {

	@Autowired
	private ScaleTaskAnswerService scaleTaskAnswerService;
	
	@ModelAttribute
	public ScaleTaskAnswer get(@RequestParam(required=false) Integer id) {
		ScaleTaskAnswer entity = null;
		if (id!=null&&id>0){
			entity = scaleTaskAnswerService.get(id);
		}
		if (entity == null){
			entity = new ScaleTaskAnswer();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scaleTaskAnswer:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScaleTaskAnswer scaleTaskAnswer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScaleTaskAnswer> page = scaleTaskAnswerService.findPage(new Page<ScaleTaskAnswer>(request, response), scaleTaskAnswer); 
		model.addAttribute("page", page);
		return "modules/jzmk/scaleTaskAnswerList";
	}

	@RequiresPermissions("jzmk:scaleTaskAnswer:view")
	@RequestMapping(value = "form")
	public String form(ScaleTaskAnswer scaleTaskAnswer, Model model) {
		model.addAttribute("scaleTaskAnswer", scaleTaskAnswer);
		return "modules/jzmk/scaleTaskAnswerForm";
	}

	@RequiresPermissions("jzmk:scaleTaskAnswer:edit")
	@RequestMapping(value = "save")
	public String save(ScaleTaskAnswer scaleTaskAnswer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleTaskAnswer)){
			return form(scaleTaskAnswer, model);
		}
		scaleTaskAnswerService.save(scaleTaskAnswer);
		addMessage(redirectAttributes, "保存答题信息成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleTaskAnswer/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleTaskAnswer:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleTaskAnswer scaleTaskAnswer, RedirectAttributes redirectAttributes) {
		scaleTaskAnswerService.delete(scaleTaskAnswer);
		addMessage(redirectAttributes, "删除答题信息成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleTaskAnswer/?repage";
	}

}