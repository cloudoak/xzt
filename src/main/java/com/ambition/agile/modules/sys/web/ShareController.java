/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.sys.web;

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
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Share;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.ShareService;

/**
 * 共享积分Controller
 * @author shenclus
 * @version 2017-12-11
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/share")
public class ShareController extends BaseController {

	@Autowired
	private ShareService shareService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public Share get(@RequestParam(required=false) Integer id) {
		Share entity = null;
		if (id!=null&&id>0){
			entity = shareService.get(id);
		}
		if (entity == null){
			entity = new Share();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:share:view")
	@RequestMapping(value = {"list", ""})
	public String list(Share share, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Office> offices = officeService.findPage(new Page<Office>(request, response), new Office()); 
		model.addAttribute("offices", offices);
		
		Page<Share> page = shareService.findPage(new Page<Share>(request, response), share); 
		model.addAttribute("page", page);
		return "modules/sys/shareList";
	}

	@RequiresPermissions("sys:share:view")
	@RequestMapping(value = "form")
	public String form(Share share, Model model) {
		model.addAttribute("share", share);
		return "modules/sys/shareForm";
	}
	
	@RequiresPermissions("sys:share:view")
	@RequestMapping(value = "audit")
	public String audit(Share share, Model model) {
		model.addAttribute("share", share);
		return "modules/sys/shareAudit";
	}
	
	@RequiresPermissions("sys:share:view")
	@RequestMapping(value = "view")
	public String view(Share share, Model model) {
		model.addAttribute("share", share);
		return "modules/sys/shareView";
	}

	@RequiresPermissions("sys:share:edit")
	@RequestMapping(value = "save")
	public String save(Share share, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, share)){
			return form(share, model);
		}
		shareService.save(share);
		addMessage(redirectAttributes, "保存共享积分成功");
		return "redirect:"+Global.getAdminPath()+"/sys/share/?repage";
	}
	
	@RequiresPermissions("sys:share:edit")
	@RequestMapping(value = "delete")
	public String delete(Share share, RedirectAttributes redirectAttributes) {
		shareService.delete(share);
		addMessage(redirectAttributes, "删除共享积分成功");
		return "redirect:"+Global.getAdminPath()+"/sys/share/?repage";
	}

}