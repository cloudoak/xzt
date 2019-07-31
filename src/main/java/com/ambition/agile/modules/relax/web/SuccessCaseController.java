/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ambition.agile.modules.relax.entity.SuccessCase;
import com.ambition.agile.modules.relax.service.SuccessCaseService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.FileUtils;

/**
 * 成功案例Controller
 * @author harry
 * @version 2017-06-25
 */
@Controller
@RequestMapping(value = "${adminPath}/relax/successCase")
public class SuccessCaseController extends BaseController {

	@Autowired
	private SuccessCaseService successCaseService;
	
	@Autowired
	@Value(value = "${file.appversion.upload}")
	private String filePath;
	
	@ModelAttribute
	public SuccessCase get(@RequestParam(required=false) Integer id) {
		SuccessCase entity = null;
		if (id!=null&&id>0){
			entity = successCaseService.get(id);
		}
		if (entity == null){
			entity = new SuccessCase();
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
	
	@RequiresPermissions("relax:successCase:view")
	@RequestMapping(value = {"list", ""})
	public String list(SuccessCase successCase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SuccessCase> page = successCaseService.findPage(new Page<SuccessCase>(request, response), successCase); 
		model.addAttribute("page", page);
		return "modules/relax/successCaseList";
	}

	@RequiresPermissions("relax:successCase:view")
	@RequestMapping(value = "form")
	public String form(SuccessCase successCase, Model model) {
		model.addAttribute("successCase", successCase);
		return "modules/relax/successCaseForm";
	}
	
	@RequiresPermissions("relax:successCase:edit")
	@RequestMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file) {
		String message = "";
		boolean flags = true;
		try {
			if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
				String path = FileUtils.transfer(file, filePath);
				message = path;
			}
		}catch(Exception ex) {
			flags = false;
			message = ex.getMessage();
		}
		return "{\"success\": " + flags + ", \"message\": \"" + message + "\"}";
	}

	@RequiresPermissions("relax:successCase:edit")
	@RequestMapping(value = "save")
	public String save(SuccessCase successCase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, successCase)){
			return form(successCase, model);
		}
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			successCase.setOrgId(orgId);
			successCase.setOrgName(user.getOffice().getName());
			
		}
		if(null != user && null != user.getName() ){
			successCase.setCreatorId(user.getId());
			successCase.setCreatorName(user.getName());
		}
		successCaseService.save(successCase);
		addMessage(redirectAttributes, "保存成功案例成功");
		return "redirect:"+Global.getAdminPath()+"/relax/successCase/?repage";
	}
	
	@RequiresPermissions("relax:successCase:edit")
	@RequestMapping(value = "delete")
	public String delete(SuccessCase successCase, RedirectAttributes redirectAttributes) {
		successCaseService.delete(successCase);
		addMessage(redirectAttributes, "删除成功案例成功");
		return "redirect:"+Global.getAdminPath()+"/relax/successCase/?repage";
	}

}