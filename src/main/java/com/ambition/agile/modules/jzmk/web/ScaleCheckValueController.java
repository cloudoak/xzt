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
import com.ambition.agile.modules.jzmk.entity.ScaleCheckValue;
import com.ambition.agile.modules.jzmk.service.ScaleCheckValueService;

/**
 * 测评评分Controller
 * @author wyz
 * @version 2017-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleCheckValue")
public class ScaleCheckValueController extends BaseController {

	@Autowired
	private ScaleCheckValueService scaleCheckValueService;
	
	@ModelAttribute
	public ScaleCheckValue get(@RequestParam(required=false) Integer id) {
		ScaleCheckValue entity = null;
		if (id!=null&&id>0){
			entity = scaleCheckValueService.get(id);
		}
		if (entity == null){
			entity = new ScaleCheckValue();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scaleCheckValue:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScaleCheckValue scaleCheckValue, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScaleCheckValue> page = scaleCheckValueService.findPage(new Page<ScaleCheckValue>(request, response), scaleCheckValue); 
		model.addAttribute("page", page);
		return "modules/jzmk/scaleCheckValueList";
	}

	@RequiresPermissions("jzmk:scaleCheckValue:view")
	@RequestMapping(value = "form")
	public String form(ScaleCheckValue scaleCheckValue, Model model) {
		model.addAttribute("scaleCheckValue", scaleCheckValue);
		return "modules/jzmk/scaleCheckValueForm";
	}

	@RequiresPermissions("jzmk:scaleCheckValue:edit")
	@RequestMapping(value = "save")
	public String save(ScaleCheckValue scaleCheckValue, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleCheckValue)){
			return form(scaleCheckValue, model);
		}
		scaleCheckValueService.save(scaleCheckValue);
		addMessage(redirectAttributes, "保存测评评分成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleCheckValue/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleCheckValue:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleCheckValue scaleCheckValue, RedirectAttributes redirectAttributes) {
		scaleCheckValueService.delete(scaleCheckValue);
		addMessage(redirectAttributes, "删除测评评分成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleCheckValue/?repage";
	}

}