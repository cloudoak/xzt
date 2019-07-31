/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 系统积分设置Entity
 * @author system_admin
 * @version 2017-07-05
 */
public class SysIntegral extends DataEntity<SysIntegral> {
	
	private static final long serialVersionUID = 1L;
	private String schoolName;		// 机构名称
	private String systemTitle;		// 系统名称
	private Integer pictureSize;		// 上传图片大小
	private Integer article;		// 上传文章大小
	private Integer checkArticle;		// 文章是否需要审核
	private Integer enableIntegral;		// 是否开通积分
	private Integer enableAnonymityAppraise;		// 是否开通匿名评价
	private Integer enableCounselFree;		// 开通咨询收费
	private Integer enableSmsRemind;		// 开通短信提醒
	private Integer enableCounselVoice;		// 开通音频咨询
	private Integer systemClosed;		// 是否关闭系统
	private Integer bookingDays;		// 预约时限
	private Integer surveyTotal;		// 职业测评次数
	private String systemPrompt;		// 关闭系统提示
	private Date timeMark;		// 更新日期
	private String authKey;		// 序列号
	private String smtpHost;		// smtp地址
	private String smtpUser;		// smtp用户名
	private String smtpPwd;		// smtp密码
	private Integer smtpPort;		// 端口号
	private String mailSubject;		// 邮件主题
	private String mailContent;		// 邮件内容
	private String logo;		// 系统Logo
	private Integer zycpIntegral;		// 专业测评积分
	private Integer zhycpIntegral;		// 职业测评积分
	private Integer zxyyIntegral;		// 咨询预约积分
	private Integer ktlyIntegral;		// 课堂留言积分
	private Integer zyzdxwplIntegral;		// 职业指导新闻评论
	private Integer zgwjIntegral;		// 资格文件
	private Integer kjscIntegral;		// 课件上传
	private Integer zyIntegral;		// 资源
	private Integer tgzxIntegral;		// 提供咨询
	private Integer jsddIntegral;		// 接收督导
	private Integer tgddIntegral;		// 提供督导
	private Integer orgId;		// 机构ID
	
	public SysIntegral() {
		super();
	}

	public SysIntegral(Integer id){
		super(id);
	}

	@Length(min=0, max=50, message="机构名称长度必须介于 0 和 50 之间")
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	@Length(min=0, max=50, message="系统名称长度必须介于 0 和 50 之间")
	public String getSystemTitle() {
		return systemTitle;
	}

	public void setSystemTitle(String systemTitle) {
		this.systemTitle = systemTitle;
	}
	
	public Integer getPictureSize() {
		return pictureSize;
	}

	public void setPictureSize(Integer pictureSize) {
		this.pictureSize = pictureSize;
	}
	
	public Integer getArticle() {
		return article;
	}

	public void setArticle(Integer article) {
		this.article = article;
	}
	
	public Integer getCheckArticle() {
		return checkArticle;
	}

	public void setCheckArticle(Integer checkArticle) {
		this.checkArticle = checkArticle;
	}
	
	public Integer getEnableIntegral() {
		return enableIntegral;
	}

	public void setEnableIntegral(Integer enableIntegral) {
		this.enableIntegral = enableIntegral;
	}
	
	public Integer getEnableAnonymityAppraise() {
		return enableAnonymityAppraise;
	}

	public void setEnableAnonymityAppraise(Integer enableAnonymityAppraise) {
		this.enableAnonymityAppraise = enableAnonymityAppraise;
	}
	
	public Integer getEnableCounselFree() {
		return enableCounselFree;
	}

	public void setEnableCounselFree(Integer enableCounselFree) {
		this.enableCounselFree = enableCounselFree;
	}
	
	public Integer getEnableSmsRemind() {
		return enableSmsRemind;
	}

	public void setEnableSmsRemind(Integer enableSmsRemind) {
		this.enableSmsRemind = enableSmsRemind;
	}
	
	public Integer getEnableCounselVoice() {
		return enableCounselVoice;
	}

	public void setEnableCounselVoice(Integer enableCounselVoice) {
		this.enableCounselVoice = enableCounselVoice;
	}
	
