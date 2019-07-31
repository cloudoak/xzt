/**
 * Copyright &copy; 2012-2017 All rights reserved.
 */
package com.ambition.agile.common.filter;

import com.ambition.agile.common.utils.SpringContextHolder;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

/**
 * 页面高速缓存过滤器
 * @author Harry
 * @version 2017-8-5
 */
public class PageCachingFilter extends SimplePageCachingFilter {

	private CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
	
	@Override
	protected CacheManager getCacheManager() {
		return cacheManager;
	}
	
}
