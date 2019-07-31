/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 量表信息Entity
 * @author dortan
 * @version 2017-07-03
 */
public class Scale extends DataEntity<Scale> {
	
	private static final long serialVersionUID = 1L;
	private Integer isLock;		// 是否锁定 0:锁定 1:解锁
	private String name;		// 量表名称
	private Integer typeId;		// 量表类别
	private String introduce;		// 量表介绍
	private String instruction;		// 量表指导语
	private String teacherMustOption;		// 教师必选项
	private String visitorMustOption;		// 来访者必选项
	private String parentMustOption;		// 家长必选项
	private Integer modeid;		// 查看规则
	private String ruleInterpreter;		// 规则解释,不允许用户查看时，必填
	private Integer parentLook;		// 允许家属查看
	private Double integral;		// 所需积分
	private Integer minShowTime;		// 最少显示时间(机构：秒)
	private Integer maxAnswerTime;		// 最大答题时间(机构：分)
	private Integer minAgeLimit;		// 最小年龄限制
	private Integer maxAgeLimit;		// 最大年龄限制
	private Integer isPublic;		// 是否共享量表
	private Integer orgId;		// 所属机构
	private Integer inside; //是否是内置量表
	private Integer family; //是否寺家族量表
	
	public Scale() {
		super();
	}

	public Scale(Integer id){
		super(id);
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	
	@Length(min=0, max=100, message="量表名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	@Length(min=0, max=300, message="教师必选项长度必须介于 0 和 300 之间")
	public String getTeacherMustOption() {
		return teacherMustOption;
	}

	public void setTeacherMustOption(String teacherMustOption) {
		this.teacherMustOption = teacherMustOption;
	}
	
	@Length(min=0, max=300, message="来访者必选项长度必须介于 0 和 300 之间")
	public String getVisitorMustOption() {
		return visitorMustOption;
	}

	public void setVisitorMustOption(String visitorMustOption) {
		this.visitorMustOption = visitorMustOption;
	}
	
	@Length(min=0, max=300, message="家长必选项长度必须介于 0 和 300 之间")
	public String getParentMustOption() {
		return parentMustOption;
	}

	public void setParentMustOption(String parentMustOption) {
		this.parentMustOption = parentMustOption;
	}
	
	public Integer getModeid() {
		return modeid;
	}

	public void setModeid(Integer modeid) {
		this.modeid = modeid;
	}
	
	public String getRuleInterpreter() {
		return ruleInterpreter;
	}

	public void setRuleInterpreter(String ruleInterpreter) {
		this.ruleInterpreter = ruleInterpreter;
	}
	
	public Integer getParentLook() {
		return parentLook;
	}

	public void setParentLook(Integer parentLook) {
		this.parentLook = parentLook;
	}
	
	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}
	
	public Integer getMinShowTime() {
		return minShowTime;
	}

	public void setMinShowTime(Integer minShowTime) {
		this.minShowTime = minShowTime;
	}
	
	public Integer getMaxAnswerTime() {
		return maxAnswerTime;
	}

	public void setMaxAnswerTime(Integer maxAnswerTime) {
		this.maxAnswerTime = maxAnswerTime;
	}
	
	public Integer getMinAgeLimit() {
		return minAgeLimit;
	}

	public void setMinAgeLimit(Integer minAgeLimit) {
		this.minAgeLimit = minAgeLimit;
	}
	
	public Integer getMaxAgeLimit() {
		return maxAgeLimit;
	}

	public void setMaxAgeLimit(Integer maxAgeLimit) {
		this.maxAgeLimit = maxAgeLimit;
	}
	
	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getInside() {
		return inside;
	}

	public void setInside(Integer inside) {
		this.inside = inside;
	}

	public Integer getFamily() {
		return family;
	}

	public void setFamily(Integer family) {
		this.family = family;
	}

}