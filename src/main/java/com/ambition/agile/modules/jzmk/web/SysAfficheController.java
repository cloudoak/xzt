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
import com.ambition.agile.modules.jzmk.entity.SysAffiche;
import com.ambition.agile.modules.jzmk.service.SysAfficheService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 系统公告管理Controller
 * @author system_admin
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/sysAffiche")
public class SysAfficheController extends BaseController {

	@Autowired
	private SysAfficheService sysAfficheService;
	
	@ModelAttribute
	public SysAffiche get(@RequestParam(required=false) Integer id) {
		SysAffiche entity = null;
		if (id!=null&&id>0){
			entity = sysAfficheService.get(id);
		}
		if (entity == null){
			entity = new SysAffiche();
		}
		return entity;
	}
	
	
	/**
	 * 公告管理列表(平台)
	 * @param sysAffiche
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:view")
	@RequestMapping(value = {"list"})
	public String list(SysAffiche sysAffiche, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(sysAffiche==null){
			sysAffiche=new SysAffiche();
		}
		sysAffiche.setOrgId(0);
		Page<SysAffiche> page = sysAfficheService.findPage(new Page<SysAffiche>(request, response), sysAffiche); 
		model.addAttribute("page", page);
		return "modules/jzmk/sysAfficheList";
	}
	
	/**
	 * 公告管理列表(机构)
	 * @param sysAffiche
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:view")
	@RequestMapping(value = {"org/list"})
	public String orglist(SysAffiche sysAffiche, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(sysAffiche==null){
			sysAffiche=new SysAffiche();
		}
		Integer orgId = UserUtils.getUser().getCompany().getId();
		sysAffiche.setOrgId(orgId);
		Page<SysAffiche> page = sysAfficheService.findPage(new Page<SysAffiche>(request, response), sysAffiche); 
		model.addAttribute("page", page);
		return "modules/jzmk/orgAfficheList";
	}

	/**
	 * form(平台)
	 * @param sysAffiche
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:view")
	@RequestMapping(value = "form")
	public String form(SysAffiche sysAffiche, Model model) {
		model.addAttribute("sysAffiche", sysAffiche);
		return "modules/jzmk/sysAfficheForm";
	}
	
	/**
	 * form(机构)
	 * @param sysAffiche
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:view")
	@RequestMapping(value = "org/form")
	public String orgForm(SysAffiche sysAffiche, Model model) {
		model.addAttribute("sysAffiche", sysAffiche);
		return "modules/jzmk/orgAfficheForm";
	}
	
	/**
	 * 查看(平台)
	 * @param sysAffiche
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:edit")
	@RequestMapping(value = "view")
	public String view(SysAffiche sysAffiche, Model model) {
		model.addAttribute("sysAffiche", sysAffiche);
		return "modules/jzmk/sysAfficheView";
	}
	
	/**
	 * 查看(机构)
	 * @param sysAffiche
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:view")
	@RequestMapping(value = "org/view")
	public String orgView(SysAffiche sysAffiche, Model model) {
		model.addAttribute("sysAffiche", sysAffiche);
		return "modules/jzmk/orgAfficheView";
	}
	
	/**
	 * 公告管理新增、修改(机构)
	 * @param sysAffiche
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:edit")
	@RequestMapping(value = "org/save")
	public String orgSave(SysAffiche sysAffiche, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysAffiche)){
			return form(sysAffiche, model);
		}
		//设置公告所属机构
		User user = UserUtils.getUser();
		if(user.getCompany()==null||"0".equals(user.getCompany())||"".equals(user.getCompany())){
			sysAffiche.setOrgId(0);
		}else{
			sysAffiche.setOrgId(user.getCompany().getId());
		}
		String content = sysAffiche.getAfficheContent();
		content = StringFunctionUtils.replaceWebJsp(content);
		sysAffiche.setAfficheContent(content);
		//保存公告
		sysAfficheService.save(sysAffiche);
		addMessage(redirectAttributes, "保存系统公告成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysAffiche/org/list?repage";
	}

	/**
	 * 公告管理新增、修改(平台)
	 * @param sysAffiche
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:edit")
	@RequestMapping(value = "save")
	public String save(SysAffiche sysAffiche, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysAffiche)){
			return form(sysAffiche, model);
		}
		//设置公告所属机构
		sysAffiche.setOrgId(0);
		String content = sysAffiche.getAfficheContent();
		content = StringFunctionUtils.replaceWebJsp(content);
		sysAffiche.setAfficheContent(content);
		//保存公告
		sysAfficheService.save(sysAffiche);
		addMessage(redirectAttributes, "保存平台公告成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysAffiche/list?repage";
	}
	
	/**
	 * 公告管理删除(平台)
	 * @param sysAffiche
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:edit")
	@RequestMapping(value = "delete")
	public String delete(SysAffiche sysAffiche, RedirectAttributes redirectAttributes) {
		sysAfficheService.delete(sysAffiche);
		addMessage(redirectAttributes, "删除平台公告成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysAffiche/list?repage";
	}
	
	/**
	 * 公告管理单个删除(机构)
	 * @param sysAffiche
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:edit")
	@RequestMapping(value = "org/delete")
	public String orgDelete(SysAffiche sysAffiche, RedirectAttributes redirectAttributes) {
		sysAfficheService.delete(sysAffiche);
		addMessage(redirectAttributes, "删除系统公告成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysAffiche/org/list?repage";
	}
	
	/**
	 * 公告管理批量删除(机构)
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysAffiche:edit")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		ids = StringFunctionUtils.replaceWebJsp(ids);
//		if(ids.length()==2) {
//			addMessage(redirectAttributes, "请选择要删除的记录！");
//			return "redirect:"+Global.getAdminPath()+"/jzmk/sysAffiche/org/list?repage";
//		}
		String[] split = ids.substring(1, ids.length()-1).split(",");
		for (String idStr : split) {
			idStr = idStr.substring(1, idStr.length()-1);
			Integer id=Integer.valueOf(idStr);
			SysAffiche sysAffiche = new SysAffiche();
			sysAffiche.setId(id);
			sysAfficheService.delete(sysAffiche);
		}
		addMessage(redirectAttributes, "删除系统公告成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysAffiche/org/list?repage";
	}

}