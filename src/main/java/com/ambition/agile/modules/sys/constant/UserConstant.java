package com.ambition.agile.modules.sys.constant;

/** 
 * @ClassName: UserConstant 
 * @Description: TODO( CounselorConstants 静态类,常用操作) 
 * @author OAK 
 * @date 2017年8月5日 下午3:47:45 
 *  
*/
public class UserConstant{
	/**
	 * 平台管理员
	 */
    public static final Integer USER_ADMIN_TYPE = 0;
    /**
     * 机构默认管理员
     */
    public static final Integer USER_ORG_ADMIN_TYPE = 1;
    /**
     * 咨询师
     */
    public static final Integer USER_CONSULTANT_TYPE = 2;
    /**
     * 家属
     */
    public static final Integer USER_FAMILYMEMBERS_TYPE = 3;
    /**
     * 来访者
     */
    public static final Integer USER_VISITOR_TYPE = 4;
    
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
	
}
