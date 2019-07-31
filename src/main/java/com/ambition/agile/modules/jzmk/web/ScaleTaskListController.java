/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.util.ArrayList;
import java.util.List;

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
import com.ambition.agile.modules.jzmk.entity.Scale;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskList;
import com.ambition.agile.modules.jzmk.service.ScaleService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskListService;

/**
 * 任务测试量表Controller
 * @author dortan
 * @version 2017-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleTaskList")
public class ScaleTaskListController extends BaseController {

	@Autowired
	private ScaleTaskListService scaleTaskListService;
	
	@Autowired
	private ScaleService scaleService;
	
	@ModelAttribute
	public ScaleTaskList get(@RequestParam(required=false) Integer id) {
		ScaleTaskList entity = null;
		if (id!=null&&id>0){
			entity = scaleTaskListService.get(id);
		}
		if (entity == null){
			entity = new ScaleTaskList();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scaleTaskList:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScaleTaskList scaleTaskList, HttpServletRequest request, HttpServletResponse response, Model model) {
		scaleTaskList.setTaskId(Integer.valueOf(request.getParameter("taskId")));
		scaleTaskList.setDelFlag("0");
		Page<ScaleTaskList> page = scaleTaskListService.findPage(new Page<ScaleTaskList>(request, response), scaleTaskList); 
		model.addAttribute("page", page);
		Scale scale=new Scale();
		scale.setDelFlag("0");
		List<Scale> scales = new ArrayList<Scale>();
		for(ScaleTaskList item:page.getList())
		{
			scales.add(scaleService.get(item.getGeneId()));
		}
		model.addAttribute("scales", scales);
		return "modules/jzmk/scaleTaskListList";
	}

	@RequiresPermissions("jzmk:scaleTaskList:view")
	@RequestMapping(value = "form")
	public String form(ScaleTaskList scaleTaskList, Model model) {
		model.addAttribute("scaleTaskList", scaleTaskList);
		return "modules/jzmk/scaleTaskListForm";
	}

	@RequiresPermissions("jzmk:scaleTaskList:edit")
	@RequestMapping(value = "save")
	public String save(ScaleTaskList scaleTaskList, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleTaskList)){
			return form(scaleTaskList, model);
		}
		scaleTaskListService.save(scaleTaskList);
		addMessage(redirectAttributes, "保存任务测试量表成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleTaskList/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleTaskList:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleTaskList scaleTaskList, RedirectAttributes redirectAttributes) {
		scaleTaskListService.delete(scaleTaskList);
		addMessage(redirectAttributes, "删除任务测试量表成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleTaskList/?repage";
	}

}