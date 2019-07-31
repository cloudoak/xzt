package com.ambition.agile.modules.ante.vo;

import java.util.Date;

public class UserInfoVo {
	
	private String account;
	
	private String name;
	
	private String password;
	
	private String nickName;
	
	private Integer sex;
	
	private Integer nation;
	
	private Date birthday;
	
	private String phone;
	
	private String address;
	
	private Integer organization;
	
	private String email;
	
	private String position;
	
	private String introduction;
	
	private String area;
	
	public UserInfoVo() {
		
	}
	
	public UserInfoVo(String account, String name, String password, String nickName, Integer sex, Integer nation,
			Date birthday, String phone, String address, Integer organization, String email, String position,
			String introduction,String area) {
		this.account = account;
		this.name = name;
		this.password = password;
		this.nickName = nickName;
		this.sex = sex;
		this.nation = nation;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.organization = organization;
		this.email = email;
		this.position = position;
		this.introduction = introduction;
		this.area = area;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getOrganization() {
		return organization;
	}

	public void setOrganization(Integer organization) {
		this.organization = organization;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String areaCode) {
		this.area = areaCode;
	}

}
