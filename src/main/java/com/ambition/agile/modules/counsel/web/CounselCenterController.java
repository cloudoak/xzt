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
import com.ambition.agile.modules.counsel.entity.CounselCenter;
import com.ambition.agile.modules.counsel.service.CounselCenterService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 咨询中心Controller
 * @author harry
 * @version 2017-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselCenter")
public class CounselCenterController extends BaseController {

	@Autowired
	private CounselCenterService counselCenterService;
	
	@RequiresPermissions("counsel:counselCenter:edit")
	@RequestMapping(value = "edit")
	public String edit( HttpServletRequest request, HttpServletResponse response,Model model) {
		
		User user = UserUtils.getUser(); 
		CounselCenter counselCenter = new CounselCenter();
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			if(orgId>-1){
				counselCenter = counselCenterService.getCounselCenterByOrgId(orgId);
			}
			if(counselCenter != null) {
				counselCenter.setOrgId(orgId);
			}
		}
		model.addAttribute("counselCenter", counselCenter);
		return "modules/counsel/counselCenterForm";
	}
	
	@RequiresPermissions("counsel:counselCenter:edit")
	@RequestMapping(value = "save")
	public String save(CounselCenter counselCenter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselCenter)){
			return form(counselCenter, model);
		}
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			counselCenter.setOrgId(orgId);
		}
		
		counselCenterService.save(counselCenter);
		addMessage(redirectAttributes, "保存咨询中心成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselCenter/edit";
	}
	
	
	@ModelAttribute
	public CounselCenter get(@RequestParam(required=false) Integer id) {
		CounselCenter entity = null;
		if (id!=null&&id>0){
			entity = counselCenterService.get(id);
		}
		if (entity == null){
			entity = new CounselCenter();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselCenter:view")
	@RequestMapping(value = {"list", ""})
	public String list(CounselCenter counselCenter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CounselCenter> page = counselCenterService.findPage(new Page<CounselCenter>(request, response), counselCenter); 
		model.addAttribute("page", page);
		return "modules/counsel/counselCenterList";
	}

	@RequiresPermissions("counsel:counselCenter:view")
	@RequestMapping(value = "form")
	public String form(CounselCenter counselCenter, Model model) {
		model.addAttribute("counselCenter", counselCenter);
		return "modules/counsel/counselCenterForm";
	}

	
	
	@RequiresPermissions("counsel:counselCenter:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselCenter counselCenter, RedirectAttributes redirectAttributes) {
		counselCenterService.delete(counselCenter);
		addMessage(redirectAttributes, "删除咨询中心成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselCenter/?repage";
	}

}