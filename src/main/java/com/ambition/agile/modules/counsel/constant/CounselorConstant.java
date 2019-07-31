package com.ambition.agile.modules.counsel.constant;

/** 
 * 
 * 咨询师常量类
 * 
 * @author OAK 
 * @since 2017/12/04
 * @version 1.0
 * 
 * <blockquote>
 * (1)、认证状态：0【未提交】、1【提交申请】、2【审核通过】、3【审核未通过】
 * (通过上传资质文件成为认证的咨询师)
 * 
 * (2)、审核状态：0【待审核】、1【审核通过】、2【审核未通过】
 * </blockquote>
 */
public class CounselorConstant {
	/**
	 * 待审核
	 */
    public static final Integer STATUS_AUDIT = 0;
    /**
     * 审核通过
     */
    public static final Integer STATUS_PASSABLE = 1;
    /**
     * 审核驳回(不通过)
     */
    public static final Integer STATUS_REJECT = 2;
	/**
	 * 审核未提交
	 */
	public static final Integer APPLY_STATUS_NOSUBMIT = 0;
	/**
	 * 审核提交
	 */
	public static final Integer APPLY_STATUS_SUBMIT = 1;
	/**
	 * 审核通过
	 */
	public static final Integer APPLY_STATUS_PASSABLE = 2;
	/**
	 * 审核驳回(不通过)
	 */
	public static final Integer APPLY_STATUS_REJECT = 3;
	
}
