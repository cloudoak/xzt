/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 来访者Entity
 * @author dortan
 * @version 2017-08-04
 */
public class VisitorInfo extends DataEntity<VisitorInfo> {
	
	private static final long serialVersionUID = 1L;
	
	private String visitorNo;			// 编号
	private String nickName;			// 昵称
	private Integer sex;				// 性别
	private Integer nation;				// 民族
	private Date birthday;				// 出生年月
	private Integer age;				// 年龄
	private String nativePlace;			// 籍贯
	private String phoneNum;			// 联系电话
	private String email;				// 电子邮件
	private String address;				// 家庭住址
	private Integer isCity;				// 是否城镇
	private Integer isBoarder;			// 是否住宿生
	private Integer isStudentCadre;		// 是否学生干部
	private String hobby;				// 爱好
	private String speciality;			// 特长
	private String childhoodExperience;	// 童年成长经历
	private String physicalConditions;	// 身体健康情况
	private String academicConditions;	// 学业情况
	private String rewardsConditions;	// 奖惩情况
	private String selfAssessment;		// 自我评价
	private Integer isSingleParent;		// 是否单亲
	private Integer isLwyp;				// 是否与父母同住
	private Integer familyConstellation;// 家庭排行
	private String fatherName;			// 父亲姓名
	private Integer fatherAge;			// 父亲年龄
	private String fatherPhone;			// 父亲电话
	private String fatherEducation;		// 父亲受教育水平
	private String fatherDuty;			// 父亲职业
	private String fatherJob;			// 父亲职务
	private String motherName;			// 母亲姓名
	private Integer motherAge;			// 母亲年龄
	private String motherPhone;			// 母亲电话
	private String motherEducation;		// 母亲受教育水平
	private String motherDuty;			// 母亲职业
	private String motherJob;			// 母亲职务
	private Integer isFamilyHistory;	// 直系亲属是否有病史
	private String familyHistory;		// 直系亲属病史
	private String familyRelationship;	// 家庭中人际关系气氛
	private String familyInformation;	// 亲朋好友基本情况及联系方式
	private Integer isPoverty;			// 是否贫困
	private Double householdIncome;		// 家庭年收入
	private Integer householdBsTotal;	// 兄弟姐妹数
	private Integer householdTotal;		// 家庭总人数
	
	private Integer userId;				// 用户号
	private Integer orgId;				// 机构
	private Integer beginAge;			// 开始 年龄
	private Integer endAge;				// 结束 年龄
	private Integer startNo;			// 范围
	private Integer endNo;				// 范围
	private Integer starLen;			// 通配符
	private String password;			// 密码
	private Integer status;				// 审核状态
	private boolean isNewRecord;		// 是否新纪录
	
	private Office org;//关联组织
	
	private User user;//关联用户
	
	private VisitorInfo visitor;
	
