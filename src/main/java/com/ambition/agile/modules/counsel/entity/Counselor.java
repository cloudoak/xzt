/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.entity;

import java.util.Date;
import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.ante.entity.CounselorType;
import com.ambition.agile.modules.ante.entity.Department;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;

/**
 * 
 * 咨询师
 * 
 * @author OAK
 * @since 2017/12/06
 * @version 1.0
 * 
 * <blockquote>
 * 咨询师分为认证的咨询师、普通咨询师，咨询师又分为外部咨询师与内部的差别
 * </blockquote>
 */
public class Counselor extends DataEntity<Counselor> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 出生日期
	 */
	private Date birthday;
	/**
	 * 简介
	 */
	private String instro;
	/**
	 * 资格证书
	 */
	private String certificatePath;
	/**
	 * 认证状态：0【未提交】、1【提交申请】、2【审核通过】、3【审核未通过】
	 * (通过上传资质文件成为认证的咨询师)
	 */
	private Integer applyStatus;
	/**
	 * 是否推荐到首页
	 * (该咨询师能在首页面出现)
	 */
	private Integer indexFlag;
	/**
	 * 是否内部/外部咨询师
	 *（其它单位的咨询师能看到）
	 */
	private Integer insideFlag;
	/**
	 * 所属机构
	 */
	private Integer orgId;	
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 职务
	 */
	private String job;
	/**
	 * 职称
	 */
	private String jobName;
	/**
	 * 审批意见
	 */
	private String approvalComments;
	/**
	 * 审核状态：0【待审核】、1【审核通过】、2【审核未通过】
	 */
	private Integer status;
	/**
	 * 民族
	 */
	private Integer nation;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 教师类型
	 */
	private Integer counselorType;
	
	/**
	 * 部门
	 */
	private Integer deptId;
	
	private Office org;
	
	private User user;
	
	private Department department;
	
	private CounselorType counselType;

	public Counselor() {
		super();
	}

	public Counselor(Integer id){
		super(id);
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
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getInstro() {
		return instro;
	}

	public void setInstro(String instro) {
		this.instro = instro;
	}
	
	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}
	
	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	public Integer getIndexFlag() {
		return indexFlag;
	}

	public void setIndexFlag(Integer indexFlag) {
		this.indexFlag = indexFlag;
	}

	public String getApprovalComments() {
		return approvalComments;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getInsideFlag() {
		return insideFlag;
	}

	public void setInsideFlag(Integer insideFlag) {
		this.insideFlag = insideFlag;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getJob() {
		return job;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCounselorType() {
		return counselorType;
	}

	public void setCounselorType(Integer counselorType) {
		this.counselorType = counselorType;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public CounselorType getCounselType() {
		return counselType;
	}

	public void setCounselType(CounselorType counselType) {
		this.counselType = counselType;
	}

}