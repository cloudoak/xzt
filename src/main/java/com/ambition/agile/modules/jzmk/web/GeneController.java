/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.FactorValue;
import com.ambition.agile.modules.jzmk.entity.Gene;
import com.ambition.agile.modules.jzmk.entity.Question;
import com.ambition.agile.modules.jzmk.entity.Scale;
import com.ambition.agile.modules.jzmk.service.AnswerService;
import com.ambition.agile.modules.jzmk.service.GeneService;
import com.ambition.agile.modules.jzmk.service.QuestionService;
import com.ambition.agile.modules.jzmk.service.ScaleService;

/**
 * 因子设置Controller
 * @author OAK
 * @version 2017-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/gene")
public class GeneController extends BaseController {

	@Autowired
	private GeneService geneService;
	
	@Autowired
	private ScaleService scaleService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@ModelAttribute
	public Gene get(@RequestParam(required=false) Integer id) {
		Gene entity = null;
		if (id!=null&&id>0){
			entity = geneService.get(id);
		}
		if (entity == null){
			entity = new Gene();
		}
		return entity;
	}
	
	@RequestMapping("checkMaxValue")
	@ResponseBody
	public int checkMaxValue(BigDecimal maxValue, String questionIds){
		BigDecimal totalScores = answerService.findTotalScores(questionIds);
		if(totalScores.compareTo(maxValue) < 0) {
			return 0;
		}
		return 1;
	}
	
	@RequiresPermissions("jzmk:gene:view")
	@RequestMapping(value = "list")
	public String list(Gene gene, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Gene> page = geneService.findPage(new Page<Gene>(request, response), gene); 
		model.addAttribute("page", page);
		Scale scale=new Scale();
		scale.setDelFlag("0");
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("tid", gene.getTid());
		model.addAttribute("scales", scales);
		return "modules/jzmk/geneList";
	}
	
	/**
	 * 进入因子列表页面
	 * @param gene
	 * @param sid
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:gene:view")
	@RequestMapping(value = "geneList")
	public String geneList(Gene gene, Integer tid, HttpServletRequest request, HttpServletResponse response, Model model) {
		gene.setTid(tid);
		Page<Gene> page = geneService.findPage(new Page<Gene>(request, response), gene); 
		model.addAttribute("page", page);
		model.addAttribute("tid", tid);
		return "modules/jzmk/geneList";
	}

	@RequiresPermissions("jzmk:gene:view")
	@RequestMapping(value = "form")
	public String form(Gene gene, Integer tid, Integer op, Model model) {
		model.addAttribute("gene", gene);
 		if(op==1){
 			Question question=new Question();
 			question.setSid(tid);
 			List<Question> questions = questionService.findByParentIdRoot(question);
 			model.addAttribute("questions", questions);
 		}
 		model.addAttribute("tid", tid);
		if(op==2){
			Gene gene2=new Gene();
			gene2.setTid(tid);
			gene2.setTypeId(1);
			List<Gene> list = geneService.findList(gene2);
			model.addAttribute("geneList", list);
			return "modules/jzmk/geneForm2";
		}
		return "modules/jzmk/geneForm";
	}

	/**
	 * 保存单因子
	 * @param gene
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("jzmk:gene:edit")
	@RequestMapping(value = "save")
	public String save(Gene gene, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, gene)){
			return form(gene, null, null, model);
		}
		
		Gene g1 = new Gene();
		g1.setTid(gene.getTid());
		Integer number = geneService.findMaxNumber(g1.getTid());
		gene.setNumber(number + 1);
		gene.setTypeId(1);
		
		gene.setFormula(gene.getQuestion());
		
		geneService.save(gene);
		addMessage(redirectAttributes, "保存因子成功");
		
		Gene g = new Gene();
		g.setTid(gene.getTid());
		Page<Gene> page = geneService.findPage(new Page<Gene>(request, response), g); 
		model.addAttribute("page", page);
		model.addAttribute("tid", g.getTid());
		return "modules/jzmk/geneList";
	}
	
	/**
	 * 保存复合因子
	 * @param gene
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("jzmk:gene:edit")
	@RequestMapping(value = "saveMoreGene")
	public String saveMoreGene(Gene gene, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, gene)){
			return form(gene, null, null, model);
		}
		
		Gene g1 = new Gene();
		g1.setTid(gene.getTid());
		List<Gene> list = geneService.findList(g1);
		gene.setNumber(list.size()+1);
		gene.setTypeId(2);
		
		/*String idsStr = StringFunctionUtils.replaceWebJsp(gene.getQuestion());
		JSONArray jsonArray = JSONArray.parseArray(idsStr);
		StringBuffer stringBuffer=new StringBuffer();
		for (Object obj : jsonArray) {
			stringBuffer.append(obj.toString()+",");
		}
		String qids = stringBuffer.toString().substring(0, stringBuffer.toString().length()-1);
		gene.setQuestion(jsonArray.size()+"项:"+qids);
		
		gene.setFormula(qids);*/
		
		geneService.save(gene);
		addMessage(redirectAttributes, "保存因子成功");
		
		Gene g = new Gene();
		g.setTid(gene.getTid());
		Page<Gene> page = geneService.findPage(new Page<Gene>(request, response), g); 
		model.addAttribute("page", page);
		model.addAttribute("tid", g.getTid());
		return "modules/jzmk/geneList";
	}
	
	@RequiresPermissions("jzmk:gene:edit")
	@RequestMapping(value = "update")
	public String updateNumber(String op, Integer id, Integer tid, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		Gene originalGene = null, modificationGene = null;
		boolean isModfied = (id != null);
		if(isModfied){
			originalGene = geneService.get(id);
			Integer number = originalGene.getNumber();
			
			if("up".equals(op)) {
				modificationGene = geneService.findMinGene(number, tid);
			}else if("down".equals(op)) {
				modificationGene = geneService.findMaxGene(number, tid);
			}
			
			if(modificationGene != null) {
				List<Gene> gens = new ArrayList<Gene>();
				Integer modificationNumber = modificationGene.getNumber();
				modificationGene.setNumber(number);
				originalGene.setNumber(modificationNumber);
				gens.add(originalGene);
				gens.add(modificationGene);
				geneService.updateGeneBatchs(gens);
			}
		}
		
		Page<Gene> page = geneService.findPage(new Page<Gene>(request, response), tid); 
		model.addAttribute("page", page);
		model.addAttribute("tid", tid);
		return "modules/jzmk/geneList";
	}
	
	@RequiresPermissions("jzmk:gene:edit")
	@RequestMapping(value = "delete")
	public String delete(Gene gene, Integer tid, RedirectAttributes redirectAttributes) {
		geneService.delete(gene);
		addMessage(redirectAttributes, "删除因子成功");
		Gene gen = new Gene();
		gen.setTid(tid);
		List<Gene> list = geneService.findList(gen);
		int i = 0, size = list.size();
		for(; i < size; i++) {
			Gene ge = list.get(i);
			if(ge.getNumber() == (i + 1)) {
				continue;
			}
			ge.setNumber(i+1);
			geneService.save(ge);
		}
		return "redirect:"+Global.getAdminPath()+"/jzmk/gene/list?tid=" + gen.getTid();
	}

}