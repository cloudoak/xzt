/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.web;

import java.util.Date;

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
import com.ambition.agile.modules.relax.entity.HeartCafe;
import com.ambition.agile.modules.relax.service.HeartCafeService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 心灵咖啡屋Controller
 * @author harry
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/relax/heartCafe")
public class HeartCafeController extends BaseController {

	@Autowired
	private HeartCafeService heartCafeService;
	
	@ModelAttribute
	public HeartCafe get(@RequestParam(required=false) Integer id) {
		HeartCafe entity = null;
		if (id!=null&&id>0){
			entity = heartCafeService.get(id);
		}
		if (entity == null){
			entity = new HeartCafe();
			User user = UserUtils.getUser(); 
			if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
				Integer orgId = user.getOffice().getId();
				entity.setOrgId(orgId);
				entity.setOrgName(user.getOffice().getName());
				
			}
			if(null != user && null != user.getName() ){
				entity.setCreatorId(user.getId());
				entity.setCreatorName(user.getName());
			}
			entity.setCreateDate( new Date());
		}
		return entity;
	}
	
	@RequiresPermissions("relax:heartCafe:view")
	@RequestMapping(value = {"list", ""})
	public String list(HeartCafe heartCafe, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HeartCafe> page = heartCafeService.findPage(new Page<HeartCafe>(request, response), heartCafe); 
		model.addAttribute("page", page);
		return "modules/relax/heartCafeList";
	}

	@RequiresPermissions("relax:heartCafe:view")
	@RequestMapping(value = "form")
	public String form(HeartCafe heartCafe, Model model) {
		model.addAttribute("heartCafe", heartCafe);
		return "modules/relax/heartCafeForm";
	}

	@RequiresPermissions("relax:heartCafe:edit")
	@RequestMapping(value = "save")
	public String save(HeartCafe heartCafe, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, heartCafe)){
			return form(heartCafe, model);
		}
		
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			heartCafe.setOrgId(orgId);
			heartCafe.setOrgName(user.getOffice().getName());
			
		}
		if(null != user && null != user.getName() ){
			heartCafe.setCreatorId(user.getId());
			heartCafe.setCreatorName(user.getName());
		}
		heartCafeService.save(heartCafe);
		addMessage(redirectAttributes, "保存心灵咖啡屋成功");
		return "redirect:"+Global.getAdminPath()+"/relax/heartCafe/?repage";
	}
	
	@RequiresPermissions("relax:heartCafe:edit")
	@RequestMapping(value = "delete")
	public String delete(HeartCafe heartCafe, RedirectAttributes redirectAttributes) {
		heartCafeService.delete(heartCafe);
		addMessage(redirectAttributes, "删除心灵咖啡屋成功");
		return "redirect:"+Global.getAdminPath()+"/relax/heartCafe/?repage";
	}

}