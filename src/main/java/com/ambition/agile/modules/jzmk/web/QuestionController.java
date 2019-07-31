/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.Answer;
import com.ambition.agile.modules.jzmk.entity.Question;
import com.ambition.agile.modules.jzmk.entity.Scale;
import com.ambition.agile.modules.jzmk.service.AnswerService;
import com.ambition.agile.modules.jzmk.service.QuestionService;
import com.ambition.agile.modules.jzmk.service.ScaleService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 量表条目Controller
 * @author OAK
 * @version 2017-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/question")
public class QuestionController extends BaseController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ScaleService scaleService;
	
	@Autowired
	private AnswerService answerService;
	
	@ModelAttribute
	public Question get(@RequestParam(required=false) Integer id) {
		Question entity = null;
		if (id!=null&&id>0){
			entity = questionService.get(id);
		}
		if (entity == null){
			entity = new Question();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:question:view")
	@RequestMapping(value = {"list"})
	public String list(Question question, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Question> page = questionService.findPage(new Page<Question>(request, response), question); 
		model.addAttribute("page", page);
		Scale scale=new Scale();
		scale.setDelFlag("0");
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		return "modules/jzmk/questionList";
	}
	
	@RequestMapping("checkAnswer")
	@ResponseBody
	public int checkAnswer(Integer isNoAnswer, Integer inside){
		List<String> isSetAnswers = null;
		if(inside == 1) {
			isSetAnswers = questionService.findBySidChilren(isNoAnswer);
		}else {
			isSetAnswers = questionService.findBySidRoot(isNoAnswer);
		}
		for(String isSetAnswer : isSetAnswers) {
			if("0".equals(isSetAnswer)) {
				return 0;
			}
		}
		return 1;
	}
	
	/**
	 * 查询未完成的量表条目
	 * @param question
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:question:view")
	@RequestMapping(value = {"qa"})
	public String listQuestion(Question question, Integer sid, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(question!=null&&question.getSid()==null){
			question.setSid(sid);
		}
		Scale scale = scaleService.get(sid);
		Integer inside =  scale.getInside();
		model.addAttribute("inside", inside);
		Page<Question> page = questionService.findPage(new Page<Question>(request, response), question);
		model.addAttribute("page", page);
		model.addAttribute("sid", sid);
		return "modules/jzmk/qaset";
	}

	@RequiresPermissions("jzmk:question:view")
	@RequestMapping(value = "form")
	public String form(Question question, Model model) {
		User user = UserUtils.getUser();
		model.addAttribute("question", question);
//		Scale scale=new Scale();
//		scale.setDelFlag("0");
//		List<Scale> scales = scaleService.findList(scale);
//		model.addAttribute("scales", scales);
		Scale scale = scaleService.get(question.getSid());
		if(user.getIsAdmin().intValue() == 2 && (scale != null && scale.getInside().intValue() == 1)) {//平台管理员拥有内置量表查看与添加权限
			Question q = new Question();
			q.setSid(question.getSid());
			List<Question> parents = questionService.findByParentIdRoot(q);
			model.addAttribute("parents", parents);
			model.addAttribute("isAdmin", 2);
		}
		return "modules/jzmk/questionForm";
	}

	@RequiresPermissions("jzmk:question:edit")
	@RequestMapping(value = "save")
	public String save(Question question, Integer sid, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, question)){
			return form(question, model);
		}
		
		//是否是新增
		if(question!=null&&question.getId()==null||question.getId()==0){
			Question q=new Question();
			q.setSid(sid);
			Integer number = questionService.findMaxNumber(sid);
			question.setNumber(++number);
		}
		String content = StringFunctionUtils.replaceWebJsp(question.getContent());
		question.setContent(content);
		questionService.save(question);
		addMessage(redirectAttributes, "保存量表条目成功");
		Question qut = new Question();
		qut.setSid(sid);
		Page<Question> page = questionService.findPage(new Page<Question>(request, response), qut); 
		model.addAttribute("page", page);
		model.addAttribute("sid", sid);
		Scale scale = scaleService.get(sid);
		Integer inside =  scale.getInside();
		model.addAttribute("inside", inside);
		return "modules/jzmk/qaset";
	}
	
	/**
	 * 条目序号上下移动
	 * @param op
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:question:edit")
	@RequestMapping(value = "update")
	public String updateNumber(String op, Integer id, Integer sid, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		Question originalQuestion = null, modificationQuestion = null;
		boolean isModfied = (id != null);
		if(isModfied){
			originalQuestion = questionService.get(id);
			Integer number = originalQuestion.getNumber();
			
			if("up".equals(op)) {
				modificationQuestion = questionService.findMinQuestion(number, sid);
			}else if("down".equals(op)) {
				modificationQuestion = questionService.findMaxQuestion(number, sid);
			}
			
			if(modificationQuestion != null){
				List<Question> questions = new ArrayList<Question>();
				Integer modificationNumber = modificationQuestion.getNumber();
				modificationQuestion.setNumber(number);
				originalQuestion.setNumber(modificationNumber);
				questions.add(originalQuestion);
				questions.add(modificationQuestion);
				questionService.updateQuestionBatchs(questions);
			}
		}

		Question qut = new Question();
		qut.setSid(sid);
		Page<Question> page = questionService.findPage(new Page<Question>(request, response), qut); 
		model.addAttribute("page", page);
		model.addAttribute("sid", sid);
		Scale scale = scaleService.get(sid);
		Integer inside =  scale.getInside();
		model.addAttribute("inside", inside);
		return "modules/jzmk/qaset";
		
	}
	
	@RequiresPermissions("jzmk:question:edit")
	@RequestMapping(value = "delete")
	public String delete(Question question, Integer sid, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		Integer questionId = question.getId();
		if(questionId != null) {
			Integer number = question.getNumber();
			
			question = questionService.get(questionId);
			Question que = new Question();
			que.setSid(sid);
			que.setNumber(number);
			List<Question> lastQuestionLists = questionService.findLastAllQuestions(question);
			
			int id = questionService.deleteQuestion(questionId);
			if(id > 0) {
				Answer answer = new Answer();
				answer.setQuestionId(questionId);
				answerService.delete(answer);
			}
			
			int i = 1, size = lastQuestionLists.size();
			for(; i < size; i++) {
				Question ques = lastQuestionLists.get(i);
				ques.setNumber(number + (i - 1));
				questionService.save(ques);
			}
			
		}
		
		Question qut = new Question();
		qut.setSid(sid);
		Page<Question> page = questionService.findPage(new Page<Question>(request, response), qut);
		model.addAttribute("page", page);
		model.addAttribute("sid", sid);
		Scale scale = scaleService.get(sid);
		Integer inside =  scale.getInside();
		model.addAttribute("inside", inside);
		addMessage(redirectAttributes, "删除量表条目成功");
		return "modules/jzmk/qaset";
	}

}