/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.TreeService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.relax.dao.MusicCatalogDao;
import com.ambition.agile.modules.relax.entity.MusicCatalog;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 音乐分类Service
 * @author harry
 * @version 2017-06-25
 */
@Service
@Transactional(readOnly = true)
public class MusicCatalogService extends TreeService<MusicCatalogDao, MusicCatalog> {

	public static final String CACHE_MUSICATALOG_LIST = "musicCatalogList";
	
	public MusicCatalog get(Integer id) {
		return super.get(id);
	}
	
	public List<MusicCatalog> findList(MusicCatalog musicCatalog) {
		if (StringUtils.isNotBlank(musicCatalog.getParentIds())){
			musicCatalog.setParentIds(","+musicCatalog.getParentIds()+",");
		}
		return super.findList(musicCatalog);
	}
	
	@Transactional(readOnly = false)
	public Integer save(MusicCatalog musicCatalog) {
		return super.save(musicCatalog);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(MusicCatalog musicCatalog) {
		return super.delete(musicCatalog);
	}
	

	@SuppressWarnings("unchecked")
	public List<MusicCatalog> findByUser(boolean isCurrentSite, String module){
		
		List<MusicCatalog> list = null;//(List<MusicCatalog>)UserUtils.getCache(CACHE_MUSICATALOG_LIST);
		if (list == null){
			User user = UserUtils.getUser();
			MusicCatalog musicCatalog = new MusicCatalog();
			//category.setOffice(new Office());
			//musicCatalog.getSqlMap().put("dsf", dataScopeFilter(user, "o", "u"));
			//category.setSite(new Site());
			musicCatalog.setParent(new MusicCatalog());
			list = dao.findList(musicCatalog);
			/*
			for (MusicCatalog e : list){
				System.out.println(e.getParentId()+ "####"+e.getName());
			}
			*/
			// 将没有父节点的节点，找到父节点
			/*
			Set<String> parentIdSet = Sets.newHashSet();
			for (MusicCatalog e : list){
				if (e.getParent()!=null && StringUtils.isNotBlank(e.getParent().getId())){
					boolean isExistParent = false;
					for (MusicCatalog e2 : list){
						if (e.getParent().getId().equals(e2.getId())){
							isExistParent = true;
							break;
						}
					}
					if (!isExistParent){
						parentIdSet.add(e.getParent().getId());
					}
				}
			}
			*/
			//if (parentIdSet.size() > 0){
				//FIXME 暂且注释，用于测试
//				dc = dao.createDetachedCriteria();
//				dc.add(Restrictions.in("id", parentIdSet));
//				dc.add(Restrictions.eq("delFlag", Category.DEL_FLAG_NORMAL));
//				dc.addOrder(Order.asc("site.id")).addOrder(Order.asc("sort"));
//				list.addAll(0, dao.find(dc));
			//}
			UserUtils.putCache(CACHE_MUSICATALOG_LIST, list);
		}
		/*
		if (isCurrentSite){
			List<Category> categoryList = Lists.newArrayList(); 
			for (Category e : list){
				if (Category.isRoot(e.getId()) || (e.getSite()!=null && e.getSite().getId() !=null 
						&& e.getSite().getId().equals(Site.getCurrentSiteId()))){
					if (StringUtils.isNotEmpty(module)){
						if (module.equals(e.getModule()) || "".equals(e.getModule())){
							categoryList.add(e);
						}
					}else{
						categoryList.add(e);
					}
				}
			}
			return categoryList;
		}
		*/
		return list;
	}
	
}