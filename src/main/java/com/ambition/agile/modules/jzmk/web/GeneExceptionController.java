/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

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
import com.ambition.agile.modules.jzmk.entity.GeneException;
import com.ambition.agile.modules.jzmk.service.GeneExceptionService;

/**
 * 异常条件规则Controller
 * @author OAK
 * @version 2017-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/geneException")
public class GeneExceptionController extends BaseController {

	@Autowired
	private GeneExceptionService geneExceptionService;
	
	@ModelAttribute
	public GeneException get(@RequestParam(required=false) Integer id) {
		GeneException entity = null;
		if (id != null){
			entity = geneExceptionService.get(id);
		}
		if (entity == null){
			entity = new GeneException();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:geneException:view")
	@RequestMapping(value = {"list", ""})
	public String list(GeneException geneException, Integer gid, Integer tid, HttpServletRequest request, HttpServletResponse response, Model model) {
		geneException.setGid(gid);
		Page<GeneException> page = geneExceptionService.findPage(new Page<GeneException>(request, response), geneException); 
		model.addAttribute("page", page);
		model.addAttribute("gid", gid);
		model.addAttribute("tid", tid);
		return "modules/jzmk/geneExceptionList";
	}

	@RequiresPermissions("jzmk:geneException:view")
	@RequestMapping(value = "form")
	public String form(GeneException geneException, Integer gid, Integer tid, Model model) {
		model.addAttribute("geneException", geneException);
		model.addAttribute("gid", gid);
		model.addAttribute("tid", tid);
		return "modules/jzmk/geneExceptionForm";
	}

	@RequiresPermissions("jzmk:geneException:edit")
	@RequestMapping(value = "save")
	public String save(GeneException geneException, Integer gid, Integer tid, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, geneException)){
			return form(geneException, gid, tid, model);
		}
		geneExceptionService.save(geneException);
		addMessage(redirectAttributes, "保存异常条件规则成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/geneException/list?gid=" + gid + "&tid="+tid;
	}
	
	@RequiresPermissions("jzmk:geneException:edit")
	@RequestMapping(value = "delete")
	public String delete(GeneException geneException, Integer gid, Integer tid, Model model, RedirectAttributes redirectAttributes) {
		geneExceptionService.delete(geneException);
		addMessage(redirectAttributes, "删除异常条件规则成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/geneException/list?gid=" + gid + "&tid="+tid;
	}

}