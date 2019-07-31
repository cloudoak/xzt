/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

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

import com.alibaba.fastjson.JSONArray;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.Answer;
import com.ambition.agile.modules.jzmk.service.AnswerService;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 条目答案Controller
 * @author OAK
 * @version 2017-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/answer")
public class AnswerController extends BaseController {

	@Autowired
	private AnswerService answerService;
	
	@ModelAttribute
	public Answer get(@RequestParam(required=false) Integer id) {
		Answer entity = null;
		if (id!=null&&id>0){
			entity = answerService.get(id);
		}
		if (entity == null){
			entity = new Answer();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:answer:view")
	@RequestMapping(value = "list")
	public String list(Answer answer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Answer> page = answerService.findPage(new Page<Answer>(request, response), answer); 
		model.addAttribute("page", page);
		return "modules/jzmk/answerList";
	}

	/**
	 * 跳转到为条目设置答案
	 * @param ids
	 * @param answer
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("jzmk:answer:view")
	@RequestMapping(value = "form")
	public String form(String ids, Integer sid, String numbers, Answer answer, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String idsStr = StringFunctionUtils.replaceWebJsp(ids);
		JSONArray jsonArray = JSONArray.parseArray(idsStr);
		StringBuffer stringBuffer=new StringBuffer();
		for (Object obj : jsonArray) {
			stringBuffer.append(obj.toString()+",");
		}
		int size = jsonArray.size();
		
		String qids = stringBuffer.toString().substring(0, stringBuffer.toString().length()-1);
		
		String numbersStr = StringFunctionUtils.replaceWebJsp(numbers);
		JSONArray jsonNumbers = JSONArray.parseArray(numbersStr);
		StringBuffer buffer=new StringBuffer();
		for (Object obj : jsonNumbers) {
			buffer.append(obj.toString()+",");
		}
		String numberList = buffer.toString().substring(0, buffer.toString().length()-1);
		//目前逻辑只有一条条目时，会查询该条条目之前的答案
		try {
			if(size >= 1){
				//要设置答案的条目ID
				String [] questionIds = qids.split(",");
				int len = questionIds.length;
				if(len > 1) {
					List<Integer> anyCounts = answerService.findByDistinctQuestionCount(questionIds);
					
					boolean isMany = true;
					
					if(anyCounts != null && anyCounts.size() > 0) {
						for(Integer count : anyCounts) {
							if(count.intValue() != len) {
								isMany = false;
							}
						}
					}
					
					if(isMany) {
						answer.setQuestionId(Integer.valueOf(questionIds[0]));
					}else {
						answer.setQuestionId(Integer.valueOf(qids));
					}
				} else {
					answer.setQuestionId(Integer.valueOf(qids));
				}
				List<Answer> list = answerService.findList(answer);
				model.addAttribute("answerList", list);	
			}
		} catch (Exception e) {
			System.out.println("获取的条目id不是数字异常");
		}
		model.addAttribute("numberList", numberList);
		model.addAttribute("qids", qids);
		model.addAttribute("sid", sid);
		return "modules/jzmk/answerList";
	}
	
	/**
	 * 打开新增条目答案弹窗
	 * @param answer
	 * @param qids
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:answer:view")
	@RequestMapping(value = "add")
	public String addForm(Answer answer, Integer sid, String qids, Model model) {
		model.addAttribute("sid", sid);
		model.addAttribute("qids", qids);
		model.addAttribute("answer", answer);
		return "modules/jzmk/answerForm";
	}

	/**
	 * 保存条目答案选项
	 * @param answer
	 * @param model
	 * @param qids
	 * @param numbers
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:answer:edit")
	@RequestMapping(value = "save")
	public String save(Answer answer, Model model, Integer sid, String qids, String numbers, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, answer)){
			return form(adminPath, sid, qids, answer, model, null, null);
		}
		//要设置答案的条目ID
		String [] questionIds = qids.split(",");
		int i = 0, qid = 0, len = questionIds.length; 
		
		List<Integer> anyCounts = answerService.findByDistinctQuestionCount(questionIds);
		
		boolean isMany = true;
		
		if(anyCounts != null && anyCounts.size() > 0) {
			for(Integer count : anyCounts) {
				if(count.intValue() != len) {
					isMany = false;
				}
			}
		}
		
		if(!isMany && len > 1) {
			answerService.deleteByQuestionIds(qids);
		}
		
		for (i = 0; i < len; i++) {
			qid = Integer.valueOf(questionIds[i]);
			Answer answer2 = new Answer();
			if(answer.getId() != null && answer.getId() != 0) {
				answer2.setId(answer.getId());
			}
			answer2.setQuestionId(qid);
			Integer answerNo = answerService.findMaxAnswerNo(qid);
			answer2.setAnswerNo(++answerNo);
			answer2.setAnswerContent(answer.getAnswerContent());
			answer2.setScore(answer.getScore());
			answer2.setParseContent(answer.getParseContent());
			answerService.save(answer2);
		}
		answer.setQuestionId(Integer.valueOf(qid));
		List<Answer> list = answerService.findList(answer);
		model.addAttribute("answerList", list);
		addMessage(redirectAttributes, "保存答案成功");
		model.addAttribute("sid", sid);
		model.addAttribute("qids", qids);
		model.addAttribute("numberList",numbers);
		return "modules/jzmk/answerList";
	}
	
	/**
	 * 删除条目答案选项
	 * @param answer
	 * @param id
	 * @param qids
	 * @param numbers
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:answer:edit")
	@RequestMapping(value = "delete")
	public String delete(Answer answer, Integer sid, Integer id, String qids, String numbers, Model model, RedirectAttributes redirectAttributes) {
		if(id != null){
			Integer answerNo = answer.getAnswerNo();
			String [] questionIds = qids.split(",");
			int len = questionIds.length;
			if(len > 1) {
				int flags = answerService.deleteByQuestionIdsAndAnswerNo(answerNo, questionIds);
				if(flags > 0) {
					for(String questionId : questionIds) {
						Answer lastAnswer = new Answer();
						lastAnswer.setQuestionId(Integer.valueOf(questionId));
						lastAnswer.setAnswerNo(answerNo);
						List<Answer> lastAnswerLists = answerService.findLastAllAnswers(lastAnswer);
						int i = 0, size = lastAnswerLists.size();
						for(; i < size; i++) {
							Answer an = lastAnswerLists.get(i);
							an.setAnswerNo(answerNo + i);
							answerService.save(an);
						}
					}
				}
			}else {
				answerService.delete(answer);
				Integer questionId = answer.getQuestionId();
				Answer lastAnswer = new Answer();
				lastAnswer.setQuestionId(questionId);
				lastAnswer.setAnswerNo(answerNo);
				List<Answer> lastAnswerLists = answerService.findLastAllAnswers(lastAnswer);
				int i = 0, size = lastAnswerLists.size();
				for(; i < size; i++) {
					Answer ans = lastAnswerLists.get(i);
					ans.setAnswerNo(answerNo + i);
					answerService.save(ans);
				}
			}
		}
		
		model.addAttribute("answerList", answerService.findList(answer.getQuestionId()));
		model.addAttribute("sid", sid);
		model.addAttribute("qids", qids);
		model.addAttribute("numberList",numbers);
		addMessage(redirectAttributes, "删除答案成功");
		return "modules/jzmk/answerList";
	}
	
}