	public Integer getStartNo() {
		return startNo;
	}

	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}

	public Integer getEndNo() {
		return endNo;
	}

	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}

	public VisitorInfo() {
		super();
	}

	public VisitorInfo(Integer id){
		super(id);
	}

	@Length(min=0, max=30, message="学号长度必须介于 0 和 30 之间")
	public String getVisitorNo() {
		return visitorNo;
	}

	public void setVisitorNo(String visitorNo) {
		this.visitorNo = visitorNo;
	}
	
	@Length(min=0, max=64, message="昵称长度必须介于 0 和 64 之间")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Length(min=0, max=20, message="籍贯长度必须介于 0 和 20 之间")
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=0, max=30, message="电子邮件长度必须介于 0 和 30 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=50, message="家庭住址长度必须介于 0 和 50 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getIsCity() {
		return isCity;
	}

	public void setIsCity(Integer isCity) {
		this.isCity = isCity;
	}
	
	public Integer getIsBoarder() {
		return isBoarder;
	}

	public void setIsBoarder(Integer isBoarder) {
		this.isBoarder = isBoarder;
	}
	
	public Integer getIsStudentCadre() {
		return isStudentCadre;
	}

	public void setIsStudentCadre(Integer isStudentCadre) {
		this.isStudentCadre = isStudentCadre;
	}
	
	@Length(min=0, max=255, message="爱好长度必须介于 0 和 255 之间")
	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	@Length(min=0, max=255, message="特长长度必须介于 0 和 255 之间")
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	@Length(min=0, max=255, message="童年成长经历长度必须介于 0 和 255 之间")
	public String getChildhoodExperience() {
		return childhoodExperience;
	}

	public void setChildhoodExperience(String childhoodExperience) {
		this.childhoodExperience = childhoodExperience;
	}
	
	@Length(min=0, max=255, message="身体健康情况长度必须介于 0 和 255 之间")
	public String getPhysicalConditions() {
		return physicalConditions;
	}

	public void setPhysicalConditions(String physicalConditions) {
		this.physicalConditions = physicalConditions;
	}
	
	@Length(min=0, max=255, message="学业情况长度必须介于 0 和 255 之间")
	public String getAcademicConditions() {
		return academicConditions;
	}

	public void setAcademicConditions(String academicConditions) {
		this.academicConditions = academicConditions;
	}
	
	@Length(min=0, max=255, message="奖惩情况长度必须介于 0 和 255 之间")
	public String getRewardsConditions() {
		return rewardsConditions;
	}

	public void setRewardsConditions(String rewardsConditions) {
		this.rewardsConditions = rewardsConditions;
	}
	
	@Length(min=0, max=255, message="自我评价长度必须介于 0 和 255 之间")
	public String getSelfAssessment() {
		return selfAssessment;
	}

	public void setSelfAssessment(String selfAssessment) {
		this.selfAssessment = selfAssessment;
	}
	
	public Integer getIsSingleParent() {
		return isSingleParent;
	}

	public void setIsSingleParent(Integer isSingleParent) {
		this.isSingleParent = isSingleParent;
	}
	
	public Integer getIsLwyp() {
		return isLwyp;
	}

	public void setIsLwyp(Integer isLwyp) {
		this.isLwyp = isLwyp;
	}
	
	public Integer getFamilyConstellation() {
		return familyConstellation;
	}

	public void setFamilyConstellation(Integer familyConstellation) {
		this.familyConstellation = familyConstellation;
	}
	
	@Length(min=0, max=30, message="父亲姓名长度必须介于 0 和 30 之间")
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	public Integer getFatherAge() {
		return fatherAge;
	}

	public void setFatherAge(Integer fatherAge) {
		this.fatherAge = fatherAge;
	}
	
	@Length(min=0, max=20, message="父亲电话长度必须介于 0 和 20 之间")
	public String getFatherPhone() {
		return fatherPhone;
	}

	public void setFatherPhone(String fatherPhone) {
		this.fatherPhone = fatherPhone;
	}
	
	@Length(min=0, max=255, message="父亲受教育水平长度必须介于 0 和 255 之间")
	public String getFatherEducation() {
		return fatherEducation;
	}

	public void setFatherEducation(String fatherEducation) {
		this.fatherEducation = fatherEducation;
	}
	
	@Length(min=0, max=255, message="父亲职业长度必须介于 0 和 255 之间")
	public String getFatherDuty() {
		return fatherDuty;
	}

	public void setFatherDuty(String fatherDuty) {
		this.fatherDuty = fatherDuty;
	}
	
	@Length(min=0, max=255, message="父亲职务长度必须介于 0 和 255 之间")
	public String getFatherJob() {
		return fatherJob;
	}

	public void setFatherJob(String fatherJob) {
		this.fatherJob = fatherJob;
	}
	
	@Length(min=0, max=30, message="母亲姓名长度必须介于 0 和 30 之间")
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	public Integer getMotherAge() {
		return motherAge;
	}

	public void setMotherAge(Integer motherAge) {
		this.motherAge = motherAge;
	}
	
	@Length(min=0, max=20, message="母亲电话长度必须介于 0 和 20 之间")
	public String getMotherPhone() {
		return motherPhone;
	}

	public void setMotherPhone(String motherPhone) {
		this.motherPhone = motherPhone;
	}
	
	@Length(min=0, max=255, message="母亲受教育水平长度必须介于 0 和 255 之间")
	public String getMotherEducation() {
		return motherEducation;
	}

	public void setMotherEducation(String motherEducation) {
		this.motherEducation = motherEducation;
	}
	
	@Length(min=0, max=255, message="母亲职业长度必须介于 0 和 255 之间")
	public String getMotherDuty() {
		return motherDuty;
	}

	public void setMotherDuty(String motherDuty) {
		this.motherDuty = motherDuty;
	}
	
	@Length(min=0, max=255, message="母亲职务长度必须介于 0 和 255 之间")
	public String getMotherJob() {
		return motherJob;
	}

	public void setMotherJob(String motherJob) {
		this.motherJob = motherJob;
	}
	
	public Integer getIsFamilyHistory() {
		return isFamilyHistory;
	}

	public void setIsFamilyHistory(Integer isFamilyHistory) {
		this.isFamilyHistory = isFamilyHistory;
	}
	
	@Length(min=0, max=255, message="直系亲属病史长度必须介于 0 和 255 之间")
	public String getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}
	
	@Length(min=0, max=255, message="家庭中人际关系气氛长度必须介于 0 和 255 之间")
	public String getFamilyRelationship() {
		return familyRelationship;
	}

	public void setFamilyRelationship(String familyRelationship) {
		this.familyRelationship = familyRelationship;
	}
	
	@Length(min=0, max=255, message="亲朋好友基本情况及联系方式长度必须介于 0 和 255 之间")
	public String getFamilyInformation() {
		return familyInformation;
	}

	public void setFamilyInformation(String familyInformation) {
		this.familyInformation = familyInformation;
	}
	
	public Integer getIsPoverty() {
		return isPoverty;
	}

	public void setIsPoverty(Integer isPoverty) {
		this.isPoverty = isPoverty;
	}
	
	public Double getHouseholdIncome() {
		return householdIncome;
	}

	public void setHouseholdIncome(Double householdIncome) {
		this.householdIncome = householdIncome;
	}
	
	public Integer getHouseholdBsTotal() {
		return householdBsTotal;
	}

	public void setHouseholdBsTotal(Integer householdBsTotal) {
		this.householdBsTotal = householdBsTotal;
	}
	
	public Integer getHouseholdTotal() {
		return householdTotal;
	}

	public void setHouseholdTotal(Integer householdTotal) {
		this.householdTotal = householdTotal;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Integer getBeginAge() {
		return beginAge;
	}

	public void setBeginAge(Integer beginAge) {
		this.beginAge = beginAge;
	}
	
	public Integer getEndAge() {
		return endAge;
	}

	public void setEndAge(Integer endAge) {
		this.endAge = endAge;
	}

	public Integer getStarLen() {
		return starLen;
	}

	public void setStarLen(Integer starLen) {
		this.starLen = starLen;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isNewRecord() {
		return isNewRecord;
	}

	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
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

	public VisitorInfo getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorInfo visitor) {
		this.visitor = visitor;
	}

}