/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.relax.entity.Music;
import com.ambition.agile.modules.relax.entity.MusicCatalog;
import com.ambition.agile.modules.relax.service.MusicCatalogService;
import com.ambition.agile.modules.relax.service.MusicService;

/**
 * 音乐Controller
 * @author harry
 * @version 2017-06-25
 */
@Controller
@RequestMapping(value = "${adminPath}/relax/music")
public class MusicController extends BaseController {

	@Autowired
	private MusicService musicService;
	
	@Autowired
	private MusicCatalogService musicCatalogService;

	@RequiresPermissions("relax:music:view")
	@RequestMapping(value = {"list", ""})
	public String list(Music music, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Music> page = musicService.findPage(new Page<Music>(request, response), music);
		model.addAttribute("page", page);
		model.addAttribute("music", music);
		return "modules/relax/musicList";
	}

	@RequiresPermissions("relax:music:edit")
	@RequestMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file) {
		return musicService.transferTo(file);
	}
	
	@RequiresPermissions("relax:music:view")
	@RequestMapping(value = "form")
	public String form(@RequestParam(required=false) Integer id,Music music, 
			Model model,HttpServletRequest request) {
		
		if (null != id  && id>0){
			music = musicService.get(id);
		}
		if (music == null){
			music = new Music();
		}
		
		if(music != null) {
			model.addAttribute("fileAbsolutePath", musicService.getOriginalFilePath(music.getPath()));
		}
		model.addAttribute("fileAbsPath", musicService.getOriginalFilePath());
		
		String musicCatalogId = (String)request.getParameter("music.musicCatalogId");
		if( StringUtils.isNotEmpty(musicCatalogId) ){
			
			MusicCatalog musicCatalog = musicCatalogService.get(Integer.parseInt(musicCatalogId));
			if(null != musicCatalog ){
				if(null != musicCatalog.getId()){
					music.setMusicCatalogId(musicCatalog.getId());
				}
				if(StringUtils.isNotEmpty(musicCatalog.getParentIds())){
					music.setMusicCatalogParentids(musicCatalog.getParentIds());
				}
				if(StringUtils.isNotEmpty(musicCatalog.getName())){
					music.setMusicCatalogName(musicCatalog.getName());
				}
				
			}
		}
		model.addAttribute("music", music);
		return "modules/relax/musicForm";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "view")
	public String view(@RequestParam(required=false) Integer id,Music music, Model model,HttpServletRequest request) {
		if (null != id  && id>0){
			music = musicService.get(id);
		}
		if (music == null){
			music = new Music();
		}
		
		if(music != null) {
			model.addAttribute("fileAbsolutePath", musicService.getOriginalFilePath(music.getPath()));
		}
		model.addAttribute("fileAbsPath", musicService.getOriginalFilePath());
		
		String musicCatalogId = (String)request.getParameter("music.musicCatalogId");
		if( StringUtils.isNotEmpty(musicCatalogId) ){
			
			MusicCatalog musicCatalog = musicCatalogService.get(Integer.parseInt(musicCatalogId));
			if(null != musicCatalog ){
				if(null != musicCatalog.getId()){
					music.setMusicCatalogId(musicCatalog.getId());
				}
				if(StringUtils.isNotEmpty(musicCatalog.getParentIds())){
					music.setMusicCatalogParentids(musicCatalog.getParentIds());
				}
				if(StringUtils.isNotEmpty(musicCatalog.getName())){
					music.setMusicCatalogName(musicCatalog.getName());
				}
				
			}
		}
		model.addAttribute("music", music);
		return "modules/relax/musicView";
	}

	@RequiresPermissions("relax:music:edit")
	@RequestMapping(value = "save")
	public String save(@RequestParam(required=false) Integer id,Music music, Model model, 
			RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, music)){
			return form(id,music, model,request);
		}
		musicService.save(music);
		addMessage(redirectAttributes, "保存音乐成功");
		return "redirect:"+Global.getAdminPath()+"/relax/music/?repage&music.musicCatalogParentids="+music.getMusicCatalogParentids()+"&music.musicCatalogId="+music.getMusicCatalogId();
	}
	
	@RequiresPermissions("relax:music:edit")
	@RequestMapping(value = "delete")
	public String delete(@RequestParam(required=false) String id,Music music, RedirectAttributes redirectAttributes) {
		
		music = null;
		if (StringUtils.isNotBlank(id)){
			music = musicService.get(Integer.parseInt(id));
		}
		if (music == null){
			music = new Music();
		}
		musicService.delete(music);
		addMessage(redirectAttributes, "删除音乐成功");
		return "redirect:"+Global.getAdminPath()+"/relax/music?musicCatalogParentids="+music.getMusicCatalogParentids()+"&musicCatalogId="+music.getMusicCatalogId();
	}

}