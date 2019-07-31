/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.web;

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
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.PublicActivity;
import com.ambition.agile.modules.ante.service.PublicActivityService;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 公益活动Controller
 * @author fengqq
 * @version 2017-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/publicActivity")
public class PublicActivityController extends BaseController {

	@Autowired
	private PublicActivityService publicActivityService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	public OrganizationService organizationService;
	
	@ModelAttribute
	public PublicActivity get(@RequestParam(required=false) Integer id) {
		PublicActivity entity = null;
		if (id!=null&&id>0){
			entity = publicActivityService.get(id);
		}
		if (entity == null){
			entity = new PublicActivity();
		}
		return entity;
	}
	
	/**
	 * 公益活动列表
	 * @param publicActivity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:view")
	@RequestMapping(value = {"list", ""})
	public String list(PublicActivity publicActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		publicActivity.setOrganization(organizationService.getOrganizationJoinString());
		Page<PublicActivity> page = publicActivityService.findPage(new Page<PublicActivity>(request, response), publicActivity); 
		model.addAttribute("page", page);
		return "modules/ante/publicActivityList";
	}
	
	/**
	 * 我发布的公益活动列表
	 * @param publicActivity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:view")
	@RequestMapping(value = {"myList", ""})
	public String myList(PublicActivity publicActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		publicActivity.setCreateName(user.getName());
		publicActivity.setOrganization(organizationService.getOrganizationJoinString());
		Page<PublicActivity> page = publicActivityService.findPage(new Page<PublicActivity>(request, response), publicActivity); 
		model.addAttribute("page", page);
		return "modules/ante/publicActivityMyList";
	}
	
	/**
	 * 我报名的公益活动列表
	 * @param publicActivity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:view")
	@RequestMapping(value = {"myEnrollList", ""})
	public String myEnrollList(PublicActivity publicActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		publicActivity.setCreateName(user.getName());
		publicActivity.setActivityStatus(3);//活动状态:0-未开始;1-报名中;2-已结束;3-报名成功;4-报名失败;
		publicActivity.setOrgId(user.getCompany().getId());
		Page<PublicActivity> page = publicActivityService.findPage(new Page<PublicActivity>(request, response), publicActivity); 
		model.addAttribute("page", page);
		return "modules/ante/publicActivityMyEnrollList";
	}
	
	/**
	 * 公益活动审核列表
	 * @param publicActivity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(PublicActivity publicActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		publicActivity.setCheckStatus(0);
		publicActivity.setOrganization(organizationService.getOrganizationJoinString());
		Page<PublicActivity> page = publicActivityService.findPage(new Page<PublicActivity>(request, response), publicActivity); 
		model.addAttribute("page", page);
		return "modules/ante/publicActivityCheckList";
	}
	
	/**
	 * @param publicActivity
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:view")
	@RequestMapping(value = "view")
	public String view(PublicActivity publicActivity, Model model) {
		model.addAttribute("publicActivity", publicActivity);
		return "modules/ante/publicActivityView";
	}

	/**
	 * @param publicActivity
	 * @param myActivity
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:view")
	@RequestMapping(value = "form")
	public String form(PublicActivity publicActivity, boolean myActivity, Model model) {
		model.addAttribute("publicActivity", publicActivity);
		model.addAttribute("myActivity", myActivity);//是否本人发布
		return "modules/ante/publicActivityForm";
	}
	
	/**
	 * 新增
	 * @param publicActivity
	 * @param myActivity
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:edit")
	@RequestMapping(value = "save")
	public String save(PublicActivity publicActivity, boolean myActivity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, publicActivity)){
			return form(publicActivity, myActivity, model);
		}
		User user = UserUtils.getUser();
		Office company = user.getCompany();
		publicActivity.setOrgName(company.getName());
		publicActivity.setOrgId(company.getId());
		publicActivity.setCreateName(user.getName());
		publicActivity.setCheckStatus(0);//审核状态:0-待审核;1-审核通过;2-审核未通过;
		publicActivityService.save(publicActivity);
		addMessage(redirectAttributes, "保存公益活动成功");
		if(myActivity==true) {
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/myList";
		}else {
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/list";
		}
	}
	
	/**
	 * 公益活动审核列表
	 * @param publicActivity
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:view")
	@RequestMapping(value = "check")
	public String check(PublicActivity publicActivity, Model model, RedirectAttributes redirectAttributes) {
		
		publicActivity.setCheckStatus(1);//审核状态:0-待审核;1-审核通过;2-审核未通过;
		publicActivityService.save(publicActivity);
		addMessage(redirectAttributes, "审核成功");
		return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/checkList";
	}
	
	/**
	 * 公益活动审核页面
	 * @param publicActivity
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(PublicActivity publicActivity, Model model, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("publicActivity", publicActivity);
		return "modules/ante/publicActivityCheckForm";
	}
	
	/**
	 * 公益活动审核：通过
	 * @param publicActivity
	 * @param myActivity
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:edit")
	@RequestMapping(value = "pass")
	public String pass(PublicActivity publicActivity, boolean myActivity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, publicActivity)){
			return form(publicActivity, myActivity, model);
		}
		publicActivity.setCheckStatus(1);//审核状态:0-待审核;1-审核通过;2-审核未通过;
		publicActivityService.save(publicActivity);
		addMessage(redirectAttributes, "审核成功");
		return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/checkList";
	}
	
	/**
	 * 公益活动审核：驳回
	 * @param publicActivity
	 * @param myActivity
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:edit")
	@RequestMapping(value = "backCheck")
	public String backCheck(PublicActivity publicActivity, boolean myActivity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, publicActivity)){
			return form(publicActivity, myActivity, model);
		}
		publicActivity.setCheckStatus(2);//审核状态:0-待审核;1-审核通过;2-审核未通过;
		publicActivityService.save(publicActivity);
		addMessage(redirectAttributes, "驳回成功");
		return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/checkList";
	}
	
	/**
	 * 删除一条
	 * @param publicActivity
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:edit")
	@RequestMapping(value = "delete")
	public String delete(PublicActivity publicActivity, boolean myActivity, RedirectAttributes redirectAttributes) {
		publicActivityService.delete(publicActivity);
		addMessage(redirectAttributes, "删除公益活动成功");
		if(publicActivity.getFlag()==1) {
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/myList";
		}else if(publicActivity.getFlag()==2) {
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/myEnrollList";
		}else{
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/list";
		}
	}
	
	/**
	 * 删除多条
	 * @param ids
	 * @param myActivity
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:edit")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, boolean myActivity, RedirectAttributes redirectAttributes) {
		ids = StringFunctionUtils.replaceWebJsp(ids);
		String[] split = ids.substring(1, ids.length()-1).split(",");
		for (String idStr : split) {
			idStr = idStr.substring(1, idStr.length()-1);
			Integer id=Integer.valueOf(idStr);
			PublicActivity publicActivity = new PublicActivity();
			publicActivity.setId(id);
			publicActivityService.delete(publicActivity);
		}
		addMessage(redirectAttributes, "删除公益活动成功");
		
		if(myActivity==true) {
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/myList";
		}else {
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/list";
		}
	}
	
	/**
	 * 报名
	 * @param publicActivity
	 * @param myActivity
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:publicActivity:edit")
	@RequestMapping(value = "enroll")
	public String enroll(PublicActivity publicActivity, boolean myActivity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, publicActivity)){
			return form(publicActivity, myActivity, model);
		}
		User user = UserUtils.getUser();
		user.setStatus(3);//活动状态:0-未开始;1-报名中;2-已结束;3-报名成功;4-报名失败;
		systemService.saveUser(user);
		
		publicActivity.setActivityStatus(3);//活动状态:0-未开始;1-报名中;2-已结束;3-报名成功;4-报名失败;
		publicActivityService.save(publicActivity);
		addMessage(redirectAttributes, "报名成功");
		if(myActivity==true) {
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/myList";
		}else {
			return "redirect:"+Global.getAdminPath()+"/ante/publicActivity/list";
		}
	}

}