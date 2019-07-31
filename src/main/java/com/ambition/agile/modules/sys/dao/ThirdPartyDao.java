/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.sys.entity.ThirdParty;

/**
 * 第三方DAO
 * @author OAK
 * @version 2017/12/05
 */
@MyBatisDao
public interface ThirdPartyDao {
	
	List<ThirdParty> findAllProvinces();
	
	List<ThirdParty> findByProvinceId(@Param("provinceId") String provinceId);
	
	List<ThirdParty> findByCityId(@Param("cityId") String cityId);
	
	List<ThirdParty> findByProvinceName(@Param("name") String name);
	
	List<ThirdParty> findByCityName(@Param("provinceId") String provinceId, @Param("name") String name);
	
	List<ThirdParty> findByAreaName(@Param("cityId") String cityId, @Param("name") String name);
	
	Map<String,Object> getFullAddressByAreaCode(@Param("areaCode") String areaCode);
	
}
