/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.service;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.relax.dao.HeartCafeDao;
import com.ambition.agile.modules.relax.entity.HeartCafe;

/**
 * 心灵咖啡屋Service
 * @author harry
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class HeartCafeService extends CrudService<HeartCafeDao, HeartCafe> {

	public HeartCafe get(Integer id) {
		return super.get(id);
	}
	
	public List<HeartCafe> findList(HeartCafe heartCafe) {
		return super.findList(heartCafe);
	}
	
	public Page<HeartCafe> findPage(Page<HeartCafe> page, HeartCafe heartCafe) {
		return super.findPage(page, heartCafe);
	}
	
	@Transactional(readOnly = false)
	public Integer save(HeartCafe heartCafe) {
		
		if (heartCafe.getContent()!=null){
			heartCafe.setContent(StringEscapeUtils.unescapeHtml4(
					heartCafe.getContent()));
		}
		
		
		return super.save(heartCafe);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(HeartCafe heartCafe) {
		return super.delete(heartCafe);
	}
	
}