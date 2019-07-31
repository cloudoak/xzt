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
import com.ambition.agile.modules.relax.entity.QuickSay;
import com.ambition.agile.modules.relax.service.QuickSayService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 一吐为快Controller
 * @author harry
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/relax/quickSay")
public class QuickSayController extends BaseController {

	@Autowired
	private QuickSayService quickSayService;
	
	@ModelAttribute
	public QuickSay get(@RequestParam(required=false) Integer id) {
		QuickSay entity = null;
		if (id!=null&&id>0){
			entity = quickSayService.get(id);
		}
		if (entity == null){
			entity = new QuickSay();
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
	
	@RequiresPermissions("relax:quickSay:view")
	@RequestMapping(value = {"list", ""})
	public String list(QuickSay quickSay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QuickSay> page = quickSayService.findPage(new Page<QuickSay>(request, response), quickSay); 
		model.addAttribute("page", page);
		return "modules/relax/quickSayList";
	}

	@RequiresPermissions("relax:quickSay:view")
	@RequestMapping(value = "form")
	public String form(QuickSay quickSay, Model model) {
		model.addAttribute("quickSay", quickSay);
		return "modules/relax/quickSayForm";
	}

	@RequiresPermissions("relax:quickSay:edit")
	@RequestMapping(value = "save")
	public String save(QuickSay quickSay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, quickSay)){
			return form(quickSay, model);
		}
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			quickSay.setOrgId(orgId);
			quickSay.setOrgName(user.getOffice().getName());
			
		}
		if(null != user && null != user.getName() ){
			quickSay.setCreatorId(user.getId());
			quickSay.setCreatorName(user.getName());
		}
		quickSayService.save(quickSay);
		addMessage(redirectAttributes, "保存一吐为快成功");
		return "redirect:"+Global.getAdminPath()+"/relax/quickSay/?repage";
	}
	
	@RequiresPermissions("relax:quickSay:edit")
	@RequestMapping(value = "delete")
	public String delete(QuickSay quickSay, RedirectAttributes redirectAttributes) {
		quickSayService.delete(quickSay);
		addMessage(redirectAttributes, "删除一吐为快成功");
		return "redirect:"+Global.getAdminPath()+"/relax/quickSay/?repage";
	}

}