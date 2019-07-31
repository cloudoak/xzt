/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ambition.agile.common.persistence.TreeDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Orgization;

/**
 * 机构DAO接口
 * @author harry
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
	
	Integer findMaxSort(Office office);

	/**
	 * 查询机构列表
	 * @param office
	 * @return
	 */
	List<Office> findOrgList(Office office);
	
	Office findByParentId(Office office);

	/**
	 * 查询机构下的部门
	 * @param orgId
	 * @return
	 */
	List<Office> findOfficeTree(Integer orgId);
	
	
	List<Orgization> findAllOrgsByParentId(Orgization org);
	
	
	Integer deleteByParentId(Office office);
	
}
