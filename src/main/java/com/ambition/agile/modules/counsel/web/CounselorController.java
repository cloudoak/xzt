/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.web;

import java.io.PrintWriter;
import java.util.List;

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
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.counsel.constant.CounselorConstant;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.FileUtils;
import com.ambition.agile.modules.utils.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 咨询师Controller
 * @author harry
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselor")
public class CounselorController extends BaseController {

	@Autowired
	private CounselorService counselorService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	public OrganizationService organizationService;
	
	@ModelAttribute
	public Counselor get(@RequestParam(required=false) Integer id) {
		Counselor entity = null;
		if (id!=null&&id>0){
			entity = counselorService.get(id);
		}
		if (entity == null){
			entity = new Counselor();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselor:view")
	@RequestMapping(value = {"list"})
	public String list(Counselor counselor, HttpServletRequest request, HttpServletResponse response, Model model) {
		counselor.setStatus(CounselorConstant.STATUS_PASSABLE);
		counselor.setApplyStatus(CounselorConstant.APPLY_STATUS_NOSUBMIT);
		counselor.setOrganization(organizationService.getOrganizationJoinString());
		Page<Counselor> page = counselorService.findPage(new Page<Counselor>(request, response), counselor); 
		model.addAttribute("page", page);
		return "modules/counsel/counselorList";
	}

	@RequiresPermissions("counsel:counselor:view")
	@RequestMapping(value = "form")
	public String form(Counselor counselor, Model model) {
		if(counselor != null) {
			model.addAttribute("fileAbsolutePath", counselorService.getOriginalFilePath(counselor.getCertificatePath()));
		}
		model.addAttribute("fileAbsPath", counselorService.getOriginalFilePath());
		model.addAttribute("counselor", counselor);
		return "modules/counsel/counselorForm";
	}
	
	@RequiresPermissions("counsel:counselor:view")
	@RequestMapping(value = "detail")
	public String detail(Counselor counselor, Model model) {
		if(counselor != null) {
			model.addAttribute("fileAbsolutePath", counselorService.getOriginalFilePath(counselor.getCertificatePath()));
		}
		model.addAttribute("fileAbsPath", counselorService.getOriginalFilePath());
		model.addAttribute("counselor", counselor);
		return "modules/counsel/counselorView";
	}

	@RequiresPermissions("counsel:counselor:edit")
	@RequestMapping(value = "save")
	public String save(Counselor counselor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselor)){
			return form(counselor, model);
		}
		
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			Office org = new Office();
			org.setId(orgId);
			counselor.setOrgId(orgId);
		}
		if(counselor.getCertificatePath() != null) {
			counselor.setApplyStatus(CounselorConstant.APPLY_STATUS_SUBMIT);
		}
		counselorService.save(counselor);
		addMessage(redirectAttributes, "保存咨询师成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselor/list";
	}
	
