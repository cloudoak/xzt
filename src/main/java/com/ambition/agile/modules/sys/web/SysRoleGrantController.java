/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.sys.web;

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
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.sys.entity.Menu;
import com.ambition.agile.modules.sys.entity.SysRoleGrant;
import com.ambition.agile.modules.sys.service.SysRoleGrantService;
import com.ambition.agile.modules.sys.service.SystemService;

/**
 * 角色权限Controller
 * @author dortan
 * @version 2017-08-28
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysRoleGrant")
public class SysRoleGrantController extends BaseController {

	@Autowired
	private SysRoleGrantService sysRoleGrantService;
	
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public SysRoleGrant get(@RequestParam(required=false) Integer id) {
		SysRoleGrant entity = null;
		if (id!=null&&id>0){
			entity = sysRoleGrantService.get(id);
		}
		if (entity == null){
			entity = new SysRoleGrant();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysRoleGrant:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysRoleGrant sysRoleGrant, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询所有的权限资源
		List<Menu> sourcelist = systemService.findAllMenu();
		//查询已存在的所有权限资源
		/*sysRoleGrant.setDelFlag("0");
		List<SysRoleGrant> list = sysRoleGrantService.findList(sysRoleGrant); 
		model.addAttribute("list", list);*/
		model.addAttribute("sourcelist",sourcelist);
		return "modules/sys/sysRoleGrantList";
	}
	
	@RequiresPermissions("sys:sysRoleGrant:view")
	@RequestMapping(value = "form")
	public String form(SysRoleGrant sysRoleGrant, Model model) {
		model.addAttribute("sysRoleGrant", sysRoleGrant);
		return "modules/sys/sysRoleGrantForm";
	}

	@RequiresPermissions("sys:sysRoleGrant:edit")
	@RequestMapping(value = "save")
	public String save(SysRoleGrant sysRoleGrant, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysRoleGrant)){
			return form(sysRoleGrant, model);
		}
		sysRoleGrantService.save(sysRoleGrant);
		addMessage(redirectAttributes, "保存权限成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysRoleGrant/?repage";
	}
	
	@RequiresPermissions("sys:sysRoleGrant:edit")
	@RequestMapping(value = "delete")
	public String delete(SysRoleGrant sysRoleGrant, RedirectAttributes redirectAttributes) {
		sysRoleGrantService.delete(sysRoleGrant);
		addMessage(redirectAttributes, "删除权限成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysRoleGrant/?repage";
	}

}