/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.relax.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.relax.service.MusicCatalogService;

/**
 * 音乐 管理Controller
 * @author harry
 * @version 2013-4-21
 */
@Controller
@RequestMapping(value = "${adminPath}/relax")
public class MusicIndexController extends BaseController {

	@Autowired
	private MusicCatalogService musicCatalogService;
	
	@RequiresPermissions("music:view")
	@RequestMapping(value = "/musicIndex")
	public String index() {
		return "modules/relax/musicIndex";
	}
	
	@RequiresPermissions("music:view")
	@RequestMapping(value = "musicTree")
	public String tree(Model model) {
		model.addAttribute("musicCatelogList",musicCatalogService.findByUser(true, null));
		return "modules/relax/musicTree";
	}
	
	@RequiresPermissions("music:view")
	@RequestMapping(value = "musicNone")
	public String none() {
		return "modules/relax/musicNone";
	}

}
