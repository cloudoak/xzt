/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.TreeService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.course.dao.CourseCatalogDao;
import com.ambition.agile.modules.course.entity.CourseCatalog;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.google.common.collect.Sets;

/**
 * 课件分类Service
 * @author harry
 * @version 2017-06-24
 */
@Service
@Transactional(readOnly = true)
public class CourseCatalogService extends TreeService<CourseCatalogDao, CourseCatalog> {

	public static final String CACHE_COURSECATALOG_LIST = "courseCatalogList";
	
	public CourseCatalog get(Integer id) {
		return super.get(id);
	}
	
	public List<CourseCatalog> findList(CourseCatalog courseCatalog) {
		if (StringUtils.isNotBlank(courseCatalog.getParentIds())){
			courseCatalog.setParentIds(","+courseCatalog.getParentIds()+",");
		}
		return super.findList(courseCatalog);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CourseCatalog courseCatalog) {
		return super.save(courseCatalog);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CourseCatalog courseCatalog) {
		return super.delete(courseCatalog);
	}
	
	@SuppressWarnings("unchecked")
	public List<CourseCatalog> findByUser(boolean isCurrentSite, String module){
		List<CourseCatalog> list = (List<CourseCatalog>)UserUtils.getCache(CACHE_COURSECATALOG_LIST);
		if (list == null){
			User user = UserUtils.getUser();
			CourseCatalog courseCategory = new CourseCatalog();
			courseCategory.getSqlMap().put("dsf", dataScopeFilter(user, "o", "u"));
			courseCategory.setParent(new CourseCatalog());
			list = dao.findList(courseCategory);
			// 将没有父节点的节点，找到父节点
			Set<Integer> parentIdSet = Sets.newHashSet();
			for (CourseCatalog e : list){
				if (e.getParent()!=null && e.getParent().getId()!=null && e.getParent().getId()>0){
					boolean isExistParent = false;
					for (CourseCatalog e2 : list){
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
			UserUtils.putCache(CACHE_COURSECATALOG_LIST, list);
		}
		return list;
	}
	
}