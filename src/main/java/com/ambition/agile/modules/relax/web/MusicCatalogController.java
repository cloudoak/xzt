/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.web;

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
import com.ambition.agile.modules.relax.entity.MusicCatalog;
import com.ambition.agile.modules.relax.service.MusicCatalogService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 音乐分类Controller
 * @author harry
 * @version 2017-06-25
 */
@Controller
@RequestMapping(value = "${adminPath}/relax/musicCatalog")
public class MusicCatalogController extends BaseController {

	@Autowired
	private MusicCatalogService musicCatalogService;
	
	@ModelAttribute
	public MusicCatalog get(@RequestParam(required=false) Integer id) {
		MusicCatalog entity = null;
		if (id!=null&&id>0){
			entity = musicCatalogService.get(id);
		}
		if (entity == null){
			entity = new MusicCatalog();
		}
		return entity;
	}
	
	@RequiresPermissions("relax:musicCatalog:view")
	@RequestMapping(value = {"list", ""})
	public String list(MusicCatalog musicCatalog, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<MusicCatalog> list = musicCatalogService.findList(musicCatalog); 
		model.addAttribute("list", list);
		return "modules/relax/musicCatalogList";
	}

	@RequiresPermissions("relax:musicCatalog:view")
	@RequestMapping(value = "form")
	public String form(MusicCatalog musicCatalog, Model model) {
		if (musicCatalog.getParent()!=null && musicCatalog.getParent().getId()!=null && musicCatalog.getParent().getId()>0){
			musicCatalog.setParent(musicCatalogService.get(musicCatalog.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (musicCatalog.getId()==null || musicCatalog.getId()==0){
				MusicCatalog musicCatalogChild = new MusicCatalog();
				musicCatalogChild.setParent(new MusicCatalog(musicCatalog.getParent().getId()));
				List<MusicCatalog> list = musicCatalogService.findList(musicCatalog); 
				if (list.size() > 0){
					musicCatalog.setSort(list.get(list.size()-1).getSort());
					if (musicCatalog.getSort() != null){
						musicCatalog.setSort(musicCatalog.getSort() + 30);
					}
				}
			}
		}
		if (musicCatalog.getSort() == null){
			musicCatalog.setSort(30);
		}
		model.addAttribute("musicCatalog", musicCatalog);
		return "modules/relax/musicCatalogForm";
	}

	@RequiresPermissions("relax:musicCatalog:edit")
	@RequestMapping(value = "save")
	public String save(MusicCatalog musicCatalog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, musicCatalog)){
			return form(musicCatalog, model);
		}
		musicCatalogService.save(musicCatalog);
		addMessage(redirectAttributes, "保存音乐分类成功");
		return "redirect:"+Global.getAdminPath()+"/relax/musicCatalog/?repage";
	}
	
	@RequiresPermissions("relax:musicCatalog:edit")
	@RequestMapping(value = "delete")
	public String delete(MusicCatalog musicCatalog, RedirectAttributes redirectAttributes) {
		musicCatalogService.delete(musicCatalog);
		addMessage(redirectAttributes, "删除音乐分类成功");
		return "redirect:"+Global.getAdminPath()+"/relax/musicCatalog/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<MusicCatalog> list = musicCatalogService.findList(new MusicCatalog());
		for (int i=0; i<list.size(); i++){
			MusicCatalog e = list.get(i);
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
	
}