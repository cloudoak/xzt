/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.FactorValue;
import com.ambition.agile.modules.jzmk.entity.ScaleTotalExplain;
import com.ambition.agile.modules.jzmk.service.QuestionService;
import com.ambition.agile.modules.jzmk.service.ScaleTotalExplainService;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 量表总解释Controller
 * @author dortan
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleTotalExplain")
public class ScaleTotalExplainController extends BaseController {

	@Autowired
	private ScaleTotalExplainService scaleTotalExplainService;
	
	@Autowired
	private QuestionService questionService;
	
	@ModelAttribute
	public ScaleTotalExplain get(@RequestParam(required=false) Integer id) {
		ScaleTotalExplain entity = null;
		if (id!=null&&id>0){
			entity = scaleTotalExplainService.get(id);
		}
		if (entity == null){
			entity = new ScaleTotalExplain();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scaleTotalExplain:view")
	@RequestMapping(value = "list")
	public String list(ScaleTotalExplain scaleTotalExplain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScaleTotalExplain> page = scaleTotalExplainService.findPage(new Page<ScaleTotalExplain>(request, response), scaleTotalExplain); 
		model.addAttribute("page", page);
		model.addAttribute("tid", scaleTotalExplain.getTid());
		return "modules/jzmk/scaleTotalExplainList";
	}
	
	@RequestMapping("checkMaxValue")
	@ResponseBody
	public int checkMaxValue(BigDecimal maxValue, Integer tid){
		BigDecimal totalScores = questionService.findTotalScores(tid);
		if(totalScores.compareTo(maxValue) < 0) {
			return 0;
		}
		return 1;
	}

	@RequiresPermissions("jzmk:scaleTotalExplain:view")
	@RequestMapping(value = "form")
	public String form(ScaleTotalExplain scaleTotalExplain, Model model) {
		model.addAttribute("scaleTotalExplain", scaleTotalExplain);
		model.addAttribute("tid", scaleTotalExplain.getTid());
		return "modules/jzmk/scaleTotalExplainForm";
	}

	@RequiresPermissions("jzmk:scaleTotalExplain:edit")
	@RequestMapping(value = "save")
	public String save(ScaleTotalExplain scaleTotalExplain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleTotalExplain)){
			return form(scaleTotalExplain, model);
		}
		String content = StringFunctionUtils.replaceWebJsp(scaleTotalExplain.getExplainContent());
		scaleTotalExplain.setExplainContent(content);
		scaleTotalExplainService.save(scaleTotalExplain);
		addMessage(redirectAttributes, "保存量表总解释成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleTotalExplain/list?tid="+scaleTotalExplain.getTid();
	}
	
	@RequiresPermissions("jzmk:scaleTotalExplain:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleTotalExplain scaleTotalExplain, RedirectAttributes redirectAttributes) {
		scaleTotalExplainService.delete(scaleTotalExplain);
		addMessage(redirectAttributes, "删除量表总解释成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleTotalExplain/list?tid="+scaleTotalExplain.getTid();
	}

}