/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Orgization;
import com.ambition.agile.modules.sys.entity.User;

/**
 * 答疑室问题Entity
 * @author harry
 * @version 2017-07-06
 */
public class CounselQuestion extends DataEntity<CounselQuestion> {
	
	private static final long serialVersionUID = 1L;
	private Integer askId;		// 咨询者id
	private String askName;		// 咨询者
	private Integer count;		//回复次数
	private Integer counselorId;		// 咨询师id
	private String counselorName;		// 咨询师
	private String title;			//标题
	private String content;		// 提问内容
	private String questionType;		// 咨询问题类型
	private Integer orgId;		// 分校id
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private User user;
	private Office org;
	private Counselor counselor;
	
	public CounselQuestion() {
		super();
	}

	public CounselQuestion(Integer id){
		super(id);
	}

	public Integer getAskId() {
		return askId;
	}

	public void setAskId(Integer askId) {
		this.askId = askId;
	}
	
	@Length(min=0, max=32, message="咨询者长度必须介于 0 和 32 之间")
	public String getAskName() {
		return askName;
	}

	public void setAskName(String askName) {
		this.askName = askName;
	}
	

	@Length(min=0, max=512, message="提问内容长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="咨询问题类型长度必须介于 0 和 1 之间")
	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}

	public String getCounselorName() {
		return counselorName;
	}
	
	//@Length(min=0, max=32, message="咨询师长度必须介于 0 和 32 之间")
	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Office getOrg() {
		return org;
	}

	public void setOrg(Office org) {
		this.org = org;
	}

	public Counselor getCounselor() {
		return counselor;
	}

	public void setCounselor(Counselor counselor) {
		this.counselor = counselor;
	}
		
}