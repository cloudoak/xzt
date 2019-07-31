/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.web;

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

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.sys.constant.DictConstant;
import com.ambition.agile.modules.sys.entity.Dict;
import com.ambition.agile.modules.sys.service.DictService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 字典Controller
 * @author harry
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;
	
	@ModelAttribute
	public Dict get(@RequestParam(required=false) Integer id) {
		if (id!=null&&id>0){
			return dictService.get(id);
		}else{
			return new Dict();
		}
	}
	
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = {"list", ""})
	public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> typeList = dictService.findTypeList();
		model.addAttribute("typeList", typeList);
        Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), dict); 
        model.addAttribute("page", page);
		return "modules/sys/dictList";
	}

	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "form")
	public String form(Dict dict, Model model) {
		model.addAttribute("dict", dict);
		return "modules/sys/dictForm";
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(Dict dict, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/dict/?repage&type="+dict.getType();
		}
		if (!beanValidator(model, dict)){
			return form(dict, model);
		}
		dictService.save(dict);
		addMessage(redirectAttributes, "保存字典'" + dict.getLabel() + "'成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type="+dict.getType();
	}
	
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "delete")
	public String delete(Dict dict, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/dict/?repage";
		}
		dictService.delete(dict);
		addMessage(redirectAttributes, "删除字典成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type="+dict.getType();
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findList(dict);
		for (int i=0; i<list.size(); i++){
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Dict> listData(@RequestParam(required=false) String type) {
		Dict dict = new Dict();
		dict.setType(type);
		return dictService.findList(dict);
	}
	
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = {"listQuestionType", ""})
	public String listQuestionType(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		dict.setType(DictConstant.QUESTION_TYPE);
		List<String> typeList = dictService.findTypeList();
		model.addAttribute("typeList", typeList);
        Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), dict); 
        model.addAttribute("page", page);
		return "modules/sys/dictListQuestionType";
	}
	
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "toQuestionType")
	public String toQuestionType(Dict dict, Model model) {
		model.addAttribute("dict", dict);
		return "modules/sys/dictFormQuestionType";
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "saveQuestionType")//@Valid 
	public String saveQuestionType(Dict dict, Model model, RedirectAttributes redirectAttributes) {
//		if(Global.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//			return "redirect:" + adminPath + "/sys/dict/listQuestionType?repage&type="+dict.getType();
//		}
		if (!beanValidator(model, dict)){
			model.addAttribute("dict", dict);
			return "modules/sys/toQuestionType";
		}
		dictService.save(dict);
		addMessage(redirectAttributes, "保存问题类型'" + dict.getLabel() + "'成功");
		return "redirect:" + adminPath + "/sys/dict/listQuestionType?repage&type="+dict.getType();
	}
	
	
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "deleteQuestionType")
	public String deleteQuestionType(Dict dict, RedirectAttributes redirectAttributes) {
		/*
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/dict/?repage";
		}
		*/
		dictService.delete(dict);
		addMessage(redirectAttributes, "删除问题类型成功");
		return "redirect:" + adminPath + "/sys/dict/listQuestionType?repage&type="+dict.getType();
	}

}
