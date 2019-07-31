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

import com.alibaba.fastjson.JSONArray;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskUser;
import com.ambition.agile.modules.jzmk.service.ScaleTaskUserService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 任务测评人员Controller
 * @author dortan
 * @version 2017-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleTaskUser")
public class ScaleTaskUserController extends BaseController {

	@Autowired
	private ScaleTaskUserService scaleTaskUserService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@ModelAttribute
	public ScaleTaskUser get(@RequestParam(required=false) Integer id) {
		ScaleTaskUser entity = null;
		if (id!=null&&id>0){
			entity = scaleTaskUserService.get(id);
		}
		if (entity == null){
			entity = new ScaleTaskUser();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:scaleTaskUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScaleTaskUser scaleTaskUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		scaleTaskUser.setTaskId(Integer.valueOf(request.getParameter("taskId")));
		scaleTaskUser.setDelFlag("0");
		Page<ScaleTaskUser> page = scaleTaskUserService.findPage(new Page<ScaleTaskUser>(request, response), scaleTaskUser); 
		model.addAttribute("page", page);
		return "modules/jzmk/scaleTaskUserList";
	}
	
	@RequiresPermissions("jzmk:scaleTaskUser:view")
	@RequestMapping(value = {"dc/scale/list", ""})
	public String selectDcScalesByUser(ScaleTaskUser scaleTaskUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*
		if(scaleTaskUser.getRole()==null){
			scaleTaskUser.setRole(1);
		}
		
		if(scaleTaskUser.getUserId()==null){
			scaleTaskUser.setUserId(1);
		}
		*/
		if(request.getParameter("ids")!=null&&!request.getParameter("ids").equals(""))
		{
			String idsStr = StringFunctionUtils.replaceWebJsp(request.getParameter("ids"));
			JSONArray jsonArray1 = JSONArray.parseArray(idsStr);
			StringBuffer stringBuffer1=new StringBuffer();
			for (Object obj : jsonArray1) {
				stringBuffer1.append(obj.toString()+",");
			}
			String memberIds = stringBuffer1.toString().substring(0, stringBuffer1.toString().length()-1);
			String [] taskUserIds = memberIds.split(",");
			for(int i=0; i < taskUserIds.length; i++) {
				scaleTaskUser.setId(Integer.valueOf(taskUserIds[i]));
				scaleTaskUser.setState(3);
				scaleTaskUserService.update(scaleTaskUser);
			}
		}
		ScaleTaskUser scaleTaskUserDc = new ScaleTaskUser();
		ScaleTaskUser scaleTaskUserCc = new ScaleTaskUser();
		scaleTaskUserDc.setUserId(UserUtils.getUser().getId());
		scaleTaskUserDc.setState(0);
		scaleTaskUserDc.setOrganization(organizationService.getOrganizationJoinString());
		scaleTaskUser.setState(1);
		scaleTaskUser.setOrganization(organizationService.getOrganizationJoinString());
		scaleTaskUserCc.setUserId(UserUtils.getUser().getId());
		scaleTaskUserCc.setState(4);
		scaleTaskUserCc.setOrganization(organizationService.getOrganizationJoinString());
		Page<ScaleTaskUser> pageDc = scaleTaskUserService.selectDcScalesCheckingByUser(new Page<ScaleTaskUser>(request, response), scaleTaskUserDc);
		if(pageDc.getCount()==0)
		{
			model.addAttribute("displayStyle","display:none");
		}
		else
		{
			model.addAttribute("displayStyle","display:block");
		}
		Page<ScaleTaskUser> pageCc = scaleTaskUserService.selectCcScalesCheckingByUser(new Page<ScaleTaskUser>(request, response), scaleTaskUserCc);
		if(pageCc.getCount()==0)
		{
			model.addAttribute("displayStyleCc","display:none");
		}
		else
		{
			model.addAttribute("displayStyleCc","display:block");
		}
		Page<ScaleTaskUser> page = scaleTaskUserService.findPage(new Page<ScaleTaskUser>(request, response), scaleTaskUser);
		if(page.getCount()==0)
		{
			model.addAttribute("displayStylePart","display:none");
		}
		else
		{
			model.addAttribute("displayStylePart","display:block");
		}
		model.addAttribute("pageDc", pageDc);
		model.addAttribute("pageCc", pageCc);
		model.addAttribute("page", page);
		return "modules/jzmk/dcScaleList";
	}
	
	@RequiresPermissions("jzmk:scaleTaskUser:view")
	@RequestMapping(value = {"cr/scale/list", ""})
	public String selectCrScalesByUser(ScaleTaskUser scaleTaskUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*if(scaleTaskUser.getRole()==null){
			scaleTaskUser.setRole(1);
		}
		if(scaleTaskUser.getUserId()==null){
			scaleTaskUser.setUserId(1);
		}*/
		scaleTaskUser.setUserId(UserUtils.getUser().getId());
		scaleTaskUser.setState(2);
		if(UserUtils.getUser().getId()!=1)
		{
			scaleTaskUser.setOrganization(organizationService.getOrganizationJoinString());
		}
		Page<ScaleTaskUser> page = scaleTaskUserService.findPage(new Page<ScaleTaskUser>(request, response), scaleTaskUser); 
		model.addAttribute("page", page);
		return "modules/jzmk/crScaleList";
	}
	
	@RequiresPermissions("jzmk:scaleTaskUser:view")
	@RequestMapping(value = {"lc/scale/list", ""})
	public String selectLcScalesByUser(ScaleTaskUser scaleTaskUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*if(scaleTaskUser.getRole()==null){
			scaleTaskUser.setRole(1);
		}
		if(scaleTaskUser.getUserId()==null){
			scaleTaskUser.setUserId(1);
		}*/
		if(request.getParameter("ids")!=null&&!request.getParameter("ids").equals(""))
		{
			String idsStr = StringFunctionUtils.replaceWebJsp(request.getParameter("ids"));
			JSONArray jsonArray1 = JSONArray.parseArray(idsStr);
			StringBuffer stringBuffer1=new StringBuffer();
			for (Object obj : jsonArray1) {
				stringBuffer1.append(obj.toString()+",");
			}
			String memberIds = stringBuffer1.toString().substring(0, stringBuffer1.toString().length()-1);
			String [] taskUserIds = memberIds.split(",");
			for(int i=0; i < taskUserIds.length; i++) {
				scaleTaskUser.setId(Integer.valueOf(taskUserIds[i]));
				scaleTaskUser.setState(3);
				scaleTaskUserService.update(scaleTaskUser);
			}
		}
		scaleTaskUser.setUserId(UserUtils.getUser().getId());
		scaleTaskUser.setState(0);
		scaleTaskUser.setOrganization(organizationService.getOrganizationJoinString());
		Page<ScaleTaskUser> page = scaleTaskUserService.selectLcScalesByUser(new Page<ScaleTaskUser>(request, response), scaleTaskUser); 
		model.addAttribute("page", page);
		return "modules/jzmk/lcScaleList";
	}

	@RequiresPermissions("jzmk:scaleTaskUser:view")
	@RequestMapping(value = "form")
	public String form(ScaleTaskUser scaleTaskUser, Model model) {
		model.addAttribute("scaleTaskUser", scaleTaskUser);
		return "modules/jzmk/scaleTaskUserForm";
	}

	@RequiresPermissions("jzmk:scaleTaskUser:edit")
	@RequestMapping(value = "save")
	public String save(ScaleTaskUser scaleTaskUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleTaskUser)){
			return form(scaleTaskUser, model);
		}
		scaleTaskUserService.save(scaleTaskUser);
		addMessage(redirectAttributes, "保存测评人员成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleTaskUser/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleTaskUser:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleTaskUser scaleTaskUser, RedirectAttributes redirectAttributes) {
		scaleTaskUserService.delete(scaleTaskUser);
		addMessage(redirectAttributes, "删除测评人员成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleTaskUser/?repage";
	}
	
	public void refreshState()
	{
		
	}
}