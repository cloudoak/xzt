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
import com.ambition.agile.modules.jzmk.entity.CircleRecord;
import com.ambition.agile.modules.jzmk.service.CircleRecordService;

/**
 * 圈子记录Controller
 * @author dortan
 * @version 2017-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/circleRecord")
public class CircleRecordController extends BaseController {

	@Autowired
	private CircleRecordService circleRecordService;
	
	@ModelAttribute
	public CircleRecord get(@RequestParam(required=false) Integer id) {
		CircleRecord entity = null;
		if (id!=null&&id>0){
			entity = circleRecordService.get(id);
		}
		if (entity == null){
			entity = new CircleRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:circleRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(CircleRecord circleRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CircleRecord> page = circleRecordService.findPage(new Page<CircleRecord>(request, response), circleRecord); 
		model.addAttribute("page", page);
		return "modules/jzmk/circleRecordList";
	}

	@RequiresPermissions("jzmk:circleRecord:view")
	@RequestMapping(value = "form")
	public String form(CircleRecord circleRecord, Model model) {
		model.addAttribute("circleRecord", circleRecord);
		return "modules/jzmk/circleRecordForm";
	}

	@RequiresPermissions("jzmk:circleRecord:edit")
	@RequestMapping(value = "save")
	public String save(CircleRecord circleRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, circleRecord)){
			return form(circleRecord, model);
		}
		circleRecordService.save(circleRecord);
		addMessage(redirectAttributes, "保存评论回复成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/circleRecord/?repage";
	}
	
	@RequiresPermissions("jzmk:circleRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(CircleRecord circleRecord, RedirectAttributes redirectAttributes) {
		circleRecordService.delete(circleRecord);
		addMessage(redirectAttributes, "删除评论回复成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/circleRecord/?repage";
	}

}