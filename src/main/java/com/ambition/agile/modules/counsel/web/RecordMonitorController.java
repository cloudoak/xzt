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
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.counsel.entity.RecordMonitor;
import com.ambition.agile.modules.counsel.service.RecordMonitorService;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 咨询督导Controller
 * @author harry
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/recordMonitor")
public class RecordMonitorController extends BaseController {

	@Autowired
	private RecordMonitorService recordMonitorService;
	
	@ModelAttribute
	public RecordMonitor get(@RequestParam(required=false) Integer id) {
		RecordMonitor entity = null;
		if (null != id && id>0 ){
			entity = recordMonitorService.get(id);
		}
		if (entity == null){
			entity = new RecordMonitor();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:recordMonitor:view")
	@RequestMapping(value = {"list", ""})
	public String list(RecordMonitor recordMonitor, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(null == recordMonitor || null ==recordMonitor.getId() || 
				recordMonitor.getId()<1){
			recordMonitor = new RecordMonitor();
			User user = UserUtils.getUser(); 
			if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
				recordMonitor.setCounselorId(user.getId());
			}
		}
		Page<RecordMonitor> page = recordMonitorService.findPage(new Page<RecordMonitor>(request, response), recordMonitor); 
		model.addAttribute("page", page);
		return "modules/counsel/recordMonitorList";
	}

	@RequiresPermissions("counsel:recordMonitor:view")
	@RequestMapping(value = "form")
	public String form(RecordMonitor recordMonitor, Model model) {
		model.addAttribute("recordMonitor", recordMonitor);
		return "modules/counsel/recordMonitorForm";
	}

	@RequiresPermissions("counsel:recordMonitor:edit")
	@RequestMapping(value = "save")
	public String save(RecordMonitor recordMonitor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recordMonitor)){
			return form(recordMonitor, model);
		}
		recordMonitorService.save(recordMonitor);
		addMessage(redirectAttributes, "保存咨询督导成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/recordMonitor/?repage";
	}
	
	@RequiresPermissions("counsel:recordMonitor:edit")
	@RequestMapping(value = "delete")
	public String delete(RecordMonitor recordMonitor, RedirectAttributes redirectAttributes) {
		recordMonitorService.delete(recordMonitor);
		addMessage(redirectAttributes, "删除咨询督导成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/recordMonitor/?repage";
	}

}