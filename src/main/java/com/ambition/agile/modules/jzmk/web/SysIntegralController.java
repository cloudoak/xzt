/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

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
import com.ambition.agile.modules.jzmk.entity.SysIntegral;
import com.ambition.agile.modules.jzmk.service.SysIntegralService;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 系统积分设置Controller
 * @author system_admin
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/sysIntegral")
public class SysIntegralController extends BaseController {

	@Autowired
	private SysIntegralService sysIntegralService;
	
	@ModelAttribute
	public SysIntegral get(@RequestParam(required=false) Integer id) {
		SysIntegral entity = null;
		if (id!=null&&id>0){
			entity = sysIntegralService.get(id);
		}
		if (entity == null){
			entity = new SysIntegral();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:sysIntegral:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysIntegral sysIntegral, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysIntegral> page = sysIntegralService.findPage(new Page<SysIntegral>(request, response), sysIntegral); 
		model.addAttribute("page", page);
		
		return "modules/jzmk/sysIntegralList";
	}

	/**
	 * 系统积分配置(机构)
	 * @param sysIntegral
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysIntegral:view")
	@RequestMapping(value = "form")
	public String form(SysIntegral sysIntegral, Model model) {
		/*sysIntegral.setOrgId(UserUtils.getUser().getCompany().getId());
		List<SysIntegral> list = sysIntegralService.findList(sysIntegral);
		if(list==null||list.size()<1){
			sysIntegral=new SysIntegral();
			Integer orgId = UserUtils.getUser().getCompany().getId();
			sysIntegral.setOrgId(orgId);
			sysIntegral.setSchoolName(UserUtils.getUser().getCompany().getName());
		}else{
			sysIntegral=list.get(0);
		}*/
		sysIntegral = sysIntegralService.findOne();
		model.addAttribute("sysIntegral", sysIntegral == null ? new  SysIntegral() : sysIntegral);
		
		return "modules/jzmk/orgIntegralForm";
	}
	
	/**
	 * 系统积分配置(平台)
	 * @param sysIntegral
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysIntegral:view")
	@RequestMapping(value = "sys/form")
	public String sysForm(SysIntegral sysIntegral, Model model) {
		model.addAttribute("sysIntegral", sysIntegral);
		
		return "modules/jzmk/sysIntegralForm";
	}
	
	/**
	 * 系统积分配置(平台):新增/修改
	 * @param sysIntegral
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysIntegral:edit")
	@RequestMapping(value = "sys/save")
	public String sysSave(SysIntegral sysIntegral, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysIntegral)){
			return form(sysIntegral, model);
		}
		sysIntegralService.save(sysIntegral);
		addMessage(redirectAttributes, "保存系统积分设置成功");
		
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysIntegral/sys/form?id=1&orgId=0";
	}

	/**
	 * 系统积分配置(机构):新增/修改
	 * @param sysIntegral
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysIntegral:edit")
	@RequestMapping(value = "save")
	public String save(SysIntegral sysIntegral, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysIntegral)){
			return form(sysIntegral, model);
		}
		if(sysIntegral!=null&&sysIntegral.getId()==null){
			Integer orgId = UserUtils.getUser().getCompany().getId();
			sysIntegral.setOrgId(orgId);
		}
		sysIntegralService.save(sysIntegral);
		addMessage(redirectAttributes, "保存系统积分设置成功");
		
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysIntegral/form";
	}
	
	/**
	 * 系统积分配置:删除
	 * @param sysIntegral
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysIntegral:edit")
	@RequestMapping(value = "delete")
	public String delete(SysIntegral sysIntegral, RedirectAttributes redirectAttributes) {
		sysIntegralService.delete(sysIntegral);
		addMessage(redirectAttributes, "删除系统积分设置成功");
		
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysIntegral/?repage";
	}

}