	@RequiresPermissions("counsel:counselor:edit")
	@RequestMapping(value = "delete")
	public String delete(Counselor counselor, RedirectAttributes redirectAttributes) {
		counselorService.delete(counselor);
		addMessage(redirectAttributes, "删除咨询师成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselor/?repage";
	}
	
	@RequestMapping(value = {"getCounselorNotApply"})
	public void getCounselorNotApply(HttpServletRequest request, HttpServletResponse response) {
		try{
		Counselor  counselor = new Counselor();
		counselor.setApplyStatus(CounselorConstant.APPLY_STATUS_NOSUBMIT);
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			Office org = new Office();
			org.setId(orgId);
			counselor.setOrgId(orgId);
		}
		List<Counselor> counselorList = counselorService.findList(counselor);
		ObjectMapper mapper = new ObjectMapper();  
		String str = mapper.writeValueAsString(counselorList); 
		PrintWriter writer = response.getWriter();
		writer.println(str);
		
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@RequiresPermissions("counsel:counselor:edit")
	@RequestMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file) {
		return counselorService.transferTo(file);
	}
	
	/**
	 * @param counselor
	 * @param request
	 * @param response
	 * @param model 
	 * @return 返回已经提交认证资质图片文件的咨询师，并且通过管理员认证审核的，成为认证的咨询师
	 */
	@RequestMapping(value = "certificationList")
	public String certificationList(Counselor counselor, HttpServletRequest request, HttpServletResponse response, Model model) {
		counselor = new Counselor();
		counselor.setOrganization(organizationService.getOrganizationJoinString());
		counselor.setApplyStatus(CounselorConstant.APPLY_STATUS_SUBMIT);
		Page<Counselor> page = counselorService.findPage(new Page<Counselor>(request, response), counselor); 
		model.addAttribute("page", page);
		return "modules/counsel/counselorCertificationList";
	}
	
	/**
	 * @param counselor
	 * @param request
	 * @param response
	 * @param model 
	 * @return 返回已经注册了咨询师的用户，并且通过管理员审核的,成为普通咨询师
	 */
	@RequestMapping(value = "auditList")
	public String auditList(Counselor counselor, HttpServletRequest request, HttpServletResponse response, Model model) {
		counselor = new Counselor();
		counselor.setOrganization(organizationService.getOrganizationJoinString());
		counselor.setStatus(CounselorConstant.STATUS_AUDIT);
		Page<Counselor> page = counselorService.findPage(new Page<Counselor>(request, response), counselor); 
		model.addAttribute("page", page);
		return "modules/counsel/counselorAuditList";
	}

	@RequiresPermissions("counsel:counselor:view")
	@RequestMapping(value = "toCheck")
	public String toCheck(Counselor counselor, Model model) {
		model.addAttribute("counselor", counselor);
		return "modules/counsel/counselorFormCheck";
	}
	
	@RequiresPermissions("counsel:counselor:edit")
	@RequestMapping(value = "certification")
	public String certification(Counselor counselor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselor)){
			return form(counselor, model);
		}
		counselorService.save(counselor);
		addMessage(redirectAttributes, "审核认证咨询师成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselor/certificationList";
	}
	
	@RequiresPermissions("counsel:counselor:edit")
	@RequestMapping(value = "audit")
	public String audit(Counselor counselor, Model model, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(model, counselor)){
			return form(counselor, model);
		}
		User user = new User();
		user.setId(counselor.getUser().getId());
		systemService.updateStatus(user, counselor.getStatus());
		counselorService.save(counselor);
		addMessage(redirectAttributes, "审核咨询师成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselor/auditList";
	}
	
		/**
		 * @param request
		 * @param response
		 * @author harry 
		 * @see 用于 心理答疑室    咨询师选择
		 */
		@RequestMapping(value = {"getCounselorList"})
		public void  getCounselorList(HttpServletRequest request, HttpServletResponse response) {
			
			try{
			Counselor  counselor = new Counselor();
			//counselor.setApplyStatus(CounselorConstant.APPLY_STATUS_NOSUBMIT);
			User user = UserUtils.getUser(); 
			if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
				Integer orgId = user.getOffice().getId();
				Office org = new Office();
				org.setId(orgId);
				counselor.setOrgId(orgId);
			}
			/*
			Office org = new Office();
			org.setId(2);
			counselor.setOrg(org);
			*/
			List<Counselor> counselorList = counselorService.findList(counselor);
			
			//Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			
			 ObjectMapper mapper = new ObjectMapper();  
			//new Gson();
			String str = mapper.writeValueAsString(counselorList); 
			//gson.toJson(counselorList);
			System.out.println("getCounselorList @@@@@@@@@@@@@@@@@@@"+str);
			
			//response.getOutputStream()或response.getWriter()
			PrintWriter writer = response.getWriter();
			writer.println(str);
			
			}catch(Exception e){
				e.printStackTrace();
			}

		}
	
}