/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.web;

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
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.ante.entity.HappyMirror;
import com.ambition.agile.modules.ante.service.HappyMirrorService;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.service.OfficeService;

/**
 * 幸福镜子Controller
 * @author whq
 * @version 2017-09-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/happyMirror")
public class HappyMirrorController extends BaseController {

	@Autowired
	private HappyMirrorService happyMirrorService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public HappyMirror get(@RequestParam(required=false) Integer id) {
		HappyMirror entity = null;
		if (id!=null&&id>0){
			entity = happyMirrorService.get(id);
		}
		if (entity == null){
			entity = new HappyMirror();
		}
		return entity;
	}
	
	@RequiresPermissions("ante:happyMirror:view")
	@RequestMapping(value = {"list", ""})
	public String list(HappyMirror happyMirror, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HappyMirror> page = happyMirrorService.findPage(new Page<HappyMirror>(request, response), happyMirror); 
		model.addAttribute("page", page);
		
		Page<Office> offices = officeService.findPage(new Page<Office>(request, response), new Office()); 
		model.addAttribute("offices", offices);
		
		return "modules/ante/happyMirrorList";
	}

	@RequiresPermissions("ante:happyMirror:view")
	@RequestMapping(value = "form")
	public String form(HappyMirror happyMirror, Model model) {
		List<Office> offices = officeService.findAll(); 
		model.addAttribute("offices", offices.iterator());
		
		model.addAttribute("happyMirror", happyMirror);
		return "modules/ante/happyMirrorForm";
	}

	@RequiresPermissions("ante:happyMirror:edit")
	@RequestMapping(value = "save")
	public String save(HappyMirror happyMirror, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, happyMirror)){
			return form(happyMirror, model);
		}
		happyMirrorService.save(happyMirror);
		addMessage(redirectAttributes, "保存幸福镜子成功");
		return "redirect:"+Global.getAdminPath()+"/ante/happyMirror/?repage";
	}
	
	@RequiresPermissions("ante:happyMirror:edit")
	@RequestMapping(value = "delete")
	public String delete(HappyMirror happyMirror, RedirectAttributes redirectAttributes) {
		happyMirrorService.delete(happyMirror);
		addMessage(redirectAttributes, "删除幸福镜子成功");
		return "redirect:"+Global.getAdminPath()+"/ante/happyMirror/?repage";
	}

}