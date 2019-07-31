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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.SysConfig;
import com.ambition.agile.modules.jzmk.service.SysConfigService;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 系统参数配置Controller
 * @author system_admin
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/sysConfig")
public class SysConfigController extends BaseController {

	@Autowired
	private SysConfigService sysConfigService;
	
	@ModelAttribute
	public SysConfig get(@RequestParam(required=false) Integer id) {
		SysConfig entity = null;
		if (id!=null&&id>0){
			entity = sysConfigService.get(id);
		}
		if (entity == null){
			entity = new SysConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:sysConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysConfig sysConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysConfig> page = sysConfigService.findPage(new Page<SysConfig>(request, response), sysConfig); 
		model.addAttribute("page", page);
		return "modules/jzmk/sysConfigList";
	}
	
	/**
	 * 系统参数配置(平台)
	 * @param sysConfig
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysConfig:view")
	@RequestMapping(value = "sys/form")
	public String sysform(SysConfig sysConfig, Model model) {
		sysConfig.setOrgId(0);
		sysConfig.setId(1);
		model.addAttribute("sysConfig", sysConfig);
		if(sysConfig != null) {
			model.addAttribute("fileAbsolutePath", sysConfigService.getOriginalFilePath(sysConfig.getLogo()));
		}
		model.addAttribute("fileAbsPath", sysConfigService.getOriginalFilePath());
		
		return "modules/jzmk/sysConfigForm";
	}

	/**
	 * 系统参数配置(机构)
	 * @param sysConfig
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:sysConfig:view")
	@RequestMapping(value = "form")
	public String form(SysConfig sysConfig, Model model) {
		/*sysConfig.setOrgId(UserUtils.getUser().getCompany().getId());
		List<SysConfig> list = sysConfigService.findList(sysConfig);
		if(list==null||list.size()<1||sysConfig==null){
			sysConfig=new SysConfig();
			Integer orgId = UserUtils.getUser().getCompany().getId();
			sysConfig.setOrgId(orgId);
			sysConfig.setSchoolName(UserUtils.getUser().getCompany().getName());
		}else{
			sysConfig=list.get(0);
			if(sysConfig.getSchoolName()==null){
				sysConfig.setSchoolName(UserUtils.getUser().getCompany().getName());
			}
		}*/
		sysConfig = sysConfigService.findOne();
		model.addAttribute("sysConfig", sysConfig == null ? new SysConfig() : sysConfig);
		
		return "modules/jzmk/orgConfigForm";
	}
	
	/**
	 * 系统参数设置(平台)
	 * @param sysConfig
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysConfig:edit")
	@RequestMapping(value = "sys/save")
	public String sysSave(SysConfig sysConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysConfig)){
			return form(sysConfig, model);
		}
		sysConfigService.save(sysConfig);
		/*model.addAttribute("fileAbsPath", sysConfigService.getOriginalFilePath());*/
		addMessage(redirectAttributes, "保存配置系统参数成功");
		
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysConfig/sys/form?id=1&orgId=0";
	}

	/**
	 * 系统参数配置(机构)
	 * @param sysConfig
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:sysConfig:edit")
	@RequestMapping(value = "save")
	public String save(SysConfig sysConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysConfig)){
			return form(sysConfig, model);
		}
		if(sysConfig!=null&&sysConfig.getId()==null){
			Integer orgId = UserUtils.getUser().getCompany().getId();
			sysConfig.setOrgId(orgId);
		}
		sysConfigService.save(sysConfig);
		addMessage(redirectAttributes, "保存配置系统参数成功");
		
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysConfig/form";
	}
	
	@RequiresPermissions("jzmk:sysConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(SysConfig sysConfig, RedirectAttributes redirectAttributes) {
		sysConfigService.delete(sysConfig);
		addMessage(redirectAttributes, "删除配置系统参数成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/sysConfig/?repage";
	}
	
	@RequestMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file) {	
		return sysConfigService.transferTo(file);
	}
}