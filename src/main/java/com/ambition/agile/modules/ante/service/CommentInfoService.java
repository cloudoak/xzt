/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.ante.entity.CommentInfo;
import com.ambition.agile.modules.ante.dao.CommentInfoDao;

/**
 * 评价管理Service
 * @author WHQ
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class CommentInfoService extends CrudService<CommentInfoDao, CommentInfo> {

	public CommentInfo get(Integer id) {
		return super.get(id);
	}
	
	public List<CommentInfo> findList(CommentInfo commentInfo) {
		return super.findList(commentInfo);
	}
	
	public Page<CommentInfo> findPage(Page<CommentInfo> page, CommentInfo commentInfo) {
		return super.findPage(page, commentInfo);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CommentInfo commentInfo) {
		return super.save(commentInfo);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CommentInfo commentInfo) {
		return super.delete(commentInfo);
	}
	
}