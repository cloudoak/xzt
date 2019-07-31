/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.web;

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
import com.ambition.agile.modules.relax.entity.IntegralUser;
import com.ambition.agile.modules.relax.service.IntegralUserService;

/**
 * 用户积分Controller
 * @author harry
 * @version 2017-09-04
 */
@Controller
@RequestMapping(value = "${adminPath}/relax/integralUser")
public class IntegralUserController extends BaseController {

	@Autowired
	private IntegralUserService integralUserService;
	
	@ModelAttribute
	public IntegralUser get(@RequestParam(required=false) Integer id) {
		IntegralUser entity = null;
		if (null != id && id>0){
			entity = integralUserService.get(id);
		}
		if (entity == null){
			entity = new IntegralUser();
		}
		return entity;
	}
	
	@RequiresPermissions("relax:integralUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(IntegralUser integralUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<IntegralUser> page = integralUserService.findPage(new Page<IntegralUser>(request, response), integralUser); 
		model.addAttribute("page", page);
		return "modules/relax/integralUserList";
	}

	@RequiresPermissions("relax:integralUser:view")
	@RequestMapping(value = "form")
	public String form(IntegralUser integralUser, Model model) {
		model.addAttribute("integralUser", integralUser);
		return "modules/relax/integralUserForm";
	}

	@RequiresPermissions("relax:integralUser:edit")
	@RequestMapping(value = "save")
	public String save(IntegralUser integralUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, integralUser)){
			return form(integralUser, model);
		}
		integralUserService.save(integralUser);
		addMessage(redirectAttributes, "保存integralUser成功");
		return "redirect:"+Global.getAdminPath()+"/relax/integralUser/?repage";
	}
	
	@RequiresPermissions("relax:integralUser:edit")
	@RequestMapping(value = "delete")
	public String delete(IntegralUser integralUser, RedirectAttributes redirectAttributes) {
		integralUserService.delete(integralUser);
		addMessage(redirectAttributes, "删除integralUser成功");
		return "redirect:"+Global.getAdminPath()+"/relax/integralUser/?repage";
	}

}