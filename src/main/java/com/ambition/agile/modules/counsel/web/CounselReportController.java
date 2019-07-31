/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.web;

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
import com.ambition.agile.modules.counsel.entity.CounselReport;
import com.ambition.agile.modules.counsel.service.CounselReportService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 咨询报表Controller
 * @author harry
 * @version 2017-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselReport")
public class CounselReportController extends BaseController {

	@Autowired
	private CounselReportService counselReportService;
	
	@ModelAttribute
	public CounselReport get(@RequestParam(required=false) Integer id) {
		CounselReport entity = null;
		if (id!=null&&id>0){
			entity = counselReportService.get(id);
		}
		if (entity == null){
			entity = new CounselReport();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(CounselReport counselReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			if(null != counselReport){
				counselReport.setOrgId(orgId);
			}
		}
		Page<CounselReport> page = counselReportService.findPage(new Page<CounselReport>(request, response), counselReport); 
		model.addAttribute("page", page);
		return "modules/counsel/counselReportList";
	}

	@RequiresPermissions("counsel:counselReport:view")
	@RequestMapping(value = "form")
	public String form(CounselReport counselReport, Model model) {
		model.addAttribute("counselReport", counselReport);
		return "modules/counsel/counselReportForm";
	}

	@RequiresPermissions("counsel:counselReport:edit")
	@RequestMapping(value = "save")
	public String save(CounselReport counselReport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselReport)){
			return form(counselReport, model);
		}
		
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			if(null != counselReport){
				counselReport.setOrgId(orgId);
			}
		}
		
		counselReportService.save(counselReport);
		addMessage(redirectAttributes, "保存咨询报表成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselReport/?repage";
	}
	
	@RequiresPermissions("counsel:counselReport:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselReport counselReport, RedirectAttributes redirectAttributes) {
		counselReportService.delete(counselReport);
		addMessage(redirectAttributes, "删除咨询报表成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselReport/?repage";
	}

}