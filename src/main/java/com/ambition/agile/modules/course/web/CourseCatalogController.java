/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.web;

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
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.course.entity.CourseCatalog;
import com.ambition.agile.modules.course.service.CourseCatalogService;
import com.ambition.agile.modules.sys.entity.Office;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 课件分类Controller
 * @author harry
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseCatalog")
public class CourseCatalogController extends BaseController {

	@Autowired
	private CourseCatalogService courseCatalogService;
	
	@ModelAttribute
	public CourseCatalog get(@RequestParam(required=false) Integer id) {
		CourseCatalog entity = null;
		if (id!=null&&id>0){
			entity = courseCatalogService.get(id);
		}
		if (entity == null){
			entity = new CourseCatalog();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseCatalog:edit")
	@RequestMapping(value = {"list", ""})
	public String list(CourseCatalog courseCatalog, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CourseCatalog> list = courseCatalogService.findList(courseCatalog); 
		model.addAttribute("list", list);
		return "modules/course/courseCatalogList";
	}

	@RequiresPermissions("course:courseCatalog:view")
	@RequestMapping(value = "form")
	public String form(CourseCatalog courseCatalog, Model model) {
		if (courseCatalog.getParent()!=null && courseCatalog.getParent().getId()!=null && courseCatalog.getParent().getId()>0){
			courseCatalog.setParent(courseCatalogService.get(courseCatalog.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (courseCatalog.getId()!=null && courseCatalog.getId()>0){
				CourseCatalog courseCatalogChild = new CourseCatalog();
				courseCatalogChild.setParent(new CourseCatalog(courseCatalog.getParent().getId()));
				List<CourseCatalog> list = courseCatalogService.findList(courseCatalog); 
				if (list.size() > 0){
					courseCatalog.setSort(list.get(list.size()-1).getSort());
					if (courseCatalog.getSort() != null){
						courseCatalog.setSort(courseCatalog.getSort() + 30);
					}
				}
			}
		}
		if (courseCatalog.getSort() == null){
			courseCatalog.setSort(30);
		}
		model.addAttribute("courseCatalog", courseCatalog);
		return "modules/course/courseCatalogForm";
	}

	@RequiresPermissions("course:courseCatalog:edit")
	@RequestMapping(value = "save")
	public String save(CourseCatalog courseCatalog, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, courseCatalog)){
			return form(courseCatalog, model);
		}*/
		//if(null != courseCatalog && courseCatalog.getPa){
			
			
		//}
		courseCatalogService.save(courseCatalog);
		addMessage(redirectAttributes, "保存课件分类成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseCatalog/?repage";
	}
	
	@RequiresPermissions("course:courseCatalog:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseCatalog courseCatalog, RedirectAttributes redirectAttributes) {
		courseCatalogService.delete(courseCatalog);
		addMessage(redirectAttributes, "删除课件分类成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseCatalog/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CourseCatalog> list = courseCatalogService.findList(new CourseCatalog());
		for (int i=0; i<list.size(); i++){
			CourseCatalog e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 验证分类是否存在
	 * @param office
	 * @return
	 */
	@RequestMapping(value = "checkCatalog")
	@ResponseBody
	public boolean checkCatalog(@RequestParam(value="name",defaultValue="") String name,@RequestParam(value="id",defaultValue="") String id,  Model model){
		List<CourseCatalog> catalogList = courseCatalogService.findList(new CourseCatalog());
		for(CourseCatalog catalog : catalogList) {
			if(id!=null&&!id.equals(""))
			{
				if(catalog.getName().equals(name)&&(Integer.valueOf(id)!=catalog.getId())) {
					return false;
				}
			}
			else{
				if(catalog.getName().equals(name)){
					return false;
				}
			}
		}
		return true;
	}
}