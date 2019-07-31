/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 评价管理Entity
 * @author WHQ
 */
public class CommentInfo extends DataEntity<CommentInfo> {
	
	private static final long serialVersionUID = 1L;
	private String title;			// 评论标题
	private String content;			// 评论内容
	private String rangeType;		// 评价范围类型
	private String userId1;			// 评价人登录名
	private String userName1;		// 评价人姓名
	private Integer userType1;		// 评价人用户类型
	private String userId2;			// 被评价人登录名
	private String userName2;		// 被评价人姓名
	private Integer userType2;		// 被评价人用户类型
	private String commentType;		// 评价类型
	private String orgId;			// 所属机构
	private String powerType;		// 权限
	
	public CommentInfo() {
		super();
	}

	public CommentInfo(Integer id){
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getRangeType() {
		return rangeType;
	}

	public String getUserId1() {
		return userId1;
	}

	public String getUserName1() {
		return userName1;
	}

	public Integer getUserType1() {
		return userType1;
	}

	public void setUserType1(Integer userType1) {
		this.userType1 = userType1;
	}

	public String getUserId2() {
		return userId2;
	}

	public String getUserName2() {
		return userName2;
	}

	public String getCommentType() {
		return commentType;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getPowerType() {
		return powerType;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRangeType(String rangeType) {
		this.rangeType = rangeType;
	}

	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}

	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}

	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}

	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}

	public Integer getUserType2() {
		return userType2;
	}

	public void setUserType2(Integer userType2) {
		this.userType2 = userType2;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

}