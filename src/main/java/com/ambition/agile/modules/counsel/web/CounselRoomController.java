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
import com.ambition.agile.modules.counsel.entity.CounselRoom;
import com.ambition.agile.modules.counsel.service.CounselRoomService;

/**
 * 咨询室Controller
 * @author harry
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselRoom")
public class CounselRoomController extends BaseController {

	@Autowired
	private CounselRoomService counselRoomService;
	
	@ModelAttribute
	public CounselRoom get(@RequestParam(required=false) Integer id) {
		CounselRoom entity = null;
		if (id!=null&&id>0){
			entity = counselRoomService.get(id);
		}
		if (entity == null){
			entity = new CounselRoom();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselRoom:view")
	@RequestMapping(value = {"list", ""})
	public String list(CounselRoom counselRoom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CounselRoom> page = counselRoomService.findPage(new Page<CounselRoom>(request, response), counselRoom); 
		model.addAttribute("page", page);
		return "modules/counsel/counselRoomList";
	}

	@RequiresPermissions("counsel:counselRoom:view")
	@RequestMapping(value = "form")
	public String form(CounselRoom counselRoom, Model model) {
		model.addAttribute("counselRoom", counselRoom);
		return "modules/counsel/counselRoomForm";
	}

	@RequiresPermissions("counsel:counselRoom:edit")
	@RequestMapping(value = "save")
	public String save(CounselRoom counselRoom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselRoom)){
			return form(counselRoom, model);
		}
		counselRoomService.save(counselRoom);
		addMessage(redirectAttributes, "保存咨询室成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselRoom/?repage";
	}
	
	@RequiresPermissions("counsel:counselRoom:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselRoom counselRoom, RedirectAttributes redirectAttributes) {
		counselRoomService.delete(counselRoom);
		addMessage(redirectAttributes, "删除咨询室成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselRoom/?repage";
	}

}