/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.ambition.agile.modules.counsel.entity.CounselAnswer;
import com.ambition.agile.modules.counsel.service.CounselAnswerService;

/**
 * 答疑室回复Controller
 * @author harry
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselAnswer")
public class CounselAnswerController extends BaseController {

	@Autowired
	private CounselAnswerService counselAnswerService;
	
	@ModelAttribute
	public CounselAnswer get(@RequestParam(required=false) Integer id) {
		CounselAnswer entity = null;
		if (id!=null&&id>0){
			entity = counselAnswerService.get(id);
		}
		//System.out.println("list:"+counselAnswer.getQuestionId());
		if (entity == null){
			entity = new CounselAnswer();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselAnswer:view")
	@RequestMapping(value = {"list", ""})
	public String list(CounselAnswer counselAnswer, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String questionId = (String)request.getParameter("counselAnswer.questionId");
		if(null == counselAnswer && StringUtils.isNotEmpty(questionId)){
			counselAnswer = new CounselAnswer();
		}
		counselAnswer.setQuestionId(Integer.parseInt(questionId));
		Page<CounselAnswer> page = counselAnswerService.findPage(new Page<CounselAnswer>(request, response), counselAnswer); 
		//System.out.println("list:"+questionId);
		model.addAttribute("page", page);
		return "modules/counsel/counselAnswerList";
	}

	@RequiresPermissions("counsel:counselAnswer:view")
	@RequestMapping(value = "form")
	public String form(CounselAnswer counselAnswer, Model model) {
		model.addAttribute("counselAnswer", counselAnswer);
		System.out.println("form:"+counselAnswer.getQuestionId());
		return "modules/counsel/counselAnswerForm";
	}

	@RequiresPermissions("counsel:counselAnswer:edit")
	@RequestMapping(value = "save")
	public String save(CounselAnswer counselAnswer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselAnswer)){
			return form(counselAnswer, model);
		}
		counselAnswerService.save(counselAnswer);
		addMessage(redirectAttributes, "保存答疑室回复成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselAnswer/?repage";
	}
	
	@RequiresPermissions("counsel:counselAnswer:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselAnswer counselAnswer, RedirectAttributes redirectAttributes) {
		counselAnswerService.delete(counselAnswer);
		addMessage(redirectAttributes, "删除答疑室回复成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselAnswer/?repage";
	}

}