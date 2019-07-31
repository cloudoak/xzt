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
import com.ambition.agile.modules.jzmk.entity.ScaleCheckScore;
import com.ambition.agile.modules.jzmk.service.ScaleCheckScoreService;

/**
 * 测评得分Controller
 * @author wyz
 * @version 2017-11-23
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleCheckScore")
public class ScaleCheckScoreController extends BaseController {

	@Autowired
	private ScaleCheckScoreService scaleCheckScoreService;
	
	@ModelAttribute
	public ScaleCheckScore get(@RequestParam(required=false) Integer id) {
		ScaleCheckScore entity = null;
		if (id!=null&&id>0){
			entity = scaleCheckScoreService.get(id);
		}
		if (entity == null){
			entity = new ScaleCheckScore();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scaleCheckScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScaleCheckScore scaleCheckScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScaleCheckScore> page = scaleCheckScoreService.findPage(new Page<ScaleCheckScore>(request, response), scaleCheckScore); 
		model.addAttribute("page", page);
		return "modules/jzmk/scaleCheckScoreList";
	}

	@RequiresPermissions("jzmk:scaleCheckScore:view")
	@RequestMapping(value = "form")
	public String form(ScaleCheckScore scaleCheckScore, Model model) {
		model.addAttribute("scaleCheckScore", scaleCheckScore);
		return "modules/jzmk/scaleCheckScoreForm";
	}

	@RequiresPermissions("jzmk:scaleCheckScore:edit")
	@RequestMapping(value = "save")
	public String save(ScaleCheckScore scaleCheckScore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleCheckScore)){
			return form(scaleCheckScore, model);
		}
		scaleCheckScoreService.save(scaleCheckScore);
		addMessage(redirectAttributes, "保存测评得分成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleCheckScore/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleCheckScore:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleCheckScore scaleCheckScore, RedirectAttributes redirectAttributes) {
		scaleCheckScoreService.delete(scaleCheckScore);
		addMessage(redirectAttributes, "删除测评得分成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleCheckScore/?repage";
	}

}