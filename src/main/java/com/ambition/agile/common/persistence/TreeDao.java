/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.common.persistence;

import java.util.List;

/**
 * DAO支持类实现
 * @author harry
 * @version 2017-05-16
 * @param <T>
 */
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

	/**
	 * 找到所有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
}