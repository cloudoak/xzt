/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 消息管理Entity
 * @author fengqq
 * @version 2017-06-25
 */
public class MessageInfo extends DataEntity<MessageInfo> {
	
	private static final long serialVersionUID = 1L;
	private String fromUser;		// 发送者
	private String toUser;		// 收件人
	private String title;		// 主题
	private String content;		// 内容
	private String status;		// 状态
	
	public MessageInfo() {
		super();
	}

	public MessageInfo(Integer id){
		super(id);
	}

	@Length(min=0, max=30, message="发送者长度必须介于 0 和 30 之间")
	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	
	@Length(min=0, max=30, message="收件人长度必须介于 0 和 30 之间")
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	
	@Length(min=0, max=50, message="主题长度必须介于 0 和 50 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}