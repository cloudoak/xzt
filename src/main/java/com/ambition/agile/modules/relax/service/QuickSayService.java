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
import com.ambition.agile.modules.relax.dao.QuickSayDao;
import com.ambition.agile.modules.relax.entity.QuickSay;

/**
 * 一吐为快Service
 * @author harry
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class QuickSayService extends CrudService<QuickSayDao, QuickSay> {

	public QuickSay get(Integer id) {
		return super.get(id);
	}
	
	public List<QuickSay> findList(QuickSay quickSay) {
		return super.findList(quickSay);
	}
	
	public Page<QuickSay> findPage(Page<QuickSay> page, QuickSay quickSay) {
		return super.findPage(page, quickSay);
	}
	
	@Transactional(readOnly = false)
	public Integer save(QuickSay quickSay) {
		
		if (quickSay.getContent()!=null){
			quickSay.setContent(StringEscapeUtils.unescapeHtml4(
					quickSay.getContent()));
		}
		return super.save(quickSay);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(QuickSay quickSay) {
		return super.delete(quickSay);
	}
	
}