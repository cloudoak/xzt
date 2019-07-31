/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.archive.entity;

import java.util.Date;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.Office;

/**
 * 档案Entity
 * @author OAK
 * @version 1.0
 * @since 2018/1/12
 */
public class Archives extends DataEntity<Archives> {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;	 //用户标识
	private Office org;		 //所在机构
	private String userName; //用户名
	private String name;  //姓名
	private Integer sex;   //性别
	private Integer age;	 //年龄
	private Integer authType; //身份
	private Date loginDate;
	
	public Archives() {
		super();
	}

	public Archives(Integer id){
		super(id);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Office getOrg() {
		return org;
	}

	public void setOrg(Office org) {
		this.org = org;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
}