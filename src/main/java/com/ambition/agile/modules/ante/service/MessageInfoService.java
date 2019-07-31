/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.ante.dao.MessageInfoDao;
import com.ambition.agile.modules.ante.entity.MessageInfo;

/**
 * 消息管理Service
 * @author fengqq
 * @version 2017-06-25
 */
@Service
@Transactional(readOnly = true)
public class MessageInfoService extends CrudService<MessageInfoDao, MessageInfo> {

	public MessageInfo get(Integer id) {
		return super.get(id);
	}
	
	public List<MessageInfo> findList(MessageInfo messageInfo) {
		return super.findList(messageInfo);
	}
	
	public Page<MessageInfo> findPage(Page<MessageInfo> page, MessageInfo messageInfo) {
		return super.findPage(page, messageInfo);
	}
	
	@Transactional(readOnly = false)
	public Integer save(MessageInfo messageInfo) {
		if(messageInfo != null && messageInfo.getId()==null||messageInfo.getId()==0) {
			messageInfo.setStatus("UNREAD");
		}
		return super.save(messageInfo);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(MessageInfo messageInfo) {
		return super.delete(messageInfo);
	}
	
}