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
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.counsel.entity.ScheduleRegular;
import com.ambition.agile.modules.counsel.service.ScheduleRegularService;

/**
 * 日程安排常规Controller
 * @author harry
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/scheduleRegular")
public class ScheduleRegularController extends BaseController {

	@Autowired
	private ScheduleRegularService scheduleRegularService;
	
	@ModelAttribute
	public ScheduleRegular get(@RequestParam(required=false) Integer id) {
		ScheduleRegular entity = null;
		if (null != id && id >0){
			entity = scheduleRegularService.get(id);
		}
		if (entity == null){
			entity = new ScheduleRegular();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:scheduleRegular:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScheduleRegular scheduleRegular, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScheduleRegular> page = scheduleRegularService.findPage(new Page<ScheduleRegular>(request, response), scheduleRegular); 
		model.addAttribute("page", page);
		return "modules/counsel/scheduleRegularList";
	}

	@RequiresPermissions("counsel:scheduleRegular:view")
	@RequestMapping(value = "form")
	public String form(ScheduleRegular scheduleRegular, Model model) {
		model.addAttribute("scheduleRegular", scheduleRegular);
		return "modules/counsel/scheduleRegularForm";
	}

	@RequiresPermissions("counsel:scheduleRegular:edit")
	@RequestMapping(value = "save")
	public String save(ScheduleRegular scheduleRegular, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scheduleRegular)){
			return form(scheduleRegular, model);
		}
		scheduleRegularService.save(scheduleRegular);
		addMessage(redirectAttributes, "保存日程安排常规成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/scheduleRegular/?repage";
	}
	
	@RequiresPermissions("counsel:scheduleRegular:edit")
	@RequestMapping(value = "delete")
	public String delete(ScheduleRegular scheduleRegular, RedirectAttributes redirectAttributes) {
		scheduleRegularService.delete(scheduleRegular);
		addMessage(redirectAttributes, "删除日程安排常规成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/scheduleRegular/?repage";
	}

}