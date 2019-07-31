package com.ambition.agile.modules.constant;

/**
 * 
 * 常量字典
 * 
 * @author OAK
 *
 */
public class Constant {
	
	/**
	 * 量锁
	 * 
	 * @author OAK
	 *
	 */
	public enum ScaleLock {
		
		/**
		 * 加锁
		 */
		LOCK(0),
		/**
		 * 解锁
		 */
		UNLOCK(1),
		/**
		 * 未完成
		 */
		UNDONE(2);
		
		private int value;
		
		ScaleLock(int value){
	        this.value = value;
	    }
		
	    public int value(){
	        return value;
	    }
		 
	}
	
	/**
	 * 共享
	 * 
	 * @author OAK
	 *
	 */
	public enum ScaleShared {
		
		/**
		 * 共享
		 */
		SHARED(0),
		/**
		 * 未共享
		 */
		UNSHARED(1);
		
		private int value;
		
		ScaleShared(int value){
	        this.value = value;
	    }
		
	    public int value(){
	        return value;
	    }
		
	}
	
	/**
	 * 内置
	 * 
	 * @author OAK
	 *
	 */
	public enum ScaleInside {
		
		/**
		 * 内置
		 */
		INSIDE(0),
		/**
		 * 未内置
		 */
		UNINSIDE(1);
		
		private int value;
		
		ScaleInside(int value){
	        this.value = value;
	    }
		
	    public int value(){
	        return value;
	    }
		
	}

	/**
	 * 家族关系
	 * 
	 * @author OAK
	 *
	 */
	public enum ScaleFamily {
		
		/**
		 * 家族
		 */
		MANY(0),
		/**
		 * 单亲
		 */
		SINGLE(1);
		
		private int value;
		
		ScaleFamily(int value){
	        this.value = value;
	    }
		
	    public int value(){
	        return value;
	    }
		
	}
	
	/**
	 * 审核状态
	 * 
	 * @author OAK
	 *
	 */
	public enum ApprovalStatus {
		
		/**
		 * 待审核
		 */
		UNAPPROVAL(0),
		/**
		 * 审核通过
		 */
		PASS(1),
		/**
		 * 审核未通过
		 */
		UNPASS(2);
		
		private int value;
		
		ApprovalStatus(int value){
	        this.value = value;
	    }
		
	    public int value(){
	        return value;
	    }
		
	}

}