	public Integer getSystemClosed() {
		return systemClosed;
	}

	public void setSystemClosed(Integer systemClosed) {
		this.systemClosed = systemClosed;
	}
	
	public Integer getBookingDays() {
		return bookingDays;
	}

	public void setBookingDays(Integer bookingDays) {
		this.bookingDays = bookingDays;
	}
	
	public Integer getSurveyTotal() {
		return surveyTotal;
	}

	public void setSurveyTotal(Integer surveyTotal) {
		this.surveyTotal = surveyTotal;
	}
	
	@Length(min=0, max=100, message="关闭系统提示长度必须介于 0 和 100 之间")
	public String getSystemPrompt() {
		return systemPrompt;
	}

	public void setSystemPrompt(String systemPrompt) {
		this.systemPrompt = systemPrompt;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeMark() {
		return timeMark;
	}

	public void setTimeMark(Date timeMark) {
		this.timeMark = timeMark;
	}
	
	@Length(min=0, max=50, message="序列号长度必须介于 0 和 50 之间")
	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	
	@Length(min=0, max=50, message="smtp地址长度必须介于 0 和 50 之间")
	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	
	@Length(min=0, max=50, message="smtp用户名长度必须介于 0 和 50 之间")
	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}
	
	@Length(min=0, max=50, message="smtp密码长度必须介于 0 和 50 之间")
	public String getSmtpPwd() {
		return smtpPwd;
	}

	public void setSmtpPwd(String smtpPwd) {
		this.smtpPwd = smtpPwd;
	}
	
	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}
	
	@Length(min=0, max=300, message="邮件主题长度必须介于 0 和 300 之间")
	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	
	@Length(min=0, max=1000, message="邮件内容长度必须介于 0 和 1000 之间")
	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	
	@Length(min=0, max=300, message="系统Logo长度必须介于 0 和 300 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public Integer getZycpIntegral() {
		return zycpIntegral;
	}

	public void setZycpIntegral(Integer zycpIntegral) {
		this.zycpIntegral = zycpIntegral;
	}
	
	public Integer getZhycpIntegral() {
		return zhycpIntegral;
	}

	public void setZhycpIntegral(Integer zhycpIntegral) {
		this.zhycpIntegral = zhycpIntegral;
	}
	
	public Integer getZxyyIntegral() {
		return zxyyIntegral;
	}

	public void setZxyyIntegral(Integer zxyyIntegral) {
		this.zxyyIntegral = zxyyIntegral;
	}
	
	public Integer getKtlyIntegral() {
		return ktlyIntegral;
	}

	public void setKtlyIntegral(Integer ktlyIntegral) {
		this.ktlyIntegral = ktlyIntegral;
	}
	
	public Integer getZyzdxwplIntegral() {
		return zyzdxwplIntegral;
	}

	public void setZyzdxwplIntegral(Integer zyzdxwplIntegral) {
		this.zyzdxwplIntegral = zyzdxwplIntegral;
	}
	
	public Integer getZgwjIntegral() {
		return zgwjIntegral;
	}

	public void setZgwjIntegral(Integer zgwjIntegral) {
		this.zgwjIntegral = zgwjIntegral;
	}
	
	public Integer getKjscIntegral() {
		return kjscIntegral;
	}

	public void setKjscIntegral(Integer kjscIntegral) {
		this.kjscIntegral = kjscIntegral;
	}
	
	public Integer getZyIntegral() {
		return zyIntegral;
	}

	public void setZyIntegral(Integer zyIntegral) {
		this.zyIntegral = zyIntegral;
	}
	
	public Integer getTgzxIntegral() {
		return tgzxIntegral;
	}

	public void setTgzxIntegral(Integer tgzxIntegral) {
		this.tgzxIntegral = tgzxIntegral;
	}
	
	public Integer getJsddIntegral() {
		return jsddIntegral;
	}

	public void setJsddIntegral(Integer jsddIntegral) {
		this.jsddIntegral = jsddIntegral;
	}
	
	public Integer getTgddIntegral() {
		return tgddIntegral;
	}

	public void setTgddIntegral(Integer tgddIntegral) {
		this.tgddIntegral = tgddIntegral;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}