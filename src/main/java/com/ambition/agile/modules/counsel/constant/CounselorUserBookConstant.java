package com.ambition.agile.modules.counsel.constant;

/** 
 * 
 * 咨询师预约常量类
 * 
 * @author OAK 
 * @since 2017/12/04
 * @version 1.0
 * 
 * <blockquote>
 * (1)、预约状态：0【未处理】、1【接受预约】、2【取消预约】、3【咨询已结束】
 * </blockquote>
 */
public class CounselorUserBookConstant{
	
	/**
	 * 未处理
	 */
	public static final Integer NOT_PROCESSED = 0;
	/**
	 * 接受预约
	 */
    public static final Integer ACCEPT  = 1;
    /**
     * 取消预约
     */
    public static final Integer CANCEL  = 2;
    /**
     * 咨询已结束
     */
    public static final Integer OVER  = 3;
    /**
     * 咨询空闲
     */
    public static final Integer IDLE  = 4;
    /**
     * 
     */
    public static final Integer COUNSELOR_SUBMIT = 0;
    /**
     * 
     */
    public static final Integer VISITOR_SUBMIT = 1;
}
