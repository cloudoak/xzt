/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.modules.sys.dao.ThirdPartyDao;
import com.ambition.agile.modules.sys.entity.ThirdParty;

/**
 * 第三方Service
 * @author OAK
 * @version 2017/12/05
 */
@Service
@Transactional(readOnly = true)
public class ThirdPartyService {
	
	@Autowired
	ThirdPartyDao thirdPartyDao;
	
	public List<ThirdParty> findAllProvinces(){
		return thirdPartyDao.findAllProvinces();
	}
	
	public List<ThirdParty> findByProvinceId(String provinceId){
		return thirdPartyDao.findByProvinceId(provinceId);
	}

	public List<ThirdParty> findByCityId(String cityId){
		return thirdPartyDao.findByCityId(cityId);
	}
	
	public List<ThirdParty> findByProvinceName(String name){
		return thirdPartyDao.findByProvinceName(name);
	}
	
	public List<ThirdParty> findByCityName(String provinceId, String name){
		return thirdPartyDao.findByCityName(provinceId, name);
	}
	
	public List<ThirdParty> findByAreaName(String cityId, String name){
		return thirdPartyDao.findByAreaName(cityId, name);
	}
	
	public Map<String,Object> getFullAddressByAreaCode(String areaCode)
	{
		return thirdPartyDao.getFullAddressByAreaCode(areaCode);
	}
}
