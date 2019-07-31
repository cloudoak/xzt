/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 家长 基本功能Entity
 * @author fengqq
 * @version 2017-07-06
 */
public class Parents extends DataEntity<Parents> {
	
	private static final long serialVersionUID = 1L;
	private String parentNo;		// 家长编号
	private String account;			// 用户名
	private String password;		// 密码
	private String name;			// 家长姓名
	private Integer gender;			// 性别
	private Integer age;				// 年龄
	private Integer nationId;		// 民族ID
	private Date birthday;			// 出生年月
	private String email;			// 邮箱
	private String phone;			// 联系电话
	private java.math.BigDecimal householdIncome;	// 家庭年收入
	private Integer householdTotal;	// 家庭总人数
	private Integer householdBsTotal;// 兄弟姐妹数
	private Integer isLwyp;			// 是否与父母同住0是，1否，2其它
	private Date lastLoginTime;		// 最后登录时间
	private Integer loginCount;		// 登录次数
	private Integer isCheck;			// 是否审核0是，1否
	private String studentCode;		// 学生编号
	private Integer state;			// 用户状态0未审核，1审核通过 ， 2 审核拒绝
	private Integer orgId;			// 机构ID
	private String reason; 			// 原因
	private Integer userId; 		// 用户系统ID
	private String parentName; 		// 用户系统ID
	
	private Office org;
	
	private User user;
	
	private VisitorInfo visitorInfo;
	
	public Parents() {
		super();
	}

	public Parents(Integer id){
		super(id);
	}

	@Length(min=0, max=20, message="家长编号长度必须介于 0 和 20 之间")
	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	
	@Length(min=0, max=20, message="用户名长度必须介于 0 和 20 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=0, max=32, message="密码长度必须介于 0 和 32 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=20, message="家长姓名长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="性别长度必须介于 0 和 11 之间")
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=11, message="年龄长度必须介于 0 和 11 之间")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Length(min=0, max=11, message="民族ID长度必须介于 0 和 11 之间")
	public Integer getNationId() {
		return nationId;
	}

	public void setNationId(Integer nationId) {
		this.nationId = nationId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=50, message="邮箱长度必须介于 0 和 50 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=11, message="联系电话长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public java.math.BigDecimal getHouseholdIncome() {
		return householdIncome;
	}

	public void setHouseholdIncome(java.math.BigDecimal householdIncome) {
		this.householdIncome = householdIncome;
	}
	
	@Length(min=0, max=11, message="家庭总人数长度必须介于 0 和 11 之间")
	public Integer getHouseholdTotal() {
		return householdTotal;
	}

	public void setHouseholdTotal(Integer householdTotal) {
		this.householdTotal = householdTotal;
	}
	
	@Length(min=0, max=11, message="兄弟姐妹数长度必须介于 0 和 11 之间")
	public Integer getHouseholdBsTotal() {
		return householdBsTotal;
	}

	public void setHouseholdBsTotal(Integer householdBsTotal) {
		this.householdBsTotal = householdBsTotal;
	}
	
	@Length(min=0, max=11, message="是否与父母同住0是，1否，2其它长度必须介于 0 和 11 之间")
	public Integer getIsLwyp() {
		return isLwyp;
	}

	public void setIsLwyp(Integer isLwyp) {
		this.isLwyp = isLwyp;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Length(min=0, max=11, message="登录次数长度必须介于 0 和 11 之间")
	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	@Length(min=0, max=11, message="是否审核0是，1否长度必须介于 0 和 11 之间")
	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	
	@Length(min=0, max=20, message="学生编号长度必须介于 0 和 20 之间")
	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	
	@Length(min=0, max=11, message="用户状态0正常，1异常长度必须介于 0 和 11 之间")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@Length(min=0, max=11, message="机构ID长度必须介于 0 和 11 之间")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=0, max=200, message="审核通过或拒绝理由在0到200个字符之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Office getOrg() {
		return org;
	}

	public void setOrg(Office org) {
		this.org = org;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VisitorInfo getVisitorInfo() {
		return visitorInfo;
	}

	public void setVisitorInfo(VisitorInfo visitorInfo) {
		this.visitorInfo = visitorInfo;
	}
	
}