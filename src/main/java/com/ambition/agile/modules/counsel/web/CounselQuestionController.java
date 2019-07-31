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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.counsel.entity.CounselQuestion;
import com.ambition.agile.modules.counsel.service.CounselQuestionService;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 答疑室问题Controller
 * @author harry
 * @version 2017-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselQuestion")
public class CounselQuestionController extends BaseController {

	@Autowired
	private CounselQuestionService counselQuestionService;
	
	@Autowired
	private VisitorInfoService visitorInfoService;
	
	@ModelAttribute
	public CounselQuestion get(@RequestParam(required=false) Integer id) {
		CounselQuestion entity = null;
		if (id!=null&&id>0){
			entity = counselQuestionService.get(id);
		}
		if (entity == null){
			entity = new CounselQuestion();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselQuestion:view")
	@RequestMapping(value = {"list", ""})
	public String list(CounselQuestion counselQuestion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CounselQuestion> page = counselQuestionService.findPage(new Page<CounselQuestion>(request, response), counselQuestion); 
		model.addAttribute("page", page);
		return "modules/counsel/counselQuestionList";
	}

	@RequiresPermissions("counsel:counselQuestion:view")
	@RequestMapping(value = "form")
	public String form(CounselQuestion counselQuestion, Model model) {
		model.addAttribute("counselQuestion", counselQuestion);
		return "modules/counsel/counselQuestionForm";
	}

	@RequiresPermissions("counsel:counselQuestion:edit")
	@RequestMapping(value = "save")
	public String save(CounselQuestion counselQuestion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselQuestion)){
			return form(counselQuestion, model);
		}
		counselQuestionService.save(counselQuestion);
		addMessage(redirectAttributes, "保存答疑室问题成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselQuestion/?repage";
	}
	
	@RequiresPermissions("counsel:counselQuestion:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselQuestion counselQuestion, RedirectAttributes redirectAttributes) {
		counselQuestionService.delete(counselQuestion);
		addMessage(redirectAttributes, "删除答疑室问题成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselQuestion/?repage";
	}
	
	//@RequiresPermissions("counsel:counselor:view")
			//@ResponseBody
			/**
			 * @param request
			 * @param response
			 * @author harry 
			 * @see 用于 心理答疑室    中的学员 选择
			 */
			@RequestMapping(value = {"getVisitorInfoList"})
			public void  getVisitorInfoList(HttpServletRequest request, 
						HttpServletResponse response) {
				
				try{
					VisitorInfo  visitorInfo = new VisitorInfo();
				//counselor.setApplyStatus(CounselorConstant.APPLY_STATUS_NOSUBMIT);
				User user = UserUtils.getUser(); 
				if(null != user && null != user.getOffice() && 
						null != user.getOffice().getId()){
					Integer orgId = user.getOffice().getId();
					visitorInfo.setOrgId(orgId);
					//Office org = new Office();
					//org.setId(orgId);
					//visitorInfo.setOrg(org);
				}
				/*
				Office org = new Office();
				org.setId(2);
				counselor.setOrg(org);
				*/
				List<VisitorInfo> visitorInfoList = visitorInfoService.getVisitorInfoList(visitorInfo);
				
				//Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				//new Gson();
				//gson.toJson(counselorList);
				
				 ObjectMapper mapper = new ObjectMapper();  
				String str = mapper.writeValueAsString(visitorInfoList); 
				System.out.println("getVisitorInfoList  @@@@@@@@@@@@@@@@@@@"+str);
				
				//response.getOutputStream()或response.getWriter()
				PrintWriter writer = response.getWriter();
				writer.println(str);
				
				}catch(Exception e){
					e.printStackTrace();
				}

			}

}