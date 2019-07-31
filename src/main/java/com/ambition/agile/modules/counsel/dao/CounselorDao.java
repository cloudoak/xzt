/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.entity.CounselorOffice;

/**
 * 咨询师DAO接口
 * @author harry
 * @version 2017-07-03
 */
@MyBatisDao
public interface CounselorDao extends CrudDao<Counselor> {
	
	public Counselor getUserByUserId(Integer userId);
	
	List<Counselor> findTop3List(Counselor counselor);
	
	boolean updateCounselor(Counselor counselor);
	
	int updateIndexFlag(@Param("indexFlag") String indexFlag, @Param("id") Integer id);
	
	/**
	 * 维护咨询师与菜单权限关系
	 * @param counselor
	 * @return
	 */
	public int deleteCounselorMenu(Counselor counselor);

	public int insertCounselorMenu(Counselor counselor);
	
	/**
	 * 维护咨询师与公司部门关系
	 * @param counselor
	 * @return
	 */
	public int deleteCounselorOffice(Counselor counselor);

	public int insertCounselorOffice(CounselorOffice counselorOffice);
	
	/**
	 * 根据咨询师Id查询咨询师机构
	 * @param counselorId
	 * @return
	 */
	public List<CounselorOffice> selectCounselorOffice(@Param("counselorId") Integer counselorId);

	/**
	 * 根据咨询师Id查询咨询师菜单
	 * @param counselorId
	 * @return
	 */
	public List<Integer> selectCounselorMenu(Integer counselorId);

	public int addCounselorMenu(@Param("counselorId")Integer counselorId, @Param("menuId")Integer menuId);
}