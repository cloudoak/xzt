/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.sys.dao.ShareDao;
import com.ambition.agile.modules.sys.entity.Share;

/**
 * 共享积分Service
 * @author shenclus
 * @version 2017-12-11
 */
@Service
@Transactional(readOnly = true)
public class ShareService extends CrudService<ShareDao, Share> {

	public Share get(Integer id) {
		return super.get(id);
	}
	
	public List<Share> findList(Share share) {
		return super.findList(share);
	}
	
	public Page<Share> findPage(Page<Share> page, Share share) {
		return super.findPage(page, share);
	}
	
	@Transactional(readOnly = false)
	public Integer save(Share share) {
		return super.save(share);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Share share) {
		return super.delete(share);
	}
	
}