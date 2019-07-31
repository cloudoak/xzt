/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.testh.web;

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
import com.ambition.agile.modules.testh.entity.TestH;
import com.ambition.agile.modules.testh.service.TestHService;

/**
 * cuidController
 * @author harry
 * @version 2017-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/testh/testH")
public class TestHController extends BaseController {

	@Autowired
	private TestHService testHService;
	
	@ModelAttribute
	public TestH get(@RequestParam(required=false) Integer id) {
		TestH entity = null;
		if (id!=null&&id>0){
			entity = testHService.get(id);
		}
		if (entity == null){
			entity = new TestH();
		}
		return entity;
	}
	
	@RequiresPermissions("testh:testH:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestH testH, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestH> page = testHService.findPage(new Page<TestH>(request, response), testH); 
		model.addAttribute("page", page);
		return "modules/testh/testHList";
	}

	@RequiresPermissions("testh:testH:view")
	@RequestMapping(value = "form")
	public String form(TestH testH, Model model) {
		model.addAttribute("testH", testH);
		return "modules/testh/testHForm";
	}

	@RequiresPermissions("testh:testH:edit")
	@RequestMapping(value = "save")
	public String save(TestH testH, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testH)){
			return form(testH, model);
		}
		testHService.save(testH);
		addMessage(redirectAttributes, "保存测试功能成功");
		return "redirect:"+Global.getAdminPath()+"/testh/testH/?repage";
	}
	
	@RequiresPermissions("testh:testH:del")
	@RequestMapping(value = "delete")
	public String delete(TestH testH, RedirectAttributes redirectAttributes) {
		testHService.delete(testH);
		addMessage(redirectAttributes, "删除测试功能成功");
		return "redirect:"+Global.getAdminPath()+"/testh/testH/?repage";
	}

}