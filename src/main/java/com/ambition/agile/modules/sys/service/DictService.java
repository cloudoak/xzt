/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.common.utils.CacheUtils;
import com.ambition.agile.modules.sys.dao.DictDao;
import com.ambition.agile.modules.sys.entity.Dict;
import com.ambition.agile.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author harry
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public Integer save(Dict dict) {
		Integer b = super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		return b;
	}

	@Transactional(readOnly = false)
	public Integer delete(Dict dict) {
		Integer b = super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		return b;
	}

}
