/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.common.service.FileService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.relax.dao.MusicCatalogDao;
import com.ambition.agile.modules.relax.dao.MusicDao;
import com.ambition.agile.modules.relax.entity.Music;
import com.ambition.agile.modules.relax.entity.MusicCatalog;

/**
 * 音乐Service
 * @author harry
 * @version 2017-06-25
 */
@Service
@Transactional(readOnly = true)
public class MusicService extends FileService<MusicDao, Music> {

	@Autowired
	private MusicCatalogDao musicCatalogDao;
	
	public Music get(Integer id) {
		return super.get(id);
	}
	
	public List<Music> findList(Music music) {
		return super.findList(music);
	}
	
	@Transactional(readOnly = false)
	public Page<Music> findPage(Page<Music> page, Music music) {
		// 更新过期的权重，间隔为“6”个小时
		
//		DetachedCriteria dc = dao.createDetachedCriteria();
//		dc.createAlias("category", "category");
//		dc.createAlias("category.site", "category.site"); 
		// && !Category.isRoot(music.getMusicCatalogId())
		System.out.println("#############"+music.getMusicCatalogParentids());
		if (StringUtils.isNotEmpty(music.getMusicCatalogParentids())
				){
//			Category category = categoryDao.get(music.getCategory().getId());
//			if (category==null){
//				category = new Category();
//			}
//			category.setParentIds(category.getId());
//			category.setSite(category.getSite());
			//music.setCategory(category);
			//music.setMusicCatalogId(musicCatalogId);
		}
		else{
			music.setMusicCatalogParentids("0,");
			//music.setMusicCatalogId("0");
		}
//		if (StringUtils.isBlank(page.getOrderBy())){
//			page.setOrderBy("a.weight,a.update_date desc");
//		}
//		return dao.find(page, dc);
	//	article.getSqlMap().put("dsf", dataScopeFilter(article.getCurrentUser(), "o", "u"));
		return super.findPage(page, music);
		
	}
	
	@Transactional(readOnly = false)
	public Page<Music> findPage(Page<Music> page, Music music, boolean isDataScopeFilter) {
		// 更新过期的权重，间隔为“6”个小时
		/*
		Date updateExpiredWeightDate =  (Date)CacheUtils.get("updateExpiredWeightDateByArticle");
		if (updateExpired
		WeightDate == null || (updateExpiredWeightDate != null 
				&& updateExpiredWeightDate.getTime() < new Date().getTime())){
			//dao.updateExpiredWeight(article);
			//CacheUtils.put("updateExpiredWeightDateByArticle", DateUtils.addHours(new Date(), 6));
		}*/
//		DetachedCriteria dc = dao.createDetachedCriteria();
//		dc.createAlias("category", "category");
//		dc.createAlias("category.site", "category.site"); 
		// && !Category.isRoot(music.getMusicCatalogId())
		System.out.println("#############"+music.getMusicCatalogParentids());
		if (StringUtils.isNotEmpty(music.getMusicCatalogParentids())
				){
//			Category category = categoryDao.get(music.getCategory().getId());
//			if (category==null){
//				category = new Category();
//			}
//			category.setParentIds(category.getId());
//			category.setSite(category.getSite());
			//music.setCategory(category);
			//music.setMusicCatalogId(musicCatalogId);
		}
		else{
			music.setMusicCatalogParentids("0,");
			//music.setMusicCatalogId("0");
		}
//		if (StringUtils.isBlank(page.getOrderBy())){
//			page.setOrderBy("a.weight,a.update_date desc");
//		}
//		return dao.find(page, dc);
	//	article.getSqlMap().put("dsf", dataScopeFilter(article.getCurrentUser(), "o", "u"));
		return super.findPage(page, music);
		
	}
	
//	public Page<Music> findPage(Page<Music> page, Music music) {
//		return super.findPage(page, music);
//	}
	
	@Transactional(readOnly = false)
	public Integer save(Music music) {
		
		if(null != music && music.getMusicCatalogId()!=null && music.getMusicCatalogId()>0){
			MusicCatalog  musicCatalog = musicCatalogDao.get(music.getMusicCatalogId());
			if(null != musicCatalog && musicCatalog.getId()!=null && musicCatalog.getId()>0){
				music.setMusicCatalogParentids(musicCatalog.getParentIds());
			}
		}
		return super.save(music);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Music music) {
		return super.delete(music);
	}
	
}