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
import com.ambition.agile.modules.counsel.entity.ScheduleDay;
import com.ambition.agile.modules.counsel.service.ScheduleDayService;

/**
 * 日程安排天Controller
 * @author harry
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/scheduleDay")
public class ScheduleDayController extends BaseController {

	@Autowired
	private ScheduleDayService scheduleDayService;
	
	@ModelAttribute
	public ScheduleDay get(@RequestParam(required=false) Integer id) {
		ScheduleDay entity = null;
		if (null != id && id >0){
			entity = scheduleDayService.get(id);
		}
		if (entity == null){
			entity = new ScheduleDay();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:scheduleDay:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScheduleDay scheduleDay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScheduleDay> page = scheduleDayService.findPage(new Page<ScheduleDay>(request, response), scheduleDay); 
		model.addAttribute("page", page);
		return "modules/counsel/scheduleDayList";
	}

	@RequiresPermissions("counsel:scheduleDay:view")
	@RequestMapping(value = "form")
	public String form(ScheduleDay scheduleDay, Model model) {
		model.addAttribute("scheduleDay", scheduleDay);
		return "modules/counsel/scheduleDayForm";
	}

	@RequiresPermissions("counsel:scheduleDay:edit")
	@RequestMapping(value = "save")
	public String save(ScheduleDay scheduleDay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scheduleDay)){
			return form(scheduleDay, model);
		}
		scheduleDayService.save(scheduleDay);
		addMessage(redirectAttributes, "保存日程安排天成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/scheduleDay/?repage";
	}
	
	@RequiresPermissions("counsel:scheduleDay:edit")
	@RequestMapping(value = "delete")
	public String delete(ScheduleDay scheduleDay, RedirectAttributes redirectAttributes) {
		scheduleDayService.delete(scheduleDay);
		addMessage(redirectAttributes, "删除日程安排天成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/scheduleDay/?repage";
	}

}