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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.service.CommentInfoService;
import com.ambition.agile.modules.ante.service.ParentsService;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.ante.vo.ParentsVo;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;


/**
 * 家长管理Controller
 * @author fengqq
 * @version 2017-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/parents")
public class ParentsController extends BaseController {

	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SystemService systemService;
	 
	@Autowired
	private ParentsService parentsService;
	
	@Autowired
	private VisitorInfoService visitorInfoService;
	
    @Autowired
	public OrganizationService organizationService;
	

	@ModelAttribute
	public Parents get(@RequestParam(required=false) Integer id) {
		Parents entity = null;
		if (id!=null&&id>0){
			entity = parentsService.get(id);
		}
		if (entity == null){
			entity = new Parents();
		}
		return entity;
	}
	
	@RequiresPermissions("ante:parents:view")
	@RequestMapping(value = {"list", ""})
	public String list(Parents parents, HttpServletRequest request, HttpServletResponse response, Model model) {
		parents.setOrganization(organizationService.getOrganizationJoinString());
		Page<ParentsVo> page = parentsService.findNewList(new Page<Parents>(request, response), parents);
		model.addAttribute("page", page);
		return "modules/ante/parentsList";
	}

	@RequiresPermissions("ante:parents:view")
	@RequestMapping(value = "form")
	public String form(Parents parents, Model model) {
		ParentsVo parentsVo = parentsService.getParent(parents.getId());
		model.addAttribute("parentsVo", parentsVo);
		return "modules/ante/parentsForm";
	}
	
	@RequiresPermissions("ante:parents:view")
	@RequestMapping(value = "view")
	public String view(Parents parents, Model model) {
		ParentsVo parentsVo = parentsService.getParent(parents.getId());
		model.addAttribute("parentsVo", parentsVo);
		return "modules/ante/parentsView";
	}

	@RequiresPermissions("ante:parents:edit")
	@RequestMapping(value = "save")
	public String save(ParentsVo parentsVo, Model model, RedirectAttributes redirectAttributes) {
		int orgId = UserUtils.getUser().getOffice().getId();//机构号
		parentsVo.setOrgId(orgId);
		List<Parents> parents = parentsService.findList(new Parents());
		for(Parents ps : parents) {
			if(ps.getAccount().equals(parentsVo.getAccount())) {
				model.addAttribute("message", "编号或名字已存在!");
				return "modules/ante/parentsForm";
			}
		}
		VisitorInfo visitorInfo = new VisitorInfo();
		visitorInfo.setVisitorNo(parentsVo.getStudentCode());
		List<VisitorInfo> visList = visitorInfoService.findList(visitorInfo);		
		if(visList != null && visList.size() > 0){
			visitorInfo = visList.get(0);
		}
		parentsVo.setVisitorInfo(visitorInfo);
		parentsVo.setState(0);//审核状态：0-待审核;1-审核通过;2-审核未通过;
		try {
			parentsService.save(parentsVo);
		} catch (Exception e) {
			model.addAttribute("message", "编号或名字已存在!");
			return "modules/ante/parentsForm";
		}
		addMessage(redirectAttributes, "保存家长成功, 家长编号为： " + parentsVo.getParentNo());
		return "redirect:"+Global.getAdminPath()+"/ante/parents/list";
	}
	
	/**
	 * 删除(一条)
	 * @param parents
	 * @param reviewSign
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:parents:edit")
	@RequestMapping(value = "delete")
	public String delete(Parents parents, boolean reviewSign, RedirectAttributes redirectAttributes) {
		parentsService.delete(parents);
		addMessage(redirectAttributes, "删除家长成功");
		
		if(reviewSign==true) {
        	return "redirect:" + Global.getAdminPath() + "/ante/parents/reviewList";
        }else {
        	return "redirect:" + Global.getAdminPath() + "/ante/parents/list";
        }
	}
	
	/**
	 * 审核列表
	 * @param parents
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:parents:view")
	@RequestMapping(value = {"reviewList"})
	public String reviewList(Parents parents, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Office> offices = officeService.findPage(new Page<Office>(request, response), new Office()); 
		model.addAttribute("offices", offices);
		
		int orgId = UserUtils.getUser().getOffice().getId();//机构号
		User user = UserUtils.getUser(); 
		if(user.getUserType().intValue() != UserConstant.USER_ADMIN_TYPE) {
			parents.setOrgId(orgId);
		}
		
		parents.setState(0);//审核状态：0-待审核;1-审核通过;2-审核未通过;
  	  	//Page<Parents> page = parentsService.findPage(new Page<Parents>(request, response), parents); 
		Page<ParentsVo> page = parentsService.findNewList(new Page<Parents>(request, response), parents);
		
		model.addAttribute("page", page);
		return "modules/ante/parentsReviewList";
	}
	
	/**
	 * @decs: 到来访者审核页面(修改状态)
	 * @return
	 */
	@RequiresPermissions("ante:parents:edit")
	@RequestMapping(value = "toModStatus")
	public String toModStatus(Parents parents, Model model, RedirectAttributes redirectAttributes) {
		ParentsVo parentsVo = parentsService.getParent(parents.getId());
		model.addAttribute("parentsVo", parentsVo);
		
		return "modules/ante/parentsModState";
	}
	
	/**
	 * 审核
	 * @param parents
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:parents:edit")
	@RequestMapping(value = "modStatus")
	public String modStatus(Parents parents, Model model, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		User user = new User();
		user.setId(parents.getUserId());
		systemService.updateStatus(user, 1);
		
		parents.setState(1);//审核状态：0-待审核;1-审核通过;2-审核未通过;
		parentsService.save(parents);
		addMessage(redirectAttributes, "审核成功！");
		return "redirect:"+Global.getAdminPath()+"/ante/parents/reviewList";
	}

	/**
	 * 检验来访者编号
	 * @param studentCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="checkStuCode")
	@ResponseBody
	public String checkStuCode(@RequestParam(value="studentCode",defaultValue="") String studentCode, Model model){
		String msg = "false";
		VisitorInfo visitorInfo = new VisitorInfo();
		visitorInfo.setVisitorNo(studentCode);
		List<VisitorInfo> visList = visitorInfoService.findList(visitorInfo);
		
		if(visList.size() > 0){
			msg = "true";
		}
		return msg;
	}
	
	/**
	 * 检验家属用户名
	 * @param account
	 * @param model
	 * @return
	 */
	@RequestMapping(value="checkAccount")
	@ResponseBody
	public String checkAccount(@RequestParam(value="account",defaultValue="") String account, Model model){
		String msg = "false";
		ParentsVo parentsVo = new ParentsVo();
		parentsVo.setAccount(account);
		List<Parents> parentsList = parentsService.findList(parentsVo);
		if(parentsList.size() > 0){
			msg = "true";
		}
		return msg;
	}
	
}