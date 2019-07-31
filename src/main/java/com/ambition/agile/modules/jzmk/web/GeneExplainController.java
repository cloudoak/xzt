/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.math.BigDecimal;
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
import com.ambition.agile.modules.jzmk.entity.GeneExplain;
import com.ambition.agile.modules.jzmk.entity.Sections;
import com.ambition.agile.modules.jzmk.service.GeneExplainService;
import com.ambition.agile.modules.jzmk.service.GeneService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 因子解释Controller
 * @author OAK
 * @version 2017-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/geneExplain")
public class GeneExplainController extends BaseController {

	@Autowired
	private GeneExplainService geneExplainService;
	
	@Autowired
	private GeneService geneService;
	
	@ModelAttribute
	public GeneExplain get(@RequestParam(required=false) Integer id) {
		GeneExplain entity = null;
		if (id!=null&&id>0){
			entity = geneExplainService.get(id);
		}
		if (entity == null){
			entity = new GeneExplain();
		}
		return entity;
	}
	
	/**
	 * 因子解释列表
	 * @param geneExplain
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:geneExplain:view")
	@RequestMapping(value = "list")
	public String list(GeneExplain geneExplain, Integer tid, Integer gid, HttpServletRequest request, HttpServletResponse response, Model model) {
		geneExplain.setTid(tid);
		Page<GeneExplain> page = geneExplainService.findPage(new Page<GeneExplain>(request, response), geneExplain); 
		model.addAttribute("page", page);
		model.addAttribute("tid", geneExplain.getTid());
		return "modules/jzmk/geneExplainList";
	}
	
	@RequestMapping("checkMinValue")
	@ResponseBody
	public int checkMinValue(BigDecimal minValue, Integer gid){
		FactorValue factorValue = geneExplainService.getFactorValue(gid);
		BigDecimal mValue = factorValue.getMinValue();
		BigDecimal maxValue = factorValue.getMaxValue();
		if(mValue.compareTo(minValue) > 0  || maxValue.compareTo(minValue) < 0) {
			return 0;
		}
		return 1;
	}
	
	@RequestMapping("checkMaxValue")
	@ResponseBody
	public int checkMaxValue(BigDecimal maxValue, Integer gid){
		FactorValue factorValue = geneExplainService.getFactorValue(gid);
		BigDecimal genMaxValue = factorValue.getMaxValue();
		if(genMaxValue.compareTo(maxValue) < 0) {
			return 0;
		}
		return 1;
	}

	/**
	 * form表单 因子解释
	 * @param geneExplain
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:geneExplain:view")
	@RequestMapping(value = "form")
	public String form(GeneExplain geneExplain, Model model) {
		User user = UserUtils.getUser();
		model.addAttribute("geneExplain", geneExplain);
		Gene gene=new Gene();
		gene.setDelFlag("0");
		gene.setTid(geneExplain.getTid());
		gene.setOrgId(user.getCompany().getId());
		List<Gene> geneList = geneService.findList(gene);
		if(geneExplain.getGid() == null && geneList != null && geneList.size() > 0) {
			Gene g = geneList.get(0);
			geneExplain.setGid(g.getId());
		}
		List<Sections> sections = geneExplainService.getSections(geneExplain.getTid(), geneExplain.getGid());
		model.addAttribute("sections", sections);
		model.addAttribute("geneList", geneList);
		model.addAttribute("tid", geneExplain.getTid());
		return "modules/jzmk/geneExplainForm";
	}

	@RequiresPermissions("jzmk:geneExplain:edit")
	@RequestMapping(value = "save")
	public String save(GeneExplain geneExplain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, geneExplain)){
			return form(geneExplain, model);
		}
		String content = StringFunctionUtils.replaceWebJsp(geneExplain.getGeneExplain());
		geneExplain.setGeneExplain(content);
		geneExplainService.save(geneExplain);
		addMessage(redirectAttributes, "保存因子解释成功");
		model.addAttribute("tid", geneExplain.getTid());
		return "redirect:"+Global.getAdminPath()+"/jzmk/geneExplain/list?tid="+geneExplain.getTid();
	}
	
	@RequiresPermissions("jzmk:geneExplain:edit")
	@RequestMapping(value = "delete")
	public String delete(GeneExplain geneExplain, RedirectAttributes redirectAttributes) {
		geneExplainService.delete(geneExplain);
		addMessage(redirectAttributes, "删除因子解释成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/geneExplain/list?tid=" + geneExplain.getTid();
	}
	
}